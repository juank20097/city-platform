package city.model.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.extras.DatosReporteTree;

@Stateless
public class ManagerReporteZonas {
	@EJB
	private ManagerDAO mngDao;

	
	public ArrayList<DatosReporteTree> getAllElementosZonas() {

		String sql = "SELECT gen_zonas.zon_id, gen_zonas.zon_nombre, gen_zonas.zon_descripcion,"
				+ " gen_zonas.zon_kilometros," + "gen_zonas.zon_observacion, gen_elementos_zona.elz_nombre,"
				+ " gen_elementos_zona.elz_tipo," + "gen_elementos_zona.elz_unidad_medida,"
				+ " gen_elemento_zona_valor.ezv_valor " 
				+ " FROM gen_zonas "
				+ " LEFT JOIN gen_elemento_zona_valor  ON (gen_zonas.zon_id = gen_elemento_zona_valor.zon_id)"
				+ " LEFT JOIN gen_elementos_zona ON (gen_elementos_zona.elz_id = gen_elemento_zona_valor.elz_id)"
				+ " ORDER BY gen_zonas.zon_id;";

		List<Object[]> list = mngDao.findAllNativeSQL(sql);

		return this.parseZonas(list);
	}

	public ArrayList<DatosReporteTree> getAllDatosVecindarios() {

		String sql = "SELECT gen_zonas.zon_id, gen_zonas.zon_nombre, gen_zonas.zon_descripcion, gen_zonas.zon_hectareas,"
				+ " gen_zonas.zon_kilometros, gen_zonas.zon_observacion, gen_distritos.dis_id, gen_distritos.dis_nombre,"
				+ " gen_distritos.dis_descripcion, gen_distritos.dis_hectareas, gen_distritos.dis_kilometros, "
				+ " gen_distritos.dis_observacion, gen_barrios.bar_id, gen_barrios.bar_nombre, gen_barrios.bar_descripcion,"
				+ " gen_barrios.bar_hectareas, gen_barrios.bar_kilometros, gen_barrios.bar_observacion, gen_elementos_barrio.elb_nombre,"
				+ " gen_elementos_barrio.elb_tipo, gen_elementos_barrio.elb_unidad_medida, gen_elemento_barrio_valor.ebv_valor"
				+ " FROM gen_zonas"
				+ " INNER JOIN gen_distritos ON (gen_zonas.zon_id = gen_distritos.zon_id)"
				+ " INNER JOIN gen_barrios ON (gen_distritos.dis_id = gen_barrios.dis_id)"
				+ " INNER JOIN gen_elemento_barrio_valor ON (gen_barrios.bar_id = gen_elemento_barrio_valor.bar_id)"
				+ " INNER JOIN gen_elementos_barrio ON (gen_elemento_barrio_valor.elb_id = gen_elementos_barrio.elb_id)"
				+ " ORDER BY gen_zonas.zon_id, gen_distritos.dis_id, gen_barrios.bar_id, gen_elementos_barrio.elb_unidad_medida;";

		List<Object[]> list = mngDao.findAllNativeSQL(sql);

		return this.parseVecindarios(list);
	}

	private String getValueNotNull(Object value) {
		return value != null ? value + "" : "";
	}

	private ArrayList<DatosReporteTree> parseZonas(List<Object[]> list) {
		ArrayList<DatosReporteTree> datosZonas = new ArrayList<DatosReporteTree>();

		for (Object it : list) {

			DatosReporteTree datosReporte = new DatosReporteTree();
			Object[] result = (Object[]) it;

			datosReporte.setId(getValueNotNull(result[0]));
			datosReporte.setNombre(getValueNotNull(result[1]));
			datosReporte.setDescripcion(getValueNotNull(result[2]));
			datosReporte.setKilometros(getValueNotNull(result[3]));
			datosReporte.setObservacion(getValueNotNull(result[4]));
			datosReporte.setElementoNombre(getValueNotNull(result[5]));
			datosReporte.setElementoTipo(getValueNotNull(result[6]));
			datosReporte.setElementoUnidadMedida(getValueNotNull(result[7]));
			datosReporte.setElementoValor(getValueNotNull(result[8]));

			datosZonas.add(datosReporte);

		}
		return datosZonas;

	}


	private ArrayList<DatosReporteTree> parseVecindarios(List<Object[]> list) {
		
		ArrayList<DatosReporteTree> datosBarrios = new ArrayList<DatosReporteTree>();

		for (Object it : list) {
			
			DatosReporteTree datosReporte = new DatosReporteTree();
			Object[] result = (Object[]) it;
			datosReporte.setParentId(getValueNotNull(result[0]));		
			datosReporte.setNombre(getValueNotNull(result[1]));
			datosReporte.setDescripcion(getValueNotNull(result[2]));
			datosReporte.setHectareas(getValueNotNull(result[3]));
			datosReporte.setKilometros(getValueNotNull(result[4]));			
			datosReporte.setObservacion(getValueNotNull(result[5]));
			datosReporte.setDistritoId(getValueNotNull(result[6]));
			datosReporte.setDistritoNombre(getValueNotNull(result[7]));
			datosReporte.setDistritoDescripcion(getValueNotNull(result[8]));
			datosReporte.setDistritoHectareas(getValueNotNull(result[9]));
			datosReporte.setDistritoKilometros(getValueNotNull(result[10]));
			datosReporte.setDistritoObservacion(getValueNotNull(result[11]));
			datosReporte.setVecindarioId(getValueNotNull(result[12]));
			datosReporte.setVecindarioNombre(getValueNotNull(result[13]));
			datosReporte.setVecindarioDescripcion(getValueNotNull(result[14]));
			datosReporte.setVecindarioHectareas(getValueNotNull(result[15]));
			datosReporte.setVecindarioKilometros(getValueNotNull(result[16]));
			datosReporte.setVecindarioObservacion(getValueNotNull(result[17]));
			datosReporte.setElementoNombre(getValueNotNull(result[18]));
			datosReporte.setElementoTipo(getValueNotNull(result[19]));
			datosReporte.setElementoUnidadMedida(getValueNotNull(result[20]));
			datosReporte.setElementoValor(getValueNotNull(result[21]));
			
			datosBarrios.add(datosReporte);

		}
		return datosBarrios;
		
	}

}
