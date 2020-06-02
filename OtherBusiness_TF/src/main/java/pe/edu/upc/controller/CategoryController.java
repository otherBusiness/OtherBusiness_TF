package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.entity.Category;
import pe.edu.upc.serviceinterface.ICategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private ICategoryService cS;
	
	@GetMapping("/new")
	public String newCategory(Model model) {
		model.addAttribute("category",new Category());
		return "category/category";
		
	}
	
	@PostMapping("/save")
	public String saveCategory(@Validated Category category, BindingResult result, Model model) throws Exception{
		if(result.hasErrors()) {
			return "category/category";
		}else {
			int rpta=cS.insert(category);
			if(rpta>0) {
				model.addAttribute("mensaje","Ya existe la Categoría");
				return "category/category";
			}else {
				model.addAttribute("listCategories",cS.list());
				return "category/listCategories";
			}
		}
	}
	
	@GetMapping("/list")
	public String listCategories(Model model) {
		try {
			model.addAttribute("listCategories", cS.list());
		}catch(Exception e){
			model.addAttribute("error", e.getMessage());
		}
		return "category/listCategories";
		
	}
}
