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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PizzaController {
	

	@Autowired
	private ServicePizza pizzaService;
	
	@Autowired
	private ServiceIngrediente ingredienteService;
	
	@GetMapping("/")
	public String getHome() {
		
		return "home";
	}
	
	@GetMapping("/pizza")
	public String getPizza(Model model) {
		
		List<Pizza> pizzas = pizzaService.findAll();
		
		model.addAttribute("pizzas", pizzas);
		
		return "pizzas";
	}
	
	@PostMapping("/pizza/by/nome")
	public String getPizzaByNome(Model model, @RequestParam(required = false) String nome) {
		
		List<Pizza> pizzas = pizzaService.findByNome(nome);
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("nome", nome);
		
		return "pizzas";
	}
	
	@GetMapping("/pizza/{id}")
	public String getPizza(
			Model model,
			@PathVariable("id") int id
	) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizza", pizza);
		
		return "pizza";
	}
	
	@GetMapping("/pizza/create")
	public String createPizza(Model model) {
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredienti", ingredienti);
		
		return "pizza-create";
	}
	@PostMapping("/pizza/create")
	public String storePizza(Model model,
						@Valid @ModelAttribute Pizza pizza,
						BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("pizza", pizza);
			
			model.addAttribute("errors", bindingResult);
			
			
			return "pizza-create";
		}
		
		 pizzaService.save(pizza);
		
		return "redirect:/pizza";
	}
	
	@GetMapping("/pizza/delete/{id}")
	public String deletePizza(
			@PathVariable int id
		) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		Pizza pizza = optPizza.get();
		pizzaService.deletePizza(pizza);
		
		return "redirect:/pizza";
	}
	
	@GetMapping("/pizza/update/{id}")
	public String editPizza(
			Model model,
			@PathVariable int id
		) {
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		Optional<Pizza> optPizza = pizzaService.findById(id);
		Pizza pizza = optPizza.get();
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredienti" ,ingredienti);
		
		return "pizza-update";
	}
	@PostMapping("/pizza/update/{id}")
	public String updatePizza(Model model,
			@PathVariable int id,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			
			
			return "pizza-update";
		}
		
		
		pizzaService.save(pizza);
		
		return "redirect:/pizza";
	}
	
	
}
