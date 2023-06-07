package org.java.best;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.java.best.auth.pojo.Role;
import org.java.best.auth.pojo.User;
import org.java.best.auth.serv.RoleService;
import org.java.best.auth.serv.UserService;
import org.java.best.pojo.Ingrediente;
import org.java.best.pojo.Offerta;
import org.java.best.pojo.Pizza;
import org.java.best.service.ServiceIngrediente;
import org.java.best.service.ServiceOfferta;
import org.java.best.service.ServicePizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {
	
	@Autowired
	private ServicePizza servicePizza;
	
	@Autowired
	private ServiceOfferta serviceOfferta;
	
	@Autowired
	private ServiceIngrediente serviceIngrediente;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		Role roleUser = new Role("USER");
		Role roleAdmin = new Role("ADMIN");
		
		roleService.save(roleUser);
		roleService.save(roleAdmin);
		
		final String pws = new BCryptPasswordEncoder().encode("pws");
		
		User userUser = new User("user", pws, roleUser);
		User userAdmin = new User("admin", pws, roleAdmin);
				
		userService.save(userUser);
		userService.save(userAdmin);
		
		
		
		Pizza p1 = new Pizza("margherita1", "descrizione margherita", "https://staticfanpage.akamaized.net/wp-content/uploads/sites/9/2019/08/LP_8611563.jpg", 1);
		Pizza p2 = new Pizza("margherita2", "descrizione margherita", "https://staticfanpage.akamaized.net/wp-content/uploads/sites/9/2019/08/LP_8611563.jpg", 1);
		Pizza p3 = new Pizza("margherita3", "descrizione margherita", "https://staticfanpage.akamaized.net/wp-content/uploads/sites/9/2019/08/LP_8611563.jpg", 1);
		
		servicePizza.save(p1);
		servicePizza.save(p2);
		servicePizza.save(p3);
		
		Ingrediente i1 = new Ingrediente("ingrediente 1");
		Ingrediente i2 = new Ingrediente("ingrediente 2");
		Ingrediente i3 = new Ingrediente("ingrediente 3");
		Ingrediente i4 = new Ingrediente("ingrediente 4");
		
		serviceIngrediente.save(i1);
		serviceIngrediente.save(i2);
		serviceIngrediente.save(i3);
		serviceIngrediente.save(i4);
		
		Offerta o1 = new Offerta(LocalDate.of(2023, 12, 12), LocalDate.parse("2023-12-12"), "ciao", 20, p1);
		
		serviceOfferta.save(o1);
		
		List<Pizza> pizzas = servicePizza.findAll();
		System.out.println(pizzas);
		
		Optional<Pizza> optPizza = servicePizza.findByIdWithOfferte(1);
		Pizza firstPizza = optPizza.get();
		
		System.out.println(firstPizza);
		System.out.println(firstPizza.getOfferte());
		
		if (optPizza.isPresent()) {
			
			Pizza dbPizza = optPizza.get();
			
			System.out.println("Pizza con id 1");
			System.out.println("--------------");
			System.out.println(dbPizza);
		} else 
			System.out.println("Pizza con id 1 non trovato :-(");
	}
		
		
	

}
