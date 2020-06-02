package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "investors")
public class Investor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idInvestor;
	
	@Column(name = "rucInvestor", nullable = false, length = 11)
	private int rucInvestor;
	
	@Column(name = "countryInvestor", nullable = false, length = 25)
	private String countryInvestor;
	
	@Column(name = "districtInvestor", nullable = false, length = 25)
	private String districtInvestor;
	
	@Column(name = "emailInvestor", nullable = false, length = 50)
	private String emailInvestor;
	
	@OneToOne
	@JoinColumn(name = "idCustomer")
	private Customer customer;

	public Investor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Investor(int idInvestor, int rucInvestor, String countryInvestor, String districtInvestor,
			String emailInvestor, Customer customer) {
		super();
		this.idInvestor = idInvestor;
		this.rucInvestor = rucInvestor;
		this.countryInvestor = countryInvestor;
		this.districtInvestor = districtInvestor;
		this.emailInvestor = emailInvestor;
		this.customer = customer;
	}

	public int getIdInvestor() {
		return idInvestor;
	}

	public void setIdInvestor(int idInvestor) {
		this.idInvestor = idInvestor;
	}

	public int getRucInvestor() {
		return rucInvestor;
	}

	public void setRucInvestor(int rucInvestor) {
		this.rucInvestor = rucInvestor;
	}

	public String getCountryInvestor() {
		return countryInvestor;
	}

	public void setCountryInvestor(String countryInvestor) {
		this.countryInvestor = countryInvestor;
	}

	public String getDistrictInvestor() {
		return districtInvestor;
	}

	public void setDistrictInvestor(String districtInvestor) {
		this.districtInvestor = districtInvestor;
	}

	public String getEmailInvestor() {
		return emailInvestor;
	}

	public void setEmailInvestor(String emailInvestor) {
		this.emailInvestor = emailInvestor;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
