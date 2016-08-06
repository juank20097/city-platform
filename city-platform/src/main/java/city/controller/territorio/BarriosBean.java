package city.controller.territorio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import city.model.dao.entidades.GenBarrio;
import city.model.dao.entidades.GenDistrito;
import city.model.generic.Mensaje;
import city.model.manager.ManagerBarrio;
import city.model.manager.ManagerDistrito;

@SessionScoped
@ManagedBean

public class BarriosBean implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 2042974807682029933L;

	private static String ID_ACTIVO = "A"; 
	private static String ID_INACTIVO = "I";
	private static String SELECT_DISTRITO = "0";
	
	@EJB
	private ManagerBarrio mngBarrio;
	
	@EJB
	private ManagerDistrito mngDistrito;
		
	@NotEmpty(message="ID no debe estar vacío.")
	@NotBlank(message="ID no debe ser solo espacios blancos.")
	private String id; 
	
	@NotEmpty(message="DESCRIPCIÓN no debe estar vacío.")
	@NotBlank(message="DESCRIPCIÓN no debe ser solo espacios blancos.")
	private String descripcion;
	
	private String estado;
	
	private BigDecimal hectareas;
	
	@NotEmpty(message="MAPA LINK no debe estar vacío.")
	@NotBlank(message="MAPA LINK no debe ser solo espacios blancos.")
	@URL(message="MAPA LINK no es una url válida.")
	private String linkMapa;
	
	@NotEmpty(message="PDF LINK no debe estar vacío.")
	@NotBlank(message="PDF LINK no debe ser solo espacios blancos.")
	@URL(message="PDF LINK no es una url válida.")
	private String linkPdf;
	
	private BigDecimal metrosCuadrados;
	
	@NotEmpty(message="NOMBRE no debe estar vacío.")
	@NotBlank(message="NOMBRE no debe ser solo espacios blancos.")
	private String nombre;
	
	private List<GenBarrio> lstBarrios;
	private boolean edicion;
	private List<SelectItem> slctEstados;
	private String distritoId;
	private List<SelectItem> slctDistritos;
	
	@PostConstruct
	public void init(){
		estado = ID_ACTIVO;
		lstBarrios = new ArrayList<GenBarrio>();
		hectareas = new BigDecimal(0);
		distritoId = SELECT_DISTRITO;
		slctDistritos = new ArrayList<SelectItem>();
		slctEstados = new ArrayList<SelectItem>();
		cargarBarrios();
		cargarDistritos();
		cargarEstados();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getHectareas() {
		return hectareas;
	}

	public void setHectareas(BigDecimal hectareas) {
		this.hectareas = hectareas;
	}

	public String getLinkMapa() {
		return linkMapa;
	}

	public void setLinkMapa(String linkMapa) {
		this.linkMapa = linkMapa;
	}

	public String getLinkPdf() {
		return linkPdf;
	}

	public void setLinkPdf(String linkPdf) {
		this.linkPdf = linkPdf;
	}

	public BigDecimal getMetrosCuadrados() {
		return metrosCuadrados;
	}

	public void setMetrosCuadrados(BigDecimal metrosCuadrados) {
		this.metrosCuadrados = metrosCuadrados;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<GenBarrio> getLstBarrios() {
		return lstBarrios;
	}

	public void setLstBarrios(List<GenBarrio> lstBarrios) {
		this.lstBarrios = lstBarrios;
	}

	public boolean isEdicion() {
		return edicion;
	}

	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	public List<SelectItem> getSlctEstados() {
		return slctEstados;
	}

	public void setSlctEstados(List<SelectItem> slctEstados) {
		this.slctEstados = slctEstados;
	}

	public String getDistritoId() {
		return distritoId;
	}

	public void setDistritoId(String distritoId) {
		this.distritoId = distritoId;
	}

	public List<SelectItem> getSlctDistritos() {
		return slctDistritos;
	}

	public void setSlctDistritos(List<SelectItem> slctDistritos) {
		this.slctDistritos = slctDistritos;
	}
	
	// Métodos
	
	private void cargarEstados(){
		getSlctEstados().add(new SelectItem(ID_ACTIVO,"Activo"));
		getSlctEstados().add(new SelectItem(ID_INACTIVO,"Inactivo"));
	}
	
	private void cargarDistritos (){
		getSlctDistritos().add(new SelectItem(SELECT_DISTRITO,"Seleccionar"));
		for (GenDistrito distrito : mngBarrio.findAllDistritosAc()) {
			getSlctDistritos().add(new SelectItem(distrito.getDisId(), distrito.getDisNombre()));
		}
	}
	
	public String nuevoBarrio(){
		limpiarDatos();
		return "nBarrio?faces-redirect=true";
	}
	
	public String cargarBarrio(GenBarrio barrio){
		setEdicion(true);
		setId(barrio.getBarId());
		setDescripcion(barrio.getBarDescripcion());
		setEstado(barrio.getBarEstado());
		setHectareas(barrio.getBarHectareas());
		setMetrosCuadrados(barrio.getBarMetrosCuadrados());
		setLinkMapa(barrio.getBarLinkMapa());
		setLinkPdf(barrio.getBarLinkPdf());
		setNombre(barrio.getBarNombre());
		setDistritoId(barrio.getGenDistrito().getDisId());
		
		return "nBarrio?faces-redirect=true";
	}
	
	public String guardarEditarBarrio(){
		try {
			if(getDistritoId().equals(SELECT_DISTRITO)){
				Mensaje.crearMensajeWARN("Seleccione un distrito");
				return "";
			}else if(!isEdicion() && mngBarrio.findBarrioById(getId())!= null){
				Mensaje.crearMensajeWARN("Ya existe un barrio con el mimo id, favor cámbielo.");
				return "";
			}else {
				GenBarrio b= new GenBarrio();
				b.setBarId(getId());
				b.setBarDescripcion(getDescripcion());
				b.setBarEstado(getEstado());
				b.setBarHectareas(getHectareas());
				b.setBarMetrosCuadrados(getMetrosCuadrados());
				b.setBarLinkMapa(getLinkMapa());
				b.setBarLinkPdf(getLinkPdf());
				b.setBarNombre(getNombre());
				b.setGenDistrito(mngDistrito.findDistritoById(getDistritoId()));
				if(isEdicion()){
					mngBarrio.modicarBarrio(b);
				}
				else{
					mngBarrio.insertarBarrio(b);
				}
				cargarBarrios();
				limpiarDatos();
				return "barrios?faces-redirect=true"; 
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error: "+e.getMessage());
			return "";
		}
	}
	
	public String cancelar(){
		limpiarDatos();
		cargarBarrios();
		return "barrios?faces-redirect=true"; 
	}
	private void cargarBarrios(){
		getLstBarrios().clear();
		getLstBarrios().addAll(mngBarrio.findAllBarrios());
	}
	
	private void limpiarDatos(){
		setId(null);
		setDescripcion(null);
		setEstado(ID_ACTIVO);
		setHectareas(new BigDecimal(0));
		setMetrosCuadrados(new BigDecimal(0));
		setLinkMapa(null);
		setLinkPdf(null);
		setNombre(null);
		setEdicion(false);
		setDistritoId(SELECT_DISTRITO);
	}
	
}
