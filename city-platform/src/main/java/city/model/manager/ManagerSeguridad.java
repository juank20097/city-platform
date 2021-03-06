package city.model.manager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenFuncionariosInstitucion;
import city.model.dao.entidades.GenParametro;
import city.model.dao.entidades.SegIncidenciasAdmin;
import city.model.dao.entidades.SegRegistroEmergencia;

/**
 * Esta Clase permite manejar el ManagerDAO en conveniencia a la gesti�n
 * necesaria
 * 
 * @author Juan Carlos Est�vez Hidalgo
 * @version 1.0
 * 
 */
@Stateless
public class ManagerSeguridad {

	@EJB
	private ManagerDAO mngDao;

	/**
	 * Metodo de inicializaci�n de contructor
	 */
	public ManagerSeguridad() {
	}// Cierre del Constructor
	
	// //////////////////////////////////////////////////////////(INCIDENCIAS_ADMIN)/////////////////////////////////////////////////////////////////////
	
	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<SegIncidenciasAdmin> findAlladmin() throws Exception {
		return mngDao.findAll(SegIncidenciasAdmin.class);
	}// Cierre del metodo

	// //////////////////////////////////////////////////////////(SEGURIDAD)/////////////////////////////////////////////////////////////////////
	/**
	 * Creaci�n de metodos para el manejo de la tabla SEGURIDAD
	 * 
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<SegRegistroEmergencia> findAllseguridad() throws Exception {
		return mngDao.findAll(SegRegistroEmergencia.class,"o.genFuncionariosInstitucion.genPersona.perNombres");
	}// Cierre del metodo
	
	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<SegRegistroEmergencia> findAllseguridad(String usuario) throws Exception {
		List<SegRegistroEmergencia> l = mngDao.findWhere(SegRegistroEmergencia.class,
				"o.segUsuarioAplicacion='" + usuario + "'", "o.genFuncionariosInstitucion.genPersona.perNombres");
		if (l == null || l.size() == 0) {
			l=new ArrayList<SegRegistroEmergencia>();
			return l;
		} else {
			return l;
		}
	}

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenFuncionariosInstitucion> findAllfuncionarios() throws Exception {
		return mngDao.findAll(GenFuncionariosInstitucion.class);
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
	}

	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public SegRegistroEmergencia SeguridadByID(Integer dni) throws Exception {
		return (SegRegistroEmergencia) mngDao.findById(SegRegistroEmergencia.class, dni);
	}

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public GenFuncionariosInstitucion findFuncionarioXDni(String per_dni) throws Exception {
		List<GenFuncionariosInstitucion> l = mngDao.findWhere(GenFuncionariosInstitucion.class,
				"o.id.perDni='" + per_dni + "'", null);
		if (l == null || l.size() == 0) {
			return null;
		} else {
			return l.get(0);
		}
	}// Cierre del metodo

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenFuncionariosInstitucion> findFuncionarioXNombre(String dato) throws Exception {
		List<GenFuncionariosInstitucion> l = mngDao.findWhere(GenFuncionariosInstitucion.class,
				"o.genPersona.perApellidos like '%" + dato.toUpperCase() + "%' or o.genPersona.perNombres like '%"
						+ dato.toUpperCase() + "%'",
				null);
		if (l == null || l.size() == 0) {
			return null;
		} else {
			return l;
		}
	}// Cierre del metodo

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<SegRegistroEmergencia> findSeguridadxTipo(String tipo) throws Exception {
		List<SegRegistroEmergencia> l = mngDao.findWhere(SegRegistroEmergencia.class,
				"o.segTipoEmergencia='" + tipo + "'", null);
		return l;
	}// Cierre del metodo

	/**
	 * Metodo para generar el id
	 * 
	 * @return
	 */
	public Integer seguridadId() {
		Integer id = 0;
		try {
			List<SegRegistroEmergencia> ls = this.findAllseguridad();
			if (ls == null || ls.size() == 0) {
				id = 1;
			} else {
				id = ls.size() + 1;
			}
			return id;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Metodo para ingresar un Atributo a la base de datos
	 * 
	 * @param nombre
	 * @param direccion
	 * @param descripcion
	 * @throws Exception
	 */
	public void insertarSeguridad(Integer id, String per_dni, String accion, String emergencia, Date fecha, String tipo,
			double latitud, double longitud, String sub_tipo, String sub_hijo, String archivo,String usuario,String documento,String utm_x,String utm_y) throws Exception {
		try {
			SegRegistroEmergencia seg = new SegRegistroEmergencia();
			seg.setSegId(id);
			seg.setGenFuncionariosInstitucion(this.findFuncionarioXDni(per_dni));
			seg.setSegAccion(accion);
			seg.setSegEmergencia(emergencia);
			seg.setSegFecha(new Timestamp(fecha.getTime()));
			seg.setSegTipoEmergencia(tipo);
			seg.setSegLatitud(latitud);
			seg.setSegLongitud(longitud);
			seg.setSegSubTipo(sub_tipo);
			seg.setSegSubHijo(sub_hijo);
			seg.setSegArchivo(archivo);
			seg.setSegDocumento(documento);
			seg.setSegFechaRegistro(new Timestamp(new Date().getTime()));
			seg.setSegUsuarioAplicacion(usuario);
			seg.setSegUtmX(utm_x);
			seg.setSegUtmY(utm_y);
			mngDao.insertar(seg);
			System.out.println("Bien_insertar_seguridad");
		} catch (Exception e) {
			System.out.println("Error_insertar_seguridad");
			e.printStackTrace();
		}
	}// Cierre del metodo

	/**
	 * Metodo para editar un Atributo en la base de datos
	 * 
	 * @param id
	 * @param nombre
	 * @param direccion
	 * @param descripcion
	 * @param estado
	 * @throws Exception
	 */
	public void editarSeguridad(Integer id, String per_dni, String accion, String emergencia, Date fecha, String tipo, double latitud,
			double longitud, String sub_tipo, String sub_hijo, String archivo,String usuario,String documento,String utm_x,String utm_y) throws Exception {
		try {
			SegRegistroEmergencia seg = this.SeguridadByID(id);
			seg.setSegAccion(accion);
			seg.setGenFuncionariosInstitucion(this.findFuncionarioXDni(per_dni));
			seg.setSegEmergencia(emergencia);
			seg.setSegFecha(new Timestamp(fecha.getTime()));
			seg.setSegTipoEmergencia(tipo);
			seg.setSegLatitud(latitud);
			seg.setSegLongitud(longitud);
			seg.setSegSubTipo(sub_tipo);
			seg.setSegSubHijo(sub_hijo);
			seg.setSegArchivo(archivo);
			seg.setSegDocumento(documento);
			seg.setSegFechaRegistro(new Timestamp(new Date().getTime()));
			seg.setSegUsuarioAplicacion(usuario);
			seg.setSegUtmX(utm_x);
			seg.setSegUtmY(utm_y);
			mngDao.actualizar(seg);
			System.out.println("Bien_mod_seguridad");
		} catch (Exception e) {
			System.out.println("Error_mod_seguridad");
			e.printStackTrace();
		}
	}// Cierre del metodo

	/**
	 * Metodo para listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GenCatalogoItemsDet> AllofItems(String cat_nombre) {
		List<GenCatalogoItemsDet> li = mngDao.findWhere(GenCatalogoItemsDet.class,
				"o.genCatalogoCab.catCodigo='" + cat_nombre + "'", null);
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

}