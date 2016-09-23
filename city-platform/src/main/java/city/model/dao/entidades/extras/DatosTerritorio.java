package city.model.dao.entidades.extras;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DatosTerritorio {

	
	public String zonNombre;
	public String zonDescripcion;
	public String zonKilometros;
	public String zonObservacion;
	public String elzNombre;
	public String elzTipo;
	public String elzUnidadMedida;
	public String ezvValor;
	public String disNombre;
	public String disDescripcion;
	public String disKilometros;
	public String disObservacion;
	public String barNombre;
	public String barDescripcion;
	public String barKilometros;
	public String barHectareas;
	public String barObservacion;
	public String elbNombre;
	public String elbTipo;
	public String elbUnidadMedida;
	public String ebvValor;


	
	public String getZonNombre() {
		return zonNombre;
	}



	public void setZonNombre(String zonNombre) {
		this.zonNombre = zonNombre;
	}



	public String getZonDescripcion() {
		return zonDescripcion;
	}



	public void setZonDescripcion(String zonDescripcion) {
		this.zonDescripcion = zonDescripcion;
	}



	public String getZonKilometros() {
		return zonKilometros;
	}



	public void setZonKilometros(String zonKilometros) {
		this.zonKilometros = zonKilometros;
	}



	public String getZonObservacion() {
		return zonObservacion;
	}



	public void setZonObservacion(String zonObservacion) {
		this.zonObservacion = zonObservacion;
	}



	public String getElzNombre() {
		return elzNombre;
	}



	public void setElzNombre(String elzNombre) {
		this.elzNombre = elzNombre;
	}



	public String getElzTipo() {
		return elzTipo;
	}



	public void setElzTipo(String elzTipo) {
		this.elzTipo = elzTipo;
	}



	public String getElzUnidadMedida() {
		return elzUnidadMedida;
	}



	public void setElzUnidadMedida(String elzUnidadMedida) {
		this.elzUnidadMedida = elzUnidadMedida;
	}



	public String getEzvValor() {
		return ezvValor;
	}



	public void setEzvValor(String ezvValor) {
		this.ezvValor = ezvValor;
	}



	public String getDisNombre() {
		return disNombre;
	}



	public void setDisNombre(String disNombre) {
		this.disNombre = disNombre;
	}



	public String getDisDescripcion() {
		return disDescripcion;
	}



	public void setDisDescripcion(String disDescripcion) {
		this.disDescripcion = disDescripcion;
	}



	public String getDisKilometros() {
		return disKilometros;
	}



	public void setDisKilometros(String disKilometros) {
		this.disKilometros = disKilometros;
	}



	public String getDisObservacion() {
		return disObservacion;
	}



	public void setDisObservacion(String disObservacion) {
		this.disObservacion = disObservacion;
	}



	public String getBarNombre() {
		return barNombre;
	}



	public void setBarNombre(String barNombre) {
		this.barNombre = barNombre;
	}



	public String getBarDescripcion() {
		return barDescripcion;
	}



	public void setBarDescripcion(String barDescripcion) {
		this.barDescripcion = barDescripcion;
	}



	public String getBarKilometros() {
		return barKilometros;
	}



	public void setBarKilometros(String barKilometros) {
		this.barKilometros = barKilometros;
	}



	public String getBarHectareas() {
		return barHectareas;
	}



	public void setBarHectareas(String barHectareas) {
		this.barHectareas = barHectareas;
	}



	public String getBarObservacion() {
		return barObservacion;
	}



	public void setBarObservacion(String barObservacion) {
		this.barObservacion = barObservacion;
	}



	public String getElbNombre() {
		return elbNombre;
	}



	public void setElbNombre(String elbNombre) {
		this.elbNombre = elbNombre;
	}



	public String getElbTipo() {
		return elbTipo;
	}



	public void setElbTipo(String elbTipo) {
		this.elbTipo = elbTipo;
	}



	public String getElbUnidadMedida() {
		return elbUnidadMedida;
	}



	public void setElbUnidadMedida(String elbUnidadMedida) {
		this.elbUnidadMedida = elbUnidadMedida;
	}



	public String getEbvValor() {
		return ebvValor;
	}



	public void setEbvValor(String ebvValor) {
		this.ebvValor = ebvValor;
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
