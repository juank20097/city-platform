package city.controller.access;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.json.JsonObject;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.primefaces.context.RequestContext;

import city.model.access.Menu;
import city.model.dao.entidades.GenPersona;
import city.model.generic.ConsumeREST;
import city.model.generic.Funciones;
import city.model.generic.Mail;
import city.model.generic.Mensaje;
import city.model.manager.ManagerAcceso;
import city.model.manager.ManagerPersona;

@ManagedBean
@SessionScoped
public class SesionBean implements Serializable {

	/**
	 * SERIAL ID
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerAcceso mngAcc;

	@EJB
	private ManagerPersona mngPer;

	private String usuario;
	private String pass;
	private String pass2;
	private List<Menu> menu;
	private String persona;

	public SesionBean() {
		menu = new ArrayList<Menu>();
	}

	/**
	 * @return the pass2
	 */
	public String getPass2() {
		return pass2;
	}

	/**
	 * @param pass2 the pass2 to set
	 */
	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}

	/**
	 * @return the persona
	 */
	public String getPersona() {
		return persona;
	}

	/**
	 * @param persona
	 *            the persona to set
	 */
	public void setPersona(String persona) {
		this.persona = persona;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass
	 *            the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return the menu
	 */
	public List<Menu> getMenu() {
		return menu;
	}

	/**
	 * @param menu
	 *            the menu to set
	 */
	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	/**
	 * Permite ingresar al sistema
	 * 
	 * @return
	 */
	public String logIn() {
		try {
			if (getUsuario() == null || getUsuario().isEmpty() || getPass() == null || getPass().isEmpty()) {
				Mensaje.crearMensajeWARN("Campos usuario y contrase�a requeridos");
				return "";
			} else {
				setMenu(mngAcc.loginWS(getUsuario(), getPass(), "PARAM"));
				setPass(null);
				setPersona(WSUsuario(getUsuario()));
				return "/views/index?faces-redirect=true";
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
			setPass(null);
			return "";
		}
	}

	/**
	 * Permite salir del Sistema
	 * 
	 * @return
	 */
	public String logOut() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		setPass(null);
		setUsuario(null);
		setMenu(new ArrayList<Menu>());
		return "/index?faces-redirect=true";
	}

	/**
	 * Verifica y devuelve el usuario en sesi�n
	 * 
	 * @param vista
	 *            p�gina principal de acceso
	 * @return String
	 */
	public String validarSesion(String vista) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		SesionBean user = (SesionBean) session.getAttribute("sesionBean");
		if (user == null || user.getUsuario() == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/parametros/index.xhtml");
			} catch (IOException ex) {
				Mensaje.crearMensajeERROR(ex.getMessage());
			}
			return null;
		} else {
			ManagerAcceso ma = new ManagerAcceso();
			if (ma.poseePermiso(vista, user.getMenu()))
				return user.getUsuario();
			else {
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("/parametros/views/index.xhtml");
				} catch (IOException ex) {
					Mensaje.crearMensajeERROR(ex.getMessage());
				}
				return null;
			}
		}
	}

	public GenPersona validarPersona(String vista) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		SesionBean user = (SesionBean) session.getAttribute("sesionBean");
		if (user == null || user.getPersona() == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/parametros/index.xhtml");
			} catch (IOException ex) {
				Mensaje.crearMensajeERROR(ex.getMessage());
			}
			return null;
		} else {
			ManagerAcceso ma = new ManagerAcceso();
			if (ma.poseePermiso(vista, user.getMenu()))
				try {
					System.out.println(user.getPersona()+" persona");
					return mngPer.PersonaByID(user.getPersona());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();return null;
				}
			else {
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("/parametros/views/index.xhtml");
				} catch (IOException ex) {
					Mensaje.crearMensajeERROR(ex.getMessage());
				}
				return null;
			}
		}
	}

	/**
	 * Verifica y devuelve el usuario en sesi�n
	 * 
	 * @param vista
	 *            p�gina principal de acceso
	 * @return String
	 */
	public String validarSesion() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		SesionBean user = (SesionBean) session.getAttribute("sesionBean");
		if (user == null || user.getUsuario() == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/parametros/index.xhtml");
			} catch (IOException ex) {
				Mensaje.crearMensajeERROR(ex.getMessage());
			}
			return null;
		} else {
			return user.getUsuario();
		}
	}

	public GenPersona ponerPersona(String dni) {
		GenPersona p = new GenPersona();
		try {
			if (!dni.trim().equals("ERROR")){
				p = mngPer.PersonaByID(dni);
			}else{
				p=null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			p=null;
		}
		return p;
	}
	
	public String WSUsuario(String usuario){
		String r = "";
		String param = "http://10.20.8.190:8080/yachay-ws/usuario/"+usuario+";";
		try {
			r = ConsumeREST.consumeGetRestEasyObject2(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public void EnviarClave(){
		GenPersona p= new GenPersona();
		String usu=WSUsuario(getUsuario()); //m�todo de obtener cedula por usuario
		p=ponerPersona(usu);
		if (p!=null){
			String t = "<html>"
					+ "<body>"
					+ "<p>Ingrese al siguiente link para cambiar su clave:<a href='http://localhost:8080/city-platform/cambio.xhtml'>Link de Cambio</a></p>"
					+ "</body>"
					+ "</html>";
			try {
				Mail.generateAndSendEmail(p.getPerCorreo(), "Cambio de Clave YACHAY CITY PLATFORM", t);
				Mensaje.crearMensajeINFO("El correo se envi� satisfactoriamente.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			Mensaje.crearMensajeERROR("El usuario ingresado no existe");
		}
	}
	
	@SuppressWarnings("unchecked")
	public String cambiarClave(){
		String r="";
		String ws=WSUsuario(getUsuario());
		if (ws.equals("ERROR") || ws.equals("")){
			Mensaje.crearMensajeERROR("Usuario no existente.");
		}else{
			if (getPass().trim().equals(getPass2().trim())){
				JSONObject json=new JSONObject();
				json.put("usr", getUsuario());
				json.put("pwd", getPass());
				try {
					ConsumeREST.postClientRestEasy("http://10.20.8.190:8080/yachay-ws/usuario", json);
					r="index?faces-redirect=true";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				Mensaje.crearMensajeERROR("Las claves no coinciden.");
			}
		}
		return r;
	}
	
	
}
