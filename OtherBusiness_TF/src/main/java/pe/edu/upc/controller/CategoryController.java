package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Category;
import pe.edu.upc.serviceinterface.ICategoryService;

@Controller
@RequestMapping("/categories")
@Secured("ROLE_ADMIN")
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
			int rpta=cS.insert(category);//model.addAttribute("success", "Orden Generada");
			if(rpta>0) {
				model.addAttribute("mensaje","Ya existe la Categoría");
				return "category/category";
			}else {
				model.addAttribute("listCategories",cS.list());
				//model.addAttribute("success", "Orden Generada");
				model.addAttribute("mensaje", "Se guardó correctamente");

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
	
	@RequestMapping("/delete/{id}")
	public String deleteCategory(Model model, @PathVariable(value = "id") int id) {
		try {
			if (id > 0) {
				cS.delete(id);
				model.addAttribute("listCategories", cS.list());
				model.addAttribute("category", new Category());
				model.addAttribute("mensaje", "Categoría Eliminada");

				
				
			}
			return "category/listCategories";

		} catch (Exception e) {
			model.addAttribute("category", new Category());

			System.out.println(e.getMessage());
			model.addAttribute("mensaje", "No se puede eliminar una categoría relacionada");
			model.addAttribute("listCategories", cS.list());

			return "category/listCategories";
		}

	}
	
	@RequestMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model,RedirectAttributes objRedir){
		Optional<Category> objCate=cS.searchId(id);
			if(objCate==null) {
			objRedir.addFlashAttribute("mensaje","ocurrio un error");

			return "redirect:/categories/list";
		}else {		//
			//model.addAttribute("mensaje","Actualizado");//(model,"xdxd");//("mensaje","xdxdxdxdlistooor");

			model.addAttribute("listCategories",cS.list());
			model.addAttribute("category",objCate.get());
			model.addAttribute("mensaje","Guardar para actualizar");
		String p="exito";
	
			model.addAttribute("mensaje1", "Se guardó correctamente");//.addFlashAttribute("mensaje",p);
		//	objRedir.addFlashAttribute("mensaje", "OcurriÃ³Â³ un error222");
		        System.out.println(p);

			
		//	model.addAttribute("category", "Categoría Eliminada2222222222");  //para el mensaje cuando se modifica
			//objRedir.addFlashAttribute("error", "El valor debe ser positivo");  //para el mensaje cuando se modifica

			
			return "category/category";
			}

		}
	
	@RequestMapping("/search")
	public String searchCategories(Model model, @Validated Category category) throws ParseException {
		List<Category> listCategories;

		listCategories = cS.findNameCategoryFull(category.getNameCategory());
		if (listCategories.isEmpty()) {

			model.addAttribute("mensaje", "No se encontró");
		}
		model.addAttribute("listCategories", listCategories);
		return "category/listCategories";

	}
	
	@RequestMapping("/reporte2")
	public String categoryTop(Map<String, Object> model) {
		model.put("listCategoriesTop", cS.categorytop());
		return "reports/categoryTop";
	}
	
	
	}
