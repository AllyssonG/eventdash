package br.com.isidrocorp.eventdash.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.isidrocorp.eventdash.dao.EventoDAO;
import br.com.isidrocorp.eventdash.model.Evento;

@RestController
public class EventoController {

	@Autowired
	private EventoDAO dao;
	
	@GetMapping("/eventos")
	public ArrayList<Evento> recuperarTodos(@RequestParam String ini, @RequestParam String fim) {
		ArrayList<Evento> lista;
		lista = (ArrayList<Evento>)dao.findAll();
		// vou converter essas strings para LocalDate e usar o método que eu criei
		LocalDate inicio = LocalDate.parse(ini);
		LocalDate dtfim = LocalDate.parse(fim);
		
		lista = dao.findAllByDataEventoBetween(inicio, dtfim);
		return lista;
	}
}
