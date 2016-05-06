package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the int_sitios_ccaa_departamentos database table.
 * 
 */
@Embeddable
public class IntSitiosCcaaDepartamentoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cca_id_departamento")
	private Integer ccaIdDepartamento;

	@Column(name="sit_id", insertable=false, updatable=false)
	private String sitId;

	public IntSitiosCcaaDepartamentoPK() {
	}
	public Integer getCcaIdDepartamento() {
		return this.ccaIdDepartamento;
	}
	public void setCcaIdDepartamento(Integer ccaIdDepartamento) {
		this.ccaIdDepartamento = ccaIdDepartamento;
	}
	public String getSitId() {
		return this.sitId;
	}
	public void setSitId(String sitId) {
		this.sitId = sitId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof IntSitiosCcaaDepartamentoPK)) {
			return false;
		}
		IntSitiosCcaaDepartamentoPK castOther = (IntSitiosCcaaDepartamentoPK)other;
		return 
			this.ccaIdDepartamento.equals(castOther.ccaIdDepartamento)
			&& this.sitId.equals(castOther.sitId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ccaIdDepartamento.hashCode();
		hash = hash * prime + this.sitId.hashCode();
		
		return hash;
	}
}