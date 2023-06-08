package org.java.best.api.controller;

import java.util.List;
import java.util.Optional;

import org.java.best.pojo.Pizza;
import org.java.best.service.ServicePizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class MyRestController {
	
	@Autowired
	private ServicePizza pizzaService;

	@GetMapping("/hello")
	public ResponseEntity<String> sayHello(){
		return new ResponseEntity<>("hello world", HttpStatus.OK);
	}
	
	@GetMapping("/pizza")
	public ResponseEntity<List<Pizza>> getPizza(){
		
		List<Pizza> pizzas = pizzaService.findAll();
		
		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}
	
	@GetMapping("/pizza/search")
	public ResponseEntity<List<Pizza>> getPizzaByNome(@RequestParam("nome") String nome){
		
		List<Pizza> pizzas = pizzaService.findByNome(nome);
		
		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}
	
	@GetMapping("/pizza/{id}")
	public ResponseEntity<Pizza> getPizzaById(@PathVariable int id){
		
		Pizza optPizza = pizzaService.findById(id).get();
		
		return new ResponseEntity<>(optPizza, HttpStatus.OK);
	}
	
	@PostMapping("/pizza")
	public ResponseEntity<Pizza> storePizza(
			@RequestBody(required = true) Pizza pizza
		) {
		
		pizza = pizzaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@PutMapping("/pizza")
	public ResponseEntity<Pizza> updatePizza(
			@RequestBody(required = true) Pizza pizza
		) {
		
		pizza = pizzaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@DeleteMapping("/pizza/{id}")
	public ResponseEntity<Pizza> deletePizza(
			@PathVariable int id
		) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		
		Pizza pizza = optPizza.get();
		
		pizza.getIngredienti().clear();
		pizzaService.save(pizza);
		pizzaService.deletePizza(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
}
