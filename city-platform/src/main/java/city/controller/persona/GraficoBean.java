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
	
	//datos obtenidos
	private Integer h1=0;
	private Integer h2=0;
	private Integer h3=0;
	private Integer h4=0;
	private Integer h5=0;
	private Integer h6=0;
	private Integer h7=0;
	private Integer h8=0;
	private Integer h9=0;
	private Integer h10=0;
	private Integer h11=0;
	private Integer h12=0;
	private Integer h13=0;
	private Integer h14=0;
	
	private Integer m1=0;
	private Integer m2=0;
	private Integer m3=0;
	private Integer m4=0;
	private Integer m5=0;
	private Integer m6=0;
	private Integer m7=0;
	private Integer m8=0;
	private Integer m9=0;
	private Integer m10=0;
	private Integer m11=0;
	private Integer m12=0;
	private Integer m13=0;
	private Integer m14=0;
	
	

	@PostConstruct
	public void init() {
		session.validarSesion();
		llenarEnteros();
		createBarModel();
		createBarModelPorcentual();
	}

	public BarChartModel getBarModelP() {
		return barModelP;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	private void createBarModel() {
		barModel = initBarModel();

		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Años");
		xAxis.setTickAngle(75);

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Número");
		yAxis.setMin(0);
		yAxis.setMax(125);
	}

	private void createBarModelPorcentual() {
		barModelP = initBarModelP();

		barModelP.setLegendPosition("ne");

		Axis xAxis = barModelP.getAxis(AxisType.X);
		xAxis.setLabel("Años");
		xAxis.setTickAngle(75);

		Axis yAxis = barModelP.getAxis(AxisType.Y);
		yAxis.setLabel("Porcentaje");
		yAxis.setMin(0);
		yAxis.setMax(30);
	}
	
	public void llenarEnteros(){
		h1 = manager.obtencionDatos(18, 19, "M");
		h2 = manager.obtencionDatos(20, 24, "M");
		h3 = manager.obtencionDatos(25, 29, "M");
		h4 =manager.obtencionDatos(30, 34, "M");
		h5 = manager.obtencionDatos(35, 39, "M");
		h6 = manager.obtencionDatos(40, 44, "M");
		h7 = manager.obtencionDatos(45, 49, "M");
		h8 = manager.obtencionDatos(50, 54, "M");
		h9 = manager.obtencionDatos(55, 59, "M");
		h10 = manager.obtencionDatos(60, 64, "M");
		h11 = manager.obtencionDatos(65, 69, "M");
		h12 = manager.obtencionDatos(70, 74, "M");
		h13 = manager.obtencionDatos(75, 80, "M");
		h14 = manager.obtencionDatos(80, 84, "M");
		
		m1 = manager.obtencionDatos(18, 19, "F");
		m2 = manager.obtencionDatos(20, 24, "F");
		m3 = manager.obtencionDatos(25, 29, "F");
		m4 =manager.obtencionDatos(30, 34, "F");
		m5 = manager.obtencionDatos(35, 39, "F");
		m6 = manager.obtencionDatos(40, 44, "F");
		m7 = manager.obtencionDatos(45, 49, "F");
		m8 = manager.obtencionDatos(50, 54, "F");
		m9 = manager.obtencionDatos(55, 59, "F");
		m10 = manager.obtencionDatos(60, 64, "F");
		m11 = manager.obtencionDatos(65, 69, "F");
		m12 = manager.obtencionDatos(70, 74, "F");
		m13 = manager.obtencionDatos(75, 80, "F");
		m14 = manager.obtencionDatos(80, 84, "F");
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries hombres = new ChartSeries();
		hombres.setLabel("HOMBRES");
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

		ChartSeries mujeres = new ChartSeries();
		mujeres.setLabel("MUJERES");
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

		model.addSeries(hombres);
		model.addSeries(mujeres);

		return model;
	}
	
	private BarChartModel initBarModelP() {
		BarChartModel modelp = new BarChartModel();

		ChartSeries hombresp = new ChartSeries();
		hombresp.setLabel("HOMBRES = 497");
		hombresp.set("18 a 19",manager.porcentaje(497, h1));
		hombresp.set("20 a 24", manager.porcentaje(497, h2));
		hombresp.set("25 a 29", manager.porcentaje(497, h3));
		hombresp.set("30 a 34",manager.porcentaje(497, h4));
		hombresp.set("35 a 39", manager.porcentaje(497, h5));
		hombresp.set("40 a 44", manager.porcentaje(497, h6));
		hombresp.set("45 a 49",manager.porcentaje(497, h7));
		hombresp.set("50 a 54", manager.porcentaje(497, h8));
		hombresp.set("55 a 59", manager.porcentaje(497, h9));
		hombresp.set("60 a 64", manager.porcentaje(497, h10));
		hombresp.set("65 a 69", manager.porcentaje(497, h11));
		hombresp.set("70 a 74", manager.porcentaje(497, h12));
		hombresp.set("75 a 80", manager.porcentaje(497, h13));
		hombresp.set("80 a 84", manager.porcentaje(497, h14));

		ChartSeries mujeresp = new ChartSeries();
		mujeresp.setLabel("MUJERES = 240");
		mujeresp.set("18 a 19", manager.porcentaje(240, m1));
		mujeresp.set("20 a 24", manager.porcentaje(240, m2));
		mujeresp.set("25 a 29", manager.porcentaje(240, m3));
		mujeresp.set("30 a 34", manager.porcentaje(240, m4));
		mujeresp.set("35 a 39", manager.porcentaje(240, m5));
		mujeresp.set("40 a 44", manager.porcentaje(240, m6));
		mujeresp.set("45 a 49", manager.porcentaje(240, m7));
		mujeresp.set("50 a 54", manager.porcentaje(240, m8));
		mujeresp.set("55 a 59", manager.porcentaje(240, m9));
		mujeresp.set("60 a 64", manager.porcentaje(240, m10));
		mujeresp.set("65 a 69", manager.porcentaje(240, m11));
		mujeresp.set("70 a 74", manager.porcentaje(240, m12));
		mujeresp.set("75 a 80", manager.porcentaje(240, m13));
		mujeresp.set("80 a 84", manager.porcentaje(240, m14));

		modelp.addSeries(hombresp);
		modelp.addSeries(mujeresp);

		return modelp;
	}

}
