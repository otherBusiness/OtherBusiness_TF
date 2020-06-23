package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "projects")
public class Project implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProject;
	
	@NotEmpty(message="Debe ingresar nombre del proyecto")
	@Column(name = "nameProject", nullable = false, length = 50)
	private String nameProject;
	
	/*@NotEmpty(message="Debe ingresar la descripción del proyecto")*/
	@Column(name = "descriptionProject", nullable = false, length = 500)
	private String descriptionProject;
	
	/*@NotNull(message="El costo es obligatorio")*/
	@Column(name = "costProject", nullable = false)
	/*@Positive(message = "El costo debe de ser positivo")*/
	private double costProject;
	
	/*@NotNull(message="Inicio de fecha es obligatorio")*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "startDateProject", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date startDateProject;
	
	/*@NotNull(message="Inicio de fin es obligatorio")*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "finishDateProject", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date finishDateProject;
	
	
	@ManyToOne
	@JoinColumn(name = "idCategory")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "idStudent")
	private Student student;

	
	private String foto;
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Project(int idProject, @NotEmpty(message = "Debe ingresar nombre del proyecto") String nameProject,
			String descriptionProject, double costProject, Date startDateProject, Date finishDateProject,
			Category category, Student student, String foto) {
		super();
		this.idProject = idProject;
		this.nameProject = nameProject;
		this.descriptionProject = descriptionProject;
		this.costProject = costProject;
		this.startDateProject = startDateProject;
		this.finishDateProject = finishDateProject;
		this.category = category;
		this.student = student;
		this.foto = foto;
	}

	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public String getNameProject() {
		return nameProject;
	}

	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}

	public String getDescriptionProject() {
		return descriptionProject;
	}

	public void setDescriptionProject(String descriptionProject) {
		this.descriptionProject = descriptionProject;
	}

	public double getCostProject() {
		return costProject;
	}

	public void setCostProject(double costProject) {
		this.costProject = costProject;
	}

	public Date getStartDateProject() {
		return startDateProject;
	}

	public void setStartDateProject(Date startDateProject) {
		this.startDateProject = startDateProject;
	}

	public Date getFinishDateProject() {
		return finishDateProject;
	}

	public void setFinishDateProject(Date finishDateProject) {
		this.finishDateProject = finishDateProject;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
