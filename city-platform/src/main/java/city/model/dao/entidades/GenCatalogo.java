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

	//bi-directional many-to-one association to GenItem
	@OneToMany(mappedBy="genCatalogo")
	private List<GenItem> genItems;

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

	public List<GenItem> getGenItems() {
		return this.genItems;
	}

	public void setGenItems(List<GenItem> genItems) {
		this.genItems = genItems;
	}

	public GenItem addGenItem(GenItem genItem) {
		getGenItems().add(genItem);
		genItem.setGenCatalogo(this);

		return genItem;
	}

	public GenItem removeGenItem(GenItem genItem) {
		getGenItems().remove(genItem);
		genItem.setGenCatalogo(null);

		return genItem;
	}

}