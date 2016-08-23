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

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenComunidade;
import city.model.dao.entidades.GenManzana;
import city.model.dao.entidades.GenManzanaDetalle;
import city.model.dao.entidades.GenManzanaPosicione;
import city.model.dao.entidades.GenZona;
import city.model.dao.entidades.GenZonasComunidade;
import city.model.generic.Mensaje;
import city.model.manager.ManagerTerritorio;

/**
 * @author jestevez
 *
 */
@SessionScoped
@ManagedBean
public class ManzanaBean {
//
//	@EJB
//	private ManagerTerritorio manager;
//
//	// Atributos de la clase comunidad
//	@NotEmpty(message = "ID no debe estar vacío.")
//	@NotBlank(message = "ID no debe ser solo espacios blancos.")
//	private String manId;
//	@NotEmpty(message = "NOMBRE no debe estar vacío.")
//	@NotBlank(message = "NOMBRE no debe ser solo espacios blancos.")
//	private String manNombre;
//	@NotEmpty(message = "ESTADO no debe estar vacío.")
//	@NotBlank(message = "ESTADO no debe ser solo espacios blancos.")
//	private String manEstado;
//	@NotEmpty(message = "BARRIO no debe estar vacío.")
//	@NotBlank(message = "BARRIO no debe ser solo espacios blancos.")
//	private String barrio;
//
//	// manejo de listas
//	List<GenManzana> l_manzanas;
//	List<GenManzanaDetalle> l_manzanasd;
//	List<GenManzanaPosicione> l_manzanasp;
//	List<SelectItem> l_estados;
//	List<SelectItem> l_barrios;
//
//	// valor de edición e inserción
//	private boolean edicion;
//
//	public ManzanaBean() {
//	}
//
//	@PostConstruct
//	public void init() {
//		edicion = false;
//		l_manzanas = new ArrayList<GenManzana>();
//		l_manzanasd = new ArrayList<GenManzanaDetalle>();
//		l_manzanasp = new ArrayList<GenManzanaPosicione>();
//		l_estados = new ArrayList<SelectItem>();
//		l_barrios = new ArrayList<SelectItem>();
//		cargarComunidad();
//		cargarEstados();
//
//	}
//
//	/**
//	 * @return the barrio
//	 */
//	public String getBarrio() {
//		return barrio;
//	}
//
//	/**
//	 * @param barrio
//	 *            the barrio to set
//	 */
//	public void setBarrio(String barrio) {
//		this.barrio = barrio;
//	}
//
//	/**
//	 * @return the edicion
//	 */
//	public boolean isEdicion() {
//		return edicion;
//	}
//
//	/**
//	 * @param edicion
//	 *            the edicion to set
//	 */
//	public void setEdicion(boolean edicion) {
//		this.edicion = edicion;
//	}
//
//	/**
//	 * @return the manId
//	 */
//	public String getManId() {
//		return manId;
//	}
//
//	/**
//	 * @param manId
//	 *            the manId to set
//	 */
//	public void setManId(String manId) {
//		this.manId = manId;
//	}
//
//	/**
//	 * @return the manNombre
//	 */
//	public String getManNombre() {
//		return manNombre;
//	}
//
//	/**
//	 * @param manNombre
//	 *            the manNombre to set
//	 */
//	public void setManNombre(String manNombre) {
//		this.manNombre = manNombre;
//	}
//
//	/**
//	 * @return the manEstado
//	 */
//	public String getManEstado() {
//		return manEstado;
//	}
//
//	/**
//	 * @param manEstado
//	 *            the manEstado to set
//	 */
//	public void setManEstado(String manEstado) {
//		this.manEstado = manEstado;
//	}
//
//	/**
//	 * @return the l_manzanas
//	 */
//	public List<GenManzana> getL_manzanas() {
//		return l_manzanas;
//	}
//
//	/**
//	 * @param l_manzanas
//	 *            the l_manzanas to set
//	 */
//	public void setL_manzanas(List<GenManzana> l_manzanas) {
//		this.l_manzanas = l_manzanas;
//	}
//
//	/**
//	 * @return the l_manzanasd
//	 */
//	public List<GenManzanaDetalle> getL_manzanasd() {
//		return l_manzanasd;
//	}
//
//	/**
//	 * @param l_manzanasd
//	 *            the l_manzanasd to set
//	 */
//	public void setL_manzanasd(List<GenManzanaDetalle> l_manzanasd) {
//		this.l_manzanasd = l_manzanasd;
//	}
//
//	/**
//	 * @return the l_manzanasp
//	 */
//	public List<GenManzanaPosicione> getL_manzanasp() {
//		return l_manzanasp;
//	}
//
//	/**
//	 * @param l_manzanasp
//	 *            the l_manzanasp to set
//	 */
//	public void setL_manzanasp(List<GenManzanaPosicione> l_manzanasp) {
//		this.l_manzanasp = l_manzanasp;
//	}
//
//	/**
//	 * @return the l_barrios
//	 */
//	public List<SelectItem> getL_barrios() {
//		return l_barrios;
//	}
//
//	/**
//	 * @param l_barrios
//	 *            the l_barrios to set
//	 */
//	public void setL_barrios(List<SelectItem> l_barrios) {
//		this.l_barrios = l_barrios;
//	}
//
//	/**
//	 * @return the l_estados
//	 */
//	public List<SelectItem> getL_estados() {
//		return l_estados;
//	}
//
//	/**
//	 * @param l_estados
//	 *            the l_estados to set
//	 */
//	public void setL_estados(List<SelectItem> l_estados) {
//		this.l_estados = l_estados;
//	}
//
//	/**
//	 * Redirección a la página de creación de personas
//	 * 
//	 * @return vista
//	 */
//	public String nuevaComunidad() {
//		edicion = false;
//		cargarBarrios();
//		return "nmanzana?faces-redirect=true";
//	}
//
//	/**
//	 * Permite la creación o modificación de una manzana
//	 * 
//	 * @return
//	 */
//	public String crearManzana() {
//		String r = "";
//		try {
//			if (edicion) {
//				GenManzana m = new GenManzana();
//				m.setManId(getManId());
//				m.setManEstado(getManEstado());
//				m.setManNombre(getManNombre());
//				m.setGenBarrio(manager.findBarrioById(getBarrio()));
//				manager.modicarManzana(m);
//				Mensaje.crearMensajeINFO("Actualizado - Manzana Modificada");
//			} else {
//				GenManzana m = new GenManzana();
//				m.setManId(getManId());
//				m.setManEstado("A");
//				m.setManNombre(getManNombre());
//				m.setGenBarrio(manager.findBarrioById(getBarrio()));
//				manager.insertarManzana(m);
//				Mensaje.crearMensajeINFO("Registrado - Manzana Creada");
//				this.guardarZonas();
//			}
//			r = "manzana?faces-redirect=true";
//			this.cleanDatos();
//			this.cargarComunidad();
//		} catch (Exception e) {
//			Mensaje.crearMensajeERROR(e.getMessage());
//		}
//		return r;
//	}
//
//	/**
//	 * Método de seteo de datos
//	 */
//	public void cleanDatos() {
//		setManId("");
//		setManEstado("A");
//		setManNombre("");
//		setBarrio("");
//		setEdicion(false);
//	}
//
//	/**
//	 * Método para cargar una Manzana para su edición
//	 * 
//	 * @param persona
//	 * @return
//	 */
//	public String cargarManzana(GenManzana man) {
//		try {
//			cargarBarrios();
//			cargarEstados();
//			setManId(man.getManId());
//			setManEstado(man.getManEstado());
//			setBarrio(man.getGenBarrio().getBarId());
//			setManNombre(man.getManNombre());
//			setEdicion(true);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "nmanzana?faces-redirect=true";
//	}
//
//	/**
//	 * Método para setear datos y regresar la vista
//	 * 
//	 * @return
//	 */
//	public String cancelar() {
//		getL_manzanas().clear();
//		getL_manzanasd().clear();
//		getL_manzanasp().clear();
//		this.cleanDatos();
//		this.cargarComunidad();
//		return "manzana?faces-redirect=true";
//	}
//
////	/**
////	 * Lista de Comunidades
////	 */
////	public void cargarComunidad() {
////		try {
////			getL_comunidades().clear();
////			getL_comunidades().addAll(manager.findAllcomunidades());
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	}
////
////	/**
////	 * Lista de Estado
////	 */
////	public void cargarEstados() {
////		getL_estados().clear();
////		for (GenCatalogoItemsDet i : manager.AllofItems("cat_estados")) {
////			getL_estados().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
////		}
////	}
////
////	/**
////	 * Método para cargar zonas activas
////	 */
////	public void cargarZonas() {
////		getL_zonas_s().clear();
////		try {
////			for (GenZona i : manager.findAllZonasA()) {
////				getL_zonas_s().add(new SelectItem(i.getZonNombre(), i.getZonNombre()));
////			}
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	}
////
////	/**
////	 * Método cambio de boolean
////	 * 
////	 * @param b
////	 * @return
////	 */
////	public String cambio(Boolean b) {
////		String a = "";
////		if (b == true) {
////			a = "SI";
////		} else {
////			a = "NO";
////		}
////		return a;
////	}
////
////	/**
////	 * Método para guardar las zonas
////	 */
////	public void guardarZonas() {
////		for (String s : l_zonas) {
////			try {
////				manager.insertarZonaCom(getComId(), s);
////			} catch (Exception e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////
////		}
////	}
////
////	/**
////	 * Método para indicar las zonas de una comunidad
////	 * 
////	 * @param id
////	 * @return
////	 */
////	public List<String> mostrarZonas(String id) {
////		List<String> s = new ArrayList<String>();
////		List<GenZonasComunidade> lz = manager.zonacXComunidad(id);
////		if (lz != null) {
////			for (GenZonasComunidade zc : lz) {
////				s.add(zc.getGenZona().getZonNombre());
////			}
////		}
////		return s;
////	}
////
////	/**
////	 * Método para obtener las zonas por comunidad
////	 * 
////	 * @param id
////	 */
////	public void ZonasXComunidad(String id) {
////		getL_zonas().clear();
////		List<GenZonasComunidade> lz = manager.zonacXComunidad(id);
////		if (lz != null) {
////			for (GenZonasComunidade zc : lz) {
////				getL_zonas().add(zc.getGenZona().getZonNombre());
////			}
////		}
////	}
////
////	/**
////	 * Método para eliminar zonas
////	 * 
////	 * @param id_com
////	 */
////	public void eliminarZonaCom(String id_com) {
////		List<GenZonasComunidade> lz = manager.zonacXComunidad(id_com);
////		if (lz != null) {
////			for (GenZonasComunidade zc : lz) {
////				manager.eliminarZonaCom(zc.getId());
////			}
////		}
////	}
}
