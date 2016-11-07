package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the gtr_administrador_contrato database table.
 * 
 */
@Entity
@Table(name="gtr_administrador_contrato")
@NamedQuery(name="GtrAdministradorContrato.findAll", query="SELECT g FROM GtrAdministradorContrato g")
public class GtrAdministradorContrato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="adc_id")
	private Integer adcId;

	@Column(name="adc_administrador")
	private String adcAdministrador;

	@Column(name="adc_direccion_adm")
	private String adcDireccionAdm;

	@Column(name="adc_estado", columnDefinition = "bpchar")
	private String adcEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="adc_fecha_fin")
	private Date adcFechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="adc_fecha_inicio")
	private Date adcFechaInicio;

	//bi-directional many-to-one association to GtrContratoAsignacion
	@ManyToOne
	@JoinColumn(name="cas_id")
	private GtrContratoAsignacion gtrContratoAsignacion;

	public GtrAdministradorContrato() {
	}

	public Integer getAdcId() {
		return this.adcId;
	}

	public void setAdcId(Integer adcId) {
		this.adcId = adcId;
	}

	public String getAdcAdministrador() {
		return this.adcAdministrador;
	}

	public void setAdcAdministrador(String adcAdministrador) {
		this.adcAdministrador = adcAdministrador;
	}

	public String getAdcDireccionAdm() {
		return this.adcDireccionAdm;
	}

	public void setAdcDireccionAdm(String adcDireccionAdm) {
		this.adcDireccionAdm = adcDireccionAdm;
	}

	public String getAdcEstado() {
		return this.adcEstado;
	}

	public void setAdcEstado(String adcEstado) {
		this.adcEstado = adcEstado;
	}

	public Date getAdcFechaFin() {
		return this.adcFechaFin;
	}

	public void setAdcFechaFin(Date adcFechaFin) {
		this.adcFechaFin = adcFechaFin;
	}

	public Date getAdcFechaInicio() {
		return this.adcFechaInicio;
	}

	public void setAdcFechaInicio(Date adcFechaInicio) {
		this.adcFechaInicio = adcFechaInicio;
	}

	public GtrContratoAsignacion getGtrContratoAsignacion() {
		return this.gtrContratoAsignacion;
	}

	public void setGtrContratoAsignacion(GtrContratoAsignacion gtrContratoAsignacion) {
		this.gtrContratoAsignacion = gtrContratoAsignacion;
	}

}