package org.java.best.controller;

import java.util.List;

import org.java.best.pojo.Offerta;
import org.java.best.pojo.Pizza;
import org.java.best.service.ServiceOfferta;
import org.java.best.service.ServicePizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class OffertaController {
	
	@Autowired
	private ServicePizza pizzaService;
	
	@Autowired
	private ServiceOfferta offertaService;
	
	@GetMapping("/offerte")
	public String getHome(Model model) {
		
		List<Offerta> offerte = offertaService.findAll();
		
		model.addAttribute("offerte", offerte);
		
		return "offerte-index";
	}
	
	@GetMapping("/offerte/create")
	public String createOfferte(Model model) {
		
		List<Pizza> pizzas = pizzaService.findAll();
		
		model.addAttribute("offerta", new Offerta());
		model.addAttribute("pizzas", pizzas);
		
		return "offerta-create";
	}
	@PostMapping("/offerte/create")
	public String offertaPizzeStore(Model model,
			@Valid @ModelAttribute Offerta offerta,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("offerta", offerta);
			model.addAttribute("errors", bindingResult);
			
			
			List<Pizza> pizzas = pizzaService.findAll();
			model.addAttribute("pizzas", pizzas);
			
			return "offerta-create";
		}
		
		offertaService.save(offerta);
		
		return "redirect:/offerte";
	}
	
	@GetMapping("/offerte/update/{id}")
	public String editOfferte(Model model,
							@PathVariable int id) {
		
		List<Pizza> pizzas = pizzaService.findAll();
		Offerta offerta = offertaService.findById(id).get();
		
		model.addAttribute("offerta", offerta);
		model.addAttribute("pizzas", pizzas);
		
		return "offerta-update";
	}
	
	@PostMapping("/offerte/update/{id}")
	public String updateOfferte(Model model,
			@PathVariable int id,
			@Valid @ModelAttribute Offerta offerta,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("offerta", offerta);
			model.addAttribute("errors", bindingResult);
			
			List<Pizza> pizzas = pizzaService.findAll();
			model.addAttribute("pizzas", pizzas);
			
			
			return "offerta-update";
		}

		offertaService.save(offerta);

		return "redirect:/offerte";
	}
}
