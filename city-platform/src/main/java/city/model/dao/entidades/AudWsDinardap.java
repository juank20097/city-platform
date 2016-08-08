package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the aud_ws_dinardap database table.
 * 
 */
@Entity
@Table(name="aud_ws_dinardap")
@NamedQuery(name="AudWsDinardap.findAll", query="SELECT a FROM AudWsDinardap a")
public class AudWsDinardap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="wsd_id")
	private Integer wsdId;

	@Column(name="wsd_fecha")
	private Timestamp wsdFecha;

	@Column(name="wsd_paquete")
	private String wsdPaquete;

	@Column(name="wsd_respuesta")
	private String wsdRespuesta;

	@Column(name="wsd_usuario")
	private String wsdUsuario;

	public AudWsDinardap() {
	}

	public Integer getWsdId() {
		return this.wsdId;
	}

	public void setWsdId(Integer wsdId) {
		this.wsdId = wsdId;
	}

	public Timestamp getWsdFecha() {
		return this.wsdFecha;
	}

	public void setWsdFecha(Timestamp wsdFecha) {
		this.wsdFecha = wsdFecha;
	}

	public String getWsdPaquete() {
		return this.wsdPaquete;
	}

	public void setWsdPaquete(String wsdPaquete) {
		this.wsdPaquete = wsdPaquete;
	}

	public String getWsdRespuesta() {
		return this.wsdRespuesta;
	}

	public void setWsdRespuesta(String wsdRespuesta) {
		this.wsdRespuesta = wsdRespuesta;
	}

	public String getWsdUsuario() {
		return this.wsdUsuario;
	}

	public void setWsdUsuario(String wsdUsuario) {
		this.wsdUsuario = wsdUsuario;
	}

}