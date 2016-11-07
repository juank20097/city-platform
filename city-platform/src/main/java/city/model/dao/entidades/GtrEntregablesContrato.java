package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the gtr_entregables_contrato database table.
 * 
 */
@Entity
@Table(name="gtr_entregables_contrato")
@NamedQuery(name="GtrEntregablesContrato.findAll", query="SELECT g FROM GtrEntregablesContrato g")
public class GtrEntregablesContrato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="eco_id")
	private Integer ecoId;

	@Column(name="eco_documento")
	private String ecoDocumento;

	@Column(name="eco_estado", columnDefinition = "bpchar")
	private String ecoEstado;

	@Column(name="eco_fecha_max_entrega")
	private Timestamp ecoFechaMaxEntrega;

	@Column(name="eco_fecha_subida")
	private Timestamp ecoFechaSubida;

	@Column(name="eco_responsable")
	private String ecoResponsable;

	@Column(name="eco_usuario_subida")
	private String ecoUsuarioSubida;

	//bi-directional many-to-one association to GtrContratoAsignacion
	@ManyToOne
	@JoinColumn(name="cas_id")
	private GtrContratoAsignacion gtrContratoAsignacion;

	public GtrEntregablesContrato() {
	}

	public Integer getEcoId() {
		return this.ecoId;
	}

	public void setEcoId(Integer ecoId) {
		this.ecoId = ecoId;
	}

	public String getEcoDocumento() {
		return this.ecoDocumento;
	}

	public void setEcoDocumento(String ecoDocumento) {
		this.ecoDocumento = ecoDocumento;
	}

	public String getEcoEstado() {
		return this.ecoEstado;
	}

	public void setEcoEstado(String ecoEstado) {
		this.ecoEstado = ecoEstado;
	}

	public Timestamp getEcoFechaMaxEntrega() {
		return this.ecoFechaMaxEntrega;
	}

	public void setEcoFechaMaxEntrega(Timestamp ecoFechaMaxEntrega) {
		this.ecoFechaMaxEntrega = ecoFechaMaxEntrega;
	}

	public Timestamp getEcoFechaSubida() {
		return this.ecoFechaSubida;
	}

	public void setEcoFechaSubida(Timestamp ecoFechaSubida) {
		this.ecoFechaSubida = ecoFechaSubida;
	}

	public String getEcoResponsable() {
		return this.ecoResponsable;
	}

	public void setEcoResponsable(String ecoResponsable) {
		this.ecoResponsable = ecoResponsable;
	}

	public String getEcoUsuarioSubida() {
		return this.ecoUsuarioSubida;
	}

	public void setEcoUsuarioSubida(String ecoUsuarioSubida) {
		this.ecoUsuarioSubida = ecoUsuarioSubida;
	}

	public GtrContratoAsignacion getGtrContratoAsignacion() {
		return this.gtrContratoAsignacion;
	}

	public void setGtrContratoAsignacion(GtrContratoAsignacion gtrContratoAsignacion) {
		this.gtrContratoAsignacion = gtrContratoAsignacion;
	}

}