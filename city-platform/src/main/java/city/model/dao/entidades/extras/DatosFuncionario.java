package city.model.dao.entidades.extras;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DatosFuncionario extends Object implements Serializable {
	private static final long serialVersionUID = 1L;

	private String perNombres;
	private String perApellidos;
	public String perDni;
	private String funCargo;
	private String funGerencia;
	private String funDireccion;
	private String funFechaIngreso;
	private String perCorreo;
	private String perFechaNacimiento;
	private String perGenero;
	private String perCorreo2;
	private String pdeDireccion;
	private String perCelular;
	private String perTelefono;
	private String pdeEmergContactoNombres;
	private String pdeEmergContactoTelefono;
	private String perEstadoCivil;
	private String sldCarnetConadies;

	public String getPerNombres() {
		return perNombres;
	}

	public void setPerNombres(String perNombres) {
		this.perNombres = perNombres;
	}

	public String getPerApellidos() {
		return perApellidos;
	}

	public void setPerApellidos(String perApellidos) {
		this.perApellidos = perApellidos;
	}

	public String getPerDni() {
		return perDni;
	}

	public void setPerDni(String perDni) {
		this.perDni = perDni;
	}

	public String getFunCargo() {
		return funCargo;
	}

	public void setFunCargo(String funCargo) {
		this.funCargo = funCargo;
	}

	public String getFunGerencia() {
		return funGerencia;
	}

	public void setFunGerencia(String funGerencia) {
		this.funGerencia = funGerencia;
	}

	public String getFunDireccion() {
		return funDireccion;
	}

	public void setFunDireccion(String funDireccion) {
		this.funDireccion = funDireccion;
	}

	public String getFunFechaIngreso() {
		return funFechaIngreso;
	}

	public void setFunFechaIngreso(String funFechaIngreso) {
		this.funFechaIngreso = funFechaIngreso;
	}

	public String getPerCorreo() {
		return perCorreo;
	}

	public void setPerCorreo(String perCorreo) {
		this.perCorreo = perCorreo;
	}

	public String getPerFechaNacimiento() {
		return perFechaNacimiento;
	}

	public void setPerFechaNacimiento(String perFechaNacimiento) {
		this.perFechaNacimiento = perFechaNacimiento;
	}

	public String getPerGenero() {
		return perGenero;
	}

	public void setPerGenero(String perGenero) {
		this.perGenero = perGenero;
	}

	public String getPerCorreo2() {
		return perCorreo2;
	}

	public void setPerCorreo2(String perCorreo2) {
		this.perCorreo2 = perCorreo2;
	}

	public String getPdeDireccion() {
		return pdeDireccion;
	}

	public void setPdeDireccion(String pdeDireccion) {
		this.pdeDireccion = pdeDireccion;
	}

	public String getPerCelular() {
		return perCelular;
	}

	public void setPerCelular(String perCelular) {
		this.perCelular = perCelular;
	}

	public String getPerTelefono() {
		return perTelefono;
	}

	public void setPerTelefono(String perTelefono) {
		this.perTelefono = perTelefono;
	}

	public String getPdeEmergContactoNombres() {
		return pdeEmergContactoNombres;
	}

	public void setPdeEmergContactoNombres(String pdeEmergContactoNombres) {
		this.pdeEmergContactoNombres = pdeEmergContactoNombres;
	}

	public String getPdeEmergContactoTelefono() {
		return pdeEmergContactoTelefono;
	}

	public void setPdeEmergContactoTelefono(String pdeEmergContactoTelefono) {
		this.pdeEmergContactoTelefono = pdeEmergContactoTelefono;
	}

	public String getPerEstadoCivil() {
		return perEstadoCivil;
	}

	public void setPerEstadoCivil(String perEstadoCivil) {
		this.perEstadoCivil = perEstadoCivil;
	}

	public String getSldCarnetConadies() {
		return sldCarnetConadies;
	}

	public void setSldCarnetConadies(String sldCarnetConadies) {
		this.sldCarnetConadies = sldCarnetConadies;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		
		for (Method f : getClass().getDeclaredMethods()) {
			System.out.println(f.getName()+" ");
		}
		
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
