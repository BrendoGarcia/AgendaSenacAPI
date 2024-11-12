package com.agendasenac.controllers;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agendasenac.modells.Curso;
import com.agendasenac.repository.CursoRepository;

@RestController
public class CursoController {
	
	@Autowired
	private CursoRepository cr;

	@GetMapping("/cursos")
	public Iterable<Curso> curso() {
		return cr.findAll();
	}
	
	@PostMapping("/cursos")
	@CrossOrigin 
	public ResponseEntity<String> cadastrocurso(@RequestBody Curso curso) {
	        cr.save(curso);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Curso cadastrado com sucesso");
	}
	
	
	@PutMapping("/cursos/{idCurso}")
	@CrossOrigin
	public ResponseEntity<String> atualizarTurma(@PathVariable Long idCurso, @RequestBody Map<String, Object> updates) {
	    Optional<Curso> optionalcurso = Optional.ofNullable(cr.findByidCurso(idCurso));
	    
	    if (optionalcurso.isPresent()) {
	        Curso curso = optionalcurso.get();
	        
	        updates.forEach((key, value) -> {
	            try {
	                Field field = Curso.class.getDeclaredField(key);
	                field.setAccessible(true);
	                field.set(curso, value);
	            } catch (NoSuchFieldException e) {
	                // Log de aviso ou mensagem para campo não encontrado
	                System.out.println("Campo não encontrado: " + key);
	            } catch (IllegalAccessException e) {
	                return;
	            }
	        });
	        
	        cr.save(curso);
	        return ResponseEntity.ok("Curso atualizada com sucesso");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrada");
	    }
	}
	
}
