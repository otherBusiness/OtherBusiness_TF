package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "districts")
public class District implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDistrict;
	
	@ManyToOne
	@JoinColumn(name = "idCountry")
	private Country country;
	
	@Column(name = "nameDistrict", nullable = false, length = 25)
	private String nameDistrict;

	public District() {
		super();
	}

	public District(int idDistrict, Country country, String nameDistrict) {
		super();
		this.idDistrict = idDistrict;
		this.country = country;
		this.nameDistrict = nameDistrict;
	}

	public int getIdDistrict() {
		return idDistrict;
	}

	public void setIdDistrict(int idDistrict) {
		this.idDistrict = idDistrict;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getNameDistrict() {
		return nameDistrict;
	}

	public void setNameDistrict(String nameDistrict) {
		this.nameDistrict = nameDistrict;
	}

	
}
