package business.json;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class SitioJSON {
	
	@XmlElement
	private String sitio;
	@XmlElement
	private String direccion;
	@XmlElement
	private int puntuacion;
	
	public SitioJSON() {
	}
	
	public SitioJSON(String sitio, String direccion, int puntuacion) {
		this.sitio=sitio;
		this.direccion=direccion;
		this.puntuacion=puntuacion;
	}	
		
	public String getSitio() {
		return sitio;
	}
	public void setSitio(String sitio) {
		this.sitio = sitio;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	

}

