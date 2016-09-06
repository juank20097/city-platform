package city.model.dao.entidades;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the gen_persona_detalle database table.
 * 
 */
@Entity
@Table(name = "gen_persona_detalle")
@NamedQuery(name = "GenPersonaDetalle.findAll", query = "SELECT g FROM GenPersonaDetalle g")
public class GenPersonaDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pde_dni")
	private String pdeDni;

	@Column(name = "pde_ciudad_nacimiento")
	private String pdeCiudadNacimiento;

	@Column(name = "pde_ciudad_residencia")
	private String pdeCiudadResidencia;

	@Column(name = "pde_condicion_ciudadana")
	private String pdeCondicionCiudadana;

	@Column(name = "pde_conyuge")
	private String pdeConyuge;

	@Column(name = "pde_direccion")
	private String pdeDireccion;

	@Column(name = "pde_emerg_contacto_id")
	private String pdeEmergContactoId;

	@Column(name = "pde_emerg_contacto_nombres")
	private String pdeEmergContactoNombres;

	@Column(name = "pde_emerg_contacto_telefono")
	private String pdeEmergContactoTelefono;

	@Temporal(TemporalType.DATE)
	@Column(name = "pde_fecha_defuncion")
	private Date pdeFechaDefuncion;

	@Temporal(TemporalType.DATE)
	@Column(name = "pde_fecha_matrimonio")
	private Date pdeFechaMatrimonio;

	@Column(name = "pde_foto")
	private String pdeFoto;

	@Column(name = "pde_huella")
	private byte[] pdeHuella;

	@Column(name = "pde_inscripcion_defuncion")
	private String pdeInscripcionDefuncion;

	@Column(name = "pde_lugar_nacimiento")
	private String pdeLugarNacimiento;

	@Column(name = "pde_nacionalidad_madre")
	private String pdeNacionalidadMadre;

	@Column(name = "pde_nacionalidad_padre")
	private String pdeNacionalidadPadre;

	@Column(name = "pde_nombre_madre")
	private String pdeNombreMadre;

	@Column(name = "pde_nombre_padre")
	private String pdeNombrePadre;

	@Column(name = "pde_num_hijos")
	private Integer pdeNumHijos;

	@Column(name = "pde_observacion")
	private String pdeObservacion;

	@Column(name = "pde_pais_nacimiento")
	private String pdePaisNacimiento;

	@Column(name = "pde_pais_residencia")
	private String pdePaisResidencia;

	@Column(name = "pde_provincia_nacimiento")
	private String pdeProvinciaNacimiento;

	@Column(name = "pde_provincia_residencia")
	private String pdeProvinciaResidencia;

	@Column(name = "pde_emerg_contacto_telefono2")
	private String pdeEmergContactoTelefono2;

	@Column(name = "pde_emerg_contacto_correo")
	private String pdeEmergContactoCorreo;

	@Column(name = "pde_residencia")
	private String pdeResidencia;

	@Column(name = "pde_estadia_dias")
	private Integer pdeEstadiaDias;

	@Column(name = "pde_estadia_horas")
	private Integer pdeEstadiaHoras;

	// bi-directional one-to-one association to GenPersona
	@OneToOne
	@JoinColumn(name = "pde_dni")
	private GenPersona genPersona;

	public GenPersonaDetalle() {
	}

	public String getPdeDni() {
		return this.pdeDni;
	}

	public void setPdeDni(String pdeDni) {
		this.pdeDni = pdeDni;
	}

	public Integer getPdeNumHijos() {
		return this.pdeNumHijos;
	}

	public void setPdeNumHijos(Integer pdeNumHijos) {
		this.pdeNumHijos = pdeNumHijos;
	}

	public String getPdeCiudadNacimiento() {
		return this.pdeCiudadNacimiento;
	}

	public void setPdeCiudadNacimiento(String pdeCiudadNacimiento) {
		this.pdeCiudadNacimiento = pdeCiudadNacimiento;
	}

	public String getPdeCiudadResidencia() {
		return this.pdeCiudadResidencia;
	}

	public void setPdeCiudadResidencia(String pdeCiudadResidencia) {
		this.pdeCiudadResidencia = pdeCiudadResidencia;
	}

	public String getPdeCondicionCiudadana() {
		return this.pdeCondicionCiudadana;
	}

	public void setPdeCondicionCiudadana(String pdeCondicionCiudadana) {
		this.pdeCondicionCiudadana = pdeCondicionCiudadana;
	}

	public String getPdeConyuge() {
		return this.pdeConyuge;
	}

	public void setPdeConyuge(String pdeConyuge) {
		this.pdeConyuge = pdeConyuge;
	}

	public String getPdeDireccion() {
		return this.pdeDireccion;
	}

	public void setPdeDireccion(String pdeDireccion) {
		this.pdeDireccion = pdeDireccion;
	}

	public String getPdeEmergContactoId() {
		return this.pdeEmergContactoId;
	}

	public void setPdeEmergContactoId(String pdeEmergContactoId) {
		this.pdeEmergContactoId = pdeEmergContactoId;
	}

	public String getPdeEmergContactoNombres() {
		return this.pdeEmergContactoNombres;
	}

	public void setPdeEmergContactoNombres(String pdeEmergContactoNombres) {
		this.pdeEmergContactoNombres = pdeEmergContactoNombres;
	}

	public String getPdeEmergContactoTelefono() {
		return this.pdeEmergContactoTelefono;
	}

	public void setPdeEmergContactoTelefono(String pdeEmergContactoTelefono) {
		this.pdeEmergContactoTelefono = pdeEmergContactoTelefono;
	}

	public Date getPdeFechaDefuncion() {
		return this.pdeFechaDefuncion;
	}

	public void setPdeFechaDefuncion(Date pdeFechaDefuncion) {
		this.pdeFechaDefuncion = pdeFechaDefuncion;
	}

	public Date getPdeFechaMatrimonio() {
		return this.pdeFechaMatrimonio;
	}

	public void setPdeFechaMatrimonio(Date pdeFechaMatrimonio) {
		this.pdeFechaMatrimonio = pdeFechaMatrimonio;
	}

	public String getPdeFoto() {
		return this.pdeFoto;
	}

	public void setPdeFoto(String pdeFoto) {
		this.pdeFoto = pdeFoto;
	}

	public byte[] getPdeHuella() {
		return this.pdeHuella;
	}

	public void setPdeHuella(byte[] pdeHuella) {
		this.pdeHuella = pdeHuella;
	}

	public String getPdeInscripcionDefuncion() {
		return this.pdeInscripcionDefuncion;
	}

	public void setPdeInscripcionDefuncion(String pdeInscripcionDefuncion) {
		this.pdeInscripcionDefuncion = pdeInscripcionDefuncion;
	}

	public String getPdeLugarNacimiento() {
		return this.pdeLugarNacimiento;
	}

	public void setPdeLugarNacimiento(String pdeLugarNacimiento) {
		this.pdeLugarNacimiento = pdeLugarNacimiento;
	}

	public String getPdeNacionalidadMadre() {
		return this.pdeNacionalidadMadre;
	}

	public void setPdeNacionalidadMadre(String pdeNacionalidadMadre) {
		this.pdeNacionalidadMadre = pdeNacionalidadMadre;
	}

	public String getPdeNacionalidadPadre() {
		return this.pdeNacionalidadPadre;
	}

	public void setPdeNacionalidadPadre(String pdeNacionalidadPadre) {
		this.pdeNacionalidadPadre = pdeNacionalidadPadre;
	}

	public String getPdeNombreMadre() {
		return this.pdeNombreMadre;
	}

	public void setPdeNombreMadre(String pdeNombreMadre) {
		this.pdeNombreMadre = pdeNombreMadre;
	}

	public String getPdeNombrePadre() {
		return this.pdeNombrePadre;
	}

	public void setPdeNombrePadre(String pdeNombrePadre) {
		this.pdeNombrePadre = pdeNombrePadre;
	}

	public String getPdeObservacion() {
		return this.pdeObservacion;
	}

	public void setPdeObservacion(String pdeObservacion) {
		this.pdeObservacion = pdeObservacion;
	}

	public String getPdePaisNacimiento() {
		return this.pdePaisNacimiento;
	}

	public void setPdePaisNacimiento(String pdePaisNacimiento) {
		this.pdePaisNacimiento = pdePaisNacimiento;
	}

	public String getPdePaisResidencia() {
		return this.pdePaisResidencia;
	}

	public void setPdePaisResidencia(String pdePaisResidencia) {
		this.pdePaisResidencia = pdePaisResidencia;
	}

	public String getPdeProvinciaNacimiento() {
		return this.pdeProvinciaNacimiento;
	}

	public void setPdeProvinciaNacimiento(String pdeProvinciaNacimiento) {
		this.pdeProvinciaNacimiento = pdeProvinciaNacimiento;
	}

	public String getPdeProvinciaResidencia() {
		return this.pdeProvinciaResidencia;
	}

	public void setPdeProvinciaResidencia(String pdeProvinciaResidencia) {
		this.pdeProvinciaResidencia = pdeProvinciaResidencia;
	}

	public GenPersona getGenPersona() {
		return this.genPersona;
	}

	public void setGenPersona(GenPersona genPersona) {
		this.genPersona = genPersona;
	}

	public String getPdeEmergContactoTelefono2() {
		return this.pdeEmergContactoTelefono2;
	}

	public void setPdeEmergContactoTelefono2(String pdeEmergContactoTelefono2) {
		this.pdeEmergContactoTelefono2 = pdeEmergContactoTelefono2;
	}

	public String getPdeEmergContactoCorreo() {
		return this.pdeEmergContactoCorreo;
	}

	public void setPdeEmergContactoCorreo(String pdeEmergContactoCorreo) {
		this.pdeEmergContactoCorreo = pdeEmergContactoCorreo;
	}
	
	public String getPdeResidencia() {
		return this.pdeResidencia;
	}

	public void setPdeResidencia(String pdeResidencia) {
		this.pdeResidencia = pdeResidencia;
	}
	
	public Integer getPdeEstadiaDias() {
		return this.pdeEstadiaDias;
	}

	public void setPdeEstadiaDias(Integer pdeEstadiaDias) {
		this.pdeEstadiaDias = pdeEstadiaDias;
	}

	public Integer getPdeEstadiaHoras() {
		return this.pdeEstadiaHoras;
	}

	public void setPdeEstadiaHoras(Integer pdeEstadiaHoras) {
		this.pdeEstadiaHoras = pdeEstadiaHoras;
	}

}