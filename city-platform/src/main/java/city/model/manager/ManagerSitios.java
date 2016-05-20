package city.model.manager;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.GenArea;
import city.model.dao.entidades.GenCatalogoItem;
import city.model.dao.entidades.GenInstitucione;
import city.model.dao.entidades.GenSectore;
import city.model.dao.entidades.GenSitio;
import city.model.dao.entidades.GenTipoSitio;

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
	public Integer sectorId() {
		Integer id = 0;
		try {
			List<GenSectore> ls = this.findAllsectores();
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
	public void insertarSector(String nombre, String direccion,
			String descripcion) throws Exception {
		try {
			GenSectore sector = new GenSectore();
			sector.setSecId(this.sectorId());
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
			String descripcion, String estado) throws Exception {
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

	/**
	 * Metodo para listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GenSectore> AllSectoresActivos() {
		List<GenSectore> l = mngDao.findWhere(GenSectore.class,
				"o.secEstado='A'", null);
		return l;
	}// Cierre del metodo

	// //////////////////////////////////////////////////////////(AREAS)/////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla AREAS
	 * 
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenArea> findAllAreas() throws Exception {
		return mngDao.findAll(GenArea.class);
	}// Cierre del metodo

	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public GenArea AreaByID(Integer dni) throws Exception {
		return (GenArea) mngDao.findById(GenArea.class, dni);
	}// Cierre del metodo

	/**
	 * Metodo para generar el id
	 * 
	 * @return
	 */
	public Integer areaId() {
		Integer id = 0;
		try {
			List<GenArea> l = this.findAllAreas();
			if (l == null || l.size() == 0) {
				id = 1;
			} else {
				id = l.size() + 1;
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
	 * @param sector
	 * @param nombre
	 * @param descripcion
	 * @param latitud
	 * @param longitud
	 * @param padre
	 * @throws Exception
	 */
	public void insertarArea(Integer sector, String nombre, String descripcion,
			String latitud, String longitud, String padre) throws Exception {
		try {
			GenArea area = new GenArea();
			area.setAreId(this.areaId());
			area.setGenSectore(this.SectoresByID(sector));
			area.setAreNombre(nombre);
			area.setAreDescripcion(descripcion);
			area.setAreLatitud(latitud);
			area.setAreLongitud(longitud);
			area.setArePadre(padre);
			area.setAreEstado("A");
			mngDao.insertar(area);
			System.out.println("Bien_insertar_area");
		} catch (Exception e) {
			System.out.println("Error_insertar_area");
			e.printStackTrace();
		}
	}// Cierre del metodo

	/**
	 * Metodo para editar un Atributo en la base de datos
	 * 
	 * @param area
	 * @param sector
	 * @param nombre
	 * @param descripcion
	 * @param latitud
	 * @param longitud
	 * @param padre
	 * @throws Exception
	 */
	public void editarArea(Integer area, Integer sector, String nombre,
			String descripcion, String latitud, String longitud, String padre,
			String estado) throws Exception {
		try {
			GenArea are = this.AreaByID(area);
			are.setGenSectore(this.SectoresByID(sector));
			are.setAreNombre(nombre);
			are.setAreDescripcion(descripcion);
			are.setAreLatitud(latitud);
			are.setAreLongitud(longitud);
			are.setArePadre(padre);
			are.setAreEstado(estado);
			mngDao.actualizar(are);
			System.out.println("Bien_mod_area");
		} catch (Exception e) {
			System.out.println("Error_mod_area");
			e.printStackTrace();
		}
	}// Cierre del metodo

	// //////////////////////////////////////////////////////////(TIPO_SITIO)/////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla TIPO SITIO
	 * 
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenTipoSitio> findAllTipoSitios() throws Exception {
		return mngDao.findAll(GenTipoSitio.class);
	}// Cierre del metodo

	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public GenTipoSitio TipoSitioByID(Integer dni) throws Exception {
		return (GenTipoSitio) mngDao.findById(GenTipoSitio.class, dni);
	}// Cierre del metodo

	/**
	 * Metodo para generar el id
	 * 
	 * @return
	 */
	public Integer tsitioId() {
		Integer id = 0;
		try {
			List<GenTipoSitio> l = this.findAllTipoSitios();
			if (l == null || l.size() == 0) {
				id = 1;
			} else {
				id = l.size() + 1;
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
	 * @param descripcion
	 * @throws Exception
	 */
	public void insertarTipoSitio(String nombre, String descripcion)
			throws Exception {
		try {
			GenTipoSitio tsitio = new GenTipoSitio();
			tsitio.setTsiId(this.tsitioId());
			tsitio.setTsiNombre(nombre);
			tsitio.setTsiDescripcion(descripcion);
			tsitio.setTsiEstado("A");
			mngDao.insertar(tsitio);
			System.out.println("Bien_insertar_Tipo_Sitio");
		} catch (Exception e) {
			System.out.println("Error_insertar_Tipo_Sitio");
			e.printStackTrace();
		}
	}// Cierre del metodo

	/**
	 * Metodo para editar un Atributo en la base de datos
	 * 
	 * @param tsitio
	 * @param nombre
	 * @param descripcion
	 * @param estado
	 * @throws Exception
	 */
	public void editarTipoSitio(Integer tsitio, String nombre,
			String descripcion, String estado) throws Exception {
		try {
			GenTipoSitio ts = this.TipoSitioByID(tsitio);
			ts.setTsiNombre(nombre);
			ts.setTsiDescripcion(descripcion);
			ts.setTsiEstado(estado);
			mngDao.actualizar(ts);
			System.out.println("Bien_mod_Tipo_Sitio");
		} catch (Exception e) {
			System.out.println("Error_mod_Tipo_Sitio");
			e.printStackTrace();
		}
	}// Cierre del metodo

	// //////////////////////////////////////////////////////////(INSTITUCIÓN)/////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla INSTITUCIÓN
	 * 
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenInstitucione> findAllInstituciones() throws Exception {
		return mngDao.findAll(GenInstitucione.class);
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
	 * Metodo para ingresar un Atributo a la base de datos
	 * 
	 * @param codio
	 * @param nombre
	 * @param descripcion
	 * @param ruc
	 * @param razon_social
	 * @param categoria
	 * @throws Exception
	 */
	public void insertarInstitucion(String codigo, String nombre,
			String descripcion, String ruc, String razon_social,
			String categoria) throws Exception {
		try {
			GenInstitucione insti = new GenInstitucione();
			insti.setInsCodigo(codigo);
			insti.setInsNombre(nombre);
			insti.setInsCategoria(categoria);
			insti.setInsDescripcion(descripcion);
			insti.setInsRazonSocial(razon_social);
			insti.setInsRuc(ruc);
			insti.setInsEstado("A");
			mngDao.insertar(insti);
			System.out.println("Bien_insertar_Institución");
		} catch (Exception e) {
			System.out.println("Error_insertar_Institución");
			e.printStackTrace();
		}
	}// Cierre del metodo

	/**
	 * Metodo para editar un Atributo en la base de datos
	 * 
	 * @param tsitio
	 * @param nombre
	 * @param descripcion
	 * @param estado
	 * @throws Exception
	 */
	public void editarInstitucion(String codigo, String nombre,
			String descripcion, String ruc, String razon_social,
			String categoria, String estado) throws Exception {
		try {
			GenInstitucione insti = this.InstitucionByID(codigo);
			insti.setInsNombre(nombre);
			insti.setInsCategoria(categoria);
			insti.setInsDescripcion(descripcion);
			insti.setInsEstado("A");
			insti.setInsRazonSocial(razon_social);
			insti.setInsRuc(ruc);
			mngDao.actualizar(insti);
			System.out.println("Bien_mod_Institucion");
		} catch (Exception e) {
			System.out.println("Error_mod_Institucion");
			e.printStackTrace();
		}
	}// Cierre del metodo

	// //////////////////////////////////////////////////////////(SITIOS)/////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla SITIOS
	 * 
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenSitio> findAllSitios() throws Exception {
		return mngDao.findAll(GenSitio.class);
	}// Cierre del metodo

	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public GenSitio SitioByID(String dni) throws Exception {
		return (GenSitio) mngDao.findById(GenSitio.class, dni);
	}// Cierre del metodo

	/**
	 * Metodo para ingresar un Atributo a la base de datos
	 * 
	 * @param nombre
	 * @param direccion
	 * @param descripcion
	 * @throws Exception
	 */
	public void insertarSitio(String sit_id,Integer tsitio,Integer area,String institucion, String nombre, String numero,
			String descripcion, String calleP, String calleS,
			Integer capacidad, BigDecimal costo, Integer piso, Boolean acceso)
			throws Exception {
		try {
			GenSitio sitio = new GenSitio();
			sitio.setGenArea(this.AreaByID(area));
			sitio.setGenInstitucione(this.InstitucionByID(institucion));
			sitio.setGenTipoSitio(this.TipoSitioByID(tsitio));
			sitio.setSitId(sit_id);
			sitio.setSitCallePrincipal(calleP);
			sitio.setSitCalleSecundaria(calleS);
			sitio.setSitCapacidad(capacidad);
			sitio.setSitCostoArriendo(costo);
			sitio.setSitDescripcion(descripcion);
			sitio.setSitEstado("A");
			sitio.setSitIntegracionAccesos(acceso);
			sitio.setSitNombre(nombre);
			sitio.setSitNumero(numero);
			sitio.setSitPiso(piso);
			mngDao.insertar(sitio);
			System.out.println("Bien_insertar_sitio");
		} catch (Exception e) {
			System.out.println("Error_insertar_sitio");
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
	public void editarSitio(String sit_id,Integer tsitio,Integer area,String institucion, String nombre, String numero,
			String descripcion, String calleP, String calleS,
			Integer capacidad, BigDecimal costo, Integer piso, Boolean acceso,String estado) throws Exception {
		try {
			GenSitio sitio = this.SitioByID(sit_id);
			sitio.setGenArea(this.AreaByID(area));
			sitio.setGenInstitucione(this.InstitucionByID(institucion));
			sitio.setGenTipoSitio(this.TipoSitioByID(tsitio));
			sitio.setSitId(sit_id);
			sitio.setSitCallePrincipal(calleP);
			sitio.setSitCalleSecundaria(calleS);
			sitio.setSitCapacidad(capacidad);
			sitio.setSitCostoArriendo(costo);
			sitio.setSitDescripcion(descripcion);
			sitio.setSitEstado(estado);
			sitio.setSitIntegracionAccesos(acceso);
			sitio.setSitNombre(nombre);
			sitio.setSitNumero(numero);
			sitio.setSitPiso(piso);
			mngDao.actualizar(sitio);
			System.out.println("Bien_mod_sitio");
		} catch (Exception e) {
			System.out.println("Error_mod_sitio");
			e.printStackTrace();
		}
	}// Cierre del metodo

	/**
	 * Metodo para listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GenArea> AllAreasActivas() {
		List<GenArea> l = mngDao.findWhere(GenArea.class,
				"o.areEstado='A'", null);
		return l;
	}// Cierre del metodo
	
	/**
	 * Metodo para listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GenTipoSitio> AllTipoSitioActivos() {
		List<GenTipoSitio> l = mngDao.findWhere(GenTipoSitio.class,
				"o.tsiEstado='A'", null);
		return l;
	}// Cierre del metodo
	
	/**
	 * Metodo para listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GenInstitucione> AllInstitucionesActivas() {
		List<GenInstitucione> l = mngDao.findWhere(GenInstitucione.class,
				"o.insEstado='A'", null);
		return l;
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