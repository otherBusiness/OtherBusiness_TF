package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.entity.Investor;
import pe.edu.upc.serviceinterface.IInvestorService;

@Controller
@RequestMapping("/investors")
public class InvestorController {
	
	@Autowired
	private IInvestorService iS;
	
	@GetMapping("/new")
	public String newInvestor(Model model) {
		model.addAttribute("investor", new Investor());
		return "investor/investor";
	}
	
	@PostMapping("/save")
	public String saveInvestor(@Validated Investor investor, BindingResult result, Model model) throws Exception {
		if (result.hasErrors()) {
			return "investor/investor";	
		} else {
			int rpta = iS.insert(investor);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe ese inversionista");
				return "investor/investor";
			} else {
				model.addAttribute("listInvestors", iS.list());
				return "investor/listInvestors";
			}
		}
	}
	
	@GetMapping("/list")
	public String listaInvestors(Model model) {
		try {
			model.addAttribute("listInvestors", iS.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "investor/listInvestors";
	}
	
}
