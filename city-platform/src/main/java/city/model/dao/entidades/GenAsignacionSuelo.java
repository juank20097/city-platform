package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the gen_asignacion_suelo database table.
 * 
 */
@Entity
@Table(name = "gen_asignacion_suelo")
@NamedQuery(name = "GenAsignacionSuelo.findAll", query = "SELECT g FROM GenAsignacionSuelo g")
public class GenAsignacionSuelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "sue_id")
	private Integer sueId;

	@Column(name = "sue_actividad")
	private String sueActividad;

	@Column(name = "sue_archivo")
	private String sueArchivo;

	@Column(name = "sue_asignacion")
	private String sueAsignacion;

	@Column(name = "sue_estado", columnDefinition = "bpchar")
	private String sueEstado;

	@Temporal(TemporalType.DATE)
	@Column(name = "sue_fecha_fin")
	private Date sueFechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name = "sue_fecha_inicio")
	private Date sueFechaInicio;

	@Column(name = "sue_metros")
	private BigDecimal sueMetros;

	@Column(name = "sue_observacion")
	private String sueObservacion;

	@Column(name = "sue_tipo")
	private String sueTipo;

	@Column(name = "sue_numeroanios")
	private Integer sueNumeroAnios;

	// bi-directional many-to-one association to GenZona
	@ManyToOne
	@JoinColumn(name = "zon_id")
	private GenZona genZona;

	// bi-directional many-to-one association to GenHistorialSeguimiento
	@OneToMany(mappedBy = "genAsignacionSuelo")
	private List<GenHistorialSeguimiento> genHistorialSeguimientos;

	public GenAsignacionSuelo() {
	}

	public Integer getSueId() {
		return this.sueId;
	}

	public void setSueId(Integer sueId) {
		this.sueId = sueId;
	}

	public String getSueActividad() {
		return this.sueActividad;
	}

	public void setSueActividad(String sueActividad) {
		this.sueActividad = sueActividad;
	}

	public String getSueArchivo() {
		return this.sueArchivo;
	}

	public void setSueArchivo(String sueArchivo) {
		this.sueArchivo = sueArchivo;
	}

	public String getSueAsignacion() {
		return this.sueAsignacion;
	}

	public void setSueAsignacion(String sueAsignacion) {
		this.sueAsignacion = sueAsignacion;
	}

	public String getSueEstado() {
		return this.sueEstado;
	}

	public void setSueEstado(String sueEstado) {
		this.sueEstado = sueEstado;
	}

	public Date getSueFechaFin() {
		return this.sueFechaFin;
	}

	public void setSueFechaFin(Date sueFechaFin) {
		this.sueFechaFin = sueFechaFin;
	}

	public Date getSueFechaInicio() {
		return this.sueFechaInicio;
	}

	public void setSueFechaInicio(Date sueFechaInicio) {
		this.sueFechaInicio = sueFechaInicio;
	}

	public BigDecimal getSueMetros() {
		return this.sueMetros;
	}

	public void setSueMetros(BigDecimal sueMetros) {
		this.sueMetros = sueMetros;
	}

	public String getSueObservacion() {
		return this.sueObservacion;
	}

	public void setSueObservacion(String sueObservacion) {
		this.sueObservacion = sueObservacion;
	}

	public String getSueTipo() {
		return this.sueTipo;
	}

	public void setSueTipo(String sueTipo) {
		this.sueTipo = sueTipo;
	}

	public Integer getSueNumeroAnios() {
		return sueNumeroAnios;
	}

	public void setSueNumeroAnios(Integer sueNumeroAnios) {
		this.sueNumeroAnios = sueNumeroAnios;
	}

	public GenZona getGenZona() {
		return this.genZona;
	}

	public void setGenZona(GenZona genZona) {
		this.genZona = genZona;
	}
	
	public List<GenHistorialSeguimiento> getGenHistorialSeguimientos() {
		return this.genHistorialSeguimientos;
	}

	public void setGenHistorialSeguimientos(List<GenHistorialSeguimiento> genHistorialSeguimientos) {
		this.genHistorialSeguimientos = genHistorialSeguimientos;
	}

	public GenHistorialSeguimiento addGenHistorialSeguimiento(GenHistorialSeguimiento genHistorialSeguimiento) {
		getGenHistorialSeguimientos().add(genHistorialSeguimiento);
		genHistorialSeguimiento.setGenAsignacionSuelo(this);

		return genHistorialSeguimiento;
	}

	public GenHistorialSeguimiento removeGenHistorialSeguimiento(GenHistorialSeguimiento genHistorialSeguimiento) {
		getGenHistorialSeguimientos().remove(genHistorialSeguimiento);
		genHistorialSeguimiento.setGenAsignacionSuelo(null);

		return genHistorialSeguimiento;
	}

}