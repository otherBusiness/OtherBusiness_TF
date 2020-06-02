package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Campus;

@Repository
public interface ICampusRepository extends JpaRepository<Campus, Integer>{

	@Query("select count(c.nameCampus) from Campus c where c.nameCampus=LOWER(:nameCampus) or c.nameCampus=UPPER(:nameCampus)")
	public int searchCampus(@Param("nameCampus") String nombre);
}
