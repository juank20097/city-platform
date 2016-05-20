package city.controller.sitios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import city.model.dao.entidades.GenArea;
import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenInstitucione;
import city.model.dao.entidades.GenSitio;
import city.model.dao.entidades.GenTipoSitio;
import city.model.generic.Mensaje;
import city.model.manager.ManagerSitios;

/**
 * @author jestevez
 * 
 */
@SessionScoped
@ManagedBean
public class SitioBean {

	// Atributos de la Clase
	@EJB
	private ManagerSitios manager;

	// Atriutos de la clase
	private String sitId;
	private String sitCallePrincipal;
	private String sitCalleSecundaria;
	private Integer sitCapacidad;
	private BigDecimal sitCostoArriendo;
	private String sitDescripcion;
	private String sitEstado;
	private Boolean sitIntegracionAccesos;
	private String sitNombre;
	private String sitNumero;
	private Integer sitPiso;
	private Integer area;
	private Integer tsitio;
	private String institucion;

	// manejo de vistas
	List<GenSitio> l_sitios;
	List<SelectItem> l_areas;
	List<SelectItem> l_estados;
	List<SelectItem> l_tsitios;
	List<SelectItem> l_insti;

	// valor de edición e inserción
	private boolean edicion;

	public SitioBean() {
	}

	@PostConstruct
	public void init() {
		edicion = false;
		l_sitios = new ArrayList<GenSitio>();
		l_estados = new ArrayList<SelectItem>();
		l_tsitios = new ArrayList<SelectItem>();
		l_insti = new ArrayList<SelectItem>();
		cargarSitios();
		cargarAreas();
		cargarInstituciones();
		cargarTipoSitios();
	}

	/**
	 * @return the area
	 */
	public Integer getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(Integer area) {
		this.area = area;
	}

	/**
	 * @return the tsitio
	 */
	public Integer getTsitio() {
		return tsitio;
	}

	/**
	 * @param tsitio
	 *            the tsitio to set
	 */
	public void setTsitio(Integer tsitio) {
		this.tsitio = tsitio;
	}

	/**
	 * @return the institucion
	 */
	public String getInstitucion() {
		return institucion;
	}

	/**
	 * @param institucion
	 *            the institucion to set
	 */
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	/**
	 * @return the sitId
	 */
	public String getSitId() {
		return sitId;
	}

	/**
	 * @param sitId
	 *            the sitId to set
	 */
	public void setSitId(String sitId) {
		this.sitId = sitId;
	}

	/**
	 * @return the sitCallePrincipal
	 */
	public String getSitCallePrincipal() {
		return sitCallePrincipal;
	}

	/**
	 * @param sitCallePrincipal
	 *            the sitCallePrincipal to set
	 */
	public void setSitCallePrincipal(String sitCallePrincipal) {
		this.sitCallePrincipal = sitCallePrincipal;
	}

	/**
	 * @return the sitCalleSecundaria
	 */
	public String getSitCalleSecundaria() {
		return sitCalleSecundaria;
	}

	/**
	 * @param sitCalleSecundaria
	 *            the sitCalleSecundaria to set
	 */
	public void setSitCalleSecundaria(String sitCalleSecundaria) {
		this.sitCalleSecundaria = sitCalleSecundaria;
	}

	/**
	 * @return the sitCapacidad
	 */
	public Integer getSitCapacidad() {
		return sitCapacidad;
	}

	/**
	 * @param sitCapacidad
	 *            the sitCapacidad to set
	 */
	public void setSitCapacidad(Integer sitCapacidad) {
		this.sitCapacidad = sitCapacidad;
	}

	/**
	 * @return the sitCostoArriendo
	 */
	public BigDecimal getSitCostoArriendo() {
		return sitCostoArriendo;
	}

	/**
	 * @param sitCostoArriendo
	 *            the sitCostoArriendo to set
	 */
	public void setSitCostoArriendo(BigDecimal sitCostoArriendo) {
		this.sitCostoArriendo = sitCostoArriendo;
	}

	/**
	 * @return the sitDescripcion
	 */
	public String getSitDescripcion() {
		return sitDescripcion;
	}

	/**
	 * @param sitDescripcion
	 *            the sitDescripcion to set
	 */
	public void setSitDescripcion(String sitDescripcion) {
		this.sitDescripcion = sitDescripcion;
	}

	/**
	 * @return the sitEstado
	 */
	public String getSitEstado() {
		return sitEstado;
	}

	/**
	 * @param sitEstado
	 *            the sitEstado to set
	 */
	public void setSitEstado(String sitEstado) {
		this.sitEstado = sitEstado;
	}

	/**
	 * @return the sitIntegracionAccesos
	 */
	public Boolean getSitIntegracionAccesos() {
		return sitIntegracionAccesos;
	}

	/**
	 * @param sitIntegracionAccesos
	 *            the sitIntegracionAccesos to set
	 */
	public void setSitIntegracionAccesos(Boolean sitIntegracionAccesos) {
		this.sitIntegracionAccesos = sitIntegracionAccesos;
	}

	/**
	 * @return the sitNombre
	 */
	public String getSitNombre() {
		return sitNombre;
	}

	/**
	 * @param sitNombre
	 *            the sitNombre to set
	 */
	public void setSitNombre(String sitNombre) {
		this.sitNombre = sitNombre;
	}

	/**
	 * @return the sitNumero
	 */
	public String getSitNumero() {
		return sitNumero;
	}

	/**
	 * @param sitNumero
	 *            the sitNumero to set
	 */
	public void setSitNumero(String sitNumero) {
		this.sitNumero = sitNumero;
	}

	/**
	 * @return the sitPiso
	 */
	public Integer getSitPiso() {
		return sitPiso;
	}

	/**
	 * @param sitPiso
	 *            the sitPiso to set
	 */
	public void setSitPiso(Integer sitPiso) {
		this.sitPiso = sitPiso;
	}

	/**
	 * @return the l_sitios
	 */
	public List<GenSitio> getL_sitios() {
		return l_sitios;
	}

	/**
	 * @param l_sitios
	 *            the l_sitios to set
	 */
	public void setL_sitios(List<GenSitio> l_sitios) {
		this.l_sitios = l_sitios;
	}

	/**
	 * @return the l_areas
	 */
	public List<SelectItem> getL_areas() {
		return l_areas;
	}

	/**
	 * @param l_areas
	 *            the l_areas to set
	 */
	public void setL_areas(List<SelectItem> l_areas) {
		this.l_areas = l_areas;
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
	 * @return the l_tsitios
	 */
	public List<SelectItem> getL_tsitios() {
		return l_tsitios;
	}

	/**
	 * @param l_tsitios
	 *            the l_tsitios to set
	 */
	public void setL_tsitios(List<SelectItem> l_tsitios) {
		this.l_tsitios = l_tsitios;
	}

	/**
	 * @return the l_insti
	 */
	public List<SelectItem> getL_insti() {
		return l_insti;
	}

	/**
	 * @param l_insti
	 *            the l_insti to set
	 */
	public void setL_insti(List<SelectItem> l_insti) {
		this.l_insti = l_insti;
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
	 * Redirecciona a la pagina de creacion de sitio
	 * 
	 * @return vista
	 */
	public String nuevoSitio() {
		edicion = false;
		return "nsitio?faces-redirect=true";
	}

	/**
	 * Permite la creacion o modificacion de un sitio
	 * 
	 * @return
	 */
	public String crearSitio() {
		String r = "";
		try {
			if (!validarCampos()) {
				if (edicion) {
					manager.editarSitio(getSitId(), getTsitio(), getArea(),
							getInstitucion(), getSitNombre(), getSitNumero(),
							getSitDescripcion(), getSitCallePrincipal(),
							getSitCalleSecundaria(), getSitCapacidad(),
							getSitCostoArriendo(), getSitPiso(),
							getSitIntegracionAccesos(), getSitEstado());
					Mensaje.crearMensajeINFO("Actualizado - Sitio Modificada");
				} else {
					manager.insertarSitio(getSitId(), getTsitio(), getArea(),
							getInstitucion(), getSitNombre(), getSitNumero(),
							getSitDescripcion(), getSitCallePrincipal(),
							getSitCalleSecundaria(), getSitCapacidad(),
							getSitCostoArriendo(), getSitPiso(),
							getSitIntegracionAccesos());
					Mensaje.crearMensajeINFO("Registrado - Sitio Creada");
				}
				r = "sitio?faces-redirect=true";
				this.cleanDatos();
				this.cargarSitios();
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
		return r;
	}

	/**
	 * Metodo para valida campos de SelctItems
	 * 
	 * @return
	 */
	public boolean validarCampos() {
		if (getArea() == null || getArea() == -1) {
			Mensaje.crearMensajeERROR("Campo area requerido");
			return true;
		} else if (getTsitio() == null || getTsitio() == -1) {
			Mensaje.crearMensajeERROR("Campo tipo sitio requerido");
			return true;
		} else if (getInstitucion() == null || getInstitucion().equals("-1")) {
			Mensaje.crearMensajeERROR("Campo institución requerido");
			return true;
		} else if ((edicion == true && getSitEstado() == null)
				|| (edicion == true && getSitEstado().equals("-1"))) {
			Mensaje.crearMensajeERROR("Campo estado requerido");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo de seteo de datos
	 */
	public void cleanDatos() {
		setSitId("");
		setArea(0);
		setInstitucion("");
		setSitCallePrincipal("");
		setSitCalleSecundaria("");
		setSitCapacidad(0);
		setSitCostoArriendo(null);
		setSitDescripcion("");
		setSitEstado("");
		setSitIntegracionAccesos(null);
		setSitNombre("");
		setSitNumero("");
		setSitPiso(0);
		setTsitio(0);
		setEdicion(false);
	}

	/**
	 * Metodo para cargar un sitio para su edicion
	 * 
	 * @param persona
	 * @return
	 */
	public String cargarSitio(GenSitio sitio) {
		try {
			cargarEstados();
			setSitId(sitio.getSitId());
			setArea(sitio.getGenArea().getAreId());
			setInstitucion(sitio.getGenInstitucione().getInsCodigo());
			setSitCallePrincipal(sitio.getSitCallePrincipal());
			setSitCalleSecundaria(sitio.getSitCalleSecundaria());
			setSitCapacidad(sitio.getSitCapacidad());
			setSitCostoArriendo(sitio.getSitCostoArriendo());
			setSitDescripcion(sitio.getSitDescripcion());
			setSitEstado(sitio.getSitEstado());
			setSitIntegracionAccesos(sitio.getSitIntegracionAccesos());
			setSitNombre(sitio.getSitNombre());
			setSitNumero(sitio.getSitNumero());
			setSitPiso(sitio.getSitPiso());
			setTsitio(sitio.getGenTipoSitio().getTsiId());
			setEdicion(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "nsitio?faces-redirect=true";
	}

	/**
	 * Metodo para setear datos y regresar la vista
	 * 
	 * @return
	 */
	public String cancelar() {
		this.cleanDatos();
		this.cargarSitios();
		return "sitio?faces-redirect=true";
	}

	/**
	 * Lista de Sitios
	 */
	public void cargarSitios() {
		try {
			getL_sitios().clear();
			getL_sitios().addAll(manager.findAllSitios());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Lista de Areas
	 */
	public void cargarAreas() {
		getL_areas().clear();
		for (GenArea i : manager.AllAreasActivas()) {
			getL_areas().add(new SelectItem(i.getAreId(), i.getAreNombre()));
		}
	}

	/**
	 * Lista de Intituciones
	 */
	public void cargarInstituciones() {
		getL_insti().clear();
		for (GenInstitucione i : manager.AllInstitucionesActivas()) {
			getL_insti()
					.add(new SelectItem(i.getInsCodigo(), i.getInsNombre()));
		}
	}

	/**
	 * Lista de Tipos Sitios
	 */
	public void cargarTipoSitios() {
		getL_tsitios().clear();
		for (GenTipoSitio i : manager.AllTipoSitioActivos()) {
			getL_tsitios().add(new SelectItem(i.getTsiId(), i.getTsiNombre()));
		}
	}

	/**
	 * Lista de Estado
	 */
	public void cargarEstados() {
		getL_estados().clear();
		for (GenCatalogoItemsDet i : manager.AllofItems("cat_estados")) {
			getL_estados().add(
					new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

}
