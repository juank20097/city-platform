package city.model.manager;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.GenPersona;
import city.model.generic.Mensaje;

/**
 *  Esta Clase permite manejar el ManagerDAO en conveniencia a la gestión necesaria
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
	 * Constructor para la utilizacion de metodos de la clase HibernateDAO
	 * 
	 * @param manager
	 *            El parametro manager inicializa la utilizacion de la clase
	 *            HIbernateDAO y todos sus metodos
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
	public List<GenPersona> findAllPersonas() throws Exception{
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
	public void insertarPersona(String dni, String nombres, String apellidos,
			Date fecha_nacimiento, String genero, String telefono,
			String celular, String correo, String estado_civil, String estado)
			throws Exception {
		try {
			GenPersona persona = new GenPersona();
			persona.setPerDni(dni);
			persona.setPerNombres(nombres);
			persona.setPerApellidos(apellidos);
			persona.setPerFechaNacimiento(fecha_nacimiento);
			persona.setPerGenero(genero);
			persona.setPerTelefono(telefono);
			persona.setPerCelular(celular);
			persona.setPerCorreo(correo);
			persona.setPerEstadoCivil(estado_civil);
			persona.setPerEstado(estado);
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
	public void editarCategoria(String dni, String nombres, String apellidos,
			Date fecha_nacimiento, String genero, String telefono,
			String celular, String correo, String estado_civil, String estado)
			throws Exception {
		try {
			GenPersona persona = this.PersonaByID(dni);
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
	public void inactivarPersona(String dni) throws Exception {
		try {
			GenPersona persona = this.PersonaByID(dni);
			if (persona.getPerEstado().equals("A")) {
				persona.setPerEstado("I");
			}
			Mensaje.crearMensajeINFO("Persona inactivada correctamente.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// Cierre del metodo

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}