package city.model.manager;

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
import city.model.dao.entidades.GenPersona;
import city.model.dao.entidades.extras.Estudiante;
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
public class ManagerTipos {

	@EJB
	private ManagerDAO mngDao;

	// POSICIONES DEL ARRAY DE DATOS DE Estudiantes
	private int POSICION_CEDULA = 0;
	private int POSICION_NOMBRES = 1;
	private int POSICION_APELLIDOS = 2;
	private int POSICION_FECHA = 3;
	private int POSICION_TELEFONO = 4;
	private int POSICION_CELULAR = 5;
	private int POSICION_ESTADO_CIV = 6;
	private int POSICION_GENERO = 7;
	private int POSICION_CORREO_PRO = 8;
	private int POSICION_CORREO_INS = 9;
	private int POSICION_NIVEL = 10;
	private int POSICION_CARRERA = 11;
	private int POSICION_MODALIDAD = 12;
	private int POSICION_AREA = 13;

	private String[] encabezados = { "CÉDULA", "NOMBRES", "APELLIDOS",
			"FECHA_NACIMIENTO", "TELÉFONO", "CELULAR", "ESTADO_CIVIL",
			"GÉNERO", "CORREO_PERSONAL", "CORREO_INSTITUCIONAL", "NIVEL",
			"CARRERA", "MODALIDAD", "AREA_ESTUDIO" };

	/**
	 * Metodo de inicialización de contructor
	 */
	public ManagerTipos() {
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
	public GenEstudianteInstitucion EstudianteByID(String ins_codigo,
			String per_dni) throws Exception {
		return (GenEstudianteInstitucion) mngDao.findWhere(
				GenEstudianteInstitucion.class, "o.id.insCodigo = '"
						+ ins_codigo + "' and o.id.perDni = '" + per_dni + "'",
				null);
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
	public void insertarEstudiante(String ins_codigo, String per_dni,
			String correo, String nivel, String carrera, String modalidad,
			String area_estudio) throws Exception {
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
	public void editarEstudiante(String ins_codigo, String per_dni,
			String correo, String nivel, String carrera, String modalidad,
			String area_estudio, String estado) throws Exception {
		try {
			GenEstudianteInstitucion estudiante = this.EstudianteByID(
					ins_codigo, per_dni);
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

	// ////////////////////////////////////////////PROCESOS_DE_EXCEL_(ESTUDIANTES)//////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Valida la estructura de encabezados de excel
	 * 
	 * @param row
	 * @return
	 */
	public boolean validarEncabezadosExcelEstudiante(Cell[] row) {
		if (row[POSICION_CEDULA].getContents().equals(
				encabezados[POSICION_CEDULA])
				&& row[POSICION_CARRERA].getContents().equals(
						encabezados[POSICION_CARRERA])
				&& row[POSICION_CORREO_INS].getContents().equals(
						encabezados[POSICION_CORREO_INS])
				&& row[POSICION_CORREO_PRO].getContents().equals(
						encabezados[POSICION_CORREO_PRO])
				&& row[POSICION_FECHA].getContents().equals(
						encabezados[POSICION_FECHA])
				&& row[POSICION_GENERO].getContents().equals(
						encabezados[POSICION_GENERO])
				&& row[POSICION_NIVEL].getContents().equals(
						encabezados[POSICION_NIVEL])
				&& row[POSICION_NOMBRES].getContents().equals(
						encabezados[POSICION_NOMBRES])
				&& row[POSICION_APELLIDOS].getContents().equals(
						encabezados[POSICION_APELLIDOS])
				&& row[POSICION_AREA].getContents().equals(
						encabezados[POSICION_AREA])
				&& row[POSICION_CELULAR].getContents().equals(
						encabezados[POSICION_CELULAR])
				&& row[POSICION_ESTADO_CIV].getContents().equals(
						encabezados[POSICION_ESTADO_CIV])
				&& row[POSICION_MODALIDAD].getContents().equals(
						encabezados[POSICION_MODALIDAD])
				&& row[POSICION_TELEFONO].getContents().equals(
						encabezados[POSICION_TELEFONO])) {
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
		String errores = "";
		// validar cedula
		if (column[POSICION_CEDULA].getContents() == null
				|| column[POSICION_CEDULA].getContents().trim().isEmpty()) {
			errores += " CÉDULA ESTUDIANTE vacío, ";
		} else if (Funciones.validacionCedula(column[POSICION_CEDULA]
				.getContents().trim()) != true)
			errores += " CÉDULA ESTUDIANTE invalido, ";
		// validar nombre
		if (column[POSICION_NOMBRES].getContents() == null
				|| column[POSICION_NOMBRES].getContents().trim().isEmpty()) {
			errores += " NOMBRE ESTUDIANTE vacío, ";
		}
		// validar apellidos
		if (column[POSICION_APELLIDOS].getContents() == null
				|| column[POSICION_APELLIDOS].getContents().trim().isEmpty()) {
			errores += " APELLIDOS ESTUDIANTE vacío, ";
		}
		// validar fecha nacimiento
		if (column[POSICION_FECHA].getContents() == null
				|| column[POSICION_FECHA].getContents().trim().isEmpty()) {
			errores += " FECHA NACIMIENTO vacío, ";
		}
		// validar nivel
		if (column[POSICION_NIVEL].getContents() == null
				|| column[POSICION_NIVEL].getContents().trim().isEmpty()) {
			errores += " NIVEL vacío, ";
		}
		// validar carrera
		if (column[POSICION_CARRERA].getContents() == null
				|| column[POSICION_CARRERA].getContents().trim().isEmpty()) {
			errores += " CARRERA vacío, ";
		}
		// validar correo_institucional
		if (column[POSICION_CORREO_INS].getContents() == null
				|| column[POSICION_CORREO_INS].getContents().trim().isEmpty()) {
			errores += " CORREO INSTITUCIONAL vacío, ";
		} else {
			if (Funciones.validarEmail(column[POSICION_CORREO_INS]
					.getContents().trim()) != true)
				errores += " CORREO INSTITUCIONAL invalido, ";
		}
		// validar correo
		if (column[POSICION_CORREO_PRO].getContents() == null
				|| column[POSICION_CORREO_PRO].getContents().trim().isEmpty()) {
			errores += " CORREO PROPIO vacío, ";
		} else {
			if (Funciones.validarEmail(column[POSICION_CORREO_PRO]
					.getContents().trim()) != true)
				errores += " CORREO PROPIO invalido, ";
		}
		// validar genero
		if (column[POSICION_GENERO].getContents() == null
				|| column[POSICION_GENERO].getContents().trim().isEmpty()) {
			errores += " GENERO vacío, ";
		}
		// validar area_estudio
		if (column[POSICION_AREA].getContents() == null
				|| column[POSICION_AREA].getContents().trim().isEmpty()) {
			errores += " AREA ESTUDIO vacío, ";
		}
		// validar celular
		if (column[POSICION_CELULAR].getContents() == null
				|| column[POSICION_CELULAR].getContents().trim().isEmpty()) {
			errores += " CELULAR ESTUDIO vacío, ";
		}
		// validar telefono
		if (column[POSICION_TELEFONO].getContents() == null
				|| column[POSICION_TELEFONO].getContents().trim().isEmpty()) {
			errores += " TELÉFONO vacío, ";
		}
		// validar estado_civil
		if (column[POSICION_ESTADO_CIV].getContents() == null
				|| column[POSICION_ESTADO_CIV].getContents().trim().isEmpty()) {
			errores += " ESTADO CIVIL vacío, ";
		}
		// validar modalidad
		if (column[POSICION_MODALIDAD].getContents() == null
				|| column[POSICION_MODALIDAD].getContents().trim().isEmpty()) {
			errores += " MODALIDAD vacío, ";
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
	public Estudiante crearEstudiante(List<String> datosEstudiante,
			String ins_codigo) throws Exception {
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
		est.setPerFechaNacimiento(Funciones.stringToDate(datosEstudiante
				.get(POSICION_FECHA)));
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
	public void ingresarEstudiantePersona(List<Estudiante> listadoEstudiantes)
			throws Exception {
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
			}
			if (validarExistenciaEstudiante(e.getPerDni(),e.getIns_codigo())) {
				editarEstudiante(e.getIns_codigo(),
						e.getPerDni(),
						e.getEstCorreo(), e.getEstNivel(),
						e.getEstCarrera(), e.getEstModalidad(),
						e.getEstAreaEstudio(), e.getEstEstado());
			} else {
				insertarEstudiante(e.getIns_codigo(),
						e.getPerDni(),
						e.getEstCorreo(), e.getEstNivel(),
						e.getEstCarrera(), e.getEstModalidad(),
						e.getEstAreaEstudio());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public boolean validarExistenciaPersona(String dni) {
		List<GenPersona> l_p = mngDao.findWhere(GenPersona.class, "o.perDni='"
				+ dni + "'", null);
		if (l_p == null || l_p.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean validarExistenciaEstudiante(String dni,String codigo_ins) {
		List<GenEstudianteInstitucion> l_e = mngDao.findWhere(GenEstudianteInstitucion.class, "o.id.perDni='"
				+ dni + "' and o.id.insCodigo='"+codigo_ins+"'", null);
		if (l_e == null || l_e.size() == 0) {
			return false;
		} else {
			return true;
		}
	}	

	// //////////////////////////////////////////////////////////(FUNCIONARIO-INSTITUCION)/////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla FUNCIONARIO-INSTITUCION
	 * 
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenFuncionariosInstitucion> findAllFuncionarios()
			throws Exception {
		return mngDao.findAll(GenFuncionariosInstitucion.class);
	}// Cierre del metodo

	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public GenFuncionariosInstitucion FuncionarioByID(String ins_codigo,
			String per_dni) throws Exception {
		return (GenFuncionariosInstitucion) mngDao.findWhere(
				GenFuncionariosInstitucion.class, "o.id.insCodigo = '"
						+ ins_codigo + "' and o.id.perDni = '" + per_dni + "'",
				null);
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
	public void insertarFuncionarios(String ins_codigo, String per_dni,
			String cargo, String direccion, Date fecha_ingreso,
			String gerencia, String jefe_inmediato, String tipo,
			String tipo_evaluacion) throws Exception {
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
	public void editarFuncionario(String ins_codigo, String per_dni,
			String cargo, String direccion, Date fecha_ingreso,
			String gerencia, String jefe_inmediato, String tipo,
			String tipo_evaluacion, String estado) throws Exception {
		try {
			GenFuncionariosInstitucion funcionario = this.FuncionarioByID(
					ins_codigo, per_dni);
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
	public void insertarExterno(String per_dni, String tipo, String referencia)
			throws Exception {
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
	public void editarExterno(String per_dni, String tipo, String referencia)
			throws Exception {
		try {
			GenExterno externo = this.ExternoByID(per_dni);
			externo.setPerDni(per_dni);
			externo.setExtReferencia(referencia);
			externo.setExtTipo(tipo);
			externo.setGenPersona(this.PersonaByID(per_dni));
			mngDao.actualizar(externo);
			System.out.println("Bien_mod_externo");
		} catch (Exception e) {
			System.out.println("Error_mod_externo");
			e.printStackTrace();
		}
	}// Cierre del metodo

}