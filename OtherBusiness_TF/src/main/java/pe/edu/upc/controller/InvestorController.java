package pe.edu.upc.controller;

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

import com.sun.el.parser.ParseException;

import pe.edu.upc.entity.Investor;
import pe.edu.upc.serviceinterface.ICountryService;
import pe.edu.upc.serviceinterface.IInvestorService;

@Controller
@RequestMapping("/investors")
public class InvestorController {

	@Autowired
	private IInvestorService iS;
	
	@Autowired
	private ICountryService cS;
	

	@GetMapping("/new")
	public String newInvestor(Model model) {
		model.addAttribute("listCountry", cS.list());
		model.addAttribute("investor", new Investor());
		return "investor/investor";
	}

	@PostMapping("/save")
	public String saveInvestor(@Validated Investor investor, BindingResult result, Model model) throws Exception {
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
				return "redirect:/investors/list";
			}
		}
	}

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

	@GetMapping("/delete/{id}")
	public String deleteInvestor(Model model, @PathVariable(value = "id") int id) {
		try {
			if (id > 0) {
				iS.delete(id);
			}
			model.addAttribute("listInvestors", iS.list());
			model.addAttribute("mensaje", "Se elimino el inversionista");
		} catch (Exception e) {
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
	public String searchRucInvestor(Model model, @Validated Investor investor) throws ParseException{
		List<Investor> listInvestors;
		listInvestors = iS.findRucInvestor(investor.getRucInvestor());
		if(listInvestors.isEmpty()) {
			model.addAttribute("mensaje", "no se encontro");
		}
		model.addAttribute("listInvestors", listInvestors);
		return "investor/listInvestors";
	}
}
