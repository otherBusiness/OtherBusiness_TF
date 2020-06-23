package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
	
	@Column (name = "dniCustomer", nullable = false, length = 8)
	private int dniCustomer;
	
	@Column(name = "phoneCustomer", nullable = false, length = 9)
	private int phoneCustomer;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int idCustomer, String firstNameCustomer, String lastNameCustomer, int dniCustomer,
			int phoneCustomer) {
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

	public int getDniCustomer() {
		return dniCustomer;
	}

	public void setDniCustomer(int dniCustomer) {
		this.dniCustomer = dniCustomer;
	}

	public int getPhoneCustomer() {
		return phoneCustomer;
	}

	public void setPhoneCustomer(int phoneCustomer) {
		this.phoneCustomer = phoneCustomer;
	}
}
