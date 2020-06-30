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

import pe.edu.upc.entity.Investor;
import pe.edu.upc.serviceinterface.ICountryService;
import pe.edu.upc.serviceinterface.IInvestorService;

@Controller
@RequestMapping("/investors")
@Secured("ROLE_ADMIN")
public class InvestorController {

	@Autowired
	private IInvestorService iS;

	@Autowired
	private ICountryService cS;

	@Secured("ROLE_INVESTOR")
	@GetMapping("/new")
	public String newInvestor(Model model) {
		model.addAttribute("listCountry", cS.list());
		model.addAttribute("investor", new Investor());
		return "investor/investor";
	}

	@Secured({ "ROLE_INVESTOR", "ROLE_ADMIN" })
	@PostMapping("/save")
	public String saveInvestor(@Validated Investor investor, BindingResult result, Model model, RedirectAttributes flash) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listCountry", cS.list());
			return "investor/investor";
		} else {
			int rpta = iS.insert(investor);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe ese inversionista");
				model.addAttribute("listCountry", cS.list());
				return "investor/investor";
			} else {
				model.addAttribute("listInvestors", iS.list());
				flash.addFlashAttribute("mensaje", "Se guardo correctamente");
				return "redirect:/investors/list";
			}
		}
	}

	@Secured({ "ROLE_INVESTOR", "ROLE_ADMIN" })
	@GetMapping("/list")
	public String listaInvestors(Model model) {
		try {
			model.addAttribute("investor", new Investor());
			model.addAttribute("listInvestors", iS.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "investor/listInvestors";
	}

	/*
	 * @GetMapping("/delete/{id}") public String deleteInvestor(Model
	 * model, @PathVariable(value = "id") int id) { try { if (id > 0) {
	 * iS.delete(id); } model.addAttribute("listInvestors", iS.list());
	 * model.addAttribute("mensaje", "Se elimino el inversionista"); } catch
	 * (Exception e) { System.out.println(e.getMessage());
	 * model.addAttribute("mensaje", "Ocurrio un error al eliminar");
	 * model.addAttribute("listInvestors", iS.list()); } return
	 * "investor/listInvestors"; }
	 */

	@RequestMapping("/delete/{id}")
	public String deleteInvestor(Model model, @PathVariable(value = "id") int id) {
		try {
			if (id > 0) {
				iS.delete(id);

				model.addAttribute("listInvestors", iS.list());
				model.addAttribute("investor", new Investor());
				model.addAttribute("mensaje", "Se elimino el inversionista");
			}

			return "investor/listInvestors";

		} catch (Exception e) {
			model.addAttribute("investor", new Investor());
			System.out.println(e.getMessage());
			model.addAttribute("mensaje", "Ocurrio un error al eliminar");
			model.addAttribute("listInvestors", iS.list());
		}
		return "investor/listInvestors";
	}

	@RequestMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Investor> objStu = iS.searchId(id);
		if (objStu == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/investor/list";
		} else {
			model.addAttribute("listCountry", cS.list());
			model.addAttribute("investor", objStu.get());
			return "investor/investor";
		}
	}

	@RequestMapping("/searchruc")
	public String searchRucInvestor(Model model, @Validated Investor investor) throws ParseException {
		List<Investor> listInvestors;
		listInvestors = iS.findRucInvestor(investor.getRucInvestor());
		if (listInvestors.isEmpty()) {
			model.addAttribute("mensaje", "no se encontro");
		}
		model.addAttribute("listInvestors", listInvestors);
		return "investor/listInvestors";
	}

	@GetMapping(value = "/view/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {

		Optional<Investor> investor = iS.searchId(id);
		if (investor == null) {
			flash.addFlashAttribute("error", "El proyecto no existe en la base de datos");
			return "redirect:/projects/list";
		}

		model.addAttribute("investor", investor.get());

		return "investor/view";
	}
}
