package city.controller.persona;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.ServletContext;

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
import city.model.dao.entidades.GenFuncionariosInstitucion;
import city.model.dao.entidades.GenInstitucione;
import city.model.dao.entidades.GenPersona;
import city.model.dao.entidades.extras.Funcionario;
import city.model.generic.Funciones;
import city.model.generic.Mensaje;
import city.model.manager.ManagerCarga;

/**
 * @author jestevez
 * 
 */
@SessionScoped
@ManagedBean
public class FuncionarioBean {

	// Atributos de la Clase
	@EJB
	private ManagerCarga manager;
	@Inject
	private SesionBean session;

	// Atriutos de la clase persona detalle
	private String insCodigo;
	private String insCodigoBusqueda;

	private int NUMERO_COLUMNAS_EXCEL_ESTUDIANTE = 16;

	// listas de registros
	private List<Funcionario> l_funcionarios;
	private List<Funcionario> l_funcionarios_total;
	private List<String> errores;
	private List<SelectItem> l_instituciones;

	// string con todos los errores
	private String error;

	// atributos de Registro excel
	private String exc_nombre;
	private String exc_usuario;

	public FuncionarioBean() {
	}

	@PostConstruct
	public void ini() {
		exc_usuario = session.validarSesion();
		l_instituciones = new ArrayList<SelectItem>();
		l_funcionarios = new ArrayList<Funcionario>();
		l_funcionarios_total = new ArrayList<Funcionario>();
		errores = new ArrayList<String>();
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
	 * @return the l_funcionarios
	 */
	public List<Funcionario> getL_funcionarios() {
		return l_funcionarios;
	}

	/**
	 * @param l_funcionarios
	 *            the l_funcionarios to set
	 */
	public void setL_funcionarios(List<Funcionario> l_funcionarios) {
		this.l_funcionarios = l_funcionarios;
	}

	/**
	 * @return the l_funcionarios_total
	 */
	public List<Funcionario> getL_funcionarios_total() {
		return l_funcionarios_total;
	}

	/**
	 * @param l_funcionarios_total
	 *            the l_funcionarios_total to set
	 */
	public void setL_funcionarios_total(List<Funcionario> l_funcionarios_total) {
		this.l_funcionarios_total = l_funcionarios_total;
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

	private void ListFuncionarios() {
		try {
			l_funcionarios_total = new ArrayList<Funcionario>();
			if (getInsCodigoBusqueda() == null
					|| getInsCodigoBusqueda().equals("-1")) {
				Mensaje.crearMensajeWARN("No existe los funcionarios respectivos para la institución seleccionada");
			} else {
				for (GenFuncionariosInstitucion f : manager
						.findAllFuncionarioXInstitucion(getInsCodigoBusqueda())) {
					GenPersona per = manager.PersonaByID(f.getGenPersona()
							.getPerDni());
					Funcionario fun = new Funcionario();
					fun.setFunCargo(f.getFunCargo());
					fun.setFunDireccion(f.getFunDireccion());
					fun.setFunEstado(f.getFunEstado());
					fun.setFunFechaIngreso(f.getFunFechaIngreso());
					fun.setFunGerencia(f.getFunGerencia());
					fun.setFunJefeInmediato(f.getFunJefeInmediato());
					fun.setFunTipo(f.getFunTipo());
					fun.setFunTipoEvaluacion(f.getFunTipoEvaluacion());
					fun.setPerApellidos(per.getPerApellidos());
					fun.setPerCelular(per.getPerCelular());
					fun.setPerCorreo(per.getPerCorreo());
					fun.setPerDni(per.getPerDni());
					fun.setPerEstado(per.getPerEstado());
					fun.setPerEstadoCivil(per.getPerEstadoCivil());
					fun.setPerFechaNacimiento(per
							.getPerFechaNacimiento());
					fun.setPerGenero(per.getPerGenero());
					fun.setPerNombres(per.getPerNombres());
					fun.setPerTelefono(per.getPerTelefono());
					fun.setPerTipoDni(per.getPerTipoDni());
					l_funcionarios_total.add(fun);
				}
				this.crearExcel(l_funcionarios_total);
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
		l_funcionarios = new ArrayList<Funcionario>();
		errores = new ArrayList<String>();
		List<String> datosFila = new ArrayList<String>();
		// Toma la primera hoja
		Sheet hoja = Workbook.getWorkbook(archivo.getInputstream()).getSheet(0);
		// Validar estructura de excel
		if (!poseeEstructuraValidaFuncionario(hoja))
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
				l_funcionarios
						.add(manager.crearFuncionario(datosFila, insCodigo));
			}
		}
		// inactivar estudiantes no encontrados y activos
		manager.inactivarFuncionario(l_funcionarios, getInsCodigo());
		// ingreso de estudiantes
		manager.ingresarFuncionario(l_funcionarios);
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
	private boolean poseeEstructuraValidaFuncionario(Sheet hoja) {
		return manager.validarEncabezadosExcelFuncionario(hoja.getRow(0));
	}

	/**
	 * Valida los datos de una fila
	 * 
	 * @param column
	 * @param nroFila
	 * @return boolean
	 */
	private boolean filaValida(Cell[] column, int nroFila) {
		String error = manager.validarFilaExcelFuncionario(column);
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
					.findAllInstitucionesServicios()) {
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
	public void cargarFuncionarios() {
		this.ListFuncionarios();
	}

	/**
	 * Metodo para verificar la institución seleccionada
	 */
	public void mostrarInstitucion() {
		l_funcionarios = new ArrayList<Funcionario>();
		System.out.println(getInsCodigo());
	}

	/**
	 * Metodo para cargar todos los estudiantes
	 */
	public void cargarFuncionariosTotal() {
		this.ListFuncionarios();
	}

	// ////////////////////////////////////////(Método_creación_excel_imprimir)///////////////////////////////////////////////////////

	/**
	 * Método para crear un excel y guardarlo en una dirección específica
	 * 
	 * @param est
	 */
	public void crearExcel(List<Funcionario> fun) {
		try {
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String contextPath = servletContext.getRealPath(File.separator
					+ "resources/doc/descarga");
			System.out.println(contextPath);

			HSSFWorkbook libro = new HSSFWorkbook();

			HSSFSheet hoja = libro.createSheet("Datos");
			System.out.println(fun.size());
			for (int i = 0; i <= fun.size() - 1; i++) {
				HSSFRow row = hoja.createRow(i);
				llenarFila(fun.get(i), row);
			}
			OutputStream out = new FileOutputStream(contextPath
					+ "DatosExcel_Funcionarios.xls");
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
	public void llenarFila(Funcionario est, HSSFRow row) {
		if (row.getRowNum() == 0) {
			HSSFCell celda0 = row.createCell(0);
			celda0.setCellValue("CÉDULA");
			HSSFCell celda1 = row.createCell(1);
			celda1.setCellValue("NOMBRE COMPLETO");
			HSSFCell celda2 = row.createCell(2);
			celda2.setCellValue("CORREO PERSONAL");
			HSSFCell celda3 = row.createCell(3);
			celda3.setCellValue("FECHA DE NACIMIENTO");
			HSSFCell celda4 = row.createCell(4);
			celda4.setCellValue("TELÉFONO");
			HSSFCell celda5 = row.createCell(5);
			celda5.setCellValue("GÉNERO");
			HSSFCell celda6 = row.createCell(6);
			celda6.setCellValue("GERENCIA");
			HSSFCell celda7 = row.createCell(7);
			celda7.setCellValue("DIRECCIÓN");
			HSSFCell celda8 = row.createCell(8);
			celda8.setCellValue("CARGO");
			HSSFCell celda9 = row.createCell(9);
			celda9.setCellValue("FECHA INGRESO");
			HSSFCell celda10 = row.createCell(10);
			celda10.setCellValue("JEFE INMEDIATO");
			HSSFCell celda11 = row.createCell(11);
			celda11.setCellValue("TIPO");
			HSSFCell celda12= row.createCell(12);
			celda12.setCellValue("TIPO EVALUACIÓN");
		} else {
			HSSFCell celda0 = row.createCell(0);
			celda0.setCellValue(est.getPerDni());
			HSSFCell celda1 = row.createCell(1);
			celda1.setCellValue(est.getPerNombres() + " "
					+ est.getPerApellidos());
			HSSFCell celda2 = row.createCell(2);
			celda2.setCellValue(est.getPerCorreo());
			HSSFCell celda3 = row.createCell(3);
			celda3.setCellValue(Funciones.dateToString(est
					.getPerFechaNacimiento()));
			HSSFCell celda4 = row.createCell(4);
			celda4.setCellValue(est.getPerTelefono());
			HSSFCell celda5 = row.createCell(5);
			celda5.setCellValue(est.getPerGenero());
			HSSFCell celda6 = row.createCell(6);
			celda6.setCellValue(est.getFunGerencia());
			HSSFCell celda7 = row.createCell(7);
			celda7.setCellValue(est.getFunDireccion());
			HSSFCell celda8 = row.createCell(8);
			celda8.setCellValue(est.getFunCargo());
			HSSFCell celda9 = row.createCell(9);
			celda9.setCellValue(Funciones.dateToString(est.getFunFechaIngreso()));
			HSSFCell celda10 = row.createCell(10);
			celda10.setCellValue(est.getFunJefeInmediato());
			HSSFCell celda11 = row.createCell(11);
			celda11.setCellValue(est.getFunTipo());
			HSSFCell celda12 = row.createCell(12);
			celda12.setCellValue(est.getFunTipoEvaluacion());
		}
	}

	/**
	 * Método para descargar un archivo excel
	 */
	public void descargarArchivo() {
		if (getInsCodigoBusqueda() == null
				|| getInsCodigoBusqueda().equals("-1")) {
			Mensaje.crearMensajeWARN("No se puede realizar la exportación del archivo porque la lista está vacía o nula.");
		} else {
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String contextPath = servletContext.getRealPath(File.separator
					+ "resources/doc/descargaDatosExcel_Funcionarios.xls");
			Funciones.descargarExcel(contextPath);
		}
	}

	/**
	 * Método para descargar los archivos de ejemplo
	 */
	public void descargarArchivoEjemplo() {
		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		String contextPath = servletContext.getRealPath(File.separator
				+ "resources/doc/Ejemplo_Base_Funcionario.xls");
		Funciones.descargarExcel(contextPath);
	}
}
