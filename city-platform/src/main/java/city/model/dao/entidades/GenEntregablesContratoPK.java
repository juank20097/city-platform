package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the gen_entregables_contrato database table.
 * 
 */
@Embeddable
public class GenEntregablesContratoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cas_id", insertable=false, updatable=false)
	private String casId;

	@Column(name="eco_documento")
	private String ecoDocumento;

	public GenEntregablesContratoPK() {
	}
	public String getCasId() {
		return this.casId;
	}
	public void setCasId(String casId) {
		this.casId = casId;
	}
	public String getEcoDocumento() {
		return this.ecoDocumento;
	}
	public void setEcoDocumento(String ecoDocumento) {
		this.ecoDocumento = ecoDocumento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GenEntregablesContratoPK)) {
			return false;
		}
		GenEntregablesContratoPK castOther = (GenEntregablesContratoPK)other;
		return 
			this.casId.equals(castOther.casId)
			&& this.ecoDocumento.equals(castOther.ecoDocumento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.casId.hashCode();
		hash = hash * prime + this.ecoDocumento.hashCode();
		
		return hash;
	}
}