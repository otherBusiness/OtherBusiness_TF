package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.entity.Investor;

public interface IInvestorRepository extends JpaRepository<Investor, Integer>{

}
