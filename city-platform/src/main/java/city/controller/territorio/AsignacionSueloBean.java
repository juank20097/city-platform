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
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GtrAdministradorContrato;
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
public class AsignacionSueloBean implements Serializable {

	private static final long serialVersionUID = -3198725647396964268L;
	private static String ENPROCESO = "P";
	private static String APROBADO = "A";
	private static String NEGADO = "N";
	private static String VALORENPROCESO = "En Proceso";
	private static String VALORAPROBADO = "Aprobado";
	private static String VALORNEGADO = "Negado";
	private static String SELECCIONAR = "S/N";
	private static String ACTIVO = "A";
	private static String INACTIVO ="A";
	private static String VALORACTIVO = "Activo";
	private static String VALORINACTIVO ="Inactivo";

	@EJB
	private ManagerTerritorio mngTerritorio;
	
	@EJB
	private ManagerGeneral mngGeneral;
	
	@EJB
	private ManagerSitios msitios;
	
	@Inject
	private SesionBean session;
	
	private String usuarioSesion;

	@NotEmpty(message = "El campo ID no debe estar vacío.")
	@NotBlank(message = "El campo ID no debe tener solo espacios blancos.")
	private Integer idAsignacion;
	private String zona;
	@NotEmpty(message = "El campo ACTIVIDAD no debe estar vacío.")
	@NotBlank(message = "El campo ACTIVIDAD no debe tener solo espacios blancos.")
	private String actividad;
	@NotEmpty(message = "El campo ASIGNACIÓN no debe estar vacío.")
	@NotBlank(message = "El campo ASIGNACIÓN no debe tener solo espacios blancos.")
	private String asignacion;
	private String estado;
	private String observacion;
	private String tipoCatalogo;
	private Integer numeroAnios;
	private String unidadTiempo;
	@NotEmpty(message = "El campo NOMBRE no debe estar vacío.")
	@NotBlank(message = "El campo NOMBRE no debe tener solo espacios blancos.")
	private String nombre;
	@NotEmpty(message = "El campo DESCRIPCIÓN no debe estar vacío.")
	@NotBlank(message = "El campo DESCRIPCIÓN no debe tener solo espacios blancos.")
	private String descripcion;
	private String inforGestionTerr;
	@NotEmpty(message = "El campo FUENTE HÍDRICA no debe estar vacío.")
	@NotBlank(message = "El campo FUENTE HÍDRICA no debe tener solo espacios blancos.")
	private String fuenteHidrica;
	@NotEmpty(message = "El campo CONCESIÓN FUENTE HÍDRICA no debe estar vacío.")
	@NotBlank(message = "El campo CONCESIÓN FUENTE HÍDRICA no debe tener solo espacios blancos.")
	private String concesionFHidrica;
	@NotEmpty(message = "El campo RESPONSABLE CONCESIÓN no debe estar vacío.")
	@NotBlank(message = "El campo RESPONSABLE CONCESIÓN no debe tener solo espacios blancos.")
	private String responsableConcesion;
	@NotEmpty(message = "El campo CAUDAL ASIGNADO no debe estar vacío.")
	@NotBlank(message = "El campo CAUDAL ASIGNADO no debe tener solo espacios blancos.")
	private String caudalAsignado;
	@NotEmpty(message = "El campo CAUDAL TOTAL no debe estar vacío.")
	@NotBlank(message = "El campo CAUDAL TOTAL no debe tener solo espacios blancos.")
	private String caudalTotal;
	private String inforCaracterizacion;
	private Date fechaDocCaracte;
	private Date fechaSubidaCaracte ;
	private String usuCaracterizacion;
	private String inforUsoSuelo;
	private Date fechaDocUsoSuelo;
	private Date fechaSubidaUsoS;
	private String usuUsoSuelo;
	private String inforDisponibilidad;
	private Date fechaDocDisponibil;
	private Date fechaSubidaDisponi;
	private String usuDisponibilidad;
	private String inforConsolidado;
	private Date fechaDocConsolidado;
	private Date fechaSubidaConsol;
	private String usuConsolidado;
	private String resolucion;
	private Date fechaDocResolucion;
	private Date fechaSubidaResol;
	private String usuResolucion;
	private String actaResolutiva;
	private Date fechaDocActaResol;
	private Date fechaSubidaActaRes;
	private String usuActaResol;
	private String convocatoria;
	private Date fechaDocConvoca;
	private Date fechaSubidaConvoca;
	private String usuConvocatoria;
	private String direccionResponsable;
	@NotNull(message = "El campo Responsable no debe estar vacío.")
	private String dniResponsablePorDir;
	private String nombreResponsablePorDir;
	private String busquedaPersona;
	private String figuraLegal;
	private boolean aplicaRegAmbiental;
	private String archRegulacionAmb;
	private Date fechaDocRegulacionAmb;
	private Date fechaSubRegAmbiental;
	private String usuRegAmbiental;
	private String archivoKMZ;
	private Date fechaArcKMZ;
	private Date fechaSubidaKMZ;
	private String usuArchivoKMZ;
	private String archivoPDF;
	private Date fechaArcPDF;
	private Date fechaSubidaPDF;
	private String usuArchivoPDF;
	private String notificacionApNe;
	private Date fechaDocNotificacion;
	private Date fechaSubidaNotificacion;
	private String usuNotificacion;
	@DecimalMin("0")
	private BigDecimal superficieSolicitada;
	private String unidadMedSupSol;
	@DecimalMin("0")
	private BigDecimal superficieAsignada;
	private String unidadMedSupAsignada;
	private String enteAprobador;
	private boolean edicionAS;
	private UploadedFile filePdf;
	private GtrAsignacionSuelo asignacionSuelo;
	private List<GtrAsignacionSuelo> lstAsignacionSuelos;
	private List<SelectItem> lstZonas;
	private List<SelectItem> lstTipoCatalogo;
	private List<SelectItem> slctEstados;
	private List<SelectItem> lstPersonas;
	private List<SelectItem> lstUnidadTiempo;
	private List<SelectItem> lstTipoUso;
	
	private List<SelectItem> lstFiguraLegal;
	private List<SelectItem> lstEnteAprobador;
	private List<SelectItem> lstTipoActividad;
	private List<SelectItem> lstTipoContrato;
	private List<SelectItem> lstUnidadMedida;
	
	// Contrato
	private String idContrato;
	private String tdrContrato;
	private Date fechaDocTdr;
	private Date fechaSubidaTdr;
	private String usuTdr;
	private String pliegoContrato;
	private Date fechaDocPliego;
	private Date fechaSubPliego;
	private String usuPliego;
	private String archContrato;
	private Date fechaDocContrato;
	private Date fechaSubContrato;
	private String usuContrato;
	private Date fechaInicioC;
	private Date fechaFinC;
	private String arrendadorCotrato;
	private String arrendatarioContrato;
	private BigDecimal periodicidadPagoC;
	private String unidadTiempoContrato;
	@DecimalMin("0")
	private BigDecimal precio;
	@DecimalMin("0")
	private BigDecimal periodicidadPago;
	private String unidadTiempoPPago;
	private String estadoContrato;
	private String tipoContrato;
	private boolean edicionContrato;
	private List<GtrContratoAsignacion> lstContratos;
	private List<SelectItem> slctEstadosContrato;
	private GtrContratoAsignacion contrato;
	// Entregables
	private int idEntregable;
	private String documento;
	private String dniresponsableEntregable;
	private String nombreResponsableEntregable;
	@NotEmpty(message = "El campo NOMBRE ENTREGABLE no debe estar vacío.")
	@NotBlank(message = "El campo NOMBRE ENTREGABLE no debe tener solo espacios blancos.")
	private String nombreEntregable;
	private Date fechaMaxEntregaDoc;
	private Date fechaSubidaDoc;
	private String usuarioDocumento;
	private String estadoEntregable;
	private boolean edicionEntregable;
	private GtrEntregablesContrato entregableC;
	private List<GtrEntregablesContrato> lstEntregables;
	private List<SelectItem> slctEstadosEntregable;
	private String busquedaResponsableEC;
	// Administrador Contrato
	private int idAdmin;
	private String dniAdmin;
	private String nombreAdmin;
	private String direccionAdmin;
	private Date fechaInicioAdmin;
	private Date fechaFinAdmin;
	private String estadoAdmin;
	private boolean edicionAdmin;
	private GtrAdministradorContrato adminContrato;
	private List<GtrAdministradorContrato> lstAdministradores;
	private List<SelectItem> slctEstadosAdmin;
	private Timestamp fechaActual; 
	// lista de seguimientos
	List<GtrHistorialSeguimiento> l_seguimiento;
	
	public AsignacionSueloBean() {
	
	}
	
	@PostConstruct
	public void init() {
		session.validarSesion();
		usuarioSesion = session.getUsuario();
		estado = ENPROCESO;
		slctEstados = new ArrayList<SelectItem>();
		slctEstadosContrato = new ArrayList<SelectItem>();
		slctEstadosEntregable = new ArrayList<SelectItem>();
		slctEstadosAdmin = new ArrayList<SelectItem>();
		lstPersonas = new ArrayList<SelectItem>();
		lstAsignacionSuelos = new ArrayList<GtrAsignacionSuelo>();
		l_seguimiento = new ArrayList<GtrHistorialSeguimiento>();
		lstTipoCatalogo = new ArrayList<SelectItem>();
		lstAdministradores = new ArrayList<GtrAdministradorContrato>();
		lstUnidadTiempo = new ArrayList<SelectItem>();
		lstTipoUso = new ArrayList<SelectItem>();
		lstZonas = new ArrayList<SelectItem>();
		lstUnidadMedida = new ArrayList<SelectItem>();
		lstTipoContrato = new ArrayList<SelectItem>();
		lstTipoActividad = new ArrayList<SelectItem>();
		lstEnteAprobador = new ArrayList<SelectItem>();
		lstFiguraLegal = new ArrayList<SelectItem>();
		superficieAsignada = new BigDecimal(0);
		superficieSolicitada = new BigDecimal(0);
		numeroAnios = 0;
		periodicidadPagoC = BigDecimal.ZERO;
		zona = SELECCIONAR;
		tipoCatalogo  = SELECCIONAR;
		unidadTiempo = SELECCIONAR;
		cargarZona();
		cargarAsignacionSuelo();
		cargarTiposCatalogo();
		cargarUnidadTiempo();
		cargarTipoUso();
		cargarEstados();
		cargarBusqueda();
		cargarTipoContrato();
		cargarEnteAprobador();
		cargarFiguraLegal();
		cargarUnidadMedida();
		cargarTipoActividad();
		inicializarFechas();
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

	public String getConcesionFHidrica() {
		return concesionFHidrica;
	}

	public void setConcesionFHidrica(String concesionFHidrica) {
		this.concesionFHidrica = concesionFHidrica;
	}

	public String getCaudalAsignado() {
		return caudalAsignado;
	}

	public void setCaudalAsignado(String caudalAsignado) {
		this.caudalAsignado = caudalAsignado;
	}

	public String getCaudalTotal() {
		return caudalTotal;
	}

	public void setCaudalTotal(String caudalTotal) {
		this.caudalTotal = caudalTotal;
	}

	public String getActaResolutiva() {
		return actaResolutiva;
	}

	public void setActaResolutiva(String actaResolutiva) {
		this.actaResolutiva = actaResolutiva;
	}

	public String getBusquedaPersona() {
		return busquedaPersona;
	}

	public void setBusquedaPersona(String busquedaPersona) {
		this.busquedaPersona = busquedaPersona;
	}

	public boolean isAplicaRegAmbiental() {
		return aplicaRegAmbiental;
	}

	public void setAplicaRegAmbiental(boolean aplicaRegAmbiental) {
		this.aplicaRegAmbiental = aplicaRegAmbiental;
	}

	public String getArchRegulacionAmb() {
		return archRegulacionAmb;
	}

	public void setArchRegulacionAmb(String archRegulacionAmb) {
		this.archRegulacionAmb = archRegulacionAmb;
	}
	
	public Date getFechaDocRegulacionAmb() {
		return fechaDocRegulacionAmb;
	}
	
	public void setFechaDocRegulacionAmb(Date fechaDocRegulacionAmb) {
		this.fechaDocRegulacionAmb = fechaDocRegulacionAmb;
	}

	public String getArchivoKMZ() {
		return archivoKMZ;
	}

	public void setArchivoKMZ(String archivoKMZ) {
		this.archivoKMZ = archivoKMZ;
	}

	public String getArchivoPDF() {
		return archivoPDF;
	}

	public void setArchivoPDF(String archivoPDF) {
		this.archivoPDF = archivoPDF;
	}

	public GtrAsignacionSuelo getAsignacionSuelo() {
		return asignacionSuelo;
	}

	public void setAsignacionSuelo(GtrAsignacionSuelo asignacionSuelo) {
		this.asignacionSuelo = asignacionSuelo;
	}

	public String getArchContrato() {
		return archContrato;
	}

	public void setArchContrato(String archContrato) {
		this.archContrato = archContrato;
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

	public GtrContratoAsignacion getContrato() {
		return contrato;
	}

	public void setContrato(GtrContratoAsignacion contrato) {
		this.contrato = contrato;
	}

	public String getBusquedaResponsableEC() {
		return busquedaResponsableEC;
	}

	public void setBusquedaResponsableEC(String busquedaResponsableEC) {
		this.busquedaResponsableEC = busquedaResponsableEC;
	}

	public GtrAdministradorContrato getAdminContrato() {
		return adminContrato;
	}

	public void setAdminContrato(GtrAdministradorContrato adminContrato) {
		this.adminContrato = adminContrato;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getConvocatoria() {
		return convocatoria;
	}

	public void setConvocatoria(String convocatoria) {
		this.convocatoria = convocatoria;
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

	public Date getFechaArcKMZ() {
		return fechaArcKMZ;
	}

	public void setFechaArcKMZ(Date fechaArcKMZ) {
		this.fechaArcKMZ = fechaArcKMZ;
	}

	public Date getFechaArcPDF() {
		return fechaArcPDF;
	}

	public void setFechaArcPDF(Date fechaArcPDF) {
		this.fechaArcPDF = fechaArcPDF;
	}

	public boolean isEdicionAS() {
		return edicionAS;
	}

	public void setEdicionAS(boolean edicionAS) {
		this.edicionAS = edicionAS;
	}

	public String getEstadoContrato() {
		return estadoContrato;
	}

	public void setEstadoContrato(String estadoContrato) {
		this.estadoContrato = estadoContrato;
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

	public String getEnteAprobador() {
		return enteAprobador;
	}
	
	public void setEnteAprobador(String enteAprobador) {
		this.enteAprobador = enteAprobador;
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

	public GtrEntregablesContrato getEntregableC() {
		return entregableC;
	}

	public void setEntregableC(GtrEntregablesContrato entregableC) {
		this.entregableC = entregableC;
	}

	public String getDniAdmin() {
		return dniAdmin;
	}

	public void setDniAdmin(String dniAdmin) {
		this.dniAdmin = dniAdmin;
	}

	public String getDireccionAdmin() {
		return direccionAdmin;
	}

	public void setDireccionAdmin(String direccionAdmin) {
		this.direccionAdmin = direccionAdmin;
	}

	public String getEstadoAdmin() {
		return estadoAdmin;
	}

	public void setEstadoAdmin(String estadoAdmin) {
		this.estadoAdmin = estadoAdmin;
	}

	public boolean isEdicionAdmin() {
		return edicionAdmin;
	}
	
	public void setEdicionAdmin(boolean edicionAdmin) {
		this.edicionAdmin = edicionAdmin;
	}
	
	public Integer getIdAsignacion() {
		return idAsignacion;
	}

	public void setIdAsignacion(Integer idAsignacion) {
		this.idAsignacion = idAsignacion;
	}

	public String getInforGestionTerr() {
		return inforGestionTerr;
	}

	public void setInforGestionTerr(String inforGestionTerr) {
		this.inforGestionTerr = inforGestionTerr;
	}

	public String getFuenteHidrica() {
		return fuenteHidrica;
	}

	public void setFuenteHidrica(String fuenteHidrica) {
		this.fuenteHidrica = fuenteHidrica;
	}

	public String getInforCaracterizacion() {
		return inforCaracterizacion;
	}

	public void setInforCaracterizacion(String inforCaracterizacion) {
		this.inforCaracterizacion = inforCaracterizacion;
	}

	public Date getFechaDocCaracte() {
		return fechaDocCaracte;
	}

	public void setFechaDocCaracte(Date fechaDocCaracte) {
		this.fechaDocCaracte = fechaDocCaracte;
	}

	public Date getFechaSubidaCaracte() {
		return fechaSubidaCaracte;
	}

	public void setFechaSubidaCaracte(Date fechaSubidaCaracte) {
		this.fechaSubidaCaracte = fechaSubidaCaracte;
	}

	public String getInforUsoSuelo() {
		return inforUsoSuelo;
	}

	public void setInforUsoSuelo(String inforUsoSuelo) {
		this.inforUsoSuelo = inforUsoSuelo;
	}

	public Date getFechaDocUsoSuelo() {
		return fechaDocUsoSuelo;
	}

	public void setFechaDocUsoSuelo(Date fechaDocUsoSuelo) {
		this.fechaDocUsoSuelo = fechaDocUsoSuelo;
	}

	public Date getFechaSubidaUsoS() {
		return fechaSubidaUsoS;
	}

	public void setFechaSubidaUsoS(Date fechaSubidaUsoS) {
		this.fechaSubidaUsoS = fechaSubidaUsoS;
	}

	public String getInforDisponibilidad() {
		return inforDisponibilidad;
	}

	public void setInforDisponibilidad(String inforDisponibilidad) {
		this.inforDisponibilidad = inforDisponibilidad;
	}

	public Date getFechaDocDisponibil() {
		return fechaDocDisponibil;
	}

	public void setFechaDocDisponibil(Date fechaDocDisponibil) {
		this.fechaDocDisponibil = fechaDocDisponibil;
	}

	public Date getFechaSubidaDisponi() {
		return fechaSubidaDisponi;
	}

	public void setFechaSubidaDisponi(Date fechaSubidaDisponi) {
		this.fechaSubidaDisponi = fechaSubidaDisponi;
	}

	public String getInforConsolidado() {
		return inforConsolidado;
	}

	public void setInforConsolidado(String inforConsolidado) {
		this.inforConsolidado = inforConsolidado;
	}

	public Date getFechaDocConsolidado() {
		return fechaDocConsolidado;
	}

	public void setFechaDocConsolidado(Date fechaDocConsolidado) {
		this.fechaDocConsolidado = fechaDocConsolidado;
	}

	public Date getFechaSubidaConsol() {
		return fechaSubidaConsol;
	}

	public void setFechaSubidaConsol(Date fechaSubidaConsol) {
		this.fechaSubidaConsol = fechaSubidaConsol;
	}

	public Date getFechaDocResolucion() {
		return fechaDocResolucion;
	}

	public void setFechaDocResolucion(Date fechaDocResolucion) {
		this.fechaDocResolucion = fechaDocResolucion;
	}

	public Date getFechaSubidaResol() {
		return fechaSubidaResol;
	}

	public void setFechaSubidaResol(Date fechaSubidaResol) {
		this.fechaSubidaResol = fechaSubidaResol;
	}

	public Date getFechaDocActaResol() {
		return fechaDocActaResol;
	}

	public void setFechaDocActaResol(Date fechaDocActaResol) {
		this.fechaDocActaResol = fechaDocActaResol;
	}

	public Date getFechaSubidaActaRes() {
		return fechaSubidaActaRes;
	}

	public void setFechaSubidaActaRes(Date fechaSubidaActaRes) {
		this.fechaSubidaActaRes = fechaSubidaActaRes;
	}

	public Date getFechaDocConvoca() {
		return fechaDocConvoca;
	}

	public void setFechaDocConvoca(Date fechaDocConvoca) {
		this.fechaDocConvoca = fechaDocConvoca;
	}

	public Date getFechaSubidaConvoca() {
		return fechaSubidaConvoca;
	}

	public void setFechaSubidaConvoca(Date fechaSubidaConvoca) {
		this.fechaSubidaConvoca = fechaSubidaConvoca;
	}

	public String getFiguraLegal() {
		return figuraLegal;
	}

	public void setFiguraLegal(String figuraLegal) {
		this.figuraLegal = figuraLegal;
	}

	public Date getFechaSubRegAmbiental() {
		return fechaSubRegAmbiental;
	}

	public void setFechaSubRegAmbiental(Date fechaSubRegAmbiental) {
		this.fechaSubRegAmbiental = fechaSubRegAmbiental;
	}

	public Date getFechaSubidaKMZ() {
		return fechaSubidaKMZ;
	}

	public void setFechaSubidaKMZ(Date fechaSubidaKMZ) {
		this.fechaSubidaKMZ = fechaSubidaKMZ;
	}

	public Date getFechaSubidaPDF() {
		return fechaSubidaPDF;
	}

	public void setFechaSubidaPDF(Date fechaSubidaPDF) {
		this.fechaSubidaPDF = fechaSubidaPDF;
	}

	public Date getFechaDocNotificacion() {
		return fechaDocNotificacion;
	}

	public void setFechaDocNotificacion(Date fechaDocNotificacion) {
		this.fechaDocNotificacion = fechaDocNotificacion;
	}

	public Date getFechaSubidaNotificacion() {
		return fechaSubidaNotificacion;
	}

	public void setFechaSubidaNotificacion(Date fechaSubidaNotificacion) {
		this.fechaSubidaNotificacion = fechaSubidaNotificacion;
	}

	public UploadedFile getFilePdf() {
		return filePdf;
	}

	public void setFilePdf(UploadedFile filePdf) {
		this.filePdf = filePdf;
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

	public Date getFechaDocTdr() {
		return fechaDocTdr;
	}

	public void setFechaDocTdr(Date fechaDocTdr) {
		this.fechaDocTdr = fechaDocTdr;
	}

	public Date getFechaSubidaTdr() {
		return fechaSubidaTdr;
	}

	public void setFechaSubidaTdr(Date fechaSubidaTdr) {
		this.fechaSubidaTdr = fechaSubidaTdr;
	}

	public Date getFechaDocPliego() {
		return fechaDocPliego;
	}

	public void setFechaDocPliego(Date fechaDocPliego) {
		this.fechaDocPliego = fechaDocPliego;
	}

	public Date getFechaSubPliego() {
		return fechaSubPliego;
	}

	public void setFechaSubPliego(Date fechaSubPliego) {
		this.fechaSubPliego = fechaSubPliego;
	}

	public Date getFechaDocContrato() {
		return fechaDocContrato;
	}

	public void setFechaDocContrato(Date fechaDocContrato) {
		this.fechaDocContrato = fechaDocContrato;
	}

	public Date getFechaSubContrato() {
		return fechaSubContrato;
	}

	public void setFechaSubContrato(Date fechaSubContrato) {
		this.fechaSubContrato = fechaSubContrato;
	}

	public Date getFechaInicioC() {
		return fechaInicioC;
	}

	public void setFechaInicioC(Date fechaInicioC) {
		this.fechaInicioC = fechaInicioC;
	}

	public Date getFechaFinC() {
		return fechaFinC;
	}

	public void setFechaFinC(Date fechaFinC) {
		this.fechaFinC = fechaFinC;
	}

	public int getIdEntregable() {
		return idEntregable;
	}

	public void setIdEntregable(int idEntregable) {
		this.idEntregable = idEntregable;
	}

	public Date getFechaMaxEntregaDoc() {
		return fechaMaxEntregaDoc;
	}

	public void setFechaMaxEntregaDoc(Date fechaMaxEntregaDoc) {
		this.fechaMaxEntregaDoc = fechaMaxEntregaDoc;
	}

	public Date getFechaSubidaDoc() {
		return fechaSubidaDoc;
	}

	public void setFechaSubidaDoc(Date fechaSubidaDoc) {
		this.fechaSubidaDoc = fechaSubidaDoc;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public Date getFechaInicioAdmin() {
		return fechaInicioAdmin;
	}

	public void setFechaInicioAdmin(Date fechaInicioAdmin) {
		this.fechaInicioAdmin = fechaInicioAdmin;
	}

	public Date getFechaFinAdmin() {
		return fechaFinAdmin;
	}

	public void setFechaFinAdmin(Date fechaFinAdmin) {
		this.fechaFinAdmin = fechaFinAdmin;
	}

	public List<GtrHistorialSeguimiento> getL_seguimiento() {
		return l_seguimiento;
	}

	public void setL_seguimiento(List<GtrHistorialSeguimiento> l_seguimiento) {
		this.l_seguimiento = l_seguimiento;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getTipoCatalogo() {
		return tipoCatalogo;
	}

	public void setTipoCatalogo(String tipoCatalogo) {
		this.tipoCatalogo = tipoCatalogo;
	}

	public Integer getNumeroAnios() {
		return numeroAnios;
	}

	public void setNumeroAnios(Integer numeroAnios) {
		this.numeroAnios = numeroAnios;
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

	public String getResponsableConcesion() {
		return responsableConcesion;
	}

	public void setResponsableConcesion(String responsableConcesion) {
		this.responsableConcesion = responsableConcesion;
	}

	public String getUsuCaracterizacion() {
		return usuCaracterizacion;
	}

	public void setUsuCaracterizacion(String usuCaracterizacion) {
		this.usuCaracterizacion = usuCaracterizacion;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public String getUsuActaResol() {
		return usuActaResol;
	}

	public void setUsuActaResol(String usuActaResol) {
		this.usuActaResol = usuActaResol;
	}

	public String getNombreResponsablePorDir() {
		return nombreResponsablePorDir;
	}

	public void setNombreResponsablePorDir(String nombreResponsablePorDir) {
		this.nombreResponsablePorDir = nombreResponsablePorDir;
	}

	public String getUsuArchivoKMZ() {
		return usuArchivoKMZ;
	}

	public void setUsuArchivoKMZ(String usuArchivoKMZ) {
		this.usuArchivoKMZ = usuArchivoKMZ;
	}

	public String getUsuArchivoPDF() {
		return usuArchivoPDF;
	}

	public void setUsuArchivoPDF(String usuArchivoPDF) {
		this.usuArchivoPDF = usuArchivoPDF;
	}

	public String getNotificacionApNe() {
		return notificacionApNe;
	}

	public void setNotificacionApNe(String notificacionApNe) {
		this.notificacionApNe = notificacionApNe;
	}

	public BigDecimal getSuperficieSolicitada() {
		return superficieSolicitada;
	}

	public void setSuperficieSolicitada(BigDecimal superficieSolicitada) {
		this.superficieSolicitada = superficieSolicitada;
	}

	public String getUnidadMedSupSol() {
		return unidadMedSupSol;
	}

	public void setUnidadMedSupSol(String unidadMedSupSol) {
		this.unidadMedSupSol = unidadMedSupSol;
	}

	public BigDecimal getSuperficieAsignada() {
		return superficieAsignada;
	}

	public void setSuperficieAsignada(BigDecimal superficieAsignada) {
		this.superficieAsignada = superficieAsignada;
	}

	public String getUnidadMedSupAsignada() {
		return unidadMedSupAsignada;
	}

	public void setUnidadMedSupAsignada(String unidadMedSupAsignada) {
		this.unidadMedSupAsignada = unidadMedSupAsignada;
	}

	public List<GtrAsignacionSuelo> getLstAsignacionSuelos() {
		return lstAsignacionSuelos;
	}

	public void setLstAsignacionSuelos(
			List<GtrAsignacionSuelo> lstAsignacionSuelos) {
		this.lstAsignacionSuelos = lstAsignacionSuelos;
	}

	public List<SelectItem> getLstZonas() {
		return lstZonas;
	}

	public void setLstZonas(List<SelectItem> lstZonas) {
		this.lstZonas = lstZonas;
	}

	public List<SelectItem> getLstTipoCatalogo() {
		return lstTipoCatalogo;
	}

	public void setLstTipoCatalogo(List<SelectItem> lstTipoCatalogo) {
		this.lstTipoCatalogo = lstTipoCatalogo;
	}

	public List<SelectItem> getSlctEstados() {
		return slctEstados;
	}

	public void setSlctEstados(List<SelectItem> slctEstados) {
		this.slctEstados = slctEstados;
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
	
	public List<SelectItem> getLstFiguraLegal() {
		return lstFiguraLegal;
	}

	public void setLstFiguraLegal(List<SelectItem> lstFiguraLegal) {
		this.lstFiguraLegal = lstFiguraLegal;
	}

	public List<SelectItem> getLstEnteAprobador() {
		return lstEnteAprobador;
	}

	public void setLstEnteAprobador(List<SelectItem> lstEnteAprobador) {
		this.lstEnteAprobador = lstEnteAprobador;
	}

	public List<SelectItem> getLstTipoActividad() {
		return lstTipoActividad;
	}

	public void setLstTipoActividad(List<SelectItem> lstTipoActividad) {
		this.lstTipoActividad = lstTipoActividad;
	}

	public List<SelectItem> getLstTipoContrato() {
		return lstTipoContrato;
	}

	public void setLstTipoContrato(List<SelectItem> lstTipoContrato) {
		this.lstTipoContrato = lstTipoContrato;
	}

	public List<SelectItem> getLstUnidadMedida() {
		return lstUnidadMedida;
	}

	public void setLstUnidadMedida(List<SelectItem> lstUnidadMedida) {
		this.lstUnidadMedida = lstUnidadMedida;
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

	public BigDecimal getPeriodicidadPago() {
		return periodicidadPago;
	}

	public void setPeriodicidadPago(BigDecimal periodicidadPago) {
		this.periodicidadPago = periodicidadPago;
	}

	public String getUnidadTiempoPPago() {
		return unidadTiempoPPago;
	}

	public void setUnidadTiempoPPago(String unidadTiempoPPago) {
		this.unidadTiempoPPago = unidadTiempoPPago;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public List<GtrContratoAsignacion> getLstContratos() {
		return lstContratos;
	}

	public void setLstContratos(List<GtrContratoAsignacion> lstContratos) {
		this.lstContratos = lstContratos;
	}

	public List<SelectItem> getSlctEstadosContrato() {
		return slctEstadosContrato;
	}

	public void setSlctEstadosContrato(List<SelectItem> slctEstadosContrato) {
		this.slctEstadosContrato = slctEstadosContrato;
	}

	public String getNombreResponsableEntregable() {
		return nombreResponsableEntregable;
	}

	public void setNombreResponsableEntregable(String nombreResponsableEntregable) {
		this.nombreResponsableEntregable = nombreResponsableEntregable;
	}

	public String getNombreEntregable() {
		return nombreEntregable;
	}
	
	public void setNombreEntregable(String nombreEntregable) {
		this.nombreEntregable = nombreEntregable;
	}
	
	public String getUsuarioDocumento() {
		return usuarioDocumento;
	}

	public void setUsuarioDocumento(String usuarioDocumento) {
		this.usuarioDocumento = usuarioDocumento;
	}

	public List<GtrEntregablesContrato> getLstEntregables() {
		return lstEntregables;
	}

	public void setLstEntregables(List<GtrEntregablesContrato> lstEntregables) {
		this.lstEntregables = lstEntregables;
	}

	public List<SelectItem> getSlctEstadosEntregable() {
		return slctEstadosEntregable;
	}

	public void setSlctEstadosEntregable(List<SelectItem> slctEstadosEntregable) {
		this.slctEstadosEntregable = slctEstadosEntregable;
	}

	public String getNombreAdmin() {
		return nombreAdmin;
	}

	public void setNombreAdmin(String nombreAdmin) {
		this.nombreAdmin = nombreAdmin;
	}

	public List<GtrAdministradorContrato> getLstAdministradores() {
		return lstAdministradores;
	}

	public void setLstAdministradores(List<GtrAdministradorContrato> lstAdministradores) {
		this.lstAdministradores = lstAdministradores;
	}

	public List<SelectItem> getSlctEstadosAdmin() {
		return slctEstadosAdmin;
	}

	public void setSlctEstadosAdmin(List<SelectItem> slctEstadosAdmin) {
		this.slctEstadosAdmin = slctEstadosAdmin;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}
	
	public void setUsuarioSesion(String usuarioSesion) {
		this.usuarioSesion = usuarioSesion;
	}

	public String getUsuarioSesion() {
		return usuarioSesion;
	}
	
	public String getUsuUsoSuelo() {
		return usuUsoSuelo;
	}

	public void setUsuUsoSuelo(String usuUsoSuelo) {
		this.usuUsoSuelo = usuUsoSuelo;
	}

	public String getUsuDisponibilidad() {
		return usuDisponibilidad;
	}

	public void setUsuDisponibilidad(String usuDisponibilidad) {
		this.usuDisponibilidad = usuDisponibilidad;
	}

	public String getUsuConsolidado() {
		return usuConsolidado;
	}

	public void setUsuConsolidado(String usuConsolidado) {
		this.usuConsolidado = usuConsolidado;
	}

	public String getUsuResolucion() {
		return usuResolucion;
	}

	public void setUsuResolucion(String usuResolucion) {
		this.usuResolucion = usuResolucion;
	}

	public String getUsuConvocatoria() {
		return usuConvocatoria;
	}

	public void setUsuConvocatoria(String usuConvocatoria) {
		this.usuConvocatoria = usuConvocatoria;
	}

	public String getUsuRegAmbiental() {
		return usuRegAmbiental;
	}

	public void setUsuRegAmbiental(String usuRegAmbiental) {
		this.usuRegAmbiental = usuRegAmbiental;
	}

	public String getUsuNotificacion() {
		return usuNotificacion;
	}

	public void setUsuNotificacion(String usuNotificacion) {
		this.usuNotificacion = usuNotificacion;
	}

	public String getUsuTdr() {
		return usuTdr;
	}

	public void setUsuTdr(String usuTdr) {
		this.usuTdr = usuTdr;
	}

	public String getUsuPliego() {
		return usuPliego;
	}

	public void setUsuPliego(String usuPliego) {
		this.usuPliego = usuPliego;
	}

	public String getUsuContrato() {
		return usuContrato;
	}

	public void setUsuContrato(String usuContrato) {
		this.usuContrato = usuContrato;
	}
	
	public Timestamp getFechaActual() {
		return fechaActual;
	}
	
	public void setFechaActual(Timestamp fechaActual) {
		this.fechaActual = fechaActual;
	}

	private void cargarEstados() {
		cargarEstadosAsignacion();
		cargarEstadosEntregables();
	}

	private void cargarEstadosAsignacion(){
		getSlctEstados().add(new SelectItem(ENPROCESO, VALORENPROCESO));
		getSlctEstados().add(new SelectItem(APROBADO, VALORAPROBADO));
		getSlctEstados().add(new SelectItem(NEGADO, VALORNEGADO));
	}
	
	private void cargarEstadosEntregables(){
		getSlctEstadosEntregable().add(new SelectItem(ACTIVO, VALORACTIVO));
		getSlctEstadosEntregable().add(new SelectItem(INACTIVO, VALORINACTIVO));
	}	
	
	private void inicializarFechas(){
		setFechaActual(new Timestamp(new Date().getTime()));
		// Asignación
		setFechaDocRegulacionAmb(new Date()); setFechaArcKMZ(new Date());
		setFechaArcPDF(new Date()); setFechaDocCaracte(new Date());
		setFechaDocUsoSuelo(new Date()); setFechaDocDisponibil(new Date());
		setFechaDocConsolidado(new Date()); setFechaDocResolucion(new Date());
		setFechaDocActaResol(new Date()); setFechaDocConvoca(new Date());
		setFechaDocNotificacion(new Date());
		// Contrato
		setFechaDocTdr(new Date()); setFechaDocPliego(new Date());
		setFechaDocContrato(new Date()); 
		setFechaInicioC(new Timestamp(new Date().getTime()));
		setFechaFinC(new Timestamp(new Date().getTime()));
		// Entregables
		setFechaMaxEntregaDoc(new Timestamp(new Date().getTime()));
		// Administradores
		setFechaInicioAdmin(new Date()); setFechaFinAdmin(new Date());
	}
	
	public void switchRegAmbiental(){
		if(isAplicaRegAmbiental() == false){
			setArchRegulacionAmb(""); setUsuRegAmbiental("");
			setFechaDocRegulacionAmb(null); setFechaSubRegAmbiental(null);
		}else {
			setFechaDocRegulacionAmb(new Date()); setFechaSubRegAmbiental(new Timestamp(new Date().getTime()));
		}
	}
	
	public void cargarBusqueda() {
		try {
			getLstPersonas().clear();
			getLstPersonas().add(
					new SelectItem(SELECCIONAR, " --Seleccione-- "));
			List<GenFuncionariosInstitucion> list = mngGeneral
					.findAllfuncionarios();
			for (GenFuncionariosInstitucion i : list) {
				getLstPersonas().add(
						new SelectItem(i.getGenPersona().getPerDni(), i
								.getGenPersona().getPerDni()
								+ " | "
								+ i.getGenPersona().getPerNombres()
								+ " "
								+ i.getGenPersona().getPerApellidos()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cargarZona() {
		getLstZonas().clear();
		getLstZonas().add( new SelectItem(SELECCIONAR, " --Seleccione-- "));
		for (GenZona i : mngTerritorio.findAllZonasA()) {
			getLstZonas().add(new SelectItem(i.getZonId(), i.getZonNombre()));
		}
	}

	public void cargarTiposCatalogo() {
		getLstTipoCatalogo().clear();
		getLstTipoCatalogo().add( new SelectItem(SELECCIONAR, " --Seleccione-- "));
		List<GenCatalogoItemsDet> completo = mngTerritorio.AllofItems("cat_tipo_asignacionsuelo");
		for (GenCatalogoItemsDet i : completo) {
			getLstTipoCatalogo().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
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
	
	public void cargarTipoActividad() {
		getLstTipoActividad().clear();
		getLstTipoActividad().add( new SelectItem(SELECCIONAR, " --Seleccione-- "));
		for (GenCatalogoItemsDet i : mngTerritorio.AllofItems("cat_tipo_actividad")) {
			getLstTipoActividad().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}
	
	public void cargarTipoContrato() {
		getLstTipoContrato().clear();
		getLstTipoContrato().add( new SelectItem(SELECCIONAR, " --Seleccione-- "));
		for (GenCatalogoItemsDet i : mngTerritorio.AllofItems("cat_tipo_contrato")) {
			getLstTipoContrato().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}
	
	public void cargarFiguraLegal() {
	getLstFiguraLegal().clear();
	getLstFiguraLegal().add( new SelectItem(SELECCIONAR, " --Seleccione-- "));
		for (GenCatalogoItemsDet i : mngTerritorio.AllofItems("cat_figura_legal")) {
			getLstFiguraLegal().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}
	
	public void cargarEnteAprobador() {
		getLstEnteAprobador().clear();
		getLstEnteAprobador().add(new SelectItem(SELECCIONAR, " --Seleccione-- "));
		for (GenCatalogoItemsDet i : mngTerritorio.AllofItems("cat_ente_aprobador")) {
			getLstEnteAprobador().add( new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}
		
	public void cargarUnidadMedida() {
		getLstUnidadMedida().clear();
		getLstUnidadMedida().add( new SelectItem(SELECCIONAR, " --Seleccione-- "));
		for (GenCatalogoItemsDet i : mngTerritorio.AllofItems("cat_unidades_medida")) {
			getLstUnidadMedida().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}
	public String nuevoSuelo() {
		return "neAsignacionSuelo?faces-redirect=true";
	}

	public String cargarEditarAsignacionSuelo(GtrAsignacionSuelo asigSuelo) {
		setIdAsignacion(asigSuelo.getSueId());
		setZona(asigSuelo.getGenZona().getZonId());
		setActividad(asigSuelo.getSueActividad());
		setAsignacion(asigSuelo.getSueAsignacion());
		setEstado(asigSuelo.getSueEstado());
		setTipoCatalogo(asigSuelo.getSueTipo());
		setObservacion(asigSuelo.getSueObservacion());
		setNumeroAnios(asigSuelo.getSueNumeroAnios());
		setUnidadTiempo(asigSuelo.getSueUnidadTiempo());
		setNombre(asigSuelo.getSueNombre());
		setDescripcion(asigSuelo.getSueDescripcion());
		setInforGestionTerr(asigSuelo.getSueInforGestionTerritorial());
		setFuenteHidrica(asigSuelo.getSueFuenteHidrica());
		setConcesionFHidrica(asigSuelo.getSueConcesionFuenteHidrica());
		setResponsableConcesion(asigSuelo.getSueResponsableConcesion());
		setCaudalAsignado(asigSuelo.getSueCaudalAsignado());
		setCaudalTotal(asigSuelo.getSueCaudalTotal());
		setDniResponsablePorDir(asigSuelo.getSueResponsable());
		setNombreResponsablePorDir(buscarNombre(getDniResponsablePorDir()));
		setDireccionResponsable(asigSuelo.getSueDireccionResponsable());
		setFiguraLegal(asigSuelo.getSueFiguraLegal());
		setearAsignacionSuelo(getIdAsignacion());
		setAplicaRegAmbiental(asigSuelo.getSueAplicaRegulacionAmbiental());
		setArchRegulacionAmb(asigSuelo.getSueArchivoRegulacionAmb());
		setFechaDocRegulacionAmb(asigSuelo.getSueFechaArchivoRegAmb());
		setUsuRegAmbiental(asigSuelo.getSueUsuarioSubidaRegAmb());
		setFechaSubRegAmbiental(asigSuelo.getSueFechaSubidaRegAmb());
		setArchivoKMZ(asigSuelo.getSueArchivoKmz());
		setFechaArcKMZ(asigSuelo.getSueFechaArchivoKmz());
		setFechaSubidaKMZ(asigSuelo.getSueFechaSubidaKmz());
		setUsuArchivoKMZ(asigSuelo.getSueUsuarioArchivoKmz());
		setArchivoPDF(asigSuelo.getSueArchivoPdf());
		setFechaArcPDF(asigSuelo.getSueFechaArchivoPdf());
		setFechaSubidaPDF(asigSuelo.getSueFechaSubidaPdf());
		setUsuArchivoPDF(asigSuelo.getSueUsuarioArchivoPdf());
		setSuperficieAsignada(asigSuelo.getSueSuperficieAsignada());
		setUnidadMedSupAsignada(asigSuelo.getSueUnidadSupAsignada());
		setSuperficieSolicitada(asigSuelo.getSueSuperficieSolicitada());
		setUnidadMedSupSol(asigSuelo.getSueUnidadSupSolicitada());
		setEnteAprobador(asigSuelo.getSueEnteAprobador());
		// informes
		setInforCaracterizacion(asigSuelo.getSueInforCaracterizacion());
		setFechaDocCaracte(asigSuelo.getSueFechaDocCaracterizacion());
		setFechaSubidaCaracte(asigSuelo.getSueFechaSubidaCaracterizacio());
		setUsuCaracterizacion(asigSuelo.getSueUsuarioCaracterizacion());
		setInforUsoSuelo(asigSuelo.getSueInforUsoSuelo());
		setFechaDocUsoSuelo(asigSuelo.getSueFechaDocUsoSuelo());
		setFechaSubidaUsoS(asigSuelo.getSueFechaSubidaUsoSuelo());
		setUsuUsoSuelo(asigSuelo.getSueUsuarioUsoSuelo());
		setInforDisponibilidad(asigSuelo.getSueInforDisponibilidad());
		setFechaDocDisponibil(asigSuelo.getSueFechaDocDisponibilidad());
		setFechaSubidaDisponi(asigSuelo.getSueFechaSubidaDisponibilidad());
		setUsuDisponibilidad(asigSuelo.getSueUsuarioDisponibilidad());
		setInforConsolidado(asigSuelo.getSueInforConsolidado());
		setFechaDocConsolidado(asigSuelo.getSueFechaDocConsolidado());
		setFechaSubidaConsol(asigSuelo.getSueFechaSubidaConsolidado());
		setUsuConsolidado(asigSuelo.getSueUsuarioConsolidado());
		setResolucion(asigSuelo.getSueResolucion());
		setFechaDocResolucion(asigSuelo.getSueFechaDocResolucion());
		setFechaSubidaResol(asigSuelo.getSueFechaSubidaResolucion());
		setUsuResolucion(asigSuelo.getSueUsuarioResolucion());
		setActaResolutiva(asigSuelo.getSueActaResolutiva());
		setFechaDocActaResol(asigSuelo.getSueFechaDocResolutiva());
		setFechaSubidaActaRes(asigSuelo.getSueFechaSubidaResolutiva());
		setUsuActaResol(asigSuelo.getSueUsuarioResolutiva());
		setConvocatoria(asigSuelo.getSueConvocatoria());
		setFechaDocConvoca(asigSuelo.getSueFechaDocConvocatoria());
		setFechaSubidaConvoca(asigSuelo.getSueFechaSubidaConvocatoria());
		setUsuConvocatoria(asigSuelo.getSueUsuarioConvocatoria());
		setNotificacionApNe(asigSuelo.getSueNotificacionApNe());
		setFechaDocNotificacion(asigSuelo.getSueFechaDocNotificacion());
		setFechaSubidaNotificacion(asigSuelo.getSueFechaSubidaNotificacion());
		setUsuNotificacion(asigSuelo.getSueUsuarioNotificacion());
		// Fechas
		if(validaFechaVacia(asigSuelo.getSueFechaArchivoRegAmb()) && isAplicaRegAmbiental()){
			setFechaDocRegulacionAmb(new Date());
		}
		if(validaFechaVacia(asigSuelo.getSueFechaArchivoKmz())){
			setFechaArcKMZ(new Date());
		}
		if(validaFechaVacia(asigSuelo.getSueFechaArchivoPdf())){
			
			setFechaArcPDF(new Date());
		}
		if(validaFechaVacia(asigSuelo.getSueFechaDocCaracterizacion())){
			setFechaDocCaracte(new Date());
		}
		if(validaFechaVacia(asigSuelo.getSueFechaDocUsoSuelo())){
			setFechaDocUsoSuelo(new Date());
		}
		if(validaFechaVacia(asigSuelo.getSueFechaDocDisponibilidad())){
			setFechaDocDisponibil(new Date());
		}
		if(validaFechaVacia(asigSuelo.getSueFechaDocConsolidado())){
			setFechaDocConsolidado(new Date());
		}
		if(validaFechaVacia(asigSuelo.getSueFechaDocResolucion())){
			setFechaDocResolucion(new Date());
		}
		if(validaFechaVacia(asigSuelo.getSueFechaDocResolutiva())){
			setFechaDocActaResol(new Date()); 
		}
		if(validaFechaVacia(asigSuelo.getSueFechaDocConvocatoria())){
			setFechaDocConvoca(new Date());
		}
		if(validaFechaVacia(asigSuelo.getSueFechaDocNotificacion())){
			setFechaDocNotificacion(new Date());
		}
		
		setFechaActual(new Timestamp(new Date().getTime()));
		setEdicionAS(true);
		cargarContratoPorAsignacion(getIdAsignacion());
		return "neAsignacionSuelo?faces-redirect=true";
	}
	public boolean validaFechaVacia(Date fecha){
		if(fecha == null || fecha.equals("")){
			return true;
		}else 
			return false;
	}
	
	public void cargarContratoPorAsignacion(int idAsignacion){
		setContrato(mngTerritorio.findContratoByIdAsignacion(idAsignacion));
		cargarContrato(getContrato());
	}

	public void cargarContrato(GtrContratoAsignacion contrato){
		if(contrato !=null){
		setEdicionContrato(true);
		setIdContrato(contrato.getCasId());
		setTdrContrato(contrato.getCasTdr());
		setFechaDocTdr(contrato.getCasFechaDocTdr());
		setFechaSubidaTdr(contrato.getCasFechaSubidaTdr());
		setUsuTdr(contrato.getCasUsuarioTdr());
		setPliegoContrato(contrato.getCasPliego());
		setFechaDocPliego(contrato.getCasFechaDocPliego());
		setFechaSubPliego(contrato.getCasFechaSubidaPliego());
		setUsuPliego(contrato.getCasUsuarioPliego());
		setArchContrato(contrato.getCasContrato());
		setFechaDocContrato(contrato.getCasFechaDocContrato());
		setFechaSubContrato(contrato.getCasFechaSubidaContrato());
		setUsuContrato(contrato.getCasUsuarioContrato());
		setFechaInicioC(contrato.getCasFechaInicio());
		setFechaFinC(contrato.getCasFechaFin());
		setArrendadorCotrato(contrato.getCasArrendador());
		setArrendatarioContrato(contrato.getCasArrendatario());
		setPrecio(contrato.getCasPrecio());
		setPeriodicidadPagoC(contrato.getCasPeriodicidadPago());
		setUnidadTiempoContrato(contrato.getCasUnidadTiempo());
		setEstadoContrato(contrato.getCasEstado());
		setTipoContrato(contrato.getCasTipo());
		// Fechas
		if(validaFechaVacia(contrato.getCasFechaDocTdr())){
			setFechaDocTdr(new Date()); 
		}
		if(validaFechaVacia(contrato.getCasFechaDocPliego())){
			setFechaDocPliego(new Date());
		}
		if(validaFechaVacia(contrato.getCasFechaDocContrato())){
			setFechaDocContrato(new Date()); 
		}
		//cargar entregables
		cargarLstEntregables(getIdContrato());
		//cargar administradores
		cargarLstAdministradores(getIdContrato());
	}else{
		Mensaje.crearMensajeINFO("Aún no se ha ingresado información de un contrato.");
	}
	}
	private void cargarLstEntregables(String idContrato){
		setLstEntregables(mngTerritorio.findAllEntregablesPorContrato(idContrato));
	}
	private void cargarLstAdministradores(String idContrato){
		setLstAdministradores(mngTerritorio.findAllAdministradoresPorContrato(idContrato));
	}
	
	public void guardarEditarAsignacionSuelos() {
		try {
			System.out.println("ingreso a metodo guardar");
			System.out.println("tipo catalogo "+getTipoCatalogo());
			if (validarCampos()) {
				GtrAsignacionSuelo as = new GtrAsignacionSuelo();
				as.setGenZona(mngTerritorio.findZonaById(getZona()));
				as.setSueActividad(Funciones.quitarEspacios(getActividad()));
				as.setSueAsignacion(Funciones.quitarEspacios(getAsignacion()));
				as.setSueEstado(getEstado());
				as.setSueTipo(getTipoCatalogo());
				as.setSueObservacion(Funciones.quitarEspacios(getObservacion()));
				as.setSueNumeroAnios(getNumeroAnios());
				as.setSueUnidadTiempo(getUnidadTiempo());
				as.setSueNombre(Funciones.quitarEspacios(getNombre()));
				as.setSueDescripcion(Funciones.quitarEspacios(getDescripcion()));
				as.setSueFuenteHidrica(Funciones.quitarEspacios(getFuenteHidrica()));
				as.setSueConcesionFuenteHidrica(Funciones.quitarEspacios(getConcesionFHidrica()));
				as.setSueResponsableConcesion(Funciones.quitarEspacios(getResponsableConcesion()));
				as.setSueCaudalAsignado(Funciones.quitarEspacios(getCaudalAsignado()));
				as.setSueCaudalTotal(Funciones.quitarEspacios(getCaudalTotal()));
				as.setSueResponsable(getDniResponsablePorDir());
				as.setSueDireccionResponsable(getDireccionResponsable());
				as.setSueFiguraLegal(getFiguraLegal());
				as.setSueAplicaRegulacionAmbiental(isAplicaRegAmbiental());
				if(aplicaRegulacionAmbiental()){
					as.setSueArchivoRegulacionAmb(getArchRegulacionAmb());
					as.setSueFechaArchivoRegAmb(getFechaDocRegulacionAmb());
					as.setSueFechaSubidaRegAmb(new Timestamp(getFechaSubRegAmbiental().getTime()));
					as.setSueUsuarioSubidaRegAmb(getUsuRegAmbiental());
				}else{
					as.setSueArchivoRegulacionAmb("");
					as.setSueFechaArchivoRegAmb(null);
					as.setSueFechaSubidaRegAmb(null);
					as.setSueUsuarioSubidaRegAmb("");
				}
				as.setSueSuperficieAsignada(getSuperficieAsignada());
				as.setSueUnidadSupAsignada(getUnidadMedSupAsignada());
				as.setSueSuperficieSolicitada(getSuperficieSolicitada());
				as.setSueUnidadSupSolicitada(getUnidadMedSupSol());
				as.setSueEnteAprobador(getEnteAprobador());
				
				if(isEdicionAS()){
					GtrAsignacionSuelo asignacionSuelo = mngTerritorio.findAsignacionSueloById(getIdAsignacion());
					setearAsignacionSuelo(getIdAsignacion());
					as.setSueId(getIdAsignacion());
					//Informes
					if(getInforGestionTerr() != null ){
					as.setSueInforGestionTerritorial(getInforGestionTerr());
					}else{
						as.setSueInforGestionTerritorial(asignacionSuelo.getSueInforGestionTerritorial());
					}
					if(getArchivoKMZ()!= null){
						as.setSueArchivoKmz(getArchivoKMZ());
						as.setSueFechaArchivoKmz(getFechaArcKMZ());
						as.setSueFechaSubidaKmz(new Timestamp(getFechaSubidaKMZ().getTime()));
						as.setSueUsuarioArchivoKmz(getUsuArchivoKMZ());
					}else{
						as.setSueArchivoKmz(asignacionSuelo.getSueArchivoKmz());
						as.setSueFechaArchivoKmz(asignacionSuelo.getSueFechaArchivoKmz());
						as.setSueFechaSubidaKmz(asignacionSuelo.getSueFechaSubidaKmz());
						as.setSueUsuarioArchivoKmz(asignacionSuelo.getSueUsuarioArchivoKmz());
					}
					if(getArchivoPDF()!= null){
						as.setSueArchivoPdf(getArchivoPDF());
						as.setSueFechaArchivoPdf(getFechaArcPDF());
						as.setSueFechaSubidaPdf(new Timestamp(getFechaSubidaPDF().getTime()));
						as.setSueUsuarioArchivoPdf(getUsuArchivoPDF());
					}else{
						as.setSueArchivoPdf(asignacionSuelo.getSueArchivoPdf());
						as.setSueFechaArchivoPdf(asignacionSuelo.getSueFechaArchivoPdf());
						as.setSueFechaSubidaPdf(asignacionSuelo.getSueFechaSubidaPdf());
						as.setSueUsuarioArchivoPdf(asignacionSuelo.getSueUsuarioArchivoPdf());
					}
					if(getInforConsolidado()!= null){
						as.setSueInforConsolidado(getInforConsolidado());
						as.setSueFechaDocConsolidado(getFechaDocConsolidado());
						as.setSueFechaSubidaConsolidado(new Timestamp(getFechaSubidaConsol().getTime()));
						as.setSueUsuarioConsolidado(getUsuConsolidado());
					}else{
						as.setSueInforConsolidado(asignacionSuelo.getSueInforConsolidado());
						as.setSueFechaDocConsolidado(asignacionSuelo.getSueFechaDocConsolidado());
						as.setSueFechaSubidaConsolidado(asignacionSuelo.getSueFechaSubidaConsolidado());
						as.setSueUsuarioConsolidado(asignacionSuelo.getSueUsuarioConsolidado());
					}
					if(getInforCaracterizacion()!= null){
						as.setSueInforCaracterizacion(getInforCaracterizacion());
						as.setSueFechaDocCaracterizacion(getFechaDocCaracte());
						as.setSueFechaSubidaCaracterizacio(new Timestamp(getFechaSubidaCaracte().getTime()));
						as.setSueUsuarioCaracterizacion(getUsuCaracterizacion());
					}else{
						as.setSueInforCaracterizacion(asignacionSuelo.getSueInforCaracterizacion());
						as.setSueFechaDocCaracterizacion(asignacionSuelo.getSueFechaDocCaracterizacion());
						as.setSueFechaSubidaCaracterizacio(asignacionSuelo.getSueFechaSubidaCaracterizacio());
						as.setSueUsuarioCaracterizacion(asignacionSuelo.getSueUsuarioCaracterizacion());
					}
					if(getInforUsoSuelo()!= null){
						as.setSueInforUsoSuelo(getInforUsoSuelo());
						as.setSueFechaDocUsoSuelo(getFechaDocUsoSuelo());
						as.setSueFechaSubidaUsoSuelo(new Timestamp(getFechaSubidaUsoS().getTime()));
						as.setSueUsuarioUsoSuelo(getUsuUsoSuelo());
					}else{
						as.setSueInforUsoSuelo(asignacionSuelo.getSueInforUsoSuelo());
						as.setSueFechaDocUsoSuelo(asignacionSuelo.getSueFechaDocUsoSuelo());
						as.setSueFechaSubidaUsoSuelo(asignacionSuelo.getSueFechaSubidaUsoSuelo());
						as.setSueUsuarioUsoSuelo(asignacionSuelo.getSueUsuarioUsoSuelo());
					}
					if(getInforDisponibilidad()!= null ){
						as.setSueInforDisponibilidad(getInforDisponibilidad());
						as.setSueFechaDocDisponibilidad(getFechaDocDisponibil());
						as.setSueFechaSubidaDisponibilidad(new Timestamp(getFechaSubidaDisponi().getTime()));
						as.setSueUsuarioDisponibilidad(getUsuDisponibilidad());
					}else{
						as.setSueInforDisponibilidad(asignacionSuelo.getSueInforDisponibilidad());
						as.setSueFechaDocDisponibilidad(asignacionSuelo.getSueFechaDocDisponibilidad());
						as.setSueFechaSubidaDisponibilidad(asignacionSuelo.getSueFechaSubidaDisponibilidad());
						as.setSueUsuarioDisponibilidad(asignacionSuelo.getSueUsuarioDisponibilidad());
					}
					if(getInforConsolidado()!= null){
						as.setSueInforConsolidado(getInforConsolidado());
						as.setSueFechaDocConsolidado(getFechaDocConsolidado());
						as.setSueFechaSubidaConsolidado(new Timestamp(getFechaSubidaConsol().getTime()));
						as.setSueUsuarioConsolidado(getUsuConsolidado());
					}else{
						as.setSueInforConsolidado(asignacionSuelo.getSueInforConsolidado());
						as.setSueFechaDocConsolidado(asignacionSuelo.getSueFechaDocConsolidado());
						as.setSueFechaSubidaConsolidado(asignacionSuelo.getSueFechaSubidaConsolidado());
						as.setSueUsuarioConsolidado(asignacionSuelo.getSueUsuarioConsolidado());
					}
					if(getResolucion()!= null){
						as.setSueResolucion(getResolucion());
						as.setSueFechaDocResolucion(getFechaDocResolucion());
						as.setSueFechaSubidaResolucion(new Timestamp(getFechaSubidaResol().getTime()));
						as.setSueUsuarioResolucion(getUsuResolucion());
					}else{
						as.setSueResolucion(asignacionSuelo.getSueResolucion());
						as.setSueFechaDocResolucion(asignacionSuelo.getSueFechaDocResolucion());
						as.setSueFechaSubidaResolucion(asignacionSuelo.getSueFechaSubidaResolucion());
						as.setSueUsuarioResolucion(asignacionSuelo.getSueUsuarioResolucion());
					}
					if(getActaResolutiva()!= null){
						as.setSueActaResolutiva(getActaResolutiva());
						as.setSueFechaDocResolutiva(getFechaDocActaResol());
						as.setSueFechaSubidaResolutiva(new Timestamp(getFechaSubidaActaRes().getTime()));
						as.setSueUsuarioResolutiva(getUsuActaResol());
					}else{
						as.setSueActaResolutiva(asignacionSuelo.getSueActaResolutiva());
						as.setSueFechaDocResolutiva(asignacionSuelo.getSueFechaDocResolutiva());
						as.setSueFechaSubidaResolutiva(asignacionSuelo.getSueFechaSubidaResolutiva());
						as.setSueUsuarioResolutiva(asignacionSuelo.getSueUsuarioResolutiva());
					}
					if(getActaResolutiva()!= null){
						as.setSueActaResolutiva(getActaResolutiva());
						as.setSueFechaDocResolutiva(getFechaDocActaResol());
						as.setSueFechaSubidaResolutiva(new Timestamp(getFechaSubidaActaRes().getTime()));
						as.setSueUsuarioResolutiva(getUsuActaResol());
					}else{
						as.setSueActaResolutiva(asignacionSuelo.getSueActaResolutiva());
						as.setSueFechaDocResolutiva(asignacionSuelo.getSueFechaDocResolutiva());
						as.setSueFechaSubidaResolutiva(asignacionSuelo.getSueFechaSubidaResolutiva());
						as.setSueUsuarioResolutiva(asignacionSuelo.getSueUsuarioResolutiva());
					}
					if(getConvocatoria()!= null){
						as.setSueConvocatoria(getConvocatoria());
						as.setSueFechaDocConvocatoria(getFechaDocConvoca());
						as.setSueFechaSubidaConvocatoria(new Timestamp(getFechaSubidaConvoca().getTime()));
						as.setSueUsuarioConvocatoria(getUsuConvocatoria());
					}else{
						as.setSueConvocatoria(asignacionSuelo.getSueConvocatoria());
						as.setSueFechaDocConvocatoria(asignacionSuelo.getSueFechaDocConvocatoria());
						as.setSueFechaSubidaConvocatoria(asignacionSuelo.getSueFechaSubidaConvocatoria());
						as.setSueUsuarioConvocatoria(asignacionSuelo.getSueUsuarioConvocatoria());
					}
					if(getNotificacionApNe()!= null){
						as.setSueNotificacionApNe(getNotificacionApNe());
						as.setSueFechaDocNotificacion(getFechaDocNotificacion());
						as.setSueFechaSubidaNotificacion(new Timestamp(getFechaSubidaNotificacion().getTime()));
						as.setSueUsuarioNotificacion(getUsuNotificacion());
					}else{
						as.setSueNotificacionApNe(asignacionSuelo.getSueNotificacionApNe());
						as.setSueFechaDocNotificacion(asignacionSuelo.getSueFechaDocNotificacion());
						as.setSueFechaSubidaNotificacion(asignacionSuelo.getSueFechaSubidaNotificacion());
						as.setSueUsuarioNotificacion(asignacionSuelo.getSueUsuarioNotificacion());
					}
					mngTerritorio.modificarAsignacionSuelo(as);
					Mensaje.crearMensajeINFO("Asignación de Suelo actualizada correctamente.");
				} else {
					setIdAsignacion(mngTerritorio.generarIdAsignacionSuelo());
					as.setSueId(getIdAsignacion());
					mngTerritorio.insertarAsignacionSuelo(as);
					setEdicionAS(true);
					Mensaje.crearMensajeINFO("Asignación de Suelo ingresada correctamente.");
				}
				setearAsignacionSuelo(getIdAsignacion());
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al almacenar suelo: " + e.getMessage());
			System.out.println("Error al almacenar suelo: ");
			e.printStackTrace();
		}
	}
	private boolean aplicaRegulacionAmbiental(){
		if(validarRegAmbiental()){
			return true;
		} else {
			return false;
		}
	}
	private boolean validarRegAmbiental(){
		boolean resultado = false;
		if(isAplicaRegAmbiental()){
			if(getFechaDocRegulacionAmb() == null){
				Mensaje.crearMensajeWARN("Se debe seleccionar la fecha del documento.");
				resultado = false;
			}else {
				resultado = true;
			}
		}
		return resultado;
	}
	
	private void setearAsignacionSuelo(int idSuelo){
		try {
			setAsignacionSuelo(mngTerritorio.findAsignacionSueloById(getIdAsignacion()));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al buscar Asignación Suelo por ID. ");
		}
	}
//
	public boolean validarCampos() {
		if (getZona().equals(SELECCIONAR)) {
			Mensaje.crearMensajeERROR("Campo zona requerido");
			return false;
		} else if (getTipoCatalogo().equals(SELECCIONAR)) {
			Mensaje.crearMensajeERROR("Campo tipo requerido");
			return false;
		} else if (getUnidadTiempo().equals(SELECCIONAR)) {
			Mensaje.crearMensajeERROR("Seleccione la unidad de tiempo de la Temporalidad. ");
			return false;
		} else if(getEnteAprobador().equals(SELECCIONAR)){
			Mensaje.crearMensajeERROR("Seleccione el Ente Aprobador. ");
			return false;
		}else if(getFiguraLegal().equals(SELECCIONAR)){
			Mensaje.crearMensajeERROR("Seleccione la Figura Legal. ");
			return false;
		} else if(getActividad().equals(SELECCIONAR)){
			Mensaje.crearMensajeERROR("Seleccione la Actividad. ");
			return false;
		}else if(getUnidadMedSupAsignada().equals(SELECCIONAR)){
			Mensaje.crearMensajeERROR("Seleccione la unidad de medida de la Superficie Asignada. ");
			return false;
		}else if(getUnidadMedSupSol().equals(SELECCIONAR)){
			Mensaje.crearMensajeERROR("Seleccione la unidad de medida de la Superficie Solicitada. ");
			return false;
		}else {
			return true;
		}
	}

	public String cancelar() {
		limpiarDatos();
		cargarAsignacionSuelo();
		return "asignacionSuelos?faces-redirect=true";
	}

	private void limpiarDatos() {
		setIdAsignacion(0);
		setZona(null); setActividad(SELECCIONAR); setAsignacion("");
		setEstado(ENPROCESO); setTipoCatalogo(SELECCIONAR);
		setObservacion(""); setUnidadTiempo(SELECCIONAR);
		setNombre(""); setDescripcion(""); setInforGestionTerr("");
		setFuenteHidrica(""); setConcesionFHidrica("");
		setResponsableConcesion(""); setCaudalAsignado("");
		setCaudalTotal(""); setDniResponsablePorDir("");
		setNombreResponsablePorDir(""); setDireccionResponsable("");
		setFiguraLegal(SELECCIONAR); setAsignacionSuelo(null);
		setAplicaRegAmbiental(false); setArchRegulacionAmb("");
		setFechaDocRegulacionAmb(new Date()); setUsuRegAmbiental("");
		setFechaSubRegAmbiental(new Timestamp(new Date().getTime()));
		setArchivoKMZ(""); setFechaArcKMZ(new Date());
		setFechaSubidaKMZ(new Timestamp(new Date().getTime()));
		setUsuArchivoKMZ("");
		setArchivoPDF(""); setFechaArcPDF(new Date());
		setFechaSubidaPDF(new Timestamp(new Date().getTime()));
		setUsuArchivoPDF("");
		setSuperficieAsignada(BigDecimal.ZERO);	setUnidadMedSupAsignada(SELECCIONAR);
		setSuperficieSolicitada(BigDecimal.ZERO); setUnidadMedSupSol(SELECCIONAR);
		setEnteAprobador(SELECCIONAR);
		// informes
		setInforCaracterizacion(""); setFechaDocCaracte(new Date());
		setFechaSubidaCaracte(new Timestamp(new Date().getTime()));
		setUsuCaracterizacion("");
		setInforUsoSuelo(""); setFechaDocUsoSuelo(new Date());
		setFechaSubidaUsoS(new Timestamp(new Date().getTime()));
		setUsuUsoSuelo("");
		setInforDisponibilidad(""); setFechaDocDisponibil(new Date());
		setFechaSubidaDisponi(new Timestamp(new Date().getTime()));
		setUsuDisponibilidad("");
		setInforConsolidado(""); setFechaDocConsolidado(new Date());
		setFechaSubidaConsol(new Timestamp(new Date().getTime()));
		setUsuConsolidado("");
		setResolucion(""); setFechaDocResolucion(new Date());
		setFechaSubidaResol(new Timestamp(new Date().getTime()));
		setUsuResolucion("");
		setActaResolutiva(""); setFechaDocActaResol(new Date());
		setFechaSubidaActaRes(new Timestamp(new Date().getTime()));
		setUsuActaResol("");
		setConvocatoria(""); setFechaDocConvoca(new Date());
		setFechaSubidaConvoca(new Timestamp(new Date().getTime()));
		setUsuConvocatoria("");
		setNotificacionApNe(""); setFechaDocNotificacion(new Date());
		setFechaSubidaNotificacion(new Timestamp(new Date().getTime()));
		setUsuNotificacion("");
		
		setEdicionAS(false);
		limpiarCamposAdmin();
		limpiarCamposEntregable();
		limpiarCamposContrato();
		
	}

	private void cargarAsignacionSuelo() {
		getLstAsignacionSuelos().clear();
		getLstAsignacionSuelos().addAll(mngTerritorio.findAllAsignacionSuelo());
	}
	
//	public void editarAsignacionSuelo() {
//		try {
//			if (validarCampos()) {
//				GtrAsignacionSuelo as = new GtrAsignacionSuelo();
//				as.setSueId(getId());
//				as.setSueTipo(getTipoCatalogo());
//				as.setSueNombre(Funciones.quitarEspacios(getNombre()));
//				as.setSueDescripcion(Funciones.quitarEspacios(getDescripcion()));
//				as.setSueActividad(Funciones.quitarEspacios(getActividad()));
//				as.setGenZona(mngTerritorio.findZonaById(getZona()));
//				as.setSueAsignacion(Funciones.quitarEspacios(getAsignacion()));
//				as.setSueMetros(getMetros());
//				as.setSueNumeroAnios(getSueNumeroanios());
//				as.setSueUnidadTiempo(getUnidadTiempo());
//				as.setSueObservacion(Funciones.quitarEspacios(getObservacion()));
//				as.setSueRegulacionAmbiental(Funciones.quitarEspacios(getRegAmbiental()));
//				as.setSueCoordenadaX(getCoordenadaX());
//				as.setSueCoordenadaY(getCoordenadaY());
//				as.setSueSuperficieSolicitada(getSuperficieSolicitada());
//				as.setSueSuperficieAsignada(getSuperficieAsignada());
//				as.setSueOcupado(isOcupado());
//				as.setSueOcupadoPor(getOcupadoPor());
//				as.setSueTipoUso(getTipoUso());
//				as.setSueEstado(getEstado());
//				as.setSueResponsable(Funciones.quitarEspacios(getDniResponsablePorDir()));
//				as.setSueDireccionResponsable(Funciones.quitarEspacios(getDireccionResponsable()));
//				as.setSueFuenteHidrica(Funciones.quitarEspacios(getFuenteHidrica()));
//				as.setSueConcesionFuenteHidrica(Funciones.quitarEspacios(getConcesionFHidrica()));
//				as.setSueResponsableConcesion(Funciones.quitarEspacios(getResponsableConcesion()));
//
//				GtrAsignacionSuelo asignacionSuelo = mngTerritorio
//						.findAsignacionSueloById(getId());
//
//				if (getInforGestionTerr() != null
//						|| !getInforGestionTerr().isEmpty()) {
//					as.setSueInforGestionTerritorial(getInforGestionTerr());
//				} else {
//					as.setSueInforGestionTerritorial(asignacionSuelo
//							.getSueInforGestionTerritorial());
//				}
//				if (getInforActualGT() != null || !getInforActualGT().isEmpty()) {
//					as.setSueInforActualGestionTerr(getInforActualGT());
//				} else {
//					as.setSueInforActualGestionTerr(asignacionSuelo
//							.getSueInforActualGestionTerr());
//				}
//				if (getInforConsolidado() != null
//						|| !getInforConsolidado().isEmpty()) {
//					as.setSueInforConsolidado(getInforConsolidado());
//				} else {
//					as.setSueInforConsolidado(asignacionSuelo
//							.getSueInforConsolidado());
//				}
//				if (getInforPronJuidico() != null
//						|| !getInforPronJuidico().isEmpty()) {
//					as.setSueInforPronunciamientoJurid(getInforPronJuidico());
//				} else {
//					as.setSueInforPronunciamientoJurid(asignacionSuelo
//							.getSueInforPronunciamientoJurid());
//				}
//				if (getInforConsolidado2() != null
//						|| getInforConsolidado2().isEmpty()) {
//					as.setSueInforConsolidado2(getInforConsolidado2());
//				} else {
//					as.setSueInforConsolidado2(asignacionSuelo
//							.getSueInforConsolidado2());
//				}
//				if (getSolicitudComite() != null || getSolicitudComite().isEmpty()) {
//					as.setSueSolicitudComite(getSolicitudComite());
//				} else {
//					as.setSueSolicitudComite(asignacionSuelo
//							.getSueSolicitudComite());
//				}
//				if (getActaResolucion() != null || getActaResolucion().isEmpty()) {
//					as.setSueActaResolucionComite(getActaResolucion());
//				} else {
//					as.setSueActaResolucionComite(asignacionSuelo
//							.getSueActaResolucionComite());
//				}
//				mngTerritorio.modificarAsignacionSuelo(as);
//			} 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
		
	public String nombreBotonAS() {
		if (isEdicionAS()) {
			return "Actualizar";
		} else {
			return "Insertar";
		}
	}
	
	public String nombreBotonAd() {
		if (isEdicionAdmin()) {
			return "Actualizar";
		} else {
			return "Insertar";
		}
	}
	
	public String nombreBotonEn() {
		if (isEdicionEntregable()) {
			return "Actualizar";
		} else {
			return "Insertar";
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
	
	// Contrato 
	
	public void guardarEditarContrato(){
		try{
		if(validarCamposContrato() && validarFechasContrato()){
			GtrContratoAsignacion ca = new GtrContratoAsignacion();
			ca.setCasId(Funciones.quitarEspacios(getIdContrato()).toUpperCase());
			ca.setGtrAsignacionSuelo(getAsignacionSuelo());
			ca.setCasArrendador(Funciones.quitarEspacios(getArrendadorCotrato()));
			ca.setCasArrendatario(Funciones.quitarEspacios(getArrendatarioContrato()));
			ca.setCasFechaInicio(new Timestamp(getFechaInicioC().getTime()));
			ca.setCasFechaFin(new Timestamp(getFechaFinC().getTime()));
			ca.setCasPrecio(getPrecio());
			ca.setCasPeriodicidadPago(getPeriodicidadPagoC());
			ca.setCasUnidadTiempo(getUnidadTiempoContrato());
			ca.setCasEstado(getEstadoContrato());
			ca.setCasTipo(getTipoContrato());
			if( isEdicionContrato()){
				GtrContratoAsignacion contrato = mngTerritorio.findContratoById(getIdContrato());
				if(getTdrContrato() != null ){
					ca.setCasTdr(getTdrContrato());
					ca.setCasFechaDocTdr(getFechaDocTdr());
					ca.setCasFechaSubidaTdr(new Timestamp(getFechaSubidaTdr().getTime()));
					ca.setCasUsuarioTdr(getUsuTdr());
				}else{
					ca.setCasTdr(contrato.getCasTdr());
					ca.setCasFechaDocTdr(contrato.getCasFechaDocTdr());
					ca.setCasFechaSubidaTdr(contrato.getCasFechaSubidaTdr());
					ca.setCasUsuarioTdr(contrato.getCasUsuarioTdr());
				}
				if(getPliegoContrato() != null ){
					ca.setCasPliego(getPliegoContrato());
					ca.setCasFechaDocPliego(contrato.getCasFechaDocPliego());
					ca.setCasFechaSubidaPliego(new Timestamp(getFechaSubPliego().getTime()));
					ca.setCasUsuarioPliego(getUsuPliego());
				}else{
					ca.setCasPliego(contrato.getCasPliego());
					ca.setCasFechaDocPliego(getFechaDocPliego());
					ca.setCasFechaSubidaPliego(contrato.getCasFechaSubidaPliego());
					ca.setCasUsuarioPliego(contrato.getCasUsuarioPliego());
				}
				if(getArchContrato() != null){
					ca.setCasContrato(getArchContrato());
					ca.setCasFechaDocContrato(getFechaDocContrato());
					ca.setCasFechaSubidaContrato(new Timestamp(getFechaSubContrato().getTime()));
					ca.setCasUsuarioContrato(getUsuContrato());
				}else{
					ca.setCasTdr(contrato.getCasContrato());
					ca.setCasFechaDocContrato(contrato.getCasFechaDocContrato());
					ca.setCasFechaSubidaContrato(contrato.getCasFechaSubidaContrato());
					ca.setCasUsuarioContrato(contrato.getCasUsuarioContrato());
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
			//cargar entregables
			cargarLstEntregables(getIdContrato());
			//cargar administradores
			cargarLstAdministradores(getIdContrato());
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
		}else if(getTipoContrato().equals(SELECCIONAR)){
			Mensaje.crearMensajeERROR("Se debe seleccionar El tipo de Contrato.");
			return false;
		}else {
			return true;
		}
	}
	
	private boolean validarFechasContrato(){
		if(getFechaFinC().before(getFechaInicioC())){
			Mensaje.crearMensajeERROR("La Fecha Fin debe ser mayor a la Fecha de Inicio.");
			return false;
		}
		return true;
	}

//	private void editarContrato() {
//		try {
//			if (validarCamposContrato()) {
//				GtrContratoAsignacion ca = new GtrContratoAsignacion();
//				ca.setCasId(Funciones.quitarEspacios(getIdContrato())
//						.toUpperCase());
//				ca.setGtrAsignacionSuelo(mngTerritorio
//						.findAsignacionSueloById(getId()));
//				ca.setCasArrendador(Funciones
//						.quitarEspacios(getArrendadorCotrato()));
//				ca.setCasArrendatario(Funciones
//						.quitarEspacios(getArrendatarioContrato()));
//				ca.setCasFechaInicio(new Timestamp(getFechaIncio().getTime()));
//				ca.setCasFechaFin(new Timestamp(getFechaFin().getTime()));
//				ca.setCasPeriodicidadPago(getPeriodicidadPagoC());
//				ca.setCasUnidadTiempo(getUnidadTiempoContrato());
//				GtrContratoAsignacion contrato = mngTerritorio
//						.findContratoById(getIdContrato());
//				if (getTdrContrato() != null || !getTdrContrato().isEmpty()) {
//					ca.setCasTdr(getTdrContrato());
//				} else {
//					ca.setCasTdr(contrato.getCasTdr());
//				}
//				if (getPliegoContrato() != null || getPliegoContrato().isEmpty()) {
//					ca.setCasPliego(getPliegoContrato());
//				} else {
//					ca.setCasPliego(contrato.getCasPliego());
//				}
//				mngTerritorio.modificarContrato(ca);
//			}
//			cargarLstContratos();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void cancelarContrato(){
//		limpiarCamposContrato();
//		
//		RequestContext.getCurrentInstance().execute("PF('conDlg').hide();");
	}
//	
	public void limpiarCamposContrato(){
		setIdContrato(""); 
		setTdrContrato(""); setFechaDocTdr(new Date());
		setFechaSubidaTdr(new Timestamp(new Date().getTime()));
		setUsuTdr("");
		setPliegoContrato(""); setFechaDocPliego(new Date());
		setFechaSubPliego(new Timestamp(new Date().getTime()));
		setUsuPliego("");
		setArchContrato(""); setFechaDocContrato(new Date());
		setFechaSubContrato(new Timestamp(new Date().getTime()));
		setUsuContrato("");
		setFechaInicioC(new Timestamp(new Date().getTime())); setFechaFinC(new Timestamp(new Date().getTime())); 
		setArrendadorCotrato(""); setArrendatarioContrato("");
		setPeriodicidadPagoC(BigDecimal.ZERO); setUnidadTiempoContrato(SELECCIONAR);
		setPrecio(BigDecimal.ZERO); setPrecio(BigDecimal.ZERO);
		
		setEdicionContrato(false);
	}
	
	// Entregables
	
	public void mostrarDlgEntregables() {
		RequestContext.getCurrentInstance().execute("PF('entDlg').show();");
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
	
	public void cargarEditarEntregables(GtrEntregablesContrato entregable){
		setEdicionEntregable(true);
		setIdEntregable(entregable.getEcoId());
		setDocumento(entregable.getEcoDocumento());
		setDniresponsableEntregable(entregable.getEcoResponsable());
		setFechaMaxEntregaDoc(entregable.getEcoFechaMaxEntrega());
		setFechaSubidaDoc(entregable.getEcoFechaSubida());
		setUsuarioDocumento(entregable.getEcoUsuarioSubida());
		setEstadoEntregable(entregable.getEcoEstado());
		setNombreEntregable(entregable.getEcoNombreEntregable());
		setNombreResponsableEntregable(buscarNombre(entregable.getEcoResponsable()));
		// Fechas
		if(validaFechaVacia(entregable.getEcoFechaMaxEntrega())){
			setFechaMaxEntregaDoc(new Date()); 
		}
		
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
			GtrEntregablesContrato ec = new GtrEntregablesContrato();
			ec.setGtrContratoAsignacion(mngTerritorio.findContratoById(getIdContrato()));
			ec.setEcoId(getIdEntregable()); ec.setEcoResponsable(getDniresponsableEntregable());
			ec.setEcoEstado(getEstadoEntregable()); ec.setEcoNombreEntregable(getNombreEntregable());
			ec.setEcoFechaMaxEntrega(new Timestamp(getFechaMaxEntregaDoc().getTime()));
		if(isEdicionEntregable()){
			GtrEntregablesContrato entregable = mngTerritorio.findEntregableById(getIdEntregable());
			if(getDocumento() != null){
				ec.setEcoDocumento(getDocumento());
				ec.setEcoFechaSubida(new Timestamp(getFechaSubidaDoc().getTime()));
				ec.setEcoUsuarioSubida(getUsuarioDocumento());
			}else{
				ec.setEcoDocumento(entregable.getEcoDocumento());
				ec.setEcoFechaSubida(entregable.getEcoFechaSubida());
				ec.setEcoUsuarioSubida(entregable.getEcoUsuarioSubida());
			}
			mngTerritorio.modificarEntregable(ec);
			Mensaje.crearMensajeINFO("Entregable actualizado correctamente.");
		}else{
			setIdEntregable(mngTerritorio.generarIdEntregable());
			ec.setEcoId(getIdEntregable());
			mngTerritorio.insertarEntregable(ec);
			setEdicionEntregable(true);
			Mensaje.crearMensajeINFO("Entregable ingresado correctamente.");
		}
		cargarLstEntregables(getIdContrato());
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al almacenar entregable contrato: " + e.getMessage());
			System.out.println("Error al almacenar entregable contrato ");
			e.printStackTrace();
		}
	}
	
	public void cancelarEntregable(){
		limpiarCamposEntregable();
		cargarLstEntregables(getIdContrato());
		RequestContext.getCurrentInstance().execute("PF('entDlg').hide();");
	}

	public void limpiarCamposEntregable(){
		setEdicionEntregable(false);
		setDocumento(""); setDniresponsableEntregable(""); setNombreResponsableEntregable("");
		setFechaMaxEntregaDoc(new Timestamp(new Date().getTime())); setFechaSubidaDoc(new Timestamp(new Date().getTime()));
		setEstadoEntregable(ACTIVO);
	}
	
	// Administrador
		
	public void mostrarDlgAdministrador() {
		RequestContext.getCurrentInstance().execute("PF('admCDlg').show();");
	}
	
	public void cancelarAdministrador(){
		limpiarCamposAdmin();
		cargarLstAdministradores(getIdContrato());
		RequestContext.getCurrentInstance().execute("PF('admCDlg').hide();");
	}
	
	public void cargarEditarAdministrador(GtrAdministradorContrato administrador){
		setEdicionAdmin(true);
		setIdAdmin(administrador.getAdcId()); setDniAdmin(administrador.getAdcAdministrador());
		setNombreAdmin(buscarNombre(administrador.getAdcAdministrador()));
		setDireccionAdmin(administrador.getAdcDireccionAdm()); setFechaInicioAdmin(administrador.getAdcFechaInicio());
		setFechaFinAdmin(administrador.getAdcFechaFin()); setEstadoAdmin(administrador.getAdcEstado());
		mostrarDlgAdministrador();
	}
	
	public void buscarAdministrador() {
		if (getDniAdmin() == null || getDniAdmin().isEmpty()) {
			Mensaje.crearMensajeWARN("Debe ingresar el dato para realizar la búsqueda.");
			setDniAdmin(""); setNombreAdmin(""); setDireccionAdmin("");
		} else {
			try {
				GenFuncionariosInstitucion f = mngGeneral.findFuncionarioXDni(getDniAdmin());
				if (f == null) {
					Mensaje.crearMensajeWARN("Persona no encontrada");
					setDniAdmin(""); setNombreAdmin(""); setDireccionAdmin("");
				} else {
					setDniAdmin(f.getGenPersona().getPerDni()); setDireccionAdmin(f.getFunDireccion());
					setNombreAdmin(f.getGenPersona().getPerNombres() + " " + f.getGenPersona().getPerApellidos());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void guardarEditarAdministrador() {
		try {
			if (validarFechasAdmin()) {
				GtrAdministradorContrato ad = new GtrAdministradorContrato();
				ad.setAdcAdministrador(getDniAdmin()); ad.setAdcDireccionAdm(getDireccionAdmin());
				ad.setAdcFechaInicio(getFechaInicioAdmin()); ad.setAdcFechaFin(getFechaFinAdmin());
				ad.setAdcEstado(getEstadoAdmin()); ad.setGtrContratoAsignacion(mngTerritorio.findContratoById(getIdContrato()));
				if (isEdicionAdmin()) {
					ad.setAdcId(getIdAdmin());
					mngTerritorio.modificarAdministrador(ad);
					Mensaje.crearMensajeINFO("Administrador actualizado Correctamente.");
				} else {
					setIdAdmin(mngTerritorio.generarIdAdministrador());
					ad.setAdcId(getIdAdmin());
					mngTerritorio.insertarAdministrador(ad);
					Mensaje.crearMensajeINFO("Administrador ingresado Correctamente.");
				}
				cargarLstAdministradores(getIdContrato());
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al almacenar administrador contrato: " + e.getMessage());
			System.out.println("Error al almacenar administrador contrato ");
			e.printStackTrace();
		}
	}
	
	private boolean validarFechasAdmin(){
		if(getFechaFinAdmin().before(getFechaInicioAdmin())){
			Mensaje.crearMensajeERROR("La Fecha Fin debe ser mayor que la Fecha de Inicio.");
			return false;
		}else {
			return true;
		}
	}
	
	private void limpiarCamposAdmin(){
		setEdicionAdmin(false);
		setIdAdmin(0); setDniAdmin(""); setNombreAdmin("");
		setDireccionAdmin(""); setFechaInicioAdmin(new Date());
		setFechaFinAdmin(new Date()); setEstadoAdmin(ACTIVO);
	}
	
	// Manejo de Archivos
	
	public void subirInforGestionT(FileUploadEvent evento) {
		try {
			setInforGestionTerr(cargarInformes(evento));
			editarAsignacion(getAsignacionSuelo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/****/
	public void editarAsignacion(GtrAsignacionSuelo asigSuelo){
		try {
			asigSuelo.setSueInforGestionTerritorial(getInforGestionTerr());
			System.out.println("ingreso al método");
			mngTerritorio.modificarAsignacionSuelo(asigSuelo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void subirInforCaracterizacion(FileUploadEvent evento) {
		try {
			setInforCaracterizacion(cargarInformes(evento));
			setFechaSubidaCaracte(new Timestamp(new Date().getTime()));
			setUsuCaracterizacion(getUsuarioSesion());
			editarICaracterizacion(getAsignacionSuelo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editarICaracterizacion(GtrAsignacionSuelo asigSuelo){
		try {
			asigSuelo.setSueInforCaracterizacion(getInforCaracterizacion());
			asigSuelo.setSueFechaSubidaCaracterizacio(new Timestamp(getFechaSubidaCaracte().getTime()));
			asigSuelo.setSueFechaDocCaracterizacion(getFechaDocCaracte());
			asigSuelo.setSueUsuarioCaracterizacion(getUsuCaracterizacion());
			mngTerritorio.modificarAsignacionSuelo(asigSuelo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void subirInforUsoSuelo(FileUploadEvent evento) {
		try {
			setInforUsoSuelo(cargarInformes(evento));
			setFechaSubidaUsoS(new Timestamp(new Date().getTime()));
			setUsuUsoSuelo(getUsuarioSesion());
			editarIUsoS(getAsignacionSuelo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void editarIUsoS(GtrAsignacionSuelo asigSuelo){
		try {
			asigSuelo.setSueInforUsoSuelo(getInforUsoSuelo());
			asigSuelo.setSueFechaDocUsoSuelo(getFechaDocUsoSuelo());
			asigSuelo.setSueFechaSubidaUsoSuelo(new Timestamp(getFechaSubidaUsoS().getTime()));
			asigSuelo.setSueUsuarioUsoSuelo(getUsuUsoSuelo());
			mngTerritorio.modificarAsignacionSuelo(asigSuelo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void subirInforDisponibilidad(FileUploadEvent evento) {
		try {
			setInforDisponibilidad(cargarInformes(evento));
			setFechaSubidaDisponi(new Timestamp(new Date().getTime()));
			setUsuDisponibilidad(getUsuarioSesion());
			editarIDsiponibilidad(getAsignacionSuelo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarIDsiponibilidad(GtrAsignacionSuelo asigSuelo){
		try {
			asigSuelo.setSueInforDisponibilidad(getInforDisponibilidad());
			asigSuelo.setSueFechaDocDisponibilidad(getFechaDocDisponibil());
			asigSuelo.setSueFechaSubidaDisponibilidad(new Timestamp(getFechaSubidaDisponi().getTime()));
			asigSuelo.setSueUsuarioDisponibilidad(getUsuDisponibilidad());
			mngTerritorio.modificarAsignacionSuelo(asigSuelo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void subirInforConsolidado(FileUploadEvent evento) {
		try {
			setInforConsolidado(cargarInformes(evento));
			setFechaSubidaConsol(new Timestamp(new Date().getTime()));
			setUsuConsolidado(getUsuarioSesion());
			editarIConsolidado(getAsignacionSuelo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarIConsolidado(GtrAsignacionSuelo asigSuelo){
		try {
			asigSuelo.setSueInforConsolidado(getInforConsolidado());
			asigSuelo.setSueFechaDocConsolidado(getFechaDocConsolidado());
			asigSuelo.setSueFechaSubidaConsolidado(new Timestamp(getFechaSubidaConsol().getTime()));
			asigSuelo.setSueUsuarioConsolidado(getUsuConsolidado());
			mngTerritorio.modificarAsignacionSuelo(asigSuelo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void subirResolucion(FileUploadEvent evento) {
		try {
			setResolucion(cargarInformes(evento));
			setFechaSubidaResol(new Timestamp(new Date().getTime()));
			setUsuResolucion(getUsuarioSesion());
			editarResolucion(getAsignacionSuelo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarResolucion(GtrAsignacionSuelo asigSuelo){
		try {
			asigSuelo.setSueResolucion(getResolucion());
			asigSuelo.setSueFechaDocResolucion(getFechaDocResolucion());
			asigSuelo.setSueFechaSubidaResolucion(new Timestamp(getFechaSubidaResol().getTime()));
			asigSuelo.setSueUsuarioResolucion(getUsuResolucion());
			mngTerritorio.modificarAsignacionSuelo(asigSuelo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void subirActaResolutiva(FileUploadEvent evento) {
		try {
			setActaResolutiva(cargarInformes(evento));
			setFechaSubidaActaRes(new Timestamp(new Date().getTime()));
			setUsuActaResol(getUsuarioSesion());
			editarActaResolutiva(getAsignacionSuelo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarActaResolutiva(GtrAsignacionSuelo asigSuelo){
		try {
			asigSuelo.setSueActaResolutiva(getActaResolutiva());
			asigSuelo.setSueFechaDocResolutiva(getFechaDocActaResol());
			asigSuelo.setSueFechaSubidaResolutiva(new Timestamp(getFechaSubidaActaRes().getTime()));
			asigSuelo.setSueUsuarioResolutiva(getUsuActaResol());
			mngTerritorio.modificarAsignacionSuelo(asigSuelo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void subirConvocatoria(FileUploadEvent evento) {
		try {
			setConvocatoria(cargarInformes(evento));
			setFechaSubidaConvoca(new Timestamp(new Date().getTime()));
			setUsuConvocatoria(getUsuarioSesion());
			editarConvocatoria(getAsignacionSuelo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarConvocatoria(GtrAsignacionSuelo asigSuelo){
		try {
			asigSuelo.setSueConvocatoria(getConvocatoria());
			asigSuelo.setSueFechaDocConvocatoria(getFechaDocConvoca());
			asigSuelo.setSueFechaSubidaResolutiva(new Timestamp(getFechaSubidaConvoca().getTime()));
			asigSuelo.setSueUsuarioResolutiva(getUsuConvocatoria());
			mngTerritorio.modificarAsignacionSuelo(asigSuelo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void subirRegulacionAmb(FileUploadEvent evento) {
		try {
			setArchRegulacionAmb(cargarInformes(evento));
			setFechaSubRegAmbiental(new Timestamp(new Date().getTime()));
			setUsuRegAmbiental(getUsuarioSesion());
			editarRegulacionAmb(getAsignacionSuelo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarRegulacionAmb(GtrAsignacionSuelo asigSuelo){
		try {
			asigSuelo.setSueArchivoRegulacionAmb(getArchRegulacionAmb());
			asigSuelo.setSueFechaArchivoRegAmb(getFechaDocRegulacionAmb());
			asigSuelo.setSueFechaSubidaRegAmb(new Timestamp(getFechaSubRegAmbiental().getTime()));
			asigSuelo.setSueUsuarioSubidaRegAmb(getUsuRegAmbiental());
			mngTerritorio.modificarAsignacionSuelo(asigSuelo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void subirKMZ(FileUploadEvent evento) {
		try {
			setArchivoKMZ(cargarInformes(evento));
			setFechaArcKMZ(new Timestamp(new Date().getTime()));
			setUsuArchivoKMZ(getUsuarioSesion());
			editarKMZ(getAsignacionSuelo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarKMZ(GtrAsignacionSuelo asigSuelo){
		try {
			asigSuelo.setSueArchivoKmz(getArchivoKMZ());
			asigSuelo.setSueFechaArchivoKmz(getFechaArcKMZ());
			asigSuelo.setSueFechaSubidaKmz(new Timestamp(getFechaArcKMZ().getTime()));
			asigSuelo.setSueUsuarioArchivoKmz(getUsuArchivoKMZ());
			mngTerritorio.modificarAsignacionSuelo(asigSuelo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void subirPDF(FileUploadEvent evento) {
		try {
			setArchivoPDF(cargarInformes(evento));
			setFechaArcPDF(new Timestamp(new Date().getTime()));
			setUsuArchivoPDF(getUsuarioSesion());
			editarPDF(getAsignacionSuelo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarPDF(GtrAsignacionSuelo asigSuelo){
		try {
			asigSuelo.setSueArchivoPdf(getArchRegulacionAmb());
			asigSuelo.setSueFechaArchivoPdf(getFechaArcPDF());
			asigSuelo.setSueFechaSubidaPdf(new Timestamp(getFechaSubidaPDF().getTime()));
			asigSuelo.setSueUsuarioArchivoPdf(getUsuArchivoPDF());
			mngTerritorio.modificarAsignacionSuelo(asigSuelo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void subirNotificacionAN(FileUploadEvent evento) {
		try {
			setNotificacionApNe(cargarInformes(evento));
			setFechaSubidaNotificacion(new Timestamp(new Date().getTime()));
			setUsuNotificacion(getUsuarioSesion());
			editarNotificacionAN(getAsignacionSuelo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarNotificacionAN(GtrAsignacionSuelo asigSuelo){
		try {
			asigSuelo.setSueNotificacionApNe(getNotificacionApNe());
			asigSuelo.setSueFechaDocNotificacion(getFechaDocNotificacion());
			asigSuelo.setSueFechaSubidaNotificacion(new Timestamp(getFechaSubidaNotificacion().getTime()));
			asigSuelo.setSueUsuarioNotificacion(getUsuNotificacion());
			mngTerritorio.modificarAsignacionSuelo(asigSuelo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Contrato

	public void subirTDR(FileUploadEvent evento) {
		try {
			setTdrContrato(cargarInformes(evento));
			setFechaSubidaTdr(new Timestamp(new Date().getTime()));
			setUsuTdr(getUsuarioSesion());
			editarTDR(getContrato());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void editarTDR(GtrContratoAsignacion contrato){
		try {
			contrato.setCasTdr(getTdrContrato());
			contrato.setCasFechaDocTdr(getFechaDocTdr());
			contrato.setCasFechaSubidaTdr(new Timestamp(getFechaSubidaTdr().getTime()));
			contrato.setCasUsuarioTdr(getUsuTdr());
			mngTerritorio.modificarContrato(contrato);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void subirPliego(FileUploadEvent evento) {
		try {
			setPliegoContrato(cargarInformes(evento));
			setFechaSubPliego(new Timestamp(new Date().getTime()));
			setUsuPliego(getUsuarioSesion());
			editarPliego(getContrato());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarPliego(GtrContratoAsignacion contrato){
		try {
			contrato.setCasPliego(getPliegoContrato());
			contrato.setCasFechaDocPliego(getFechaDocPliego());
			contrato.setCasFechaSubidaPliego(new Timestamp(getFechaSubPliego().getTime()));
			contrato.setCasUsuarioPliego(getUsuPliego());
			mngTerritorio.modificarContrato(contrato);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void subirContrato(FileUploadEvent evento) {
		try {
			setArchContrato(cargarInformes(evento));
			setFechaSubContrato(new Timestamp(new Date().getTime()));
			setUsuContrato(getUsuarioSesion());
			editarContrato(getContrato());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editarContrato(GtrContratoAsignacion contrato){
		try {
			contrato.setCasContrato(getArchContrato());
			contrato.setCasFechaDocContrato(getFechaDocContrato());
			contrato.setCasFechaSubidaContrato(new Timestamp(getFechaSubContrato().getTime()));
			contrato.setCasUsuarioContrato(getUsuContrato());
			mngTerritorio.modificarContrato(contrato);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Entregables

	public void subirDocumentoEntregable(FileUploadEvent evento) {
		try {
			setDocumento(cargarInformes(evento));
			setFechaSubidaDoc(new Timestamp(new Date().getTime()));
			setUsuarioDocumento(getUsuarioSesion());
			editarDocumento(getEntregableC());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarDocumento(GtrEntregablesContrato entregable){
		try {
			entregable.setEcoDocumento(getDocumento());
			entregable.setEcoFechaMaxEntrega(new Timestamp(getFechaMaxEntregaDoc().getTime()));
			entregable.setEcoFechaSubida(new Timestamp(getFechaSubidaDoc().getTime()));
			entregable.setEcoUsuarioSubida(getUsuarioDocumento());
			mngTerritorio.modificarEntregable(entregable);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String cargarInformes(FileUploadEvent event) throws IOException {
		filePdf = event.getFile();
		String nombreArchivo = "";
		InputStream inputStream = null;
		OutputStream outputStream = null;
		if (filePdf != null) {
			try {
				File directorio=new File(direccionFicheros()); 
				directorio.mkdir(); 
				String direccion = direccionFicheros()+"/"+generarNombreArchivo(filePdf);
				outputStream = new FileOutputStream(direccion);
				inputStream = filePdf.getInputstream();
				nombreArchivo = filePdf.getFileName();

				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				Mensaje.crearMensajeINFO("Carga del archivo Correcta");
				filePdf = null;
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
			return nombreArchivo;
		} else {
			Mensaje.crearMensajeWARN("No se pudo cargar el archivo");
			return "";
		}
	}
	
	public String direccionFicheros(){
		try {
			String carpeta = mngTerritorio.findParametroByID("direccion_informes") + "/"+ getAsignacionSuelo().getSueId()+"/";
			System.out.println("Carpeta -----> "+carpeta);
			return carpeta;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public String generarNombreArchivo(UploadedFile file){
		try {
			String nombreArchivo = file.getFileName();
			System.out.println("PAD -----> "+nombreArchivo);
			return nombreArchivo;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String extensionArchivo(String nombreArchivo) {
		return nombreArchivo.substring(nombreArchivo.lastIndexOf('.'));
	}
	
	public void descargarInforme(String informe){
		try {
			if(informe == null || !informe.isEmpty()){
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

//	public String fechasaanios(Date fechaInicio, Date fechaFin) {
//		String aniosEntreFechas = mngTerritorio.fechasaAnios(getFechaIncio(), getFechaFin(), getId());
//		return aniosEntreFechas;
//	}
//
//	public void tablaSeguimiento(GtrAsignacionSuelo asignacion) {
//		getL_seguimiento().clear();
//		setL_seguimiento(mngTerritorio.listaSeguimientoFiltrado(asignacion.getSueId()));
//		RequestContext.getCurrentInstance().execute("PF('dlgSeg').show();");
//	}
//	
	public void descargarDocumento(GtrHistorialSeguimiento seguimiento) {
		try {
			if (seguimiento.getHseAdjuntoDoc() == null
					|| !seguimiento.getHseAdjuntoDoc().isEmpty()) {
				Mensaje.crearMensajeERROR("No existe un archivo asignado.");
			} else {
				String contextPath = mngTerritorio.findParametroByID("direccion_ad_doc")
						+ File.separatorChar + seguimiento.getHseAdjuntoDoc() + "";
				Funciones.descargarPDF(contextPath);
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
		 
	public String cambiarNombre(String param){
		if(param.equals(ENPROCESO)){
			return VALORENPROCESO;
		}else if(param.equals(APROBADO)){
			return	VALORAPROBADO;
		}else{
			return VALORNEGADO;
		}
	}
}