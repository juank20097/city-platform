package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the gen_elemento_zona_valor database table.
 * 
 */
@Embeddable
public class GenElementoZonaValorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="elz_id", insertable=false, updatable=false)
	private Integer elzId;

	@Column(name="zon_id", insertable=false, updatable=false)
	private String zonId;

	public GenElementoZonaValorPK() {
	}
	public Integer getElzId() {
		return this.elzId;
	}
	public void setElzId(Integer elzId) {
		this.elzId = elzId;
	}
	public String getZonId() {
		return this.zonId;
	}
	public void setZonId(String zonId) {
		this.zonId = zonId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GenElementoZonaValorPK)) {
			return false;
		}
		GenElementoZonaValorPK castOther = (GenElementoZonaValorPK)other;
		return 
			this.elzId.equals(castOther.elzId)
			&& this.zonId.equals(castOther.zonId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.elzId.hashCode();
		hash = hash * prime + this.zonId.hashCode();
		
		return hash;
	}
}