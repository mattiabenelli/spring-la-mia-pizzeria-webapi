package org.java.best.service;

import java.util.List;
import java.util.Optional;

import org.java.best.pojo.Ingrediente;
import org.java.best.repo.IngredienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceIngrediente {
	
	@Autowired
	private IngredienteRepo ingredienteRepo;
	
	public List<Ingrediente> findAll() {
		
		return ingredienteRepo.findAll();
	}
	public Optional<Ingrediente> findById(int id) {
		
		return ingredienteRepo.findById(id);
	}
	public Ingrediente save(Ingrediente ingrediente) {
		
		return ingredienteRepo.save(ingrediente);
	}
	
	public void deleteIngrediente(Ingrediente ingrediente) {
		
		ingredienteRepo.delete(ingrediente);
	}
}
