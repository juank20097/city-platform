package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the gen_familiares database table.
 * 
 */
@Entity
@Table(name="gen_familiares")
@NamedQuery(name="GenFamiliare.findAll", query="SELECT g FROM GenFamiliare g")
public class GenFamiliare implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GenFamiliarePK id;

	@Column(name="fam_estabilidad")
	private String famEstabilidad;

	@Temporal(TemporalType.DATE)
	@Column(name="fam_fecha_nacimiento")
	private Date famFechaNacimiento;

	@Column(name="fam_labor")
	private String famLabor;

	@Column(name="fam_lugar")
	private String famLugar;

	@Column(name="fam_nombre")
	private String famNombre;

	@Column(name="fam_tipo")
	private String famTipo;

	//bi-directional many-to-one association to GenPersonaDetalle
	@ManyToOne
	@JoinColumn(name="pde_dni", insertable=false, updatable=false)
	private GenPersonaDetalle genPersonaDetalle;

	public GenFamiliare() {
	}

	public GenFamiliarePK getId() {
		return this.id;
	}

	public void setId(GenFamiliarePK id) {
		this.id = id;
	}

	public String getFamEstabilidad() {
		return this.famEstabilidad;
	}

	public void setFamEstabilidad(String famEstabilidad) {
		this.famEstabilidad = famEstabilidad;
	}

	public Date getFamFechaNacimiento() {
		return this.famFechaNacimiento;
	}

	public void setFamFechaNacimiento(Date famFechaNacimiento) {
		this.famFechaNacimiento = famFechaNacimiento;
	}

	public String getFamLabor() {
		return this.famLabor;
	}

	public void setFamLabor(String famLabor) {
		this.famLabor = famLabor;
	}

	public String getFamLugar() {
		return this.famLugar;
	}

	public void setFamLugar(String famLugar) {
		this.famLugar = famLugar;
	}

	public String getFamNombre() {
		return this.famNombre;
	}

	public void setFamNombre(String famNombre) {
		this.famNombre = famNombre;
	}

	public String getFamTipo() {
		return this.famTipo;
	}

	public void setFamTipo(String famTipo) {
		this.famTipo = famTipo;
	}

	public GenPersonaDetalle getGenPersonaDetalle() {
		return this.genPersonaDetalle;
	}

	public void setGenPersonaDetalle(GenPersonaDetalle genPersonaDetalle) {
		this.genPersonaDetalle = genPersonaDetalle;
	}

}