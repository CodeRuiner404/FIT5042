package controller;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.Contact;
import mBeans.ContactManagedbean;

@RequestScoped
@Named("searchContact")
public class SearchContact {
	@ManagedProperty(value="#{contactManagedBean}")
	ContactManagedbean contactManagedBean;
	
	Application app;
	private Contact contact;
	private int intInput;
    private double doubleInput;
    
    public SearchContact() {
    	ELContext el = FacesContext.getCurrentInstance().getELContext();
		app = (Application)FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(el, null, "CustomerManagementApplication");
		app.updateContactList();
    }
    
    public void searchContactByID(int id) {
    	try {
    		app.searchContactByID(id);
    	}catch(Exception ex) {
    		
    	}
    }
    
    public void searchContactByGMV(double GMV) {
    	try {
    		app.searchContactByGMV(GMV);
    	}catch(Exception ex) {
    		
    	}
    }
    
    public void reset() {
    	try {
    		app.contactSearchAll();;
    	}catch(Exception ex) {
    		
    	}
    }

	public Application getApp() {
		return app;
	}

	public void setApp(Application app) {
		this.app = app;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
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
    
    
	

}
