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
import city.model.dao.entidades.GenZona;
import city.model.generic.Funciones;
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
	@Inject
	private SesionBean session;

	
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
	
	@DecimalMin("1")
	private BigDecimal kilometros;
	@NotEmpty(message="NOMBRE no debe estar vacío.")
	@NotBlank(message="NOMBRE no debe ser solo espacios blancos.")
	private String nombre;
	
	@NotEmpty(message="OBSERVACIÓN no debe estar vacío.")
	@NotBlank(message="OBSERVACIÓN no debe ser solo espacios blancos.")
	private String observacion;
	
	private UploadedFile fileMapa;
	private UploadedFile filePdf;
	
	private String dirMapa; 
	private String dirPdf;
	
	private List<GenDistrito> listDistritos;
	private boolean edicion;
	private List<SelectItem> slctEstados;
	private String zonaId;
	private GenZona zona;
	private List<SelectItem> slctZonas;
	
	@PostConstruct
	public void init(){
		session.validarSesion();
		estado = ID_ACTIVO;
		listDistritos = new ArrayList<GenDistrito>();
		hectareas = new BigDecimal(0);
		kilometros = new BigDecimal(0);
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
	
	public GenZona getZona() {
		return zona;
	}
	
	public void setZona(GenZona zona) {
		this.zona = zona;
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
		setKilometros(distrito.getDisKilometros()); setObservacion(distrito.getDisObservacion());
		setNombre(distrito.getDisNombre());
		setEdicion(true);
		setZonaId(distrito.getGenZona().getZonId());
		setZona(distrito.getGenZona());
		setDirMapa(distrito.getDisLinkMapa());
		setDirPdf(distrito.getDisLinkPdf());
		
		return "neDistrito?faces-redirect=true";
	}
	
	public String guardarEditarDistrito(){
		try {
			String respuesta = "";
			if(getZonaId().equals(SELECT_ZONA)){
				Mensaje.crearMensajeWARN("Seleccione una zona");
				return respuesta;
			}else if(!isEdicion() && manager.findDistritoById(getId())!=null){
				Mensaje.crearMensajeWARN("Ya existe un distrito con el mismo id, favor cámbielo.");
				return respuesta;
			}else{
				GenDistrito d = new GenDistrito();
				d.setDisId(Funciones.quitarEspacios(getId()));
				d.setDisDescripcion(Funciones.quitarEspacios(getDescripcion()));
				d.setDisEstado(getEstado());
				d.setDisKilometros(getKilometros());
				d.setDisNombre(Funciones.quitarEspacios(getNombre()));
				d.setDisObservacion(Funciones.quitarEspacios(getObservacion()));
				d.setGenZona(manager.findZonaById(getZonaId()));
				if(isEdicion()){
					if(getDirMapa()!= null || getDirMapa() != ""){
						d.setDisLinkMapa(getDirMapa());
					}else {
						d.setDisLinkMapa(manager.findDistritoById(getId()).getDisLinkMapa());
					}
					if(getDirPdf() != null || getDirPdf() != ""){
						d.setDisLinkPdf(getDirPdf());
					} else{
						d.setDisLinkPdf(manager.findDistritoById(getId()).getDisLinkPdf());
					}
					manager.modicarDistrito(d);
					Mensaje.crearMensajeINFO("Distrito actualizado correctamente.");
				}else{
					manager.insertarDistrito(d);
					setEdicion(true);
					Mensaje.crearMensajeINFO("Distrito guardado correctamente.");
				}
				return respuesta;
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
		setHectareas(new BigDecimal(0));setKilometros(new BigDecimal(0));
		setLinkMapa(null);setLinkPdf(null);setNombre(null); setObservacion(null);
		setEdicion(false);setZonaId(SELECT_ZONA);
	}
	
	public void cargaMapa(FileUploadEvent event) throws IOException {
		fileMapa = event.getFile();
		InputStream inputStream = null;
		OutputStream outputStream = null;

		if (fileMapa != null) {
			try {
				
				String carpeta = manager.findParametroByID("direccion_mapa") + "/";
				System.out.println("nombre del archivo --> "+ fileMapa.getFileName());
					setDirMapa("Mapa_D_"+getId()+extensionArchivo(fileMapa.getFileName()));
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
					editarDistrito();
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
					setDirPdf("PDF_D_"+getId() + extensionArchivo(filePdf.getFileName()));
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
					editarDistrito();
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

	
	public void editarDistrito(){
		try {	
			GenDistrito dis= new GenDistrito();
			dis.setDisId(Funciones.quitarEspacios(getId()));
			dis.setDisNombre(Funciones.quitarEspacios(getNombre()));
			dis.setDisDescripcion(Funciones.quitarEspacios(getDescripcion()));
			dis.setDisObservacion(Funciones.quitarEspacios(getObservacion()));
			dis.setDisKilometros(getKilometros());
			dis.setDisLinkMapa(getDirMapa());
			dis.setDisLinkPdf(getDirPdf());
			dis.setDisEstado(getEstado());
			System.out.println("id ---> "+getZonaId());
			dis.setGenZona(manager.findZonaById(getZonaId()));
			manager.modicarDistrito(dis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void descargarMapa(String idDistrito) {
		try {
			GenDistrito distrito = manager.findDistritoById(idDistrito);
			if (distrito.getDisLinkMapa() == null
					|| distrito.getDisLinkMapa().isEmpty()) {
				Mensaje.crearMensajeERROR("El distrito no cuenta con un archivo asignado.");
			} else {
				String contextPath = manager
						.findParametroByID("direccion_mapa") + File.separatorChar
						+ distrito.getDisLinkMapa() + "";
				Funciones.descargarPDF(contextPath);
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error: "+e.getMessage());
			e.printStackTrace();
		}
	}
	public void descargarPDF(String idDistrito) {
		try {
			GenDistrito distrito = manager.findDistritoById(idDistrito);
			if (distrito.getDisLinkPdf() == null
					|| distrito.getDisLinkPdf().isEmpty()) {
				Mensaje.crearMensajeERROR("El distrito no cuenta con un archivo asignado.");
			} else {
				String contextPath = manager
						.findParametroByID("direccion_pdf") + File.separatorChar
						+ distrito.getDisLinkPdf() + "";
				Funciones.descargarPDF(contextPath);
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public BigDecimal suma(GenDistrito dis){
		BigDecimal sum = BigDecimal.ZERO;
		List<GenBarrio> lst = manager.findBarriosByDistrito(dis.getDisId());
		for (GenBarrio genBarrio : lst) {
			if(genBarrio.getBarHectareas() != null ){
				sum = sum.add(genBarrio.getBarHectareas());
			}
		}
		return sum;
	}
	
	public BigDecimal sumaHectareas(String disId){
		return manager.sumHectareasDistrito(disId);
	}
	
}
