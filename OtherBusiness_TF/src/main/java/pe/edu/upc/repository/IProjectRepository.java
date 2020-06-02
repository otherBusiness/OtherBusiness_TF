package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Project;
@Repository
public interface IProjectRepository extends JpaRepository<Project, Integer>{
	@Query("select count (p.nameProject) from Project p where p.nameProject= LOWER(:nameProject) or p.nameProject=UPPER(:nameProject)")
	public int searchProject(@Param("nameProject") String nombre);
}
