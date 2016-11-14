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

package business.json;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class CriticaJSON {
	
	@XmlElement
	private String critica;
	@XmlElement
	private Date fecha;
	@XmlElement
	private String usuario;
	@XmlElement
	private String sitio;
	
	public CriticaJSON() {
	}
	
	public CriticaJSON(String critica, Date fecha, String usuario,String sitio) {
		this.critica=critica;
		this.fecha=fecha;
		this.usuario=usuario;
		this.sitio=sitio;
	}	
		
	public String getCritica() {
		return critica;
	}
	public void setCritica(String critica) {
		this.critica = critica;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSitio() {
		return sitio;
	}
	public void setSitio(String sitio) {
		this.sitio = sitio;
	}
	
	

}

