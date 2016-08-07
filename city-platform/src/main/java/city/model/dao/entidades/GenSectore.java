package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gen_sectores database table.
 * 
 */
@Entity
@Table(name="gen_sectores")
@NamedQuery(name="GenSectore.findAll", query="SELECT g FROM GenSectore g")
public class GenSectore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sec_id")
	private Integer secId;

	@Column(name="sec_descripcion")
	private String secDescripcion;

	@Column(name="sec_direccion_referencial")
	private String secDireccionReferencial;

	@Column(name="sec_estado",columnDefinition="bpchar")
	private String secEstado;

	@Column(name="sec_nombre")
	private String secNombre;

	//bi-directional many-to-one association to GenArea
	@OneToMany(mappedBy="genSectore")
	private List<GenArea> genAreas;

	public GenSectore() {
	}

	public Integer getSecId() {
		return this.secId;
	}

	public void setSecId(Integer secId) {
		this.secId = secId;
	}

	public String getSecDescripcion() {
		return this.secDescripcion;
	}

	public void setSecDescripcion(String secDescripcion) {
		this.secDescripcion = secDescripcion;
	}

	public String getSecDireccionReferencial() {
		return this.secDireccionReferencial;
	}

	public void setSecDireccionReferencial(String secDireccionReferencial) {
		this.secDireccionReferencial = secDireccionReferencial;
	}

	public String getSecEstado() {
		return this.secEstado;
	}

	public void setSecEstado(String secEstado) {
		this.secEstado = secEstado;
	}

	public String getSecNombre() {
		return this.secNombre;
	}

	public void setSecNombre(String secNombre) {
		this.secNombre = secNombre;
	}

	public List<GenArea> getGenAreas() {
		return this.genAreas;
	}

	public void setGenAreas(List<GenArea> genAreas) {
		this.genAreas = genAreas;
	}

	public GenArea addGenArea(GenArea genArea) {
		getGenAreas().add(genArea);
		genArea.setGenSectore(this);

		return genArea;
	}

	public GenArea removeGenArea(GenArea genArea) {
		getGenAreas().remove(genArea);
		genArea.setGenSectore(null);

		return genArea;
	}

}