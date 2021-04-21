package entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Pattern;

@Entity
@NamedQueries({
    @NamedQuery(name = Contact.GET_ALL_CONTACT_NAME, query = "SELECT c FROM Contact c order by c.contactID desc")})
public class Contact implements Serializable{
public static final String GET_ALL_CONTACT_NAME = "Contact.getAll";
	
	//题目的field，找到4个
	
	private int contactID;
	private String Contact_name;
	private String Contact_phoneNumber;
	private Customer Contact_company;
	private String Contact_email;
	
	//contact在公司的头衔
	private String title;
	//这个contact产生的交易额
	private Double contact_GMV;
	private int userID;
	
	//field完成
	public Contact() {
		
	}
	
	public Contact(int contactID, String contact_name, String contact_phoneNumber, Customer contact_company,
			String title, Double contact_GMV,String Contact_email) {
		this.contactID = contactID;
		this.Contact_name = contact_name;
		this.Contact_phoneNumber = contact_phoneNumber;
		this.Contact_company = contact_company;
		this.title = title;
		this.contact_GMV = contact_GMV;
		this.Contact_email = Contact_email;
	}
	
	@Pattern(regexp = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]", message = "This is not a valid email")
	public String getContact_email() {
		return Contact_email;
	}

	public void setContact_email(String contact_email) {
		this.Contact_email = contact_email;
	}

	@Id
    @GeneratedValue
	public int getContactID() {
		return contactID;
	}
	public void setContactID(int contactID) {
		this.contactID = contactID;
	}
	public String getContact_name() {
		return Contact_name;
	}
	public void setContact_name(String contact_name) {
		this.Contact_name = contact_name;
	}
	


	@Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$", message = "This is not a valid phone number")
	public String getContact_phoneNumber() {
		return Contact_phoneNumber;
	}
	public void setContact_phoneNumber(String contact_phoneNumber) {
		this.Contact_phoneNumber = contact_phoneNumber;
	}
	
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
	public Customer getContact_company() {
		return Contact_company;
	}
	public void setContact_company(Customer contact_company) {
		this.Contact_company = contact_company;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getContact_GMV() {
		return contact_GMV;
	}
	public void setContact_GMV(Double contact_GMV) {
		this.contact_GMV = contact_GMV;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + contactID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (contactID != other.contactID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contact [contactID=" + contactID + ", Contact_name=" + Contact_name + ", Contact_phoneNumber="
				+ Contact_phoneNumber + "]";
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}
