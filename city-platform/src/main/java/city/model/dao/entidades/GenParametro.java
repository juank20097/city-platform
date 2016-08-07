package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the gen_parametros database table.
 * 
 */
@Entity
@Table(name="gen_parametros")
@NamedQuery(name="GenParametro.findAll", query="SELECT g FROM GenParametro g")
public class GenParametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="par_id")
	private String parId;

	@Column(name="par_descripcion")
	private String parDescripcion;

	@Column(name="par_estado", columnDefinition="bpchar")
	private String parEstado;

	@Column(name="par_nombre")
	private String parNombre;

	@Column(name="par_padre")
	private String parPadre;

	@Column(name="par_valor")
	private String parValor;

	public GenParametro() {
	}

	public String getParId() {
		return this.parId;
	}

	public void setParId(String parId) {
		this.parId = parId;
	}

	public String getParDescripcion() {
		return this.parDescripcion;
	}

	public void setParDescripcion(String parDescripcion) {
		this.parDescripcion = parDescripcion;
	}

	public String getParEstado() {
		return this.parEstado;
	}

	public void setParEstado(String parEstado) {
		this.parEstado = parEstado;
	}

	public String getParNombre() {
		return this.parNombre;
	}

	public void setParNombre(String parNombre) {
		this.parNombre = parNombre;
	}

	public String getParPadre() {
		return this.parPadre;
	}

	public void setParPadre(String parPadre) {
		this.parPadre = parPadre;
	}

	public String getParValor() {
		return this.parValor;
	}

	public void setParValor(String parValor) {
		this.parValor = parValor;
	}

}