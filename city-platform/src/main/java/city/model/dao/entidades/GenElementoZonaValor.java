package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the gen_elemento_zona_valor database table.
 * 
 */
@Entity
@Table(name="gen_elemento_zona_valor")
@NamedQuery(name="GenElementoZonaValor.findAll", query="SELECT g FROM GenElementoZonaValor g")
public class GenElementoZonaValor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GenElementoZonaValorPK id;

	@Column(name="ezv_estado", columnDefinition = "bpchar", length = 1)
	private String ezvEstado;

	@Column(name="ezv_valor")
	private BigDecimal ezvValor;

	//bi-directional many-to-one association to GenElementosZona
	@ManyToOne
	@JoinColumn(name="elz_id", insertable=false, updatable=false)
	private GenElementosZona genElementosZona;

	//bi-directional many-to-one association to GenZona
	@ManyToOne
	@JoinColumn(name="zon_id", insertable=false, updatable=false)
	private GenZona genZona;

	public GenElementoZonaValor() {
	}

	public GenElementoZonaValorPK getId() {
		return this.id;
	}

	public void setId(GenElementoZonaValorPK id) {
		this.id = id;
	}

	public String getEzvEstado() {
		return this.ezvEstado;
	}

	public void setEzvEstado(String ezvEstado) {
		this.ezvEstado = ezvEstado;
	}

	public BigDecimal getEzvValor() {
		return this.ezvValor;
	}

	public void setEzvValor(BigDecimal ezvValor) {
		this.ezvValor = ezvValor;
	}

	public GenElementosZona getGenElementosZona() {
		return this.genElementosZona;
	}

	public void setGenElementosZona(GenElementosZona genElementosZona) {
		this.genElementosZona = genElementosZona;
	}

	public GenZona getGenZona() {
		return this.genZona;
	}

	public void setGenZona(GenZona genZona) {
		this.genZona = genZona;
	}

}