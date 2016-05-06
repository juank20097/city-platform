package city.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the gen_sit_foto database table.
 * 
 */
@Entity
@Table(name="gen_sit_foto")
@NamedQuery(name="GenSitFoto.findAll", query="SELECT g FROM GenSitFoto g")
public class GenSitFoto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sif_id")
	private Integer sifId;

	@Column(name="sif_imagen")
	private byte[] sifImagen;

	@Column(name="sif_nombre")
	private String sifNombre;

	@Column(name="sif_ruta_img")
	private String sifRutaImg;

	//bi-directional many-to-one association to GenSitio
	@ManyToOne
	@JoinColumn(name="sit_id")
	private GenSitio genSitio;

	public GenSitFoto() {
	}

	public Integer getSifId() {
		return this.sifId;
	}

	public void setSifId(Integer sifId) {
		this.sifId = sifId;
	}

	public byte[] getSifImagen() {
		return this.sifImagen;
	}

	public void setSifImagen(byte[] sifImagen) {
		this.sifImagen = sifImagen;
	}

	public String getSifNombre() {
		return this.sifNombre;
	}

	public void setSifNombre(String sifNombre) {
		this.sifNombre = sifNombre;
	}

	public String getSifRutaImg() {
		return this.sifRutaImg;
	}

	public void setSifRutaImg(String sifRutaImg) {
		this.sifRutaImg = sifRutaImg;
	}

	public GenSitio getGenSitio() {
		return this.genSitio;
	}

	public void setGenSitio(GenSitio genSitio) {
		this.genSitio = genSitio;
	}

}