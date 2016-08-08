package city.model.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenComunidade;
import city.model.dao.entidades.GenSectore;
import city.model.dao.entidades.GenZona;
import city.model.dao.entidades.GenZonasComunidade;
import city.model.dao.entidades.GenZonasComunidadePK;

@Stateless
public class ManagerComunidades {

	@EJB
	private ManagerDAO mngDao;

	public ManagerComunidades() {
		// TODO Auto-generated constructor stub
	}

	// //////////////////////////////////////////////////////////(COMUNIDADES)/////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla COMUNIDADES
	 * 
	 */

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenComunidade> findAllcomunidades() throws Exception {
		return mngDao.findAll(GenComunidade.class);
	}// Cierre del metodo

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenZona> findAllzonasActivas() throws Exception {
		List<GenZona> lz= mngDao.findWhere(GenZona.class, "o.zonEstado='A'", "o.zonNombre asc");
		if (lz!=null){
			return lz;
		}else{
			lz= new ArrayList<GenZona>();
			return lz;
		}
	}// Cierre del metodo

	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenZonasComunidade> findAllzonaComu() throws Exception {
		return mngDao.findAll(GenZonasComunidade.class);
	}// Cierre del metodo

	/**
	 * Busca a los funcionarios por código de institución
	 * @param insCodigo
	 * @return List<GenPersona>
	 */
	@SuppressWarnings("unchecked")
	public GenZona zonaXNombre(String nombre){
		List<GenZona> l= mngDao.findWhere(GenZona.class, 
				"o.zonNombre='"+nombre+"'",null);
		if (l==null || l.isEmpty()){
			return null;
		}else{
			return l.get(0);
		}
	}
	
	/**
	 * Busca a los funcionarios por código de institución
	 * @param insCodigo
	 * @return List<GenPersona>
	 */
	@SuppressWarnings("unchecked")
	public List<GenZonasComunidade> zonacXComunidad(String id){
		List<GenZonasComunidade> l= mngDao.findWhere(GenZonasComunidade.class, 
				"o.genComunidade.comId='"+id+"'",null);
		if (l==null || l.isEmpty()){
			return null;
		}else{
			return l;
		}
	}
	
	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public GenComunidade ComunidadesByID(String dni) throws Exception {
		return (GenComunidade) mngDao.findById(GenComunidade.class, dni);
	}// Cierre del metodo

	/**
	 * Método para ingresar una Comunidad a la base de datos
	 * 
	 * @param nombre
	 * @param direccion
	 * @param descripcion
	 * @throws Exception
	 */
	public void insertarComunidad(String id, String nombre,
			BigDecimal hectareas, BigDecimal metros, Boolean ubicacion,
			String mapa, String pdf) throws Exception {
		try {
			GenComunidade comunidades = new GenComunidade();
			comunidades.setComId(id);
			comunidades.setComEstado("A");
			comunidades.setComHectareas(hectareas);
			comunidades.setComLinkMapa(mapa);
			comunidades.setComLinkPdf(pdf);
			comunidades.setComMetrosCuadrados(metros);
			comunidades.setComNombre(nombre);
			comunidades.setComUbicacion(ubicacion);
			mngDao.insertar(comunidades);
			System.out.println("Bien_insertar_comunidades");
		} catch (Exception e) {
			System.out.println("Error_insertar_comunidades");
			e.printStackTrace();
		}
	}// Cierre del metodo
	
	/**
	 * Método para ingresar una Comunidad a la base de datos
	 * 
	 * @param nombre
	 * @param direccion
	 * @param descripcion
	 * @throws Exception
	 */
	public void insertarZonaCom(String id_com, String nom_zon) throws Exception {
		try {
			GenZona z=this.zonaXNombre(nom_zon);
			GenComunidade c=this.ComunidadesByID(id_com);
			GenZonasComunidadePK zc = new GenZonasComunidadePK();
			zc.setComId(id_com);
			zc.setZonId(z.getZonId());
			GenZonasComunidade zb=new GenZonasComunidade();
			zb.setGenZona(z);
			zb.setGenComunidade(c);
			zb.setId(zc);
			mngDao.insertar(zb);
			System.out.println("Bien_insertar_zcomunidades");
		} catch (Exception e) {
			System.out.println("Error_insertar_zcomunidades");
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
	public void editarComunidad(String id, String nombre, BigDecimal hectareas,
			BigDecimal metros, Boolean ubicacion, String estado, String mapa,
			String pdf) throws Exception {
		try {
			GenComunidade comunidades = this.ComunidadesByID(id);
			comunidades.setComEstado("A");
			comunidades.setComHectareas(hectareas);
			comunidades.setComLinkMapa(mapa);
			comunidades.setComLinkPdf(pdf);
			comunidades.setComMetrosCuadrados(metros);
			comunidades.setComNombre(nombre);
			comunidades.setComUbicacion(ubicacion);
			mngDao.actualizar(comunidades);
			System.out.println("Bien_mod_comunidad");
		} catch (Exception e) {
			System.out.println("Error_mod_comunidad");
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
	
	public void eliminarZonaCom(GenZonasComunidadePK id){
		try {
			mngDao.eliminar(GenZonasComunidade.class, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
