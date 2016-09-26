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
	private static String EN_PROGRESO = "P";
	private static String APROBADO = "A";

	@EJB
	private ManagerTerritorio mngAsignacionSuelo;
	@Inject
	private SesionBean session;

	@NotEmpty(message = "ID no debe estar vac�o.")
	@NotBlank(message = "ID no debe ser solo espacios blancos.")
	private Integer id;

	private String estado;

	private Date fechaIncio;
	private Date fechaFin;

	@NotEmpty(message = "PDF LINK no debe estar vac�o.")
	@NotBlank(message = "PDF LINK no debe ser solo espacios blancos.")
	@URL(message = "PDF LINK no es una url v�lida.")
	private String linkPdf;

	@DecimalMin("0")
	private BigDecimal metros;

	@NotEmpty(message = "ACTIVIDAD no debe estar vac�o.")
	@NotBlank(message = "ACTIVIDAD no debe ser solo espacios blancos.")
	private String actividad;

	@NotEmpty(message = "ASIGNACI�N no debe estar vac�o.")
	@NotBlank(message = "ASIGNACI�N no debe ser solo espacios blancos.")
	private String asignacion;

	private List<GenAsignacionSuelo> listAsignacionSuelos;
	private boolean edicion;
	private List<SelectItem> slctEstados;

	private String observacion;

	private UploadedFile filePdf;
	private String dirPdf;

	List<SelectItem> l_zona;

	@NotEmpty(message = "ZONA no debe estar vac�o.")
	private String zona;

	List<SelectItem> l_tipo_catalogo;

	@NotEmpty(message = "TIPO no debe estar vac�o.")
	private String SueTipoCatalogo;

	private Date date;

	private Integer sueNumeroanios;

	@EJB
	private ManagerSitios msitios;

	@PostConstruct
	public void init() {
		session.validarSesion();
		estado = EN_PROGRESO;
		slctEstados = new ArrayList<SelectItem>();
		listAsignacionSuelos = new ArrayList<GenAsignacionSuelo>();
		l_tipo_catalogo = new ArrayList<SelectItem>();
		l_zona = new ArrayList<SelectItem>();
		metros = new BigDecimal(0);
		sueNumeroanios = 0;
		cargarZona();
		edicion = false;
		cargarAsignacionSuelo();
		cargarTiposCatalogo();
		fechaIncio = null;
		fechaFin = null;
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
		getSlctEstados().add(new SelectItem(EN_PROGRESO, "En progreso"));
		getSlctEstados().add(new SelectItem(APROBADO, "Aprobado"));
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
		return date;
	}

	public Integer getSueNumeroanios() {
		return sueNumeroanios;
	}

	public void setSueNumeroanios(Integer sueNumeroanios) {
		this.sueNumeroanios = sueNumeroanios;
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
		setSueNumeroanios(asignacionSuelo.getSueNumeroAnios());
		setObservacion(asignacionSuelo.getSueObservacion());
		setFechaIncio(asignacionSuelo.getSueFechaInicio());
		setFechaFin(asignacionSuelo.getSueFechaFin());
		setZona(asignacionSuelo.getGenZona().getZonId());
		setEdicion(true);
		// fechasaanios(asignacionSuelo.getSueFechaInicio(),
		// asignacionSuelo.getSueFechaFin());
		return "neAsignacionSuelo?faces-redirect=true";
	}

	public String guardarEditarAsignacionSuelos() {
		try {
			String respuesta = "";
			if (!validarCampos()) {
				if (isEdicion()) {
					GenAsignacionSuelo a = mngAsignacionSuelo.findAsignacionSueloById(getId());
					a.setSueActividad(Funciones.quitarEspacios(getActividad()));
					a.setGenZona(mngAsignacionSuelo.findZonaById(zona));
					a.setSueEstado(getEstado());
					a.setSueTipo(getSueTipoCatalogo());
					a.setSueMetros(getMetros());
					a.setSueFechaInicio(fechaIncio);
					a.setSueNumeroAnios(sueNumeroanios);
					a.setSueFechaFin(fechaFin);
					a.setSueAsignacion(Funciones.quitarEspacios(getAsignacion()));
					a.setSueObservacion(Funciones.quitarEspacios(getObservacion()));
					if (getDirPdf() != null || getDirPdf() != "") {
						a.setSueArchivo(getDirPdf());
					} else {
						a.setSueArchivo(mngAsignacionSuelo.findAsignacionSueloById(getId()).getSueArchivo());
					}
					mngAsignacionSuelo.modicarAsignacionSuelo(a);
					Mensaje.crearMensajeINFO("Asignaciòn de Suelo actualizada correctamente.");
				} else {
					GenAsignacionSuelo z = new GenAsignacionSuelo();
					z.setSueId(mngAsignacionSuelo.asignacionSueloId());
					z.setSueActividad(Funciones.quitarEspacios(getActividad()));
					z.setGenZona(mngAsignacionSuelo.findZonaById(zona));
					z.setSueEstado(getEstado());
					z.setSueTipo(getSueTipoCatalogo());
					z.setSueMetros(getMetros());
					z.setSueFechaInicio(fechaIncio);
					z.setSueNumeroAnios(sueNumeroanios);
					z.setSueFechaFin(fechaFin);
					z.setSueAsignacion(Funciones.quitarEspacios(getAsignacion()));
					z.setSueObservacion(Funciones.quitarEspacios(getObservacion()));
					mngAsignacionSuelo.insertarAsignacionSuelo(z);
					setEdicion(false);
					Mensaje.crearMensajeINFO("Asignaciòn de Suelo ingresada correctamente.");
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
		if (getZona().equals("-1")) {
			Mensaje.crearMensajeERROR("Campo zona requerido");
			return true;
		} else if (getSueTipoCatalogo().equals("-1")) {
			Mensaje.crearMensajeERROR("Campo tipo requerido");
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
		return "asignacionSuelos?faces-redirect=true";
	}

	private void cargarAsignacionSuelo() {
		getListAsignacionSuelos().clear();
		getListAsignacionSuelos().addAll(mngAsignacionSuelo.findAllAsignacionSuelo());
	}

	private void limpiarDatos() {
		setId(null);
		setActividad("");
		setEstado(EN_PROGRESO);
		setMetros(new BigDecimal(0));
		setAsignacion("");
		setSueNumeroanios(0);
		setZona("-1");
		setSueTipoCatalogo("-1");
		setSueNumeroanios(0);
		setObservacion("");
		setFechaIncio(null);
		setFechaFin(null);
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
			if (!validarCampos()) {
				GenAsignacionSuelo z = new GenAsignacionSuelo();
				z.setSueId(getId());
				z.setSueActividad(Funciones.quitarEspacios(getActividad()));
				z.setSueMetros(getMetros());
				z.setGenZona(mngAsignacionSuelo.findZonaById(zona));
				z.setSueFechaInicio(fechaIncio);
				z.setSueNumeroAnios(sueNumeroanios);
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
			} else {

			}
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
				Mensaje.crearMensajeERROR("La asignaciòn de suelo no cuenta con un archivo asignado.");
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

	public String fechasaanios(Date fechaInicio, Date fechaFin) {
		String aniosEntreFechas = mngAsignacionSuelo.fechasaAnios(getFechaIncio(), getFechaFin(), getId());
		return aniosEntreFechas;

	}
}
