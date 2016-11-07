package city.controller.territorio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GtrAsignacionSuelo;
import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GtrContratoAsignacion;
import city.model.dao.entidades.GtrEntregablesContrato;
import city.model.dao.entidades.GenFuncionariosInstitucion;
import city.model.dao.entidades.GtrHistorialSeguimiento;
import city.model.dao.entidades.GenZona;
import city.model.generic.Funciones;
import city.model.generic.Mensaje;
import city.model.manager.ManagerGeneral;
import city.model.manager.ManagerSitios;
import city.model.manager.ManagerTerritorio;

@SessionScoped
@ManagedBean
public class CopyOfAsignacionSueloBean implements Serializable {

	private static final long serialVersionUID = -3198725647396964268L;
	private static String EN_PROGRESO = "P";
	private static String APROBADO = "A";
	private static String SELECCIONAR = "S/N";

	@EJB
	private ManagerTerritorio mngTerritorio;
	
	@EJB
	private ManagerGeneral mngGeneral;
	
	@EJB
	private ManagerSitios msitios;
	
	@Inject
	private SesionBean session;

	@NotEmpty(message = "El campo ID no debe estar vacío.")
	@NotBlank(message = "El campo ID no debe tener solo espacios blancos.")
	private Integer id;

	private String estado;

	private Date fechaIncio;
	private Date fechaFin;

	@NotEmpty(message = "PDF LINK no debe estar vacío.")
	@NotBlank(message = "PDF LINK no debe ser solo espacios blancos.")
	@URL(message = "PDF LINK no es una url válida.")
	private String linkPdf;

	@DecimalMin("0")
	private BigDecimal metros;

	@NotEmpty(message = "El campo ACTIVIDAD no debe estar vacío.")
	@NotBlank(message = "El campo ACTIVIDAD no debe tener solo espacios blancos.")
	private String actividad;

	@NotEmpty(message = "El campo ASIGNACIÓN no debe estar vacío.")
	@NotBlank(message = "El campo ASIGNACIÓN no debe tener solo espacios blancos.")
	private String asignacion;

//	private List<GenAsignacionSuelo> listAsignacionSuelos;
//	private boolean edicion;
//	private List<SelectItem> slctEstados;
//
//	private String observacion;
//
//	private UploadedFile filePdf;
//	private String dirPdf;
//
//	List<SelectItem> l_zona;
//
//	@NotEmpty(message = "ZONA no debe estar vacío.")
//	private String zona;
//
//	List<SelectItem> l_tipo_catalogo;
//
//	@NotEmpty(message = "TIPO no debe estar vacío.")
//	private String SueTipoCatalogo;
//
//	private Date date;
//
//	private Integer sueNumeroanios;
//	
//	private boolean ocultarGuardar;
//	
//	// Campos 2 Fase
//	private String unidadTiempo;
//	
//	@NotEmpty(message = "El campo NOMBRE no debe estar vacío.")
//	@NotBlank(message = "El campo NOMBRE no debe tener solo espacios blancos.")
//	private String nombre;
//	
//	@NotEmpty(message = "El campo DESCRIPCIÓN no debe estar vacío.")
//	@NotBlank(message = "El campo DESCRIPCIÓN no debe tener solo espacios blancos.")
//	private String descripcion;
//	
//	@NotEmpty(message = "El campo REGULACIÓN AMBIENTAL no debe estar vacío.")
//	@NotBlank(message = "El campo REGULACIÓN AMBIENTAL no debe tener solo espacios blancos.")
//	private String regAmbiental;
//	private String inforGestionTerr;
//	
//	
//	private String coordenadaX;
//	private String coordenadaY;
//	
//	@NotEmpty(message = "El campo FUENTE HÍDRICA no debe estar vacío.")
//	@NotBlank(message = "El campo FUENTE HÍDRICA no debe tener solo espacios blancos.")
//	private String fuenteHidrica;
//	@NotEmpty(message = "El campo CONCESIÓN FUENTE HÍDRICA no debe estar vacío.")
//	@NotBlank(message = "El campo CONCESIÓN FUENTE HÍDRICA no debe tener solo espacios blancos.")
//	private String concesionFHidrica;
//	@NotEmpty(message = "El campo RESPONSABLE CONCESIÓN no debe estar vacío.")
//	@NotBlank(message = "El campo RESPONSABLE CONCESIÓN no debe tener solo espacios blancos.")
//	private String responsableConcesion;
//	private boolean ocupado;
//	private String ocupadoPor;
//	private String tipoUso;
//	private String superficieSolicitada;
//	private String superficieAsignada;
//	private String inforActualGT;
//	private String inforConsolidado;
//	private String inforPronJuidico;
//	private String informeConsolidado2;
//	private String solicitudComite;
//	private String actaResolucion;
//	private String estadoProceso;
//	
//	private String direccionResponsable;
//	@NotNull(message = "El campo Responsable no debe estar vacío.")
//	private String dniResponsablePorDir;
//	private String nombreResponsablePorDir;
//	private String busquedaPersona;
//	private List<SelectItem> lstPersonas;
//	private List<SelectItem> lstUnidadTiempo;
//	private List<SelectItem> lstTipoUso;
//	// Contrato
//	private String idContrato;
//	private String tdrContrato;
//	private String pliegoContrato;
//	private String arrendadorCotrato;
//	private String arrendatarioContrato;
//	private BigDecimal periodicidadPagoC;
//	private String unidadTiempoContrato;
//	private BigDecimal precio;
//	private boolean edicionContrato;
//	private List<GenContratoAsignacion> lstContratos;
//	private List<SelectItem> slctEstadosContrato;
//	private GenContratoAsignacion contrato;
//	// Entregables
//	private String documento;
//	private String dniresponsableEntregable;
//	private String nombreResponsableEntregable;
//	private Date fechaMaxEntrega;
//	private Timestamp fechaSubida;
//	private String estadoEntregable;
//	private boolean edicionEntregable;
//	private GenEntregablesContrato entregableC;
//	private GenEntregablesContratoPK pkEntregable;
//	private List<GenEntregablesContrato> lstEntregables;
//	private List<SelectItem> slctEstadosEntregable;
//	private String busquedaResponsableEC;
//
//	// lista de seguimientos
//	List<GenHistorialSeguimiento> l_seguimiento;
//	
//	public CopyOfAsignacionSueloBean() {
//	
//	}
//	
//	@PostConstruct
//	public void init() {
//		session.validarSesion();
//		estado = EN_PROGRESO;
//		slctEstados = new ArrayList<SelectItem>();
//		lstPersonas = new ArrayList<SelectItem>();
//		listAsignacionSuelos = new ArrayList<GenAsignacionSuelo>();
//		l_seguimiento = new ArrayList<GenHistorialSeguimiento>();
//		l_tipo_catalogo = new ArrayList<SelectItem>();
//		l_zona = new ArrayList<SelectItem>();
//		lstUnidadTiempo = new ArrayList<SelectItem>();
//		lstTipoUso = new ArrayList<SelectItem>();
//		metros = new BigDecimal(0);
//		sueNumeroanios = 0;
//		edicion = false;
//		ocultarGuardar=false;
//		fechaIncio = null;
//		fechaFin = null;
//		periodicidadPagoC = BigDecimal.ZERO;
//		cargarZona();
//		cargarAsignacionSuelo();
//		cargarTiposCatalogo();
//		cargarUnidadTiempo();
//		cargarEstados();
//		cargarBusqueda();
//	}
//
//	/**
//	 * @return the l_seguimiento
//	 */
//	public List<GenHistorialSeguimiento> getL_seguimiento() {
//		return l_seguimiento;
//	}
//
//	/**
//	 * @param l_seguimiento
//	 *            the l_seguimiento to set
//	 */
//	public void setL_seguimiento(List<GenHistorialSeguimiento> l_seguimiento) {
//		this.l_seguimiento = l_seguimiento;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getActividad() {
//		return actividad;
//	}
//
//	public void setActividad(String actividad) {
//		this.actividad = actividad;
//	}
//
//	public String getAsignacion() {
//		return asignacion;
//	}
//
//	public void setAsignacion(String asignacion) {
//		this.asignacion = asignacion;
//	}
//
//	public Date getFechaIncio() {
//		return fechaIncio;
//	}
//
//	public void setFechaIncio(Date fechaIncio) {
//		this.fechaIncio = fechaIncio;
//	}
//
//	public Date getFechaFin() {
//		return fechaFin;
//	}
//
//	public void setFechaFin(Date fechaFin) {
//		this.fechaFin = fechaFin;
//	}
//
//	public String getEstado() {
//		return estado;
//	}
//
//	public void setEstado(String estado) {
//		this.estado = estado;
//	}
//
//	public String getLinkPdf() {
//		return linkPdf;
//	}
//
//	public void setLinkPdf(String linkPdf) {
//		this.linkPdf = linkPdf;
//	}
//
//	public BigDecimal getMetros() {
//		return metros;
//	}
//
//	public void setMetros(BigDecimal metros) {
//		this.metros = metros;
//	}
//
//	public List<GenAsignacionSuelo> getListAsignacionSuelos() {
//		return listAsignacionSuelos;
//	}
//
//	public void setListAsignacionSuelos(List<GenAsignacionSuelo> listAsignacionSuelos) {
//		this.listAsignacionSuelos = listAsignacionSuelos;
//	}
//
//	public List<SelectItem> getL_tipo_catalogo() {
//		return l_tipo_catalogo;
//	}
//
//	public void setL_tipo_catalogo(List<SelectItem> l_tipo_catalogo) {
//		this.l_tipo_catalogo = l_tipo_catalogo;
//	}
//
//	public boolean isEdicion() {
//		return edicion;
//	}
//
//	public void setEdicion(boolean edicion) {
//		this.edicion = edicion;
//	}
//
//	public List<SelectItem> getSlctEstados() {
//		return slctEstados;
//	}
//
//	public void setSlctEstados(List<SelectItem> slctEstados) {
//		this.slctEstados = slctEstados;
//	}
//
//	public List<SelectItem> getL_zona() {
//		return l_zona;
//	}
//
//	public void setL_zona(List<SelectItem> l_zona) {
//		this.l_zona = l_zona;
//	}
//
//	public String getObservacion() {
//		return observacion;
//	}
//
//	public void setObservacion(String observacion) {
//		this.observacion = observacion;
//	}
//
//	public String getZona() {
//		return zona;
//	}
//
//	public void setZona(String zona) {
//		this.zona = zona;
//	}
//
//	public String getSueTipoCatalogo() {
//		return SueTipoCatalogo;
//	}
//
//	public void setSueTipoCatalogo(String sueTipoCatalogo) {
//		SueTipoCatalogo = sueTipoCatalogo;
//	}
//	
//	public boolean isOcultarGuardar() {
//		return ocultarGuardar;
//	}
//	
//	public void setOcultarGuardar(boolean ocultarGuardar) {
//		this.ocultarGuardar = ocultarGuardar;
//	}
//
//	private void cargarEstados() {
//		getSlctEstados().add(new SelectItem(EN_PROGRESO, "En progreso"));
//		getSlctEstados().add(new SelectItem(APROBADO, "Aprobado"));
//	}
//
//	public UploadedFile getFilePdf() {
//		return filePdf;
//	}
//
//	public void setFilePdf(UploadedFile filePdf) {
//		this.filePdf = filePdf;
//	}
//
//	public String getDirPdf() {
//		return dirPdf;
//	}
//
//	public void setDirPdf(String dirPdf) {
//		this.dirPdf = dirPdf;
//	}
//
//	public Date getDate() {
//		return date;
//	}
//
//	public Integer getSueNumeroanios() {
//		return sueNumeroanios;
//	}
//
//	public void setSueNumeroanios(Integer sueNumeroanios) {
//		this.sueNumeroanios = sueNumeroanios;
//	}
//
//	//////////
//	
//	public ManagerTerritorio getMngAsignacionSuelo() {
//		return mngTerritorio;
//	}
//
//	public void setMngAsignacionSuelo(ManagerTerritorio mngAsignacionSuelo) {
//		this.mngTerritorio = mngAsignacionSuelo;
//	}
//
//	public String getUnidadTiempo() {
//		return unidadTiempo;
//	}
//
//	public void setUnidadTiempo(String unidadTiempo) {
//		this.unidadTiempo = unidadTiempo;
//	}
//
//	public String getNombre() {
//		return nombre;
//	}
//
//	public void setNombre(String nombre) {
//		this.nombre = nombre;
//	}
//
//	public String getDescripcion() {
//		return descripcion;
//	}
//
//	public void setDescripcion(String descripcion) {
//		this.descripcion = descripcion;
//	}
//
//	public String getRegAmbiental() {
//		return regAmbiental;
//	}
//
//	public void setRegAmbiental(String regAmbiental) {
//		this.regAmbiental = regAmbiental;
//	}
//
//	public String getInforGestionTerr() {
//		return inforGestionTerr;
//	}
//
//	public void setInforGestionTerr(String inforGestionTerr) {
//		this.inforGestionTerr = inforGestionTerr;
//	}
//
//	public String getCoordenadaX() {
//		return coordenadaX;
//	}
//
//	public void setCoordenadaX(String coordenadaX) {
//		this.coordenadaX = coordenadaX;
//	}
//
//	public String getCoordenadaY() {
//		return coordenadaY;
//	}
//
//	public void setCoordenadaY(String coordenadaY) {
//		this.coordenadaY = coordenadaY;
//	}
//
//	public String getFuenteHidrica() {
//		return fuenteHidrica;
//	}
//
//	public void setFuenteHidrica(String fuenteHidrica) {
//		this.fuenteHidrica = fuenteHidrica;
//	}
//
//	public String getConcesionFHidrica() {
//		return concesionFHidrica;
//	}
//
//	public void setConcesionFHidrica(String concesionFHidrica) {
//		this.concesionFHidrica = concesionFHidrica;
//	}
//
//	public String getResponsableConcesion() {
//		return responsableConcesion;
//	}
//
//	public void setResponsableConcesion(String responsableConcesion) {
//		this.responsableConcesion = responsableConcesion;
//	}
//
//	public boolean isOcupado() {
//		return ocupado;
//	}
//
//	public void setOcupado(boolean ocupado) {
//		this.ocupado = ocupado;
//	}
//
//	public String getOcupadoPor() {
//		return ocupadoPor;
//	}
//
//	public void setOcupadoPor(String ocupadoPor) {
//		this.ocupadoPor = ocupadoPor;
//	}
//
//	public String getTipoUso() {
//		return tipoUso;
//	}
//
//	public void setTipoUso(String tipoUso) {
//		this.tipoUso = tipoUso;
//	}
//
//	public String getSuperficieSolicitada() {
//		return superficieSolicitada;
//	}
//
//	public void setSuperficieSolicitada(String superficieSolicitada) {
//		this.superficieSolicitada = superficieSolicitada;
//	}
//
//	public String getSuperficieAsignada() {
//		return superficieAsignada;
//	}
//
//	public void setSuperficieAsignada(String superficieAsignada) {
//		this.superficieAsignada = superficieAsignada;
//	}
//
//	public String getInforActualGT() {
//		return inforActualGT;
//	}
//
//	public void setInforActualGT(String inforActualGT) {
//		this.inforActualGT = inforActualGT;
//	}
//
//	public String getInforConsolidado() {
//		return inforConsolidado;
//	}
//
//	public void setInforConsolidado(String inforConsolidado) {
//		this.inforConsolidado = inforConsolidado;
//	}
//
//	public String getInforPronJuidico() {
//		return inforPronJuidico;
//	}
//
//	public void setInforPronJuidico(String inforPronJuidico) {
//		this.inforPronJuidico = inforPronJuidico;
//	}
//
//	public String getInformeConsolidado2() {
//		return informeConsolidado2;
//	}
//
//	public void setInformeConsolidado2(String informeConsolidado2) {
//		this.informeConsolidado2 = informeConsolidado2;
//	}
//
//	public String getSolicitudComite() {
//		return solicitudComite;
//	}
//
//	public void setSolicitudComite(String solicitudComite) {
//		this.solicitudComite = solicitudComite;
//	}
//
//	public String getActaResolucion() {
//		return actaResolucion;
//	}
//
//	public void setActaResolucion(String actaResolucion) {
//		this.actaResolucion = actaResolucion;
//	}
//
//	public String getEstadoProceso() {
//		return estadoProceso;
//	}
//
//	public void setEstadoProceso(String estadoProceso) {
//		this.estadoProceso = estadoProceso;
//	}
//
//	public String getDireccionResponsable() {
//		return direccionResponsable;
//	}
//
//	public void setDireccionResponsable(String direccionResponsable) {
//		this.direccionResponsable = direccionResponsable;
//	}
//
//	public String getDniResponsablePorDir() {
//		return dniResponsablePorDir;
//	}
//	
//	public void setDniResponsablePorDir(String dniResponsablePorDir) {
//		this.dniResponsablePorDir = dniResponsablePorDir;
//	}
//	
//	public String getNombreResponsablePorDir() {
//		return nombreResponsablePorDir;
//	}
//	
//	public void setNombreResponsablePorDir(String nombreResponsablePorDir) {
//		this.nombreResponsablePorDir = nombreResponsablePorDir;
//	}
//	
//	public String getBusquedaPersona() {
//		return busquedaPersona;
//	}
//	
//	public void setBusquedaPersona(String busquedaPersona) {
//		this.busquedaPersona = busquedaPersona;
//	}
//	
//	public List<SelectItem> getLstPersonas() {
//		return lstPersonas;
//	}
//	
//	public void setLstPersonas(List<SelectItem> lstPersonas) {
//		this.lstPersonas = lstPersonas;
//	}
//	
//	public List<SelectItem> getLstUnidadTiempo() {
//		return lstUnidadTiempo;
//	}
//	
//	public void setLstUnidadTiempo(List<SelectItem> lstUnidadTiempo) {
//		this.lstUnidadTiempo = lstUnidadTiempo;
//	}
//	
//	public List<SelectItem> getLstTipoUso() {
//		return lstTipoUso;
//	}
//	
//	public void setLstTipoUso(List<SelectItem> lstTipoUso) {
//		this.lstTipoUso = lstTipoUso;
//	}
//	
//	public List<GenContratoAsignacion> getLstContratos() {
//		return lstContratos;
//	}
//	
//	public void setLstContratos(List<GenContratoAsignacion> lstContratos) {
//		this.lstContratos = lstContratos;
//	}
//	
//	//Contrato 
//	
//	public String getIdContrato() {
//		return idContrato;
//	}
//
//	public void setIdContrato(String idContrato) {
//		this.idContrato = idContrato;
//	}
//
//	public String getTdrContrato() {
//		return tdrContrato;
//	}
//
//	public void setTdrContrato(String tdrContrato) {
//		this.tdrContrato = tdrContrato;
//	}
//
//	public String getPliegoContrato() {
//		return pliegoContrato;
//	}
//
//	public void setPliegoContrato(String pliegoContrato) {
//		this.pliegoContrato = pliegoContrato;
//	}
//
//	public String getArrendadorCotrato() {
//		return arrendadorCotrato;
//	}
//
//	public void setArrendadorCotrato(String arrendadorCotrato) {
//		this.arrendadorCotrato = arrendadorCotrato;
//	}
//
//	public String getArrendatarioContrato() {
//		return arrendatarioContrato;
//	}
//
//	public void setArrendatarioContrato(String arrendatarioContrato) {
//		this.arrendatarioContrato = arrendatarioContrato;
//	}
//
//	public BigDecimal getPeriodicidadPagoC() {
//		return periodicidadPagoC;
//	}
//
//	public void setPeriodicidadPagoC(BigDecimal periodicidadPagoC) {
//		this.periodicidadPagoC = periodicidadPagoC;
//	}
//
//	public String getUnidadTiempoContrato() {
//		return unidadTiempoContrato;
//	}
//
//	public void setUnidadTiempoContrato(String unidadTiempoContrato) {
//		this.unidadTiempoContrato = unidadTiempoContrato;
//	}
//
//	public BigDecimal getPrecio() {
//		return precio;
//	}
//
//	public void setPrecio(BigDecimal precio) {
//		this.precio = precio;
//	}
//	
//	public boolean isEdicionContrato() {
//		return edicionContrato;
//	}
//
//	public void setEdicionContrato(boolean edicionContrato) {
//		this.edicionContrato = edicionContrato;
//	}
//
//	public String getDocumento() {
//		return documento;
//	}
//
//	public void setDocumento(String documento) {
//		this.documento = documento;
//	}
//
//	public String getDniresponsableEntregable() {
//		return dniresponsableEntregable;
//	}
//
//	public void setDniresponsableEntregable(String dniresponsableEntregable) {
//		this.dniresponsableEntregable = dniresponsableEntregable;
//	}
//
//	public String getNombreResponsableEntregable() {
//		return nombreResponsableEntregable;
//	}
//
//	public void setNombreResponsableEntregable(String nombreResponsableEntregable) {
//		this.nombreResponsableEntregable = nombreResponsableEntregable;
//	}
//
//	public Date getFechaMaxEntrega() {
//		return fechaMaxEntrega;
//	}
//
//	public void setFechaMaxEntrega(Date fechaMaxEntrega) {
//		this.fechaMaxEntrega = fechaMaxEntrega;
//	}
//
//	public Timestamp getFechaSubida() {
//		return fechaSubida;
//	}
//
//	public void setFechaSubida(Timestamp fechaSubida) {
//		this.fechaSubida = fechaSubida;
//	}
//
//	public String getEstadoEntregable() {
//		return estadoEntregable;
//	}
//
//	public void setEstadoEntregable(String estadoEntregable) {
//		this.estadoEntregable = estadoEntregable;
//	}
//
//	public boolean isEdicionEntregable() {
//		return edicionEntregable;
//	}
//
//	public void setEdicionEntregable(boolean edicionEntregable) {
//		this.edicionEntregable = edicionEntregable;
//	}
//
//	public List<SelectItem> getSlctEstadosContrato() {
//		return slctEstadosContrato;
//	}
//
//	public void setSlctEstadosContrato(List<SelectItem> slctEstadosContrato) {
//		this.slctEstadosContrato = slctEstadosContrato;
//	}
//
//	public GenContratoAsignacion getContrato() {
//		return contrato;
//	}
//
//	public void setContrato(GenContratoAsignacion contrato) {
//		this.contrato = contrato;
//	}
//
//	public GenEntregablesContrato getEntregableC() {
//		return entregableC;
//	}
//
//	public void setEntregableC(GenEntregablesContrato entregableC) {
//		this.entregableC = entregableC;
//	}
//
//	public GenEntregablesContratoPK getPkEntregable() {
//		return pkEntregable;
//	}
//
//	public void setPkEntregable(GenEntregablesContratoPK pkEntregable) {
//		this.pkEntregable = pkEntregable;
//	}
//
//	public List<GenEntregablesContrato> getLstEntregables() {
//		return lstEntregables;
//	}
//
//	public void setLstEntregables(List<GenEntregablesContrato> lstEntregables) {
//		this.lstEntregables = lstEntregables;
//	}
//
//	public List<SelectItem> getSlctEstadosEntregable() {
//		return slctEstadosEntregable;
//	}
//
//	public void setSlctEstadosEntregable(List<SelectItem> slctEstadosEntregable) {
//		this.slctEstadosEntregable = slctEstadosEntregable;
//	}
//	
//	public String getBusquedaResponsableEC() {
//		return busquedaResponsableEC;
//	}
//	
//	public void setBusquedaResponsableEC(String busquedaResponsableEC) {
//		this.busquedaResponsableEC = busquedaResponsableEC;
//	}
//
//	public String nuevoSuelo() {
//		setOcultarGuardar(false);
//		limpiarDatos();
//
//		date = new Date();
//		return "neAsignacionSuelo?faces-redirect=true";
//	}
//
//	public String cargarEditarAsignacionSuelo(GenAsignacionSuelo asignacionSuelo) {
//		setId(asignacionSuelo.getSueId());
//		setActividad(asignacionSuelo.getSueActividad());
//		setEstado(asignacionSuelo.getSueEstado());
//		setMetros(asignacionSuelo.getSueMetros());
//		setSueTipoCatalogo(asignacionSuelo.getSueTipo());
//		setAsignacion(asignacionSuelo.getSueAsignacion());
//		setSueNumeroanios(asignacionSuelo.getSueNumeroAnios());
//		setObservacion(asignacionSuelo.getSueObservacion());
//		setFechaIncio(asignacionSuelo.getSueFechaInicio());
//		setFechaFin(asignacionSuelo.getSueFechaFin());
//		setZona(asignacionSuelo.getGenZona().getZonId());
//		setEdicion(true);
//		ocultarGuardar = false;
//		cargarLstContratos();
//		
//		return "neAsignacionSuelo?faces-redirect=true";
//	}
//
//	public void guardarEditarAsignacionSuelos() {
//		try {
//			if (validarCampos()) {
//				if (isEdicion()) {
//					GenAsignacionSuelo a = mngTerritorio.findAsignacionSueloById(getId());
//					a.setSueActividad(Funciones.quitarEspacios(getActividad()));
//					a.setGenZona(mngTerritorio.findZonaById(zona));
//					a.setSueEstado(getEstado());
//					a.setSueTipo(getSueTipoCatalogo());
//					a.setSueMetros(getMetros());
//					a.setSueFechaInicio(fechaIncio);
//					a.setSueNumeroAnios(sueNumeroanios);
//					a.setSueFechaFin(fechaFin);
//					a.setSueAsignacion(Funciones.quitarEspacios(getAsignacion()));
//					a.setSueObservacion(Funciones.quitarEspacios(getObservacion()));
//					if (getDirPdf() != null || getDirPdf() != "") {
//						a.setSueArchivo(getDirPdf());
//					} else {
//						a.setSueArchivo(mngTerritorio.findAsignacionSueloById(getId()).getSueArchivo());
//					}
//					mngTerritorio.modicarAsignacionSuelo(a);
//					Mensaje.crearMensajeINFO("Asignación de Suelo actualizada correctamente.");
//				} else {
//					GenAsignacionSuelo z = new GenAsignacionSuelo();
//					z.setSueId(mngTerritorio.asignacionSueloId());
//					z.setSueActividad(Funciones.quitarEspacios(getActividad()));
//					z.setGenZona(mngTerritorio.findZonaById(zona));
//					z.setSueEstado(getEstado());
//					z.setSueTipo(getSueTipoCatalogo());
//					z.setSueMetros(getMetros());
//					z.setSueFechaInicio(fechaIncio);
//					z.setSueNumeroAnios(sueNumeroanios);
//					z.setSueFechaFin(fechaFin);
//					z.setSueAsignacion(Funciones.quitarEspacios(getAsignacion()));
//					z.setSueObservacion(Funciones.quitarEspacios(getObservacion()));
//					mngTerritorio.insertarAsignacionSuelo(z);
//					setEdicion(false);
//					setOcultarGuardar(true);
//					Mensaje.crearMensajeINFO("Asignación de Suelo ingresada correctamente.");
//				}
//			}
//		} catch (Exception e) {
//			Mensaje.crearMensajeERROR("Error al almacenar suelo: " + e.getMessage());
//			System.out.println("Error al almacenar suelo: ");
//			e.printStackTrace();
//		}
//	}
//
//	public boolean validarCampos() {
//		if (getZona().equals(SELECCIONAR)) {
//			Mensaje.crearMensajeERROR("Campo zona requerido");
//			return false;
//		} else if (getSueTipoCatalogo().equals(SELECCIONAR)) {
//			Mensaje.crearMensajeERROR("Campo tipo requerido");
//			return false;
//		} else if (getUnidadTiempo().equals(SELECCIONAR)) {
//			Mensaje.crearMensajeERROR("Seleccione la unidad de tiempo de la Temporalidad. ");
//			return false;
//		} else {
//			if ((edicion == true && getEstado() == null)
//					|| (edicion == true && getEstado().equals(SELECCIONAR))) {
//				Mensaje.crearMensajeERROR("Campo estado requerido");
//				return false;
//			}
//			return true;
//		}
//	}
//
//	public String cancelar() {
//		limpiarDatos();
//		cargarAsignacionSuelo();
//		return "asignacionSuelos?faces-redirect=true";
//	}
//
//	private void cargarAsignacionSuelo() {
//		getListAsignacionSuelos().clear();
//		getListAsignacionSuelos().addAll(mngTerritorio.findAllAsignacionSuelo());
//	}
//
//	private void limpiarDatos() {
//		setId(null);
//		setActividad("");
//		setEstado(EN_PROGRESO);
//		setMetros(new BigDecimal(0));
//		setAsignacion("");
//		setSueNumeroanios(0);
//		setZona("-1");
//		setSueTipoCatalogo("-1");
//		setSueNumeroanios(0);
//		setObservacion("");
//		setFechaIncio(null);
//		setOcultarGuardar(false);
//		setFechaFin(null);
//		setEdicion(false);
//	}
//
//	public void cargaPDF(FileUploadEvent event) throws IOException {
//		filePdf = event.getFile();
//		InputStream inputStream = null;
//		OutputStream outputStream = null;
//
//		if (filePdf != null) {
//			try {
//				String carpeta = mngTerritorio.findParametroByID("direccion_pdf") + "/";
//				setDirPdf("PDF_S_" + getId() + extensionArchivo(filePdf.getFileName()));
//				System.out.println("PAD------> " + carpeta);
//				System.out.println("name------> " + getDirPdf());
//				outputStream = new FileOutputStream(new File(carpeta + File.separatorChar + getDirPdf()));
//				inputStream = filePdf.getInputstream();
//
//				int read = 0;
//				byte[] bytes = new byte[1024];
//
//				while ((read = inputStream.read(bytes)) != -1) {
//					outputStream.write(bytes, 0, read);
//				}
//				Mensaje.crearMensajeINFO("Carga del archivo Correcta");
//				editarAsignacionSuelo();
//			} catch (Exception e) {
//				Mensaje.crearMensajeERROR("No se pudo cargar el archivo");
//				e.printStackTrace();
//			} finally {
//				if (inputStream != null) {
//					inputStream.close();
//				}
//				if (outputStream != null) {
//					outputStream.close();
//				}
//			}
//		} else {
//			Mensaje.crearMensajeWARN("No se pudo cargar el archivo");
//		}
//	}
//
//	public String extensionArchivo(String nombreArchivo) {
//		return nombreArchivo.substring(nombreArchivo.lastIndexOf('.'));
//	}
//
//	public void editarAsignacionSuelo() {
//		try {
//			if (!validarCampos()) {
//				GenAsignacionSuelo z = new GenAsignacionSuelo();
//				z.setSueId(getId());
//				z.setSueActividad(Funciones.quitarEspacios(getActividad()));
//				z.setSueMetros(getMetros());
//				z.setGenZona(mngTerritorio.findZonaById(zona));
//				z.setSueFechaInicio(fechaIncio);
//				z.setSueNumeroAnios(sueNumeroanios);
//				z.setSueTipo(getSueTipoCatalogo());
//				z.setSueFechaFin(fechaFin);
//				z.setSueAsignacion(Funciones.quitarEspacios(getAsignacion()));
//				z.setSueObservacion(Funciones.quitarEspacios(getObservacion()));
//				z.setSueEstado(getEstado());
//				if (getDirPdf() != null || getDirPdf() != "") {
//					z.setSueArchivo(getDirPdf());
//				} else {
//					z.setSueArchivo(mngTerritorio.findAsignacionSueloById(getId()).getSueArchivo());
//				}
//				mngTerritorio.modicarAsignacionSuelo(z);
//			} else {
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void descargarPDF(GenAsignacionSuelo asignacionSuelo) {
//		try {
//			if (asignacionSuelo.getSueArchivo() == null || asignacionSuelo.getSueArchivo().isEmpty()) {
//				Mensaje.crearMensajeERROR("La asignación de suelo no cuenta con un archivo asignado.");
//			} else {
//				String contextPath = mngTerritorio.findParametroByID("direccion_pdf") + File.separatorChar
//						+ asignacionSuelo.getSueArchivo() + "";
//				Funciones.descargarPDF(contextPath);
//			}
//		} catch (Exception e) {
//			Mensaje.crearMensajeERROR("Error: " + e.getMessage());
//			e.printStackTrace();
//		}
//	}
//
//	public void descargarPDF(Integer idasignacionSuelo) {
//		try {
//			GenAsignacionSuelo asignacionSuelo = mngTerritorio.findAsignacionSueloById(idasignacionSuelo);
//			if (asignacionSuelo.getSueArchivo() == null || asignacionSuelo.getSueArchivo().isEmpty()) {
//				Mensaje.crearMensajeERROR("La asignación de suelo no cuenta con un archivo asignado.");
//			} else {
//				String contextPath = mngTerritorio.findParametroByID("direccion_pdf") + File.separatorChar
//						+ asignacionSuelo.getSueArchivo() + "";
//				Funciones.descargarPDF(contextPath);
//			}
//		} catch (Exception e) {
//			Mensaje.crearMensajeERROR("Error: " + e.getMessage());
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Lista de Zona
//	 */
//	public void cargarZona() {
//		getL_zona().clear();
//		for (GenZona i : mngTerritorio.findAllZonasA()) {
//			getL_zona().add(new SelectItem(i.getZonId(), i.getZonNombre()));
//		}
//	}
//
//	/**
//	 * Lista de TiposCatalogo
//	 */
//	public void cargarTiposCatalogo() {
//		getL_tipo_catalogo().clear();
//		List<GenCatalogoItemsDet> completo = mngTerritorio.AllofItems("cat_tipo_asignacionsuelo");
//		for (GenCatalogoItemsDet i : completo) {
//			getL_tipo_catalogo().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
//		}
//	}
//	
//	public void cargarUnidadTiempo() {
//		getLstUnidadTiempo().clear();
//		getLstUnidadTiempo().add( new SelectItem(SELECCIONAR, " --Seleccione-- "));
//		for (GenCatalogoItemsDet i : mngTerritorio.AllofItems("cat_unidad_tiempo")) {
//			getLstUnidadTiempo().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
//		}
//	}
//	
//	public void cargarTipoUso() {
//		getLstUnidadTiempo().clear();
//		getLstUnidadTiempo().add( new SelectItem(SELECCIONAR, " --Seleccione-- "));
//		for (GenCatalogoItemsDet i : mngTerritorio.AllofItems("cat_tipo_uso")) {
//			getLstUnidadTiempo().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
//		}
//	}
//
//	/**
//	 * Validar campos
//	 * 
//	 * @param item
//	 * @return
//	 */
//	public String validarItemCatalogo(String item) {
//		String respuesta = "";
//		try {
//			respuesta = mngTerritorio.catalogoItem(item);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			Mensaje.crearMensajeERROR("Error al validar Item. " + e.getMessage());
//		}
//		return respuesta;
//	}
//
//	public String fechasaanios(Date fechaInicio, Date fechaFin) {
//		String aniosEntreFechas = mngTerritorio.fechasaAnios(getFechaIncio(), getFechaFin(), getId());
//		return aniosEntreFechas;
//	}
//
//	public void tablaSeguimiento(GenAsignacionSuelo asignacion) {
//		getL_seguimiento().clear();
//		setL_seguimiento(mngTerritorio.listaSeguimientoFiltrado(asignacion.getSueId()));
//		RequestContext.getCurrentInstance().execute("PF('dlgSeg').show()");
//	}
//	
//	public void descargarDocumento(GenHistorialSeguimiento seguimiento) {
//		  try {
//		   if (seguimiento.getHseAdjuntoDoc() == null
//		     || seguimiento.getHseAdjuntoDoc().isEmpty()) {
//		    Mensaje.crearMensajeERROR("No existe un archivo asignado.");
//		   } else {
//		    String contextPath = mngTerritorio
//		      .findParametroByID("direccion_ad_doc") + File.separatorChar 
//		      + seguimiento.getHseAdjuntoDoc() + "";
//		    Funciones.descargarPDF(contextPath);
//		   }
//		  } catch (Exception e) {
//		   Mensaje.crearMensajeERROR("Error: "+e.getMessage());
//		   e.printStackTrace();
//		  }
//		 }
//		 
//		 public void descargarFoto(GenHistorialSeguimiento seguimiento) {
//		  try {
//		   if (seguimiento.getHseAdjuntoFot() == null
//		     || seguimiento.getHseAdjuntoFot().isEmpty()) {
//		    Mensaje.crearMensajeERROR("No existe un archivo asignado.");
//		   } else {
//		    String contextPath = mngTerritorio
//		      .findParametroByID("direccion_ad_foto") + File.separatorChar
//		      + seguimiento.getHseAdjuntoFot() + "";
//		    Funciones.descargarPDF(contextPath);
//		   }
//		  } catch (Exception e) {
//		   Mensaje.crearMensajeERROR("Error: "+e.getMessage());
//		   e.printStackTrace();
//		  }
//		 }
//	
//	public String cambiarNombre(String param){
//		if(param.equals(EN_PROGRESO)){
//			return "En progreso";
//		}else{
//			return "Actualizado";
//		}
//		
//	}
//	
//	//// Fase 2
//	
//	public String nombreBoton() {
//		if (isEdicionContrato() || isEdicionEntregable()) {
//			return "Actualizar";
//		} else {
//			return "Insertar";
//		}
//	}
//	
//	public void cargarBusqueda() {
//		try {
//			getLstPersonas().clear();
//			getLstPersonas().add(new SelectItem(SELECCIONAR, " --Seleccione-- "));
//			List<GenFuncionariosInstitucion> list = mngGeneral.findAllfuncionarios();
//			for (GenFuncionariosInstitucion i : list) {
//				getLstPersonas().add(new SelectItem(i.getGenPersona().getPerDni(), i.getGenPersona().getPerDni() + " | "
//						+ i.getGenPersona().getPerNombres() + " " + i.getGenPersona().getPerApellidos()));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * Método para buscar un funcionario
//	 */
//	public void buscarPersona() {
//		if (getBusquedaPersona() == null || getBusquedaPersona().isEmpty()) {
//			Mensaje.crearMensajeWARN("Debe ingresar el dato para realizar la búsqueda.");
//			setBusquedaPersona("");
//			setDniResponsablePorDir("");
//			setNombreResponsablePorDir("");
//			setDireccionResponsable("");
//		} else {
//			try {
//				GenFuncionariosInstitucion f = mngGeneral.findFuncionarioXDni(getBusquedaPersona());
//				if (f == null) {
//					Mensaje.crearMensajeWARN("Persona no encontrada");
//					setBusquedaPersona("");
//					setDniResponsablePorDir("");
//					setNombreResponsablePorDir("");
//					setDireccionResponsable("");
//				} else {
//					setBusquedaPersona("");
//					setDniResponsablePorDir(f.getGenPersona().getPerDni());
//					setNombreResponsablePorDir(f.getGenPersona().getPerNombres() + " " + f.getGenPersona().getPerApellidos());
//					setDireccionResponsable(f.getFunDireccion());
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	//Contrato 
//	public void validar(){
//		
//	}
//	
//	public void mostrarDlgContrato() {
//		RequestContext.getCurrentInstance().execute("PF('conDlg').show();");
//	}
//	
//	public void cargarContrato(GenContratoAsignacion contrato){
//		setEdicionContrato(true);
//		setIdContrato(contrato.getCasId());
//		setTdrContrato(contrato.getCasTdr());
//		setPliegoContrato(contrato.getCasPliego());
//		setFechaIncio(contrato.getCasFechaInicio());
//		setFechaFin(contrato.getCasFechaFin());
//		setArrendadorCotrato(contrato.getCasArrendador());
//		setArrendatarioContrato(contrato.getCasArrendatario());
//		setPeriodicidadPagoC(contrato.getCasPeriodicidadPago());
//		setUnidadTiempo(contrato.getCasUnidadTiempo());
//		setPrecio(contrato.getCasPrecio());
//		
//		RequestContext.getCurrentInstance().execute("PF('conDlg').show();");
//	}
//	
//	public void guardarEditarContrato(){
//		
//	}
//	
//	public void cancelarContrato(){
//		limpiarCamposContrato();
//		
//		RequestContext.getCurrentInstance().execute("PF('conDlg').hide();");
//	}
//	
//	public void limpiarCamposContrato(){
//		setIdContrato(""); setTdrContrato(""); setPliegoContrato(""); 
//		setFechaIncio(new Date()); setFechaFin(new Date()); 
//		setArrendadorCotrato(""); setArrendatarioContrato("");
//		setPeriodicidadPagoC(BigDecimal.ZERO); setUnidadTiempoContrato(SELECCIONAR);
//		setPrecio(BigDecimal.ZERO); setEdicionContrato(false);
//	}
//	
//	private void cargarLstContratos(){
//		setLstContratos(mngTerritorio.findAllContratosPorAsignacion(getId()));
//	}
//	
//	// Entregables
//	
//	public void mostrarDlgEntregables() {
//		RequestContext.getCurrentInstance().execute("PF('entDlg').show();");
//	}
//	
//	public void buscarResponsableEnt() {
//		if (getBusquedaResponsableEC() == null || getBusquedaResponsableEC().isEmpty()) {
//			Mensaje.crearMensajeWARN("Debe ingresar el dato para realizar la búsqueda.");
//			setBusquedaResponsableEC("");
//			setDniresponsableEntregable("");
//			setNombreResponsableEntregable("");
//		} else {
//			try {
//				GenFuncionariosInstitucion f = mngGeneral.findFuncionarioXDni(getBusquedaResponsableEC());
//				if (f == null) {
//					Mensaje.crearMensajeWARN("Persona no encontrada");
//					setBusquedaResponsableEC("");
//					setDniresponsableEntregable("");
//					setNombreResponsableEntregable("");
//				} else {
//					setBusquedaResponsableEC("");
//					setDniresponsableEntregable(f.getGenPersona().getPerDni());
//					setNombreResponsableEntregable(f.getGenPersona().getPerNombres() + " " + f.getGenPersona().getPerApellidos());
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	public void cargarEntregables(GenEntregablesContrato entregable){
//		setEdicionEntregable(true);
//		setPkEntregable(entregable.getId());
//		setDocumento(entregable.getId().getEcoDocumento());
//		setDniresponsableEntregable(entregable.getEcoResponsable());
//		setFechaMaxEntrega(entregable.getEcoFechaMaxEntrega());
//		setFechaSubida(entregable.getEcoFechaSubida());
//		setEstadoEntregable(entregable.getEcoEstado());
//		setNombreResponsableEntregable(buscarNombre(entregable.getEcoResponsable()));
//		
//		RequestContext.getCurrentInstance().execute("PF('conDlg').show();");
//	}
//	
//	public String buscarNombre(String cedula){
//		try {
//			if(cedula != null){
//			String nombre = mngGeneral.findFuncionarioXDni(cedula).getGenPersona().getPerNombres()
//					+" "+mngGeneral.findFuncionarioXDni(cedula).getGenPersona().getPerApellidos();
//			return nombre;
//			}else
//				return "";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "";
//		}
//	}
//	
//	public void guardarEditarEntregable(){
//		
//	}
//	
//	public void cancelarEntregable(){
//		limpiarCamposEntregable();
//		
//		RequestContext.getCurrentInstance().execute("PF('entDlg').hide();");
//	}
//	
//	public void limpiarCamposEntregable(){
//		setEdicionEntregable(false); setPkEntregable(new GenEntregablesContratoPK());
//		setDocumento(""); setDniresponsableEntregable(""); setNombreResponsableEntregable("");
//		setFechaMaxEntrega(new Date()); setFechaSubida(new Timestamp(new Date().getTime()));
//		setEstadoEntregable(SELECCIONAR);
//	}
//	
//	private void cargarLstEntregables(){
//		setLstEntregables(mngTerritorio.findAllEntregablesPorContrato(getIdContrato()));
//	}
}
