package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCustomer;
	
	@Column(name = "firstNameCustomer", nullable = false, length = 50)
	private String firstNameCustomer;
	
	@Column(name = "lastNameCustomer", nullable = false, length = 50)
	private String lastNameCustomer;
	
	@Column (name = "dniCustomer", nullable = false, length = 25)
	private String dniCustomer;
	
	@Column(name = "phoneCustomer", nullable = false, length = 25)
	private String phoneCustomer;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int idCustomer, String firstNameCustomer, String lastNameCustomer, String dniCustomer,
			String phoneCustomer) {
		super();
		this.idCustomer = idCustomer;
		this.firstNameCustomer = firstNameCustomer;
		this.lastNameCustomer = lastNameCustomer;
		this.dniCustomer = dniCustomer;
		this.phoneCustomer = phoneCustomer;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getFirstNameCustomer() {
		return firstNameCustomer;
	}

	public void setFirstNameCustomer(String firstNameCustomer) {
		this.firstNameCustomer = firstNameCustomer;
	}

	public String getLastNameCustomer() {
		return lastNameCustomer;
	}

	public void setLastNameCustomer(String lastNameCustomer) {
		this.lastNameCustomer = lastNameCustomer;
	}

	public String getDniCustomer() {
		return dniCustomer;
	}

	public void setDniCustomer(String dniCustomer) {
		this.dniCustomer = dniCustomer;
	}

	public String getPhoneCustomer() {
		return phoneCustomer;
	}

	public void setPhoneCustomer(String phoneCustomer) {
		this.phoneCustomer = phoneCustomer;
	}
}
