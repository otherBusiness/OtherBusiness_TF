package pe.edu.upc.entity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "InvestorsProject")
public class InvestorProject {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idInvestorProject;
	
	
	@ManyToOne
	@JoinColumn(name = "idInvestor")
	private Investor investor;
	
	
	
	@Column(name = "requestDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestDate;// cuando se pidio
	
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idInvestorProject", nullable = true)
	private List<InvesmentDetails> invesmentDetails;

	@PrePersist
	public void prePersist() {
		this.requestDate = new Date();
	}

	public Double getTotal() {
		return invesmentDetails.stream().collect(Collectors.summingDouble(InvesmentDetails::calcularSubTotal));
	}

	public void addDetailImportation(InvesmentDetails item) {
		this.invesmentDetails.add(item);
	}

	public Long getIdInvestorProject() {
		return idInvestorProject;
	}

	public void setIdInvestorProject(Long idInvestorProject) {
		this.idInvestorProject = idInvestorProject;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public List<InvesmentDetails> getInvesmentDetails() {
		return invesmentDetails;
	}

	public void setInvesmentDetails(List<InvesmentDetails> invesmentDetails) {
		this.invesmentDetails = invesmentDetails;
	}

	
	
	
}