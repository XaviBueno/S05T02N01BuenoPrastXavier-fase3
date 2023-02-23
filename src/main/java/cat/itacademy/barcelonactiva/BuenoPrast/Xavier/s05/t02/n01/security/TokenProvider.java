package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.security;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.security.Constants.*;

public class TokenProvider {

	private TokenProvider() {
	}

	public static String generateToken(Authentication authentication) {
		// Genera el token con roles, issuer, fecha, expiración (8h)
		final String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		return Jwts.builder()
				.setSubject(authentication.getName())
				.claim(AUTHORITIES_KEY, authorities)
				.signWith(Keys.hmacShaKeyFor(SIGNING_KEY.getBytes()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setIssuer(ISSUER_TOKEN)
				.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
				.compact();
	}

	public static UsernamePasswordAuthenticationToken getAuthentication(final String token,
			final UserDetails userDetails) {

		final JwtParserBuilder jwtParser = Jwts.parserBuilder().setSigningKey(SIGNING_KEY.getBytes());

		final Jws<Claims> claimsJws =  jwtParser.build().parseClaimsJws(token);

		final Claims claims = claimsJws.getBody();

		final Collection<SimpleGrantedAuthority> authorities =
				Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());

		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}

	public static String getUserName(final String token) {
		final JwtParserBuilder jwtParser = Jwts.parserBuilder().setSigningKey(SIGNING_KEY.getBytes());

		final Jws<Claims> claimsJws = jwtParser.build().parseClaimsJws(token);
		
		return claimsJws.getBody().getSubject();
	}
	

}
