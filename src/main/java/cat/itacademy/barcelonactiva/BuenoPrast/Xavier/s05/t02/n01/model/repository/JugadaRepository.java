package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Jugada;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Jugador;

public interface JugadaRepository extends JpaRepository<Jugada, Integer> {
	
	public long removeByJugador(Jugador jugador);
	
	public List<Jugada> findByJugador(Jugador jugador);
	public List<Jugada> findByJugadorAndResultat(Jugador jugador,boolean resultat);
	public List<Jugada> findAllByResultat(boolean resultat);

}
