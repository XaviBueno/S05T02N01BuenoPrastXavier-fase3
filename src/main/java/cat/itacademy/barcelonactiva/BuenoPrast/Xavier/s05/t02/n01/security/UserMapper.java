package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.security;

import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Jugador;


public class UserMapper {

	private UserMapper() {
	}

	public static UserResponse toResponse(Jugador jugador) {
		return UserResponse.builder().name(jugador.getNom()).id(jugador.getIdJugador()).build();
	}

	public static Jugador toDomain(AuthorizationRequest authorizationRequest) {
		return Jugador.builder().nom(authorizationRequest.getNom()).password(authorizationRequest.getPassword())
				.build();
	}
}
