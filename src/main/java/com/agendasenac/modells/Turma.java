package com.agendasenac.modells;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
	
	@Column(name = "NomeTurma")
	private String NomeTurma;
	
	@Column(name = "DatalhesTurma")
	private String DatalhesTurma;
	
	@Column(name = "Perido")
	private String Perido;
	
	@Column(name = "Ano")
	private String Ano;
	
	@Column(name = "Turno")
	private String Turno;
	
	@ManyToOne(optional = true)
	private Curso curso;
	
	@ManyToMany
	private List<Disciplinas> disciplinas;
	
	@OneToOne(optional = true)
	private UserSistema repesentante;  //Representant
	
	public String getPerido() {
		return Perido;
	}
	public void setPerido(String perido) {
		Perido = perido;
	}
	public String getAno() {
		return Ano;
	}
	public void setAno(String ano) {
		Ano = ano;
	}
	public UserSistema getUsersistema() {
		return repesentante;
	}
	public void setUsersistema(UserSistema usersistema) {
		this.repesentante = usersistema;
	}
	

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
