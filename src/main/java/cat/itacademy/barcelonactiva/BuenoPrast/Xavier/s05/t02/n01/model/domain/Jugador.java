package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;



import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Role;

import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.dto.JugadorDto;

@Entity
@Builder
@AllArgsConstructor
@Data


@Table(name="jugadors")
public class Jugador {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="jugador_id")
	private int idJugador;
	
	
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
	@Column(name="nom")
	private String nom;
	
	@Column(name="exits")
	private double exits;

	@OneToMany(mappedBy = "jugador")
	private List<Jugada> jugades;
	
	public Jugador(int idJugador, String nom) {
		this.idJugador = idJugador;
		this.nom = nom;
	}
	@Column
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "JUGADOR_ROLES", joinColumns = {
			@JoinColumn(name = "JUGADOR_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles;


	public List<Jugada> getJugades() {
		return jugades;
	}

	public void setJugades(List<Jugada> jugades) {
		this.jugades = jugades;
	}

	public Jugador() {
	}

	public Jugador(JugadorDto jugador) {
		
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
