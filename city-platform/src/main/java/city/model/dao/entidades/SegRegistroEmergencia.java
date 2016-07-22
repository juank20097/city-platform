package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the seg_registro_emergencias database table.
 * 
 */
@Entity
@Table(name = "seg_registro_emergencias")
@NamedQuery(name = "SegRegistroEmergencia.findAll", query = "SELECT s FROM SegRegistroEmergencia s")
public class SegRegistroEmergencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "seg_id")
	private Integer segId;

	@Column(name = "seg_accion")
	private String segAccion;

	@Column(name = "seg_emergencia")
	private String segEmergencia;

	@Column(name = "seg_fecha")
	private Timestamp segFecha;

	@Column(name = "seg_tipo_emergencia")
	private String segTipoEmergencia;

	@Column(name = "seg_ubicacion")
	private String segUbicacion;

	// bi-directional many-to-one association to GenFuncionariosInstitucion
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "ins_codigo", referencedColumnName = "ins_codigo"),
			@JoinColumn(name = "per_dni", referencedColumnName = "per_dni") })
	private GenFuncionariosInstitucion genFuncionariosInstitucion;

	public SegRegistroEmergencia() {
	}

	public Integer getSegId() {
		return this.segId;
	}

	public void setSegId(Integer segId) {
		this.segId = segId;
	}

	public String getSegAccion() {
		return this.segAccion;
	}

	public void setSegAccion(String segAccion) {
		this.segAccion = segAccion;
	}

	public String getSegEmergencia() {
		return this.segEmergencia;
	}

	public void setSegEmergencia(String segEmergencia) {
		this.segEmergencia = segEmergencia;
	}

	public Timestamp getSegFecha() {
		return this.segFecha;
	}

	public void setSegFecha(Timestamp segFecha) {
		this.segFecha = segFecha;
	}

	public String getSegUbicacion() {
		return this.segUbicacion;
	}

	public void setSegUbicacion(String segUbicacion) {
		this.segUbicacion = segUbicacion;
	}

	public String getSegTipoEmergencia() {
		return this.segTipoEmergencia;
	}

	public void setSegTipoEmergencia(String segTipoEmergencia) {
		this.segTipoEmergencia = segTipoEmergencia;
	}

	public GenFuncionariosInstitucion getGenFuncionariosInstitucion() {
		return this.genFuncionariosInstitucion;
	}

	public void setGenFuncionariosInstitucion(GenFuncionariosInstitucion genFuncionariosInstitucion) {
		this.genFuncionariosInstitucion = genFuncionariosInstitucion;
	}

}