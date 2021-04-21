package entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue(value = "S")
@PrimaryKeyJoinColumn(name = "user_id")
@NamedQueries({
	@NamedQuery(name = Staff.GET_ALL_STAFF_NAME, query = "SELECT s FROM Staff s") })
public class Staff extends AppUser{
	public static final String GET_ALL_STAFF_NAME = "Staff.getAll";
	private String staffEmail;
	private int overtime;
	private Set<Customer> staffCustomers;
		
	
	public Staff() {
		super();

	}
	public Staff(int appUserID, String appUserName, String appUserPassword, String AppUserType, Set<Customer> staffCustomers, String staffEmail, int overtime) {
		super(appUserID, appUserName, appUserPassword, AppUserType);
		this.staffCustomers = staffCustomers;
		this.staffEmail = staffEmail;
		this.overtime = overtime;

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
	@OneToMany(mappedBy="customer_user",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Customer> getStaffCustomers() {
		return staffCustomers;
	}
	public void setStaffCustomers(Set<Customer> staffCustomers) {
		this.staffCustomers = staffCustomers;
	}
	
	
	

}
