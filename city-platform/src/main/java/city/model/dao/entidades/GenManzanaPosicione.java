package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the gen_manzana_posiciones database table.
 * 
 */
@Entity
@Table(name="gen_manzana_posiciones")
@NamedQuery(name="GenManzanaPosicione.findAll", query="SELECT g FROM GenManzanaPosicione g")
public class GenManzanaPosicione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pos_id")
	private Integer posId;

	@Column(name="pos_latitud")
	private double posLatitud;

	@Column(name="pos_longitud")
	private double posLongitud;

	//bi-directional many-to-one association to GenManzana
	@ManyToOne
	@JoinColumn(name="man_id")
	private GenManzana genManzana;

	public GenManzanaPosicione() {
	}

	public Integer getPosId() {
		return this.posId;
	}

	public void setPosId(Integer posId) {
		this.posId = posId;
	}

	public double getPosLatitud() {
		return this.posLatitud;
	}

	public void setPosLatitud(double posLatitud) {
		this.posLatitud = posLatitud;
	}

	public double getPosLongitud() {
		return this.posLongitud;
	}

	public void setPosLongitud(double posLongitud) {
		this.posLongitud = posLongitud;
	}

	public GenManzana getGenManzana() {
		return this.genManzana;
	}

	public void setGenManzana(GenManzana genManzana) {
		this.genManzana = genManzana;
	}

}