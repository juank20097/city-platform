package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gen_catalogo database table.
 * 
 */
@Entity
@Table(name="gen_catalogo")
@NamedQuery(name="GenCatalogo.findAll", query="SELECT g FROM GenCatalogo g")
public class GenCatalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cat_codigo")
	private String catCodigo;

	@Column(name="cat_descripcion")
	private String catDescripcion;

	@Column(name="cat_nombre")
	private String catNombre;

	//bi-directional many-to-one association to GenCatalogoItem
	@OneToMany(mappedBy="genCatalogo")
	private List<GenCatalogoItem> genCatalogoItems;

	public GenCatalogo() {
	}

	public String getCatCodigo() {
		return this.catCodigo;
	}

	public void setCatCodigo(String catCodigo) {
		this.catCodigo = catCodigo;
	}

	public String getCatDescripcion() {
		return this.catDescripcion;
	}

	public void setCatDescripcion(String catDescripcion) {
		this.catDescripcion = catDescripcion;
	}

	public String getCatNombre() {
		return this.catNombre;
	}

	public void setCatNombre(String catNombre) {
		this.catNombre = catNombre;
	}

	public List<GenCatalogoItem> getGenCatalogoItems() {
		return this.genCatalogoItems;
	}

	public void setGenCatalogoItems(List<GenCatalogoItem> genCatalogoItems) {
		this.genCatalogoItems = genCatalogoItems;
	}

	public GenCatalogoItem addGenCatalogoItem(GenCatalogoItem genCatalogoItem) {
		getGenCatalogoItems().add(genCatalogoItem);
		genCatalogoItem.setGenCatalogo(this);

		return genCatalogoItem;
	}

	public GenCatalogoItem removeGenCatalogoItem(GenCatalogoItem genCatalogoItem) {
		getGenCatalogoItems().remove(genCatalogoItem);
		genCatalogoItem.setGenCatalogo(null);

		return genCatalogoItem;
	}

}