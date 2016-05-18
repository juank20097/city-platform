package city.controller.sitios;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import city.model.dao.entidades.GenArea;
import city.model.dao.entidades.GenCatalogoItem;
import city.model.dao.entidades.GenSectore;
import city.model.generic.Mensaje;
import city.model.manager.ManagerSitios;

/**
 * @author jestevez
 * 
 */
/**
 * @author jestevez
 *
 */
/**
 * @author jestevez
 *
 */
/**
 * @author jestevez
 * 
 */
@SessionScoped
@ManagedBean
public class AreaBean {

	// Atributos de la Clase
	@EJB
	private ManagerSitios manager;

	// Atriutos de la clase sector
	private Integer areId;
	private String areDescripcion;
	private String areEstado;
	private String areLatitud;
	private String areLongitud;
	private String areNombre;
	private String arePadre;
	private Integer sector;

	// manejo de vistas
	List<GenArea> l_areas;
	List<SelectItem> l_areas_s;
	List<SelectItem> l_estados;
	List<SelectItem> l_sector;

	// Datos de Mapas
	private MapModel geoModel;
	private Marker marker;

	// valor de edición e inserción
	private boolean edicion;

	public AreaBean() {
	}

	@PostConstruct
	public void init() {
		edicion = false;
		l_areas = new ArrayList<GenArea>();
		l_estados = new ArrayList<SelectItem>();
		l_sector = new ArrayList<SelectItem>();
		l_areas_s = new ArrayList<SelectItem>();
		geoModel = new DefaultMapModel();
		// definicion de marcador principal
		LatLng coordenada = new LatLng(0.4044186, -78.17527749999999);
		geoModel.addOverlay(new Marker(coordenada,
				"Yachay Ciudad del Conocimiento"));
		marker = geoModel.getMarkers().get(0);
		marker.setDraggable(true);
		cargarArea();
		cargarSector();
		cargarAreaSelectItem();
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
	 * @return the l_areas_s
	 */
	public List<SelectItem> getL_areas_s() {
		return l_areas_s;
	}

	/**
	 * @param l_areas_s
	 *            the l_areas_s to set
	 */
	public void setL_areas_s(List<SelectItem> l_areas_s) {
		this.l_areas_s = l_areas_s;
	}

	/**
	 * @return the areId
	 */
	public Integer getAreId() {
		return areId;
	}

	/**
	 * @param areId
	 *            the areId to set
	 */
	public void setAreId(Integer areId) {
		this.areId = areId;
	}

	/**
	 * @return the areDescripcion
	 */
	public String getAreDescripcion() {
		return areDescripcion;
	}

	/**
	 * @param areDescripcion
	 *            the areDescripcion to set
	 */
	public void setAreDescripcion(String areDescripcion) {
		this.areDescripcion = areDescripcion;
	}

	/**
	 * @return the areEstado
	 */
	public String getAreEstado() {
		return areEstado;
	}

	/**
	 * @param areEstado
	 *            the areEstado to set
	 */
	public void setAreEstado(String areEstado) {
		this.areEstado = areEstado;
	}

	/**
	 * @return the areLatitud
	 */
	public String getAreLatitud() {
		return areLatitud;
	}

	/**
	 * @param areLatitud
	 *            the areLatitud to set
	 */
	public void setAreLatitud(String areLatitud) {
		this.areLatitud = areLatitud;
	}

	/**
	 * @return the areLongitud
	 */
	public String getAreLongitud() {
		return areLongitud;
	}

	/**
	 * @param areLongitud
	 *            the areLongitud to set
	 */
	public void setAreLongitud(String areLongitud) {
		this.areLongitud = areLongitud;
	}

	/**
	 * @return the areNombre
	 */
	public String getAreNombre() {
		return areNombre;
	}

	/**
	 * @param areNombre
	 *            the areNombre to set
	 */
	public void setAreNombre(String areNombre) {
		this.areNombre = areNombre;
	}

	/**
	 * @return the arePadre
	 */
	public String getArePadre() {
		return arePadre;
	}

	/**
	 * @param arePadre
	 *            the arePadre to set
	 */
	public void setArePadre(String arePadre) {
		this.arePadre = arePadre;
	}

	/**
	 * @return the l_areas
	 */
	public List<GenArea> getL_areas() {
		return l_areas;
	}

	/**
	 * @param l_areas
	 *            the l_areas to set
	 */
	public void setL_areas(List<GenArea> l_areas) {
		this.l_areas = l_areas;
	}

	/**
	 * @return the l_sector
	 */
	public List<SelectItem> getL_sector() {
		return l_sector;
	}

	/**
	 * @param l_sector
	 *            the l_sector to set
	 */
	public void setL_sector(List<SelectItem> l_sector) {
		this.l_sector = l_sector;
	}

	/**
	 * @return the l_estados
	 */
	public List<SelectItem> getL_estados() {
		return l_estados;
	}

	/**
	 * @param l_estados
	 *            the l_estados to set
	 */
	public void setL_estados(List<SelectItem> l_estados) {
		this.l_estados = l_estados;
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
	 * @return the sector
	 */
	public Integer getSector() {
		return sector;
	}

	/**
	 * @param sector
	 *            the sector to set
	 */
	public void setSector(Integer sector) {
		this.sector = sector;
	}

	/**
	 * Redirecciona a la pagina de creacion de area
	 * 
	 * @return vista
	 */
	public String nuevaArea() {
		edicion = false;
		return "narea?faces-redirect=true";
	}

	/**
	 * Permite la creacion o modificacion de un area
	 * 
	 * @return
	 */
	public String crearArea() {
		String r = "";
		try {
			if (!validarCampos()) {
				if (edicion) {
					manager.editarArea(getAreId(), getSector(), getAreNombre(),
							getAreDescripcion(), getAreLatitud(),
							getAreLongitud(), getArePadre(), getAreEstado());
					Mensaje.crearMensajeINFO("Actualizado - Area Modificada");
				} else {
					manager.insertarArea(getSector(), getAreNombre(),
							getAreDescripcion(), getAreLatitud(),
							getAreLongitud(), getArePadre());
					Mensaje.crearMensajeINFO("Registrado - Area Creada");
				}
				r = "area?faces-redirect=true";
				this.cleanDatos();
				this.cargarArea();
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
		return r;
	}

	public boolean validarCampos() {
		if (getSector() == null || getSector() == -1) {
			Mensaje.crearMensajeERROR("Campo sector requerido");
			return true;
		} else {
			if ((edicion == true && getAreEstado() == null)
					|| (edicion == true && getAreEstado().equals("-1"))) {
				Mensaje.crearMensajeERROR("Campo estado requerido");
				return true;
			}
			;
			return false;
		}
	}

	/**
	 * Metodo de seteo de datos
	 */
	public void cleanDatos() {
		setAreId(0);
		setSector(0);
		setAreDescripcion("");
		setAreEstado("");
		setAreLatitud("");
		setAreLongitud("");
		setAreNombre("");
		setArePadre("");
		setEdicion(false);
	}

	/**
	 * Metodo para cargar un area para su edicion
	 * 
	 * @param persona
	 * @return
	 */
	public String cargarArea(GenArea area) {
		try {
			cargarEstados();
			setAreId(area.getAreId());
			setSector(area.getGenSectore().getSecId());
			setAreDescripcion(area.getAreDescripcion());
			setAreEstado(area.getAreEstado());
			setAreLatitud(area.getAreLatitud());
			setAreLongitud(area.getAreLongitud());
			setAreNombre(area.getAreNombre());
			setArePadre(area.getArePadre());
			setEdicion(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "narea?faces-redirect=true";
	}

	/**
	 * Metodo para setear datos y regresar la vista
	 * 
	 * @return
	 */
	public String cancelar() {
		this.cleanDatos();
		this.cargarArea();
		return "area?faces-redirect=true";
	}

	/**
	 * Lista de Areas
	 */
	public void cargarArea() {
		try {
			getL_areas().clear();
			getL_areas().addAll(manager.findAllAreas());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Lista de Areas SelectItems
	 */
	public void cargarAreaSelectItem() {
		getL_areas_s().clear();
		for (GenArea i : l_areas) {
			getL_areas_s().add(new SelectItem(i.getAreId(), i.getAreNombre()));
		}
	}

	/**
	 * Lista de Sector
	 */
	public void cargarSector() {
		getL_sector().clear();
		for (GenSectore i : manager.AllSectoresActivos()) {
			getL_sector().add(new SelectItem(i.getSecId(), i.getSecNombre()));
		}
	}

	/**
	 * Lista de Estado
	 */
	public void cargarEstados() {
		getL_estados().clear();
		for (GenCatalogoItem i : manager.AllofItems("cat_estados")) {
			getL_estados().add(
					new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	// metodo de Google Maps

	/**
	 * Metodo para tomar el punto de seleccion en el mapa
	 * 
	 * @param event
	 */
	public void TomarMarca(MarkerDragEvent event) {
		marker = event.getMarker();
		setAreLatitud(marker.getLatlng().getLat() + "");
		setAreLongitud(marker.getLatlng().getLng() + "");
		Mensaje.crearMensajeINFO("Punto Seleccionado:" + getAreLatitud()
				+ " , " + getAreLongitud() + "");
	}

	/**
	 * Metodo para tomar el punto de seleccion en el mapa
	 * 
	 */
	public void abrirDialog() {
		RequestContext.getCurrentInstance().execute("PF('mapDialog').show()");
	}

}
