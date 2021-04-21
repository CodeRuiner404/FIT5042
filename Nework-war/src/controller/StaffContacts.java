package controller;

import java.util.ArrayList;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.Contact;
import entities.Customer;
import mBeans.ContactManagedbean;
import mBeans.CustomerManagedBean;

@Named(value = "staffContact")
@RequestScoped
public class StaffContacts {
	@ManagedProperty(value="#{customerManagedBean}")
	CustomerManagedBean customerMbean;
	
	@ManagedProperty(value ="#{contactMbean}")
	ContactManagedbean contactMbean;
	
	private int contact_ID;
	private int customerId;
//	private ArrayList<Contact> contacts;
	private Application app;
	private Customer customer;
//	private int customerRealId;
	
	public StaffContacts() {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		 app = (Application) FacesContext.getCurrentInstance()
              .getApplication()
              .getELResolver()
              .getValue(context, null, "CustomerManagementApplication");
		 
		 contactMbean = (ContactManagedbean) FacesContext.getCurrentInstance().getApplication()
	    			.getELResolver().getValue(context, null, "contactManagedBean");
		 
		 customerMbean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
	                .getELResolver().getValue(context, null, "customerManagedBean");
		 
		 customerId = Integer.valueOf(FacesContext.getCurrentInstance()
	                .getExternalContext()
	                .getRequestParameterMap()
	                .get("customerId"));
		 
//		 customerRealId = Integer.valueOf(FacesContext.getCurrentInstance()
//	                .getExternalContext()
//	                .getRequestParameterMap()
//	                .get("customerRealId"));
		 
//		 contacts = getContacts();
		 customer = getCustomer();

		 
	}

	public int getContact_ID() {
		return contact_ID;
	}

	public void setContact_ID(int contact_ID) {
		this.contact_ID = contact_ID;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

//	public ArrayList<Contact> getContacts() {
//		if(contacts == null) {		
//			ArrayList<Contact> c = new ArrayList<>();
//			for(Contact ct:app.getStaffCustomers().get(--customerId).getCustomer_contacts()) {
//				c.add(ct);
//			}
//			return c;
//		}
//		return contacts;
//	}
//	
//
//	public void setContacts(ArrayList<Contact> contacts) {
//		this.contacts = contacts;
//	}

	public Customer getCustomer() {
//		if (customer == null) {
			return app.retriveCustomerById(customerId);
//		}
//		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

//	public int getCustomerRealId() {
//		return customerRealId;
//	}
//
//	public void setCustomerRealId(int customerRealId) {
//		this.customerRealId = customerRealId;
//	}

	
	
	
	




	
	
	
	
}
