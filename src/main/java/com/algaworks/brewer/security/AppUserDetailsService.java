package com.algaworks.brewer.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.Usuarios;

@Service
public class AppUserDetailsService implements UserDetailsService{

	/*precisamos carregar um user pelo username que é o valor do e-mail: aula 19-4
		O username vai ser o email que está na tela de Login.html, ou seja o nome do atributo que está lá.
		Também temos que fazer a busca no repositorio/herper de usuario por email e ativo.
	*/
	
	@Autowired
	private Usuarios usuarios;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {//esse método lança uma exception que temos que tratar no "orElseThrow()"
		Optional<Usuario> usuarioOptional = usuarios.porEmailEAtivo(email);
		//tentamos pegar um usuário, mas caso não consigamos lançamos a exception
		//usamos a expressão lambda "->" para lançar a exception
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senhas incorretos"));
				
		//return new User(usuario.getEmail(), usuario.getSenha(), new HashSet<>()); //Aqui é onde se cadastra as Role() que esta no SecurityConfig, o HasSet está vazio somente passando a senha e usuario, mas o certo é pegar do banco as Roles()
		return new User(usuario.getEmail(), usuario.getSenha(), getPermissoes(usuario));
	}


	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		12:57
		// Lista permissoes do usuario
		return authorities;
	}

}