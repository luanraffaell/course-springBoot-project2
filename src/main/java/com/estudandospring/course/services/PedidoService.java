package com.estudandospring.course.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudandospring.course.domain.Pedido;
import com.estudandospring.course.repositories.PedidoRepository;
import com.estudandospring.course.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
}
