package city.model.dao.entidades;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gen_manzanas database table.
 * 
 */
@Entity
@Table(name="gen_manzanas")
@NamedQuery(name="GenManzana.findAll", query="SELECT g FROM GenManzana g")
public class GenManzana implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="man_id")
	private String manId;

	@Column(name="man_nombre")
	private String manNombre;

	//bi-directional many-to-one association to GenManzanaDetalle
	@OneToMany(mappedBy="genManzana")
	private List<GenManzanaDetalle> genManzanaDetalles;

	//bi-directional many-to-one association to GenManzanaPosicione
	@OneToMany(mappedBy="genManzana")
	private List<GenManzanaPosicione> genManzanaPosiciones;

	//bi-directional many-to-one association to GenBarrio
	@ManyToOne
	@JoinColumn(name="bar_id")
	private GenBarrio genBarrio;

	public GenManzana() {
	}

	public String getManId() {
		return this.manId;
	}

	public void setManId(String manId) {
		this.manId = manId;
	}

	public String getManNombre() {
		return this.manNombre;
	}

	public void setManNombre(String manNombre) {
		this.manNombre = manNombre;
	}

	public List<GenManzanaDetalle> getGenManzanaDetalles() {
		return this.genManzanaDetalles;
	}

	public void setGenManzanaDetalles(List<GenManzanaDetalle> genManzanaDetalles) {
		this.genManzanaDetalles = genManzanaDetalles;
	}

	public GenManzanaDetalle addGenManzanaDetalle(GenManzanaDetalle genManzanaDetalle) {
		getGenManzanaDetalles().add(genManzanaDetalle);
		genManzanaDetalle.setGenManzana(this);

		return genManzanaDetalle;
	}

	public GenManzanaDetalle removeGenManzanaDetalle(GenManzanaDetalle genManzanaDetalle) {
		getGenManzanaDetalles().remove(genManzanaDetalle);
		genManzanaDetalle.setGenManzana(null);

		return genManzanaDetalle;
	}

	public List<GenManzanaPosicione> getGenManzanaPosiciones() {
		return this.genManzanaPosiciones;
	}

	public void setGenManzanaPosiciones(List<GenManzanaPosicione> genManzanaPosiciones) {
		this.genManzanaPosiciones = genManzanaPosiciones;
	}

	public GenManzanaPosicione addGenManzanaPosicione(GenManzanaPosicione genManzanaPosicione) {
		getGenManzanaPosiciones().add(genManzanaPosicione);
		genManzanaPosicione.setGenManzana(this);

		return genManzanaPosicione;
	}

	public GenManzanaPosicione removeGenManzanaPosicione(GenManzanaPosicione genManzanaPosicione) {
		getGenManzanaPosiciones().remove(genManzanaPosicione);
		genManzanaPosicione.setGenManzana(null);

		return genManzanaPosicione;
	}

	public GenBarrio getGenBarrio() {
		return this.genBarrio;
	}

	public void setGenBarrio(GenBarrio genBarrio) {
		this.genBarrio = genBarrio;
	}

}