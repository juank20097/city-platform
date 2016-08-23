package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the gen_formacionacademica database table.
 * 
 */
@Entity
@Table(name="gen_formacionacademica")
@NamedQuery(name="GenFormacionacademica.findAll", query="SELECT g FROM GenFormacionacademica g")
public class GenFormacionacademica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GEN_FORMACIONACADEMICA_FOAID_GENERATOR", sequenceName="SEQ_FORM_ACADEMICA", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GEN_FORMACIONACADEMICA_FOAID_GENERATOR")
	@Column(name="foa_id")
	private Integer foaId;

	@Column(name="foa_actual")
	private Boolean foaActual;

	@Column(name="foa_area_laboral_estudio", length=100)
	private String foaAreaLaboralEstudio;

	@Column(name="foa_duracion")
	private BigDecimal foaDuracion;

	@Temporal(TemporalType.DATE)
	@Column(name="foa_fecha_fin")
	private Date foaFechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="foa_fecha_inicio")
	private Date foaFechaInicio;

	@Column(name="foa_institucion", length=200)
	private String foaInstitucion;

	@Column(name="foa_nivel_instruccion", length=50)
	private String foaNivelInstruccion;

	@Column(name="foa_num_reg_senescyt", length=100)
	private String foaNumRegSenescyt;

	@Column(name="foa_pais", length=50)
	private String foaPais;

	@Column(name="foa_registro_senescyt")
	private Boolean foaRegistroSenescyt;

	@Column(name="foa_titulo", length=200)
	private String foaTitulo;

	//bi-directional many-to-one association to GenPersona
	@ManyToOne
	@JoinColumn(name="per_dni")
	private GenPersona genPersona;

	public GenFormacionacademica() {
	}

	public Integer getFoaId() {
		return this.foaId;
	}

	public void setFoaId(Integer foaId) {
		this.foaId = foaId;
	}

	public Boolean getFoaActual() {
		return this.foaActual;
	}

	public void setFoaActual(Boolean foaActual) {
		this.foaActual = foaActual;
	}

	public String getFoaAreaLaboralEstudio() {
		return this.foaAreaLaboralEstudio;
	}

	public void setFoaAreaLaboralEstudio(String foaAreaLaboralEstudio) {
		this.foaAreaLaboralEstudio = foaAreaLaboralEstudio;
	}

	public BigDecimal getFoaDuracion() {
		return this.foaDuracion;
	}

	public void setFoaDuracion(BigDecimal foaDuracion) {
		this.foaDuracion = foaDuracion;
	}

	public Date getFoaFechaFin() {
		return this.foaFechaFin;
	}

	public void setFoaFechaFin(Date foaFechaFin) {
		this.foaFechaFin = foaFechaFin;
	}

	public Date getFoaFechaInicio() {
		return this.foaFechaInicio;
	}

	public void setFoaFechaInicio(Date foaFechaInicio) {
		this.foaFechaInicio = foaFechaInicio;
	}

	public String getFoaInstitucion() {
		return this.foaInstitucion;
	}

	public void setFoaInstitucion(String foaInstitucion) {
		this.foaInstitucion = foaInstitucion;
	}

	public String getFoaNivelInstruccion() {
		return this.foaNivelInstruccion;
	}

	public void setFoaNivelInstruccion(String foaNivelInstruccion) {
		this.foaNivelInstruccion = foaNivelInstruccion;
	}

	public String getFoaNumRegSenescyt() {
		return this.foaNumRegSenescyt;
	}

	public void setFoaNumRegSenescyt(String foaNumRegSenescyt) {
		this.foaNumRegSenescyt = foaNumRegSenescyt;
	}

	public String getFoaPais() {
		return this.foaPais;
	}

	public void setFoaPais(String foaPais) {
		this.foaPais = foaPais;
	}

	public Boolean getFoaRegistroSenescyt() {
		return this.foaRegistroSenescyt;
	}

	public void setFoaRegistroSenescyt(Boolean foaRegistroSenescyt) {
		this.foaRegistroSenescyt = foaRegistroSenescyt;
	}

	public String getFoaTitulo() {
		return this.foaTitulo;
	}

	public void setFoaTitulo(String foaTitulo) {
		this.foaTitulo = foaTitulo;
	}

	public GenPersona getGenPersona() {
		return this.genPersona;
	}

	public void setGenPersona(GenPersona genPersona) {
		this.genPersona = genPersona;
	}

}