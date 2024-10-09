package com.agendasenac.modells;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AvaliandoALuno implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long idavalicacion;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idaluno")
	private UserSistema aluno;
	
	@ManyToOne
	@JoinColumn(name = "idprofessor")
	private UserSistema Professor;
	
	@ManyToOne
	@JoinColumn(name = "idconceito")
	private Conceitos conceito;
	
	@ManyToOne
	@JoinColumn(name = "iddisciplina")
	private Disciplinas disciplina;

	public Long getIdavalicacion() {
		return idavalicacion;
	}

	public void setIdavalicacion(Long idavalicacion) {
		this.idavalicacion = idavalicacion;
	}

	///
	
	public String getnomeAluno() {
        return aluno != null ? aluno.getNomeCompletoUser() : "SEM NOME"; ///pq tu não quer irrr
    }
	
	public Long getidALuno() {
        return aluno != null ? aluno.getCodigo() : null; ///pq tu não quer irrr
    }
	
	
	public Long getidprofessor() {
        return Professor != null ? Professor.getCodigo() : null; ///pq tu não quer irrr
    }
	
	public String getnomer() {
        return Professor != null ? Professor.getNomeCompletoUser() : null; ///pq tu não quer irrr
    }
	

	//
	public void setAluno(UserSistema aluno) {
		this.aluno = aluno;
	}


	public void setProfessor(UserSistema professor) {
		Professor = professor;
	}

	public Conceitos getConceito() {
		return conceito;
	}

	public void setConceito(Conceitos conceito) {
		this.conceito = conceito;
	}

	public Disciplinas getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplinas disciplina) {
		this.disciplina = disciplina;
	}
	

}
