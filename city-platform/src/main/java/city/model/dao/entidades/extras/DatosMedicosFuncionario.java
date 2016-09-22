package city.model.dao.entidades.extras;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DatosMedicosFuncionario {

	public String perApellidos;
	public String perNombres;
	public String perDni;
	public String perFechaNacimiento;
	public String perGenero;
	public String perEstadoCivil;
	public String residencia;
	public String pdeDireccion;
	public String pdeResidencia;
	public String pdeEstadiaDias;
	public String pdeEstadiaHoras;
	public String sldDeguroIess;
	public String sldSeguroPrivado;
	public String nombreSeguroPrivado;
	public String sldGrupoSanguineo;
	public String sldDiscapacidad;
	public String sldCarnetConadies;
	public String sldDiscapacidadTipo;
	public String sldDiscapacidadGrado;
	public String sldRealizaEjercicio;
	public String sldPeriodicidadEjercicio;
	public String sldEjercicioHoras;
	public String sldConsumeAlcohol;
	public String sldPeriodicidadAlcohol;
	public String sldEmbriagar;
	public String sldPeriodicidadEmbriagar;
	public String sldConsumeTabaco;
	public String sldPeriodicidadTabaco;
	public String sldTabacoSemana;
	public String sldPadreEnfermedadesActuales;
	public String sldPadreFallecio;
	public String sldPadreEdad;
	public String sldMadreEnfermedadesActuales;
	public String sldMadreFallecio;
	public String sldMadreEdad;
	public String sldAlergias;
	public String sldMedicamentos;
	public String sldAlergiasCronicas2;
	public String sldMedicamentosCronicos2;
	public String sldAlergiasCronicas3;
	public String sldMedicamentosCronicos3;
	public String sldFrecuenciaConsumoMedicame;
	public String sldNombreLugarCentroMedico;
	public String sldObservaciones;
	public String foaNivelInstruccion;

	
	
	public String getPerDni() {
		return perDni;
	}

	public void setPerDni(String perDni) {
		this.perDni = perDni;
	}

	public String getPerApellidos() {
		return perApellidos;
	}

	public void setPerApellidos(String perApellidos) {
		this.perApellidos = perApellidos;
	}

	public String getPerNombres() {
		return perNombres;
	}

	public void setPerNombres(String perNombres) {
		this.perNombres = perNombres;
	}

	public String getPerFechaNacimiento() {
		return perFechaNacimiento;
	}

	public void setPerFechaNacimiento(String perFechaNacimiento) {
		this.perFechaNacimiento = perFechaNacimiento;
	}

	public String getPerGenero() {
		return perGenero;
	}

	public void setPerGenero(String perGenero) {
		this.perGenero = perGenero;
	}

	public String getPerEstadoCivil() {
		return perEstadoCivil;
	}

	public void setPerEstadoCivil(String perEstadoCivil) {
		this.perEstadoCivil = perEstadoCivil;
	}

	public String getResidencia() {
		return residencia;
	}

	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}

	public String getPdeDireccion() {
		return pdeDireccion;
	}

	public void setPdeDireccion(String pdeDireccion) {
		this.pdeDireccion = pdeDireccion;
	}

	public String getPdeResidencia() {
		return pdeResidencia;
	}

	public void setPdeResidencia(String pdeResidencia) {
		this.pdeResidencia = pdeResidencia;
	}

	public String getPdeEstadiaDias() {
		return pdeEstadiaDias;
	}

	public void setPdeEstadiaDias(String pdeEstadiaDias) {
		this.pdeEstadiaDias = pdeEstadiaDias;
	}

	public String getPdeEstadiaHoras() {
		return pdeEstadiaHoras;
	}

	public void setPdeEstadiaHoras(String pdeEstadiaHoras) {
		this.pdeEstadiaHoras = pdeEstadiaHoras;
	}

	public String getSldDeguroIess() {
		return sldDeguroIess;
	}

	public void setSldDeguroIess(String sldDeguroIess) {
		this.sldDeguroIess = sldDeguroIess;
	}

	public String getSldSeguroPrivado() {
		return sldSeguroPrivado;
	}

	public void setSldSeguroPrivado(String sldSeguroPrivado) {
		this.sldSeguroPrivado = sldSeguroPrivado;
	}

	public String getNombreSeguroPrivado() {
		return nombreSeguroPrivado;
	}

	public void setNombreSeguroPrivado(String nombreSeguroPrivado) {
		this.nombreSeguroPrivado = nombreSeguroPrivado;
	}

	public String getSldGrupoSanguineo() {
		return sldGrupoSanguineo;
	}

	public void setSldGrupoSanguineo(String sldGrupoSanguineo) {
		this.sldGrupoSanguineo = sldGrupoSanguineo;
	}

	public String getSldDiscapacidad() {
		return sldDiscapacidad;
	}

	public void setSldDiscapacidad(String sldDiscapacidad) {
		this.sldDiscapacidad = sldDiscapacidad;
	}

	public String getSldCarnetConadies() {
		return sldCarnetConadies;
	}

	public void setSldCarnetConadies(String sldCarnetConadies) {
		this.sldCarnetConadies = sldCarnetConadies;
	}

	public String getSldDiscapacidadTipo() {
		return sldDiscapacidadTipo;
	}

	public void setSldDiscapacidadTipo(String sldDiscapacidadTipo) {
		this.sldDiscapacidadTipo = sldDiscapacidadTipo;
	}

	public String getSldDiscapacidadGrado() {
		return sldDiscapacidadGrado;
	}

	public void setSldDiscapacidadGrado(String sldDiscapacidadGrado) {
		this.sldDiscapacidadGrado = sldDiscapacidadGrado;
	}

	public String getSldRealizaEjercicio() {
		return sldRealizaEjercicio;
	}

	public void setSldRealizaEjercicio(String sldRealizaEjercicio) {
		this.sldRealizaEjercicio = sldRealizaEjercicio;
	}

	public String getSldPeriodicidadEjercicio() {
		return sldPeriodicidadEjercicio;
	}

	public void setSldPeriodicidadEjercicio(String sldPeriodicidadEjercicio) {
		this.sldPeriodicidadEjercicio = sldPeriodicidadEjercicio;
	}

	public String getSldEjercicioHoras() {
		return sldEjercicioHoras;
	}

	public void setSldEjercicioHoras(String sldEjercicioHoras) {
		this.sldEjercicioHoras = sldEjercicioHoras;
	}

	public String getSldConsumeAlcohol() {
		return sldConsumeAlcohol;
	}

	public void setSldConsumeAlcohol(String sldConsumeAlcohol) {
		this.sldConsumeAlcohol = sldConsumeAlcohol;
	}

	public String getSldPeriodicidadAlcohol() {
		return sldPeriodicidadAlcohol;
	}

	public void setSldPeriodicidadAlcohol(String sldPeriodicidadAlcohol) {
		this.sldPeriodicidadAlcohol = sldPeriodicidadAlcohol;
	}

	public String getSldEmbriagar() {
		return sldEmbriagar;
	}

	public void setSldEmbriagar(String sldEmbriagar) {
		this.sldEmbriagar = sldEmbriagar;
	}

	public String getSldPeriodicidadEmbriagar() {
		return sldPeriodicidadEmbriagar;
	}

	public void setSldPeriodicidadEmbriagar(String sldPeriodicidadEmbriagar) {
		this.sldPeriodicidadEmbriagar = sldPeriodicidadEmbriagar;
	}

	public String getSldConsumeTabaco() {
		return sldConsumeTabaco;
	}

	public void setSldConsumeTabaco(String sldConsumeTabaco) {
		this.sldConsumeTabaco = sldConsumeTabaco;
	}

	public String getSldPeriodicidadTabaco() {
		return sldPeriodicidadTabaco;
	}

	public void setSldPeriodicidadTabaco(String sldPeriodicidadTabaco) {
		this.sldPeriodicidadTabaco = sldPeriodicidadTabaco;
	}

	public String getSldTabacoSemana() {
		return sldTabacoSemana;
	}

	public void setSldTabacoSemana(String sldTabacoSemana) {
		this.sldTabacoSemana = sldTabacoSemana;
	}

	public String getSldPadreEnfermedadesActuales() {
		return sldPadreEnfermedadesActuales;
	}

	public void setSldPadreEnfermedadesActuales(String sldPadreEnfermedadesActuales) {
		this.sldPadreEnfermedadesActuales = sldPadreEnfermedadesActuales;
	}

	public String getSldPadreFallecio() {
		return sldPadreFallecio;
	}

	public void setSldPadreFallecio(String sldPadreFallecio) {
		this.sldPadreFallecio = sldPadreFallecio;
	}

	public String getSldPadreEdad() {
		return sldPadreEdad;
	}

	public void setSldPadreEdad(String sldPadreEdad) {
		this.sldPadreEdad = sldPadreEdad;
	}

	public String getSldMadreEnfermedadesActuales() {
		return sldMadreEnfermedadesActuales;
	}

	public void setSldMadreEnfermedadesActuales(String sldMadreEnfermedadesActuales) {
		this.sldMadreEnfermedadesActuales = sldMadreEnfermedadesActuales;
	}

	public String getSldMadreFallecio() {
		return sldMadreFallecio;
	}

	public void setSldMadreFallecio(String sldMadreFallecio) {
		this.sldMadreFallecio = sldMadreFallecio;
	}

	public String getSldMadreEdad() {
		return sldMadreEdad;
	}

	public void setSldMadreEdad(String sldMadreEdad) {
		this.sldMadreEdad = sldMadreEdad;
	}

	public String getSldAlergias() {
		return sldAlergias;
	}

	public void setSldAlergias(String sldAlergias) {
		this.sldAlergias = sldAlergias;
	}

	public String getSldMedicamentos() {
		return sldMedicamentos;
	}

	public void setSldMedicamentos(String sldMedicamentos) {
		this.sldMedicamentos = sldMedicamentos;
	}

	public String getSldAlergiasCronicas2() {
		return sldAlergiasCronicas2;
	}

	public void setSldAlergiasCronicas2(String sldAlergiasCronicas2) {
		this.sldAlergiasCronicas2 = sldAlergiasCronicas2;
	}

	public String getSldMedicamentosCronicos2() {
		return sldMedicamentosCronicos2;
	}

	public void setSldMedicamentosCronicos2(String sldMedicamentosCronicos2) {
		this.sldMedicamentosCronicos2 = sldMedicamentosCronicos2;
	}

	public String getSldAlergiasCronicas3() {
		return sldAlergiasCronicas3;
	}

	public void setSldAlergiasCronicas3(String sldAlergiasCronicas3) {
		this.sldAlergiasCronicas3 = sldAlergiasCronicas3;
	}

	public String getSldMedicamentosCronicos3() {
		return sldMedicamentosCronicos3;
	}

	public void setSldMedicamentosCronicos3(String sldMedicamentosCronicos3) {
		this.sldMedicamentosCronicos3 = sldMedicamentosCronicos3;
	}

	public String getSldFrecuenciaConsumoMedicame() {
		return sldFrecuenciaConsumoMedicame;
	}

	public void setSldFrecuenciaConsumoMedicame(String sldFrecuenciaConsumoMedicame) {
		this.sldFrecuenciaConsumoMedicame = sldFrecuenciaConsumoMedicame;
	}

	public String getSldNombreLugarCentroMedico() {
		return sldNombreLugarCentroMedico;
	}

	public void setSldNombreLugarCentroMedico(String sldNombreLugarCentroMedico) {
		this.sldNombreLugarCentroMedico = sldNombreLugarCentroMedico;
	}

	public String getSldObservaciones() {
		return sldObservaciones;
	}

	public void setSldObservaciones(String sldObservaciones) {
		this.sldObservaciones = sldObservaciones;
	}

	public String getFoaNivelInstruccion() {
		return foaNivelInstruccion;
	}

	public void setFoaNivelInstruccion(String foaNivelInstruccion) {
		this.foaNivelInstruccion = foaNivelInstruccion;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getName());
		sb.append(": ");
		for (Field f : getClass().getDeclaredFields()) {
			sb.append(f.getName());
			sb.append("=");
			try {
				sb.append(f.get(this));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sb.append(", ");
		}
		return sb.toString();
	}

}
