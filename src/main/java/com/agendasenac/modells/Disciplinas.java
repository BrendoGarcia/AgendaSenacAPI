package com.agendasenac.modells;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
	
	

	@ManyToOne(optional = true)
	private UserSistema professor;
	

	@ManyToMany
	private Curso curso;
	
	
	public String getnomeprofessor() {
        return professor != null ? professor.getNomeCompletoUser() : "SEM NOME"; ///pq tu não quer irrr
    }

    public String getcontatoprofessor() {
        return professor != null ? professor.getContatopessoal() : "SEM CONTATO";
    }
    
    public Long getprovessorid() {
    	return professor != null ? professor.getCodigo() : null;
    }
	
  

	public void setTurma(Curso curso) {
		this.curso = curso;
	}
	
	public void setProfessor(UserSistema professor) {
		this.professor = professor;
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

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
