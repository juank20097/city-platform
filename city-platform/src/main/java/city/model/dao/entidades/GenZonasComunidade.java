package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the gen_zonas_comunidades database table.
 * 
 */
@Entity
@Table(name="gen_zonas_comunidades")
@NamedQuery(name="GenZonasComunidade.findAll", query="SELECT g FROM GenZonasComunidade g")
public class GenZonasComunidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GenZonasComunidadePK id;

	//bi-directional many-to-one association to GenComunidade
	@ManyToOne
	@JoinColumn(name="com_id", insertable=false, updatable=false)
	private GenComunidade genComunidade;

	//bi-directional many-to-one association to GenZona
	@ManyToOne
	@JoinColumn(name="zon_id", insertable=false, updatable=false)
	private GenZona genZona;

	public GenZonasComunidade() {
	}

	public GenZonasComunidadePK getId() {
		return this.id;
	}

	public void setId(GenZonasComunidadePK id) {
		this.id = id;
	}

	public GenComunidade getGenComunidade() {
		return this.genComunidade;
	}

	public void setGenComunidade(GenComunidade genComunidade) {
		this.genComunidade = genComunidade;
	}

	public GenZona getGenZona() {
		return this.genZona;
	}

	public void setGenZona(GenZona genZona) {
		this.genZona = genZona;
	}

}