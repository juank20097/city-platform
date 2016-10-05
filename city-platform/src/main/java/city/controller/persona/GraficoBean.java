package city.controller.persona;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import city.controller.access.SesionBean;
import city.model.dao.entidades.extras.GenericClassBoolean;
import city.model.dao.entidades.extras.Sangre;
import city.model.manager.ManagerPersona;

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
	
	//totales para porcentaje
	private Integer total_hombre;
	private Integer total_mujer;
	
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
		llenarEnteros();
		llenarTotales();
		createBarModel();
		createBarModelPorcentual();
		createBarModelSanguineo();
		createBarModelEjercicio();
		createBarModelAlcohol();
		createBarModelEmbriaguez();
		createBarModelTabaco();
		createBarModelSeguroI();
		createBarModelSeguroP();
		createBarModelSanguineoP();
		createBarModelEjercicioP();
		createBarModelAlcoholP();
		createBarModelEmbriaguezP();
		createBarModelTabacoP();
		createBarModelSeguroIP();
		createBarModelSeguroPP();
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
	
	private void llenarTotales(){
		total_hombre = manager.totalHombres();
		total_mujer = manager.totalMujeres();
	}

	// gráfico_numérico

	private void createBarModel() {
		barModel = initBarModel();

		barModel.setLegendPosition("ne");
		barModel.setAnimate(true);
		barModel.setShowPointLabels(true);

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Años");
		xAxis.setTickAngle(75);

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Personas");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

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

		return model;
	}

	// gráfico_porcentual

	private void createBarModelPorcentual() {
		barModelP = initBarModelP();

		barModelP.setLegendPosition("ne");
		barModelP.setAnimate(true);
		barModelP.setShowPointLabels(true);

		Axis xAxis = barModelP.getAxis(AxisType.X);
		xAxis.setLabel("Años");
		xAxis.setTickAngle(75);

		Axis yAxis = barModelP.getAxis(AxisType.Y);
		yAxis.setLabel("Porcentaje (%)");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelP() {
		BarChartModel modelp = new BarChartModel();

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

		return modelp;
	}

	private Integer porcentaje(Integer tope, Integer valor) {
		return (valor * 100) / tope;
	}

	// gráfico_sanguineo

	private void createBarModelSanguineo() {
		barModelSanguineo = initBarModelSanguineo();

		barModelSanguineo.setLegendPosition("ne");
		barModelSanguineo.setAnimate(true);
		barModelSanguineo.setShowPointLabels(true);

		Axis xAxis = barModelSanguineo.getAxis(AxisType.X);
		xAxis.setLabel("Tipos de Sangre");

		Axis yAxis = barModelSanguineo.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Personas");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelSanguineo() {
		BarChartModel models = new BarChartModel();

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

		return models;
	}

	private Integer recuperarDatosSanguineos(String tipo, String genero) {
		Integer r = 0;
		for (Sangre s : manager.listaSanguinea()) {
			if (s != null && s.getSan_tipo().equals(tipo) && s.getSan_genero().equals(genero)) {
				r = s.getSan_cantidad();
				break;
			}
		}
		return r;
	}

	private Integer recuperarDatosSanguineosNulos(String genero) {
		Integer r = 0;
		for (Sangre s : manager.listaSanguinea()) {
			if ((s.getSan_tipo().equals("null") || s.getSan_tipo().equals("S/N")) && s.getSan_genero().equals(genero)) {
				r += s.getSan_cantidad();
			}
		}
		return r;
	}

	// gráfico_sanguineo_porcentual

	private void createBarModelSanguineoP() {
		barModelSanguineoP = initBarModelSanguineoP();

		barModelSanguineoP.setLegendPosition("ne");
		barModelSanguineoP.setAnimate(true);
		barModelSanguineoP.setShowPointLabels(true);

		Axis xAxis = barModelSanguineoP.getAxis(AxisType.X);
		xAxis.setLabel("Tipos de Sangre");

		Axis yAxis = barModelSanguineoP.getAxis(AxisType.Y);
		yAxis.setLabel("Porcentaje (%)");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelSanguineoP() {
		BarChartModel models = new BarChartModel();

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

		return models;
	}

	// gráfico_ejercicio

	private void createBarModelEjercicio() {
		barModelEjercicio = initBarModelEjercicio();

		barModelEjercicio.setLegendPosition("ne");
		barModelEjercicio.setAnimate(true);
		barModelEjercicio.setShowPointLabels(true);

		Axis xAxis = barModelEjercicio.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelEjercicio.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Personas");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelEjercicio() {
		BarChartModel models = new BarChartModel();

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

		return models;
	}

	private Integer recuperarDatosEjercicio(Boolean tipo, String genero) {
		Integer r = 0;
		for (GenericClassBoolean gc : manager.listaEjercicio()) {
			if (gc.isGen_valor() == tipo && gc.getGen_genero().equals(genero)) {
				r += gc.getGen_cantidad();
			}
		}
		return r;
	}

	// gráfico_ejercicio_porcentual

	private void createBarModelEjercicioP() {
		barModelEjercicioP = initBarModelEjercicioP();

		barModelEjercicioP.setLegendPosition("ne");
		barModelEjercicioP.setAnimate(true);
		barModelEjercicioP.setShowPointLabels(true);

		Axis xAxis = barModelEjercicioP.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelEjercicioP.getAxis(AxisType.Y);
		yAxis.setLabel("Porcentaje (%)");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelEjercicioP() {
		BarChartModel models = new BarChartModel();

		ChartSeries hombresp = new ChartSeries();
		hombresp.setLabel("HOMBRES = "+ total_hombre);
		hombresp.set("SI", porcentaje(total_hombre, recuperarDatosEjercicio(true, "M")));
		hombresp.set("NO", porcentaje(total_hombre, recuperarDatosEjercicio(false, "M")));

		ChartSeries mujeresp = new ChartSeries();
		mujeresp.setLabel("MUJERES = " + total_mujer);
		mujeresp.set("SI", porcentaje(total_mujer, recuperarDatosEjercicio(true, "F")));
		mujeresp.set("NO", porcentaje(total_mujer, recuperarDatosEjercicio(false, "F")));

		models.addSeries(hombresp);
		models.addSeries(mujeresp);

		return models;
	}

	// gráfico_alcohol

	private void createBarModelAlcohol() {
		barModelAlcohol = initBarModelAlcohol();

		barModelAlcohol.setLegendPosition("ne");
		barModelAlcohol.setAnimate(true);
		barModelAlcohol.setShowPointLabels(true);

		Axis xAxis = barModelAlcohol.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelAlcohol.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Personas");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelAlcohol() {
		BarChartModel models = new BarChartModel();

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

		return models;
	}

	private Integer recuperarDatosAlcohol(Boolean tipo, String genero) {
		Integer r = 0;
		for (GenericClassBoolean gc : manager.listaAlcohol()) {
			if (gc.isGen_valor() == tipo && gc.getGen_genero().equals(genero)) {
				r += gc.getGen_cantidad();
			}
		}
		return r;
	}
	
	// gráfico_alcohol_porcentual

		private void createBarModelAlcoholP() {
			barModelAlcoholP = initBarModelAlcoholP();

			barModelAlcoholP.setLegendPosition("ne");
			barModelAlcoholP.setAnimate(true);
			barModelAlcoholP.setShowPointLabels(true);

			Axis xAxis = barModelAlcoholP.getAxis(AxisType.X);
			xAxis.setLabel("Valores");

			Axis yAxis = barModelAlcoholP.getAxis(AxisType.Y);
			yAxis.setLabel("Porcentaje (%)");
			yAxis.setMin(0);
		}

		private BarChartModel initBarModelAlcoholP() {
			BarChartModel models = new BarChartModel();

			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = "+ total_hombre);
			hombresp.set("SI",  porcentaje(total_hombre,recuperarDatosAlcohol(true, "M")));
			hombresp.set("NO",  porcentaje(total_hombre,recuperarDatosAlcohol(false, "M")));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("SI", porcentaje(total_mujer,recuperarDatosAlcohol(true, "F")));
			mujeresp.set("NO", porcentaje(total_mujer,recuperarDatosAlcohol(false, "F")));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);

			return models;
		}

	// gráfico_embriaguez

	private void createBarModelEmbriaguez() {
		barModelEmbriaguez = initBarModelEmbriaguez();

		barModelEmbriaguez.setLegendPosition("ne");
		barModelEmbriaguez.setAnimate(true);
		barModelEmbriaguez.setShowPointLabels(true);

		Axis xAxis = barModelEmbriaguez.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelEmbriaguez.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Personas");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelEmbriaguez() {
		BarChartModel models = new BarChartModel();

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

		return models;
	}

	private Integer recuperarDatosEmbriaguez(Boolean tipo, String genero) {
		Integer r = 0;
		for (GenericClassBoolean gc : manager.listaEmbriaguez()) {
			if (gc.isGen_valor() == tipo && gc.getGen_genero().equals(genero)) {
				r += gc.getGen_cantidad();
			}
		}
		return r;
	}
	
	// gráfico_embriaguez_porcentual
	
	private void createBarModelEmbriaguezP() {
		barModelEmbriaguezP = initBarModelEmbriaguezP();

		barModelEmbriaguezP.setLegendPosition("ne");
		barModelEmbriaguezP.setAnimate(true);
		barModelEmbriaguezP.setShowPointLabels(true);

		Axis xAxis = barModelEmbriaguezP.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelEmbriaguezP.getAxis(AxisType.Y);
		yAxis.setLabel("Porcentaje (%)");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelEmbriaguezP() {
		BarChartModel models = new BarChartModel();

		ChartSeries hombresp = new ChartSeries();
		hombresp.setLabel("HOMBRES = "+ total_hombre);
		hombresp.set("SI",  porcentaje(total_hombre,recuperarDatosEmbriaguez(true, "M")));
		hombresp.set("NO",  porcentaje(total_hombre,recuperarDatosEmbriaguez(false, "M")));

		ChartSeries mujeresp = new ChartSeries();
		mujeresp.setLabel("MUJERES = " + total_mujer);
		mujeresp.set("SI",  porcentaje(total_mujer,recuperarDatosEmbriaguez(true, "F")));
		mujeresp.set("NO",  porcentaje(total_mujer,recuperarDatosEmbriaguez(false, "F")));

		models.addSeries(hombresp);
		models.addSeries(mujeresp);

		return models;
	}

	// gráfico_tabaco

	private void createBarModelTabaco() {
		barModelTabaco = initBarModelTabaco();

		barModelTabaco.setLegendPosition("ne");
		barModelTabaco.setAnimate(true);
		barModelTabaco.setShowPointLabels(true);

		Axis xAxis = barModelTabaco.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelTabaco.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Personas");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelTabaco() {
		BarChartModel models = new BarChartModel();

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

		return models;
	}

	private Integer recuperarDatosTabaco(Boolean tipo, String genero) {
		Integer r = 0;
		for (GenericClassBoolean gc : manager.listaTabaco()) {
			if (gc.isGen_valor() == tipo && gc.getGen_genero().equals(genero)) {
				r += gc.getGen_cantidad();
			}
		}
		return r;
	}
	
	// gráfico_tabaco_porcentual

		private void createBarModelTabacoP() {
			barModelTabacoP = initBarModelTabacoP();

			barModelTabacoP.setLegendPosition("ne");
			barModelTabacoP.setAnimate(true);
			barModelTabacoP.setShowPointLabels(true);

			Axis xAxis = barModelTabacoP.getAxis(AxisType.X);
			xAxis.setLabel("Valores");

			Axis yAxis = barModelTabacoP.getAxis(AxisType.Y);
			yAxis.setLabel("Porcentaje (%)");
			yAxis.setMin(0);
		}

		private BarChartModel initBarModelTabacoP() {
			BarChartModel models = new BarChartModel();

			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = "+ total_hombre);
			hombresp.set("SI", porcentaje(total_hombre,recuperarDatosTabaco(true, "M")));
			hombresp.set("NO", porcentaje(total_hombre,recuperarDatosTabaco(false, "M")));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("SI", porcentaje(total_mujer,recuperarDatosTabaco(true, "F")));
			mujeresp.set("NO", porcentaje(total_mujer,recuperarDatosTabaco(false, "F")));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);

			return models;
		}

	// gráfico_seguro_iess

	private void createBarModelSeguroI() {
		barModelSeguroIESS = initBarModelSeguroI();

		barModelSeguroIESS.setLegendPosition("ne");
		barModelSeguroIESS.setAnimate(true);
		barModelSeguroIESS.setShowPointLabels(true);

		Axis xAxis = barModelSeguroIESS.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelSeguroIESS.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Personas");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelSeguroI() {
		BarChartModel models = new BarChartModel();

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

		return models;
	}

	private Integer recuperarDatosSeguroI(Boolean tipo, String genero) {
		Integer r = 0;
		for (GenericClassBoolean gc : manager.listaSeguroI()) {
			if (gc.isGen_valor() == tipo && gc.getGen_genero().equals(genero)) {
				r += gc.getGen_cantidad();
			}
		}
		return r;
	}
	
	// gráfico_seguro_iess_porcentual

		private void createBarModelSeguroIP() {
			barModelSeguroIESSP = initBarModelSeguroIP();

			barModelSeguroIESSP.setLegendPosition("ne");
			barModelSeguroIESSP.setAnimate(true);
			barModelSeguroIESSP.setShowPointLabels(true);

			Axis xAxis = barModelSeguroIESSP.getAxis(AxisType.X);
			xAxis.setLabel("Valores");

			Axis yAxis = barModelSeguroIESSP.getAxis(AxisType.Y);
			yAxis.setLabel("Porcentaje (%)");
			yAxis.setMin(0);
		}

		private BarChartModel initBarModelSeguroIP() {
			BarChartModel models = new BarChartModel();

			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = "+ total_hombre);
			hombresp.set("SI", porcentaje(total_hombre,recuperarDatosSeguroI(true, "M")));
			hombresp.set("NO", porcentaje(total_hombre,recuperarDatosSeguroI(false, "M")));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("SI", porcentaje(total_mujer,recuperarDatosSeguroI(true, "F")));
			mujeresp.set("NO", porcentaje(total_mujer,recuperarDatosSeguroI(false, "F")));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);

			return models;
		}

	// gráfico_seguro_privado

	private void createBarModelSeguroP() {
		barModelSeguroPrivado = initBarModelSeguroP();

		barModelSeguroPrivado.setLegendPosition("ne");
		barModelSeguroPrivado.setAnimate(true);
		barModelSeguroPrivado.setShowPointLabels(true);

		Axis xAxis = barModelSeguroPrivado.getAxis(AxisType.X);
		xAxis.setLabel("Valores");

		Axis yAxis = barModelSeguroPrivado.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Personas");
		yAxis.setMin(0);
	}

	private BarChartModel initBarModelSeguroP() {
		BarChartModel models = new BarChartModel();

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

		return models;
	}

	private Integer recuperarDatosSeguroP(Boolean tipo, String genero) {
		Integer r = 0;
		for (GenericClassBoolean gc : manager.listaSeguroP()) {
			if (gc.isGen_valor() == tipo && gc.getGen_genero().equals(genero)) {
				r += gc.getGen_cantidad();
			}
		}
		return r;
	}
	
	// gráfico_seguro_privado_porcentual

		private void createBarModelSeguroPP() {
			barModelSeguroPrivadoP = initBarModelSeguroPP();

			barModelSeguroPrivadoP.setLegendPosition("ne");
			barModelSeguroPrivadoP.setAnimate(true);
			barModelSeguroPrivadoP.setShowPointLabels(true);

			Axis xAxis = barModelSeguroPrivadoP.getAxis(AxisType.X);
			xAxis.setLabel("Valores");

			Axis yAxis = barModelSeguroPrivadoP.getAxis(AxisType.Y);
			yAxis.setLabel("Porcentaje (%)");
			yAxis.setMin(0);
		}

		private BarChartModel initBarModelSeguroPP() {
			BarChartModel models = new BarChartModel();

			ChartSeries hombresp = new ChartSeries();
			hombresp.setLabel("HOMBRES = "+ total_hombre);
			hombresp.set("SI", porcentaje(total_hombre,recuperarDatosSeguroP(true, "M")));
			hombresp.set("NO", porcentaje(total_hombre,recuperarDatosSeguroP(false, "M")));

			ChartSeries mujeresp = new ChartSeries();
			mujeresp.setLabel("MUJERES = " + total_mujer);
			mujeresp.set("SI", porcentaje(total_mujer,recuperarDatosSeguroP(true, "F")));
			mujeresp.set("NO", porcentaje(total_mujer,recuperarDatosSeguroP(false, "F")));

			models.addSeries(hombresp);
			models.addSeries(mujeresp);

			return models;
		}

	// metodo fuera de gráficos

	public void cargarDatos() {
		llenarEnteros();
		createBarModel();
		createBarModelPorcentual();
		createBarModelSanguineo();
		createBarModelEjercicio();
	}

	public void llenarEnteros() {
		h0 = manager.obtencionDatos(0, 17, "M");
		h1 = manager.obtencionDatos(18, 19, "M");
		h2 = manager.obtencionDatos(20, 24, "M");
		h3 = manager.obtencionDatos(25, 29, "M");
		h4 = manager.obtencionDatos(30, 34, "M");
		h5 = manager.obtencionDatos(35, 39, "M");
		h6 = manager.obtencionDatos(40, 44, "M");
		h7 = manager.obtencionDatos(45, 49, "M");
		h8 = manager.obtencionDatos(50, 54, "M");
		h9 = manager.obtencionDatos(55, 59, "M");
		h10 = manager.obtencionDatos(60, 64, "M");
		h11 = manager.obtencionDatos(65, 69, "M");
		h12 = manager.obtencionDatos(70, 74, "M");
		h13 = manager.obtencionDatos(75, 80, "M");
		h14 = manager.obtencionDatos(81, 84, "M");
		h15 = manager.obtencionDatos(85, 100, "M");

		m0 = manager.obtencionDatos(0, 17, "F");
		m1 = manager.obtencionDatos(18, 19, "F");
		m2 = manager.obtencionDatos(20, 24, "F");
		m3 = manager.obtencionDatos(25, 29, "F");
		m4 = manager.obtencionDatos(30, 34, "F");
		m5 = manager.obtencionDatos(35, 39, "F");
		m6 = manager.obtencionDatos(40, 44, "F");
		m7 = manager.obtencionDatos(45, 49, "F");
		m8 = manager.obtencionDatos(50, 54, "F");
		m9 = manager.obtencionDatos(55, 59, "F");
		m10 = manager.obtencionDatos(60, 64, "F");
		m11 = manager.obtencionDatos(65, 69, "F");
		m12 = manager.obtencionDatos(70, 74, "F");
		m13 = manager.obtencionDatos(75, 80, "F");
		m14 = manager.obtencionDatos(81, 84, "F");
		m15 = manager.obtencionDatos(85, 100, "F");
	}

}
