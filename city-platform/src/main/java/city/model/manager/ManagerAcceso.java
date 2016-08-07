package city.model.manager;



import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import city.model.access.Menu;
import city.model.access.Submenu;
import city.model.dao.entidades.GenParametro;
import city.model.generic.ConsumeREST;
import city.model.generic.Funciones;



@Stateless
public class ManagerAcceso {
	
	@EJB
	private ManagerDAO mngDao;

	public ManagerAcceso() {
	}
	
	/**
	 * Metodo para obtener el Atributo mediante un ID
	 * 
	 * @param dni
	 * @return Objeto
	 * @throws Exception
	 */
	public GenParametro ParametroByID(String codigo) throws Exception {
		return (GenParametro) mngDao.findById(GenParametro.class, codigo);
	}// Cierre del metodo
	
	/**
	 * Lista de menus para menú dinámico
	 * @param menu
	 * @return List<Menu>
	 */
	public List<Menu> cargarMenu(JSONArray menu){
		List<Menu> menus = new ArrayList<Menu>();
		for (Object objmenu : menu) {
			Menu gmenu = new Menu();
			gmenu.setNombre(((JSONObject)objmenu).get("nombre").toString());
			gmenu.setLstLinks(new ArrayList<Submenu>());
			JSONArray jvistas = (JSONArray) ((JSONObject)objmenu).get("vistas");
			for (Object objvis : jvistas) {
				gmenu.getLstLinks().add(new Submenu(((JSONObject) objvis).get("nombre").toString(),
						((JSONObject) objvis).get("link").toString()));
			}
			menus.add(gmenu);
			gmenu=null;
		}	
		return menus;
	}
	
	/***
	 * Consulta los permisos en el SWLogin
	 * @param usr
	 * @param pass
	 * @param aplicacion
	 * @return List<Menu>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> loginWS(String usr, String pass, String aplicacion) throws Exception
	{
		GenParametro login= ParametroByID("login_ws");
		if (login==null)
			throw new Exception("Error al consultar parámetro de login");
		List<Menu> lmenu = new ArrayList<Menu>();
		JSONObject salida = new JSONObject();
		salida.put("usr", usr);salida.put("pwd", pass);salida.put("apl", aplicacion);
		JSONObject respuesta = ConsumeREST.postClient(login.getParValor(),salida);
		if(!respuesta.get("status").equals("OK"))
			throw new Exception("ERROR al consultar sus permisos: "+respuesta.get("mensaje").toString());
		else
			lmenu = cargarMenu((JSONArray) respuesta.get("value"));
		return lmenu;
		
	}
	
	/**
	 * Devuelve el nombre de una persona
	 * @param dni
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String nombrePersonaWS(String dni) throws Exception{
		JSONObject objSalida = new JSONObject();
		objSalida.put("query", dni);
		String url = Funciones.hostWS+"WSPersonas/findPersonas";
		JSONObject respuesta = ConsumeREST.postClient(url, objSalida);
		if(!respuesta.get("status").equals("OK"))
			throw new Exception("No se pudo recuperar el nombre de la persona.");
		else{
			JSONArray arrayPersona = (JSONArray) respuesta.get("value");
			JSONObject dataPersona = (JSONObject) arrayPersona.get(0);
			return Funciones.evaluarDatoWS(dataPersona.get("nombres"));
		}
	}
	
	/**
	 * Valida si posee permisos
	 * @param vista
	 * @param permisos
	 * @return true o false
	 */
	public boolean poseePermiso(String vista, List<Menu> permisos){
		for (Menu menu : permisos) {
			for (Submenu submenu : menu.getLstLinks()) {
				if(submenu.getLink().equals(vista))
					return true;
			}
		}
		return false;
	}
}
