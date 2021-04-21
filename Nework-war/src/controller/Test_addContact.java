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
@Named("test_addContact")
public class Test_addContact {
	@ManagedProperty(value="#{contactManagedBean}")
	ContactManagedbean contactManagedBean;
	
	Application app;
	private Contact contact;
	private String Contact_name;
	private String contact_phoneNumber;
	private String ContactEmail;
	
	public Test_addContact() {
		ELContext el = FacesContext.getCurrentInstance().getELContext();
		app = (Application)FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(el, null, "CustomerManagementApplication");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		contactManagedBean = (ContactManagedbean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "contactManagedBean");
	}
	
	public void addContact() {
		Contact ct = new Contact();
		ct.setContact_name(Contact_name);
		ct.setContact_phoneNumber(contact_phoneNumber);
		ct.setContact_email(ContactEmail);
		contactManagedBean.addContact(ct);
		app.contactSearchAll();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact has been added succesfully"));
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getContact_name() {
		return Contact_name;
	}

	public void setContact_name(String contact_name) {
		Contact_name = contact_name;
	}
	
	//phone number	
	public String getContact_phoneNumber() {
		return contact_phoneNumber;
	}

	public void setContact_phoneNumber(String contact_phoneNumber) {
		this.contact_phoneNumber = contact_phoneNumber;
	}
	
	public String getContactEmail() {
		return ContactEmail;
	}

	public void setContactEmail(String contactEmail) {
		ContactEmail = contactEmail;
	}
	
	
	
	
	
}
