package city.controller.persona;

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

import org.primefaces.context.RequestContext;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GenCatalogoItemsDet;

import city.model.dao.entidades.GenFuncionariosInstitucion;
import city.model.dao.entidades.SegRegistroEmergencia;
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
	private String segUbicacion;

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

	// mapa
	private Marker marker;
	private MapModel geoModel;

	public SeguridadBean() {
	}

	@PostConstruct
	public void ini() {
		l_seguridad = new ArrayList<SegRegistroEmergencia>();
		l_tipos_emergencia = new ArrayList<SelectItem>();
		geoModel = new DefaultMapModel();
		// definicion de marcador principal
		LatLng coordenada = new LatLng(0.4044186, -78.17527749999999);
		geoModel.addOverlay(new Marker(coordenada, "Yachay Ciudad del Conocimiento"));
		marker = geoModel.getMarkers().get(0);
		marker.setDraggable(true);
		cargarIncidentes();
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
	 * @return the segUbicacion
	 */
	public String getSegUbicacion() {
		return segUbicacion;
	}

	/**
	 * @param segUbicacion
	 *            the segUbicacion to set
	 */
	public void setSegUbicacion(String segUbicacion) {
		this.segUbicacion = segUbicacion;
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
		setEdicion(true);
		return "nseguridad?faces-redirect=true";
	}

	/**
	 * Método para cargar todos los select
	 */
	public void carga() {
		cargarGeneros();
	}

	/**
	 * Permite la creación o modificación de una persona
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
					Integer id=manager.seguridadId();
					manager.insertarSeguridad(id,getPerDni(), getSegAccion(), getSegEmergencia(), getSegFecha(),
							getSegTipoEmergencia(), getSegUbicacion());
					Mensaje.crearMensajeINFO("Registrado - Incidente Creado");
					setEdicion(false);
				} else {
					manager.editarSeguridad(getSegId(), getSegAccion(), getSegEmergencia(), getSegFecha(),
							getSegTipoEmergencia(), getSegUbicacion());
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
//				|| (getSegUbicacion() == null || getSegUbicacion().isEmpty())
				|| (getPerDni() == null || getPerDni().isEmpty())
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
		setSegFecha(new Timestamp(new Date().getTime()));
		setSegTipoEmergencia("S/N");
		setSegUbicacion("");
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
			setPerCargo(incidente.getGenFuncionariosInstitucion().getFunCargo());
			setPerDni(incidente.getGenFuncionariosInstitucion().getGenPersona().getPerDni());
			setPerEmpresa(incidente.getGenFuncionariosInstitucion().getGenInstitucione().getInsNombre());
			setPerNombre(incidente.getGenFuncionariosInstitucion().getGenPersona().getPerNombres() + " "
					+ incidente.getGenFuncionariosInstitucion().getGenPersona().getPerApellidos());
			setSegAccion(incidente.getSegAccion());
			setSegEmergencia(incidente.getSegEmergencia());
			setSegFecha(incidente.getSegFecha());
			setSegTipoEmergencia(incidente.getSegTipoEmergencia());
			setSegUbicacion(incidente.getSegUbicacion());
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
	 * Método para buscar un funcionario
	 */
	public void BuscarPersona() {
		if (getPerDni() == null || getPerDni().isEmpty()) {
			Mensaje.crearMensajeWARN("Debe ingresar la cédula para realizar la búsqueda.");
			setPerCargo("");
			setPerEmpresa("");
			setPerNombre("");
		} else {
			try {
				GenFuncionariosInstitucion f = manager.findFuncionarioXDni(getPerDni());
				if (f == null) {
					Mensaje.crearMensajeWARN("La cédula no pudo ser encontrada");
					setPerCargo("");
					setPerEmpresa("");
					setPerNombre("");
				} else {
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
		setSegUbicacion(marker.getLatlng() + "");
		Mensaje.crearMensajeINFO("Punto Seleccionado:" + getSegUbicacion() + ";");
	}

}
