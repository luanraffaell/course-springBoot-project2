package com.estudandospring.course.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estudandospring.course.domain.Cliente;
import com.estudandospring.course.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	//@GetMapping(value = "/{id}") também funciona
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {	
		Cliente obj = service.buscar(id);		
		return ResponseEntity.ok().body(obj);
	}
}
