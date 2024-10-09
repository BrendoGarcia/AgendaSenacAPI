package com.agendasenac.modells;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "Disciplinas")
public class Disciplinas implements Serializable{
	private static final long serialVesionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "idDisciplina")
	private Long idDisciplina;
	
	@Column(name = "NomeDaDisciplina")
	private String NomeDaDisciplina;
	
	@Column(name = "DetalhesAdicionais")
	private String DetalhesAdicionais;

	@Column(name = "CargaHoraria")
	private String cargaHoraria;
	
	

	public UserSistema getProfessor() {
		return professor;
	}

	public void setProfessor(UserSistema professor) {
		this.professor = professor;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	@ManyToOne(optional = true)
	private UserSistema professor;
	
	@ManyToOne(optional = true)
	private Turma turma;
	

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getCargaHoraria(){
		return cargaHoraria;
	}

	public void setCargaHoraia(String cargaHoraria){
		this.cargaHoraria = cargaHoraria;
	}
	
	public long getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	public String getNomeDaDisciplina() {
		return NomeDaDisciplina;
	}
	public void setNomeDaDisciplina(String nomeDaDisciplina) {
		NomeDaDisciplina = nomeDaDisciplina;
	}
	public String getDetalhesAdicionais() {
		return DetalhesAdicionais;
	}
	public void setDetalhesAdicionais(String detalhesAdicionais) {
		DetalhesAdicionais = detalhesAdicionais;
	}

}
