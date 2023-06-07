package org.java.best.api.controller;

import java.util.List;

import org.java.best.pojo.Pizza;
import org.java.best.service.ServicePizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
}
