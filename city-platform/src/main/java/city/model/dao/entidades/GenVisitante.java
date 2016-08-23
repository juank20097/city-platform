package city.model.dao.entidades;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the gen_visitante database table.
 * 
 */
@Entity
@Table(name="gen_visitante")
@NamedQuery(name="GenVisitante.findAll", query="SELECT g FROM GenVisitante g")
public class GenVisitante implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GenVisitantePK id;

	@Column(name="vis_estado", columnDefinition="bpchar",length=1)
	private String visEstado;

	@Column(name="vis_fecha_actividad")
	private Timestamp visFechaActividad;

	//bi-directional many-to-one association to GenPersona
	@ManyToOne
	@JoinColumn(name="per_dni",insertable=false, updatable=false)
	private GenPersona genPersona;

	public GenVisitante() {
	}

	public GenVisitantePK getId() {
		return this.id;
	}

	public void setId(GenVisitantePK id) {
		this.id = id;
	}

	public String getVisEstado() {
		return this.visEstado;
	}

	public void setVisEstado(String visEstado) {
		this.visEstado = visEstado;
	}

	public Timestamp getVisFechaActividad() {
		return this.visFechaActividad;
	}

	public void setVisFechaActividad(Timestamp visFechaActividad) {
		this.visFechaActividad = visFechaActividad;
	}

	public GenPersona getGenPersona() {
		return this.genPersona;
	}

	public void setGenPersona(GenPersona genPersona) {
		this.genPersona = genPersona;
	}

}