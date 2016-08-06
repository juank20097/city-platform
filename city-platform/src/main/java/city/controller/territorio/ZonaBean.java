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

import city.model.dao.entidades.GenZona;
import city.model.generic.Mensaje;
import city.model.manager.ManagerZona;


@SessionScoped
@ManagedBean
public class ZonaBean implements Serializable{


	private static final long serialVersionUID = -3198725647396964268L;
	private static String ID_ACTIVO = "A"; 
	private static String ID_INACTIVO = "I"; 

	@EJB
	private ManagerZona mngZona;
	
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
	private List<GenZona> listZonas;
	private boolean edicion;
	private List<SelectItem> slctEstados;
	
	@PostConstruct
	public void init(){
		estado = ID_ACTIVO;
		slctEstados = new ArrayList<SelectItem>();
		listZonas = new ArrayList<GenZona>();
		hectareas = new BigDecimal(0);
		metrosCuadrados = new BigDecimal(0);
		cargarZonas();
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

	public List<GenZona> getListZonas() {
		return listZonas;
	}

	public void setListZonas(List<GenZona> listZonas) {
		this.listZonas = listZonas;
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
	
	public String nuevaZona(){
		limpiarDatos();
		return "neZona?faces-redirect=true";
	}
	
	public String cargarEditarZona(GenZona zona){
		setId(zona.getZonId());setDescripcion(zona.getZonDescripcion());setEstado(zona.getZonEstado());
		setHectareas(zona.getZonHectareas());setMetrosCuadrados(zona.getZonMetrosCuadrados());
		setLinkMapa(zona.getZonLinkMapa());setLinkPdf(zona.getZonLinkPdf());setNombre(zona.getZonNombre());
		setEdicion(true);
		return "neZona?faces-redirect=true";
	}
	
	public String guardarEditarZona(){
		try {
			if(!isEdicion() && mngZona.findZonaById(getId())!=null){
				Mensaje.crearMensajeWARN("Ya existe un zona con el mismo id, favor cámbielo.");
				return "";
			}else{
				GenZona z = new GenZona();
				z.setZonId(getId());z.setZonDescripcion(getDescripcion());z.setZonEstado(getEstado());
				z.setZonLinkMapa(getLinkMapa());z.setZonLinkPdf(getLinkPdf());z.setZonHectareas(getHectareas());
				z.setZonMetrosCuadrados(getMetrosCuadrados());z.setZonNombre(getNombre());
				if(isEdicion())
					mngZona.modicarZona(z);
				else
					mngZona.insertarZona(z);
				cargarZonas();
				limpiarDatos();
				return "zonas?faces-redirect=true";
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al almacenar zona: "+e.getMessage());
			System.out.println("Error al almacenar zona: ");e.printStackTrace();
			return "";
		}
	}
	
	public String cancelar(){
		limpiarDatos();
		cargarZonas();
		return "zonas?faces-redirect=true";
	}
	
	private void cargarZonas() {
		getListZonas().clear();
		getListZonas().addAll(mngZona.findAllZonas());
	}
	
	private void limpiarDatos(){
		setId(null);setDescripcion(null);setEstado(ID_ACTIVO);
		setHectareas(new BigDecimal(0));setMetrosCuadrados(new BigDecimal(0));
		setLinkMapa(null);setLinkPdf(null);setNombre(null);
		setEdicion(false);
	}
}
