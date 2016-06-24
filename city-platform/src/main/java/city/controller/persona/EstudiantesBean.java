package city.controller.persona;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import city.model.dao.entidades.GenEstudianteInstitucion;
import city.model.dao.entidades.GenInstitucione;
import city.model.dao.entidades.GenPersona;
import city.model.dao.entidades.extras.Estudiante;
import city.model.generic.Mensaje;
import city.model.manager.ManagerTipos;

/**
 * @author jestevez
 * 
 */
@SessionScoped
@ManagedBean
public class EstudiantesBean {

	// Atributos de la Clase
	@EJB
	private ManagerTipos manager;

	// Atriutos de la clase persona detalle
	private String insCodigo;

	private int NUMERO_COLUMNAS_EXCEL_ESTUDIANTE = 14;

	// listas de registros
	private List<Estudiante> l_estudiantes;
	private List<Estudiante> l_estudiantes_total;
	private List<String> errores;
	private List<SelectItem> l_instituciones;

	// string con todos los errores
	private String error;

	// content de el archivo base a ser descargado
	private StreamedContent file;

	public EstudiantesBean() {
	}

	@PostConstruct
	public void ini() {
		l_instituciones = new ArrayList<SelectItem>();
		l_estudiantes = new ArrayList<Estudiante>();
		l_estudiantes_total = new ArrayList<Estudiante>();
		errores = new ArrayList<String>();
		InputStream stream = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/resources/doc/excelbase.xls");
		file = new DefaultStreamedContent(stream, "texto/xls",
				"archivo_Ejemplo_Estudiantes.xls");
		selecInsti();
	}

	/**
	 * @return the l_instituciones
	 */
	public List<SelectItem> getL_instituciones() {
		return l_instituciones;
	}

	/**
	 * @param l_instituciones the l_instituciones to set
	 */
	public void setL_instituciones(List<SelectItem> l_instituciones) {
		this.l_instituciones = l_instituciones;
	}

	/**
	 * @return the insCodigo
	 */
	public String getInsCodigo() {
		return insCodigo;
	}

	/**
	 * @param insCodigo
	 *            the insCodigo to set
	 */
	public void setInsCodigo(String insCodigo) {
		this.insCodigo = insCodigo;
	}

	/**
	 * @return the l_estudiantes_total
	 */
	public List<Estudiante> getL_estudiantes_total() {
		return l_estudiantes_total;
	}

	/**
	 * @param l_estudiantes_total the l_estudiantes_total to set
	 */
	public void setL_estudiantes_total(List<Estudiante> l_estudiantes_total) {
		this.l_estudiantes_total = l_estudiantes_total;
	}

	/**
	 * @return the l_estudiantes
	 */
	public List<Estudiante> getL_estudiantes() {
		return l_estudiantes;
	}

	/**
	 * @param l_estudiantes
	 *            the l_estudiantes to set
	 */
	public void setL_estudiantes(List<Estudiante> l_estudiantes) {
		this.l_estudiantes = l_estudiantes;
	}

	/**
	 * @return the errores
	 */
	public List<String> getErrores() {
		return errores;
	}

	/**
	 * @param errores
	 *            the errores to set
	 */
	public void setErrores(List<String> errores) {
		this.errores = errores;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the file
	 */
	public StreamedContent getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(StreamedContent file) {
		this.file = file;
	}

	private void ListEstudiantes() {
		try {
			l_estudiantes_total = new ArrayList<Estudiante>();
			if (getInsCodigo()==null || getInsCodigo().equals("-1")){
				Mensaje.crearMensajeWARN("No existe los estudiantes respectivos para la institución seleccionada");
			}else{
			for (GenEstudianteInstitucion est : manager.findAllEstudiantesXInstitucion(getInsCodigo())) {
				GenPersona per = manager.PersonaByID(est.getGenPersona()
						.getPerDni());
				Estudiante estudiante = new Estudiante();
				estudiante.setEstAreaEstudio(est.getEstAreaEstudio());
				estudiante.setEstCarrera(est.getEstCarrera());
				estudiante.setEstCorreo(est.getEstCorreo());
				estudiante.setEstEstado(est.getEstEstado());
				estudiante.setEstModalidad(est.getEstModalidad());
				estudiante.setEstNivel(est.getEstModalidad());
				estudiante.setIns_codigo(est.getGenInstitucione()
						.getInsCodigo());
				estudiante.setPerApellidos(per.getPerApellidos());
				estudiante.setPerCelular(per.getPerCelular());
				estudiante.setPerCorreo(per.getPerCorreo());
				estudiante.setPerDni(per.getPerDni());
				estudiante.setPerEstado(per.getPerEstado());
				estudiante.setPerEstadoCivil(per.getPerEstadoCivil());
				estudiante.setPerFechaNacimiento(per.getPerFechaNacimiento());
				estudiante.setPerGenero(per.getPerGenero());
				estudiante.setPerNombres(per.getPerNombres());
				estudiante.setPerTelefono(per.getPerTelefono());
				estudiante.setPerTipoDni(per.getPerTipoDni());
				l_estudiantes_total.add(estudiante);
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//MÉTODOS_DE_EXCEL///////////////////////////////////////////////////////////////////////////////

	/**
	 * Maneja el proceso de selección, carga e inserción de datos de
	 * estudiantes, partiendo de un archivo excel .XLS
	 * 
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		try {
			
			if (getInsCodigo() == null || getInsCodigo().isEmpty()
					|| getInsCodigo().equals("-1")) {
				Mensaje.crearMensajeWARN("Debe seleccionar una institución para ser añadida la información");
			} else {
				if (event.getFile() == null)
					throw new Exception("No se ha seleccionado archivo");
				else {
					validarGuardarDatosExcel(event.getFile());
					//this.ListEstudiantes();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Mensaje.crearMensajeERROR(e.getMessage());
		}
	}

	/**
	 * Valida y Almacena los datos de excel
	 * 
	 * @param archivo
	 * @throws Exception
	 */
	public void validarGuardarDatosExcel(UploadedFile archivo) throws Exception {
		l_estudiantes = new ArrayList<Estudiante>();
		errores = new ArrayList<String>();
		List<String> datosFila = new ArrayList<String>();
		// Toma la primera hoja
		Sheet hoja = Workbook.getWorkbook(archivo.getInputstream()).getSheet(0);
		// Validar estructura de excel
		if (!poseeEstructuraValidaEstudiante(hoja))
			throw new Exception("El archivo no posee la estructura correcta.");
		// Recorre todas las filas y columnas
		for (int i = 1; i < hoja.getRows(); i++) {
			if (filaValida(hoja.getRow(i), i + 1)) {
				datosFila.clear();
				for (int j = 0; j < NUMERO_COLUMNAS_EXCEL_ESTUDIANTE; j++) {
					datosFila.add(hoja.getCell(j, i).getContents().trim());
					System.out.println("fila:"+i+" ,columna:"+j+" dato:"+hoja.getCell(j, i).getContents());
				}
				l_estudiantes
						.add(manager.crearEstudiante(datosFila, insCodigo));
			}
		}
		// ingresar personas
		manager.ingresarEstudiantePersona(l_estudiantes);
		// mostrar errores
		if (errores.size() > 0) {
			mostrarListaErrores();
			Mensaje.crearMensajeWARN("Existió errores dentro del archivo, "
					+ "pero los datos sin error fueron guardados.");
		} else
			Mensaje.crearMensajeINFO("Datos ingresados correctamente");
	}

	/**
	 * Abre un popup con la lista de errores
	 */
	private void mostrarListaErrores() {
		error = "";
		RequestContext.getCurrentInstance().execute("PF('dlgerr').show()");
		for (String e : errores) {
			error = error + e + "\n";
			System.out.println(error);
		}
	}

	/**
	 * Valida la extructura de Excel
	 * 
	 * @param hoja
	 * @return boolean
	 */
	private boolean poseeEstructuraValidaEstudiante(Sheet hoja) {
		return manager.validarEncabezadosExcelEstudiante(hoja.getRow(0));
	}

	/**
	 * Valida los datos de una fila
	 * 
	 * @param column
	 * @param nroFila
	 * @return boolean
	 */
	private boolean filaValida(Cell[] column, int nroFila) {
		String error = manager.validarFilaExcelEstudiante(column);
		if (error.isEmpty())
			return true;
		else {
			errores.add("Fila NRO " + nroFila + " : " + error);
			return false;
		}
	}

	/////////////////////////////////////////CIERRE_MÉTODOS//////////////////////////////////////////////
	
	/**
	 * Método para cargar los selectItems de Institución
	 * 
	 * @return
	 */
	public List<SelectItem> selecInsti(){
		try {
			for (GenInstitucione insti : manager.findAllInstitucionesEducativas()) {
				l_instituciones.add(new SelectItem(insti.getInsCodigo(), insti.getInsNombre()));
			}
			return l_instituciones;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo para cargar los sitios
	 */
	public void cargarEstudiantes() {
		this.ListEstudiantes();
	}
	
	/**
	 * Metodo para verificar la institución seleccionada
	 */
	public void mostrarInstitucion(){
		l_estudiantes = new ArrayList<Estudiante>();
		System.out.println(getInsCodigo());
	}
	
	/**
	 * Metodo para cargar todos los estudiantes
	 */
	public void cargarEstudiantesTotal(){
		this.ListEstudiantes();
	}
	
}
