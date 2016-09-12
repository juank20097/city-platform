package city.model.manager;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import jxl.Cell;
import city.model.dao.entidades.GenEstudianteInstitucion;
import city.model.dao.entidades.GenEstudianteInstitucionPK;
import city.model.dao.entidades.GenExterno;
import city.model.dao.entidades.GenFuncionariosInstitucion;
import city.model.dao.entidades.GenFuncionariosInstitucionPK;
import city.model.dao.entidades.GenInstitucione;
import city.model.dao.entidades.GenParametro;
import city.model.dao.entidades.GenPersona;
import city.model.dao.entidades.GenRegistroExcel;
import city.model.dao.entidades.extras.Estudiante;
import city.model.dao.entidades.extras.Externo;
import city.model.dao.entidades.extras.Funcionario;
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
public class ManagerCarga {

	@EJB
	private ManagerDAO mngDao;

	// atributos de registro excel
	private static Integer exc_nuevos;
	private static Integer exc_actualizados;
	private static Integer exc_inactivados;
	private static Integer exc_error;

	// POSICIONES DEL ARRAY DE DATOS DE Personas
	private int POSICION_CEDULA = 0;
	private int POSICION_NOMBRES = 1;
	private int POSICION_APELLIDOS = 2;
	private int POSICION_FECHA = 3;
	private int POSICION_TELEFONO = 4;
	private int POSICION_CELULAR = 5;
	private int POSICION_ESTADO_CIV = 6;
	private int POSICION_GENERO = 7;
	private int POSICION_CORREO_PRO = 8;

	// POSICIONES DEL ARRAY DE DATOS DE Estudiantes
	private int POSICION_CORREO_INS = 9;
	private int POSICION_NIVEL = 10;
	private int POSICION_CARRERA = 11;
	private int POSICION_MODALIDAD = 12;
	private int POSICION_AREA = 13;

	// POSICIONES DEL ARRAY DE DATOS DE Externo
	private int POSICION_REFERENCIA = 9;
	private int POSICION_TIPO = 10;

	// POSICIONES DEL ARRAY DE DATOS DE Funcionarios
	private int POSICION_CARGO = 9;
	private int POSICION_DIRECCION = 10;
	private int POSICION_FECHAINGRESO = 11;
	private int POSICION_GERENCIA = 12;
	private int POSICION_TIPOF = 13;
	private int POSICION_JEFE = 14;
	private int POSICION_EVALUACION = 15;

	private String[] encabezados_estudiante = { "CÉDULA", "NOMBRES", "APELLIDOS", "FECHA_NACIMIENTO", "TELÉFONO",
			"CELULAR", "ESTADO_CIVIL", "GÉNERO", "CORREO_PERSONAL", "CORREO_INSTITUCIONAL", "NIVEL", "CARRERA",
			"MODALIDAD", "AREA_ESTUDIO" };

	private String[] encabezados_externo = { "CÉDULA", "NOMBRES", "APELLIDOS", "FECHA_NACIMIENTO", "TELÉFONO",
			"CELULAR", "ESTADO_CIVIL", "GÉNERO", "CORREO_PERSONAL", "REFERENCIA", "TIPO" };

	private String[] encabezados_funcionario = { "CÉDULA", "NOMBRES", "APELLIDOS", "FECHA_NACIMIENTO", "TELÉFONO",
			"CELULAR", "ESTADO_CIVIL", "GÉNERO", "CORREO_PERSONAL", "CARGO", "DIRECCIÓN", "FECHA_INGRESO", "GERENCIA",
			"TIPO", "JEFE_INMEDIATO", "TIPO_EVALUACIÓN" };

	/**
	 * Metodo de inicialización de contructor
	 */
	public ManagerCarga() {
	}// Cierre del Constructor

	// //////////////////////////////////////////////////////////(ESTUDIANTE-INSTITUCION)/////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla ESTUDIANTE-INSTITUCION
	 * 
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenEstudianteInstitucion> findAllEstudiantes() throws Exception {
		return mngDao.findAll(GenEstudianteInstitucion.class);
	}// Cierre del metodo

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenInstitucione> findAllInstitucionesEducativas() throws Exception {
		return mngDao.findWhere(GenInstitucione.class, "o.insCategoria='Educación'", null);
	}// Cierre del metodo

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenEstudianteInstitucion> findAllEstudiantesXInstitucion(String ins_codigo) throws Exception {
		return mngDao.findWhere(GenEstudianteInstitucion.class, "o.id.insCodigo='" + ins_codigo + "'", null);
	}// Cierre del metodo

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenEstudianteInstitucion> findAllEstudiantesXInstitucionActivos(String ins_codigo) throws Exception {
		return mngDao.findWhere(GenEstudianteInstitucion.class,
				"o.id.insCodigo='" + ins_codigo + "' and o.estEstado='A'", null);
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
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public String ParametroByID(String dni) throws Exception {
		GenParametro p = (GenParametro) mngDao.findById(GenParametro.class, dni);
		return p.getParValor();
	}// Cierre del metodo

	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public GenInstitucione InstitucionByID(String dni) throws Exception {
		return (GenInstitucione) mngDao.findById(GenInstitucione.class, dni);
	}// Cierre del metodo

	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public GenEstudianteInstitucion EstudianteByID(String ins_codigo, String per_dni) throws Exception {
		List<GenEstudianteInstitucion> l_est = mngDao.findWhere(GenEstudianteInstitucion.class,
				"o.id.insCodigo = '" + ins_codigo + "' and o.id.perDni = '" + per_dni + "'", null);
		if (l_est == null || l_est.size() == 0) {
			return null;
		} else {
			return l_est.get(0);
		}
	}// Cierre del metodo

	/**
	 * Metodo para ingresar un Atributo a la base de datos
	 * 
	 * @param ins_codigo
	 * @param per_dni
	 * @param correo
	 * @param nivel
	 * @param carrera
	 * @param modalidad
	 * @param area_estudio
	 * @throws Exception
	 */
	public void insertarEstudiante(String ins_codigo, String per_dni, String correo, String nivel, String carrera,
			String modalidad, String area_estudio) throws Exception {
		try {
			GenEstudianteInstitucionPK pk_estudiante = new GenEstudianteInstitucionPK();
			pk_estudiante.setInsCodigo(ins_codigo);
			pk_estudiante.setPerDni(per_dni);
			GenEstudianteInstitucion estudiante = new GenEstudianteInstitucion();
			estudiante.setId(pk_estudiante);
			estudiante.setEstAreaEstudio(area_estudio);
			estudiante.setEstCarrera(carrera);
			estudiante.setEstCorreo(correo);
			estudiante.setEstEstado("A");
			estudiante.setEstModalidad(modalidad);
			estudiante.setEstNivel(nivel);
			estudiante.setGenPersona(this.PersonaByID(per_dni));
			estudiante.setGenInstitucione(this.InstitucionByID(ins_codigo));
			mngDao.insertar(estudiante);
			System.out.println("Bien_insertar_estudiante");
		} catch (Exception e) {
			System.out.println("Error_insertar_estudiante");
			e.printStackTrace();
		}
	}// Cierre del metodo

	/**
	 * Metodo para editar un Atributo en la base de datos
	 * 
	 * @param ins_codigo
	 * @param per_dni
	 * @param correo
	 * @param nivel
	 * @param carrera
	 * @param modalidad
	 * @param area_estudio
	 * @param estado
	 * @throws Exception
	 */
	public void editarEstudiante(String ins_codigo, String per_dni, String correo, String nivel, String carrera,
			String modalidad, String area_estudio, String estado) throws Exception {
		try {
			GenEstudianteInstitucion estudiante = this.EstudianteByID(ins_codigo, per_dni);
			estudiante.setEstAreaEstudio(area_estudio);
			estudiante.setEstCarrera(carrera);
			estudiante.setEstCorreo(correo);
			estudiante.setEstEstado(estado);
			estudiante.setEstModalidad(modalidad);
			estudiante.setEstNivel(nivel);
			mngDao.actualizar(estudiante);
			System.out.println("Bien_mod_estudiante");
		} catch (Exception e) {
			System.out.println("Error_mod_estudiante");
			e.printStackTrace();
		}
	}// Cierre del metodo

	/**
	 * Método para inactivar un estudiante
	 * 
	 * @param e
	 */
	public void inactivarEstadoEstudiante(GenEstudianteInstitucion e, String ins_codigo) {
		try {
			if (e.getId().getInsCodigo().equals(ins_codigo)) {
				e.setEstEstado("I");
				mngDao.actualizar(e);
				System.out.println("Bien_mod_estudiante");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// ////////////////////////////////////////////PROCESOS_DE_EXCEL_(ESTUDIANTES)//////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Valida la estructura de encabezados de excel
	 * 
	 * @param row
	 * @return
	 */
	public boolean validarEncabezadosExcelEstudiante(Cell[] row) {
		if (row[POSICION_CEDULA].getContents().equals(encabezados_estudiante[POSICION_CEDULA])
				&& row[POSICION_CARRERA].getContents().equals(encabezados_estudiante[POSICION_CARRERA])
				&& row[POSICION_CORREO_INS].getContents().equals(encabezados_estudiante[POSICION_CORREO_INS])
				&& row[POSICION_CORREO_PRO].getContents().equals(encabezados_estudiante[POSICION_CORREO_PRO])
				&& row[POSICION_FECHA].getContents().equals(encabezados_estudiante[POSICION_FECHA])
				&& row[POSICION_GENERO].getContents().equals(encabezados_estudiante[POSICION_GENERO])
				&& row[POSICION_NIVEL].getContents().equals(encabezados_estudiante[POSICION_NIVEL])
				&& row[POSICION_NOMBRES].getContents().equals(encabezados_estudiante[POSICION_NOMBRES])
				&& row[POSICION_APELLIDOS].getContents().equals(encabezados_estudiante[POSICION_APELLIDOS])
				&& row[POSICION_AREA].getContents().equals(encabezados_estudiante[POSICION_AREA])
				&& row[POSICION_CELULAR].getContents().equals(encabezados_estudiante[POSICION_CELULAR])
				&& row[POSICION_ESTADO_CIV].getContents().equals(encabezados_estudiante[POSICION_ESTADO_CIV])
				&& row[POSICION_MODALIDAD].getContents().equals(encabezados_estudiante[POSICION_MODALIDAD])
				&& row[POSICION_TELEFONO].getContents().equals(encabezados_estudiante[POSICION_TELEFONO])) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Valida que los datos no sean vacio o null
	 * 
	 * @param column
	 * @return String
	 */
	public String validarFilaExcelEstudiante(Cell[] column) {
		// seteo de variable de conteo
		exc_error = 0;
		String errores = "";
		if (column.length >= 14) {
		// validar cedula
		if (column[POSICION_CEDULA].getContents() == null || column[POSICION_CEDULA].getContents().trim().isEmpty()) {
			errores += " CÉDULA ESTUDIANTE vacío, ";
			exc_error += 1;
		} else if (Funciones.validacionCedula(column[POSICION_CEDULA].getContents().trim()) != true) {
			errores += " CÉDULA ESTUDIANTE inválido, ";
			exc_error = +1;
		}
		// validar nombre
		if (column[POSICION_NOMBRES].getContents() == null || column[POSICION_NOMBRES].getContents().trim().isEmpty()) {
			errores += " NOMBRE ESTUDIANTE vacío, ";
			exc_error += 1;
		}
		// validar apellidos
		if (column[POSICION_APELLIDOS].getContents() == null
				|| column[POSICION_APELLIDOS].getContents().trim().isEmpty()) {
			errores += " APELLIDOS ESTUDIANTE vacío, ";
			exc_error += 1;
		}
		// validar fecha nacimiento
		if (column[POSICION_FECHA].getContents() == null || column[POSICION_FECHA].getContents().trim().isEmpty()) {
			errores += " FECHA NACIMIENTO vacío, ";
			exc_error += 1;
		}
		// validar nivel
		if (column[POSICION_NIVEL].getContents() == null || column[POSICION_NIVEL].getContents().trim().isEmpty()) {
			errores += " NIVEL vacío, ";
			exc_error += 1;
		}
		// validar carrera
		if (column[POSICION_CARRERA].getContents() == null || column[POSICION_CARRERA].getContents().trim().isEmpty()) {
			errores += " CARRERA vacío, ";
			exc_error += 1;
		}
		// validar correo_institucional
		if (column[POSICION_CORREO_INS].getContents() == null
				|| column[POSICION_CORREO_INS].getContents().trim().isEmpty()) {
			errores += " CORREO INSTITUCIONAL vacío, ";
			exc_error += 1;
		} else {
			if (Funciones.validarEmail(column[POSICION_CORREO_INS].getContents().trim()) != true) {
				errores += " CORREO INSTITUCIONAL inválido, ";
				exc_error += 1;
			}
		}
		// validar correo
		if (column[POSICION_CORREO_PRO].getContents() == null
				|| column[POSICION_CORREO_PRO].getContents().trim().isEmpty()) {
			errores += " CORREO PROPIO vacío, ";
			exc_error += 1;
		} else {
			if (Funciones.validarEmail(column[POSICION_CORREO_PRO].getContents().trim()) != true) {
				errores += " CORREO PROPIO inválido, ";
				exc_error += 1;
			}
		}
		// validar genero
		if (column[POSICION_GENERO].getContents() == null || column[POSICION_GENERO].getContents().trim().isEmpty()) {
			errores += " GENERO vacío, ";
			exc_error += 1;
		}
		// validar area_estudio
		if (column[POSICION_AREA].getContents() == null || column[POSICION_AREA].getContents().trim().isEmpty()) {
			errores += " AREA ESTUDIO vacío, ";
			exc_error += 1;
		}
		// validar celular
		if (column[POSICION_CELULAR].getContents() == null || column[POSICION_CELULAR].getContents().trim().isEmpty()) {
			errores += " CELULAR ESTUDIO vacío, ";
			exc_error += 1;
		}
		// validar telefono
		if (column[POSICION_TELEFONO].getContents() == null
				|| column[POSICION_TELEFONO].getContents().trim().isEmpty()) {
			errores += " TELÉFONO vacío, ";
			exc_error += 1;
		}
		// validar estado_civil
		if (column[POSICION_ESTADO_CIV].getContents() == null
				|| column[POSICION_ESTADO_CIV].getContents().trim().isEmpty()) {
			errores += " ESTADO CIVIL vacío, ";
			exc_error += 1;
		}
		// validar modalidad
		if (column[POSICION_MODALIDAD].getContents() == null
				|| column[POSICION_MODALIDAD].getContents().trim().isEmpty()) {
			errores += " MODALIDAD vacío, ";
			exc_error += 1;
		}
		}
		// retornar errores
		return errores;
	}

	/**
	 * Crea una instancia de Estudiantes mediante una Lista de String
	 * 
	 * @param datosPersona
	 * @return SinfPersonal
	 * @throws Exception
	 */
	public Estudiante crearEstudiante(List<String> datosEstudiante, String ins_codigo) throws Exception {
		Estudiante est = new Estudiante();
		est.setIns_codigo(ins_codigo);
		est.setPerDni(datosEstudiante.get(this.POSICION_CEDULA));
		est.setEstAreaEstudio(datosEstudiante.get(this.POSICION_AREA));
		est.setEstCarrera(datosEstudiante.get(this.POSICION_CARRERA));
		est.setEstCorreo(datosEstudiante.get(this.POSICION_CORREO_INS));
		est.setEstEstado("A");
		est.setEstModalidad(datosEstudiante.get(this.POSICION_MODALIDAD));
		est.setEstNivel(datosEstudiante.get(this.POSICION_NIVEL));
		est.setPerApellidos(datosEstudiante.get(POSICION_APELLIDOS));
		est.setPerCelular(datosEstudiante.get(POSICION_CELULAR));
		est.setPerCorreo(datosEstudiante.get(POSICION_CORREO_PRO));
		est.setPerDni(datosEstudiante.get(POSICION_CEDULA));
		est.setPerEstado("A");
		est.setPerEstadoCivil(datosEstudiante.get(POSICION_ESTADO_CIV));
		est.setPerFechaNacimiento(Funciones.stringToDate(datosEstudiante.get(POSICION_FECHA)));
		est.setPerGenero(datosEstudiante.get(POSICION_GENERO));
		est.setPerNombres(datosEstudiante.get(POSICION_NOMBRES));
		est.setPerTelefono(datosEstudiante.get(POSICION_TELEFONO));
		est.setPerTipoDni("Cédula");
		return est;
	}

	/**
	 * Permite el ingreso y actualización de datos de estudiante
	 * 
	 * @param listadoPersonal
	 *            Personas dentro de un periodo determinado
	 * @throws Exception
	 */
	public void ingresarEstudiantePersona(List<Estudiante> listadoEstudiantes) throws Exception {
		// seteo de valores iniciales de conteo
		exc_actualizados = 0;
		exc_nuevos = 0;

		for (Estudiante e : listadoEstudiantes) {
			if (validarExistenciaPersona(e.getPerDni())) {
				GenPersona p = PersonaByID(e.getPerDni());
				p.setPerApellidos(e.getPerApellidos());
				p.setPerCelular(e.getPerCelular());
				p.setPerCorreo(e.getPerCorreo());
				p.setPerEstado(e.getPerEstado());
				p.setPerEstadoCivil(e.getPerEstadoCivil());
				p.setPerFechaNacimiento(e.getPerFechaNacimiento());
				p.setPerGenero(e.getPerGenero());
				p.setPerNombres(e.getPerNombres());
				p.setPerTelefono(e.getPerTelefono());
				p.setPerTipoDni("Cédula");
				mngDao.actualizar(p);
				System.out.println("Bien_actualizado_persona");
			} else {
				GenPersona p = new GenPersona();
				p.setPerDni(e.getPerDni());
				p.setPerApellidos(e.getPerApellidos());
				p.setPerCelular(e.getPerCelular());
				p.setPerCorreo(e.getPerCorreo());
				p.setPerEstado(e.getPerEstado());
				p.setPerEstadoCivil(e.getPerEstadoCivil());
				p.setPerFechaNacimiento(e.getPerFechaNacimiento());
				p.setPerGenero(e.getPerGenero());
				p.setPerNombres(e.getPerNombres());
				p.setPerTelefono(e.getPerTelefono());
				p.setPerTipoDni("Cédula");
				mngDao.insertar(p);
				System.out.println("Bien_insertado_persona");
			}
			if (validarExistenciaEstudiante(e.getPerDni(), e.getIns_codigo())) {
				editarEstudiante(e.getIns_codigo(), e.getPerDni(), e.getEstCorreo(), e.getEstNivel(), e.getEstCarrera(),
						e.getEstModalidad(), e.getEstAreaEstudio(), e.getEstEstado());
				exc_actualizados = exc_actualizados + 1;
			} else {
				insertarEstudiante(e.getIns_codigo(), e.getPerDni(), e.getEstCorreo(), e.getEstNivel(),
						e.getEstCarrera(), e.getEstModalidad(), e.getEstAreaEstudio());
				exc_nuevos = exc_nuevos + 1;
			}
		}
	}

	/**
	 * Método para comprobar si la persona esta creada
	 * 
	 * @param dni
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean validarExistenciaPersona(String dni) {
		List<GenPersona> l_p = mngDao.findWhere(GenPersona.class, "o.perDni='" + dni + "'", null);
		if (l_p == null || l_p.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Método para comprobar si el estudiante esta creado
	 * 
	 * @param dni
	 * @param codigo_ins
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean validarExistenciaEstudiante(String dni, String codigo_ins) {
		List<GenEstudianteInstitucion> l_f = mngDao.findWhere(GenEstudianteInstitucion.class,
				"o.id.perDni='" + dni + "' and o.id.insCodigo='" + codigo_ins + "'", null);
		if (l_f == null || l_f.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Método para inactivar un estudiante
	 * 
	 * @param estudiantes_excel
	 */
	public void inactivarEstudiantes(List<Estudiante> estudiantes_excel, String ins_codigo) {
		exc_inactivados = 0;
		try {
			for (GenEstudianteInstitucion estudiante : findAllEstudiantesXInstitucionActivos(ins_codigo)) {
				inactivarEstadoEstudiante(estudiante, ins_codigo);
				exc_inactivados = exc_inactivados + 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// //////////////////////////////////////////////////////////(FUNCIONARIO-INSTITUCION)/////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla FUNCIONARIO-INSTITUCION
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenFuncionariosInstitucion> findAllFuncionarios() throws Exception {
		return mngDao.findAll(GenFuncionariosInstitucion.class);
	}// Cierre del metodo

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenFuncionariosInstitucion> findAllFuncionarioXInstitucion(String ins_codigo) throws Exception {
		return mngDao.findWhere(GenFuncionariosInstitucion.class, "o.id.insCodigo='" + ins_codigo + "'", null);
	}// Cierre del metodo

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenFuncionariosInstitucion> findAllFuncionariosXInstitucionActivos(String ins_codigo) throws Exception {
		return mngDao.findWhere(GenFuncionariosInstitucion.class,
				"o.id.insCodigo='" + ins_codigo + "' and o.funEstado='A'", null);
	}// Cierre del metodo

	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public GenFuncionariosInstitucion FuncionarioByID(String ins_codigo, String per_dni) throws Exception {
		List<GenFuncionariosInstitucion> l = mngDao.findWhere(GenFuncionariosInstitucion.class,
				"o.id.insCodigo = '" + ins_codigo + "' and o.id.perDni = '" + per_dni + "'", null);
		return l.get(0);
	}// Cierre del metodo

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenInstitucione> findAllInstitucionesServicios() throws Exception {
		return mngDao.findWhere(GenInstitucione.class, "o.insCategoria='Servicios'", null);
	}// Cierre del metodo

	/**
	 * Metodo para ingresar un Atributo a la base de datos
	 * 
	 * @param ins_codigo
	 * @param per_dni
	 * @param cargo
	 * @param direccion
	 * @param fecha_ingreso
	 * @param gerencia
	 * @param jefe_inmediato
	 * @param tipo
	 * @param tipo_evaluacion
	 * @throws Exception
	 */
	public void insertarFuncionarios(String ins_codigo, String per_dni, String cargo, String direccion,
			Date fecha_ingreso, String gerencia, String jefe_inmediato, String tipo, String tipo_evaluacion)
			throws Exception {
		try {
			GenFuncionariosInstitucionPK pk_funcionario = new GenFuncionariosInstitucionPK();
			pk_funcionario.setInsCodigo(ins_codigo);
			pk_funcionario.setPerDni(per_dni);
			GenFuncionariosInstitucion funcionario = new GenFuncionariosInstitucion();
			funcionario.setId(pk_funcionario);
			funcionario.setFunCargo(cargo);
			funcionario.setFunDireccion(direccion);
			funcionario.setFunEstado("A");
			funcionario.setFunFechaIngreso(fecha_ingreso);
			funcionario.setFunGerencia(gerencia);
			funcionario.setFunJefeInmediato(jefe_inmediato);
			funcionario.setFunTipo(tipo);
			funcionario.setFunTipoEvaluacion(tipo_evaluacion);
			funcionario.setGenInstitucione(this.InstitucionByID(ins_codigo));
			funcionario.setGenPersona(this.PersonaByID(per_dni));
			mngDao.insertar(funcionario);
			System.out.println("Bien_insertar_funcionario");
		} catch (Exception e) {
			System.out.println("Error_insertar_funcionario");
			e.printStackTrace();
		}
	}// Cierre del metodo

	/**
	 * Metodo para editar un Atributo en la base de datos
	 * 
	 * @param ins_codigo
	 * @param per_dni
	 * @param correo
	 * @param nivel
	 * @param carrera
	 * @param modalidad
	 * @param area_estudio
	 * @param estado
	 * @throws Exception
	 */
	public void editarFuncionario(String ins_codigo, String per_dni, String cargo, String direccion, Date fecha_ingreso,
			String gerencia, String jefe_inmediato, String tipo, String tipo_evaluacion, String estado)
			throws Exception {
		try {
			GenFuncionariosInstitucion funcionario = this.FuncionarioByID(ins_codigo, per_dni);
			funcionario.setFunCargo(cargo);
			funcionario.setFunDireccion(direccion);
			funcionario.setFunEstado(estado);
			funcionario.setFunFechaIngreso(fecha_ingreso);
			funcionario.setFunGerencia(gerencia);
			funcionario.setFunJefeInmediato(jefe_inmediato);
			funcionario.setFunTipo(tipo);
			funcionario.setFunTipoEvaluacion(tipo_evaluacion);
			mngDao.actualizar(funcionario);
			System.out.println("Bien_mod_funcionario");
		} catch (Exception e) {
			System.out.println("Error_mod_funcionario");
			e.printStackTrace();
		}
	}// Cierre del metodo

	/**
	 * Método para inactivar un estudiante
	 * 
	 * @param e
	 */
	public void inactivarEstadoFuncionario(GenFuncionariosInstitucion e, String ins_codigo) {
		try {
			if (e.getId().getInsCodigo().equals(ins_codigo)) {
				e.setFunEstado("I");
				mngDao.actualizar(e);
				System.out.println("Bien_mod_estudiante");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// ////////////////////////////////////////////PROCESOS_DE_EXCEL_(FUNCIONARIOS)//////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Valida la estructura de encabezados de excel
	 * 
	 * @param row
	 * @return
	 */
	public boolean validarEncabezadosExcelFuncionario(Cell[] row) {
		if (row[POSICION_CEDULA].getContents().equals(encabezados_funcionario[POSICION_CEDULA])
				&& row[POSICION_CORREO_PRO].getContents().equals(encabezados_funcionario[POSICION_CORREO_PRO])
				&& row[POSICION_FECHA].getContents().equals(encabezados_funcionario[POSICION_FECHA])
				&& row[POSICION_GENERO].getContents().equals(encabezados_funcionario[POSICION_GENERO])
				&& row[POSICION_NOMBRES].getContents().equals(encabezados_funcionario[POSICION_NOMBRES])
				&& row[POSICION_APELLIDOS].getContents().equals(encabezados_funcionario[POSICION_APELLIDOS])
				&& row[POSICION_CELULAR].getContents().equals(encabezados_funcionario[POSICION_CELULAR])
				&& row[POSICION_CARGO].getContents().equals(encabezados_funcionario[POSICION_CARGO])
				&& row[POSICION_DIRECCION].getContents().equals(encabezados_funcionario[POSICION_DIRECCION])
				&& row[POSICION_FECHAINGRESO].getContents().equals(encabezados_funcionario[POSICION_FECHAINGRESO])
				&& row[POSICION_GERENCIA].getContents().equals(encabezados_funcionario[POSICION_GERENCIA])
				&& row[POSICION_TIPOF].getContents().equals(encabezados_funcionario[POSICION_TIPOF])
				&& row[POSICION_JEFE].getContents().equals(encabezados_funcionario[POSICION_JEFE])
				&& row[POSICION_EVALUACION].getContents().equals(encabezados_funcionario[POSICION_EVALUACION])
				&& row[POSICION_ESTADO_CIV].getContents().equals(encabezados_funcionario[POSICION_ESTADO_CIV])
				&& row[POSICION_TELEFONO].getContents().equals(encabezados_funcionario[POSICION_TELEFONO])) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Valida que los datos no sean vacio o null
	 * 
	 * @param column
	 * @return String
	 */
	public String validarFilaExcelFuncionario(Cell[] column) {
		// seteo de variable de conteo
		exc_error = 0;
		String errores = "";
		if (column.length >= 16) {
			// validar cedula
			if (column[POSICION_CEDULA].getContents() == null
					|| column[POSICION_CEDULA].getContents().trim().isEmpty()) {
				errores += " CÉDULA ESTUDIANTE vacío, ";
				exc_error += 1;
			} else if (Funciones.validacionCedula(column[POSICION_CEDULA].getContents().trim()) != true) {
				errores += " CÉDULA ESTUDIANTE inválido, ";
				exc_error = +1;
			}
			// validar nombre
			if (column[POSICION_NOMBRES].getContents() == null
					|| column[POSICION_NOMBRES].getContents().trim().isEmpty()) {
				errores += " NOMBRE ESTUDIANTE vacío, ";
				exc_error += 1;
			}
			// validar apellidos
			if (column[POSICION_APELLIDOS].getContents() == null
					|| column[POSICION_APELLIDOS].getContents().trim().isEmpty()) {
				errores += " APELLIDOS ESTUDIANTE vacío, ";
				exc_error += 1;
			}
			// validar fecha nacimiento
			if (column[POSICION_FECHA].getContents() == null || column[POSICION_FECHA].getContents().trim().isEmpty()) {
				errores += " FECHA NACIMIENTO vacío, ";
				exc_error += 1;
			}
			// validar correo
			if (column[POSICION_CORREO_PRO].getContents() == null
					|| column[POSICION_CORREO_PRO].getContents().trim().isEmpty()) {
				errores += " CORREO PROPIO vacío, ";
				exc_error += 1;
			} else {
				if (Funciones.validarEmail(column[POSICION_CORREO_PRO].getContents().trim()) != true) {
					errores += " CORREO PROPIO inválido, ";
					exc_error += 1;
				}
			}
			// validar genero
			if (column[POSICION_GENERO].getContents() == null
					|| column[POSICION_GENERO].getContents().trim().isEmpty()) {
				errores += " GENERO vacío, ";
				exc_error += 1;
			}
			// validar celular
			if (column[POSICION_CELULAR].getContents() == null
					|| column[POSICION_CELULAR].getContents().trim().isEmpty()) {
				errores += " CELULAR ESTUDIO vacío, ";
				exc_error += 1;
			}
			// validar telefono
			if (column[POSICION_TELEFONO].getContents() == null
					|| column[POSICION_TELEFONO].getContents().trim().isEmpty()) {
				errores += " TELÉFONO vacío, ";
				exc_error += 1;
			}
			// validar estado_civil
			if (column[POSICION_ESTADO_CIV].getContents() == null
					|| column[POSICION_ESTADO_CIV].getContents().trim().isEmpty()) {
				errores += " ESTADO CIVIL vacío, ";
				exc_error += 1;
			}
			// validar cargo
			if (column[POSICION_CARGO].getContents() == null || column[POSICION_CARGO].getContents().trim().isEmpty()) {
				errores += " CARGO vacío, ";
				exc_error += 1;
			}
			// validar direccion
			if (column[POSICION_DIRECCION].getContents() == null
					|| column[POSICION_DIRECCION].getContents().trim().isEmpty()) {
				errores += " DIRECCIÓN vacío, ";
				exc_error += 1;
			}
			// validar fecha ingreso
			if (column[POSICION_FECHAINGRESO].getContents() == null
					|| column[POSICION_FECHAINGRESO].getContents().trim().isEmpty()) {
				errores += " FECHA INGRESO vacío, ";
				exc_error += 1;
			}
			// validar gerencia
			if (column[POSICION_GERENCIA].getContents() == null
					|| column[POSICION_GERENCIA].getContents().trim().isEmpty()) {
				errores += " GERENCIA vacío, ";
				exc_error += 1;
			}
			// validar tipo
			if (column[POSICION_TIPOF].getContents() == null || column[POSICION_TIPOF].getContents().trim().isEmpty()) {
				errores += " TIPO vacío, ";
				exc_error += 1;
			}
			// validar JEFE inmediato
			if (column[POSICION_JEFE].getContents() == null || column[POSICION_JEFE].getContents().trim().isEmpty()) {
				errores += " JEFE INMEDIATO vacío, ";
				exc_error += 1;
			}
			// validar tipo evaluación
			if (column[POSICION_EVALUACION].getContents() == null
					|| column[POSICION_EVALUACION].getContents().trim().isEmpty()) {
				errores += " TIPO EVALUACIÓN vacío, ";
				exc_error += 1;
			}
		}
		// retornar errores
		return errores;
	}

	/**
	 * Crea una instancia de Funcionarios mediante una Lista de String
	 * 
	 * @param datosFuncionarios
	 * @return SinfPersonal
	 * @throws Exception
	 */
	public Funcionario crearFuncionario(List<String> datosFuncionarios, String ins_codigo) throws Exception {
		Funcionario fun = new Funcionario();
		fun.setIns_codigo(ins_codigo);
		fun.setPerDni(datosFuncionarios.get(POSICION_CEDULA));
		fun.setPerApellidos(datosFuncionarios.get(POSICION_APELLIDOS));
		fun.setPerCelular(datosFuncionarios.get(POSICION_CELULAR));
		fun.setPerCorreo(datosFuncionarios.get(POSICION_CORREO_PRO));
		fun.setPerEstado("A");
		fun.setPerEstadoCivil(datosFuncionarios.get(POSICION_ESTADO_CIV));
		fun.setPerFechaNacimiento(Funciones.stringToDate(datosFuncionarios.get(POSICION_FECHA)));
		fun.setPerGenero(datosFuncionarios.get(POSICION_GENERO));
		fun.setPerNombres(datosFuncionarios.get(POSICION_NOMBRES));
		fun.setPerTelefono(datosFuncionarios.get(POSICION_TELEFONO));
		fun.setPerTipoDni("Cédula");
		fun.setFunCargo(datosFuncionarios.get(POSICION_CARGO));
		fun.setFunJefeInmediato(datosFuncionarios.get(POSICION_JEFE));
		fun.setFunTipoEvaluacion(datosFuncionarios.get(POSICION_EVALUACION));
		fun.setFunDireccion(datosFuncionarios.get(POSICION_DIRECCION));
		fun.setFunEstado("A");
		fun.setFunFechaIngreso(Funciones.stringToDate(datosFuncionarios.get(POSICION_FECHAINGRESO)));
		fun.setFunGerencia(datosFuncionarios.get(POSICION_GERENCIA));
		fun.setFunTipo(datosFuncionarios.get(POSICION_TIPOF));
		return fun;
	}

	/**
	 * Permite el ingreso y actualización de datos de estudiante
	 * 
	 * @param listadoPersonal
	 *            Personas dentro de un periodo determinado
	 * @throws Exception
	 */
	public void ingresarFuncionario(List<Funcionario> listadoFuncionarios) throws Exception {
		// seteo de valores iniciales de conteo
		exc_actualizados = 0;
		exc_nuevos = 0;
		for (Funcionario e : listadoFuncionarios) {
			if (validarExistenciaPersona(e.getPerDni())) {
				GenPersona p = PersonaByID(e.getPerDni());
				setearFuncionarios(e, p);
				mngDao.actualizar(p);
				System.out.println("Bien_actualizado_persona");
			} else {
				GenPersona p = new GenPersona();
				p.setPerDni(e.getPerDni());
				setearFuncionarios(e, p);
				mngDao.insertar(p);
				System.out.println("Bien_insertado_persona");
			}
			if (validarExistenciaFuncionario(e.getPerDni(), e.getIns_codigo())) {
				editarFuncionario(e.getIns_codigo(), e.getPerDni(), e.getFunCargo(), e.getFunDireccion(),
						e.getFunFechaIngreso(), e.getFunGerencia(), e.getFunJefeInmediato(), e.getFunTipo(),
						e.getFunTipoEvaluacion(), e.getFunEstado());
				exc_actualizados = exc_actualizados + 1;
			} else {
				insertarFuncionarios(e.getIns_codigo(), e.getPerDni(), e.getFunCargo(), e.getFunDireccion(),
						e.getFunFechaIngreso(), e.getFunGerencia(), e.getFunJefeInmediato(), e.getFunTipo(),
						e.getFunTipoEvaluacion());
				exc_nuevos = exc_nuevos + 1;
			}
		}
	}

	private void setearFuncionarios(Funcionario e, GenPersona p) {
		p.setPerApellidos(e.getPerApellidos());
		p.setPerCelular(e.getPerCelular());
		p.setPerCorreo(e.getPerCorreo());
		p.setPerEstado(e.getPerEstado());
		p.setPerEstadoCivil(e.getPerEstadoCivil());
		p.setPerFechaNacimiento(e.getPerFechaNacimiento());
		p.setPerGenero(e.getPerGenero());
		p.setPerNombres(e.getPerNombres());
		p.setPerTelefono(e.getPerTelefono());
		p.setPerTipoDni("Cédula");
	}

	/**
	 * Método para comprobar si el estudiante esta creado
	 * 
	 * @param dni
	 * @param codigo_ins
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean validarExistenciaFuncionario(String dni, String codigo_ins) {
		List<GenFuncionariosInstitucion> l_f = mngDao.findWhere(GenFuncionariosInstitucion.class,
				"o.id.perDni='" + dni + "' and o.id.insCodigo='" + codigo_ins + "'", null);
		if (l_f == null || l_f.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Método para inactivar un estudiante
	 * 
	 * @param estudiantes_excel
	 */
	public void inactivarFuncionario(List<Funcionario> funcionarios_excel, String ins_codigo) {
		exc_inactivados = 0;
		try {
			for (GenFuncionariosInstitucion fun : findAllFuncionariosXInstitucionActivos(ins_codigo)) {
				inactivarEstadoFuncionario(fun, ins_codigo);
				exc_inactivados = exc_inactivados + 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// //////////////////////////////////////////////////////////(EXTERNO)/////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla EXTERNO
	 * 
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenExterno> findAllExterno() throws Exception {
		return mngDao.findAll(GenExterno.class);
	}// Cierre del metodo

	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public GenExterno ExternoByID(String dni) throws Exception {
		return (GenExterno) mngDao.findById(GenExterno.class, dni);
	}// Cierre del metodo

	/**
	 * Metodo para ingresar un Atributo a la base de datos
	 * 
	 * @param per_dni
	 * @param tipo
	 * @param referencia
	 * @throws Exception
	 */
	public void insertarExterno(String per_dni, String tipo, String referencia) throws Exception {
		try {
			GenExterno externo = new GenExterno();
			externo.setPerDni(per_dni);
			externo.setExtReferencia(referencia);
			externo.setExtTipo(tipo);
			externo.setGenPersona(this.PersonaByID(per_dni));
			mngDao.insertar(externo);
			System.out.println("Bien_insertar_externo");
		} catch (Exception e) {
			System.out.println("Error_insertar_externo");
			e.printStackTrace();
		}
	}// Cierre del metodo

	/**
	 * Metodo para editar un Atributo en la base de datos
	 * 
	 * @param ins_codigo
	 * @param per_dni
	 * @param correo
	 * @param nivel
	 * @param carrera
	 * @param modalidad
	 * @param area_estudio
	 * @param estado
	 * @throws Exception
	 */
	public void editarExterno(String per_dni, String tipo, String referencia) throws Exception {
		try {
			GenExterno externo = this.ExternoByID(per_dni);
			externo.setExtReferencia(referencia);
			externo.setExtTipo(tipo);
			mngDao.actualizar(externo);
			System.out.println("Bien_mod_externo");
		} catch (Exception e) {
			System.out.println("Error_mod_externo");
			e.printStackTrace();
		}
	}// Cierre del metodo

	// ////////////////////////////////////////////PROCESOS_DE_EXCEL_(EXTERNO)//////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Valida la estructura de encabezados de excel
	 * 
	 * @param row
	 * @return
	 */
	public boolean validarEncabezadosExcelExterno(Cell[] row) {
		if (row[POSICION_CEDULA].getContents().equals(encabezados_externo[POSICION_CEDULA])
				&& row[POSICION_CORREO_PRO].getContents().equals(encabezados_externo[POSICION_CORREO_PRO])
				&& row[POSICION_FECHA].getContents().equals(encabezados_externo[POSICION_FECHA])
				&& row[POSICION_GENERO].getContents().equals(encabezados_externo[POSICION_GENERO])
				&& row[POSICION_REFERENCIA].getContents().equals(encabezados_externo[POSICION_REFERENCIA])
				&& row[POSICION_TIPO].getContents().equals(encabezados_externo[POSICION_TIPO])
				&& row[POSICION_NOMBRES].getContents().equals(encabezados_externo[POSICION_NOMBRES])
				&& row[POSICION_APELLIDOS].getContents().equals(encabezados_externo[POSICION_APELLIDOS])
				&& row[POSICION_CELULAR].getContents().equals(encabezados_externo[POSICION_CELULAR])
				&& row[POSICION_ESTADO_CIV].getContents().equals(encabezados_externo[POSICION_ESTADO_CIV])
				&& row[POSICION_TELEFONO].getContents().equals(encabezados_externo[POSICION_TELEFONO])) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Valida que los datos no sean vacio o null
	 * 
	 * @param column
	 * @return String
	 */
	public String validarFilaExcelExterno(Cell[] column) {
		// seteo de variable de conteo
		exc_error = 0;
		String errores = "";
		if (column.length >= 11) {
		// validar cedula
		if (column[POSICION_CEDULA].getContents() == null || column[POSICION_CEDULA].getContents().trim().isEmpty()) {
			errores += " CÉDULA EXTERNO vacío, ";
			exc_error += 1;
		} else if (Funciones.validacionCedula(column[POSICION_CEDULA].getContents().trim()) != true) {
			errores += " CÉDULA EXTERNO inválido, ";
			exc_error = +1;
		}
		// validar nombre
		if (column[POSICION_NOMBRES].getContents() == null || column[POSICION_NOMBRES].getContents().trim().isEmpty()) {
			errores += " NOMBRE ESTUDIANTE vacío, ";
			exc_error += 1;
		}
		// validar apellidos
		if (column[POSICION_APELLIDOS].getContents() == null
				|| column[POSICION_APELLIDOS].getContents().trim().isEmpty()) {
			errores += " APELLIDOS ESTUDIANTE vacío, ";
			exc_error += 1;
		}
		// validar fecha nacimiento
		if (column[POSICION_FECHA].getContents() == null || column[POSICION_FECHA].getContents().trim().isEmpty()) {
			errores += " FECHA NACIMIENTO vacío, ";
			exc_error += 1;
		}
		// validar TIPO
		if (column[POSICION_TIPO].getContents() == null || column[POSICION_TIPO].getContents().trim().isEmpty()) {
			errores += " TIPO vacío, ";
			exc_error += 1;
		}
		// validar carrera
		if (column[POSICION_REFERENCIA].getContents() == null
				|| column[POSICION_REFERENCIA].getContents().trim().isEmpty()) {
			errores += " REFERENCIA vacío, ";
			exc_error += 1;
		}
		// validar correo
		if (column[POSICION_CORREO_PRO].getContents() == null
				|| column[POSICION_CORREO_PRO].getContents().trim().isEmpty()) {
			errores += " CORREO PROPIO vacío, ";
			exc_error += 1;
		} else {
			if (Funciones.validarEmail(column[POSICION_CORREO_PRO].getContents().trim()) != true) {
				errores += " CORREO PROPIO inválido, ";
				exc_error += 1;
			}
		}
		// validar genero
		if (column[POSICION_GENERO].getContents() == null || column[POSICION_GENERO].getContents().trim().isEmpty()) {
			errores += " GENERO vacío, ";
			exc_error += 1;
		}
		// validar celular
		if (column[POSICION_CELULAR].getContents() == null || column[POSICION_CELULAR].getContents().trim().isEmpty()) {
			errores += " CELULAR ESTUDIO vacío, ";
			exc_error += 1;
		}
		// validar telefono
		if (column[POSICION_TELEFONO].getContents() == null
				|| column[POSICION_TELEFONO].getContents().trim().isEmpty()) {
			errores += " TELÉFONO vacío, ";
			exc_error += 1;
		}
		// validar estado_civil
		if (column[POSICION_ESTADO_CIV].getContents() == null
				|| column[POSICION_ESTADO_CIV].getContents().trim().isEmpty()) {
			errores += " ESTADO CIVIL vacío, ";
			exc_error += 1;
		}
		}
		// retornar errores
		return errores;
	}

	/**
	 * Crea una instancia de Externo mediante una Lista de String
	 * 
	 * @param datosPersona
	 * @return SinfPersonal
	 * @throws Exception
	 */
	public Externo crearExterno(List<String> datosExterno) throws Exception {
		Externo ext = new Externo();
		ext.setPerDni(datosExterno.get(POSICION_CEDULA));
		ext.setPerApellidos(datosExterno.get(POSICION_APELLIDOS));
		ext.setPerCelular(datosExterno.get(POSICION_CEDULA));
		ext.setPerCorreo(datosExterno.get(POSICION_CORREO_PRO));
		ext.setPerEstado("A");
		ext.setPerEstadoCivil(datosExterno.get(POSICION_ESTADO_CIV));
		ext.setPerFechaNacimiento(Funciones.stringToDate(datosExterno.get(POSICION_FECHA)));
		ext.setPerGenero(datosExterno.get(POSICION_GENERO));
		ext.setPerNombres(datosExterno.get(POSICION_NOMBRES));
		ext.setPerTelefono(datosExterno.get(POSICION_TELEFONO));
		ext.setPerTipoDni("Cédula");
		ext.setExtReferencia(datosExterno.get(POSICION_REFERENCIA));
		ext.setExtTipo(datosExterno.get(POSICION_TIPO));
		return ext;
	}

	/**
	 * Permite el ingreso y actualización de datos de externo
	 * 
	 * @param listadoExterno
	 *            Externos dentro de un periodo determinado
	 * @throws Exception
	 */
	public void ingresarExterno(List<Externo> listadoExterno) throws Exception {
		// seteo de valores iniciales de conteo
		exc_actualizados = 0;
		exc_nuevos = 0;

		for (Externo e : listadoExterno) {
			if (validarExistenciaPersona(e.getPerDni())) {
				GenPersona p = PersonaByID(e.getPerDni());
				p.setPerApellidos(e.getPerApellidos());
				p.setPerCelular(e.getPerCelular());
				p.setPerCorreo(e.getPerCorreo());
				p.setPerEstado(e.getPerEstado());
				p.setPerEstadoCivil(e.getPerEstadoCivil());
				p.setPerFechaNacimiento(e.getPerFechaNacimiento());
				p.setPerGenero(e.getPerGenero());
				p.setPerNombres(e.getPerNombres());
				p.setPerTelefono(e.getPerTelefono());
				p.setPerTipoDni("Cédula");
				mngDao.actualizar(p);
				System.out.println("Bien_actualizado_persona");
			} else {
				GenPersona p = new GenPersona();
				p.setPerDni(e.getPerDni());
				p.setPerApellidos(e.getPerApellidos());
				p.setPerCelular(e.getPerCelular());
				p.setPerCorreo(e.getPerCorreo());
				p.setPerEstado(e.getPerEstado());
				p.setPerEstadoCivil(e.getPerEstadoCivil());
				p.setPerFechaNacimiento(e.getPerFechaNacimiento());
				p.setPerGenero(e.getPerGenero());
				p.setPerNombres(e.getPerNombres());
				p.setPerTelefono(e.getPerTelefono());
				p.setPerTipoDni("Cédula");
				mngDao.insertar(p);
				System.out.println("Bien_insertado_persona");
			}
			if (validarExistenciaExterno(e.getPerDni())) {
				editarExterno(e.getPerDni(), e.getExtTipo(), e.getExtReferencia());
				exc_actualizados = exc_actualizados + 1;
			} else {
				insertarExterno(e.getPerDni(), e.getExtTipo(), e.getExtReferencia());
				exc_nuevos = exc_nuevos + 1;
			}
		}
	}

	/**
	 * Método para comprobar si el externo esta creado
	 * 
	 * @param dni
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean validarExistenciaExterno(String dni) {
		List<GenExterno> l_e = mngDao.findWhere(GenExterno.class, "o.perDni='" + dni + "'", null);
		if (l_e == null || l_e.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	// //////////////////////////////////////////////////////////(REGISTRO-EXCEL)/////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla REGISTRO EXCEL
	 * 
	 */

	/**
	 * Metodo para ingresar un Atributo a la base de datos
	 * 
	 * @param id
	 * @param usuario
	 * @param nombre_archivo
	 * @param nuevos
	 * @param actualizados
	 * @param inactivos
	 * @param errores
	 * @throws Exception
	 */
	public String insertarExcel(String usuario, String nombre_archivo) throws Exception {
		try {
			System.out.println(exc_inactivados);
			System.out.println(exc_nuevos);
			System.out.println(exc_actualizados);
			if (exc_inactivados == null || exc_inactivados == 0) {
				exc_inactivados = 0;
			} else {
				exc_inactivados = exc_inactivados - exc_actualizados;
			}
			GenRegistroExcel excel = new GenRegistroExcel();
			excel.setExcId(ingresarIDRegistroExcel());
			excel.setExcUsuario(usuario);
			excel.setExcFecha(new Timestamp(new Date().getTime()));
			excel.setExcNombreArchivo(nombre_archivo);
			excel.setExcNuevos(exc_nuevos);
			excel.setExcActualizados(exc_actualizados);
			excel.setExcErrores(exc_error);
			excel.setExcInactivos(exc_inactivados);
			excel.setExcIp(Funciones.getIp());
			mngDao.insertar(excel);
			System.out.println("Bien_insertar_registro_excel");
			return "\nDatos nuevos: " + exc_nuevos + ";\n Datos actualizados: " + exc_actualizados
					+ ";\n Datos erroneos: " + exc_error + ";\n Datos inactivados: " + exc_inactivados + "";
		} catch (Exception e) {
			System.out.println("Error_insertar_registro_excel");
			e.printStackTrace();
			return null;
		}
	}// Cierre del metodo

	/**
	 * Metodo para poner id a los metodos
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer ingresarIDRegistroExcel() {
		List<GenRegistroExcel> l_exc = mngDao.findAll(GenRegistroExcel.class);
		if (l_exc == null) {
			System.out.println("valor nulo");
			return null;
		} else if (l_exc.size() == 0) {
			return 1;
		} else {
			return l_exc.size() + 1;
		}
	}// Cierre del metodo
}