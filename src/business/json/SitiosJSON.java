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

