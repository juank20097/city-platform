package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gen_tipo_sitios database table.
 * 
 */
@Entity
@Table(name="gen_tipo_sitios")
@NamedQuery(name="GenTipoSitio.findAll", query="SELECT g FROM GenTipoSitio g")
public class GenTipoSitio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="tsi_id")
	private Integer tsiId;

	@Column(name="tsi_descripcion")
	private String tsiDescripcion;

	@Column(name="tsi_estado")
	private String tsiEstado;

	@Column(name="tsi_nombre")
	private String tsiNombre;

	//bi-directional many-to-one association to GenSitio
	@OneToMany(mappedBy="genTipoSitio")
	private List<GenSitio> genSitios;

	public GenTipoSitio() {
	}

	public Integer getTsiId() {
		return this.tsiId;
	}

	public void setTsiId(Integer tsiId) {
		this.tsiId = tsiId;
	}

	public String getTsiDescripcion() {
		return this.tsiDescripcion;
	}

	public void setTsiDescripcion(String tsiDescripcion) {
		this.tsiDescripcion = tsiDescripcion;
	}

	public String getTsiEstado() {
		return this.tsiEstado;
	}

	public void setTsiEstado(String tsiEstado) {
		this.tsiEstado = tsiEstado;
	}

	public String getTsiNombre() {
		return this.tsiNombre;
	}

	public void setTsiNombre(String tsiNombre) {
		this.tsiNombre = tsiNombre;
	}

	public List<GenSitio> getGenSitios() {
		return this.genSitios;
	}

	public void setGenSitios(List<GenSitio> genSitios) {
		this.genSitios = genSitios;
	}

	public GenSitio addGenSitio(GenSitio genSitio) {
		getGenSitios().add(genSitio);
		genSitio.setGenTipoSitio(this);

		return genSitio;
	}

	public GenSitio removeGenSitio(GenSitio genSitio) {
		getGenSitios().remove(genSitio);
		genSitio.setGenTipoSitio(null);

		return genSitio;
	}

}