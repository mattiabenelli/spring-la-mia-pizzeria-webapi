package org.java.best.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.best.pojo.Pizza;
import org.java.best.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ServicePizza {

	@Autowired
	private PizzaRepo pizzaRepo;
	
	public List<Pizza> findAll() {
		
		return pizzaRepo.findAll();
	}

	public Optional<Pizza> findById(int id) {
		
		return pizzaRepo.findById(id);
	}
	
	public List<Pizza> findByNome(String nome) {
		
		return pizzaRepo.findByNomeContaining(nome);
	}
	
	public void deletePizza(Pizza pizza) {
		
		pizzaRepo.delete(pizza);
	}
	
	
	@Transactional
	public Optional<Pizza> findByIdWithOfferte(int id) {
		
		Optional<Pizza> pizzaOpt = pizzaRepo.findById(id);
		Hibernate.initialize(pizzaOpt.get().getOfferte());
		
		return pizzaOpt;
	}
	public Pizza save(Pizza pizza) {
		
		return pizzaRepo.save(pizza);
	}
}
	
	

