package city.model.dao.entidades;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the gen_instituciones database table.
 * 
 */
@Entity
@Table(name = "gen_instituciones")
@NamedQuery(name = "GenInstitucione.findAll", query = "SELECT g FROM GenInstitucione g")
public class GenInstitucione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ins_codigo")
	private String insCodigo;

	@Column(name = "ins_categoria")
	private String insCategoria;

	@Column(name = "ins_descripcion")
	private String insDescripcion;

	@Column(name = "ins_estado", columnDefinition = "bpchar")
	private String insEstado;

	@Column(name = "ins_nombre")
	private String insNombre;

	@Column(name = "ins_razon_social")
	private String insRazonSocial;

	@Column(name = "ins_ruc")
	private String insRuc;

	// bi-directional many-to-one association to GenFuncionariosInstitucion
	@OneToMany(mappedBy = "genInstitucione")
	private List<GenFuncionariosInstitucion> genFuncionariosInstitucions;

	// bi-directional many-to-one association to GenSitio
	@OneToMany(mappedBy = "genInstitucione")
	private List<GenSitio> genSitios;

	// bi-directional many-to-one association to GenEstudianteInstitucion
	@OneToMany(mappedBy = "genInstitucione")
	private List<GenEstudianteInstitucion> genEstudianteInstitucions;

	public GenInstitucione() {
	}

	public String getInsCodigo() {
		return this.insCodigo;
	}

	public void setInsCodigo(String insCodigo) {
		this.insCodigo = insCodigo;
	}

	public String getInsCategoria() {
		return this.insCategoria;
	}

	public void setInsCategoria(String insCategoria) {
		this.insCategoria = insCategoria;
	}

	public String getInsDescripcion() {
		return this.insDescripcion;
	}

	public void setInsDescripcion(String insDescripcion) {
		this.insDescripcion = insDescripcion;
	}

	public String getInsEstado() {
		return this.insEstado;
	}

	public void setInsEstado(String insEstado) {
		this.insEstado = insEstado;
	}

	public String getInsNombre() {
		return this.insNombre;
	}

	public void setInsNombre(String insNombre) {
		this.insNombre = insNombre;
	}

	public String getInsRazonSocial() {
		return this.insRazonSocial;
	}

	public void setInsRazonSocial(String insRazonSocial) {
		this.insRazonSocial = insRazonSocial;
	}

	public String getInsRuc() {
		return this.insRuc;
	}

	public void setInsRuc(String insRuc) {
		this.insRuc = insRuc;
	}

	public List<GenSitio> getGenSitios() {
		return this.genSitios;
	}

	public void setGenSitios(List<GenSitio> genSitios) {
		this.genSitios = genSitios;
	}

	public GenSitio addGenSitio(GenSitio genSitio) {
		getGenSitios().add(genSitio);
		genSitio.setGenInstitucione(this);

		return genSitio;
	}

	public GenSitio removeGenSitio(GenSitio genSitio) {
		getGenSitios().remove(genSitio);
		genSitio.setGenInstitucione(null);

		return genSitio;
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
		genFuncionariosInstitucion.setGenInstitucione(this);

		return genFuncionariosInstitucion;
	}

	public GenFuncionariosInstitucion removeGenFuncionariosInstitucion(
			GenFuncionariosInstitucion genFuncionariosInstitucion) {
		getGenFuncionariosInstitucions().remove(genFuncionariosInstitucion);
		genFuncionariosInstitucion.setGenInstitucione(null);

		return genFuncionariosInstitucion;
	}
	
	public List<GenEstudianteInstitucion> getGenEstudianteInstitucions() {
		return this.genEstudianteInstitucions;
	}

	public void setGenEstudianteInstitucions(List<GenEstudianteInstitucion> genEstudianteInstitucions) {
		this.genEstudianteInstitucions = genEstudianteInstitucions;
	}

	public GenEstudianteInstitucion addGenEstudianteInstitucion(GenEstudianteInstitucion genEstudianteInstitucion) {
		getGenEstudianteInstitucions().add(genEstudianteInstitucion);
		genEstudianteInstitucion.setGenInstitucione(this);

		return genEstudianteInstitucion;
	}

	public GenEstudianteInstitucion removeGenEstudianteInstitucion(GenEstudianteInstitucion genEstudianteInstitucion) {
		getGenEstudianteInstitucions().remove(genEstudianteInstitucion);
		genEstudianteInstitucion.setGenInstitucione(null);

		return genEstudianteInstitucion;
	}

}