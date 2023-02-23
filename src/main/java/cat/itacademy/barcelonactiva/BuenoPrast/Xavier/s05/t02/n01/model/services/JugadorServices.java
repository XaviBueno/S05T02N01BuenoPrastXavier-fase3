package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.services;

import java.util.ArrayList;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.dto.JugadorDto;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Jugador;
public interface JugadorServices {
	
	public Jugador saveJugador (JugadorDto jugador);
				
	public JugadorDto getOneJugador(int ident);
	
	public ArrayList<JugadorDto> getAllJugadors();
	
	public boolean buscarJugador(String nom);
	
	

}
