package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.dto.JugadaDto;

@Entity
@Table(name="jugades")
public class Jugada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="jugades_id")
	private int idJugada;
	/*
	@Column(name="jugador_id")
	private int idJugador;
	*/
	@Column(name="resultat_dau1")
	private int resultDau1;
	
	@Column(name="resultat_dau2")
	private int resultDau2;
	
	@Column(name="resultat")
	private boolean resultat;


	
	@ManyToOne
	@JoinColumn(name="jugador_id")
	private Jugador jugador;

	public Jugada(int idJugada) {
		this.idJugada = idJugada;
		
	}

	public Jugada() {
	}
	
	public Jugada(JugadaDto jugadaDto) {
		this.idJugada=jugadaDto.getIdJugada();
		this.resultDau1=jugadaDto.getResultDau1();
		this.resultDau2=jugadaDto.getResultDau2();
		this.resultat=jugadaDto.getResultat();
		this.jugador= new Jugador(jugadaDto.getJugador());
	}

	public int getIdJugada() {
		return idJugada;
	}

	public void setIdJugada(int idJugada) {
		this.idJugada = idJugada;
	}

	

	public int getResultDau1() {
		return resultDau1;
	}

	public void setResultDau1(int resultDau1) {
		this.resultDau1 = resultDau1;
	}

	public int getResultDau2() {
		return resultDau2;
	}

	public void setResultDau2(int resultDau2) {
		this.resultDau2 = resultDau2;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public boolean getResultat() {
		return resultat;
	}

	public void setResultat(boolean resultat) {
		this.resultat = resultat;
	}
	
	
	

}
