package city.model.dao.entidades.extras;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jestevez
 * 
 */
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	// atributos persona
	private String perDni;

	private String ins_codigo;

	private String perApellidos;

	private String perCelular;

	private String perCorreo;

	private String perEstado;

	private String perEstadoCivil;

	private Date perFechaNacimiento;

	private String perGenero;

	private String perNombres;

	private String perTelefono;

	private String perTipoDni;

	// atributos funcionario
	private String funCargo;

	private String funDireccion;

	private Date funFechaIngreso;

	private String funJefeInmediato;

	private String funTipoEvaluacion;

	private String funGerencia;

	private String funTipo;

	private String funEstado;

	public Funcionario() {
	}

	/**
	 * @return the perDni
	 */
	public String getPerDni() {
		return perDni;
	}

	/**
	 * @param perDni
	 *            the perDni to set
	 */
	public void setPerDni(String perDni) {
		this.perDni = perDni;
	}

	/**
	 * @return the funJefeInmediato
	 */
	public String getFunJefeInmediato() {
		return funJefeInmediato;
	}

	/**
	 * @param funJefeInmediato
	 *            the funJefeInmediato to set
	 */
	public void setFunJefeInmediato(String funJefeInmediato) {
		this.funJefeInmediato = funJefeInmediato;
	}

	/**
	 * @return the funTipoEvaluacion
	 */
	public String getFunTipoEvaluacion() {
		return funTipoEvaluacion;
	}

	/**
	 * @param funTipoEvaluacion
	 *            the funTipoEvaluacion to set
	 */
	public void setFunTipoEvaluacion(String funTipoEvaluacion) {
		this.funTipoEvaluacion = funTipoEvaluacion;
	}

	/**
	 * @return the ins_codigo
	 */
	public String getIns_codigo() {
		return ins_codigo;
	}

	/**
	 * @param ins_codigo
	 *            the ins_codigo to set
	 */
	public void setIns_codigo(String ins_codigo) {
		this.ins_codigo = ins_codigo;
	}

	/**
	 * @return the perApellidos
	 */
	public String getPerApellidos() {
		return perApellidos;
	}

	/**
	 * @param perApellidos
	 *            the perApellidos to set
	 */
	public void setPerApellidos(String perApellidos) {
		this.perApellidos = perApellidos;
	}

	/**
	 * @return the perCelular
	 */
	public String getPerCelular() {
		return perCelular;
	}

	/**
	 * @param perCelular
	 *            the perCelular to set
	 */
	public void setPerCelular(String perCelular) {
		this.perCelular = perCelular;
	}

	/**
	 * @return the perCorreo
	 */
	public String getPerCorreo() {
		return perCorreo;
	}

	/**
	 * @param perCorreo
	 *            the perCorreo to set
	 */
	public void setPerCorreo(String perCorreo) {
		this.perCorreo = perCorreo;
	}

	/**
	 * @return the perEstado
	 */
	public String getPerEstado() {
		return perEstado;
	}

	/**
	 * @param perEstado
	 *            the perEstado to set
	 */
	public void setPerEstado(String perEstado) {
		this.perEstado = perEstado;
	}

	/**
	 * @return the perEstadoCivil
	 */
	public String getPerEstadoCivil() {
		return perEstadoCivil;
	}

	/**
	 * @param perEstadoCivil
	 *            the perEstadoCivil to set
	 */
	public void setPerEstadoCivil(String perEstadoCivil) {
		this.perEstadoCivil = perEstadoCivil;
	}

	/**
	 * @return the perFechaNacimiento
	 */
	public Date getPerFechaNacimiento() {
		return perFechaNacimiento;
	}

	/**
	 * @param perFechaNacimiento
	 *            the perFechaNacimiento to set
	 */
	public void setPerFechaNacimiento(Date perFechaNacimiento) {
		this.perFechaNacimiento = perFechaNacimiento;
	}

	/**
	 * @return the perGenero
	 */
	public String getPerGenero() {
		return perGenero;
	}

	/**
	 * @param perGenero
	 *            the perGenero to set
	 */
	public void setPerGenero(String perGenero) {
		this.perGenero = perGenero;
	}

	/**
	 * @return the perNombres
	 */
	public String getPerNombres() {
		return perNombres;
	}

	/**
	 * @param perNombres
	 *            the perNombres to set
	 */
	public void setPerNombres(String perNombres) {
		this.perNombres = perNombres;
	}

	/**
	 * @return the perTelefono
	 */
	public String getPerTelefono() {
		return perTelefono;
	}

	/**
	 * @param perTelefono
	 *            the perTelefono to set
	 */
	public void setPerTelefono(String perTelefono) {
		this.perTelefono = perTelefono;
	}

	/**
	 * @return the perTipoDni
	 */
	public String getPerTipoDni() {
		return perTipoDni;
	}

	/**
	 * @param perTipoDni
	 *            the perTipoDni to set
	 */
	public void setPerTipoDni(String perTipoDni) {
		this.perTipoDni = perTipoDni;
	}

	/**
	 * @return the funCargo
	 */
	public String getFunCargo() {
		return funCargo;
	}

	/**
	 * @param funCargo
	 *            the funCargo to set
	 */
	public void setFunCargo(String funCargo) {
		this.funCargo = funCargo;
	}

	/**
	 * @return the funDireccion
	 */
	public String getFunDireccion() {
		return funDireccion;
	}

	/**
	 * @param funDireccion
	 *            the funDireccion to set
	 */
	public void setFunDireccion(String funDireccion) {
		this.funDireccion = funDireccion;
	}

	/**
	 * @return the funFechaIngreso
	 */
	public Date getFunFechaIngreso() {
		return funFechaIngreso;
	}

	/**
	 * @param funFechaIngreso
	 *            the funFechaIngreso to set
	 */
	public void setFunFechaIngreso(Date funFechaIngreso) {
		this.funFechaIngreso = funFechaIngreso;
	}

	/**
	 * @return the funGerencia
	 */
	public String getFunGerencia() {
		return funGerencia;
	}

	/**
	 * @param funGerencia
	 *            the funGerencia to set
	 */
	public void setFunGerencia(String funGerencia) {
		this.funGerencia = funGerencia;
	}

	/**
	 * @return the funTipo
	 */
	public String getFunTipo() {
		return funTipo;
	}

	/**
	 * @param funTipo
	 *            the funTipo to set
	 */
	public void setFunTipo(String funTipo) {
		this.funTipo = funTipo;
	}

	/**
	 * @return the funEstado
	 */
	public String getFunEstado() {
		return funEstado;
	}

	/**
	 * @param funEstado
	 *            the funEstado to set
	 */
	public void setFunEstado(String funEstado) {
		this.funEstado = funEstado;
	}

}