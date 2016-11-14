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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SitiosJSON {
	@XmlElement(name="sitio")
	private List<SitioJSON> sitios;
	
	public SitiosJSON() {
		sitios=new ArrayList<SitioJSON>();
	}	
	
	public List<SitioJSON> getSitios() {
		return sitios;
	}
	
	public void setSitios(List<SitioJSON> sitios) {
		this.sitios=sitios;
	}
}

