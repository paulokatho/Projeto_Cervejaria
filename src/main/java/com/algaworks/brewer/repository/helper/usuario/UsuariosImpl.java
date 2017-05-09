package com.algaworks.brewer.repository.helper.usuario;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algaworks.brewer.model.Usuario;

public class UsuariosImpl implements UsuariosQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Optional<Usuario> porEmailEAtivo(String email) {
		
		return manager
				.createQuery("from Usuario where lower(email) = lower(:email) and ativo = true", Usuario.class)
				.setParameter("email", 	email).getResultList().stream().findFirst();
		
		/*
			createQuery é jpql e (:email) é o parametro que vem de (String email) e Usuario.class é para termos a classe usuario disponível
			setParamenter é para o parametro email que pega o resultList.stream.findFirst é da API java 8 que facilita no caso do usuario ser nulo.
		*/
	}

}
