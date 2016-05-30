package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the gen_persona_institucion database table.
 * 
 */
@Embeddable
public class GenPersonaInstitucionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="per_dni",insertable = false, updatable = false)
	private String perDni;

	@Column(name="ins_codigo",insertable = false, updatable = false)
	private String insCodigo;

	@Column(name="pei_rol",insertable = false, updatable = false)
	private String peiRol;

	public GenPersonaInstitucionPK() {
	}
	public String getPerDni() {
		return this.perDni;
	}
	public void setPerDni(String perDni) {
		this.perDni = perDni;
	}
	public String getInsCodigo() {
		return this.insCodigo;
	}
	public void setInsCodigo(String insCodigo) {
		this.insCodigo = insCodigo;
	}
	public String getPeiRol() {
		return this.peiRol;
	}
	public void setPeiRol(String peiRol) {
		this.peiRol = peiRol;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GenPersonaInstitucionPK)) {
			return false;
		}
		GenPersonaInstitucionPK castOther = (GenPersonaInstitucionPK)other;
		return 
			this.perDni.equals(castOther.perDni)
			&& this.insCodigo.equals(castOther.insCodigo)
			&& this.peiRol.equals(castOther.peiRol);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.perDni.hashCode();
		hash = hash * prime + this.insCodigo.hashCode();
		hash = hash * prime + this.peiRol.hashCode();
		
		return hash;
	}
}