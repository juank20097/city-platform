package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gen_elementos_barrio database table.
 * 
 */
@Entity
@Table(name="gen_elementos_barrio")
@NamedQuery(name="GenElementosBarrio.findAll", query="SELECT g FROM GenElementosBarrio g")
public class GenElementosBarrio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="elb_id")
	private Integer elbId;

	@Column(name="elb_estado", columnDefinition = "bpchar", length=1)
	private String elbEstado;

	@Column(name="elb_nombre", length = 250)
	private String elbNombre;

	@Column(name="elb_tipo", length = 50)
	private String elbTipo;

	@Column(name="elb_unidad_medida", length = 50)
	private String elbUnidadMedida;

	//bi-directional many-to-one association to GenElementoBarrioValor
	@OneToMany(mappedBy="genElementosBarrio")
	private List<GenElementoBarrioValor> genElementoBarrioValors;

	public GenElementosBarrio() {
	}

	public Integer getElbId() {
		return this.elbId;
	}

	public void setElbId(Integer elbId) {
		this.elbId = elbId;
	}

	public String getElbEstado() {
		return this.elbEstado;
	}

	public void setElbEstado(String elbEstado) {
		this.elbEstado = elbEstado;
	}

	public String getElbNombre() {
		return this.elbNombre;
	}

	public void setElbNombre(String elbNombre) {
		this.elbNombre = elbNombre;
	}

	public String getElbTipo() {
		return this.elbTipo;
	}

	public void setElbTipo(String elbTipo) {
		this.elbTipo = elbTipo;
	}

	public String getElbUnidadMedida() {
		return this.elbUnidadMedida;
	}

	public void setElbUnidadMedida(String elbUnidadMedida) {
		this.elbUnidadMedida = elbUnidadMedida;
	}

	public List<GenElementoBarrioValor> getGenElementoBarrioValors() {
		return this.genElementoBarrioValors;
	}

	public void setGenElementoBarrioValors(List<GenElementoBarrioValor> genElementoBarrioValors) {
		this.genElementoBarrioValors = genElementoBarrioValors;
	}

	public GenElementoBarrioValor addGenElementoBarrioValor(GenElementoBarrioValor genElementoBarrioValor) {
		getGenElementoBarrioValors().add(genElementoBarrioValor);
		genElementoBarrioValor.setGenElementosBarrio(this);

		return genElementoBarrioValor;
	}

	public GenElementoBarrioValor removeGenElementoBarrioValor(GenElementoBarrioValor genElementoBarrioValor) {
		getGenElementoBarrioValors().remove(genElementoBarrioValor);
		genElementoBarrioValor.setGenElementosBarrio(null);

		return genElementoBarrioValor;
	}

}