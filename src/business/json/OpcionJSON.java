package business.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OpcionJSON {
	@XmlElement	
	private int opcionCode;
	@XmlElement	
	private String opcion;

	public OpcionJSON() {
	}
	
	public OpcionJSON(int opcionCode, String opcion) {
		this.opcionCode=opcionCode;
		this.opcion=opcion;
	}	

	public int getOpcionCode() {
		return opcionCode;
	}
		
	public String getOpcion() {
		return opcion;
	}
	
	/*public void setLessonCode(String opcionCode) {
		this.opcionCode=opcionCode;
	}
	
	public void setTitle(String opcion) {
		this.opcion=opcion;
	}*/
}
