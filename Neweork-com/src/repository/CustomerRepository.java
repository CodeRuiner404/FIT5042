package repository;

import java.util.List;

import javax.ejb.Remote;

import entities.Contact;
import entities.Customer;
import entities.Staff;


@Remote
public interface CustomerRepository {

    public List<Customer> getAllCustomer() throws Exception;
    public void editCustomer(Customer customer) throws Exception;
    public void addCustomer(Customer customer) throws Exception;
    public void deleteCustomer(int customer_ID) throws Exception;
    public void adminEditCustomer(Customer customer) throws Exception;
    public Customer searchCustomerById(int id) throws Exception;
    public List<Customer> searchCustomerByGMV(double GMV) throws Exception;
    public void addContact (Contact contact) throws Exception;
    public Customer searchCustomerByContactId(int contactId)throws Exception;
    public void userAddCustomer(Customer customer) throws Exception;
}
