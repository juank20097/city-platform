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
	public String perFechaNacimiento;
	public String perGenero;
	public String perCorreo2;
	public String pdeDireccion;
	public String perCelular;
	public String perTelefono;
	public String pdeEmergContactoNombres;
	public String pdeEmergContactoTelefono;
	public String perEstadoCivil;
	public String sldCarnetConadies;


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
