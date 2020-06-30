package pe.edu.upc.controller;

import java.util.List;
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

import com.sun.el.parser.ParseException;

import pe.edu.upc.entity.Student;
import pe.edu.upc.serviceinterface.ICampusService;
import pe.edu.upc.serviceinterface.IStudentService;

@Controller
@RequestMapping("/students")
@Secured({ "ROLE_ADMIN","ROLE_STUDENT"})
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

	
	@GetMapping("/list")
	public String listStudents(Model model) {
		try {
			model.addAttribute("student", new Student());
			model.addAttribute("listStudents", sS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			// model.addAttribute("mensaje1", "Estudiante Registrado");
		}
		return "student/listStudents";
	}

	
	@PostMapping("/save")
	public String saveStudent(@Validated Student student, BindingResult result, Model model, RedirectAttributes flash)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listCampus", cS.list());
			return "student/student";
		} else {
			int rpta = sS.insert(student);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Estudiante ya existe");
				model.addAttribute("listCampus", cS.list());
				return "student/student";
			} else {
				// model.addAttribute("mensaje", "Estudiante Registrado");
				model.addAttribute("listStudent", sS.list());//
				flash.addFlashAttribute("mensaje", "Se guardo correctamente");
				// flash.addFlashAttribute("mensaje2", "¡Guardado Correctamente!2");

				return "redirect:/students/list";
				// return "student/listStudents";
			}

		}
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete/{id}")
	public String deleteStudent(Model model, @PathVariable(value = "id") int id) {
		try {
			if (id > 0) {
				sS.delete(id);
				model.addAttribute("listStudents", sS.list());
				model.addAttribute("student", new Student());
				model.addAttribute("mensaje", "Se elimino el estudiante");
			}
			return "student/listStudents";
		} catch (Exception e) {
			model.addAttribute("student", new Student());
			System.out.println(e.getMessage());
			model.addAttribute("mensaje", "Ocurrio un error al eliminar");
			model.addAttribute("listStudents", sS.list());
		}
		return "student/listStudents";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Student> objStu = sS.searchId(id);
		if (objStu == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/students/list";
		} else {
			model.addAttribute("listCampus", cS.list());
			model.addAttribute("student", objStu.get());
			objRedir.addFlashAttribute("mensaje", "¡Guardado Correctamente!");

			return "student/student";
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/search")
	public String searchEmailStudents(Model model, @Validated Student student) throws ParseException {
		List<Student> listStudents;
		listStudents = sS.findEmailStudent(student.getEmailStudent());
		if (listStudents.isEmpty()) {
			model.addAttribute("mensaje", "no se encontro");
		}
		model.addAttribute("listStudents", listStudents);
		return "student/listStudents";
	}

	@GetMapping(value = "/view/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {

		Optional<Student> student = sS.searchId(id);
		if (student == null) {
			flash.addFlashAttribute("error", "El estudiante no existe en la base de datos");
			return "redirect:/students/list";
		}

		model.addAttribute("student", student.get());

		return "student/view";
	}
}
