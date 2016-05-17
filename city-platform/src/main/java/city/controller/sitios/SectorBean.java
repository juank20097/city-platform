package city.controller.sitios;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;


import city.model.dao.entidades.GenCatalogoItem;
import city.model.dao.entidades.GenSectore;
import city.model.generic.Mensaje;
import city.model.manager.ManagerSitios;

/**
 * @author jestevez
 *
 */
@SessionScoped
@ManagedBean
public class SectorBean {

	// Atributos de la Clase
	@EJB
	private ManagerSitios manager;

	// Atriutos de la clase sector
	private Integer secId;
	private String secDescripcion;
	private String secDireccionReferencial;
	private String secEstado;
	private String secNombre;

	// manejo de vistas
	List<GenSectore> l_sector;
	List<SelectItem> l_estados;

	// valor de edición e inserción
	private boolean edicion;

	public SectorBean() {
	}

	@PostConstruct
	public void init() {
		edicion = false;
		l_sector = new ArrayList<GenSectore>();
		l_estados = new ArrayList<SelectItem>();
		cargarSectores();
		cargarEstados();
	}
	/**
	 * @return the secId
	 */
	public Integer getSecId() {
		return secId;
	}

	/**
	 * @param secId the secId to set
	 */
	public void setSecId(Integer secId) {
		this.secId = secId;
	}

	/**
	 * @return the secDescripcion
	 */
	public String getSecDescripcion() {
		return secDescripcion;
	}

	/**
	 * @param secDescripcion the secDescripcion to set
	 */
	public void setSecDescripcion(String secDescripcion) {
		this.secDescripcion = secDescripcion;
	}

	/**
	 * @return the secDireccionReferencial
	 */
	public String getSecDireccionReferencial() {
		return secDireccionReferencial;
	}

	/**
	 * @param secDireccionReferencial the secDireccionReferencial to set
	 */
	public void setSecDireccionReferencial(String secDireccionReferencial) {
		this.secDireccionReferencial = secDireccionReferencial;
	}

	/**
	 * @return the secEstado
	 */
	public String getSecEstado() {
		return secEstado;
	}

	/**
	 * @param secEstado the secEstado to set
	 */
	public void setSecEstado(String secEstado) {
		this.secEstado = secEstado;
	}

	/**
	 * @return the secNombre
	 */
	public String getSecNombre() {
		return secNombre;
	}

	/**
	 * @param secNombre the secNombre to set
	 */
	public void setSecNombre(String secNombre) {
		this.secNombre = secNombre;
	}

	/**
	 * @return the l_sector
	 */
	public List<GenSectore> getL_sector() {
		return l_sector;
	}

	/**
	 * @param l_sector the l_sector to set
	 */
	public void setL_sector(List<GenSectore> l_sector) {
		this.l_sector = l_sector;
	}

	/**
	 * @return the l_estados
	 */
	public List<SelectItem> getL_estados() {
		return l_estados;
	}

	/**
	 * @param l_estados the l_estados to set
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
	 * @param edicion the edicion to set
	 */
	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	/**
	 * Redirecciona a la pagina de creacion de personas
	 * 
	 * @return vista
	 */
	public String nuevoSector() {
		edicion = false;
		return "nsector?faces-redirect=true";
	}

	/**
	 * Permite la creacion o modificacion de un sector
	 * 
	 * @return
	 */
	public String crearSector() {
		String r = "";
		try {
				if (edicion) {
					manager.editarSector(getSecId(),getSecNombre(),getSecDireccionReferencial(),getSecDescripcion(), getSecEstado());
					Mensaje.crearMensajeINFO("Actualizado - Sector Modificado");
				} else {
					manager.insertarSector(getSecNombre(), getSecDireccionReferencial(), getSecDescripcion());
					Mensaje.crearMensajeINFO("Registrado - Sector Creado");
				}
				r = "sector?faces-redirect=true";
				this.cleanDatos();
				this.cargarSectores();
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
		return r;
	}
	
	/**
	 * Metodo de seteo de datos
	 */
	public void cleanDatos() {
		setSecId(0);
		setSecNombre("");
		setSecDireccionReferencial("");
		setSecDescripcion("");
		setSecEstado("");
		setEdicion(false);
	}

	/**
	 * Metodo para cargar un Sector para su edicion
	 * 
	 * @param persona
	 * @return
	 */
	public String cargarSector(GenSectore sector) {
		try {
			cargarEstados();
			setSecId(sector.getSecId());
			setSecNombre(sector.getSecNombre());
			setSecDireccionReferencial(sector.getSecDireccionReferencial());
			setSecDescripcion(sector.getSecDescripcion());
			setSecEstado(sector.getSecEstado());
			setEdicion(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "nsector?faces-redirect=true";
	}

	/**
	 * Metodo para setear datos y regresar la vista
	 * 
	 * @return
	 */
	public String cancelar() {
		this.cleanDatos();
		this.cargarSectores();
		return "sector?faces-redirect=true";
	}
	
	/**
	 * Lista de Sectores
	 */
	public void cargarSectores() {
		try {
			getL_sector().clear();
			getL_sector().addAll(manager.findAllsectores());
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
		List<GenCatalogoItem> completo = manager.AllofItems("cat_estados");
		for (GenCatalogoItem i : completo) {
			getL_estados().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}
}
