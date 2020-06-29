package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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
	
	@Positive
	@Size(min = 11, max = 11)
	@NotEmpty(message="El ruc es obligatorio")
	@Column(name = "rucInvestor", nullable = false, length = 11)
	private String rucInvestor;
	
	@Email(message = "El email no cuenta con el formato")
	@Column(name = "emailInvestor", nullable = false, length = 50)
	private String emailInvestor;
	
	@ManyToOne
	@JoinColumn(name = "idCountry")
	private Country country;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCustomer")
	private Customer customer;

	public Investor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Investor(int idInvestor, String rucInvestor, Country country,String emailInvestor,
			Customer customer) {
		super();
		this.idInvestor = idInvestor;
		this.rucInvestor = rucInvestor;
		this.country = country;
		this.emailInvestor = emailInvestor;
		this.customer = customer;
	}

	public int getIdInvestor() {
		return idInvestor;
	}

	public void setIdInvestor(int idInvestor) {
		this.idInvestor = idInvestor;
	}

	public String getRucInvestor() {
		return rucInvestor;
	}

	public void setRucInvestor(String rucInvestor) {
		this.rucInvestor = rucInvestor;
	}



	public Country getCountry() {
		return country;
	}



	public void setCountry(Country country) {
		this.country = country;
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
