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
	@NamedQuery(name="Sitio.findAllByOpcion", query="SELECT s FROM Sitio s WHERE s.opcion.opcion= :opcion")
})

public class Sitio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String sitio;

	private String direccion;

	private int puntuacion;

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

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getPuntuacion() {
		return this.puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
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