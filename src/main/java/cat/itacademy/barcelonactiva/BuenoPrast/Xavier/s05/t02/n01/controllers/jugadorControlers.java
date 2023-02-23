package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.dto.JugadorDto;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.dto.JugadaDto;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.services.JugadorServicesClass;
import jakarta.transaction.Transactional;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.services.JugadaServicesClass;

@Controller
public class jugadorControlers {
	
	@Autowired
	private JugadorServicesClass jugadorServices; 
	@Autowired
	private JugadaServicesClass  jugadaServices; 
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/players")
	
	ResponseEntity <String> crearJugador(@RequestBody JugadorDto jugador){
		
		ResponseEntity<String> resposta;
		if(jugadorServices.buscarJugador(jugador.getNom())) {
			resposta= new ResponseEntity<String>("El jugador ja existeix", HttpStatus.BAD_REQUEST);
			
		}
		else {
			jugadorServices.saveJugador(jugador);
			resposta= new ResponseEntity<String>("Jugador desat", HttpStatus.CREATED);
		}
		
		return resposta;
		
	}
	@PreAuthorize("hasRole('ROLE_PLAYER')")
	@PutMapping("/players")
			
	ResponseEntity<String> modificaNomJugador(@RequestBody JugadorDto jugador){
		
		ResponseEntity<String> resposta;
		JugadorDto _jugador;
		int id;
		String nom;
		 nom=jugador.getNom();
		 id=jugador.getIdJugador();
		 _jugador= jugadorServices.getOneJugador(id);
		if (_jugador==null) {
			
			resposta= new ResponseEntity<String>("Jugador inexistent",HttpStatus.BAD_REQUEST);
			
		}
		else {
			if(jugadorServices.buscarJugador(nom)) {
				resposta=new ResponseEntity<String>("El nom de jugador ja existeix",HttpStatus.BAD_REQUEST);
			}
			else {
				_jugador.setNom(nom);
				jugadorServices.saveJugador(_jugador);
				resposta= new ResponseEntity<String>("Nom jugador modificat",HttpStatus.OK);
				
			}
			
			
		}
		
		
		return resposta;
		
	}
	
	@PreAuthorize("hasRole('ROLE_PLAYER')")
	@PostMapping("/players/{id}/games")
	
	public ResponseEntity<String> jugadaJugador(@PathVariable ("id") int id) {
		JugadaDto jugada;
		JugadorDto jugador=jugadorServices.getOneJugador(id);
		ResponseEntity<String> resp;
		if (jugador==null){
			resp=new ResponseEntity<String>("Jugador Inexistent",HttpStatus.BAD_REQUEST);
			
		}
		else {
			
			jugada=jugadaServices.generaJugada(jugador);
			
			jugadaServices.saveJugada(jugada);
			jugador.setExits(jugadaServices.calculaExits(jugador));
			jugadorServices.saveJugador(jugador);
			
			
			
			resp= new ResponseEntity<String>("Jugada realitzada",HttpStatus.OK);
			
		}
		
		return resp;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/players/{id}/games")
	@Transactional
	public ResponseEntity<String> esborraJugades(@PathVariable ("id") int id){
		ResponseEntity<String> resp;
		JugadorDto jugador=jugadorServices.getOneJugador(id);
		if (jugador==null){
			resp=new ResponseEntity<String>("Jugador Inexistent",HttpStatus.BAD_REQUEST);
			
		}
		else {
			
			jugadorServices.esborraJugadesJugador(jugador);
			resp=new ResponseEntity<String>("Partides esborrades",HttpStatus.OK);;
			
		}
		return resp;
	}
	@PreAuthorize("hasRole('ROLE_PLAYER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/players")
	
	public ResponseEntity<ArrayList<JugadorDto>> llistarJugadors(){
		
		
		
		return new ResponseEntity<ArrayList<JugadorDto>> (jugadorServices.getAllJugadors(),HttpStatus.OK);
		
	}
	
	
	@PreAuthorize("hasRole('ROLE_PLAYER') or hasRole('ROLE_ADMIN') ")
	@GetMapping("/players/{id}/games")
	@Transactional
	
	public ResponseEntity<ArrayList<JugadaDto>>  llistarJugades(@PathVariable ("id") int id){
		
		JugadorDto jugador=jugadorServices.getOneJugador(id);
		
		return new ResponseEntity<ArrayList<JugadaDto>>( jugadaServices.jugadesJugador(jugador), HttpStatus.OK);
		
	}
	@PreAuthorize("hasRole('ROLE_PLAYER') or hasRole('ROLE_ADMIN') ")
	@GetMapping("players/ranking")
	
	public ResponseEntity<String> ranking(){
		
		double exits;
		exits=jugadaServices.calculaExitsTotals();
		
		return new ResponseEntity<String> (String.format("\"El nº total d'éxits és...%.2f %%", exits) ,HttpStatus.OK);
		
	}
	@PreAuthorize("hasRole('ROLE_PLAYER') or hasRole('ROLE_ADMIN') ")
	@GetMapping("players/ranking/loser")
	
	public ResponseEntity<ArrayList<JugadorDto>>loser(){
		
		List<JugadorDto> jugadors=jugadorServices.getAllJugadors();
		ArrayList<JugadorDto> losers=new ArrayList<JugadorDto>();
		JugadorDto jugador= new JugadorDto();
		
		Collections.sort(jugadors,Comparator.comparingDouble(val-> val.getExits()) );
		Iterator<JugadorDto> iter= jugadors.iterator();
		while(iter.hasNext()) {
			jugador= iter.next();
			if(jugador.getExits()==jugadors.get(0).getExits()) {
				losers.add(jugador);
				
			}
			
		}
		
		return new ResponseEntity<ArrayList<JugadorDto>>(losers,HttpStatus.OK);
	}  
	@PreAuthorize("hasRole('ROLE_PLAYER') or hasRole('ROLE_ADMIN') ")
	@GetMapping("players/ranking/winner")
	public ResponseEntity<ArrayList<JugadorDto>>winner(){
		
		List<JugadorDto> jugadors=jugadorServices.getAllJugadors();
		ArrayList<JugadorDto> winners=new ArrayList<JugadorDto>();
		JugadorDto jugador= new JugadorDto();
		
	    Collections.sort(jugadors,Collections.reverseOrder(Comparator.comparingDouble(val-> val.getExits()) ));
		
		Iterator<JugadorDto> iter= jugadors.iterator();
		while(iter.hasNext()) {
			jugador= iter.next();
			if(jugador.getExits()==jugadors.get(0).getExits()) {
				winners.add(jugador);
				
			}
			
		}
		
		return new ResponseEntity<ArrayList<JugadorDto>>(winners,HttpStatus.OK);
	}  
	
		
}
	
	
	
	
	
	
		
	
		
		
	
	
	
	
	


