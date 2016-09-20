package city.controller.persona;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.rpc.ServiceException;

import org.hibernate.validator.constraints.NotBlank;
import org.json.simple.JSONObject;
import org.primefaces.context.RequestContext;

import antlr.ParserSharedInputState;
import city.controller.access.SesionBean;
import city.model.dao.entidades.GenCapacitacione;
import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenExperiencialaboral;
import city.model.dao.entidades.GenFamiliare;
import city.model.dao.entidades.GenFamiliarePK;
import city.model.dao.entidades.GenFormacionacademica;
import city.model.dao.entidades.GenPersona;
import city.model.dao.entidades.GenPersonaDetalle;
import city.model.dao.entidades.GenSalud;
import city.model.generic.Funciones;
import city.model.generic.Mensaje;
import city.model.manager.ManagerPersona;
import city.model.manager.ManagerWSDinardap;
import ec.gob.dinardap.interoperacion.interoperadorws.servicio.ClienteWS;
import ec.gob.dinardap.interoperacion.interoperadorws.servicio.FichaGeneral;
import ec.gob.dinardap.interoperacion.interoperadorws.servicio.Institucion;
import ec.gob.dinardap.interoperacion.interoperadorws.servicio.Registro;

@SessionScoped
@ManagedBean
public class PersonaBean {

	// Atributos de la Clase
	@EJB
	private ManagerPersona manager;

	@EJB
	private ManagerWSDinardap managerWS;

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
	private String perCorreo2;

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
	private String pdeEmergContactoTelefono2;
	private String pdeEmergContactoCorreo;
	private String pdeResidencia;
	private Integer pdeEstadiaDias;
	private Integer pdeEstadiaHoras;

	// Atributos de la clase Familiares
	private Integer famid;
	private String famEstabilidad;
	private Date famFechaNacimiento;
	private String famLabor;
	private String famLugar;
	private String famNombre;
	private String famTipo;

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
	private Boolean sldEstupefacientes;
	private String sldPeriodicidadEstupefacientes;
	private String sldEjercicioHoras;
	private Boolean sldDiscapacidad;
	private Boolean sldSeguroIess;
	private Boolean sldSeguroPrivado;
	private String sldTabacoSemana;
	private String sldAlergiasCronicas3;
	private String sldMedicamentosCronicos3;

	private boolean sld_padre;
	private boolean sld_madre;

	// atributos de la clase persona - institucion
	private String peiEstado;
	private Date peiFechaRegistro;
	private String peiRol;
	private String institucion;

	// manejo de vistas
	List<GenPersona> l_persona;
	List<GenFamiliare> l_familiares;
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
	List<SelectItem> l_residencia;
	List<SelectItem> l_dias;
	List<SelectItem> l_horas;
	List<SelectItem> l_fam_tipo;
	List<SelectItem> l_fam_actividad;
	List<SelectItem> l_fam_estabilidad;

	// manejo de booleanos
	private boolean seguro;
	private boolean ejercicio;
	private boolean alcohol;
	private boolean tabaco;
	private boolean estupefacientes;
	private boolean discapacidad;
	private boolean embriaguez;
	private boolean familia;
	private boolean dinardap;
	private boolean estabilidad;
	private boolean edad_p;
	private boolean edad_m;

	// valor de ediciï¿½n e inserciï¿½n
	private boolean edicion;
	private String datoBuscar;

	// mensaje de validaciï¿½n de campos
	private String sms_validacion;

	// valor de provincias y ciudades
	private boolean select_n;
	private boolean select_r;

	// valor de ususario
	private String usuario;

	// Curriculum Vitae

	private GenPersona persona;

	private List<SelectItem> lstAreasLE;
	private List<SelectItem> lstNivelesInstruccion;
	private List<SelectItem> lstTiposEventos;

	private List<SelectItem> lstNivelesAprobados;

	// Formacion Academica

	private boolean actualFA;
	private Integer idFormacion;

	@NotNull(message = "El campo Título no debe ser nulo.")
	@NotBlank(message = "El campo Título no debe contener solo espacios blancos.")
	private String titulo;

	@NotNull(message = "El campo Institución no debe ser nulo.")
	@NotBlank(message = "El campo Institución no debe contener solo espacios blancos.")
	private String institucionFA;
	private String nivelInstruccion;
	private String nivelesAprobados;

	@NotNull(message = "El campo Duración no debe ser nulo.")
	@DecimalMin("1")
	private BigDecimal duracion;

	private String paisFA;
	private boolean edicionFA;
	private boolean registroSenescyt;
	private Date fechaIniFA;
	private Date fechaFinFA;
	private GenFormacionacademica formAcademica;
	private GenFormacionacademica formAcEliminar;
	private List<GenFormacionacademica> lstFormAcademica;
	private boolean visualizarCampos;

	// Capacitaciones

	private Integer idCapacitacion;
	private boolean edicionCa;

	@NotNull(message = "El campo Nombre no debe ser nulo.")
	@NotBlank(message = "El campo Nombre no debe contener solo espacios blancos.")
	private String nombreCap;
	private String tipoEvento;

	@NotNull(message = "El campo Nombre InstituciÃ³n no debe ser nulo.")
	@NotBlank(message = "El campo Nombre InstituciÃ³n no debe contener solo espacios blancos.")
	private String nombreInstitucion;

	private boolean relacionPerfil;

	@Min(8)
	private int numHoras;
	private String areaCa;
	private GenCapacitacione capacitacion;
	private GenCapacitacione capaEliminar;
	private List<GenCapacitacione> lstCapacitaciones;

	// Experiencia Laboral

	private boolean actualEL;
	private boolean edicionEL;
	private Integer idExperiencia;

	@NotNull(message = "El campo Puesto no debe ser nulo.")
	@NotBlank(message = "El campo Puesto no debe contener solo espacios blancos.")
	private String puesto;

	@NotNull(message = "El campo Empresa no debe ser nulo.")
	@NotBlank(message = "El campo Empresa no debe contener solo espacios blancos.")
	private String empresa;
	private boolean sectorPublico;

	@NotNull(message = "El campo Responsabilidades no debe ser nulo.")
	@NotBlank(message = "El campo Responsabilidades no debe contener solo espacios blancos.")
	@Size(max = 500, message = "El campo Responsabilidades debe contener máximo 500 caracteres.")
	private String responsabilidades;
	private String paisEL;
	private String areaEL;
	private Date fechaIniEL;
	private Date fechaFinEL;
	private GenExperiencialaboral experienciaLab;
	private GenExperiencialaboral expLabEliminar;
	private List<GenExperiencialaboral> lstExperienciaLab;

	public PersonaBean() {
	}

	@PostConstruct
	public void init() {
		session.validarSesion();
		setearBooleanInit();
		cargarSelectItemsInit();
		l_persona = new ArrayList<GenPersona>();
		l_familiares = new ArrayList<GenFamiliare>();
		sms_validacion = "";

		// Curriculums
		lstAreasLE = new ArrayList<SelectItem>();
		lstNivelesInstruccion = new ArrayList<SelectItem>();
		lstTiposEventos = new ArrayList<SelectItem>();

		lstNivelesAprobados = new ArrayList<SelectItem>();
		nivelesAprobados = "S/N";

		sectorPublico = false;
		duracion = BigDecimal.ZERO;
		numHoras = 0;
		fechaIniEL = new Date();
		fechaIniFA = new Date();
		fechaFinEL = new Date();
		fechaFinFA = new Date();
		paisEL = "EC";
		paisFA = "EC";
		areaCa = "S/N";
		areaEL = "S/N";
		areaCa = "S/N";
		tipoEvento = "S/N";
		nivelInstruccion = "S/N";
		visualizarCampos = true;
		// this.carga();
//		cargarSesion();
	}

	/**
	 * @return the estabilidad
	 */
	public boolean isEstabilidad() {
		return estabilidad;
	}

	/**
	 * @param estabilidad
	 *            the estabilidad to set
	 */
	public void setEstabilidad(boolean estabilidad) {
		this.estabilidad = estabilidad;
	}

	/**
	 * @return the famid
	 */
	public Integer getFamid() {
		return famid;
	}

	/**
	 * @param famid
	 *            the famid to set
	 */
	public void setFamid(Integer famid) {
		this.famid = famid;
	}

	/**
	 * @return the famEstabilidad
	 */
	public String getFamEstabilidad() {
		return famEstabilidad;
	}

	/**
	 * @return the dinardap
	 */
	public boolean isDinardap() {
		return dinardap;
	}

	/**
	 * @param dinardap
	 *            the dinardap to set
	 */
	public void setDinardap(boolean dinardap) {
		this.dinardap = dinardap;
	}

	/**
	 * @param famEstabilidad
	 *            the famEstabilidad to set
	 */
	public void setFamEstabilidad(String famEstabilidad) {
		this.famEstabilidad = famEstabilidad;
	}

	/**
	 * @return the famFechaNacimiento
	 */
	public Date getFamFechaNacimiento() {
		return famFechaNacimiento;
	}

	/**
	 * @param famFechaNacimiento
	 *            the famFechaNacimiento to set
	 */
	public void setFamFechaNacimiento(Date famFechaNacimiento) {
		this.famFechaNacimiento = famFechaNacimiento;
	}

	/**
	 * @return the famLabor
	 */
	public String getFamLabor() {
		return famLabor;
	}

	/**
	 * @param famLabor
	 *            the famLabor to set
	 */
	public void setFamLabor(String famLabor) {
		this.famLabor = famLabor;
	}

	/**
	 * @return the famLugar
	 */
	public String getFamLugar() {
		return famLugar;
	}

	/**
	 * @param famLugar
	 *            the famLugar to set
	 */
	public void setFamLugar(String famLugar) {
		this.famLugar = famLugar;
	}

	/**
	 * @return the famNombre
	 */
	public String getFamNombre() {
		return famNombre;
	}

	/**
	 * @param famNombre
	 *            the famNombre to set
	 */
	public void setFamNombre(String famNombre) {
		this.famNombre = famNombre;
	}

	/**
	 * @return the famTipo
	 */
	public String getFamTipo() {
		return famTipo;
	}

	/**
	 * @param famTipo
	 *            the famTipo to set
	 */
	public void setFamTipo(String famTipo) {
		this.famTipo = famTipo;
	}

	/**
	 * @return the l_familiares
	 */
	public List<GenFamiliare> getL_familiares() {
		return l_familiares;
	}

	/**
	 * @param l_familiares
	 *            the l_familiares to set
	 */
	public void setL_familiares(List<GenFamiliare> l_familiares) {
		this.l_familiares = l_familiares;
	}

	/**
	 * @return the embriaguez
	 */
	public boolean isEmbriaguez() {
		return embriaguez;
	}

	/**
	 * @param embriaguez
	 *            the embriaguez to set
	 */
	public void setEmbriaguez(boolean embriaguez) {
		this.embriaguez = embriaguez;
	}

	/**
	 * @return the l_dias
	 */
	public List<SelectItem> getL_dias() {
		return l_dias;
	}

	/**
	 * @param l_dias
	 *            the l_dias to set
	 */
	public void setL_dias(List<SelectItem> l_dias) {
		this.l_dias = l_dias;
	}

	/**
	 * @return the l_horas
	 */
	public List<SelectItem> getL_horas() {
		return l_horas;
	}

	/**
	 * @param l_horas
	 *            the l_horas to set
	 */
	public void setL_horas(List<SelectItem> l_horas) {
		this.l_horas = l_horas;
	}

	/**
	 * @return the l_residencia
	 */
	public List<SelectItem> getL_residencia() {
		return l_residencia;
	}

	/**
	 * @param l_residencia
	 *            the l_residencia to set
	 */
	public void setL_residencia(List<SelectItem> l_residencia) {
		this.l_residencia = l_residencia;
	}

	/**
	 * @return the sldDiscapacidad
	 */
	public Boolean getSldDiscapacidad() {
		return sldDiscapacidad;
	}

	/**
	 * @param sldDiscapacidad
	 *            the sldDiscapacidad to set
	 */
	public void setSldDiscapacidad(Boolean sldDiscapacidad) {
		this.sldDiscapacidad = sldDiscapacidad;
	}

	/**
	 * @return the sldSeguroIess
	 */
	public Boolean getSldSeguroIess() {
		return sldSeguroIess;
	}

	/**
	 * @param sldSeguroIess
	 *            the sldSeguroIess to set
	 */
	public void setSldSeguroIess(Boolean sldSeguroIess) {
		this.sldSeguroIess = sldSeguroIess;
	}

	/**
	 * @return the sldSeguroPrivado
	 */
	public Boolean getSldSeguroPrivado() {
		return sldSeguroPrivado;
	}

	/**
	 * @param sldSeguroPrivado
	 *            the sldSeguroPrivado to set
	 */
	public void setSldSeguroPrivado(Boolean sldSeguroPrivado) {
		this.sldSeguroPrivado = sldSeguroPrivado;
	}

	/**
	 * @return the sldEjercicioHoras
	 */
	public String getSldEjercicioHoras() {
		return sldEjercicioHoras;
	}

	/**
	 * @param sldEjercicioHoras
	 *            the sldEjercicioHoras to set
	 */
	public void setSldEjercicioHoras(String sldEjercicioHoras) {
		this.sldEjercicioHoras = sldEjercicioHoras;
	}

	/**
	 * @return the sldTabacoSemana
	 */
	public String getSldTabacoSemana() {
		return sldTabacoSemana;
	}

	/**
	 * @param sldTabacoSemana
	 *            the sldTabacoSemana to set
	 */
	public void setSldTabacoSemana(String sldTabacoSemana) {
		this.sldTabacoSemana = sldTabacoSemana;
	}

	/**
	 * @return the sldAlergiasCronicas3
	 */
	public String getSldAlergiasCronicas3() {
		return sldAlergiasCronicas3;
	}

	/**
	 * @param sldAlergiasCronicas3
	 *            the sldAlergiasCronicas3 to set
	 */
	public void setSldAlergiasCronicas3(String sldAlergiasCronicas3) {
		this.sldAlergiasCronicas3 = sldAlergiasCronicas3;
	}

	/**
	 * @return the sldMedicamentosCronicos3
	 */
	public String getSldMedicamentosCronicos3() {
		return sldMedicamentosCronicos3;
	}

	/**
	 * @return the familia
	 */
	public boolean isFamilia() {
		return familia;
	}

	/**
	 * @param familia
	 *            the familia to set
	 */
	public void setFamilia(boolean familia) {
		this.familia = familia;
	}

	/**
	 * @param sldMedicamentosCronicos3
	 *            the sldMedicamentosCronicos3 to set
	 */
	public void setSldMedicamentosCronicos3(String sldMedicamentosCronicos3) {
		this.sldMedicamentosCronicos3 = sldMedicamentosCronicos3;
	}

	/**
	 * @return the pdeResidencia
	 */
	public String getPdeResidencia() {
		return pdeResidencia;
	}

	/**
	 * @param pdeResidencia
	 *            the pdeResidencia to set
	 */
	public void setPdeResidencia(String pdeResidencia) {
		this.pdeResidencia = pdeResidencia;
	}

	/**
	 * @return the pdeEstadiaDias
	 */
	public Integer getPdeEstadiaDias() {
		return pdeEstadiaDias;
	}

	/**
	 * @param pdeEstadiaDias
	 *            the pdeEstadiaDias to set
	 */
	public void setPdeEstadiaDias(Integer pdeEstadiaDias) {
		this.pdeEstadiaDias = pdeEstadiaDias;
	}

	/**
	 * @return the pdeEstadiaHoras
	 */
	public Integer getPdeEstadiaHoras() {
		return pdeEstadiaHoras;
	}

	/**
	 * @return the edad_p
	 */
	public boolean isEdad_p() {
		return edad_p;
	}

	/**
	 * @param edad_p
	 *            the edad_p to set
	 */
	public void setEdad_p(boolean edad_p) {
		this.edad_p = edad_p;
	}

	/**
	 * @return the edad_m
	 */
	public boolean isEdad_m() {
		return edad_m;
	}

	/**
	 * @param edad_m
	 *            the edad_m to set
	 */
	public void setEdad_m(boolean edad_m) {
		this.edad_m = edad_m;
	}

	/**
	 * @param pdeEstadiaHoras
	 *            the pdeEstadiaHoras to set
	 */
	public void setPdeEstadiaHoras(Integer pdeEstadiaHoras) {
		this.pdeEstadiaHoras = pdeEstadiaHoras;
	}

	/**
	 * @return the discapacidad
	 */
	public boolean isDiscapacidad() {
		return discapacidad;
	}

	/**
	 * @param discapacidad
	 *            the discapacidad to set
	 */
	public void setDiscapacidad(boolean discapacidad) {
		this.discapacidad = discapacidad;
	}

	/**
	 * @return the perCorreo2
	 */
	public String getPerCorreo2() {
		return perCorreo2;
	}

	/**
	 * @param perCorreo2
	 *            the perCorreo2 to set
	 */
	public void setPerCorreo2(String perCorreo2) {
		this.perCorreo2 = perCorreo2;
	}

	/**
	 * @return the sldEstupefacientes
	 */
	public Boolean getSldEstupefacientes() {
		return sldEstupefacientes;
	}

	/**
	 * @param sldEstupefacientes
	 *            the sldEstupefacientes to set
	 */
	public void setSldEstupefacientes(Boolean sldEstupefacientes) {
		this.sldEstupefacientes = sldEstupefacientes;
	}

	/**
	 * @return the sldPeriodicidadEstupefacientes
	 */
	public String getSldPeriodicidadEstupefacientes() {
		return sldPeriodicidadEstupefacientes;
	}

	/**
	 * @param sldPeriodicidadEstupefacientes
	 *            the sldPeriodicidadEstupefacientes to set
	 */
	public void setSldPeriodicidadEstupefacientes(String sldPeriodicidadEstupefacientes) {
		this.sldPeriodicidadEstupefacientes = sldPeriodicidadEstupefacientes;
	}

	/**
	 * @return the pdeEmergContactoTelefono2
	 */
	public String getPdeEmergContactoTelefono2() {
		return pdeEmergContactoTelefono2;
	}

	/**
	 * @param pdeEmergContactoTelefono2
	 *            the pdeEmergContactoTelefono2 to set
	 */
	public void setPdeEmergContactoTelefono2(String pdeEmergContactoTelefono2) {
		this.pdeEmergContactoTelefono2 = pdeEmergContactoTelefono2;
	}

	/**
	 * @return the pdeEmergContactoCorreo
	 */
	public String getPdeEmergContactoCorreo() {
		return pdeEmergContactoCorreo;
	}

	/**
	 * @param pdeEmergContactoCorreo
	 *            the pdeEmergContactoCorreo to set
	 */
	public void setPdeEmergContactoCorreo(String pdeEmergContactoCorreo) {
		this.pdeEmergContactoCorreo = pdeEmergContactoCorreo;
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
	 * @return the l_fam_tipo
	 */
	public List<SelectItem> getL_fam_tipo() {
		return l_fam_tipo;
	}

	/**
	 * @param l_fam_tipo
	 *            the l_fam_tipo to set
	 */
	public void setL_fam_tipo(List<SelectItem> l_fam_tipo) {
		this.l_fam_tipo = l_fam_tipo;
	}

	/**
	 * @return the l_fam_actividad
	 */
	public List<SelectItem> getL_fam_actividad() {
		return l_fam_actividad;
	}

	/**
	 * @param l_fam_actividad
	 *            the l_fam_actividad to set
	 */
	public void setL_fam_actividad(List<SelectItem> l_fam_actividad) {
		this.l_fam_actividad = l_fam_actividad;
	}

	/**
	 * @return the l_fam_estabilidad
	 */
	public List<SelectItem> getL_fam_estabilidad() {
		return l_fam_estabilidad;
	}

	/**
	 * @param l_fam_estabilidad
	 *            the l_fam_estabilidad to set
	 */
	public void setL_fam_estabilidad(List<SelectItem> l_fam_estabilidad) {
		this.l_fam_estabilidad = l_fam_estabilidad;
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
	 * @param sldConsumeAlcohol
	 *            the sldConsumeAlcohol to set
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
	 * @param sldConsumeTabaco
	 *            the sldConsumeTabaco to set
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
	 * @return the seguro
	 */
	public boolean isSeguro() {
		return seguro;
	}

	/**
	 * @param seguro
	 *            the seguro to set
	 */
	public void setSeguro(boolean seguro) {
		this.seguro = seguro;
	}

	/**
	 * @return the ejercicio
	 */
	public boolean isEjercicio() {
		return ejercicio;
	}

	/**
	 * @param ejercicio
	 *            the ejercicio to set
	 */
	public void setEjercicio(boolean ejercicio) {
		this.ejercicio = ejercicio;
	}

	/**
	 * @return the alcohol
	 */
	public boolean isAlcohol() {
		return alcohol;
	}

	/**
	 * @param alcohol
	 *            the alcohol to set
	 */
	public void setAlcohol(boolean alcohol) {
		this.alcohol = alcohol;
	}

	/**
	 * @return the tabaco
	 */
	public boolean isTabaco() {
		return tabaco;
	}

	/**
	 * @param tabaco
	 *            the tabaco to set
	 */
	public void setTabaco(boolean tabaco) {
		this.tabaco = tabaco;
	}

	/**
	 * @return the estupefacientes
	 */
	public boolean isEstupefacientes() {
		return estupefacientes;
	}

	/**
	 * @param estupefacientes
	 *            the estupefacientes to set
	 */
	public void setEstupefacientes(boolean estupefacientes) {
		this.estupefacientes = estupefacientes;
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
	public void FA(String institucion) {
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

	// Getters y Setters de Curriculum

	/**
	 * @return the lstAreasLE
	 */
	public List<SelectItem> getLstAreasLE() {
		return lstAreasLE;
	}

	/**
	 * @param lstAreasLE
	 *            the lstAreasLE to set
	 */
	public void setLstAreasLE(List<SelectItem> lstAreasLE) {
		this.lstAreasLE = lstAreasLE;
	}

	/**
	 * @return the lstNivelesInstruccion
	 */
	public List<SelectItem> getLstNivelesInstruccion() {
		return lstNivelesInstruccion;
	}

	/**
	 * @param lstNivelesInstruccion
	 *            the lstNivelesInstruccion to set
	 */
	public void setLstNivelesInstruccion(List<SelectItem> lstNivelesInstruccion) {
		this.lstNivelesInstruccion = lstNivelesInstruccion;
	}

	/**
	 * @return the lstTiposEventos
	 */
	public List<SelectItem> getLstTiposEventos() {
		return lstTiposEventos;
	}

	/**
	 * @param lstTiposEventos
	 *            the lstTiposEventos to set
	 */
	public void setLstTiposEventos(List<SelectItem> lstTiposEventos) {
		this.lstTiposEventos = lstTiposEventos;
	}

	/**
	 * @return the lstNivelesAprobados
	 */
	public List<SelectItem> getLstNivelesAprobados() {
		return lstNivelesAprobados;
	}

	/**
	 * @param lstNivelesAprobados
	 *            the lstNivelesAprobados to set
	 */
	public void setLstNivelesAprobados(List<SelectItem> lstNivelesAprobados) {
		this.lstNivelesAprobados = lstNivelesAprobados;
	}

	/**
	 * @return the actualFA
	 */
	public boolean isActualFA() {
		return actualFA;
	}

	/**
	 * @param actualFA
	 *            the actualFA to set
	 */
	public void setActualFA(boolean actualFA) {
		this.actualFA = actualFA;
	}

	/**
	 * @return the idFormacion
	 */
	public Integer getIdFormacion() {
		return idFormacion;
	}

	/**
	 * @param idFormacion
	 *            the idFormacion to set
	 */
	public void setIdFormacion(Integer idFormacion) {
		this.idFormacion = idFormacion;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the institucionFA
	 */
	public String getInstitucionFA() {
		return institucionFA;
	}

	/**
	 * @param institucionFA
	 *            the institucionFA to set
	 */
	public void setInstitucionFA(String institucionFA) {
		this.institucionFA = institucionFA;
	}

	/**
	 * @return the nivelInstruccion
	 */
	public String getNivelInstruccion() {
		return nivelInstruccion;
	}

	/**
	 * @param nivelInstruccion
	 *            the nivelInstruccion to set
	 */
	public void setNivelInstruccion(String nivelInstruccion) {
		this.nivelInstruccion = nivelInstruccion;
	}

	/**
	 * @return the nivelesAprobados
	 */
	public String getNivelesAprobados() {
		return nivelesAprobados;
	}

	/**
	 * @param nivelesAprobados
	 *            the nivelesAprobados to set
	 */
	public void setNivelesAprobados(String nivelesAprobados) {
		this.nivelesAprobados = nivelesAprobados;
	}

	/**
	 * @return the visualizarCampos
	 */
	public boolean isVisualizarCampos() {
		return visualizarCampos;
	}

	/**
	 * @param visualizarCampos
	 *            the visualizarCampos to set
	 */
	public void setVisualizarCampos(boolean visualizarCampos) {
		this.visualizarCampos = visualizarCampos;
	}

	/**
	 * @return the duracion
	 */
	public BigDecimal getDuracion() {
		return duracion;
	}

	/**
	 * @param duracion
	 *            the duracion to set
	 */
	public void setDuracion(BigDecimal duracion) {
		this.duracion = duracion;
	}

	/**
	 * @return the paisFA
	 */
	public String getPaisFA() {
		return paisFA;
	}

	/**
	 * @param paisFA
	 *            the paisFA to set
	 */
	public void setPaisFA(String paisFA) {
		this.paisFA = paisFA;
	}

	/**
	 * @return the edicionFA
	 */
	public boolean isEdicionFA() {
		return edicionFA;
	}

	/**
	 * @param edicionFA
	 *            the edicionFA to set
	 */
	public void setEdicionFA(boolean edicionFA) {
		this.edicionFA = edicionFA;
	}

	/**
	 * @return the registroSenescyt
	 */
	public boolean isRegistroSenescyt() {
		return registroSenescyt;
	}

	/**
	 * @param registroSenescyt
	 *            the registroSenescyt to set
	 */
	public void setRegistroSenescyt(boolean registroSenescyt) {
		this.registroSenescyt = registroSenescyt;
	}

	/**
	 * @return the fechaIniFA
	 */
	public Date getFechaIniFA() {
		return fechaIniFA;
	}

	/**
	 * @param fechaIniFA
	 *            the fechaIniFA to set
	 */
	public void setFechaIniFA(Date fechaIniFA) {
		this.fechaIniFA = fechaIniFA;
	}

	/**
	 * @return the fechaFinFA
	 */
	public Date getFechaFinFA() {
		return fechaFinFA;
	}

	/**
	 * @param fechaFinFA
	 *            the fechaFinFA to set
	 */
	public void setFechaFinFA(Date fechaFinFA) {
		this.fechaFinFA = fechaFinFA;
	}

	/**
	 * @return the formAcademica
	 */
	public GenFormacionacademica getFormAcademica() {
		return formAcademica;
	}

	/**
	 * @param formAcademica
	 *            the formAcademica to set
	 */
	public void setFormAcademica(GenFormacionacademica formAcademica) {
		this.formAcademica = formAcademica;
	}

	/**
	 * @return the formAcEliminar
	 */
	public GenFormacionacademica getFormAcEliminar() {
		return formAcEliminar;
	}

	/**
	 * @param formAcEliminar
	 *            the formAcEliminar to set
	 */
	public void setFormAcEliminar(GenFormacionacademica formAcEliminar) {
		this.formAcEliminar = formAcEliminar;
	}

	/**
	 * @return the lstFormAcademica
	 */
	public List<GenFormacionacademica> getLstFormAcademica() {
		return lstFormAcademica;
	}

	/**
	 * @param lstFormAcademica
	 *            the lstFormAcademica to set
	 */
	public void setLstFormAcademica(List<GenFormacionacademica> lstFormAcademica) {
		this.lstFormAcademica = lstFormAcademica;
	}

	/**
	 * @return the idCapacitacion
	 */
	public Integer getIdCapacitacion() {
		return idCapacitacion;
	}

	/**
	 * @param idCapacitacion
	 *            the idCapacitacion to set
	 */
	public void setIdCapacitacion(Integer idCapacitacion) {
		this.idCapacitacion = idCapacitacion;
	}

	/**
	 * @return the edicionCa
	 */
	public boolean isEdicionCa() {
		return edicionCa;
	}

	/**
	 * @param edicionCa
	 *            the edicionCa to set
	 */
	public void setEdicionCa(boolean edicionCa) {
		this.edicionCa = edicionCa;
	}

	/**
	 * @return the nombreCap
	 */
	public String getNombreCap() {
		return nombreCap;
	}

	/**
	 * @param nombreCap
	 *            the nombreCap to set
	 */
	public void setNombreCap(String nombreCap) {
		this.nombreCap = nombreCap;
	}

	/**
	 * @return the tipoEvento
	 */
	public String getTipoEvento() {
		return tipoEvento;
	}

	/**
	 * @param tipoEvento
	 *            the tipoEvento to set
	 */
	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	/**
	 * @return the nombreInstitucion
	 */
	public String getNombreInstitucion() {
		return nombreInstitucion;
	}

	/**
	 * @param nombreInstitucion
	 *            the nombreInstitucion to set
	 */
	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}

	/**
	 * @return the relacionPerfil
	 */
	public boolean isRelacionPerfil() {
		return relacionPerfil;
	}

	/**
	 * @param relacionPerfil
	 *            the relacionPerfil to set
	 */
	public void setRelacionPerfil(boolean relacionPerfil) {
		this.relacionPerfil = relacionPerfil;
	}

	/**
	 * @return the numHoras
	 */
	public int getNumHoras() {
		return numHoras;
	}

	/**
	 * @param numHoras
	 *            the numHoras to set
	 */
	public void setNumHoras(int numHoras) {
		this.numHoras = numHoras;
	}

	/**
	 * @return the areaCa
	 */
	public String getAreaCa() {
		return areaCa;
	}

	/**
	 * @param areaCa
	 *            the areaCa to set
	 */
	public void setAreaCa(String areaCa) {
		this.areaCa = areaCa;
	}

	/**
	 * @return the capacitacion
	 */
	public GenCapacitacione getCapacitacion() {
		return capacitacion;
	}

	/**
	 * @param capacitacion
	 *            the capacitacion to set
	 */
	public void setCapacitacion(GenCapacitacione capacitacion) {
		this.capacitacion = capacitacion;
	}

	/**
	 * @return the capaEliminar
	 */
	public GenCapacitacione getCapaEliminar() {
		return capaEliminar;
	}

	/**
	 * @param capaEliminar
	 *            the capaEliminar to set
	 */
	public void setCapaEliminar(GenCapacitacione capaEliminar) {
		this.capaEliminar = capaEliminar;
	}

	/**
	 * @return the lstCapacitaciones
	 */
	public List<GenCapacitacione> getLstCapacitaciones() {
		return lstCapacitaciones;
	}

	/**
	 * @param lstCapacitaciones
	 *            the lstCapacitaciones to set
	 */
	public void setLstCapacitaciones(List<GenCapacitacione> lstCapacitaciones) {
		this.lstCapacitaciones = lstCapacitaciones;
	}

	/**
	 * @return the actualEL
	 */
	public boolean isActualEL() {
		return actualEL;
	}

	/**
	 * @param actualEL
	 *            the actualEL to set
	 */
	public void setActualEL(boolean actualEL) {
		this.actualEL = actualEL;
	}

	/**
	 * @return the edicionEL
	 */
	public boolean isEdicionEL() {
		return edicionEL;
	}

	/**
	 * @param edicionEL
	 *            the edicionEL to set
	 */
	public void setEdicionEL(boolean edicionEL) {
		this.edicionEL = edicionEL;
	}

	/**
	 * @return the idExperiencia
	 */
	public Integer getIdExperiencia() {
		return idExperiencia;
	}

	/**
	 * @param idExperiencia
	 *            the idExperiencia to set
	 */
	public void setIdExperiencia(Integer idExperiencia) {
		this.idExperiencia = idExperiencia;
	}

	/**
	 * @return the puesto
	 */
	public String getPuesto() {
		return puesto;
	}

	/**
	 * @param puesto
	 *            the puesto to set
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the sectorPublico
	 */
	public boolean isSectorPublico() {
		return sectorPublico;
	}

	/**
	 * @param sectorPublico
	 *            the sectorPublico to set
	 */
	public void setSectorPublico(boolean sectorPublico) {
		this.sectorPublico = sectorPublico;
	}

	/**
	 * @return the responsabilidades
	 */
	public String getResponsabilidades() {
		return responsabilidades;
	}

	/**
	 * @param responsabilidades
	 *            the responsabilidades to set
	 */
	public void setResponsabilidades(String responsabilidades) {
		this.responsabilidades = responsabilidades;
	}

	/**
	 * @return the paisEL
	 */
	public String getPaisEL() {
		return paisEL;
	}

	/**
	 * @param paisEL
	 *            the paisEL to set
	 */
	public void setPaisEL(String paisEL) {
		this.paisEL = paisEL;
	}

	/**
	 * @return the areaEL
	 */
	public String getAreaEL() {
		return areaEL;
	}

	/**
	 * @param areaEL
	 *            the areaEL to set
	 */
	public void setAreaEL(String areaEL) {
		this.areaEL = areaEL;
	}

	/**
	 * @return the fechaIniEL
	 */
	public Date getFechaIniEL() {
		return fechaIniEL;
	}

	/**
	 * @param fechaIniEL
	 *            the fechaIniEL to set
	 */
	public void setFechaIniEL(Date fechaIniEL) {
		this.fechaIniEL = fechaIniEL;
	}

	/**
	 * @return the fechaFinEL
	 */
	public Date getFechaFinEL() {
		return fechaFinEL;
	}

	/**
	 * @param fechaFinEL
	 *            the fechaFinEL to set
	 */
	public void setFechaFinEL(Date fechaFinEL) {
		this.fechaFinEL = fechaFinEL;
	}

	/**
	 * @return the experienciaLab
	 */
	public GenExperiencialaboral getExperienciaLab() {
		return experienciaLab;
	}

	/**
	 * @param experienciaLab
	 *            the experienciaLab to set
	 */
	public void setExperienciaLab(GenExperiencialaboral experienciaLab) {
		this.experienciaLab = experienciaLab;
	}

	/**
	 * @return the expLabEliminar
	 */
	public GenExperiencialaboral getExpLabEliminar() {
		return expLabEliminar;
	}

	/**
	 * @param expLabEliminar
	 *            the expLabEliminar to set
	 */
	public void setExpLabEliminar(GenExperiencialaboral expLabEliminar) {
		this.expLabEliminar = expLabEliminar;
	}

	/**
	 * @return the lstExperienciaLab
	 */
	public List<GenExperiencialaboral> getLstExperienciaLab() {
		return lstExperienciaLab;
	}

	/**
	 * @param lstExperienciaLab
	 *            the lstExperienciaLab to set
	 */
	public void setLstExperienciaLab(List<GenExperiencialaboral> lstExperienciaLab) {
		this.lstExperienciaLab = lstExperienciaLab;
	}

	/**
	 * @return the persona
	 */
	public GenPersona getPersona() {
		return persona;
	}

	/**
	 * @param persona
	 *            the persona to set
	 */
	public void setPersona(GenPersona persona) {
		this.persona = persona;
	}

	/**
	 * Redirecciona a la pagina de creacion de personas
	 * 
	 * @return vista
	 */
	public String nuevaPersona() {
		setSelect_n(true);
		setSelect_r(true);
		setDinardap(false);
		getL_familiares().clear();
		cleanDatos();
		this.carga();
		return "npersona?faces-redirect=true";
	}

	/**
	 * Método para inicializar valores booleanos en el init()
	 */
	public void setearBooleanInit() {
		familia = false;
		estabilidad = false;
		edicion = false;
		select_n = true;
		select_r = true;
		sld_madre = false;
		sld_padre = false;
		seguro = false;
		ejercicio = false;
		alcohol = false;
		tabaco = false;
		estupefacientes = false;
		discapacidad = false;
		embriaguez = false;
		familia = false;
		dinardap = false;
		estabilidad = false;
		edad_p = false;
		edad_m = false;
	}

	/**
	 * Método para inicializar las listas de todos los SelectItems
	 */
	public void cargarSelectItemsInit() {
		l_ciudad_n = new ArrayList<SelectItem>();
		l_ciudad_r = new ArrayList<SelectItem>();
		l_estado_civil = new ArrayList<SelectItem>();
		l_estados = new ArrayList<SelectItem>();
		l_genero = new ArrayList<SelectItem>();
		l_pais = new ArrayList<SelectItem>();
		l_provincia = new ArrayList<SelectItem>();
		l_tipo_dni = new ArrayList<SelectItem>();
		l_sangre = new ArrayList<SelectItem>();
		l_residencia = new ArrayList<SelectItem>();
		l_dias = new ArrayList<SelectItem>();
		l_horas = new ArrayList<SelectItem>();
		l_discapacidad = new ArrayList<SelectItem>();
		l_fam_tipo = new ArrayList<SelectItem>();
		l_fam_actividad = new ArrayList<SelectItem>();
		l_fam_estabilidad = new ArrayList<SelectItem>();
	}

	/**
	 * Mï¿½todo para cargar todos los select
	 */
	public void carga() {
		cargarEstadoCivil();
		cargarGeneros();
		cargarPaises();
		cargarProvincias();
		cargarTiposDni();
		cargarTipoSangre();
		cargarTiposResidencia();
		cargarTiposFamiliares();
		cargarActividadFamiliares();
		cargarEstabilidadFamiliares();
		cargarDias();
		cargarHoras();
		cargarEstados();
		cargarDiscapacidad();

		// CV

		cargarAreasLaboralEstudio();
		cargarNivelesInstruccion();
		cargarTiposEventos();
	}

	/**
	 * Permite la creaciï¿½n o modificaciï¿½n de una persona
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
							getPerEstadoCivil().trim(), getPerCorreo2().trim());
					this.crearEditarPersonaDetalle();
					this.crearEditarSalud();
					Mensaje.crearMensajeINFO("Persona registrada correctamente");
					// setEdicion(true);
				} else {
					manager.editarPersona(getPerDni().trim(), getPerTipoDni().trim(), getPerNombres().trim(),
							getPerApellidos().trim(), getPerFechaNacimiento(), getPerGenero().trim(),
							getPerTelefono().trim(), getPerCelular().trim(), getPerCorreo().trim(),
							getPerEstadoCivil().trim(), getPerCorreo2(), getPerEstado());
					this.crearEditarPersonaDetalle();
					this.crearEditarSalud();
					Mensaje.crearMensajeINFO("Persona actualizada correctamente");
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
	 * Permite la creaciï¿½n o modificaciï¿½n de una persona
	 * 
	 * @return
	 */
	public String crearPersonaPersonal() {
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
							getPerEstadoCivil().trim(), getPerCorreo2().trim());
					this.crearEditarPersonaDetalle();
					this.crearEditarSalud();
					Mensaje.crearMensajeINFO("Persona creada correctamente");
					// setEdicion(true);
				} else {
					manager.editarPersona(getPerDni().trim(), getPerTipoDni().trim(), getPerNombres().trim(),
							getPerApellidos().trim(), getPerFechaNacimiento(), getPerGenero().trim(),
							getPerTelefono().trim(), getPerCelular().trim(), getPerCorreo().trim(),
							getPerEstadoCivil().trim(), getPerCorreo2(), getPerEstado());
					this.crearEditarPersonaDetalle();
					this.crearEditarSalud();
					Mensaje.crearMensajeINFO("Persona modificada correctamente");
				}
				r = "npersona2?faces-redirect=true";
				// this.cleanDatos();
				// this.cargarPersonas();
			}
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
		return r;
	}

	/**
	 * Metodo para cargar una Persona para su edición
	 * 
	 * @param persona
	 * @return
	 */
	public String cargarPersona(GenPersona persona) {
		try {
			this.carga();
			if (persona.getPerDinardap() == null || persona.getPerDinardap() == false) {
				dinardap = false;
			} else
				dinardap = true;
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
			setPerCorreo2(persona.getPerCorreo2());
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

			// setea la persona
			setPersona(persona);

			// carga de formaciÃ²n acadÃ¨mica
			findAllFormacionAcademica();

			// carga de capacitaciones
			findAllCapacitaciones();

			// cargar de experiencia laboral
			findAllExperianciaLaboral();

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

	public String cancelarFicha() {
		// this.cleanDatos();
		// this.cargarPersonas();
		getL_persona().clear();
		return "index?faces-redirect=true";
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
	 * Lista de Residencias
	 */
	public void cargarTiposResidencia() {
		getL_residencia().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_residencia");
		for (GenCatalogoItemsDet i : completo) {
			getL_residencia().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de Dias
	 */
	public void cargarDias() {
		getL_dias().clear();
		for (int i = 0; i <= 31; i++) {
			getL_dias().add(new SelectItem(i, "" + i));
		}
	}

	/**
	 * Lista de Horas
	 */
	public void cargarHoras() {
		getL_horas().clear();
		for (int i = 0; i <= 24; i++) {
			getL_horas().add(new SelectItem(i, "" + i));
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
	 * Lista de Tipos Familiares
	 */
	public void cargarTiposFamiliares() {
		getL_fam_tipo().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_fam_tipo");
		for (GenCatalogoItemsDet i : completo) {
			getL_fam_tipo().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de Activiades Familiares
	 */
	public void cargarActividadFamiliares() {
		getL_fam_actividad().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_fam_actividad");
		for (GenCatalogoItemsDet i : completo) {
			getL_fam_actividad().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de Estabilidad Familiares
	 */
	public void cargarEstabilidadFamiliares() {
		getL_fam_estabilidad().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_fam_estabilidad");
		for (GenCatalogoItemsDet i : completo) {
			getL_fam_estabilidad().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
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
			if (!i.getIteCodigo().trim().equals("P"))
				getL_estados().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Método para cambiar el char nombre por el nombre completo
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
	 * Mï¿½todo para buscar una persona
	 */
	public void buscarPersona() {
		l_persona.clear();
		if (datoBuscar == null || datoBuscar.isEmpty()) {
			Mensaje.crearMensajeWARN("No existe el dato para realizar la bï¿½squeda.");
		} else {
			l_persona = manager.buscarPersona(datoBuscar);
		}
	}

	public void mostrarDialog() {
		RequestContext.getCurrentInstance().execute("PF('dlgprincipal').show()");
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
						getPdeEmergContactoTelefono(), getPdeEmergContactoTelefono2(), getPdeEmergContactoCorreo(),
						getPdeInscripcionDefuncion(), getPdeFechaDefuncion(), getPdeObservacion(), getPdeResidencia(),
						getPdeEstadiaDias(), getPdeEstadiaHoras());
			} else {
				manager.editarPersonaDetalle(getPerDni(), getPdeFoto(), getPdePaisNacimiento(),
						getPdeProvinciaNacimiento(), getPdeCiudadNacimiento(), getPdeLugarNacimiento(),
						getPdePaisResidencia(), getPdeProvinciaResidencia(), getPdeCiudadResidencia(),
						getPdeDireccion(), getPdeCondicionCiudadana(), getPdeConyuge(), getPdeFechaMatrimonio(),
						getPdeNumHijos(), getPdeNombrePadre(), getPdeNacionalidadPadre(), getPdeNombreMadre(),
						getPdeNacionalidadMadre(), getPdeEmergContactoNombres(), getPdeEmergContactoId(),
						getPdeEmergContactoTelefono(), getPdeEmergContactoTelefono2(), getPdeEmergContactoCorreo(),
						getPdeInscripcionDefuncion(), getPdeFechaDefuncion(), getPdeObservacion(), getPdeResidencia(),
						getPdeEstadiaDias(), getPdeEstadiaHoras());
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
			setPdeEmergContactoTelefono2(persona.getPdeEmergContactoTelefono2());
			setPdeResidencia(persona.getPdeResidencia());
			setPdeEstadiaDias(persona.getPdeEstadiaDias());
			setPdeEstadiaHoras(persona.getPdeEstadiaHoras());
			setPdeEmergContactoCorreo(persona.getPdeEmergContactoCorreo());
			setL_familiares(manager.familiarByDNI(getPerDni()));
			// actualizaciï¿½n de lista de sitios
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
				|| (getPerCorreo() == null || getPerCorreo().isEmpty()
						|| Funciones.validarEmail(getPerCorreo()) == false)
				|| (getPerCorreo2() == null || getPerCorreo2().isEmpty()
						|| Funciones.validarEmail(getPerCorreo2()) == false)
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
		setPerCorreo2("");
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
		setPdeEmergContactoTelefono2("");
		setPdeEmergContactoCorreo("");
		setPdeResidencia("");
		setPdeEstadiaDias(null);
		setPdeEstadiaHoras(null);
		// salud
		setSldAlergias("");
		setSldAltura(null);
		setSldAsegurado("");
		setSldCarnetConadies("");
		setSldConsumeAlcohol("");
		setSldConsumeTabaco("");
		setSldDiscapacidadGrado("0");
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
		setSldEstupefacientes(false);
		setSldPeriodicidadEstupefacientes("");
		setSldSeguroPrivado(false);
		setSldSeguroIess(false);
		setSldDiscapacidad(false);
		setSldEjercicioHoras(null);
		setSldTabacoSemana(null);
		setSldAlergiasCronicas3("");
		setSldMedicamentosCronicos3("");
		setEdicion(false);
		//seteo de listas de CV
		getLstExperienciaLab().clear();
		getLstCapacitaciones().clear();
		getLstFormAcademica().clear();
	}

	// ////////////////////////////////////////////////////////FAMILIAR/////////////////////////////////////////////////////////

	public void cargarFamiliar(GenFamiliare familiar) {
		try {
			setFamEstabilidad(familiar.getFamEstabilidad());
			setFamFechaNacimiento(familiar.getFamFechaNacimiento());
			setFamLabor(familiar.getFamLabor());
			setFamLugar(familiar.getFamLugar());
			setFamTipo(familiar.getFamTipo());
			setFamid(familiar.getId().getFamId());
			setFamNombre(familiar.getFamNombre());
			RequestContext.getCurrentInstance().execute("PF('dlgeditar').show()");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Permite la creacion de un familiar
	 * 
	 * @return
	 */
	public void crearEditarFamiliar() {
		try {
			if (getPerDni() == null) {
				Mensaje.crearMensajeINFO(
						"Debe guardar la pestaña principal para poder almacenar los familares en el detalle.");
			} else {
				if (getFamid() == null) {
					setFamid(0);
				}
				GenFamiliare res_familiar = manager.findFamiliarByID(getFamid(), getPerDni());
				if (res_familiar == null) {
					GenFamiliarePK familiar_pk = new GenFamiliarePK();
					familiar_pk.setFamId(manager.familiarId());
					familiar_pk.setPdeDni(getPerDni());
					GenFamiliare familiar = new GenFamiliare();
					familiar.setId(familiar_pk);
					familiar.setFamEstabilidad(getFamEstabilidad());
					familiar.setFamFechaNacimiento(getFamFechaNacimiento());
					familiar.setFamLabor(getFamLabor());
					familiar.setFamLugar(getFamLugar().toUpperCase());
					familiar.setFamNombre(getFamNombre().toUpperCase());
					familiar.setFamTipo(getFamTipo());
					familiar.setGenPersonaDetalle(manager.PersonaDetalleByID(getPerDni()));
					verificarConyugeInsertar(familiar.getFamTipo(), familiar);
					setL_familiares(manager.familiarByDNI(getPerDni()));
				} else {
					res_familiar.setFamEstabilidad(getFamEstabilidad());
					res_familiar.setFamFechaNacimiento(getFamFechaNacimiento());
					res_familiar.setFamLabor(getFamLabor());
					res_familiar.setFamLugar(getFamLugar().toUpperCase());
					res_familiar.setFamNombre(getFamNombre().toUpperCase());
					res_familiar.setFamTipo(getFamTipo());
					manager.editarFamiliar(res_familiar);
					// verificarConyugeEditar(res_familiar.getFamTipo(),
					// res_familiar);
					setL_familiares(manager.familiarByDNI(getPerDni()));
				}
			}
			this.setearFamiliares();
			RequestContext.getCurrentInstance().execute("PF('dlgeditar').hide()");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verificarConyugeEditar(String tipo, GenFamiliare familiar) {
		try {
			if (tipo.equals("Conyuge")) {
				if (manager.FamiliarByConyuge(getPerDni())) {
					Mensaje.crearMensajeERROR("La persona ya cuenta con un Conyuge");
				} else {
					manager.editarFamiliar(familiar);
				}
			} else
				manager.editarFamiliar(familiar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void verificarConyugeInsertar(String tipo, GenFamiliare familiar) {
		try {
			if (tipo.equals("Conyuge")) {
				if (manager.FamiliarByConyuge(getPerDni())) {
					Mensaje.crearMensajeERROR("La persona ya cuenta con un Conyuge");
				} else {
					manager.insertarFamiliar(familiar);
				}
			} else
				manager.insertarFamiliar(familiar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void mostrarIngresoFamiliar() {
		this.setearFamiliares();
		RequestContext.getCurrentInstance().execute("PF('dlgeditar').show()");
	}

	public void setearFamiliares() {
		setFamEstabilidad("S/N");
		setFamFechaNacimiento(null);
		setFamLabor("S/N");
		setFamLugar("");
		setFamNombre("");
		setFamTipo("S/N");
	}

	public boolean validarCamposFamiliar() {
		if (getFamFechaNacimiento() == null) {
			System.err.println("fecha");
			return true;
		} else if (getFamLabor() == null || getFamLabor().equals("S/N")) {
			System.err.println("labor");
			return true;
		} else if (getFamLugar() == null || getFamLugar().equals("")) {
			System.err.println("lugar");
			return true;
		} else if (getFamNombre() == null || getFamNombre().equals("")) {
			System.err.println("nombre");
			return true;
		} else if (getFamTipo() == null || getFamTipo().equals("S/N")) {
			System.err.println("tipo");
			return true;
		} else {
			return false;
		}
	}

	public void eliminarFamiliar(GenFamiliare familiar) {
		try {
			getL_familiares().clear();
			manager.eliminarFamiliar(familiar);
			setL_familiares(manager.familiarByDNI(getPerDni()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cancelarFamiliar() {
		RequestContext.getCurrentInstance().execute("PF('dlgeditar').hide()");
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
				if (getSldDiscapacidadGrado() == null) {
					setSldDiscapacidadGrado("0");
				}
				if (getSldDiscapacidad() == null) {
					setSldDiscapacidad(false);
				}
				if (getSldSeguroPrivado() == null) {
					setSldSeguroPrivado(false);
				}
				if (getSldSeguroIess() == null) {
					setSldSeguroIess(false);
				}
				manager.insertarSalud(getPerDni(), getSldAlergias(), getSldAltura(), getSldAsegurado(),
						getSldCarnetConadies(), getSldConsumeAlcohol(), getSldConsumeTabaco(), getSldDiscapacidadTipo(),
						getSldDiscapacidadGrado(), getSldFrecienciaConsumoMedicame(), getSldGrupoSanguineo(),
						getSldMedicamentosCronicos1(), getSldMedicamentosCronicos2(), getSldNivelAzucar(),
						getSldPeriodicidadEjercicio(), getSldPeso(), getSldPresion(), getSldRealizaEjercicio(),
						getSldVegetariano(), getSldAlergiasCronicas2(), getSldEmbriagar(), getSldMadreCausaMuerte(),
						getSldMadreEdad(), getSldMadreEnfermedadesActuales(), getSldMadreFallecio(),
						getSldNombreLugarCentroMedico(), getSldObservaciones(), getSldPadreCausaMuerte(),
						getSldPadreEdad(), getSldPadreEnfermedadesActuales(), getSldPadreFallecio(),
						getSldPeriodicidadAlcohol(), getSldPeriodicidadEmbriagar(), getSldPeriodicidadTabaco(),
						getSldEstupefacientes(), getSldPeriodicidadEstupefacientes(), getSldSeguroIess(),
						getSldSeguroPrivado(), getSldDiscapacidad(), getSldEjercicioHoras(), getSldTabacoSemana(),
						getSldAlergiasCronicas3(), getSldMedicamentosCronicos3());
			} else {
				if (getSldDiscapacidad() == null) {
					setSldDiscapacidad(false);
				}
				if (getSldSeguroPrivado() == null) {
					setSldSeguroPrivado(false);
				}
				if (getSldSeguroIess() == null) {
					setSldSeguroIess(false);
				}
				manager.editarSalud(getPerDni(), getSldAlergias(), getSldAltura(), getSldAsegurado(),
						getSldCarnetConadies(), getSldConsumeAlcohol(), getSldConsumeTabaco(), getSldDiscapacidadTipo(),
						getSldDiscapacidadGrado(), getSldFrecienciaConsumoMedicame(), getSldGrupoSanguineo(),
						getSldMedicamentosCronicos1(), getSldMedicamentosCronicos2(), getSldNivelAzucar(),
						getSldPeriodicidadEjercicio(), getSldPeso(), getSldPresion(), getSldRealizaEjercicio(),
						getSldVegetariano(), getSldAlergiasCronicas2(), getSldEmbriagar(), getSldMadreCausaMuerte(),
						getSldMadreEdad(), getSldMadreEnfermedadesActuales(), getSldMadreFallecio(),
						getSldNombreLugarCentroMedico(), getSldObservaciones(), getSldPadreCausaMuerte(),
						getSldPadreEdad(), getSldPadreEnfermedadesActuales(), getSldPadreFallecio(),
						getSldPeriodicidadAlcohol(), getSldPeriodicidadEmbriagar(), getSldPeriodicidadTabaco(),
						getSldEstupefacientes(), getSldPeriodicidadEstupefacientes(), getSldSeguroIess(),
						getSldSeguroPrivado(), getSldDiscapacidad(), getSldEjercicioHoras(), getSldTabacoSemana(),
						getSldAlergiasCronicas3(), getSldMedicamentosCronicos3());
			}

		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
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
			if (salud.getSldDiscapacidad() == null) {
				salud.setSldDiscapacidad(false);
			}
			if (salud.getSldSeguroPrivado() == null) {
				salud.setSldSeguroPrivado(false);
			}
			if (salud.getSldRealizaEjercicio() == null) {
				salud.setSldRealizaEjercicio(false);
			}
			if (salud.getSldEmbriagar() == null) {
				salud.setSldEmbriagar(false);
			}
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
			setSldSeguroIess(salud.getSldSeguroIess());
			setSldSeguroPrivado(salud.getSldSeguroPrivado());
			setSldDiscapacidad(salud.getSldDiscapacidad());
			setSldEjercicioHoras("" + salud.getSldEjercicioHoras());
			setSldTabacoSemana("" + salud.getSldTabacoSemana());
			setSldAlergiasCronicas3(salud.getSldAlergiasCronicas3());
			setSldMedicamentosCronicos3(salud.getSldMedicamentosCronicos3());
			this.llenarBooleanos(getSldSeguroPrivado(), getSldDiscapacidad(), getSldRealizaEjercicio(),
					getSldConsumeAlcohol(), getSldEmbriagar(), getSldConsumeTabaco(), getSldPadreFallecio(),
					getSldMadreFallecio());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "npersona?faces-redirect=true";
	}

	public void llenarBooleanos(boolean seguro_privado_switch, boolean discapacidad_switch, boolean ejercicio_switch,
			String alcohol_switch, boolean embriago_switch, String tabaco_switch, boolean edad_padre,
			boolean edad_madre) {
		System.out.println(seguro_privado_switch + " " + discapacidad_switch + ejercicio_switch + alcohol_switch
				+ embriago_switch + tabaco_switch);
		seguro = verificarSwitch(seguro_privado_switch);
		ejercicio = verificarSwitch(ejercicio_switch);
		alcohol = verificarSwitch(alcohol_switch);
		tabaco = verificarSwitch(tabaco_switch);
		discapacidad = verificarSwitch(discapacidad_switch);
		embriaguez = verificarSwitch(embriago_switch);
		edad_m = verificarSwitch(edad_madre);
		edad_p = verificarSwitch(edad_padre);
	}

	public boolean verificarSwitch(boolean dato) {
		if (dato == false) {
			return false;
		} else {
			return true;
		}
	}

	public boolean verificarSwitch(String dato) {
		if (dato == null || dato.equals("false")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Mï¿½todo para manejo de vista de aspectos del padre
	 */
	public void switch_padre() {
		if (isEdad_p() == true) {
			setEdad_p(false);
		} else {
			setSldPadreEdad(0);
			setEdad_p(true);
		}
	}

	/**
	 * Mï¿½todo para manejo de vista de aspectos de la madre
	 */
	public void switch_madre() {
		if (isEdad_m() == true) {
			setEdad_m(false);
		} else {
			setSldMadreEdad(0);
			setEdad_m(true);
		}
	}

	public void switch_seguro() {
		if (isSeguro() == false) {
			setSeguro(true);
		} else if (isSeguro() == true) {
			setSldAsegurado("");
			setSeguro(false);
		}
	}

	public void switch_ejercicio() {
		if (isEjercicio() == false) {
			setEjercicio(true);
		} else if (isEjercicio() == true) {
			setSldEjercicioHoras("0");
			setSldPeriodicidadEjercicio("0");
			setEjercicio(false);
		}
	}

	public void switch_alcohol() {
		if (isAlcohol() == false) {
			setAlcohol(true);
		} else if (isAlcohol() == true) {
			setAlcohol(false);
			setSldPeriodicidadAlcohol("0");
		}
	}

	public void switch_embriaguez() {
		if (isEmbriaguez() == false)
			setEmbriaguez(true);
		else if (isEmbriaguez() == true) {
			setEmbriaguez(false);
			setSldPeriodicidadEmbriagar("0");
		}
	}

	public void switch_tabaco() {
		if (isTabaco() == false)
			setTabaco(true);
		else if (isTabaco() == true) {
			setTabaco(false);
			setSldPeriodicidadTabaco("0");
			setSldTabacoSemana("0");
		}
	}

	public void switch_estupefacientes() {
		if (isEstupefacientes() == false)
			setEstupefacientes(true);
		else if (isEstupefacientes() == true)
			setEstupefacientes(false);
	}

	public void switch_discapacidad() {
		if (isDiscapacidad() == false)
			setDiscapacidad(true);
		else if (isDiscapacidad() == true) {
			setSldCarnetConadies("");
			setSldDiscapacidadTipo("");
			setSldDiscapacidadGrado("0");
			setDiscapacidad(false);
		}
	}

	private String replaceSpecialChars(String input) {
		// Cadena de caracteres original a sustituir.
		String original = "Ã¡Ã Ã¤Ã©Ã¨Ã«Ã­Ã¬Ã¯Ã³Ã²Ã¶ÃºÃ¹uÃ±Ã�Ã€Ã„Ã‰ÃˆÃ‹Ã�ÃŒÃ�Ã“Ã’Ã–ÃšÃ™ÃœÃ‘Ã§Ã‡";
		// Cadena de caracteres ASCII que reemplazarÃ¡n los originales.
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		String output = input;
		for (int i = 0; i < original.length(); i++) {
			// Reemplazamos los caracteres especiales.
			output = output.replace(original.charAt(i), ascii.charAt(i));
		} // for i
		return output;
	}// remove1

	private String getItemName(List<SelectItem> list, String value) {

		String val = "";
		for (SelectItem selectItem : list) {
			String itemValue = replaceSpecialChars(selectItem.getLabel().toString().toLowerCase());
			String findValue = ".*" + replaceSpecialChars(value.toLowerCase()) + ".*";
			findValue = findValue.isEmpty() ? value.toLowerCase() : findValue;

			if (itemValue.matches(findValue)) {
				val = selectItem.getValue() + "";
				break;
			}
		}

		return val;
	}

	@SuppressWarnings("unchecked")
	public void loadWS() {

		try {
			if (!Funciones.validacionCedula(getPerDni())) {
				Mensaje.crearMensajeWARN("CÃ©dula incorrecta");
				return;
			}
			if (manager.PersonaByID(getPerDni()) != null) {
				Mensaje.crearMensajeWARN("Persona ya registrada");
				return;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ClienteWS conn = new ClienteWS("", "");

		if (this.perDni == null || this.perDni.isEmpty())
			return;
		try {
			// for (int i = 265; i < 275; i++) {

			FichaGeneral fichaGeneral = conn.getDatosInteroperabilidad(this.perDni, "");
			Institucion[] instituciones = fichaGeneral.getInstituciones();
			if (instituciones != null)

				for (Institucion institucion : instituciones) {
					Registro[] registros = institucion.getDatosPrincipales();
					if (registros != null) {
						JSONObject jsonResponse = new JSONObject();
						for (Registro registro : registros) {

							jsonResponse.put(registro.getCampo(),
									registro.getValor() == null ? "" : registro.getValor().trim());
							if (registro.getCodigo().equals("1")) {

								if (registro.getCampo().equals("cedula")) {

									this.setPerTipoDni("CÃ©dula");

								}

							} else if (registro.getCodigo().equals("2")) {
								StringTokenizer allName = new StringTokenizer(registro.getValor());

								String name = "";
								String lastName = "";

								int count = 0;
								while (allName.hasMoreTokens()) {
									if (count++ < 2)
										name += " " + allName.nextToken();
									else
										lastName += " " + allName.nextToken();
								}
								setPerNombres(name);
								setPerApellidos(lastName);
							} else if (registro.getCodigo().equals("3")) {

								if (registro.getValor().equals("HOMBRE"))
									this.setPerGenero("M");
								else
									this.setPerGenero("F");

							} else if (registro.getCodigo().equals("5")) {

								StringTokenizer allDate = new StringTokenizer(registro.getValor(), "/");

								if (allDate != null) {

									int date = Integer.parseInt(allDate.nextToken());
									int month = Integer.parseInt(allDate.nextToken());
									int year = Integer.parseInt(allDate.nextToken());
									this.perFechaNacimiento = new Date(year - 1900, month - 1, date);
								}

							} else if (registro.getCodigo().equals("6")) {
								StringTokenizer birthPlace = new StringTokenizer(registro.getValor(), "/");

								String province = "S/N";
								String city = "S/N";
								String place = "S/N";

								int count = 0;
								while (birthPlace.hasMoreTokens()) {
									if (++count == 1)
										province = birthPlace.nextToken();
									else if (count == 2)
										city = birthPlace.nextToken();
									else
										place = birthPlace.nextToken();
								}

								this.pdePaisNacimiento = getItemName(l_pais, "Ecuador");
								this.habilitarCamposNac();
								this.pdeProvinciaNacimiento = getItemName(l_provincia, province);
								cargarCiudadesNac(getItemName(l_provincia, province));
								this.pdeCiudadNacimiento = getItemName(l_ciudad_n, city);
								this.pdeLugarNacimiento = place;
							} else if (registro.getCodigo().equals("8")) {
								if (registro.getCampo().equals("estadoCivil"))
									this.setPerEstadoCivil(
											this.getItemName(this.getL_estado_civil(), registro.getValor()));

							} else if (registro.getCodigo().equals("10")) {
								if (registro.getCampo().equals("conyuge"))
									this.setPdeConyuge(registro.getValor());

							} else if (registro.getCodigo().equals("11")) {
								if (registro.getCampo().equals("nombrePadre"))
									this.setPdeNombrePadre(registro.getValor());

							} else if (registro.getCodigo().equals("13")) {
								if (registro.getCampo().equals("nombreMadre"))
									this.setPdeNombreMadre(registro.getValor());

							}
						}
						HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
								.getSession(false);
						SesionBean user = (SesionBean) session.getAttribute("sesionBean");
						String userName = user.getUsuario();

						try {
							managerWS.createWS(userName, "", jsonResponse.toJSONString().trim(),
									new Timestamp(new java.util.Date().getTime()));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			// }
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// *** MÃ¨todos de Curriculum

	/**
	 * Lista de Areas Laborales Estudio desde Catalogos
	 */
	public void cargarAreasLaboralEstudio() {
		getLstAreasLE().clear();
		getLstAreasLE().add(new SelectItem("S/N", "--Seleccione--"));
		for (GenCatalogoItemsDet i : manager.AllofItems("cat_areas_LE")) {
			getLstAreasLE().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Lista de Niveles de Instruccion desde Catalogos
	 */
	public void cargarNivelesInstruccion() {
		getLstNivelesInstruccion().clear();
		getLstNivelesInstruccion().add(new SelectItem("S/N", "--Seleccione--"));
		for (GenCatalogoItemsDet i : manager.AllItemsOrder("cat_nivel_instruccion")) {
			getLstNivelesInstruccion().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	public void cargarNivelesAprobadosEB() {
		getLstNivelesAprobados().clear();
		getLstNivelesAprobados().add(new SelectItem("S/N", "--Seleccione--"));
		for (GenCatalogoItemsDet i : manager.AllofItems("cat_nvl_aprobados_eb")) {
			getLstNivelesAprobados().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	public void cargarNivelesAprobadosBa() {
		getLstNivelesAprobados().clear();
		getLstNivelesAprobados().add(new SelectItem("S/N", "--Seleccione--"));
		for (GenCatalogoItemsDet i : manager.AllofItems("cat_nvl_aprobados_ba")) {
			getLstNivelesAprobados().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	public void cargaNivelesAp() {
		if (getNivelInstruccion().equals("nvlIns_1")) {
			cargarNivelesAprobadosEB();
			setTitulo("--");
			setInstitucionFA("--");
			setVisualizarCampos(false);

		} else if (getNivelInstruccion().equals("nvlIns_2")) {
			cargarNivelesAprobadosBa();
			setTitulo("--");
			setInstitucionFA("--");
			setVisualizarCampos(false);

		} else {
			setTitulo("");
			setInstitucionFA("");
			setVisualizarCampos(true);
			setNivelesAprobados("S/N");
			setLstNivelesAprobados(new ArrayList<SelectItem>());
		}

	}

	/**
	 * Lista de Tipos de Eventos desde Catalogos
	 */
	public void cargarTiposEventos() {
		getLstTiposEventos().clear();
		getLstTiposEventos().add(new SelectItem("S/N", "--Seleccione--"));
		for (GenCatalogoItemsDet i : manager.AllofItems("cat_tipo_evento")) {
			getLstTiposEventos().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

	/**
	 * Permite cambiar el nombre del boton de agregar a actualizar en el momento
	 * de edicion de datos de formacion academica, capacitacion y experiencia
	 * laboral
	 * 
	 * @return
	 */
	public String nombreBoton() {
		if (isEdicionFA() || isEdicionCa() || isEdicionEL()) {
			return "Actualizar";
		} else {
			return "Insertar";
		}
	}

	/********** formaciÃ²n acadÃ¨mica **********/

	/**
	 * Metodo para mostrar los campos de Formacion Academica en el formulario
	 * para su edicion
	 * 
	 * @param fAca
	 */
	public void cargarFormacionAc(GenFormacionacademica fAca) {
		try {
			setEdicionFA(true);
			if (fAca.getFoaNivelInstruccion().equals("nvlIns_1")) {
				cargarNivelesAprobadosEB();
				setNivelesAprobados(fAca.getFoaNivelesAprobados());
				setVisualizarCampos(false);
			} else if (fAca.getFoaNivelInstruccion().equals("nvlIns_2")) {
				cargarNivelesAprobadosBa();
				setNivelesAprobados(fAca.getFoaNivelesAprobados());
				setVisualizarCampos(false);
			} else {
				setLstNivelesAprobados(new ArrayList<SelectItem>());
				setVisualizarCampos(true);
			}
			setIdFormacion(fAca.getFoaId());
			setInstitucionFA(fAca.getFoaInstitucion());
			setTitulo(fAca.getFoaTitulo());
			setPaisFA(fAca.getFoaPais());
			setNivelInstruccion(fAca.getFoaNivelInstruccion());

			setDuracion(fAca.getFoaDuracion());
			setFechaIniFA(fAca.getFoaFechaInicio());
			setFechaFinFA(fAca.getFoaFechaFin());

			RequestContext.getCurrentInstance().execute("PF('fADlg').show();");
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
	}

	/**
	 * Metodo para agregar o editar FormaciÃ³n AcadÃ©mica
	 */
	public void agregarEditarFormAc() {
		try {
			if (validarSelectItemsFA()) {
				if (validarFechaFinFA()) {
					if (!isEdicionFA()) {
						manager.ingresarFormacionAc(getPersona(), getTitulo().trim(), getInstitucionFA().trim(),
								getFechaIniFA(), getFechaFinFA(), getNivelInstruccion(), getPaisFA(), getDuracion(),
								isRegistroSenescyt(), getNivelesAprobados());
						Mensaje.crearMensajeINFO("Datos guardados correctamente.");
					} else {
						manager.editarFormacionAc(getIdFormacion(), getTitulo().trim(), getInstitucionFA().trim(),
								getFechaIniFA(), getFechaFinFA(), getNivelInstruccion(), getPaisFA(), getDuracion(),
								getNivelesAprobados());
						Mensaje.crearMensajeINFO("Datos actualizados correctamente.");
					}
					limpiarCamposFA();
					findAllFormacionAcademica();
					RequestContext.getCurrentInstance().execute("PF('fADlg').hide();");
				} else {
					Mensaje.crearMensajeWARN("La fecha fin debe ser mayor a la fecha inicio.");
				}
			} else
				Mensaje.crearMensajeWARN("Seleccione todos los campos para continuar.");
		} catch (Exception e) {
			e.printStackTrace();
			Mensaje.crearMensajeERROR("Error al ingresar o editar la FormaciÃ³n AcadÃ©mica: " + e.getMessage());
		}
	}

	public void cancelarFA() {
		limpiarCamposFA();
		RequestContext.getCurrentInstance().execute("PF('fADlg').hide();");
	}

	public void limpiarCamposFA() {
		setIdFormacion(0);
		setTitulo("");
		setInstitucionFA("");
		setPaisFA("EC");
		setDuracion(BigDecimal.ZERO);
		setFechaIniFA(new Date());
		setFechaFinFA(new Date());
		setNivelInstruccion("S/N");
		setVisualizarCampos(true);
		setNivelesAprobados("S/N");

		setEdicionFA(false);

	}

	public boolean validarSelectItemsFA() {
		if (getPaisFA().equals("S/N") || getNivelInstruccion().equals("S/N")) {
			return false;
		} else if (getNivelesAprobados().equals("S/N")
				&& (getNivelInstruccion().equals("nvlIns_1") || getNivelInstruccion().equals("nvlIns_1"))) {
			return false;
		} else
			return true;
	}

	public boolean validarSelectItemsCa() {
		if (getAreaCa().equals("S/N") || getTipoEvento().equals("S/N")) {
			return false;
		} else
			return true;
	}

	public boolean validarSelectItemsEL() {
		if (getAreaEL().equals("S/N") || getPaisEL().equals("S/N")) {
			return false;
		} else
			return true;
	}

	private boolean validarFechaFinFA() {
		if (!getFechaFinFA().before(getFechaIniFA())) {
			return true;
		} else {
			return false;
		}
	}

	private boolean validarFechaFinEL() {
		if (!isActualEL()) {
			if (!getFechaFinEL().before(getFechaIniEL())) {
				return true;
			} else
				return false;
		} else
			return true;
	}

	/**
	 * Mostrar dialogo de confirmaciÃ³n y setear la formacion academica
	 * 
	 * @param fa
	 */
	public void dialogElFormAcademica(GenFormacionacademica fa) {
		setFormAcEliminar(fa);
		RequestContext.getCurrentInstance().execute("PF('dlgefa').show();");
	}

	/**
	 * Metodo para eliminar FormaciÃ³n AcadÃ©mica
	 */
	public void eliminarFormacionAca() {
		try {
			manager.eliminarFormAcademica(getFormAcEliminar());
			Mensaje.crearMensajeINFO("Datos eliminados correctamente.");
			findAllFormacionAcademica();
			// liberar
			setFormAcEliminar(null);

		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
	}

	public void findAllFormacionAcademica() {
		setLstFormAcademica(manager.findFormAcademicaBYCedula(getPersona().getPerDni()));

	}

	public void mostrarDlgFormacionAca() {
		RequestContext.getCurrentInstance().execute("PF('fADlg').show();");
	}

	public String validarBoleano(boolean valor) {
		String respuesta = "";
		try {
			if (valor == true) {
				respuesta = "Si";
			} else {
				respuesta = "No";
			}
		} catch (Exception e) {
			e.printStackTrace();
			Mensaje.crearMensajeERROR("Error al validar campos Boleanos. " + e.getMessage());
		}
		return respuesta;
	}

	public String validarItemCatalogo(String item) {
		String respuesta = "";
		try {
			respuesta = manager.catalogoItem(item);

		} catch (Exception e) {
			e.printStackTrace();
			Mensaje.crearMensajeERROR("Error al validar Item. " + e.getMessage());
		}
		return respuesta;
	}

	/********** capacitaciones **********/

	/**
	 * Metodo para mostrar los campos de Capacitaciones en el formulario para su
	 * edicion
	 * 
	 * @param cap
	 */
	public void cargarCapacitacion(GenCapacitacione cap) {
		try {
			setEdicionCa(true);
			setIdCapacitacion(cap.getCapId());
			setAreaCa(cap.getCapAreaLaboralEstudio());
			setNombreCap(cap.getCapNombre());
			setTipoEvento(cap.getCapTipoEvento());
			setNumHoras(cap.getCapNumHoras());
			setNombreInstitucion(cap.getCapInstitucionCapacitacion());
			setRelacionPerfil(cap.getCapRelacionPerfilProfesional());

			RequestContext.getCurrentInstance().execute("PF('cADlg').show();");
		} catch (Exception e) {
			e.printStackTrace();
			Mensaje.crearMensajeERROR(e.getMessage());
		}

	}

	public void agregarEditarCapacitaciones() {
		try {
			if (validarSelectItemsCa()) {
				if (!isEdicionCa()) {
					manager.ingresarCapacitaciones(getPersona(), isRelacionPerfil(), getNombreCap().trim(),
							getNombreInstitucion().trim(), getAreaCa(), getTipoEvento(), getNumHoras());
					Mensaje.crearMensajeINFO("Datos guardados correctamente.");
				} else {
					manager.editarCapacitaciones(getIdCapacitacion(), isRelacionPerfil(), getNombreCap().trim(),
							getNombreInstitucion().trim(), getAreaCa(), getTipoEvento(), getNumHoras());
					Mensaje.crearMensajeINFO("Datos actualizados correctamente.");
				}
				limpiarCamposCa();
				findAllCapacitaciones();
				RequestContext.getCurrentInstance().execute("PF('cADlg').hide();");
			} else
				Mensaje.crearMensajeWARN("Seleccione todos los campos para continuar.");
		} catch (Exception e) {
			e.printStackTrace();
			Mensaje.crearMensajeERROR("Error al ingresar o editar CapacitaciÃ³n: " + e.getMessage());
		}
	}

	public void cancelarCa() {
		limpiarCamposCa();
		RequestContext.getCurrentInstance().execute("PF('cADlg').hide();");
	}

	public void limpiarCamposCa() {
		setIdCapacitacion(0);
		setRelacionPerfil(false);
		setNombreCap("");
		setNombreInstitucion("");
		setAreaCa("S/N");
		setTipoEvento("S/N");
		setNumHoras(0);

		setEdicionCa(false);
	}

	public void dialogElCapacitacion(GenCapacitacione ca) {
		setCapaEliminar(ca);
		RequestContext.getCurrentInstance().execute("PF('dlgeC').show();");
	}

	public void eliminarCapacitaciones() {
		try {
			manager.eliminarCapacitaciones(getCapaEliminar());
			Mensaje.crearMensajeINFO("Datos eliminados correctamente.");
			findAllCapacitaciones();
			// liberar
			setCapaEliminar(null);

		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
	}

	public void mostrarDlgCapacitacion() {
		RequestContext.getCurrentInstance().execute("PF('cADlg').show();");
	}

	public void findAllCapacitaciones() {
		setLstCapacitaciones(manager.findCapacitacionesByCedula(getPersona().getPerDni()));
	}

	/********** experiencia laboral **********/

	/**
	 * Metodo para mostrar los campos de Experiencia laboral en el formulario
	 * para su edicion
	 * 
	 * @param exl
	 */
	public void cargarExperienciaLab(GenExperiencialaboral exl) {
		try {
			setEdicionEL(true);
			setIdExperiencia(exl.getExlId());
			setAreaEL(exl.getExlAreaLaboralEstudio());
			setPuesto(exl.getExlPuesto());
			setEmpresa(exl.getExlEmpresa());
			setSectorPublico(exl.getExlSectorPublico());
			setPaisEL(exl.getExlPais());
			setFechaIniEL(exl.getExlFechaInicio());
			setFechaFinEL(exl.getExlFechaFin());
			setResponsabilidades(exl.getExlResponsabilidades());
			setActualEL(exl.getExlActual());
			if (exl.getExlActual().equals("S"))
				setFechaFinEL(new Date());
			RequestContext.getCurrentInstance().execute("PF('eLDlg').show();");
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
	}

	public void agregarEditarExperienciaLab() {
		try {
			if (validarSelectItemsEL()) {
				if (validarFechaFinEL()) {
					if (!isEdicionEL()) {

						manager.ingresarExperienciaLab(getPersona(), getAreaEL(), getPuesto().trim(),
								getEmpresa().trim(), isSectorPublico(), getPaisEL(), getFechaIniEL(), getFechaFinEL(),
								getResponsabilidades().trim(), isActualEL());
						Mensaje.crearMensajeINFO("Datos guardados correctamente.");
					} else {
						manager.editarExperienciaLab(getIdExperiencia(), getAreaEL(), getPuesto(), getEmpresa(),
								isSectorPublico(), getPaisEL(), getFechaIniEL(), getFechaFinEL(),
								getResponsabilidades(), isActualEL());
						Mensaje.crearMensajeINFO("Datos actualizados correctamente.");
					}
					limpiarCamposEL();
					findAllExperianciaLaboral();
					RequestContext.getCurrentInstance().execute("PF('eLDlg').hide();");
				} else {
					Mensaje.crearMensajeWARN("La fecha fin debe ser mayor a la fecha inicio.");
				}
			} else
				Mensaje.crearMensajeWARN("Seleccione todos los campos para continuar.");
		} catch (Exception e) {
			e.printStackTrace();
			Mensaje.crearMensajeERROR("Error al ingresar o editar Experiencia Laboral: " + e.getMessage());
		}
	}

	public void cancelarEL() {
		limpiarCamposEL();
		RequestContext.getCurrentInstance().execute("PF('eLDlg').hide();");
	}

	public void limpiarCamposEL() {
		setIdExperiencia(0);
		setAreaEL("S/N");
		setPuesto("");
		setEmpresa("");
		setSectorPublico(false);
		setPaisEL("EC");
		setFechaIniEL(new Date());
		setFechaFinEL(new Date());
		setResponsabilidades("");
		setActualEL(false);

		setEdicionEL(false);

	}

	public void dialogElExpeLaboral(GenExperiencialaboral el) {
		setExpLabEliminar(el);
		RequestContext.getCurrentInstance().execute("PF('dlgeEl').show();");
	}

	public void eliminarExperienciaLab() {
		try {
			manager.eliminarExperienciaLab(getExpLabEliminar());
			Mensaje.crearMensajeINFO("Datos eliminados correctamente.");
			findAllExperianciaLaboral();
			setExpLabEliminar(null);

		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
	}

	public void mostrarDlgExperienciaLa() {
		RequestContext.getCurrentInstance().execute("PF('eLDlg').show();");
	}

	public void findAllExperianciaLaboral() {
		setLstExperienciaLab(manager.findExperienciaLabByCedula(getPersona().getPerDni()));
	}

	public String validarPaisEL(GenExperiencialaboral el) {
		String pais = "";
		try {
			pais = manager.catalogoItem(el.getExlPais());

		} catch (Exception e) {
			e.printStackTrace();
			Mensaje.crearMensajeERROR("Error al validar País Formación Académica. " + e.getMessage());
		}
		return pais;
	}

	public void switch_actualFA() {
		if (isActualFA() == true) {
			setFechaFinFA(null);
		} else {
			setFechaFinFA(new Date());
		}
	}

	public void switch_actualEL() {
		if (isActualEL() == true) {
			setFechaFinEL(null);
		} else {
			setFechaFinEL(new Date());
		}
	}
	
	public void cargarSesion(){
		cargarPersona(session.validarPersona("npersona2.xhtml"));
	}
}
