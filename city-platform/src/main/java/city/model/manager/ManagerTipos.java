package city.model.manager;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.GenEstudianteInstitucion;
import city.model.dao.entidades.GenEstudianteInstitucionPK;
import city.model.dao.entidades.GenExterno;
import city.model.dao.entidades.GenFuncionariosInstitucion;
import city.model.dao.entidades.GenFuncionariosInstitucionPK;
import city.model.dao.entidades.GenInstitucione;
import city.model.dao.entidades.GenPersona;

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
	public GenEstudianteInstitucion EstudianteByID(String ins_codigo,String per_dni) throws Exception {
		return (GenEstudianteInstitucion) mngDao.findWhere(GenEstudianteInstitucion.class, "o.id.insCodigo = '"+ins_codigo+"' and o.id.perDni = '"+per_dni+"'", null);
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
	public void insertarEstudiante(String ins_codigo, String per_dni, String correo,
			String nivel, String carrera, String modalidad, String area_estudio)
			throws Exception {
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
	public void editarEstudiante(String ins_codigo, String per_dni, String correo,
			String nivel, String carrera, String modalidad, String area_estudio,String estado) throws Exception {
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
		public List<GenFuncionariosInstitucion> findAllFuncionarios() throws Exception {
			return mngDao.findAll(GenFuncionariosInstitucion.class);
		}// Cierre del metodo
		
		/**
		 * Metodo para obtener el Atributo mediante un ID
		 * 
		 * @param dni
		 * @return Objeto
		 * @throws Exception
		 */
		public GenFuncionariosInstitucion FuncionarioByID(String ins_codigo,String per_dni) throws Exception {
			return (GenFuncionariosInstitucion) mngDao.findWhere(GenFuncionariosInstitucion.class, "o.id.insCodigo = '"+ins_codigo+"' and o.id.perDni = '"+per_dni+"'", null);
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
		public void insertarFuncionarios(String ins_codigo, String per_dni, String cargo,
				String direccion, Date fecha_ingreso, String gerencia, String jefe_inmediato,String tipo,String tipo_evaluacion)
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
		public void editarFuncionario(String ins_codigo, String per_dni, String cargo,
				String direccion, Date fecha_ingreso, String gerencia, String jefe_inmediato,String tipo,String tipo_evaluacion, String estado) throws Exception {
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
				public void insertarExterno(String per_dni, String tipo,String referencia)
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
				public void editarExterno(String per_dni, String tipo,String referencia) throws Exception {
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