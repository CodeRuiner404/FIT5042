package controller;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.Contact;
import mBeans.ContactManagedbean;

@Named(value = "staffContactController")
@RequestScoped
public class StaffContactController {
	@ManagedProperty(value ="#{contactMbean}")
	ContactManagedbean contactMbean;
	
	private int contact_ID;
	private int customerId;
	private Contact contact;
	private Application app;
	
	public StaffContactController() {
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
		 customerId = getCustomerId();
	}
	
	public void editContact() {
		contactMbean.editContact(contact);
		app.staffCustomerSearchAll();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edit Contact has been added succesfully"));
	}

	public int getContact_ID() {
		return contact_ID;
	}

	public void setContact_ID(int contact_ID) {
		this.contact_ID = contact_ID;
	}

	public int getCustomerId() {
		int id = this.contact.getContact_company().getCustomer_ID();
		setCustomerId(id);
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Contact getContact() {
		if(contact == null) {
			for(Contact contact:app.getContacts()) {
				if (contact.getContactID() ==  this.contact_ID) {
					return contact;
				}
			}
		}
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	

}
