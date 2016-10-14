package city.controller.access;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Request;

import org.json.simple.JSONObject;

import city.model.access.Menu;
import city.model.dao.entidades.GenParametro;
import city.model.dao.entidades.GenPersona;
import city.model.generic.ConsumeREST;
import city.model.generic.Funciones;
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

	private static SecretKey key = Funciones.addKey("YachayEP2016-cambio!/");

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
	 * @param pass2
	 *            the pass2 to set
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
				Mensaje.crearMensajeWARN("Campos usuario y contraseña requeridos");
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

	public GenPersona validarPersona(String vista) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setMaxInactiveInterval(1200);
		SesionBean user = (SesionBean) session.getAttribute("sesionBean");
		if (user == null || user.getPersona() == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
			} catch (IOException ex) {
				Mensaje.crearMensajeERROR(ex.getMessage());
			}
			return null;
		} else {
			ManagerAcceso ma = new ManagerAcceso();
			if (ma.poseePermiso(vista, user.getMenu()))
				try {
					System.out.println(user.getPersona() + " persona");
					return mngPer.PersonaByID(user.getPersona());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			else {
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("/views/index.xhtml");
				} catch (IOException ex) {
					Mensaje.crearMensajeERROR(ex.getMessage());
				}
				return null;
			}
		}
	}

	/**
	 * Verifica y devuelve el usuario en sesión
	 * 
	 * @param vista
	 *            página principal de acceso
	 * @return String
	 */
	public String validarSesion() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setMaxInactiveInterval(1200);
		SesionBean user = (SesionBean) session.getAttribute("sesionBean");
		if (user == null || user.getUsuario() == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
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
			if (!dni.trim().equals("ERROR")) {
				p = mngPer.PersonaByID(dni);
			} else {
				p = null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			p = null;
		}
		return p;
	}

	public String WSUsuario(String usuario) {
		String r = "";
		try {
			GenParametro p = mngAcc.ParametroByID("usuario_ws");
			String param = p.getParValor() + "/" + usuario;
			r = ConsumeREST.consumeGetRestEasyObject2(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}


	public void EnviarClave() {
		String cambio = "";
		String usu = WSUsuario(getUsuario());
		GenPersona p = ponerPersona(usu);
		try {
			GenParametro p1 = mngAcc.ParametroByID("direccion_cambio");
			cambio = p1.getParValor();
			if (p != null) {
				String texto;
				texto = "<html>" + "<body>" + "<p>Ingrese al siguiente link para cambiar su clave:<a href='" + cambio
						+ "?usuario=" + URLEncoder.encode(Funciones.encriptarAES256(getUsuario(), key), "UTF-8") + "'>Cambio de Credencial</a></p>"
						+ "</body>" + "</html>";
				if (p.getPerCorreo()!=null)
				envioMailWS(p.getPerCorreo(), "Cambio de Credenciales", texto);
				if (p.getPerCorreo2()!=null)
				envioMailWS(p.getPerCorreo2(), "Cambio de Credenciales", texto);
				Mensaje.crearMensajeINFO("El correo se envió satisfactoriamente.");
			} else {
				Mensaje.crearMensajeERROR("El usuario ingresado no existe");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String cambiarClave() {
		String respuesta = "";
		String res_json = "";
		try {
			String variable;
			System.out.println(getUsuario());
			variable = Funciones.desencriptarAES256(getUsuario(), key);
			if (verificarSimilitudPassword(pass, pass2)) {
				try {
					GenParametro p1 = mngAcc.ParametroByID("usuario_ws");
					res_json = ConsumeREST.postClientRestEasy2(p1.getParValor(), crearJson(variable, pass));
					setPass("");
					setPass2("");
					setUsuario("");
					if (res_json.equals("ERROR")) {
						Mensaje.crearMensajeERROR("Su clave no pudo ser cambiada.");
						respuesta = "";
					} else if (res_json.equals("OK")) {
						Mensaje.crearMensajeINFO("Su clave fue cambiada exitosamente.");
						respuesta = "index?faces-redirect=true";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Mensaje.crearMensajeERROR("Las claves no coinciden.");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return respuesta;
	}
	

	
	
	
	public boolean verificarSimilitudPassword(String pass1, String pass2) {
		if (pass1.trim().equals(pass2.trim())) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public JSONObject crearJson(String usuario, String password) {
		JSONObject json = new JSONObject();
		json.put("usr", usuario);
		json.put("pwd", password);
		return json;
	}

	@SuppressWarnings("unchecked")
	public void envioMailWS(String para, String asunto, String body) throws Exception {
		GenParametro param = mngAcc.ParametroByID("email_ws");
		GenParametro idWS = mngAcc.ParametroByID("id_email");
		JSONObject objSalida = new JSONObject();
		objSalida.put("id", idWS.getParValor());
		objSalida.put("para", para);
		objSalida.put("asunto", asunto);
		objSalida.put("body", body);
		System.out.println("Envio Mail ---> " + objSalida);
		String url = param.getParValor();
		JSONObject respuesta = ConsumeREST.postClientRestEasy(url, objSalida);
		if (!respuesta.get("respuesta").equals("OK"))
			throw new Exception("Error al enviar el correo. (WS)");
	}

	public void validarUsuarioSession() {
		String usuario = validarSesion();
		System.out.println("Usuario de session: " + usuario);
	}
	
	

}
