package city.model.dao.entidades;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;





/**
 * The persistent class for the gen_registro_excel database table.
 * 
 */
@Entity
@Table(name="gen_registro_excel")
@NamedQuery(name="GenRegistroExcel.findAll", query="SELECT g FROM GenRegistroExcel g")
public class GenRegistroExcel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="exc_id")
	private Integer excId;

	@Column(name="exc_actualizados")
	private Integer excActualizados;

	@Column(name="exc_errores")
	private Integer excErrores;

	@Column(name="exc_fecha")
	private Timestamp excFecha;

	@Column(name="exc_inactivos")
	private Integer excInactivos;

	@Column(name="exc_ip")
	private String excIp;

	@Column(name="exc_nombre_archivo")
	private String excNombreArchivo;

	@Column(name="exc_nuevos")
	private Integer excNuevos;

	@Column(name="exc_usuario")
	private String excUsuario;

	public GenRegistroExcel() {
	}

	public Integer getExcId() {
		return this.excId;
	}

	public void setExcId(Integer excId) {
		this.excId = excId;
	}

	public Integer getExcActualizados() {
		return this.excActualizados;
	}

	public void setExcActualizados(Integer excActualizados) {
		this.excActualizados = excActualizados;
	}

	public Integer getExcErrores() {
		return this.excErrores;
	}

	public void setExcErrores(Integer excErrores) {
		this.excErrores = excErrores;
	}

	public Timestamp getExcFecha() {
		return this.excFecha;
	}

	public void setExcFecha(Timestamp excFecha) {
		this.excFecha = excFecha;
	}

	public Integer getExcInactivos() {
		return this.excInactivos;
	}

	public void setExcInactivos(Integer excInactivos) {
		this.excInactivos = excInactivos;
	}

	public String getExcIp() {
		return this.excIp;
	}

	public void setExcIp(String excIp) {
		this.excIp = excIp;
	}

	public String getExcNombreArchivo() {
		return this.excNombreArchivo;
	}

	public void setExcNombreArchivo(String excNombreArchivo) {
		this.excNombreArchivo = excNombreArchivo;
	}

	public Integer getExcNuevos() {
		return this.excNuevos;
	}

	public void setExcNuevos(Integer excNuevos) {
		this.excNuevos = excNuevos;
	}

	public String getExcUsuario() {
		return this.excUsuario;
	}

	public void setExcUsuario(String excUsuario) {
		this.excUsuario = excUsuario;
	}

}