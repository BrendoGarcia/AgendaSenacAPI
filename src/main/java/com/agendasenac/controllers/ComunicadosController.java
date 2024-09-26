package com.agendasenac.controllers;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agendasenac.modells.ComunicaDoUser;
import com.agendasenac.modells.Turma;
import com.agendasenac.repository.ComunicadosRepository;

@RestController
@RequestMapping
public class ComunicadosController {

	@Autowired
	private ComunicadosRepository cdr;


	@GetMapping("/comunicados")
	@CrossOrigin
	public Iterable<ComunicaDoUser> RetornoComunicados() {
		return cdr.findAll();
	}

	@GetMapping("/{idComunicado}")
	@CrossOrigin
	public ResponseEntity<ComunicaDoUser> ReceberComunicadoById(@PathVariable Long idComunicado) {
		Optional<ComunicaDoUser> OpsComunicado = Optional.ofNullable(cdr.findByIdComunicado(idComunicado));

		if (OpsComunicado.isPresent()) {
			return ResponseEntity.ok(OpsComunicado.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping("/comunicados")
	@CrossOrigin
	public ResponseEntity<String> CriandoComunicado(@RequestBody ComunicaDoUser comunicadouser) {
		cdr.save(comunicadouser);
		return ResponseEntity.status(HttpStatus.CREATED).body("Comunicado criado com sucesso");
	}

	@DeleteMapping("/comunicados/{idComunicado}")
	@CrossOrigin
	public ResponseEntity<String> DeleteComunicado(@PathVariable Long idComunicado) {
		if (cdr.existsById(idComunicado)) {
			cdr.deleteById(idComunicado);
			return ResponseEntity.status(HttpStatus.OK).body("Comunicado deletado com sucesso");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comunicado não encontrado");
		}
	}
	
	
	@PatchMapping("/comunicados/{idComunicado}")
	@CrossOrigin
	public ResponseEntity<String> AtualizarComunidados(@PathVariable Long idComunicado, @RequestBody Map<String, Object> updates) {
	    Optional<ComunicaDoUser> optionalTurma = Optional.ofNullable(cdr.findByIdComunicado(idComunicado));
	    
	    if (optionalTurma.isPresent()) {
	    	ComunicaDoUser comunicador = optionalTurma.get();
	        
	        updates.forEach((key, value) -> {
	            try {
	                Field field = Turma.class.getDeclaredField(key);
	                field.setAccessible(true);
	                field.set(comunicador, value);
	            } catch (NoSuchFieldException e) {
	                // Log de aviso ou mensagem para campo não encontrado
	                System.out.println("Campo não encontrado: " + key);
	            } catch (IllegalAccessException e) {
	                return;
	            }
	        });
	        
	        cdr.save(comunicador);
	        return ResponseEntity.ok("Turma atualizada com sucesso");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
	    }
	}

	

}
