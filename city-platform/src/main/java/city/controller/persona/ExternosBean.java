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
import city.model.dao.entidades.GenExterno;
import city.model.dao.entidades.GenPersona;
import city.model.dao.entidades.extras.Externo;
import city.model.generic.Funciones;
import city.model.generic.Mensaje;
import city.model.manager.ManagerCarga;

/**
 * @author jestevez
 * 
 */
@SessionScoped
@ManagedBean
public class ExternosBean {

	// Atributos de la Clase
	@EJB
	private ManagerCarga manager;
	@Inject
	private SesionBean session;

	private int NUMERO_COLUMNAS_EXCEL = 11;

	// listas de registros
	private List<Externo> l_externos;
	private List<Externo> l_externos_total;
	private List<String> errores;

	// atributos de Registro excel
	private String exc_nombre;
	private String exc_usuario;
	
	//atributo de direccion de url
		private String url_doc;

	public ExternosBean() {
	}

	@PostConstruct
	public void ini() {
		exc_usuario = session.validarSesion();
		l_externos = new ArrayList<Externo>();
		l_externos_total = new ArrayList<Externo>();
		errores = new ArrayList<String>();
		try {
			url_doc = manager.ParametroByID("direccion_doc");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the l_externos
	 */
	public List<Externo> getL_externos() {
		return l_externos;
	}

	/**
	 * @param l_externos
	 *            the l_externos to set
	 */
	public void setL_externos(List<Externo> l_externos) {
		this.l_externos = l_externos;
	}

	/**
	 * @return the l_externos_total
	 */
	public List<Externo> getL_externos_total() {
		return l_externos_total;
	}

	/**
	 * @param l_externos_total
	 *            the l_externos_total to set
	 */
	public void setL_externos_total(List<Externo> l_externos_total) {
		this.l_externos_total = l_externos_total;
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

	private void ListExternos() {
		try {
			l_externos_total = new ArrayList<Externo>();
			for (GenExterno ext : manager.findAllExterno()){
				GenPersona per = manager.PersonaByID(ext.getPerDni());
				Externo externo = new Externo();
				externo.setExtReferencia(ext.getExtReferencia());
				externo.setExtTipo(ext.getExtTipo());
				externo.setPerApellidos(per.getPerApellidos());
				externo.setPerCelular(per.getPerCelular());
				externo.setPerCorreo(per.getPerCorreo());
				externo.setPerDni(per.getPerDni());
				externo.setPerEstado(per.getPerEstado());
				externo.setPerEstadoCivil(per.getPerEstadoCivil());
				externo.setPerFechaNacimiento(per.getPerFechaNacimiento());
				externo.setPerGenero(per.getPerGenero());
				externo.setPerNombres(per.getPerNombres());
				externo.setPerTelefono(per.getPerTelefono());
				externo.setPerTipoDni(per.getPerTipoDni());
				l_externos_total.add(externo);
			}
			this.crearExcel(l_externos_total);
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
				if (event.getFile() == null)
					throw new Exception("No se ha seleccionado archivo");
				else {
					exc_nombre = event.getFile().getFileName();
					validarGuardarDatosExcel(event.getFile());
					// this.ListEstudiantes();
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
		l_externos = new ArrayList<Externo>();
		errores = new ArrayList<String>();
		List<String> datosFila = new ArrayList<String>();
		// Toma la primera hoja
		Sheet hoja = Workbook.getWorkbook(archivo.getInputstream()).getSheet(0);
		// Validar estructura de excel
		if (!poseeEstructuraValidaExternos(hoja))
			throw new Exception("El archivo no posee la estructura correcta.");
		// Recorre todas las filas y columnas
		for (int i = 1; i < hoja.getRows(); i++) {
			if (filaValida(hoja.getRow(i), i + 1)) {
				datosFila.clear();
				// Método para saber los datos de todo el excel
				for (int j = 0; j < NUMERO_COLUMNAS_EXCEL; j++) {
					datosFila.add(hoja.getCell(j, i).getContents().trim());
					System.out.println("fila:" + i + " ,columna:" + j
							+ " dato:" + hoja.getCell(j, i).getContents());
				}
				l_externos
						.add(manager.crearExterno(datosFila));
			}
		}
		// ingreso de estudiantes
		manager.ingresarExterno(l_externos);
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
		for (String e : errores) {
			System.out.println(e);
		}
		RequestContext.getCurrentInstance().execute("PF('dlgerr').show()");
	}

	/**
	 * Valida la extructura de Excel
	 * 
	 * @param hoja
	 * @return boolean
	 */
	private boolean poseeEstructuraValidaExternos(Sheet hoja) {
		return manager.validarEncabezadosExcelExterno(hoja.getRow(0));
	}

	/**
	 * Valida los datos de una fila
	 * 
	 * @param column
	 * @param nroFila
	 * @return boolean
	 */
	private boolean filaValida(Cell[] column, int nroFila) {
		String error = manager.validarFilaExcelExterno(column);
		if (error.isEmpty())
			return true;
		else {
			errores.add("Fila NRO " + nroFila + " : " + error);
			return false;
		}
	}

	// ///////////////////////////////////////CIERRE_MÉTODOS//////////////////////////////////////////////

	/**
	 * Metodo para cargar los sitios
	 */
	public void cargarExternos() {
		this.ListExternos();
	}

	// ////////////////////////////////////////(Método_creación_excel_imprimir)///////////////////////////////////////////////////////

	/**
	 * Método para crear un excel y guardarlo en una dirección específica
	 * 
	 * @param est
	 */
	public void crearExcel(List<Externo> ext) {
		String url=url_doc+"/descarga/";
		try {
//			ServletContext servletContext = (ServletContext) FacesContext
//					.getCurrentInstance().getExternalContext().getContext();
//			String contextPath = servletContext.getRealPath(File.separator
//					+ "resources/doc/descarga/");
			System.out.println(url);

			HSSFWorkbook libro = new HSSFWorkbook();

			HSSFSheet hoja = libro.createSheet("Datos");
			System.out.println(ext.size());
			for (int i = 0; i <= ext.size() - 1; i++) {
				HSSFRow row = hoja.createRow(i);
				llenarFila(ext.get(i), row);
			}
			OutputStream out = new FileOutputStream(url
					+ "DatosExcel_Externos.xls");
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
	public void llenarFila(Externo ext, HSSFRow row) {
		if (row.getRowNum() == 0) {
			HSSFCell celda0 = row.createCell(0);
			celda0.setCellValue("CÉDULA");
			HSSFCell celda1 = row.createCell(1);
			celda1.setCellValue("NOMBRE COMPLETO");
			HSSFCell celda2 = row.createCell(2);
			celda2.setCellValue("CORREO PERSONAL");
			HSSFCell celda3 = row.createCell(3);
			celda3.setCellValue("CELULAR");
			HSSFCell celda4 = row.createCell(4);
			celda4.setCellValue("TELÉFONO");
			HSSFCell celda5 = row.createCell(5);
			celda5.setCellValue("FECHA DE NACIMIENTO");
			HSSFCell celda6 = row.createCell(6);
			celda6.setCellValue("ESTADO CIVIL");
			HSSFCell celda7 = row.createCell(7);
			celda7.setCellValue("GÉNERO");
			HSSFCell celda8 = row.createCell(8);
			celda8.setCellValue("REFERENCIA");
			HSSFCell celda9 = row.createCell(9);
			celda9.setCellValue("TIPO");
			HSSFCell celda10 = row.createCell(10);
			celda10.setCellValue("ESTADO");
		} else {
			HSSFCell celda0 = row.createCell(0);
			celda0.setCellValue(ext.getPerDni());
			HSSFCell celda1 = row.createCell(1);
			celda1.setCellValue(ext.getPerNombres() + " "
					+ ext.getPerApellidos());
			HSSFCell celda2 = row.createCell(2);
			celda2.setCellValue(ext.getPerCorreo());
			HSSFCell celda3 = row.createCell(3);
			celda3.setCellValue(ext.getPerCelular());
			HSSFCell celda4 = row.createCell(4);
			celda4.setCellValue(ext.getPerTelefono());
			HSSFCell celda5 = row.createCell(5);
			celda5.setCellValue(Funciones.dateToString(ext
					.getPerFechaNacimiento()));
			HSSFCell celda6 = row.createCell(6);
			celda6.setCellValue(ext.getPerEstadoCivil());
			HSSFCell celda7 = row.createCell(7);
			celda7.setCellValue(ext.getPerGenero());
			HSSFCell celda8 = row.createCell(8);
			celda8.setCellValue(ext.getExtReferencia());
			HSSFCell celda9 = row.createCell(9);
			celda9.setCellValue(ext.getExtTipo());
			HSSFCell celda10 = row.createCell(10);
			celda10.setCellValue(ext.getPerEstado());
		}
	}

	/**
	 * Método para descargar un archivo excel
	 */
	public void descargarArchivo() {
			if (l_externos_total==null || l_externos_total.size()==0){
				Mensaje.crearMensajeWARN("La tabla está vacía como para generar un reporte.");
			}else{
//			ServletContext servletContext = (ServletContext) FacesContext
//					.getCurrentInstance().getExternalContext().getContext();
//			String contextPath = servletContext.getRealPath(File.separator
//					+ "resources/doc/descargaDatosExcel_Externos.xls");
			try {
				Funciones.descargarExcel(url_doc+"/descarga/DatosExcel_Externos.xls");
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
	public void descargarArchivoEjemplo() {
//		ServletContext servletContext = (ServletContext) FacesContext
//				.getCurrentInstance().getExternalContext().getContext();
//		String contextPath = servletContext.getRealPath(File.separator
//				+ "resources/doc/Ejemplo_Base_Externos.xls");
		try {
			Funciones.descargarExcel(url_doc+"/Ejemplo_Base_Externos.xls");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Mensaje.crearMensajeERROR("El archivo no fue encontrado para su descarga.");
			e.printStackTrace();
		}
	}
}
