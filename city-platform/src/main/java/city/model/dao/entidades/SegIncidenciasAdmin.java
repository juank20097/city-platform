package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the seg_incidencias_admin database table.
 * 
 */
@Entity
@Table(name="seg_incidencias_admin")
@NamedQuery(name="SegIncidenciasAdmin.findAll", query="SELECT s FROM SegIncidenciasAdmin s")
public class SegIncidenciasAdmin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="adm_id")
	private Integer admId;

	@Column(name="adm_usuario")
	private String admUsuario;

	public SegIncidenciasAdmin() {
	}

	public Integer getAdmId() {
		return this.admId;
	}

	public void setAdmId(Integer admId) {
		this.admId = admId;
	}

	public String getAdmUsuario() {
		return this.admUsuario;
	}

	public void setAdmUsuario(String admUsuario) {
		this.admUsuario = admUsuario;
	}

}