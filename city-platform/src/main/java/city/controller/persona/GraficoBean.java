package city.controller.persona;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GenCatalogoItemsDet;
import city.model.dao.entidades.GenInstitucione;
import city.model.dao.entidades.extras.GenericClassBoolean;
import city.model.dao.entidades.extras.Sangre;
import city.model.generic.Mensaje;
import city.model.manager.ManagerPersona;
import city.model.manager.ManagerSitios;

/**
 * @author jestevez
 * 
 */
@SessionScoped
@ManagedBean
public class GraficoBean {

	// Atributos de la Clase
	@EJB
	private ManagerPersona manager;
	@EJB
	private ManagerSitios managerSitio;
	@Inject
	private SesionBean session;

	private BarChartModel barModel;
	private BarChartModel barModelP;
	private BarChartModel barModelSanguineo;
	private BarChartModel barModelSanguineoP;
	private BarChartModel barModelEjercicio;
	private BarChartModel barModelEjercicioP;
	private BarChartModel barModelAlcohol;
	private BarChartModel barModelAlcoholP;
	private BarChartModel barModelEmbriaguez;
	private BarChartModel barModelEmbriaguezP;
	private BarChartModel barModelTabaco;
	private BarChartModel barModelTabacoP;
	private BarChartModel barModelSeguroIESS;
	private BarChartModel barModelSeguroIESSP;
	private BarChartModel barModelSeguroPrivado;
	private BarChartModel barModelSeguroPrivadoP;

	// totales para porcentaje
	private Integer total_hombre;
	private Integer total_mujer;

	// atributos para el filtro
	private String institucion;
	private String personas;
	private List<SelectItem> l_instituciones;
	private List<SelectItem> l_personas;

	// datos obtenidos
	private Integer h0 = 0;
	private Integer h1 = 0;
	private Integer h2 = 0;
	private Integer h3 = 0;
	private Integer h4 = 0;
	private Integer h5 = 0;
	private Integer h6 = 0;
	private Integer h7 = 0;
	private Integer h8 = 0;
	private Integer h9 = 0;
	private Integer h10 = 0;
	private Integer h11 = 0;
	private Integer h12 = 0;
	private Integer h13 = 0;
	private Integer h14 = 0;
	private Integer h15 = 0;

	private Integer m0 = 0;
	private Integer m1 = 0;
	private Integer m2 = 0;
	private Integer m3 = 0;
	private Integer m4 = 0;
	private Integer m5 = 0;
	private Integer m6 = 0;
	private Integer m7 = 0;
	private Integer m8 = 0;
	private Integer m9 = 0;
	private Integer m10 = 0;
	private Integer m11 = 0;
	private Integer m12 = 0;
	private Integer m13 = 0;
	private Integer m14 = 0;
	private Integer m15 = 0;

	@PostConstruct
	public void init() {
		// session.validarSesion();
		l_instituciones = new ArrayList<SelectItem>();
		l_personas = new ArrayList<SelectItem>();
		setPersonas("all");
		setInstitucion("all");
		cargarInstituciones();
		cargarPersonas();
		cargarDatos();
	}

	public List<SelectItem> getL_personas() {
		return l_personas;
	}

	public void setL_personas(List<SelectItem> l_personas) {
		this.l_personas = l_personas;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getPersonas() {
		return personas;
	}

	public void setPersonas(String personas) {
		this.personas = personas;
	}

	public BarChartModel getBarModelP() {
		return barModelP;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public BarChartModel getBarModelAlcohol() {
		return barModelAlcohol;
	}

	public BarChartModel getBarModelEmbriaguez() {
		return barModelEmbriaguez;
	}

	public BarChartModel getBarModelTabaco() {
		return barModelTabaco;
	}

	public BarChartModel getBarModelSeguroIESS() {
		return barModelSeguroIESS;
	}

	public BarChartModel getBarModelSeguroPrivado() {
		return barModelSeguroPrivado;
	}

	public BarChartModel getBarModelSanguineo() {
		return barModelSanguineo;
	}

	public BarChartModel getBarModelEjercicio() {
		return barModelEjercicio;
	}

	public BarChartModel getBarModelSanguineoP() {
		return barModelSanguineoP;
	}

	public BarChartModel getBarModelEjercicioP() {
		return barModelEjercicioP;
	}

	public BarChartModel getBarModelTabacoP() {
		return barModelTabacoP;
	}

	public BarChartModel getBarModelAlcoholP() {
		return barModelAlcoholP;
	}

	public BarChartModel getBarModelEmbriaguezP() {
		return barModelEmbriaguezP;
	}

	public BarChartModel getBarModelSeguroIESSP() {
		return barModelSeguroIESSP;
	}

	public BarChartModel getBarModelSeguroPrivadoP() {
		return barModelSeguroPrivadoP;
	}

	public List<SelectItem> getL_instituciones() {
		return l_instituciones;
	}

	public void setL_instituciones(List<SelectItem> l_instituciones) {
		this.l_instituciones = l_instituciones;
	}

	// gráfico_numérico

	private void createBarModel(boolean valor) {
		barModel = initBarModel(valor);

		barModel.setLegendPosition("ne");
		barModel.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		barModel.setAnimate(true);
		barModel.setShowPointLabels(true);

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Años");
		xAxis.setTickAngle(75);

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Personas");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModel(boolean valor) {
		BarChartModel model = new BarChartModel();

		if (valor == true) {
			ChartSeries hombres = new ChartSeries();
			hombres.setLabel("HOMBRES");
			hombres.set("menos 18", h0);
			hombres.set("18 a 19", h1);
			hombres.set("20 a 24", h2);
			hombres.set("25 a 29", h3);
			hombres.set("30 a 34", h4);
			hombres.set("35 a 39", h5);
			hombres.set("40 a 44", h6);
			hombres.set("45 a 49", h7);
			hombres.set("50 a 54", h8);
			hombres.set("55 a 59", h9);
			hombres.set("60 a 64", h10);
			hombres.set("65 a 69", h11);
			hombres.set("70 a 74", h12);
			hombres.set("75 a 80", h13);
			hombres.set("80 a 84", h14);
			hombres.set("más 84", h15);

			ChartSeries mujeres = new ChartSeries();
			mujeres.setLabel("MUJERES");
			hombres.set("menos 18", m0);
			mujeres.set("18 a 19", m1);
			mujeres.set("20 a 24", m2);
			mujeres.set("25 a 29", m3);
			mujeres.set("30 a 34", m4);
			mujeres.set("35 a 39", m5);
			mujeres.set("40 a 44", m6);
			mujeres.set("45 a 49", m7);
			mujeres.set("50 a 54", m8);
			mujeres.set("55 a 59", m9);
			mujeres.set("60 a 64", m10);
			mujeres.set("65 a 69", m11);
			mujeres.set("70 a 74", m12);
			mujeres.set("75 a 80", m13);
			mujeres.set("80 a 84", m14);
			hombres.set("más 84", m15);

			model.addSeries(hombres);
			model.addSeries(mujeres);
		} else {
			ChartSeries hombres = new ChartSeries();
			hombres.setLabel("HOMBRES");
			hombres.set("menos 18", 0);
			hombres.set("18 a 19", 0);
			hombres.set("20 a 24", 0);
			hombres.set("25 a 29", 0);
			hombres.set("30 a 34", 0);
			hombres.set("35 a 39", 0);
			hombres.set("40 a 44", 0);
			hombres.set("45 a 49", 0);
			hombres.set("50 a 54", 0);
			hombres.set("55 a 59", 0);
			hombres.set("60 a 64", 0);
			hombres.set("65 a 69", 0);
			hombres.set("70 a 74", 0);
			hombres.set("75 a 80", 0);
			hombres.set("80 a 84", 0);
			hombres.set("más 84", 0);

			ChartSeries mujeres = new ChartSeries();
			mujeres.setLabel("MUJERES");
			hombres.set("menos 18", 0);
			mujeres.set("18 a 19", 0);
			mujeres.set("20 a 24", 0);
			mujeres.set("25 a 29", 0);
			mujeres.set("30 a 34", 0);
			mujeres.set("35 a 39", 0);
			mujeres.set("40 a 44", 0);
			mujeres.set("45 a 49", 0);
			mujeres.set("50 a 54", 0);
			mujeres.set("55 a 59", 0);
			mujeres.set("60 a 64", 0);
			mujeres.set("65 a 69", 0);
			mujeres.set("70 a 74", 0);
			mujeres.set("75 a 80", 0);
			mujeres.set("80 a 84", 0);
			hombres.set("más 84", 0);

			model.addSeries(hombres);
			model.addSeries(mujeres);
		}

		return model;
	}

	// gráfico_porcentual

	private void createBarModelPorcentual(boolean valor) {
		barModelP = initBarModelP(valor);

		barModelP.setLegendPosition("ne");
		barModelP.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		barModelP.setAnimate(true);
		barModelP.setShowPointLabels(true);

		Axis xAxis = barModelP.getAxis(AxisType.X);
		xAxis.setLabel("Años");
		xAxis.setTickAngle(75);

		Axis yAxis = barModelP.getAxis(AxisType.Y);
		yAxis.setLabel("Porcentaje (%)");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelP(boolean valor) {
		BarChartModel modelp = new BarChartModel();

		if (valor == true) {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = " + total_hombre + "");
			hombresp.set("menos 18", porcentaje(total_hombre, h0));
			hombresp.set("18 a 19", porcentaje(total_hombre, h1));
			hombresp.set("20 a 24", porcentaje(total_hombre, h2));
			hombresp.set("25 a 29", porcentaje(total_hombre, h3));
			hombresp.set("30 a 34", porcentaje(total_hombre, h4));
			hombresp.set("35 a 39", porcentaje(total_hombre, h5));
			hombresp.set("40 a 44", porcentaje(total_hombre, h6));
			hombresp.set("45 a 49", porcentaje(total_hombre, h7));
			hombresp.set("50 a 54", porcentaje(total_hombre, h8));
			hombresp.set("55 a 59", porcentaje(total_hombre, h9));
			hombresp.set("60 a 64", porcentaje(total_hombre, h10));
			hombresp.set("65 a 69", porcentaje(total_hombre, h11));
			hombresp.set("70 a 74", porcentaje(total_hombre, h12));
			hombresp.set("75 a 80", porcentaje(total_hombre, h13));
			hombresp.set("80 a 84", porcentaje(total_hombre, h14));
			hombresp.set("más 84", porcentaje(total_hombre, h15));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer + "");
			hombresp.set("menos 18", porcentaje(total_hombre, m0));
			mujeresp.set("18 a 19", porcentaje(total_mujer, m1));
			mujeresp.set("20 a 24", porcentaje(total_mujer, m2));
			mujeresp.set("25 a 29", porcentaje(total_mujer, m3));
			mujeresp.set("30 a 34", porcentaje(total_mujer, m4));
			mujeresp.set("35 a 39", porcentaje(total_mujer, m5));
			mujeresp.set("40 a 44", porcentaje(total_mujer, m6));
			mujeresp.set("45 a 49", porcentaje(total_mujer, m7));
			mujeresp.set("50 a 54", porcentaje(total_mujer, m8));
			mujeresp.set("55 a 59", porcentaje(total_mujer, m9));
			mujeresp.set("60 a 64", porcentaje(total_mujer, m10));
			mujeresp.set("65 a 69", porcentaje(total_mujer, m11));
			mujeresp.set("70 a 74", porcentaje(total_mujer, m12));
			mujeresp.set("75 a 80", porcentaje(total_mujer, m13));
			mujeresp.set("80 a 84", porcentaje(total_mujer, m14));
			hombresp.set("más 84", porcentaje(total_hombre, m15));

			modelp.addSeries(hombresp);
			modelp.addSeries(mujeresp);

		} else {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = " + total_hombre + "");
			hombresp.set("menos 18", 0);
			hombresp.set("18 a 19", 0);
			hombresp.set("20 a 24", 0);
			hombresp.set("25 a 29", 0);
			hombresp.set("30 a 34", 0);
			hombresp.set("35 a 39", 0);
			hombresp.set("40 a 44", 0);
			hombresp.set("45 a 49", 0);
			hombresp.set("50 a 54", 0);
			hombresp.set("55 a 59", 0);
			hombresp.set("60 a 64", 0);
			hombresp.set("65 a 69", 0);
			hombresp.set("70 a 74", 0);
			hombresp.set("75 a 80", 0);
			hombresp.set("80 a 84", 0);
			hombresp.set("más 84", 0);

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer + "");
			hombresp.set("menos 18", 0);
			mujeresp.set("18 a 19", 0);
			mujeresp.set("20 a 24", 0);
			mujeresp.set("25 a 29", 0);
			mujeresp.set("30 a 34", 0);
			mujeresp.set("35 a 39", 0);
			mujeresp.set("40 a 44", 0);
			mujeresp.set("45 a 49", 0);
			mujeresp.set("50 a 54", 0);
			mujeresp.set("55 a 59", 0);
			mujeresp.set("60 a 64", 0);
			mujeresp.set("65 a 69", 0);
			mujeresp.set("70 a 74", 0);
			mujeresp.set("75 a 80", 0);
			mujeresp.set("80 a 84", 0);
			hombresp.set("más 84", 0);

			modelp.addSeries(hombresp);
			modelp.addSeries(mujeresp);
		}

		return modelp;
	}

	private Integer porcentaje(Integer tope, Integer valor) {
		return (valor * 100) / tope;
	}

	// gráfico_sanguineo

	private void createBarModelSanguineo(boolean valor) {
		barModelSanguineo = initBarModelSanguineo(valor);

		barModelSanguineo.setLegendPosition("ne");
		barModelSanguineo.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		barModelSanguineo.setAnimate(true);
		barModelSanguineo.setShowPointLabels(true);

		Axis xAxis = barModelSanguineo.getAxis(AxisType.X);
		xAxis.setLabel("Tipos de Sangre");

		Axis yAxis = barModelSanguineo.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Personas");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelSanguineo(boolean valor) {
		BarChartModel models = new BarChartModel();

		if (valor == true) {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES");
			hombresp.set("O-", recuperarDatosSanguineos("O-", "M"));
			hombresp.set("O+", recuperarDatosSanguineos("O+", "M"));
			hombresp.set("A-", recuperarDatosSanguineos("A-", "M"));
			hombresp.set("A+", recuperarDatosSanguineos("A+", "M"));
			hombresp.set("AB-", recuperarDatosSanguineos("AB-", "M"));
			hombresp.set("AB+", recuperarDatosSanguineos("AB+", "M"));
			hombresp.set("B+", recuperarDatosSanguineos("B+", "M"));
			hombresp.set("B-", recuperarDatosSanguineos("B-", "M"));
			hombresp.set("Vacios", recuperarDatosSanguineosNulos("M"));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES");
			mujeresp.set("O-", recuperarDatosSanguineos("O-", "F"));
			mujeresp.set("O+", recuperarDatosSanguineos("O+", "F"));
			mujeresp.set("A-", recuperarDatosSanguineos("A-", "F"));
			mujeresp.set("A+", recuperarDatosSanguineos("A+", "F"));
			mujeresp.set("AB-", recuperarDatosSanguineos("AB-", "F"));
			mujeresp.set("AB+", recuperarDatosSanguineos("AB+", "F"));
			mujeresp.set("B+", recuperarDatosSanguineos("B+", "F"));
			mujeresp.set("B-", recuperarDatosSanguineos("B-", "F"));
			mujeresp.set("Vacios", recuperarDatosSanguineosNulos("F"));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		} else {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES");
			hombresp.set("O-", 0);
			hombresp.set("O+", 0);
			hombresp.set("A-", 0);
			hombresp.set("A+", 0);
			hombresp.set("AB-", 0);
			hombresp.set("AB+", 0);
			hombresp.set("B+", 0);
			hombresp.set("B-", 0);
			hombresp.set("Vacios", 0);

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES");
			mujeresp.set("O-", 0);
			mujeresp.set("O+", 0);
			mujeresp.set("A-", 0);
			mujeresp.set("A+", 0);
			mujeresp.set("AB-", 0);
			mujeresp.set("AB+", 0);
			mujeresp.set("B+", 0);
			mujeresp.set("B-", 0);
			mujeresp.set("Vacios", 0);

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		}

		return models;
	}

	private Integer recuperarDatosSanguineos(String tipo, String genero) {
		Integer r = 0;
		for (Sangre s : manager.listaSanguinea(getInstitucion(), getPersonas())) {
			if (s != null && s.getSan_tipo().equals(tipo) && s.getSan_genero().equals(genero)) {
				r = s.getSan_cantidad();
				break;
			}
		}
		return r;
	}

	private Integer recuperarDatosSanguineosNulos(String genero) {
		Integer r = 0;
		for (Sangre s : manager.listaSanguinea(getInstitucion(), getPersonas())) {
			if ((s.getSan_tipo().equals("null") || s.getSan_tipo().equals("S/N")) && s.getSan_genero().equals(genero)) {
				r += s.getSan_cantidad();
			}
		}
		return r;
	}

	// gráfico_sanguineo_porcentual

	private void createBarModelSanguineoP(boolean valor) {
		barModelSanguineoP = initBarModelSanguineoP(valor);

		barModelSanguineoP.setLegendPosition("ne");
		barModelSanguineoP.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		barModelSanguineoP.setAnimate(true);
		barModelSanguineoP.setShowPointLabels(true);

		Axis xAxis = barModelSanguineoP.getAxis(AxisType.X);
		xAxis.setLabel("Tipos de Sangre");

		Axis yAxis = barModelSanguineoP.getAxis(AxisType.Y);
		yAxis.setLabel("Porcentaje (%)");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelSanguineoP(boolean valor) {
		BarChartModel models = new BarChartModel();

		if (valor == true) {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = " + total_hombre);
			hombresp.set("O-", porcentaje(total_hombre, recuperarDatosSanguineos("O-", "M")));
			hombresp.set("O+", porcentaje(total_hombre, recuperarDatosSanguineos("O+", "M")));
			hombresp.set("A-", porcentaje(total_hombre, recuperarDatosSanguineos("A-", "M")));
			hombresp.set("A+", porcentaje(total_hombre, recuperarDatosSanguineos("A+", "M")));
			hombresp.set("AB-", porcentaje(total_hombre, recuperarDatosSanguineos("AB-", "M")));
			hombresp.set("AB+", porcentaje(total_hombre, recuperarDatosSanguineos("AB+", "M")));
			hombresp.set("B+", porcentaje(total_hombre, recuperarDatosSanguineos("B+", "M")));
			hombresp.set("B-", porcentaje(total_hombre, recuperarDatosSanguineos("B-", "M")));
			hombresp.set("Vacios", porcentaje(total_hombre, recuperarDatosSanguineosNulos("M")));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("O-", porcentaje(total_mujer, recuperarDatosSanguineos("O-", "F")));
			mujeresp.set("O+", porcentaje(total_mujer, recuperarDatosSanguineos("O+", "F")));
			mujeresp.set("A-", porcentaje(total_mujer, recuperarDatosSanguineos("A-", "F")));
			mujeresp.set("A+", porcentaje(total_mujer, recuperarDatosSanguineos("A+", "F")));
			mujeresp.set("AB-", porcentaje(total_mujer, recuperarDatosSanguineos("AB-", "F")));
			mujeresp.set("AB+", porcentaje(total_mujer, recuperarDatosSanguineos("AB+", "F")));
			mujeresp.set("B+", porcentaje(total_mujer, recuperarDatosSanguineos("B+", "F")));
			mujeresp.set("B-", porcentaje(total_mujer, recuperarDatosSanguineos("B-", "F")));
			mujeresp.set("Vacios", porcentaje(total_mujer, recuperarDatosSanguineosNulos("F")));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		} else {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = " + total_hombre);
			hombresp.set("O-", 0);
			hombresp.set("O+", 0);
			hombresp.set("A-", 0);
			hombresp.set("A+", 0);
			hombresp.set("AB-", 0);
			hombresp.set("AB+", 0);
			hombresp.set("B+", 0);
			hombresp.set("B-", 0);
			hombresp.set("Vacios", 0);

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("O-", 0);
			mujeresp.set("O+", 0);
			mujeresp.set("A-", 0);
			mujeresp.set("A+", 0);
			mujeresp.set("AB-", 0);
			mujeresp.set("AB+", 0);
			mujeresp.set("B+", 0);
			mujeresp.set("B-", 0);
			mujeresp.set("Vacios", 0);

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		}

		return models;
	}

	// gráfico_ejercicio

	private void createBarModelEjercicio(boolean valor) {
		barModelEjercicio = initBarModelEjercicio(valor);

		barModelEjercicio.setLegendPosition("ne");
		barModelEjercicio.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		barModelEjercicio.setAnimate(true);
		barModelEjercicio.setShowPointLabels(true);

		Axis xAxis = barModelEjercicio.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelEjercicio.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Personas");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelEjercicio(boolean valor) {
		BarChartModel models = new BarChartModel();

		if (valor == true) {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES");
			hombresp.set("SI", recuperarDatosEjercicio(true, "M"));
			hombresp.set("NO", recuperarDatosEjercicio(false, "M"));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES");
			mujeresp.set("SI", recuperarDatosEjercicio(true, "F"));
			mujeresp.set("NO", recuperarDatosEjercicio(false, "F"));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		} else {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES");
			hombresp.set("SI", 0);
			hombresp.set("NO", 0);

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES");
			mujeresp.set("SI", 0);
			mujeresp.set("NO", 0);

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		}

		return models;
	}

	private Integer recuperarDatosEjercicio(Boolean tipo, String genero) {
		Integer r = 0;
		for (GenericClassBoolean gc : manager.listaEjercicio(getInstitucion(), getPersonas())) {
			if (gc.isGen_valor() == tipo && gc.getGen_genero().equals(genero)) {
				r += gc.getGen_cantidad();
			}
		}
		return r;
	}

	// gráfico_ejercicio_porcentual

	private void createBarModelEjercicioP(boolean valor) {
		barModelEjercicioP = initBarModelEjercicioP(valor);

		barModelEjercicioP.setLegendPosition("ne");
		barModelEjercicioP.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		barModelEjercicioP.setAnimate(true);
		barModelEjercicioP.setShowPointLabels(true);

		Axis xAxis = barModelEjercicioP.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelEjercicioP.getAxis(AxisType.Y);
		yAxis.setLabel("Porcentaje (%)");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelEjercicioP(boolean valor) {
		BarChartModel models = new BarChartModel();

		if (valor == true) {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = " + total_hombre);
			hombresp.set("SI", porcentaje(total_hombre, recuperarDatosEjercicio(true, "M")));
			hombresp.set("NO", porcentaje(total_hombre, recuperarDatosEjercicio(false, "M")));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("SI", porcentaje(total_mujer, recuperarDatosEjercicio(true, "F")));
			mujeresp.set("NO", porcentaje(total_mujer, recuperarDatosEjercicio(false, "F")));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		} else {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = " + total_hombre);
			hombresp.set("SI", 0);
			hombresp.set("NO", 0);

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("SI", 0);
			mujeresp.set("NO", 0);

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		}

		return models;
	}

	// gráfico_alcohol

	private void createBarModelAlcohol(boolean valor) {
		barModelAlcohol = initBarModelAlcohol(valor);

		barModelAlcohol.setLegendPosition("ne");
		barModelAlcohol.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		barModelAlcohol.setAnimate(true);
		barModelAlcohol.setShowPointLabels(true);

		Axis xAxis = barModelAlcohol.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelAlcohol.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Personas");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelAlcohol(boolean valor) {
		BarChartModel models = new BarChartModel();

		if (valor == true) {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES");
			hombresp.set("SI", recuperarDatosAlcohol(true, "M"));
			hombresp.set("NO", recuperarDatosAlcohol(false, "M"));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES");
			mujeresp.set("SI", recuperarDatosAlcohol(true, "F"));
			mujeresp.set("NO", recuperarDatosAlcohol(false, "F"));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		} else {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES");
			hombresp.set("SI", 0);
			hombresp.set("NO", 0);

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES");
			mujeresp.set("SI", 0);
			mujeresp.set("NO", 0);

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		}

		return models;
	}

	private Integer recuperarDatosAlcohol(Boolean tipo, String genero) {
		Integer r = 0;
		for (GenericClassBoolean gc : manager.listaAlcohol(getInstitucion(), getPersonas())) {
			if (gc.isGen_valor() == tipo && gc.getGen_genero().equals(genero)) {
				r += gc.getGen_cantidad();
			}
		}
		return r;
	}

	// gráfico_alcohol_porcentual

	private void createBarModelAlcoholP(boolean valor) {
		barModelAlcoholP = initBarModelAlcoholP(valor);

		barModelAlcoholP.setLegendPosition("ne");
		barModelAlcoholP.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		barModelAlcoholP.setAnimate(true);
		barModelAlcoholP.setShowPointLabels(true);

		Axis xAxis = barModelAlcoholP.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelAlcoholP.getAxis(AxisType.Y);
		yAxis.setLabel("Porcentaje (%)");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelAlcoholP(boolean valor) {
		BarChartModel models = new BarChartModel();

		if (valor == true) {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = " + total_hombre);
			hombresp.set("SI", porcentaje(total_hombre, recuperarDatosAlcohol(true, "M")));
			hombresp.set("NO", porcentaje(total_hombre, recuperarDatosAlcohol(false, "M")));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("SI", porcentaje(total_mujer, recuperarDatosAlcohol(true, "F")));
			mujeresp.set("NO", porcentaje(total_mujer, recuperarDatosAlcohol(false, "F")));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		} else {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = " + total_hombre);
			hombresp.set("SI", 0);
			hombresp.set("NO", 0);

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("SI", 0);
			mujeresp.set("NO", 0);

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		}
		return models;
	}

	// gráfico_embriaguez

	private void createBarModelEmbriaguez(boolean valor) {
		barModelEmbriaguez = initBarModelEmbriaguez(valor);

		barModelEmbriaguez.setLegendPosition("ne");
		barModelEmbriaguez.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		barModelEmbriaguez.setAnimate(true);
		barModelEmbriaguez.setShowPointLabels(true);

		Axis xAxis = barModelEmbriaguez.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelEmbriaguez.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Personas");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelEmbriaguez(boolean valor) {
		BarChartModel models = new BarChartModel();

		if (valor == true) {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES");
			hombresp.set("SI", recuperarDatosEmbriaguez(true, "M"));
			hombresp.set("NO", recuperarDatosEmbriaguez(false, "M"));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES");
			mujeresp.set("SI", recuperarDatosEmbriaguez(true, "F"));
			mujeresp.set("NO", recuperarDatosEmbriaguez(false, "F"));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		} else {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES");
			hombresp.set("SI", 0);
			hombresp.set("NO", 0);

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES");
			mujeresp.set("SI", 0);
			mujeresp.set("NO", 0);

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		}

		return models;
	}

	private Integer recuperarDatosEmbriaguez(Boolean tipo, String genero) {
		Integer r = 0;
		for (GenericClassBoolean gc : manager.listaEmbriaguez(getInstitucion(), getPersonas())) {
			if (gc.isGen_valor() == tipo && gc.getGen_genero().equals(genero)) {
				r += gc.getGen_cantidad();
			}
		}
		return r;
	}

	// gráfico_embriaguez_porcentual

	private void createBarModelEmbriaguezP(boolean valor) {
		barModelEmbriaguezP = initBarModelEmbriaguezP(valor);

		barModelEmbriaguezP.setLegendPosition("ne");
		barModelEmbriaguezP.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		barModelEmbriaguezP.setAnimate(true);
		barModelEmbriaguezP.setShowPointLabels(true);

		Axis xAxis = barModelEmbriaguezP.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelEmbriaguezP.getAxis(AxisType.Y);
		yAxis.setLabel("Porcentaje (%)");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelEmbriaguezP(boolean valor) {
		BarChartModel models = new BarChartModel();

		if (valor == true) {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = " + total_hombre);
			hombresp.set("SI", porcentaje(total_hombre, recuperarDatosEmbriaguez(true, "M")));
			hombresp.set("NO", porcentaje(total_hombre, recuperarDatosEmbriaguez(false, "M")));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("SI", porcentaje(total_mujer, recuperarDatosEmbriaguez(true, "F")));
			mujeresp.set("NO", porcentaje(total_mujer, recuperarDatosEmbriaguez(false, "F")));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		} else {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = " + total_hombre);
			hombresp.set("SI", 0);
			hombresp.set("NO", 0);

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("SI", 0);
			mujeresp.set("NO", 0);

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		}

		return models;
	}

	// gráfico_tabaco

	private void createBarModelTabaco(boolean valor) {
		barModelTabaco = initBarModelTabaco(valor);

		barModelTabaco.setLegendPosition("ne");
		barModelTabaco.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		barModelTabaco.setAnimate(true);
		barModelTabaco.setShowPointLabels(true);

		Axis xAxis = barModelTabaco.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelTabaco.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Personas");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelTabaco(boolean valor) {
		BarChartModel models = new BarChartModel();

		if (valor == true) {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES");
			hombresp.set("SI", recuperarDatosTabaco(true, "M"));
			hombresp.set("NO", recuperarDatosTabaco(false, "M"));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES");
			mujeresp.set("SI", recuperarDatosTabaco(true, "F"));
			mujeresp.set("NO", recuperarDatosTabaco(false, "F"));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		} else {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES");
			hombresp.set("SI", 0);
			hombresp.set("NO", 0);

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES");
			mujeresp.set("SI", 0);
			mujeresp.set("NO", 0);

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		}

		return models;
	}

	private Integer recuperarDatosTabaco(Boolean tipo, String genero) {
		Integer r = 0;
		for (GenericClassBoolean gc : manager.listaTabaco(getInstitucion(), getPersonas())) {
			if (gc.isGen_valor() == tipo && gc.getGen_genero().equals(genero)) {
				r += gc.getGen_cantidad();
			}
		}
		return r;
	}

	// gráfico_tabaco_porcentual

	private void createBarModelTabacoP(boolean valor) {
		barModelTabacoP = initBarModelTabacoP(valor);

		barModelTabacoP.setLegendPosition("ne");
		barModelTabacoP.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		barModelTabacoP.setAnimate(true);
		barModelTabacoP.setShowPointLabels(true);

		Axis xAxis = barModelTabacoP.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelTabacoP.getAxis(AxisType.Y);
		yAxis.setLabel("Porcentaje (%)");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelTabacoP(boolean valor) {
		BarChartModel models = new BarChartModel();

		if (valor == true) {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = " + total_hombre);
			hombresp.set("SI", porcentaje(total_hombre, recuperarDatosTabaco(true, "M")));
			hombresp.set("NO", porcentaje(total_hombre, recuperarDatosTabaco(false, "M")));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("SI", porcentaje(total_mujer, recuperarDatosTabaco(true, "F")));
			mujeresp.set("NO", porcentaje(total_mujer, recuperarDatosTabaco(false, "F")));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		} else {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = " + total_hombre);
			hombresp.set("SI", 0);
			hombresp.set("NO", 0);

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("SI", 0);
			mujeresp.set("NO", 0);

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		}

		return models;
	}

	// gráfico_seguro_iess

	private void createBarModelSeguroI(boolean valor) {
		barModelSeguroIESS = initBarModelSeguroI(valor);

		barModelSeguroIESS.setLegendPosition("ne");
		barModelSeguroIESS.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		barModelSeguroIESS.setAnimate(true);
		barModelSeguroIESS.setShowPointLabels(true);

		Axis xAxis = barModelSeguroIESS.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelSeguroIESS.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Personas");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelSeguroI(boolean valor) {
		BarChartModel models = new BarChartModel();

		if (valor == true) {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES");
			hombresp.set("SI", recuperarDatosSeguroI(true, "M"));
			hombresp.set("NO", recuperarDatosSeguroI(false, "M"));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES");
			mujeresp.set("SI", recuperarDatosSeguroI(true, "F"));
			mujeresp.set("NO", recuperarDatosSeguroI(false, "F"));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		} else {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES");
			hombresp.set("SI", 0);
			hombresp.set("NO", 0);

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES");
			mujeresp.set("SI", 0);
			mujeresp.set("NO", 0);

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		}

		return models;
	}

	private Integer recuperarDatosSeguroI(Boolean tipo, String genero) {
		Integer r = 0;
		for (GenericClassBoolean gc : manager.listaSeguroI(getInstitucion(), getPersonas())) {
			if (gc.isGen_valor() == tipo && gc.getGen_genero().equals(genero)) {
				r += gc.getGen_cantidad();
			}
		}
		return r;
	}

	// gráfico_seguro_iess_porcentual

	private void createBarModelSeguroIP(boolean valor) {
		barModelSeguroIESSP = initBarModelSeguroIP(valor);

		barModelSeguroIESSP.setLegendPosition("ne");
		barModelSeguroIESSP.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		barModelSeguroIESSP.setAnimate(true);
		barModelSeguroIESSP.setShowPointLabels(true);

		Axis xAxis = barModelSeguroIESSP.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelSeguroIESSP.getAxis(AxisType.Y);
		yAxis.setLabel("Porcentaje (%)");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelSeguroIP(boolean valor) {
		BarChartModel models = new BarChartModel();

		if (valor == true) {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = " + total_hombre);
			hombresp.set("SI", porcentaje(total_hombre, recuperarDatosSeguroI(true, "M")));
			hombresp.set("NO", porcentaje(total_hombre, recuperarDatosSeguroI(false, "M")));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("SI", porcentaje(total_mujer, recuperarDatosSeguroI(true, "F")));
			mujeresp.set("NO", porcentaje(total_mujer, recuperarDatosSeguroI(false, "F")));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		} else {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = " + total_hombre);
			hombresp.set("SI", 0);
			hombresp.set("NO", 0);

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("SI", 0);
			mujeresp.set("NO", 0);

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		}

		return models;
	}

	// gráfico_seguro_privado

	private void createBarModelSeguroP(boolean valor) {
		barModelSeguroPrivado = initBarModelSeguroP(valor);

		barModelSeguroPrivado.setLegendPosition("ne");
		barModelSeguroPrivado.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		barModelSeguroPrivado.setAnimate(true);
		barModelSeguroPrivado.setShowPointLabels(true);

		Axis xAxis = barModelSeguroPrivado.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelSeguroPrivado.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Personas");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelSeguroP(boolean valor) {
		BarChartModel models = new BarChartModel();

		if (valor == true) {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES");
			hombresp.set("SI", recuperarDatosSeguroP(true, "M"));
			hombresp.set("NO", recuperarDatosSeguroP(false, "M"));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES");
			mujeresp.set("SI", recuperarDatosSeguroP(true, "F"));
			mujeresp.set("NO", recuperarDatosSeguroP(false, "F"));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		} else {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES");
			hombresp.set("SI", 0);
			hombresp.set("NO", 0);

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES");
			mujeresp.set("SI", 0);
			mujeresp.set("NO", 0);

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		}

		return models;
	}

	private Integer recuperarDatosSeguroP(Boolean tipo, String genero) {
		Integer r = 0;
		for (GenericClassBoolean gc : manager.listaSeguroP(getInstitucion(), getPersonas())) {
			if (gc.isGen_valor() == tipo && gc.getGen_genero().equals(genero)) {
				r += gc.getGen_cantidad();
			}
		}
		return r;
	}

	// gráfico_seguro_privado_porcentual

	private void createBarModelSeguroPP(boolean valor) {
		barModelSeguroPrivadoP = initBarModelSeguroPP(valor);

		barModelSeguroPrivadoP.setLegendPosition("ne");
		barModelSeguroPrivadoP.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		barModelSeguroPrivadoP.setAnimate(true);
		barModelSeguroPrivadoP.setShowPointLabels(true);

		Axis xAxis = barModelSeguroPrivadoP.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelSeguroPrivadoP.getAxis(AxisType.Y);
		yAxis.setLabel("Porcentaje (%)");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelSeguroPP(boolean valor) {
		BarChartModel models = new BarChartModel();

		if (valor == true) {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = " + total_hombre);
			hombresp.set("SI", porcentaje(total_hombre, recuperarDatosSeguroP(true, "M")));
			hombresp.set("NO", porcentaje(total_hombre, recuperarDatosSeguroP(false, "M")));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("SI", porcentaje(total_mujer, recuperarDatosSeguroP(true, "F")));
			mujeresp.set("NO", porcentaje(total_mujer, recuperarDatosSeguroP(false, "F")));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		} else {
			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = " + total_hombre);
			hombresp.set("SI", 0);
			hombresp.set("NO", 0);

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("SI", 0);
			mujeresp.set("NO", 0);

			models.addSeries(hombresp);
			models.addSeries(mujeresp);
		}

		return models;
	}

	// metodo fuera de gráficos

	public void cargarDatos() {
		if (llenarEnteros()) {
			createBarModel(true);
			createBarModelPorcentual(true);
			createBarModelSanguineo(true);
			createBarModelEjercicio(true);
			createBarModelAlcohol(true);
			createBarModelEmbriaguez(true);
			createBarModelTabaco(true);
			createBarModelSeguroI(true);
			createBarModelSeguroP(true);
			createBarModelSanguineoP(true);
			createBarModelEjercicioP(true);
			createBarModelAlcoholP(true);
			createBarModelEmbriaguezP(true);
			createBarModelTabacoP(true);
			createBarModelSeguroIP(true);
			createBarModelSeguroPP(true);
		} else {
			createBarModel(false);
			createBarModelPorcentual(false);
			createBarModelSanguineo(false);
			createBarModelEjercicio(false);
			createBarModelAlcohol(false);
			createBarModelEmbriaguez(false);
			createBarModelTabaco(false);
			createBarModelSeguroI(false);
			createBarModelSeguroP(false);
			createBarModelSanguineoP(false);
			createBarModelEjercicioP(false);
			createBarModelAlcoholP(false);
			createBarModelEmbriaguezP(false);
			createBarModelTabacoP(false);
			createBarModelSeguroIP(false);
			createBarModelSeguroPP(false);
			Mensaje.crearMensajeWARN("La consulta solicitada no obtuvo respuestas");
		}
	}

	public boolean llenarEnteros() {
		h0 = manager.obtencionDatos(0, 17, "M", getInstitucion(), getPersonas());
		if (h0 == -1) {
			return false;
		} else {
			h1 = manager.obtencionDatos(18, 19, "M", getInstitucion(), getPersonas());
			h2 = manager.obtencionDatos(20, 24, "M", getInstitucion(), getPersonas());
			h3 = manager.obtencionDatos(25, 29, "M", getInstitucion(), getPersonas());
			h4 = manager.obtencionDatos(30, 34, "M", getInstitucion(), getPersonas());
			h5 = manager.obtencionDatos(35, 39, "M", getInstitucion(), getPersonas());
			h6 = manager.obtencionDatos(40, 44, "M", getInstitucion(), getPersonas());
			h7 = manager.obtencionDatos(45, 49, "M", getInstitucion(), getPersonas());
			h8 = manager.obtencionDatos(50, 54, "M", getInstitucion(), getPersonas());
			h9 = manager.obtencionDatos(55, 59, "M", getInstitucion(), getPersonas());
			h10 = manager.obtencionDatos(60, 64, "M", getInstitucion(), getPersonas());
			h11 = manager.obtencionDatos(65, 69, "M", getInstitucion(), getPersonas());
			h12 = manager.obtencionDatos(70, 74, "M", getInstitucion(), getPersonas());
			h13 = manager.obtencionDatos(75, 80, "M", getInstitucion(), getPersonas());
			h14 = manager.obtencionDatos(81, 84, "M", getInstitucion(), getPersonas());
			h15 = manager.obtencionDatos(85, 100, "M", getInstitucion(), getPersonas());
			total_hombre = h0 + h1 + h2 + h3 + h4 + h5 + h6 + h7 + h8 + h9 + h10 + h11 + h12 + h13 + h14 + h15;

			m0 = manager.obtencionDatos(0, 17, "F", getInstitucion(), getPersonas());
			m1 = manager.obtencionDatos(18, 19, "F", getInstitucion(), getPersonas());
			m2 = manager.obtencionDatos(20, 24, "F", getInstitucion(), getPersonas());
			m3 = manager.obtencionDatos(25, 29, "F", getInstitucion(), getPersonas());
			m4 = manager.obtencionDatos(30, 34, "F", getInstitucion(), getPersonas());
			m5 = manager.obtencionDatos(35, 39, "F", getInstitucion(), getPersonas());
			m6 = manager.obtencionDatos(40, 44, "F", getInstitucion(), getPersonas());
			m7 = manager.obtencionDatos(45, 49, "F", getInstitucion(), getPersonas());
			m8 = manager.obtencionDatos(50, 54, "F", getInstitucion(), getPersonas());
			m9 = manager.obtencionDatos(55, 59, "F", getInstitucion(), getPersonas());
			m10 = manager.obtencionDatos(60, 64, "F", getInstitucion(), getPersonas());
			m11 = manager.obtencionDatos(65, 69, "F", getInstitucion(), getPersonas());
			m12 = manager.obtencionDatos(70, 74, "F", getInstitucion(), getPersonas());
			m13 = manager.obtencionDatos(75, 80, "F", getInstitucion(), getPersonas());
			m14 = manager.obtencionDatos(81, 84, "F", getInstitucion(), getPersonas());
			m15 = manager.obtencionDatos(85, 100, "F", getInstitucion(), getPersonas());
			total_mujer = m0 + m1 + m2 + m3 + m4 + m5 + m6 + m7 + m8 + m9 + m10 + m11 + m12 + m13 + m14 + m15;
			return true;
		}
	}

	public void cargarInstituciones() {
		try {
			getL_instituciones().clear();
			List<GenInstitucione> completo = managerSitio.findAllInstituciones();
			for (GenInstitucione i : completo) {
				getL_instituciones().add(new SelectItem(i.getInsNombre(), i.getInsNombre()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cargarPersonas() {
		getL_personas().clear();
		List<GenCatalogoItemsDet> completo = manager.AllofItems("cat_persona_grafico");
		for (GenCatalogoItemsDet i : completo) {
			getL_personas().add(new SelectItem(i.getIteCodigo(), i.getIteNombre()));
		}
	}

}
