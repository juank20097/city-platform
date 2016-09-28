package city.controller.territorio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.hibernate.validator.constraints.URL;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GenBarrio;
import city.model.dao.entidades.GenDistrito;
import city.model.generic.Funciones;
import city.model.generic.Mensaje;
import city.model.manager.ManagerTerritorio;

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
	private ManagerTerritorio manager;
	@Inject
	private SesionBean session;

	@NotEmpty(message = "ID no debe estar vacío.")
	@NotBlank(message = "ID no debe ser solo espacios blancos.")
	private String id;

	@NotEmpty(message = "DESCRIPCIÓN no debe estar vacío.")
	@NotBlank(message = "DESCRIPCIÓN no debe ser solo espacios blancos.")
	private String descripcion;

	private String estado;

	@DecimalMin("0")
	private BigDecimal hectareas;

	@NotEmpty(message = "MAPA LINK no debe estar vacío.")
	@NotBlank(message = "MAPA LINK no debe ser solo espacios blancos.")
	@URL(message = "MAPA LINK no es una url válida.")
	private String linkMapa;

	@NotEmpty(message = "PDF LINK no debe estar vacío.")
	@NotBlank(message = "PDF LINK no debe ser solo espacios blancos.")
	@URL(message = "PDF LINK no es una url válida.")
	private String linkPdf;

	@DecimalMin("0")
	private BigDecimal kilometros;

	@NotEmpty(message = "NOMBRE no debe estar vacío.")
	@NotBlank(message = "NOMBRE no debe ser solo espacios blancos.")
	private String nombre;

	private String observacion;
	
	private UploadedFile fileMapa;
	private UploadedFile filePdf;
	
	private String dirMapa; 
	private String dirPdf;
	private boolean guardado;
	
	private List<GenBarrio> lstBarrios;
	private boolean edicion;
	private List<SelectItem> slctEstados;
	private String distritoId;
	private GenDistrito distrito;
	private List<SelectItem> slctDistritos;

	@PostConstruct
	public void init() {
		session.validarSesion();
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

	public BigDecimal getKilometros() {
		return kilometros;
	}

	public void setKilometros(BigDecimal kilometros) {
		this.kilometros = kilometros;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObservacion() {
		return observacion;
	}
	
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public UploadedFile getFileMapa() {
		return fileMapa;
	}

	public void setFileMapa(UploadedFile fileMapa) {
		this.fileMapa = fileMapa;
	}

	public UploadedFile getFilePdf() {
		return filePdf;
	}

	public void setFilePdf(UploadedFile filePdf) {
		this.filePdf = filePdf;
	}

	public String getDirMapa() {
		return dirMapa;
	}

	public void setDirMapa(String dirMapa) {
		this.dirMapa = dirMapa;
	}

	public String getDirPdf() {
		return dirPdf;
	}

	public void setDirPdf(String dirPdf) {
		this.dirPdf = dirPdf;
	}
	
	public boolean isGuardado() {
		return guardado;
	}
	
	public void setGuardado(boolean guardado) {
		this.guardado = guardado;
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

	public GenDistrito getDistrito() {
		return distrito;
	}
	
	public void setDistrito(GenDistrito distrito) {
		this.distrito = distrito;
	}
	
	public List<SelectItem> getSlctDistritos() {
		return slctDistritos;
	}

	public void setSlctDistritos(List<SelectItem> slctDistritos) {
		this.slctDistritos = slctDistritos;
	}

	// Mï¿½todos

	private void cargarEstados() {
		getSlctEstados().add(new SelectItem(ID_ACTIVO, "Activo"));
		getSlctEstados().add(new SelectItem(ID_INACTIVO, "Inactivo"));
	}

	private void cargarDistritos() {
		getSlctDistritos().add(new SelectItem(SELECT_DISTRITO, "Seleccionar"));
		for (GenDistrito distrito : manager.findAllDistritosAc()) {
			getSlctDistritos().add(new SelectItem(distrito.getDisId(), distrito.getDisNombre()));
		}
	}

	public String nuevoBarrio() {
		limpiarDatos();
		return "nVecindario?faces-redirect=true";
	}

	public String cargarBarrio(GenBarrio barrio) {
		setEdicion(true);
		setId(barrio.getBarId());
		setDescripcion(barrio.getBarDescripcion());
		setEstado(barrio.getBarEstado());
		setHectareas(barrio.getBarHectareas());
		setKilometros(barrio.getBarKilometros());
		setObservacion(barrio.getBarObservacion());
		setNombre(barrio.getBarNombre());
		setDistritoId(barrio.getGenDistrito().getDisId());
		setDistrito(barrio.getGenDistrito());
		setObservacion(barrio.getBarObservacion());
		setDirMapa(barrio.getBarLinkMapa());
		setDirPdf(barrio.getBarLinkPdf());

		return "nVecindario?faces-redirect=true";
	}

	public String guardarEditarBarrio() {
		try {
			String respuesta = "";
			if (getDistritoId().equals(SELECT_DISTRITO)) {
				Mensaje.crearMensajeWARN("Seleccione un distrito");
				return respuesta;
			} else if (!isEdicion() && manager.findBarrioById(getId()) != null) {
				Mensaje.crearMensajeWARN("Ya existe un vecindario con el mismo id, favor cámbielo.");
				return respuesta;
			} else {
				GenBarrio b = new GenBarrio();
				b.setBarId(Funciones.quitarEspacios(getId()).toUpperCase());
				b.setBarDescripcion(Funciones.quitarEspacios(getDescripcion().toLowerCase()));
				b.setBarEstado(getEstado());
				b.setBarKilometros(getKilometros());
				b.setBarNombre(Funciones.quitarEspacios(getNombre()).toUpperCase());
				b.setBarHectareas(getHectareas());
				b.setBarObservacion(Funciones.quitarEspacios(getObservacion()));
				b.setGenDistrito(manager.findDistritoById(getDistritoId()));
				if (isEdicion()) {
					if(getDirMapa()!= null || getDirMapa() != ""){
						b.setBarLinkMapa(getDirMapa());
					}else {
						b.setBarLinkMapa(manager.findBarrioById(getId()).getBarLinkMapa());
					}
					if(getDirPdf() != null || getDirPdf() != ""){
						b.setBarLinkPdf(getDirPdf());
					} else{
						b.setBarLinkPdf(manager.findBarrioById(getId()).getBarLinkPdf());
					}
					manager.modicarBarrio(b);
					Mensaje.crearMensajeINFO("Vecindario actualizado correctamente.");
				} else {
					manager.insertarBarrio(b); 
					setEdicion(true);
					Mensaje.crearMensajeINFO("Vecindario ingresado correctamente. ");
				}
				return respuesta;
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error: " + e.getMessage());
			return "";
		}
	}

	public String cancelar() {
		limpiarDatos();
		cargarBarrios();
		return "vecindarios?faces-redirect=true";
	}

	private void cargarBarrios() {
		getLstBarrios().clear();
		getLstBarrios().addAll(manager.findAllBarrios());
	}

	private void limpiarDatos() {
		setId(null);
		setDescripcion(null);
		setEstado(ID_ACTIVO);
		setKilometros(new BigDecimal(0));
		setDirMapa(null);
		setDirPdf(null);
		setNombre(null);
		setObservacion(null);
		setEdicion(false);
		setDistritoId(SELECT_DISTRITO);
	}
	public void cargaMapa(FileUploadEvent event) throws IOException {
		fileMapa = event.getFile();
		InputStream inputStream = null;
		OutputStream outputStream = null;

		if (fileMapa != null) {
			try {
				
				String carpeta = manager.findParametroByID("direccion_mapa") + "/";
					setDirMapa("KMZ_V_" +getId()+ extensionArchivo(fileMapa.getFileName()));
					System.out.println("PAD------> " + carpeta);
					System.out.println("name------> " + getDirMapa());
					outputStream = new FileOutputStream(new File(carpeta + File.separatorChar + getDirMapa()));
					inputStream = fileMapa.getInputstream();

					int read = 0;
					byte[] bytes = new byte[1024];

					while ((read = inputStream.read(bytes)) != -1) {
						outputStream.write(bytes, 0, read);
					}
					Mensaje.crearMensajeINFO("Carga del archivo Correcta");
					editarBarrio();
			} catch (Exception e) {
				Mensaje.crearMensajeERROR("No se pudo cargar el archivo");
				e.printStackTrace();
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			}
		} else {
			Mensaje.crearMensajeWARN("No se pudo cargar el archivo");
		}
	}
	
	public void cargaPDF(FileUploadEvent event) throws IOException {
		filePdf = event.getFile();
		InputStream inputStream = null;
		OutputStream outputStream = null;

		if (filePdf != null) {
			try {
				
				String carpeta = manager.findParametroByID("direccion_pdf") + "/";
					setDirPdf("MAPA_V_" +getId()+ extensionArchivo(filePdf.getFileName()));
					System.out.println("PAD------> " + carpeta);
					System.out.println("name------> " + getDirPdf());
					outputStream = new FileOutputStream(new File(carpeta + File.separatorChar + getDirPdf()));
					inputStream = filePdf.getInputstream();

					int read = 0;
					byte[] bytes = new byte[1024];

					while ((read = inputStream.read(bytes)) != -1) {
						outputStream.write(bytes, 0, read);
					}
					Mensaje.crearMensajeINFO("Carga del archivo Correcta");
					editarBarrio();
			} catch (Exception e) {
				Mensaje.crearMensajeERROR("No se pudo cargar el archivo");
				e.printStackTrace();
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			}
		} else {
			Mensaje.crearMensajeWARN("No se pudo cargar el archivo");
		}
	}
	
	public String extensionArchivo(String nombreArchivo){
		return nombreArchivo.substring(nombreArchivo.lastIndexOf('.'));
	}
	
	public void editarBarrio(){
		try {
			GenBarrio bar = new GenBarrio();
			bar.setBarId(Funciones.quitarEspacios(getId()).toUpperCase());
			bar.setBarNombre(Funciones.quitarEspacios(getNombre()).toUpperCase());
			bar.setBarDescripcion(Funciones.quitarEspacios(getDescripcion()).toUpperCase());
			bar.setBarObservacion(Funciones.quitarEspacios(getObservacion()).toUpperCase());
			bar.setBarKilometros(getKilometros());
			bar.setBarHectareas(getHectareas());
			bar.setGenDistrito(getDistrito());
			bar.setBarLinkMapa(getDirMapa());
			bar.setBarLinkPdf(getDirPdf());
			bar.setBarEstado(getEstado());
			manager.modicarBarrio(bar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void descargarMapa(String idBarrio) {
		try {
			GenBarrio barrio = manager.findBarrioById(idBarrio);
			if (barrio.getBarLinkMapa() == null
					|| barrio.getBarLinkMapa().isEmpty()) {
				Mensaje.crearMensajeERROR("El Vecindario no cuenta con un archivo asignado.");
			} else {
				String contextPath = manager
						.findParametroByID("direccion_mapa") + File.separatorChar
						+ barrio.getBarLinkMapa() + "";
				Funciones.descargarPDF(contextPath);
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error: "+e.getMessage());
			e.printStackTrace();
		}
	}
	public void descargarPDF( String idBarrio) {
		try {
			GenBarrio barrio = manager.findBarrioById(idBarrio);
			if (barrio.getBarLinkPdf() == null
					|| barrio.getBarLinkPdf().isEmpty()) {
				Mensaje.crearMensajeERROR("El Vecindario no cuenta con un archivo asignado.");
			} else {
				String contextPath = manager
						.findParametroByID("direccion_pdf") + File.separatorChar
						+ barrio.getBarLinkPdf() + "";
				Funciones.descargarPDF(contextPath);
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error: "+e.getMessage());
			e.printStackTrace();
		}
	}
}
