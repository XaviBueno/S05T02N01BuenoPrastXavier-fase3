package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.services;

import java.util.ArrayList;

import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.dto.JugadaDto;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.dto.JugadorDto;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Jugada;
public interface JugadaServices {
	
	JugadaDto generaJugada(JugadorDto jugador);
	double calculaExits(JugadorDto jugador);
	double calculaExitsTotals();
	ArrayList<JugadaDto> jugadesJugador(JugadorDto jugadorDto);
	Jugada saveJugada(JugadaDto jugada);
	
	

}
