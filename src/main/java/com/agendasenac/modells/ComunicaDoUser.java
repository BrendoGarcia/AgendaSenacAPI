package com.agendasenac.modells;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "ComunicaDoUser")
public class ComunicaDoUser implements Serializable {
	
	private static final long serialVesionUID = 1L;
		

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column (name = "IdComunicado")
	private Long idComunicado;
	
	@Column (name = "DataPublicacao")
	private String DataPulicacao;
	
	@Column (name = "ConteudoComunicado")
	private String ConteudoComunicado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserAI")
	private UserSistema usersistema;

	
	
	public UserSistema getUsersistema() {
		return usersistema;
	}

	public void setUsersistema(UserSistema usersistema) {
		this.usersistema = usersistema;
	}

	public Long getIdComunicado() {
		return idComunicado;
	}

	public void setIdComunicado(Long idComunicado) {
		this.idComunicado = idComunicado;
	}

	public String getDataPulicacao() {
		return DataPulicacao;
	}

	public void setDataPulicacao(String dataPulicacao) {
		DataPulicacao = dataPulicacao;
	}

	public String getConteudoComunicado() {
		return ConteudoComunicado;
	}

	public void setConteudoComunicado(String conteudoComunicado) {
		ConteudoComunicado = conteudoComunicado;
	}
	
	
}


