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

	public ArrayList<DatosFuncionario> getAllDatosFuncionarios() {

		ArrayList<DatosFuncionario> datosFuncionarios = new ArrayList<DatosFuncionario>();
		String sql = "SELECT "
				+ "gen_persona.per_nombres, gen_persona.per_apellidos, gen_persona.per_dni, gen_funcionarios_institucion.fun_cargo,"
				+ "gen_funcionarios_institucion.fun_gerencia, gen_funcionarios_institucion.fun_direccion,"
				+ "gen_funcionarios_institucion.fun_fecha_ingreso, gen_persona.per_correo,"
				+ "( CASE WHEN (SELECT COUNT(foa_titulo) AS tiene_titulos_tercer_nivel FROM gen_formacionacademica WHERE foa_nivel_instruccion='nvlIns_6' AND gen_formacionacademica.per_dni = gen_persona.per_dni GROUP BY gen_persona.per_dni)  > 0 THEN 'Si' ELSE 'No' END) AS tiene_titulos_tercer_nivel, "
				+ "(SELECT array_to_string(array_agg(foa_titulo), ', ') AS titulos_tercer_nivel FROM gen_formacionacademica WHERE foa_nivel_instruccion='nvlIns_6' AND gen_formacionacademica.per_dni = gen_persona.per_dni GROUP BY gen_persona.per_dni),"
				+ "( CASE WHEN (SELECT  COUNT(foa_titulo) AS tiene_titulos_cuarto_nivel FROM gen_formacionacademica WHERE foa_nivel_instruccion='nvlIns_7' AND gen_formacionacademica.per_dni = gen_persona.per_dni GROUP BY gen_persona.per_dni) > 0 THEN 'Si' ELSE 'No' END) AS tiene_titulos_cuarto_nivel, "
				+ "(SELECT array_to_string(array_agg(foa_titulo), ', ') AS titulos_cuarto_nivel FROM gen_formacionacademica WHERE foa_nivel_instruccion='nvlIns_7' AND gen_formacionacademica.per_dni = gen_persona.per_dni GROUP BY gen_persona.per_dni),"
				+ "gen_persona.per_fecha_nacimiento,"
				+ "(SELECT CONCAT(TO_CHAR(AGE(TIMESTAMP 'NOW()', DATE(gen_persona.per_fecha_nacimiento)), 'yy Año(s), MM Mes(es), dd'),' Día(s)') AS edad_completa),"
				+ "(SELECT EXTRACT(YEAR FROM AGE(TIMESTAMP 'now()',DATE(gen_persona.per_fecha_nacimiento))) AS edad),"
				+ "gen_persona.per_genero, gen_persona.per_correo2, gen_persona_detalle.pde_direccion, gen_persona.per_celular,"
				+ "gen_persona.per_telefono, gen_persona_detalle.pde_emerg_contacto_nombres, gen_persona_detalle.pde_emerg_contacto_telefono,"
				+ "gen_persona.per_estado_civil, gen_salud.sld_carnet_conadies" + " FROM "
				+ "gen_persona, gen_funcionarios_institucion, gen_persona_detalle, gen_salud" + " WHERE "
				+ "gen_persona.per_dni = gen_funcionarios_institucion.per_dni AND gen_persona.per_dni = gen_persona_detalle.pde_dni AND "
				+ "gen_persona.per_dni = gen_salud.per_dni";

		List<Object[]> list = mngDao.findAllNativeSQL(sql);
		for (Object it : list) {
			// DatosFuncionario datosFuncionario = (DatosFuncionario) it;
			DatosFuncionario dt = new DatosFuncionario();
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

			datosFuncionarios.add(dt);

		}

		return datosFuncionarios;
	}

	private String getValueNotNull(Object value) {
		return value != null ? value + "" : "";
	}

}
