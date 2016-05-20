package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gen_catalogo_cab database table.
 * 
 */
@Entity
@Table(name="gen_catalogo_cab")
@NamedQuery(name="GenCatalogoCab.findAll", query="SELECT g FROM GenCatalogoCab g")
public class GenCatalogoCab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cat_codigo")
	private String catCodigo;

	@Column(name="cat_descripcion")
	private String catDescripcion;

	@Column(name="cat_nombre")
	private String catNombre;

	//bi-directional many-to-one association to GenCatalogoItemsDet
	@OneToMany(mappedBy="genCatalogoCab")
	private List<GenCatalogoItemsDet> genCatalogoItemsDets;

	public GenCatalogoCab() {
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

	public List<GenCatalogoItemsDet> getGenCatalogoItemsDets() {
		return this.genCatalogoItemsDets;
	}

	public void setGenCatalogoItemsDets(List<GenCatalogoItemsDet> genCatalogoItemsDets) {
		this.genCatalogoItemsDets = genCatalogoItemsDets;
	}

	public GenCatalogoItemsDet addGenCatalogoItemsDet(GenCatalogoItemsDet genCatalogoItemsDet) {
		getGenCatalogoItemsDets().add(genCatalogoItemsDet);
		genCatalogoItemsDet.setGenCatalogoCab(this);

		return genCatalogoItemsDet;
	}

	public GenCatalogoItemsDet removeGenCatalogoItemsDet(GenCatalogoItemsDet genCatalogoItemsDet) {
		getGenCatalogoItemsDets().remove(genCatalogoItemsDet);
		genCatalogoItemsDet.setGenCatalogoCab(null);

		return genCatalogoItemsDet;
	}

}