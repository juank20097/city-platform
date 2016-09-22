package city.controller.territorio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import org.hibernate.validator.internal.util.privilegedactions.SetAccessibility;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GenAsignacionSuelo;
import city.model.dao.entidades.GenBarrio;
import city.model.dao.entidades.GenZona;
import city.model.generic.Funciones;
import city.model.generic.Mensaje;
import city.model.manager.ManagerTerritorio;

@SessionScoped
@ManagedBean
public class AsignacionSuelo implements Serializable{


	private static final long serialVersionUID = -3198725647396964268L;
	private static String ID_ACTIVO = "A"; 
	private static String ID_INACTIVO = "I"; 

	@EJB
	private ManagerTerritorio mngAsignacionSuelo;
	@Inject
	private SesionBean session;
	
	@NotEmpty(message="ID no debe estar vacío.")
	@NotBlank(message="ID no debe ser solo espacios blancos.")
	private Integer id;
	private String estado;
	
	private Date fechaIncio;
	private Date fechaFin;
	
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
	private BigDecimal metros;
	
	@NotEmpty(message="ACTIVIDAD no debe estar vacío.")
	@NotBlank(message="ACTIVIDAD no debe ser solo espacios blancos.")
	private String actividad;
	
	@NotEmpty(message="ASIGNACIÓN no debe estar vacío.")
	@NotBlank(message="ASIGNACIÓN no debe ser solo espacios blancos.")
	private String asignacion;
	
	private List<GenAsignacionSuelo> listAsignacionSuelos;
	private boolean edicion;
	private List<SelectItem> slctEstados;
	
	@NotEmpty(message="OBSERVACIÓN no debe estar vacío.")
	@NotBlank(message="OBSERVACIÓN no debe ser solo espacios blancos.")
	private String observacion;
	
	private UploadedFile fileMapa;
	private UploadedFile filePdf;
	
	private String dirMapa; 
	private String dirPdf;
	
	@PostConstruct
	public void init(){
		session.validarSesion();
		estado = ID_ACTIVO;
		slctEstados = new ArrayList<SelectItem>();
		listAsignacionSuelos = new ArrayList<GenAsignacionSuelo>();
		hectareas = new BigDecimal(0);
		metros = new BigDecimal(0);
//		cargarZonas();
		cargarEstados();
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getActividad() {
		return actividad;
	}
	
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	
	public String getAsignacion() {
		return asignacion;
	}
	
	public void setAsignacion(String asignacion) {
		this.asignacion = asignacion;
	}
	
	public Date getFechaIncio() {
		return fechaIncio;
	}
	
	public void setFechaIncio(Date fechaIncio) {
		this.fechaIncio = fechaIncio;
	}
	
	public Date getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
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

	public BigDecimal getMetros() {
		return metros;
	}
	
	public void setMetros(BigDecimal metros) {
		this.metros = metros;
	}
	
	public List<GenAsignacionSuelo> getListAsignacionSuelos() {
		return listAsignacionSuelos;
	}
	
	public void setListAsignacionSuelos(List<GenAsignacionSuelo> listAsignacionSuelos) {
		this.listAsignacionSuelos = listAsignacionSuelos;
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

	private void cargarEstados() {
		getSlctEstados().add(new SelectItem(ID_ACTIVO,"Activo"));
		getSlctEstados().add(new SelectItem(ID_INACTIVO,"Inactivo"));
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

	public String nuevoSuelo(){
//		limpiarDatos();
		return "neAsignacionSuelo?faces-redirect=true";
	}
	
	public String cargarEditarSuelo(GenAsignacionSuelo asignacionSuelo){
		setId(asignacionSuelo.getSueId());
		setActividad(asignacionSuelo.getSueActividad());
		setEstado(asignacionSuelo.getSueEstado());
		setMetros(asignacionSuelo.getSueMetros());
		setAsignacion(asignacionSuelo.getSueAsignacion());
		setObservacion(asignacionSuelo.getSueObservacion()); 
		setEdicion(true);
		return "neAsignacionSuelo?faces-redirect=true";
	}
	
	public String guardarEditarSuelos() {
		try {
			String respuesta = "";
			if (!isEdicion() && mngAsignacionSuelo.findAsignacionSueloById(getId()) != null) {
				Mensaje.crearMensajeWARN("Ya existe una asignación de suelo con el mismo id, favor cámbielo.");
				return respuesta;
			} else {
					GenAsignacionSuelo z = new GenAsignacionSuelo();
					z.setSueId(getId());
					z.setSueActividad(Funciones.quitarEspacios(getActividad()));
					z.setSueEstado(getEstado());
					z.setSueMetros(getMetros());
					z.setSueAsignacion(Funciones.quitarEspacios(getAsignacion()));
					z.setSueObservacion(Funciones.quitarEspacios(getObservacion()));
					if (isEdicion()) {
						if(getDirPdf() != null || getDirPdf() != ""){
							z.setSueArchivo(getDirPdf());
						} else{
							z.setSueArchivo(mngAsignacionSuelo.findAsignacionSueloById(getId()).getSueArchivo());
						}
						mngAsignacionSuelo.modicarAsignacionSuelo(z);
						Mensaje.crearMensajeINFO("Asignacion Suelo actualizada correctamente.");
					} else {
						mngAsignacionSuelo.insertarAsignacionSuelo(z);
						setEdicion(true);
						Mensaje.crearMensajeINFO("Asignacion Suelo ingresada correctamente.");
					}
				return respuesta;
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al almacenar suelo: "
					+ e.getMessage());
			System.out.println("Error al almacenar suelo: ");
			e.printStackTrace();
			return "";
		}
	}
	
//	public String cancelar(){
//		limpiarDatos();
//		cargarZonas();
//		return "asignacionSuelos?faces-redirect=true";
//	}
	
//	private void cargarZonas() {
//		getListZonas().clear();
//		getListZonas().addAll(mngZona.findAllZonas());
//	}
//	
//	private void limpiarDatos(){
//		setId(null);setDescripcion(null);setEstado(ID_ACTIVO);
//		setKilometros(new BigDecimal(0));
//		setNombre(null); setObservacion(null);
//		setEdicion(false);
//	}
//	public void cargaMapa(FileUploadEvent event) throws IOException {
//		fileMapa = event.getFile();
//		InputStream inputStream = null;
//		OutputStream outputStream = null;
//
//		if (fileMapa != null) {
//			try {
//				String carpeta = mngZona.findParametroByID("direccion_mapa") + "/";
//					setDirMapa("Mapa_Z_" +getId()+ extensionArchivo(fileMapa.getFileName()));
//					System.out.println("PAD------> " + carpeta);
//					System.out.println("name------> " + getDirMapa());
//					outputStream = new FileOutputStream(new File(carpeta + File.separatorChar + getDirMapa()));
//					inputStream = fileMapa.getInputstream();
//
//					int read = 0;
//					byte[] bytes = new byte[1024];
//
//					while ((read = inputStream.read(bytes)) != -1) {
//						outputStream.write(bytes, 0, read);
//					}
//					Mensaje.crearMensajeINFO("Carga del archivo Correcta");
//					editarZona();
//			} catch (Exception e) {
//				Mensaje.crearMensajeERROR("No se pudo cargar el archivo");
//				e.printStackTrace();
//			} finally {
//				if (inputStream != null) {
//					inputStream.close();
//				}
//				if (outputStream != null) {
//					outputStream.close();
//				}
//			}
//		} else {
//			Mensaje.crearMensajeWARN("No se pudo cargar el archivo");
//		}
//	}
//	
//	public void cargaPDF(FileUploadEvent event) throws IOException {
//		filePdf = event.getFile();
//		InputStream inputStream = null;
//		OutputStream outputStream = null;
//
//		if (filePdf != null) {
//			try {
//				
//				String carpeta = mngZona.findParametroByID("direccion_pdf") + "/";
//					setDirPdf("PDF_Z_" +getId()+extensionArchivo(filePdf.getFileName()));
//					System.out.println("PAD------> " + carpeta);
//					System.out.println("name------> " + getDirPdf());
//					outputStream = new FileOutputStream(new File(carpeta + File.separatorChar + getDirPdf()));
//					inputStream = filePdf.getInputstream();
//
//					int read = 0;
//					byte[] bytes = new byte[1024];
//
//					while ((read = inputStream.read(bytes)) != -1) {
//						outputStream.write(bytes, 0, read);
//					}
//					Mensaje.crearMensajeINFO("Carga del archivo Correcta");
//					editarZona();
////				}
//			} catch (Exception e) {
//				Mensaje.crearMensajeERROR("No se pudo cargar el archivo");
//				e.printStackTrace();
//			} finally {
//				if (inputStream != null) {
//					inputStream.close();
//				}
//				if (outputStream != null) {
//					outputStream.close();
//				}
//			}
//		} else {
//			Mensaje.crearMensajeWARN("No se pudo cargar el archivo");
//		}
//	}
//	
//	public String extensionArchivo(String nombreArchivo){
//		return nombreArchivo.substring(nombreArchivo.lastIndexOf('.'));
//	}
//	
//	public void editarZona(){
//		try {
//			GenZona z = new GenZona();
//			z.setZonId(Funciones.quitarEspacios(getId()));
//			z.setZonNombre(Funciones.quitarEspacios(getNombre()));
//			z.setZonKilometros(getKilometros());
//			z.setZonDescripcion(Funciones.quitarEspacios(getDescripcion()));
//			z.setZonObservacion(Funciones.quitarEspacios(getObservacion()));
//			z.setZonEstado(getEstado());
//			if(getDirMapa()!= null || getDirMapa() != ""){
//				z.setZonLinkMapa(getDirMapa());
//			}else {
//				z.setZonLinkMapa(mngZona.findZonaById(getId()).getZonLinkMapa());
//			}
//			if(getDirPdf() != null || getDirPdf() != ""){
//				z.setZonLinkPdf(getDirPdf());
//			} else{
//				z.setZonLinkPdf(mngZona.findZonaById(getId()).getZonLinkPdf());
//			}
//			mngZona.modicarZona(z);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void descargarMapa(GenZona zona) {
//		try {
//			if (zona.getZonLinkMapa() == null
//					|| zona.getZonLinkMapa().isEmpty()) {
//				Mensaje.crearMensajeERROR("La zona no cuenta con un archivo asignado.");
//			} else {
//				String contextPath = mngZona
//						.findParametroByID("direccion_mapa") + File.separatorChar 
//						+ zona.getZonLinkMapa() + "";
//				Funciones.descargarPDF(contextPath);
//			}
//		} catch (Exception e) {
//			Mensaje.crearMensajeERROR("Error: "+e.getMessage());
//			e.printStackTrace();
//		}
//	}
//	
//	public void descargarMapa(String idZona) {
//		try {
//			GenZona zona = mngZona.findZonaById(idZona);
//			if (zona.getZonLinkMapa() == null
//					|| zona.getZonLinkMapa().isEmpty()) {
//				Mensaje.crearMensajeERROR("La zona no cuenta con un archivo asignado.");
//			} else {
//				String contextPath = mngZona
//						.findParametroByID("direccion_mapa") + File.separatorChar 
//						+ zona.getZonLinkMapa() + "";
//				Funciones.descargarPDF(contextPath);
//			}
//		} catch (Exception e) {
//			Mensaje.crearMensajeERROR("Error: "+e.getMessage());
//			e.printStackTrace();
//		}
//	}
//	public void descargarPDF(GenZona zona) {
//		try {
//			if (zona.getZonLinkPdf() == null
//					|| zona.getZonLinkPdf().isEmpty()) {
//				Mensaje.crearMensajeERROR("La zona no cuenta con un archivo asignado.");
//			} else {
//				String contextPath = mngZona
//						.findParametroByID("direccion_pdf") + File.separatorChar
//						+ zona.getZonLinkPdf() + "";
//				Funciones.descargarPDF(contextPath);
//			}
//		} catch (Exception e) {
//			Mensaje.crearMensajeERROR("Error: "+e.getMessage());
//			e.printStackTrace();
//		}
//	}
//	
//	public void descargarPDF(String idZona) {
//		try {
//			GenZona zona = mngZona.findZonaById(idZona);
//			if (zona.getZonLinkPdf() == null
//					|| zona.getZonLinkPdf().isEmpty()) {
//				Mensaje.crearMensajeERROR("La zona no cuenta con un archivo asignado.");
//			} else {
//				String contextPath = mngZona
//						.findParametroByID("direccion_pdf") + File.separatorChar
//						+ zona.getZonLinkPdf() + "";
//				Funciones.descargarPDF(contextPath);
//			}
//		} catch (Exception e) {
//			Mensaje.crearMensajeERROR("Error: "+e.getMessage());
//			e.printStackTrace();
//		}
//	}
//	
//	public BigDecimal suma(GenZona zona){
//		List<GenBarrio> lst = mngZona.findBarriosByZona(zona.getZonId());
//		BigDecimal sum = BigDecimal.ZERO;
//		for (GenBarrio genBarrio : lst) {
//			sum = sum.add(genBarrio.getBarHectareas());
//		}
//		return sum;
//	}
//	
//	public BigDecimal sumaHectareas(String zona){
//		return mngZona.sumHectareasZona(zona);
//	}
}
