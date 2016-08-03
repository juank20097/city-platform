package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the gen_salud database table.
 * 
 */
@Entity
@Table(name = "gen_salud")
@NamedQuery(name = "GenSalud.findAll", query = "SELECT g FROM GenSalud g")
public class GenSalud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "per_dni")
	private String perDni;

	@Column(name = "sld_alergias")
	private String sldAlergias;

	@Column(name = "sld_altura")
	private BigDecimal sldAltura;

	@Column(name = "sld_asegurado")
	private String sldAsegurado;

	@Column(name = "sld_carnet_conadies")
	private String sldCarnetConadies;

	@Column(name = "sld_consume_alcohol")
	private Boolean sldConsumeAlcohol;

	@Column(name = "sld_consume_tabaco")
	private Boolean sldConsumeTabaco;

	@Column(name = "sld_discapacidad_grado")
	private String sldDiscapacidadGrado;

	@Column(name = "sld_discapacidad_tipo")
	private String sldDiscapacidadTipo;

	@Column(name = "sld_frecuencia_consumo_medicame")
	private String sldFrecuenciaConsumoMedicame;

	@Column(name = "sld_grupo_sanguineo")
	private String sldGrupoSanguineo;

	@Column(name = "sld_medicamentos")
	private String sldMedicamentos;

	@Column(name = "sld_nivel_azucar")
	private String sldNivelAzucar;

	@Column(name = "sld_periodicidad_ejercicio")
	private String sldPeriodicidadEjercicio;

	@Column(name = "sld_peso")
	private BigDecimal sldPeso;

	@Column(name = "sld_presion")
	private String sldPresion;

	@Column(name = "sld_realiza_ejercicio")
	private Boolean sldRealizaEjercicio;

	@Column(name = "sld_vegetariano")
	private Boolean sldVegetariano;

	@Column(name="sld_alergias_cronicas2")
	private String sldAlergiasCronicas2;
	
	@Column(name="sld_embriagar")
	private Boolean sldEmbriagar;
	
	@Column(name="sld_madre_causa_muerte")
	private String sldMadreCausaMuerte;

	@Column(name="sld_madre_edad")
	private Integer sldMadreEdad;

	@Column(name="sld_madre_enfermedades_actuales")
	private String sldMadreEnfermedadesActuales;

	@Column(name="sld_madre_viva")
	private Boolean sldMadreViva;
	
	@Column(name="sld_nombre_lugar_centro_medico")
	private String sldNombreLugarCentroMedico;

	@Column(name="sld_observaciones")
	private String sldObservaciones;

	@Column(name="sld_padre_causa_muerte")
	private String sldPadreCausaMuerte;

	@Column(name="sld_padre_edad")
	private Integer sldPadreEdad;

	@Column(name="sld_padre_enfermedades_actuales")
	private String sldPadreEnfermedadesActuales;

	@Column(name="sld_padre_vivo")
	private Boolean sldPadreVivo;

	@Column(name="sld_periodicidad_alcohol")
	private String sldPeriodicidadAlcohol;
	
	@Column(name="sld_periodicidad_embriagar")
	private String sldPeriodicidadEmbriagar;

	@Column(name="sld_periodicidad_tabaco")
	private String sldPeriodicidadTabaco;

	// bi-directional one-to-one association to GenPersona
	@OneToOne
	@JoinColumn(name = "per_dni")
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

	public Boolean getSldConsumeAlcohol() {
		return this.sldConsumeAlcohol;
	}

	public void setSldConsumeAlcohol(Boolean sldConsumeAlcohol) {
		this.sldConsumeAlcohol = sldConsumeAlcohol;
	}

	public Boolean getSldConsumeTabaco() {
		return this.sldConsumeTabaco;
	}

	public void setSldConsumeTabaco(Boolean sldConsumeTabaco) {
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

	public String getSldFrecuenciaConsumoMedicame() {
		return sldFrecuenciaConsumoMedicame;
	}

	public void setSldFrecuenciaConsumoMedicame(
			String sldFrecuenciaConsumoMedicame) {
		this.sldFrecuenciaConsumoMedicame = sldFrecuenciaConsumoMedicame;
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

	public String getSldAlergiasCronicas2() {
		return this.sldAlergiasCronicas2;
	}

	public void setSldAlergiasCronicas2(String sldAlergiasCronicas2) {
		this.sldAlergiasCronicas2 = sldAlergiasCronicas2;
	}
	
	public Boolean getSldEmbriagar() {
		return this.sldEmbriagar;
	}

	public void setSldEmbriagar(Boolean sldEmbriagar) {
		this.sldEmbriagar = sldEmbriagar;
	}
	
	public String getSldMadreCausaMuerte() {
		return this.sldMadreCausaMuerte;
	}

	public void setSldMadreCausaMuerte(String sldMadreCausaMuerte) {
		this.sldMadreCausaMuerte = sldMadreCausaMuerte;
	}

	public Integer getSldMadreEdad() {
		return this.sldMadreEdad;
	}

	public void setSldMadreEdad(Integer sldMadreEdad) {
		this.sldMadreEdad = sldMadreEdad;
	}

	public String getSldMadreEnfermedadesActuales() {
		return this.sldMadreEnfermedadesActuales;
	}

	public void setSldMadreEnfermedadesActuales(String sldMadreEnfermedadesActuales) {
		this.sldMadreEnfermedadesActuales = sldMadreEnfermedadesActuales;
	}

	public Boolean getSldMadreViva() {
		return this.sldMadreViva;
	}

	public void setSldMadreViva(Boolean sldMadreViva) {
		this.sldMadreViva = sldMadreViva;
	}
	
	public String getSldNombreLugarCentroMedico() {
		return this.sldNombreLugarCentroMedico;
	}

	public void setSldNombreLugarCentroMedico(String sldNombreLugarCentroMedico) {
		this.sldNombreLugarCentroMedico = sldNombreLugarCentroMedico;
	}

	public String getSldObservaciones() {
		return this.sldObservaciones;
	}

	public void setSldObservaciones(String sldObservaciones) {
		this.sldObservaciones = sldObservaciones;
	}

	public String getSldPadreCausaMuerte() {
		return this.sldPadreCausaMuerte;
	}

	public void setSldPadreCausaMuerte(String sldPadreCausaMuerte) {
		this.sldPadreCausaMuerte = sldPadreCausaMuerte;
	}

	public Integer getSldPadreEdad() {
		return this.sldPadreEdad;
	}

	public void setSldPadreEdad(Integer sldPadreEdad) {
		this.sldPadreEdad = sldPadreEdad;
	}

	public String getSldPadreEnfermedadesActuales() {
		return this.sldPadreEnfermedadesActuales;
	}

	public void setSldPadreEnfermedadesActuales(String sldPadreEnfermedadesActuales) {
		this.sldPadreEnfermedadesActuales = sldPadreEnfermedadesActuales;
	}

	public Boolean getSldPadreVivo() {
		return this.sldPadreVivo;
	}

	public void setSldPadreVivo(Boolean sldPadreVivo) {
		this.sldPadreVivo = sldPadreVivo;
	}

	public String getSldPeriodicidadAlcohol() {
		return this.sldPeriodicidadAlcohol;
	}

	public void setSldPeriodicidadAlcohol(String sldPeriodicidadAlcohol) {
		this.sldPeriodicidadAlcohol = sldPeriodicidadAlcohol;
	}
	
	public String getSldPeriodicidadEmbriagar() {
		return this.sldPeriodicidadEmbriagar;
	}

	public void setSldPeriodicidadEmbriagar(String sldPeriodicidadEmbriagar) {
		this.sldPeriodicidadEmbriagar = sldPeriodicidadEmbriagar;
	}

	public String getSldPeriodicidadTabaco() {
		return this.sldPeriodicidadTabaco;
	}

	public void setSldPeriodicidadTabaco(String sldPeriodicidadTabaco) {
		this.sldPeriodicidadTabaco = sldPeriodicidadTabaco;
	}


}