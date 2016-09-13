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
	
	@Column(name="seg_documento")
	private String segDocumento;

	@Column(name = "seg_tipo_emergencia")
	private String segTipoEmergencia;

	@Column(name = "seg_latitud")
	private double segLatitud;

	@Column(name = "seg_longitud")
	private double segLongitud;

	@Column(name = "seg_sub_hijo")
	private String segSubHijo;

	@Column(name = "seg_sub_tipo")
	private String segSubTipo;

	@Column(name = "seg_archivo")
	private String segArchivo;
	
	@Column(name="seg_fecha_registro")
	private Timestamp segFechaRegistro;
	
	@Column(name="seg_usuario_aplicacion")
	private String segUsuarioAplicacion;

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

	public double getSegLatitud() {
		return this.segLatitud;
	}

	public void setSegLatitud(double segLatitud) {
		this.segLatitud = segLatitud;
	}

	public double getSegLongitud() {
		return this.segLongitud;
	}

	public void setSegLongitud(double segLongitud) {
		this.segLongitud = segLongitud;
	}

	public String getSegSubHijo() {
		return this.segSubHijo;
	}

	public void setSegSubHijo(String segSubHijo) {
		this.segSubHijo = segSubHijo;
	}

	public String getSegSubTipo() {
		return this.segSubTipo;
	}

	public void setSegSubTipo(String segSubTipo) {
		this.segSubTipo = segSubTipo;
	}

	public String getSegArchivo() {
		return this.segArchivo;
	}

	public void setSegArchivo(String segArchivo) {
		this.segArchivo = segArchivo;
	}
	
	public String getSegUsuarioAplicacion() {
		return this.segUsuarioAplicacion;
	}

	public void setSegUsuarioAplicacion(String segUsuarioAplicacion) {
		this.segUsuarioAplicacion = segUsuarioAplicacion;
	}
	
	public Timestamp getSegFechaRegistro() {
		return this.segFechaRegistro;
	}

	public void setSegFechaRegistro(Timestamp segFechaRegistro) {
		this.segFechaRegistro = segFechaRegistro;
	}
	
	public String getSegDocumento() {
		return this.segDocumento;
	}

	public void setSegDocumento(String segDocumento) {
		this.segDocumento = segDocumento;
	}

}