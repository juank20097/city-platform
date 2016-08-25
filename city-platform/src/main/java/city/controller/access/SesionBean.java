package city.controller.access;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import city.model.access.Menu;
import city.model.dao.entidades.GenParametro;
import city.model.dao.entidades.GenPersona;
import city.model.generic.ConsumeREST;
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

	/**
	 * Verifica y devuelve el usuario en sesión
	 * 
	 * @param vista
	 *            página principal de acceso
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
	 * Verifica y devuelve el usuario en sesión
	 * 
	 * @param vista
	 *            página principal de acceso
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
		try {
			GenParametro p = mngAcc.ParametroByID("usuario_ws");
			String param = p.getParValor()+"/"+usuario;
			r = ConsumeREST.consumeGetRestEasyObject2(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public void EnviarClave(){
		String cambio="";
		String usu=WSUsuario(getUsuario());
		GenPersona p=ponerPersona(usu);
			try {
				GenParametro p1 = mngAcc.ParametroByID("direccion_cambio");
				cambio= p1.getParValor();
				if (p!=null){
					String t = "<html>"
							+ "<body>"
							+ "<p>Ingrese al siguiente link para cambiar su clave:<a href='"+cambio+"'>Cambio de Credencial</a></p>"
							+ "</body>"
							+ "</html>";
				envioMailWS(p.getPerCorreo(), "Cambio de Credenciales", t);
				Mensaje.crearMensajeINFO("El correo se envió satisfactoriamente.");
				}else{
					Mensaje.crearMensajeERROR("El usuario ingresado no existe");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@SuppressWarnings("unchecked")
	public String cambiarClave(){
		String r="";
		String t="";
		String ws=WSUsuario(getUsuario());
		if (ws.equals("ERROR") || ws.equals("")){
			Mensaje.crearMensajeERROR("Usuario no existente.");
		}else{
			if (getPass().trim().equals(getPass2().trim())){
				JSONObject json=new JSONObject();
				json.put("usr", getUsuario());
				json.put("pwd", getPass());
				try {
					GenParametro p1 = mngAcc.ParametroByID("usuario_ws");
					 t=ConsumeREST.postClientRestEasy2(p1.getParValor(), json);
					 setPass("");
					 setPass2("");
					 setUsuario("");
					 if (t.equals("ERROR")){
						 Mensaje.crearMensajeERROR("Su clave no pudo ser cambiada.");
						 r=""; 
					 }else if(t.equals("OK")){
						 Mensaje.crearMensajeINFO("Su clave fue cambiada exitosamente.");
						 r="index?faces-redirect=true";
					 }
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
	
	@SuppressWarnings("unchecked")
	 public void envioMailWS(String para, String asunto, String body) throws Exception {
	  GenParametro param= mngAcc.ParametroByID("email_ws");
	  GenParametro idWS = mngAcc.ParametroByID("id_email");
	  JSONObject objSalida = new JSONObject();
	  objSalida.put("id", idWS.getParValor());
	  objSalida.put("para", para);
	  objSalida.put("asunto", asunto);
	  objSalida.put("body",body);
	  System.out.println("Envio Mail ---> "+objSalida);
	  String url = param.getParValor();
	  JSONObject respuesta = ConsumeREST.postClientRestEasy(url, objSalida);
	  if (!respuesta.get("respuesta").equals("OK"))
	   throw new Exception("Error al enviar el correo. (WS)");
	 }
	
	
}
