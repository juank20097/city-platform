package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


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

	@Column(name="bar_estado", columnDefinition = "bpchar")
	private String barEstado;

	@Column(name="bar_hectareas")
	private BigDecimal barHectareas;

	@Column(name="bar_link_mapa")
	private String barLinkMapa;

	@Column(name="bar_link_pdf")
	private String barLinkPdf;

	@Column(name="bar_kilometros")
	private BigDecimal barKilometros;

	@Column(name="bar_nombre")
	private String barNombre;
	
	@Column(name="bar_observacion")
	private String barObservacion;

	//bi-directional many-to-one association to GenDistrito
	@ManyToOne
	@JoinColumn(name="dis_id")
	private GenDistrito genDistrito;
	
	//bi-directional many-to-one association to GenElementoBarrioValor
	@OneToMany(mappedBy="genBarrio")
	private List<GenElementoBarrioValor> genElementoBarrioValors;
	

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

	public BigDecimal getBarKilometros() {
		return this.barKilometros;
	}

	public void setBarKilometros(BigDecimal barKilometros) {
		this.barKilometros = barKilometros;
	}

	public String getBarNombre() {
		return this.barNombre;
	}

	public String getBarObservacion() {
		return this.barObservacion;
	}

	public void setBarObservacion(String barObservacion) {
		this.barObservacion = barObservacion;
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
	public List<GenElementoBarrioValor> getGenElementoBarrioValors() {
		return this.genElementoBarrioValors;
	}

	public void setGenElementoBarrioValors(List<GenElementoBarrioValor> genElementoBarrioValors) {
		this.genElementoBarrioValors = genElementoBarrioValors;
	}

	public GenElementoBarrioValor addGenElementoBarrioValor(GenElementoBarrioValor genElementoBarrioValor) {
		getGenElementoBarrioValors().add(genElementoBarrioValor);
		genElementoBarrioValor.setGenBarrio(this);

		return genElementoBarrioValor;
	}

	public GenElementoBarrioValor removeGenElementoBarrioValor(GenElementoBarrioValor genElementoBarrioValor) {
		getGenElementoBarrioValors().remove(genElementoBarrioValor);
		genElementoBarrioValor.setGenBarrio(null);

		return genElementoBarrioValor;
	}

}