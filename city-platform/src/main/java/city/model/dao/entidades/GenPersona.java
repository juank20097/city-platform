package city.model.dao.entidades;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the gen_persona database table.
 * 
 */
@Entity
@Table(name = "gen_persona")
@NamedQuery(name = "GenPersona.findAll", query = "SELECT g FROM GenPersona g")
public class GenPersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "per_dni")
	private String perDni;

	@Column(name = "per_apellidos")
	private String perApellidos;

	@Column(name = "per_celular")
	private String perCelular;

	@Column(name = "per_correo")
	private String perCorreo;

	@Column(name = "per_estado", columnDefinition = "bpchar")
	private String perEstado;

	@Column(name = "per_estado_civil")
	private String perEstadoCivil;

	@Temporal(TemporalType.DATE)
	@Column(name = "per_fecha_nacimiento")
	private Date perFechaNacimiento;

	@Column(name = "per_genero")
	private String perGenero;

	@Column(name = "per_nombres")
	private String perNombres;

	@Column(name = "per_telefono")
	private String perTelefono;

	@Column(name = "per_tipo_dni")
	private String perTipoDni;

	// bi-directional one-to-one association to GenPersonaDetalle
	@OneToOne(mappedBy = "genPersona")
	private GenPersonaDetalle genPersonaDetalle;

	// bi-directional one-to-one association to GenSalud
	@OneToOne(mappedBy = "genPersona")
	private GenSalud genSalud;

	// bi-directional one-to-one association to GenPersonaInstitucion
	@OneToMany(mappedBy = "genPersona")
	private List<GenPersonaInstitucion> genPersonaInstitucions;

	public GenPersona() {
	}

	public String getPerDni() {
		return this.perDni;
	}

	public void setPerDni(String perDni) {
		this.perDni = perDni;
	}

	public String getPerApellidos() {
		return this.perApellidos;
	}

	public void setPerApellidos(String perApellidos) {
		this.perApellidos = perApellidos;
	}

	public String getPerCelular() {
		return this.perCelular;
	}

	public void setPerCelular(String perCelular) {
		this.perCelular = perCelular;
	}

	public String getPerCorreo() {
		return this.perCorreo;
	}

	public void setPerCorreo(String perCorreo) {
		this.perCorreo = perCorreo;
	}

	public String getPerEstado() {
		return this.perEstado;
	}

	public void setPerEstado(String perEstado) {
		this.perEstado = perEstado;
	}

	public String getPerEstadoCivil() {
		return this.perEstadoCivil;
	}

	public void setPerEstadoCivil(String perEstadoCivil) {
		this.perEstadoCivil = perEstadoCivil;
	}

	public Date getPerFechaNacimiento() {
		return this.perFechaNacimiento;
	}

	public void setPerFechaNacimiento(Date perFechaNacimiento) {
		this.perFechaNacimiento = perFechaNacimiento;
	}

	public String getPerGenero() {
		return this.perGenero;
	}

	public void setPerGenero(String perGenero) {
		this.perGenero = perGenero;
	}

	public String getPerNombres() {
		return this.perNombres;
	}

	public void setPerNombres(String perNombres) {
		this.perNombres = perNombres;
	}

	public String getPerTelefono() {
		return this.perTelefono;
	}

	public void setPerTelefono(String perTelefono) {
		this.perTelefono = perTelefono;
	}

	public String getPerTipoDni() {
		return this.perTipoDni;
	}

	public void setPerTipoDni(String perTipoDni) {
		this.perTipoDni = perTipoDni;
	}

	public GenPersonaDetalle getGenPersonaDetalle() {
		return this.genPersonaDetalle;
	}

	public void setGenPersonaDetalle(GenPersonaDetalle genPersonaDetalle) {
		this.genPersonaDetalle = genPersonaDetalle;
	}

	public GenSalud getGenSalud() {
		return this.genSalud;
	}

	public void setGenSalud(GenSalud genSalud) {
		this.genSalud = genSalud;
	}

	/**
	 * @return the genPersonaInstitucions
	 */
	public List<GenPersonaInstitucion> getGenPersonaInstitucions() {
		return genPersonaInstitucions;
	}

	/**
	 * @param genPersonaInstitucions the genPersonaInstitucions to set
	 */
	public void setGenPersonaInstitucions(
			List<GenPersonaInstitucion> genPersonaInstitucions) {
		this.genPersonaInstitucions = genPersonaInstitucions;
	}
	
	

}