package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Campus;

@Repository
public interface ICampusRepository extends JpaRepository<Campus, Integer>{

	@Query("select count(c.nameCampus) from Campus c where c.nameCampus=LOWER(:nameCampus) or c.nameCampus=UPPER(:nameCampus)")
	public int searchCampus(@Param("nameCampus") String nombre);
	
	
	@Query(value = "SELECT c.name_campus, count(pr.id_project) from public.investors_project i join invesment_details ide on ide.id_investor_project=i.id_investor_project join projects pr on ide.id_project= pr.id_project join campus c on c.id_campus= c.id_campus group by c.name_campus order by count(c.name_campus) desc limit 1", nativeQuery = true) 
	//count(pr.id_product) from public.importation i join import_details ide on  ide.id_importation = i.id_importation join products pr on ide.id_product = pr.id_product join brands c on c.id_brand= pr.id_brand group by c.brand_name ORDER BY COUNT(c.brand_name) DESC limit 1", nativeQuery = true)
	public List<String[]> masCampus();
}
