package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.entity.Student;
import pe.edu.upc.serviceinterface.ICampusService;
import pe.edu.upc.serviceinterface.IStudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private IStudentService sS;
	
	@Autowired
	private ICampusService cS;
	
	@GetMapping("/new")
	public String newStudent(Model model) {
		model.addAttribute("listCampus", cS.list());
		model.addAttribute("student", new Student());
		return "student/student";
	}
	
	@PostMapping("/save")
	public String saveStudent(@Validated Student student, BindingResult result, Model model) throws Exception{
		if(result.hasErrors()) {
			model.addAttribute("listCampus", cS.list());
			return "student/student";
		}else {
			int rpta = sS.insert(student);
			if(rpta > 0) {
				model.addAttribute("mensaje","Ya existe el email");
				return "student/student";
			}else {
				model.addAttribute("listStudents",sS.list());
				return "student/listStudents"; 
			}
			
		}
	}
	
	
	@GetMapping("/list")
	public String listStudents(Model model) {
		try {
			model.addAttribute("listStudents", sS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "student/listStudents";
	}
}
