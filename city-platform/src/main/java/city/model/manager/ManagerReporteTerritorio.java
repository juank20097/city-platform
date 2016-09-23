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

import city.model.dao.entidades.extras.DatosMedicosFuncionario;
import city.model.dao.entidades.extras.DatosTerritorio;

@Stateless
public class ManagerReporteTerritorio {
	@EJB
	private ManagerDAO mngDao;

	public ArrayList<DatosTerritorio> getAllDatosTerritorios() {

		ArrayList<DatosTerritorio> datosTerritorios = new ArrayList<DatosTerritorio>();
		
		String sql = "SELECT gen_zonas.zon_nombre, gen_zonas.zon_descripcion, gen_zonas.zon_kilometros,"
				+ " gen_zonas.zon_observacion, gen_elementos_zona.elz_nombre, gen_elementos_zona.elz_tipo,"
				+ " gen_elementos_zona.elz_unidad_medida, gen_elemento_zona_valor.ezv_valor, gen_distritos.dis_nombre,"
				+ " gen_distritos.dis_descripcion, gen_distritos.dis_kilometros, gen_distritos.dis_observacion,"
				+ " gen_barrios.bar_nombre, gen_barrios.bar_descripcion, gen_barrios.bar_kilometros, "
				+ " gen_barrios.bar_hectareas, gen_barrios.bar_observacion, gen_elementos_barrio.elb_nombre,"
				+ " gen_elementos_barrio.elb_tipo, gen_elementos_barrio.elb_unidad_medida, gen_elemento_barrio_valor.ebv_valor"
				+ " FROM"
				+ " gen_zonas"
				+ " LEFT JOIN gen_elemento_zona_valor  ON (gen_zonas.zon_id = gen_elemento_zona_valor.zon_id)"
				+ " LEFT JOIN gen_elementos_zona ON (gen_elementos_zona.elz_id = gen_elemento_zona_valor.elz_id)"
				+ " LEFT JOIN gen_distritos  ON (gen_zonas.zon_id = gen_distritos.zon_id)"
				+ " LEFT JOIN gen_barrios  ON (gen_distritos.dis_id = gen_barrios.dis_id)"
				+ " LEFT JOIN gen_elemento_barrio_valor  ON (gen_barrios.bar_id = gen_elemento_barrio_valor.bar_id)"
				+ " LEFT JOIN gen_elementos_barrio  ON (gen_barrios.bar_id = gen_elemento_barrio_valor.bar_id);";

		List<Object[]> list = mngDao.findAllNativeSQL(sql);
		for (Object it : list) {
			// DatosFuncionario datosFuncionario = (DatosFuncionario) it;
			DatosTerritorio dt = new DatosTerritorio();
			Class ftClass = dt.getClass();
			Object[] result = (Object[]) it;
			int i = 0;
			for (Field f : ftClass.getDeclaredFields()) {
				try {
					// System.out.println(result[i]);
					f.set(dt, getValueNotNull(result[i++]) + "");
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			datosTerritorios.add(dt);

		}

		return datosTerritorios;
	}

	private String getValueNotNull(Object value) {
		return value != null ? value + "" : "";
	}

}
