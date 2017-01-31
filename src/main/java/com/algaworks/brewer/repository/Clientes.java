package com.algaworks.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.brewer.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long> {

	//Optional é uma verificação para ver se não é nulo, pois pode existir ou não. 
	//Essa forma é mais orientada a objetos utilizando java
	//A partir do java 8
	public Optional<Cliente> findByCpfOuCnpj(String cpfOuCnpj);

}
