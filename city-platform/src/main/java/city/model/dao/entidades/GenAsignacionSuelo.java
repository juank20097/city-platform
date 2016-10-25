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
@Table(name="gen_asignacion_suelo")
@NamedQuery(name="GenAsignacionSuelo.findAll", query="SELECT g FROM GenAsignacionSuelo g")
public class GenAsignacionSuelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sue_id")
	private Integer sueId;

	@Column(name="sue_acta_resolucion_comite")
	private String sueActaResolucionComite;

	@Column(name="sue_actividad")
	private String sueActividad;

	@Column(name="sue_archivo")
	private String sueArchivo;

	@Column(name="sue_asignacion")
	private String sueAsignacion;

	@Column(name="sue_concesion_fuente_hidrica")
	private String sueConcesionFuenteHidrica;

	@Column(name="sue_coordenada_x")
	private BigDecimal sueCoordenadaX;

	@Column(name="sue_coordenada_y")
	private BigDecimal sueCoordenadaY;

	@Column(name="sue_descripcion")
	private String sueDescripcion;

	@Column(name="sue_direccion_responsable")
	private String sueDireccionResponsable;

	@Column(name="sue_estado", columnDefinition = "bpchar")
	private String sueEstado;

	@Column(name="sue_estado_proceso", columnDefinition = "bpchar")
	private String sueEstadoProceso;

	@Temporal(TemporalType.DATE)
	@Column(name="sue_fecha_fin")
	private Date sueFechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="sue_fecha_inicio")
	private Date sueFechaInicio;

	@Column(name="sue_fuente_hidrica")
	private String sueFuenteHidrica;

	@Column(name="sue_infor_actual_gestion_terr")
	private String sueInforActualGestionTerr;

	@Column(name="sue_infor_consolidado")
	private String sueInforConsolidado;

	@Column(name="sue_infor_consolidado_2")
	private String sueInforConsolidado2;

	@Column(name="sue_infor_gestion_territorial")
	private String sueInforGestionTerritorial;

	@Column(name="sue_infor_pronunciamiento_jurid")
	private String sueInforPronunciamientoJurid;

	@Column(name="sue_metros")
	private BigDecimal sueMetros;

	@Column(name="sue_nombre")
	private String sueNombre;

	@Column(name="sue_numero_anios")
	private Integer sueNumeroAnios;

	@Column(name="sue_observacion")
	private String sueObservacion;

	@Column(name="sue_ocupado")
	private Boolean sueOcupado;

	@Column(name="sue_ocupado_por")
	private String sueOcupadoPor;

	@Column(name="sue_regulacion_ambiental")
	private String sueRegulacionAmbiental;

	@Column(name="sue_responsable")
	private String sueResponsable;

	@Column(name="sue_responsable_concesion")
	private String sueResponsableConcesion;

	@Column(name="sue_solicitud_comite")
	private String sueSolicitudComite;

	@Column(name="sue_superficie_asignada")
	private BigDecimal sueSuperficieAsignada;

	@Column(name="sue_superficie_solicitada")
	private BigDecimal sueSuperficieSolicitada;

	@Column(name="sue_tipo")
	private String sueTipo;

	@Column(name="sue_tipo_uso")
	private String sueTipoUso;

	@Column(name="sue_unidad_tiempo")
	private String sueUnidadTiempo;

	//bi-directional many-to-one association to GenZona
	@ManyToOne
	@JoinColumn(name="zon_id")
	private GenZona genZona;

	//bi-directional many-to-one association to GenContratoAsignacion
	@OneToMany(mappedBy="genAsignacionSuelo")
	private List<GenContratoAsignacion> genContratoAsignacions;

	//bi-directional many-to-one association to GenHistorialSeguimiento
	@OneToMany(mappedBy="genAsignacionSuelo")
	private List<GenHistorialSeguimiento> genHistorialSeguimientos;

	public GenAsignacionSuelo() {
	}

	public Integer getSueId() {
		return this.sueId;
	}

	public void setSueId(Integer sueId) {
		this.sueId = sueId;
	}

	public String getSueActaResolucionComite() {
		return this.sueActaResolucionComite;
	}

	public void setSueActaResolucionComite(String sueActaResolucionComite) {
		this.sueActaResolucionComite = sueActaResolucionComite;
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

	public String getSueConcesionFuenteHidrica() {
		return this.sueConcesionFuenteHidrica;
	}

	public void setSueConcesionFuenteHidrica(String sueConcesionFuenteHidrica) {
		this.sueConcesionFuenteHidrica = sueConcesionFuenteHidrica;
	}

	public BigDecimal getSueCoordenadaX() {
		return this.sueCoordenadaX;
	}

	public void setSueCoordenadaX(BigDecimal sueCoordenadaX) {
		this.sueCoordenadaX = sueCoordenadaX;
	}

	public BigDecimal getSueCoordenadaY() {
		return this.sueCoordenadaY;
	}

	public void setSueCoordenadaY(BigDecimal sueCoordenadaY) {
		this.sueCoordenadaY = sueCoordenadaY;
	}

	public String getSueDescripcion() {
		return this.sueDescripcion;
	}

	public void setSueDescripcion(String sueDescripcion) {
		this.sueDescripcion = sueDescripcion;
	}

	public String getSueDireccionResponsable() {
		return this.sueDireccionResponsable;
	}

	public void setSueDireccionResponsable(String sueDireccionResponsable) {
		this.sueDireccionResponsable = sueDireccionResponsable;
	}

	public String getSueEstado() {
		return this.sueEstado;
	}

	public void setSueEstado(String sueEstado) {
		this.sueEstado = sueEstado;
	}

	public String getSueEstadoProceso() {
		return this.sueEstadoProceso;
	}

	public void setSueEstadoProceso(String sueEstadoProceso) {
		this.sueEstadoProceso = sueEstadoProceso;
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

	public String getSueFuenteHidrica() {
		return this.sueFuenteHidrica;
	}

	public void setSueFuenteHidrica(String sueFuenteHidrica) {
		this.sueFuenteHidrica = sueFuenteHidrica;
	}

	public String getSueInforActualGestionTerr() {
		return this.sueInforActualGestionTerr;
	}

	public void setSueInforActualGestionTerr(String sueInforActualGestionTerr) {
		this.sueInforActualGestionTerr = sueInforActualGestionTerr;
	}

	public String getSueInforConsolidado() {
		return this.sueInforConsolidado;
	}

	public void setSueInforConsolidado(String sueInforConsolidado) {
		this.sueInforConsolidado = sueInforConsolidado;
	}

	public String getSueInforConsolidado2() {
		return this.sueInforConsolidado2;
	}

	public void setSueInforConsolidado2(String sueInforConsolidado2) {
		this.sueInforConsolidado2 = sueInforConsolidado2;
	}

	public String getSueInforGestionTerritorial() {
		return this.sueInforGestionTerritorial;
	}

	public void setSueInforGestionTerritorial(String sueInforGestionTerritorial) {
		this.sueInforGestionTerritorial = sueInforGestionTerritorial;
	}

	public String getSueInforPronunciamientoJurid() {
		return this.sueInforPronunciamientoJurid;
	}

	public void setSueInforPronunciamientoJurid(String sueInforPronunciamientoJurid) {
		this.sueInforPronunciamientoJurid = sueInforPronunciamientoJurid;
	}

	public BigDecimal getSueMetros() {
		return this.sueMetros;
	}

	public void setSueMetros(BigDecimal sueMetros) {
		this.sueMetros = sueMetros;
	}

	public String getSueNombre() {
		return this.sueNombre;
	}

	public void setSueNombre(String sueNombre) {
		this.sueNombre = sueNombre;
	}

	public Integer getSueNumeroAnios() {
		return this.sueNumeroAnios;
	}

	public void setSueNumeroAnios(Integer sueNumeroAnios) {
		this.sueNumeroAnios = sueNumeroAnios;
	}

	public String getSueObservacion() {
		return this.sueObservacion;
	}

	public void setSueObservacion(String sueObservacion) {
		this.sueObservacion = sueObservacion;
	}

	public Boolean getSueOcupado() {
		return this.sueOcupado;
	}

	public void setSueOcupado(Boolean sueOcupado) {
		this.sueOcupado = sueOcupado;
	}

	public String getSueOcupadoPor() {
		return this.sueOcupadoPor;
	}

	public void setSueOcupadoPor(String sueOcupadoPor) {
		this.sueOcupadoPor = sueOcupadoPor;
	}

	public String getSueRegulacionAmbiental() {
		return this.sueRegulacionAmbiental;
	}

	public void setSueRegulacionAmbiental(String sueRegulacionAmbiental) {
		this.sueRegulacionAmbiental = sueRegulacionAmbiental;
	}

	public String getSueResponsable() {
		return this.sueResponsable;
	}

	public void setSueResponsable(String sueResponsable) {
		this.sueResponsable = sueResponsable;
	}

	public String getSueResponsableConcesion() {
		return this.sueResponsableConcesion;
	}

	public void setSueResponsableConcesion(String sueResponsableConcesion) {
		this.sueResponsableConcesion = sueResponsableConcesion;
	}

	public String getSueSolicitudComite() {
		return this.sueSolicitudComite;
	}

	public void setSueSolicitudComite(String sueSolicitudComite) {
		this.sueSolicitudComite = sueSolicitudComite;
	}

	public BigDecimal getSueSuperficieAsignada() {
		return this.sueSuperficieAsignada;
	}

	public void setSueSuperficieAsignada(BigDecimal sueSuperficieAsignada) {
		this.sueSuperficieAsignada = sueSuperficieAsignada;
	}

	public BigDecimal getSueSuperficieSolicitada() {
		return this.sueSuperficieSolicitada;
	}

	public void setSueSuperficieSolicitada(BigDecimal sueSuperficieSolicitada) {
		this.sueSuperficieSolicitada = sueSuperficieSolicitada;
	}

	public String getSueTipo() {
		return this.sueTipo;
	}

	public void setSueTipo(String sueTipo) {
		this.sueTipo = sueTipo;
	}

	public String getSueTipoUso() {
		return this.sueTipoUso;
	}

	public void setSueTipoUso(String sueTipoUso) {
		this.sueTipoUso = sueTipoUso;
	}

	public String getSueUnidadTiempo() {
		return this.sueUnidadTiempo;
	}

	public void setSueUnidadTiempo(String sueUnidadTiempo) {
		this.sueUnidadTiempo = sueUnidadTiempo;
	}

	public GenZona getGenZona() {
		return this.genZona;
	}

	public void setGenZona(GenZona genZona) {
		this.genZona = genZona;
	}

	public List<GenContratoAsignacion> getGenContratoAsignacions() {
		return this.genContratoAsignacions;
	}

	public void setGenContratoAsignacions(List<GenContratoAsignacion> genContratoAsignacions) {
		this.genContratoAsignacions = genContratoAsignacions;
	}

	public GenContratoAsignacion addGenContratoAsignacion(GenContratoAsignacion genContratoAsignacion) {
		getGenContratoAsignacions().add(genContratoAsignacion);
		genContratoAsignacion.setGenAsignacionSuelo(this);

		return genContratoAsignacion;
	}

	public GenContratoAsignacion removeGenContratoAsignacion(GenContratoAsignacion genContratoAsignacion) {
		getGenContratoAsignacions().remove(genContratoAsignacion);
		genContratoAsignacion.setGenAsignacionSuelo(null);

		return genContratoAsignacion;
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