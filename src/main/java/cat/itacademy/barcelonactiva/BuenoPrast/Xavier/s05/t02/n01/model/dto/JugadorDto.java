package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Set;

import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Jugador;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Role;


public class JugadorDto {
	
	
	private int idJugador;
	private String nom;
	private double exits;
	private String password;
	private Set<Role> roles;


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public JugadorDto(int idJugador, String nom) {
		this.idJugador = idJugador;
		this.nom = nom;
	}


	public JugadorDto() {
	}
	
	public JugadorDto(Jugador jugador) {
		
		this.idJugador=jugador.getIdJugador();
		this.nom=jugador.getNom();
		this.exits=jugador.getExits();
		this.password=jugador.getPassword();
		this.roles=jugador.getRoles();
		
		
	}


	public int getIdJugador() {
		return idJugador;
	}


	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public double getExits() {
		return exits;
	}


	public void setExits(double exits) {
		this.exits = exits;
	}
	
	
	
	
}
