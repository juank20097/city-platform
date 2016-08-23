package city.model.dao.entidades;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the gen_capacitaciones database table.
 * 
 */
@Entity
@Table(name="gen_capacitaciones")
@NamedQuery(name="GenCapacitacione.findAll", query="SELECT g FROM GenCapacitacione g")
public class GenCapacitacione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GEN_CAPACITACIONES_CAPID_GENERATOR", sequenceName="SEQ_CAPACITACIONES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GEN_CAPACITACIONES_CAPID_GENERATOR")
	@Column(name="cap_id")
	private Integer capId;

	@Column(name="cap_area_laboral_estudio", length=100)
	private String capAreaLaboralEstudio;
	
	@Column(name="cap_institucion_capacitacion", length=254)
	private String capInstitucionCapacitacion;

	@Column(name="cap_nombre", length=200)
	private String capNombre;

	@Column(name="cap_num_horas")
	private Integer capNumHoras;
	
	@Column(name="cap_relacion_perfil_profesional")
	private Boolean capRelacionPerfilProfesional;

	@Column(name="cap_tipo_evento", length=100)
	private String capTipoEvento;

	//bi-directional many-to-one association to GenPersona
	@ManyToOne
	@JoinColumn(name="per_dni")
	private GenPersona genPersona;

	public GenCapacitacione() {
	}

	public Integer getCapId() {
		return this.capId;
	}

	public void setCapId(Integer capId) {
		this.capId = capId;
	}

	public String getCapAreaLaboralEstudio() {
		return this.capAreaLaboralEstudio;
	}

	public void setCapAreaLaboralEstudio(String capAreaLaboralEstudio) {
		this.capAreaLaboralEstudio = capAreaLaboralEstudio;
	}

	public String getCapInstitucionCapacitacion() {
		return this.capInstitucionCapacitacion;
	}

	public void setCapInstitucionCapacitacion(String capInstitucionCapacitacion) {
		this.capInstitucionCapacitacion = capInstitucionCapacitacion;
	}
	
	public String getCapNombre() {
		return this.capNombre;
	}

	public void setCapNombre(String capNombre) {
		this.capNombre = capNombre;
	}

	public Integer getCapNumHoras() {
		return this.capNumHoras;
	}

	public void setCapNumHoras(Integer capNumHoras) {
		this.capNumHoras = capNumHoras;
	}

	public Boolean getCapRelacionPerfilProfesional() {
		return this.capRelacionPerfilProfesional;
	}

	public void setCapRelacionPerfilProfesional(Boolean capRelacionPerfilProfesional) {
		this.capRelacionPerfilProfesional = capRelacionPerfilProfesional;
	}
	
	public String getCapTipoEvento() {
		return this.capTipoEvento;
	}

	public void setCapTipoEvento(String capTipoEvento) {
		this.capTipoEvento = capTipoEvento;
	}

	public GenPersona getGenPersona() {
		return this.genPersona;
	}

	public void setGenPersona(GenPersona genPersona) {
		this.genPersona = genPersona;
	}

}