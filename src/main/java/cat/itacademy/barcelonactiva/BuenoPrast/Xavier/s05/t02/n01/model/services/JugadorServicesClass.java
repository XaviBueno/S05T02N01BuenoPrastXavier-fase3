package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.services;


import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.dto.JugadorDto;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Jugador;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Role;
import  cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.repository.JugadorRepository;
import  cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.repository.JugadaRepository;
import  cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.repository.RoleRepository;
import lombok.AllArgsConstructor;


@Service

public class JugadorServicesClass implements JugadorServices{
	
	@Autowired
	
	private JugadorRepository jugadorRepository;
	
	@Autowired
	private JugadaRepository jugadaRepository;
	
	@Autowired
	
	private RoleRepository roleRepository;

	@Override
	
	public Jugador saveJugador(JugadorDto jugadorDto) {
		
		Set<Role> roles =new HashSet<>();;
		
		String nom;
		String password;
		JugadorDto jugador= jugadorDto;
		nom=jugadorDto.getNom();
		password=jugadorDto .getPassword();
		jugadorDto.setPassword(new BCryptPasswordEncoder().encode(password));
		jugadorDto.setExits(0);
		Role role=roleRepository.findByName("PLAYER");
		roles.add(role);
		jugadorDto.setRoles(roles);
		nom.replace("\\s","");
		if(nom.isEmpty()) {
			jugador.setNom("ANONIM");
		}
		return jugadorRepository.save(new Jugador(jugador));
	}

	

	@Override
	public JugadorDto getOneJugador(int ident) {
		
		Jugador jugador=new Jugador();
		jugador=jugadorRepository.findById(ident).orElse(null);
		if(jugador==null) {
			return null;
		}
		else
			return new JugadorDto(jugador);
	}


	@Override
	public ArrayList<JugadorDto> getAllJugadors() {
		
		ArrayList<Jugador> jugadors= new ArrayList<Jugador>(jugadorRepository.findAll());
		ArrayList<JugadorDto> jugadorsDto=new ArrayList<JugadorDto>();
		Iterator<Jugador> iterator= jugadors.iterator();
		while(iterator.hasNext()) {
			jugadorsDto.add(new JugadorDto(iterator.next()));
			
		}
		return jugadorsDto;
		
	}


	

	@Override
	public boolean buscarJugador(String nom) {
		boolean resp=false;
		Jugador jugador= new Jugador();
		jugador=jugadorRepository.findByNom(nom);
		if(jugador!=null) {
			resp=true;
		}
		
		return resp;
		
	}


	public void esborraJugadesJugador(JugadorDto jugador) {
	
		int id= jugador.getIdJugador();;
		
		if(jugadorRepository.existsById(id)) {
			Jugador _jugador= new Jugador(jugador);
			jugadaRepository.removeByJugador(_jugador);
		}
		
		
	}
	

  
}

	

	

	

	
	


