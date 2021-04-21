package controller;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entities.AppUser;
import entities.Staff;
import mBeans.UserManagedBean;
import util.SHA256Util;

@Named(value = "userController")
@RequestScoped
public class UserController {
	@ManagedProperty(value ="#{userManagedBean}")
	UserManagedBean userManagedBean;
	
	private int user_ID;
	private Staff staff;
	
	public UserController() {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
    	userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
    			.getELResolver().getValue(context, null, "userManagedBean");
    	
		user_ID = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("user_ID"));
		
		staff = getStaff();
	}

	public int getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}


	public Staff getStaff() {
		if(staff == null) {
			ELContext context = FacesContext.getCurrentInstance().getELContext();
			
			Application app = (Application) FacesContext.getCurrentInstance()
                    .getApplication()
                    .getELResolver()
                    .getValue(context, null, "CustomerManagementApplication");
			
			return app.getStaffs().get(--user_ID);
			
		}
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	public void adminEditStaff() {
		String sha256hex = SHA256Util.getSHA256StrJava(staff.getAppUserPassword());
		staff.setAppUserPassword(sha256hex);
		userManagedBean.editStaff(staff);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edit staff has been succesfully"));
	}
	
	
	
	
	

}
