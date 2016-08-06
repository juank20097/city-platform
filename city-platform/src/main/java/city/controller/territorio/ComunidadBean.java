package city.controller.territorio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.persistence.Column;
import javax.persistence.Id;

import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenComunidade;
import city.model.dao.entidades.GenSectore;
import city.model.generic.Mensaje;
import city.model.manager.ManagerComunidades;
import city.model.manager.ManagerSitios;

/**
 * @author jestevez
 *
 */
@SessionScoped
@ManagedBean
public class ComunidadBean {

	// Atributos de la Clase
	@EJB
	private ManagerComunidades manager;

	// Atriutos de la clase comunidad
	private String comId;
	private String comEstado;
	private BigDecimal comHectareas;
	private String comLinkMapa;
	private String comLinkPdf;
	private BigDecimal comMetrosCuadrados;
	private String comNombre;
	private Boolean comUbicacion;

	// manejo de vistas
	List<GenComunidade> l_comunidades;
	List<SelectItem> l_estados;

	// valor de edición e inserción
	private boolean edicion;

	public ComunidadBean() {
	}

	@PostConstruct
	public void init() {
		edicion = false;
		l_comunidades = new ArrayList<GenComunidade>();
		l_estados = new ArrayList<SelectItem>();
		cargarComunidad();
		cargarEstados();
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
	 * @return the comId
	 */
	public String getComId() {
		return comId;
	}

	/**
	 * @param comId
	 *            the comId to set
	 */
	public void setComId(String comId) {
		this.comId = comId;
	}

	/**
	 * @return the comEstado
	 */
	public String getComEstado() {
		return comEstado;
	}

	/**
	 * @param comEstado
	 *            the comEstado to set
	 */
	public void setComEstado(String comEstado) {
		this.comEstado = comEstado;
	}

	/**
	 * @return the comHectareas
	 */
	public BigDecimal getComHectareas() {
		return comHectareas;
	}

	/**
	 * @param comHectareas
	 *            the comHectareas to set
	 */
	public void setComHectareas(BigDecimal comHectareas) {
		this.comHectareas = comHectareas;
	}

	/**
	 * @return the comLinkMapa
	 */
	public String getComLinkMapa() {
		return comLinkMapa;
	}

	/**
	 * @param comLinkMapa
	 *            the comLinkMapa to set
	 */
	public void setComLinkMapa(String comLinkMapa) {
		this.comLinkMapa = comLinkMapa;
	}

	/**
	 * @return the comLinkPdf
	 */
	public String getComLinkPdf() {
		return comLinkPdf;
	}

	/**
	 * @param comLinkPdf
	 *            the comLinkPdf to set
	 */
	public void setComLinkPdf(String comLinkPdf) {
		this.comLinkPdf = comLinkPdf;
	}

	/**
	 * @return the comMetrosCuadrados
	 */
	public BigDecimal getComMetrosCuadrados() {
		return comMetrosCuadrados;
	}

	/**
	 * @param comMetrosCuadrados
	 *            the comMetrosCuadrados to set
	 */
	public void setComMetrosCuadrados(BigDecimal comMetrosCuadrados) {
		this.comMetrosCuadrados = comMetrosCuadrados;
	}

	/**
	 * @return the comNombre
	 */
	public String getComNombre() {
		return comNombre;
	}

	/**
	 * @param comNombre
	 *            the comNombre to set
	 */
	public void setComNombre(String comNombre) {
		this.comNombre = comNombre;
	}

	/**
	 * @return the comUbicacion
	 */
	public Boolean getComUbicacion() {
		return comUbicacion;
	}

	/**
	 * @param comUbicacion
	 *            the comUbicacion to set
	 */
	public void setComUbicacion(Boolean comUbicacion) {
		this.comUbicacion = comUbicacion;
	}

	/**
	 * @return the l_comunidades
	 */
	public List<GenComunidade> getL_comunidades() {
		return l_comunidades;
	}

	/**
	 * @param l_comunidades
	 *            the l_comunidades to set
	 */
	public void setL_comunidades(List<GenComunidade> l_comunidades) {
		this.l_comunidades = l_comunidades;
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
	 * Redirección a la página de creación de personas
	 * 
	 * @return vista
	 */
	public String nuevaComunidad() {
		edicion = false;
		return "ncomunidad?faces-redirect=true";
	}

	/**
	 * Permite la creacion o modificacion de un sector
	 * 
	 * @return
	 */
	public String crearComunidad() {
		String r = "";
		try {
			if (edicion) {
				manager.editarComunidad(getComId(), getComNombre(),
						getComHectareas(), getComMetrosCuadrados(),
						getComUbicacion(), getComEstado(), getComLinkMapa(),
						getComLinkPdf());
				Mensaje.crearMensajeINFO("Actualizado - Comunidad Modificado");
			} else {
				manager.insertarComunidad(getComId(), getComNombre(),
						getComHectareas(), getComMetrosCuadrados(),
						getComUbicacion(), getComLinkMapa(), getComLinkPdf());
				Mensaje.crearMensajeINFO("Registrado - Sector Creado");
			}
			r = "comunidad?faces-redirect=true";
			this.cleanDatos();
			this.cargarComunidad();
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
		return r;
	}

	/**
	 * Metodo de seteo de datos
	 */
	public void cleanDatos() {
		setComId("");
		setComEstado("");
		setComHectareas(null);
		setComLinkMapa("");
		setComLinkPdf("");
		setComMetrosCuadrados(null);
		setComNombre("");
		setComUbicacion(false);
		setEdicion(false);
	}

	/**
	 * Metodo para cargar un Sector para su edicion
	 * 
	 * @param persona
	 * @return
	 */
	public String cargarComunidad(GenComunidade com) {
		try {
			cargarEstados();
			setComId(com.getComId());
			setComEstado(com.getComEstado());
			setComHectareas(com.getComHectareas());
			setComMetrosCuadrados(com.getComMetrosCuadrados());
			setComLinkMapa(com.getComLinkMapa());
			setComLinkPdf(com.getComLinkPdf());
			setComNombre(com.getComNombre());
			setComUbicacion(com.getComUbicacion());
			setEdicion(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ncomunidad?faces-redirect=true";
	}

	/**
	 * Metodo para setear datos y regresar la vista
	 * 
	 * @return
	 */
	public String cancelar() {
		this.cleanDatos();
		this.cargarComunidad();
		return "comunidad?faces-redirect=true";
	}

	/**
	 * Lista de Sectores
	 */
	public void cargarComunidad() {
		try {
			getL_comunidades().clear();
			getL_comunidades().addAll(manager.findAllcomunidades());
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
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_estados");
		for (GenCatalogoItemsDet i : completo) {
			getL_estados().add(
					new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}
}
