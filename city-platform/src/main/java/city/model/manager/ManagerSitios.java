package city.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.GenCatalogoItem;
import city.model.dao.entidades.GenSectore;

/**
 * Esta Clase permite manejar el ManagerDAO en conveniencia a la gestión
 * necesaria
 * 
 * @author Juan Carlos Estévez Hidalgo
 * @version 1.0
 * 
 */
@Stateless
public class ManagerSitios {

	@EJB
	private ManagerDAO mngDao;

	/**
	 * Metodo de inicialización de contructor
	 */
	public ManagerSitios() {
	}// Cierre del Constructor

	// //////////////////////////////////////////////////////////(SECTORES)/////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla SECTORES
	 * 
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenSectore> findAllsectores() throws Exception {
		return mngDao.findAll(GenSectore.class);
	}// Cierre del metodo

	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public GenSectore SectoresByID(Integer dni) throws Exception {
		return (GenSectore) mngDao.findById(GenSectore.class, dni);
	}// Cierre del metodo

	/**
	 * Metodo para generar el id
	 * 
	 * @return
	 */
	public Integer cargarId(){
		Integer id=0;
		try {
			List<GenSectore> ls = this.findAllsectores();
			if (ls==null || ls.size()==0){
				id=1;
			}else{
				id=ls.size()+1;
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
	public void insertarSector(String nombre, String direccion,
			String descripcion) throws Exception {
		try {
			GenSectore sector = new GenSectore();
			sector.setSecId(this.cargarId());
			sector.setSecNombre(nombre);
			sector.setSecDireccionReferencial(direccion);
			sector.setSecDescripcion(descripcion);
			sector.setSecEstado("A");
			mngDao.insertar(sector);
			System.out.println("Bien_insertar_sector");
		} catch (Exception e) {
			System.out.println("Error_insertar_sector");
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
	public void editarSector(Integer id, String nombre, String direccion,
			String descripcion,String estado) throws Exception {
		try {
			GenSectore sector = this.SectoresByID(id);
			sector.setSecNombre(nombre);
			sector.setSecNombre(nombre);
			sector.setSecDireccionReferencial(direccion);
			sector.setSecDescripcion(descripcion);
			sector.setSecEstado(estado);
			mngDao.actualizar(sector);
			System.out.println("Bien_mod_sector");
		} catch (Exception e) {
			System.out.println("Error_mod_sector");
			e.printStackTrace();
		}
	}// Cierre del metodo
	
	// //////////////////////////////////////////////////////////(Items)/////////////////////////////////////////////////////////////////////
	
	/**
	 * Metodo para listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GenCatalogoItem> AllofItems(String cat_nombre) {
		List<GenCatalogoItem> li = mngDao.findWhere(GenCatalogoItem.class,
				"o.genCatalogo.catCodigo='" + cat_nombre + "'", null);
		if (li == null || li.isEmpty()) {
			return null;
		} else {
			return li;
		}
	}// Cierre del metodo

}