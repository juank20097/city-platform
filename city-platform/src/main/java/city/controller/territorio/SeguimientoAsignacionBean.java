package city.controller.territorio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GenAsignacionSuelo;
import city.model.dao.entidades.GenFuncionariosInstitucion;
import city.model.dao.entidades.GenHistorialSeguimiento;
import city.model.dao.entidades.GenHistorialSeguimientoPK;
import city.model.generic.Funciones;
import city.model.generic.Mensaje;
import city.model.manager.ManagerSeguridad;
import city.model.manager.ManagerTerritorio;

@SessionScoped
@ManagedBean
public class SeguimientoAsignacionBean implements Serializable {

	private static final long serialVersionUID = -95714538625600440L;
	private static String ID_ACTIVO = "A";
	private static String ID_INACTIVO = "I";
	private static String SELECCIONAR = "S/N";
	private static int ID_INICIAL = 0;
	private static String EN_PROGRESO = "P";
	

	@EJB
	private ManagerTerritorio manager;
	
	@EJB
	private ManagerSeguridad mngSeguridad;

	@Inject
	private SesionBean session;

	private String usuario;
	private String usuarioSesion;
	private String cedulaResponsable;
	private String busquedaP;
	private String busquedaAS;
	private String nombreResponsable;
	private List<SelectItem> lstAsignacion;
	private List<SelectItem> slctEstados;
	private List<SelectItem> lstPersonas;
	private boolean edicion;
	private int idAsignacion;
	private String asignacion;
	private int idSeguimiento;
	private Timestamp fechaRegistro;
	private Date fechaSeguimiento; 
	
	@NotEmpty(message = "NOVEDADES no debe estar vacío.")
	@NotBlank(message = "NOVEDADES no debe ser solo espacios blancos.")
	private String novedades;
	private String estado;
	private List<GenHistorialSeguimiento> lstSeguimiento;
	private GenAsignacionSuelo asignacionSuelo;
	private GenHistorialSeguimiento seguimiento;
	private GenHistorialSeguimientoPK pkSeguimiento;
	private String dirAdjuntoDoc;
	private String dirAdjuntoFoto;
	private boolean asignacionSeleccionada;
	
	private UploadedFile fileDoc;
	private UploadedFile fileFoto;

	@PostConstruct
	public void init() {
		session.validarSesion();
		usuarioSesion = session.validarSesion();
		lstAsignacion = new ArrayList<SelectItem>();
		lstSeguimiento = new ArrayList<GenHistorialSeguimiento>();
		lstPersonas = new ArrayList<SelectItem>();
		slctEstados = new ArrayList<SelectItem>();
		idAsignacion = ID_INICIAL;
		estado = ID_ACTIVO;
		fechaRegistro = new Timestamp(new Date().getTime());
		fechaSeguimiento = new Date();
		cargarLstAsignacion();
		cargarEstados();
		cargarBusqueda();
		
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuarioSesion() {
		return usuarioSesion;
	}
	
	public void setUsuarioSesion(String usuarioSesion) {
		this.usuarioSesion = usuarioSesion;
	}
	
	public String getCedulaResponsable() {
		return cedulaResponsable;
	}
	
	public void setCedulaResponsable(String cedulaResponsable) {
		this.cedulaResponsable = cedulaResponsable;
	}

	public String getBusquedaP() {
		return busquedaP;
	}
	
	public void setBusquedaP(String busquedaP) {
		this.busquedaP = busquedaP;
	}
	
	public String getBusquedaAS() {
		return busquedaAS;
	}
	
	public void setBusquedaAS(String busquedaAS) {
		this.busquedaAS = busquedaAS;
	}
	
	public String getNombreResponsable() {
		return nombreResponsable;
	}
	
	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}
	
	public List<SelectItem> getLstAsignacion() {
		return lstAsignacion;
	}

	public void setLstAsignacion(List<SelectItem> lstAsignacion) {
		this.lstAsignacion = lstAsignacion;
	}

	public List<SelectItem> getSlctEstados() {
		return slctEstados;
	}
	
	public void setSlctEstados(List<SelectItem> slctEstados) {
		this.slctEstados = slctEstados;
	}
	
	public List<SelectItem> getLstPersonas() {
		return lstPersonas;
	}
	
	public void setLstPersonas(List<SelectItem> lstPersonas) {
		this.lstPersonas = lstPersonas;
	}
	
	public boolean isEdicion() {
		return edicion;
	}

	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}
	
	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	public Date getFechaSeguimiento() {
		return fechaSeguimiento;
	}
	
	public void setFechaSeguimiento(Date fechaSeguimiento) {
		this.fechaSeguimiento = fechaSeguimiento;
	}
	
	public int getIdAsignacion() {
		return idAsignacion;
	}

	public void setIdAsignacion(int idAsignacion) {
		this.idAsignacion = idAsignacion;
	}

	public String getAsignacion() {
		return asignacion;
	}
	
	public void setAsignacion(String asignacion) {
		this.asignacion = asignacion;
	}
	
	public int getIdSeguimiento() {
		return idSeguimiento;
	}
	
	public void setIdSeguimiento(int idSeguimiento) {
		this.idSeguimiento = idSeguimiento;
	}
	
	public String getNovedades() {
		return novedades;
	}
	
	public void setNovedades(String novedades) {
		this.novedades = novedades;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public List<GenHistorialSeguimiento> getLstSeguimiento() {
		return lstSeguimiento;
	}
	
	public void setLstSeguimiento(List<GenHistorialSeguimiento> lstSeguimiento) {
		this.lstSeguimiento = lstSeguimiento;
	}
	
	public GenAsignacionSuelo getAsignacionSuelo() {
		return asignacionSuelo;
	}
	
	public void setAsignacionSuelo(GenAsignacionSuelo asignacionSuelo) {
		this.asignacionSuelo = asignacionSuelo;
	}
	
	public GenHistorialSeguimiento getSeguimiento() {
		return seguimiento;
	}
	
	public void setSeguimiento(GenHistorialSeguimiento seguimiento) {
		this.seguimiento = seguimiento;
	}
	
	public GenHistorialSeguimientoPK getPkSeguimiento() {
		return pkSeguimiento;
	}
	
	public void setPkSeguimiento(GenHistorialSeguimientoPK pkSeguimiento) {
		this.pkSeguimiento = pkSeguimiento;
	}
	
	public String getDirAdjuntoDoc() {
		return dirAdjuntoDoc;
	}
	
	public void setDirAdjuntoDoc(String dirAdjuntoDoc) {
		this.dirAdjuntoDoc = dirAdjuntoDoc;
	}
	
	public String getDirAdjuntoFoto() {
		return dirAdjuntoFoto;
	}
	
	public void setDirAdjuntoFoto(String dirAdjuntoFoto) {
		this.dirAdjuntoFoto = dirAdjuntoFoto;
	}
	
	public boolean isAsignacionSeleccionada() {
		return asignacionSeleccionada;
	}

	public void setAsignacionSeleccionada(boolean asignacionSeleccionada) {
		this.asignacionSeleccionada = asignacionSeleccionada;
	}

	private void cargarEstados() {
		getSlctEstados().add(new SelectItem(ID_ACTIVO, "Activo"));
		getSlctEstados().add(new SelectItem(ID_INACTIVO, "Inactivo"));
	}
	
	private void cargarLstAsignacion() {
		getLstAsignacion().clear();
		getLstAsignacion().add(new SelectItem(SELECCIONAR, "Seleccionar"));
		for (GenAsignacionSuelo as : manager.findAllAsignacionSuelo()) {
			getLstAsignacion().add(
					new SelectItem(as.getSueId(),as.getGenZona().getZonNombre()+" | "+ as.getSueAsignacion()));
		}
		System.out.println(manager.findAllAsignacionSuelo().size());
	}
	
	public void cargarBusqueda() {
		try {
			getLstPersonas().add(new SelectItem(SELECCIONAR, "Seleccionar"));
			List<GenFuncionariosInstitucion> list = mngSeguridad.findAllfuncionarios();
			for (GenFuncionariosInstitucion i : mngSeguridad.findAllfuncionarios()) {
				getLstPersonas().add(new SelectItem(i.getGenPersona().getPerDni(), i.getGenPersona().getPerDni() + " | "
						+ i.getGenPersona().getPerNombres() + " " + i.getGenPersona().getPerApellidos()));
			}
			System.out.println("fin metodo cargar busqueda");
			System.out.println("tamaño de la lista ---> "+list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void nuevoSeguimiento(){
		cargarBusqueda();
		RequestContext.getCurrentInstance().execute("PF('nSDlg').show();");
	}
	
	public void cargarEditarSeguimiento(GenHistorialSeguimiento seguimiento){
		setUsuario(seguimiento.getHseUsuario());
		if(!getUsuarioSesion().equals(getUsuario())){
			Mensaje.crearMensajeWARN("El Seguimiento puede ser editado unicamente por el usuario de creación.");
		}else{
			cargarBusqueda();
			setIdSeguimiento(seguimiento.getId().getHseId());
			setIdAsignacion(seguimiento.getId().getSueId());
			setFechaSeguimiento(seguimiento.getHseFecha());
			setFechaRegistro(seguimiento.getHseFechaRegistro());
			setDirAdjuntoDoc(seguimiento.getHseAdjuntoDoc());
			setDirAdjuntoFoto(seguimiento.getHseAdjuntoFot());
			setAsignacionSuelo(seguimiento.getGenAsignacionSuelo());
			setPkSeguimiento(seguimiento.getId());
			setEstado(seguimiento.getHseEstado());
			setNovedades(seguimiento.getHseNovedades());
			setCedulaResponsable(seguimiento.getHseResponsable());
			setNombreResponsable(obtenerNOmbre());
			setSeguimiento(seguimiento);
			
			setEdicion(true);
			RequestContext.getCurrentInstance().execute("PF('nSDlg').show();");
		}
	}
	public String obtenerNOmbre(){
		
		try {
			GenFuncionariosInstitucion funcionario;
			funcionario = manager.findFuncionarioXDni(getCedulaResponsable());
			return funcionario.getGenPersona().getPerNombres()+" "+funcionario.getGenPersona().getPerApellidos();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * Método para buscar un funcionario
	 */
	public void buscarPersona() {
		if (getBusquedaP() == null || getBusquedaP().isEmpty()) {
			Mensaje.crearMensajeWARN("Debe ingresar el dato para realizar la búsqueda.");
			setBusquedaP("");
			setCedulaResponsable("");
			setNombreResponsable("");
			
		} else {
			try {
				GenFuncionariosInstitucion f = manager.findFuncionarioXDni(getBusquedaP());
				if (f == null) {
					Mensaje.crearMensajeWARN("Persona no encontrada");
					setBusquedaP("");
					setCedulaResponsable("");
					setNombreResponsable("");
				} else {
					setBusquedaP("");
					setCedulaResponsable(f.getGenPersona().getPerDni());
					setNombreResponsable(f.getGenPersona().getPerNombres() + " " + f.getGenPersona().getPerApellidos());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void buscarAsignaciones() {
		if (getIdAsignacion() == ID_INICIAL ) {
			Mensaje.crearMensajeWARN("Debe ingresar el dato para realizar la búsqueda.");
			setIdAsignacion(ID_INICIAL);
			setAsignacion("");
			
		} else {
			try {
				GenAsignacionSuelo as = manager.findAsignacionSueloById(getIdAsignacion());
				if (as == null) {
					Mensaje.crearMensajeWARN("Asignación no encontrada");
					setIdAsignacion(ID_INICIAL);
					setAsignacion("");
				} else {
					setAsignacionSeleccionada(true);
					setIdAsignacion(as.getSueId());
					setAsignacionSuelo(as);
					setAsignacion(as.getSueAsignacion());
					cargarLstSeguimientoAs(getIdAsignacion());
					System.out.println("id -----> "+getIdAsignacion());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void cargarLstSeguimientoAs(int idAsignacion){
		getLstSeguimiento().clear();
		getLstSeguimiento().addAll(manager.findSeguimientoByIDAsignacion(idAsignacion));
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

	public void guardarEditarSeguimientoAsignacion() {
		try {
			if (!validarSelects()) {
				Mensaje.crearMensajeWARN("Se debe seleccionar todos los campos para continuar.");
			} else {
				GenHistorialSeguimiento seg = new GenHistorialSeguimiento();
				seg.setId(getPkSeguimiento());
				seg.setHseFecha(getFechaSeguimiento());
				seg.setHseNovedades(Funciones.quitarEspacios(getNovedades()).toUpperCase());
				seg.setHseResponsable(getCedulaResponsable());
				seg.setGenAsignacionSuelo(manager.findAsignacionSueloById(getIdAsignacion()));
				seg.setHseEstado(getEstado());

				if (isEdicion()) {
						if(seg.getHseAdjuntoDoc() != null || seg.getHseAdjuntoDoc() != ""){
							seg.setHseAdjuntoDoc(getDirAdjuntoDoc());
						}else {
							seg.setHseAdjuntoDoc(manager.findSeguimientoByID(getPkSeguimiento()).getHseAdjuntoDoc());
						}
						if(seg.getHseAdjuntoFot() != null || seg.getHseAdjuntoFot() != ""){
							seg.setHseAdjuntoFot(getDirAdjuntoFoto());
						}else{
							seg.setHseAdjuntoFot(manager.findSeguimientoByID(getPkSeguimiento()).getHseAdjuntoFot());
						}
						seg.setHseUsuario(getUsuario());
						seg.setHseFechaRegistro(getFechaRegistro());
						manager.modificarSeguimiento(seg);
					Mensaje.crearMensajeINFO("Seguimiento actualizado correctamente.");
					
				} else {
					setIdSeguimiento(manager.idSeguimientoAsignacion(getIdAsignacion()));
					GenHistorialSeguimientoPK pk = new GenHistorialSeguimientoPK();
					pk.setHseId(getIdSeguimiento());
					pk.setSueId(getIdAsignacion());
					seg.setId(pk);
					seg.setHseUsuario(getUsuarioSesion());
					seg.setHseFechaRegistro(new Timestamp(new Date().getTime()));
					manager.insertarSeguimiento(seg);
					setPkSeguimiento(pk);
					setUsuario(seg.getHseUsuario());
					setEdicion(true);
					Mensaje.crearMensajeINFO("Seguimiento ingresado correctamente.");
				}
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error al almacenar seguimiento Asignación: "
					+ e.getMessage());
			System.out.println("Error al almacenar seguimiento Asignación: ");
			e.printStackTrace();
		}
	}

	public boolean validarSelects() {
		if(getCedulaResponsable() == null || getCedulaResponsable().equals("")){
			return false;
		}else{
		return true;
		}
	}

	public void cancelar() {
		limpiarDatos();
		cargarLstSeguimientoAs(getIdAsignacion());
		RequestContext.getCurrentInstance().execute("PF('nSDlg').hide();");
	}

	private void limpiarDatos() {
		setNovedades("");
		setCedulaResponsable(SELECCIONAR);
		setNombreResponsable("");
		setFechaRegistro(new Timestamp(new Date().getTime()));
		setFechaSeguimiento(new Date());
		setIdSeguimiento(ID_INICIAL);
		setEstado(ID_ACTIVO);
		setNovedades("");
		
		setEdicion(false);
	}
	
	public void cargarDocumento(FileUploadEvent event) throws IOException {
		fileDoc = event.getFile();
		InputStream inputStream = null;
		OutputStream outputStream = null;

		if (fileDoc != null) {
			try {
				String carpeta = manager.findParametroByID("direccion_ad_doc") + "/";
					setDirAdjuntoDoc("DOC_SEG_" +getIdSeguimiento()+getIdAsignacion()+ extensionArchivo(fileDoc.getFileName()));
					System.out.println("PAD------> " + carpeta);
					System.out.println("name------> " + getDirAdjuntoDoc());
					outputStream = new FileOutputStream(new File(carpeta + File.separatorChar + getDirAdjuntoDoc()));
					inputStream = fileDoc.getInputstream();

					int read = 0;
					byte[] bytes = new byte[1024];

					while ((read = inputStream.read(bytes)) != -1) {
						outputStream.write(bytes, 0, read);
					}
					Mensaje.crearMensajeINFO("Carga del archivo Correcta");
					editarSeguimiento();
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
	
	public void cargarFoto(FileUploadEvent event) throws IOException {
		fileFoto = event.getFile();
		InputStream inputStream = null;
		OutputStream outputStream = null;

		if (fileFoto != null) {
			try {
				
				String carpeta = manager.findParametroByID("direccion_ad_foto") + "/";
					setDirAdjuntoFoto("FOTO_SEG_" +getIdSeguimiento()+getIdAsignacion()+extensionArchivo(fileFoto.getFileName()));
					System.out.println("PAD------> " + carpeta);
					System.out.println("name------> " + getDirAdjuntoFoto());
					outputStream = new FileOutputStream(new File(carpeta + File.separatorChar + getDirAdjuntoFoto()));
					inputStream = fileFoto.getInputstream();

					int read = 0;
					byte[] bytes = new byte[1024];

					while ((read = inputStream.read(bytes)) != -1) {
						outputStream.write(bytes, 0, read);
					}
					Mensaje.crearMensajeINFO("Carga del archivo Correcta");
					editarSeguimiento();
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
	
	public void editarSeguimiento(){
		try {
			System.out.println("id seguimiento ----> "+getIdSeguimiento());
			GenHistorialSeguimientoPK pk = new GenHistorialSeguimientoPK();
			pk.setHseId(getIdSeguimiento());
			pk.setSueId(getIdAsignacion());
			GenHistorialSeguimiento seg = new GenHistorialSeguimiento();
			seg.setId(pk);
			seg.setHseFecha(getFechaSeguimiento());
			seg.setHseFechaRegistro(getFechaRegistro());
			seg.setHseNovedades(getNovedades());
			seg.setHseResponsable(getCedulaResponsable());
			seg.setHseUsuario(getUsuario());
			seg.setGenAsignacionSuelo(manager.findAsignacionSueloById(getIdAsignacion()));
			seg.setHseEstado(getEstado());
			
			if(seg.getHseAdjuntoDoc() != null || seg.getHseAdjuntoDoc() != ""){
				seg.setHseAdjuntoDoc(getDirAdjuntoDoc());
			}else {
				seg.setHseAdjuntoDoc(manager.findSeguimientoByID(pk).getHseAdjuntoDoc());
			}
			if(seg.getHseAdjuntoFot() != null || seg.getHseAdjuntoFot() != ""){
				seg.setHseAdjuntoFot(getDirAdjuntoFoto());
			}else{
				seg.setHseAdjuntoFot(manager.findSeguimientoByID(pk).getHseAdjuntoFot());
			}
			
			manager.modificarSeguimiento(seg);
			setSeguimiento(seg);
			cargarLstSeguimientoAs(getIdAsignacion());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void descargarDocumento(GenHistorialSeguimiento seguimiento) {
		try {
			if (seguimiento.getHseAdjuntoDoc() == null
					|| seguimiento.getHseAdjuntoDoc().isEmpty()) {
				Mensaje.crearMensajeERROR("No existe un archivo asignado.");
			} else {
				String contextPath = manager
						.findParametroByID("direccion_ad_doc") + File.separatorChar 
						+ seguimiento.getHseAdjuntoDoc() + "";
				Funciones.descargarPDF(contextPath);
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void descargarFoto(GenHistorialSeguimiento seguimiento) {
		try {
			if (seguimiento.getHseAdjuntoFot() == null
					|| seguimiento.getHseAdjuntoFot().isEmpty()) {
				Mensaje.crearMensajeERROR("No existe un archivo asignado.");
			} else {
				String contextPath = manager
						.findParametroByID("direccion_ad_foto") + File.separatorChar
						+ seguimiento.getHseAdjuntoFot() + "";
				Funciones.descargarPDF(contextPath);
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("Error: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String cambiarNombre(String param){
		if(param.equals(EN_PROGRESO)){
			return "En progreso";
		}else{
			return "Actualizado";
		}
	}
}
