package business.json;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CriticasJSON {
	@XmlElement(name="critica")
	private List<CriticaJSON> criticas;
	
	public CriticasJSON() {
		criticas=new ArrayList<CriticaJSON>();
	}	
	
	public List<CriticaJSON> getCriticas() {
		return criticas;
	}
	
	public void setCriticas(List<CriticaJSON> criticas) {
		this.criticas=criticas;
	}
}

