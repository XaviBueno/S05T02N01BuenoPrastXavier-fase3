package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.dto;

import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Jugada;


public class JugadaDto {
	
	private int idJugada;
	private int resultDau1;
	private int resultDau2;
	private boolean resultat;
	private JugadorDto jugador;
		

	

	public JugadaDto( int resultDau1, int resultDau2,JugadorDto jugador) {
		
		this.resultDau1 = resultDau1;
		this.resultDau2 = resultDau2;
		this.jugador = jugador;
	}

	
	
	public JugadaDto(Jugada jugada) {
		this.idJugada=jugada.getIdJugada();
		this.resultDau1 = jugada.getResultDau1();
		this.resultDau2 = jugada.getResultDau2();
		this.resultat=jugada.getResultat();
		this.jugador = new JugadorDto(jugada.getJugador());
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



	public JugadorDto getJugador() {
		return jugador;
	}



	public void setJugador(JugadorDto jugador) {
		this.jugador = jugador;
	}



	public boolean getResultat() {
		return resultat;
	}



	public void setResultat(boolean resultat) {
		this.resultat = resultat;
	}

	

	


}
