package controller;

import java.util.ArrayList;

import javax.el.ELContext;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.faces.bean.ManagedProperty;

import entities.Customer;
import entities.IndustryType;
import entities.Staff;
import entities.Admin;
import entities.AppUser;
import entities.Contact;
import mBeans.ContactManagedbean;
import mBeans.CustomerManagedBean;
import mBeans.TypeManagedBean;
import mBeans.UserManagedBean;



@Named(value = "CustomerManagementApplication")
@ApplicationScoped
public class Application {
	
	@ManagedProperty(value="#{customerManagedBean}")
	CustomerManagedBean customerMbean;
	
	@ManagedProperty(value ="#{contactMbean}")
	ContactManagedbean contactMbean;
	
	@ManagedProperty(value ="#{userManagedBean}")
	UserManagedBean userManagedBean;
	
	@ManagedProperty(value ="#{typeManagedBean}")
	TypeManagedBean typeManagedBean;
	
	private ArrayList<entities.Customer> customers;
	private ArrayList<Contact> contacts;
	private ArrayList<entities.IndustryType> types;
	private ArrayList<AppUser> users;
	private ArrayList<Admin> admins;
	private ArrayList<Staff> staffs;
	
	//user特供
	private Staff oneStaff;
	private String userName;
	private ArrayList<entities.Customer> staffCustomers;
	
	
	private boolean showForm = true;

    public boolean isShowForm() {
        return showForm;
    }
    
    public Application() throws Exception{
    	customers = new ArrayList<>();
    	contacts = new ArrayList<>();
    	types = new ArrayList<>();
    	users = new ArrayList<>();
    	admins = new ArrayList<>();
    	staffs = new ArrayList<>();
    	oneStaff = new Staff();
    	staffCustomers = new ArrayList<>();
    	
    	ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	customerMbean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "customerManagedBean");
    	
    	ELContext el = FacesContext.getCurrentInstance().getELContext();
    	contactMbean = (ContactManagedbean) FacesContext.getCurrentInstance().getApplication()
    			.getELResolver().getValue(el, null, "contactManagedBean");
    	
    	ELContext context = FacesContext.getCurrentInstance().getELContext();
    	userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
    			.getELResolver().getValue(context, null, "userManagedBean");
    	
    	ELContext con = FacesContext.getCurrentInstance().getELContext();
    	typeManagedBean = (TypeManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(con, null, "typeManagedBean");
    	
    	userName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    	
    	updateCustomerList();
    	updateContactList();    	
    	updateTypeList();
    	updateStaffList();
    	updateOneStaff();
    	updateStaffCustomer();
       }
    
    //Customer 
    public void updateCustomerList() {
  		if (customers != null && customers.size() > 0)
        {
            
        }
        else
        {

		customers.clear();
		for(Customer customer: customerMbean.getAllCustomer() ) {
			customers.add(customer);
		}
		
		setCustomers(customers);
		}
	   }
    
      public ArrayList<Customer> getCustomers(){
  	       return customers;
       }
     
      public void setCustomers(ArrayList<Customer> newCustomers) {
  	   this.customers = newCustomers;
      }
      
      public void searchAll() {
  		customers.clear();
  		for(Customer customer:customerMbean.getAllCustomer()) {
  			customers.add(customer);
  		}
  		setCustomers(customers);
  	    }
      
      public void searchCustomerByID(int id) {
  		customers.clear();
  		customers.add(customerMbean.searchCustomerById(id));
  	   }
      
      public Customer retriveCustomerById(int id) {
    	  return customerMbean.searchCustomerById(id);
      }
  	
  	public void searchCustomerByGMV(double GMV) {
  		customers.clear();
  		for(Customer customer:customerMbean.searchCustomerByGMV(GMV)) {
  			customers.add(customer);
  		}
  		setCustomers(customers);
  	}
  	
  	public void searchCustomerByContactId(int id) {
  		customers.clear();
  		customers.add(customerMbean.searchCustomerByContactId(id));
  	}
  	
  	public int getFinalID() {
		return customerMbean.getAllCustomer().size()+1;
	}
      
      
      
      //Contact       
      public void updateContactList() {
  		if(contacts == null || contacts.size()<=0) {
  			contactSearchAll();
  		}
  		if(contacts != null && contacts.size()>0) {
  			
  		}else {
  			contacts.clear();
  			for(Contact contact:contactMbean.getAllContacts()) {
  				contacts.add(contact);
  			}
  			setContacts(contacts);
  		}
  	}
      
      public ArrayList<Contact> getContacts() {
  		  return contacts;
  	}

  	public void setContacts(ArrayList<Contact> contacts) {
  		this.contacts = contacts;
  	}
 
	
	public void contactSearchAll() {
		contacts.clear();
		for(Contact contact:contactMbean.getAllContacts()) {
			contacts.add(contact);
		}
		setContacts(contacts);
	}
	
	
	
	public void searchContactByID(int id) {
		contacts.clear();
		contacts.add(contactMbean.searchContactById(id));
	}
	
	public void searchContactByGMV(double GMV) {
		contacts.clear();
		for(Contact contact:contactMbean.searchContactByGMV(GMV)) {
			contacts.add(contact);
		}
		setContacts(contacts);
	}
	
//	public Customer retriveCustomerById(int id) {
//  	  return customerMbean.searchCustomerById(id);
//    }
	
	public Contact retriveContactById(int id) {
		return contactMbean.searchContactById(id);
	}
	
	//All user
	public void updateUserList() 
	{
		if(users != null && users.size()>0) {
			
		}else {
        users.clear();
		for(AppUser aUser: userManagedBean.getAllUser() ) {
			users.add(aUser);
		}
		
		setUsers(users);
		}
	}
	

	public ArrayList<AppUser> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<AppUser> users) {
		this.users = users;
	}
	

	
	
	//staff and administrator
	public void updateStaffList() {
		if(staffs != null && staffs.size()>0) {
			
		}else {
			staffs.clear();
			for(Staff staff:userManagedBean.getAllStaff()) {
				staffs.add(staff);
			}
			setStaffs(staffs);
		}
	}
	
	public ArrayList<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(ArrayList<Staff> staffs) {
		this.staffs = staffs;
	}
	
	public void staffSearchAll() {
		staffs.clear();
		for(Staff staff:userManagedBean.getAllStaff()) {
			staffs.add(staff);
		}
		setStaffs(staffs);
	}
	
	public void searchStaffById(int id) {
		staffs.clear();
		staffs.add(userManagedBean.searchStaffById(id));
	}
	
	public Staff findStaffById(int id) {
		return userManagedBean.searchStaffById(id);
	}

	//type
	public void updateTypeList() {
		if(types != null && types.size()>0) {
			
		}else {
			types.clear();
			
			for(IndustryType aType: typeManagedBean.getAllIndustryType()) {
				types.add(aType);
			}
			setTypes(types);
		}
	}

	public ArrayList<IndustryType> getTypes() {
		return types;
	}

	public void setTypes(ArrayList<IndustryType> types) {
		this.types = types;
	}
	
	public void typeSearchAll() {
		types.clear();
		for(IndustryType iType: typeManagedBean.getAllIndustryType()) {
			types.add(iType);
		}
		setTypes(types);
	}
	
	public void searchTypeById(int id) {
		types.clear();
		types.add(typeManagedBean.searchIndustryTypeById(id));
	}

	
	
	//user CRUD	
	public void updateOneStaff() {
		
			for(Staff staff:userManagedBean.getAllStaff()) {
				if(staff.getAppUserName().equals(userName)) {
					setOneStaff(staff);
				}		
			}

	}
	
	public Staff getOneStaff() {
		return oneStaff;
	}

	public void setOneStaff(Staff oneStaff) {
		this.oneStaff = oneStaff;
	}
	
	//user CRUD 的customer
	public void updateStaffCustomer() {
		if(staffCustomers.size()>0 && staffCustomers!= null) {
			
		}else {
		for(Customer customer: customerMbean.getAllCustomer() ) {
	      
			if(customer.getCustomer_user()!= null && customer.getCustomer_user().getAppUserName().equals(this.userName) ) {
				
				staffCustomers.add(customer);
			}
//			setStaffCustomers(staffCustomers);
		}
		}
	}

	public ArrayList<entities.Customer> getStaffCustomers() {
		return staffCustomers;
	}

	public void setStaffCustomers(ArrayList<entities.Customer> staffCustomers) {
		this.staffCustomers = staffCustomers;
	}
	
	public void staffCustomerSearchAll() {
		staffCustomers.clear();
		for(Customer customer: customerMbean.getAllCustomer() ) {
			if(customer.getCustomer_user().getAppUserName().equals(this.userName)) {
				staffCustomers.add(customer);
			}
			setStaffCustomers(staffCustomers);
		}
	}
	
	public String logout() {
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "index.xhtml";
	}
	

	
	
	

	
	

}
