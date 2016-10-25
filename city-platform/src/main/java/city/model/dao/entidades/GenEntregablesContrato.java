package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the gen_entregables_contrato database table.
 * 
 */
@Entity
@Table(name="gen_entregables_contrato")
@NamedQuery(name="GenEntregablesContrato.findAll", query="SELECT g FROM GenEntregablesContrato g")
public class GenEntregablesContrato implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GenEntregablesContratoPK id;

	@Column(name="eco_estado", columnDefinition = "bpchar", length = 1)
	private String ecoEstado;

	@Column(name="eco_fecha_max_entrega")
	private Timestamp ecoFechaMaxEntrega;

	@Column(name="eco_fecha_subida")
	private Timestamp ecoFechaSubida;

	@Column(name="eco_responsable")
	private String ecoResponsable;

	//bi-directional many-to-one association to GenContratoAsignacion
	@ManyToOne
	@JoinColumn(name="cas_id", insertable=false, updatable=false)
	private GenContratoAsignacion genContratoAsignacion;

	public GenEntregablesContrato() {
	}

	public GenEntregablesContratoPK getId() {
		return this.id;
	}

	public void setId(GenEntregablesContratoPK id) {
		this.id = id;
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

	public GenContratoAsignacion getGenContratoAsignacion() {
		return this.genContratoAsignacion;
	}

	public void setGenContratoAsignacion(GenContratoAsignacion genContratoAsignacion) {
		this.genContratoAsignacion = genContratoAsignacion;
	}

}