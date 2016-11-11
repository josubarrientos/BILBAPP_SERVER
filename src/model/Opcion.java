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