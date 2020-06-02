package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Campus;

@Repository
public interface ICampusRepository extends JpaRepository<Campus, Integer>{

}
