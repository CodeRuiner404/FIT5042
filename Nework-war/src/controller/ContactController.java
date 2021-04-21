package controller;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.Contact;
import mBeans.ContactManagedbean;

@Named(value = "contactController")
@RequestScoped
public class ContactController {
	@ManagedProperty(value ="#{contactMbean}")
	ContactManagedbean contactMbean;
	
	private int contact_ID;
	private int customerId;
	private Contact contact;
	private Application app;
	
	public ContactController() {
		 ELContext context = FacesContext.getCurrentInstance().getELContext();
		 app = (Application) FacesContext.getCurrentInstance()
               .getApplication()
               .getELResolver()
               .getValue(context, null, "CustomerManagementApplication");
		 
		 contactMbean = (ContactManagedbean) FacesContext.getCurrentInstance().getApplication()
	    			.getELResolver().getValue(context, null, "contactManagedBean");
		 
		 contact_ID = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("contact_ID"));
		
		 contact = getContact();
		
	}
	
	//方法一
	//
//	public Contact getContact() {
//		if(contact == null) {
//			
//			
//			return app.getContacts().get(--contact_ID);
//			
//		}
//		return contact;
//	}
	
	//方法二
	public Contact  getContact() {
         if(contact == null) {		
			return app.retriveContactById(contact_ID);		
		}
		return contact;
	}
	
	public void editContact() {
//		contact.setContact_company(app.retriveCustomerById(customerId));	
		contactMbean.editContact(contact);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edit Contact has been succesfully"));
	}
		

	public int getContact_ID() {
		return contact_ID;
	}

	public void setContact_ID(int contact_ID) {
		this.contact_ID = contact_ID;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
	
	

}
