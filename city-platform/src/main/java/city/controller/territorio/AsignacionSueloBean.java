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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GenAsignacionSuelo;
import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenZona;
import city.model.generic.Funciones;
import city.model.generic.Mensaje;
import city.model.manager.ManagerSitios;
import city.model.manager.ManagerTerritorio;

@SessionScoped
@ManagedBean
public class AsignacionSueloBean implements Serializable {

	private static final long serialVersionUID = -3198725647396964268L;
	private static String ID_ACTIVO = "A";
	private static String ID_INACTIVO = "I";

	@EJB
	private ManagerTerritorio mngAsignacionSuelo;
	@Inject
	private SesionBean session;

	@NotEmpty(message = "ID no debe estar vacío.")
	@NotBlank(message = "ID no debe ser solo espacios blancos.")
	private Integer id;
	private String estado;

	private Date fechaIncio;
	private Date fechaFin;

	@NotEmpty(message = "PDF LINK no debe estar vacío.")
	@NotBlank(message = "PDF LINK no debe ser solo espacios blancos.")
	@URL(message = "PDF LINK no es una url válida.")
	private String linkPdf;

	@DecimalMin("1")
	private BigDecimal metros;

	@NotEmpty(message = "ACTIVIDAD no debe estar vacío.")
	@NotBlank(message = "ACTIVIDAD no debe ser solo espacios blancos.")
	private String actividad;

	@NotEmpty(message = "ASIGNACIÓN no debe estar vacío.")
	@NotBlank(message = "ASIGNACIÓN no debe ser solo espacios blancos.")
	private String asignacion;

	private List<GenAsignacionSuelo> listAsignacionSuelos;
	private boolean edicion;
	private List<SelectItem> slctEstados;

	@NotEmpty(message = "OBSERVACIÓN no debe estar vacío.")
	@NotBlank(message = "OBSERVACIÓN no debe ser solo espacios blancos.")
	private String observacion;

	private UploadedFile filePdf;
	private String dirPdf;

	List<SelectItem> l_zona;
	private String zona;

	List<SelectItem> l_tipo_catalogo;

	@NotEmpty(message = "TIPO no debe estar vacío.")
	private String SueTipoCatalogo;

	private Date date;
	
	private String numanios;

	@EJB
	private ManagerSitios msitios;

	@PostConstruct
	public void init() {
		session.validarSesion();
		estado = ID_ACTIVO;
		slctEstados = new ArrayList<SelectItem>();
		listAsignacionSuelos = new ArrayList<GenAsignacionSuelo>();
		l_tipo_catalogo = new ArrayList<SelectItem>();
		l_zona = new ArrayList<SelectItem>();
		metros = new BigDecimal(0);
		cargarZona();
		edicion = false;
		cargarAsignacionSuelo();
		cargarTiposCatalogo();
		fechaIncio = new Date();
		fechaFin = new Date();
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

	public List<SelectItem> getL_tipo_catalogo() {
		return l_tipo_catalogo;
	}

	public void setL_tipo_catalogo(List<SelectItem> l_tipo_catalogo) {
		this.l_tipo_catalogo = l_tipo_catalogo;
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

	public List<SelectItem> getL_zona() {
		return l_zona;
	}

	public void setL_zona(List<SelectItem> l_zona) {
		this.l_zona = l_zona;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getSueTipoCatalogo() {
		return SueTipoCatalogo;
	}

	public void setSueTipoCatalogo(String sueTipoCatalogo) {
		SueTipoCatalogo = sueTipoCatalogo;
	}

	private void cargarEstados() {
		getSlctEstados().add(new SelectItem(ID_ACTIVO, "Activo"));
		getSlctEstados().add(new SelectItem(ID_INACTIVO, "Inactivo"));
	}

	public UploadedFile getFilePdf() {
		return filePdf;
	}

	public void setFilePdf(UploadedFile filePdf) {
		this.filePdf = filePdf;
	}

	public String getDirPdf() {
		return dirPdf;
	}

	public void setDirPdf(String dirPdf) {
		this.dirPdf = dirPdf;
	}

	public Date getDate() {
		date = new Date();
		return date;
	}
	
	public String getNumanios() {
		return numanios;
	}
	
	public void setNumanios(String numanios) {
		this.numanios = numanios;
	}

	public String nuevoSuelo() {
		limpiarDatos();

		date = new Date();
		return "neAsignacionSuelo?faces-redirect=true";
	}

	public String cargarEditarAsignacionSuelo(GenAsignacionSuelo asignacionSuelo) {
		setId(asignacionSuelo.getSueId());
		setActividad(asignacionSuelo.getSueActividad());
		setEstado(asignacionSuelo.getSueEstado());
		setMetros(asignacionSuelo.getSueMetros());
		setSueTipoCatalogo(asignacionSuelo.getSueTipo());
		setAsignacion(asignacionSuelo.getSueAsignacion());
		setObservacion(asignacionSuelo.getSueObservacion());
		setFechaIncio(asignacionSuelo.getSueFechaInicio());
		setFechaFin(asignacionSuelo.getSueFechaFin());
		setZona(asignacionSuelo.getGenZona().getZonId());
		setEdicion(true);
		numanios=fechasaanios(asignacionSuelo.getSueFechaInicio(), asignacionSuelo.getSueFechaFin());
		date = new Date();
		return "neAsignacionSuelo?faces-redirect=true";
	}

	public String guardarEditarAsignacionSuelos() {
		try {
			String respuesta = "";
			if (!validarCampos()) {
				GenAsignacionSuelo z = new GenAsignacionSuelo();
				z.setSueId(mngAsignacionSuelo.asignacionSueloId());
				z.setSueActividad(Funciones.quitarEspacios(getActividad()));
				z.setGenZona(mngAsignacionSuelo.findZonaById(zona));
				z.setSueEstado(getEstado());
				z.setSueTipo(getSueTipoCatalogo());
				z.setSueMetros(getMetros());
				z.setSueFechaInicio(fechaIncio);
				z.setSueFechaFin(fechaFin);
				z.setSueAsignacion(Funciones.quitarEspacios(getAsignacion()));
				z.setSueObservacion(Funciones.quitarEspacios(getObservacion()));
				if (isEdicion()) {
					if (getDirPdf() != null || getDirPdf() != "") {
						z.setSueArchivo(getDirPdf());
					} else {
						z.setSueArchivo(mngAsignacionSuelo.findAsignacionSueloById(getId()).getSueArchivo());
					}
					mngAsignacionSuelo.modicarAsignacionSuelo(z);
					Mensaje.crearMensajeINFO("Asignacion Suelo actualizada correctamente.");
				} else {
					mngAsignacionSuelo.insertarAsignacionSuelo(z);
					setEdicion(true);
					Mensaje.crearMensajeINFO("Asignacion Suelo ingresada correctamente.");
				}
			}
			return respuesta;
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al almacenar suelo: " + e.getMessage());
			System.out.println("Error al almacenar suelo: ");
			e.printStackTrace();
			return "";
		}
	}

	public boolean validarCampos() {
		if (getZona() == null || getZona() == "") {
			Mensaje.crearMensajeERROR("Campo zona requerido");
			return true;
		} else {
			if ((edicion == true && getEstado() == null) || (edicion == true && getEstado().equals("-1"))) {
				Mensaje.crearMensajeERROR("Campo estado requerido");
				return true;
			}
			;
			return false;
		}
	}

	public String cancelar() {
		limpiarDatos();
		cargarAsignacionSuelo();
		date = new Date();
		return "asignacionSuelos?faces-redirect=true";
	}

	private void cargarAsignacionSuelo() {
		getListAsignacionSuelos().clear();
		getListAsignacionSuelos().addAll(mngAsignacionSuelo.findAllAsignacionSuelo());
	}

	private void limpiarDatos() {
		setId(null);
		setActividad("");
		setEstado(ID_ACTIVO);
		setMetros(new BigDecimal(0));
		setAsignacion("");
		setZona("");
		setSueTipoCatalogo("");
		setObservacion("");
		setFechaIncio(new Date());
		setFechaFin(new Date());
		date = new Date();
		setEdicion(false);
	}

	public void cargaPDF(FileUploadEvent event) throws IOException {
		filePdf = event.getFile();
		InputStream inputStream = null;
		OutputStream outputStream = null;

		if (filePdf != null) {
			try {
				String carpeta = mngAsignacionSuelo.findParametroByID("direccion_pdf") + "/";
				setDirPdf("PDF_S_" + getId() + extensionArchivo(filePdf.getFileName()));
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
				editarAsignacionSuelo();
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

	public String extensionArchivo(String nombreArchivo) {
		return nombreArchivo.substring(nombreArchivo.lastIndexOf('.'));
	}

	public void editarAsignacionSuelo() {
		try {
			GenAsignacionSuelo z = new GenAsignacionSuelo();
			z.setSueId(getId());
			z.setSueActividad(Funciones.quitarEspacios(getActividad()));
			z.setSueMetros(getMetros());
			z.setGenZona(mngAsignacionSuelo.findZonaById(zona));
			z.setSueFechaInicio(fechaIncio);
			z.setSueTipo(getSueTipoCatalogo());
			z.setSueFechaFin(fechaFin);
			z.setSueAsignacion(Funciones.quitarEspacios(getAsignacion()));
			z.setSueObservacion(Funciones.quitarEspacios(getObservacion()));
			z.setSueEstado(getEstado());
			if (getDirPdf() != null || getDirPdf() != "") {
				z.setSueArchivo(getDirPdf());
			} else {
				z.setSueArchivo(mngAsignacionSuelo.findAsignacionSueloById(getId()).getSueArchivo());
			}
			mngAsignacionSuelo.modicarAsignacionSuelo(z);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void descargarPDF(GenAsignacionSuelo asignacionSuelo) {
		try {
			if (asignacionSuelo.getSueArchivo() == null || asignacionSuelo.getSueArchivo().isEmpty()) {
				Mensaje.crearMensajeERROR("La asignacion de suelo no cuenta con un archivo asignado.");
			} else {
				String contextPath = mngAsignacionSuelo.findParametroByID("direccion_pdf") + File.separatorChar
						+ asignacionSuelo.getSueArchivo() + "";
				Funciones.descargarPDF(contextPath);
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void descargarPDF(Integer idasignacionSuelo) {
		try {
			GenAsignacionSuelo asignacionSuelo = mngAsignacionSuelo.findAsignacionSueloById(idasignacionSuelo);
			if (asignacionSuelo.getSueArchivo() == null || asignacionSuelo.getSueArchivo().isEmpty()) {
				Mensaje.crearMensajeERROR("La asignacion de suelo no cuenta con un archivo asignado.");
			} else {
				String contextPath = mngAsignacionSuelo.findParametroByID("direccion_pdf") + File.separatorChar
						+ asignacionSuelo.getSueArchivo() + "";
				Funciones.descargarPDF(contextPath);
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Lista de Zona
	 */
	public void cargarZona() {
		getL_zona().clear();
		for (GenZona i : mngAsignacionSuelo.findAllZonasA()) {
			getL_zona().add(new SelectItem(i.getZonId(), i.getZonNombre()));
		}
	}

	/**
	 * Lista de TiposCatalogo
	 */
	public void cargarTiposCatalogo() {
		getL_tipo_catalogo().clear();
		List<GenCatalogoItemsDet> completo = mngAsignacionSuelo.AllofItems("cat_tipo_asignacionsuelo");
		for (GenCatalogoItemsDet i : completo) {
			getL_tipo_catalogo().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Validar campos
	 * 
	 * @param item
	 * @return
	 */
	public String validarItemCatalogo(String item) {
		String respuesta = "";
		try {
			respuesta = mngAsignacionSuelo.catalogoItem(item);

		} catch (Exception e) {
			e.printStackTrace();
			Mensaje.crearMensajeERROR("Error al validar Item. " + e.getMessage());
		}
		return respuesta;
	}
	
	public String fechasaanios(Date fechaInicio, Date fechaFin){
		String aniosEntreFechas= mngAsignacionSuelo.fechasaAnios(getFechaIncio(), getFechaFin(), getId());
		return  aniosEntreFechas;
		
	}
}
