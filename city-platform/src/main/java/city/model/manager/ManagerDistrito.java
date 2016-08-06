package city.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.GenDistrito;

@Stateless
public class ManagerDistrito {
	@EJB
	ManagerDAO mngDAO;
	
	public GenDistrito findDistritoById(String idZona) throws Exception{
		return (GenDistrito) mngDAO.findById(GenDistrito.class, idZona);
	}
	
	@SuppressWarnings("unchecked")
	public List<GenDistrito> findAllDistritos(){
		return mngDAO.findAll(GenDistrito.class);
	}
	
	public void insertarDistrito(GenDistrito zona) throws Exception{
		mngDAO.insertar(zona);
	}
	
	public void modicarDistrito(GenDistrito zona) throws Exception{
		mngDAO.actualizar(zona);
	}
}

