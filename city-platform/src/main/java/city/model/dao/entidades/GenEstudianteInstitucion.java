package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the gen_estudiante_institucion database table.
 * 
 */
@Entity
@Table(name="gen_estudiante_institucion")
@NamedQuery(name="GenEstudianteInstitucion.findAll", query="SELECT g FROM GenEstudianteInstitucion g")
public class GenEstudianteInstitucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GenEstudianteInstitucionPK id;

	@Column(name="est_area_estudio")
	private String estAreaEstudio;

	@Column(name="est_carrera")
	private String estCarrera;

	@Column(name="est_correo")
	private String estCorreo;

	@Column(name="est_estado")
	private String estEstado;

	@Column(name="est_modalidad")
	private String estModalidad;

	@Column(name="est_nivel")
	private String estNivel;

	//bi-directional many-to-one association to GenInstitucione
	@ManyToOne
	@JoinColumn(name="ins_codigo")
	private GenInstitucione genInstitucione;

	//bi-directional many-to-one association to GenPersona
	@ManyToOne
	@JoinColumn(name="per_dni")
	private GenPersona genPersona;

	public GenEstudianteInstitucion() {
	}

	public GenEstudianteInstitucionPK getId() {
		return this.id;
	}

	public void setId(GenEstudianteInstitucionPK id) {
		this.id = id;
	}

	public String getEstAreaEstudio() {
		return this.estAreaEstudio;
	}

	public void setEstAreaEstudio(String estAreaEstudio) {
		this.estAreaEstudio = estAreaEstudio;
	}

	public String getEstCarrera() {
		return this.estCarrera;
	}

	public void setEstCarrera(String estCarrera) {
		this.estCarrera = estCarrera;
	}

	public String getEstCorreo() {
		return this.estCorreo;
	}

	public void setEstCorreo(String estCorreo) {
		this.estCorreo = estCorreo;
	}

	public String getEstEstado() {
		return this.estEstado;
	}

	public void setEstEstado(String estEstado) {
		this.estEstado = estEstado;
	}

	public String getEstModalidad() {
		return this.estModalidad;
	}

	public void setEstModalidad(String estModalidad) {
		this.estModalidad = estModalidad;
	}

	public String getEstNivel() {
		return this.estNivel;
	}

	public void setEstNivel(String estNivel) {
		this.estNivel = estNivel;
	}

	public GenInstitucione getGenInstitucione() {
		return this.genInstitucione;
	}

	public void setGenInstitucione(GenInstitucione genInstitucione) {
		this.genInstitucione = genInstitucione;
	}

	public GenPersona getGenPersona() {
		return this.genPersona;
	}

	public void setGenPersona(GenPersona genPersona) {
		this.genPersona = genPersona;
	}

}