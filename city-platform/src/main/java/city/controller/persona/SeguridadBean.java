package city.controller.persona;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.primefaces.model.UploadedFile;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenFuncionariosInstitucion;
import city.model.dao.entidades.SegIncidenciasAdmin;
import city.model.dao.entidades.SegRegistroEmergencia;
import city.model.generic.Funciones;
import city.model.generic.Mensaje;
import city.model.manager.ManagerSeguridad;

/**
 * @author jestevez
 * 
 */
@SessionScoped
@ManagedBean
public class SeguridadBean {

	// Atributos de la Clase
	@EJB
	private ManagerSeguridad manager;
	@Inject
	private SesionBean session;

	// Atriutos de la clase seguridad
	private Integer segId;
	private String segAccion;
	private String segEmergencia;
	private Date segFecha;
	private String segTipoEmergencia;
	private String segSubHijo;
	private String segSubTipo;
	private double segLatitud;
	private double segLongitud;
	private String segArchivo;
	private String usuario;
	private String segDocumento;
	private String utmX;
	private String utmY;

	private Boolean control;

	private String DatoBusqueda;
	private String perDni;
	private String perNombre;
	private String perEmpresa;
	private String perCargo;

	// mensaje de validación de campos
	private String sms_validacion;
	private boolean edicion;

	// listas de registros
	private List<SegRegistroEmergencia> l_seguridad;
	private List<SelectItem> l_tipos_emergencia;
	private List<SelectItem> l_tipos_emergencia_1;
	private List<String> l_tipos_emergencia_2;
	private List<SelectItem> l_busqueda;

	// mapa
	private Marker marker;
	private MapModel geoModel;

	// guardar archivo
	private UploadedFile file;

	// atributo de direccion de url
	private String url_doc;

	// estadistica
	private static String Cod_sal = "1. MÉDICA";
	private static String Cod_soc = "2. PROTECCIÓN CIVIL";
	private static String Cod_seg = "3. SEGURIDAD";
	private static String Cod_ser = "4. SERVICIOS PÚBLICOS";
	private int totalSAL;
	private int totalSOC;
	private int totalSEG;
	private int totalSER;
	private int total;

	// graficos
	private PieChartModel pieModel;
	private MapModel geoModel1;
	private BarChartModel dateModel;
	// private LineChartModel dateModel;

	// fechas de filtrado
	private Date f_desde;
	private Date f_hasta;

	private List<SegRegistroEmergencia> l_estadistica;

	public SeguridadBean() {
	}

	@PostConstruct
	public void ini() {
		control = false;
		usuario = session.validarSesion();
		f_desde = null;
		f_hasta = null;
		segLatitud = 0;
		segLongitud = 0;
		totalSAL = 0;
		totalSEG = 0;
		totalSER = 0;
		totalSOC = 0;
		total = 0;
		l_seguridad = new ArrayList<SegRegistroEmergencia>();
		l_estadistica = new ArrayList<SegRegistroEmergencia>();
		l_tipos_emergencia = new ArrayList<SelectItem>();
		l_tipos_emergencia_1 = new ArrayList<SelectItem>();
		l_tipos_emergencia_2 = new ArrayList<String>();
		l_busqueda = new ArrayList<SelectItem>();
		geoModel = new DefaultMapModel();
		// definicion de marcador principal
		LatLng coordenada = new LatLng(0.4044186, -78.17527749999999);
		geoModel.addOverlay(new Marker(coordenada, "Yachay Ciudad del Conocimiento"));
		marker = geoModel.getMarkers().get(0);
		marker.setDraggable(true);
		try {
			url_doc = manager.ParametroByID("direccion_doc");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comprobaradmin();
		cargarIncidentes();
	}

	/**
	 * @return the utmX
	 */
	public String getUtmX() {
		return utmX;
	}

	/**
	 * @param utmX
	 *            the utmX to set
	 */
	public void setUtmX(String utmX) {
		this.utmX = utmX;
	}

	/**
	 * @return the utmY
	 */
	public String getUtmY() {
		return utmY;
	}

	/**
	 * @param utmY
	 *            the utmY to set
	 */
	public void setUtmY(String utmY) {
		this.utmY = utmY;
	}

	/**
	 * @return the segDocumento
	 */
	public String getSegDocumento() {
		return segDocumento;
	}

	/**
	 * @param segDocumento
	 *            the segDocumento to set
	 */
	public void setSegDocumento(String segDocumento) {
		this.segDocumento = segDocumento;
	}

	/**
	 * @return the dateModel
	 */
	public BarChartModel getDateModel() {
		return dateModel;
	}

	/**
	 * @param dateModel
	 *            the dateModel to set
	 */
	public void setDateModel(BarChartModel dateModel) {
		this.dateModel = dateModel;
	}

	/**
	 * @return the control
	 */
	public Boolean getControl() {
		return control;
	}

	/**
	 * @param control
	 *            the control to set
	 */
	public void setControl(Boolean control) {
		this.control = control;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the f_desde
	 */
	public Date getF_desde() {
		return f_desde;
	}

	/**
	 * @param f_desde
	 *            the f_desde to set
	 */
	public void setF_desde(Date f_desde) {
		this.f_desde = f_desde;
	}

	/**
	 * @return the f_hasta
	 */
	public Date getF_hasta() {
		return f_hasta;
	}

	/**
	 * @param f_hasta
	 *            the f_hasta to set
	 */
	public void setF_hasta(Date f_hasta) {
		this.f_hasta = f_hasta;
	}

	/**
	 * @return the l_estadistica
	 */
	public List<SegRegistroEmergencia> getL_estadistica() {
		return l_estadistica;
	}

	/**
	 * @param l_estadistica
	 *            the l_estadistica to set
	 */
	public void setL_estadistica(List<SegRegistroEmergencia> l_estadistica) {
		this.l_estadistica = l_estadistica;
	}

	/**
	 * @return the segArchivo
	 */
	public String getSegArchivo() {
		return segArchivo;
	}

	/**
	 * @param segArchivo
	 *            the segArchivo to set
	 */
	public void setSegArchivo(String segArchivo) {
		this.segArchivo = segArchivo;
	}

	/**
	 * @return the segSubHijo
	 */
	public String getSegSubHijo() {
		return segSubHijo;
	}

	/**
	 * @param segSubHijo
	 *            the segSubHijo to set
	 */
	public void setSegSubHijo(String segSubHijo) {
		this.segSubHijo = segSubHijo;
	}

	/**
	 * @return the segSubTipo
	 */
	public String getSegSubTipo() {
		return segSubTipo;
	}

	/**
	 * @param segSubTipo
	 *            the segSubTipo to set
	 */
	public void setSegSubTipo(String segSubTipo) {
		this.segSubTipo = segSubTipo;
	}

	/**
	 * @return the l_tipos_emergencia_1
	 */
	public List<SelectItem> getL_tipos_emergencia_1() {
		return l_tipos_emergencia_1;
	}

	/**
	 * @param l_tipos_emergencia_1
	 *            the l_tipos_emergencia_1 to set
	 */
	public void setL_tipos_emergencia_1(List<SelectItem> l_tipos_emergencia_1) {
		this.l_tipos_emergencia_1 = l_tipos_emergencia_1;
	}

	/**
	 * @return the l_tipos_emergencia_2
	 */
	public List<String> getL_tipos_emergencia_2() {
		return l_tipos_emergencia_2;
	}

	/**
	 * @param l_tipos_emergencia_2
	 *            the l_tipos_emergencia_2 to set
	 */
	public void setL_tipos_emergencia_2(List<String> l_tipos_emergencia_2) {
		this.l_tipos_emergencia_2 = l_tipos_emergencia_2;
	}

	/**
	 * @return the datoBusqueda
	 */
	public String getDatoBusqueda() {
		return DatoBusqueda;
	}

	/**
	 * @param datoBusqueda
	 *            the datoBusqueda to set
	 */
	public void setDatoBusqueda(String datoBusqueda) {
		DatoBusqueda = datoBusqueda;
	}

	/**
	 * @return the segLatitud
	 */
	public double getSegLatitud() {
		return segLatitud;
	}

	/**
	 * @param segLatitud
	 *            the segLatitud to set
	 */
	public void setSegLatitud(double segLatitud) {
		this.segLatitud = segLatitud;
	}

	/**
	 * @return the segLongitud
	 */
	public double getSegLongitud() {
		return segLongitud;
	}

	/**
	 * @param segLongitud
	 *            the segLongitud to set
	 */
	public void setSegLongitud(double segLongitud) {
		this.segLongitud = segLongitud;
	}

	/**
	 * @return the geoModel1
	 */
	public MapModel getGeoModel1() {
		return geoModel1;
	}

	/**
	 * @param geoModel1
	 *            the geoModel1 to set
	 */
	public void setGeoModel1(MapModel geoModel1) {
		this.geoModel1 = geoModel1;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the pieModel
	 */
	public PieChartModel getPieModel() {
		return pieModel;
	}

	/**
	 * @param pieModel
	 *            the pieModel to set
	 */
	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	/**
	 * @return the totalSAL
	 */
	public int getTotalSAL() {
		return totalSAL;
	}

	/**
	 * @param totalSAL
	 *            the totalSAL to set
	 */
	public void setTotalSAL(int totalSAL) {
		this.totalSAL = totalSAL;
	}

	/**
	 * @return the totalSOC
	 */
	public int getTotalSOC() {
		return totalSOC;
	}

	/**
	 * @param totalSOC
	 *            the totalSOC to set
	 */
	public void setTotalSOC(int totalSOC) {
		this.totalSOC = totalSOC;
	}

	/**
	 * @return the totalSEG
	 */
	public int getTotalSEG() {
		return totalSEG;
	}

	/**
	 * @param totalSEG
	 *            the totalSEG to set
	 */
	public void setTotalSEG(int totalSEG) {
		this.totalSEG = totalSEG;
	}

	/**
	 * @return the totalSER
	 */
	public int getTotalSER() {
		return totalSER;
	}

	/**
	 * @param totalSER
	 *            the totalSER to set
	 */
	public void setTotalSER(int totalSER) {
		this.totalSER = totalSER;
	}

	/**
	 * @return the sms_validacion
	 */
	public String getSms_validacion() {
		return sms_validacion;
	}

	/**
	 * @return the marker
	 */
	public Marker getMarker() {
		return marker;
	}

	/**
	 * @param marker
	 *            the marker to set
	 */
	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	/**
	 * @return the geoModel
	 */
	public MapModel getGeoModel() {
		return geoModel;
	}

	/**
	 * @param geoModel
	 *            the geoModel to set
	 */
	public void setGeoModel(MapModel geoModel) {
		this.geoModel = geoModel;
	}

	/**
	 * @param sms_validacion
	 *            the sms_validacion to set
	 */
	public void setSms_validacion(String sms_validacion) {
		this.sms_validacion = sms_validacion;
	}

	/**
	 * @return the perNombre
	 */
	public String getPerNombre() {
		return perNombre;
	}

	/**
	 * @return the l_tipos_emergencia
	 */
	public List<SelectItem> getL_tipos_emergencia() {
		return l_tipos_emergencia;
	}

	/**
	 * @param l_tipos_emergencia
	 *            the l_tipos_emergencia to set
	 */
	public void setL_tipos_emergencia(List<SelectItem> l_tipos_emergencia) {
		this.l_tipos_emergencia = l_tipos_emergencia;
	}

	/**
	 * @return the l_busqueda
	 */
	public List<SelectItem> getL_busqueda() {
		return l_busqueda;
	}

	/**
	 * @param l_busqueda
	 *            the l_busqueda to set
	 */
	public void setL_busqueda(List<SelectItem> l_busqueda) {
		this.l_busqueda = l_busqueda;
	}

	/**
	 * @param perNombre
	 *            the perNombre to set
	 */
	public void setPerNombre(String perNombre) {
		this.perNombre = perNombre;
	}

	/**
	 * @return the perEmpresa
	 */
	public String getPerEmpresa() {
		return perEmpresa;
	}

	/**
	 * @return the edicion
	 */
	public boolean isEdicion() {
		return edicion;
	}

	/**
	 * @param edicion
	 *            the edicion to set
	 */
	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	/**
	 * @param perEmpresa
	 *            the perEmpresa to set
	 */
	public void setPerEmpresa(String perEmpresa) {
		this.perEmpresa = perEmpresa;
	}

	/**
	 * @return the perCargo
	 */
	public String getPerCargo() {
		return perCargo;
	}

	/**
	 * @param perCargo
	 *            the perCargo to set
	 */
	public void setPerCargo(String perCargo) {
		this.perCargo = perCargo;
	}

	/**
	 * @return the segId
	 */
	public Integer getSegId() {
		return segId;
	}

	/**
	 * @param segId
	 *            the segId to set
	 */
	public void setSegId(Integer segId) {
		this.segId = segId;
	}

	/**
	 * @return the segAccion
	 */
	public String getSegAccion() {
		return segAccion;
	}

	/**
	 * @param segAccion
	 *            the segAccion to set
	 */
	public void setSegAccion(String segAccion) {
		this.segAccion = segAccion;
	}

	/**
	 * @return the segEmergencia
	 */
	public String getSegEmergencia() {
		return segEmergencia;
	}

	/**
	 * @param segEmergencia
	 *            the segEmergencia to set
	 */
	public void setSegEmergencia(String segEmergencia) {
		this.segEmergencia = segEmergencia;
	}

	/**
	 * @return the segFecha
	 */
	public Date getSegFecha() {
		return segFecha;
	}

	/**
	 * @param segFecha
	 *            the segFecha to set
	 */
	public void setSegFecha(Date segFecha) {
		this.segFecha = segFecha;
	}

	/**
	 * @return the segTipoEmergencia
	 */
	public String getSegTipoEmergencia() {
		return segTipoEmergencia;
	}

	/**
	 * @param segTipoEmergencia
	 *            the segTipoEmergencia to set
	 */
	public void setSegTipoEmergencia(String segTipoEmergencia) {
		this.segTipoEmergencia = segTipoEmergencia;
	}

	/**
	 * @return the perDni
	 */
	public String getPerDni() {
		return perDni;
	}

	/**
	 * @param perDni
	 *            the perDni to set
	 */
	public void setPerDni(String perDni) {
		this.perDni = perDni;
	}

	/**
	 * @return the l_seguridad
	 */
	public List<SegRegistroEmergencia> getL_seguridad() {
		return l_seguridad;
	}

	/**
	 * @param l_seguridad
	 *            the l_seguridad to set
	 */
	public void setL_seguridad(List<SegRegistroEmergencia> l_seguridad) {
		this.l_seguridad = l_seguridad;
	}

	/**
	 * Redirecciona a la pagina de creacion de personas
	 * 
	 * @return vista
	 */
	public String nuevoIncidente() {
		this.carga();
		// setControl(true);
		setSegFecha(new Date());
		setEdicion(true);
		return "nseguridad?faces-redirect=true";
	}

	/**
	 * Método para cargar todos los select
	 */
	public void carga() {
		cargarGeneros();
		cargarBusqueda();
	}

	/**
	 * Permite la creación o modificación de una persona
	 * 
	 * @return
	 */
	public String crearSeguridad() {
		String r = "";
		System.out.println("valor" + usuario);
		try {
			if (this.validarCampos()) {
				Mensaje.crearMensajeERROR(getSms_validacion());
			} else {
				if (edicion) {
					Integer id = manager.seguridadId();
					manager.insertarSeguridad(id, getPerDni(), getSegAccion(), getSegEmergencia(), getSegFecha(),
							getSegTipoEmergencia(), getSegLatitud(), getSegLongitud(), getSegSubTipo(), getSegSubHijo(),
							getSegArchivo(), usuario, getSegDocumento(), getUtmX(), getUtmY());
					Mensaje.crearMensajeINFO("Registrado - Incidente Creado");
					setEdicion(false);
				} else {
					manager.editarSeguridad(getSegId(), getSegAccion(), getSegEmergencia(), getSegFecha(),
							getSegTipoEmergencia(), getSegLatitud(), getSegLongitud(), getSegSubTipo(), getSegSubHijo(),
							getSegArchivo(), usuario, getSegDocumento(), getUtmX(), getUtmY());
					Mensaje.crearMensajeINFO("Actualizado - Incidente Modificado");
				}
				r = "seguridad?faces-redirect=true";
				// setControl(false);
				this.cleanDatos();
				this.cargarIncidentes();
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
		return r;
	}

	/**
	 * Metodo para validar los campos generales de la persona
	 * 
	 * @return
	 */
	public boolean validarCampos() {
		if ((getSegAccion() == null || getSegAccion().isEmpty())
				|| (getSegEmergencia() == null || getSegEmergencia().isEmpty()) || (getSegFecha() == null)
				|| (getSegTipoEmergencia() == null || getSegTipoEmergencia().equals("S/N"))
				|| (getSegLatitud() == 0 || getSegLongitud() == 0) || (getPerDni() == null || getPerDni().isEmpty())
				|| (getPerNombre() == null || getPerNombre().isEmpty())
				|| (getSegSubTipo() == null || getSegSubTipo().isEmpty())
				|| (getSegSubHijo() == null || getSegSubHijo().isEmpty())) {
			setSms_validacion("Todos los datos de seguridad son requeridos.");
			return true;
		} else {
			return false;
		}
	}

	public void cleanDatos() {
		setPerCargo("");
		setPerDni("");
		setPerEmpresa("");
		setPerNombre("");
		setSegAccion("");
		setSegEmergencia("");
		setSegFecha(null);
		setSegTipoEmergencia("S/N");
		setSegSubTipo("S/N");
		setSegSubHijo("");
		setSegLatitud(0);
		setSegLongitud(0);
		setDatoBusqueda("");
		setSegArchivo("");
		setSegDocumento("");
		setEdicion(false);
	}

	/**
	 * Metodo para cargar una Persona para su edicion
	 * 
	 * @param persona
	 * @return
	 */
	public String cargarIncidente(SegRegistroEmergencia incidente) {
		try {
			this.carga();
			setSegId(incidente.getSegId());
			setPerCargo(incidente.getGenFuncionariosInstitucion().getFunCargo());
			setPerDni(incidente.getGenFuncionariosInstitucion().getGenPersona().getPerDni());
			setPerEmpresa(incidente.getGenFuncionariosInstitucion().getGenInstitucione().getInsNombre());
			setPerNombre(incidente.getGenFuncionariosInstitucion().getGenPersona().getPerNombres() + " "
					+ incidente.getGenFuncionariosInstitucion().getGenPersona().getPerApellidos());
			setSegAccion(incidente.getSegAccion());
			setSegEmergencia(incidente.getSegEmergencia());
			setSegFecha(incidente.getSegFecha());
			setSegTipoEmergencia(incidente.getSegTipoEmergencia());
			setSegLatitud(incidente.getSegLatitud());
			setSegLongitud(incidente.getSegLongitud());
			setSegSubTipo(incidente.getSegSubTipo());
			setSegSubHijo(incidente.getSegSubHijo());
			setSegArchivo(incidente.getSegArchivo());
			setSegDocumento(incidente.getSegDocumento());
			System.out.println(getControl());
			setEdicion(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "nseguridad?faces-redirect=true";
	}

	/**
	 * Metodo para setear datos y regresar la vista
	 * 
	 * @return
	 */
	public String cancelar() {
		// setControl(false);
		this.cleanDatos();
		this.cargarIncidentes();
		return "seguridad?faces-redirect=true";
	}

	/**
	 * Lista de Personas
	 */
	public void cargarIncidentes() {
		try {
			getL_seguridad().clear();
			if (getControl() == true) {
				getL_seguridad().addAll(manager.findAllseguridad());
			} else {
				getL_seguridad().addAll(manager.findAllseguridad(usuario));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método para buscar un funcionario
	 */
	public void BuscarPersona() {
		if (getDatoBusqueda() == null || getDatoBusqueda().isEmpty()) {
			Mensaje.crearMensajeWARN("Debe ingresar el dato para realizar la búsqueda.");
			setDatoBusqueda("");
			setPerDni("");
			setPerCargo("");
			setPerEmpresa("");
			setPerNombre("");
		} else {
			try {
				GenFuncionariosInstitucion f = manager.findFuncionarioXDni(getDatoBusqueda());
				if (f == null) {
					Mensaje.crearMensajeWARN("el dato no pudo ser encontrada");
					setDatoBusqueda("");
					setPerDni("");
					setPerCargo("");
					setPerEmpresa("");
					setPerNombre("");
				} else {
					setDatoBusqueda("");
					setPerDni(f.getGenPersona().getPerDni());
					setPerCargo(f.getFunCargo());
					setPerEmpresa(f.getGenInstitucione().getInsNombre());
					setPerNombre(f.getGenPersona().getPerNombres() + " " + f.getGenPersona().getPerApellidos());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo para tomar el punto de seleccion en el mapa
	 * 
	 */
	public void abrirDialog() {
		RequestContext.getCurrentInstance().execute("PF('mapDialog').show()");
	}

	/**
	 * Metodo para tomar el punto de seleccion en el mapa
	 * 
	 * @param event
	 */
	public void TomarMarca(MarkerDragEvent event) {
		marker = event.getMarker();
		setSegLatitud(marker.getLatlng().getLat());
		setSegLongitud(marker.getLatlng().getLng());
		String r = obtenerUTM(getSegLatitud(), getSegLongitud());
		String [] parts= r.split(" ");
		setUtmX(parts[0].trim());
		setUtmY(parts[1].trim());
		Mensaje.crearMensajeINFO("Punto Seleccionado: 17 N "+r+" ");
	}

	private String obtenerUTM(double lat, double lon) {
		String utm="";
		utm=Deg2UTM(lat, lon);
		return utm;
	}

	private String Deg2UTM(double Lat, double Lon) {
		double Easting;
		double Northing;
		int Zone;
		char Letter;
		Zone = (int) Math.floor(Lon / 6 + 31);
		if (Lat < -72)
			Letter = 'C';
		else if (Lat < -64)
			Letter = 'D';
		else if (Lat < -56)
			Letter = 'E';
		else if (Lat < -48)
			Letter = 'F';
		else if (Lat < -40)
			Letter = 'G';
		else if (Lat < -32)
			Letter = 'H';
		else if (Lat < -24)
			Letter = 'J';
		else if (Lat < -16)
			Letter = 'K';
		else if (Lat < -8)
			Letter = 'L';
		else if (Lat < 0)
			Letter = 'M';
		else if (Lat < 8)
			Letter = 'N';
		else if (Lat < 16)
			Letter = 'P';
		else if (Lat < 24)
			Letter = 'Q';
		else if (Lat < 32)
			Letter = 'R';
		else if (Lat < 40)
			Letter = 'S';
		else if (Lat < 48)
			Letter = 'T';
		else if (Lat < 56)
			Letter = 'U';
		else if (Lat < 64)
			Letter = 'V';
		else if (Lat < 72)
			Letter = 'W';
		else
			Letter = 'X';
		Easting = 0.5
				* Math.log((1 + Math.cos(Lat * Math.PI / 180)
						* Math.sin(Lon * Math.PI / 180 - (6 * Zone - 183) * Math.PI / 180))
						/ (1 - Math.cos(Lat * Math.PI / 180) * Math.sin(
								Lon * Math.PI / 180 - (6 * Zone - 183) * Math.PI / 180)))
				* 0.9996 * 6399593.62
				/ Math.pow(
						(1 + Math
								.pow(0.0820944379,
										2)
								* Math.pow(Math.cos(Lat * Math.PI / 180), 2)),
						0.5)
				* (1 + Math.pow(0.0820944379, 2) / 2
						* Math.pow(
								(0.5 * Math.log((1
										+ Math.cos(Lat * Math.PI / 180)
												* Math.sin(Lon * Math.PI / 180 - (6 * Zone - 183) * Math.PI / 180))
										/ (1 - Math.cos(Lat * Math.PI / 180)
												* Math.sin(Lon * Math.PI / 180 - (6 * Zone - 183) * Math.PI / 180)))),
								2)
						* Math.pow(Math.cos(Lat * Math.PI / 180), 2) / 3)
				+ 500000;
		Easting = Math.round(Easting * 100) * 0.01;
		Northing = (Math
				.atan(Math.tan(Lat * Math.PI / 180)
						/ Math.cos((Lon * Math.PI / 180 - (6 * Zone - 183) * Math.PI / 180)))
				- Lat * Math.PI
						/ 180)
				* 0.9996 * 6399593.625
				/ Math.sqrt(
						1 + 0.006739496742
								* Math.pow(
										Math.cos(
												Lat * Math.PI
														/ 180),
										2))
				* (1 + 0.006739496742 / 2
						* Math.pow(
								0.5 * Math.log((1 + Math.cos(Lat * Math.PI / 180)
										* Math.sin((Lon * Math.PI / 180 - (6 * Zone - 183) * Math.PI / 180)))
										/ (1 - Math.cos(Lat * Math.PI / 180) * Math.sin(
												(Lon * Math.PI / 180 - (6 * Zone - 183) * Math.PI / 180)))),
								2)
						* Math.pow(Math.cos(Lat * Math.PI / 180), 2))
				+ 0.9996 * 6399593.625 * (Lat * Math.PI / 180
						- 0.005054622556 * (Lat * Math.PI / 180 + Math.sin(2 * Lat * Math.PI / 180) / 2)
						+ 4.258201531e-05 * (3 * (Lat * Math.PI / 180 + Math.sin(2 * Lat * Math.PI / 180) / 2)
								+ Math.sin(2 * Lat * Math.PI / 180) * Math.pow(Math.cos(Lat * Math.PI / 180), 2)) / 4
						- 1.674057895e-07 * (5
								* (3 * (Lat * Math.PI / 180 + Math.sin(2 * Lat * Math.PI / 180) / 2)
										+ Math.sin(2 * Lat * Math.PI / 180)
												* Math.pow(Math.cos(Lat * Math.PI / 180), 2))
								/ 4
								+ Math.sin(2 * Lat * Math.PI / 180) * Math.pow(Math.cos(Lat * Math.PI / 180), 2)
										* Math.pow(Math.cos(Lat * Math.PI / 180), 2))
								/ 3);
		if (Letter < 'M')
			Northing = Northing + 10000000;
		Northing = Math.round(Northing * 100) * 0.01;
		return Easting + " " + Northing;
	}

	// ////////////////////////////////////////////ESTADISTICAS///////////////////////////////////////////

	/**
	 * Método para ir a estadísticas y cargar funcionalidades
	 * 
	 * @return
	 */
	public String irEstadistica() {
		this.llenarLista();
		this.cargarIncidencias();
		return "est_seguridad?faces-redirect=true";
	}

	public void llenarLista() {
		getL_estadistica().clear();
		for (SegRegistroEmergencia re : getL_seguridad()) {
			getL_estadistica().add(re);
		}
	}

	public void filtrarFechas() {
		getL_estadistica().clear();
		for (SegRegistroEmergencia re : getL_seguridad()) {
			if (getF_desde() == null || getF_hasta() == null) {
				Mensaje.crearMensajeWARN("No existe fechas para poder realizar el filtrado.");
				break;
			}
			if (getF_desde().after(getF_hasta())) {
				Mensaje.crearMensajeWARN("La fecha final es menor que la fecha inicio.");
				break;
			} else if (re.getSegFecha().after(f_desde) && re.getSegFecha().before(f_hasta)) {
				getL_estadistica().add(re);
			}
		}
		if (getL_estadistica().size() > 0) {
			this.cargarIncidencias();
		} else {
			Mensaje.crearMensajeWARN("El filtrado no presenta ninguna Incidencia.");
		}

	}

	/**
	 * Método para cargar todas las incidencias
	 */
	public void cargarIncidencias() {
		setTotalSAL(0);
		setTotalSEG(0);
		setTotalSER(0);
		setTotalSOC(0);
		setTotal(0);
		try {
			for (SegRegistroEmergencia seg : getL_estadistica()) {
				if (seg.getSegTipoEmergencia().equals(Cod_sal))
					setTotalSAL(getTotalSAL() + 1);
				if (seg.getSegTipoEmergencia().equals(Cod_soc))
					setTotalSOC(getTotalSOC() + 1);
				if (seg.getSegTipoEmergencia().equals(Cod_seg))
					setTotalSEG(getTotalSEG() + 1);
				if (seg.getSegTipoEmergencia().equals(Cod_ser))
					setTotalSER(getTotalSER() + 1);
				setTotal(getL_estadistica().size());
			}
			this.pie(getTotalSAL(), getTotalSOC(), getTotalSEG(), getTotalSER());
			this.marcarMapa();
			List<SegRegistroEmergencia> l = manager.findAllseguridad();
			this.crearHistograma(l);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Método de diagramado del pie
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 */
	public void pie(Integer a, Integer b, Integer c, Integer d) {
		pieModel = new PieChartModel();
		pieModel.set(Cod_sal, a);
		pieModel.set(Cod_soc, b);
		pieModel.set(Cod_seg, c);
		pieModel.set(Cod_ser, d);

		pieModel.setLegendPosition("e");
		pieModel.setFill(false);
		pieModel.setShowDataLabels(true);
		pieModel.setDiameter(150);
	}

	/**
	 * Método para dibujar el mapa
	 */
	public void marcarMapa() {
		geoModel1 = new DefaultMapModel();
		for (SegRegistroEmergencia seg : getL_estadistica()) {
			LatLng coord = new LatLng(seg.getSegLatitud(), seg.getSegLongitud());
			geoModel1.addOverlay(new Marker(coord, seg.getSegEmergencia()));
		}
	}

	/**
	 * Método para marcar el mapa segun la selección
	 * 
	 * @param v
	 */
	public void marcaPersonal(String v) {
		geoModel1 = new DefaultMapModel();
		if (v.equals("1")) {
			try {
				List<SegRegistroEmergencia> l = filtrarIncidencias(Cod_sal);
				for (SegRegistroEmergencia seg : l) {
					LatLng coord = new LatLng(seg.getSegLatitud(), seg.getSegLongitud());
					geoModel1.addOverlay(new Marker(coord, seg.getSegEmergencia()));
				}
				this.crearHistograma(l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (v.equals("2")) {
			try {
				List<SegRegistroEmergencia> l = filtrarIncidencias(Cod_soc);
				for (SegRegistroEmergencia seg : l) {
					LatLng coord = new LatLng(seg.getSegLatitud(), seg.getSegLongitud());
					geoModel1.addOverlay(new Marker(coord, seg.getSegEmergencia()));
				}
				this.crearHistograma(l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (v.equals("3")) {
			try {
				List<SegRegistroEmergencia> l = filtrarIncidencias(Cod_seg);
				for (SegRegistroEmergencia seg : l) {
					LatLng coord = new LatLng(seg.getSegLatitud(), seg.getSegLongitud());
					geoModel1.addOverlay(new Marker(coord, seg.getSegEmergencia()));
				}
				this.crearHistograma(l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (v.equals("4")) {
			try {
				List<SegRegistroEmergencia> l = filtrarIncidencias(Cod_ser);
				for (SegRegistroEmergencia seg : l) {
					LatLng coord = new LatLng(seg.getSegLatitud(), seg.getSegLongitud());
					geoModel1.addOverlay(new Marker(coord, seg.getSegEmergencia()));
				}
				this.crearHistograma(l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (v.equals("5")) {
			try {
				for (SegRegistroEmergencia seg : getL_estadistica()) {
					LatLng coord = new LatLng(seg.getSegLatitud(), seg.getSegLongitud());
					geoModel1.addOverlay(new Marker(coord, seg.getSegEmergencia()));
				}
				this.crearHistograma(getL_estadistica());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Método para filtrar una lista
	 * 
	 * @param codigo
	 * @return
	 */
	public List<SegRegistroEmergencia> filtrarIncidencias(String codigo) {
		List<SegRegistroEmergencia> l = new ArrayList<SegRegistroEmergencia>();
		for (SegRegistroEmergencia si : getL_estadistica()) {
			if (si.getSegTipoEmergencia().equals(codigo)) {
				l.add(si);
			}
		}
		return l;
	}

	/**
	 * 
	 * Método para regresar de vista
	 * 
	 * @return
	 */
	public String volverSeg() {
		return "seguridad?faces-redirect=true";
	}

	public void crearHistograma(List<SegRegistroEmergencia> lista) {
		// dateModel = new LineChartModel();
		dateModel = new BarChartModel();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// ChartSeries series = new ChartSeries();
		//
		// System.out.println(lista.size());
		// for (SegRegistroEmergencia re : lista) {
		// series.set(dateFormat.format(re.getSegFecha()), 0);
		// }
		// series.setLabel("");
		// dateModel.addSeries(series);

		List<SegRegistroEmergencia> l2 = obtenerLista(Cod_soc);
		if (l2 != null || l2.size() > 0) {
			ChartSeries series2 = new ChartSeries();
			series2.setLabel(Cod_soc);
			for (SegRegistroEmergencia r : lista) {
				for (SegRegistroEmergencia re : l2) {
					String f1 = dateFormat.format(r.getSegFecha());
					String f2 = dateFormat.format(re.getSegFecha());
					if (f1.equals(f2)) {
						series2.set(f2, incidenciasFechas(re, l2));
						break;
					} else {
						series2.set(f1, 0);
					}
				}
			}
			dateModel.addSeries(series2);
		}

		List<SegRegistroEmergencia> l3 = obtenerLista(Cod_seg);
		if (l3 != null || l3.size() > 0) {
			ChartSeries series3 = new ChartSeries();
			series3.setLabel(Cod_seg);
			for (SegRegistroEmergencia r : lista) {
				for (SegRegistroEmergencia re : l3) {
					String f1 = dateFormat.format(r.getSegFecha());
					String f2 = dateFormat.format(re.getSegFecha());
					if (f1.equals(f2)) {
						series3.set(f2, incidenciasFechas(re, l3));
						break;
					} else {
						series3.set(f1, 0);
					}
				}
			}
			dateModel.addSeries(series3);
		}

		List<SegRegistroEmergencia> l4 = obtenerLista(Cod_ser);
		if (l4 != null || l4.size() > 0) {
			ChartSeries series4 = new ChartSeries();
			series4.setLabel(Cod_ser);
			for (SegRegistroEmergencia r : lista) {
				for (SegRegistroEmergencia re : l4) {
					String f1 = dateFormat.format(r.getSegFecha());
					String f2 = dateFormat.format(re.getSegFecha());
					if (f1.equals(f2)) {
						series4.set(f2, incidenciasFechas(re, l4));
						break;
					} else {
						series4.set(f1, 0);
					}
				}
			}
			dateModel.addSeries(series4);
		}

		List<SegRegistroEmergencia> l1 = obtenerLista(Cod_sal);
		if (l1 != null || l1.size() > 0) {
			ChartSeries series1 = new ChartSeries();
			series1.setLabel(Cod_sal);
			for (SegRegistroEmergencia r : lista) {
				for (SegRegistroEmergencia re : l1) {
					String f1 = dateFormat.format(r.getSegFecha());
					String f2 = dateFormat.format(re.getSegFecha());
					if (f1.equals(f2)) {
						series1.set(f2, incidenciasFechas(re, l1));
					} else {
						series1.set(f1, 0);
					}
				}
			}
			dateModel.addSeries(series1);
		}

		Axis xAxis = dateModel.getAxis(AxisType.X);
		xAxis.setTickAngle(-50);
		xAxis.setLabel("Fechas");

		Axis yAxis = dateModel.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Incidencias");

		dateModel.setAnimate(true);
		dateModel.setLegendPosition("e");
	}

	/**
	 * Método para verificar fechas repetidas
	 * 
	 * @param r
	 * @param lista
	 * @return
	 */
	public Integer incidenciasFechas(SegRegistroEmergencia r, List<SegRegistroEmergencia> lista) {
		Integer v = 0;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (SegRegistroEmergencia re : lista) {
			if (dateFormat.format(r.getSegFecha()).equals(dateFormat.format(re.getSegFecha())))
				v = v + 1;
		}
		return v;
	}

	/**
	 * Método para obtener una lista por código
	 * 
	 * @param codigo
	 * @return
	 */
	public List<SegRegistroEmergencia> obtenerLista(String codigo) {
		List<SegRegistroEmergencia> l = new ArrayList<SegRegistroEmergencia>();
		for (SegRegistroEmergencia re : getL_estadistica()) {
			if (re.getSegTipoEmergencia().equals(codigo)) {
				l.add(re);
			}
		}
		return l;
	}

	///////////////////////////////////////// (TIPOS_DE_EMERGENCIAS)/////////////////////////////////
	/**
	 * Lista de Tipos
	 */
	public void cargarGeneros() {
		getL_tipos_emergencia().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_emergencia");
		for (GenCatalogoItemsDet i : completo) {
			getL_tipos_emergencia().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de sub_tipos
	 */
	public void cargarSubtipo() {
		getL_tipos_emergencia_1().clear();
		for (GenCatalogoItemsDet i : manager.AllofItems("cat_tipos_emergencia", getSegTipoEmergencia())) {
			getL_tipos_emergencia_1().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de sub_hijos
	 */
	public void cargarHijos() {
		getL_tipos_emergencia_2().clear();
		if (manager.AllofItems("cat_subtipos_emergencia", getSegSubTipo()) != null) {
			for (GenCatalogoItemsDet i : manager.AllofItems("cat_subtipos_emergencia", getSegSubTipo())) {
				getL_tipos_emergencia_2().add(i.getIteNombre());
			}
		}
	}

	/**
	 * Lista de Busqueda
	 */
	public void cargarBusqueda() {
		getL_busqueda().clear();
		try {
			for (GenFuncionariosInstitucion i : manager.findAllfuncionarios()) {
				getL_busqueda().add(new SelectItem(i.getGenPersona().getPerDni(), i.getGenPersona().getPerDni() + " | "
						+ i.getGenPersona().getPerNombres() + " " + i.getGenPersona().getPerApellidos()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metod para mostrar la ciudad dependiendo de la provincia
	 */
	public void mostrarSubTipo() {
		cargarSubtipo();
	}

	/**
	 * Metod para mostrar la ciudad dependiendo de la provincia
	 */
	public List<String> mostrarHijos() {
		cargarHijos();
		List<String> l = new ArrayList<String>();
		l.addAll(getL_tipos_emergencia_2());
		return l;
	}

	//////////////////////////// (PROCESO_DE_GUARDAR_ARCHIVO)////////////////////////////////////////////////

	// metodo para guardar la imagen en el servidor
	public void cargaArchivo(FileUploadEvent event) throws IOException {
		file = event.getFile();
		InputStream inputStream = null;
		OutputStream outputStream = null;

		if (file != null) {
			try {
				// Tomar PAD REAL
				// ServletContext servletContext = (ServletContext)
				// FacesContext.getCurrentInstance().getExternalContext()
				// .getContext();
				// String carpeta = servletContext.getRealPath(File.separator +
				// "resources/doc/doc_incidencias/");
				String carpeta = url_doc + "/doc_incidencias/";
				if (getPerDni() == null || getPerDni().isEmpty()) {
					Mensaje.crearMensajeWARN(
							"No se pudo cargar el archivo, persona no asignada. Por favor seleccione una.");
				} else {
					DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyyHHmm");
					setSegArchivo("Informe_" + idImagen() + dateFormat.format(getSegFecha()) + ".pdf");
					System.out.println("PAD------> " + carpeta);
					System.out.println("name------> " + getSegArchivo());
					outputStream = new FileOutputStream(new File(carpeta + File.separatorChar + getSegArchivo()));
					inputStream = file.getInputstream();

					int read = 0;
					byte[] bytes = new byte[1024];

					while ((read = inputStream.read(bytes)) != -1) {
						outputStream.write(bytes, 0, read);
					}
					Mensaje.crearMensajeINFO("Carga del archivo Correcta");
				}
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
		} else {
			Mensaje.crearMensajeWARN("No se pudo cargar el archivo");
		}
	}

	/**
	 * Método para comprobar el admin
	 */
	public void comprobaradmin() {
		try {
			List<SegIncidenciasAdmin> l = manager.findAlladmin();
			if (l != null || l.size() > 0) {
				for (SegIncidenciasAdmin i : l) {
					if (getUsuario().equals(i.getAdmUsuario())) {
						setControl(true);
						break;
					} else {
						setControl(false);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método para descargar un archivo excel
	 */
	public void descargarArchivo(SegRegistroEmergencia emergencia) {
		// ServletContext servletContext = (ServletContext)
		// FacesContext.getCurrentInstance().getExternalContext()
		// .getContext();
		// String contextPath = servletContext
		// .getRealPath(File.separator + "resources/doc/doc_incidencias/" +
		// emergencia.getSegArchivo()+"");
		// System.out.println(contextPath);
		if (emergencia.getSegArchivo() == null || emergencia.getSegArchivo().isEmpty()) {
			Mensaje.crearMensajeERROR("La incidencia no cuenta con un archivo asignado.");
		} else {
			String contextPath = url_doc + "/doc_incidencias/" + emergencia.getSegArchivo() + "";
			Funciones.descargarPDF(contextPath);
		}
	}

	//////////////////////////// (PROCESO_DE_GUARDAR_DOCUMENTO)////////////////////////////////////////////////

	// metodo para guardar la imagen en el servidor
	public void cargaDocumento(FileUploadEvent event) throws IOException {
		file = event.getFile();
		InputStream inputStream = null;
		OutputStream outputStream = null;

		if (file != null) {
			try {
				// Tomar PAD REAL
				// ServletContext servletContext = (ServletContext)
				// FacesContext.getCurrentInstance().getExternalContext()
				// .getContext();
				// String carpeta = servletContext.getRealPath(File.separator +
				// "resources/doc/doc_incidencias/");
				String carpeta = url_doc + "/doc_incidencias/";
				if (getPerDni() == null || getPerDni().isEmpty()) {
					Mensaje.crearMensajeWARN(
							"No se pudo cargar el archivo, persona no asignada. Por favor seleccione una.");
				} else {
					DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyyHHmm");
					setSegDocumento("Documento_" + idImagen() + dateFormat.format(getSegFecha()) + ".pdf");
					System.out.println("PAD------> " + carpeta);
					System.out.println("name------> " + getSegDocumento());
					outputStream = new FileOutputStream(new File(carpeta + File.separatorChar + getSegDocumento()));
					inputStream = file.getInputstream();

					int read = 0;
					byte[] bytes = new byte[1024];

					while ((read = inputStream.read(bytes)) != -1) {
						outputStream.write(bytes, 0, read);
					}
					Mensaje.crearMensajeINFO("Carga del archivo Correcta");
				}
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
		} else {
			Mensaje.crearMensajeWARN("No se pudo cargar el archivo");
		}
	}

	private String idImagen() {
		String retorno;
		if (getSegId() == null) {
			retorno = manager.seguridadId().toString();
		} else {
			retorno = getSegId().toString();
		}
		return retorno;
	}

	/**
	 * Método para descargar un archivo excel
	 */
	public void descargarDocumento(SegRegistroEmergencia emergencia) {
		// ServletContext servletContext = (ServletContext)
		// FacesContext.getCurrentInstance().getExternalContext()
		// .getContext();
		// String contextPath = servletContext
		// .getRealPath(File.separator + "resources/doc/doc_incidencias/" +
		// emergencia.getSegDocumento()+"");
		// System.out.println(contextPath);
		if (emergencia.getSegDocumento() == null || emergencia.getSegDocumento().isEmpty()) {
			Mensaje.crearMensajeERROR("La incidencia no cuenta con un documento de respaldo.");
		} else {
			String contextPath = url_doc + "/doc_incidencias/" + emergencia.getSegDocumento() + "";
			Funciones.descargarPDF(contextPath);
		}
	}
}
