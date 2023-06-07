package org.java.best.service;

import java.util.List;
import java.util.Optional;

import org.java.best.pojo.Offerta;
import org.java.best.repo.OffertaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceOfferta {
	
	@Autowired
	private OffertaRepo offertaRepo;
	
	public List<Offerta> findAll() {
		
		return offertaRepo.findAll();
	}
	public Offerta save(Offerta offerta) {
		
		return offertaRepo.save(offerta);
	}
	public Optional<Offerta> findById(int id) {
		
		return offertaRepo.findById(id);
	}
}
