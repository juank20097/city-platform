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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GenBarrio;
import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenElementoBarrioValor;
import city.model.dao.entidades.GenElementoBarrioValorPK;
import city.model.dao.entidades.GenElementoZonaValor;
import city.model.dao.entidades.GenElementoZonaValorPK;
import city.model.dao.entidades.GenElementosBarrio;
import city.model.dao.entidades.GenElementosZona;
import city.model.dao.entidades.GenZona;
import city.model.generic.Mensaje;
import city.model.manager.ManagerTerritorio;

@SessionScoped
@ManagedBean
public class ElementoUbicacionBean implements Serializable {

	private static final long serialVersionUID = -95714538625600440L;
	private static String ID_ACTIVO = "A";
	private static String ID_INACTIVO = "I";
	private static String SELECCIONAR = "S/N";
	private static int ID_ELEMENTO = 0;
	private static String TIPO_1 = "tipo_1";
	private static String TIPO_2 = "tipo_2";

	@EJB
	private ManagerTerritorio manager;

	@Inject
	private SesionBean session;

	@NotEmpty(message = "NOMBRE no debe estar vacío.")
	@NotBlank(message = "NOMBRE no debe ser solo espacios blancos.")
	private String nombreBV;

	private String estadoBV;

	private List<SelectItem> lstElementos;
	private List<SelectItem> slctUbicaciones;
	private boolean edicionBV;
	private List<SelectItem> slctEstados;
	private int idElementoBV;

	@NotNull(message = "VALOR no debe ser nulo.")
	@DecimalMin("0")
	private BigDecimal valorBV;
	private GenElementosBarrio elementoBarrio;
	private GenElementoBarrioValorPK pkBarrio;
	private List<GenElementoBarrioValor> lstUbicacionElementoBV;
	private List<GenBarrio> lstVecindarios;

	private boolean elemBarrioSelecionado;
	private boolean elemZonaSelecionado;
	// //
	@NotEmpty(message = "NOMBRE no debe estar vacío.")
	@NotBlank(message = "NOMBRE no debe ser solo espacios blancos.")
	private String nombreBZ;

	private List<GenZona> lstZonas;
	
	@NotNull(message = "VALOR no debe ser nulo.")
	@DecimalMin("0")
	private BigDecimal valorBZ;
	private int idElementoBZ;
	private String ubicacionBZ;

	private List<GenElementoZonaValor> lstUbicacionElementoZV;
	private GenElementoZonaValorPK pkZona;
	private GenElementosZona elementoZona;
	private boolean edicionBZ;
	private String estadoBZ;

	
	private List<SelectItem> slctTipo;
	private String tipo;
	private int idElemento;
	private String ubicacion;
	
	@PostConstruct
	public void init() {
		session.validarSesion();
		estadoBV = ID_ACTIVO;
		estadoBZ = ID_ACTIVO;
		lstElementos = new ArrayList<SelectItem>();
		lstUbicacionElementoBV = new ArrayList<GenElementoBarrioValor>();
		lstUbicacionElementoZV = new ArrayList<GenElementoZonaValor>();
		slctEstados = new ArrayList<SelectItem>();
		slctUbicaciones = new ArrayList<SelectItem>();
		slctTipo = new ArrayList<SelectItem>();
		cargarTipos();
		cargarEstados();
	}

	public String getNombreBV() {
		return nombreBV;
	}

	public void setNombreBV(String nombreBV) {
		this.nombreBV = nombreBV;
	}

	public String getEstadoBV() {
		return estadoBV;
	}

	public void setEstadoBV(String estadoBV) {
		this.estadoBV = estadoBV;
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

	public boolean isEdicionBV() {
		return edicionBV;
	}

	public void setEdicionBV(boolean edicionBV) {
		this.edicionBV = edicionBV;
	}

	public List<SelectItem> getSlctEstados() {
		return slctEstados;
	}

	public void setSlctEstados(List<SelectItem> slctEstados) {
		this.slctEstados = slctEstados;
	}

	public int getIdElementoBV() {
		return idElementoBV;
	}

	public void setIdElementoBV(int idElementoBV) {
		this.idElementoBV = idElementoBV;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public BigDecimal getValorBV() {
		return valorBV;
	}

	public void setValorBV(BigDecimal valorBV) {
		this.valorBV = valorBV;
	}

	public GenElementosBarrio getElementoBarrio() {
		return elementoBarrio;
	}

	public void setElementoBarrio(GenElementosBarrio elementoBarrio) {
		this.elementoBarrio = elementoBarrio;
	}

	public GenElementoBarrioValorPK getPkBarrio() {
		return pkBarrio;
	}

	public void setPkBarrio(GenElementoBarrioValorPK pkBarrio) {
		this.pkBarrio = pkBarrio;
	}

	public List<GenElementoBarrioValor> getLstUbicacionElementoBV() {
		return lstUbicacionElementoBV;
	}

	public void setLstUbicacionElementoBV(
			List<GenElementoBarrioValor> lstUbicacionElementoBV) {
		this.lstUbicacionElementoBV = lstUbicacionElementoBV;
	}

	public List<GenBarrio> getLstVecindarios() {
		return lstVecindarios;
	}

	public void setLstVecindarios(List<GenBarrio> lstVecindarios) {
		this.lstVecindarios = lstVecindarios;
	}

	public boolean isElemZonaSelecionado() {
		return elemZonaSelecionado;
	}

	public void setElemZonaSelecionado(boolean elemZonaSelecionado) {
		this.elemZonaSelecionado = elemZonaSelecionado;
	}

	public boolean isElemBarrioSelecionado() {
		return elemBarrioSelecionado;
	}
	
	public void setElemBarrioSelecionado(boolean elemBarrioSelecionado) {
		this.elemBarrioSelecionado = elemBarrioSelecionado;
	}
	
	public String getNombreBZ() {
		return nombreBZ;
	}

	public void setNombreBZ(String nombreBZ) {
		this.nombreBZ = nombreBZ;
	}

	public List<GenZona> getLstZonas() {
		return lstZonas;
	}

	public void setLstZonas(List<GenZona> lstZonas) {
		this.lstZonas = lstZonas;
	}

	public BigDecimal getValorBZ() {
		return valorBZ;
	}

	public void setValorBZ(BigDecimal valorBZ) {
		this.valorBZ = valorBZ;
	}

	public int getIdElementoBZ() {
		return idElementoBZ;
	}

	public void setIdElementoBZ(int idElementoBZ) {
		this.idElementoBZ = idElementoBZ;
	}

	public String getUbicacionBZ() {
		return ubicacionBZ;
	}

	public void setUbicacionBZ(String ubicacionBZ) {
		this.ubicacionBZ = ubicacionBZ;
	}

	public List<GenElementoZonaValor> getLstUbicacionElementoZV() {
		return lstUbicacionElementoZV;
	}

	public void setLstUbicacionElementoZV(
			List<GenElementoZonaValor> lstUbicacionElementoZV) {
		this.lstUbicacionElementoZV = lstUbicacionElementoZV;
	}

	public GenElementoZonaValorPK getPkZona() {
		return pkZona;
	}

	public void setPkZona(GenElementoZonaValorPK pkZona) {
		this.pkZona = pkZona;
	}

	public GenElementosZona getElementoZona() {
		return elementoZona;
	}

	public void setElementoZona(GenElementosZona elementoZona) {
		this.elementoZona = elementoZona;
	}

	public boolean isEdicionBZ() {
		return edicionBZ;
	}

	public void setEdicionBZ(boolean edicionBZ) {
		this.edicionBZ = edicionBZ;
	}

	public String getEstadoBZ() {
		return estadoBZ;
	}

	public void setEstadoBZ(String estadoBZ) {
		this.estadoBZ = estadoBZ;
	}

	public List<SelectItem> getSlctTipo() {
		return slctTipo;
	}
	
	public void setSlctTipo(List<SelectItem> slctTipo) {
		this.slctTipo = slctTipo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public int getIdElemento() {
		return idElemento;
	}
	
	public void setIdElemento(int idElemento) {
		this.idElemento = idElemento;
	}
	
	private void cargarEstados() {
		getSlctEstados().add(new SelectItem(ID_ACTIVO, "Activo"));
		getSlctEstados().add(new SelectItem(ID_INACTIVO, "Inactivo"));
	}

	private void cargarTipos() {
		getSlctTipo().add(new SelectItem(SELECCIONAR, "Seleccionar"));
		for (GenCatalogoItemsDet i : manager.AllofItems("cat_tipo_elemento")) {
			getSlctTipo().add(
					new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	public void cargarElementos() {
		limpiarCamposBusqueda();
		setIdElemento(idElemento);
		getLstElementos().clear();
		getLstElementos().add(new SelectItem(ID_ELEMENTO, "Seleccionar"));
		if(getTipo().equals(TIPO_1)){
			for (GenElementosZona z : manager.findAllElementosZona()) {
				getLstElementos().add(new SelectItem(z.getElzId(), z.getElzNombre()));
			}
		}else if(getTipo().equals(TIPO_2)){
			for (GenElementosBarrio b : manager.findAllElementosBarrio()) {
				getLstElementos().add(new SelectItem(b.getElbId(), b.getElbNombre()));
			}
		}
	}

	public void verElementoUbicacion(){
		limpiarCamposBusqueda();
		try {
			if(getTipo().equals(TIPO_1)){
				cargarListaEZV();
			}else if(getTipo().equals(TIPO_2)){
				cargarListaEBV();
			}
			
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al ver ubicaciones: "+e.getMessage());
			e.printStackTrace();
		}
	}
	public void limpiarCamposBusqueda(){
		getLstUbicacionElementoBV().clear();
		getLstUbicacionElementoZV().clear();
		setElemBarrioSelecionado(false);
		setElemZonaSelecionado(false);
		setElementoBarrio(null);
		setElementoZona(null);
	}
	public void cargarListaEBV() {
		System.out.println("elemento a buscar ----> " + getIdElemento());
		getLstUbicacionElementoBV().clear();
		try {
			if (getIdElemento() != ID_ELEMENTO) {
				setElementoBarrio(manager.findElementosBarrioByID(idElemento));
				cargarElementoBarrioValor(getElementoBarrio());
				setElemBarrioSelecionado(true);
			} else {
				setElemBarrioSelecionado(false);
				setElementoBarrio(null);
				Mensaje.crearMensajeWARN("Debe seleccionar el elemento para cargar las ubicaciones.");
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al cargar Lista de ubicaciones por elemento. ");
			e.printStackTrace();
		}
	}
	
	public void cargarListaEZV() {
		System.out.println("elemento a buscar ----> " + getIdElemento());
		getLstUbicacionElementoZV().clear();
		try {
			if (getIdElemento() != ID_ELEMENTO) {
				setElementoZona(manager.findElementosZonaByID(idElemento));
				cargarElementoZonaValor(getElementoZona());
				setElemZonaSelecionado(true);
			} else {
				setElemZonaSelecionado(false);
				setElementoZona(null);
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
			System.out.println("item a validar ----------> " + item);
			respuesta = manager.catalogoItem(item);

		} catch (Exception e) {
			e.printStackTrace();
			Mensaje.crearMensajeERROR("Error al validar Item. "
					+ e.getMessage());
		}
		return respuesta;
	}

	public String nuevoElementoBarrioValor() {
		// limpiarDatos();
		cargarUbicaciones();
		return "nElementoBV?faces-redirect=true";
	}

	public String cargarEditarElementoBarrioValor(GenElementoBarrioValor barrioValor) {
		setPkBarrio(barrioValor.getId());
		setElementoBarrio(barrioValor.getGenElementosBarrio());
		setUbicacion(barrioValor.getId().getBarId());
		setEstadoBV(barrioValor.getEbvEstado());
		setValorBV(barrioValor.getEbvValor());
		setEdicionBV(true);
		cargarUbicaciones();

		return "nElementoBV?faces-redirect=true";
	}

	private void cargarUbicaciones() {
		getSlctUbicaciones().clear();
		getSlctUbicaciones().add(new SelectItem(SELECCIONAR, "Seleccionar"));
		if (getTipo().equals(TIPO_1)) {
			for (GenZona zona : manager.findAllZonasA()) {
				getSlctUbicaciones()
						.add(new SelectItem(zona.getZonId(), zona
								.getZonNombre()));
			}
		} else if (getTipo().equals(TIPO_2)) {
			for (GenBarrio barrio : manager.findAllBarriosA()) {
				getSlctUbicaciones().add(
						new SelectItem(barrio.getBarId(), barrio
								.getBarNombre()));
			}
		}
	}

	public String guardarEditarElementoBarrioValor() { 
		try {
			if (!validarSelects()) {
				Mensaje.crearMensajeWARN("Se debe seleccionar todos los campos para continuar.");
				return "";
			} else {
				GenElementoBarrioValorPK pk = new GenElementoBarrioValorPK();
				pk.setElbId(getElementoBarrio().getElbId());
				pk.setBarId(getUbicacion());
				GenElementoBarrioValor ebv = new GenElementoBarrioValor();
				ebv.setId(pk);
				ebv.setGenBarrio(manager.findBarrioById(getUbicacion()));
				ebv.setEbvEstado(getEstadoBV());
				ebv.setEbvValor(getValorBV());

				if (isEdicionBV()) {
					manager.modificarElementoBarrioValor(ebv);
					cargarElementoBarrioValor(getElementoBarrio());
					limpiarDatosBV();
					return "elementosUbicacion?faces-redirect=true";
				} else {
					if (validarExistenciaEBV(pk)) {
						manager.insertarElementoBarrioValor(ebv);
						cargarElementoBarrioValor(getElementoBarrio());
						limpiarDatosBV();
						return "elementosUbicacion?faces-redirect=true";
					} else {
						Mensaje.crearMensajeERROR("Este elemento ya fue ingresado en la ubicación seleccionada.");
					}
				}
				return "";
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al almacenar ubicación elemento: "
					+ e.getMessage());
			System.out.println("Error al almacenar ubicación elemento: ");
			e.printStackTrace();
			return "";
		}
	}

	public boolean validarSelects() {
		if (getUbicacion().equals(SELECCIONAR)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validarExistenciaEBV(GenElementoBarrioValorPK id) {
		try {
			if (manager.findElementoBarrioValorByID(id) == null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String cancelarBV(){
		limpiarDatosBV();
		cargarElementoBarrioValor(getElementoBarrio());
		return "elementosUbicacion?faces-redirect=true";
	}

	private void cargarElementoBarrioValor(GenElementosBarrio elemento) {
		getLstUbicacionElementoBV().clear();
		getLstUbicacionElementoBV().addAll(
				manager.findAllElementoBVByElemento(elemento.getElbId()));
	}

	private void limpiarDatosBV() {
		setValorBV(BigDecimal.ZERO);
		setUbicacion(SELECCIONAR);
		getSlctUbicaciones().clear();
		setEstadoBV(ID_ACTIVO);
		setEdicionBV(false);
	}
	
	/// *** ///
	
	public String nuevoElementoZonaValor() {
		// limpiarDatos();
		cargarUbicaciones();
		return "nElementoZV?faces-redirect=true";
	}

	public String cargarEditarElementoZonaValor(GenElementoZonaValor zonaValor) {
		setPkZona(zonaValor.getId());
		setElementoZona(zonaValor.getGenElementosZona());
		setUbicacion(zonaValor.getId().getZonId());
		setEstadoBZ(zonaValor.getEzvEstado());
		setValorBZ(zonaValor.getEzvValor());
		setEdicionBZ(true);
		cargarUbicaciones();

		return "nElementoZV?faces-redirect=true";
	}
	
	public String guardarEditarElementoZonaValor() { 
		try {
			if (!validarSelects()) {
				Mensaje.crearMensajeWARN("Se debe seleccionar todos los campos para continuar.");
				return "";
			} else {
				GenElementoZonaValorPK pk = new GenElementoZonaValorPK();
				pk.setElzId(getElementoZona().getElzId());
				pk.setZonId(getUbicacion());
				GenElementoZonaValor ezv = new GenElementoZonaValor();
				ezv.setId(pk);
				ezv.setGenZona(manager.findZonaById(getUbicacion()));
				ezv.setEzvEstado(getEstadoBZ());
				ezv.setEzvValor(getValorBZ());

				if (isEdicionBZ()) {
					manager.modificarElementoZonaValor(ezv);
					cargarElementoZonaValor(getElementoZona());
					limpiarDatosZV();
					return "elementosUbicacion?faces-redirect=true";
				} else {
					if (validarExistenciaEZV(pk)) {
						manager.insertarElementoZonaValor(ezv);
						cargarElementoZonaValor(getElementoZona());
						limpiarDatosBV();
						return "elementosUbicacion?faces-redirect=true";
					} else {
						Mensaje.crearMensajeERROR("Este elemento ya fue ingresado en la ubicación seleccionada.");
					}
				}
				return "";
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al almacenar ubicación elemento Zona: "
					+ e.getMessage());
			System.out.println("Error al almacenar ubicación elemento Zona: ");
			e.printStackTrace();
			return "";
		}
	}

	private void limpiarDatosZV() {
		setValorBZ(BigDecimal.ZERO);
		setUbicacion(SELECCIONAR);
		getSlctUbicaciones().clear();
		setEstadoBZ(ID_ACTIVO);
		setEdicionBZ(false);
	}
	
	public boolean validarExistenciaEZV(GenElementoZonaValorPK id) {
		try {
			if (manager.findElementoZonaValorByID(id) == null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String cancelarZV(){
		limpiarDatosZV();
		cargarElementoZonaValor(getElementoZona());
		return "elementosUbicacion?faces-redirect=true";
	}

	private void cargarElementoZonaValor(GenElementosZona elemento) {
		getLstUbicacionElementoZV().clear();
		getLstUbicacionElementoZV().addAll(
				manager.findAllElementoZVByElemento(elemento.getElzId()));
	}
}
