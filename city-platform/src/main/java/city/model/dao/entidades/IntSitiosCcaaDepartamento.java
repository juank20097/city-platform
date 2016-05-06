package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the int_sitios_ccaa_departamentos database table.
 * 
 */
@Entity
@Table(name="int_sitios_ccaa_departamentos")
@NamedQuery(name="IntSitiosCcaaDepartamento.findAll", query="SELECT i FROM IntSitiosCcaaDepartamento i")
public class IntSitiosCcaaDepartamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IntSitiosCcaaDepartamentoPK id;

	@Column(name="cca_departamento")
	private String ccaDepartamento;

	@Column(name="cca_estado")
	private String ccaEstado;

	@Column(name="cca_exito")
	private String ccaExito;

	@Column(name="cca_observacion")
	private String ccaObservacion;

	@Column(name="sit_nombre")
	private String sitNombre;

	//bi-directional many-to-one association to GenSitio
	@ManyToOne
	@JoinColumn(name="sit_id")
	private GenSitio genSitio;

	public IntSitiosCcaaDepartamento() {
	}

	public IntSitiosCcaaDepartamentoPK getId() {
		return this.id;
	}

	public void setId(IntSitiosCcaaDepartamentoPK id) {
		this.id = id;
	}

	public String getCcaDepartamento() {
		return this.ccaDepartamento;
	}

	public void setCcaDepartamento(String ccaDepartamento) {
		this.ccaDepartamento = ccaDepartamento;
	}

	public String getCcaEstado() {
		return this.ccaEstado;
	}

	public void setCcaEstado(String ccaEstado) {
		this.ccaEstado = ccaEstado;
	}

	public String getCcaExito() {
		return this.ccaExito;
	}

	public void setCcaExito(String ccaExito) {
		this.ccaExito = ccaExito;
	}

	public String getCcaObservacion() {
		return this.ccaObservacion;
	}

	public void setCcaObservacion(String ccaObservacion) {
		this.ccaObservacion = ccaObservacion;
	}

	public String getSitNombre() {
		return this.sitNombre;
	}

	public void setSitNombre(String sitNombre) {
		this.sitNombre = sitNombre;
	}

	public GenSitio getGenSitio() {
		return this.genSitio;
	}

	public void setGenSitio(GenSitio genSitio) {
		this.genSitio = genSitio;
	}

}