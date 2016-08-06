package city.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.GenZona;


@Stateless
public class ManagerZona {
	
	@EJB
	ManagerDAO mngDAO;
	
	public GenZona findZonaById(String idZona) throws Exception{
		return (GenZona) mngDAO.findById(GenZona.class, idZona);
	}
	
	@SuppressWarnings("unchecked")
	public List<GenZona> findAllZonas(){
		return mngDAO.findAll(GenZona.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<GenZona> findAllZonasA(){
		return mngDAO.findWhere(GenZona.class,"o.zonEstado='A'",null);
	}
	
	public void insertarZona(GenZona zona) throws Exception{
		mngDAO.insertar(zona);
	}
	
	public void modicarZona(GenZona zona) throws Exception{
		mngDAO.actualizar(zona);
	}
}

