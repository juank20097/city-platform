package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the gen_funcionarios_institucion database table.
 * 
 */
@Entity
@Table(name="gen_funcionarios_institucion")
@NamedQuery(name="GenFuncionariosInstitucion.findAll", query="SELECT g FROM GenFuncionariosInstitucion g")
public class GenFuncionariosInstitucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GenFuncionariosInstitucionPK id;

	@Column(name="fun_cargo")
	private String funCargo;

	@Column(name="fun_direccion")
	private String funDireccion;

	@Temporal(TemporalType.DATE)
	@Column(name="fun_fecha_ingreso")
	private Date funFechaIngreso;

	@Column(name="fun_gerencia")
	private String funGerencia;

	@Column(name="fun_jefe_inmediato")
	private String funJefeInmediato;

	@Column(name="fun_tipo")
	private String funTipo;

	@Column(name="fun_tipo_evaluacion")
	private String funTipoEvaluacion;

	//bi-directional many-to-one association to GenInstitucione
	@ManyToOne
	@JoinColumn(name="ins_codigo", insertable=false, updatable=false)
	private GenInstitucione genInstitucione;

	//bi-directional many-to-one association to GenPersona
	@ManyToOne
	@JoinColumn(name="per_dni", insertable=false, updatable=false)
	private GenPersona genPersona;

	public GenFuncionariosInstitucion() {
	}

	public GenFuncionariosInstitucionPK getId() {
		return this.id;
	}

	public void setId(GenFuncionariosInstitucionPK id) {
		this.id = id;
	}

	public String getFunCargo() {
		return this.funCargo;
	}

	public void setFunCargo(String funCargo) {
		this.funCargo = funCargo;
	}

	public String getFunDireccion() {
		return this.funDireccion;
	}

	public void setFunDireccion(String funDireccion) {
		this.funDireccion = funDireccion;
	}

	public Date getFunFechaIngreso() {
		return this.funFechaIngreso;
	}

	public void setFunFechaIngreso(Date funFechaIngreso) {
		this.funFechaIngreso = funFechaIngreso;
	}

	public String getFunGerencia() {
		return this.funGerencia;
	}

	public void setFunGerencia(String funGerencia) {
		this.funGerencia = funGerencia;
	}

	public String getFunJefeInmediato() {
		return this.funJefeInmediato;
	}

	public void setFunJefeInmediato(String funJefeInmediato) {
		this.funJefeInmediato = funJefeInmediato;
	}

	public String getFunTipo() {
		return this.funTipo;
	}

	public void setFunTipo(String funTipo) {
		this.funTipo = funTipo;
	}

	public String getFunTipoEvaluacion() {
		return this.funTipoEvaluacion;
	}

	public void setFunTipoEvaluacion(String funTipoEvaluacion) {
		this.funTipoEvaluacion = funTipoEvaluacion;
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