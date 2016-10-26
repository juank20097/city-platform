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
import city.model.dao.entidades.GenAsignacionSuelo;
import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenContratoAsignacion;
import city.model.dao.entidades.GenEntregablesContrato;
import city.model.dao.entidades.GenEntregablesContratoPK;
import city.model.dao.entidades.GenFuncionariosInstitucion;
import city.model.dao.entidades.GenHistorialSeguimiento;
import city.model.dao.entidades.GenZona;
import city.model.generic.Funciones;
import city.model.generic.Mensaje;
import city.model.manager.ManagerGeneral;
import city.model.manager.ManagerSitios;
import city.model.manager.ManagerTerritorio;

@SessionScoped
@ManagedBean
public class AsignacionSueloBean implements Serializable {

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

	private List<GenAsignacionSuelo> listAsignacionSuelos;
	private boolean edicion;
	private List<SelectItem> slctEstados;

	private String observacion;

	private UploadedFile filePdf;

	List<SelectItem> l_zona;

	private String zona;

	List<SelectItem> l_tipo_catalogo;

	private String tipoCatalogo;

	private Date date;

	private Integer sueNumeroanios;
	
	private boolean ocultarGuardar;
	
	// Campos 2 Fase
	private String unidadTiempo;
	
	@NotEmpty(message = "El campo NOMBRE no debe estar vacío.")
	@NotBlank(message = "El campo NOMBRE no debe tener solo espacios blancos.")
	private String nombre;
	
	@NotEmpty(message = "El campo DESCRIPCIÓN no debe estar vacío.")
	@NotBlank(message = "El campo DESCRIPCIÓN no debe tener solo espacios blancos.")
	private String descripcion;
	
	@NotEmpty(message = "El campo REGULACIÓN AMBIENTAL no debe estar vacío.")
	@NotBlank(message = "El campo REGULACIÓN AMBIENTAL no debe tener solo espacios blancos.")
	private String regAmbiental;
	private String inforGestionTerr;
	private BigDecimal coordenadaX;
	private BigDecimal coordenadaY;
	@NotEmpty(message = "El campo FUENTE HÍDRICA no debe estar vacío.")
	@NotBlank(message = "El campo FUENTE HÍDRICA no debe tener solo espacios blancos.")
	private String fuenteHidrica;
	@NotEmpty(message = "El campo CONCESIÓN FUENTE HÍDRICA no debe estar vacío.")
	@NotBlank(message = "El campo CONCESIÓN FUENTE HÍDRICA no debe tener solo espacios blancos.")
	private String concesionFHidrica;
	@NotEmpty(message = "El campo RESPONSABLE CONCESIÓN no debe estar vacío.")
	@NotBlank(message = "El campo RESPONSABLE CONCESIÓN no debe tener solo espacios blancos.")
	private String responsableConcesion;
	private boolean ocupado;
	private String ocupadoPor;
	private String tipoUso;
	private BigDecimal superficieSolicitada;
	private BigDecimal superficieAsignada;
	private String inforActualGT;
	private String inforConsolidado;
	private String inforPronJuidico;
	private String inforConsolidado2;
	private String solicitudComite;
	private String actaResolucion;
	private String estadoProceso;
	private String direccionResponsable;
	@NotNull(message = "El campo Responsable no debe estar vacío.")
	private String dniResponsablePorDir;
	private String nombreResponsablePorDir;
	private String busquedaPersona;
	private List<SelectItem> lstPersonas;
	private List<SelectItem> lstUnidadTiempo;
	private List<SelectItem> lstTipoUso;
	private String dirPdf;
	private GenAsignacionSuelo asignacionSuelo;
	// Contrato
	private String idContrato;
	private String tdrContrato;
	private String pliegoContrato;
	private String arrendadorCotrato;
	private String arrendatarioContrato;
	private BigDecimal periodicidadPagoC;
	private String unidadTiempoContrato;
	private BigDecimal precio;
	private boolean edicionContrato;
	private List<GenContratoAsignacion> lstContratos;
	private List<SelectItem> slctEstadosContrato;
	private GenContratoAsignacion contrato;
	// Entregables
	private String documento;
	private String dniresponsableEntregable;
	private String nombreResponsableEntregable;
	private Date fechaMaxEntrega;
	private Timestamp fechaSubida;
	private String estadoEntregable;
	private boolean edicionEntregable;
	private GenEntregablesContrato entregableC;
	private GenEntregablesContratoPK pkEntregable;
	private List<GenEntregablesContrato> lstEntregables;
	private List<SelectItem> slctEstadosEntregable;
	private String busquedaResponsableEC;

	// lista de seguimientos
	List<GenHistorialSeguimiento> l_seguimiento;
	
	public AsignacionSueloBean() {
	
	}
	
	@PostConstruct
	public void init() {
		session.validarSesion();
		estado = EN_PROGRESO;
		slctEstados = new ArrayList<SelectItem>();
		lstPersonas = new ArrayList<SelectItem>();
		listAsignacionSuelos = new ArrayList<GenAsignacionSuelo>();
		l_seguimiento = new ArrayList<GenHistorialSeguimiento>();
		l_tipo_catalogo = new ArrayList<SelectItem>();
		l_zona = new ArrayList<SelectItem>();
		lstUnidadTiempo = new ArrayList<SelectItem>();
		lstTipoUso = new ArrayList<SelectItem>();
		metros = new BigDecimal(0);
		sueNumeroanios = 0;
		edicion = false;
		ocultarGuardar=false;
		fechaIncio = null;
		fechaFin = null;
		periodicidadPagoC = BigDecimal.ZERO;
		zona = SELECCIONAR;
		tipoUso = SELECCIONAR;
		tipoCatalogo  = SELECCIONAR;
		unidadTiempo = SELECCIONAR;
		System.out.println(tipoCatalogo+"<-----------------");
		cargarZona();
		cargarAsignacionSuelo();
		cargarTiposCatalogo();
		cargarUnidadTiempo();
		cargarTipoUso();
		cargarEstados();
		cargarBusqueda();
	}

	/**
	 * @return the l_seguimiento
	 */
	public List<GenHistorialSeguimiento> getL_seguimiento() {
		return l_seguimiento;
	}

	/**
	 * @param l_seguimiento
	 *            the l_seguimiento to set
	 */
	public void setL_seguimiento(List<GenHistorialSeguimiento> l_seguimiento) {
		this.l_seguimiento = l_seguimiento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(String asignacion) {
		this.asignacion = asignacion;
	}

	public Date getFechaIncio() {
		return fechaIncio;
	}

	public void setFechaIncio(Date fechaIncio) {
		this.fechaIncio = fechaIncio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getLinkPdf() {
		return linkPdf;
	}

	public void setLinkPdf(String linkPdf) {
		this.linkPdf = linkPdf;
	}

	public BigDecimal getMetros() {
		return metros;
	}

	public void setMetros(BigDecimal metros) {
		this.metros = metros;
	}

	public List<GenAsignacionSuelo> getListAsignacionSuelos() {
		return listAsignacionSuelos;
	}

	public void setListAsignacionSuelos(List<GenAsignacionSuelo> listAsignacionSuelos) {
		this.listAsignacionSuelos = listAsignacionSuelos;
	}

	public List<SelectItem> getL_tipo_catalogo() {
		return l_tipo_catalogo;
	}

	public void setL_tipo_catalogo(List<SelectItem> l_tipo_catalogo) {
		this.l_tipo_catalogo = l_tipo_catalogo;
	}

	public boolean isEdicion() {
		return edicion;
	}

	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	public List<SelectItem> getSlctEstados() {
		return slctEstados;
	}

	public void setSlctEstados(List<SelectItem> slctEstados) {
		this.slctEstados = slctEstados;
	}

	public List<SelectItem> getL_zona() {
		return l_zona;
	}

	public void setL_zona(List<SelectItem> l_zona) {
		this.l_zona = l_zona;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getTipoCatalogo() {
		return tipoCatalogo;
	}
	
	public void setTipoCatalogo(String tipoCatalogo) {
		this.tipoCatalogo = tipoCatalogo;
	}
	
	public boolean isOcultarGuardar() {
		return ocultarGuardar;
	}
	
	public void setOcultarGuardar(boolean ocultarGuardar) {
		this.ocultarGuardar = ocultarGuardar;
	}

	private void cargarEstados() {
		getSlctEstados().add(new SelectItem(EN_PROGRESO, "En progreso"));
		getSlctEstados().add(new SelectItem(APROBADO, "Aprobado"));
	}

	public UploadedFile getFilePdf() {
		return filePdf;
	}

	public void setFilePdf(UploadedFile filePdf) {
		this.filePdf = filePdf;
	}


	public Date getDate() {
		return date;
	}

	public Integer getSueNumeroanios() {
		return sueNumeroanios;
	}

	public void setSueNumeroanios(Integer sueNumeroanios) {
		this.sueNumeroanios = sueNumeroanios;
	}

	//////////
	
	public ManagerTerritorio getMngAsignacionSuelo() {
		return mngTerritorio;
	}

	public void setMngAsignacionSuelo(ManagerTerritorio mngAsignacionSuelo) {
		this.mngTerritorio = mngAsignacionSuelo;
	}

	public String getUnidadTiempo() {
		return unidadTiempo;
	}

	public void setUnidadTiempo(String unidadTiempo) {
		this.unidadTiempo = unidadTiempo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRegAmbiental() {
		return regAmbiental;
	}

	public void setRegAmbiental(String regAmbiental) {
		this.regAmbiental = regAmbiental;
	}

	public String getInforGestionTerr() {
		return inforGestionTerr;
	}

	public void setInforGestionTerr(String inforGestionTerr) {
		this.inforGestionTerr = inforGestionTerr;
	}

	public BigDecimal getCoordenadaX() {
		return coordenadaX;
	}

	public void setCoordenadaX(BigDecimal coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public BigDecimal getCoordenadaY() {
		return coordenadaY;
	}

	public void setCoordenadaY(BigDecimal coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	public String getFuenteHidrica() {
		return fuenteHidrica;
	}

	public void setFuenteHidrica(String fuenteHidrica) {
		this.fuenteHidrica = fuenteHidrica;
	}

	public String getConcesionFHidrica() {
		return concesionFHidrica;
	}

	public void setConcesionFHidrica(String concesionFHidrica) {
		this.concesionFHidrica = concesionFHidrica;
	}

	public String getResponsableConcesion() {
		return responsableConcesion;
	}

	public void setResponsableConcesion(String responsableConcesion) {
		this.responsableConcesion = responsableConcesion;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public String getOcupadoPor() {
		return ocupadoPor;
	}

	public void setOcupadoPor(String ocupadoPor) {
		this.ocupadoPor = ocupadoPor;
	}

	public String getTipoUso() {
		return tipoUso;
	}

	public void setTipoUso(String tipoUso) {
		this.tipoUso = tipoUso;
	}

	public BigDecimal getSuperficieSolicitada() {
		return superficieSolicitada;
	}

	public void setSuperficieSolicitada(BigDecimal superficieSolicitada) {
		this.superficieSolicitada = superficieSolicitada;
	}

	public BigDecimal getSuperficieAsignada() {
		return superficieAsignada;
	}

	public void setSuperficieAsignada(BigDecimal superficieAsignada) {
		this.superficieAsignada = superficieAsignada;
	}

	public String getInforActualGT() {
		return inforActualGT;
	}

	public void setInforActualGT(String inforActualGT) {
		this.inforActualGT = inforActualGT;
	}

	public String getInforConsolidado() {
		return inforConsolidado;
	}

	public void setInforConsolidado(String inforConsolidado) {
		this.inforConsolidado = inforConsolidado;
	}

	public String getInforPronJuidico() {
		return inforPronJuidico;
	}

	public void setInforPronJuidico(String inforPronJuidico) {
		this.inforPronJuidico = inforPronJuidico;
	}

	public String getInforConsolidado2() {
		return inforConsolidado2;
	}

	public void setInforConsolidado2(String inforConsolidado2) {
		this.inforConsolidado2 = inforConsolidado2;
	}

	public String getSolicitudComite() {
		return solicitudComite;
	}

	public void setSolicitudComite(String solicitudComite) {
		this.solicitudComite = solicitudComite;
	}

	public String getActaResolucion() {
		return actaResolucion;
	}

	public void setActaResolucion(String actaResolucion) {
		this.actaResolucion = actaResolucion;
	}

	public String getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	public String getDireccionResponsable() {
		return direccionResponsable;
	}

	public void setDireccionResponsable(String direccionResponsable) {
		this.direccionResponsable = direccionResponsable;
	}

	public String getDniResponsablePorDir() {
		return dniResponsablePorDir;
	}
	
	public void setDniResponsablePorDir(String dniResponsablePorDir) {
		this.dniResponsablePorDir = dniResponsablePorDir;
	}
	
	public String getNombreResponsablePorDir() {
		return nombreResponsablePorDir;
	}
	
	public void setNombreResponsablePorDir(String nombreResponsablePorDir) {
		this.nombreResponsablePorDir = nombreResponsablePorDir;
	}
	
	public String getBusquedaPersona() {
		return busquedaPersona;
	}
	
	public void setBusquedaPersona(String busquedaPersona) {
		this.busquedaPersona = busquedaPersona;
	}
	
	public List<SelectItem> getLstPersonas() {
		return lstPersonas;
	}
	
	public void setLstPersonas(List<SelectItem> lstPersonas) {
		this.lstPersonas = lstPersonas;
	}
	
	public List<SelectItem> getLstUnidadTiempo() {
		return lstUnidadTiempo;
	}
	
	public void setLstUnidadTiempo(List<SelectItem> lstUnidadTiempo) {
		this.lstUnidadTiempo = lstUnidadTiempo;
	}
	
	public List<SelectItem> getLstTipoUso() {
		return lstTipoUso;
	}
	
	public void setLstTipoUso(List<SelectItem> lstTipoUso) {
		this.lstTipoUso = lstTipoUso;
	}
	
	public String getDirPdf() {
		return dirPdf;
	}
	
	public void setDirPdf(String dirPdf) {
		this.dirPdf = dirPdf;
	}
	
	public GenAsignacionSuelo getAsignacionSuelo() {
		return asignacionSuelo;
	}
	
	public void setAsignacionSuelo(GenAsignacionSuelo asignacionSuelo) {
		this.asignacionSuelo = asignacionSuelo;
	}
	
	//Contrato 
	
	public List<GenContratoAsignacion> getLstContratos() {
		return lstContratos;
	}
	
	public void setLstContratos(List<GenContratoAsignacion> lstContratos) {
		this.lstContratos = lstContratos;
	}
	
	
	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

	public String getTdrContrato() {
		return tdrContrato;
	}

	public void setTdrContrato(String tdrContrato) {
		this.tdrContrato = tdrContrato;
	}

	public String getPliegoContrato() {
		return pliegoContrato;
	}

	public void setPliegoContrato(String pliegoContrato) {
		this.pliegoContrato = pliegoContrato;
	}

	public String getArrendadorCotrato() {
		return arrendadorCotrato;
	}

	public void setArrendadorCotrato(String arrendadorCotrato) {
		this.arrendadorCotrato = arrendadorCotrato;
	}

	public String getArrendatarioContrato() {
		return arrendatarioContrato;
	}

	public void setArrendatarioContrato(String arrendatarioContrato) {
		this.arrendatarioContrato = arrendatarioContrato;
	}

	public BigDecimal getPeriodicidadPagoC() {
		return periodicidadPagoC;
	}

	public void setPeriodicidadPagoC(BigDecimal periodicidadPagoC) {
		this.periodicidadPagoC = periodicidadPagoC;
	}

	public String getUnidadTiempoContrato() {
		return unidadTiempoContrato;
	}

	public void setUnidadTiempoContrato(String unidadTiempoContrato) {
		this.unidadTiempoContrato = unidadTiempoContrato;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	
	public boolean isEdicionContrato() {
		return edicionContrato;
	}

	public void setEdicionContrato(boolean edicionContrato) {
		this.edicionContrato = edicionContrato;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getDniresponsableEntregable() {
		return dniresponsableEntregable;
	}

	public void setDniresponsableEntregable(String dniresponsableEntregable) {
		this.dniresponsableEntregable = dniresponsableEntregable;
	}

	public String getNombreResponsableEntregable() {
		return nombreResponsableEntregable;
	}

	public void setNombreResponsableEntregable(String nombreResponsableEntregable) {
		this.nombreResponsableEntregable = nombreResponsableEntregable;
	}

	public Date getFechaMaxEntrega() {
		return fechaMaxEntrega;
	}

	public void setFechaMaxEntrega(Date fechaMaxEntrega) {
		this.fechaMaxEntrega = fechaMaxEntrega;
	}

	public Timestamp getFechaSubida() {
		return fechaSubida;
	}

	public void setFechaSubida(Timestamp fechaSubida) {
		this.fechaSubida = fechaSubida;
	}

	public String getEstadoEntregable() {
		return estadoEntregable;
	}

	public void setEstadoEntregable(String estadoEntregable) {
		this.estadoEntregable = estadoEntregable;
	}

	public boolean isEdicionEntregable() {
		return edicionEntregable;
	}

	public void setEdicionEntregable(boolean edicionEntregable) {
		this.edicionEntregable = edicionEntregable;
	}

	public List<SelectItem> getSlctEstadosContrato() {
		return slctEstadosContrato;
	}

	public void setSlctEstadosContrato(List<SelectItem> slctEstadosContrato) {
		this.slctEstadosContrato = slctEstadosContrato;
	}

	public GenContratoAsignacion getContrato() {
		return contrato;
	}

	public void setContrato(GenContratoAsignacion contrato) {
		this.contrato = contrato;
	}

	public GenEntregablesContrato getEntregableC() {
		return entregableC;
	}

	public void setEntregableC(GenEntregablesContrato entregableC) {
		this.entregableC = entregableC;
	}

	public GenEntregablesContratoPK getPkEntregable() {
		return pkEntregable;
	}

	public void setPkEntregable(GenEntregablesContratoPK pkEntregable) {
		this.pkEntregable = pkEntregable;
	}

	public List<GenEntregablesContrato> getLstEntregables() {
		return lstEntregables;
	}

	public void setLstEntregables(List<GenEntregablesContrato> lstEntregables) {
		this.lstEntregables = lstEntregables;
	}

	public List<SelectItem> getSlctEstadosEntregable() {
		return slctEstadosEntregable;
	}

	public void setSlctEstadosEntregable(List<SelectItem> slctEstadosEntregable) {
		this.slctEstadosEntregable = slctEstadosEntregable;
	}
	
	public String getBusquedaResponsableEC() {
		return busquedaResponsableEC;
	}
	
	public void setBusquedaResponsableEC(String busquedaResponsableEC) {
		this.busquedaResponsableEC = busquedaResponsableEC;
	}

	public String nuevoSuelo() {
		setOcultarGuardar(false);
		limpiarDatos();

		date = new Date();
		return "neAsignacionSuelo?faces-redirect=true";
	}

	public String cargarEditarAsignacionSuelo(GenAsignacionSuelo asigSuelo) {
		setId(asigSuelo.getSueId());
		setTipoCatalogo(asigSuelo.getSueTipo());
		setNombre(asigSuelo.getSueNombre());
		setDescripcion(asigSuelo.getSueDescripcion());
		setActividad(asigSuelo.getSueActividad());
		setZona(asigSuelo.getGenZona().getZonId());
		setAsignacion(asigSuelo.getSueAsignacion());
		setMetros(asigSuelo.getSueMetros());
		setSueNumeroanios(asigSuelo.getSueNumeroAnios());
		setUnidadTiempo(asigSuelo.getSueUnidadTiempo());
		setObservacion(asigSuelo.getSueObservacion());
		setEstado(asigSuelo.getSueEstado());
		setRegAmbiental(asigSuelo.getSueRegulacionAmbiental());
		setCoordenadaX(asigSuelo.getSueCoordenadaX());
		setCoordenadaY(asigSuelo.getSueCoordenadaY());
		setSuperficieAsignada(asigSuelo.getSueSuperficieAsignada());
		setSuperficieSolicitada(asigSuelo.getSueSuperficieSolicitada());
		setOcupado(asigSuelo.getSueOcupado());
		setOcupadoPor(asigSuelo.getSueOcupadoPor());
		setTipoUso(asigSuelo.getSueTipoUso());
		setEstado(asigSuelo.getSueEstado());
		setDniResponsablePorDir(asigSuelo.getSueResponsable());
		setNombreResponsablePorDir(buscarNombre(getDniResponsablePorDir()));
		setDireccionResponsable(asigSuelo.getSueDireccionResponsable());
		setFuenteHidrica(asigSuelo.getSueFuenteHidrica());
		setConcesionFHidrica(asigSuelo.getSueConcesionFuenteHidrica());
		setResponsableConcesion(asigSuelo.getSueResponsableConcesion());
		setearAsignacionSuelo(getId());
	    // Settear Informes
		setInforActualGT(asigSuelo.getSueInforActualGestionTerr());
		setInforConsolidado(asigSuelo.getSueInforConsolidado());
		setInforGestionTerr(asigSuelo.getSueInforGestionTerritorial());
		setInforConsolidado2(asigSuelo.getSueInforConsolidado2());
		setInforPronJuidico(asigSuelo.getSueInforPronunciamientoJurid());
		setActaResolucion(asigSuelo.getSueActaResolucionComite());
		setSolicitudComite(asigSuelo.getSueSolicitudComite());
		setEdicion(true);
		ocultarGuardar = false;
		cargarLstContratos();
		
		return "neAsignacionSuelo?faces-redirect=true";
	}

	public void guardarEditarAsignacionSuelos() {
		try {
			System.out.println("ingreso a metodo guardar");
			System.out.println("tipo catalogo "+getTipoCatalogo());
			if (validarCampos()) {
				GenAsignacionSuelo as = new GenAsignacionSuelo();
				as.setSueTipo(getTipoCatalogo());
				as.setSueNombre(Funciones.quitarEspacios(getNombre()));
				as.setSueDescripcion(Funciones.quitarEspacios(getDescripcion()));
				as.setSueActividad(Funciones.quitarEspacios(getActividad()));
				as.setGenZona(mngTerritorio.findZonaById(getZona()));
				as.setSueAsignacion(Funciones.quitarEspacios(getAsignacion()));
				as.setSueMetros(getMetros());
				as.setSueNumeroAnios(getSueNumeroanios());
				as.setSueUnidadTiempo(getUnidadTiempo());
				as.setSueObservacion(Funciones.quitarEspacios(getObservacion()));
				as.setSueRegulacionAmbiental(Funciones.quitarEspacios(getRegAmbiental()));
				as.setSueCoordenadaX(getCoordenadaX());
				as.setSueCoordenadaY(getCoordenadaY());
				as.setSueSuperficieSolicitada(getSuperficieSolicitada());
				as.setSueSuperficieAsignada(getSuperficieAsignada());
				as.setSueOcupado(isOcupado());
				as.setSueOcupadoPor(getOcupadoPor());
				as.setSueTipoUso(getTipoUso());
				as.setSueEstado(getEstado());
				as.setSueResponsable(Funciones.quitarEspacios(getDniResponsablePorDir()));
				as.setSueDireccionResponsable(Funciones.quitarEspacios(getDireccionResponsable()));
				as.setSueFuenteHidrica(Funciones.quitarEspacios(getFuenteHidrica()));
				as.setSueConcesionFuenteHidrica(Funciones.quitarEspacios(getConcesionFHidrica()));
				as.setSueResponsableConcesion(Funciones.quitarEspacios(getResponsableConcesion()));
				
				if (isEdicion()) {
					GenAsignacionSuelo asignacionSuelo = mngTerritorio.findAsignacionSueloById(getId());
					setId(asignacionSuelo.getSueId());
					as.setSueId(getId());
					
					if(getInforGestionTerr() != null || getInforGestionTerr() != ""){
					as.setSueInforGestionTerritorial(getInforGestionTerr());	
					}else{
						as.setSueInforGestionTerritorial(asignacionSuelo.getSueInforGestionTerritorial());
					}
					if(getInforActualGT()!= null || getInforActualGT() != ""){
						as.setSueInforActualGestionTerr(getInforActualGT());
					}else{
						as.setSueInforActualGestionTerr(asignacionSuelo.getSueInforActualGestionTerr());
					}
					if(getInforConsolidado()!= null || getInforConsolidado() != ""){
						as.setSueInforConsolidado(getInforConsolidado());
					}else{
						as.setSueInforConsolidado(asignacionSuelo.getSueInforConsolidado());
					}
					if(getInforPronJuidico()!= null || getInforPronJuidico() != ""){
						as.setSueInforPronunciamientoJurid(getInforPronJuidico());
					}else{
						as.setSueInforPronunciamientoJurid(asignacionSuelo.getSueInforPronunciamientoJurid());
					}
					if(getInforConsolidado2()!= null || getInforConsolidado2() != ""){
						as.setSueInforConsolidado2(getInforConsolidado2());
					}else{
						as.setSueInforConsolidado2(asignacionSuelo.getSueInforConsolidado2());
					}
					if(getSolicitudComite()!= null || getSolicitudComite() != ""){
						as.setSueSolicitudComite(getSolicitudComite());
					}else{
						as.setSueSolicitudComite(asignacionSuelo.getSueSolicitudComite());
					}
					if(getActaResolucion()!= null || getActaResolucion() != ""){
						as.setSueActaResolucionComite(getActaResolucion());
					}else{
						as.setSueActaResolucionComite(asignacionSuelo.getSueActaResolucionComite());
					}
					mngTerritorio.modicarAsignacionSuelo(as);
					Mensaje.crearMensajeINFO("Asignación de Suelo actualizada correctamente.");
				} else {
					setId(mngTerritorio.asignacionSueloId());
					as.setSueId(getId());
					mngTerritorio.insertarAsignacionSuelo(as);
					setEdicion(true);
					setOcultarGuardar(true);
					Mensaje.crearMensajeINFO("Asignación de Suelo ingresada correctamente.");
				}
				setearAsignacionSuelo(getId());
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al almacenar suelo: " + e.getMessage());
			System.out.println("Error al almacenar suelo: ");
			e.printStackTrace();
		}
	}
	private void setearAsignacionSuelo(int idSuelo){
		try {
			setAsignacionSuelo(mngTerritorio.findAsignacionSueloById(getId()));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al buscar Asignación Suelo por ID. ");
		}
	}

	public boolean validarCampos() {
		if (getZona().equals(SELECCIONAR)) {
			Mensaje.crearMensajeERROR("Campo zona requerido");
			return false;
		} else if (getTipoCatalogo().equals(SELECCIONAR)) {
			Mensaje.crearMensajeERROR("Campo tipo requerido");
			return false;
		} else {
			if (getUnidadTiempo().equals(SELECCIONAR)) {
				Mensaje.crearMensajeERROR("Seleccione la unidad de tiempo de la Temporalidad. ");
				return false;
			}
			return true;
		}
	}

	public String cancelar() {
		limpiarDatos();
		cargarAsignacionSuelo();
		return "asignacionSuelos?faces-redirect=true";
	}

	private void limpiarDatos() {
		setId(null);
		setTipoCatalogo(SELECCIONAR); setNombre(""); setDescripcion("");
		setActividad(""); setZona(SELECCIONAR); setAsignacion(""); 
		setMetros(BigDecimal.ZERO); setSueNumeroanios(0); setUnidadTiempo(SELECCIONAR);
		setObservacion(""); setEstado(EN_PROGRESO);
		setRegAmbiental(""); setCoordenadaX(BigDecimal.ZERO); setCoordenadaY(BigDecimal.ZERO);
		setSuperficieAsignada(BigDecimal.ZERO); setSuperficieSolicitada(BigDecimal.ZERO);
		setOcupado(false); setOcupadoPor(""); setTipoUso(SELECCIONAR);
		setDniResponsablePorDir(""); setNombreResponsablePorDir("");
		setDireccionResponsable(""); setFuenteHidrica("");
		setConcesionFHidrica(""); setResponsableConcesion("");
		setOcultarGuardar(false);
		setEdicion(false);
	}

	private void cargarAsignacionSuelo() {
		getListAsignacionSuelos().clear();
		getListAsignacionSuelos().addAll(mngTerritorio.findAllAsignacionSuelo());
	}
	
	public void subirInforActualGT(FileUploadEvent evento) {
		try {
			System.out.println("Ingreso a metodo informActual");
			setInforActualGT(cargarInformes(evento));
			editarAsignacionSuelo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void subirInforGestionT(FileUploadEvent evento) {
		try {
			System.out.println("Ingreso a metodo informActual");
			setInforGestionTerr(cargarInformes(evento));
			editarAsignacionSuelo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void subirInforConsolidado(FileUploadEvent evento) {
		try {
			System.out.println("Ingreso a metodo informActual");
			setInforConsolidado(cargarInformes(evento));
			editarAsignacionSuelo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void subirInforPronunciamientoJ(FileUploadEvent evento) {
		try {
			System.out.println("Ingreso a metodo informActual");
			setInforPronJuidico(cargarInformes(evento));
			editarAsignacionSuelo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void subirInforConsolidado2(FileUploadEvent evento) {
		try {
			System.out.println("Ingreso a metodo informActual");
			setInforConsolidado2(cargarInformes(evento));
			editarAsignacionSuelo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void subirSolicitudComite(FileUploadEvent evento) {
		try {
			System.out.println("Ingreso a metodo informActual");
			setSolicitudComite(cargarInformes(evento));
			editarAsignacionSuelo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void subirActaComite(FileUploadEvent evento) {
		try {
			System.out.println("Ingreso a metodo informActual");
			setActaResolucion(cargarInformes(evento));
			editarAsignacionSuelo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Contrato

	public void subirTDR(FileUploadEvent evento) {
		try {
			setTdrContrato(cargarInformes(evento));
			editarContrato();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void subirPliego(FileUploadEvent evento) {
		try {
			setTdrContrato(cargarInformes(evento));
			editarContrato();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Entregables

	public void subirDocumentoEntregable(FileUploadEvent evento) {
		try {
			setDocumento(cargarInformes(evento));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String cargarInformes(FileUploadEvent event) throws IOException {
		filePdf = event.getFile();
		String nombreArchivo ="";
		InputStream inputStream = null;
		OutputStream outputStream = null;
		if (filePdf != null) {
			try {
				
				outputStream = new FileOutputStream(direccionArchivo(filePdf));
				inputStream = filePdf.getInputstream();
				nombreArchivo = filePdf.getFileName();

				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				Mensaje.crearMensajeINFO("Carga del archivo Correcta");
			} catch (Exception e) {
				Mensaje.crearMensajeERROR("No se pudo cargar el archivo");
				e.printStackTrace();
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			}
			return  nombreArchivo ;
		} else {
			Mensaje.crearMensajeWARN("No se pudo cargar el archivo");
			return "";
		}
	}
	
	public String direccionArchivo(UploadedFile file){
		try {
			String carpeta = mngTerritorio.findParametroByID("direccion_informes") + "/";
			String nombreArchivo = file.getFileName();
			String ubicacionArchivo = carpeta + File.separatorChar +nombreArchivo;
			System.out.println("Carpeta -----> "+carpeta);
			System.out.println("PAD -----> "+nombreArchivo);
			return ubicacionArchivo;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String extensionArchivo(String nombreArchivo) {
		return nombreArchivo.substring(nombreArchivo.lastIndexOf('.'));
	}

	public void editarAsignacionSuelo() {
		try {
			if (validarCampos()) {
				GenAsignacionSuelo as = new GenAsignacionSuelo();
				as.setSueId(getId());
				as.setSueTipo(getTipoCatalogo());
				as.setSueNombre(Funciones.quitarEspacios(getNombre()));
				as.setSueDescripcion(Funciones.quitarEspacios(getDescripcion()));
				as.setSueActividad(Funciones.quitarEspacios(getActividad()));
				as.setGenZona(mngTerritorio.findZonaById(getZona()));
				as.setSueAsignacion(Funciones.quitarEspacios(getAsignacion()));
				as.setSueMetros(getMetros());
				as.setSueNumeroAnios(getSueNumeroanios());
				as.setSueUnidadTiempo(getUnidadTiempo());
				as.setSueObservacion(Funciones.quitarEspacios(getObservacion()));
				as.setSueRegulacionAmbiental(Funciones.quitarEspacios(getRegAmbiental()));
				as.setSueCoordenadaX(getCoordenadaX());
				as.setSueCoordenadaY(getCoordenadaY());
				as.setSueSuperficieSolicitada(getSuperficieSolicitada());
				as.setSueSuperficieAsignada(getSuperficieAsignada());
				as.setSueOcupado(isOcupado());
				as.setSueOcupadoPor(getOcupadoPor());
				as.setSueTipoUso(getTipoUso());
				as.setSueEstado(getEstado());
				as.setSueResponsable(Funciones.quitarEspacios(getDniResponsablePorDir()));
				as.setSueDireccionResponsable(Funciones.quitarEspacios(getDireccionResponsable()));
				as.setSueFuenteHidrica(Funciones.quitarEspacios(getFuenteHidrica()));
				as.setSueConcesionFuenteHidrica(Funciones.quitarEspacios(getConcesionFHidrica()));
				as.setSueResponsableConcesion(Funciones.quitarEspacios(getResponsableConcesion()));

				GenAsignacionSuelo asignacionSuelo = mngTerritorio
						.findAsignacionSueloById(getId());

				if (getInforGestionTerr() != null
						|| getInforGestionTerr() != "") {
					as.setSueInforGestionTerritorial(getInforGestionTerr());
				} else {
					as.setSueInforGestionTerritorial(asignacionSuelo
							.getSueInforGestionTerritorial());
				}
				if (getInforActualGT() != null || getInforActualGT() != "") {
					as.setSueInforActualGestionTerr(getInforActualGT());
				} else {
					as.setSueInforActualGestionTerr(asignacionSuelo
							.getSueInforActualGestionTerr());
				}
				if (getInforConsolidado() != null
						|| getInforConsolidado() != "") {
					as.setSueInforConsolidado(getInforConsolidado());
				} else {
					as.setSueInforConsolidado(asignacionSuelo
							.getSueInforConsolidado());
				}
				if (getInforPronJuidico() != null
						|| getInforPronJuidico() != "") {
					as.setSueInforPronunciamientoJurid(getInforPronJuidico());
				} else {
					as.setSueInforPronunciamientoJurid(asignacionSuelo
							.getSueInforPronunciamientoJurid());
				}
				if (getInforConsolidado2() != null
						|| getInforConsolidado2() != "") {
					as.setSueInforConsolidado2(getInforConsolidado2());
				} else {
					as.setSueInforConsolidado2(asignacionSuelo
							.getSueInforConsolidado2());
				}
				if (getSolicitudComite() != null || getSolicitudComite() != "") {
					as.setSueSolicitudComite(getSolicitudComite());
				} else {
					as.setSueSolicitudComite(asignacionSuelo
							.getSueSolicitudComite());
				}
				if (getActaResolucion() != null || getActaResolucion() != "") {
					as.setSueActaResolucionComite(getActaResolucion());
				} else {
					as.setSueActaResolucionComite(asignacionSuelo
							.getSueActaResolucionComite());
				}
				mngTerritorio.modicarAsignacionSuelo(as);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void descargarInforme(String informe){
		try {
			if(informe == null || informe.isEmpty()){
				Mensaje.crearMensajeERROR("No existe un archivo asignado.");
			}else {
				String contextPath = mngTerritorio.findParametroByID("direccion_informes") + File.separatorChar
					+ informe+"";
				Funciones.descargarPDF(contextPath);
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al descargar el documento: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Lista de Zona
	 */
	public void cargarZona() {
		getL_zona().clear();
		getL_zona().add( new SelectItem(SELECCIONAR, " --Seleccione-- "));
		for (GenZona i : mngTerritorio.findAllZonasA()) {
			getL_zona().add(new SelectItem(i.getZonId(), i.getZonNombre()));
		}
	}

	/**
	 * Lista de TiposCatalogo
	 */
	public void cargarTiposCatalogo() {
		getL_tipo_catalogo().clear();
		getL_tipo_catalogo().add( new SelectItem(SELECCIONAR, " --Seleccione-- "));
		List<GenCatalogoItemsDet> completo = mngTerritorio.AllofItems("cat_tipo_asignacionsuelo");
		for (GenCatalogoItemsDet i : completo) {
			getL_tipo_catalogo().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}
	
	public void cargarUnidadTiempo() {
		getLstUnidadTiempo().clear();
		getLstUnidadTiempo().add( new SelectItem(SELECCIONAR, " --Seleccione-- "));
		for (GenCatalogoItemsDet i : mngTerritorio.AllofItems("cat_unidad_tiempo")) {
			getLstUnidadTiempo().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}
	
	public void cargarTipoUso() {
		getLstTipoUso().clear();
		getLstTipoUso().add( new SelectItem(SELECCIONAR, " --Seleccione-- "));
		for (GenCatalogoItemsDet i : mngTerritorio.AllofItems("cat_tipo_uso")) {
			getLstTipoUso().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Validar campos
	 * 
	 * @param item
	 * @return
	 */
	public String validarItemCatalogo(String item) {
		String respuesta = "";
		try {
			respuesta = mngTerritorio.catalogoItem(item);

		} catch (Exception e) {
			e.printStackTrace();
			Mensaje.crearMensajeERROR("Error al validar Item. " + e.getMessage());
		}
		return respuesta;
	}

	public String fechasaanios(Date fechaInicio, Date fechaFin) {
		String aniosEntreFechas = mngTerritorio.fechasaAnios(getFechaIncio(), getFechaFin(), getId());
		return aniosEntreFechas;
	}

	public void tablaSeguimiento(GenAsignacionSuelo asignacion) {
		getL_seguimiento().clear();
		setL_seguimiento(mngTerritorio.listaSeguimientoFiltrado(asignacion.getSueId()));
		RequestContext.getCurrentInstance().execute("PF('dlgSeg').show();");
	}
	
	public void descargarDocumento(GenHistorialSeguimiento seguimiento) {
		  try {
		   if (seguimiento.getHseAdjuntoDoc() == null
		     || seguimiento.getHseAdjuntoDoc().isEmpty()) {
		    Mensaje.crearMensajeERROR("No existe un archivo asignado.");
		   } else {
		    String contextPath = mngTerritorio
		      .findParametroByID("direccion_ad_doc") + File.separatorChar 
		      + seguimiento.getHseAdjuntoDoc() + "";
		    Funciones.descargarPDF(contextPath);
		   }
		  } catch (Exception e) {
		   Mensaje.crearMensajeERROR("Error: "+e.getMessage());
		   e.printStackTrace();
		  }
		 }
		 
		 public void descargarFoto(GenHistorialSeguimiento seguimiento) {
		  try {
		   if (seguimiento.getHseAdjuntoFot() == null
		     || seguimiento.getHseAdjuntoFot().isEmpty()) {
		    Mensaje.crearMensajeERROR("No existe un archivo asignado.");
		   } else {
		    String contextPath = mngTerritorio
		      .findParametroByID("direccion_ad_foto") + File.separatorChar
		      + seguimiento.getHseAdjuntoFot() + "";
		    Funciones.descargarPDF(contextPath);
		   }
		  } catch (Exception e) {
		   Mensaje.crearMensajeERROR("Error: "+e.getMessage());
		   e.printStackTrace();
		  }
		 }
	
	public String cambiarNombre(String param){
		if(param.equals(EN_PROGRESO)){
			return "En progreso";
		}else{
			return "Actualizado";
		}
		
	}
	
	//// Fase 2
	
	public String nombreBoton() {
		if (isEdicionContrato() || isEdicionEntregable()) {
			return "Actualizar";
		} else {
			return "Insertar";
		}
	}
	
	public void switchOcupado(){
		if(isOcupado() == false){
			setOcupadoPor("");
			setTipoUso(SELECCIONAR);
		}
	}
	
	public void cargarBusqueda() {
		try {
			getLstPersonas().clear();
			getLstPersonas().add(new SelectItem(SELECCIONAR, " --Seleccione-- "));
			List<GenFuncionariosInstitucion> list = mngGeneral.findAllfuncionarios();
			for (GenFuncionariosInstitucion i : list) {
				getLstPersonas().add(new SelectItem(i.getGenPersona().getPerDni(), i.getGenPersona().getPerDni() + " | "
						+ i.getGenPersona().getPerNombres() + " " + i.getGenPersona().getPerApellidos()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para buscar un funcionario
	 */
	public void buscarPersona() {
		if (getBusquedaPersona() == null || getBusquedaPersona().isEmpty()) {
			Mensaje.crearMensajeWARN("Debe ingresar el dato para realizar la búsqueda.");
			setBusquedaPersona("");
			setDniResponsablePorDir("");
			setNombreResponsablePorDir("");
			setDireccionResponsable("");
		} else {
			try {
				GenFuncionariosInstitucion f = mngGeneral.findFuncionarioXDni(getBusquedaPersona());
				if (f == null) {
					Mensaje.crearMensajeWARN("Persona no encontrada");
					setBusquedaPersona("");
					setDniResponsablePorDir("");
					setNombreResponsablePorDir("");
					setDireccionResponsable("");
				} else {
					setBusquedaPersona("");
					setDniResponsablePorDir(f.getGenPersona().getPerDni());
					setNombreResponsablePorDir(f.getGenPersona().getPerNombres() + " " + f.getGenPersona().getPerApellidos());
					setDireccionResponsable(f.getFunDireccion());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//Contrato 
	
	public void mostrarDlgContrato() {
		RequestContext.getCurrentInstance().execute("PF('conDlg').show();");
	}
	
	public void cargarContrato(GenContratoAsignacion contrato){
		setEdicionContrato(true);
		setIdContrato(contrato.getCasId());
		setTdrContrato(contrato.getCasTdr());
		setPliegoContrato(contrato.getCasPliego());
		setFechaIncio(contrato.getCasFechaInicio());
		setFechaFin(contrato.getCasFechaFin());
		setArrendadorCotrato(contrato.getCasArrendador());
		setArrendatarioContrato(contrato.getCasArrendatario());
		setPeriodicidadPagoC(contrato.getCasPeriodicidadPago());
		setUnidadTiempo(contrato.getCasUnidadTiempo());
		setPrecio(contrato.getCasPrecio());
		
		RequestContext.getCurrentInstance().execute("PF('conDlg').show();");
	}
	
	public void guardarEditarContrato(){
		try{
		if(validarCamposContrato()){
			GenContratoAsignacion ca = new GenContratoAsignacion();
			ca.setCasId(Funciones.quitarEspacios(getIdContrato()).toUpperCase());
			ca.setGenAsignacionSuelo(mngTerritorio.findAsignacionSueloById(getId()));
			ca.setCasArrendador(Funciones.quitarEspacios(getArrendadorCotrato()));
			ca.setCasArrendatario(Funciones.quitarEspacios(getArrendatarioContrato()));
			ca.setCasFechaInicio(new Timestamp(getFechaIncio().getTime()));
			ca.setCasFechaFin(new Timestamp(getFechaFin().getTime()));
			ca.setCasPeriodicidadPago(getPeriodicidadPagoC());
			ca.setCasUnidadTiempo(getUnidadTiempoContrato());
			if( isEdicionContrato()){
				GenContratoAsignacion contrato = mngTerritorio.findContratoById(getIdContrato());
				if(getTdrContrato() != null || getTdrContrato() != ""){
					ca.setCasTdr(getTdrContrato());
				}else{
					ca.setCasTdr(contrato.getCasTdr());
				}
				if(getPliegoContrato() != null || getPliegoContrato() != ""){
					ca.setCasPliego(getPliegoContrato());
				}else{
					ca.setCasPliego(contrato.getCasPliego());
				}
				mngTerritorio.modificarContrato(ca);
				Mensaje.crearMensajeINFO("Contrato actualizado correctamente.");
				
			}else{
				if(mngTerritorio.findContratoById(getIdContrato()) != null){
					Mensaje.crearMensajeERROR("Ya existe el código de contrato.");
				}else{
					mngTerritorio.insertarContrato(ca);
					setEdicionContrato(true);
					Mensaje.crearMensajeINFO("Contrato ingresado correctamente.");
				}
			}
		}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al almacenar contrato: " + e.getMessage());
			System.out.println("Error al almacenar contrato ");
			e.printStackTrace();
		}
	} 
	private boolean validarCamposContrato(){
		if(getUnidadTiempoContrato().equals(SELECCIONAR)){
			Mensaje.crearMensajeERROR("Se debe seleccionar la Unidad de tiempo de pa Periodicidad de Pago.");
			return false;
		}else {
			return true;
		}
	}

	private void editarContrato() {
		try {
			if (validarCamposContrato()) {
				GenContratoAsignacion ca = new GenContratoAsignacion();
				ca.setCasId(Funciones.quitarEspacios(getIdContrato())
						.toUpperCase());
				ca.setGenAsignacionSuelo(mngTerritorio
						.findAsignacionSueloById(getId()));
				ca.setCasArrendador(Funciones
						.quitarEspacios(getArrendadorCotrato()));
				ca.setCasArrendatario(Funciones
						.quitarEspacios(getArrendatarioContrato()));
				ca.setCasFechaInicio(new Timestamp(getFechaIncio().getTime()));
				ca.setCasFechaFin(new Timestamp(getFechaFin().getTime()));
				ca.setCasPeriodicidadPago(getPeriodicidadPagoC());
				ca.setCasUnidadTiempo(getUnidadTiempoContrato());
				GenContratoAsignacion contrato = mngTerritorio
						.findContratoById(getIdContrato());
				if (getTdrContrato() != null || getTdrContrato() != "") {
					ca.setCasTdr(getTdrContrato());
				} else {
					ca.setCasTdr(contrato.getCasTdr());
				}
				if (getPliegoContrato() != null || getPliegoContrato() != "") {
					ca.setCasPliego(getPliegoContrato());
				} else {
					ca.setCasPliego(contrato.getCasPliego());
				}
				mngTerritorio.modificarContrato(ca);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cancelarContrato(){
		limpiarCamposContrato();
		
		RequestContext.getCurrentInstance().execute("PF('conDlg').hide();");
	}
	
	public void limpiarCamposContrato(){
		setIdContrato(""); setTdrContrato(""); setPliegoContrato(""); 
		setFechaIncio(new Date()); setFechaFin(new Date()); 
		setArrendadorCotrato(""); setArrendatarioContrato("");
		setPeriodicidadPagoC(BigDecimal.ZERO); setUnidadTiempoContrato(SELECCIONAR);
		setPrecio(BigDecimal.ZERO); setEdicionContrato(false);
	}
	
	private void cargarLstContratos(){
		setLstContratos(mngTerritorio.findAllContratosPorAsignacion(getId()));
	}
	
	// Entregables
	
	public void mostrarDlgEntregables() {
		cargarLstEntregables();
		RequestContext.getCurrentInstance().execute("PF('entDlg').show();");
	}
	
	private void cargarLstEntregables(){
		setLstEntregables(mngTerritorio.findAllEntregablesPorContrato(getIdContrato()));
	}
	
	public void buscarResponsableEnt() {
		if (getBusquedaResponsableEC() == null || getBusquedaResponsableEC().isEmpty()) {
			Mensaje.crearMensajeWARN("Debe ingresar el dato para realizar la búsqueda.");
			setBusquedaResponsableEC("");
			setDniresponsableEntregable("");
			setNombreResponsableEntregable("");
		} else {
			try {
				GenFuncionariosInstitucion f = mngGeneral.findFuncionarioXDni(getBusquedaResponsableEC());
				if (f == null) {
					Mensaje.crearMensajeWARN("Persona no encontrada");
					setBusquedaResponsableEC("");
					setDniresponsableEntregable("");
					setNombreResponsableEntregable("");
				} else {
					setBusquedaResponsableEC("");
					setDniresponsableEntregable(f.getGenPersona().getPerDni());
					setNombreResponsableEntregable(f.getGenPersona().getPerNombres() + " " + f.getGenPersona().getPerApellidos());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void cargarEntregables(GenEntregablesContrato entregable){
		setEdicionEntregable(true);
		setPkEntregable(entregable.getId());
		setContrato(entregable.getGenContratoAsignacion());
		setDocumento(entregable.getId().getEcoDocumento());
		setDniresponsableEntregable(entregable.getEcoResponsable());
		setFechaMaxEntrega(entregable.getEcoFechaMaxEntrega());
		setFechaSubida(entregable.getEcoFechaSubida());
		setEstadoEntregable(entregable.getEcoEstado());
		setNombreResponsableEntregable(buscarNombre(entregable.getEcoResponsable()));
		
		RequestContext.getCurrentInstance().execute("PF('entDlg').show();");
	}
	
	public String buscarNombre(String cedula){
		try {
			if(cedula != null){
			String nombre = mngGeneral.findFuncionarioXDni(cedula).getGenPersona().getPerNombres()
					+" "+mngGeneral.findFuncionarioXDni(cedula).getGenPersona().getPerApellidos();
			return nombre;
			}else
				return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public void guardarEditarEntregable(){
		try{
		GenEntregablesContratoPK pk = new  GenEntregablesContratoPK();
		pk.setCasId(getIdContrato()); pk.setEcoDocumento(getDocumento());
		
		GenEntregablesContrato ec = new GenEntregablesContrato();
		ec.setId(pk); ec.setEcoFechaMaxEntrega(new Timestamp(getFechaMaxEntrega().getTime()));
		ec.setEcoFechaSubida(getFechaSubida()); ec.setEcoResponsable(getDniresponsableEntregable());
		
		if(isEdicionEntregable()){
			mngTerritorio.modificarEntregable(ec);
			Mensaje.crearMensajeINFO("Entregable ingresado correctamente.");
			setEdicionEntregable(false);
		}else{
			if(mngTerritorio.findEntregableById(pk) != null){
				mngTerritorio.insertarEntregable(ec);
				Mensaje.crearMensajeINFO("Entregable ingresado correctamente.");
			}
		}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al almacenar entregable contrato: " + e.getMessage());
			System.out.println("Error al almacenar entregable contrato ");
			e.printStackTrace();
		}
	}
	
	
	public void cancelarEntregable(){
		limpiarCamposEntregable();
		
		RequestContext.getCurrentInstance().execute("PF('entDlg').hide();");
	}
	
	public void limpiarCamposEntregable(){
		setEdicionEntregable(false); setPkEntregable(new GenEntregablesContratoPK());
		setDocumento(""); setDniresponsableEntregable(""); setNombreResponsableEntregable("");
		setFechaMaxEntrega(new Date()); setFechaSubida(new Timestamp(new Date().getTime()));
		setEstadoEntregable(SELECCIONAR);
	}
	
}
