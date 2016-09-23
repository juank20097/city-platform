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
import city.model.dao.entidades.extras.Funcionario;

@Stateless
public class ManagerReporteMedico {
	@EJB
	private ManagerDAO mngDao;

	public ArrayList<DatosMedicosFuncionario> getAllDatosFuncionarios() {

		ArrayList<DatosMedicosFuncionario> datosFuncionarios = new ArrayList<DatosMedicosFuncionario>();

		String sql = "SELECT " + "gen_persona.per_apellidos, " + "gen_persona.per_nombres, gen_persona.per_dni, "
				+ "gen_persona.per_fecha_nacimiento, " + "gen_persona.per_genero, " + "gen_persona.per_estado_civil, "
				+ "CONCAT( "
				+ "  (SELECT ite_nombre FROM gen_catalogo_items_det WHERE cat_codigo = 'cat_paises' AND ite_codigo = gen_persona_detalle.pde_pais_residencia), ' - ' , "
				+ "  (SELECT ite_nombre FROM gen_catalogo_items_det WHERE cat_codigo = 'cat_provincias' AND ite_codigo = gen_persona_detalle.pde_provincia_residencia), ' - ' , "
				+ "  (SELECT ite_nombre FROM gen_catalogo_items_det WHERE cat_codigo = 'cat_ciudades' AND ite_codigo = gen_persona_detalle.pde_ciudad_residencia)) AS residencia, "
				+ "  gen_persona_detalle.pde_direccion, " + "gen_persona_detalle.pde_residencia, "
				+ "gen_persona_detalle.pde_estadia_dias, " + "gen_persona_detalle.pde_estadia_horas, "
				+ "( CASE WHEN (gen_salud.sld_seguro_iess) THEN 'Si' ELSE 'No' END) AS sld_seguro_iess, "
				+ "( CASE WHEN (gen_salud.sld_seguro_privado) THEN 'Si' ELSE 'No' END) AS sld_seguro_privado, "
				+ "gen_salud.sld_asegurado AS nombre_seguro_privado, " + "gen_salud.sld_grupo_sanguineo, "
				+ "( CASE WHEN (gen_salud.sld_discapacidad) THEN 'Si' ELSE 'No' END) AS sld_discapacidad, "
				+ "gen_salud.sld_carnet_conadies, " + "gen_salud.sld_discapacidad_tipo, "
				+ "gen_salud.sld_discapacidad_grado, "
				+ "( CASE WHEN (gen_salud.sld_realiza_ejercicio) THEN 'Si' ELSE 'No' END) AS sld_realiza_ejercicio, "
				+ "gen_salud.sld_periodicidad_ejercicio, " + "gen_salud.sld_ejercicio_horas, "
				+ "( CASE WHEN (gen_salud.sld_consume_alcohol='true') THEN 'Si' ELSE 'No' END) AS sld_consume_alcohol, "
				+ "gen_salud.sld_periodicidad_alcohol, "
				+ "( CASE WHEN (gen_salud.sld_embriagar) THEN 'Si' ELSE 'No' END) AS sld_embriagar, "
				+ "gen_salud.sld_periodicidad_embriagar, "
				+ "( CASE WHEN (gen_salud.sld_consume_tabaco='true') THEN 'Si' ELSE 'No' END) AS sld_consume_tabaco, "
				+ "gen_salud.sld_periodicidad_tabaco, " + "gen_salud.sld_tabaco_semana, "
				+ "gen_salud.sld_padre_enfermedades_actuales, "
				+ "( CASE WHEN (gen_salud.sld_padre_fallecio) THEN 'Si' ELSE 'No' END) AS sld_padre_fallecio, "
				+ "gen_salud.sld_padre_edad, " + "gen_salud.sld_madre_enfermedades_actuales, "
				+ "( CASE WHEN (gen_salud.sld_madre_fallecio) THEN 'Si' ELSE 'No' END) AS sld_madre_fallecio, "
				+ "gen_salud.sld_madre_edad, " + "gen_salud.sld_alergias, " + "gen_salud.sld_medicamentos, "
				+ "gen_salud.sld_alergias_cronicas2, " + "gen_salud.sld_medicamentos_cronicos2, "
				+ "gen_salud.sld_alergias_cronicas3, " + "gen_salud.sld_medicamentos_cronicos3, "
				+ "gen_salud.sld_frecuencia_consumo_medicame, " + "gen_salud.sld_nombre_lugar_centro_medico, "
				+ "gen_salud.sld_observaciones, "
				+ "  (SELECT ite_nombre FROM gen_catalogo_items_det WHERE cat_codigo = 'cat_nivel_instruccion' AND ite_codigo = "
				+ "(SELECT MAX(foa_nivel_instruccion) FROM gen_formacionacademica WHERE gen_formacionacademica.per_dni=gen_persona.per_dni GROUP BY gen_formacionacademica.per_dni)) AS foa_nivel_instruccion "
				+ " FROM " + "gen_persona "
				+ "  INNER JOIN gen_funcionarios_institucion  ON(gen_persona.per_dni = gen_funcionarios_institucion.per_dni )"
				+ "  LEFT JOIN gen_persona_detalle ON (gen_persona.per_dni = gen_persona_detalle.pde_dni)"
				+ "  LEFT JOIN gen_salud ON (gen_persona_detalle.pde_dni = gen_salud.per_dni)" + " WHERE "
				+ "  gen_persona.per_estado = 'A' AND fun_estado ='A';";

		List<Object[]> list = mngDao.findAllNativeSQL(sql);
		for (Object it : list) {
			// DatosFuncionario datosFuncionario = (DatosFuncionario) it;
			DatosMedicosFuncionario dt = new DatosMedicosFuncionario();
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
