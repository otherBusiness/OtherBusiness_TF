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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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
	
	@NotEmpty(message = "Ingrese el email del estudiante")
	@Column(name = "emailStudent", nullable = false, length = 25)
	private String emailStudent;
	
	@Positive(message="No puede ser negativo")
	@NotEmpty(message = "Ingrese el codigo del estudiante")
	@Size(min = 9, max = 9)
	@Column(name = "codeStudent", nullable = false, unique = true, length = 9)
	@Pattern(regexp = "[0-9]*", message="El campo solo puede tener numeros.")
	private String codeStudent;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCustomer")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "idCampus")
	private Campus campus;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int idStudent, String emailStudent, String codeStudent, Customer customer, Campus campus) {
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

	public String getCodeStudent() {
		return codeStudent;
	}

	public void setCodeStudent(String codeStudent) {
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
