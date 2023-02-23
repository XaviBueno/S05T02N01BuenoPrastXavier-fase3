package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.security;

import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Role;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Jugador;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsMapper {

	public static UserDetails build(Jugador jugador) {
		return new org.springframework.security.core.userdetails.User(jugador.getNom(), jugador.getPassword(), getAuthorities(jugador));
	}

	private static Set<? extends GrantedAuthority> getAuthorities(Jugador retrievedUser) {
		Set<Role> roles = retrievedUser.getRoles();

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));

		return authorities;
	}
}
