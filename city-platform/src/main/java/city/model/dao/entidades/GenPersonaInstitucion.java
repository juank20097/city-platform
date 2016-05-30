package city.model.dao.entidades;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the gen_persona_institucion database table.
 * 
 */
@Entity
@Table(name="gen_persona_institucion")
@NamedQuery(name="GenPersonaInstitucion.findAll", query="SELECT g FROM GenPersonaInstitucion g")
public class GenPersonaInstitucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GenPersonaInstitucionPK id;

	@Column(name="pei_estado" , columnDefinition="bpchar")
	private String peiEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="pei_fecha_registro")
	private Date peiFechaRegistro;

	@Column(name="pei_rol",insertable = false, updatable = false)
	private String peiRol;

	//bi-directional many-to-one association to GenInstitucione
	@ManyToOne
	@JoinColumn(name="ins_codigo",insertable = false, updatable = false)
	private GenInstitucione genInstitucione;
	
	//bi-directional one-to-one association to GenPersona
	@ManyToOne
	@JoinColumn(name="per_dni",insertable = false, updatable = false)
	private GenPersona genPersona;

	public GenPersonaInstitucion() {
	}

	public String getPeiEstado() {
		return this.peiEstado;
	}

	public GenPersonaInstitucionPK getId() {
		return this.id;
	}

	public void setId(GenPersonaInstitucionPK id) {
		this.id = id;
	}
	
	public void setPeiEstado(String peiEstado) {
		this.peiEstado = peiEstado;
	}

	public Date getPeiFechaRegistro() {
		return this.peiFechaRegistro;
	}

	public void setPeiFechaRegistro(Date peiFechaRegistro) {
		this.peiFechaRegistro = peiFechaRegistro;
	}

	public String getPeiRol() {
		return this.peiRol;
	}

	public void setPeiRol(String peiRol) {
		this.peiRol = peiRol;
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