package city.model.dao.entidades;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the gen_ubicacionelemento database table.
 * 
 */
@Entity
@Table(name="gen_ubicacionelemento")
@NamedQuery(name="GenUbicacionelemento.findAll", query="SELECT g FROM GenUbicacionelemento g")
public class GenUbicacionelemento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GenUbicacionelementoPK id;

	@Column(name="vel_estado", columnDefinition = "bpchar", length = 1)
	private String velEstado;

	@Column(name="vel_valor")
	private BigDecimal velValor;

	//bi-directional many-to-one association to GenElemento
	@ManyToOne
	@JoinColumn(name="ele_id", insertable=false, updatable=false)
	private GenElemento genElemento;

	public GenUbicacionelemento() {
	}

	public GenUbicacionelementoPK getId() {
		return this.id;
	}

	public void setId(GenUbicacionelementoPK id) {
		this.id = id;
	}

	public String getVelEstado() {
		return this.velEstado;
	}

	public void setVelEstado(String velEstado) {
		this.velEstado = velEstado;
	}

	public BigDecimal getVelValor() {
		return this.velValor;
	}

	public void setVelValor(BigDecimal velValor) {
		this.velValor = velValor;
	}

	public GenElemento getGenElemento() {
		return this.genElemento;
	}

	public void setGenElemento(GenElemento genElemento) {
		this.genElemento = genElemento;
	}

}