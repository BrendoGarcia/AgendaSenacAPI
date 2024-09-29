package com.agendasenac.modells;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Turma")
public class Turma{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "idturma")
	private Long idturma;
	
	@Column(name = "nomeTurma")
	private String nomeTurma;
	
	@Column(name = "datalhesTurma")
	private String datalhesTurma;
	
	@Column(name = "periodo")
	private String periodo;
	
	@Column(name = "Ano")
	private String anno;
	
	@Column(name = "turno")
	private String turno;
	
	@ManyToOne(optional = true)
	private Curso curso;
	
	@JsonIgnore
	@OneToOne(optional = true)
	private UserSistema repesentante;  //Representant
	

	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public UserSistema getRepesentante() {
		return repesentante;
	}
	public void setRepesentante(UserSistema repesentante) {
		this.repesentante = repesentante;
	}

	
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public void setIdturma(Long idturma) {
		this.idturma = idturma;
	}
	public Long getIdturma() {
		return idturma;
	}
	public String getNomeTurma() {
		return nomeTurma;
	}
	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}
	public String getDatalhesTurma() {
		return datalhesTurma;
	}
	public void setDatalhesTurma(String datalhesTurma) {
		this.datalhesTurma = datalhesTurma;
	}
	public String getAnno() {
		return anno;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}

	

}
