package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the gen_zonas_comunidades database table.
 * 
 */
@Embeddable
public class GenZonasComunidadePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="com_id", insertable=false, updatable=false)
	private String comId;

	@Column(name="zon_id", insertable=false, updatable=false)
	private String zonId;

	public GenZonasComunidadePK() {
	}
	public String getComId() {
		return this.comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
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
		if (!(other instanceof GenZonasComunidadePK)) {
			return false;
		}
		GenZonasComunidadePK castOther = (GenZonasComunidadePK)other;
		return 
			this.comId.equals(castOther.comId)
			&& this.zonId.equals(castOther.zonId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.comId.hashCode();
		hash = hash * prime + this.zonId.hashCode();
		
		return hash;
	}
}