package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gen_areas database table.
 * 
 */
@Entity
@Table(name="gen_areas")
@NamedQuery(name="GenArea.findAll", query="SELECT g FROM GenArea g")
public class GenArea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="are_id")
	private Integer areId;

	@Column(name="are_descripcion")
	private String areDescripcion;

	@Column(name="are_estado",columnDefinition="bpchar")
	private String areEstado;

	@Column(name="are_latitud")
	private String areLatitud;

	@Column(name="are_longitud")
	private String areLongitud;

	@Column(name="are_nombre")
	private String areNombre;

	@Column(name="are_padre")
	private String arePadre;

	//bi-directional many-to-one association to GenSectore
	@ManyToOne
	@JoinColumn(name="sec_id")
	private GenSectore genSectore;

	//bi-directional many-to-one association to GenSitio
	@OneToMany(mappedBy="genArea")
	private List<GenSitio> genSitios;

	public GenArea() {
	}

	public Integer getAreId() {
		return this.areId;
	}

	public void setAreId(Integer areId) {
		this.areId = areId;
	}

	public String getAreDescripcion() {
		return this.areDescripcion;
	}

	public void setAreDescripcion(String areDescripcion) {
		this.areDescripcion = areDescripcion;
	}

	public String getAreEstado() {
		return this.areEstado;
	}

	public void setAreEstado(String areEstado) {
		this.areEstado = areEstado;
	}

	public String getAreLatitud() {
		return this.areLatitud;
	}

	public void setAreLatitud(String areLatitud) {
		this.areLatitud = areLatitud;
	}

	public String getAreLongitud() {
		return this.areLongitud;
	}

	public void setAreLongitud(String areLongitud) {
		this.areLongitud = areLongitud;
	}

	public String getAreNombre() {
		return this.areNombre;
	}

	public void setAreNombre(String areNombre) {
		this.areNombre = areNombre;
	}

	public String getArePadre() {
		return this.arePadre;
	}

	public void setArePadre(String arePadre) {
		this.arePadre = arePadre;
	}

	public GenSectore getGenSectore() {
		return this.genSectore;
	}

	public void setGenSectore(GenSectore genSectore) {
		this.genSectore = genSectore;
	}

	public List<GenSitio> getGenSitios() {
		return this.genSitios;
	}

	public void setGenSitios(List<GenSitio> genSitios) {
		this.genSitios = genSitios;
	}

	public GenSitio addGenSitio(GenSitio genSitio) {
		getGenSitios().add(genSitio);
		genSitio.setGenArea(this);

		return genSitio;
	}

	public GenSitio removeGenSitio(GenSitio genSitio) {
		getGenSitios().remove(genSitio);
		genSitio.setGenArea(null);

		return genSitio;
	}

}