package city.controller.sitios;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;


import city.model.dao.entidades.GenCatalogoItem;
import city.model.dao.entidades.GenTipoSitio;
import city.model.generic.Mensaje;
import city.model.manager.ManagerSitios;

/**
 * @author jestevez
 * 
 */
@SessionScoped
@ManagedBean
public class TipoSitioBean {

	// Atributos de la Clase
	@EJB
	private ManagerSitios manager;

	// Atriutos de la clase sector
	private Integer tsiId;
	private String tsiDescripcion;
	private String tsiEstado;
	private String tsiNombre;

	// manejo de vistas
	List<SelectItem> l_estados;

	// valor de edici�n e inserci�n
	private boolean edicion;

	public TipoSitioBean() {
	}

	@PostConstruct
	public void init() {
		edicion = false;
		l_estados = new ArrayList<SelectItem>();
		cargarEstados();
	}

	/**
	 * @return the tsiId
	 */
	public Integer getTsiId() {
		return tsiId;
	}

	/**
	 * @param tsiId
	 *            the tsiId to set
	 */
	public void setTsiId(Integer tsiId) {
		this.tsiId = tsiId;
	}

	/**
	 * @return the tsiDescripcion
	 */
	public String getTsiDescripcion() {
		return tsiDescripcion;
	}

	/**
	 * @param tsiDescripcion
	 *            the tsiDescripcion to set
	 */
	public void setTsiDescripcion(String tsiDescripcion) {
		this.tsiDescripcion = tsiDescripcion;
	}

	/**
	 * @return the tsiEstado
	 */
	public String getTsiEstado() {
		return tsiEstado;
	}

	/**
	 * @param tsiEstado
	 *            the tsiEstado to set
	 */
	public void setTsiEstado(String tsiEstado) {
		this.tsiEstado = tsiEstado;
	}

	/**
	 * @return the tsiNombre
	 */
	public String getTsiNombre() {
		return tsiNombre;
	}

	/**
	 * @param tsiNombre
	 *            the tsiNombre to set
	 */
	public void setTsiNombre(String tsiNombre) {
		this.tsiNombre = tsiNombre;
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
	 * Redirecciona a la pagina de creacion de un tipo sitio
	 * 
	 * @return vista
	 */
	public String nuevoTipoSitio() {
		edicion = false;
		return "ntsitio?faces-redirect=true";
	}

	/**
	 * Permite la creacion o modificacion de un tipo sitio
	 * 
	 * @return
	 */
	public String crearTipoSitio() {
		String r = "";
		try {
			if (edicion) {
				manager.editarTipoSitio(getTsiId(), getTsiNombre(), getTsiDescripcion(), getTsiEstado());
				Mensaje.crearMensajeINFO("Actualizado - Tipo Sitio Modificado");
			} else {
				manager.insertarTipoSitio(getTsiNombre(), getTsiDescripcion());
				Mensaje.crearMensajeINFO("Registrado - Tipo Sitio Creado");
			}
			r = "tsitio?faces-redirect=true";
			this.cleanDatos();
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
		return r;
	}

	/**
	 * Metodo de seteo de datos
	 */
	public void cleanDatos() {
		setTsiId(0);
		setTsiNombre("");
		setTsiDescripcion("");
		setTsiEstado("");
		setEdicion(false);
	}

	/**
	 * Metodo para cargar un tipo sitio para su edicion
	 * 
	 * @param tsitio
	 * @return
	 */
	public String cargarTiposSitio(GenTipoSitio tsitio) {
		try {
			cargarEstados();
			setTsiNombre(tsitio.getTsiNombre());
			setTsiDescripcion(tsitio.getTsiDescripcion());
			setTsiEstado(tsitio.getTsiEstado());
			setEdicion(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ntsitio?faces-redirect=true";
	}

	/**
	 * Metodo para setear datos y regresar la vista
	 * 
	 * @return
	 */
	public String cancelar() {
		this.cleanDatos();
		return "tsitio?faces-redirect=true";
	}

	/**
	 * Lista de Estado
	 */
	public void cargarEstados() {
		getL_estados().clear();
		List<GenCatalogoItem> completo = manager.AllofItems("cat_estados");
		for (GenCatalogoItem i : completo) {
			getL_estados().add(
					new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}
}
