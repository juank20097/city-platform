package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the gen_historial_seguimiento database table.
 * 
 */
@Entity
@Table(name="gen_historial_seguimiento")
@NamedQuery(name="GenHistorialSeguimiento.findAll", query="SELECT g FROM GenHistorialSeguimiento g")
public class GenHistorialSeguimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GenHistorialSeguimientoPK id;

	@Column(name="hse_adjunto_doc")
	private String hseAdjuntoDoc;

	@Column(name="hse_adjunto_fot")
	private String hseAdjuntoFot;

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
	
	@Column(name="hse_estado", columnDefinition="bpchar")
	private String hseEstado;

	//bi-directional many-to-one association to GenAsignacionSuelo
	@ManyToOne
	@JoinColumn(name="sue_id", insertable=false, updatable=false)
	private GenAsignacionSuelo genAsignacionSuelo;

	public GenHistorialSeguimiento() {
	}

	public GenHistorialSeguimientoPK getId() {
		return this.id;
	}

	public void setId(GenHistorialSeguimientoPK id) {
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

	public GenAsignacionSuelo getGenAsignacionSuelo() {
		return this.genAsignacionSuelo;
	}

	public void setGenAsignacionSuelo(GenAsignacionSuelo genAsignacionSuelo) {
		this.genAsignacionSuelo = genAsignacionSuelo;
	}

}