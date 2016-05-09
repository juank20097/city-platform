package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the gen_item database table.
 * 
 */
@Entity
@Table(name="gen_item")
@NamedQuery(name="GenItem.findAll", query="SELECT g FROM GenItem g")
public class GenItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ite_codigo")
	private String iteCodigo;

	@Column(name="ite_nombre")
	private String iteNombre;

	@Column(name="ite_padre")
	private String itePadre;

	//bi-directional many-to-one association to GenCatalogo
	@ManyToOne
	@JoinColumn(name="cat_codigo")
	private GenCatalogo genCatalogo;

	public GenItem() {
	}

	public String getIteCodigo() {
		return this.iteCodigo;
	}

	public void setIteCodigo(String iteCodigo) {
		this.iteCodigo = iteCodigo;
	}

	public String getIteNombre() {
		return this.iteNombre;
	}

	public void setIteNombre(String iteNombre) {
		this.iteNombre = iteNombre;
	}

	public String getItePadre() {
		return this.itePadre;
	}

	public void setItePadre(String itePadre) {
		this.itePadre = itePadre;
	}

	public GenCatalogo getGenCatalogo() {
		return this.genCatalogo;
	}

	public void setGenCatalogo(GenCatalogo genCatalogo) {
		this.genCatalogo = genCatalogo;
	}

}