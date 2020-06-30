package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {
	@Query("select count (c.nameCategory) from Category c where c.nameCategory= LOWER(:nameCategory) or c.nameCategory=UPPER(:nameCategory)")
	public int searchCategory(@Param("nameCategory") String nombre);

	List<Category> findBynameCategory(String nameCategory);
	
	
	@Query(value = "SELECT c.name_Category,count(pr.id_project) from public.investors_project i join invesment_details ide on  ide.id_investor_project = i.id_investor_project join projects pr on ide.id_project = pr.id_project join categories c on c.id_category= pr.id_category group by c.name_category ORDER BY COUNT(c.name_category) DESC limit 1", nativeQuery = true)
	public List<String[]> categoryTop();
}
