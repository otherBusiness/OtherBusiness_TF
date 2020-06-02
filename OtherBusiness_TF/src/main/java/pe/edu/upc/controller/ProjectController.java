package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.entity.Project;
import pe.edu.upc.serviceinterface.ICategoryService;
import pe.edu.upc.serviceinterface.IProjectService;

@Controller
@RequestMapping("projects")
public class ProjectController {
	@Autowired
	private IProjectService pS;
	
	@Autowired
	 private ICategoryService cS; 
	
	
	@GetMapping("/new")
	public String newProject(Model model) {
		model.addAttribute("listCategories", cS.list());
		model.addAttribute("project", new Project());
		return "project/project";
	}
	
	@PostMapping("/save")
	public String saveProject(@Validated Project project, BindingResult result, Model model) throws Exception{
		if(result.hasErrors()) {
			/*model.addAttribute("listCategories", cS.list());*/
			return "project/project";
		}else {
			pS.insert(project);
			model.addAttribute("mensaje","Se guardó correctamente");
			return "project/project";
		}
		
	}
	
	@GetMapping("/list")
	public String listProject(Model model) {
		try {
			model.addAttribute("listProjects",pS.list());
		}catch (Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		return "project/listProjects";
	}
}
