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

	@Id
	@Column(name="zc_id")
	private String zcId;

	//bi-directional many-to-one association to GenComunidade
	@ManyToOne
	@JoinColumn(name="com_id")
	private GenComunidade genComunidade;

	//bi-directional many-to-one association to GenZona
	@ManyToOne
	@JoinColumn(name="zon_id")
	private GenZona genZona;

	public GenZonasComunidade() {
	}

	public String getZcId() {
		return this.zcId;
	}

	public void setZcId(String zcId) {
		this.zcId = zcId;
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