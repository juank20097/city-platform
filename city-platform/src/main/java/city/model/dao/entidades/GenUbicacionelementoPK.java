package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the gen_ubicacionelemento database table.
 * 
 */
@Embeddable
public class GenUbicacionelementoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ele_id", insertable=false, updatable=false)
	private Integer eleId;

	@Column(name="id_vecindario_zona", length = 50)
	private String idVecindarioZona;

	public GenUbicacionelementoPK() {
	}
	public Integer getEleId() {
		return this.eleId;
	}
	public void setEleId(Integer eleId) {
		this.eleId = eleId;
	}
	public String getIdVecindarioZona() {
		return this.idVecindarioZona;
	}
	public void setIdVecindarioZona(String idVecindarioZona) {
		this.idVecindarioZona = idVecindarioZona;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GenUbicacionelementoPK)) {
			return false;
		}
		GenUbicacionelementoPK castOther = (GenUbicacionelementoPK)other;
		return 
			this.eleId.equals(castOther.eleId)
			&& this.idVecindarioZona.equals(castOther.idVecindarioZona);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.eleId.hashCode();
		hash = hash * prime + this.idVecindarioZona.hashCode();
		
		return hash;
	}
}