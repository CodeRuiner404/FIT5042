package mBeans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import entities.Contact;
import entities.Customer;
import repository.CustomerRepository;



@ManagedBean(name = "customerManagedBean")
@SessionScoped
public class CustomerManagedBean implements Serializable{
	@EJB
	CustomerRepository customerRepository;
	
    public CustomerManagedBean() {
		
	}
    
    public List<Customer> getAllCustomer(){
		try {
			List<Customer> customers = customerRepository.getAllCustomer();
			return customers;
			
		} catch(Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
		
		return null;
	}
    
    public void editCustomer(Customer customer) {
    	try {
    		customerRepository.editCustomer(customer);
    	}catch(Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
    }
    
    public void addCustomer(Customer customer) {
    	try {
    		customerRepository.addCustomer(customer);
    	} catch (Exception ex) {
    		Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,ex);
    	}
    }
    
    public void userAddCustomer(Customer customer) {
    	try {
    		customerRepository.userAddCustomer(customer);
    	} catch (Exception ex) {
    		Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,ex);
    	}
    }
    
    public void deleteCustomer(int customer_ID) {
    	try {
    		customerRepository.deleteCustomer(customer_ID);
    	} catch(Exception ex) {
    		Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,ex);
    	}
    	
    }
    
    public Customer searchCustomerById(int id) {
    	try {
    		return customerRepository.searchCustomerById(id);
    	} catch(Exception ex) {
    		Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,ex);
    	}
    	return null;
    }
    
    public List<Customer> searchCustomerByGMV(double GMV){
    	try {
    		return customerRepository.searchCustomerByGMV(GMV);
    	}catch(Exception ex) {
    		Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,ex);
    	}   	
		return null;
    }
    
    public Customer searchCustomerByContactId(int contactId) {
    	try {
    		return customerRepository.searchCustomerByContactId(contactId);
    	} catch(Exception ex) {
    		Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,ex);
    	}
    	return null;
    }
    
    
    


   
}
