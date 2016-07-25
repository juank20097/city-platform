package city.controller.persona;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenFuncionariosInstitucion;
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
	private double segLatitud;
	private double segLongitud;

	private String DatoBusqueda;
	private String perDni;
	private String perNombre;
	private String perEmpresa;
	private String perCargo;

	// mensaje de validaci�n de campos
	private String sms_validacion;
	private boolean edicion;

	// listas de registros
	private List<SegRegistroEmergencia> l_seguridad;
	private List<SelectItem> l_tipos_emergencia;

	// mapa
	private Marker marker;
	private MapModel geoModel;

	// estadistica
	private static String Cod_sal = "M�dica";
	private static String Cod_soc = "Protecci�n Civil";
	private static String Cod_seg = "Seguridad";
	private static String Cod_ser = "Servicio P�blico";
	private int totalSAL;
	private int totalSOC;
	private int totalSEG;
	private int totalSER;
	private int total;

	private PieChartModel pieModel;
	private MapModel geoModel1;

	public SeguridadBean() {
	}

	@PostConstruct
	public void ini() {
		segLatitud = 0;
		segLongitud = 0;
		totalSAL = 0;
		totalSEG = 0;
		totalSER = 0;
		totalSOC = 0;
		total = 0;
		pieModel = new PieChartModel();
		l_seguridad = new ArrayList<SegRegistroEmergencia>();
		l_tipos_emergencia = new ArrayList<SelectItem>();
		geoModel = new DefaultMapModel();
		geoModel1 = new DefaultMapModel();
		// definicion de marcador principal
		LatLng coordenada = new LatLng(0.4044186, -78.17527749999999);
		geoModel.addOverlay(new Marker(coordenada, "Yachay Ciudad del Conocimiento"));
		marker = geoModel.getMarkers().get(0);
		marker.setDraggable(true);
		cargarIncidentes();
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
		setSegFecha(new Date());
		setEdicion(true);
		return "nseguridad?faces-redirect=true";
	}

	/**
	 * M�todo para cargar todos los select
	 */
	public void carga() {
		cargarGeneros();
	}

	/**
	 * Permite la creaci�n o modificaci�n de una persona
	 * 
	 * @return
	 */
	public String crearSeguridad() {
		String r = "";
		try {
			if (this.validarCampos()) {
				Mensaje.crearMensajeERROR(getSms_validacion());
			} else {
				if (edicion) {
					Integer id = manager.seguridadId();
					manager.insertarSeguridad(id, getPerDni(), getSegAccion(), getSegEmergencia(), getSegFecha(),
							getSegTipoEmergencia(), getSegLatitud(), getSegLongitud());
					Mensaje.crearMensajeINFO("Registrado - Incidente Creado");
					setEdicion(false);
				} else {
					manager.editarSeguridad(getSegId(), getSegAccion(), getSegEmergencia(), getSegFecha(),
							getSegTipoEmergencia(), getSegLatitud(), getSegLongitud());
					Mensaje.crearMensajeINFO("Actualizado - Incidente Modificado");
				}
				r = "seguridad?faces-redirect=true";
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
				|| (getPerNombre() == null || getPerNombre().isEmpty())) {
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
		setSegLatitud(0);
		setSegLongitud(0);
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
			getL_seguridad().addAll(manager.findAllseguridad());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Lista de Generos
	 */
	public void cargarGeneros() {
		getL_tipos_emergencia().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_emergencia");
		for (GenCatalogoItemsDet i : completo) {
			getL_tipos_emergencia().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * M�todo para buscar un funcionario
	 */
	public void BuscarPersona() {
		if (getDatoBusqueda() == null || getDatoBusqueda().isEmpty()) {
			Mensaje.crearMensajeWARN("Debe ingresar el dato para realizar la b�squeda.");
			setPerDni("");
			setPerCargo("");
			setPerEmpresa("");
			setPerNombre("");
		} else {
			try {
				if (Funciones.isNumeric(getDatoBusqueda())){
					GenFuncionariosInstitucion f = manager.findFuncionarioXDni(getDatoBusqueda());
					if (f == null) {
						Mensaje.crearMensajeWARN("el dato no pudo ser encontrada");
						setPerDni("");
						setPerCargo("");
						setPerEmpresa("");
						setPerNombre("");
					} else {
						setPerDni(f.getGenPersona().getPerDni());
						setPerCargo(f.getFunCargo());
						setPerEmpresa(f.getGenInstitucione().getInsNombre());
						setPerNombre(f.getGenPersona().getPerNombres() + " " + f.getGenPersona().getPerApellidos());
					}
				} else{
					List<GenFuncionariosInstitucion> f =manager.findFuncionarioXNombre(getDatoBusqueda());
					if (f==null || f.size()==0){
						Mensaje.crearMensajeWARN("el dato no pudo ser encontrada");
						setPerDni("");
						setPerCargo("");
						setPerEmpresa("");
						setPerNombre("");
					}else if (f.size()>1){
						Mensaje.crearMensajeWARN("el dato encontr� varias coincidencias, busque mejor por c�dula");
						setPerDni("");
						setPerCargo("");
						setPerEmpresa("");
						setPerNombre("");
					}else{
						GenFuncionariosInstitucion g=f.get(0);
						setPerDni(g.getGenPersona().getPerDni());
						setPerCargo(g.getFunCargo());
						setPerEmpresa(g.getGenInstitucione().getInsNombre());
						setPerNombre(g.getGenPersona().getPerNombres() + " " + g.getGenPersona().getPerApellidos());
					}
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
		Mensaje.crearMensajeINFO("Punto Seleccionado:" + getSegLatitud() + " " + getSegLongitud());
	}

	// ////////////////////////////////////////////ESTADISTICAS///////////////////////////////////////////

	/**
	 * M�todo para cargar todas las incidencias
	 */
	public String cargarIncidencias() {
		setTotalSAL(0);
		setTotalSEG(0);
		setTotalSER(0);
		setTotalSOC(0);
		setTotal(0);
		try {
			for (SegRegistroEmergencia seg : getL_seguridad()) {
				if (seg.getSegTipoEmergencia().equals(Cod_sal))
					setTotalSAL(getTotalSAL() + 1);
				if (seg.getSegTipoEmergencia().equals(Cod_soc))
					setTotalSOC(getTotalSOC() + 1);
				if (seg.getSegTipoEmergencia().equals(Cod_seg))
					setTotalSEG(getTotalSEG() + 1);
				if (seg.getSegTipoEmergencia().equals(Cod_ser))
					setTotalSER(getTotalSER() + 1);
				setTotal(getL_seguridad().size());
				this.pie(getTotalSAL(), getTotalSOC(), getTotalSEG(), getTotalSER());
				this.marcarMapa();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "est_seguridad?faces-redirect=true";
	}

	/**
	 * M�todo de diagramado del pie
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 */
	public void pie(Integer a, Integer b, Integer c, Integer d) {
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
	 * M�todo para dibujar el mapa
	 */
	public void marcarMapa() {
		for (SegRegistroEmergencia seg : getL_seguridad()) {
			LatLng coord = new LatLng(seg.getSegLatitud(), seg.getSegLongitud());
			geoModel1.addOverlay(new Marker(coord, seg.getSegEmergencia()));
		}
	}

	/**
	 * M�todo para marcar el mapa segun la selecci�n
	 * 
	 * @param v
	 */
	public void marcaPersonal(String v) {
		geoModel1 = new DefaultMapModel();
		if (v.equals("1")) {
			try {
				List<SegRegistroEmergencia> l = manager.findSeguridadxTipo(Cod_sal);
				for (SegRegistroEmergencia seg : l) {
					LatLng coord = new LatLng(seg.getSegLatitud(), seg.getSegLongitud());
					geoModel1.addOverlay(new Marker(coord, seg.getSegEmergencia()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (v.equals("2")) {
			try {
				List<SegRegistroEmergencia> l = manager.findSeguridadxTipo(Cod_soc);
				for (SegRegistroEmergencia seg : l) {
					LatLng coord = new LatLng(seg.getSegLatitud(), seg.getSegLongitud());
					geoModel1.addOverlay(new Marker(coord, seg.getSegEmergencia()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (v.equals("3")) {
			try {
				List<SegRegistroEmergencia> l = manager.findSeguridadxTipo(Cod_seg);
				for (SegRegistroEmergencia seg : l) {
					LatLng coord = new LatLng(seg.getSegLatitud(), seg.getSegLongitud());
					geoModel1.addOverlay(new Marker(coord, seg.getSegEmergencia()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (v.equals("4")) {
			try {
				List<SegRegistroEmergencia> l = manager.findSeguridadxTipo(Cod_ser);
				for (SegRegistroEmergencia seg : l) {
					LatLng coord = new LatLng(seg.getSegLatitud(), seg.getSegLongitud());
					geoModel1.addOverlay(new Marker(coord, seg.getSegEmergencia()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (v.equals("5")) {
			try {
				for (SegRegistroEmergencia seg : getL_seguridad()) {
					LatLng coord = new LatLng(seg.getSegLatitud(), seg.getSegLongitud());
					geoModel1.addOverlay(new Marker(coord, seg.getSegEmergencia()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String volverSeg() {
		return "seguridad?faces-redirect=true";
	}
}
