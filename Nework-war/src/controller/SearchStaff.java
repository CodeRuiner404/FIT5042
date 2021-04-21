package controller;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.AppUser;
import entities.Staff;
import mBeans.UserManagedBean;

@RequestScoped
@Named("searchStaff")
public class SearchStaff {
	@ManagedProperty(value ="#{userManagedBean}")
	UserManagedBean userManagedBean;
	
	Application app;
	private Staff staff;
	private int intInput;
	
	public SearchStaff() {
        ELContext el = FacesContext.getCurrentInstance().getELContext();
		
		app = (Application)FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(el, null, "CustomerManagementApplication");
		
		app.updateStaffList();
	}


	public int getIntInput() {
		return intInput;
	}

	public void setIntInput(int intInput) {
		this.intInput = intInput;
	}
	
	
	public void searchStaffById(int id) {
		try {
			app.searchStaffById(id);
		}catch(Exception ex) {
    		
    	}
	}
	
	public void reset() {
		try {
			app.staffSearchAll();
		}catch(Exception ex) {
    		
    	}
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	
	
	

}
