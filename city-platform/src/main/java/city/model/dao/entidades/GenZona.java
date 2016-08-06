package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the gen_zonas database table.
 * 
 */
@Entity
@Table(name="gen_zonas")
@NamedQuery(name="GenZona.findAll", query="SELECT g FROM GenZona g")
public class GenZona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="zon_id")
	private String zonId;

	@Column(name="zon_descripcion")
	private String zonDescripcion;

	@Column(name="zon_estado")
	private String zonEstado;

	@Column(name="zon_hectareas")
	private BigDecimal zonHectareas;

	@Column(name="zon_link_mapa")
	private String zonLinkMapa;

	@Column(name="zon_link_pdf")
	private String zonLinkPdf;

	@Column(name="zon_metros_cuadrados")
	private BigDecimal zonMetrosCuadrados;

	@Column(name="zon_nombre")
	private String zonNombre;

	//bi-directional many-to-one association to GenDistrito
	@OneToMany(mappedBy="genZona")
	private List<GenDistrito> genDistritos;

	//bi-directional many-to-one association to GenZonasComunidade
	@OneToMany(mappedBy="genZona")
	private List<GenZonasComunidade> genZonasComunidades;

	public GenZona() {
	}

	public String getZonId() {
		return this.zonId;
	}

	public void setZonId(String zonId) {
		this.zonId = zonId;
	}

	public String getZonDescripcion() {
		return this.zonDescripcion;
	}

	public void setZonDescripcion(String zonDescripcion) {
		this.zonDescripcion = zonDescripcion;
	}

	public String getZonEstado() {
		return this.zonEstado;
	}

	public void setZonEstado(String zonEstado) {
		this.zonEstado = zonEstado;
	}

	public BigDecimal getZonHectareas() {
		return this.zonHectareas;
	}

	public void setZonHectareas(BigDecimal zonHectareas) {
		this.zonHectareas = zonHectareas;
	}

	public String getZonLinkMapa() {
		return this.zonLinkMapa;
	}

	public void setZonLinkMapa(String zonLinkMapa) {
		this.zonLinkMapa = zonLinkMapa;
	}

	public String getZonLinkPdf() {
		return this.zonLinkPdf;
	}

	public void setZonLinkPdf(String zonLinkPdf) {
		this.zonLinkPdf = zonLinkPdf;
	}

	public BigDecimal getZonMetrosCuadrados() {
		return this.zonMetrosCuadrados;
	}

	public void setZonMetrosCuadrados(BigDecimal zonMetrosCuadrados) {
		this.zonMetrosCuadrados = zonMetrosCuadrados;
	}

	public String getZonNombre() {
		return this.zonNombre;
	}

	public void setZonNombre(String zonNombre) {
		this.zonNombre = zonNombre;
	}

	public List<GenDistrito> getGenDistritos() {
		return this.genDistritos;
	}

	public void setGenDistritos(List<GenDistrito> genDistritos) {
		this.genDistritos = genDistritos;
	}

	public GenDistrito addGenDistrito(GenDistrito genDistrito) {
		getGenDistritos().add(genDistrito);
		genDistrito.setGenZona(this);

		return genDistrito;
	}

	public GenDistrito removeGenDistrito(GenDistrito genDistrito) {
		getGenDistritos().remove(genDistrito);
		genDistrito.setGenZona(null);

		return genDistrito;
	}

	public List<GenZonasComunidade> getGenZonasComunidades() {
		return this.genZonasComunidades;
	}

	public void setGenZonasComunidades(List<GenZonasComunidade> genZonasComunidades) {
		this.genZonasComunidades = genZonasComunidades;
	}

	public GenZonasComunidade addGenZonasComunidade(GenZonasComunidade genZonasComunidade) {
		getGenZonasComunidades().add(genZonasComunidade);
		genZonasComunidade.setGenZona(this);

		return genZonasComunidade;
	}

	public GenZonasComunidade removeGenZonasComunidade(GenZonasComunidade genZonasComunidade) {
		getGenZonasComunidades().remove(genZonasComunidade);
		genZonasComunidade.setGenZona(null);

		return genZonasComunidade;
	}

}