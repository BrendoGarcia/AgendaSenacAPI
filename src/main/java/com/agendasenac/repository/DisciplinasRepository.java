package com.agendasenac.repository;

import org.springframework.data.repository.CrudRepository;

import com.agendasenac.modells.Disciplinas;

public interface DisciplinasRepository extends CrudRepository<Disciplinas, Long>{
			Disciplinas findByidDisciplina(Long idDisciplina);
}
