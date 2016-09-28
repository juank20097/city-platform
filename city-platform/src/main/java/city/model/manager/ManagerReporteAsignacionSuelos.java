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
import city.model.dao.entidades.extras.DatosAsignacionSuelo;
import city.model.dao.entidades.extras.DatosFuncionario;
import city.model.dao.entidades.extras.Funcionario;

@Stateless
public class ManagerReporteAsignacionSuelos {
	@EJB
	private ManagerDAO mngDao;

	public ArrayList<DatosAsignacionSuelo> getAllDatosAsignacionSuelo() {

		ArrayList<DatosAsignacionSuelo> datosAsignacionSuelo = new ArrayList<DatosAsignacionSuelo>();
		String sql = "SELECT  zon_nombre, sue_actividad, sue_asignacion, sue_metros, sue_fecha_inicio,"
				+ " sue_fecha_fin, sue_observacion, sue_archivo, sue_tipo FROM gen_zonas, gen_asignacion_suelo "
				+ " WHERE  gen_zonas.zon_id = gen_asignacion_suelo.zon_id";

		List<Object[]> list = mngDao.findAllNativeSQL(sql);
		for (Object it : list) {
			// DatosFuncionario datosFuncionario = (DatosFuncionario) it;
			DatosAsignacionSuelo dt = new DatosAsignacionSuelo();
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

			datosAsignacionSuelo.add(dt);

		}

		return datosAsignacionSuelo;
	}

	private String getValueNotNull(Object value) {
		return value != null ? value + "" : "";
	}

}
