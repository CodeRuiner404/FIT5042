package controller;

import java.io.Serializable;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.Customer;
import mBeans.CustomerManagedBean;

@RequestScoped
@Named("searchCustomer")
public class SearchCustomer{
	@ManagedProperty(value = "#{customerManagedBean}")
	CustomerManagedBean customerManagedBean;
	
	private Customer customer;
    Application app;
    private int intInput;
    private double doubleInput;
    private int contactIdInput;
    
    public SearchCustomer() {
    	ELContext el
        = FacesContext.getCurrentInstance().getELContext();
		
		app = (Application)FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(el, null, "CustomerManagementApplication");
		
		app.updateCustomerList();
    }
    
    public void searchCustomerByID(int ID) {
    	try {
    		app.searchCustomerByID(ID);
    	} catch(Exception ex) {
    		
    	}
    }
	
    public void serchCustomerByGMV(double GMV) {
    	try {
    		app.searchCustomerByGMV(GMV);
    	} catch(Exception ex) {
    		
    	}
    }
    
    public void searchCustomerByContactId(int id) {
    	try {
    		app.searchCustomerByContactId(id);;
    	} catch(Exception ex) {
    		
    	}
    }
    
    public void reset() {
    	try {
    		app.searchAll();
    	}catch(Exception ex) {
    		
    	}
    }

    //get && set
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getIntInput() {
		return intInput;
	}

	public void setIntInput(int intInput) {
		this.intInput = intInput;
	}

	public double getDoubleInput() {
		return doubleInput;
	}

	public void setDoubleInput(double doubleInput) {
		this.doubleInput = doubleInput;
	}

	public Application getApp() {
		return app;
	}

	public void setApp(Application app) {
		this.app = app;
	}

	public int getContactIdInput() {
		return contactIdInput;
	}

	public void setContactIdInput(int contactIdInput) {
		this.contactIdInput = contactIdInput;
	}
	
	
    
	
    
    

}
