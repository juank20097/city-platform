package city.model.manager;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenFuncionariosInstitucion;
import city.model.dao.entidades.SegRegistroEmergencia;
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
public class ManagerSeguridad {

	@EJB
	private ManagerDAO mngDao;

	/**
	 * Metodo de inicialización de contructor
	 */
	public ManagerSeguridad() {
	}// Cierre del Constructor

	// //////////////////////////////////////////////////////////(SEGURIDAD)/////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla SEGURIDAD
	 * 
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<SegRegistroEmergencia> findAllseguridad() throws Exception {
		return mngDao.findAll(SegRegistroEmergencia.class);
	}// Cierre del metodo

	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public SegRegistroEmergencia SeguridadByID(Integer dni) throws Exception {
		return (SegRegistroEmergencia) mngDao.findById(SegRegistroEmergencia.class, dni);
	}// Cierre del metodo

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public GenFuncionariosInstitucion findFuncionarioXDni(String per_dni) throws Exception {
		List<GenFuncionariosInstitucion> l = mngDao.findWhere(GenFuncionariosInstitucion.class,
				"o.id.perDni='" + per_dni + "'", null);
		if ( l==null || l.size()==0){
		return null;
		} else{
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
				"o.genPersona.perApellidos like '%" + dato.toUpperCase() + "%' or o.genPersona.perNombres like '%" + dato.toUpperCase() + "%'", null);
		if (l==null || l.size()==0){
			return null;
		}else{
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
	public void insertarSeguridad(Integer id,String per_dni, String accion, String emergencia, Date fecha, String tipo,
			double latitud,double longitud) throws Exception {
		try {
			SegRegistroEmergencia seg = new SegRegistroEmergencia();
			seg.setSegId(id);
			seg.setGenFuncionariosInstitucion(this.findFuncionarioXDni(per_dni));
			seg.setSegAccion(accion);
			seg.setSegEmergencia(emergencia);
			seg.setSegFecha(new Timestamp(new Date().getTime()));
			seg.setSegTipoEmergencia(tipo);
			seg.setSegLatitud(latitud);
			seg.setSegLongitud(longitud);
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
	public void editarSeguridad(Integer id, String accion, String emergencia, Date fecha, String tipo,
			double latitud,double longitud) throws Exception {
		try {
			SegRegistroEmergencia seg = this.SeguridadByID(id);
			seg.setSegAccion(accion);
			seg.setSegEmergencia(emergencia);
			seg.setSegFecha(new Timestamp(fecha.getTime()));
			seg.setSegTipoEmergencia(tipo);
			seg.setSegLatitud(latitud);
			seg.setSegLongitud(longitud);
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

}