package pe.edu.upc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import pe.edu.upc.entity.InvestorProject;

@Repository
public interface IInvestorProjectRepository extends JpaRepository<InvestorProject, Long>{
	
	@Query("select i from InvestorProject i join fetch i.invesmentDetails ide join fetch ide.project where i.idInvestorProject=?1")
	Optional<InvestorProject> fetchByInvestorProjectsIdWhithInvesmentsDetailsWithProject(Long id);

}
