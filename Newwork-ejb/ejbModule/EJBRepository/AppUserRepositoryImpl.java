package EJBRepository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Admin;
import entities.AppUser;
import entities.Staff;
import repository.AppUserRepository;

@Stateless
public class AppUserRepositoryImpl implements AppUserRepository {
	@PersistenceContext(unitName= "Assignment_C_ejb")
	private EntityManager entityManager;
	
	@Override
	public List<AppUser> getAllUser()throws Exception{
		return entityManager.createNamedQuery(AppUser.GET_ALL_APPUSER_NAME).getResultList();
	}
	@Override
	public List<Staff> getAllStaff() throws Exception{
		return entityManager.createNamedQuery(Staff.GET_ALL_STAFF_NAME).getResultList();
	}
	
	@Override
	public List<Admin> getAllAdmin() throws Exception{
		return entityManager.createNamedQuery(Admin.GET_ALL_ADMIN_NAME).getResultList();
	}

	
	@Override
	public void addStaff(Staff staff) throws Exception{
		try {
			List<AppUser> users = entityManager.createNamedQuery(AppUser.GET_ALL_APPUSER_NAME).getResultList();
			staff.setAppUserID(users.size()+1);
			entityManager.persist(staff);
		}catch(Exception ex) {
			System.out.print("Add staff failed");
		}
	}
	
	@Override
	public void editStaff(Staff staff) throws Exception{
		try {
			entityManager.merge(staff);
		} catch(Exception ex) {
			
		}
	}
	
	
	@Override
	public void deleteStaff(int id) throws Exception{
		Staff staff = this.searchStaffById(id);
		if(staff != null) {
			entityManager.remove(staff);
		}
	}
    
	@Override
	public Staff searchStaffById(int id) throws Exception{
		Staff staff = entityManager.find(Staff.class, id);
		return staff;
	}
	

}
