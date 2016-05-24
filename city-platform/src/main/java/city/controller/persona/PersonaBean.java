package city.controller.persona;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenPersona;
import city.model.dao.entidades.GenPersonaDetalle;
import city.model.generic.Mensaje;
import city.model.manager.ManagerPersona;

/**
 * @author jestevez
 *
 */
@SessionScoped
@ManagedBean
public class PersonaBean {

	// Atributos de la Clase
	@EJB
	private ManagerPersona manager;

	// Atriutos de la clase persona detalle
	private String perDni;
	private String perApellidos;
	private String perCelular;
	private String perCorreo;
	private String perEstado;
	private String perEstadoCivil;
	private Date perFechaNacimiento;
	private String perGenero;
	private String perNombres;
	private String perTelefono;
	private String perTipoDni;

	// Atriutos de la clase persona detalle
	private String pdeCiudadNacimiento;
	private String pdeCiudadResidencia;
	private String pdeCondicionCiudadana;
	private String pdeConyuge;
	private String pdeDireccion;
	private String pdeEmergContactoId;
	private String pdeEmergContactoNombres;
	private String pdeEmergContactoTelefono;
	private Date pdeFechaDefuncion;
	private Date pdeFechaMatrimonio;
	private String pdeFoto;
	private byte[] pdeHuella;
	private String pdeInscripcionDefuncion;
	private String pdeLugarNacimiento;
	private String pdeNacionalidadMadre;
	private String pdeNacionalidadPadre;
	private String pdeNombreMadre;
	private String pdeNombrePadre;
	private String pdeObservacion;
	private String pdePaisNacimiento;
	private String pdePaisResidencia;
	private String pdeProvinciaNacimiento;
	private String pdeProvinciaResidencia;

	// manejo de vistas
	List<GenPersona> l_persona;
	List<SelectItem> l_estados;
	List<SelectItem> l_tipo_dni;
	List<SelectItem> l_genero;
	List<SelectItem> l_estado_civil;
	List<SelectItem> l_pais;
	List<SelectItem> l_provincia;
	List<SelectItem> l_ciudad_n;
	List<SelectItem> l_ciudad_r;

	// valor de edición e inserción
	private boolean edicion;
	
	//valor de provincias y ciudades
	private boolean select_n;
	private boolean select_r;

	public PersonaBean() {
	}

	@PostConstruct
	public void init() {
		edicion = false;
		select_n = true;
		select_r = true;
		l_ciudad_n = new ArrayList<SelectItem>();
		l_ciudad_r = new ArrayList<SelectItem>();
		l_estado_civil = new ArrayList<SelectItem>();
		l_estados = new ArrayList<SelectItem>();
		l_genero = new ArrayList<SelectItem>();
		l_pais = new ArrayList<SelectItem>();
		l_provincia = new ArrayList<SelectItem>();
		l_tipo_dni = new ArrayList<SelectItem>();
		l_persona =new ArrayList<GenPersona>();
		cargarEstadoCivil();
		cargarGeneros();
		cargarPaises();
		cargarTiposDni();
		cargarPersonas();
	}

	/**
	 * @return the l_ciudad_n
	 */
	public List<SelectItem> getL_ciudad_n() {
		return l_ciudad_n;
	}

	/**
	 * @param l_ciudad_n the l_ciudad_n to set
	 */
	public void setL_ciudad_n(List<SelectItem> l_ciudad_n) {
		this.l_ciudad_n = l_ciudad_n;
	}

	/**
	 * @return the l_ciudad_r
	 */
	public List<SelectItem> getL_ciudad_r() {
		return l_ciudad_r;
	}

	/**
	 * @param l_ciudad_r the l_ciudad_r to set
	 */
	public void setL_ciudad_r(List<SelectItem> l_ciudad_r) {
		this.l_ciudad_r = l_ciudad_r;
	}

	/**
	 * @return the select_n
	 */
	public boolean isSelect_n() {
		return select_n;
	}

	/**
	 * @param select_n the select_n to set
	 */
	public void setSelect_n(boolean select_n) {
		this.select_n = select_n;
	}

	/**
	 * @return the select_r
	 */
	public boolean isSelect_r() {
		return select_r;
	}

	/**
	 * @param select_r the select_r to set
	 */
	public void setSelect_r(boolean select_r) {
		this.select_r = select_r;
	}

	/**
	 * @return the perDni
	 */
	public String getPerDni() {
		return perDni;
	}

	/**
	 * @param perDni
	 *            the perDni to set
	 */
	public void setPerDni(String perDni) {
		this.perDni = perDni;
	}

	/**
	 * @return the perApellidos
	 */
	public String getPerApellidos() {
		return perApellidos;
	}

	/**
	 * @param perApellidos
	 *            the perApellidos to set
	 */
	public void setPerApellidos(String perApellidos) {
		this.perApellidos = perApellidos;
	}

	/**
	 * @return the perCelular
	 */
	public String getPerCelular() {
		return perCelular;
	}

	/**
	 * @param perCelular
	 *            the perCelular to set
	 */
	public void setPerCelular(String perCelular) {
		this.perCelular = perCelular;
	}

	/**
	 * @return the perCorreo
	 */
	public String getPerCorreo() {
		return perCorreo;
	}

	/**
	 * @param perCorreo
	 *            the perCorreo to set
	 */
	public void setPerCorreo(String perCorreo) {
		this.perCorreo = perCorreo;
	}

	/**
	 * @return the perEstado
	 */
	public String getPerEstado() {
		return perEstado;
	}

	/**
	 * @param perEstado
	 *            the perEstado to set
	 */
	public void setPerEstado(String perEstado) {
		this.perEstado = perEstado;
	}

	/**
	 * @return the perEstadoCivil
	 */
	public String getPerEstadoCivil() {
		return perEstadoCivil;
	}

	/**
	 * @param perEstadoCivil
	 *            the perEstadoCivil to set
	 */
	public void setPerEstadoCivil(String perEstadoCivil) {
		this.perEstadoCivil = perEstadoCivil;
	}

	/**
	 * @return the perFechaNacimiento
	 */
	public Date getPerFechaNacimiento() {
		return perFechaNacimiento;
	}

	/**
	 * @param perFechaNacimiento
	 *            the perFechaNacimiento to set
	 */
	public void setPerFechaNacimiento(Date perFechaNacimiento) {
		this.perFechaNacimiento = perFechaNacimiento;
	}

	/**
	 * @return the perGenero
	 */
	public String getPerGenero() {
		return perGenero;
	}

	/**
	 * @param perGenero
	 *            the perGenero to set
	 */
	public void setPerGenero(String perGenero) {
		this.perGenero = perGenero;
	}

	/**
	 * @return the perNombres
	 */
	public String getPerNombres() {
		return perNombres;
	}

	/**
	 * @param perNombres
	 *            the perNombres to set
	 */
	public void setPerNombres(String perNombres) {
		this.perNombres = perNombres;
	}

	/**
	 * @return the perTelefono
	 */
	public String getPerTelefono() {
		return perTelefono;
	}

	/**
	 * @param perTelefono
	 *            the perTelefono to set
	 */
	public void setPerTelefono(String perTelefono) {
		this.perTelefono = perTelefono;
	}

	/**
	 * @return the perTipoDni
	 */
	public String getPerTipoDni() {
		return perTipoDni;
	}

	/**
	 * @param perTipoDni
	 *            the perTipoDni to set
	 */
	public void setPerTipoDni(String perTipoDni) {
		this.perTipoDni = perTipoDni;
	}

	/**
	 * @return the edicion
	 */
	public boolean isEdicion() {
		return edicion;
	}
	
	// Getteres and Setteres de persona
	// detalle/////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @return the pdeCiudadNacimiento
	 */
	public String getPdeCiudadNacimiento() {
		return pdeCiudadNacimiento;
	}

	/**
	 * @return the l_persona
	 */
	public List<GenPersona> getL_persona() {
		return l_persona;
	}

	/**
	 * @param l_persona the l_persona to set
	 */
	public void setL_persona(List<GenPersona> l_persona) {
		this.l_persona = l_persona;
	}

	/**
	 * @return the l_estados
	 */
	public List<SelectItem> getL_estados() {
		return l_estados;
	}

	/**
	 * @param l_estados the l_estados to set
	 */
	public void setL_estados(List<SelectItem> l_estados) {
		this.l_estados = l_estados;
	}

	/**
	 * @return the l_tipo_dni
	 */
	public List<SelectItem> getL_tipo_dni() {
		return l_tipo_dni;
	}

	/**
	 * @param l_tipo_dni the l_tipo_dni to set
	 */
	public void setL_tipo_dni(List<SelectItem> l_tipo_dni) {
		this.l_tipo_dni = l_tipo_dni;
	}

	/**
	 * @return the l_genero
	 */
	public List<SelectItem> getL_genero() {
		return l_genero;
	}

	/**
	 * @param l_genero the l_genero to set
	 */
	public void setL_genero(List<SelectItem> l_genero) {
		this.l_genero = l_genero;
	}

	/**
	 * @return the l_estado_civil
	 */
	public List<SelectItem> getL_estado_civil() {
		return l_estado_civil;
	}

	/**
	 * @param l_estado_civil the l_estado_civil to set
	 */
	public void setL_estado_civil(List<SelectItem> l_estado_civil) {
		this.l_estado_civil = l_estado_civil;
	}

	/**
	 * @return the l_pais
	 */
	public List<SelectItem> getL_pais() {
		return l_pais;
	}

	/**
	 * @param l_pais the l_pais to set
	 */
	public void setL_pais(List<SelectItem> l_pais) {
		this.l_pais = l_pais;
	}

	/**
	 * @return the l_provincia
	 */
	public List<SelectItem> getL_provincia() {
		return l_provincia;
	}

	/**
	 * @param l_provincia the l_provincia to set
	 */
	public void setL_provincia(List<SelectItem> l_provincia) {
		this.l_provincia = l_provincia;
	}

	

	/**
	 * @param pdeCiudadNacimiento
	 *            the pdeCiudadNacimiento to set
	 */
	public void setPdeCiudadNacimiento(String pdeCiudadNacimiento) {
		this.pdeCiudadNacimiento = pdeCiudadNacimiento;
	}

	/**
	 * @return the pdeCiudadResidencia
	 */
	public String getPdeCiudadResidencia() {
		return pdeCiudadResidencia;
	}

	/**
	 * @param pdeCiudadResidencia
	 *            the pdeCiudadResidencia to set
	 */
	public void setPdeCiudadResidencia(String pdeCiudadResidencia) {
		this.pdeCiudadResidencia = pdeCiudadResidencia;
	}

	/**
	 * @return the pdeCondicionCiudadana
	 */
	public String getPdeCondicionCiudadana() {
		return pdeCondicionCiudadana;
	}

	/**
	 * @param pdeCondicionCiudadana
	 *            the pdeCondicionCiudadana to set
	 */
	public void setPdeCondicionCiudadana(String pdeCondicionCiudadana) {
		this.pdeCondicionCiudadana = pdeCondicionCiudadana;
	}

	/**
	 * @return the pdeConyuge
	 */
	public String getPdeConyuge() {
		return pdeConyuge;
	}

	/**
	 * @param pdeConyuge
	 *            the pdeConyuge to set
	 */
	public void setPdeConyuge(String pdeConyuge) {
		this.pdeConyuge = pdeConyuge;
	}

	/**
	 * @return the pdeDireccion
	 */
	public String getPdeDireccion() {
		return pdeDireccion;
	}

	/**
	 * @param pdeDireccion
	 *            the pdeDireccion to set
	 */
	public void setPdeDireccion(String pdeDireccion) {
		this.pdeDireccion = pdeDireccion;
	}

	/**
	 * @return the pdeEmergContactoId
	 */
	public String getPdeEmergContactoId() {
		return pdeEmergContactoId;
	}

	/**
	 * @param pdeEmergContactoId
	 *            the pdeEmergContactoId to set
	 */
	public void setPdeEmergContactoId(String pdeEmergContactoId) {
		this.pdeEmergContactoId = pdeEmergContactoId;
	}

	/**
	 * @return the pdeEmergContactoNombres
	 */
	public String getPdeEmergContactoNombres() {
		return pdeEmergContactoNombres;
	}

	/**
	 * @param pdeEmergContactoNombres
	 *            the pdeEmergContactoNombres to set
	 */
	public void setPdeEmergContactoNombres(String pdeEmergContactoNombres) {
		this.pdeEmergContactoNombres = pdeEmergContactoNombres;
	}

	/**
	 * @return the pdeEmergContactoTelefono
	 */
	public String getPdeEmergContactoTelefono() {
		return pdeEmergContactoTelefono;
	}

	/**
	 * @param pdeEmergContactoTelefono
	 *            the pdeEmergContactoTelefono to set
	 */
	public void setPdeEmergContactoTelefono(String pdeEmergContactoTelefono) {
		this.pdeEmergContactoTelefono = pdeEmergContactoTelefono;
	}

	/**
	 * @return the pdeFechaDefuncion
	 */
	public Date getPdeFechaDefuncion() {
		return pdeFechaDefuncion;
	}

	/**
	 * @param pdeFechaDefuncion
	 *            the pdeFechaDefuncion to set
	 */
	public void setPdeFechaDefuncion(Date pdeFechaDefuncion) {
		this.pdeFechaDefuncion = pdeFechaDefuncion;
	}

	/**
	 * @return the pdeFechaMatrimonio
	 */
	public Date getPdeFechaMatrimonio() {
		return pdeFechaMatrimonio;
	}

	/**
	 * @param pdeFechaMatrimonio
	 *            the pdeFechaMatrimonio to set
	 */
	public void setPdeFechaMatrimonio(Date pdeFechaMatrimonio) {
		this.pdeFechaMatrimonio = pdeFechaMatrimonio;
	}

	/**
	 * @return the pdeFoto
	 */
	public String getPdeFoto() {
		return pdeFoto;
	}

	/**
	 * @param pdeFoto
	 *            the pdeFoto to set
	 */
	public void setPdeFoto(String pdeFoto) {
		this.pdeFoto = pdeFoto;
	}

	/**
	 * @return the pdeHuella
	 */
	public byte[] getPdeHuella() {
		return pdeHuella;
	}

	/**
	 * @param pdeHuella
	 *            the pdeHuella to set
	 */
	public void setPdeHuella(byte[] pdeHuella) {
		this.pdeHuella = pdeHuella;
	}

	/**
	 * @return the pdeInscripcionDefuncion
	 */
	public String getPdeInscripcionDefuncion() {
		return pdeInscripcionDefuncion;
	}

	/**
	 * @param pdeInscripcionDefuncion
	 *            the pdeInscripcionDefuncion to set
	 */
	public void setPdeInscripcionDefuncion(String pdeInscripcionDefuncion) {
		this.pdeInscripcionDefuncion = pdeInscripcionDefuncion;
	}

	/**
	 * @return the pdeLugarNacimiento
	 */
	public String getPdeLugarNacimiento() {
		return pdeLugarNacimiento;
	}

	/**
	 * @param pdeLugarNacimiento
	 *            the pdeLugarNacimiento to set
	 */
	public void setPdeLugarNacimiento(String pdeLugarNacimiento) {
		this.pdeLugarNacimiento = pdeLugarNacimiento;
	}

	/**
	 * @return the pdeNacionalidadMadre
	 */
	public String getPdeNacionalidadMadre() {
		return pdeNacionalidadMadre;
	}

	/**
	 * @param pdeNacionalidadMadre
	 *            the pdeNacionalidadMadre to set
	 */
	public void setPdeNacionalidadMadre(String pdeNacionalidadMadre) {
		this.pdeNacionalidadMadre = pdeNacionalidadMadre;
	}

	/**
	 * @return the pdeNacionalidadPadre
	 */
	public String getPdeNacionalidadPadre() {
		return pdeNacionalidadPadre;
	}

	/**
	 * @param pdeNacionalidadPadre
	 *            the pdeNacionalidadPadre to set
	 */
	public void setPdeNacionalidadPadre(String pdeNacionalidadPadre) {
		this.pdeNacionalidadPadre = pdeNacionalidadPadre;
	}

	/**
	 * @return the pdeNombreMadre
	 */
	public String getPdeNombreMadre() {
		return pdeNombreMadre;
	}

	/**
	 * @param pdeNombreMadre
	 *            the pdeNombreMadre to set
	 */
	public void setPdeNombreMadre(String pdeNombreMadre) {
		this.pdeNombreMadre = pdeNombreMadre;
	}

	/**
	 * @return the pdeNombrePadre
	 */
	public String getPdeNombrePadre() {
		return pdeNombrePadre;
	}

	/**
	 * @param pdeNombrePadre
	 *            the pdeNombrePadre to set
	 */
	public void setPdeNombrePadre(String pdeNombrePadre) {
		this.pdeNombrePadre = pdeNombrePadre;
	}

	/**
	 * @return the pdeObservacion
	 */
	public String getPdeObservacion() {
		return pdeObservacion;
	}

	/**
	 * @param pdeObservacion
	 *            the pdeObservacion to set
	 */
	public void setPdeObservacion(String pdeObservacion) {
		this.pdeObservacion = pdeObservacion;
	}

	/**
	 * @return the pdePaisNacimiento
	 */
	public String getPdePaisNacimiento() {
		return pdePaisNacimiento;
	}

	/**
	 * @param pdePaisNacimiento
	 *            the pdePaisNacimiento to set
	 */
	public void setPdePaisNacimiento(String pdePaisNacimiento) {
		this.pdePaisNacimiento = pdePaisNacimiento;
	}

	/**
	 * @return the pdePaisResidencia
	 */
	public String getPdePaisResidencia() {
		return pdePaisResidencia;
	}

	/**
	 * @param pdePaisResidencia
	 *            the pdePaisResidencia to set
	 */
	public void setPdePaisResidencia(String pdePaisResidencia) {
		this.pdePaisResidencia = pdePaisResidencia;
	}

	/**
	 * @return the pdeProvinciaNacimiento
	 */
	public String getPdeProvinciaNacimiento() {
		return pdeProvinciaNacimiento;
	}

	/**
	 * @param pdeProvinciaNacimiento
	 *            the pdeProvinciaNacimiento to set
	 */
	public void setPdeProvinciaNacimiento(String pdeProvinciaNacimiento) {
		this.pdeProvinciaNacimiento = pdeProvinciaNacimiento;
	}

	/**
	 * @return the pdeProvinciaResidencia
	 */
	public String getPdeProvinciaResidencia() {
		return pdeProvinciaResidencia;
	}

	/**
	 * @param pdeProvinciaResidencia
	 *            the pdeProvinciaResidencia to set
	 */
	public void setPdeProvinciaResidencia(String pdeProvinciaResidencia) {
		this.pdeProvinciaResidencia = pdeProvinciaResidencia;
	}

	/**
	 * @param edicion
	 *            the edicion to set
	 */
	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	/**
	 * Metodo de obtención de una lista con todos los datos
	 * 
	 * @return List<Persona>
	 */
	public List<GenPersona> getListPersona() {
		try {
			return manager.findAllPersonas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Redirecciona a la pagina de creacion de personas
	 * 
	 * @return vista
	 */
	public String nuevaPersona() {
		edicion = false;
		setSelect_n(true);
		setSelect_r(true);
		return "npersona.xhtml";
	}

	/**
	 * Permite la creacion o modificacion de una persona
	 * 
	 * @return
	 */
	public String crearPersona() {
		String r = "";
		try {
			if (this.validarCampos()) {
				Mensaje.crearMensajeERROR("Todos los datos generales son requeridos");
			} else {
				if (edicion) {
					manager.editarPersona(getPerDni(), getPerTipoDni(),
							getPerNombres(), getPerApellidos(),
							getPerFechaNacimiento(), getPerGenero(),
							getPerTelefono(), getPerCelular(), getPerCorreo(),
							getPerEstadoCivil(), getPerEstado());
					this.editarPersonaDetalle();
					Mensaje.crearMensajeINFO("Actualizado - Persona Modificada");
				} else {
					manager.insertarPersona(getPerDni(), getPerTipoDni(),
							getPerNombres(), getPerApellidos(),
							getPerFechaNacimiento(), getPerGenero(),
							getPerTelefono(), getPerCelular(), getPerCorreo(),
							getPerEstadoCivil());
					this.crearPersonaDetalle();
					Mensaje.crearMensajeINFO("Registrado - Persona Creada");
				}
				r = "persona?faces-redirect=true";
				this.cleanDatos();
				this.cargarPersonas();
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
		return r;
	}

	/**
	 * Metodo para cargar una Persona para su edicion
	 * 
	 * @param persona
	 * @return
	 */
	public String cargarPersona(GenPersona persona) {
		try {
			setSelect_n(false);
			setSelect_r(false);
			cargarEstados();
			setPerDni(persona.getPerDni());
			setPerTipoDni(persona.getPerTipoDni());
			setPerNombres(persona.getPerNombres());
			setPerApellidos(persona.getPerApellidos());
			setPerFechaNacimiento(persona.getPerFechaNacimiento());
			setPerGenero(persona.getPerGenero());
			setPerTelefono(persona.getPerTelefono());
			setPerCelular(persona.getPerCelular());
			setPerCorreo(persona.getPerCorreo());
			setPerEstadoCivil(persona.getPerEstadoCivil());
			setPerEstado(persona.getPerEstado());
			// carga de persona detalle
			GenPersonaDetalle pd = manager.PersonaDetalleByID(persona
					.getPerDni());
			this.cargarPersonaDetalle(pd);
			setEdicion(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "npersona?faces-redirect=true";
	}

	/**
	 * Metodo para setear datos y regresar la vista
	 * 
	 * @return
	 */
	public String cancelar() {
		this.cleanDatos();
		this.cargarPersonas();
		return "persona?faces-redirect=true";
	}
	
	/**
	 * Lista de Personas
	 */
	public void cargarPersonas() {
		try {
			getL_persona().clear();
			getL_persona().addAll(manager.findAllPersonas());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Lista de TiposDNI
	 */
	public void cargarTiposDni() {
		getL_tipo_dni().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_tipo_dni");
		for (GenCatalogoItemsDet i : completo) {
			getL_tipo_dni().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de Generos
	 */
	public void cargarGeneros() {
		getL_genero().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_genero");
		for (GenCatalogoItemsDet i : completo) {
			getL_genero().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de EstadoCivil
	 */
	public void cargarEstadoCivil() {
		getL_estado_civil().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_estado_civil");
		for (GenCatalogoItemsDet i : completo) {
			getL_estado_civil().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de Paises
	 */
	public void cargarPaises() {
		getL_pais().clear();
		for (GenCatalogoItemsDet i : manager.AllofItems("cat_paises")) {
			getL_pais().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de Provincias
	 */
	public void cargarProvincias() {
		getL_provincia().clear();
		for (GenCatalogoItemsDet i : manager.AllofItems("cat_provincias")) {
			getL_provincia().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de Ciudades Nacimiento
	 */
	public void cargarCiudadesNac(String provincia) {
		getL_ciudad_n().clear();
		if (manager.AllofItems("cat_ciudades",provincia)!=null){
		for (GenCatalogoItemsDet i : manager.AllofItems("cat_ciudades",provincia)) {
			getL_ciudad_n().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}}
	}
	
	/**
	 * Lista de Ciudades Residencia
	 */
	public void cargarCiudadesRes(String provincia) {
		getL_ciudad_r().clear();
		if (manager.AllofItems("cat_ciudades",provincia)!=null){
		for (GenCatalogoItemsDet i : manager.AllofItems("cat_ciudades",provincia)) {
			getL_ciudad_r().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}}
	}

	/**
	 * Lista de Estado
	 */
	public void cargarEstados() {
		getL_estados().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_estados");
		for (GenCatalogoItemsDet i : completo) {
			getL_estados().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	// ////////////////////////////////////////////////////////PERSONA -
	// DETALLE/////////////////////////////////////////////////////////

	/**
	 * Permite la creacion de una persona detalle
	 * 
	 * @return
	 */
	public void crearPersonaDetalle() {
		try {
			manager.insertarPersonaDetalle(getPerDni(), getPdeFoto(),
					getPdePaisNacimiento(), getPdeProvinciaNacimiento(),
					getPdeCiudadNacimiento(), getPdeLugarNacimiento(),
					getPdePaisResidencia(), getPdeProvinciaResidencia(),
					getPdeCiudadResidencia(), getPdeDireccion(),
					getPdeCondicionCiudadana(), getPdeConyuge(),
					getPdeFechaMatrimonio(), getPdeNombrePadre(),
					getPdeNacionalidadPadre(), getPdeNombreMadre(),
					getPdeNacionalidadMadre(), getPdeEmergContactoNombres(),
					getPdeEmergContactoId(), getPdeEmergContactoTelefono(),
					getPdeInscripcionDefuncion(), getPdeFechaDefuncion(),
					getPdeObservacion());
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
	}

	/**
	 * Permite la edición de una persona detalle
	 * 
	 * @return
	 */
	public void editarPersonaDetalle() {
		try {
			manager.editarPersonaDetalle(getPerDni(), getPdeFoto(),
					getPdePaisNacimiento(), getPdeProvinciaNacimiento(),
					getPdeCiudadNacimiento(), getPdeLugarNacimiento(),
					getPdePaisResidencia(), getPdeProvinciaResidencia(),
					getPdeCiudadResidencia(), getPdeDireccion(),
					getPdeCondicionCiudadana(), getPdeConyuge(),
					getPdeFechaMatrimonio(), getPdeNombrePadre(),
					getPdeNacionalidadPadre(), getPdeNombreMadre(),
					getPdeNacionalidadMadre(), getPdeEmergContactoNombres(),
					getPdeEmergContactoId(), getPdeEmergContactoTelefono(),
					getPdeInscripcionDefuncion(), getPdeFechaDefuncion(),
					getPdeObservacion());
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
	}

	/**
	 * Metodo para cargar una Persona-Detalle para su edicion
	 * 
	 * @param persona
	 * @return
	 */
	public String cargarPersonaDetalle(GenPersonaDetalle persona) {
		try {
			setPdeCiudadNacimiento(persona.getPdeCiudadNacimiento());
			setPdeCiudadResidencia(persona.getPdeCiudadResidencia());
			setPdeCondicionCiudadana(persona.getPdeCondicionCiudadana());
			setPdeConyuge(persona.getPdeConyuge());
			setPdeDireccion(persona.getPdeDireccion());
			setPdeEmergContactoId(persona.getPdeEmergContactoId());
			setPdeEmergContactoNombres(persona.getPdeEmergContactoNombres());
			setPdeEmergContactoTelefono(persona.getPdeEmergContactoTelefono());
			setPdeFechaDefuncion(persona.getPdeFechaDefuncion());
			setPdeFechaMatrimonio(persona.getPdeFechaMatrimonio());
			setPdeFoto(persona.getPdeFoto());
			setPdeHuella(persona.getPdeHuella());
			setPdeInscripcionDefuncion(persona.getPdeInscripcionDefuncion());
			setPdeLugarNacimiento(persona.getPdeLugarNacimiento());
			setPdeNacionalidadMadre(persona.getPdeNacionalidadMadre());
			setPdeNacionalidadPadre(persona.getPdeNacionalidadPadre());
			setPdeNombreMadre(persona.getPdeNombreMadre());
			setPdeNombrePadre(persona.getPdeNombrePadre());
			setPdeObservacion(persona.getPdeObservacion());
			setPdePaisNacimiento(persona.getPdePaisNacimiento());
			setPdePaisResidencia(persona.getPdePaisResidencia());
			setPdeProvinciaNacimiento(persona.getPdeProvinciaNacimiento());
			setPdeProvinciaResidencia(persona.getPdeProvinciaResidencia());
			//actualización de lista de sitios
			cargarCiudadesNac(persona.getPdeProvinciaNacimiento());
			cargarCiudadesRes(persona.getPdeProvinciaResidencia());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "npersona?faces-redirect=true";
	}
	
	/**
	 * Metodo para habilitar campos
	 */
	public void habilitarCamposNac(){
		if (getPdePaisNacimiento().equals("EC")){
			setSelect_n(false);
			cargarProvincias();
		}else{
			setPdeProvinciaNacimiento(null);
			setPdeCiudadNacimiento(null);
			setSelect_n(true);
		}
	}
	
	/**
	 * Metodo para habilitar campos
	 */
	public void habilitarCamposRer(){
		if (getPdePaisResidencia().equals("EC")){
			setSelect_r(false);
			cargarProvincias();
		}else{
			setPdeProvinciaResidencia(null);
			setPdeCiudadResidencia(null);
			setSelect_r(true);
		}
	}
	
	/**
	 * Metod para mostrar la ciudad dependiendo de la provincia
	 */
	public void mostrarCiudadNac(String provincia){
		cargarCiudadesNac(provincia);
	}
	
	/**
	 * Metod para mostrar la ciudad dependiendo de la provincia
	 */
	public void mostrarCiudadRes(String provincia){
		cargarCiudadesRes(provincia);
	}
	

	/**
	 * Metodo para validar los campos generales de la persona
	 * 
	 * @return
	 */
	public boolean validarCampos() {
		if ((getPerApellidos() == null || getPerApellidos().isEmpty())
				|| (getPerCelular() == null || getPerCelular().isEmpty())
				|| (getPerCorreo() == null || getPerCorreo().isEmpty())
				|| (getPerDni() == null || getPerDni().isEmpty())
				|| (getPerEstadoCivil() == null || getPerEstadoCivil().equals(
						"-1")) || (getPerFechaNacimiento() == null)
				|| (getPerGenero() == null || getPerGenero().equals("-1"))
				|| (getPerNombres() == null || getPerNombres().isEmpty())
				|| (getPerTipoDni() == null || getPerTipoDni().equals("-1"))
				|| (getPerTelefono() == null || getPerTelefono().isEmpty())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo de seteo de datos
	 */
	public void cleanDatos() {
		setPerDni("");
		setPerTipoDni("");
		setPerNombres("");
		setPerApellidos("");
		setPerFechaNacimiento(null);
		setPerGenero("");
		setPerTelefono("");
		setPerCelular("");
		setPerCorreo("");
		setPerEstadoCivil("");
		setPerEstado("");
		// (persona-detalle)
		setPdeCiudadNacimiento("");
		setPdeCiudadResidencia("");
		setPdeCondicionCiudadana("");
		setPdeConyuge("");
		setPdeDireccion("");
		setPdeEmergContactoId("");
		setPdeEmergContactoNombres("");
		setPdeEmergContactoTelefono("");
		setPdeFechaDefuncion(null);
		setPdeFechaMatrimonio(null);
		setPdeFoto("");
		setPdeInscripcionDefuncion("");
		setPdeLugarNacimiento("");
		setPdeNacionalidadMadre("");
		setPdeNacionalidadPadre("");
		setPdeNombreMadre("");
		setPdeNombrePadre("");
		setPdeObservacion("");
		setPdePaisNacimiento("");
		setPdePaisResidencia("");
		setPdeProvinciaNacimiento("");
		setPdeProvinciaResidencia("");
		setEdicion(false);
	}
}
