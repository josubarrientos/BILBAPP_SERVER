package business.json;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OpcionesJSON {
	@XmlElement(name="opcion")
	private List<OpcionJSON> opciones;
	
	public OpcionesJSON() {
		opciones=new ArrayList<OpcionJSON>();
	}	
	
	public List<OpcionJSON> getOpciones() {
		return opciones;
	}
	
	public void setOpciones(List<OpcionJSON> opciones) {
		this.opciones=opciones;
	}
}

