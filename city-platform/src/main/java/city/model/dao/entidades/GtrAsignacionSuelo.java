package city.model.dao.entidades;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the gtr_asignacion_suelo database table.
 * 
 */
@Entity
@Table(name="gtr_asignacion_suelo")
@NamedQuery(name="GtrAsignacionSuelo.findAll", query="SELECT g FROM GtrAsignacionSuelo g")
public class GtrAsignacionSuelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sue_id")
	private Integer sueId;

	@Column(name="sue_acta_resolutiva")
	private String sueActaResolutiva;

	@Column(name="sue_actividad")
	private String sueActividad;

	@Column(name="sue_aplica_regulacion_ambiental")
	private Boolean sueAplicaRegulacionAmbiental;

	@Column(name="sue_archivo_kmz")
	private String sueArchivoKmz;

	@Column(name="sue_archivo_pdf")
	private String sueArchivoPdf;

	@Column(name="sue_archivo_regulacion_amb")
	private String sueArchivoRegulacionAmb;

	@Column(name="sue_asignacion")
	private String sueAsignacion;

	@Column(name="sue_caudal_asignado")
	private String sueCaudalAsignado;

	@Column(name="sue_caudal_total")
	private String sueCaudalTotal;

	@Column(name="sue_concesion_fuente_hidrica")
	private String sueConcesionFuenteHidrica;

	@Column(name="sue_convocatoria")
	private String sueConvocatoria;

	@Column(name="sue_descripcion")
	private String sueDescripcion;

	@Column(name="sue_direccion_responsable")
	private String sueDireccionResponsable;

	@Column(name="sue_ente_aprobador")
	private String sueEnteAprobador;
	
	@Column(name="sue_estado", columnDefinition = "bpchar")
	private String sueEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="sue_fecha_archivo_kmz")
	private Date sueFechaArchivoKmz;

	@Temporal(TemporalType.DATE)
	@Column(name="sue_fecha_archivo_pdf")
	private Date sueFechaArchivoPdf;

	@Temporal(TemporalType.DATE)
	@Column(name="sue_fecha_archivo_reg_amb")
	private Date sueFechaArchivoRegAmb;

	@Temporal(TemporalType.DATE)
	@Column(name="sue_fecha_doc_caracterizacion")
	private Date sueFechaDocCaracterizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="sue_fecha_doc_consolidado")
	private Date sueFechaDocConsolidado;

	@Temporal(TemporalType.DATE)
	@Column(name="sue_fecha_doc_convocatoria")
	private Date sueFechaDocConvocatoria;

	@Temporal(TemporalType.DATE)
	@Column(name="sue_fecha_doc_disponibilidad")
	private Date sueFechaDocDisponibilidad;

	@Temporal(TemporalType.DATE)
	@Column(name="sue_fecha_doc_notificacion")
	private Date sueFechaDocNotificacion;

	@Temporal(TemporalType.DATE)
	@Column(name="sue_fecha_doc_resolucion")
	private Date sueFechaDocResolucion;

	@Temporal(TemporalType.DATE)
	@Column(name="sue_fecha_doc_resolutiva")
	private Date sueFechaDocResolutiva;

	@Temporal(TemporalType.DATE)
	@Column(name="sue_fecha_doc_uso_suelo")
	private Date sueFechaDocUsoSuelo;

	@Temporal(TemporalType.DATE)
	@Column(name="sue_fecha_fin")
	private Date sueFechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="sue_fecha_inicio")
	private Date sueFechaInicio;

	@Column(name="sue_fecha_subida_caracterizacio")
	private Timestamp sueFechaSubidaCaracterizacio;

	@Column(name="sue_fecha_subida_consolidado")
	private Timestamp sueFechaSubidaConsolidado;

	@Column(name="sue_fecha_subida_convocatoria")
	private Timestamp sueFechaSubidaConvocatoria;

	@Column(name="sue_fecha_subida_disponibilidad")
	private Timestamp sueFechaSubidaDisponibilidad;

	@Column(name="sue_fecha_subida_kmz")
	private Timestamp sueFechaSubidaKmz;

	@Column(name="sue_fecha_subida_notificacion")
	private Timestamp sueFechaSubidaNotificacion;

	@Column(name="sue_fecha_subida_pdf")
	private Timestamp sueFechaSubidaPdf;

	@Column(name="sue_fecha_subida_reg_amb")
	private Timestamp sueFechaSubidaRegAmb;

	@Column(name="sue_fecha_subida_resolucion")
	private Timestamp sueFechaSubidaResolucion;

	@Column(name="sue_fecha_subida_resolutiva")
	private Timestamp sueFechaSubidaResolutiva;

	@Column(name="sue_fecha_subida_uso_suelo")
	private Timestamp sueFechaSubidaUsoSuelo;

	@Column(name="sue_figura_legal")
	private String sueFiguraLegal;

	@Column(name="sue_fuente_hidrica")
	private String sueFuenteHidrica;

	@Column(name="sue_infor_caracterizacion")
	private String sueInforCaracterizacion;

	@Column(name="sue_infor_consolidado")
	private String sueInforConsolidado;

	@Column(name="sue_infor_disponibilidad")
	private String sueInforDisponibilidad;

	@Column(name="sue_infor_gestion_territorial")
	private String sueInforGestionTerritorial;

	@Column(name="sue_infor_uso_suelo")
	private String sueInforUsoSuelo;

	@Column(name="sue_nombre")
	private String sueNombre;

	@Column(name="sue_notificacion_ap_ne")
	private String sueNotificacionApNe;

	@Column(name="sue_numero_anios")
	private Integer sueNumeroAnios;

	@Column(name="sue_observacion")
	private String sueObservacion;

	@Column(name="sue_resolucion")
	private String sueResolucion;

	@Column(name="sue_responsable")
	private String sueResponsable;

	@Column(name="sue_responsable_concesion")
	private String sueResponsableConcesion;

	@Column(name="sue_superficie_asignada")
	private BigDecimal sueSuperficieAsignada;

	@Column(name="sue_superficie_solicitada")
	private BigDecimal sueSuperficieSolicitada;

	@Column(name="sue_tipo")
	private String sueTipo;

	@Column(name="sue_unidad_sup_asignada")
	private String sueUnidadSupAsignada;

	@Column(name="sue_unidad_sup_solicitada")
	private String sueUnidadSupSolicitada;

	@Column(name="sue_unidad_tiempo")
	private String sueUnidadTiempo;

	@Column(name="sue_usuario_archivo_kmz")
	private String sueUsuarioArchivoKmz;

	@Column(name="sue_usuario_archivo_pdf")
	private String sueUsuarioArchivoPdf;

	@Column(name="sue_usuario_caracterizacion")
	private String sueUsuarioCaracterizacion;

	@Column(name="sue_usuario_consolidado")
	private String sueUsuarioConsolidado;

	@Column(name="sue_usuario_convocatoria")
	private String sueUsuarioConvocatoria;

	@Column(name="sue_usuario_disponibilidad")
	private String sueUsuarioDisponibilidad;

	@Column(name="sue_usuario_notificacion")
	private String sueUsuarioNotificacion;

	@Column(name="sue_usuario_resolucion")
	private String sueUsuarioResolucion;

	@Column(name="sue_usuario_resolutiva")
	private String sueUsuarioResolutiva;

	@Column(name="sue_usuario_subida_reg_amb")
	private String sueUsuarioSubidaRegAmb;

	@Column(name="sue_usuario_uso_suelo")
	private String sueUsuarioUsoSuelo;

	//bi-directional many-to-one association to GenZona
	@ManyToOne
	@JoinColumn(name="zon_id")
	private GenZona genZona;

	//bi-directional many-to-one association to GtrContratoAsignacion
	@OneToMany(mappedBy="gtrAsignacionSuelo")
	private List<GtrContratoAsignacion> gtrContratoAsignacions;

	//bi-directional many-to-one association to GtrHistorialSeguimiento
	@OneToMany(mappedBy="gtrAsignacionSuelo")
	private List<GtrHistorialSeguimiento> gtrHistorialSeguimientos;

	public GtrAsignacionSuelo() {
	}

	public Integer getSueId() {
		return this.sueId;
	}

	public void setSueId(Integer sueId) {
		this.sueId = sueId;
	}

	public String getSueActaResolutiva() {
		return this.sueActaResolutiva;
	}

	public void setSueActaResolutiva(String sueActaResolutiva) {
		this.sueActaResolutiva = sueActaResolutiva;
	}

	public String getSueActividad() {
		return this.sueActividad;
	}

	public void setSueActividad(String sueActividad) {
		this.sueActividad = sueActividad;
	}

	public Boolean getSueAplicaRegulacionAmbiental() {
		return this.sueAplicaRegulacionAmbiental;
	}

	public void setSueAplicaRegulacionAmbiental(Boolean sueAplicaRegulacionAmbiental) {
		this.sueAplicaRegulacionAmbiental = sueAplicaRegulacionAmbiental;
	}

	public String getSueArchivoKmz() {
		return this.sueArchivoKmz;
	}

	public void setSueArchivoKmz(String sueArchivoKmz) {
		this.sueArchivoKmz = sueArchivoKmz;
	}

	public String getSueArchivoPdf() {
		return this.sueArchivoPdf;
	}

	public void setSueArchivoPdf(String sueArchivoPdf) {
		this.sueArchivoPdf = sueArchivoPdf;
	}

	public String getSueArchivoRegulacionAmb() {
		return this.sueArchivoRegulacionAmb;
	}

	public void setSueArchivoRegulacionAmb(String sueArchivoRegulacionAmb) {
		this.sueArchivoRegulacionAmb = sueArchivoRegulacionAmb;
	}

	public String getSueAsignacion() {
		return this.sueAsignacion;
	}

	public void setSueAsignacion(String sueAsignacion) {
		this.sueAsignacion = sueAsignacion;
	}

	public String getSueCaudalAsignado() {
		return this.sueCaudalAsignado;
	}

	public void setSueCaudalAsignado(String sueCaudalAsignado) {
		this.sueCaudalAsignado = sueCaudalAsignado;
	}

	public String getSueCaudalTotal() {
		return this.sueCaudalTotal;
	}

	public void setSueCaudalTotal(String sueCaudalTotal) {
		this.sueCaudalTotal = sueCaudalTotal;
	}

	public String getSueConcesionFuenteHidrica() {
		return this.sueConcesionFuenteHidrica;
	}

	public void setSueConcesionFuenteHidrica(String sueConcesionFuenteHidrica) {
		this.sueConcesionFuenteHidrica = sueConcesionFuenteHidrica;
	}

	public String getSueConvocatoria() {
		return this.sueConvocatoria;
	}

	public void setSueConvocatoria(String sueConvocatoria) {
		this.sueConvocatoria = sueConvocatoria;
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

	public String getSueEnteAprobador() {
		return this.sueEnteAprobador;
	}

	public void setSueEnteAprobador(String sueEnteAprobador) {
		this.sueEnteAprobador = sueEnteAprobador;
	}
	
	public String getSueEstado() {
		return this.sueEstado;
	}

	public void setSueEstado(String sueEstado) {
		this.sueEstado = sueEstado;
	}

	public Date getSueFechaArchivoKmz() {
		return this.sueFechaArchivoKmz;
	}

	public void setSueFechaArchivoKmz(Date sueFechaArchivoKmz) {
		this.sueFechaArchivoKmz = sueFechaArchivoKmz;
	}

	public Date getSueFechaArchivoPdf() {
		return this.sueFechaArchivoPdf;
	}

	public void setSueFechaArchivoPdf(Date sueFechaArchivoPdf) {
		this.sueFechaArchivoPdf = sueFechaArchivoPdf;
	}

	public Date getSueFechaArchivoRegAmb() {
		return this.sueFechaArchivoRegAmb;
	}

	public void setSueFechaArchivoRegAmb(Date sueFechaArchivoRegAmb) {
		this.sueFechaArchivoRegAmb = sueFechaArchivoRegAmb;
	}

	public Date getSueFechaDocCaracterizacion() {
		return this.sueFechaDocCaracterizacion;
	}

	public void setSueFechaDocCaracterizacion(Date sueFechaDocCaracterizacion) {
		this.sueFechaDocCaracterizacion = sueFechaDocCaracterizacion;
	}

	public Date getSueFechaDocConsolidado() {
		return this.sueFechaDocConsolidado;
	}

	public void setSueFechaDocConsolidado(Date sueFechaDocConsolidado) {
		this.sueFechaDocConsolidado = sueFechaDocConsolidado;
	}

	public Date getSueFechaDocConvocatoria() {
		return this.sueFechaDocConvocatoria;
	}

	public void setSueFechaDocConvocatoria(Date sueFechaDocConvocatoria) {
		this.sueFechaDocConvocatoria = sueFechaDocConvocatoria;
	}

	public Date getSueFechaDocDisponibilidad() {
		return this.sueFechaDocDisponibilidad;
	}

	public void setSueFechaDocDisponibilidad(Date sueFechaDocDisponibilidad) {
		this.sueFechaDocDisponibilidad = sueFechaDocDisponibilidad;
	}

	public Date getSueFechaDocNotificacion() {
		return this.sueFechaDocNotificacion;
	}

	public void setSueFechaDocNotificacion(Date sueFechaDocNotificacion) {
		this.sueFechaDocNotificacion = sueFechaDocNotificacion;
	}

	public Date getSueFechaDocResolucion() {
		return this.sueFechaDocResolucion;
	}

	public void setSueFechaDocResolucion(Date sueFechaDocResolucion) {
		this.sueFechaDocResolucion = sueFechaDocResolucion;
	}

	public Date getSueFechaDocResolutiva() {
		return this.sueFechaDocResolutiva;
	}

	public void setSueFechaDocResolutiva(Date sueFechaDocResolutiva) {
		this.sueFechaDocResolutiva = sueFechaDocResolutiva;
	}

	public Date getSueFechaDocUsoSuelo() {
		return this.sueFechaDocUsoSuelo;
	}

	public void setSueFechaDocUsoSuelo(Date sueFechaDocUsoSuelo) {
		this.sueFechaDocUsoSuelo = sueFechaDocUsoSuelo;
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

	public Timestamp getSueFechaSubidaCaracterizacio() {
		return this.sueFechaSubidaCaracterizacio;
	}

	public void setSueFechaSubidaCaracterizacio(Timestamp sueFechaSubidaCaracterizacio) {
		this.sueFechaSubidaCaracterizacio = sueFechaSubidaCaracterizacio;
	}

	public Timestamp getSueFechaSubidaConsolidado() {
		return this.sueFechaSubidaConsolidado;
	}

	public void setSueFechaSubidaConsolidado(Timestamp sueFechaSubidaConsolidado) {
		this.sueFechaSubidaConsolidado = sueFechaSubidaConsolidado;
	}

	public Timestamp getSueFechaSubidaConvocatoria() {
		return this.sueFechaSubidaConvocatoria;
	}

	public void setSueFechaSubidaConvocatoria(Timestamp sueFechaSubidaConvocatoria) {
		this.sueFechaSubidaConvocatoria = sueFechaSubidaConvocatoria;
	}

	public Timestamp getSueFechaSubidaDisponibilidad() {
		return this.sueFechaSubidaDisponibilidad;
	}

	public void setSueFechaSubidaDisponibilidad(Timestamp sueFechaSubidaDisponibilidad) {
		this.sueFechaSubidaDisponibilidad = sueFechaSubidaDisponibilidad;
	}

	public Timestamp getSueFechaSubidaKmz() {
		return this.sueFechaSubidaKmz;
	}

	public void setSueFechaSubidaKmz(Timestamp sueFechaSubidaKmz) {
		this.sueFechaSubidaKmz = sueFechaSubidaKmz;
	}

	public Timestamp getSueFechaSubidaNotificacion() {
		return this.sueFechaSubidaNotificacion;
	}

	public void setSueFechaSubidaNotificacion(Timestamp sueFechaSubidaNotificacion) {
		this.sueFechaSubidaNotificacion = sueFechaSubidaNotificacion;
	}

	public Timestamp getSueFechaSubidaPdf() {
		return this.sueFechaSubidaPdf;
	}

	public void setSueFechaSubidaPdf(Timestamp sueFechaSubidaPdf) {
		this.sueFechaSubidaPdf = sueFechaSubidaPdf;
	}

	public Timestamp getSueFechaSubidaRegAmb() {
		return this.sueFechaSubidaRegAmb;
	}

	public void setSueFechaSubidaRegAmb(Timestamp sueFechaSubidaRegAmb) {
		this.sueFechaSubidaRegAmb = sueFechaSubidaRegAmb;
	}

	public Timestamp getSueFechaSubidaResolucion() {
		return this.sueFechaSubidaResolucion;
	}

	public void setSueFechaSubidaResolucion(Timestamp sueFechaSubidaResolucion) {
		this.sueFechaSubidaResolucion = sueFechaSubidaResolucion;
	}

	public Timestamp getSueFechaSubidaResolutiva() {
		return this.sueFechaSubidaResolutiva;
	}

	public void setSueFechaSubidaResolutiva(Timestamp sueFechaSubidaResolutiva) {
		this.sueFechaSubidaResolutiva = sueFechaSubidaResolutiva;
	}

	public Timestamp getSueFechaSubidaUsoSuelo() {
		return this.sueFechaSubidaUsoSuelo;
	}

	public void setSueFechaSubidaUsoSuelo(Timestamp sueFechaSubidaUsoSuelo) {
		this.sueFechaSubidaUsoSuelo = sueFechaSubidaUsoSuelo;
	}

	public String getSueFiguraLegal() {
		return this.sueFiguraLegal;
	}

	public void setSueFiguraLegal(String sueFiguraLegal) {
		this.sueFiguraLegal = sueFiguraLegal;
	}

	public String getSueFuenteHidrica() {
		return this.sueFuenteHidrica;
	}

	public void setSueFuenteHidrica(String sueFuenteHidrica) {
		this.sueFuenteHidrica = sueFuenteHidrica;
	}

	public String getSueInforCaracterizacion() {
		return this.sueInforCaracterizacion;
	}

	public void setSueInforCaracterizacion(String sueInforCaracterizacion) {
		this.sueInforCaracterizacion = sueInforCaracterizacion;
	}

	public String getSueInforConsolidado() {
		return this.sueInforConsolidado;
	}

	public void setSueInforConsolidado(String sueInforConsolidado) {
		this.sueInforConsolidado = sueInforConsolidado;
	}

	public String getSueInforDisponibilidad() {
		return this.sueInforDisponibilidad;
	}

	public void setSueInforDisponibilidad(String sueInforDisponibilidad) {
		this.sueInforDisponibilidad = sueInforDisponibilidad;
	}

	public String getSueInforGestionTerritorial() {
		return this.sueInforGestionTerritorial;
	}

	public void setSueInforGestionTerritorial(String sueInforGestionTerritorial) {
		this.sueInforGestionTerritorial = sueInforGestionTerritorial;
	}

	public String getSueInforUsoSuelo() {
		return this.sueInforUsoSuelo;
	}

	public void setSueInforUsoSuelo(String sueInforUsoSuelo) {
		this.sueInforUsoSuelo = sueInforUsoSuelo;
	}

	public String getSueNombre() {
		return this.sueNombre;
	}

	public void setSueNombre(String sueNombre) {
		this.sueNombre = sueNombre;
	}

	public String getSueNotificacionApNe() {
		return this.sueNotificacionApNe;
	}

	public void setSueNotificacionApNe(String sueNotificacionApNe) {
		this.sueNotificacionApNe = sueNotificacionApNe;
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

	public String getSueResolucion() {
		return this.sueResolucion;
	}

	public void setSueResolucion(String sueResolucion) {
		this.sueResolucion = sueResolucion;
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

	public String getSueUnidadSupAsignada() {
		return this.sueUnidadSupAsignada;
	}

	public void setSueUnidadSupAsignada(String sueUnidadSupAsignada) {
		this.sueUnidadSupAsignada = sueUnidadSupAsignada;
	}

	public String getSueUnidadSupSolicitada() {
		return this.sueUnidadSupSolicitada;
	}

	public void setSueUnidadSupSolicitada(String sueUnidadSupSolicitada) {
		this.sueUnidadSupSolicitada = sueUnidadSupSolicitada;
	}

	public String getSueUnidadTiempo() {
		return this.sueUnidadTiempo;
	}

	public void setSueUnidadTiempo(String sueUnidadTiempo) {
		this.sueUnidadTiempo = sueUnidadTiempo;
	}

	public String getSueUsuarioArchivoKmz() {
		return this.sueUsuarioArchivoKmz;
	}

	public void setSueUsuarioArchivoKmz(String sueUsuarioArchivoKmz) {
		this.sueUsuarioArchivoKmz = sueUsuarioArchivoKmz;
	}

	public String getSueUsuarioArchivoPdf() {
		return this.sueUsuarioArchivoPdf;
	}

	public void setSueUsuarioArchivoPdf(String sueUsuarioArchivoPdf) {
		this.sueUsuarioArchivoPdf = sueUsuarioArchivoPdf;
	}

	public String getSueUsuarioCaracterizacion() {
		return this.sueUsuarioCaracterizacion;
	}

	public void setSueUsuarioCaracterizacion(String sueUsuarioCaracterizacion) {
		this.sueUsuarioCaracterizacion = sueUsuarioCaracterizacion;
	}

	public String getSueUsuarioConsolidado() {
		return this.sueUsuarioConsolidado;
	}

	public void setSueUsuarioConsolidado(String sueUsuarioConsolidado) {
		this.sueUsuarioConsolidado = sueUsuarioConsolidado;
	}

	public String getSueUsuarioConvocatoria() {
		return this.sueUsuarioConvocatoria;
	}

	public void setSueUsuarioConvocatoria(String sueUsuarioConvocatoria) {
		this.sueUsuarioConvocatoria = sueUsuarioConvocatoria;
	}

	public String getSueUsuarioDisponibilidad() {
		return this.sueUsuarioDisponibilidad;
	}

	public void setSueUsuarioDisponibilidad(String sueUsuarioDisponibilidad) {
		this.sueUsuarioDisponibilidad = sueUsuarioDisponibilidad;
	}

	public String getSueUsuarioNotificacion() {
		return this.sueUsuarioNotificacion;
	}

	public void setSueUsuarioNotificacion(String sueUsuarioNotificacion) {
		this.sueUsuarioNotificacion = sueUsuarioNotificacion;
	}

	public String getSueUsuarioResolucion() {
		return this.sueUsuarioResolucion;
	}

	public void setSueUsuarioResolucion(String sueUsuarioResolucion) {
		this.sueUsuarioResolucion = sueUsuarioResolucion;
	}

	public String getSueUsuarioResolutiva() {
		return this.sueUsuarioResolutiva;
	}

	public void setSueUsuarioResolutiva(String sueUsuarioResolutiva) {
		this.sueUsuarioResolutiva = sueUsuarioResolutiva;
	}

	public String getSueUsuarioSubidaRegAmb() {
		return this.sueUsuarioSubidaRegAmb;
	}

	public void setSueUsuarioSubidaRegAmb(String sueUsuarioSubidaRegAmb) {
		this.sueUsuarioSubidaRegAmb = sueUsuarioSubidaRegAmb;
	}

	public String getSueUsuarioUsoSuelo() {
		return this.sueUsuarioUsoSuelo;
	}

	public void setSueUsuarioUsoSuelo(String sueUsuarioUsoSuelo) {
		this.sueUsuarioUsoSuelo = sueUsuarioUsoSuelo;
	}

	public GenZona getGenZona() {
		return this.genZona;
	}

	public void setGenZona(GenZona genZona) {
		this.genZona = genZona;
	}

	public List<GtrContratoAsignacion> getGtrContratoAsignacions() {
		return this.gtrContratoAsignacions;
	}

	public void setGtrContratoAsignacions(List<GtrContratoAsignacion> gtrContratoAsignacions) {
		this.gtrContratoAsignacions = gtrContratoAsignacions;
	}

	public GtrContratoAsignacion addGtrContratoAsignacion(GtrContratoAsignacion gtrContratoAsignacion) {
		getGtrContratoAsignacions().add(gtrContratoAsignacion);
		gtrContratoAsignacion.setGtrAsignacionSuelo(this);

		return gtrContratoAsignacion;
	}

	public GtrContratoAsignacion removeGtrContratoAsignacion(GtrContratoAsignacion gtrContratoAsignacion) {
		getGtrContratoAsignacions().remove(gtrContratoAsignacion);
		gtrContratoAsignacion.setGtrAsignacionSuelo(null);

		return gtrContratoAsignacion;
	}

	public List<GtrHistorialSeguimiento> getGtrHistorialSeguimientos() {
		return this.gtrHistorialSeguimientos;
	}

	public void setGtrHistorialSeguimientos(List<GtrHistorialSeguimiento> gtrHistorialSeguimientos) {
		this.gtrHistorialSeguimientos = gtrHistorialSeguimientos;
	}

	public GtrHistorialSeguimiento addGtrHistorialSeguimiento(GtrHistorialSeguimiento gtrHistorialSeguimiento) {
		getGtrHistorialSeguimientos().add(gtrHistorialSeguimiento);
		gtrHistorialSeguimiento.setGtrAsignacionSuelo(this);

		return gtrHistorialSeguimiento;
	}

	public GtrHistorialSeguimiento removeGtrHistorialSeguimiento(GtrHistorialSeguimiento gtrHistorialSeguimiento) {
		getGtrHistorialSeguimientos().remove(gtrHistorialSeguimiento);
		gtrHistorialSeguimiento.setGtrAsignacionSuelo(null);

		return gtrHistorialSeguimiento;
	}

}