package org.java.best.pojo;




import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "il nome non può essere vuoto")
	private String nome;
	@NotBlank(message = "la descrizione non può essere vuota")
	private String descrizione;
	@NotBlank(message = "l'url non può essere vuoto")
	private String foto;
	@Min(value = 1, message = "Il prezzo non può essere minore di 0")
	private int price;
	
	@OneToMany(mappedBy = "pizza")
	private List<Offerta> offerte;
	
	@ManyToMany
	private List<Ingrediente> ingredienti;
	
	
	public Pizza() { }
	public Pizza(String nome, String descrizione, String foto, int price, Ingrediente... ingredienti) {
		
		setNome(nome);
		setDescrizione(descrizione);
		setFoto(foto);
		setPrice(price);
		
		setIngredienti(ingredienti);
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public int getPrice() {
		return price;
	}
	public List<Offerta> getOfferte() {
		return offerte;
	}
	public void setOfferte(List<Offerta> offerte) {
		this.offerte = offerte;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}
	
	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}

	public void setIngredienti(Ingrediente[] ingredienti) {
		setIngredienti(Arrays.asList(ingredienti));
	}
	
	@Override
	public String toString() {
		return "[" + getId() + "] "
				+ "\nnome: " + getNome()
				+ "\ndescrizione: " + getDescrizione()
				+ "\nurl foto: " + getFoto()
				+ "\n prezzo:" + getPrice();
	}
}
