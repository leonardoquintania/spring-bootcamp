package br.com.uniciv.mycash.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.uniciv.mycash.domain.Usuario;
import br.com.uniciv.mycash.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl 
	implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {

		Usuario usuario =  usuarioRepo.findByEmail(username)
				.orElseThrow( () -> new UsernameNotFoundException("Usuario não encontrado"));

		SimpleGrantedAuthority authority = 
				new SimpleGrantedAuthority(usuario.getRole().name());
		
		return new User(
				usuario.getEmail(), 
				usuario.getSenha(), 
				Arrays.asList(authority));
	}

}