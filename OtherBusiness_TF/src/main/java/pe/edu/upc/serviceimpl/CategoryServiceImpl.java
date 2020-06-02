package pe.edu.upc.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Category;
import pe.edu.upc.repository.ICategoryRepository;
import pe.edu.upc.serviceinterface.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	private ICategoryRepository cR;
	
	@Transactional
	@Override
	public int insert(Category category) {
		// TODO Auto-generated method stub
		int rpta=cR.searchCategory(category.getNameCategory());
		if(rpta==0){
			cR.save(category);
		}
		return rpta;
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

}
