package EJBRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import entities.Contact;
import entities.Customer;
import entities.Staff;
import repository.ContactRepository;



@Stateless
public class ContactRepositoryImpl implements ContactRepository{
	@PersistenceContext(unitName = "Assignment_C_ejb")
	private EntityManager entityManager;

	@Override
	public List<Contact> getAllContacts() throws Exception {
		return entityManager.createNamedQuery(Contact.GET_ALL_CONTACT_NAME).getResultList();
	}
	
	@Override
	public void addContact (Contact contact) throws Exception{
		List<Contact> contacts =  entityManager.createNamedQuery(Contact.GET_ALL_CONTACT_NAME).getResultList();
		if(contacts.size()==0) {
			contact.setContactID(1);
		}else {
			contact.setContactID(contacts.get(0).getContactID()+1);
		}
		Customer customer = contact.getContact_company();
		customer.getCustomer_contacts().add(contact);
		entityManager.merge(customer);
//		entityManager.persist(contact);
		
	}
	
	
	@Override
	public void deleteContact(int id) throws Exception{
		Contact contact = searchContactById(id);
		if(contact != null) {
			Customer customer = contact.getContact_company();
			for(Contact con:customer.getCustomer_contacts()) {
			 if(con.getContactID()==id) {
				 customer.getCustomer_contacts().remove(con);
				 
			 }
			}
			entityManager.merge(customer);
							
		}
		
	}
	
	@Override
	public void staffDeleteContact(int id) throws Exception{
		Contact contact = searchContactById(id);
		Staff staff = contact.getContact_company().getCustomer_user();
		for(Customer cus : staff.getStaffCustomers()) {
			if(cus.getCustomer_ID()==contact.getContact_company().getCustomer_ID()) {
				for(Contact con:cus.getCustomer_contacts()) {
					if(con.getContactID()==id) {
						cus.getCustomer_contacts().remove(con);
					}
				}
			}
		}
		entityManager.merge(staff);
	}
	
	
	@Override
	public void editContact(Contact contact) throws Exception{
		try {
			entityManager.merge(contact);
		} catch (Exception ex) {
			
		}
	}
	
	
	
	@Override
	public Contact searchContactById(int id) throws Exception{
		Contact contact = entityManager.find(Contact.class, id);
		return contact;
		
	}
	
	@Override
	public List<Contact> searchContactByGMV(double GMV) throws Exception{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery query = builder.createQuery(Contact.class);
		Root<Contact> c = query.from(Contact.class);
		query.select(c).where(builder.le(c.get("contact_GMV").as(Double.class),GMV));
		List<Contact> cl = entityManager.createQuery(query).getResultList();
		return cl;
		
	}


}
