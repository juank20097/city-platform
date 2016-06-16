package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the aud_actividad_interna database table.
 * 
 */
@Entity
@Table(name="aud_actividad_interna")
@NamedQuery(name="AudActividadInterna.findAll", query="SELECT a FROM AudActividadInterna a")
public class AudActividadInterna implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="aud_id")
	private Long audId;

	@Temporal(TemporalType.DATE)
	@Column(name="aud_fecha_registro")
	private Date audFechaRegistro;

	@Column(name="aud_ip")
	private String audIp;

	@Column(name="aud_sql")
	private String audSql;

	@Column(name="aud_usuario")
	private String audUsuario;

	public AudActividadInterna() {
	}

	public Long getAudId() {
		return this.audId;
	}

	public void setAudId(Long audId) {
		this.audId = audId;
	}

	public Date getAudFechaRegistro() {
		return this.audFechaRegistro;
	}

	public void setAudFechaRegistro(Date audFechaRegistro) {
		this.audFechaRegistro = audFechaRegistro;
	}

	public String getAudIp() {
		return this.audIp;
	}

	public void setAudIp(String audIp) {
		this.audIp = audIp;
	}

	public String getAudSql() {
		return this.audSql;
	}

	public void setAudSql(String audSql) {
		this.audSql = audSql;
	}

	public String getAudUsuario() {
		return this.audUsuario;
	}

	public void setAudUsuario(String audUsuario) {
		this.audUsuario = audUsuario;
	}

}