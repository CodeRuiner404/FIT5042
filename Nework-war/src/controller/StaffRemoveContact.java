package controller;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.Contact;
import mBeans.ContactManagedbean;

@RequestScoped
@Named("staffRemoveContact")
public class StaffRemoveContact {
	@ManagedProperty(value="#{contactManagedBean}")
	ContactManagedbean contactManagedBean;
	
	private Application app;
	private Contact contact;
	private int contact_ID;
	
	public StaffRemoveContact() {
		ELContext el = FacesContext.getCurrentInstance().getELContext();
		app = (Application)FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(el, null, "CustomerManagementApplication");
		
		contactManagedBean = (ContactManagedbean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(el, null, "contactManagedBean");
		
		app.updateContactList();
		
		contact_ID = Integer.valueOf(FacesContext.getCurrentInstance()
	               .getExternalContext()
	               .getRequestParameterMap()
	               .get("contact_ID"));
		
		
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public void deleteContact() {
		try {
			contactManagedBean.deleteContact(this.contact_ID);
			app.staffCustomerSearchAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact has been deleted succesfully"));
		} catch(Exception ex) {
			
		}
	}
	
	public void staffRemoveContact() {
		try {
			contactManagedBean.staffDeleteContact(this.contact_ID);
			app.staffCustomerSearchAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact has been deleted succesfully"));
		} catch(Exception ex) {
			
		}
	}

	public int getContact_ID() {
		return contact_ID;
	}

	public void setContact_ID(int contact_ID) {
		this.contact_ID = contact_ID;
	}
	
	

}
