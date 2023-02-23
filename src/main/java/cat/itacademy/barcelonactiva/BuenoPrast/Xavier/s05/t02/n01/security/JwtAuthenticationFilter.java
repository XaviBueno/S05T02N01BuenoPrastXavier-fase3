package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.security.Constants.HEADER_AUTHORIZATION_KEY;
import static cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.security.Constants.TOKEN_BEARER_PREFIX;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		super.setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler());
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			AuthorizationRequest userCredentials = new ObjectMapper().readValue(request.getReader(), AuthorizationRequest.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					userCredentials.getNom(), userCredentials.getPassword()));
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String token = TokenProvider.generateToken(authResult);
		response.addHeader(HEADER_AUTHORIZATION_KEY, TOKEN_BEARER_PREFIX + " " + token);
	}
}
