package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the aud_actividad_aplicacion database table.
 * 
 */
@Entity
@Table(name="aud_actividad_aplicacion")
@NamedQuery(name="AudActividadAplicacion.findAll", query="SELECT a FROM AudActividadAplicacion a")
public class AudActividadAplicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="apl_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer aplId;

	@Column(name="apl_aplicacion")
	private String aplAplicacion;

	@Column(name="apl_aplicacion_cliente")
	private String aplAplicacionCliente;

	@Column(name="apl_fecha")
	private Timestamp aplFecha;

	@Column(name="apl_ip")
	private String aplIp;

	@Column(name="apl_origen_archivo")
	private String aplOrigenArchivo;

	@Column(name="apl_sentencia")
	private String aplSentencia;

	@Column(name="apl_tabla")
	private String aplTabla;

	@Column(name="apl_tipo_operacion")
	private String aplTipoOperacion;

	@Column(name="apl_usuario_aplicacion")
	private String aplUsuarioAplicacion;

	public AudActividadAplicacion() {
	}

	public Integer getAplId() {
		return this.aplId;
	}

	public void setAplId(Integer aplId) {
		this.aplId = aplId;
	}

	public String getAplAplicacion() {
		return this.aplAplicacion;
	}

	public void setAplAplicacion(String aplAplicacion) {
		this.aplAplicacion = aplAplicacion;
	}

	public String getAplAplicacionCliente() {
		return this.aplAplicacionCliente;
	}

	public void setAplAplicacionCliente(String aplAplicacionCliente) {
		this.aplAplicacionCliente = aplAplicacionCliente;
	}

	public Timestamp getAplFecha() {
		return this.aplFecha;
	}

	public void setAplFecha(Timestamp aplFecha) {
		this.aplFecha = aplFecha;
	}

	public String getAplIp() {
		return this.aplIp;
	}

	public void setAplIp(String aplIp) {
		this.aplIp = aplIp;
	}

	public String getAplOrigenArchivo() {
		return this.aplOrigenArchivo;
	}

	public void setAplOrigenArchivo(String aplOrigenArchivo) {
		this.aplOrigenArchivo = aplOrigenArchivo;
	}

	public String getAplSentencia() {
		return this.aplSentencia;
	}

	public void setAplSentencia(String aplSentencia) {
		this.aplSentencia = aplSentencia;
	}

	public String getAplTabla() {
		return this.aplTabla;
	}

	public void setAplTabla(String aplTabla) {
		this.aplTabla = aplTabla;
	}

	public String getAplTipoOperacion() {
		return this.aplTipoOperacion;
	}

	public void setAplTipoOperacion(String aplTipoOperacion) {
		this.aplTipoOperacion = aplTipoOperacion;
	}

	public String getAplUsuarioAplicacion() {
		return this.aplUsuarioAplicacion;
	}

	public void setAplUsuarioAplicacion(String aplUsuarioAplicacion) {
		this.aplUsuarioAplicacion = aplUsuarioAplicacion;
	}

}