package controller;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.Contact;
import entities.Customer;
import mBeans.ContactManagedbean;
import mBeans.CustomerManagedBean;

@RequestScoped
@Named("addContact")
public class addContact {
	@ManagedProperty(value="#{contactManagedBean}")
	ContactManagedbean contactManagedBean;
	@ManagedProperty(value = "#{customerManagedBean}")
	CustomerManagedBean customerManagedBean;
	
	Application app;
	private Contact contact;
	private String Contact_name;
	private String Contact_phoneNumber;
	private String Contact_email;
	private String title;
	private Double contact_GMV;

	
	public addContact() {
		ELContext el = FacesContext.getCurrentInstance().getELContext();
		app = (Application)FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(el, null, "CustomerManagementApplication");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		contactManagedBean = (ContactManagedbean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "contactManagedBean");
		
		ELContext Context = FacesContext.getCurrentInstance().getELContext();
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(Context, null, "customerManagedBean");
		
		
		contact = getContact();
		Contact_name = getContact_name();
		Contact_phoneNumber = getContact_phoneNumber();
		Contact_email = getContact_email();
		title = getTitle();
		contact_GMV =getContact_GMV();
			
		
	}
	
	
	public void addContact1() {
		try {
			Contact ct = new Contact();
			ct.setContact_name(Contact_name);
			ct.setContact_phoneNumber(Contact_phoneNumber);
			ct.setContact_email(Contact_email);
			ct.setTitle(title);
//			ct.setContact_GMV(contact_GMV);
			contactManagedBean.addContact(ct);
			app.contactSearchAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact has been added succesfully"));
		} catch(Exception ex) {
			
		}
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

	public String getContact_phoneNumber() {
		return Contact_phoneNumber;
	}

	public void setContact_phoneNumber(String contact_phoneNumber) {
		Contact_phoneNumber = contact_phoneNumber;
	}

	public String getContact_email() {
		return Contact_email;
	}

	public void setContact_email(String contact_email) {
		Contact_email = contact_email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getContact_GMV() {
		return contact_GMV;
	}

	public void setContact_GMV(Double contact_GMV) {
		this.contact_GMV = contact_GMV;
	}


	


}
