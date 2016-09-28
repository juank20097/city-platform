package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the gen_historial_seguimiento database table.
 * 
 */
@Embeddable
public class GenHistorialSeguimientoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="sue_id", insertable=false, updatable=false)
	private Integer sueId;

	@Column(name="hse_id")
	private Integer hseId;

	public GenHistorialSeguimientoPK() {
	}
	public Integer getSueId() {
		return this.sueId;
	}
	public void setSueId(Integer sueId) {
		this.sueId = sueId;
	}
	public Integer getHseId() {
		return this.hseId;
	}
	public void setHseId(Integer hseId) {
		this.hseId = hseId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GenHistorialSeguimientoPK)) {
			return false;
		}
		GenHistorialSeguimientoPK castOther = (GenHistorialSeguimientoPK)other;
		return 
			this.sueId.equals(castOther.sueId)
			&& this.hseId.equals(castOther.hseId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.sueId.hashCode();
		hash = hash * prime + this.hseId.hashCode();
		
		return hash;
	}
}