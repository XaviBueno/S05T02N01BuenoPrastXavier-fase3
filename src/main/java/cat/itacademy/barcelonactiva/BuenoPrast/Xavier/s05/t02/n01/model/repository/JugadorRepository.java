package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Integer> {
	
	public Jugador findByNom(String nom);
 
}
