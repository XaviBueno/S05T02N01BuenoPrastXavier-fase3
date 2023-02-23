package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.security.JwtAuthorizationFilter;
import lombok.AllArgsConstructor;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true) // Habilitamos la securización de nuestra API con @Secured
@EnableGlobalMethodSecurity(prePostEnabled = true) 
@AllArgsConstructor
public class WebSecurityConfig  {

	private final UserDetailsService userDetailsService;
	


	

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean 
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				
				.passwordEncoder(encoder())
				.and()
				.build();
				
	}
	
	
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Configuración de la clase que recupera los usuarios y algorito para procesar las passwords
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}
	*/
	
	/*
	 * 
	 @Override
protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(customAuthenticationProvider)
        .authenticationProvider(googleCloudAuthenticationProvider);
}
New version (After Spring Security 5.7.0)

@Autowired
void registerProvider(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(customAuthenticationProvider)
        .authenticationProvider(googleCloudAuthenticationProvider);
}
	 */
	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilterBean() {
		return new JwtAuthorizationFilter();
	}
	@Bean
	public  SecurityFilterChain filterChain(HttpSecurity http,AuthenticationManager authManager) throws Exception  {
		JwtAuthenticationFilter jwtAuthenticationFilter =new  JwtAuthenticationFilter(authenticationManager(http));
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		//jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		http
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.cors().and()
				.csrf().disable()
				.authorizeHttpRequests().requestMatchers(HttpMethod.POST,"/login").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(jwtAuthorizationFilterBean(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
