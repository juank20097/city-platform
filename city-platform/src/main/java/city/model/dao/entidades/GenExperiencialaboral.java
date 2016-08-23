package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the gen_experiencialaboral database table.
 * 
 */
@Entity
@Table(name="gen_experiencialaboral")
@NamedQuery(name="GenExperiencialaboral.findAll", query="SELECT g FROM GenExperiencialaboral g")
public class GenExperiencialaboral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GEN_EXPERIENCIALABORAL_EXLID_GENERATOR", sequenceName="SEQ_EXP_LABORAL", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GEN_EXPERIENCIALABORAL_EXLID_GENERATOR")
	@Column(name="exl_id")
	private Integer exlId;

	@Column(name="exl_actual")
	private Boolean exlActual;

	@Column(name="exl_area_laboral_estudio", length=100)
	private String exlAreaLaboralEstudio;

	@Column(name="exl_empresa", columnDefinition="text")
	private String exlEmpresa;

	@Temporal(TemporalType.DATE)
	@Column(name="exl_fecha_fin")
	private Date exlFechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="exl_fecha_inicio")
	private Date exlFechaInicio;

	@Column(name="exl_pais", length=50)
	private String exlPais;

	@Column(name="exl_puesto", columnDefinition="text")
	private String exlPuesto;

	@Column(name="exl_responsabilidades", columnDefinition="text")
	private String exlResponsabilidades;

	@Column(name="exl_sector_publico")
	private Boolean exlSectorPublico;

	//bi-directional many-to-one association to GenPersona
	@ManyToOne
	@JoinColumn(name="per_dni")
	private GenPersona genPersona;

	public GenExperiencialaboral() {
	}

	public Integer getExlId() {
		return this.exlId;
	}

	public void setExlId(Integer exlId) {
		this.exlId = exlId;
	}

	public Boolean getExlActual() {
		return this.exlActual;
	}

	public void setExlActual(Boolean exlActual) {
		this.exlActual = exlActual;
	}

	public String getExlAreaLaboralEstudio() {
		return this.exlAreaLaboralEstudio;
	}

	public void setExlAreaLaboralEstudio(String exlAreaLaboralEstudio) {
		this.exlAreaLaboralEstudio = exlAreaLaboralEstudio;
	}

	public String getExlEmpresa() {
		return this.exlEmpresa;
	}

	public void setExlEmpresa(String exlEmpresa) {
		this.exlEmpresa = exlEmpresa;
	}

	public Date getExlFechaFin() {
		return this.exlFechaFin;
	}

	public void setExlFechaFin(Date exlFechaFin) {
		this.exlFechaFin = exlFechaFin;
	}

	public Date getExlFechaInicio() {
		return this.exlFechaInicio;
	}

	public void setExlFechaInicio(Date exlFechaInicio) {
		this.exlFechaInicio = exlFechaInicio;
	}

	public String getExlPais() {
		return this.exlPais;
	}

	public void setExlPais(String exlPais) {
		this.exlPais = exlPais;
	}

	public String getExlPuesto() {
		return this.exlPuesto;
	}

	public void setExlPuesto(String exlPuesto) {
		this.exlPuesto = exlPuesto;
	}

	public String getExlResponsabilidades() {
		return this.exlResponsabilidades;
	}

	public void setExlResponsabilidades(String exlResponsabilidades) {
		this.exlResponsabilidades = exlResponsabilidades;
	}

	public Boolean getExlSectorPublico() {
		return this.exlSectorPublico;
	}

	public void setExlSectorPublico(Boolean exlSectorPublico) {
		this.exlSectorPublico = exlSectorPublico;
	}

	public GenPersona getGenPersona() {
		return this.genPersona;
	}

	public void setGenPersona(GenPersona genPersona) {
		this.genPersona = genPersona;
	}

}