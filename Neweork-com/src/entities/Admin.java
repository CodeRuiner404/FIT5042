package entities;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;



@Entity
@DiscriminatorValue(value = "A")
@PrimaryKeyJoinColumn(name = "user_id")
@NamedQueries({
	@NamedQuery(name = Admin.GET_ALL_ADMIN_NAME, query = "SELECT a FROM Admin a") })
public class Admin extends AppUser{
	public static final String GET_ALL_ADMIN_NAME ="Admin.getAll";
	private int bonus;
	private String phoneNumber;
	
	
	public Admin() {
		super();

	}
	public Admin(int appUserID, String appUserName, String appUserPassword, String AppUserType, int bonus, String phoneNumber) {
		super(appUserID, appUserName, appUserPassword, AppUserType);
		this.bonus = bonus;
		this.phoneNumber = phoneNumber;

	}
	
	public int getBonus() {
		return bonus;
	}
	
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
	

}
