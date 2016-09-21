package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gen_elementos database table.
 * 
 */
@Entity
@Table(name="gen_elementos")
@NamedQuery(name="GenElemento.findAll", query="SELECT g FROM GenElemento g")
public class GenElemento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ele_id")
	private Integer eleId;

	@Column(name="ele_estado", columnDefinition = "bpchar", length=1)
	private String eleEstado;

	@Column(name="ele_nombre", length = 250)
	private String eleNombre;

	@Column(name="ele_tipo", length = 50)
	private String eleTipo;

	@Column(name="ele_unidad_medida", length = 50)
	private String eleUnidadMedida;

	//bi-directional many-to-one association to GenUbicacionelemento
	@OneToMany(mappedBy="genElemento")
	private List<GenUbicacionelemento> genUbicacionelementos;

	public GenElemento() {
	}

	public Integer getEleId() {
		return this.eleId;
	}

	public void setEleId(Integer eleId) {
		this.eleId = eleId;
	}

	public String getEleEstado() {
		return this.eleEstado;
	}

	public void setEleEstado(String eleEstado) {
		this.eleEstado = eleEstado;
	}

	public String getEleNombre() {
		return this.eleNombre;
	}

	public void setEleNombre(String eleNombre) {
		this.eleNombre = eleNombre;
	}

	public String getEleTipo() {
		return this.eleTipo;
	}

	public void setEleTipo(String eleTipo) {
		this.eleTipo = eleTipo;
	}

	public String getEleUnidadMedida() {
		return this.eleUnidadMedida;
	}

	public void setEleUnidadMedida(String eleUnidadMedida) {
		this.eleUnidadMedida = eleUnidadMedida;
	}

	public List<GenUbicacionelemento> getGenUbicacionelementos() {
		return this.genUbicacionelementos;
	}

	public void setGenUbicacionelementos(List<GenUbicacionelemento> genUbicacionelementos) {
		this.genUbicacionelementos = genUbicacionelementos;
	}

	public GenUbicacionelemento addGenUbicacionelemento(GenUbicacionelemento genUbicacionelemento) {
		getGenUbicacionelementos().add(genUbicacionelemento);
		genUbicacionelemento.setGenElemento(this);

		return genUbicacionelemento;
	}

	public GenUbicacionelemento removeGenUbicacionelemento(GenUbicacionelemento genUbicacionelemento) {
		getGenUbicacionelementos().remove(genUbicacionelemento);
		genUbicacionelemento.setGenElemento(null);

		return genUbicacionelemento;
	}

}