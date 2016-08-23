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

	//bi-directional many-to-one association to GenCapacitacione
	@OneToMany(mappedBy="genPersona")
	private List<GenCapacitacione> genCapacitaciones;

	//bi-directional many-to-one association to GenExperiencialaboral
	@OneToMany(mappedBy="genPersona")
	private List<GenExperiencialaboral> genExperiencialaborals;

	//bi-directional many-to-one association to GenFormacionacademica
	@OneToMany(mappedBy="genPersona")
	private List<GenFormacionacademica> genFormacionacademicas;

	//bi-directional many-to-one association to GenVisitante
	@OneToMany(mappedBy="genPersona")
	private List<GenVisitante> genVisitantes;
	
	// bi-directional many-to-one association to GenFuncionariosInstitucion
	@OneToMany(mappedBy = "genPersona", fetch = FetchType.EAGER)
	private List<GenFuncionariosInstitucion> genFuncionariosInstitucions;

	// bi-directional one-to-one association to GenPersonaDetalle
	@OneToOne(mappedBy = "genPersona")
	private GenPersonaDetalle genPersonaDetalle;

	// bi-directional one-to-one association to GenSalud
	@OneToOne(mappedBy = "genPersona")
	private GenSalud genSalud;

	// bi-directional many-to-one association to GenEstudianteInstitucion
	@OneToMany(mappedBy = "genPersona", fetch = FetchType.EAGER)
	private List<GenEstudianteInstitucion> genEstudianteInstitucions;

	// bi-directional one-to-one association to GenExterno
	@OneToOne(mappedBy = "genPersona")
	private GenExterno genExterno;

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

	public List<GenCapacitacione> getGenCapacitaciones() {
		return this.genCapacitaciones;
	}

	public void setGenCapacitaciones(List<GenCapacitacione> genCapacitaciones) {
		this.genCapacitaciones = genCapacitaciones;
	}

	public GenCapacitacione addGenCapacitacione(GenCapacitacione genCapacitacione) {
		getGenCapacitaciones().add(genCapacitacione);
		genCapacitacione.setGenPersona(this);

		return genCapacitacione;
	}

	public GenCapacitacione removeGenCapacitacione(GenCapacitacione genCapacitacione) {
		getGenCapacitaciones().remove(genCapacitacione);
		genCapacitacione.setGenPersona(null);

		return genCapacitacione;
	}

	public List<GenExperiencialaboral> getGenExperiencialaborals() {
		return this.genExperiencialaborals;
	}

	public void setGenExperiencialaborals(List<GenExperiencialaboral> genExperiencialaborals) {
		this.genExperiencialaborals = genExperiencialaborals;
	}

	public GenExperiencialaboral addGenExperiencialaboral(GenExperiencialaboral genExperiencialaboral) {
		getGenExperiencialaborals().add(genExperiencialaboral);
		genExperiencialaboral.setGenPersona(this);

		return genExperiencialaboral;
	}

	public GenExperiencialaboral removeGenExperiencialaboral(GenExperiencialaboral genExperiencialaboral) {
		getGenExperiencialaborals().remove(genExperiencialaboral);
		genExperiencialaboral.setGenPersona(null);

		return genExperiencialaboral;
	}

	public List<GenFormacionacademica> getGenFormacionacademicas() {
		return this.genFormacionacademicas;
	}

	public void setGenFormacionacademicas(List<GenFormacionacademica> genFormacionacademicas) {
		this.genFormacionacademicas = genFormacionacademicas;
	}

	public GenFormacionacademica addGenFormacionacademica(GenFormacionacademica genFormacionacademica) {
		getGenFormacionacademicas().add(genFormacionacademica);
		genFormacionacademica.setGenPersona(this);

		return genFormacionacademica;
	}

	public GenFormacionacademica removeGenFormacionacademica(GenFormacionacademica genFormacionacademica) {
		getGenFormacionacademicas().remove(genFormacionacademica);
		genFormacionacademica.setGenPersona(null);

		return genFormacionacademica;
	}

	public List<GenVisitante> getGenVisitantes() {
		return this.genVisitantes;
	}

	public void setGenVisitantes(List<GenVisitante> genVisitantes) {
		this.genVisitantes = genVisitantes;
	}

	public GenVisitante addGenVisitante(GenVisitante genVisitante) {
		getGenVisitantes().add(genVisitante);
		genVisitante.setGenPersona(this);

		return genVisitante;
	}

	public GenVisitante removeGenVisitante(GenVisitante genVisitante) {
		getGenVisitantes().remove(genVisitante);
		genVisitante.setGenPersona(null);

		return genVisitante;
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

	public List<GenFuncionariosInstitucion> getGenFuncionariosInstitucions() {
		return this.genFuncionariosInstitucions;
	}

	public void setGenFuncionariosInstitucions(
			List<GenFuncionariosInstitucion> genFuncionariosInstitucions) {
		this.genFuncionariosInstitucions = genFuncionariosInstitucions;
	}

	public GenFuncionariosInstitucion addGenFuncionariosInstitucion(
			GenFuncionariosInstitucion genFuncionariosInstitucion) {
		getGenFuncionariosInstitucions().add(genFuncionariosInstitucion);
		genFuncionariosInstitucion.setGenPersona(this);

		return genFuncionariosInstitucion;
	}

	public GenFuncionariosInstitucion removeGenFuncionariosInstitucion(
			GenFuncionariosInstitucion genFuncionariosInstitucion) {
		getGenFuncionariosInstitucions().remove(genFuncionariosInstitucion);
		genFuncionariosInstitucion.setGenPersona(null);

		return genFuncionariosInstitucion;
	}

	public List<GenEstudianteInstitucion> getGenEstudianteInstitucions() {
		return this.genEstudianteInstitucions;
	}

	public void setGenEstudianteInstitucions(
			List<GenEstudianteInstitucion> genEstudianteInstitucions) {
		this.genEstudianteInstitucions = genEstudianteInstitucions;
	}

	public GenEstudianteInstitucion addGenEstudianteInstitucion(
			GenEstudianteInstitucion genEstudianteInstitucion) {
		getGenEstudianteInstitucions().add(genEstudianteInstitucion);
		genEstudianteInstitucion.setGenPersona(this);

		return genEstudianteInstitucion;
	}

	public GenEstudianteInstitucion removeGenEstudianteInstitucion(
			GenEstudianteInstitucion genEstudianteInstitucion) {
		getGenEstudianteInstitucions().remove(genEstudianteInstitucion);
		genEstudianteInstitucion.setGenPersona(null);

		return genEstudianteInstitucion;
	}

	public GenExterno getGenExterno() {
		return this.genExterno;
	}

	public void setGenExterno(GenExterno genExterno) {
		this.genExterno = genExterno;
	}

}