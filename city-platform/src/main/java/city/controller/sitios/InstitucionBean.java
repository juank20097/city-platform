package city.controller.sitios;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import city.model.dao.entidades.GenCatalogoItem;
import city.model.dao.entidades.GenInstitucione;
import city.model.generic.Mensaje;
import city.model.manager.ManagerSitios;

/**
 * @author jestevez
 * 
 */
@SessionScoped
@ManagedBean
public class InstitucionBean {

	// Atributos de la Clase
	@EJB
	private ManagerSitios manager;

	// Atriutos de la clase sector
	private String insCodigo;
	private String insCategoria;
	private String insDescripcion;
	private String insEstado;
	private String insNombre;
	private String insRazonSocial;
	private String insRuc;

	// manejo de vistas
	List<SelectItem> l_estados;
	List<GenInstitucione> l_insti;

	// valor de edición e inserción
	private boolean edicion;

	public InstitucionBean() {
	}

	@PostConstruct
	public void init() {
		edicion = false;
		l_estados = new ArrayList<SelectItem>();
		l_insti = new ArrayList<GenInstitucione>();
		cargarEstados();
		cargarInstitucion();
	}

	/**
	 * @return the l_insti
	 */
	public List<GenInstitucione> getL_insti() {
		return l_insti;
	}

	/**
	 * @param l_insti
	 *            the l_insti to set
	 */
	public void setL_insti(List<GenInstitucione> l_insti) {
		this.l_insti = l_insti;
	}

	/**
	 * @return the insCodigo
	 */
	public String getInsCodigo() {
		return insCodigo;
	}

	/**
	 * @param insCodigo
	 *            the insCodigo to set
	 */
	public void setInsCodigo(String insCodigo) {
		this.insCodigo = insCodigo;
	}

	/**
	 * @return the insCategoria
	 */
	public String getInsCategoria() {
		return insCategoria;
	}

	/**
	 * @param insCategoria
	 *            the insCategoria to set
	 */
	public void setInsCategoria(String insCategoria) {
		this.insCategoria = insCategoria;
	}

	/**
	 * @return the insDescripcion
	 */
	public String getInsDescripcion() {
		return insDescripcion;
	}

	/**
	 * @param insDescripcion
	 *            the insDescripcion to set
	 */
	public void setInsDescripcion(String insDescripcion) {
		this.insDescripcion = insDescripcion;
	}

	/**
	 * @return the insEstado
	 */
	public String getInsEstado() {
		return insEstado;
	}

	/**
	 * @param insEstado
	 *            the insEstado to set
	 */
	public void setInsEstado(String insEstado) {
		this.insEstado = insEstado;
	}

	/**
	 * @return the insNombre
	 */
	public String getInsNombre() {
		return insNombre;
	}

	/**
	 * @param insNombre
	 *            the insNombre to set
	 */
	public void setInsNombre(String insNombre) {
		this.insNombre = insNombre;
	}

	/**
	 * @return the insRazonSocial
	 */
	public String getInsRazonSocial() {
		return insRazonSocial;
	}

	/**
	 * @param insRazonSocial
	 *            the insRazonSocial to set
	 */
	public void setInsRazonSocial(String insRazonSocial) {
		this.insRazonSocial = insRazonSocial;
	}

	/**
	 * @return the insRuc
	 */
	public String getInsRuc() {
		return insRuc;
	}

	/**
	 * @param insRuc
	 *            the insRuc to set
	 */
	public void setInsRuc(String insRuc) {
		this.insRuc = insRuc;
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
	 * Redirecciona a la pagina de creacion de una Institución
	 * 
	 * @return vista
	 */
	public String nuevaInstitucion() {
		edicion = false;
		return "ninstitucion?faces-redirect=true";
	}

	/**
	 * Permite la creacion o modificacion de una Institución
	 * 
	 * @return
	 */
	public String crearInstitucion() {
		String r = "";
		try {
			if (edicion) {
				manager.editarInstitucion(getInsCodigo(), getInsNombre(),
						getInsDescripcion(), getInsRuc(), getInsRazonSocial(),
						getInsCategoria(), getInsEstado());
				Mensaje.crearMensajeINFO("Actualizado - Institución Modificada");
			} else {
				manager.insertarInstitucion(getInsCodigo(), getInsNombre(),
						getInsDescripcion(), getInsRuc(), getInsRazonSocial(),
						getInsCategoria());
				Mensaje.crearMensajeINFO("Registrado - Institución Creada");
			}
			r = "institucion?faces-redirect=true";
			this.cleanDatos();
			this.cargarInstitucion();
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
		return r;
	}

	/**
	 * Metodo de seteo de datos
	 */
	public void cleanDatos() {
		setInsCodigo("");
		setInsNombre("");
		setInsCategoria("");
		setInsDescripcion("");
		setInsEstado("");
		setInsRazonSocial("");
		setInsRuc("");
		setEdicion(false);
	}

	/**
	 * Metodo para cargar una Institución para su edición
	 * 
	 * @param insti
	 * @return
	 */
	public String cargarInstitucion(GenInstitucione insti) {
		try {
			cargarEstados();
			setInsCodigo(insti.getInsCodigo());
			setInsCategoria(insti.getInsCategoria());
			setInsDescripcion(insti.getInsDescripcion());
			setInsEstado(insti.getInsEstado());
			setInsNombre(insti.getInsNombre());
			setInsRazonSocial(insti.getInsRazonSocial());
			setInsRuc(insti.getInsRuc());
			setEdicion(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ninstitucion?faces-redirect=true";
	}

	/**
	 * Metodo para setear datos y regresar la vista
	 * 
	 * @return
	 */
	public String cancelar() {
		this.cleanDatos();
		return "institucion?faces-redirect=true";
	}

	/**
	 * Lista de Institucion
	 */
	public void cargarInstitucion() {
		try {
			getL_insti().clear();
			getL_insti().addAll(manager.findAllInstituciones());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
