package mBeans;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entities.Contact;
import entities.Customer;
import repository.ContactRepository;
import repository.CustomerRepository;



@ManagedBean(name = "contactManagedBean")
@SessionScoped
public class ContactManagedbean implements Serializable{
	@EJB
	ContactRepository contactRepository;
	@EJB
	CustomerRepository customerRepository;
	
	
	
	public List<Contact> getAllContacts(){
		try {
			return contactRepository.getAllContacts();
		} catch(Exception ex) {
			Logger.getLogger(ContactManagedbean.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}
	
	public void addContact (Contact contact) {
		try {		
			//contactRepository.addContact(contact);
			customerRepository.addContact(contact);
		} catch (Exception ex) {
			Logger.getLogger(ContactManagedbean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void customerAddTheContact(Contact contact) {
		try {		
			contactRepository.addContact(contact);
		} catch (Exception ex) {
			Logger.getLogger(ContactManagedbean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void deleteContact(int id) {
		try {
			contactRepository.deleteContact(id);
		} catch(Exception ex) {
			Logger.getLogger(ContactManagedbean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void staffDeleteContact(int id) {
		try {
			contactRepository.staffDeleteContact(id);;
		} catch(Exception ex) {
			Logger.getLogger(ContactManagedbean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	public void editContact(Contact contact) {
		try {
			contactRepository.editContact(contact);;
		} catch(Exception ex) {
			Logger.getLogger(ContactManagedbean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public Contact searchContactById(int ID) {
		try {
			return contactRepository.searchContactById(ID);
		} catch(Exception ex) {
			Logger.getLogger(ContactManagedbean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
		
	}
	
	public List<Contact> searchContactByGMV(double GMV){
		try {
			return contactRepository.searchContactByGMV(GMV);
		} catch(Exception ex) {
			Logger.getLogger(ContactManagedbean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
}
