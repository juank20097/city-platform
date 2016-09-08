package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the gen_familiares database table.
 * 
 */
@Embeddable
public class GenFamiliarePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="fam_id")
	private Integer famId;

	@Column(name="pde_dni", insertable=false, updatable=false)
	private String pdeDni;

	public GenFamiliarePK() {
	}
	public Integer getFamId() {
		return this.famId;
	}
	public void setFamId(Integer famId) {
		this.famId = famId;
	}
	public String getPdeDni() {
		return this.pdeDni;
	}
	public void setPdeDni(String pdeDni) {
		this.pdeDni = pdeDni;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GenFamiliarePK)) {
			return false;
		}
		GenFamiliarePK castOther = (GenFamiliarePK)other;
		return 
			this.famId.equals(castOther.famId)
			&& this.pdeDni.equals(castOther.pdeDni);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.famId.hashCode();
		hash = hash * prime + this.pdeDni.hashCode();
		
		return hash;
	}
}