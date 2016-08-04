package city.controller.persona;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenPersona;
import city.model.dao.entidades.GenPersonaDetalle;
import city.model.dao.entidades.GenSalud;
import city.model.generic.Funciones;
import city.model.generic.Mensaje;
import city.model.manager.ManagerPersona;

/**
 * @author jestevez
 * 
 */
/**
 * @author jestevez
 *
 */
/**
 * @author jestevez
 *
 */
@SessionScoped
@ManagedBean
public class PersonaBean {

	// Atributos de la Clase
	@EJB
	private ManagerPersona manager;
	@Inject
	private SesionBean session;

	// Atriutos de la clase persona detalle
	private String perDni;
	private String perApellidos;
	private String perCelular;
	private String perCorreo;
	private String perEstado;
	private String perEstadoCivil;
	private Date perFechaNacimiento;
	private String perGenero;
	private String perNombres;
	private String perTelefono;
	private String perTipoDni;

	// Atriutos de la clase persona detalle
	private String pdeCiudadNacimiento;
	private String pdeCiudadResidencia;
	private String pdeCondicionCiudadana;
	private String pdeConyuge;
	private String pdeDireccion;
	private String pdeEmergContactoId;
	private String pdeEmergContactoNombres;
	private String pdeEmergContactoTelefono;
	private Date pdeFechaDefuncion;
	private Date pdeFechaMatrimonio;
	private String pdeFoto;
	private byte[] pdeHuella;
	private String pdeInscripcionDefuncion;
	private String pdeLugarNacimiento;
	private String pdeNacionalidadMadre;
	private String pdeNacionalidadPadre;
	private String pdeNombreMadre;
	private String pdeNombrePadre;
	private String pdeObservacion;
	private String pdePaisNacimiento;
	private String pdePaisResidencia;
	private String pdeProvinciaNacimiento;
	private String pdeProvinciaResidencia;
	private Integer pdeNumHijos;

	// Atributos de la clase salud
	private String sldAlergias;
	private BigDecimal sldAltura;
	private String sldAsegurado;
	private String sldCarnetConadies;
	private String sldConsumeAlcohol;
	private String sldConsumeTabaco;
	private String sldDiscapacidadGrado;
	private String sldDiscapacidadTipo;
	private String sldFrecienciaConsumoMedicame;
	private String sldGrupoSanguineo;
	private String sldMedicamentosCronicos1;
	private String sldMedicamentosCronicos2;
	private String sldNivelAzucar;
	private String sldPeriodicidadEjercicio;
	private BigDecimal sldPeso;
	private String sldPresion;
	private Boolean sldRealizaEjercicio;
	private Boolean sldVegetariano;
	private String sldAlergiasCronicas2;
	private Boolean sldEmbriagar;
	private String sldMadreCausaMuerte;
	private Integer sldMadreEdad;
	private String sldMadreEnfermedadesActuales;
	private Boolean sldMadreFallecio;
	private String sldNombreLugarCentroMedico;
	private String sldObservaciones;
	private String sldPadreCausaMuerte;
	private Integer sldPadreEdad;
	private String sldPadreEnfermedadesActuales;
	private Boolean sldPadreFallecio;
	private String sldPeriodicidadAlcohol;
	private String sldPeriodicidadEmbriagar;
	private String sldPeriodicidadTabaco;

	private boolean sld_padre;
	private boolean sld_madre;

	// atributos de la clase persona - institucion
	private String peiEstado;
	private Date peiFechaRegistro;
	private String peiRol;
	private String institucion;

	// manejo de vistas
	List<GenPersona> l_persona;
	List<SelectItem> l_estados;
	List<SelectItem> l_tipo_dni;
	List<SelectItem> l_genero;
	List<SelectItem> l_estado_civil;
	List<SelectItem> l_pais;
	List<SelectItem> l_provincia;
	List<SelectItem> l_ciudad_n;
	List<SelectItem> l_ciudad_r;
	List<SelectItem> l_sangre;
	List<SelectItem> l_discapacidad;

	// valor de edición e inserción
	private boolean edicion;
	private String datoBuscar;

	// mensaje de validación de campos
	private String sms_validacion;

	// valor de provincias y ciudades
	private boolean select_n;
	private boolean select_r;

	// valor de ususario
	private String usuario;

	public PersonaBean() {
	}

	@PostConstruct
	public void init() {
		// proceso de filtrado de tabla
		// this.personas = new LazyDataModel<GenPersona>() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public List<GenPersona> load(int first, int pageSize, String
		// sortField, SortOrder sortOrder,
		// Map<String, String> filters) {
		// List<GenPersona> result = inventoryManagerEJB.getResultList(first,
		// pageSize, sortField, sortOrder,
		// filters);
		// model.setRowCount(inventoryManagerEJB.count(sortField, sortOrder,
		// filters));
		// return result;
		// }
		// };

		session.validarSesion();
		edicion = false;
		select_n = true;
		select_r = true;
		sld_madre = false;
		sld_padre = false;
		l_ciudad_n = new ArrayList<SelectItem>();
		l_ciudad_r = new ArrayList<SelectItem>();
		l_estado_civil = new ArrayList<SelectItem>();
		l_estados = new ArrayList<SelectItem>();
		l_genero = new ArrayList<SelectItem>();
		l_pais = new ArrayList<SelectItem>();
		l_provincia = new ArrayList<SelectItem>();
		l_tipo_dni = new ArrayList<SelectItem>();
		l_sangre = new ArrayList<SelectItem>();
		l_discapacidad = new ArrayList<SelectItem>();
		l_persona = new ArrayList<GenPersona>();
		sms_validacion = "";
		// cargarPersonas();
	}

	/**
	 * @return the sldAlergiasCronicas2
	 */
	public String getSldAlergiasCronicas2() {
		return sldAlergiasCronicas2;
	}

	/**
	 * @param sldAlergiasCronicas2
	 *            the sldAlergiasCronicas2 to set
	 */
	public void setSldAlergiasCronicas2(String sldAlergiasCronicas2) {
		this.sldAlergiasCronicas2 = sldAlergiasCronicas2;
	}

	/**
	 * @return the sldEmbriagar
	 */
	public Boolean getSldEmbriagar() {
		return sldEmbriagar;
	}

	/**
	 * @param sldEmbriagar
	 *            the sldEmbriagar to set
	 */
	public void setSldEmbriagar(Boolean sldEmbriagar) {
		this.sldEmbriagar = sldEmbriagar;
	}

	/**
	 * @return the sldMadreCausaMuerte
	 */
	public String getSldMadreCausaMuerte() {
		return sldMadreCausaMuerte;
	}

	/**
	 * @param sldMadreCausaMuerte
	 *            the sldMadreCausaMuerte to set
	 */
	public void setSldMadreCausaMuerte(String sldMadreCausaMuerte) {
		this.sldMadreCausaMuerte = sldMadreCausaMuerte;
	}

	/**
	 * @return the sldMadreEdad
	 */
	public Integer getSldMadreEdad() {
		return sldMadreEdad;
	}

	/**
	 * @param sldMadreEdad
	 *            the sldMadreEdad to set
	 */
	public void setSldMadreEdad(Integer sldMadreEdad) {
		this.sldMadreEdad = sldMadreEdad;
	}

	/**
	 * @return the sldMadreEnfermedadesActuales
	 */
	public String getSldMadreEnfermedadesActuales() {
		return sldMadreEnfermedadesActuales;
	}

	/**
	 * @param sldMadreEnfermedadesActuales
	 *            the sldMadreEnfermedadesActuales to set
	 */
	public void setSldMadreEnfermedadesActuales(String sldMadreEnfermedadesActuales) {
		this.sldMadreEnfermedadesActuales = sldMadreEnfermedadesActuales;
	}

	/**
	 * @return the sldNombreLugarCentroMedico
	 */
	public String getSldNombreLugarCentroMedico() {
		return sldNombreLugarCentroMedico;
	}

	/**
	 * @param sldNombreLugarCentroMedico
	 *            the sldNombreLugarCentroMedico to set
	 */
	public void setSldNombreLugarCentroMedico(String sldNombreLugarCentroMedico) {
		this.sldNombreLugarCentroMedico = sldNombreLugarCentroMedico;
	}

	/**
	 * @return the sldObservaciones
	 */
	public String getSldObservaciones() {
		return sldObservaciones;
	}

	/**
	 * @param sldObservaciones
	 *            the sldObservaciones to set
	 */
	public void setSldObservaciones(String sldObservaciones) {
		this.sldObservaciones = sldObservaciones;
	}

	/**
	 * @return the sldPadreCausaMuerte
	 */
	public String getSldPadreCausaMuerte() {
		return sldPadreCausaMuerte;
	}

	/**
	 * @param sldPadreCausaMuerte
	 *            the sldPadreCausaMuerte to set
	 */
	public void setSldPadreCausaMuerte(String sldPadreCausaMuerte) {
		this.sldPadreCausaMuerte = sldPadreCausaMuerte;
	}

	/**
	 * @return the sldPadreEdad
	 */
	public Integer getSldPadreEdad() {
		return sldPadreEdad;
	}

	/**
	 * @param sldPadreEdad
	 *            the sldPadreEdad to set
	 */
	public void setSldPadreEdad(Integer sldPadreEdad) {
		this.sldPadreEdad = sldPadreEdad;
	}

	/**
	 * @return the sldPadreEnfermedadesActuales
	 */
	public String getSldPadreEnfermedadesActuales() {
		return sldPadreEnfermedadesActuales;
	}

	/**
	 * @param sldPadreEnfermedadesActuales
	 *            the sldPadreEnfermedadesActuales to set
	 */
	public void setSldPadreEnfermedadesActuales(String sldPadreEnfermedadesActuales) {
		this.sldPadreEnfermedadesActuales = sldPadreEnfermedadesActuales;
	}

	/**
	 * @return the sld_padre
	 */
	public boolean isSld_padre() {
		return sld_padre;
	}

	/**
	 * @param sld_padre
	 *            the sld_padre to set
	 */
	public void setSld_padre(boolean sld_padre) {
		this.sld_padre = sld_padre;
	}

	/**
	 * @return the sld_madre
	 */
	public boolean isSld_madre() {
		return sld_madre;
	}

	/**
	 * @param sld_madre
	 *            the sld_madre to set
	 */
	public void setSld_madre(boolean sld_madre) {
		this.sld_madre = sld_madre;
	}

	/**
	 * @return the sldMadreFallecio
	 */
	public Boolean getSldMadreFallecio() {
		return sldMadreFallecio;
	}

	/**
	 * @param sldMadreFallecio
	 *            the sldMadreFallecio to set
	 */
	public void setSldMadreFallecio(Boolean sldMadreFallecio) {
		this.sldMadreFallecio = sldMadreFallecio;
	}

	/**
	 * @return the sldPadreFallecio
	 */
	public Boolean getSldPadreFallecio() {
		return sldPadreFallecio;
	}

	/**
	 * @param sldPadreFallecio
	 *            the sldPadreFallecio to set
	 */
	public void setSldPadreFallecio(Boolean sldPadreFallecio) {
		this.sldPadreFallecio = sldPadreFallecio;
	}

	/**
	 * @return the sldPeriodicidadAlcohol
	 */
	public String getSldPeriodicidadAlcohol() {
		return sldPeriodicidadAlcohol;
	}

	/**
	 * @param sldPeriodicidadAlcohol
	 *            the sldPeriodicidadAlcohol to set
	 */
	public void setSldPeriodicidadAlcohol(String sldPeriodicidadAlcohol) {
		this.sldPeriodicidadAlcohol = sldPeriodicidadAlcohol;
	}

	/**
	 * @return the sldPeriodicidadEmbriagar
	 */
	public String getSldPeriodicidadEmbriagar() {
		return sldPeriodicidadEmbriagar;
	}

	/**
	 * @param sldPeriodicidadEmbriagar
	 *            the sldPeriodicidadEmbriagar to set
	 */
	public void setSldPeriodicidadEmbriagar(String sldPeriodicidadEmbriagar) {
		this.sldPeriodicidadEmbriagar = sldPeriodicidadEmbriagar;
	}

	/**
	 * @return the sldPeriodicidadTabaco
	 */
	public String getSldPeriodicidadTabaco() {
		return sldPeriodicidadTabaco;
	}

	/**
	 * @param sldPeriodicidadTabaco
	 *            the sldPeriodicidadTabaco to set
	 */
	public void setSldPeriodicidadTabaco(String sldPeriodicidadTabaco) {
		this.sldPeriodicidadTabaco = sldPeriodicidadTabaco;
	}

	/**
	 * @return the datoBuscar
	 */
	public String getDatoBuscar() {
		return datoBuscar;
	}

	/**
	 * @param datoBuscar
	 *            the datoBuscar to set
	 */
	public void setDatoBuscar(String datoBuscar) {
		this.datoBuscar = datoBuscar;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the pdeNumHijos
	 */
	public Integer getPdeNumHijos() {
		return pdeNumHijos;
	}

	/**
	 * @param pdeNumHijos
	 *            the pdeNumHijos to set
	 */
	public void setPdeNumHijos(Integer pdeNumHijos) {
		this.pdeNumHijos = pdeNumHijos;
	}

	/**
	 * @return the sms_validacion
	 */
	public String getSms_validacion() {
		return sms_validacion;
	}

	/**
	 * @param sms_validacion
	 *            the sms_validacion to set
	 */
	public void setSms_validacion(String sms_validacion) {
		this.sms_validacion = sms_validacion;
	}

	/**
	 * @return the sldAlergias
	 */
	public String getSldAlergias() {
		return sldAlergias;
	}

	/**
	 * @param sldAlergias
	 *            the sldAlergias to set
	 */
	public void setSldAlergias(String sldAlergias) {
		this.sldAlergias = sldAlergias;
	}

	/**
	 * @return the sldAltura
	 */
	public BigDecimal getSldAltura() {
		return sldAltura;
	}

	/**
	 * @param sldAltura
	 *            the sldAltura to set
	 */
	public void setSldAltura(BigDecimal sldAltura) {
		this.sldAltura = sldAltura;
	}

	/**
	 * @return the sldAsegurado
	 */
	public String getSldAsegurado() {
		return sldAsegurado;
	}

	/**
	 * @param sldAsegurado
	 *            the sldAsegurado to set
	 */
	public void setSldAsegurado(String sldAsegurado) {
		this.sldAsegurado = sldAsegurado;
	}

	/**
	 * @return the sldCarnetConadies
	 */
	public String getSldCarnetConadies() {
		return sldCarnetConadies;
	}

	/**
	 * @param sldCarnetConadies
	 *            the sldCarnetConadies to set
	 */
	public void setSldCarnetConadies(String sldCarnetConadies) {
		this.sldCarnetConadies = sldCarnetConadies;
	}

	/**
	 * @return the sldConsumeAlcohol
	 */
	public String getSldConsumeAlcohol() {
		return sldConsumeAlcohol;
	}

	/**
	 * @param sldConsumeAlcohol the sldConsumeAlcohol to set
	 */
	public void setSldConsumeAlcohol(String sldConsumeAlcohol) {
		this.sldConsumeAlcohol = sldConsumeAlcohol;
	}

	/**
	 * @return the sldConsumeTabaco
	 */
	public String getSldConsumeTabaco() {
		return sldConsumeTabaco;
	}

	/**
	 * @param sldConsumeTabaco the sldConsumeTabaco to set
	 */
	public void setSldConsumeTabaco(String sldConsumeTabaco) {
		this.sldConsumeTabaco = sldConsumeTabaco;
	}

	/**
	 * @return the sldDiscapacidadGrado
	 */
	public String getSldDiscapacidadGrado() {
		return sldDiscapacidadGrado;
	}

	/**
	 * @param sldDiscapacidadGrado
	 *            the sldDiscapacidadGrado to set
	 */
	public void setSldDiscapacidadGrado(String sldDiscapacidadGrado) {
		this.sldDiscapacidadGrado = sldDiscapacidadGrado;
	}

	/**
	 * @return the sldDiscapacidadTipo
	 */
	public String getSldDiscapacidadTipo() {
		return sldDiscapacidadTipo;
	}

	/**
	 * @param sldDiscapacidadTipo
	 *            the sldDiscapacidadTipo to set
	 */
	public void setSldDiscapacidadTipo(String sldDiscapacidadTipo) {
		this.sldDiscapacidadTipo = sldDiscapacidadTipo;
	}

	/**
	 * @return the sldFrecienciaConsumoMedicame
	 */
	public String getSldFrecienciaConsumoMedicame() {
		return sldFrecienciaConsumoMedicame;
	}

	/**
	 * @param sldFrecienciaConsumoMedicame
	 *            the sldFrecienciaConsumoMedicame to set
	 */
	public void setSldFrecienciaConsumoMedicame(String sldFrecienciaConsumoMedicame) {
		this.sldFrecienciaConsumoMedicame = sldFrecienciaConsumoMedicame;
	}

	/**
	 * @return the sldGrupoSanguineo
	 */
	public String getSldGrupoSanguineo() {
		return sldGrupoSanguineo;
	}

	/**
	 * @param sldGrupoSanguineo
	 *            the sldGrupoSanguineo to set
	 */
	public void setSldGrupoSanguineo(String sldGrupoSanguineo) {
		this.sldGrupoSanguineo = sldGrupoSanguineo;
	}

	/**
	 * @return the sldMedicamentosCronicos1
	 */
	public String getSldMedicamentosCronicos1() {
		return sldMedicamentosCronicos1;
	}

	/**
	 * @param sldMedicamentosCronicos1
	 *            the sldMedicamentosCronicos1 to set
	 */
	public void setSldMedicamentosCronicos1(String sldMedicamentosCronicos1) {
		this.sldMedicamentosCronicos1 = sldMedicamentosCronicos1;
	}

	/**
	 * @return the sldMedicamentosCronicos2
	 */
	public String getSldMedicamentosCronicos2() {
		return sldMedicamentosCronicos2;
	}

	/**
	 * @param sldMedicamentosCronicos2
	 *            the sldMedicamentosCronicos2 to set
	 */
	public void setSldMedicamentosCronicos2(String sldMedicamentosCronicos2) {
		this.sldMedicamentosCronicos2 = sldMedicamentosCronicos2;
	}

	/**
	 * @return the sldNivelAzucar
	 */
	public String getSldNivelAzucar() {
		return sldNivelAzucar;
	}

	/**
	 * @param sldNivelAzucar
	 *            the sldNivelAzucar to set
	 */
	public void setSldNivelAzucar(String sldNivelAzucar) {
		this.sldNivelAzucar = sldNivelAzucar;
	}

	/**
	 * @return the sldPeriodicidadEjercicio
	 */
	public String getSldPeriodicidadEjercicio() {
		return sldPeriodicidadEjercicio;
	}

	/**
	 * @param sldPeriodicidadEjercicio
	 *            the sldPeriodicidadEjercicio to set
	 */
	public void setSldPeriodicidadEjercicio(String sldPeriodicidadEjercicio) {
		this.sldPeriodicidadEjercicio = sldPeriodicidadEjercicio;
	}

	/**
	 * @return the sldPeso
	 */
	public BigDecimal getSldPeso() {
		return sldPeso;
	}

	/**
	 * @param sldPeso
	 *            the sldPeso to set
	 */
	public void setSldPeso(BigDecimal sldPeso) {
		this.sldPeso = sldPeso;
	}

	/**
	 * @return the sldPresion
	 */
	public String getSldPresion() {
		return sldPresion;
	}

	/**
	 * @param sldPresion
	 *            the sldPresion to set
	 */
	public void setSldPresion(String sldPresion) {
		this.sldPresion = sldPresion;
	}

	/**
	 * @return the sldRealizaEjercicio
	 */
	public Boolean getSldRealizaEjercicio() {
		return sldRealizaEjercicio;
	}

	/**
	 * @param sldRealizaEjercicio
	 *            the sldRealizaEjercicio to set
	 */
	public void setSldRealizaEjercicio(Boolean sldRealizaEjercicio) {
		this.sldRealizaEjercicio = sldRealizaEjercicio;
	}

	/**
	 * @return the sldVegetariano
	 */
	public Boolean getSldVegetariano() {
		return sldVegetariano;
	}

	/**
	 * @param sldVegetariano
	 *            the sldVegetariano to set
	 */
	public void setSldVegetariano(Boolean sldVegetariano) {
		this.sldVegetariano = sldVegetariano;
	}

	/**
	 * @return the peiEstado
	 */
	public String getPeiEstado() {
		return peiEstado;
	}

	/**
	 * @param peiEstado
	 *            the peiEstado to set
	 */
	public void setPeiEstado(String peiEstado) {
		this.peiEstado = peiEstado;
	}

	/**
	 * @return the peiFechaRegistro
	 */
	public Date getPeiFechaRegistro() {
		return peiFechaRegistro;
	}

	/**
	 * @param peiFechaRegistro
	 *            the peiFechaRegistro to set
	 */
	public void setPeiFechaRegistro(Date peiFechaRegistro) {
		this.peiFechaRegistro = peiFechaRegistro;
	}

	/**
	 * @return the peiRol
	 */
	public String getPeiRol() {
		return peiRol;
	}

	/**
	 * @param peiRol
	 *            the peiRol to set
	 */
	public void setPeiRol(String peiRol) {
		this.peiRol = peiRol;
	}

	/**
	 * @return the institucion
	 */
	public String getInstitucion() {
		return institucion;
	}

	/**
	 * @param institucion
	 *            the institucion to set
	 */
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	/**
	 * @return the l_ciudad_n
	 */
	public List<SelectItem> getL_ciudad_n() {
		return l_ciudad_n;
	}

	/**
	 * @param l_ciudad_n
	 *            the l_ciudad_n to set
	 */
	public void setL_ciudad_n(List<SelectItem> l_ciudad_n) {
		this.l_ciudad_n = l_ciudad_n;
	}

	/**
	 * @return the l_discapacidad
	 */
	public List<SelectItem> getL_discapacidad() {
		return l_discapacidad;
	}

	/**
	 * @param l_discapacidad
	 *            the l_discapacidad to set
	 */
	public void setL_discapacidad(List<SelectItem> l_discapacidad) {
		this.l_discapacidad = l_discapacidad;
	}

	/**
	 * @return the l_ciudad_r
	 */
	public List<SelectItem> getL_ciudad_r() {
		return l_ciudad_r;
	}

	/**
	 * @param l_ciudad_r
	 *            the l_ciudad_r to set
	 */
	public void setL_ciudad_r(List<SelectItem> l_ciudad_r) {
		this.l_ciudad_r = l_ciudad_r;
	}

	/**
	 * @return the select_n
	 */
	public boolean isSelect_n() {
		return select_n;
	}

	/**
	 * @param select_n
	 *            the select_n to set
	 */
	public void setSelect_n(boolean select_n) {
		this.select_n = select_n;
	}

	/**
	 * @return the select_r
	 */
	public boolean isSelect_r() {
		return select_r;
	}

	/**
	 * @param select_r
	 *            the select_r to set
	 */
	public void setSelect_r(boolean select_r) {
		this.select_r = select_r;
	}

	/**
	 * @return the perDni
	 */
	public String getPerDni() {
		return perDni;
	}

	/**
	 * @param perDni
	 *            the perDni to set
	 */
	public void setPerDni(String perDni) {
		this.perDni = perDni;
	}

	/**
	 * @return the perApellidos
	 */
	public String getPerApellidos() {
		return perApellidos;
	}

	/**
	 * @param perApellidos
	 *            the perApellidos to set
	 */
	public void setPerApellidos(String perApellidos) {
		this.perApellidos = perApellidos;
	}

	/**
	 * @return the perCelular
	 */
	public String getPerCelular() {
		return perCelular;
	}

	/**
	 * @param perCelular
	 *            the perCelular to set
	 */
	public void setPerCelular(String perCelular) {
		this.perCelular = perCelular;
	}

	/**
	 * @return the perCorreo
	 */
	public String getPerCorreo() {
		return perCorreo;
	}

	/**
	 * @param perCorreo
	 *            the perCorreo to set
	 */
	public void setPerCorreo(String perCorreo) {
		this.perCorreo = perCorreo;
	}

	/**
	 * @return the perEstado
	 */
	public String getPerEstado() {
		return perEstado;
	}

	/**
	 * @param perEstado
	 *            the perEstado to set
	 */
	public void setPerEstado(String perEstado) {
		this.perEstado = perEstado;
	}

	/**
	 * @return the perEstadoCivil
	 */
	public String getPerEstadoCivil() {
		return perEstadoCivil;
	}

	/**
	 * @param perEstadoCivil
	 *            the perEstadoCivil to set
	 */
	public void setPerEstadoCivil(String perEstadoCivil) {
		this.perEstadoCivil = perEstadoCivil;
	}

	/**
	 * @return the perFechaNacimiento
	 */
	public Date getPerFechaNacimiento() {
		return perFechaNacimiento;
	}

	/**
	 * @param perFechaNacimiento
	 *            the perFechaNacimiento to set
	 */
	public void setPerFechaNacimiento(Date perFechaNacimiento) {
		this.perFechaNacimiento = perFechaNacimiento;
	}

	/**
	 * @return the perGenero
	 */
	public String getPerGenero() {
		return perGenero;
	}

	/**
	 * @param perGenero
	 *            the perGenero to set
	 */
	public void setPerGenero(String perGenero) {
		this.perGenero = perGenero;
	}

	/**
	 * @return the perNombres
	 */
	public String getPerNombres() {
		return perNombres;
	}

	/**
	 * @param perNombres
	 *            the perNombres to set
	 */
	public void setPerNombres(String perNombres) {
		this.perNombres = perNombres;
	}

	/**
	 * @return the perTelefono
	 */
	public String getPerTelefono() {
		return perTelefono;
	}

	/**
	 * @param perTelefono
	 *            the perTelefono to set
	 */
	public void setPerTelefono(String perTelefono) {
		this.perTelefono = perTelefono;
	}

	/**
	 * @return the perTipoDni
	 */
	public String getPerTipoDni() {
		return perTipoDni;
	}

	/**
	 * @param perTipoDni
	 *            the perTipoDni to set
	 */
	public void setPerTipoDni(String perTipoDni) {
		this.perTipoDni = perTipoDni;
	}

	/**
	 * @return the edicion
	 */
	public boolean isEdicion() {
		return edicion;
	}

	// Getteres and Setteres de persona
	// detalle/////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @return the pdeCiudadNacimiento
	 */
	public String getPdeCiudadNacimiento() {
		return pdeCiudadNacimiento;
	}

	/**
	 * @return the l_persona
	 */
	public List<GenPersona> getL_persona() {
		return l_persona;
	}

	/**
	 * @param l_persona
	 *            the l_persona to set
	 */
	public void setL_persona(List<GenPersona> l_persona) {
		this.l_persona = l_persona;
	}

	/**
	 * @return the l_estados
	 */
	public List<SelectItem> getL_estados() {
		return l_estados;
	}

	/**
	 * @param l_estados
	 *            the l_estados to set
	 */
	public void setL_estados(List<SelectItem> l_estados) {
		this.l_estados = l_estados;
	}

	/**
	 * @return the l_tipo_dni
	 */
	public List<SelectItem> getL_tipo_dni() {
		return l_tipo_dni;
	}

	/**
	 * @param l_tipo_dni
	 *            the l_tipo_dni to set
	 */
	public void setL_tipo_dni(List<SelectItem> l_tipo_dni) {
		this.l_tipo_dni = l_tipo_dni;
	}

	/**
	 * @return the l_genero
	 */
	public List<SelectItem> getL_genero() {
		return l_genero;
	}

	/**
	 * @param l_genero
	 *            the l_genero to set
	 */
	public void setL_genero(List<SelectItem> l_genero) {
		this.l_genero = l_genero;
	}

	/**
	 * @return the l_sangre
	 */
	public List<SelectItem> getL_sangre() {
		return l_sangre;
	}

	/**
	 * @param l_sangre
	 *            the l_sangre to set
	 */
	public void setL_sangre(List<SelectItem> l_sangre) {
		this.l_sangre = l_sangre;
	}

	/**
	 * @return the l_estado_civil
	 */
	public List<SelectItem> getL_estado_civil() {
		return l_estado_civil;
	}

	/**
	 * @param l_estado_civil
	 *            the l_estado_civil to set
	 */
	public void setL_estado_civil(List<SelectItem> l_estado_civil) {
		this.l_estado_civil = l_estado_civil;
	}

	/**
	 * @return the l_pais
	 */
	public List<SelectItem> getL_pais() {
		return l_pais;
	}

	/**
	 * @param l_pais
	 *            the l_pais to set
	 */
	public void setL_pais(List<SelectItem> l_pais) {
		this.l_pais = l_pais;
	}

	/**
	 * @return the l_provincia
	 */
	public List<SelectItem> getL_provincia() {
		return l_provincia;
	}

	/**
	 * @param l_provincia
	 *            the l_provincia to set
	 */
	public void setL_provincia(List<SelectItem> l_provincia) {
		this.l_provincia = l_provincia;
	}

	/**
	 * @param pdeCiudadNacimiento
	 *            the pdeCiudadNacimiento to set
	 */
	public void setPdeCiudadNacimiento(String pdeCiudadNacimiento) {
		this.pdeCiudadNacimiento = pdeCiudadNacimiento;
	}

	/**
	 * @return the pdeCiudadResidencia
	 */
	public String getPdeCiudadResidencia() {
		return pdeCiudadResidencia;
	}

	/**
	 * @param pdeCiudadResidencia
	 *            the pdeCiudadResidencia to set
	 */
	public void setPdeCiudadResidencia(String pdeCiudadResidencia) {
		this.pdeCiudadResidencia = pdeCiudadResidencia;
	}

	/**
	 * @return the pdeCondicionCiudadana
	 */
	public String getPdeCondicionCiudadana() {
		return pdeCondicionCiudadana;
	}

	/**
	 * @param pdeCondicionCiudadana
	 *            the pdeCondicionCiudadana to set
	 */
	public void setPdeCondicionCiudadana(String pdeCondicionCiudadana) {
		this.pdeCondicionCiudadana = pdeCondicionCiudadana;
	}

	/**
	 * @return the pdeConyuge
	 */
	public String getPdeConyuge() {
		return pdeConyuge;
	}

	/**
	 * @param pdeConyuge
	 *            the pdeConyuge to set
	 */
	public void setPdeConyuge(String pdeConyuge) {
		this.pdeConyuge = pdeConyuge;
	}

	/**
	 * @return the pdeDireccion
	 */
	public String getPdeDireccion() {
		return pdeDireccion;
	}

	/**
	 * @param pdeDireccion
	 *            the pdeDireccion to set
	 */
	public void setPdeDireccion(String pdeDireccion) {
		this.pdeDireccion = pdeDireccion;
	}

	/**
	 * @return the pdeEmergContactoId
	 */
	public String getPdeEmergContactoId() {
		return pdeEmergContactoId;
	}

	/**
	 * @param pdeEmergContactoId
	 *            the pdeEmergContactoId to set
	 */
	public void setPdeEmergContactoId(String pdeEmergContactoId) {
		this.pdeEmergContactoId = pdeEmergContactoId;
	}

	/**
	 * @return the pdeEmergContactoNombres
	 */
	public String getPdeEmergContactoNombres() {
		return pdeEmergContactoNombres;
	}

	/**
	 * @param pdeEmergContactoNombres
	 *            the pdeEmergContactoNombres to set
	 */
	public void setPdeEmergContactoNombres(String pdeEmergContactoNombres) {
		this.pdeEmergContactoNombres = pdeEmergContactoNombres;
	}

	/**
	 * @return the pdeEmergContactoTelefono
	 */
	public String getPdeEmergContactoTelefono() {
		return pdeEmergContactoTelefono;
	}

	/**
	 * @param pdeEmergContactoTelefono
	 *            the pdeEmergContactoTelefono to set
	 */
	public void setPdeEmergContactoTelefono(String pdeEmergContactoTelefono) {
		this.pdeEmergContactoTelefono = pdeEmergContactoTelefono;
	}

	/**
	 * @return the pdeFechaDefuncion
	 */
	public Date getPdeFechaDefuncion() {
		return pdeFechaDefuncion;
	}

	/**
	 * @param pdeFechaDefuncion
	 *            the pdeFechaDefuncion to set
	 */
	public void setPdeFechaDefuncion(Date pdeFechaDefuncion) {
		this.pdeFechaDefuncion = pdeFechaDefuncion;
	}

	/**
	 * @return the pdeFechaMatrimonio
	 */
	public Date getPdeFechaMatrimonio() {
		return pdeFechaMatrimonio;
	}

	/**
	 * @param pdeFechaMatrimonio
	 *            the pdeFechaMatrimonio to set
	 */
	public void setPdeFechaMatrimonio(Date pdeFechaMatrimonio) {
		this.pdeFechaMatrimonio = pdeFechaMatrimonio;
	}

	/**
	 * @return the pdeFoto
	 */
	public String getPdeFoto() {
		return pdeFoto;
	}

	/**
	 * @param pdeFoto
	 *            the pdeFoto to set
	 */
	public void setPdeFoto(String pdeFoto) {
		this.pdeFoto = pdeFoto;
	}

	/**
	 * @return the pdeHuella
	 */
	public byte[] getPdeHuella() {
		return pdeHuella;
	}

	/**
	 * @param pdeHuella
	 *            the pdeHuella to set
	 */
	public void setPdeHuella(byte[] pdeHuella) {
		this.pdeHuella = pdeHuella;
	}

	/**
	 * @return the pdeInscripcionDefuncion
	 */
	public String getPdeInscripcionDefuncion() {
		return pdeInscripcionDefuncion;
	}

	/**
	 * @param pdeInscripcionDefuncion
	 *            the pdeInscripcionDefuncion to set
	 */
	public void setPdeInscripcionDefuncion(String pdeInscripcionDefuncion) {
		this.pdeInscripcionDefuncion = pdeInscripcionDefuncion;
	}

	/**
	 * @return the pdeLugarNacimiento
	 */
	public String getPdeLugarNacimiento() {
		return pdeLugarNacimiento;
	}

	/**
	 * @param pdeLugarNacimiento
	 *            the pdeLugarNacimiento to set
	 */
	public void setPdeLugarNacimiento(String pdeLugarNacimiento) {
		this.pdeLugarNacimiento = pdeLugarNacimiento;
	}

	/**
	 * @return the pdeNacionalidadMadre
	 */
	public String getPdeNacionalidadMadre() {
		return pdeNacionalidadMadre;
	}

	/**
	 * @param pdeNacionalidadMadre
	 *            the pdeNacionalidadMadre to set
	 */
	public void setPdeNacionalidadMadre(String pdeNacionalidadMadre) {
		this.pdeNacionalidadMadre = pdeNacionalidadMadre;
	}

	/**
	 * @return the pdeNacionalidadPadre
	 */
	public String getPdeNacionalidadPadre() {
		return pdeNacionalidadPadre;
	}

	/**
	 * @param pdeNacionalidadPadre
	 *            the pdeNacionalidadPadre to set
	 */
	public void setPdeNacionalidadPadre(String pdeNacionalidadPadre) {
		this.pdeNacionalidadPadre = pdeNacionalidadPadre;
	}

	/**
	 * @return the pdeNombreMadre
	 */
	public String getPdeNombreMadre() {
		return pdeNombreMadre;
	}

	/**
	 * @param pdeNombreMadre
	 *            the pdeNombreMadre to set
	 */
	public void setPdeNombreMadre(String pdeNombreMadre) {
		this.pdeNombreMadre = pdeNombreMadre;
	}

	/**
	 * @return the pdeNombrePadre
	 */
	public String getPdeNombrePadre() {
		return pdeNombrePadre;
	}

	/**
	 * @param pdeNombrePadre
	 *            the pdeNombrePadre to set
	 */
	public void setPdeNombrePadre(String pdeNombrePadre) {
		this.pdeNombrePadre = pdeNombrePadre;
	}

	/**
	 * @return the pdeObservacion
	 */
	public String getPdeObservacion() {
		return pdeObservacion;
	}

	/**
	 * @param pdeObservacion
	 *            the pdeObservacion to set
	 */
	public void setPdeObservacion(String pdeObservacion) {
		this.pdeObservacion = pdeObservacion;
	}

	/**
	 * @return the pdePaisNacimiento
	 */
	public String getPdePaisNacimiento() {
		return pdePaisNacimiento;
	}

	/**
	 * @param pdePaisNacimiento
	 *            the pdePaisNacimiento to set
	 */
	public void setPdePaisNacimiento(String pdePaisNacimiento) {
		this.pdePaisNacimiento = pdePaisNacimiento;
	}

	/**
	 * @return the pdePaisResidencia
	 */
	public String getPdePaisResidencia() {
		return pdePaisResidencia;
	}

	/**
	 * @param pdePaisResidencia
	 *            the pdePaisResidencia to set
	 */
	public void setPdePaisResidencia(String pdePaisResidencia) {
		this.pdePaisResidencia = pdePaisResidencia;
	}

	/**
	 * @return the pdeProvinciaNacimiento
	 */
	public String getPdeProvinciaNacimiento() {
		return pdeProvinciaNacimiento;
	}

	/**
	 * @param pdeProvinciaNacimiento
	 *            the pdeProvinciaNacimiento to set
	 */
	public void setPdeProvinciaNacimiento(String pdeProvinciaNacimiento) {
		this.pdeProvinciaNacimiento = pdeProvinciaNacimiento;
	}

	/**
	 * @return the pdeProvinciaResidencia
	 */
	public String getPdeProvinciaResidencia() {
		return pdeProvinciaResidencia;
	}

	/**
	 * @param pdeProvinciaResidencia
	 *            the pdeProvinciaResidencia to set
	 */
	public void setPdeProvinciaResidencia(String pdeProvinciaResidencia) {
		this.pdeProvinciaResidencia = pdeProvinciaResidencia;
	}

	/**
	 * @param edicion
	 *            the edicion to set
	 */
	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	/**
	 * Redirecciona a la pagina de creacion de personas
	 * 
	 * @return vista
	 */
	public String nuevaPersona() {
		setSelect_n(true);
		setSelect_r(true);
		this.carga();
		return "npersona?faces-redirect=true";
	}

	/**
	 * Método para cargar todos los select
	 */
	public void carga() {
		cargarEstadoCivil();
		cargarGeneros();
		cargarPaises();
		cargarProvincias();
		cargarTiposDni();
		cargarTipoSangre();
		cargarEstados();
		cargarDiscapacidad();
	}

	/**
	 * Permite la creación o modificación de una persona
	 * 
	 * @return
	 */
	public String crearPersona() {
		String r = "";
		try {
			if (this.validarCampos()) {
				Mensaje.crearMensajeERROR(getSms_validacion());
			} else {
				GenPersona p = manager.PersonaByID(getPerDni());
				if (p == null) {
					manager.insertarPersona(getPerDni().trim(), getPerTipoDni().trim(), getPerNombres().trim(),
							getPerApellidos().trim(), getPerFechaNacimiento(), getPerGenero().trim(),
							getPerTelefono().trim(), getPerCelular().trim(), getPerCorreo().trim(),
							getPerEstadoCivil().trim());
					this.crearEditarPersonaDetalle();
					this.crearEditarSalud();
					Mensaje.crearMensajeINFO("Registrado - Persona Creada");
					// setEdicion(true);
				} else {
					manager.editarPersona(getPerDni().trim(), getPerTipoDni().trim(), getPerNombres().trim(),
							getPerApellidos().trim(), getPerFechaNacimiento(), getPerGenero().trim(),
							getPerTelefono().trim(), getPerCelular().trim(), getPerCorreo().trim(),
							getPerEstadoCivil().trim(), getPerEstado());
					this.crearEditarPersonaDetalle();
					this.crearEditarSalud();
					Mensaje.crearMensajeINFO("Actualizado - Persona Modificada");
				}
				r = "npersona?faces-redirect=true";
				// this.cleanDatos();
				// this.cargarPersonas();
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
		return r;
	}

	/**
	 * Metodo para cargar una Persona para su edicion
	 * 
	 * @param persona
	 * @return
	 */
	public String cargarPersona(GenPersona persona) {
		try {
			this.carga();
			setSelect_n(false);
			setSelect_r(false);
			cargarEstados();
			setPerDni(persona.getPerDni());
			setPerTipoDni(persona.getPerTipoDni());
			setPerNombres(persona.getPerNombres());
			setPerApellidos(persona.getPerApellidos());
			setPerFechaNacimiento(persona.getPerFechaNacimiento());
			setPerGenero(persona.getPerGenero());
			setPerTelefono(persona.getPerTelefono());
			setPerCelular(persona.getPerCelular());
			setPerCorreo(persona.getPerCorreo());
			setPerEstadoCivil(persona.getPerEstadoCivil());
			setPerEstado(persona.getPerEstado());

			// carga de persona detalle si existe
			GenPersonaDetalle pd = manager.PersonaDetalleByID(persona.getPerDni());
			if (pd != null)
				this.cargarPersonaDetalle(pd);

			// carga de Salud si existe
			GenSalud sl = manager.SaludByID(persona.getPerDni());
			if (sl != null)
				this.cargarSalud(sl);

			setEdicion(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "npersona?faces-redirect=true";
	}

	/**
	 * Metodo para setear datos y regresar la vista
	 * 
	 * @return
	 */
	public String cancelar() {
		this.cleanDatos();
		// this.cargarPersonas();
		getL_persona().clear();
		return "persona?faces-redirect=true";
	}

	/**
	 * Lista de Personas
	 */
	public void cargarPersonas() {
		try {
			getL_persona().clear();
			getL_persona().addAll(manager.findAllPersonas());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Lista de TiposDNI
	 */
	public void cargarTiposDni() {
		getL_tipo_dni().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_tipo_dni");
		for (GenCatalogoItemsDet i : completo) {
			getL_tipo_dni().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de Generos
	 */
	public void cargarGeneros() {
		getL_genero().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_genero");
		for (GenCatalogoItemsDet i : completo) {
			getL_genero().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de Tipos Sangre
	 */
	public void cargarTipoSangre() {
		getL_sangre().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_sangre");
		for (GenCatalogoItemsDet i : completo) {
			getL_sangre().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de Tipos de Discapacidad
	 */
	public void cargarDiscapacidad() {
		getL_discapacidad().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_discapacidad");
		for (GenCatalogoItemsDet i : completo) {
			getL_discapacidad().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de EstadoCivil
	 */
	public void cargarEstadoCivil() {
		getL_estado_civil().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_estado_civil");
		for (GenCatalogoItemsDet i : completo) {
			getL_estado_civil().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de Paises
	 */
	public void cargarPaises() {
		getL_pais().clear();
		for (GenCatalogoItemsDet i : manager.AllofItems("cat_paises")) {
			getL_pais().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de Provincias
	 */
	public void cargarProvincias() {
		getL_provincia().clear();
		for (GenCatalogoItemsDet i : manager.AllofItems("cat_provincias")) {
			getL_provincia().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de Ciudades Nacimiento
	 */
	public void cargarCiudadesNac(String provincia) {
		getL_ciudad_n().clear();
		if (manager.AllofItems("cat_ciudades", provincia) != null) {
			for (GenCatalogoItemsDet i : manager.AllofItems("cat_ciudades", provincia)) {
				getL_ciudad_n().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
			}
		}
	}

	/**
	 * Lista de Ciudades Residencia
	 */
	public void cargarCiudadesRes(String provincia) {
		getL_ciudad_r().clear();
		if (manager.AllofItems("cat_ciudades", provincia) != null) {
			for (GenCatalogoItemsDet i : manager.AllofItems("cat_ciudades", provincia)) {
				getL_ciudad_r().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
			}
		}
	}

	/**
	 * Lista de Estado
	 */
	public void cargarEstados() {
		getL_estados().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_estados");
		for (GenCatalogoItemsDet i : completo) {
			getL_estados().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Metodo para cambiar el char nombre por el nombre completo
	 * 
	 * @param estado
	 * @return
	 */
	public String cambiarNombreEstado(String estado) {
		String result = "";

		if (estado.equals("A"))
			result = "Activo";
		else if (estado.equals("P"))
			result = "Pendiente";
		else if (estado.equals("I"))
			result = "Inactivo";

		return result;
	}

	/**
	 * Metodo para cambiar de color segun los estados
	 * 
	 * @param estado
	 * @return
	 */
	public String cambiarColorEstado(String estado) {
		String color = "";

		if (estado.equals("A"))
			color = "green";
		else if (estado.equals("P"))
			color = "yellow";
		else if (estado.equals("I"))
			color = "red";

		return color;
	}

	/**
	 * Método para buscar una persona
	 */
	public void buscarPersona() {
		l_persona.clear();
		if (datoBuscar == null || datoBuscar.isEmpty()) {
			Mensaje.crearMensajeWARN("No existe el dato para realizar la búsqueda.");
		} else {
			l_persona = manager.buscarPersona(datoBuscar);
		}
	}

	// ////////////////////////////////////////////////////////PERSONA-DETALLE/////////////////////////////////////////////////////////

	/**
	 * Permite la creacion de una persona detalle
	 * 
	 * @return
	 */
	public void crearEditarPersonaDetalle() {
		try {
			GenPersonaDetalle pd = manager.PersonaDetalleByID(getPerDni());
			if (pd == null) {
				manager.insertarPersonaDetalle(getPerDni(), getPdeFoto(), getPdePaisNacimiento(),
						getPdeProvinciaNacimiento(), getPdeCiudadNacimiento(), getPdeLugarNacimiento(),
						getPdePaisResidencia(), getPdeProvinciaResidencia(), getPdeCiudadResidencia(),
						getPdeDireccion(), getPdeCondicionCiudadana(), getPdeConyuge(), getPdeFechaMatrimonio(),
						getPdeNumHijos(), getPdeNombrePadre(), getPdeNacionalidadPadre(), getPdeNombreMadre(),
						getPdeNacionalidadMadre(), getPdeEmergContactoNombres(), getPdeEmergContactoId(),
						getPdeEmergContactoTelefono(), getPdeInscripcionDefuncion(), getPdeFechaDefuncion(),
						getPdeObservacion());
			} else {
				manager.editarPersonaDetalle(getPerDni(), getPdeFoto(), getPdePaisNacimiento(),
						getPdeProvinciaNacimiento(), getPdeCiudadNacimiento(), getPdeLugarNacimiento(),
						getPdePaisResidencia(), getPdeProvinciaResidencia(), getPdeCiudadResidencia(),
						getPdeDireccion(), getPdeCondicionCiudadana(), getPdeConyuge(), getPdeFechaMatrimonio(),
						getPdeNumHijos(), getPdeNombrePadre(), getPdeNacionalidadPadre(), getPdeNombreMadre(),
						getPdeNacionalidadMadre(), getPdeEmergContactoNombres(), getPdeEmergContactoId(),
						getPdeEmergContactoTelefono(), getPdeInscripcionDefuncion(), getPdeFechaDefuncion(),
						getPdeObservacion());
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
	}

	/**
	 * Metodo para cargar una Persona-Detalle para su edicion
	 * 
	 * @param persona
	 * @return
	 */
	public String cargarPersonaDetalle(GenPersonaDetalle persona) {
		try {
			setPdeCiudadNacimiento(persona.getPdeCiudadNacimiento());
			setPdeCiudadResidencia(persona.getPdeCiudadResidencia());
			setPdeCondicionCiudadana(persona.getPdeCondicionCiudadana());
			setPdeConyuge(persona.getPdeConyuge());
			setPdeDireccion(persona.getPdeDireccion());
			setPdeEmergContactoId(persona.getPdeEmergContactoId());
			setPdeEmergContactoNombres(persona.getPdeEmergContactoNombres());
			setPdeEmergContactoTelefono(persona.getPdeEmergContactoTelefono());
			setPdeFechaDefuncion(persona.getPdeFechaDefuncion());
			setPdeFechaMatrimonio(persona.getPdeFechaMatrimonio());
			setPdeFoto(persona.getPdeFoto());
			setPdeHuella(persona.getPdeHuella());
			setPdeInscripcionDefuncion(persona.getPdeInscripcionDefuncion());
			setPdeLugarNacimiento(persona.getPdeLugarNacimiento());
			setPdeNacionalidadMadre(persona.getPdeNacionalidadMadre());
			setPdeNacionalidadPadre(persona.getPdeNacionalidadPadre());
			setPdeNombreMadre(persona.getPdeNombreMadre());
			setPdeNombrePadre(persona.getPdeNombrePadre());
			setPdeObservacion(persona.getPdeObservacion());
			setPdePaisNacimiento(persona.getPdePaisNacimiento());
			setPdePaisResidencia(persona.getPdePaisResidencia());
			setPdeProvinciaNacimiento(persona.getPdeProvinciaNacimiento());
			setPdeProvinciaResidencia(persona.getPdeProvinciaResidencia());
			// actualización de lista de sitios
			cargarCiudadesNac(persona.getPdeProvinciaNacimiento());
			cargarCiudadesRes(persona.getPdeProvinciaResidencia());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "npersona?faces-redirect=true";
	}

	/**
	 * Metodo para habilitar campos
	 */
	public void habilitarCamposNac() {
		if (getPdePaisNacimiento().equals("EC")) {
			setSelect_n(false);
			cargarProvincias();
		} else {
			setPdeProvinciaNacimiento(null);
			setPdeCiudadNacimiento(null);
			setSelect_n(true);
		}
	}

	/**
	 * Metodo para habilitar campos
	 */
	public void habilitarCamposRer() {
		if (getPdePaisResidencia().equals("EC")) {
			setSelect_r(false);
			cargarProvincias();
		} else {
			setPdeProvinciaResidencia(null);
			setPdeCiudadResidencia(null);
			setSelect_r(true);
		}
	}

	/**
	 * Metod para mostrar la ciudad dependiendo de la provincia
	 */
	public void mostrarCiudadNac(String provincia) {
		cargarCiudadesNac(provincia);
	}

	/**
	 * Metod para mostrar la ciudad dependiendo de la provincia
	 */
	public void mostrarCiudadRes(String provincia) {
		cargarCiudadesRes(provincia);
	}

	/**
	 * Metodo para validar los campos generales de la persona
	 * 
	 * @return
	 */
	public boolean validarCampos() {
		if ((getPerApellidos() == null || getPerApellidos().isEmpty())
				|| (getPerCelular() == null || getPerCelular().isEmpty())
				|| (getPerCorreo() == null || getPerCorreo().isEmpty())
				|| (getPerDni() == null || getPerDni().isEmpty())
				|| (getPerEstadoCivil() == null || getPerEstadoCivil().equals("S/N"))
				|| (getPerFechaNacimiento() == null) || (getPerGenero() == null || getPerGenero().equals("S/N"))
				|| (getPerNombres() == null || getPerNombres().isEmpty())
				|| (getPerTipoDni() == null || getPerTipoDni().equals("S/N"))
				|| (getPerTelefono() == null || getPerTelefono().isEmpty())) {
			setSms_validacion("Todos los datos generales son requeridos.");
			return true;
		} else if (!Funciones.validarEmail(getPerCorreo())) {
			setSms_validacion("EL formato del correo es incorrecto.");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo de seteo de datos
	 */
	public void cleanDatos() {
		setPerDni("");
		setPerTipoDni("");
		setPerNombres("");
		setPerApellidos("");
		setPerFechaNacimiento(null);
		setPerGenero("");
		setPerTelefono("");
		setPerCelular("");
		setPerCorreo("");
		setPerEstadoCivil("");
		setPerEstado("");
		// (persona-detalle)
		setPdeCiudadNacimiento("");
		setPdeCiudadResidencia("");
		setPdeCondicionCiudadana("");
		setPdeConyuge("");
		setPdeDireccion("");
		setPdeEmergContactoId("");
		setPdeEmergContactoNombres("");
		setPdeEmergContactoTelefono("");
		setPdeFechaDefuncion(null);
		setPdeFechaMatrimonio(null);
		setPdeFoto("");
		setPdeInscripcionDefuncion("");
		setPdeLugarNacimiento("");
		setPdeNacionalidadMadre("");
		setPdeNacionalidadPadre("");
		setPdeNombreMadre("");
		setPdeNombrePadre("");
		setPdeObservacion("");
		setPdePaisNacimiento("");
		setPdePaisResidencia("");
		setPdeProvinciaNacimiento("");
		setPdeProvinciaResidencia("");
		// salud
		setSldAlergias("");
		setSldAltura(null);
		setSldAsegurado("");
		setSldCarnetConadies("");
		setSldConsumeAlcohol("");
		setSldConsumeTabaco("");
		setSldDiscapacidadGrado("");
		setSldDiscapacidadTipo("");
		setSldFrecienciaConsumoMedicame("");
		setSldGrupoSanguineo("");
		setSldMedicamentosCronicos1("");
		setSldMedicamentosCronicos2("");
		setSldNivelAzucar("");
		setSldPeriodicidadEjercicio("");
		setSldPeso(null);
		setSldPresion("");
		setSldRealizaEjercicio(null);
		setSldVegetariano(null);
		setSldAlergiasCronicas2("");
		setSldEmbriagar(false);
		setSldMadreCausaMuerte("");
		setSldMadreEdad(null);
		setSldMadreEnfermedadesActuales("");
		setSldMadreFallecio(false);
		setSldNombreLugarCentroMedico("");
		setSldObservaciones("");
		setSldPadreCausaMuerte("");
		setSldPadreEdad(null);
		setSldPadreEnfermedadesActuales("");
		setSldPadreFallecio(false);
		setSldPeriodicidadAlcohol("");
		setSldPeriodicidadEmbriagar("");
		setSldPeriodicidadTabaco("");
		setEdicion(false);
	}

	// ////////////////////////////////////////////////////////SALUD/////////////////////////////////////////////////////////

	/**
	 * Permite la creacion de una SALUD
	 * 
	 * @return
	 */
	public void crearEditarSalud() {
		try {
			GenSalud sal = manager.SaludByID(getPerDni());
			if (sal == null) {
				manager.insertarSalud(getPerDni(), getSldAlergias(), getSldAltura(), getSldAsegurado(),
						getSldCarnetConadies(), getSldConsumeAlcohol(), getSldConsumeTabaco(), getSldDiscapacidadTipo(),
						getSldDiscapacidadGrado(), getSldFrecienciaConsumoMedicame(), getSldGrupoSanguineo(),
						getSldMedicamentosCronicos1(),getSldMedicamentosCronicos2(), getSldNivelAzucar(), getSldPeriodicidadEjercicio(), getSldPeso(),
						getSldPresion(), getSldRealizaEjercicio(), getSldVegetariano(), getSldAlergiasCronicas2(),
						getSldEmbriagar(), getSldMadreCausaMuerte(), getSldMadreEdad(),
						getSldMadreEnfermedadesActuales(), getSldMadreFallecio(), getSldNombreLugarCentroMedico(),
						getSldObservaciones(), getSldPadreCausaMuerte(), getSldPadreEdad(),
						getSldPadreEnfermedadesActuales(), getSldPadreFallecio(), getSldPeriodicidadAlcohol(),
						getSldPeriodicidadEmbriagar(), getSldPeriodicidadTabaco());
			} else {
				manager.editarSalud(getPerDni(), getSldAlergias(), getSldAltura(), getSldAsegurado(),
						getSldCarnetConadies(), getSldConsumeAlcohol(), getSldConsumeTabaco(), getSldDiscapacidadTipo(),
						getSldDiscapacidadGrado(), getSldFrecienciaConsumoMedicame(), getSldGrupoSanguineo(),
						getSldMedicamentosCronicos1(),getSldMedicamentosCronicos2(), getSldNivelAzucar(), getSldPeriodicidadEjercicio(), getSldPeso(),
						getSldPresion(), getSldRealizaEjercicio(), getSldVegetariano(), getSldAlergiasCronicas2(),
						getSldEmbriagar(), getSldMadreCausaMuerte(), getSldMadreEdad(),
						getSldMadreEnfermedadesActuales(), getSldMadreFallecio(), getSldNombreLugarCentroMedico(),
						getSldObservaciones(), getSldPadreCausaMuerte(), getSldPadreEdad(),
						getSldPadreEnfermedadesActuales(), getSldPadreFallecio(), getSldPeriodicidadAlcohol(),
						getSldPeriodicidadEmbriagar(), getSldPeriodicidadTabaco());
			}

		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
	}

	/**
	 * Metodo para cargar Salud para su edicion
	 * 
	 * @param persona
	 * @return
	 */
	public String cargarSalud(GenSalud salud) {
		try {
			setSldAlergias(salud.getSldAlergias());
			setSldAltura(salud.getSldAltura());
			setSldAsegurado(salud.getSldAsegurado());
			setSldCarnetConadies(salud.getSldCarnetConadies());
			setSldConsumeAlcohol(salud.getSldConsumeAlcohol());
			setSldConsumeTabaco(salud.getSldConsumeTabaco());
			setSldDiscapacidadGrado(salud.getSldDiscapacidadGrado());
			setSldDiscapacidadTipo(salud.getSldDiscapacidadTipo());
			setSldFrecienciaConsumoMedicame(salud.getSldFrecuenciaConsumoMedicame());
			setSldGrupoSanguineo(salud.getSldGrupoSanguineo());
			setSldMedicamentosCronicos1(salud.getSldMedicamentos());
			setSldMedicamentosCronicos2(salud.getSldMedicamentosCronicos2());
			setSldNivelAzucar(salud.getSldNivelAzucar());
			setSldPeriodicidadEjercicio(salud.getSldPeriodicidadEjercicio());
			setSldPeso(salud.getSldPeso());
			setSldPresion(salud.getSldPresion());
			setSldRealizaEjercicio(salud.getSldRealizaEjercicio());
			setSldVegetariano(salud.getSldVegetariano());
			setSldAlergiasCronicas2(salud.getSldAlergiasCronicas2());
			setSldEmbriagar(salud.getSldEmbriagar());
			setSldMadreCausaMuerte(salud.getSldMadreCausaMuerte());
			setSldMadreEdad(salud.getSldMadreEdad());
			setSldMadreEnfermedadesActuales(salud.getSldMadreEnfermedadesActuales());
			setSldMadreFallecio(salud.getSldMadreFallecio());
			setSldNombreLugarCentroMedico(salud.getSldNombreLugarCentroMedico());
			setSldObservaciones(salud.getSldObservaciones());
			setSldPadreCausaMuerte(salud.getSldPadreCausaMuerte());
			setSldPadreEdad(salud.getSldPadreEdad());
			setSldPadreEnfermedadesActuales(salud.getSldPadreEnfermedadesActuales());
			setSldPadreFallecio(salud.getSldPadreFallecio());
			setSldPeriodicidadAlcohol(salud.getSldPeriodicidadAlcohol());
			setSldPeriodicidadEmbriagar(salud.getSldPeriodicidadEmbriagar());
			setSldPeriodicidadTabaco(salud.getSldPeriodicidadTabaco());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "npersona?faces-redirect=true";
	}

	 /**
	 * Método para manejo de vista de aspectos del padre
	 */
	public void switch_padre(){
		setSldPadreCausaMuerte("");
		setSldPadreEnfermedadesActuales("");
	 if (getSldPadreFallecio()==true){
		 sld_padre=true;
	 }else{
		 sld_padre=false;
	 }
	 }
	
	/**
	 * Método para manejo de vista de aspectos de la madre
	 */
	public void switch_madre(){
		 setSldMadreEnfermedadesActuales("");
		 setSldMadreCausaMuerte("");
	 if (getSldMadreFallecio()==true){
		 sld_madre=true;
	 }else{
		 sld_madre=false;
	 }
	 }
}
