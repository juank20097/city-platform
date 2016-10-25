package city.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenFuncionariosInstitucion;
import city.model.dao.entidades.GenParametro;

@Stateless
public class ManagerGeneral {
	@EJB
	ManagerDAO mngDAO;


	@SuppressWarnings("unchecked")
	public List<GenCatalogoItemsDet> AllofItems(String cat_nombre) {
		List<GenCatalogoItemsDet> li = mngDAO.findWhere(GenCatalogoItemsDet.class,
				"o.genCatalogoCab.catCodigo='" + cat_nombre + "'", null);
		if (li == null || li.isEmpty()) {
			return null;
		} else {
			return li;
		}
	}

	public String findParametroByID(String iParametro) throws Exception {
		GenParametro p = (GenParametro) mngDAO.findById(GenParametro.class, iParametro);
		return p.getParValor();
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
	public GenFuncionariosInstitucion findFuncionarioXDni(String per_dni) throws Exception {
		List<GenFuncionariosInstitucion> l = mngDAO.findWhere(GenFuncionariosInstitucion.class,
				"o.id.perDni='" + per_dni + "'", null);
		if (l == null || l.size() == 0) {
			return null;
		} else {
			return l.get(0);
		}
	}
	
	/**
	 * Metodo para listar todas los datos existentes
	 * 
	 * @return La lista de todas los datos encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenFuncionariosInstitucion> findAllfuncionarios() throws Exception {
		return mngDAO.findAll(GenFuncionariosInstitucion.class);
	}
}
