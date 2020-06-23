package pe.edu.upc.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Campus;
import pe.edu.upc.serviceinterface.ICampusService;

@Controller // maneja los eventos
@RequestMapping("/campus")
@Secured("ROLE_ADMIN")
public class CampusController {
	@Autowired
	private ICampusService cS;
//declarar metodos que voy a usar

	@GetMapping("/new") // anotar tipo de envio ->le dice q muestre campus/new en el url
	public String newCampus(Model model) {
		model.addAttribute("campus", new Campus());
		return "campus/campus";
	}

	@PostMapping("/save") // @Valid me permite traer la entidad y hacer la instancia de ella
	public String saveCampus(@Valid Campus campus, BindingResult result, Model model) throws Exception // manejar
																										// errores
	{
		if (result.hasErrors()) {
			return "campus/campus";
		} // si ocurre error, no se mueve solo muestra la vista campus
		else {
			int rpta = cS.insert(campus);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe el Campus, intente otra vez");
				return "campus/campus";
			} else { model.addAttribute("listCampus",cS.list());
			return "campus/listCampus";
			}

		}

	}

	@GetMapping("/list")
	public String listCampus(Model model) {
		try {
			model.addAttribute("listCampus", cS.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "campus/listCampus";
	}

	
	@RequestMapping ("/delete/{id}")   //atiende solicitud a traves de esta peticion 
	public String deleteCampus(Model model,@PathVariable(value="id")int id) {
		try {
			//llamar al service y hacer la eliminacion
			if(id>0) {cS.delete(id);}//ver si existe
			model.addAttribute("listCampus",cS.list());
			model.addAttribute("mensaje","Se elimino de la lista");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("mensaje","Ocurrio un error al eliminar de la lista");
			model.addAttribute("listCampus",cS.list());

		}
		return "campus/listCampus";
	}
	
	
	@RequestMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model,RedirectAttributes objRedir)
	{//conectar con el service donde estan los metodos
		Optional<Campus> objCamp=cS.searchId(id);
		if(objCamp==null) {
			objRedir.addFlashAttribute("mensaje","ocurrio un error");
			return "redirect:/campus/list";
		}else {
			model.addAttribute("listCampus",cS.list());
			model.addAttribute("campus",objCamp.get());
			model.addAttribute("mensaje","Guardar para actualizar");

			return "campus/campus";
		}
								
	}
	
}
