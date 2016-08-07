package city.model.dao.entidades.extras;

import java.io.Serializable;

import java.util.Date;

/**
 * @author jestevez
 * 
 */
public class Estudiante implements Serializable {
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

	// atributos estudiante
	private String estAreaEstudio;

	private String estCarrera;

	private String estCorreo;

	private String estEstado;

	private String estModalidad;

	private String estNivel;

	public Estudiante() {
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
	 * @return the estAreaEstudio
	 */
	public String getEstAreaEstudio() {
		return estAreaEstudio;
	}

	/**
	 * @param estAreaEstudio
	 *            the estAreaEstudio to set
	 */
	public void setEstAreaEstudio(String estAreaEstudio) {
		this.estAreaEstudio = estAreaEstudio;
	}

	/**
	 * @return the estCarrera
	 */
	public String getEstCarrera() {
		return estCarrera;
	}

	/**
	 * @param estCarrera
	 *            the estCarrera to set
	 */
	public void setEstCarrera(String estCarrera) {
		this.estCarrera = estCarrera;
	}

	/**
	 * @return the estCorreo
	 */
	public String getEstCorreo() {
		return estCorreo;
	}

	/**
	 * @param estCorreo
	 *            the estCorreo to set
	 */
	public void setEstCorreo(String estCorreo) {
		this.estCorreo = estCorreo;
	}

	/**
	 * @return the estEstado
	 */
	public String getEstEstado() {
		return estEstado;
	}

	/**
	 * @param estEstado
	 *            the estEstado to set
	 */
	public void setEstEstado(String estEstado) {
		this.estEstado = estEstado;
	}

	/**
	 * @return the estModalidad
	 */
	public String getEstModalidad() {
		return estModalidad;
	}

	/**
	 * @param estModalidad
	 *            the estModalidad to set
	 */
	public void setEstModalidad(String estModalidad) {
		this.estModalidad = estModalidad;
	}

	/**
	 * @return the estNivel
	 */
	public String getEstNivel() {
		return estNivel;
	}

	/**
	 * @param estNivel
	 *            the estNivel to set
	 */
	public void setEstNivel(String estNivel) {
		this.estNivel = estNivel;
	}

}