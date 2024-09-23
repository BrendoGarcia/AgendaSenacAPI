package com.agendasenac.controllers;

import java.lang.reflect.Field;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RestController;

import com.agendasenac.modells.Disciplinas;
import com.agendasenac.modells.UserSistema;
import com.agendasenac.repository.DisciplinasRepository;

@RestController
public class DisciplinasController {
	
	@Autowired
	private DisciplinasRepository dr;
	

	@GetMapping("/disciplinas")
	@CrossOrigin
	public Iterable<Disciplinas> disiplinas() {
		return dr.findAll();
	}
	
	@GetMapping("/disciplinas/{idDisciplina}")
	@CrossOrigin
	public ResponseEntity<Disciplinas> RetornandoUmadiciplina(@PathVariable Long idDisciplina){
		Optional<Disciplinas> disiciplias = Optional.ofNullable(dr.findByidDisciplina(idDisciplina));
		
		if (disiciplias.isPresent()) {
	        return ResponseEntity.ok(disiciplias.get());
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
		
	}
	
	
	
	@PostMapping("/disciplinas")
	@CrossOrigin
	public ResponseEntity<Map<String, String>> cadastroDisciplinas(@RequestBody Disciplinas disciplinas) {
	        
			dr.save(disciplinas);
	        // Cria um Map para retornar o JSON com a chave "text"
	        Map<String, String> response = new HashMap<>();
	        response.put("text", "Disciplina cadastrado com sucesso");
	        return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}
	
	@DeleteMapping("/disciplinas/{idDisciplina}")
	@CrossOrigin
	public ResponseEntity<Map<String, String>> deletedisciplinas(@PathVariable Long idDisciplina) {
	        Map<String, String> response = new HashMap<>();
	        
	        if (dr.existsById(idDisciplina)) {
	            dr.deleteById(idDisciplina);
	            
	            // Prepara o JSON de resposta com a mensagem de sucesso
	            response.put("text", "Disciplina deletada com sucesso");
	            return ResponseEntity.status(HttpStatus.OK).body(response);
	        } else {
	            // Prepara o JSON de resposta para quando a disciplina não é encontrada
	            response.put("text", "Disciplina não encontrada");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	}

	
	
	@PatchMapping("/disciplinas/{idDisciplina}")
	@CrossOrigin
	public ResponseEntity<Map<String, String>> updateUser(@PathVariable Long idDisciplina, @RequestBody Map<String, Object> updates) {
			Map<String, String> response = new HashMap<>();
	        Optional<Disciplinas> optionaldisciplina = Optional.ofNullable(dr.findByidDisciplina(idDisciplina));

	        if (optionaldisciplina.isPresent()) {
	            Disciplinas disciplinasis = optionaldisciplina.get();

	            updates.forEach((key, value) -> {
	                try {
	                    if (value instanceof Map) {
	                        // Se o valor for um Map, então faz o tipo aninhado para tratar o objeto tipo turma
	                        Map<String, Object> nestedObject = (Map<String, Object>) value;
	                        Field field = ReflectionUtils.findRequiredField(UserSistema.class, key);
	                        if (field != null) {
	                            field.setAccessible(true);
	                            Object nestedInstance = field.getType().newInstance();
	                            nestedObject.forEach((nestedKey, nestedValue) -> {
	                                try {
	                                    Field nestedField = ReflectionUtils.findRequiredField(nestedInstance.getClass(), nestedKey);
	                                    if (nestedField != null) {
	                                        nestedField.setAccessible(true);
	                                        // Conversão de Integer para Long se necessário
	                                        if (nestedField.getType().equals(Long.class) && nestedValue instanceof Integer) {
	                                            nestedValue = Long.valueOf((Integer) nestedValue);
	                                        }
	                                        ReflectionUtils.setField(nestedField, nestedInstance, nestedValue);
	                                    }
	                                } catch (Exception e) {
	                                    throw new RuntimeException("Erro ao atualizar o campo aninhado: " + nestedKey, e);
	                                }
	                            });
	                            ReflectionUtils.setField(field, disciplinasis, nestedInstance);
	                        }
	                    } else {
	                        // Atualiza os campos simples
	                        Field field = ReflectionUtils.findRequiredField(UserSistema.class, key);
	                        if (field != null) {
	                            field.setAccessible(true);
	                            // Conversão de Integer para Long se necessário
	                            if (field.getType().equals(Long.class) && value instanceof Integer) {
	                                value = Long.valueOf((Integer) value);
	                            }
	                            ReflectionUtils.setField(field, disciplinasis, value);
	                        } else {
	                            throw new NoSuchFieldException("Campo não encontrado: " + key);
	                        }
	                    }
	                } catch (Exception e) {
	                    throw new RuntimeException("Erro ao atualizar o campo: " + key, e);
	                }
	            });

	            dr.save(disciplinasis);
	            response.put("text", "Disciplina deletada com sucesso");
	            return ResponseEntity.status(HttpStatus.OK).body(response);
	        } else {
	        	response.put("text", "Disciplina não encontrada");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	}
	
	
	
	
	
	
}
