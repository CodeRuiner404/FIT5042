package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


@Entity
@NamedQueries({
     @NamedQuery(name = Customer.GET_ALL_CUSTOMER_NAME, query = "SELECT ct FROM Customer ct order by ct.customer_ID desc")})
public class Customer implements Serializable{
	public static final String GET_ALL_CUSTOMER_NAME = "Customer.getAll";

	private static final TemporalType temporalType = null;
	private int customer_ID;
	private String customer_name;
	private String customer_information;
	private Set<Contact> customer_contacts;
	private Address address;
	
	private double customer_GMV;
	private Date customer_co_year;

	private String customer_type;
	
	private Staff customer_user;
	
	public Customer() {
		
	}

	public Customer(int customer_ID, String customer_name, String customer_information,
			Set<Contact> customer_contacts,double customer_GMV, Date customer_co_year,String customer_type, Staff customer_user, Address address ) {
		super();
		this.customer_name = customer_name;
		this.customer_information = customer_information;
		this.customer_contacts = customer_contacts;
		this.customer_GMV = customer_GMV;
		this.customer_co_year = customer_co_year;
		this.customer_type = customer_type;
		this.customer_user = customer_user;
		this.address = address;

	}

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int getCustomer_ID() {
		return customer_ID;
	}

	public void setCustomer_ID(int customer_ID) {
		this.customer_ID = customer_ID;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	@Size(min=0, max=50)
	public String getCustomer_information() {
		return customer_information;
	}

	public void setCustomer_information(String customer_information) {
		this.customer_information = customer_information;
	}

	@OneToMany(mappedBy="Contact_company",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Contact> getCustomer_contacts() {
		return customer_contacts;
	}

	public void setCustomer_contacts(Set<Contact> customer_contacts) {
		this.customer_contacts = customer_contacts;
	}

	public double getCustomer_GMV() {
		return customer_GMV;
	}

	public void setCustomer_GMV(double customer_GMV) {
		this.customer_GMV = customer_GMV;
	}

	@Past
	@Temporal(TemporalType.DATE)
	public Date getCustomer_co_year() {
		return customer_co_year;
	}
	

	public void setCustomer_co_year(Date customer_co_year) {
		this.customer_co_year = customer_co_year;
	}
	
	@ManyToOne(cascade= {CascadeType.ALL})
//	@JoinColumn(name = "customer_user_AppUserID")
	public Staff getCustomer_user() {
		return customer_user;
	}

	public void setCustomer_user(Staff customer_user) {
		this.customer_user = customer_user;
	}
	
	public String getCustomer_type() {
		return customer_type;
	}

	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}
	
	
	@Embedded
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
	    return String.format("%s[customer_ID=%d]", getClass().getSimpleName(), getCustomer_ID());
	}

}
