package pe.edu.upc.repository;

import java.util.List;

/*import java.util.List;*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Project;
@Repository
public interface IProjectRepository extends JpaRepository<Project, Integer>{
	@Query("select count (p.nameProject) from Project p where p.nameProject= LOWER(:nameProject) or p.nameProject=UPPER(:nameProject)")
	public int searchProject(@Param("nameProject") String nombre);
	
	
	@Query("from Project p where p.nameProject like %:busqueda%  or p.category.nameCategory like %:busqueda%")
/*List<Project> findBynameProject(String nameProject);*/
	List<Project> search(@Param("busqueda") String busqueda);
	
	
	@Query( value="SELECT pr.name_project,sum(ide.amount_Invested) from investors_project i join invesment_details ide on  ide.id_investor_project = i.id_investor_project join projects pr on ide.id_project = pr.id_project group by pr.name_project",
			nativeQuery = true )
	public List<String[]> montin();
}
