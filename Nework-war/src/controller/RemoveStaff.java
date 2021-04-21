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
@Named("removeStaff")
public class RemoveStaff {
	@ManagedProperty(value ="#{userManagedBean}")
	UserManagedBean userManagedBean;
	
	Application app;
	
	private Staff staff;
	
	public RemoveStaff() {
		ELContext el
        = FacesContext.getCurrentInstance().getELContext();
		
		app = (Application)FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(el, null, "CustomerManagementApplication");
		
		app.updateStaffList();
		
		ELContext context = FacesContext.getCurrentInstance().getELContext();
    	userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
    			.getELResolver().getValue(context, null, "userManagedBean");
		
		
	}

		
	public Staff getStaff() {
		return staff;
	}


	public void setStaff(Staff staff) {
		this.staff = staff;
	}


	public void removeStaff(int staffID) {
		try {
			userManagedBean.deleteStaff(staffID);
			app.staffSearchAll();
		} catch(Exception ex) {
			
		}
	}
	
	
}
