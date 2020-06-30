package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

@Entity
@Table(name="InvesmentDetails")
public class InvesmentDetails {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idInvesmentDetails;
	
	
	@ManyToOne
	@JoinColumn(name = "idProject", nullable = false)
	private Project project;
	
	
	
	@Positive(message = "El monto de la inversion debe de ser positivo")
	@Column(name = "amountInvested")
	private double amountInvested;
	
	public Double calcularSubTotal() {
		return amountInvested;
	}

	public Long getIdInvesmentDetails() {
		return idInvesmentDetails;
	}

	public void setIdInvesmentDetails(Long idInvesmentDetails) {
		this.idInvesmentDetails = idInvesmentDetails;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public double getAmountInvested() {
		return amountInvested;
	}

	public void setAmountInvested(double amountInvested) {
		this.amountInvested = amountInvested;
	}

	
	
}
