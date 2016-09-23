package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the gen_elemento_barrio_valor database table.
 * 
 */
@Entity
@Table(name="gen_elemento_barrio_valor")
@NamedQuery(name="GenElementoBarrioValor.findAll", query="SELECT g FROM GenElementoBarrioValor g")
public class GenElementoBarrioValor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GenElementoBarrioValorPK id;

	@Column(name="ebv_valor")
	private BigDecimal ebvValor;

	@Column(name="ebv_estado", columnDefinition = "bpchar", length = 1)
	private String ebvEstado;

	//bi-directional many-to-one association to GenBarrio
	@ManyToOne
	@JoinColumn(name="bar_id", insertable=false, updatable=false)
	private GenBarrio genBarrio;

	//bi-directional many-to-one association to GenElementosBarrio
	@ManyToOne
	@JoinColumn(name="elb_id", insertable=false, updatable=false)
	private GenElementosBarrio genElementosBarrio;

	public GenElementoBarrioValor() {
	}

	public GenElementoBarrioValorPK getId() {
		return this.id;
	}

	public void setId(GenElementoBarrioValorPK id) {
		this.id = id;
	}

	public BigDecimal getEbvValor() {
		return this.ebvValor;
	}

	public void setEbvValor(BigDecimal ebvValor) {
		this.ebvValor = ebvValor;
	}

	public String getEbvEstado() {
		return this.ebvEstado;
	}

	public void setEbvEstado(String ebvEstado) {
		this.ebvEstado = ebvEstado;
	}

	public GenBarrio getGenBarrio() {
		return this.genBarrio;
	}

	public void setGenBarrio(GenBarrio genBarrio) {
		this.genBarrio = genBarrio;
	}

	public GenElementosBarrio getGenElementosBarrio() {
		return this.genElementosBarrio;
	}

	public void setGenElementosBarrio(GenElementosBarrio genElementosBarrio) {
		this.genElementosBarrio = genElementosBarrio;
	}

}