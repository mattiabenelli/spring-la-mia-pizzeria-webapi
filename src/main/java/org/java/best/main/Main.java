package org.java.best.main;


import java.util.List;
import java.util.Optional;

import org.java.best.pojo.Pizza;
import org.java.best.service.ServicePizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Main {

	@SpringBootApplication
	public class CrudTest1Application implements CommandLineRunner {

		@Autowired
		private ServicePizza pizzaservice;

		// ...

		@Override
		public void run(String... args) throws Exception {
			
			Pizza p1 = new Pizza("margherita", "descrizione margherita", "sium", 1);
			System.out.println(p1);
			
			pizzaservice.save(p1);
			
			List<Pizza> pizzas = pizzaservice.findAll();
			System.out.println(pizzas);
			
			Optional<Pizza> optPizza = pizzaservice.findById(1);
			
			if (optPizza.isPresent()) {
				
				Pizza dbPizza = optPizza.get();
				
				System.out.println("Pizza con id 1");
				System.out.println("--------------");
				System.out.println(dbPizza);
			} else 
				System.out.println("Pizza con id 1 non trovato :-(");
		}
	}

	
}
