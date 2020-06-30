package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;


import pe.edu.upc.entity.InvestorProject;

public interface IInvestorProjectService {

	
	public boolean insert(InvestorProject investorproject);

	InvestorProject listarId(Long idInvestorProjects);

	List<InvestorProject> listar();

	Optional<InvestorProject> fetchByInvestorProjectsIdWhithInvesmentsDetailsWithProject(Long id);

}

