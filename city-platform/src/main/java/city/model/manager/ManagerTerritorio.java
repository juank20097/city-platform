package city.model.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.GenAsignacionSuelo;
import city.model.dao.entidades.GenBarrio;
import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenComunidade;
import city.model.dao.entidades.GenContratoAsignacion;
import city.model.dao.entidades.GenDistrito;
import city.model.dao.entidades.GenElementoBarrioValor;
import city.model.dao.entidades.GenElementoBarrioValorPK;
import city.model.dao.entidades.GenElementoZonaValor;
import city.model.dao.entidades.GenElementoZonaValorPK;
import city.model.dao.entidades.GenElementosBarrio;
import city.model.dao.entidades.GenElementosZona;
import city.model.dao.entidades.GenEntregablesContrato;
import city.model.dao.entidades.GenFuncionariosInstitucion;
import city.model.dao.entidades.GenHistorialSeguimiento;
import city.model.dao.entidades.GenHistorialSeguimientoPK;
import city.model.dao.entidades.GenManzana;
import city.model.dao.entidades.GenManzanaDetalle;
import city.model.dao.entidades.GenManzanaPosicione;
import city.model.dao.entidades.GenParametro;
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
		return mngDAO.findAll(GenBarrio.class,"o.barId");
	}

	@SuppressWarnings("unchecked")
	public List<GenBarrio> findAllBarriosA() {
		return mngDAO.findWhere(GenBarrio.class, "o.barEstado = 'A' ", "o.barId");
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
		return mngDAO.findAll(GenComunidade.class,"o.comNombre");
	}// Cierre del metodo

	@SuppressWarnings("unchecked")
	public List<GenZonasComunidade> findAllzonaComu() throws Exception {
		return mngDAO.findAll(GenZonasComunidade.class);
	}// Cierre del metodo

	/**
	 * Busca a los funcionarios por cï¿½digo de instituciï¿½n
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
	 * Busca a los funcionarios por cï¿½digo de instituciï¿½n
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
		if(cat_nombre!=null){
		List<GenCatalogoItemsDet> li = mngDAO.findWhere(GenCatalogoItemsDet.class,
				"o.genCatalogoCab.catCodigo='" + cat_nombre + "'", null);
		if (li == null || li.isEmpty()) {
			return null;
		} else {
			return li;
		}
		}else 
			return null;
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
		return mngDAO.findAll(GenDistrito.class, "o.disId");
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
		return mngDAO.findAll(GenZona.class,"o.zonId");
	}

	@SuppressWarnings("unchecked")
	public List<GenZona> findAllZonasA() {
		return mngDAO.findWhere(GenZona.class, "o.zonEstado='A'", "o.zonId");
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
		return mngDAO.findAll(GenManzana.class,"o.manNombre");
	}

	@SuppressWarnings("unchecked")
	public List<GenManzana> findAllManzanasA() {
		return mngDAO.findWhere(GenManzana.class, "o.manEstado='A'", "o.manNombre");
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

	////////////////////////////////////// (Generales) ////////////////////////// /////////////////////////////////////

	public String findParametroByID(String iParametro) throws Exception {
		GenParametro p = (GenParametro) mngDAO.findById(GenParametro.class, iParametro);
		return p.getParValor();
	}

	//////////////////////////// (Elementos Barrio) ////////////////////////////

	public GenElementosBarrio findElementosBarrioByID(int idElemento) throws Exception {
		return (GenElementosBarrio) mngDAO.findById(GenElementosBarrio.class, idElemento);
	}

	public Integer idElementoBarrio() {
		Integer id = 0;
		try {
			List<GenElementosBarrio> ls = this.findAllElementosBarrio();
			if (ls == null || ls.size() == 0) {
				id = 1;
			} else {
				id = ls.size() + 1;
			}
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<GenElementosBarrio> findAllElementosBarrio() {
		return mngDAO.findAll(GenElementosBarrio.class);
	}

	public void insertarElementoBarrio(GenElementosBarrio elemento) throws Exception {
		mngDAO.insertar(elemento);
	}

	public void modificarElementoBarrio(GenElementosBarrio elemento) throws Exception {
		mngDAO.actualizar(elemento);
	}

	@SuppressWarnings("unchecked")
	public List<GenElementoBarrioValor> findAllElementoBVByElemento(int idElemento) {
		return mngDAO.findWhere(GenElementoBarrioValor.class, "o.id.elbId = " + idElemento, "");
	}

	/// ***
	@SuppressWarnings("unchecked")
	public List<GenElementoBarrioValor> findElementoBVByElementoActivas(int idElemento) {
		return mngDAO.findWhere(GenElementoBarrioValor.class, "o.ebvEstado = 'A' ", "o.genBarrio.barNombre");
	}

	public GenElementoBarrioValor findElementoBarrioValorByID(GenElementoBarrioValorPK id) throws Exception {
		return (GenElementoBarrioValor) mngDAO.findById(GenElementoBarrioValor.class, id);
	}

	public void insertarElementoBarrioValor(GenElementoBarrioValor barrioValor) throws Exception {
		mngDAO.insertar(barrioValor);
	}

	public void modificarElementoBarrioValor(GenElementoBarrioValor barrioValor) throws Exception {
		mngDAO.actualizar(barrioValor);
	}

	///////////////////////// (Elementos Zonas) /////////////////// /////////////////////////////////

	public Integer idElementoZona() {
		Integer id = 0;
		try {
			List<GenElementosZona> ls = this.findAllElementosZona();
			if (ls == null || ls.size() == 0) {
				id = 1;
			} else {
				id = ls.size() + 1;
			}
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<GenElementosZona> findAllElementosZona() {
		return mngDAO.findAll(GenElementosZona.class);
	}

	@SuppressWarnings("unchecked")
	public List<GenElementoZonaValor> findAllElementoZVByElemento(int idElemento) {
		return mngDAO.findWhere(GenElementoZonaValor.class, "o.id.elzId = " + idElemento, "");
	}

	public void insertarElementoZona(GenElementosZona elementoZ) throws Exception {
		mngDAO.insertar(elementoZ);
	}

	public void modificarElementoZona(GenElementosZona elementoZ) throws Exception {
		mngDAO.actualizar(elementoZ);
	}

	public GenElementosZona findElementosZonaByID(int idElemento) throws Exception {
		return (GenElementosZona) mngDAO.findById(GenElementosZona.class, idElemento);
	}

	public GenElementoZonaValor findElementoZonaValorByID(GenElementoZonaValorPK id) throws Exception {
		return (GenElementoZonaValor) mngDAO.findById(GenElementoZonaValor.class, id);
	}

	public void insertarElementoZonaValor(GenElementoZonaValor zonaValor) throws Exception {
		mngDAO.insertar(zonaValor);
	}

	public void modificarElementoZonaValor(GenElementoZonaValor zonaValor) throws Exception {
		mngDAO.actualizar(zonaValor);
	}

	public String catalogoItem(String idItem) throws Exception {
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

	public GenCatalogoItemsDet ItemByID(String dni) throws Exception {
		return (GenCatalogoItemsDet) mngDAO.findById(GenCatalogoItemsDet.class, dni);
	}

	@SuppressWarnings("unchecked")
	public List<GenBarrio> findBarriosByDistrito(String distrito) {
		return mngDAO.findWhere(GenBarrio.class, "o.genDistrito.disId='" + distrito + "' ", null);
	}

	@SuppressWarnings("unchecked")
	public List<GenDistrito> findDistritosByZona(String zona) {
		return mngDAO.findWhere(GenDistrito.class, "o.genZona.zonId = '" + zona + "'", null);
	}

	@SuppressWarnings("unchecked")
	public List<GenBarrio> findBarriosByZona(String zona) {
		return mngDAO.findWhere(GenBarrio.class, "o.genDistrito.genZona.zonId ='" + zona + "'", null);
	}

	public BigDecimal sumHectareasDistrito(String distrito) {
		return (BigDecimal) mngDAO.ejectNativeSQL2(
				"SELECT coalesce(sum(bar_hectareas),0) FROM gen_barrios WHERE bar_hectareas is not null and dis_id ='"
						+ distrito + "';");
	}

	public BigDecimal sumHectareasZona(String zona) {
		return (BigDecimal) mngDAO.ejectNativeSQL2(
				"select  coalesce(sum(bar_hectareas),0) from gen_barrios where bar_hectareas is not null and dis_id in (select dis_id from gen_distritos where zon_id ='"
						+ zona + "');");

	}

	// //////////////////////////////////////////////////////////(AsignacionSuelo)/////////////////////////////////////////////////////////////////////

	/**
	 * Metodo para generar el id
	 * 
	 * @return
	 */
	public Integer asignacionSueloId() {
		Integer id = 0;
		try {
			id = (Integer) mngDAO.ejectNativeSQL2("select max(sue_id) from gen_asignacion_suelo limit 1;");
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

	/**
	 * Metodo para generar el id
	 * 
	 * @return
	 */
	public String fechasaAnios(Date fechaInicio, Date fechaFin, Integer idSuelo) {
		String numero = "";
		try {
			numero = mngDAO.ejectNativeSQL2(
					"SELECT EXTRACT(YEAR FROM AGE(DATE('"+fechaFin+"'),"
					+ "DATE('"+fechaInicio+"'))) "
					+ " from gen_asignacion_suelo where sue_id = "+idSuelo+" ;").toString();
			return numero;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<GenAsignacionSuelo> findAllAsignacionSuelo() {
		return mngDAO.findAll(GenAsignacionSuelo.class, "o.sueId");
	}

	public void insertarAsignacionSuelo(GenAsignacionSuelo asignacionSuelo) throws Exception {
		mngDAO.insertar(asignacionSuelo);
	}

	public void modicarAsignacionSuelo(GenAsignacionSuelo asignacionSuelo) throws Exception {
		mngDAO.actualizar(asignacionSuelo);
	}

	public GenAsignacionSuelo findAsignacionSueloById(Integer idAsignacionSuelo) throws Exception {
		return (GenAsignacionSuelo) mngDAO.findById(GenAsignacionSuelo.class, idAsignacionSuelo);
	}
	
	@SuppressWarnings("unchecked")
	public List<GenHistorialSeguimiento> listaSeguimientoFiltrado(Integer idAsigSuelo){
		return mngDAO.findWhere(GenHistorialSeguimiento.class, "o.id.sueId="+idAsigSuelo+" and o.hseEstado='A'", "o.hseFecha desc");
	}

	// Contratos
	
	@SuppressWarnings("unchecked")
	public List<GenContratoAsignacion> findAllContratosPorAsignacion(int idSuelo){
		return mngDAO.findWhere(GenContratoAsignacion.class, "o.genAsignacionSuelo.sueId="+idSuelo, "o.casFechaInicio");
	}
	
	// Entregables
	
	@SuppressWarnings("unchecked")
	public List<GenEntregablesContrato> findAllEntregablesPorContrato(String idContrato){
		return mngDAO.findWhere(GenEntregablesContrato.class, "o.genContratoAsignacion.casId = '"+idContrato+"'", "o.id.ecoDocumento");
	}
	///////////////////////////// (Seguimiento Asignación Suelo) ////////////////////////////
	
	/**
	 * Método para generar el id de seguimiento asignación
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer idSeguimientoAsignacion(int idAsignacion) {
		Integer id = 0;
		try {
			List<GenHistorialSeguimiento> lst= mngDAO.findWhere(GenHistorialSeguimiento.class, "o.id.sueId = "+idAsignacion, null);
			if (lst.isEmpty()) {
				id = 1;
			} else {
				id =  lst.size()+ 1;
			}
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public GenFuncionariosInstitucion findFuncionarioXDni(String per_dni) throws Exception {
		List<GenFuncionariosInstitucion> l = mngDAO.findWhere(GenFuncionariosInstitucion.class,
				"o.id.perDni='" + per_dni + "'", null);
		if (l == null || l.size() == 0) {
			return null;
		} else {
			return l.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<GenHistorialSeguimiento> findSeguimientoByIDAsignacion(int idAsignacion){
		return mngDAO.findWhere(GenHistorialSeguimiento.class, "o.id.sueId="+idAsignacion, "o.hseFecha");
	}
	
	public GenHistorialSeguimiento findSeguimientoByID(GenHistorialSeguimientoPK idSeguimiento) throws Exception{
		return (GenHistorialSeguimiento) mngDAO.findById(GenHistorialSeguimiento.class, idSeguimiento);
	}
	
	public void insertarSeguimiento(GenHistorialSeguimiento seguimiento) throws Exception{
		mngDAO.insertar(seguimiento);
	}
	
	public void modificarSeguimiento(GenHistorialSeguimiento seguimiento) throws Exception{
		mngDAO.actualizar(seguimiento);
	}
}
