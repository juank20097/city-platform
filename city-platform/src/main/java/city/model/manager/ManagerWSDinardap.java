package city.model.manager;

import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.AudWsDinardap;

@Stateless
public class ManagerWSDinardap {
	@EJB
	private ManagerDAO mngDao;

	public ManagerWSDinardap() {

	}

	public void createWS(String usuario, String paquete, String respuesta, Timestamp fecha) throws Exception {
		try {
			AudWsDinardap wsData = new AudWsDinardap();
			wsData.setWsdFecha(fecha);
			wsData.setWsdPaquete(paquete);
			wsData.setWsdRespuesta(respuesta);
			wsData.setWsdUsuario(usuario);
			mngDao.insertar(wsData);
			System.out.println("Bien_insertar_ws");
		} catch (Exception e) {
			System.out.println("Error_insertar_ws");
			e.printStackTrace();
		}
	}
}
