package city.controller.territorio;

import java.io.Serializable;
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
import city.model.dao.entidades.GenElementosZona;
import city.model.dao.entidades.GenZona;
import city.model.generic.Funciones;
import city.model.generic.Mensaje;
import city.model.manager.ManagerTerritorio;

@SessionScoped
@ManagedBean
public class ElementoZonaBean implements Serializable{

	private static final long serialVersionUID = -54606143247492817L;
	private static String ID_ACTIVO = "A"; 
	private static String ID_INACTIVO = "I";
	private static String SELECCIONAR = "S/N";
	private static String TIPO_UBICACION = "tipo_1";

	@EJB
	private ManagerTerritorio manager;
	
	@Inject
	private SesionBean session;
	
	private int id;
	
	@NotEmpty(message="NOMBRE no debe estar vacío.")
	@NotBlank(message="NOMBRE no debe ser solo espacios blancos.")
	private String nombre;
	
	private String tipo;
	private String unidadMedida;
	private String estado;
	
	private List<GenElementosZona> lstElementos;
	private boolean edicion;
	private List<SelectItem> slctEstados;
	private List<SelectItem> slctUnidadesMedida;
	// 
	private int idElemento;
	private List<SelectItem> slctUbicaciones;
	private List<GenZona> lstZonas;
	
	@PostConstruct
	public void init(){
		session.validarSesion();
		estado = ID_ACTIVO;
		tipo = TIPO_UBICACION;
		lstElementos = new ArrayList<GenElementosZona>();
		slctEstados = new ArrayList<SelectItem>();
		slctUnidadesMedida = new ArrayList<SelectItem>();
		cargarElementos();
		cargarUnidadesMedida();
		cargarEstados();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<GenElementosZona> getLstElementos() {
		return lstElementos;
	}

	public void setLstElementos(List<GenElementosZona> lstElementos) {
		this.lstElementos = lstElementos;
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

	public List<SelectItem> getSlctUnidadesMedida() {
		return slctUnidadesMedida;
	}

	public void setSlctUnidadesMedida(List<SelectItem> slctUnidadesMedida) {
		this.slctUnidadesMedida = slctUnidadesMedida;
	}

	public int getIdElemento() {
		return idElemento;
	}

	public void setIdElemento(int idElemento) {
		this.idElemento = idElemento;
	}

	public List<SelectItem> getSlctUbicaciones() {
		return slctUbicaciones;
	}

	public void setSlctUbicaciones(List<SelectItem> slctUbicaciones) {
		this.slctUbicaciones = slctUbicaciones;
	}

	public List<GenZona> getLstZonas() {
		return lstZonas;
	}

	public void setLstZonas(List<GenZona> lstZonas) {
		this.lstZonas = lstZonas;
	}

	private void cargarEstados() {
		getSlctEstados().add(new SelectItem(ID_ACTIVO,"Activo"));
		getSlctEstados().add(new SelectItem(ID_INACTIVO,"Inactivo"));
	}
	
	private void cargarUnidadesMedida() {
		getSlctUnidadesMedida().add(new SelectItem(SELECCIONAR,"Seleccionar"));
		for (GenCatalogoItemsDet i : manager.AllofItems("cat_unidades_medida")) {
			getSlctUnidadesMedida().add(new SelectItem(i.getIteCodigo(),i.getIteNombre()));
		}
	}
	public String validarItemCatalogo(String item) {
		String respuesta = "";
		try {
			respuesta = manager.catalogoItem(item);

		} catch (Exception e) {
			e.printStackTrace();
			Mensaje.crearMensajeERROR("Error al validar Item. " + e.getMessage());
		}
		return respuesta;
	}
	
	public String nuevoElemento(){
		limpiarDatos();
		return "nElementoZ?faces-redirect=true";
	}
	
	public String cargarEditarElemento(GenElementosZona elemento){
		setId(elemento.getElzId());
		setNombre(Funciones.quitarEspacios(elemento.getElzNombre()));
		setEstado(elemento.getElzEstado());
		setTipo(elemento.getElzTipo());
		setUnidadMedida(elemento.getElzUnidadMedida());
		setEdicion(true);
		
		return "nElementoZ?faces-redirect=true";
	}
	
	public String guardarEditarElemento(){
		try {
			if(!validarSelects()){
				Mensaje.crearMensajeWARN("Se debe seleccionar todos los campos para continuar.");
				return "";
			}else{
				GenElementosZona e = new GenElementosZona();
				e.setElzNombre(Funciones.quitarEspacios(getNombre().toUpperCase()));
				e.setElzTipo(getTipo());
				e.setElzUnidadMedida(getUnidadMedida());
				e.setElzEstado(getEstado());
				if(isEdicion()){
					e.setElzId(getId());
					manager.modificarElementoZona(e);
					Mensaje.crearMensajeINFO("Elemento Zona actualizado correctamente.");
				}else{
					setId(manager.idElementoZona());
					e.setElzId(getId());
					manager.insertarElementoZona(e);
					Mensaje.crearMensajeINFO("Elemento Zona insertado correctamente.");
				}
				cargarElementos();
				limpiarDatos();
				return "elementosZ?faces-redirect=true";
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al almacenar elemento Zona: "+e.getMessage());
			System.out.println("Error al almacenar elemento Zona: ");e.printStackTrace();
			return "";
		}
	}
	public boolean validarSelects(){
		if(getUnidadMedida().equals(SELECCIONAR)){
			return false;
		} else {
			return true;
		}
	}
	
	public String cancelar(){
		limpiarDatos();
		cargarElementos();
		return "elementosZ?faces-redirect=true";
	}
	
	private void cargarElementos() {
		getLstElementos().clear();
		getLstElementos().addAll(manager.findAllElementosZona());
	}
	
	private void limpiarDatos(){
		setId(0); setNombre(null); setUnidadMedida(SELECCIONAR);
		setEstado(ID_ACTIVO); setEdicion(false);
	}
}
