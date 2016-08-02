package city.controller.persona;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
public class ChartView implements Serializable {

	private BarChartModel dateModel;

	@PostConstruct
	public void init() {
		createDateModel();
		//crearHistograma();
	}

	public BarChartModel getDateModel() {
		return dateModel;
	}

	private void createDateModel() {
		dateModel = new BarChartModel();
		ChartSeries series1 = new ChartSeries();
		series1.setLabel("Series 1");

		series1.set("2014-01-01", 51);
		series1.set("2014-01-06", 22);
		series1.set("2014-01-30", 51);
		series1.set("2014-01-12", 65);
		series1.set("2014-01-18", 74);
		series1.set("2014-01-24", 24);

		ChartSeries series2 = new ChartSeries();
		series2.setLabel("Series 2");

		series2.set("2014-01-01", 32);
		series2.set("2014-01-06", 73);
		series2.set("2014-01-12", 24);
		series2.set("2014-01-18", 12);
		series2.set("2014-01-24", 74);
		series2.set("2014-01-30", 62);

		dateModel.addSeries(series1);
		dateModel.addSeries(series2);

		dateModel.setTitle("Zoom for Details");
		dateModel.setZoom(true);
		dateModel.getAxis(AxisType.Y).setLabel("Values");
		DateAxis axis = new DateAxis("Dates");
		axis.setTickAngle(-50);
		axis.setMax("2014-02-01");
		axis.setTickFormat("%b %#d, %y");

		dateModel.getAxes().put(AxisType.X, axis);
	}
	
	public void crearHistograma() {
		dateModel = new BarChartModel();
		LineChartSeries series1 = new LineChartSeries();
		LineChartSeries series2 = new LineChartSeries();
		LineChartSeries series3 = new LineChartSeries();
		LineChartSeries series4 = new LineChartSeries();
		series1.setLabel("A");
		series2.setLabel("B");
		series3.setLabel("C");
		series4.setLabel("D");
		
		series1.set("2014-01-01", 51);
		
		
		dateModel.addSeries(series1);
		dateModel.addSeries(series2);
		dateModel.addSeries(series3);
		dateModel.addSeries(series4);
		
		Axis xAxis = dateModel.getAxis(AxisType.X);
		xAxis.setTickAngle(-50);
		xAxis.setLabel("Fechas");

		Axis yAxis = dateModel.getAxis(AxisType.Y);
		yAxis.setLabel("Número de Incidencias");

		dateModel.setAnimate(true);
		dateModel.setLegendPosition("e");
	}
}