package city.model.manager;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.GenCapacitacione;
import city.model.dao.entidades.GenCatalogoCab;
import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenExperiencialaboral;
import city.model.dao.entidades.GenFamiliare;
import city.model.dao.entidades.GenFormacionacademica;
import city.model.dao.entidades.GenPersona;
import city.model.dao.entidades.GenPersonaDetalle;
import city.model.dao.entidades.GenSalud;
import city.model.dao.entidades.SegIncidenciasAdmin;
import city.model.dao.entidades.extras.DatosFuncionario;
import city.model.dao.entidades.extras.GenericClassBoolean;
import city.model.dao.entidades.extras.Sangre;
import city.model.generic.Funciones;

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

	private static String INS_YACHAYEP = "Yachay EP";
	private static String INS_YACHAYTECH = "Yachay TECH";
	private static String INS_CIUDAD = "Ciudad Yachay";
	private static String INS_IST = "Instituto Tecnológico 17 de Julio";

	private static String PER_FUN = "Funcionarios";
	private static String PER_EST = "Estudiantes";
	private static String PER_EXT = "Externos";
	private static String PER_COM = "Comuneros";

	@EJB
	private ManagerDAO mngDao;

	/**
	 * Metodo de inicialización de contructor
	 */
	public ManagerPersona() {
	}

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
		return mngDao.findAll(GenPersona.class, "o.perDni asc");
	}// Cierre del metodo

	@SuppressWarnings("unchecked")
	public Integer contador() {
		List<Integer> l = mngDao.Countclase(GenPersona.class);
		if (l != null) {
			return l.get(0);
		} else {
			return null;
		}
	}

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
	public void insertarPersona(String dni, String tipo_dni, String nombres, String apellidos, Date fecha_nacimiento,
			String genero, String telefono, String celular, String correo, String estado_civil, String correo2)
			throws Exception {
		try {
			GenPersona persona = new GenPersona();
			persona.setPerDni(dni);
			persona.setPerTipoDni(tipo_dni);
			persona.setPerNombres(nombres.toUpperCase());
			persona.setPerApellidos(apellidos.toUpperCase());
			persona.setPerFechaNacimiento(fecha_nacimiento);
			persona.setPerGenero(genero);
			persona.setPerTelefono(telefono);
			persona.setPerCelular(celular);
			persona.setPerCorreo(correo);
			persona.setPerEstadoCivil(estado_civil);
			persona.setPerEstado("A");
			persona.setPerCorreo2(correo2);
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
	public void editarPersona(String dni, String tipo_dni, String nombres, String apellidos, Date fecha_nacimiento,
			String genero, String telefono, String celular, String correo, String estado_civil, String correo2,
			String estado) throws Exception {
		try {
			GenPersona persona = this.PersonaByID(dni);
			persona.setPerTipoDni(tipo_dni);
			persona.setPerNombres(nombres.toUpperCase());
			persona.setPerApellidos(apellidos.toUpperCase());
			persona.setPerFechaNacimiento(fecha_nacimiento);
			persona.setPerGenero(genero);
			persona.setPerTelefono(telefono);
			persona.setPerCelular(celular);
			persona.setPerCorreo(correo);
			persona.setPerCorreo2(correo2);
			persona.setPerEstadoCivil(estado_civil);
			if (estado != null)
				persona.setPerEstado(estado);
			else
				persona.setPerEstado("A");
			mngDao.actualizar(persona);
			System.out.println("Bien_mod_persona");
		} catch (Exception e) {
			System.out.println("Error_mod_persona");
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

	@SuppressWarnings("unchecked")
	public boolean verificarUsuarioSalud(String usuario) {
		List<SegIncidenciasAdmin> lista_usu_admin = mngDao.findWhere(SegIncidenciasAdmin.class,
				"o.admUsuario = '" + usuario.trim() + "'", null);
		if (lista_usu_admin == null || lista_usu_admin.size() == 0) {
			return false;
		} else {
			return true;
		}

	}

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
				"o.genCatalogoCab.catCodigo='" + cat_nombre + "'", "o.iteNombre");
		if (li == null || li.isEmpty()) {
			return null;
		} else {
			return li;
		}
	}// Cierre del metodo

	@SuppressWarnings("unchecked")
	public List<GenCatalogoItemsDet> AllItemsOrder(String cat_nombre) {
		List<GenCatalogoItemsDet> li = mngDao.findWhere(GenCatalogoItemsDet.class,
				"o.genCatalogoCab.catCodigo='" + cat_nombre + "'", "o.iteCodigo");
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
				"o.genCatalogoCab.catCodigo='" + cat_nombre + "' and o.itePadre='" + padre + "'", null);
		if (li == null || li.isEmpty()) {
			return null;
		} else {
			return li;
		}
	}// Cierre del metodo

	/**
	 * Método para buscar una persona por variable
	 * 
	 * @param dato
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GenPersona> buscarPersona(String dato) {
		List<GenPersona> l_p = new ArrayList<GenPersona>();
		if (dato.length() > 2) {
			l_p.addAll((List<GenPersona>) mngDao.findWhere(GenPersona.class,
					"o.perDni like '%" + dato + "%' or o.perCelular like '%" + dato + "%' or o.perEstadoCivil like '%"
							+ dato + "%' or o.perTelefono like '%" + dato + "%' or o.perTipoDni like '%" + dato + "%'",
					null));
			if (l_p == null || l_p.isEmpty())
				l_p.addAll(verificarMayusculas(dato));
		} else {
			l_p.addAll((List<GenPersona>) mngDao.findWhere(GenPersona.class,
					"o.perEstado like '%" + dato + "%' or o.perGenero like '%" + dato + "%'", null));
		}
		return l_p;
	}

	/**
	 * Método para verificar el uso de mayúsculas en el filtrado por nombre
	 * 
	 * @param dato
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GenPersona> verificarMayusculas(String dato) {
		List<GenPersona> l = new ArrayList<GenPersona>();
		l.addAll((List<GenPersona>) mngDao.findWhere(GenPersona.class,
				"o.perApellidos like '%" + dato.toLowerCase() + "%' or o.perCorreo like '%" + dato.toLowerCase()
						+ "%' or o.perApellidos like '%" + dato.toUpperCase() + "%' or o.perApellidos like '%" + dato
						+ "%'",
				null));
		if (l == null || l.size() == 0)
			l.addAll((List<GenPersona>) mngDao.findWhere(GenPersona.class,
					"o.perNombres like '%" + dato.toLowerCase() + "%' or o.perCorreo like '%" + dato.toLowerCase()
							+ "%' or o.perNombres like '%" + dato.toUpperCase() + "%' or o.perNombres like '%" + dato
							+ "%'",
					null));
		return l;
	}

	// //////////////////////////////////////////////////////////(PERSONAS-DETALLES)/////////////////////////////////////////////////////////////////////
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
		return (GenPersonaDetalle) mngDao.findById(GenPersonaDetalle.class, dni);
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
	public void insertarPersonaDetalle(String dni, String foto, String pais_nac, String provincia_nac,
			String ciudad_nac, String lugar_nac, String pais_rec, String provincia_rec, String ciudad_rec,
			String direccion_rec, String condicion_ciudadana, String conyuge, Date fecha_matrimonio, Integer num_hijos,
			String nombre_pad, String nacionalidad_pad, String nombre_madre, String nacionalidad_madre,
			String nombre_emergencia, String id_emergencia, String telefono_emergencia, String telefono2_emergencia,
			String correo_emergencia, String inscripcion_defuncion, Date fecha_defuncion, String observacion,
			String residencia, Integer estadia_dias, Integer estadia_horas) throws Exception {
		try {
			GenPersonaDetalle personad = new GenPersonaDetalle();
			personad.setPdeDni(dni);
			personad.setPdeNumHijos(num_hijos);
			personad.setPdePaisNacimiento(pais_nac);
			personad.setPdeProvinciaNacimiento(provincia_nac);
			personad.setPdeCiudadNacimiento(ciudad_nac);
			personad.setPdeLugarNacimiento(lugar_nac.toUpperCase());
			personad.setPdePaisResidencia(pais_rec);
			personad.setPdeFoto(foto);
			personad.setPdeProvinciaResidencia(provincia_rec);
			personad.setPdeCiudadResidencia(ciudad_rec);
			personad.setPdeDireccion(direccion_rec.toUpperCase());
			personad.setPdeCondicionCiudadana(condicion_ciudadana);
			personad.setPdeConyuge(conyuge);
			personad.setPdeFechaMatrimonio(fecha_matrimonio);
			personad.setPdeNombrePadre(nombre_pad.toUpperCase());
			personad.setPdeNacionalidadPadre(nacionalidad_pad);
			personad.setPdeNombreMadre(nombre_madre.toUpperCase());
			personad.setPdeNacionalidadMadre(nacionalidad_madre);
			personad.setPdeEmergContactoNombres(nombre_emergencia.toUpperCase());
			personad.setPdeEmergContactoId(id_emergencia);
			personad.setPdeEmergContactoTelefono(telefono_emergencia);
			personad.setPdeFechaDefuncion(fecha_defuncion);
			personad.setPdeInscripcionDefuncion(inscripcion_defuncion);
			personad.setPdeObservacion(observacion.toUpperCase());
			personad.setPdeEmergContactoTelefono2(telefono2_emergencia);
			personad.setPdeEmergContactoCorreo(correo_emergencia);
			personad.setPdeEstadiaDias(estadia_dias);
			personad.setPdeEstadiaHoras(estadia_horas);
			personad.setPdeResidencia(residencia);
			mngDao.insertar(personad);
			System.out.println("Bien_insertar_personaDetalle");
		} catch (Exception e) {
			System.out.println("Error_insertar_personaDetalle");
			e.printStackTrace();
		}
	}

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
	public void editarPersonaDetalle(String dni, String foto, String pais_nac, String provincia_nac, String ciudad_nac,
			String lugar_nac, String pais_rec, String provincia_rec, String ciudad_rec, String direccion_rec,
			String condicion_ciudadana, String conyuge, Date fecha_matrimonio, Integer num_hijos, String nombre_pad,
			String nacionalidad_pad, String nombre_madre, String nacionalidad_madre, String nombre_emergencia,
			String id_emergencia, String telefono_emergencia, String telefono2_emergencia, String correo_emergencia,
			String inscripcion_defuncion, Date fecha_defuncion, String observacion, String residencia,
			Integer estadia_dias, Integer estadia_horas) throws Exception {
		try {
			GenPersonaDetalle personad = this.PersonaDetalleByID(dni);
			personad.setPdeDni(dni);
			personad.setPdeNumHijos(num_hijos);
			personad.setPdePaisNacimiento(pais_nac);
			personad.setPdeProvinciaNacimiento(provincia_nac);
			personad.setPdeCiudadNacimiento(ciudad_nac);
			personad.setPdeLugarNacimiento(lugar_nac.toUpperCase());
			personad.setPdePaisResidencia(pais_rec);
			personad.setPdeFoto(foto);
			personad.setPdeProvinciaResidencia(provincia_rec);
			personad.setPdeCiudadResidencia(ciudad_rec);
			personad.setPdeDireccion(direccion_rec.toUpperCase());
			personad.setPdeCondicionCiudadana(condicion_ciudadana);
			personad.setPdeConyuge(conyuge);
			personad.setPdeFechaMatrimonio(fecha_matrimonio);
			personad.setPdeNombrePadre(nombre_pad.toUpperCase());
			personad.setPdeNacionalidadPadre(nacionalidad_pad);
			personad.setPdeNombreMadre(nombre_madre.toUpperCase());
			personad.setPdeNacionalidadMadre(nacionalidad_madre);
			personad.setPdeEmergContactoNombres(nombre_emergencia.toUpperCase());
			personad.setPdeEmergContactoId(id_emergencia);
			personad.setPdeEmergContactoTelefono(telefono_emergencia);
			personad.setPdeFechaDefuncion(fecha_defuncion);
			personad.setPdeInscripcionDefuncion(inscripcion_defuncion);
			personad.setPdeObservacion(observacion.toUpperCase());
			personad.setPdeEmergContactoTelefono2(telefono2_emergencia);
			personad.setPdeEmergContactoCorreo(correo_emergencia);
			personad.setPdeEstadiaDias(estadia_dias);
			personad.setPdeEstadiaHoras(estadia_horas);
			personad.setPdeResidencia(residencia);
			mngDao.actualizar(personad);
			System.out.println("Bien_mod_personaDetalle");
		} catch (Exception e) {
			System.out.println("Error_mod_personaDetalle");
			e.printStackTrace();
		}
	}

	// //////////////////////////////////////////////////////////(FAMILIARES)//////////////////////////////////////////////////////////////////

	/**
	 * Metodo para generar el id_familiares
	 * 
	 * @return
	 */
	public Integer familiarId() {
		Integer id = 0;
		try {
			id = (Integer) mngDao.ejectNativeSQL2("select max(fam_id) from gen_familiares limit 1;");
			if (id == null || id == 0) {
				id = 1;
			} else {
				id = id + 1;
			}
			return id;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<GenFamiliare> findAllFamiliares() throws Exception {
		return mngDao.findAll(GenFamiliare.class);
	}

	@SuppressWarnings("unchecked")
	public GenFamiliare findFamiliarByID(Integer id, String per_dni) throws Exception {
		List<GenFamiliare> l_familiares = mngDao.findWhere(GenFamiliare.class,
				"o.id.pdeDni='" + per_dni + "' and o.id.famId=" + id + "", "o.famNombre asc");
		if (l_familiares != null && l_familiares.size() > 0) {
			return l_familiares.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean FamiliarByConyuge(String per_dni) throws Exception {
		List<GenFamiliare> l_familiares = mngDao.findWhere(GenFamiliare.class,
				"o.id.pdeDni='" + per_dni + "' and o.famTipo='Conyuge'", "o.famNombre asc");
		if (l_familiares != null && l_familiares.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<GenFamiliare> familiarByDNI(String per_dni) throws Exception {
		List<GenFamiliare> l_familiares = mngDao.findWhere(GenFamiliare.class, "o.id.pdeDni='" + per_dni + "'",
				"o.famNombre asc");
		if (l_familiares != null && l_familiares.size() > 0) {
			return l_familiares;
		} else {
			return null;
		}
	}

	public void insertarFamiliar(GenFamiliare familiar) {
		try {
			mngDao.insertar(familiar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editarFamiliar(GenFamiliare familiar) {
		try {
			mngDao.actualizar(familiar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void eliminarFamiliar(GenFamiliare familiar) {
		try {
			mngDao.eliminar(GenFamiliare.class, familiar.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// //////////////////////////////////////////////////////////(SALUD)/////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla SALUD
	 * 
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenSalud> findAllSalud() throws Exception {
		return mngDao.findAll(GenSalud.class);
	}

	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public GenSalud SaludByID(String dni) throws Exception {
		return (GenSalud) mngDao.findById(GenSalud.class, dni);
	}// Cierre del metodo

	/**
	 * Metodo para ingresar un Atributo a la base de datos
	 * 
	 * @param dni
	 * @param alergias
	 * @param altura
	 * @param asegurado
	 * @param carnet
	 * @param con_alcohol
	 * @param con_tabaco
	 * @param dis_tipo
	 * @param dis_grado
	 * @param con_medicina
	 * @param gru_sanguineo
	 * @param medicamentos
	 * @param niv_azucar
	 * @param ejercicios
	 * @param peso
	 * @param presion
	 * @param rea_ejercicio
	 * @param vegetariano
	 * @throws Exception
	 */
	public void insertarSalud(String dni, String alergias, BigDecimal altura, String asegurado, String carnet,
			String con_alcohol, String con_tabaco, String dis_tipo, String dis_grado, String con_medicina,
			String gru_sanguineo, String medicamentos_cronicos1, String medicamentos_cronicos2, String niv_azucar,
			String ejercicios, BigDecimal peso, String presion, Boolean rea_ejercicio, Boolean vegetariano,
			String alergias2, Boolean embriagar, String m_muerte, Integer m_edad, String m_enfermedades,
			Boolean m_fallecio, String medico, String observacion, String p_muerte, Integer p_edad,
			String p_enfermedades, Boolean p_fallecio, String periodicidad_alcohol, String periodicidad_embriaga,
			String periodicidad_tabaco, Boolean estupefacientes, String periodicidad_estupefacientes, boolean seg_iess,
			boolean seg_privado, boolean discapacidad, String ejercicio_horas, String tabaco_semana,
			String enfermedades_cronicas3, String medicamentos_cronicos3) throws Exception {
		try {
			GenSalud salud = new GenSalud();
			setearCamposSalud(dni, alergias, altura, asegurado, carnet, con_alcohol, con_tabaco, dis_tipo, dis_grado,
					con_medicina, gru_sanguineo, medicamentos_cronicos1, medicamentos_cronicos2, niv_azucar, ejercicios,
					peso, presion, rea_ejercicio, vegetariano, alergias2, embriagar, m_muerte, m_edad, m_enfermedades,
					m_fallecio, medico, observacion, p_muerte, p_edad, p_enfermedades, p_fallecio, periodicidad_alcohol,
					periodicidad_embriaga, periodicidad_tabaco, estupefacientes, periodicidad_estupefacientes, seg_iess,
					seg_privado, discapacidad, ejercicio_horas, tabaco_semana, enfermedades_cronicas3,
					medicamentos_cronicos3, salud);
			mngDao.insertar(salud);
			System.out.println("Bien_insertar_salud");
		} catch (Exception e) {
			System.out.println("Error_insertar_salud");
			e.printStackTrace();
		}
	}

	private void setearCamposSalud(String dni, String alergias, BigDecimal altura, String asegurado, String carnet,
			String con_alcohol, String con_tabaco, String dis_tipo, String dis_grado, String con_medicina,
			String gru_sanguineo, String medicamentos_cronicos1, String medicamentos_cronicos2, String niv_azucar,
			String ejercicios, BigDecimal peso, String presion, Boolean rea_ejercicio, Boolean vegetariano,
			String alergias2, Boolean embriagar, String m_muerte, Integer m_edad, String m_enfermedades,
			Boolean m_fallecio, String medico, String observacion, String p_muerte, Integer p_edad,
			String p_enfermedades, Boolean p_fallecio, String periodicidad_alcohol, String periodicidad_embriaga,
			String periodicidad_tabaco, Boolean estupefacientes, String periodicidad_estupefacientes, boolean seg_iess,
			boolean seg_privado, boolean discapacidad, String ejercicio_horas, String tabaco_semana,
			String enfermedades_cronicas3, String medicamentos_cronicos3, GenSalud salud) {
		salud.setPerDni(dni);
		salud.setSldAlergias(alergias);
		salud.setSldAltura(altura);
		salud.setSldAsegurado(asegurado);
		salud.setSldCarnetConadies(carnet);
		salud.setSldConsumeAlcohol(con_alcohol);
		salud.setSldConsumeTabaco(con_tabaco);
		salud.setSldDiscapacidadGrado(dis_grado);
		salud.setSldDiscapacidadTipo(dis_tipo);
		salud.setSldFrecuenciaConsumoMedicame(con_medicina);
		salud.setSldGrupoSanguineo(gru_sanguineo);
		salud.setSldMedicamentos(medicamentos_cronicos1);
		salud.setSldMedicamentosCronicos2(medicamentos_cronicos2);
		salud.setSldNivelAzucar(niv_azucar);
		salud.setSldPeriodicidadEjercicio(ejercicios);
		salud.setSldPeso(peso);
		salud.setSldPresion(presion);
		salud.setSldRealizaEjercicio(rea_ejercicio);
		salud.setSldVegetariano(vegetariano);
		salud.setSldAlergiasCronicas2(alergias2);
		salud.setSldEmbriagar(embriagar);
		salud.setSldMadreCausaMuerte(m_muerte);
		salud.setSldMadreEdad(m_edad);
		salud.setSldMadreEnfermedadesActuales(m_enfermedades);
		salud.setSldMadreFallecio(m_fallecio);
		salud.setSldNombreLugarCentroMedico(medico);
		salud.setSldObservaciones(observacion.toUpperCase());
		salud.setSldPadreCausaMuerte(p_muerte);
		salud.setSldPadreEdad(p_edad);
		salud.setSldPadreEnfermedadesActuales(p_enfermedades);
		salud.setSldPadreFallecio(p_fallecio);
		salud.setSldPeriodicidadAlcohol(periodicidad_alcohol);
		salud.setSldPeriodicidadEmbriagar(periodicidad_embriaga);
		salud.setSldPeriodicidadTabaco(periodicidad_tabaco);
		salud.setSldEstupefacientes(estupefacientes);
		salud.setSldPeriodicidadEstupefacientes(periodicidad_estupefacientes);
		salud.setSldSeguroIess(seg_iess);
		salud.setSldSeguroPrivado(seg_privado);
		salud.setSldDiscapacidad(discapacidad);
		salud.setSldEjercicioHoras(Integer.parseInt(cambiarNulosInteger(ejercicio_horas)));
		salud.setSldTabacoSemana(Integer.parseInt(cambiarNulosInteger(tabaco_semana)));
		salud.setSldAlergiasCronicas3(enfermedades_cronicas3);
		salud.setSldMedicamentosCronicos3(medicamentos_cronicos3);
	}

	/**
	 * Metodo para editar un Atributo en la base de datos
	 * 
	 * @param dni
	 * @param alergias
	 * @param altura
	 * @param asegurado
	 * @param carnet
	 * @param con_alcohol
	 * @param con_tabaco
	 * @param dis_tipo
	 * @param dis_grado
	 * @param con_medicina
	 * @param gru_sanguineo
	 * @param medicamentos
	 * @param niv_azucar
	 * @param ejercicios
	 * @param peso
	 * @param presion
	 * @param rea_ejercicio
	 * @param vegetariano
	 * @throws Exception
	 */
	public void editarSalud(String dni, String alergias, BigDecimal altura, String asegurado, String carnet,
			String con_alcohol, String con_tabaco, String dis_tipo, String dis_grado, String con_medicina,
			String gru_sanguineo, String medicamentos_cronicos1, String medicamentos_cronicos2, String niv_azucar,
			String ejercicios, BigDecimal peso, String presion, Boolean rea_ejercicio, Boolean vegetariano,
			String alergias2, Boolean embriagar, String m_muerte, Integer m_edad, String m_enfermedades,
			Boolean m_fallecio, String medico, String observacion, String p_muerte, Integer p_edad,
			String p_enfermedades, Boolean p_fallecio, String periodicidad_alcohol, String periodicidad_embriaga,
			String periodicidad_tabaco, Boolean estupefacientes, String periodicidad_estupefacientes, boolean seg_iess,
			boolean seg_privado, boolean discapacidad, String ejercicio_horas, String tabaco_semana,
			String enfermedades_cronicas3, String medicamentos_cronicos3) throws Exception {
		try {
			GenSalud salud = this.SaludByID(dni);
			if (ejercicio_horas == null || ejercicio_horas.equals("null")) {
				ejercicio_horas = "0";
			}
			if (tabaco_semana == null || tabaco_semana.equals("null")) {
				tabaco_semana = "0";
			}
			setearCamposSalud(dni, alergias, altura, asegurado, carnet, con_alcohol, con_tabaco, dis_tipo, dis_grado,
					con_medicina, gru_sanguineo, medicamentos_cronicos1, medicamentos_cronicos2, niv_azucar, ejercicios,
					peso, presion, rea_ejercicio, vegetariano, alergias2, embriagar, m_muerte, m_edad, m_enfermedades,
					m_fallecio, medico, observacion, p_muerte, p_edad, p_enfermedades, p_fallecio, periodicidad_alcohol,
					periodicidad_embriaga, periodicidad_tabaco, estupefacientes, periodicidad_estupefacientes, seg_iess,
					seg_privado, discapacidad, ejercicio_horas, tabaco_semana, enfermedades_cronicas3,
					medicamentos_cronicos3, salud);
			mngDao.actualizar(salud);
			System.out.println("Bien_mod_salud");
		} catch (Exception e) {
			System.out.println("Error_mod_salud");
			e.printStackTrace();
		}
	}
	
	public void editarSalud2(GenSalud s){
		try {
			mngDao.actualizar(s);
			System.out.println("Actualización correcta -- >"+s.getPerDni());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String cambiarNulosInteger(String campo) {
		if (campo == null)
			campo = "0";
		return campo;
	}

	/********** Metodos para manejo de información de CV's **********/
	/**
	 * Buscar todos los registros de Formación Académica por cédula
	 * 
	 * @param cedula
	 * @return List<GenFormacionacademica>
	 */
	@SuppressWarnings("unchecked")
	public List<GenFormacionacademica> findFormAcademicaBYCedula(String cedula) {
		return mngDao.findWhere(GenFormacionacademica.class, "o.genPersona.perDni='" + cedula + "'", "o.foaId");
	}

	/**
	 * Buscar todos los registros de Capacitaciones por cédula
	 * 
	 * @param cedula
	 * @return List<GenCapacitacione>
	 */
	@SuppressWarnings("unchecked")
	public List<GenCapacitacione> findCapacitacionesByCedula(String cedula) {
		return mngDao.findWhere(GenCapacitacione.class, "o.genPersona.perDni='" + cedula + "'", "o.capId");
	}

	/**
	 * Buscar todos los registros de ExperienciaLaboral por cédula
	 * 
	 * @param cedula
	 * @return List<GenExperiencialaboral>
	 */
	@SuppressWarnings("unchecked")
	public List<GenExperiencialaboral> findExperienciaLabByCedula(String cedula) {
		return mngDao.findWhere(GenExperiencialaboral.class, "o.genPersona.perDni='" + cedula + "'", "o.exlId");
	}

	// Formación Académica

	/**
	 * Ingresar información académica
	 * 
	 * @param persona
	 * @param areaL
	 * @param titulo
	 * @param institucion
	 * @param fechaInicio
	 * @param fechaFin
	 * @param actual
	 * @param nivelI
	 * @param pais
	 * @param duracion
	 * @throws Exception
	 */
	public void ingresarFormacionAc(GenPersona persona, String titulo, String institucion, Date fechaInicio,
			Date fechaFin, String nivelI, String pais, BigDecimal duracion, boolean registroS, String nivelesAprob)
			throws Exception {
		GenFormacionacademica fa = new GenFormacionacademica();
		fa.setFoaTitulo(titulo);
		fa.setFoaInstitucion(institucion);
		fa.setFoaFechaInicio(fechaInicio);
		fa.setFoaFechaFin(fechaFin);
		fa.setFoaNivelInstruccion(nivelI);
		fa.setFoaDuracion(duracion);
		fa.setFoaPais(pais);
		fa.setGenPersona(persona);
		fa.setFoaRegistroSenescyt(registroS);
		fa.setFoaNivelesAprobados(nivelesAprob);
		mngDao.insertar(fa);
		fa = null;
	}

	/**
	 * Editar información académica
	 * 
	 * @param idF
	 * @param areaL
	 * @param titulo
	 * @param institucion
	 * @param fechaInicio
	 * @param fechaFin
	 * @param actual
	 * @param nivelI
	 * @param pais
	 * @param duracion
	 * @throws Exception
	 */
	public void editarFormacionAc(Integer idF, String titulo, String institucion, Date fechaInicio, Date fechaFin,
			String nivelI, String pais, BigDecimal duracion, String nivelesAprob) throws Exception {
		GenFormacionacademica fa = this.findFormAcademicaById(idF);
		fa.setFoaTitulo(titulo);
		fa.setFoaInstitucion(institucion);
		fa.setFoaFechaInicio(fechaInicio);
		fa.setFoaFechaFin(fechaFin);
		fa.setFoaNivelInstruccion(nivelI);
		fa.setFoaDuracion(duracion);
		fa.setFoaPais(pais);
		fa.setFoaNivelesAprobados(nivelesAprob);
		mngDao.actualizar(fa);
		fa = null;
	}

	/**
	 * Buscar Formación Académica por ID
	 * 
	 * @param idFormacion
	 * @return
	 * @throws Exception
	 */
	public GenFormacionacademica findFormAcademicaById(int idFormacion) throws Exception {
		return (GenFormacionacademica) mngDao.findById(GenFormacionacademica.class, idFormacion);
	}

	/**
	 * Eliminar formación Académica
	 * 
	 * @param fa
	 * @throws Exception
	 */
	public void eliminarFormAcademica(GenFormacionacademica fa) throws Exception {
		mngDao.eliminar(GenFormacionacademica.class, fa.getFoaId());
	}

	// Capacitaciones
	/**
	 * Ingresar Capacitaciones
	 * 
	 * @param persona
	 * @param nombre
	 * @param areaL
	 * @param tipoEvento
	 * @param fechaInicio
	 * @param fechaFin
	 * @param numHoras
	 * @throws Exception
	 */
	public void ingresarCapacitaciones(GenPersona persona, boolean relacionPerfil, String nombre, String institucion,
			String areaL, String tipoEvento, int numHoras) throws Exception {
		GenCapacitacione ca = new GenCapacitacione();
		ca.setGenPersona(persona);
		ca.setCapRelacionPerfilProfesional(relacionPerfil);
		ca.setCapNombre(nombre);
		ca.setCapInstitucionCapacitacion(institucion);
		ca.setCapAreaLaboralEstudio(areaL);
		ca.setCapTipoEvento(tipoEvento);
		ca.setCapNumHoras(numHoras);
		mngDao.insertar(ca);
	}

	/**
	 * Editar Capacitación
	 * 
	 * @param idCa
	 * @param nombre
	 * @param areaL
	 * @param tipoEvento
	 * @param fechaInicio
	 * @param fechaFin
	 * @param numHoras
	 * @throws Exception
	 */
	public void editarCapacitaciones(Integer idCa, boolean relacionPerfil, String nombre, String institucion,
			String areaL, String tipoEvento, int numHoras) throws Exception {
		GenCapacitacione ca = this.findCapacitacionesById(idCa);
		ca.setCapRelacionPerfilProfesional(relacionPerfil);
		ca.setCapNombre(nombre);
		ca.setCapInstitucionCapacitacion(institucion);
		ca.setCapAreaLaboralEstudio(areaL);
		ca.setCapTipoEvento(tipoEvento);
		ca.setCapNumHoras(numHoras);
		mngDao.actualizar(ca);
	}

	/**
	 * Buscar Capacitación por ID
	 * 
	 * @param idCapacitaciones
	 * @return
	 * @throws Exception
	 */
	public GenCapacitacione findCapacitacionesById(int idCapacitaciones) throws Exception {
		return (GenCapacitacione) mngDao.findById(GenCapacitacione.class, idCapacitaciones);
	}

	/**
	 * Eliminar Capacitación por ID
	 * 
	 * @param ca
	 * @throws Exception
	 */
	public void eliminarCapacitaciones(GenCapacitacione ca) throws Exception {
		mngDao.eliminar(GenCapacitacione.class, ca.getCapId());
	}

	// Experiencia Laboral

	public void ingresarExperienciaLab(GenPersona persona, String areaL, String puesto, String empresa,
			Boolean sectorpublico, String pais, Date fechaInicio, Date fechaFin, String responsabilidades,
			Boolean actual) throws Exception {
		GenExperiencialaboral exl = new GenExperiencialaboral();
		exl.setGenPersona(persona);
		exl.setExlAreaLaboralEstudio(areaL);
		exl.setExlPuesto(puesto);
		exl.setExlEmpresa(empresa);
		exl.setExlSectorPublico(sectorpublico);
		exl.setExlPais(pais);
		exl.setExlFechaInicio(fechaInicio);
		exl.setExlFechaFin(fechaFin);
		exl.setExlResponsabilidades(responsabilidades);
		exl.setExlActual(actual);
		mngDao.insertar(exl);
	}

	/**
	 * Editar Experiencia Laboral
	 * 
	 * @param idExp
	 * @param areaL
	 * @param puesto
	 * @param empresa
	 * @param sectorpublico
	 * @param pais
	 * @param fechaInicio
	 * @param fechaFin
	 * @param responsabilidades
	 * @param actual
	 * @throws Exception
	 */
	public void editarExperienciaLab(Integer idExp, String areaL, String puesto, String empresa, Boolean sectorpublico,
			String pais, Date fechaInicio, Date fechaFin, String responsabilidades, Boolean actual) throws Exception {
		GenExperiencialaboral exl = this.findExperienciaLabById(idExp);
		exl.setExlAreaLaboralEstudio(areaL);
		exl.setExlPuesto(puesto);
		exl.setExlEmpresa(empresa);
		exl.setExlSectorPublico(sectorpublico);
		exl.setExlPais(pais);
		exl.setExlFechaInicio(fechaInicio);
		exl.setExlFechaFin(fechaFin);
		exl.setExlResponsabilidades(responsabilidades);
		exl.setExlActual(actual);
		mngDao.actualizar(exl);
	}

	public GenExperiencialaboral findExperienciaLabById(int idExperienciaLab) throws Exception {
		return (GenExperiencialaboral) mngDao.findById(GenExperiencialaboral.class, idExperienciaLab);
	}

	public void eliminarExperienciaLab(GenExperiencialaboral el) throws Exception {
		mngDao.eliminar(GenExperiencialaboral.class, el.getExlId());
	}

	public String catalogoItem(String idItem) throws Exception {
		System.out.println("valor Item-->" + idItem);
		if (idItem == null || idItem.equals("")) {
			return "";
		} else {
			GenCatalogoItemsDet it = this.ItemByID(idItem);
			if (it == null || it.equals("")) {
				return "";
			} else
				return it.getIteNombre();
		}
	}

	/////////////////////////////////////////////////// (GRÁFICO)/////////////////////////////////////////////////////////

	public Integer obtencionDatos(Integer edadi, Integer edadf, String genero, String ins, String per) {
		Integer valor = 0;
		ArrayList<Integer> edades = new ArrayList<Integer>();
		List<GenPersona> l_persona = listarSexoEdad(genero, ins, per);
		if (l_persona == null || l_persona.isEmpty()) {
			valor = -1;
		} else {
			for (GenPersona persona : listarSexoEdad(genero, ins, per)) {
				edades.add(edadXFecha(persona.getPerFechaNacimiento()));
			}
			for (Integer edad : edades) {
				if (edad >= edadi && edad <= edadf) {
					valor += 1;
				}
			}
		}
		return valor;
	}

	public List<GenPersona> listarSexoEdad(String genero, String ins, String per) {
		List<GenPersona> lp = new ArrayList<GenPersona>();
		List<Object> lista = new ArrayList<Object>();
		if (ins.equals("all")) {
			lista = ListaTodos(genero, per);
		} else if (ins.equals(INS_YACHAYEP)) {
			lista = ListaYachayEP(genero, per);
		} else if (ins.equals(INS_YACHAYTECH)) {
			lista = ListaYachayT(genero, per);
		} else if (ins.equals(INS_IST)) {
			lista = ListaIST(genero, per);
		} else if (ins.equals(INS_CIUDAD)) {
			lista = ListaCiudad(genero, per);
		}
		if (lista != null)
			lp = ObjectToGenPersona(lista);
		return lp;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaTodos(String genero, String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3("select * from gen_persona where per_genero='" + genero.trim() + "';");
		} else if (persona.equals(PER_COM)) {
			lista = mngDao.ejectNativeSQL3("select * from gen_persona where per_genero='" + genero.trim()
					+ "' and per_dni in (select per_dni from gen_externos where ext_tipo='Comunidad');");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3("select * from gen_persona where per_genero='" + genero.trim()
					+ "' and per_dni in (select per_dni from gen_estudiante_institucion);");
		} else if (persona.equals(PER_EXT)) {
			lista = mngDao.ejectNativeSQL3("select * from gen_persona where per_genero='" + genero.trim()
					+ "' and per_dni in (select per_dni from gen_externos where ext_tipo!='Comunidad');");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3("select * from gen_persona where per_genero='" + genero.trim()
					+ "' and per_dni in (select per_dni from gen_funcionarios_institucion where fun_estado='A');");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaYachayT(String genero, String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3("select * from gen_persona p where p.per_genero='" + genero.trim()
					+ "' and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='2' and f.fun_estado='A') or p.per_genero='"
					+ genero.trim()
					+ "' and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='2' and e.est_estado='A');");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3("select * from gen_persona p where p.per_genero='" + genero.trim()
					+ "' and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='2' and f.fun_estado='A');");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3("select * from gen_persona p where p.per_genero='" + genero.trim()
					+ "' and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='2' and e.est_estado='A');");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaIST(String genero, String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3("select * from gen_persona p where p.per_genero='" + genero.trim()
					+ "' and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='3' and f.fun_estado='A') or p.per_genero='"
					+ genero.trim()
					+ "' and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='3' and e.est_estado='A');");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3("select * from gen_persona p where p.per_genero='" + genero.trim()
					+ "' and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='3' and f.fun_estado='A');");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3("select * from gen_persona p where p.per_genero='" + genero.trim()
					+ "' and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='3' and e.est_estado='A');");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaYachayEP(String genero, String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3("select * from gen_persona where per_genero='" + genero.trim()
					+ "' and per_dni in (select per_dni from gen_funcionarios_institucion where fun_estado='A');");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3("select * from gen_persona where per_genero='" + genero.trim()
					+ "' and per_dni in (select per_dni from gen_funcionarios_institucion where fun_estado='A');");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaCiudad(String genero, String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3("select * from gen_persona p where p.per_genero='" + genero.trim()
					+ "' and p.per_dni in (select per_dni from gen_externos);");
		} else if (persona.equals(PER_COM)) {
			lista = mngDao.ejectNativeSQL3("select * from gen_persona p where p.per_genero='" + genero.trim()
					+ "' and p.per_dni in (select per_dni from gen_externos where ext_tipo='Comunidad');");
		} else if (persona.equals(PER_EXT)) {
			lista = mngDao.ejectNativeSQL3("select * from gen_persona p where p.per_genero='" + genero.trim()
					+ "' and p.per_dni in (select per_dni from gen_externos where ext_tipo!='Comunidad');");
		}
		return lista;
	}

	private List<GenPersona> ObjectToGenPersona(List<Object> lista) {
		List<GenPersona> l_persona = new ArrayList<GenPersona>();
		try {
			Iterator it = lista.iterator();
			while (it.hasNext()) {
				GenPersona p = new GenPersona();
				Object[] obj = (Object[]) it.next();
				p.setPerDni(String.valueOf(obj[0]));
				p.setPerGenero(String.valueOf(obj[5]));
				Date d = Funciones.stringToDateF(String.valueOf(obj[4]).trim());
				p.setPerFechaNacimiento(d);
				l_persona.add(p);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l_persona;
	}

	public Integer edadXFecha(Date fecha) {
		Integer v = 0;
		if (fecha != null) {
			String f = Funciones.dateToString(fecha);
			v = edad(f);
		}
		return v;

	}

	public Integer edad(String fecha_nac) { // fecha_nac debe tener el formato
											// dd/MM/yyyy
		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String hoy = formato.format(fechaActual);
		String[] dat1 = fecha_nac.split("-");
		String[] dat2 = hoy.split("-");
		int anos = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
		int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
		if (mes < 0) {
			anos = anos - 1;
		} else if (mes == 0) {
			int dia = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
			if (dia > 0) {
				anos = anos - 1;
			}
		}
		return anos;
	}

	//////////////////////////////////////////////// (GRAFICO_SANGUINEO)////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<Sangre> listaSanguinea(String ins, String per) {
		List<Sangre> lp = new ArrayList<Sangre>();
		List<Object> lista = new ArrayList<Object>();
		if (ins.equals("all")) {
			lista = ListaTodosSanguineo(per);
		} else if (ins.equals(INS_YACHAYEP)) {
			lista = ListaYachayEPSanguineo(per);
		} else if (ins.equals(INS_YACHAYTECH)) {
			lista = ListaYachayTSanguineo(per);
		} else if (ins.equals(INS_IST)) {
			lista = ListaISTSanguineo(per);
		} else if (ins.equals(INS_CIUDAD)) {
			lista = ListaCiudadSanguineo(per);
		}
		if (lista != null)
			lp = ObjectToSangre(lista);
		return lp;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaTodosSanguineo(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_grupo_sanguineo, count(*) as total from gen_persona p, gen_salud s  where p.per_dni = s.per_dni group by p.per_genero, s.sld_grupo_sanguineo;");
		} else if (persona.equals(PER_COM)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_grupo_sanguineo, count(*) as total from gen_persona p, gen_salud s, gen_externos e where e.ext_tipo='Comunidad' and p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, s.sld_grupo_sanguineo;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_grupo_sanguineo, count(*) as total from gen_persona p, gen_salud s, gen_estudiante_institucion e where p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, s.sld_grupo_sanguineo;");
		} else if (persona.equals(PER_EXT)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_grupo_sanguineo, count(*) as total from gen_persona p, gen_salud s, gen_externos e where p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, s.sld_grupo_sanguineo;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_grupo_sanguineo, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, s.sld_grupo_sanguineo;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaYachayEPSanguineo(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_grupo_sanguineo, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, s.sld_grupo_sanguineo;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_grupo_sanguineo, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, s.sld_grupo_sanguineo;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaYachayTSanguineo(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_grupo_sanguineo, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='2' and e.est_estado='A') or p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='2' and f.fun_estado='A') group by p.per_genero, s.sld_grupo_sanguineo;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_grupo_sanguineo, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='2' and f.fun_estado='A') group by p.per_genero, s.sld_grupo_sanguineo;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_grupo_sanguineo, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='2' and e.est_estado='A') group by p.per_genero, s.sld_grupo_sanguineo;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaISTSanguineo(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_grupo_sanguineo, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='3' and e.est_estado='A') or p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='3' and f.fun_estado='A') group by p.per_genero, s.sld_grupo_sanguineo;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_grupo_sanguineo, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='3' and f.fun_estado='A') group by p.per_genero, s.sld_grupo_sanguineo;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_grupo_sanguineo, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='3' and e.est_estado='A') group by p.per_genero, s.sld_grupo_sanguineo;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaCiudadSanguineo(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_grupo_sanguineo, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e )  group by p.per_genero, s.sld_grupo_sanguineo;");
		} else if (persona.equals(PER_COM)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_grupo_sanguineo, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e where e.ext_tipo='Comunidad')  group by p.per_genero, s.sld_grupo_sanguineo;");
		} else if (persona.equals(PER_EXT)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_grupo_sanguineo, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e where e.ext_tipo!='Comunidad')  group by p.per_genero, s.sld_grupo_sanguineo;");
		}
		return lista;
	}

	private List<Sangre> ObjectToSangre(List<Object> lista) {
		List<Sangre> l_sangre = new ArrayList<Sangre>();
		Iterator it = lista.iterator();
		while (it.hasNext()) {
			Sangre s = new Sangre();
			Object[] obj = (Object[]) it.next();
			s.setSan_genero(String.valueOf(obj[0]));
			s.setSan_tipo(String.valueOf(obj[1]));
			s.setSan_cantidad(Integer.parseInt(String.valueOf(obj[2])));
			l_sangre.add(s);
		}
		return l_sangre;
	}

	//////////////////////////////////////////////// (GRAFICO_EJERCICIO)////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<GenericClassBoolean> listaEjercicio(String ins, String per) {
		List<GenericClassBoolean> lp = new ArrayList<GenericClassBoolean>();
		List<Object> lista = new ArrayList<Object>();
		if (ins.equals("all")) {
			lista = ListaTodosEje(per);
		} else if (ins.equals(INS_YACHAYEP)) {
			lista = ListaYachayEPEje(per);
		} else if (ins.equals(INS_YACHAYTECH)) {
			lista = ListaYachayTEje(per);
		} else if (ins.equals(INS_IST)) {
			lista = ListaISTEje(per);
		} else if (ins.equals(INS_CIUDAD)) {
			lista = ListaCiudadEje(per);
		}
		if (lista != null)
			lp = ObjectToClass(lista);
		return lp;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaTodosEje(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_realiza_ejercicio, count(*) as total from gen_persona p, gen_salud s  where p.per_dni = s.per_dni group by p.per_genero, s.sld_realiza_ejercicio;");
		} else if (persona.equals(PER_COM)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_realiza_ejercicio, count(*) as total from gen_persona p, gen_salud s, gen_externos e where e.ext_tipo='Comunidad' and p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, s.sld_realiza_ejercicio;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_realiza_ejercicio, count(*) as total from gen_persona p, gen_salud s, gen_estudiante_institucion e where p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, s.sld_realiza_ejercicio;");
		} else if (persona.equals(PER_EXT)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_realiza_ejercicio, count(*) as total from gen_persona p, gen_salud s, gen_externos e where p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, s.sld_realiza_ejercicio;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_realiza_ejercicio, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, s.sld_realiza_ejercicio;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaYachayEPEje(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_realiza_ejercicio, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, s.sld_realiza_ejercicio;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_realiza_ejercicio, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, s.sld_realiza_ejercicio;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaYachayTEje(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_realiza_ejercicio, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='2' and e.est_estado='A') or p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='2' and f.fun_estado='A') group by p.per_genero, s.sld_realiza_ejercicio;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_realiza_ejercicio, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='2' and f.fun_estado='A') group by p.per_genero, s.sld_realiza_ejercicio;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_realiza_ejercicio, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='2' and e.est_estado='A') group by p.per_genero, s.sld_realiza_ejercicio;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaISTEje(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_realiza_ejercicio, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='3' and e.est_estado='A') or p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='3' and f.fun_estado='A') group by p.per_genero, s.sld_realiza_ejercicio;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_realiza_ejercicio, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='3' and f.fun_estado='A') group by p.per_genero, s.sld_realiza_ejercicio;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_realiza_ejercicio, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='3' and e.est_estado='A') group by p.per_genero, s.sld_realiza_ejercicio;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaCiudadEje(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_realiza_ejercicio, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e )  group by p.per_genero, s.sld_realiza_ejercicio;");
		} else if (persona.equals(PER_COM)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_realiza_ejercicio, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e where e.ext_tipo='Comunidad')  group by p.per_genero, s.sld_realiza_ejercicio;");
		} else if (persona.equals(PER_EXT)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_realiza_ejercicio, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e where e.ext_tipo!='Comunidad')  group by p.per_genero, s.sld_realiza_ejercicio;");
		}
		return lista;
	}

	//////////////////////////////////////////////// (GRAFICO_ALCOHOL)////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<GenericClassBoolean> listaAlcohol(String ins, String per) {
		List<GenericClassBoolean> lp = new ArrayList<GenericClassBoolean>();
		List<Object> lista = new ArrayList<Object>();
		if (ins.equals("all")) {
			lista = ListaTodosAlc(per);
		} else if (ins.equals(INS_YACHAYEP)) {
			lista = ListaYachayEPAlc(per);
		} else if (ins.equals(INS_YACHAYTECH)) {
			lista = ListaYachayTAlc(per);
		} else if (ins.equals(INS_IST)) {
			lista = ListaISTAlc(per);
		} else if (ins.equals(INS_CIUDAD)) {
			lista = ListaCiudadAlc(per);
		}
		if (lista != null)
			lp = ObjectToClass(lista);
		return lp;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaTodosAlc(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_alcohol, count(*) as total from gen_persona p, gen_salud s  where p.per_dni = s.per_dni group by p.per_genero, sld_consume_alcohol;");
		} else if (persona.equals(PER_COM)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_alcohol, count(*) as total from gen_persona p, gen_salud s, gen_externos e where e.ext_tipo='Comunidad' and p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, sld_consume_alcohol;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_alcohol, count(*) as total from gen_persona p, gen_salud s, gen_estudiante_institucion e where p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, sld_consume_alcohol;");
		} else if (persona.equals(PER_EXT)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_alcohol, count(*) as total from gen_persona p, gen_salud s, gen_externos e where p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, sld_consume_alcohol;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_alcohol, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, sld_consume_alcohol;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaYachayEPAlc(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_alcohol, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, sld_consume_alcohol;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_alcohol, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, sld_consume_alcohol;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaYachayTAlc(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_alcohol, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='2' and e.est_estado='A') or p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='2' and f.fun_estado='A') group by p.per_genero, sld_consume_alcohol;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_alcohol, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='2' and f.fun_estado='A') group by p.per_genero, sld_consume_alcohol;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_alcohol, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='2' and e.est_estado='A') group by p.per_genero, sld_consume_alcohol;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaISTAlc(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_alcohol, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='3' and e.est_estado='A') or p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='3' and f.fun_estado='A') group by p.per_genero, sld_consume_alcohol;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_alcohol, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='3' and f.fun_estado='A') group by p.per_genero, sld_consume_alcohol;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_alcohol, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='3' and e.est_estado='A') group by p.per_genero, sld_consume_alcohol;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaCiudadAlc(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_alcohol, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e )  group by p.per_genero, sld_consume_alcohol;");
		} else if (persona.equals(PER_COM)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_alcohol, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e where e.ext_tipo='Comunidad')  group by p.per_genero, sld_consume_alcohol;");
		} else if (persona.equals(PER_EXT)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_alcohol, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e where e.ext_tipo!='Comunidad')  group by p.per_genero, sld_consume_alcohol;");
		}
		return lista;
	}

	//////////////////////////////////////////////// (GRAFICO_EMBRIAGUEZ)////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<GenericClassBoolean> listaEmbriaguez(String ins, String per) {
		List<GenericClassBoolean> lp = new ArrayList<GenericClassBoolean>();
		List<Object> lista = new ArrayList<Object>();
		if (ins.equals("all")) {
			lista = ListaTodosEmb(per);
		} else if (ins.equals(INS_YACHAYEP)) {
			lista = ListaYachayEPEmb(per);
		} else if (ins.equals(INS_YACHAYTECH)) {
			lista = ListaYachayTEmb(per);
		} else if (ins.equals(INS_IST)) {
			lista = ListaISTEmb(per);
		} else if (ins.equals(INS_CIUDAD)) {
			lista = ListaCiudadEmb(per);
		}
		if (lista != null)
			lp = ObjectToClass(lista);
		return lp;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaTodosEmb(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_embriagar, count(*) as total from gen_persona p, gen_salud s  where p.per_dni = s.per_dni group by p.per_genero, sld_embriagar;");
		} else if (persona.equals(PER_COM)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_embriagar, count(*) as total from gen_persona p, gen_salud s, gen_externos e where e.ext_tipo='Comunidad' and p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, sld_embriagar;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_embriagar, count(*) as total from gen_persona p, gen_salud s, gen_estudiante_institucion e where p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, sld_embriagar;");
		} else if (persona.equals(PER_EXT)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_embriagar, count(*) as total from gen_persona p, gen_salud s, gen_externos e where p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, sld_embriagar;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_embriagar, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, sld_embriagar;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaYachayEPEmb(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_embriagar, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, sld_embriagar;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_embriagar, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, sld_embriagar;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaYachayTEmb(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_embriagar, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='2' and e.est_estado='A') or p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='2' and f.fun_estado='A') group by p.per_genero, sld_embriagar;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_embriagar, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='2' and f.fun_estado='A') group by p.per_genero, sld_embriagar;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_embriagar, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='2' and e.est_estado='A') group by p.per_genero, sld_embriagar;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaISTEmb(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_embriagar, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='3' and e.est_estado='A') or p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='3' and f.fun_estado='A') group by p.per_genero, sld_embriagar;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_embriagar, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='3' and f.fun_estado='A') group by p.per_genero, sld_embriagar;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_embriagar, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='3' and e.est_estado='A') group by p.per_genero, sld_embriagar;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaCiudadEmb(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_embriagar, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e )  group by p.per_genero, sld_embriagar;");
		} else if (persona.equals(PER_COM)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_embriagar, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e where e.ext_tipo='Comunidad')  group by p.per_genero, sld_embriagar;");
		} else if (persona.equals(PER_EXT)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_embriagar, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e where e.ext_tipo!='Comunidad')  group by p.per_genero, sld_embriagar;");
		}
		return lista;
	}

	//////////////////////////////////////////////// (GRAFICO_TABACO)////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<GenericClassBoolean> listaTabaco(String ins, String per) {
		List<GenericClassBoolean> lp = new ArrayList<GenericClassBoolean>();
		List<Object> lista = new ArrayList<Object>();
		if (ins.equals("all")) {
			lista = ListaTodosTab(per);
		} else if (ins.equals(INS_YACHAYEP)) {
			lista = ListaYachayEPTab(per);
		} else if (ins.equals(INS_YACHAYTECH)) {
			lista = ListaYachayTTab(per);
		} else if (ins.equals(INS_IST)) {
			lista = ListaISTTab(per);
		} else if (ins.equals(INS_CIUDAD)) {
			lista = ListaCiudadTab(per);
		}
		if (lista != null)
			lp = ObjectToClass(lista);
		return lp;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaTodosTab(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_tabaco, count(*) as total from gen_persona p, gen_salud s  where p.per_dni = s.per_dni group by p.per_genero, sld_consume_tabaco;");
		} else if (persona.equals(PER_COM)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_tabaco, count(*) as total from gen_persona p, gen_salud s, gen_externos e where e.ext_tipo='Comunidad' and p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, sld_consume_tabaco;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_tabaco, count(*) as total from gen_persona p, gen_salud s, gen_estudiante_institucion e where p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, sld_consume_tabaco;");
		} else if (persona.equals(PER_EXT)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_tabaco, count(*) as total from gen_persona p, gen_salud s, gen_externos e where p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, sld_consume_tabaco;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_tabaco, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, sld_consume_tabaco;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaYachayEPTab(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_tabaco, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, sld_consume_tabaco;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_tabaco, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, sld_consume_tabaco;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaYachayTTab(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_tabaco, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='2' and e.est_estado='A') or p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='2' and f.fun_estado='A') group by p.per_genero, sld_consume_tabaco;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_tabaco, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='2' and f.fun_estado='A') group by p.per_genero, sld_consume_tabaco;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_tabaco, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='2' and e.est_estado='A') group by p.per_genero, sld_consume_tabaco;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaISTTab(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_tabaco, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='3' and e.est_estado='A') or p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='3' and f.fun_estado='A') group by p.per_genero, sld_consume_tabaco;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_tabaco, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='3' and f.fun_estado='A') group by p.per_genero, sld_consume_tabaco;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_tabaco, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='3' and e.est_estado='A') group by p.per_genero, sld_consume_tabaco;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaCiudadTab(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_tabaco, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e )  group by p.per_genero, sld_consume_tabaco;");
		} else if (persona.equals(PER_COM)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_tabaco, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e where e.ext_tipo='Comunidad')  group by p.per_genero, sld_consume_tabaco;");
		} else if (persona.equals(PER_EXT)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, sld_consume_tabaco, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e where e.ext_tipo!='Comunidad')  group by p.per_genero, sld_consume_tabaco;");
		}
		return lista;
	}

	//////////////////////////////////////////////// (GRAFICO_SEGURO_IESS)////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<GenericClassBoolean> listaSeguroI(String ins, String per) {
		List<GenericClassBoolean> lp = new ArrayList<GenericClassBoolean>();
		List<Object> lista = new ArrayList<Object>();
		if (ins.equals("all")) {
			lista = ListaTodosIESS(per);
		} else if (ins.equals(INS_YACHAYEP)) {
			lista = ListaYachayEPIESS(per);
		} else if (ins.equals(INS_YACHAYTECH)) {
			lista = ListaYachayTIESS(per);
		} else if (ins.equals(INS_IST)) {
			lista = ListaISTIESS(per);
		} else if (ins.equals(INS_CIUDAD)) {
			lista = ListaCiudadIESS(per);
		}
		if (lista != null)
			lp = ObjectToClass(lista);
		return lp;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaTodosIESS(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_iess, count(*) as total from gen_persona p, gen_salud s  where p.per_dni = s.per_dni group by p.per_genero, s.sld_seguro_iess;");
		} else if (persona.equals(PER_COM)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_iess, count(*) as total from gen_persona p, gen_salud s, gen_externos e where e.ext_tipo='Comunidad' and p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, s.sld_seguro_iess;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_iess, count(*) as total from gen_persona p, gen_salud s, gen_estudiante_institucion e where p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, s.sld_seguro_iess;");
		} else if (persona.equals(PER_EXT)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_iess, count(*) as total from gen_persona p, gen_salud s, gen_externos e where p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, s.sld_seguro_iess;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_iess, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, s.sld_seguro_iess;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaYachayEPIESS(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_iess, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, s.sld_seguro_iess;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_iess, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, s.sld_seguro_iess;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaYachayTIESS(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_iess, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='2' and e.est_estado='A') or p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='2' and f.fun_estado='A') group by p.per_genero, s.sld_seguro_iess;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_iess, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='2' and f.fun_estado='A') group by p.per_genero, s.sld_seguro_iess;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_iess, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='2' and e.est_estado='A') group by p.per_genero, s.sld_seguro_iess;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaISTIESS(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_iess, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='3' and e.est_estado='A') or p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='3' and f.fun_estado='A') group by p.per_genero, s.sld_seguro_iess;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_iess, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='3' and f.fun_estado='A') group by p.per_genero, s.sld_seguro_iess;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_iess, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='3' and e.est_estado='A') group by p.per_genero, s.sld_seguro_iess;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaCiudadIESS(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_iess, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e )  group by p.per_genero, s.sld_seguro_iess;");
		} else if (persona.equals(PER_COM)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_iess, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e where e.ext_tipo='Comunidad')  group by p.per_genero, s.sld_seguro_iess;");
		} else if (persona.equals(PER_EXT)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_iess, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e where e.ext_tipo!='Comunidad')  group by p.per_genero, s.sld_seguro_iess;");
		}
		return lista;
	}

	//////////////////////////////////////////////// (GRAFICO_SEGURO_PRIVADO)////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<GenericClassBoolean> listaSeguroP(String ins, String per) {
		List<GenericClassBoolean> lp = new ArrayList<GenericClassBoolean>();
		List<Object> lista = new ArrayList<Object>();
		if (ins.equals("all")) {
			lista = ListaTodosPri(per);
		} else if (ins.equals(INS_YACHAYEP)) {
			lista = ListaYachayEPPri(per);
		} else if (ins.equals(INS_YACHAYTECH)) {
			lista = ListaYachayTPri(per);
		} else if (ins.equals(INS_IST)) {
			lista = ListaISTPri(per);
		} else if (ins.equals(INS_CIUDAD)) {
			lista = ListaCiudadPri(per);
		}
		if (lista != null)
			lp = ObjectToClass(lista);
		return lp;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaTodosPri(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_privado, count(*) as total from gen_persona p, gen_salud s  where p.per_dni = s.per_dni group by p.per_genero, s.sld_seguro_privado;");
		} else if (persona.equals(PER_COM)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_privado, count(*) as total from gen_persona p, gen_salud s, gen_externos e where e.ext_tipo='Comunidad' and p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, s.sld_seguro_privado;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_privado, count(*) as total from gen_persona p, gen_salud s, gen_estudiante_institucion e where p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, s.sld_seguro_privado;");
		} else if (persona.equals(PER_EXT)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_privado, count(*) as total from gen_persona p, gen_salud s, gen_externos e where p.per_dni = e.per_dni and e.per_dni = s.per_dni group by p.per_genero, s.sld_seguro_privado;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_privado, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, s.sld_seguro_privado;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaYachayEPPri(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_privado, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, s.sld_seguro_privado;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_privado, count(*) as total from gen_persona p, gen_salud s, gen_funcionarios_institucion f where f.fun_estado='A' and p.per_dni = f.per_dni and f.per_dni = s.per_dni group by p.per_genero, s.sld_seguro_privado;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaYachayTPri(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_privado, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='2' and e.est_estado='A') or p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='2' and f.fun_estado='A') group by p.per_genero, s.sld_seguro_privado;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_privado, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='2' and f.fun_estado='A') group by p.per_genero, s.sld_seguro_privado;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_privado, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='2' and e.est_estado='A') group by p.per_genero, s.sld_seguro_privado;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaISTPri(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_privado, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='3' and e.est_estado='A') or p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='3' and f.fun_estado='A') group by p.per_genero, s.sld_seguro_privado;");
		} else if (persona.equals(PER_FUN)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_privado, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select f.per_dni from gen_funcionarios_institucion f where f.ins_codigo='3' and f.fun_estado='A') group by p.per_genero, s.sld_seguro_privado;");
		} else if (persona.equals(PER_EST)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_privado, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_estudiante_institucion e where e.ins_codigo='3' and e.est_estado='A') group by p.per_genero, s.sld_seguro_privado;");
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<Object> ListaCiudadPri(String persona) {
		List<Object> lista = new ArrayList<Object>();
		if (persona.equals("all")) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_privado, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e )  group by p.per_genero, s.sld_seguro_privado;");
		} else if (persona.equals(PER_COM)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_privado, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e where e.ext_tipo='Comunidad')  group by p.per_genero, s.sld_seguro_privado;");
		} else if (persona.equals(PER_EXT)) {
			lista = mngDao.ejectNativeSQL3(
					"select p.per_genero, s.sld_seguro_privado, count(*) as total from gen_persona p, gen_salud s where p.per_dni= s.per_dni and p.per_dni in (select e.per_dni from gen_externos e where e.ext_tipo!='Comunidad')  group by p.per_genero, s.sld_seguro_privado;");
		}
		return lista;
	}

	private List<GenericClassBoolean> ObjectToClass(List<Object> lista) {
		List<GenericClassBoolean> li = new ArrayList<GenericClassBoolean>();
		Iterator it = lista.iterator();
		while (it.hasNext()) {
			GenericClassBoolean s = new GenericClassBoolean();
			Object[] obj = (Object[]) it.next();
			s.setGen_genero(String.valueOf(obj[0]));
			s.setGen_valor(Boolean.valueOf(String.valueOf(obj[1])));
			s.setGen_cantidad(Integer.parseInt(String.valueOf(obj[2])));
			li.add(s);
		}
		return li;
	}
}