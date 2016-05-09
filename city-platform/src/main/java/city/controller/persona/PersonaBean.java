package city.controller.persona;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import city.model.dao.entidades.GenItem;
import city.model.dao.entidades.GenPersona;
import city.model.generic.Mensaje;
import city.model.manager.ManagerPersona;

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

	// valor de edición e inserción
	private boolean edicion;

	public PersonaBean() {
		edicion = false;
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
		return "npersona?faces-redirect=true";
	}

	/**
	 * Permite la creacion o modificacion de una persona
	 * 
	 * @return
	 */
	public String crearPersona() {
		String r = "";
		try {
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
		return "persona?faces-redirect=true";
	}

	/**
	 * metodo para cambio de estado de Persona
	 * 
	 * @param persona
	 */
	public void changePersona(GenPersona persona) {
		try {
			manager.cambioEstadoPersona(persona);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Lista de TiposDNI
	 * 
	 * @return lista de todos los tiposDNI
	 */
	public List<SelectItem> getlistTiposDni() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<GenItem> completo = manager.AllofItems("cat_tipo_dni");
		for (GenItem i : completo) {
			lista.add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
		return lista;
	}

	/**
	 * Lista de Generos
	 * 
	 * @return lista de todos los Generos
	 */
	public List<SelectItem> getlistGeneros() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<GenItem> completo = manager.AllofItems("cat_genero");
		for (GenItem i : completo) {
			lista.add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
		return lista;
	}

	/**
	 * Lista de EstadoCivil
	 * 
	 * @return lista de todos los EstadoCivil
	 */
	public List<SelectItem> getlistEstadoCivil() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<GenItem> completo = manager.AllofItems("cat_estado_civil");
		for (GenItem i : completo) {
			lista.add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
		return lista;
	}

	/**
	 * Lista de Paises
	 * 
	 * @return lista de todos los Paises
	 */
	public List<SelectItem> getlistPaises() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<GenItem> completo = manager.AllofItems("cat_paises");
		for (GenItem i : completo) {
			lista.add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
		return lista;
	}

	/**
	 * Lista de Provincias
	 * 
	 * @return lista de todos los Provincias
	 */
	public List<SelectItem> getlistProvincias() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<GenItem> completo = manager.AllofItems("cat_provincias");
		for (GenItem i : completo) {
			lista.add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
		return lista;
	}

	/**
	 * Lista de Ciudades
	 * 
	 * @return lista de todos los Ciudades
	 */
	public List<SelectItem> getlistCiudades() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<GenItem> completo = manager.AllofItems("cat_ciudades");
		for (GenItem i : completo) {
			lista.add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
		return lista;
	}

	/**
	 * Lista de Estado
	 * 
	 * @return lista de todos los Estado
	 */
	public List<SelectItem> getlistEstado() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<GenItem> completo = manager.AllofItems("cat_estados");
		for (GenItem i : completo) {
			lista.add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
		return lista;
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
		// pd
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
