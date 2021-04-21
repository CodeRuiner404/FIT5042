package controller;

import java.util.ArrayList;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.Customer;
import entities.IndustryType;
import entities.Staff;
import mBeans.CustomerManagedBean;
import mBeans.TypeManagedBean;
import mBeans.UserManagedBean;



@Named(value = "customerController")
@RequestScoped
public class CustomerController {
	@ManagedProperty(value ="#{typeManagedBean}")
	TypeManagedBean typeManagedBean;
	
	@ManagedProperty(value="#{customerManagedBean}")
	CustomerManagedBean customerMbean;
	
	@ManagedProperty(value ="#{userManagedBean}")
	UserManagedBean userManagedBean;
	
	private int customer_ID;
	private entities.Customer customer;
	private ArrayList<String> types;
	private Application app;
	private int staffID;
	
	
	public CustomerController() {
		ELContext con = FacesContext.getCurrentInstance().getELContext();
		app = (Application) FacesContext.getCurrentInstance()
	               .getApplication()
	               .getELResolver()
	               .getValue(con, null, "CustomerManagementApplication");
		
    	typeManagedBean = (TypeManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(con, null, "typeManagedBean");
    	
    	customerMbean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(con, null, "customerManagedBean");
    	
    	userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
    			.getELResolver().getValue(con, null, "userManagedBean");
    	
		customer_ID = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("customer_ID"));
		
		customer = getCustomer();
		types = getTypes();
		staffID = getStaffID();
		}
	
	//方法一
	
//	public Customer getCustomer() {
//		if (customer == null) {
//			ELContext context = FacesContext.getCurrentInstance().getELContext();
//			
//			Application app = (Application) FacesContext.getCurrentInstance()
//                    .getApplication()
//                    .getELResolver()
//                    .getValue(context, null, "CustomerManagementApplication");
//			return app.getCustomers().get(--customer_ID);
//		}
//		return customer;
//	}
	
	//方法二
	public Customer getCustomer() {
//		if (customer == null) {
			return app.retriveCustomerById(customer_ID);
//		}
//		return customer;
	}

	public int getCustomer_ID() {
		return customer_ID;
	}

	public void setCustomer_ID(int customer_ID) {
		this.customer_ID = customer_ID;
	}
	
	public void setCustomer(entities.Customer customer) {
		this.customer = customer;
	}
	
	public ArrayList<String> getTypes() {		
		types = new ArrayList<String>();
			for(IndustryType aType: typeManagedBean.getAllIndustryType()) {
				types.add(aType.getTypeName());
			}
			setTypes(types);
		    return types;
	}

	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}
	
	public void editCustomer() {
		customerMbean.editCustomer(customer);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edit Customer has been succesfully"));
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	
	public void assignToStaff() {
		Staff staff = app.findStaffById(staffID);
		customer.setCustomer_user(staff);
		staff.getStaffCustomers().add(customer);
		userManagedBean.editStaff(staff);
		app.staffSearchAll();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Assign to staff has been succesfully"));
	}
	
	

	
	

	

}
