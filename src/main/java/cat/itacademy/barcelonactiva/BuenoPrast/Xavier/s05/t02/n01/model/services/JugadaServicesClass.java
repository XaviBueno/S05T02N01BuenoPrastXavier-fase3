package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.services;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Jugada;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.dto.JugadaDto;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.repository.JugadaRepository;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.dto.JugadorDto;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Jugador;

@Service
public class JugadaServicesClass implements JugadaServices{

	
	
	@Autowired
	private JugadaRepository jugadaRepository;
	
	
	
	
	@Override
	public JugadaDto generaJugada(JugadorDto jugador) {
		int dau1=10;
		int dau2=10;
		
		
		 dau1=(int)(Math.random()*6)+1;
		 dau2=(int)(Math.random()*6)+1;
		 
			
		JugadaDto jugada =new  JugadaDto(dau1,dau2,jugador);
		if((dau1+dau2==7)){
			jugada.setResultat(true);
		}
		else {
			jugada.setResultat(false);
		}
		
		 
		
		
		return jugada;
		
	}
	
	@Override
	public double calculaExits(JugadorDto jugador) {
		List<Jugada> jugadesJugador;
		 jugadesJugador=jugadaRepository.findByJugador(new Jugador(jugador));
		 double numJugades=jugadesJugador.size();
		 jugadesJugador=jugadaRepository.findByJugadorAndResultat(new Jugador(jugador),true);
		 double numExits=jugadesJugador.size();
		 numExits=numExits/numJugades*100;
		 return numExits;
	}
	
	public double calculaExitsTotals() {
		List<Jugada> jugades;
		
		jugades=jugadaRepository.findAll();
		double jugadesTotals=jugades.size();
		jugades=jugadaRepository.findAllByResultat(true);
		double jugadesExit=jugades.size();
		jugadesExit=jugadesExit/jugadesTotals*100;
		return jugadesExit;
		
		
	}
	
public ArrayList<JugadaDto> jugadesJugador(JugadorDto jugadorDto){
		
		ArrayList<JugadaDto> jugadesDto =new ArrayList<JugadaDto>();
		Jugador jugador =new Jugador(jugadorDto);
		
		ArrayList<Jugada> jugades= new ArrayList<Jugada>(jugadaRepository.findByJugador(jugador));
		Iterator<Jugada> iterator= jugades.iterator();
		while(iterator.hasNext()) {
			jugadesDto.add(new JugadaDto(iterator.next()));
		}
		
		return jugadesDto;
	}

@Override
public Jugada saveJugada(JugadaDto jugada) {
	
	
	
	return  jugadaRepository.save(new Jugada(jugada));

}
	


}
