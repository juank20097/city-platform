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

import city.model.dao.entidades.GenDistrito;
import city.model.dao.entidades.GenZona;
import city.model.generic.Mensaje;
import city.model.manager.ManagerTerritorio;



@SessionScoped
@ManagedBean
public class DistritoBean implements Serializable{


	private static final long serialVersionUID = -3198725647396964268L;
	private static String ID_ACTIVO = "A"; 
	private static String ID_INACTIVO = "I";
	private static String SELECT_ZONA = "0";

	@EJB
	private ManagerTerritorio manager;

	
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
	private List<GenDistrito> listDistritos;
	private boolean edicion;
	private List<SelectItem> slctEstados;
	private String zonaId;
	private List<SelectItem> slctZonas;
	
	@PostConstruct
	public void init(){
		estado = ID_ACTIVO;
		listDistritos = new ArrayList<GenDistrito>();
		hectareas = new BigDecimal(0);
		metrosCuadrados = new BigDecimal(0);
		zonaId = SELECT_ZONA;
		slctZonas = new ArrayList<SelectItem>();
		slctEstados = new ArrayList<SelectItem>();
		cargarDistritos();
		cargarZonas();
		cargarEstados();
	}
	
	public String getZonaId() {
		return zonaId;
	}
	
	public void setZonaId(String zonaId) {
		this.zonaId = zonaId;
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
	
	public List<SelectItem> getSlctZonas() {
		return slctZonas;
	}
	
	public void setSlctZonas(List<SelectItem> slctZonas) {
		this.slctZonas = slctZonas;
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

	public List<GenDistrito> getListDistritos() {
		return listDistritos;
	}
	
	public void setListDistritos(List<GenDistrito> listDistritos) {
		this.listDistritos = listDistritos;
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

	private void cargarEstados() {
		getSlctEstados().add(new SelectItem(ID_ACTIVO,"Activo"));
		getSlctEstados().add(new SelectItem(ID_INACTIVO,"Inactivo"));
	}
	
	private void cargarZonas() {
		getSlctZonas().add(new SelectItem(SELECT_ZONA,"Seleccionar"));
		for (GenZona z : manager.findAllZonasA()) {
			getSlctZonas().add(new SelectItem(z.getZonId(),z.getZonNombre()));
		}
		
	}
	
	public String nuevaDistrito(){
		limpiarDatos();
		return "neDistrito?faces-redirect=true";
	}
	
	public String cargarEditarDistrito(GenDistrito distrito){
		setId(distrito.getDisId());setDescripcion(distrito.getDisDescripcion());setEstado(distrito.getDisEstado());
		setHectareas(distrito.getDisHectareas());setMetrosCuadrados(distrito.getDisMetrosCuadrados());
		setLinkMapa(distrito.getDisLinkMapa());setLinkPdf(distrito.getDisLinkPdf());setNombre(distrito.getDisNombre());
		setEdicion(true);
		setZonaId(distrito.getGenZona().getZonId());
		return "neDistrito?faces-redirect=true";
	}
	
	public String guardarEditarDistrito(){
		try {
			if(getZonaId().equals(SELECT_ZONA)){
				Mensaje.crearMensajeWARN("Seleccione una zona");
				return "";
			}else if(!isEdicion() && manager.findDistritoById(getId())!=null){
				Mensaje.crearMensajeWARN("Ya existe un distrito con el mismo id, favor cámbielo.");
				return "";
			}else{
				GenDistrito d = new GenDistrito();
				d.setDisId(getId());d.setDisDescripcion(getDescripcion());d.setDisEstado(getEstado());
				d.setDisLinkMapa(getLinkMapa());d.setDisLinkPdf(getLinkPdf());d.setDisHectareas(getHectareas());
				d.setDisMetrosCuadrados(getMetrosCuadrados());d.setDisNombre(getNombre());
				d.setGenZona(manager.findZonaById(getZonaId()));
				if(isEdicion())
					manager.modicarDistrito(d);
				else
					manager.insertarDistrito(d);
				cargarDistritos();
				limpiarDatos();
				return "distritos?faces-redirect=true";
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al almacenar distrito: "+e.getMessage());
			System.out.println("Error al almacenar distrito: ");e.printStackTrace();
			return "";
		}
	}
	
	public String cancelar(){
		limpiarDatos();
		cargarDistritos();
		return "distritos?faces-redirect=true";
	}
	
	private void cargarDistritos() {
		getListDistritos().clear();
		getListDistritos().addAll(manager.findAllDistritos());
	}
	
	private void limpiarDatos(){
		setId(null);setDescripcion(null);setEstado(ID_ACTIVO);
		setHectareas(new BigDecimal(0));setMetrosCuadrados(new BigDecimal(0));
		setLinkMapa(null);setLinkPdf(null);setNombre(null);
		setEdicion(false);setZonaId(SELECT_ZONA);
	}
}
