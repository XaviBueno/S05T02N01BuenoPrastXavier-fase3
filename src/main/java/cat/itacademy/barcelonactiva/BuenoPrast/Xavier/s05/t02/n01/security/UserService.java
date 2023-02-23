package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.security;

import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Jugador;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	Jugador getUser(int id);

	Jugador save(Jugador user);
}
