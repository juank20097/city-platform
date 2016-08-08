package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the gen_comunidades database table.
 * 
 */
@Entity
@Table(name="gen_comunidades")
@NamedQuery(name="GenComunidade.findAll", query="SELECT g FROM GenComunidade g")
public class GenComunidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="com_id")
	private String comId;

	@Column(name="com_estado", columnDefinition="bpchar", length=1)
	private String comEstado;

	@Column(name="com_hectareas")
	private BigDecimal comHectareas;

	@Column(name="com_link_mapa")
	private String comLinkMapa;

	@Column(name="com_link_pdf")
	private String comLinkPdf;

	@Column(name="com_metros_cuadrados")
	private BigDecimal comMetrosCuadrados;

	@Column(name="com_nombre")
	private String comNombre;

	@Column(name="com_ubicacion")
	private Boolean comUbicacion;

	//bi-directional many-to-one association to GenZonasComunidade
	@OneToMany(mappedBy="genComunidade")
	private List<GenZonasComunidade> genZonasComunidades;

	public GenComunidade() {
	}

	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getComEstado() {
		return this.comEstado;
	}

	public void setComEstado(String comEstado) {
		this.comEstado = comEstado;
	}

	public BigDecimal getComHectareas() {
		return this.comHectareas;
	}

	public void setComHectareas(BigDecimal comHectareas) {
		this.comHectareas = comHectareas;
	}

	public String getComLinkMapa() {
		return this.comLinkMapa;
	}

	public void setComLinkMapa(String comLinkMapa) {
		this.comLinkMapa = comLinkMapa;
	}

	public String getComLinkPdf() {
		return this.comLinkPdf;
	}

	public void setComLinkPdf(String comLinkPdf) {
		this.comLinkPdf = comLinkPdf;
	}

	public BigDecimal getComMetrosCuadrados() {
		return this.comMetrosCuadrados;
	}

	public void setComMetrosCuadrados(BigDecimal comMetrosCuadrados) {
		this.comMetrosCuadrados = comMetrosCuadrados;
	}

	public String getComNombre() {
		return this.comNombre;
	}

	public void setComNombre(String comNombre) {
		this.comNombre = comNombre;
	}

	public Boolean getComUbicacion() {
		return this.comUbicacion;
	}

	public void setComUbicacion(Boolean comUbicacion) {
		this.comUbicacion = comUbicacion;
	}

	public List<GenZonasComunidade> getGenZonasComunidades() {
		return this.genZonasComunidades;
	}

	public void setGenZonasComunidades(List<GenZonasComunidade> genZonasComunidades) {
		this.genZonasComunidades = genZonasComunidades;
	}

	public GenZonasComunidade addGenZonasComunidade(GenZonasComunidade genZonasComunidade) {
		getGenZonasComunidades().add(genZonasComunidade);
		genZonasComunidade.setGenComunidade(this);

		return genZonasComunidade;
	}

	public GenZonasComunidade removeGenZonasComunidade(GenZonasComunidade genZonasComunidade) {
		getGenZonasComunidades().remove(genZonasComunidade);
		genZonasComunidade.setGenComunidade(null);

		return genZonasComunidade;
	}

}