package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the gen_visitante database table.
 * 
 */
@Embeddable
public class GenVisitantePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="per_dni", insertable=false, updatable=false)
	private String perDni;

	@Column(name="vis_tipo")
	private String visTipo;

	@Column(name="vis_referencia")
	private String visReferencia;

	public GenVisitantePK() {
	}
	public String getPerDni() {
		return this.perDni;
	}
	public void setPerDni(String perDni) {
		this.perDni = perDni;
	}
	public String getVisTipo() {
		return this.visTipo;
	}
	public void setVisTipo(String visTipo) {
		this.visTipo = visTipo;
	}
	public String getVisReferencia() {
		return this.visReferencia;
	}
	public void setVisReferencia(String visReferencia) {
		this.visReferencia = visReferencia;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GenVisitantePK)) {
			return false;
		}
		GenVisitantePK castOther = (GenVisitantePK)other;
		return 
			this.perDni.equals(castOther.perDni)
			&& this.visTipo.equals(castOther.visTipo)
			&& this.visReferencia.equals(castOther.visReferencia);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.perDni.hashCode();
		hash = hash * prime + this.visTipo.hashCode();
		hash = hash * prime + this.visReferencia.hashCode();
		
		return hash;
	}
}