package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the gen_externos database table.
 * 
 */
@Entity
@Table(name="gen_externos")
@NamedQuery(name="GenExterno.findAll", query="SELECT g FROM GenExterno g")
public class GenExterno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="per_dni")
	private String perDni;

	@Column(name="ext_referencia")
	private String extReferencia;

	@Column(name="ext_tipo")
	private String extTipo;

	//bi-directional one-to-one association to GenPersona
	@OneToOne
	@JoinColumn(name="per_dni")
	private GenPersona genPersona;

	public GenExterno() {
	}

	public String getPerDni() {
		return this.perDni;
	}

	public void setPerDni(String perDni) {
		this.perDni = perDni;
	}

	public String getExtReferencia() {
		return this.extReferencia;
	}

	public void setExtReferencia(String extReferencia) {
		this.extReferencia = extReferencia;
	}

	public String getExtTipo() {
		return this.extTipo;
	}

	public void setExtTipo(String extTipo) {
		this.extTipo = extTipo;
	}

	public GenPersona getGenPersona() {
		return this.genPersona;
	}

	public void setGenPersona(GenPersona genPersona) {
		this.genPersona = genPersona;
	}

}