package city.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.GenBarrio;
import city.model.dao.entidades.GenDistrito;

@Stateless
public class ManagerBarrio {
	@EJB
	ManagerDAO mngDAO;
	
	public GenBarrio findBarrioById(String idBarrio) throws Exception{
		return (GenBarrio) mngDAO.findById(GenBarrio.class, idBarrio);
	}
	
	@SuppressWarnings("unchecked")
	public List<GenBarrio> findAllBarrios(){
		return mngDAO.findAll(GenBarrio.class);
	}
	
	public void insertarBarrio(GenBarrio barrio) throws Exception{
		mngDAO.insertar(barrio);
	}
	
	public void modicarBarrio(GenBarrio barrio) throws Exception{
		mngDAO.actualizar(barrio);
	}
	
	@SuppressWarnings("unchecked")
	public List<GenDistrito> findAllDistritosAc(){
		return mngDAO.findWhere(GenDistrito.class,"o.disEstado ='A'","o.disNombre" );
	}
}
