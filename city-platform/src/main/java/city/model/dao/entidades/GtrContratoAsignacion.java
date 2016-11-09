package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the gtr_contrato_asignacion database table.
 * 
 */
@Entity
@Table(name="gtr_contrato_asignacion")
@NamedQuery(name="GtrContratoAsignacion.findAll", query="SELECT g FROM GtrContratoAsignacion g")
public class GtrContratoAsignacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cas_id")
	private String casId;

	@Column(name="cas_arrendador")
	private String casArrendador;

	@Column(name="cas_arrendatario")
	private String casArrendatario;

	@Column(name="cas_contrato")
	private String casContrato;

	@Column(name="cas_estado", columnDefinition = "bpchar")
	private String casEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="cas_fecha_doc_contrato")
	private Date casFechaDocContrato;

	@Temporal(TemporalType.DATE)
	@Column(name="cas_fecha_doc_pliego")
	private Date casFechaDocPliego;

	@Temporal(TemporalType.DATE)
	@Column(name="cas_fecha_doc_tdr")
	private Date casFechaDocTdr;

	@Column(name="cas_fecha_fin")
	private Timestamp casFechaFin;

	@Column(name="cas_fecha_inicio")
	private Timestamp casFechaInicio;

	@Column(name="cas_fecha_subida_contrato")
	private Timestamp casFechaSubidaContrato;

	@Column(name="cas_fecha_subida_pliego")
	private Timestamp casFechaSubidaPliego;

	@Column(name="cas_fecha_subida_tdr")
	private Timestamp casFechaSubidaTdr;

	@Column(name="cas_periodicidad_pago")
	private BigDecimal casPeriodicidadPago;

	@Column(name="cas_pliego")
	private String casPliego;

	@Column(name="cas_precio")
	private BigDecimal casPrecio;

	@Column(name="cas_tdr")
	private String casTdr;

	@Column(name="cas_tipo")
	private String casTipo;

	@Column(name="cas_unidad_tiempo")
	private String casUnidadTiempo;

	@Column(name="cas_usuario_contrato")
	private String casUsuarioContrato;

	@Column(name="cas_usuario_pliego")
	private String casUsuarioPliego;

	@Column(name="cas_usuario_tdr")
	private String casUsuarioTdr;

	//bi-directional many-to-one association to GtrAdministradorContrato
	@OneToMany(mappedBy="gtrContratoAsignacion")
	private List<GtrAdministradorContrato> gtrAdministradorContratos;

	//bi-directional many-to-one association to GtrAsignacionSuelo
	@ManyToOne
	@JoinColumn(name="sue_id")
	private GtrAsignacionSuelo gtrAsignacionSuelo;

	//bi-directional many-to-one association to GtrEntregablesContrato
	@OneToMany(mappedBy="gtrContratoAsignacion")
	private List<GtrEntregablesContrato> gtrEntregablesContratos;

	public GtrContratoAsignacion() {
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

	public String getCasContrato() {
		return this.casContrato;
	}

	public void setCasContrato(String casContrato) {
		this.casContrato = casContrato;
	}

	public String getCasEstado() {
		return this.casEstado;
	}

	public void setCasEstado(String casEstado) {
		this.casEstado = casEstado;
	}

	public Date getCasFechaDocContrato() {
		return this.casFechaDocContrato;
	}

	public void setCasFechaDocContrato(Date casFechaDocContrato) {
		this.casFechaDocContrato = casFechaDocContrato;
	}

	public Date getCasFechaDocPliego() {
		return this.casFechaDocPliego;
	}

	public void setCasFechaDocPliego(Date casFechaDocPliego) {
		this.casFechaDocPliego = casFechaDocPliego;
	}

	public Date getCasFechaDocTdr() {
		return this.casFechaDocTdr;
	}

	public void setCasFechaDocTdr(Date casFechaDocTdr) {
		this.casFechaDocTdr = casFechaDocTdr;
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

	public Timestamp getCasFechaSubidaContrato() {
		return this.casFechaSubidaContrato;
	}

	public void setCasFechaSubidaContrato(Timestamp casFechaSubidaContrato) {
		this.casFechaSubidaContrato = casFechaSubidaContrato;
	}

	public Timestamp getCasFechaSubidaPliego() {
		return this.casFechaSubidaPliego;
	}

	public void setCasFechaSubidaPliego(Timestamp casFechaSubidaPliego) {
		this.casFechaSubidaPliego = casFechaSubidaPliego;
	}

	public Timestamp getCasFechaSubidaTdr() {
		return this.casFechaSubidaTdr;
	}

	public void setCasFechaSubidaTdr(Timestamp casFechaSubidaTdr) {
		this.casFechaSubidaTdr = casFechaSubidaTdr;
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

	public BigDecimal getCasPrecio() {
		return this.casPrecio;
	}

	public void setCasPrecio(BigDecimal casPrecio) {
		this.casPrecio = casPrecio;
	}

	public String getCasTdr() {
		return this.casTdr;
	}

	public void setCasTdr(String casTdr) {
		this.casTdr = casTdr;
	}

	public String getCasTipo() {
		return this.casTipo;
	}

	public void setCasTipo(String casTipo) {
		this.casTipo = casTipo;
	}

	public String getCasUnidadTiempo() {
		return this.casUnidadTiempo;
	}

	public void setCasUnidadTiempo(String casUnidadTiempo) {
		this.casUnidadTiempo = casUnidadTiempo;
	}

	public String getCasUsuarioContrato() {
		return this.casUsuarioContrato;
	}

	public void setCasUsuarioContrato(String casUsuarioContrato) {
		this.casUsuarioContrato = casUsuarioContrato;
	}

	public String getCasUsuarioPliego() {
		return this.casUsuarioPliego;
	}

	public void setCasUsuarioPliego(String casUsuarioPliego) {
		this.casUsuarioPliego = casUsuarioPliego;
	}

	public String getCasUsuarioTdr() {
		return this.casUsuarioTdr;
	}

	public void setCasUsuarioTdr(String casUsuarioTdr) {
		this.casUsuarioTdr = casUsuarioTdr;
	}

	public List<GtrAdministradorContrato> getGtrAdministradorContratos() {
		return this.gtrAdministradorContratos;
	}

	public void setGtrAdministradorContratos(List<GtrAdministradorContrato> gtrAdministradorContratos) {
		this.gtrAdministradorContratos = gtrAdministradorContratos;
	}

	public GtrAdministradorContrato addGtrAdministradorContrato(GtrAdministradorContrato gtrAdministradorContrato) {
		getGtrAdministradorContratos().add(gtrAdministradorContrato);
		gtrAdministradorContrato.setGtrContratoAsignacion(this);

		return gtrAdministradorContrato;
	}

	public GtrAdministradorContrato removeGtrAdministradorContrato(GtrAdministradorContrato gtrAdministradorContrato) {
		getGtrAdministradorContratos().remove(gtrAdministradorContrato);
		gtrAdministradorContrato.setGtrContratoAsignacion(null);

		return gtrAdministradorContrato;
	}

	public GtrAsignacionSuelo getGtrAsignacionSuelo() {
		return this.gtrAsignacionSuelo;
	}

	public void setGtrAsignacionSuelo(GtrAsignacionSuelo gtrAsignacionSuelo) {
		this.gtrAsignacionSuelo = gtrAsignacionSuelo;
	}

	public List<GtrEntregablesContrato> getGtrEntregablesContratos() {
		return this.gtrEntregablesContratos;
	}

	public void setGtrEntregablesContratos(List<GtrEntregablesContrato> gtrEntregablesContratos) {
		this.gtrEntregablesContratos = gtrEntregablesContratos;
	}

	public GtrEntregablesContrato addGtrEntregablesContrato(GtrEntregablesContrato gtrEntregablesContrato) {
		getGtrEntregablesContratos().add(gtrEntregablesContrato);
		gtrEntregablesContrato.setGtrContratoAsignacion(this);

		return gtrEntregablesContrato;
	}

	public GtrEntregablesContrato removeGtrEntregablesContrato(GtrEntregablesContrato gtrEntregablesContrato) {
		getGtrEntregablesContratos().remove(gtrEntregablesContrato);
		gtrEntregablesContrato.setGtrContratoAsignacion(null);

		return gtrEntregablesContrato;
	}

}