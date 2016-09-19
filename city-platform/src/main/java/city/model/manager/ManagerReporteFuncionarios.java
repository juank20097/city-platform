package city.model.manager;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import city.model.dao.entidades.GenPersona;
import city.model.dao.entidades.extras.DatosFuncionario;
import city.model.dao.entidades.extras.Funcionario;

@Stateless
public class ManagerReporteFuncionarios {
	@EJB
	private ManagerDAO mngDao;

	public List<Object[]> test() {
		// ResultSet respuesta = mngDao.findMostPopularA();
		DatosFuncionario dt = new DatosFuncionario();
		Class ftClass = dt.getClass();
		for (Field f : ftClass.getDeclaredFields()) {
			try {
				f.set(dt, "this is public");
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Field f1 = ftClass.getDeclaredField("perDni");
			f1.set(dt, "this is public");
			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(dt.toString());
		List<Object[]> list = mngDao.findMostPopularA();
		for (Object it : list) {
			// DatosFuncionario datosFuncionario = (DatosFuncionario) it;
			Object[] result = (Object[]) it;

			for (Object object : result) {
				System.out.println(object);
			}
		}
		// List<DatosFuncionario> list = mngDao.findMostPopularA();
		// System.out.println(list.get(0).getPerApellidos());
		// for (Object it : list) {
		//
		// Object[] result = (Object[]) it;
		//
		// for (Object object : result) {
		// System.out.println(object);
		// }
		//
		// System.out.println("----------------------------------");
		// }
		return null;
	}

	public void testt() {
		// ResultSet respuesta = mngDao.findMostPopularA();
		String sql = "SELECT g FROM GenPersona g";
		// + " IN "
		// + "(SELECT a.appAplicacione.aplId FROM gen_persona a WHERE
		// a.appUsuario.usuLogin='1')";
		List<GenPersona> list = mngDao.findJPQL(sql);
		System.out.println(list.get(0));
		for (GenPersona a : list) {
			System.out.println(a.getPerApellidos());
		}
		// Object[] result = (Object[]) list.get(0);
		// for (Object object : result) {
		// System.out.println(object);
		// }
		// for (Object it : list) {
		// Object[] result = (Object[]) it;
		// for (Object object : result) {
		// System.out.println(object);
		// }
		//
		// System.out.println("----------------------------------");
		// }
	}

}
