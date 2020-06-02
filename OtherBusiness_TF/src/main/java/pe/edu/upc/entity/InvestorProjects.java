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
@Table(name = "investorsProjects")
public class InvestorProjects implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idInvestorProjects;
	
	@ManyToOne
	@JoinColumn(name = "idPoject")
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "idInvestor")
	private Investor investor;
	
	@Column(name = "amountInvested")
	private double amountInvested;
	
	@Column(name = "favoriteProject")
	private Boolean favoriteProject;

	public InvestorProjects() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvestorProjects(int idInvestorProjects , Project project, Investor investor, double amountInvested, Boolean favoriteProject) {
		super();
		this.idInvestorProjects = idInvestorProjects;
		this.project = project;
		this.investor = investor;
		this.amountInvested = amountInvested;
		this.favoriteProject = favoriteProject;
	}
	
	public int getIdInvestorProjects() {
		return idInvestorProjects;
	}

	public void setIdInvestorProjects(int idInvestorProjects) {
		this.idInvestorProjects = idInvestorProjects;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public double getAmountInvested() {
		return amountInvested;
	}

	public void setAmountInvested(double amountInvested) {
		this.amountInvested = amountInvested;
	}

	public Boolean getFavoriteProject() {
		return favoriteProject;
	}

	public void setFavoriteProject(Boolean favoriteProject) {
		this.favoriteProject = favoriteProject;
	}
}
