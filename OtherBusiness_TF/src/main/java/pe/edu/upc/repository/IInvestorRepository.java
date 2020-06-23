package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.entity.Investor;

public interface IInvestorRepository extends JpaRepository<Investor, Integer>{
	@Query("select count(i.rucInvestor) from Investor i where rucInvestor=:rucInvestor")
	public int searchInvestor(@Param("rucInvestor") String ruc);
	
	List<Investor> findByRucInvestor(String ruc);
}
