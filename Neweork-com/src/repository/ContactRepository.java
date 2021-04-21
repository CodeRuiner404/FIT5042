package repository;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import entities.Contact;
import entities.Customer;

@Remote
public interface ContactRepository {	
	
	public List<Contact> getAllContacts() throws Exception;
	public void addContact (Contact contact) throws Exception;
	public void deleteContact(int id) throws Exception;
	public void staffDeleteContact(int id) throws Exception;
	public void editContact(Contact contact) throws Exception;
	public Contact searchContactById(int id) throws Exception;
	public List<Contact> searchContactByGMV(double GMV) throws Exception;


}
