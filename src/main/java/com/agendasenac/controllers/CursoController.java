package com.agendasenac.controllers;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agendasenac.modells.Curso;
import com.agendasenac.repository.CursoRepository;

@RestController
@RequestMapping("/cursos")
@CrossOrigin
public class CursoController {

    @Autowired
    private CursoRepository cr;

    // Método para criar uma resposta padronizada
    private ResponseEntity<Map<String, Object>> createResponse(HttpStatus status, String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status.value());
        response.put("message", message);
        response.put("data", data);
        return new ResponseEntity<>(response, status);
    }

    // Listar todos os cursos
    @GetMapping
    public ResponseEntity<Map<String, Object>> listarCursos() {
        Iterable<Curso> cursos = cr.findAll();
        if (!cursos.iterator().hasNext()) {
            return createResponse(HttpStatus.NOT_FOUND, "Nenhum curso encontrado", null);
        }
        return createResponse(HttpStatus.OK, "Cursos encontrados", cursos);
    }

    // Cadastrar novo curso
    @PostMapping
    public ResponseEntity<Map<String, Object>> cadastroCurso(@RequestBody Curso curso) {
        Map<String, String> errors = validarCurso(curso);
        if (!errors.isEmpty()) {
            return createResponse(HttpStatus.BAD_REQUEST, "Erro na validação dos dados", errors);
        }
        try {
            cr.save(curso);
            return createResponse(HttpStatus.CREATED, "Curso cadastrado com sucesso", curso);
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar o curso", e.getMessage());
        }
    }

    // Atualizar curso
    @PutMapping("/{idCurso}")
    public ResponseEntity<Map<String, Object>> atualizarCurso(@PathVariable Long idCurso, @RequestBody Map<String, Object> updates) {
        Optional<Curso> optionalCurso = Optional.ofNullable(cr.findByidCurso(idCurso));
        if (!optionalCurso.isPresent()) {
            return createResponse(HttpStatus.NOT_FOUND, "Curso não encontrado", null);
        }

        Curso curso = optionalCurso.get();
        Map<String, String> errors = new HashMap<>();

        updates.forEach((key, value) -> {
            try {
                Field field = Curso.class.getDeclaredField(key);
                field.setAccessible(true);

                if ("nomeCurso".equals(key)) {
                    if (value == null || ((String) value).trim().isEmpty()) {
                        errors.put(key, "O nome do curso é obrigatório e não pode estar vazio.");
                    }
                }

                if ("detalhesCurso".equals(key)) {
                    if (value != null && ((String) value).length() < 5) {
                        errors.put(key, "Os detalhes do curso devem ter no mínimo 5 caracteres.");
                    }
                }

                field.set(curso, value);
            } catch (NoSuchFieldException e) {
                errors.put(key, "Campo não encontrado: " + key);
            } catch (IllegalAccessException e) {
                errors.put(key, "Erro ao acessar o campo: " + key);
            } catch (ClassCastException e) {
                errors.put(key, "Tipo de dado inválido para o campo: " + key);
            }
        });

        if (!errors.isEmpty()) {
            return createResponse(HttpStatus.BAD_REQUEST, "Erro na validação dos campos", errors);
        }

        try {
            cr.save(curso);
            return createResponse(HttpStatus.OK, "Curso atualizado com sucesso", curso);
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar o curso", e.getMessage());
        }
    }

    // Método de validação do curso
    private Map<String, String> validarCurso(Curso curso) {
        Map<String, String> errors = new HashMap<>();

        if (curso.getNomeCurso() == null || curso.getNomeCurso().trim().isEmpty()) {
            errors.put("NomeDoCurso", "O nome do curso é obrigatório e não pode estar vazio.");
        }

        if (curso.getDetalhesCurso() != null && curso.getDetalhesCurso().length() < 5) {
            errors.put("DetalhesCurso", "Os detalhes do curso devem ter no mínimo 5 caracteres.");
        }

        return errors;
    }
}
