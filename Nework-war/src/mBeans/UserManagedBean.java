package mBeans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entities.Admin;
import entities.AppUser;
import entities.Staff;
import repository.AppUserRepository;

@ManagedBean(name="userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable{
	@EJB
	AppUserRepository appUserRepository;
	
	public UserManagedBean() {
		
	}
	
	public List<AppUser> getAllUser(){
		try {
			List<AppUser> users = appUserRepository.getAllUser();
			return users;
		}catch (Exception ex) {
			Logger.getLogger( UserManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	
	public List<Staff> getAllStaff(){
		try {
			List<Staff> staffs = appUserRepository.getAllStaff();
			return staffs;
		}catch (Exception ex) {
			Logger.getLogger( UserManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	
	public List<Admin> getAllAdmin(){
		try {
			List<Admin> admins = appUserRepository.getAllAdmin();
			return admins;
		}catch (Exception ex) {
			Logger.getLogger( UserManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	
	public void addStaff(Staff staff) {
		try {
			appUserRepository.addStaff(staff);;
		}catch (Exception ex) {
			Logger.getLogger( UserManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	
	public void editStaff(Staff staff) {
		try {
			appUserRepository.editStaff(staff);;
		}catch (Exception ex) {
			Logger.getLogger( UserManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	
	public Staff searchStaffById(int id) {
		try {
			return appUserRepository.searchStaffById(id);
		}catch (Exception ex) {
			Logger.getLogger( UserManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	
	public void deleteStaff(int id) {
		try {
			appUserRepository.deleteStaff(id);;
		}catch (Exception ex) {
			Logger.getLogger( UserManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	


	

}
