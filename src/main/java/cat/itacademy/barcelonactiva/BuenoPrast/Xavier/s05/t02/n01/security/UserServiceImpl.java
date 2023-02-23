package cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.security;

import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Role;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.domain.Jugador;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.repository.RoleRepository;
import cat.itacademy.barcelonactiva.BuenoPrast.Xavier.s05.t02.n01.model.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {

	private RoleRepository roleRepository;

	private JugadorRepository jugadorRepository;

	@Autowired
	public UserServiceImpl(JugadorRepository userRepository, RoleRepository roleRepository) {
		this.jugadorRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		 Jugador retrievedUser = jugadorRepository.findByNom(userName);
		if (retrievedUser == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}

		return UserDetailsMapper.build(retrievedUser);
	}

	@Override
	public Jugador getUser(int id) {
		Jugador jugador=new Jugador();
		jugador=jugadorRepository.findById(id).orElse(null);
		return jugador; 
	}

	@Override
	public Jugador save(Jugador jugador) {
		Role userRole = roleRepository.findByName("USER");
		Set<Role> roles = new HashSet<>();
		roles.add(userRole);

		Jugador userToSave = Jugador.builder().nom(jugador.getNom()).password(jugador.getPassword()).roles(roles).build();

		return jugadorRepository.save(userToSave);
	}
}
