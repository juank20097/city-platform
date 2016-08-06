package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the gen_distritos database table.
 * 
 */
@Entity
@Table(name="gen_distritos")
@NamedQuery(name="GenDistrito.findAll", query="SELECT g FROM GenDistrito g")
public class GenDistrito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dis_id")
	private String disId;

	@Column(name="dis_descripcion")
	private String disDescripcion;

	@Column(name="dis_estado", columnDefinition = "bpchar")
	private String disEstado;

	@Column(name="dis_hectareas")
	private BigDecimal disHectareas;

	@Column(name="dis_link_mapa")
	private String disLinkMapa;

	@Column(name="dis_link_pdf")
	private String disLinkPdf;

	@Column(name="dis_metros_cuadrados")
	private BigDecimal disMetrosCuadrados;

	@Column(name="dis_nombre")
	private String disNombre;

	//bi-directional many-to-one association to GenBarrio
	@OneToMany(mappedBy="genDistrito")
	private List<GenBarrio> genBarrios;

	//bi-directional many-to-one association to GenZona
	@ManyToOne
	@JoinColumn(name="zon_id")
	private GenZona genZona;

	public GenDistrito() {
	}

	public String getDisId() {
		return this.disId;
	}

	public void setDisId(String disId) {
		this.disId = disId;
	}

	public String getDisDescripcion() {
		return this.disDescripcion;
	}

	public void setDisDescripcion(String disDescripcion) {
		this.disDescripcion = disDescripcion;
	}

	public String getDisEstado() {
		return this.disEstado;
	}

	public void setDisEstado(String disEstado) {
		this.disEstado = disEstado;
	}

	public BigDecimal getDisHectareas() {
		return this.disHectareas;
	}

	public void setDisHectareas(BigDecimal disHectareas) {
		this.disHectareas = disHectareas;
	}

	public String getDisLinkMapa() {
		return this.disLinkMapa;
	}

	public void setDisLinkMapa(String disLinkMapa) {
		this.disLinkMapa = disLinkMapa;
	}

	public String getDisLinkPdf() {
		return this.disLinkPdf;
	}

	public void setDisLinkPdf(String disLinkPdf) {
		this.disLinkPdf = disLinkPdf;
	}

	public BigDecimal getDisMetrosCuadrados() {
		return this.disMetrosCuadrados;
	}

	public void setDisMetrosCuadrados(BigDecimal disMetrosCuadrados) {
		this.disMetrosCuadrados = disMetrosCuadrados;
	}

	public String getDisNombre() {
		return this.disNombre;
	}

	public void setDisNombre(String disNombre) {
		this.disNombre = disNombre;
	}

	public List<GenBarrio> getGenBarrios() {
		return this.genBarrios;
	}

	public void setGenBarrios(List<GenBarrio> genBarrios) {
		this.genBarrios = genBarrios;
	}

	public GenBarrio addGenBarrio(GenBarrio genBarrio) {
		getGenBarrios().add(genBarrio);
		genBarrio.setGenDistrito(this);

		return genBarrio;
	}

	public GenBarrio removeGenBarrio(GenBarrio genBarrio) {
		getGenBarrios().remove(genBarrio);
		genBarrio.setGenDistrito(null);

		return genBarrio;
	}

	public GenZona getGenZona() {
		return this.genZona;
	}

	public void setGenZona(GenZona genZona) {
		this.genZona = genZona;
	}

}