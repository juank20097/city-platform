package city.controller.persona;


import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GenEstudianteInstitucion;
import city.model.dao.entidades.GenInstitucione;
import city.model.dao.entidades.GenPersona;
import city.model.dao.entidades.extras.Estudiante;
import city.model.generic.Funciones;
import city.model.generic.Mensaje;
import city.model.manager.ManagerCarga;

/**
 * @author jestevez
 * 
 */
@SessionScoped
@ManagedBean
public class EstudiantesBean {

	// Atributos de la Clase
	@EJB
	private ManagerCarga manager;
	@Inject
	private SesionBean session;

	// Atriutos de la clase persona detalle
	private String insCodigo;
	private String insCodigoBusqueda;

	private int NUMERO_COLUMNAS_EXCEL_ESTUDIANTE = 14;

	// listas de registros
	private List<Estudiante> l_estudiantes;
	private List<Estudiante> l_estudiantes_total;
	private List<String> errores;
	private List<SelectItem> l_instituciones;

	// string con todos los errores
	private String error;

	// atributos de Registro excel
	private String exc_nombre;
	private String exc_usuario;
	
	//atributo de direccion de url
	private String url_doc;
	
	public EstudiantesBean() {
	}

	@PostConstruct
	public void ini() {
		exc_usuario = session.validarSesion();
		l_instituciones = new ArrayList<SelectItem>();
		l_estudiantes = new ArrayList<Estudiante>();
		l_estudiantes_total = new ArrayList<Estudiante>();
		errores = new ArrayList<String>();
		try {
			url_doc = manager.ParametroByID("direccion_doc");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selecInsti();
	}

	/**
	 * @return the insCodigoBusqueda
	 */
	public String getInsCodigoBusqueda() {
		return insCodigoBusqueda;
	}

	/**
	 * @param insCodigoBusqueda
	 *            the insCodigoBusqueda to set
	 */
	public void setInsCodigoBusqueda(String insCodigoBusqueda) {
		this.insCodigoBusqueda = insCodigoBusqueda;
	}

	/**
	 * @return the l_instituciones
	 */
	public List<SelectItem> getL_instituciones() {
		return l_instituciones;
	}

	/**
	 * @param l_instituciones
	 *            the l_instituciones to set
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
	 * @param l_estudiantes_total
	 *            the l_estudiantes_total to set
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

	private void ListEstudiantes() {
		try {
			l_estudiantes_total = new ArrayList<Estudiante>();
			if (getInsCodigoBusqueda() == null
					|| getInsCodigoBusqueda().equals("-1")) {
				Mensaje.crearMensajeWARN("No existe los estudiantes respectivos para la institución seleccionada");
			} else {
				for (GenEstudianteInstitucion est : manager
						.findAllEstudiantesXInstitucion(getInsCodigoBusqueda())) {
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
					estudiante.setPerFechaNacimiento(per
							.getPerFechaNacimiento());
					estudiante.setPerGenero(per.getPerGenero());
					estudiante.setPerNombres(per.getPerNombres());
					estudiante.setPerTelefono(per.getPerTelefono());
					estudiante.setPerTipoDni(per.getPerTipoDni());
					l_estudiantes_total.add(estudiante);
				}
				this.crearExcel(l_estudiantes_total);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// MÉTODOS_DE_EXCEL///////////////////////////////////////////////////////////////////////////////

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
					exc_nombre = event.getFile().getFileName();
					validarGuardarDatosExcel(event.getFile());
					// this.ListEstudiantes();
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
		String resultado = "";
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
				// Método para saber los datos de todo el excel
				for (int j = 0; j < NUMERO_COLUMNAS_EXCEL_ESTUDIANTE; j++) {
					datosFila.add(hoja.getCell(j, i).getContents().trim());
					System.out.println("fila:" + i + " ,columna:" + j
							+ " dato:" + hoja.getCell(j, i).getContents());
				}
				l_estudiantes
						.add(manager.crearEstudiante(datosFila, insCodigo));
			}
		}
		// inactivar estudiantes no encontrados y activos
		manager.inactivarEstudiantes(l_estudiantes, getInsCodigo());
		// ingreso de estudiantes
		manager.ingresarEstudiantePersona(l_estudiantes);
		// Inserciones a registros Excel
		resultado = manager.insertarExcel(exc_usuario, exc_nombre);
		// mostrar errores
		if (errores.size() > 0) {
			mostrarListaErrores();
			Mensaje.crearMensajeWARN("Existió errores dentro del archivo, "
					+ "pero los datos sin error fueron guardados. " + resultado);
		} else

			// Método para cargar Registro de Excel
			Mensaje.crearMensajeINFO("Datos ingresados correctamente. \n"
					+ resultado);
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

	// ///////////////////////////////////////CIERRE_MÉTODOS//////////////////////////////////////////////

	/**
	 * Método para cargar los selectItems de Institución
	 * 
	 * @return
	 */
	public List<SelectItem> selecInsti() {
		try {
			for (GenInstitucione insti : manager
					.findAllInstitucionesEducativas()) {
				l_instituciones.add(new SelectItem(insti.getInsCodigo(), insti
						.getInsNombre()));
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
	public void mostrarInstitucion() {
		l_estudiantes = new ArrayList<Estudiante>();
		System.out.println(getInsCodigo());
	}

	/**
	 * Metodo para cargar todos los estudiantes
	 */
	public void cargarEstudiantesTotal() {
		this.ListEstudiantes();
	}

	// ////////////////////////////////////////(Método_creación_excel_imprimir)///////////////////////////////////////////////////////

	/**
	 * Método para crear un excel y guardarlo en una dirección específica
	 * 
	 * @param est
	 */
	public void crearExcel(List<Estudiante> est) {
		try {
//			ServletContext servletContext = (ServletContext) FacesContext
//					.getCurrentInstance().getExternalContext().getContext();
//			String contextPath = servletContext.getRealPath(File.separator
//					+ "resources/doc/descarga");
			String url=url_doc+"/descarga";
			System.out.println(url);

			HSSFWorkbook libro = new HSSFWorkbook();

			HSSFSheet hoja = libro.createSheet("Datos");
			System.out.println(est.size());
			for (int i = 0; i <= est.size() - 1; i++) {
				HSSFRow row = hoja.createRow(i);
				llenarFila(est.get(i), row);
			}
			OutputStream out = new FileOutputStream(url
					+ "DatosExcel_Estudiante.xls");
			libro.write(out);
			libro.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método para llenar una fila de un archivo excel
	 * 
	 * @param est
	 * @param row
	 */
	public void llenarFila(Estudiante est, HSSFRow row) {
		if (row.getRowNum() == 0) {
			HSSFCell celda0 = row.createCell(0);
			celda0.setCellValue("CÉDULA");
			HSSFCell celda1 = row.createCell(1);
			celda1.setCellValue("NOMBRE COMPLETO");
			HSSFCell celda2 = row.createCell(2);
			celda2.setCellValue("CORREO INSTITUCIONAL");
			HSSFCell celda3 = row.createCell(3);
			celda3.setCellValue("CORREO PERSONAL");
			HSSFCell celda4 = row.createCell(4);
			celda4.setCellValue("FECHA DE NACIMIENTO");
			HSSFCell celda5 = row.createCell(5);
			celda5.setCellValue("CARRERA");
			HSSFCell celda6 = row.createCell(6);
			celda6.setCellValue("NIVEL");
			HSSFCell celda7 = row.createCell(7);
			celda7.setCellValue("GÉNERO");
			HSSFCell celda8 = row.createCell(8);
			celda8.setCellValue("ESTADO");
		} else {
			HSSFCell celda0 = row.createCell(0);
			celda0.setCellValue(est.getPerDni());
			HSSFCell celda1 = row.createCell(1);
			celda1.setCellValue(est.getPerNombres() + " "
					+ est.getPerApellidos());
			HSSFCell celda2 = row.createCell(2);
			celda2.setCellValue(est.getEstCorreo());
			HSSFCell celda3 = row.createCell(3);
			celda3.setCellValue(est.getPerCorreo());
			HSSFCell celda4 = row.createCell(4);
			celda4.setCellValue(Funciones.dateToString(est
					.getPerFechaNacimiento()));
			HSSFCell celda5 = row.createCell(5);
			celda5.setCellValue(est.getEstCarrera());
			HSSFCell celda6 = row.createCell(6);
			celda6.setCellValue(est.getEstNivel());
			HSSFCell celda7 = row.createCell(7);
			celda7.setCellValue(est.getPerGenero());
			HSSFCell celda8 = row.createCell(8);
			celda8.setCellValue(est.getEstEstado());
		}
	}

	/**
	 * Método para descargar un archivo excel 
	 */
	public void descargarArchivo() {
		if (getInsCodigoBusqueda()==null || getInsCodigoBusqueda().equals("-1")){
			Mensaje.crearMensajeWARN("No se puede realizar la exportación del archivo porque la lista está vacía o nula.");
		}else{
//			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
//					.getContext();
//			String contextPath = servletContext
//					.getRealPath(File.separator + "resources/doc/descargaDatosExcel_Estudiante.xls");
		try {
			Funciones.descargarExcel(url_doc+"/descargaDatosExcel_Estudiante.xls");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Mensaje.crearMensajeERROR("El archivo no fue generado correctamente.");
			e.printStackTrace();
		}
		}
	}
	
	/**
	 * Método para descargar los archivos de ejemplo
	 */
	public void descargarArchivoEjemplo(){
		// ServletContext servletContext = (ServletContext)
		// FacesContext.getCurrentInstance().getExternalContext()
		// .getContext();
		// String contextPath = servletContext.getRealPath(File.separator +
		// "resources/doc/Ejemplo_Base_Estudiantes.xls");
		try {
			Funciones.descargarExcel(url_doc+"/Ejemplo_Base_Estudiantes.xls");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Mensaje.crearMensajeERROR("El archivo no fue encontrado para su descarga.");
			e.printStackTrace();
		}
	}
}
