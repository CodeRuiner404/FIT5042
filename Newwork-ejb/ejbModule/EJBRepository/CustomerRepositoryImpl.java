package EJBRepository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import entities.AppUser;
import entities.Contact;
import entities.Customer;
import entities.Staff;
import repository.CustomerRepository;

@Stateless
public class CustomerRepositoryImpl implements CustomerRepository {
	
	@PersistenceContext(unitName= "Assignment_C_ejb")
	private EntityManager entityManager;

	
	@Override
	public List<Customer> getAllCustomer() throws Exception {
		return entityManager.createNamedQuery(Customer.GET_ALL_CUSTOMER_NAME).getResultList();
	}
	
	@Override
	public void editCustomer(Customer customer) throws Exception{
		try {			
			Staff staff = customer.getCustomer_user();		
				for(Customer cus:staff.getStaffCustomers()) {
					if(cus.getCustomer_ID()== customer.getCustomer_ID()) {
						staff.getStaffCustomers().remove(cus);
						staff.getStaffCustomers().add(customer);
					}
				}

				entityManager.merge(staff);	
			
		} catch(Exception ex) {
			
		}
		
	}
	
	@Override
	public void adminEditCustomer(Customer customer) throws Exception{
		try {			
			Staff staff = customer.getCustomer_user();		
				for(Customer cus:staff.getStaffCustomers()) {
					if(cus.getCustomer_ID()== customer.getCustomer_ID()) {
						staff.getStaffCustomers().remove(cus);
						staff.getStaffCustomers().add(customer);
					}
				}

				entityManager.merge(staff);	
			
		} catch(Exception ex) {
			
		}
	}
	
	@Override
	public void addCustomer(Customer customer) throws Exception{
		List<Customer> customers = entityManager.createNamedQuery(Customer.GET_ALL_CUSTOMER_NAME).getResultList();
		
		if (customers.size()==0) {
			customer.setCustomer_ID(1);
		}else {
			customer.setCustomer_ID(customers.get(0).getCustomer_ID()+1);
		}
		
		
//		Staff staff = customer.getCustomer_user();
//		staff.getStaffCustomers().add(customer);
//		entityManager.merge(staff);
		
		entityManager.persist(customer);
		

		
	}
	
	@Override
	public void userAddCustomer(Customer customer) throws Exception{
		List<Customer> customers = entityManager.createNamedQuery(Customer.GET_ALL_CUSTOMER_NAME).getResultList();
		customer.setCustomer_ID(customers.get(0).getCustomer_ID()+1);
		Staff staff = customer.getCustomer_user();
		staff.getStaffCustomers().add(customer);
		entityManager.merge(staff);
	}
	
	
	@Override
	public void deleteCustomer(int customer_ID) throws Exception{
		Customer customer = searchCustomerById(customer_ID);
		if(customer != null) {
			entityManager.remove(customer);
		}
		
	}
	
	@Override
	public Customer searchCustomerById(int id) throws Exception{
		Customer customer = entityManager.find(Customer.class, id);
		return customer;		
	}
	
	@Override
	public List<Customer> searchCustomerByGMV(double GMV) throws Exception{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery query = builder.createQuery(Customer.class);
		Root<Customer> ct = query.from(Customer.class);
		query.select(ct).where(builder.le(ct.get("customer_GMV").as(Double.class),GMV));
		List<Customer> cl = entityManager.createQuery(query).getResultList();		
		return cl;
	}
	
	@Override
	public void addContact (Contact contact) throws Exception{
		List<Contact> contacts =  entityManager.createNamedQuery(Contact.GET_ALL_CONTACT_NAME).getResultList();
		contact.setContactID(contacts.get(0).getContactID()+1);
//		Customer customer = contact.getContact_company();
//		customer.getCustomer_contacts().add(contact);
//		entityManager.merge(customer);
		entityManager.persist(contact);
	}
	
	@Override
	public Customer searchCustomerByContactId(int contactId)throws Exception{
		Contact contact = entityManager.find(Contact.class, contactId);
		int id = contact.getContact_company().getCustomer_ID();
		Customer customer = entityManager.find(Customer.class, id);
		return customer;
	}
	

	
	

	
}
