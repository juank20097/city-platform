package city.model.dao.entidades.extras;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DatosFuncionario {

	public String perNombres;
	public String perApellidos;
	public String perDni;
	public String funCargo;
	public String funGerencia;
	public String funDireccion;
	public String funFechaIngreso;
	public String perCorreo;
	public String tieneTercerNivel;
	public String titulosTercerNivel;
	public String tieneTitulosCuartoNivel;
	public String titulosCuartoNivel;
	public String perFechaNacimiento;
	public String edadCompleta;
	public String edad;
	public String perGenero;
	public String perCorreo2;
	public String pdeDireccion;
	public String perCelular;
	public String perTelefono;
	public String pdeEmergContactoNombres;
	public String pdeEmergContactoTelefono;
	public String perEstadoCivil;
	public String sldCarnetConadies;

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
	
	public String getTieneTercerNivel() {
		return tieneTercerNivel;
	}

	public void setTieneTercerNivel(String tieneTercerNivel) {
		this.tieneTercerNivel = tieneTercerNivel;
	}

	public String getTitulosTercerNivel() {
		return titulosTercerNivel;
	}

	public void setTitulosTercerNivel(String titulosTercerNivel) {
		this.titulosTercerNivel = titulosTercerNivel;
	}

	public String getTieneTitulosCuartoNivel() {
		return tieneTitulosCuartoNivel;
	}

	public void setTieneTitulosCuartoNivel(String tieneTitulosCuartoNivel) {
		this.tieneTitulosCuartoNivel = tieneTitulosCuartoNivel;
	}

	public String getTitulosCuartoNivel() {
		return titulosCuartoNivel;
	}

	public void setTitulosCuartoNivel(String titulosCuartoNivel) {
		this.titulosCuartoNivel = titulosCuartoNivel;
	}

	public String getEdadCompleta() {
		return edadCompleta;
	}

	public void setEdadCompleta(String edadCompleta) {
		this.edadCompleta = edadCompleta;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
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
