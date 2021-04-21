package controller;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.Customer;
import mBeans.CustomerManagedBean;

@RequestScoped
@Named("removeCustomer")
public class removeCustomer {
	@ManagedProperty(value = "#{customerManagedBean}")
	CustomerManagedBean customerManagedBean;
	
	Application app;
	
	private Customer customer;
	
	public removeCustomer() {
		ELContext el
        = FacesContext.getCurrentInstance().getELContext();
		
		app = (Application)FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(el, null, "CustomerManagementApplication");
		
		app.updateCustomerList();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "customerManagedBean");
		
		
		
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void removetheCustomer(int ID) {
		try {
			Customer cust = customerManagedBean.searchCustomerById(ID);
			cust.setCustomer_user(null);
			customerManagedBean.editCustomer(cust);
			customerManagedBean.deleteCustomer(ID);
			app.searchAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been deleted succesfully"));
		} catch(Exception ex) {
			
		}
		
	}

}
