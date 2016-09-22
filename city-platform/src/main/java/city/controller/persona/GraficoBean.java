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
//	@Inject
	//private SesionBean session;

	private BarChartModel barModel;
 
    @PostConstruct
    public void init() {
        createBarModel();
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
        yAxis.setMax(150);
    }
    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries hombres = new ChartSeries();
        hombres.setLabel("HOMBRES");
        hombres.set("18 a 19", manager.obtencionDatos(18, 19, "M"));
        hombres.set("20 a 24", manager.obtencionDatos(20, 24, "M"));
        hombres.set("25 a 29", manager.obtencionDatos(25, 29, "M"));
        hombres.set("30 a 34", manager.obtencionDatos(30, 34, "M"));
        hombres.set("35 a 39", manager.obtencionDatos(35, 39, "M"));
        hombres.set("40 a 44", manager.obtencionDatos(40, 44, "M"));
        hombres.set("45 a 49", manager.obtencionDatos(45, 49, "M"));
        hombres.set("50 a 54", manager.obtencionDatos(50, 54, "M"));
        hombres.set("55 a 59", manager.obtencionDatos(55, 59, "M"));
        hombres.set("60 a 64", manager.obtencionDatos(60, 64, "M"));
        hombres.set("65 a 69", manager.obtencionDatos(65, 69, "M"));
        hombres.set("70 a 74", manager.obtencionDatos(70, 74, "M"));
        hombres.set("75 a 80", manager.obtencionDatos(75, 80, "M"));
        hombres.set("80 a 84", manager.obtencionDatos(80, 84, "M"));
 
        ChartSeries mujeres = new ChartSeries();
        mujeres.setLabel("MUJERES");
        mujeres.set("18 a 19", manager.obtencionDatos(18, 19, "F"));
        mujeres.set("20 a 24", manager.obtencionDatos(20, 24, "F"));
        mujeres.set("25 a 29", manager.obtencionDatos(25, 29, "F"));
        mujeres.set("30 a 34", manager.obtencionDatos(30, 34, "F"));
        mujeres.set("35 a 39", manager.obtencionDatos(35, 39, "F"));
        mujeres.set("40 a 44", manager.obtencionDatos(40, 44, "F"));
        mujeres.set("45 a 49", manager.obtencionDatos(45, 49, "F"));
        mujeres.set("50 a 54", manager.obtencionDatos(50, 54, "F"));
        mujeres.set("55 a 59", manager.obtencionDatos(55, 59, "F"));
        mujeres.set("60 a 64", manager.obtencionDatos(60, 64, "F"));
        mujeres.set("65 a 69", manager.obtencionDatos(65, 69, "F"));
        mujeres.set("70 a 74", manager.obtencionDatos(70, 74, "F"));
        mujeres.set("75 a 80", manager.obtencionDatos(75, 80, "F"));
        mujeres.set("80 a 84", manager.obtencionDatos(80, 84, "F"));
 
        model.addSeries(hombres);
        model.addSeries(mujeres);
         
        return model;
    }

}
