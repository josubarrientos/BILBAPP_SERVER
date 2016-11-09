package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Critica database table.
 * 
 */
@Entity
@NamedQuery(name="Critica.findAll", query="SELECT c FROM Critica c")
public class Critica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCritica;

	private String critica;

	private String usuario;

	//bi-directional many-to-one association to Sitio
	@ManyToOne
	@JoinColumn(name="Sitio_sitio")
	private Sitio sitio;

	public Critica() {
	}

	public int getIdCritica() {
		return this.idCritica;
	}

	public void setIdCritica(int idCritica) {
		this.idCritica = idCritica;
	}

	public String getCritica() {
		return this.critica;
	}

	public void setCritica(String critica) {
		this.critica = critica;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Sitio getSitio() {
		return this.sitio;
	}

	public void setSitio(Sitio sitio) {
		this.sitio = sitio;
	}

}