package com.agendasenac.modells;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Turma")
public class Turma{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "idturma")
	private Long idturma;
	
	@Column(name = "NomeTurma")
	private String NomeTurma;
	
	@Column(name = "DatalhesTurma")
	private String DatalhesTurma;
	
	@Column(name = "Turno")
	private String Turno;
	
	@ManyToOne(optional = true)
	private Curso curso;
	
	@ManyToMany
	private List<Disciplinas> disciplinas;
	

	public List<Disciplinas> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplinas> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public String getTurno() {
		return Turno;
	}
	public void setTurno(String turno) {
		Turno = turno;
	}
	public void setIdturma(Long idturma) {
		this.idturma = idturma;
	}
	public Long getIdturma() {
		return idturma;
	}
	public String getNomeTurma() {
		return NomeTurma;
	}
	public void setNomeTurma(String nomeTurma) {
		NomeTurma = nomeTurma;
	}
	public String getDatalhesTurma() {
		return DatalhesTurma;
	}
	public void setDatalhesTurma(String datalhesTurma) {
		DatalhesTurma = datalhesTurma;
	}
	

}
