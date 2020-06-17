package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Project;
import pe.edu.upc.serviceinterface.ICategoryService;
import pe.edu.upc.serviceinterface.IProjectService;
import pe.edu.upc.serviceinterface.IStudentService;

@Controller
@RequestMapping("projects")
public class ProjectController {
	@Autowired
	private IProjectService pS;
	
	@Autowired
	 private ICategoryService cS; 
	
	@Autowired
	private IStudentService sS;
	
	
	@GetMapping("/new")
	public String newProject(Model model) {
		model.addAttribute("listCategories", cS.list());
		model.addAttribute("listStudents", sS.list());
		model.addAttribute("project", new Project());
		return "project/project";
	}
	
	@PostMapping("/save")
	public String saveProject(@Validated Project project, BindingResult result, Model model) throws Exception{
		if(result.hasErrors()) {
			model.addAttribute("listCategories", cS.list());
			model.addAttribute("listStudents", sS.list());
			return "project/project";
		}else {
			int rpta=pS.insert(project);
			if(rpta>0) {
				model.addAttribute("mensaje","Ya existe el proyecto");
				model.addAttribute("listCategories",cS.list());
				model.addAttribute("listStudents",sS.list());
				return "project/project";
			}else {
				/*pS.insert(project);*/
				/*model.addAttribute("mensaje","Se guardo correctamente");*/
				model.addAttribute("mensaje", "Se guardo correctamente");
				model.addAttribute("listProjects",pS.list());
				return "redirect:/projects/list"; 
			}
			
		}
			
		
		
	}
	
	@GetMapping("/list")
	public String listProject(Model model) {
		try {
			model.addAttribute("project", new Project());
			model.addAttribute("listProjects",pS.list());
		}catch (Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		return "project/listProjects";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProject(Model model, @PathVariable(value="id")int id) {
		try {
			if(id>0) {pS.delete(id);
	}
			model.addAttribute("listProjects",pS.list());
			
			model.addAttribute("mensaje","Se elimino el proyecto");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		model.addAttribute("mensaje","Ocurrio error al eliminar");
		model.addAttribute("listProjects",pS.list());
		}
		return "project/listProjects";
	}
	
	@RequestMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Project> objPro=pS.searchId(id);
		if(objPro==null){
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
			return "redirect:/projects/list";
		}else {
			model.addAttribute("listCategories",cS.list());
			model.addAttribute("listStudents",sS.list());
			model.addAttribute("project",objPro.get());
			model.addAttribute("mensaje","Guardar para actualizar");
		return "project/project";
		}
		
	}

	@RequestMapping("/search")
	public String searchCategories(Model model, @Validated Project project) throws ParseException {
		List<Project> listProjects;
		listProjects = pS.search(project.getNameProject());
		if (listProjects.isEmpty()) {
			model.addAttribute("mensaje", "No se encontró");
		}
		model.addAttribute("listProjects", listProjects);

		return "project/listProjects";

	} 
	
	
	
}
