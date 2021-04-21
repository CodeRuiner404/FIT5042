package controller;

import java.util.Iterator;
import java.util.Set;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.Contact;
import entities.Customer;
import entities.Staff;
import mBeans.ContactManagedbean;
import mBeans.CustomerManagedBean;
import mBeans.UserManagedBean;

@RequestScoped
@Named("removeContact")
public class removeContact {
	
	@ManagedProperty(value="#{contactManagedBean}")
	ContactManagedbean contactManagedBean;
	
	@ManagedProperty(value ="#{userManagedBean}")
	UserManagedBean userManagedBean;
	
	@ManagedProperty(value="#{customerManagedBean}")
	CustomerManagedBean customerMbean;
	
	private Application app;
	private Contact contact;
	
	public removeContact() {
		ELContext el = FacesContext.getCurrentInstance().getELContext();
		app = (Application)FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(el, null, "CustomerManagementApplication");
		
		contactManagedBean = (ContactManagedbean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(el, null, "contactManagedBean");
		
		userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
    			.getELResolver().getValue(el, null, "userManagedBean");
		
		customerMbean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(el, null, "customerManagedBean");
		
		app.updateContactList();
				
		
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public void deleteContact(int id) {
		try {
			contactManagedBean.deleteContact(id);
			app.contactSearchAll();
			app.searchAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact has been deleted succesfully"));
		} catch(Exception ex) {
			
		}
	}
	
//	public void deleteTheContact(int id) {
//		try {
//			Contact con = contactManagedBean.searchContactById(id);
//			Customer cus = con.getContact_company();
//			int customerID = cus.getCustomer_ID();
//			//无staff
//			if(cus.getCustomer_user() == null) {
//				Iterator<Contact> it = cus.getCustomer_contacts().iterator();
//				while(it.hasNext()) {
//					if(it.next().getContactID() == id) {
//						it.remove();
//					}
//				}
//			customerMbean.editCustomer(cus);
//			contactManagedBean.deleteContact(id);
//			app.contactSearchAll();
//			app.searchAll();
//			app.staffSearchAll();
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact has been deleted succesfully"));
//			
//				
//			//有staff	
//			}else {
//				Staff st = cus.getCustomer_user();
//				Iterator<Customer> it2 = st.getStaffCustomers().iterator();
//				while(it2.hasNext()) {
//					if(it2.next().getCustomer_ID() == customerID) {
//						Iterator<Contact> it3 = it2.next().getCustomer_contacts().iterator();
//						while(it3.hasNext()) {
//							if(it3.next().getContactID()==id) {
//								it3.remove();
//							}
//						}
//					}
//				}
//				
//				userManagedBean.editStaff(st);
//				contactManagedBean.deleteContact(id);
//				app.contactSearchAll();
//				app.searchAll();
//				app.staffSearchAll();
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact has been deleted succesfully"));
//			}
//		}catch(Exception ex) {
//			
//		}
//	}

}
