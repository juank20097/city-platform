package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the gen_elemento_barrio_valor database table.
 * 
 */
@Embeddable
public class GenElementoBarrioValorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="elb_id", insertable=false, updatable=false)
	private Integer elbId;

	@Column(name="bar_id", insertable=false, updatable=false)
	private String barId;

	public GenElementoBarrioValorPK() {
	}
	public Integer getElbId() {
		return this.elbId;
	}
	public void setElbId(Integer elbId) {
		this.elbId = elbId;
	}
	public String getBarId() {
		return this.barId;
	}
	public void setBarId(String barId) {
		this.barId = barId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GenElementoBarrioValorPK)) {
			return false;
		}
		GenElementoBarrioValorPK castOther = (GenElementoBarrioValorPK)other;
		return 
			this.elbId.equals(castOther.elbId)
			&& this.barId.equals(castOther.barId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.elbId.hashCode();
		hash = hash * prime + this.barId.hashCode();
		
		return hash;
	}
}