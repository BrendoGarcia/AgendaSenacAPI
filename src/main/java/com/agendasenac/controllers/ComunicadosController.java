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

	@DeleteMapping("/{idComunicado}")
	@CrossOrigin
	public ResponseEntity<String> DeleteComunicado(@PathVariable Long idComunicado) {
		if (cdr.existsById(idComunicado)) {
			cdr.deleteById(idComunicado);
			return ResponseEntity.status(HttpStatus.OK).body("Comunicado deletado com sucesso");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comunicado não encontrado");
		}
	}
	
	@PatchMapping("/{idComunicado}")
	@CrossOrigin
	public ResponseEntity<String> ModificarComunicado(@PathVariable Long idComunicado, @RequestBody Map<String, Object> atualizar) {
		Optional<ComunicaDoUser> opscomunicador = Optional.ofNullable(cdr.findByIdComunicado(idComunicado));
		
		if (opscomunicador.isPresent()) {
	        ComunicaDoUser comunicador = opscomunicador.get();
	        
	        atualizar.forEach((key, value) -> {
	            Field field = ReflectionUtils.findRequiredField(ComunicaDoUser.class, key);
	            field.setAccessible(true);
	            ReflectionUtils.setField(field, comunicador, value);
	        });
	        
	        cdr.save(comunicador);
	        return ResponseEntity.status(HttpStatus.OK).body("Comunicado atualizado com sucesso");
	    }else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comunicado não encontrado");
	    }
	}
	
	

}
