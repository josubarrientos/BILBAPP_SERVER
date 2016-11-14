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
 * The persistent class for the Sitio database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Sitio.findAll", query="SELECT s FROM Sitio s"),
	@NamedQuery(name="Sitio.findAllBySitio", query="SELECT s FROM Sitio s WHERE s.sitio= :sitio"),
	@NamedQuery(name="Sitio.findAllByOpcion", query="SELECT s FROM Sitio s WHERE s.opcion.opcion= :opcion")
})

public class Sitio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String sitio;

	private int contador;

	private String direccion;

	private float puntuacion;

	//bi-directional many-to-one association to Critica
	@OneToMany(mappedBy="sitio")
	private List<Critica> criticas;

	//bi-directional many-to-one association to Opcion
	@ManyToOne
	@JoinColumn(name="Opcion_opcion")
	private Opcion opcion;

	public Sitio() {
	}

	public String getSitio() {
		return this.sitio;
	}

	public void setSitio(String sitio) {
		this.sitio = sitio;
	}

	public int getContador() {
		return this.contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public float getPuntuacion() {
		return this.puntuacion;
	}

	public void setPuntuacion(float puntuacion) {
		this.puntuacion = puntuacion;
	}

	public List<Critica> getCriticas() {
		return this.criticas;
	}

	public void setCriticas(List<Critica> criticas) {
		this.criticas = criticas;
	}

	public Critica addCritica(Critica critica) {
		getCriticas().add(critica);
		critica.setSitio(this);

		return critica;
	}

	public Critica removeCritica(Critica critica) {
		getCriticas().remove(critica);
		critica.setSitio(null);

		return critica;
	}

	public Opcion getOpcion() {
		return this.opcion;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}

}