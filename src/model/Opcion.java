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
import java.util.List;


/**
 * The persistent class for the Opcion database table.
 * 
 */
@Entity
@NamedQuery(name="Opcion.findAll", query="SELECT o FROM Opcion o")
public class Opcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String opcion;

	//bi-directional many-to-one association to Sitio
	@OneToMany(mappedBy="opcion")
	private List<Sitio> sitios;

	public Opcion() {
	}

	public String getOpcion() {
		return this.opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public List<Sitio> getSitios() {
		return this.sitios;
	}

	public void setSitios(List<Sitio> sitios) {
		this.sitios = sitios;
	}

	public Sitio addSitio(Sitio sitio) {
		getSitios().add(sitio);
		sitio.setOpcion(this);

		return sitio;
	}

	public Sitio removeSitio(Sitio sitio) {
		getSitios().remove(sitio);
		sitio.setOpcion(null);

		return sitio;
	}

}