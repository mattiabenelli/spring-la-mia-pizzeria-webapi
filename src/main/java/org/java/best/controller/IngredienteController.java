package org.java.best.controller;

import java.util.List;
import java.util.Optional;

import org.java.best.pojo.Ingrediente;
import org.java.best.pojo.Pizza;
import org.java.best.service.ServiceIngrediente;
import org.java.best.service.ServicePizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredienteController {

	@Autowired
	private ServicePizza pizzaService;
	
	@Autowired
	private ServiceIngrediente ingredienteService;
	
	@GetMapping("/ingredienti")
	private String getHome(Model model) {
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		
		model.addAttribute("ingredienti", ingredienti);
		
		return "ingredienti-index";
	}
	
	@GetMapping("/ingredienti/create")
	public String createIngredienti(Model model) {
		
		List<Pizza> pizzas = pizzaService.findAll();
		
		model.addAttribute("ingrediente", new Ingrediente());
		model.addAttribute("pizzas", pizzas);
		
		return "ingredienti-create";
	}
	
	@PostMapping("/ingredienti/create")
	public String storeNewCategory(
			@ModelAttribute Ingrediente ingrediente
		) {
		
		ingredienteService.save(ingrediente);
		
		return "redirect:/ingredienti";
	}
	
	@GetMapping("/ingredienti/delete/{id}")
	public String deleteIngrediente(
			@PathVariable int id
		) {
		
		Optional<Ingrediente> optIngrediente = ingredienteService.findById(id);
		Ingrediente ingrediente = optIngrediente.get();
		ingredienteService.deleteIngrediente(ingrediente);
		
		return "redirect:/ingredienti";
	}
}
