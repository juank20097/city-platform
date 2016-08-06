package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the gen_barrios database table.
 * 
 */
@Entity
@Table(name="gen_barrios")
@NamedQuery(name="GenBarrio.findAll", query="SELECT g FROM GenBarrio g")
public class GenBarrio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="bar_id")
	private String barId;

	@Column(name="bar_descripcion")
	private String barDescripcion;

	@Column(name="bar_estado")
	private String barEstado;

	@Column(name="bar_hectareas")
	private BigDecimal barHectareas;

	@Column(name="bar_link_mapa")
	private String barLinkMapa;

	@Column(name="bar_link_pdf")
	private String barLinkPdf;

	@Column(name="bar_metros_cuadrados")
	private BigDecimal barMetrosCuadrados;

	@Column(name="bar_nombre")
	private String barNombre;

	//bi-directional many-to-one association to GenDistrito
	@ManyToOne
	@JoinColumn(name="dis_id")
	private GenDistrito genDistrito;

	public GenBarrio() {
	}

	public String getBarId() {
		return this.barId;
	}

	public void setBarId(String barId) {
		this.barId = barId;
	}

	public String getBarDescripcion() {
		return this.barDescripcion;
	}

	public void setBarDescripcion(String barDescripcion) {
		this.barDescripcion = barDescripcion;
	}

	public String getBarEstado() {
		return this.barEstado;
	}

	public void setBarEstado(String barEstado) {
		this.barEstado = barEstado;
	}

	public BigDecimal getBarHectareas() {
		return this.barHectareas;
	}

	public void setBarHectareas(BigDecimal barHectareas) {
		this.barHectareas = barHectareas;
	}

	public String getBarLinkMapa() {
		return this.barLinkMapa;
	}

	public void setBarLinkMapa(String barLinkMapa) {
		this.barLinkMapa = barLinkMapa;
	}

	public String getBarLinkPdf() {
		return this.barLinkPdf;
	}

	public void setBarLinkPdf(String barLinkPdf) {
		this.barLinkPdf = barLinkPdf;
	}

	public BigDecimal getBarMetrosCuadrados() {
		return this.barMetrosCuadrados;
	}

	public void setBarMetrosCuadrados(BigDecimal barMetrosCuadrados) {
		this.barMetrosCuadrados = barMetrosCuadrados;
	}

	public String getBarNombre() {
		return this.barNombre;
	}

	public void setBarNombre(String barNombre) {
		this.barNombre = barNombre;
	}

	public GenDistrito getGenDistrito() {
		return this.genDistrito;
	}

	public void setGenDistrito(GenDistrito genDistrito) {
		this.genDistrito = genDistrito;
	}

}