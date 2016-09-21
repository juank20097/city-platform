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
import javax.inject.Inject;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GenBarrio;
import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenElemento;
import city.model.dao.entidades.GenUbicacionelemento;
import city.model.dao.entidades.GenUbicacionelementoPK;
import city.model.dao.entidades.GenZona;
import city.model.generic.Mensaje;
import city.model.manager.ManagerTerritorio;

@SessionScoped
@ManagedBean
public class UbicacionElementoBean implements Serializable{

	private static final long serialVersionUID = -95714538625600440L;
	private static String ID_ACTIVO = "A"; 
	private static String ID_INACTIVO = "I";
	private static String SELECCIONAR = "S/N";
	private static int ID_ELEMENTO = 0;

	@EJB
	private ManagerTerritorio manager;
	
	@Inject
	private SesionBean session;
	
	private int id;
	
	@NotEmpty(message="NOMBRE no debe estar vacío.")
	@NotBlank(message="NOMBRE no debe ser solo espacios blancos.")
	private String nombre;
	
	private String estado;
	
	private List<SelectItem> lstElementos;
	private List<SelectItem> slctUbicaciones;
	private boolean edicion;
	private List<SelectItem> slctEstados;
	private List<SelectItem> slctTipos;
	private List<SelectItem> slctUnidadesMedida;
	// 
	private int idElemento;
	private String ubicacion;
	
	@DecimalMin("1")
	private BigDecimal valor;
	private GenElemento elemento;
	private GenUbicacionelementoPK pk;
	private List<GenUbicacionelemento> lstUbicacionElemento;
	private List<GenZona> lstZonas;
	private List<GenBarrio> lstVecindarios;
	
	private boolean elemSelecionado;
	
	@PostConstruct
	public void init(){
		session.validarSesion();
		estado = ID_ACTIVO;
		lstElementos = new ArrayList<SelectItem>();
		lstUbicacionElemento = new ArrayList<GenUbicacionelemento>();
		slctEstados = new ArrayList<SelectItem>();
		slctTipos = new ArrayList<SelectItem>();
		slctUnidadesMedida = new ArrayList<SelectItem>();
		slctUbicaciones = new ArrayList<SelectItem>();
		cargarElementos();
		cargarTipos();
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

	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public List<SelectItem> getLstElementos() {
		return lstElementos;
	}


	public void setLstElementos(List<SelectItem> lstElementos) {
		this.lstElementos = lstElementos;
	}
	
	public List<SelectItem> getSlctUbicaciones() {
		return slctUbicaciones;
	}

	public void setSlctUbicaciones(List<SelectItem> slctUbicaciones) {
		this.slctUbicaciones = slctUbicaciones;
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


	public List<SelectItem> getSlctTipos() {
		return slctTipos;
	}


	public void setSlctTipos(List<SelectItem> slctTipos) {
		this.slctTipos = slctTipos;
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

	public String getUbicacion() {
		return ubicacion;
	}
	
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public GenElemento getElemento() {
		return elemento;
	}
	
	public void setElemento(GenElemento elemento) {
		this.elemento = elemento;
	}
	
	public GenUbicacionelementoPK getPk() {
		return pk;
	}
	
	public void setPk(GenUbicacionelementoPK pk) {
		this.pk = pk;
	}
	
	public List<GenUbicacionelemento> getLstUbicacionElemento() {
		return lstUbicacionElemento;
	}
	
	public void setLstUbicacionElemento(
			List<GenUbicacionelemento> lstUbicacionElemento) {
		this.lstUbicacionElemento = lstUbicacionElemento;
	}
	
	public List<GenZona> getLstZonas() {
		return lstZonas;
	}

	public void setLstZonas(List<GenZona> lstZonas) {
		this.lstZonas = lstZonas;
	}

	public List<GenBarrio> getLstVecindarios() {
		return lstVecindarios;
	}

	public void setLstVecindarios(List<GenBarrio> lstVecindarios) {
		this.lstVecindarios = lstVecindarios;
	}
	
	public boolean isElemSelecionado() {
		return elemSelecionado;
	}
	
	public void setElemSelecionado(boolean elemSelecionado) {
		this.elemSelecionado = elemSelecionado;
	}
	
	private void cargarEstados() {
		getSlctEstados().add(new SelectItem(ID_ACTIVO,"Activo"));
		getSlctEstados().add(new SelectItem(ID_INACTIVO,"Inactivo"));
	}
	
	private void cargarTipos() {
		getSlctTipos().add(new SelectItem(SELECCIONAR,"Seleccionar"));
		for (GenCatalogoItemsDet i : manager.AllofItems("cat_tipo_elemento")) {
			getSlctTipos().add(new SelectItem(i.getIteCodigo(),i.getIteNombre()));
		}
	}
	
	private void cargarElementos() {
		getLstElementos().clear();
		getLstElementos().add(new SelectItem(ID_ELEMENTO,"Seleccionar"));
		for (GenElemento ele : manager.findAllElementos()) {
			getLstElementos().add(new SelectItem(ele.getEleId(), ele.getEleNombre()));
		}
	}
	
	public void cargarLstUbicaciones(){
		System.out.println("elemento a buscar ----> "+getIdElemento());
		getLstUbicacionElemento().clear();
		try {
			if(getIdElemento() != ID_ELEMENTO){
				setElemento(manager.findElementoByID(idElemento));
				cargarUbicacionByElemento(getElemento());
				setElemSelecionado(true);
			}else{
				setElemSelecionado(false);
				setElemento(null);
				Mensaje.crearMensajeWARN("Debe seleccionar el elemento para cargar las ubicaciones.");
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al cargar Lista de ubicaciones por elemento. ");
			e.printStackTrace();
		}
		
	}
	
	public String validarItemCatalogo(String item) {
		String respuesta = "";
		try {
			System.out.println("item a validar ----------> "+item);
			respuesta = manager.catalogoItem(item);

		} catch (Exception e) {
			e.printStackTrace();
			Mensaje.crearMensajeERROR("Error al validar Item. " + e.getMessage());
		}
		return respuesta;
	}
	
	public String nuevaUbicacionElemento(){
	//	limpiarDatos();
		cargarUbicaciones();
		return "nUbicacionElemento?faces-redirect=true";
	}
	
	public String cargarEditarUbicacionElemento(GenUbicacionelemento ubElemento) {
		setPk(ubElemento.getId());
		setElemento(ubElemento.getGenElemento());
		setUbicacion(ubElemento.getId().getIdVecindarioZona());
		setEstado(ubElemento.getVelEstado());
		setValor(ubElemento.getVelValor());
		setEdicion(true);
		cargarUbicaciones();

		return "nUbicacionElemento?faces-redirect=true";
	}
	
	private void cargarUbicaciones() {
		getSlctUbicaciones().clear();
		getSlctUbicaciones().add(new SelectItem(SELECCIONAR,"Seleccionar"));
		if(getElemento().getEleTipo().equals("tipo_1")){
			for (GenZona zona : manager.findAllZonasA()) {
				getSlctUbicaciones().add(new SelectItem(zona.getZonNombre(),zona.getZonNombre()));
			}
		}else if(getElemento().getEleTipo().equals("tipo_2")){
			for (GenBarrio barrio : manager.findAllBarriosA()) {
				getSlctUbicaciones().add(new SelectItem(barrio.getBarNombre(),barrio.getBarNombre()));
			}
		}
	}
	
	public String guardarEditarUbicacionElemento(){
		try {
			if(!validarSelects()){
				Mensaje.crearMensajeWARN("Se debe seleccionar todos los campos para continuar.");
				return "";
			}else{
				GenUbicacionelementoPK pkU = new GenUbicacionelementoPK();
				pkU.setEleId(getElemento().getEleId());
				pkU.setIdVecindarioZona(getUbicacion());
				GenUbicacionelemento u = new GenUbicacionelemento();
				u.setId(pkU);
				u.setGenElemento(getElemento());
				u.setVelEstado(getEstado());
				u.setVelValor(getValor());
				
				if(isEdicion()){
					manager.modificarUbicacionElemento(u);
					cargarUbicacionByElemento(getElemento());
					limpiarDatos();
					return "ubicacionElementos?faces-redirect=true";
				}else{
					if(validarExistenciaUE(pkU)){
						manager.insertarUbicacionElemento(u);
						cargarUbicacionByElemento(getElemento());
						limpiarDatos();
						return "ubicacionElementos?faces-redirect=true";
					}else{
						Mensaje.crearMensajeERROR("Este elemento ya fue ingresado en la ubicación seleccionada.");
					}
				}
				return "";
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al almacenar ubicación elemento: "+e.getMessage());
			System.out.println("Error al almacenar ubicación elemento: ");e.printStackTrace();
			return "";
		}
	}
	
	public boolean validarSelects(){
		if(getUbicacion().equals(SELECCIONAR)){
			return false;
		} else {
			return true;
		}
	}
	
	public boolean validarExistenciaUE(GenUbicacionelementoPK id){
		try {
			if(manager.findUbicacionByID(id) == null){
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public String cancelar(){
		limpiarDatos();
		cargarUbicacionByElemento(getElemento());
		return "ubicacionElementos?faces-redirect=true";
	}
	
	private void cargarUbicacionByElemento(GenElemento elemento){
		getLstUbicacionElemento().clear();
		getLstUbicacionElemento().addAll(manager.findAllUbicacionesByElemento(elemento.getEleId()));
	}

	private void limpiarDatos(){
		setValor(BigDecimal.ZERO); setUbicacion(SELECCIONAR); 
		getSlctUbicaciones().clear(); setEstado(ID_ACTIVO);
		setEdicion(false); 
	}
		
}
