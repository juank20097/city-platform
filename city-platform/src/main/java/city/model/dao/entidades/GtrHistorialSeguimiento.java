package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the gtr_historial_seguimiento database table.
 * 
 */
@Entity
@Table(name="gtr_historial_seguimiento")
@NamedQuery(name="GtrHistorialSeguimiento.findAll", query="SELECT g FROM GtrHistorialSeguimiento g")
public class GtrHistorialSeguimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GtrHistorialSeguimientoPK id;

	@Column(name="hse_adjunto_doc")
	private String hseAdjuntoDoc;

	@Column(name="hse_adjunto_fot")
	private String hseAdjuntoFot;

	@Column(name="hse_estado", columnDefinition = "bpchar")
	private String hseEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="hse_fecha")
	private Date hseFecha;

	@Column(name="hse_fecha_registro")
	private Timestamp hseFechaRegistro;

	@Column(name="hse_novedades")
	private String hseNovedades;

	@Column(name="hse_responsable")
	private String hseResponsable;

	@Column(name="hse_usuario")
	private String hseUsuario;

	//bi-directional many-to-one association to GtrAsignacionSuelo
	@ManyToOne
	@JoinColumn(name="sue_id", insertable=false, updatable=false)
	private GtrAsignacionSuelo gtrAsignacionSuelo;

	public GtrHistorialSeguimiento() {
	}

	public GtrHistorialSeguimientoPK getId() {
		return this.id;
	}

	public void setId(GtrHistorialSeguimientoPK id) {
		this.id = id;
	}

	public String getHseAdjuntoDoc() {
		return this.hseAdjuntoDoc;
	}

	public void setHseAdjuntoDoc(String hseAdjuntoDoc) {
		this.hseAdjuntoDoc = hseAdjuntoDoc;
	}

	public String getHseAdjuntoFot() {
		return this.hseAdjuntoFot;
	}

	public void setHseAdjuntoFot(String hseAdjuntoFot) {
		this.hseAdjuntoFot = hseAdjuntoFot;
	}

	public String getHseEstado() {
		return this.hseEstado;
	}

	public void setHseEstado(String hseEstado) {
		this.hseEstado = hseEstado;
	}

	public Date getHseFecha() {
		return this.hseFecha;
	}

	public void setHseFecha(Date hseFecha) {
		this.hseFecha = hseFecha;
	}

	public Timestamp getHseFechaRegistro() {
		return this.hseFechaRegistro;
	}

	public void setHseFechaRegistro(Timestamp hseFechaRegistro) {
		this.hseFechaRegistro = hseFechaRegistro;
	}

	public String getHseNovedades() {
		return this.hseNovedades;
	}

	public void setHseNovedades(String hseNovedades) {
		this.hseNovedades = hseNovedades;
	}

	public String getHseResponsable() {
		return this.hseResponsable;
	}

	public void setHseResponsable(String hseResponsable) {
		this.hseResponsable = hseResponsable;
	}

	public String getHseUsuario() {
		return this.hseUsuario;
	}

	public void setHseUsuario(String hseUsuario) {
		this.hseUsuario = hseUsuario;
	}

	public GtrAsignacionSuelo getGtrAsignacionSuelo() {
		return this.gtrAsignacionSuelo;
	}

	public void setGtrAsignacionSuelo(GtrAsignacionSuelo gtrAsignacionSuelo) {
		this.gtrAsignacionSuelo = gtrAsignacionSuelo;
	}

}