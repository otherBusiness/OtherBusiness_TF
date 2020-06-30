package pe.edu.upc.controller;

import java.util.Date;
import java.util.Map;

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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.InvesmentDetails;
import pe.edu.upc.entity.InvestorProject;
import pe.edu.upc.serviceinterface.IInvesmentDetailService;
import pe.edu.upc.serviceinterface.IInvestorProjectService;
import pe.edu.upc.serviceinterface.IInvestorService;
import pe.edu.upc.serviceinterface.IProjectService;

@Controller
@RequestMapping("/invprojects")
@Secured({ "ROLE_INVESTOR", "ROLE_ADMIN"})
public class InvestorProjectController {
	
	@Autowired
	private IInvestorProjectService ipS;
	
	@Autowired
	private IProjectService pS;
	
	@Autowired
	private IInvestorService iS;

	
	@Autowired
	private IInvesmentDetailService  ideS;
	
	@RequestMapping("/reports")
	public String Report()
	
	{
		return "reports/reports";
	}
	
	@RequestMapping("/new")
	public String irRegistrar(Model model) {
		model.addAttribute("listInvestors",iS.list());
		model.addAttribute("investorproject", new InvestorProject());
		return "invproject/invproject";
	}
	
	@RequestMapping("/newproject/{id}")
	public String irNewProject(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		model.put("detail", new InvesmentDetails());
		model.put("listProjects", pS.list());

		InvestorProject objinp = ipS.listarId(id);
		model.put("investorproject", objinp);

		return "invproject/details/detailForm";
	}
	
	@RequestMapping("/list")
	public String listar(Map<String, Object> model) {
		model.put("listaInvestorProjects", ipS.listar());
		return "invproject/listInvestorProject";
	}

	@GetMapping("/detail/{id}")
	public String detailInvestorProject(@PathVariable(value = "id") Long id, Map<String, Object> model,
			RedirectAttributes flash) {
		InvestorProject ip = ipS.listarId(id);

		if (ip == null) {
			flash.addFlashAttribute("error", "El Detalle no existe en la base de datos");
			return "invproject/listInvestorProject"; 
		}
		model.put("investorproject", ip);
		model.put("titulo", "Detalle del inversionista #" + ip.getIdInvestorProject());

		return "invproject/details/listDetail"; 
	}

	@PostMapping("/save")
	public String saveOrder(@Valid InvestorProject investorproject, Model model, SessionStatus status, BindingResult binRes) {
		Date requestday = new Date();
		try {
			investorproject.setRequestDate(requestday);
			ipS.insert(investorproject);
			status.setComplete();
			model.addAttribute("success", "Orden Generada");
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "redirect: /invprojects/new";
		}

		return "redirect:/invprojects/list";
	}

	@PostMapping("/saveproject{id}")
	public String newMotinProjects(@PathVariable(value = "id") long id, @Valid InvesmentDetails invesmentdet,
			RedirectAttributes flash, BindingResult result, Model model, SessionStatus status) {
		InvestorProject ip = ipS.listarId(id);
		if (result.hasErrors()) {
			flash.addFlashAttribute("error", "El valor debe ser positivo");
			String cadena1 = "redirect:/invproject/newproject/" + id;
			return cadena1;
		}
		try {
			ip.addDetailImportation(invesmentdet);
			ipS.insert(ip);
			status.isComplete();
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			System.out.println(e.getMessage());
		}
		String cadena = "redirect:/invprojects/detail/" + id;
		return cadena;
	}


	@RequestMapping("{idip}/eliminardetail/{id}")
	public String eliminarDetalle(Map<String, Object> model, @PathVariable(value = "id") Long idet,
			@PathVariable(value = "idip") Long idip, RedirectAttributes flash) {
		try {
			if (idet != null && idet > 0) {
				ideS.delete(idet);
				flash.addAttribute("mensaje", "Se eliminó correctamente");
				flash.addAttribute("mensaje1", "Se eliminó correctamente el id" + idet);
			} else
				return "redirect:/home";
		} catch (Exception e) {
			model.put("mensaje", "No se puede eliminar");
			model.put("error", e.getMessage());
		}
		String cadena = "redirect:/invprojects/detail/" + idip;
		return cadena;
	}

}

