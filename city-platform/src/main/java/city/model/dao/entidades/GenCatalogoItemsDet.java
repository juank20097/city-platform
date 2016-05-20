package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the gen_catalogo_items_det database table.
 * 
 */
@Entity
@Table(name="gen_catalogo_items_det")
@NamedQuery(name="GenCatalogoItemsDet.findAll", query="SELECT g FROM GenCatalogoItemsDet g")
public class GenCatalogoItemsDet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ite_codigo")
	private String iteCodigo;

	@Column(name="ite_clasificacion")
	private String iteClasificacion;

	@Column(name="ite_nombre")
	private String iteNombre;

	@Column(name="ite_padre")
	private String itePadre;

	//bi-directional many-to-one association to GenCatalogoCab
	@ManyToOne
	@JoinColumn(name="cat_codigo")
	private GenCatalogoCab genCatalogoCab;

	public GenCatalogoItemsDet() {
	}

	public String getIteCodigo() {
		return this.iteCodigo;
	}

	public void setIteCodigo(String iteCodigo) {
		this.iteCodigo = iteCodigo;
	}

	public String getIteClasificacion() {
		return this.iteClasificacion;
	}

	public void setIteClasificacion(String iteClasificacion) {
		this.iteClasificacion = iteClasificacion;
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

	public GenCatalogoCab getGenCatalogoCab() {
		return this.genCatalogoCab;
	}

	public void setGenCatalogoCab(GenCatalogoCab genCatalogoCab) {
		this.genCatalogoCab = genCatalogoCab;
	}

}