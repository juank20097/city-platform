package city.model.manager;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.GenCatalogoCab;
import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenPersona;
import city.model.dao.entidades.GenPersonaDetalle;
import city.model.generic.Mensaje;

/**
 * Esta Clase permite manejar el ManagerDAO en conveniencia a la gestión
 * necesaria
 * 
 * @author Juan Carlos Estévez Hidalgo
 * @version 1.0
 * 
 */
@Stateless
public class ManagerPersona {

	@EJB
	private ManagerDAO mngDao;

	/**
	 * Metodo de inicialización de contructor
	 */
	public ManagerPersona() {
	}// Cierre del Constructor

	// //////////////////////////////////////////////////////////(PERSONAS)/////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla PERSONAS
	 * 
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenPersona> findAllPersonas() throws Exception {
		return mngDao.findAll(GenPersona.class);
	}// Cierre del metodo

	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public GenPersona PersonaByID(String dni) throws Exception {
		return (GenPersona) mngDao.findById(GenPersona.class, dni);
	}// Cierre del metodo

	/**
	 * Metodo para ingresar un Atributo a la base de datos
	 * 
	 * @param dni
	 * @param nombres
	 * @param apellidos
	 * @param fecha_nacimiento
	 * @param genero
	 * @param telefono
	 * @param celular
	 * @param correo
	 * @param estado_civil
	 * @param estado
	 * @throws Exception
	 */
	public void insertarPersona(String dni, String tipo_dni, String nombres,
			String apellidos, Date fecha_nacimiento, String genero,
			String telefono, String celular, String correo, String estado_civil)
			throws Exception {
		try {
			GenPersona persona = new GenPersona();
			persona.setPerDni(dni);
			persona.setPerTipoDni(tipo_dni);
			persona.setPerNombres(nombres);
			persona.setPerApellidos(apellidos);
			persona.setPerFechaNacimiento(fecha_nacimiento);
			persona.setPerGenero(genero);
			persona.setPerTelefono(telefono);
			persona.setPerCelular(celular);
			persona.setPerCorreo(correo);
			persona.setPerEstadoCivil(estado_civil);
			persona.setPerEstado("A");
			mngDao.insertar(persona);
			System.out.println("Bien_insertar_persona");
		} catch (Exception e) {
			System.out.println("Error_insertar_persona");
			e.printStackTrace();
		}
	}// Cierre del metodo

	/**
	 * Metodo para editar un Atributo en la base de datos
	 * 
	 * @param dni
	 * @param nombres
	 * @param apellidos
	 * @param fecha_nacimiento
	 * @param genero
	 * @param telefono
	 * @param celular
	 * @param correo
	 * @param estado_civil
	 * @param estado
	 * @throws Exception
	 */
	public void editarPersona(String dni, String tipo_dni, String nombres,
			String apellidos, Date fecha_nacimiento, String genero,
			String telefono, String celular, String correo,
			String estado_civil, String estado) throws Exception {
		try {
			GenPersona persona = this.PersonaByID(dni);
			persona.setPerTipoDni(tipo_dni);
			persona.setPerNombres(nombres);
			persona.setPerApellidos(apellidos);
			persona.setPerFechaNacimiento(fecha_nacimiento);
			persona.setPerGenero(genero);
			persona.setPerTelefono(telefono);
			persona.setPerCelular(celular);
			persona.setPerCorreo(correo);
			persona.setPerEstadoCivil(estado_civil);
			persona.setPerEstado(estado);
			mngDao.actualizar(persona);
			System.out.println("Bien_mod_persona");
		} catch (Exception e) {
			System.out.println("Error_mod_persona");
			e.printStackTrace();
		}
	}// Cierre del metodo

	/**
	 * Metodo para cambiar un estado en la base de datos
	 * 
	 * @param dni
	 */
	public void cambioEstadoPersona(GenPersona persona) throws Exception {
		try {
			if (persona.getPerEstado().equals("A")) {
				persona.setPerEstado("I");
			}else{ 
				if (persona.getPerEstado().equals("I")) {
					persona.setPerEstado("A");
				}
			}
			mngDao.actualizar(persona);
			Mensaje.crearMensajeINFO("Persona cambiada de estado correctamente.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// Cierre del metodo

	// ////////////////////////////////////////////////////////////(CATALOGO)///////////////////////////////////////////////////////////////////////

	/**
	 * Creación de metodos para el manejo de la tabla CATALOGO
	 * 
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenCatalogoCab> findAllCatalogo() throws Exception {
		return mngDao.findAll(GenCatalogoCab.class);
	}// Cierre del metodo

	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public GenCatalogoCab CatalogoByID(String codigo) throws Exception {
		return (GenCatalogoCab) mngDao.findById(GenCatalogoCab.class, codigo);
	}// Cierre del metodo

	// ////////////////////////////////////////////////////////////(ITEM)///////////////////////////////////////////////////////////////////////

	/**
	 * Creación de metodos para el manejo de la tabla ITEM
	 * 
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenCatalogoItemsDet> findAllItems() throws Exception {
		return mngDao.findAll(GenCatalogoItemsDet.class);
	}// Cierre del metodo

	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public GenCatalogoItemsDet ItemByID(String dni) throws Exception {
		return (GenCatalogoItemsDet) mngDao.findById(GenCatalogoItemsDet.class, dni);
	}// Cierre del metodo

	/**
	 * Metodo para listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GenCatalogoItemsDet> AllofItems(String cat_nombre) {
		List<GenCatalogoItemsDet> li = mngDao.findWhere(GenCatalogoItemsDet.class,
				"o.GenCatalogoCab.catCodigo='" + cat_nombre + "'", null);
		if (li == null || li.isEmpty()) {
			return null;
		} else {
			return li;
		}
	}// Cierre del metodo

	/**
	 * Metodo para listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GenCatalogoItemsDet> AllofItems(String cat_nombre, String padre) {
		List<GenCatalogoItemsDet> li = mngDao.findWhere(GenCatalogoItemsDet.class,
				"o.GenCatalogoCab.catCodigo='" + cat_nombre + "' and o.itePadre='"
						+ padre + "'", null);
		if (li == null || li.isEmpty()) {
			return null;
		} else {
			return li;
		}
	}// Cierre del metodo

	// //////////////////////////////////////////////////////////(PERSONAS -
	// DETALLES)/////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla PERSONAS - DETALLES
	 * 
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenPersonaDetalle> findAllPersonaDetalle() throws Exception {
		return mngDao.findAll(GenPersonaDetalle.class);
	}// Cierre del metodo

	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public GenPersonaDetalle PersonaDetalleByID(String dni) throws Exception {
		return (GenPersonaDetalle) mngDao
				.findById(GenPersonaDetalle.class, dni);
	}// Cierre del metodo

	/**
	 * Metodo para ingresar un Atributo a la base de datos
	 * 
	 * @param dni
	 * @param pais_nac
	 * @param provincia_nac
	 * @param ciudad_nac
	 * @param lugar_nac
	 * @param pais_rec
	 * @param provincia_rec
	 * @param ciudad_rec
	 * @param direccion_rec
	 * @param condicion_ciudadana
	 * @param conyuge
	 * @param fecha_matrimonio
	 * @param nombre_pad
	 * @param nacionalidad_pad
	 * @param nombre_madre
	 * @param nacionalidad_madre
	 * @param nombre_emergencia
	 * @param id_emergencia
	 * @param telefono_emergencia
	 * @param inscripcion_defuncion
	 * @param fecha_defuncion
	 * @param observacion
	 * @throws Exception
	 */
	public void insertarPersonaDetalle(String dni, String foto,
			String pais_nac, String provincia_nac, String ciudad_nac,
			String lugar_nac, String pais_rec, String provincia_rec,
			String ciudad_rec, String direccion_rec,
			String condicion_ciudadana, String conyuge, Date fecha_matrimonio,
			String nombre_pad, String nacionalidad_pad, String nombre_madre,
			String nacionalidad_madre, String nombre_emergencia,
			String id_emergencia, String telefono_emergencia,
			String inscripcion_defuncion, Date fecha_defuncion,
			String observacion) throws Exception {
		try {
			GenPersonaDetalle personad = new GenPersonaDetalle();
			personad.setPdeDni(dni);
			personad.setPdePaisNacimiento(pais_nac);
			personad.setPdeProvinciaNacimiento(provincia_nac);
			personad.setPdeCiudadNacimiento(ciudad_nac);
			personad.setPdeLugarNacimiento(lugar_nac);
			personad.setPdePaisResidencia(pais_rec);
			personad.setPdeFoto(foto);
			personad.setPdeProvinciaResidencia(provincia_rec);
			personad.setPdeCiudadResidencia(ciudad_rec);
			personad.setPdeDireccion(direccion_rec);
			personad.setPdeCondicionCiudadana(condicion_ciudadana);
			personad.setPdeConyuge(conyuge);
			personad.setPdeFechaMatrimonio(fecha_matrimonio);
			personad.setPdeNombrePadre(nombre_pad);
			personad.setPdeNacionalidadPadre(nacionalidad_pad);
			personad.setPdeNombreMadre(nombre_madre);
			personad.setPdeNacionalidadMadre(nacionalidad_madre);
			personad.setPdeEmergContactoNombres(nombre_emergencia);
			personad.setPdeEmergContactoId(id_emergencia);
			personad.setPdeEmergContactoTelefono(telefono_emergencia);
			personad.setPdeFechaDefuncion(fecha_defuncion);
			personad.setPdeInscripcionDefuncion(inscripcion_defuncion);
			personad.setPdeObservacion(observacion);
			mngDao.insertar(personad);
			System.out.println("Bien_insertar_personaDetalle");
		} catch (Exception e) {
			System.out.println("Error_insertar_personaDetalle");
			e.printStackTrace();
		}
	}// Cierre del metodo

	/**
	 * Metodo para editar un Atributo en la base de datos
	 * 
	 * @param dni
	 * @param pais_nac
	 * @param provincia_nac
	 * @param ciudad_nac
	 * @param lugar_nac
	 * @param pais_rec
	 * @param provincia_rec
	 * @param ciudad_rec
	 * @param direccion_rec
	 * @param condicion_ciudadana
	 * @param conyuge
	 * @param fecha_matrimonio
	 * @param nombre_pad
	 * @param nacionalidad_pad
	 * @param nombre_madre
	 * @param nacionalidad_madre
	 * @param nombre_emergencia
	 * @param id_emergencia
	 * @param telefono_emergencia
	 * @param inscripcion_defuncion
	 * @param fecha_defuncion
	 * @param observacion
	 * @throws Exception
	 */
	public void editarPersonaDetalle(String dni, String foto, String pais_nac,
			String provincia_nac, String ciudad_nac, String lugar_nac,
			String pais_rec, String provincia_rec, String ciudad_rec,
			String direccion_rec, String condicion_ciudadana, String conyuge,
			Date fecha_matrimonio, String nombre_pad, String nacionalidad_pad,
			String nombre_madre, String nacionalidad_madre,
			String nombre_emergencia, String id_emergencia,
			String telefono_emergencia, String inscripcion_defuncion,
			Date fecha_defuncion, String observacion) throws Exception {
		try {
			GenPersonaDetalle personad = this.PersonaDetalleByID(dni);
			personad.setPdePaisNacimiento(pais_nac);
			personad.setPdeProvinciaNacimiento(provincia_nac);
			personad.setPdeCiudadNacimiento(ciudad_nac);
			personad.setPdeLugarNacimiento(lugar_nac);
			personad.setPdeFoto(foto);
			personad.setPdePaisResidencia(pais_rec);
			personad.setPdeProvinciaResidencia(provincia_rec);
			personad.setPdeCiudadResidencia(ciudad_rec);
			personad.setPdeDireccion(direccion_rec);
			personad.setPdeCondicionCiudadana(condicion_ciudadana);
			personad.setPdeConyuge(conyuge);
			personad.setPdeFechaMatrimonio(fecha_matrimonio);
			personad.setPdeNombrePadre(nombre_pad);
			personad.setPdeNacionalidadPadre(nacionalidad_pad);
			personad.setPdeNombreMadre(nombre_madre);
			personad.setPdeNacionalidadMadre(nacionalidad_madre);
			personad.setPdeEmergContactoNombres(nombre_emergencia);
			personad.setPdeEmergContactoId(id_emergencia);
			personad.setPdeEmergContactoTelefono(telefono_emergencia);
			personad.setPdeFechaDefuncion(fecha_defuncion);
			personad.setPdeInscripcionDefuncion(inscripcion_defuncion);
			personad.setPdeObservacion(observacion);
			mngDao.actualizar(personad);
			System.out.println("Bien_mod_personaDetalle");
		} catch (Exception e) {
			System.out.println("Error_mod_personaDetalle");
			e.printStackTrace();
		}
	}// Cierre del metodo

}