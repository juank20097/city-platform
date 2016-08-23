package city.model.manager;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.GenBarrio;
import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenComunidade;
import city.model.dao.entidades.GenDistrito;
import city.model.dao.entidades.GenManzana;
import city.model.dao.entidades.GenManzanaDetalle;
import city.model.dao.entidades.GenManzanaPosicione;
import city.model.dao.entidades.GenSectore;
import city.model.dao.entidades.GenZona;
import city.model.dao.entidades.GenZonasComunidade;
import city.model.dao.entidades.GenZonasComunidadePK;

@Stateless
public class ManagerTerritorio {
	@EJB
	ManagerDAO mngDAO;

	// //////////////////////////////////////////////////////////(BARRIOS)/////////////////////////////////////////////////////////////////////

	public GenBarrio findBarrioById(String idBarrio) throws Exception {
		return (GenBarrio) mngDAO.findById(GenBarrio.class, idBarrio);
	}

	@SuppressWarnings("unchecked")
	public List<GenBarrio> findAllBarrios() {
		return mngDAO.findAll(GenBarrio.class);
	}

	public void insertarBarrio(GenBarrio barrio) throws Exception {
		mngDAO.insertar(barrio);
	}

	public void modicarBarrio(GenBarrio barrio) throws Exception {
		mngDAO.actualizar(barrio);
	}

	@SuppressWarnings("unchecked")
	public List<GenDistrito> findAllDistritosAc() {
		return mngDAO.findWhere(GenDistrito.class, "o.disEstado ='A'", "o.disNombre");
	}

	// //////////////////////////////////////////////////////////(COMUNIDADES)/////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<GenComunidade> findAllcomunidades() throws Exception {
		return mngDAO.findAll(GenComunidade.class);
	}// Cierre del metodo

	@SuppressWarnings("unchecked")
	public List<GenZonasComunidade> findAllzonaComu() throws Exception {
		return mngDAO.findAll(GenZonasComunidade.class);
	}// Cierre del metodo

	/**
	 * Busca a los funcionarios por código de institución
	 * 
	 * @param insCodigo
	 * @return List<GenPersona>
	 */
	@SuppressWarnings("unchecked")
	public GenZona zonaXNombre(String nombre) {
		List<GenZona> l = mngDAO.findWhere(GenZona.class, "o.zonNombre='" + nombre + "'", null);
		if (l == null || l.isEmpty()) {
			return null;
		} else {
			return l.get(0);
		}
	}

	/**
	 * Busca a los funcionarios por código de institución
	 * 
	 * @param insCodigo
	 * @return List<GenPersona>
	 */
	@SuppressWarnings("unchecked")
	public List<GenZonasComunidade> zonacXComunidad(String id) {
		List<GenZonasComunidade> l = mngDAO.findWhere(GenZonasComunidade.class, "o.genComunidade.comId='" + id + "'",
				null);
		if (l == null || l.isEmpty()) {
			return null;
		} else {
			return l;
		}
	}

	public GenComunidade ComunidadesByID(String dni) throws Exception {
		return (GenComunidade) mngDAO.findById(GenComunidade.class, dni);
	}// Cierre del metodo

	public void insertarComunidad(String id, String nombre, BigDecimal hectareas, BigDecimal metros, Boolean ubicacion,
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
			mngDAO.insertar(comunidades);
			System.out.println("Bien_insertar_comunidades");
		} catch (Exception e) {
			System.out.println("Error_insertar_comunidades");
			e.printStackTrace();
		}
	}// Cierre del metodo

	public void insertarZonaCom(String id_com, String nom_zon) throws Exception {
		try {
			GenZona z = this.zonaXNombre(nom_zon);
			GenComunidade c = this.ComunidadesByID(id_com);
			GenZonasComunidadePK zc = new GenZonasComunidadePK();
			zc.setComId(id_com);
			zc.setZonId(z.getZonId());
			GenZonasComunidade zb = new GenZonasComunidade();
			zb.setGenZona(z);
			zb.setGenComunidade(c);
			zb.setId(zc);
			mngDAO.insertar(zb);
			System.out.println("Bien_insertar_zcomunidades");
		} catch (Exception e) {
			System.out.println("Error_insertar_zcomunidades");
			e.printStackTrace();
		}
	}// Cierre del metodo

	public void editarComunidad(String id, String nombre, BigDecimal hectareas, BigDecimal metros, Boolean ubicacion,
			String estado, String mapa, String pdf) throws Exception {
		try {
			GenComunidade comunidades = this.ComunidadesByID(id);
			comunidades.setComEstado("A");
			comunidades.setComHectareas(hectareas);
			comunidades.setComLinkMapa(mapa);
			comunidades.setComLinkPdf(pdf);
			comunidades.setComMetrosCuadrados(metros);
			comunidades.setComNombre(nombre);
			comunidades.setComUbicacion(ubicacion);
			mngDAO.actualizar(comunidades);
			System.out.println("Bien_mod_comunidad");
		} catch (Exception e) {
			System.out.println("Error_mod_comunidad");
			e.printStackTrace();
		}
	}// Cierre del metodo

	@SuppressWarnings("unchecked")
	public List<GenSectore> AllSectoresActivos() {
		List<GenSectore> l = mngDAO.findWhere(GenSectore.class, "o.secEstado='A'", null);
		return l;
	}// Cierre del metodo

	@SuppressWarnings("unchecked")
	public List<GenCatalogoItemsDet> AllofItems(String cat_nombre) {
		List<GenCatalogoItemsDet> li = mngDAO.findWhere(GenCatalogoItemsDet.class,
				"o.genCatalogoCab.catCodigo='" + cat_nombre + "'", null);
		if (li == null || li.isEmpty()) {
			return null;
		} else {
			return li;
		}
	}// Cierre del metodo

	public void eliminarZonaCom(GenZonasComunidadePK id) {
		try {
			mngDAO.eliminar(GenZonasComunidade.class, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// //////////////////////////////////////////////////////////(DISTRITOS)/////////////////////////////////////////////////////////////////////

	public GenDistrito findDistritoById(String idZona) throws Exception {
		return (GenDistrito) mngDAO.findById(GenDistrito.class, idZona);
	}

	@SuppressWarnings("unchecked")
	public List<GenDistrito> findAllDistritos() {
		return mngDAO.findAll(GenDistrito.class);
	}

	public void insertarDistrito(GenDistrito zona) throws Exception {
		mngDAO.insertar(zona);
	}

	public void modicarDistrito(GenDistrito zona) throws Exception {
		mngDAO.actualizar(zona);
	}

	public GenZona findZonaById(String idZona) throws Exception {
		return (GenZona) mngDAO.findById(GenZona.class, idZona);
	}

	// //////////////////////////////////////////////////////////(ZONAS)/////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<GenZona> findAllZonas() {
		return mngDAO.findAll(GenZona.class);
	}

	@SuppressWarnings("unchecked")
	public List<GenZona> findAllZonasA() {
		return mngDAO.findWhere(GenZona.class, "o.zonEstado='A'", null);
	}

	public void insertarZona(GenZona zona) throws Exception {
		mngDAO.insertar(zona);
	}

	public void modicarZona(GenZona zona) throws Exception {
		mngDAO.actualizar(zona);
	}

	// //////////////////////////////////////////////////////////(MANZANAS)/////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<GenManzana> findAllManzanas() {
		return mngDAO.findAll(GenManzana.class);
	}

	@SuppressWarnings("unchecked")
	public List<GenManzana> findAllManzanasA() {
		return mngDAO.findWhere(GenManzana.class, "o.manEstado='A'", null);
	}

	public void insertarManzana(GenManzana manzana) throws Exception {
		mngDAO.insertar(manzana);
	}

	public void modicarManzana(GenManzana manzana) throws Exception {
		mngDAO.actualizar(manzana);
	}

	// //////////////////////////////////////////////////////////(MANZANAS-DETALLES)/////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<GenManzanaDetalle> findAllManzanasDetalles() {
		return mngDAO.findAll(GenManzanaDetalle.class);
	}

	public void insertarManzanaDetalle(GenManzanaDetalle manzana) throws Exception {
		mngDAO.insertar(manzana);
	}

	public void modicarManzanaDetalle(GenManzanaDetalle manzana) throws Exception {
		mngDAO.actualizar(manzana);
	}

	// //////////////////////////////////////////////////////////(MANZANAS-POSICIONES)/////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<GenManzanaPosicione> findAllManzanasPosiciones() {
		return mngDAO.findAll(GenManzanaPosicione.class);
	}

	public void insertarManzanaPosiciones(GenManzanaPosicione manzana) throws Exception {
		mngDAO.insertar(manzana);
	}

	public void modicarManzanaPosiciones(GenManzanaPosicione manzana) throws Exception {
		mngDAO.actualizar(manzana);
	}

}
