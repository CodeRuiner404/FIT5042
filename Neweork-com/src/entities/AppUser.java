package entities;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "AppUserType", discriminatorType = DiscriminatorType.STRING,length = 1)
@NamedQueries({
	@NamedQuery( name = AppUser.GET_ALL_APPUSER_NAME, query = "SELECT a FROM AppUser a order by a.appUserID")
})
public class AppUser implements Serializable {
	public static final String GET_ALL_APPUSER_NAME = "AppUser.getAll";

	
	private int AppUserID;
	private String AppUserName;
	private String AppUserPassword;
    private String AppUserType;

	
	public AppUser(int appUserID, String appUserName, String appUserPassword,String AppUserType) {
		this.AppUserID = appUserID;
		this.AppUserName = appUserName;
		this.AppUserPassword = appUserPassword;
		this.AppUserType = AppUserType;		

	}
	
	public AppUser() {
		
	}

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "user_id")
	public int getAppUserID() {
		return AppUserID;
	}

	public void setAppUserID(int appUserID) {
		AppUserID = appUserID;
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
		

	public String getAppUserType() {
		return AppUserType;
	}

	public void setAppUserType(String appUserType) {
		AppUserType = appUserType;
	}


	
}
