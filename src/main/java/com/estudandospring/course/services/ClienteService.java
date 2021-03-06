package com.estudandospring.course.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudandospring.course.domain.Cliente;
import com.estudandospring.course.repositories.ClienteRepository;
import com.estudandospring.course.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
}
