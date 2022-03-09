package com.estudandospring.course.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estudandospring.course.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	//@GetMapping também funciona
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria cat = new Categoria(1, "Informatica");
		Categoria cat1 = new Categoria(2, "Escritorio");
		
		List<Categoria> catList = Arrays.asList(cat,cat1);
		return catList;
	}
}
