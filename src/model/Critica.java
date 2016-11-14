/*
 * 
 * Copyright (C) 2016 Josu Barrientos Bahamonde
 * 
 * 
 * BILBAPP_SERVER is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * BILBAPP is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details
 * <http://www.gnu.org/licenses/>.
 */

package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Critica database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Critica.findAll", query="SELECT c FROM Critica c"),
	@NamedQuery(name="Critica.findAllBySitio", query="SELECT c FROM Critica c  WHERE c.sitio.sitio= :sitio")
})


public class Critica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCritica;

	private String critica;

	@Temporal(TemporalType.DATE)
	private Date fecha;

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

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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