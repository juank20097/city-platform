package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the gen_sitios database table.
 * 
 */
@Entity
@Table(name="gen_sitios")
@NamedQuery(name="GenSitio.findAll", query="SELECT g FROM GenSitio g")
public class GenSitio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sit_id")
	private String sitId;

	@Column(name="sit_calle_principal")
	private String sitCallePrincipal;

	@Column(name="sit_calle_secundaria")
	private String sitCalleSecundaria;

	@Column(name="sit_capacidad")
	private Integer sitCapacidad;

	@Column(name="sit_costo_arriendo")
	private BigDecimal sitCostoArriendo;

	@Column(name="sit_descripcion")
	private String sitDescripcion;

	@Column(name="sit_estado",columnDefinition="bpchar")
	private String sitEstado;

	@Column(name="sit_integracion_accesos")
	private Boolean sitIntegracionAccesos;

	@Column(name="sit_nombre")
	private String sitNombre;

	@Column(name="sit_numero")
	private String sitNumero;

	@Column(name="sit_piso")
	private Integer sitPiso;

	//bi-directional many-to-one association to GenSitFoto
	@OneToMany(mappedBy="genSitio")
	private List<GenSitFoto> genSitFotos;

	//bi-directional many-to-one association to GenArea
	@ManyToOne
	@JoinColumn(name="are_id")
	private GenArea genArea;

	//bi-directional many-to-one association to GenInstitucione
	@ManyToOne
	@JoinColumn(name="ins_codigo")
	private GenInstitucione genInstitucione;

	//bi-directional many-to-one association to GenTipoSitio
	@ManyToOne
	@JoinColumn(name="tsi_id")
	private GenTipoSitio genTipoSitio;

	public GenSitio() {
	}

	public String getSitId() {
		return this.sitId;
	}

	public void setSitId(String sitId) {
		this.sitId = sitId;
	}

	public String getSitCallePrincipal() {
		return this.sitCallePrincipal;
	}

	public void setSitCallePrincipal(String sitCallePrincipal) {
		this.sitCallePrincipal = sitCallePrincipal;
	}

	public String getSitCalleSecundaria() {
		return this.sitCalleSecundaria;
	}

	public void setSitCalleSecundaria(String sitCalleSecundaria) {
		this.sitCalleSecundaria = sitCalleSecundaria;
	}

	public Integer getSitCapacidad() {
		return this.sitCapacidad;
	}

	public void setSitCapacidad(Integer sitCapacidad) {
		this.sitCapacidad = sitCapacidad;
	}

	public BigDecimal getSitCostoArriendo() {
		return this.sitCostoArriendo;
	}

	public void setSitCostoArriendo(BigDecimal sitCostoArriendo) {
		this.sitCostoArriendo = sitCostoArriendo;
	}

	public String getSitDescripcion() {
		return this.sitDescripcion;
	}

	public void setSitDescripcion(String sitDescripcion) {
		this.sitDescripcion = sitDescripcion;
	}

	public String getSitEstado() {
		return this.sitEstado;
	}

	public void setSitEstado(String sitEstado) {
		this.sitEstado = sitEstado;
	}

	public Boolean getSitIntegracionAccesos() {
		return this.sitIntegracionAccesos;
	}

	public void setSitIntegracionAccesos(Boolean sitIntegracionAccesos) {
		this.sitIntegracionAccesos = sitIntegracionAccesos;
	}

	public String getSitNombre() {
		return this.sitNombre;
	}

	public void setSitNombre(String sitNombre) {
		this.sitNombre = sitNombre;
	}

	public String getSitNumero() {
		return this.sitNumero;
	}

	public void setSitNumero(String sitNumero) {
		this.sitNumero = sitNumero;
	}

	public Integer getSitPiso() {
		return this.sitPiso;
	}

	public void setSitPiso(Integer sitPiso) {
		this.sitPiso = sitPiso;
	}

	public List<GenSitFoto> getGenSitFotos() {
		return this.genSitFotos;
	}

	public void setGenSitFotos(List<GenSitFoto> genSitFotos) {
		this.genSitFotos = genSitFotos;
	}

	public GenSitFoto addGenSitFoto(GenSitFoto genSitFoto) {
		getGenSitFotos().add(genSitFoto);
		genSitFoto.setGenSitio(this);

		return genSitFoto;
	}

	public GenSitFoto removeGenSitFoto(GenSitFoto genSitFoto) {
		getGenSitFotos().remove(genSitFoto);
		genSitFoto.setGenSitio(null);

		return genSitFoto;
	}

	public GenArea getGenArea() {
		return this.genArea;
	}

	public void setGenArea(GenArea genArea) {
		this.genArea = genArea;
	}

	public GenInstitucione getGenInstitucione() {
		return this.genInstitucione;
	}

	public void setGenInstitucione(GenInstitucione genInstitucione) {
		this.genInstitucione = genInstitucione;
	}

	public GenTipoSitio getGenTipoSitio() {
		return this.genTipoSitio;
	}

	public void setGenTipoSitio(GenTipoSitio genTipoSitio) {
		this.genTipoSitio = genTipoSitio;
	}

}