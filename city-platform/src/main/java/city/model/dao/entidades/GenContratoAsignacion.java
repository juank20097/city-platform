package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the gen_contrato_asignacion database table.
 * 
 */
@Entity
@Table(name="gen_contrato_asignacion")
@NamedQuery(name="GenContratoAsignacion.findAll", query="SELECT g FROM GenContratoAsignacion g")
public class GenContratoAsignacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cas_id")
	private String casId;

	@Column(name="cas_arrendador")
	private String casArrendador;

	@Column(name="cas_arrendatario")
	private String casArrendatario;

	@Column(name="cas_fecha_fin")
	private Timestamp casFechaFin;

	@Column(name="cas_fecha_inicio")
	private Timestamp casFechaInicio;

	@Column(name="cas_periodicidad_pago")
	private BigDecimal casPeriodicidadPago;

	@Column(name="cas_pliego")
	private String casPliego;

	@Column(name="cas_tdr")
	private String casTdr;

	@Column(name="cas_unidad_tiempo")
	private String casUnidadTiempo;
	
	@Column(name="cas_precio")
	private BigDecimal casPrecio;

	//bi-directional many-to-one association to GenAsignacionSuelo
	@ManyToOne
	@JoinColumn(name="sue_id")
	private GenAsignacionSuelo genAsignacionSuelo;

	//bi-directional many-to-one association to GenEntregablesContrato
	@OneToMany(mappedBy="genContratoAsignacion")
	private List<GenEntregablesContrato> genEntregablesContratos;

	public GenContratoAsignacion() {
	}

	public String getCasId() {
		return this.casId;
	}

	public void setCasId(String casId) {
		this.casId = casId;
	}

	public String getCasArrendador() {
		return this.casArrendador;
	}

	public void setCasArrendador(String casArrendador) {
		this.casArrendador = casArrendador;
	}

	public String getCasArrendatario() {
		return this.casArrendatario;
	}

	public void setCasArrendatario(String casArrendatario) {
		this.casArrendatario = casArrendatario;
	}

	public Timestamp getCasFechaFin() {
		return this.casFechaFin;
	}

	public void setCasFechaFin(Timestamp casFechaFin) {
		this.casFechaFin = casFechaFin;
	}

	public Timestamp getCasFechaInicio() {
		return this.casFechaInicio;
	}

	public void setCasFechaInicio(Timestamp casFechaInicio) {
		this.casFechaInicio = casFechaInicio;
	}

	public BigDecimal getCasPeriodicidadPago() {
		return this.casPeriodicidadPago;
	}

	public void setCasPeriodicidadPago(BigDecimal casPeriodicidadPago) {
		this.casPeriodicidadPago = casPeriodicidadPago;
	}

	public String getCasPliego() {
		return this.casPliego;
	}

	public void setCasPliego(String casPliego) {
		this.casPliego = casPliego;
	}

	public String getCasTdr() {
		return this.casTdr;
	}

	public void setCasTdr(String casTdr) {
		this.casTdr = casTdr;
	}

	public String getCasUnidadTiempo() {
		return this.casUnidadTiempo;
	}

	public void setCasUnidadTiempo(String casUnidadTiempo) {
		this.casUnidadTiempo = casUnidadTiempo;
	}

	public GenAsignacionSuelo getGenAsignacionSuelo() {
		return this.genAsignacionSuelo;
	}

	public void setGenAsignacionSuelo(GenAsignacionSuelo genAsignacionSuelo) {
		this.genAsignacionSuelo = genAsignacionSuelo;
	}

	public List<GenEntregablesContrato> getGenEntregablesContratos() {
		return this.genEntregablesContratos;
	}

	public void setGenEntregablesContratos(List<GenEntregablesContrato> genEntregablesContratos) {
		this.genEntregablesContratos = genEntregablesContratos;
	}

	public BigDecimal getCasPrecio() {
		return casPrecio;
	}
	
	public void setCasPrecio(BigDecimal casPrecio) {
		this.casPrecio = casPrecio;
	}
	
	public GenEntregablesContrato addGenEntregablesContrato(GenEntregablesContrato genEntregablesContrato) {
		getGenEntregablesContratos().add(genEntregablesContrato);
		genEntregablesContrato.setGenContratoAsignacion(this);

		return genEntregablesContrato;
	}

	public GenEntregablesContrato removeGenEntregablesContrato(GenEntregablesContrato genEntregablesContrato) {
		getGenEntregablesContratos().remove(genEntregablesContrato);
		genEntregablesContrato.setGenContratoAsignacion(null);

		return genEntregablesContrato;
	}

}