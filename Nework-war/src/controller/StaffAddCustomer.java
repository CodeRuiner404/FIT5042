package controller;

import java.util.ArrayList;
import java.util.Date;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.Address;
import entities.Customer;
import entities.IndustryType;
import entities.Staff;
import mBeans.CustomerManagedBean;
import mBeans.TypeManagedBean;
import mBeans.UserManagedBean;

@RequestScoped
@Named("staffAddCustomer")
public class StaffAddCustomer {
	@ManagedProperty(value = "#{customerManagedBean}")
	CustomerManagedBean customerManagedBean;
	
	@ManagedProperty(value ="#{typeManagedBean}")
	TypeManagedBean typeManagedBean;
	
	@ManagedProperty(value ="#{userManagedBean}")
	UserManagedBean userManagedBean;
	
	Application app;
	private Customer customer;
	private String customer_name;
	private String customer_information;
	private String customer_type;
	private double customer_GMV;
	private Date customer_co_year;
	private String streetAddress;
    private String suburb;
	private String state;
	private int staff_ID;
	private ArrayList<String> types;
	
	public StaffAddCustomer() {
		ELContext el = FacesContext.getCurrentInstance().getELContext();
		app = (Application)FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(el, null, "CustomerManagementApplication");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "customerManagedBean");
		
    	ELContext con = FacesContext.getCurrentInstance().getELContext();
    	typeManagedBean = (TypeManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(con, null, "typeManagedBean");
    	
    	ELContext context = FacesContext.getCurrentInstance().getELContext();
    	userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
    			.getELResolver().getValue(context, null, "userManagedBean");
    	
    	staff_ID = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("staff_ID"));
			
		customer = getCustomer();
		customer_name = getCustomer_name();
		customer_information = getCustomer_information();
		customer_GMV = getCustomer_GMV();
		customer_co_year = getCustomer_co_year();
		customer_type = getCustomer_type();
		streetAddress = getStreetAddress();
		suburb = getSuburb();
		state = getState();
		types = getTypes();
	}
	
	public void addCustomer() {
		try {
			Customer cus = new Customer();
			Address as = new Address();
			Staff st = new Staff();
			cus.setCustomer_name(customer_name);
			cus.setCustomer_co_year(customer_co_year);
			cus.setCustomer_information(customer_information);
			cus.setCustomer_GMV(customer_GMV);
			cus.setCustomer_type(customer_type);
			as.setState(state);
			as.setStreetAddress(streetAddress);
			as.setSuburb(suburb);
			cus.setAddress(as);
			st = userManagedBean.searchStaffById(staff_ID);
			cus.setCustomer_user(st);
//			customerManagedBean.addCustomer(cus);
			customerManagedBean.userAddCustomer(cus);
			app.staffCustomerSearchAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been added succesfully"));
		}catch(Exception ex) {
			
		}
		
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}


	public String getCustomer_information() {
		return customer_information;
	}

	public void setCustomer_information(String customer_information) {
		this.customer_information = customer_information;
	}

	public double getCustomer_GMV() {
		return customer_GMV;
	}

	public void setCustomer_GMV(double customer_GMV) {
		this.customer_GMV = customer_GMV;
	}

	public Date getCustomer_co_year() {
		return customer_co_year;
	}

	public void setCustomer_co_year(Date customer_co_year) {
		this.customer_co_year = customer_co_year;
	}

	public String getCustomer_type() {
		return customer_type;
	}

	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
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
	
	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public int getStaff_ID() {
		return staff_ID;
	}

	public void setStaff_ID(int staff_ID) {
		this.staff_ID = staff_ID;
	}
	
	


	
	

}
