package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Category;


public interface ICategoryService {

	public int insert(Category category);
	
	List<Category>list();
	
	public void delete(int idProject);
	
	Optional<Category> searchId(int idCategory);

	List<Category> findNameCategoryFull(String nameCategory);
	
	public List<String[]> categorytop();
}
