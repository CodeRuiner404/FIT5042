package repository;

import java.util.List;

import javax.ejb.Remote;

import entities.Admin;
import entities.AppUser;
import entities.Staff;

@Remote
public interface AppUserRepository {
	public List<AppUser> getAllUser()throws Exception;
	public List<Staff> getAllStaff() throws Exception;
	public List<Admin> getAllAdmin() throws Exception;
	public void addStaff(Staff staff) throws Exception;
	public void editStaff(Staff staff) throws Exception;	
	public void deleteStaff(int id) throws Exception;
	public Staff searchStaffById(int id) throws Exception;
	
	
}
