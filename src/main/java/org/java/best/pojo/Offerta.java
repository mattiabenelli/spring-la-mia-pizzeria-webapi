package org.java.best.pojo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Offerta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@FutureOrPresent(message = "la data non può essere precendente ad oggi")
	private LocalDate dataInizio;
	@FutureOrPresent(message = "la data non può essere precendente ad oggi")
	private LocalDate dataFine;
	@NotBlank(message = "il nome non può essere vuoto")
	private String nomeOfferta;
	@Min(value = 1, message = "Il prezzo non può essere minore di 0")
	private int sconto;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pizza pizza;
	
	
	public Offerta() {}
	public Offerta(LocalDate dataInizio, LocalDate dataFine, String nomeOfferta, int sconto, Pizza pizza) {
		
		setDataInizio(dataInizio);
		setDataFine(dataFine);
		setNomeOfferta(nomeOfferta);
		setSconto(sconto);
		setPizza(pizza);
	}
	
	
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDate getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
	public String getNomeOfferta() {
		return nomeOfferta;
	}
	public void setNomeOfferta(String nomeOfferta) {
		this.nomeOfferta = nomeOfferta;
	}
	public int getSconto() {
		return sconto;
	}
	public void setSconto(int sconto) {
		this.sconto = sconto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	public float PrezzoScontanto() {
		
		return (getPizza().getPrice()) * (1 - getSconto() / 100f);
		
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " 
		+ "durata offerta: " + getDataInizio() + " - " + getDataFine()
		+ "nome offerta: " + getNomeOfferta()
		+ "sconto: " + getSconto();
	}
}
