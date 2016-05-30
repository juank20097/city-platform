package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the gen_salud database table.
 * 
 */
@Entity
@Table(name="gen_salud")
@NamedQuery(name="GenSalud.findAll", query="SELECT g FROM GenSalud g")
public class GenSalud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="per_dni")
	private String perDni;

	@Column(name="sld_alergias")
	private String sldAlergias;

	@Column(name="sld_altura")
	private BigDecimal sldAltura;

	@Column(name="sld_asegurado")
	private String sldAsegurado;

	@Column(name="sld_carnet_conadies")
	private String sldCarnetConadies;

	@Column(name="sld_consume_alcohol")
	private String sldConsumeAlcohol;

	@Column(name="sld_consume_tabaco")
	private String sldConsumeTabaco;

	@Column(name="sld_discapacidad_grado")
	private String sldDiscapacidadGrado;

	@Column(name="sld_discapacidad_tipo")
	private String sldDiscapacidadTipo;

	@Column(name="sld_freciencia_consumo_medicame")
	private String sldFrecienciaConsumoMedicame;

	@Column(name="sld_grupo_sanguineo")
	private String sldGrupoSanguineo;

	@Column(name="sld_medicamentos")
	private String sldMedicamentos;

	@Column(name="sld_nivel_azucar")
	private String sldNivelAzucar;

	@Column(name="sld_periodicidad_ejercicio")
	private String sldPeriodicidadEjercicio;

	@Column(name="sld_peso")
	private BigDecimal sldPeso;

	@Column(name="sld_presion")
	private String sldPresion;

	@Column(name="sld_realiza_ejercicio")
	private Boolean sldRealizaEjercicio;

	@Column(name="sld_vegetariano")
	private Boolean sldVegetariano;

	//bi-directional one-to-one association to GenPersona
	@OneToOne
	@JoinColumn(name="per_dni")
	private GenPersona genPersona;

	public GenSalud() {
	}

	public String getPerDni() {
		return this.perDni;
	}

	public void setPerDni(String perDni) {
		this.perDni = perDni;
	}

	public String getSldAlergias() {
		return this.sldAlergias;
	}

	public void setSldAlergias(String sldAlergias) {
		this.sldAlergias = sldAlergias;
	}

	public BigDecimal getSldAltura() {
		return this.sldAltura;
	}

	public void setSldAltura(BigDecimal sldAltura) {
		this.sldAltura = sldAltura;
	}

	public String getSldAsegurado() {
		return this.sldAsegurado;
	}

	public void setSldAsegurado(String sldAsegurado) {
		this.sldAsegurado = sldAsegurado;
	}

	public String getSldCarnetConadies() {
		return this.sldCarnetConadies;
	}

	public void setSldCarnetConadies(String sldCarnetConadies) {
		this.sldCarnetConadies = sldCarnetConadies;
	}

	public String getSldConsumeAlcohol() {
		return this.sldConsumeAlcohol;
	}

	public void setSldConsumeAlcohol(String sldConsumeAlcohol) {
		this.sldConsumeAlcohol = sldConsumeAlcohol;
	}

	public String getSldConsumeTabaco() {
		return this.sldConsumeTabaco;
	}

	public void setSldConsumeTabaco(String sldConsumeTabaco) {
		this.sldConsumeTabaco = sldConsumeTabaco;
	}

	public String getSldDiscapacidadGrado() {
		return this.sldDiscapacidadGrado;
	}

	public void setSldDiscapacidadGrado(String sldDiscapacidadGrado) {
		this.sldDiscapacidadGrado = sldDiscapacidadGrado;
	}

	public String getSldDiscapacidadTipo() {
		return this.sldDiscapacidadTipo;
	}

	public void setSldDiscapacidadTipo(String sldDiscapacidadTipo) {
		this.sldDiscapacidadTipo = sldDiscapacidadTipo;
	}

	public String getSldFrecienciaConsumoMedicame() {
		return this.sldFrecienciaConsumoMedicame;
	}

	public void setSldFrecienciaConsumoMedicame(String sldFrecienciaConsumoMedicame) {
		this.sldFrecienciaConsumoMedicame = sldFrecienciaConsumoMedicame;
	}

	public String getSldGrupoSanguineo() {
		return this.sldGrupoSanguineo;
	}

	public void setSldGrupoSanguineo(String sldGrupoSanguineo) {
		this.sldGrupoSanguineo = sldGrupoSanguineo;
	}

	public String getSldMedicamentos() {
		return this.sldMedicamentos;
	}

	public void setSldMedicamentos(String sldMedicamentos) {
		this.sldMedicamentos = sldMedicamentos;
	}

	public String getSldNivelAzucar() {
		return this.sldNivelAzucar;
	}

	public void setSldNivelAzucar(String sldNivelAzucar) {
		this.sldNivelAzucar = sldNivelAzucar;
	}

	public String getSldPeriodicidadEjercicio() {
		return this.sldPeriodicidadEjercicio;
	}

	public void setSldPeriodicidadEjercicio(String sldPeriodicidadEjercicio) {
		this.sldPeriodicidadEjercicio = sldPeriodicidadEjercicio;
	}

	public BigDecimal getSldPeso() {
		return this.sldPeso;
	}

	public void setSldPeso(BigDecimal sldPeso) {
		this.sldPeso = sldPeso;
	}

	public String getSldPresion() {
		return this.sldPresion;
	}

	public void setSldPresion(String sldPresion) {
		this.sldPresion = sldPresion;
	}

	public Boolean getSldRealizaEjercicio() {
		return this.sldRealizaEjercicio;
	}

	public void setSldRealizaEjercicio(Boolean sldRealizaEjercicio) {
		this.sldRealizaEjercicio = sldRealizaEjercicio;
	}

	public Boolean getSldVegetariano() {
		return this.sldVegetariano;
	}

	public void setSldVegetariano(Boolean sldVegetariano) {
		this.sldVegetariano = sldVegetariano;
	}

	public GenPersona getGenPersona() {
		return this.genPersona;
	}

	public void setGenPersona(GenPersona genPersona) {
		this.genPersona = genPersona;
	}

}