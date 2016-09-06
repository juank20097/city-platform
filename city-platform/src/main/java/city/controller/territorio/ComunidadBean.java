package city.controller.territorio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenComunidade;
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
public class ComunidadBean {

	@EJB
	private ManagerTerritorio manager;
	@Inject
	private SesionBean session;

	// Atributos de la clase comunidad
	@NotEmpty(message="ID no debe estar vacío.")
	@NotBlank(message="ID no debe ser solo espacios blancos.")
	private String comId;
	@NotEmpty(message="ESTADO no debe estar vacío.")
	@NotBlank(message="ESTADO no debe ser solo espacios blancos.")
	private String comEstado;
	private BigDecimal comHectareas;
	@NotEmpty(message="LINK MAPA no debe estar vacío.")
	@NotBlank(message="LINK MAPA no debe ser solo espacios blancos.")
	private String comLinkMapa;
	@NotEmpty(message="LINK PDF no debe estar vacío.")
	@NotBlank(message="LINK PDF no debe ser solo espacios blancos.")
	private String comLinkPdf;
	private BigDecimal comMetrosCuadrados;
	@NotEmpty(message="NOMBRE no debe estar vacío.")
	@NotBlank(message="NOMBRE no debe ser solo espacios blancos.")
	private String comNombre;
	private Boolean comUbicacion;

	// manejo de vistas
	List<GenComunidade> l_comunidades;
	List<SelectItem> l_estados;
	List<SelectItem> l_zonas_s;
	List<String> l_zonas;
	
	// valor de edición e inserción
	private boolean edicion;

	public ComunidadBean() {
	}

	@PostConstruct
	public void init() {
		session.validarSesion();
		edicion = false;
		l_zonas = new ArrayList<String>();
		l_comunidades = new ArrayList<GenComunidade>();
		l_estados = new ArrayList<SelectItem>();
		l_zonas_s = new ArrayList<SelectItem>();
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
	 * @return the l_zonas_s
	 */
	public List<SelectItem> getL_zonas_s() {
		return l_zonas_s;
	}

	/**
	 * @param l_zonas_s the l_zonas_s to set
	 */
	public void setL_zonas_s(List<SelectItem> l_zonas_s) {
		this.l_zonas_s = l_zonas_s;
	}

	/**
	 * @return the l_zonas
	 */
	public List<String> getL_zonas() {
		return l_zonas;
	}

	/**
	 * @param l_zonas the l_zonas to set
	 */
	public void setL_zonas(List<String> l_zonas) {
		this.l_zonas = l_zonas;
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
		cargarZonas();
		return "ncomunidad?faces-redirect=true";
	}

	/**
	 * Permite la creación o modificación de un sector
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
				this.eliminarZonaCom(getComId());
				this.guardarZonas();
				Mensaje.crearMensajeINFO("Actualizado - Comunidad Modificado");
			} else {
				manager.insertarComunidad(getComId(), getComNombre(),
						getComHectareas(), getComMetrosCuadrados(),
						getComUbicacion(), getComLinkMapa(), getComLinkPdf());
				Mensaje.crearMensajeINFO("Registrado - Comunidad Creada");
				this.guardarZonas();
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
	 * Método de seteo de datos
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
	 * Método para cargar una Comunidad para su edición
	 * 
	 * @param persona
	 * @return
	 */
	public String cargarComunidad(GenComunidade com) {
		try {
			cargarZonas();
			cargarEstados();
			setComId(com.getComId());
			ZonasXComunidad(getComId());
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
	 * Método para setear datos y regresar la vista
	 * 
	 * @return
	 */
	public String cancelar() {
		getL_zonas().clear();
		getL_zonas_s().clear();
		this.cleanDatos();
		this.cargarComunidad();
		return "comunidad?faces-redirect=true";
	}

	/**
	 * Lista de Comunidades
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
		for (GenCatalogoItemsDet i : manager.AllofItems("cat_estados")) {
			getL_estados().add(
					new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}
	
	/**
	 * Método para cargar zonas activas
	 */
	public void cargarZonas() {
		getL_zonas_s().clear();
		try {
			for (GenZona i : manager.findAllZonasA()) {
				getL_zonas_s().add(
						new SelectItem(i.getZonNombre(), i.getZonNombre()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Método cambio de boolean
	 * 
	 * @param b
	 * @return
	 */
	public String cambio(Boolean b){
		String a = "";
		if (b==true){
			a="SI";
		}else{
			a="NO";
		}
		return a;
	}
	
	/**
	 * Método para guardar las zonas
	 */
	public void guardarZonas(){
		for (String s : l_zonas) {
			try {
				manager.insertarZonaCom(getComId(), s);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Método para indicar las zonas de una comunidad
	 * 
	 * @param id
	 * @return
	 */
	public List<String> mostrarZonas(String id){
		List<String> s = new ArrayList<String>(); 
		List<GenZonasComunidade> lz=manager.zonacXComunidad(id);
		if (lz!=null){
		for (GenZonasComunidade zc : lz) {
			s.add(zc.getGenZona().getZonNombre());
		}
		}
		return s;
	}
	
	/**
	 * Método para obtener las zonas por comunidad
	 * 
	 * @param id
	 */
	public void ZonasXComunidad(String id){
		getL_zonas().clear();
		List<GenZonasComunidade> lz=manager.zonacXComunidad(id);
		if (lz!=null){
		for (GenZonasComunidade zc : lz) {
			getL_zonas().add(zc.getGenZona().getZonNombre());
		}
		}
	}
	
	/**
	 * Método para eliminar zonas
	 * 
	 * @param id_com
	 */
	public void eliminarZonaCom(String id_com){
		List<GenZonasComunidade> lz=manager.zonacXComunidad(id_com);
		if (lz!=null){
		for (GenZonasComunidade zc : lz) {
			manager.eliminarZonaCom(zc.getId());
		}
		}
	}
}
