package br.com.isidrocorp.eventdash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.isidrocorp.eventdash.dao.UsuarioDAO;
import br.com.isidrocorp.eventdash.model.Usuario;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioDAO dao;
	
	
	// quero fazer um método de LOGIN - para isso preciso enviar alguma informação pro Back End
	@PostMapping("/login")
	public ResponseEntity<Usuario> fazerLogin(@RequestBody Usuario dadosLogin) {
		
		Usuario res= dao.findByEmailOrRacf(dadosLogin.getEmail(), dadosLogin.getRacf());
		if (res != null) {// encontrei o usuário no banco
			if(res.getSenha().equals(dadosLogin.getSenha())) {
				return ResponseEntity.ok(res);
			}
		}
		return ResponseEntity.status(403).build();
		
	}
}
