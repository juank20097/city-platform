package city.controller.persona;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import city.model.dao.entidades.GenPersona;
import city.model.manager.ManagerEstadistica;

@ManagedBean
@ViewScoped
public class EstadisticaBean implements Serializable{
	
	private static final long serialVersionUID = 5534059231591167181L;

	@EJB
	private ManagerEstadistica mngEst;
	
	private static String COD_EP = "1";
	private static String COD_TECH = "2";
	private static String COD_IST = "3";
	private static String COD_CIUDAD = "4";
	private static String LEYENDA = "Instituciones"; 
	private static String LEYENDA_INS = "Tipo de personas"; 
	
	private List<GenPersona> personasEP;
	private List<GenPersona> personasIST;
	private List<GenPersona> personasTECH;
	private List<GenPersona> personasCIUDAD;
	private int totalEP;
	private int totalTECH;
	private int totalIST;
	private int totalCIUDAD;
	private BarChartModel barModel;
	private PieChartModel pieModel;
	
	private boolean abrirInstitucion;
	private List<GenPersona> institucionEstudiantes;
	private List<GenPersona> institucionFuncionarios;
	private int totalEstudiantes;
	private int totalFuncionarios;
	private BarChartModel institucionBarModel;
	private PieChartModel inastitucionPieModel;
	
	private boolean abrirEstudiantes;
	private boolean abrirFuncionarios;
	
	public EstadisticaBean() {	}
	
	@PostConstruct
	public void init(){
		personasEP = new ArrayList<GenPersona>();
		personasIST = new ArrayList<GenPersona>();
		personasTECH = new ArrayList<GenPersona>();
		personasCIUDAD = new ArrayList<GenPersona>();
		institucionEstudiantes = new ArrayList<GenPersona>();
		institucionFuncionarios = new ArrayList<GenPersona>();
		cargarPersonas();
		cargarChartGenerales();
	}
	
	/**
	 * @return the personasEP
	 */
	public List<GenPersona> getPersonasEP() {
		return personasEP;
	}

	/**
	 * @param personasEP the personasEP to set
	 */
	public void setPersonasEP(List<GenPersona> personasEP) {
		this.personasEP = personasEP;
	}

	/**
	 * @return the personasIST
	 */
	public List<GenPersona> getPersonasIST() {
		return personasIST;
	}

	/**
	 * @param personasIST the personasIST to set
	 */
	public void setPersonasIST(List<GenPersona> personasIST) {
		this.personasIST = personasIST;
	}

	/**
	 * @return the personasTECH
	 */
	public List<GenPersona> getPersonasTECH() {
		return personasTECH;
	}

	/**
	 * @param personasTECH the personasTECH to set
	 */
	public void setPersonasTECH(List<GenPersona> personasTECH) {
		this.personasTECH = personasTECH;
	}
	
	/**
	 * @return the personasCIUDAD
	 */
	public List<GenPersona> getPersonasCIUDAD() {
		return personasCIUDAD;
	}

	/**
	 * @param personasCIUDAD the personasCIUDAD to set
	 */
	public void setPersonasCIUDAD(List<GenPersona> personasCIUDAD) {
		this.personasCIUDAD = personasCIUDAD;
	}
	
	/**
	 * @return the totalEP
	 */
	public int getTotalEP() {
		return totalEP;
	}

	/**
	 * @param totalEP the totalEP to set
	 */
	public void setTotalEP(int totalEP) {
		this.totalEP = totalEP;
	}

	/**
	 * @return the totalTECH
	 */
	public int getTotalTECH() {
		return totalTECH;
	}

	/**
	 * @param totalTECH the totalTECH to set
	 */
	public void setTotalTECH(int totalTECH) {
		this.totalTECH = totalTECH;
	}

	/**
	 * @return the totalIST
	 */
	public int getTotalIST() {
		return totalIST;
	}

	/**
	 * @param totalIST the totalIST to set
	 */
	public void setTotalIST(int totalIST) {
		this.totalIST = totalIST;
	}

	/**
	 * @return the totalCIUDAD
	 */
	public int getTotalCIUDAD() {
		return totalCIUDAD;
	}

	/**
	 * @param totalCIUDAD the totalCIUDAD to set
	 */
	public void setTotalCIUDAD(int totalCIUDAD) {
		this.totalCIUDAD = totalCIUDAD;
	}
	
	/**
	 * @return the barModel
	 */
	public BarChartModel getBarModel() {
		return barModel;
	}

	/**
	 * @param barModel the barModel to set
	 */
	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	/**
	 * @return the pieModel
	 */
	public PieChartModel getPieModel() {
		return pieModel;
	}

	/**
	 * @param pieModel the pieModel to set
	 */
	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}
	
	//INSTITUCION
	/**
	 * @return the abrirInstitucion
	 */
	public boolean isAbrirInstitucion() {
		return abrirInstitucion;
	}

	/**
	 * @param abrirInstitucion the abrirInstitucion to set
	 */
	public void setAbrirInstitucion(boolean abrirInstitucion) {
		this.abrirInstitucion = abrirInstitucion;
	}

	/**
	 * @return the institucionEstudiantes
	 */
	public List<GenPersona> getInstitucionEstudiantes() {
		return institucionEstudiantes;
	}

	/**
	 * @param institucionEstudiantes the institucionEstudiantes to set
	 */
	public void setInstitucionEstudiantes(List<GenPersona> institucionEstudiantes) {
		this.institucionEstudiantes = institucionEstudiantes;
	}

	/**
	 * @return the institucionFuncionarios
	 */
	public List<GenPersona> getInstitucionFuncionarios() {
		return institucionFuncionarios;
	}

	/**
	 * @param institucionFuncionarios the institucionFuncionarios to set
	 */
	public void setInstitucionFuncionarios(List<GenPersona> institucionFuncionarios) {
		this.institucionFuncionarios = institucionFuncionarios;
	}

	/**
	 * @return the totalEstudiantes
	 */
	public int getTotalEstudiantes() {
		return totalEstudiantes;
	}

	/**
	 * @param totalEstudiantes the totalEstudiantes to set
	 */
	public void setTotalEstudiantes(int totalEstudiantes) {
		this.totalEstudiantes = totalEstudiantes;
	}

	/**
	 * @return the totalFuncionarios
	 */
	public int getTotalFuncionarios() {
		return totalFuncionarios;
	}

	/**
	 * @param totalFuncionarios the totalFuncionarios to set
	 */
	public void setTotalFuncionarios(int totalFuncionarios) {
		this.totalFuncionarios = totalFuncionarios;
	}

	/**
	 * @return the institucionBarModel
	 */
	public BarChartModel getInstitucionBarModel() {
		return institucionBarModel;
	}

	/**
	 * @param institucionBarModel the institucionBarModel to set
	 */
	public void setInstitucionBarModel(BarChartModel institucionBarModel) {
		this.institucionBarModel = institucionBarModel;
	}

	/**
	 * @return the inastitucionPieModel
	 */
	public PieChartModel getInastitucionPieModel() {
		return inastitucionPieModel;
	}

	/**
	 * @param inastitucionPieModel the inastitucionPieModel to set
	 */
	public void setInastitucionPieModel(PieChartModel inastitucionPieModel) {
		this.inastitucionPieModel = inastitucionPieModel;
	}
	
	/**
	 * @return the abrirEstudiantes
	 */
	public boolean isAbrirEstudiantes() {
		return abrirEstudiantes;
	}

	/**
	 * @param abrirEstudiantes the abrirEstudiantes to set
	 */
	public void setAbrirEstudiantes(boolean abrirEstudiantes) {
		this.abrirEstudiantes = abrirEstudiantes;
	}

	/**
	 * @return the abrirFuncionarios
	 */
	public boolean isAbrirFuncionarios() {
		return abrirFuncionarios;
	}

	/**
	 * @param abrirFuncionarios the abrirFuncionarios to set
	 */
	public void setAbrirFuncionarios(boolean abrirFuncionarios) {
		this.abrirFuncionarios = abrirFuncionarios;
	}
	
	//METODOS PRINCIPALES

	/**
	 * Carga listados de personas por institución
	 */
	private void cargarPersonas() {
		getPersonasEP().clear();
		getPersonasEP().addAll(mngEst.personasPorInstitucion(COD_EP));
		getPersonasIST().clear();
		getPersonasIST().addAll(mngEst.personasPorInstitucion(COD_IST));
		getPersonasTECH().clear();
		getPersonasTECH().addAll(mngEst.personasPorInstitucion(COD_TECH));
		getPersonasCIUDAD().clear();
		getPersonasCIUDAD().addAll(mngEst.personasPorInstitucion(COD_CIUDAD));
		setearTotales();
	}

	/**
	 * Carga valor totales de los listados de personas por institución
	 */
	private void setearTotales() {
		setTotalEP(getPersonasEP().size());
		setTotalIST(getPersonasIST().size());
		setTotalTECH(getPersonasTECH().size());
		setTotalCIUDAD(getPersonasCIUDAD().size());
	}

	/**
	 * Dibuja los gráficos generales
	 */
	private void cargarChartGenerales() {
		crearBarChartGeneral();
		crearPieChartGeneral();
	}

	/**
	 * Grafica el CHART general
	 */
	private void crearBarChartGeneral() {
		barModel = new BarChartModel();
		
		ChartSeries yachayEP = new ChartSeries("Yachay EP");
		yachayEP.set(LEYENDA, getTotalEP());
		
		ChartSeries yachayTECH = new ChartSeries("Yachay TECH");
		yachayTECH.set(LEYENDA, getTotalTECH());
		
		ChartSeries yachayIST = new ChartSeries("IST");
		yachayIST.set(LEYENDA, getTotalIST());
		
		ChartSeries yachayCiudad = new ChartSeries("Ciudad");
		yachayCiudad.set(LEYENDA, getTotalCIUDAD());

		
		barModel.addSeries(yachayEP);
		barModel.addSeries(yachayTECH);
		barModel.addSeries(yachayIST);
		barModel.addSeries(yachayCiudad);

		
		barModel.setShowPointLabels(true);
		barModel.setShowDatatip(false);
		barModel.setTitle("Personas dentro de Yachay");
	    barModel.setLegendPosition("ne");
	       
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Total Personas");
        yAxis.setMin(0);
        yAxis.setMax(mngEst.mayorPersonas(new int [] {getTotalEP(),getTotalTECH(),getTotalIST(),getTotalCIUDAD()}));
	}

	/**
	 * Grafica el PIE general
	 */
	private void crearPieChartGeneral() {
		pieModel = new PieChartModel();
		pieModel.set("Yachay EP", getTotalEP());
		pieModel.set("Yachay TECH", getTotalTECH());
		pieModel.set("IST", getTotalIST());
		pieModel.set("Ciudad", getTotalCIUDAD());
        pieModel.setShowDataLabels(true);
		pieModel.setTitle("Personas dentro de Yachay");
        pieModel.setLegendPosition("w");
	}

	/**
	 * Carga los datos de personas por institución
	 * @param tipo
	 */
	public void cargarPersonal(Object tipo){
		RequestContext.getCurrentInstance().execute("PF('statusDialog').show();");
		if(tipo.equals(COD_EP))
			cargarDatosInstitucion(COD_EP);
		if(tipo.equals(COD_TECH))
			cargarDatosInstitucion(COD_TECH);
		if(tipo.equals(COD_IST))
			cargarDatosInstitucion(COD_IST);
		if(tipo.equals(COD_CIUDAD))
			cargarDatosInstitucion(COD_CIUDAD);
		RequestContext.getCurrentInstance().execute("PF('statusDialog').hide();");
	}
	
	/**
	 * 
	 */
	public void volverPrincipal(){
		setAbrirInstitucion(false);
		setAbrirEstudiantes(false);
		setAbrirFuncionarios(false);
	}
	
	//METODOS POR INSTITUCION
	
	/**
	 * Carga los datos según la selección de institución
	 * @param insCodigo
	 */
	private void cargarDatosInstitucion(String insCodigo){
		getInstitucionEstudiantes().clear();
		setInstitucionEstudiantes(mngEst.estudiantesPorInstitucion(insCodigo));
		getInstitucionFuncionarios().clear();
		setInstitucionFuncionarios(mngEst.funcionariosPorInstitucion(insCodigo));
		cargarValoresTotalesInstitucion();
		cargarGraficosInstitucion();
		setAbrirInstitucion(true);
	}

	/**
	 * Setea los valores totales de estudiantes y funcionarios de institución
	 */
	private void cargarValoresTotalesInstitucion() {
		setTotalEstudiantes(getInstitucionEstudiantes().size());
		setTotalFuncionarios(getInstitucionFuncionarios().size());
	}
	
	/**
	 * Grafica los modelos de institución
	 */
	private void cargarGraficosInstitucion() {
		crearBarChartInstitucion();
		crearPieChartInstitucion();
	}

	/**
	 * Dibuja el CHAR de institución
	 */
	private void crearBarChartInstitucion() {
		institucionBarModel = new BarChartModel();
		
		ChartSeries est = new ChartSeries("Estudiantes");
		est.set(LEYENDA_INS, getTotalEstudiantes());
		
		ChartSeries fun = new ChartSeries("Funcionarios");
		fun.set(LEYENDA_INS, getTotalFuncionarios());
		
		institucionBarModel.addSeries(est);
		institucionBarModel.addSeries(fun);
		
		institucionBarModel.setShowPointLabels(true);
		institucionBarModel.setShowDatatip(false);
		institucionBarModel.setTitle("Personas dentro de Institución");
		institucionBarModel.setLegendPosition("ne");
		
		Axis yAxis = institucionBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Total Personas");
        yAxis.setMin(0);
        yAxis.setMax(mngEst.mayorEntreDosPorcentaje(getTotalEstudiantes(), getTotalFuncionarios()));
	}
	
	/**
	 * Dibuja el PIE de Institución
	 */
	private void crearPieChartInstitucion() {
		inastitucionPieModel = new PieChartModel();
		inastitucionPieModel.set("Estudiantes", getTotalEstudiantes());
		inastitucionPieModel.set("Funcionarios", getTotalFuncionarios());
		inastitucionPieModel.setShowDataLabels(true);
		inastitucionPieModel.setTitle("Personas dentro de Institución");
		inastitucionPieModel.setLegendPosition("w");
	}
	
	/**
	 * Muestra el listado de funcionarios o estudiantes según institución
	 * @param tipo
	 */
	public void abrirListado(Object tipo){
		System.out.println("EJECUTAR "+tipo);
		if(tipo.equals("E")){
			setAbrirEstudiantes(true);
			setAbrirFuncionarios(false);
		}
		if(tipo.equals("F")){
			setAbrirEstudiantes(false);
			setAbrirFuncionarios(true);
		}
		
	}
		
}
