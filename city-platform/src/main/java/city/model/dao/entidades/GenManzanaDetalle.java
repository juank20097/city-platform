package city.model.dao.entidades;


import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the gen_manzana_detalles database table.
 * 
 */
@Entity
@Table(name="gen_manzana_detalles")
@NamedQuery(name="GenManzanaDetalle.findAll", query="SELECT g FROM GenManzanaDetalle g")
public class GenManzanaDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="det_id")
	private String detId;

	@Column(name="det_cantidad")
	private Integer detCantidad;

	@Column(name="det_descripcion")
	private String detDescripcion;

	@Column(name="det_nombre")
	private String detNombre;

	//bi-directional many-to-one association to GenManzana
	@ManyToOne
	@JoinColumn(name="man_id")
	private GenManzana genManzana;

	public GenManzanaDetalle() {
	}

	public String getDetId() {
		return this.detId;
	}

	public void setDetId(String detId) {
		this.detId = detId;
	}

	public Integer getDetCantidad() {
		return this.detCantidad;
	}

	public void setDetCantidad(Integer detCantidad) {
		this.detCantidad = detCantidad;
	}

	public String getDetDescripcion() {
		return this.detDescripcion;
	}

	public void setDetDescripcion(String detDescripcion) {
		this.detDescripcion = detDescripcion;
	}

	public String getDetNombre() {
		return this.detNombre;
	}

	public void setDetNombre(String detNombre) {
		this.detNombre = detNombre;
	}

	public GenManzana getGenManzana() {
		return this.genManzana;
	}

	public void setGenManzana(GenManzana genManzana) {
		this.genManzana = genManzana;
	}

}