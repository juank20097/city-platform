package city.model.dao.entidades.extras;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DatosAsignacionSuelo {

	public String zonNombre;
	public String sueActividad;
	public String sueAsignacion;
	public String sueMetros;
	public String sueFechaInicio;
	public String sueFechaFin;
	public String sueObservacion;
	public String sueArchivo;
	public String sueTipo;

	public String getZonNombre() {
		return zonNombre;
	}

	public void setZonNombre(String zonNombre) {
		this.zonNombre = zonNombre;
	}

	public String getSueActividad() {
		return sueActividad;
	}

	public void setSueActividad(String sueActividad) {
		this.sueActividad = sueActividad;
	}

	public String getSueAsignacion() {
		return sueAsignacion;
	}

	public void setSueAsignacion(String sueAsignacion) {
		this.sueAsignacion = sueAsignacion;
	}

	public String getSueMetros() {
		return sueMetros;
	}

	public void setSueMetros(String sueMetros) {
		this.sueMetros = sueMetros;
	}

	public String getSueFechaInicio() {
		return sueFechaInicio;
	}

	public void setSueFechaInicio(String sueFechaInicio) {
		this.sueFechaInicio = sueFechaInicio;
	}

	public String getSueFechaFin() {
		return sueFechaFin;
	}

	public void setSueFechaFin(String sueFechaFin) {
		this.sueFechaFin = sueFechaFin;
	}

	public String getSueObservacion() {
		return sueObservacion;
	}

	public void setSueObservacion(String sueObservacion) {
		this.sueObservacion = sueObservacion;
	}

	public String getSueArchivo() {
		return sueArchivo;
	}

	public void setSueArchivo(String sueArchivo) {
		this.sueArchivo = sueArchivo;
	}

	public String getSueTipo() {
		return sueTipo;
	}

	public void setSueTipo(String sueTipo) {
		this.sueTipo = sueTipo;
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
