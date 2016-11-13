package business.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OpcionJSON {

	@XmlElement	
	private String opcion;

	public OpcionJSON() {
	}
	
	public OpcionJSON(String opcion) {
		this.opcion=opcion;
	}	
		
	public String getOpcion() {
		return opcion;
	}

}
