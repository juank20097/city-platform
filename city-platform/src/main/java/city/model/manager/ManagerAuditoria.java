package city.model.manager;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.controller.access.SesionBean;
import city.model.dao.entidades.AudActividadInterna;
import city.model.generic.Funciones;

/**
 * Esta Clase permite manejar el ManagerDAO en conveniencia a la gesti�n
 * necesaria
 * 
 * @author Juan Carlos Est�vez Hidalgo
 * @version 1.0
 * 
 */
@Stateless
public class ManagerAuditoria {

	@EJB
	private ManagerDAO mngDao;

	/**
	 * Metodo de inicializaci�n de contructor
	 */
	public ManagerAuditoria() {
	}// Cierre del Constructor

	// //////////////////////////////////////////////////////////(AUDITOR�A)/////////////////////////////////////////////////////////////////////
	/**
	 * Creaci�n de metodos para el manejo de la tabla AUDITOR�A INTERNA
	 * 
	 */

	/**
	 * Metodo para ingresar un Atributo a la base de datos
	 * 
	 * @param dni
	 * @param nombres
	 * @param apellidos
	 * @param fecha_nacimiento
	 * @param genero
	 * @param telefono
	 * @param celular
	 * @param correo
	 * @param estado_civil
	 * @param estado
	 * @throws Exception
	 */
	public void insertarAuditoria(String tipo,Object obj,String usuario) throws Exception {
		try {
			String sql ="";
			if (tipo.trim().equals("insert")){
				sql="INSERT INTO "+obj.getClass().getSimpleName()+" VALUES ("+obj.toString()+");";
				AudActividadInterna auditoria = new AudActividadInterna();
				auditoria.setAudId(IngresarIDAudi());
				auditoria.setAudIp(Funciones.getIp());
				auditoria.setAudUsuario(usuario);
				auditoria.setAudFechaRegistro(new Timestamp(new Date().getTime()));
				auditoria.setAudSql(sql);
				mngDao.insertar(auditoria);
				System.out.println("Bien_insertar_auditor�a");
			} if (tipo.trim().equals("delete")){
		//		sql="DELETE FROM "+obj.getClass().getSimpleName()+" where"
			}
			
		} catch (Exception e) {
			System.out.println("Error_insertar_auditor�a");
			e.printStackTrace();
		}
	}// Cierre del metodo

	/**
	 * Metodo para poner id a los metodos
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer IngresarIDAudi() {
		List<AudActividadInterna> l_aud = mngDao
				.findAll(AudActividadInterna.class);
		if (l_aud == null) {
			System.out.println("valor nulo");
			return null;
		} else if (l_aud.size() == 0) {
			return 1;
		} else {
			return l_aud.size() + 1;
		}
	}

}