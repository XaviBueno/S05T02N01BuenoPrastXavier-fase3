package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
public class AuthorizationRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("nom")
	private String nom;

	private String password;

	public AuthorizationRequest() {
	}
}
