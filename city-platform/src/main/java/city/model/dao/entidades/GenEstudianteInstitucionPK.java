package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the gen_estudiante_institucion database table.
 * 
 */
@Embeddable
public class GenEstudianteInstitucionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ins_codigo", insertable=false, updatable=false)
	private String insCodigo;

	@Column(name="per_dni", insertable=false, updatable=false)
	private String perDni;

	public GenEstudianteInstitucionPK() {
	}
	public String getInsCodigo() {
		return this.insCodigo;
	}
	public void setInsCodigo(String insCodigo) {
		this.insCodigo = insCodigo;
	}
	public String getPerDni() {
		return this.perDni;
	}
	public void setPerDni(String perDni) {
		this.perDni = perDni;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GenEstudianteInstitucionPK)) {
			return false;
		}
		GenEstudianteInstitucionPK castOther = (GenEstudianteInstitucionPK)other;
		return 
			this.insCodigo.equals(castOther.insCodigo)
			&& this.perDni.equals(castOther.perDni);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.insCodigo.hashCode();
		hash = hash * prime + this.perDni.hashCode();
		
		return hash;
	}
}