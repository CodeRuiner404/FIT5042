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

@RequestScoped
@Named("addStaff")
public class AddStaff {
	@ManagedProperty(value ="#{userManagedBean}")
	UserManagedBean userManagedBean;
	
	private Staff staff;
	private String AppUserName;
	private String AppUserPassword;
	private String staffEmail;
	private int overtime;
	Application app;
	
	public AddStaff() {
		ELContext el = FacesContext.getCurrentInstance().getELContext();
		app = (Application)FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(el, null, "CustomerManagementApplication");
		
		ELContext context = FacesContext.getCurrentInstance().getELContext();
    	userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
    			.getELResolver().getValue(context, null, "userManagedBean");
    	
    	staff = getStaff();
    	AppUserName = getAppUserName();
    	AppUserPassword = getAppUserPassword();
    	staffEmail = getStaffEmail();
    	overtime = getOvertime();
	}



	public String getAppUserName() {
		return AppUserName;
	}

	public void setAppUserName(String appUserName) {
		AppUserName = appUserName;
	}

	public String getAppUserPassword() {
		return AppUserPassword;
	}

	public void setAppUserPassword(String appUserPassword) {
		AppUserPassword = appUserPassword;
	}
	
	
	
	public String getStaffEmail() {
		return staffEmail;
	}

	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}

	public int getOvertime() {
		return overtime;
	}

	public void setOvertime(int overtime) {
		this.overtime = overtime;
	}
		

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public void addStaff() {
		try {			
			Staff st = new Staff();
			st.setAppUserName(AppUserName);
//			String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(AppUserPassword);
			String sha256hex = SHA256Util.getSHA256StrJava(AppUserPassword);
			st.setAppUserPassword(sha256hex);
			st.setAppUserType("S");
			st.setOvertime(overtime);
			st.setStaffEmail(staffEmail);
			userManagedBean.addStaff(st);
			app.staffSearchAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been added succesfully"));
		} catch(Exception ex) {
			
		}
	}
	
	

}
