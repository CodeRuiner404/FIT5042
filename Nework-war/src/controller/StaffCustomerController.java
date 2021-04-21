package controller;

import java.util.ArrayList;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.Customer;
import entities.IndustryType;
import mBeans.CustomerManagedBean;
import mBeans.TypeManagedBean;

@Named(value = "staffCustomerControll")
@RequestScoped
public class StaffCustomerController {
	@ManagedProperty(value="#{customerManagedBean}")
	CustomerManagedBean customerMbean;
	
	@ManagedProperty(value ="#{typeManagedBean}")
	TypeManagedBean typeManagedBean;
	
	private int customer_ID;
	private Customer cusOfStaff;
	private ArrayList<String> types;
	private Application app;
	
	
	public StaffCustomerController() {
		ELContext con = FacesContext.getCurrentInstance().getELContext();
		app = (Application) FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(con, null, "CustomerManagementApplication");
		
		customerMbean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(con, null, "customerManagedBean");
		
    	typeManagedBean = (TypeManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(con, null, "typeManagedBean");
    	
    	
		customer_ID = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("customer_ID"));
		
		types = getTypes();
		cusOfStaff = getCusOfStaff();
	}
	

	public int getCustomer_ID() {
		return customer_ID;
	}

	public void setCustomer_ID(int customer_ID) {
		this.customer_ID = customer_ID;
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

	// user 的 customer
	public Customer getCusOfStaff() {

			for(Customer customer:app.getStaffCustomers()) {
				if(customer.getCustomer_ID()== this.customer_ID) {
					return customer;
				}
			}
					
//			return app.getStaffCustomers().get(--customer_ID);
		
		return cusOfStaff;
	}

	public void setCusOfStaff(Customer cusOfStaff) {
		this.cusOfStaff = cusOfStaff;
	}
}
