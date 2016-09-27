package city.model.dao.entidades.extras;

import java.lang.reflect.Field;

public class DatosReporteTree implements Cloneable{

	private String parentId;
	private String id;
	private String nombre;
	private String descripcion;
	private String kilometros;
	private String hectareas;
	private String observacion;
	private String elementoNombre;
	private String elementoTipo;
	private String elementoUnidadMedida;
	private String elementoValor;
	private String distritoId;
	private String distritoNombre;
	private String distritoDescripcion;
	private String distritoHectareas;
	private String distritoKilometros;
	private String distritoObservacion;
	private String vecindarioId;
	private String vecindarioNombre;
	private String vecindarioDescripcion;
	private String vecindarioHectareas;
	private String vecindarioKilometros;
	private String vecindarioObservacion;
	private String tipoEstructura;

	public DatosReporteTree() {

	}

	public DatosReporteTree(DatosReporteTree datosReporteTree) {
		
		parentId = datosReporteTree.getParentId();
		id = datosReporteTree.getId();
		nombre = datosReporteTree.getNombre();
		descripcion = datosReporteTree.getDescripcion();
		kilometros = datosReporteTree.getKilometros();
		hectareas = datosReporteTree.getHectareas();
		observacion = datosReporteTree.getObservacion();
		elementoNombre = datosReporteTree.getElementoNombre();
		elementoTipo = datosReporteTree.getElementoTipo();
		elementoUnidadMedida = datosReporteTree.getElementoUnidadMedida();
		elementoValor = datosReporteTree.getElementoValor();
		distritoId = datosReporteTree.getDistritoId();
		distritoNombre = datosReporteTree.getDistritoNombre();
		distritoDescripcion = datosReporteTree.getDistritoDescripcion();
		distritoHectareas = datosReporteTree.getDistritoHectareas();
		distritoKilometros = datosReporteTree.getDistritoKilometros();
		distritoObservacion = datosReporteTree.getDistritoObservacion();
		vecindarioId = datosReporteTree.getVecindarioId();
		vecindarioNombre = datosReporteTree.getVecindarioNombre();
		vecindarioDescripcion = datosReporteTree.getVecindarioDescripcion();
		vecindarioHectareas = datosReporteTree.getVecindarioHectareas();
		vecindarioKilometros = datosReporteTree.getVecindarioKilometros();
		vecindarioObservacion = datosReporteTree.getVecindarioObservacion();
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getKilometros() {
		return kilometros;
	}

	public void setKilometros(String kilometros) {
		this.kilometros = kilometros;
	}

	public String getHectareas() {
		return hectareas;
	}

	public void setHectareas(String hectareas) {
		this.hectareas = hectareas;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getElementoNombre() {
		return elementoNombre;
	}

	public void setElementoNombre(String elementoNombre) {
		this.elementoNombre = elementoNombre;
	}

	public String getElementoTipo() {
		return elementoTipo;
	}

	public void setElementoTipo(String elementoTipo) {
		this.elementoTipo = elementoTipo;
	}

	public String getElementoUnidadMedida() {
		return elementoUnidadMedida;
	}

	public void setElementoUnidadMedida(String elementoUnidadMedida) {
		this.elementoUnidadMedida = elementoUnidadMedida;
	}

	public String getElementoValor() {
		return elementoValor;
	}

	public void setElementoValor(String elementoValor) {
		this.elementoValor = elementoValor;
	}

	public String getDistritoId() {
		return distritoId;
	}

	public void setDistritoId(String distritoId) {
		this.distritoId = distritoId;
	}

	public String getDistritoNombre() {
		return distritoNombre;
	}

	public void setDistritoNombre(String distritoNombre) {
		this.distritoNombre = distritoNombre;
	}

	public String getDistritoDescripcion() {
		return distritoDescripcion;
	}

	public void setDistritoDescripcion(String distritoDescripcion) {
		this.distritoDescripcion = distritoDescripcion;
	}

	public String getDistritoHectareas() {
		return distritoHectareas;
	}

	public void setDistritoHectareas(String distritoHectareas) {
		this.distritoHectareas = distritoHectareas;
	}

	public String getDistritoKilometros() {
		return distritoKilometros;
	}

	public void setDistritoKilometros(String distritoKilometros) {
		this.distritoKilometros = distritoKilometros;
	}

	public String getDistritoObservacion() {
		return distritoObservacion;
	}

	public void setDistritoObservacion(String distritoObservacion) {
		this.distritoObservacion = distritoObservacion;
	}

	public String getVecindarioId() {
		return vecindarioId;
	}

	public void setVecindarioId(String vecindarioId) {
		this.vecindarioId = vecindarioId;
	}

	public String getVecindarioNombre() {
		return vecindarioNombre;
	}

	public void setVecindarioNombre(String vecindarioNombre) {
		this.vecindarioNombre = vecindarioNombre;
	}

	public String getVecindarioDescripcion() {
		return vecindarioDescripcion;
	}

	public void setVecindarioDescripcion(String vecindarioDescripcion) {
		this.vecindarioDescripcion = vecindarioDescripcion;
	}

	public String getVecindarioHectareas() {
		return vecindarioHectareas;
	}

	public void setVecindarioHectareas(String vecindarioHectareas) {
		this.vecindarioHectareas = vecindarioHectareas;
	}

	public String getVecindarioKilometros() {
		return vecindarioKilometros;
	}

	public void setVecindarioKilometros(String vecindarioKilometros) {
		this.vecindarioKilometros = vecindarioKilometros;
	}

	public String getVecindarioObservacion() {
		return vecindarioObservacion;
	}

	public void setVecindarioObservacion(String vecindarioObservacion) {
		this.vecindarioObservacion = vecindarioObservacion;
	}
	
	

	public String getTipoEstructura() {
		return tipoEstructura;
	}

	public void setTipoEstructura(String tipoEstructura) {
		this.tipoEstructura = tipoEstructura;
	}
	
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getName());
		sb.append(": ");
		for (Field f : getClass().getDeclaredFields()) {
			sb.append(f.getName());
			sb.append("=");
			try {
				sb.append(f.get(this));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sb.append(", ");
		}
		return sb.toString();
	}

}
