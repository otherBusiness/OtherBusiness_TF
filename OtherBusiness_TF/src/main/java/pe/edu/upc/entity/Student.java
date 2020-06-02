package pe.edu.upc.entity;

import java.io.Serializable;

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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "students")
public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idStudent;
	
	@Email(message="No cuenta con el formato email")
	@NotEmpty(message="El email es obligatorio")
	@Column(name = "emailStudent", nullable = false, length = 25)
	private String emailStudent;
	
	@NotNull(message="El codigo es obligatorio")
	@Column(name = "codeStudent", nullable = false, unique = true, length = 9)
	private int codeStudent;
	
	@OneToOne
	@JoinColumn(name = "idCustomer")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "idCampus")
	private Campus campus;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int idStudent, String emailStudent, int codeStudent, Customer customer, Campus campus) {
		super();
		this.idStudent = idStudent;
		this.emailStudent = emailStudent;
		this.codeStudent = codeStudent;
		this.customer = customer;
		this.campus = campus;
	}

	public int getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	public String getEmailStudent() {
		return emailStudent;
	}

	public void setEmailStudent(String emailStudent) {
		this.emailStudent = emailStudent;
	}

	public int getCodeStudent() {
		return codeStudent;
	}

	public void setCodeStudent(int codeStudent) {
		this.codeStudent = codeStudent;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}
}
