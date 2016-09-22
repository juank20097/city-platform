package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gen_elementos_zona database table.
 * 
 */
@Entity
@Table(name="gen_elementos_zona")
@NamedQuery(name="GenElementosZona.findAll", query="SELECT g FROM GenElementosZona g")
public class GenElementosZona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="elz_id")
	private Integer elzId;

	@Column(name="elz_estado", columnDefinition = "bpchar", length=1)
	private String elzEstado;

	@Column(name="elz_nombre", length = 250)
	private String elzNombre;

	@Column(name="elz_tipo", length = 50)
	private String elzTipo;

	@Column(name="elz_unidad_medida", length = 50)
	private String elzUnidadMedida;

	//bi-directional many-to-one association to GenElementoZonaValor
	@OneToMany(mappedBy="genElementosZona")
	private List<GenElementoZonaValor> genElementoZonaValors;

	public GenElementosZona() {
	}

	public Integer getElzId() {
		return this.elzId;
	}

	public void setElzId(Integer elzId) {
		this.elzId = elzId;
	}

	public String getElzEstado() {
		return this.elzEstado;
	}

	public void setElzEstado(String elzEstado) {
		this.elzEstado = elzEstado;
	}

	public String getElzNombre() {
		return this.elzNombre;
	}

	public void setElzNombre(String elzNombre) {
		this.elzNombre = elzNombre;
	}

	public String getElzTipo() {
		return this.elzTipo;
	}

	public void setElzTipo(String elzTipo) {
		this.elzTipo = elzTipo;
	}

	public String getElzUnidadMedida() {
		return this.elzUnidadMedida;
	}

	public void setElzUnidadMedida(String elzUnidadMedida) {
		this.elzUnidadMedida = elzUnidadMedida;
	}

	public List<GenElementoZonaValor> getGenElementoZonaValors() {
		return this.genElementoZonaValors;
	}

	public void setGenElementoZonaValors(List<GenElementoZonaValor> genElementoZonaValors) {
		this.genElementoZonaValors = genElementoZonaValors;
	}

	public GenElementoZonaValor addGenElementoZonaValor(GenElementoZonaValor genElementoZonaValor) {
		getGenElementoZonaValors().add(genElementoZonaValor);
		genElementoZonaValor.setGenElementosZona(this);

		return genElementoZonaValor;
	}

	public GenElementoZonaValor removeGenElementoZonaValor(GenElementoZonaValor genElementoZonaValor) {
		getGenElementoZonaValors().remove(genElementoZonaValor);
		genElementoZonaValor.setGenElementosZona(null);

		return genElementoZonaValor;
	}

}