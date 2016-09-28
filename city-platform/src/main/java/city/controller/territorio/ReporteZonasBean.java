package city.controller.territorio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import java.util.HashMap;
import java.util.List;

import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import city.controller.access.SesionBean;

import city.model.dao.entidades.extras.DatosReporteTree;

import city.model.generic.Funciones;
import city.model.manager.ManagerCarga;
import city.model.manager.ManagerReporteZonas;

@SessionScoped
@ManagedBean
@ViewScoped
public class ReporteZonasBean implements Serializable {

	@EJB
	private ManagerCarga managerC;
	@EJB
	private ManagerReporteZonas manager;
	@Inject
	private SesionBean session;

	private List<DatosReporteTree> zonas;
	private HashMap<String, DatosReporteTree> listaVecindarios;
	private HashMap<String, DatosReporteTree> listaDistritosM;
	private List<DatosReporteTree> vecindarios;

	private TreeNode root;
	private DatosReporteTree selectedDocument;

	private String url_doc;
	

	@PostConstruct
	public void ini() throws CloneNotSupportedException {
		root = new DefaultTreeNode();
		cargarDatos();
		try {
			url_doc = managerC.ParametroByID("direccion_doc");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ReporteZonasBean() {

	}

	public void cargarDatos() throws CloneNotSupportedException {

		zonas = manager.getAllElementosZonas();
		vecindarios = manager.getAllDatosVecindarios();

		HashMap<String, DefaultTreeNode> listaZonas = new HashMap<>();
		int idZona = 0;
		for (DatosReporteTree datosZonas : zonas) {
			listaZonas.put(idZona++ + "", new DefaultTreeNode(datosZonas, root));
		}

		listaVecindarios = new HashMap<>();

		for (DatosReporteTree vecindari : vecindarios) {

			DatosReporteTree vecindario = new DatosReporteTree(vecindari);

			if (listaVecindarios.get(vecindario.getParentId() + vecindario.getElementoUnidadMedida()) == null) {
				DatosReporteTree vecindarioZona = (DatosReporteTree) vecindario.clone();
				listaVecindarios.put(vecindarioZona.getParentId() + vecindarioZona.getElementoUnidadMedida(),
						(vecindarioZona));
				if (!listaVecindarios.get(vecindarioZona.getParentId() + vecindarioZona.getElementoUnidadMedida())
						.getElementoValor().equals("")) {
					listaVecindarios.get(vecindarioZona.getParentId() + vecindarioZona.getElementoUnidadMedida())
							.setElementoValor("");
					listaVecindarios.get(vecindarioZona.getParentId() + vecindarioZona.getElementoUnidadMedida())
							.setVecindarioHectareas("");
					listaVecindarios.get(vecindarioZona.getParentId() + vecindarioZona.getElementoUnidadMedida())
							.setVecindarioKilometros("");
				}
			}

			double lastCantidadZona = 0;
			double newCantidadZona = 0;

			double lastKilometrosZona = 0;
			double newKilometrosZona = 0;

			double lastHectareasZona = 0;
			double newHectareasZona = 0;

			if (!listaVecindarios.get(vecindario.getParentId() + vecindario.getElementoUnidadMedida())
					.getElementoValor().equals("")) {
				lastCantidadZona = Double.parseDouble(listaVecindarios
						.get(vecindario.getParentId() + vecindario.getElementoUnidadMedida()).getElementoValor());
			}

			if (!vecindari.getElementoValor().equals("")) {
				newCantidadZona = Double.parseDouble(vecindari.getElementoValor());
			}

			listaVecindarios.get(vecindario.getParentId() + vecindario.getElementoUnidadMedida())
					.setElementoValor((lastCantidadZona + newCantidadZona) + "");

			if (!listaVecindarios.get(vecindario.getParentId() + vecindario.getElementoUnidadMedida())
					.getVecindarioHectareas().equals("")) {
				lastHectareasZona = Double.parseDouble(listaVecindarios
						.get(vecindario.getParentId() + vecindario.getElementoUnidadMedida()).getVecindarioHectareas());
			}

			if (!vecindari.getVecindarioHectareas().equals("")) {
				newHectareasZona = Double.parseDouble(vecindari.getVecindarioHectareas());
			}

			listaVecindarios.get(vecindario.getParentId() + vecindario.getElementoUnidadMedida())
					.setVecindarioHectareas((lastHectareasZona + newHectareasZona) + "");

			if (!listaVecindarios.get(vecindario.getParentId() + vecindario.getElementoUnidadMedida())
					.getVecindarioKilometros().equals("")) {
				lastKilometrosZona = Double.parseDouble(
						listaVecindarios.get(vecindario.getParentId() + vecindario.getElementoUnidadMedida())
								.getVecindarioKilometros());
			}

			if (!vecindari.getVecindarioKilometros().equals("")) {
				newKilometrosZona = Double.parseDouble(vecindari.getVecindarioKilometros());
			}

			listaVecindarios.get(vecindario.getParentId() + vecindario.getElementoUnidadMedida())
					.setVecindarioKilometros((lastKilometrosZona + newKilometrosZona) + "");
		}

		listaDistritosM = new HashMap<>();

		for (DatosReporteTree vecindari : vecindarios) {

			DatosReporteTree vecindario = new DatosReporteTree(vecindari);

			if (listaDistritosM.get(vecindario.getDistritoId() + vecindario.getElementoUnidadMedida()) == null) {
				DatosReporteTree vecindarioZona = (DatosReporteTree) vecindario.clone();
				vecindarioZona.setNombre(vecindarioZona.getDistritoNombre());
				listaDistritosM.put(vecindarioZona.getDistritoId() + vecindarioZona.getElementoUnidadMedida(),
						(vecindarioZona));
				if (!listaDistritosM.get(vecindarioZona.getDistritoId() + vecindarioZona.getElementoUnidadMedida())
						.getElementoValor().equals("")) {
					listaDistritosM.get(vecindarioZona.getDistritoId() + vecindarioZona.getElementoUnidadMedida())
							.setElementoValor("");
					listaDistritosM.get(vecindarioZona.getDistritoId() + vecindarioZona.getElementoUnidadMedida())
							.setVecindarioHectareas("");
					listaDistritosM.get(vecindarioZona.getDistritoId() + vecindarioZona.getElementoUnidadMedida())
							.setVecindarioKilometros("");
				}
			}

			double lastCantidadDistrito = 0;
			double newCantidadDistrito = 0;

			double lastHectareasDistrito = 0;
			double newHectareasDistrito = 0;

			double lastKilometrosDistrito = 0;
			double newKilometrosDistrito = 0;

			if (!listaDistritosM.get(vecindario.getDistritoId() + vecindario.getElementoUnidadMedida())
					.getElementoValor().equals("")) {
				lastCantidadDistrito = Double.parseDouble(listaDistritosM
						.get(vecindario.getDistritoId() + vecindario.getElementoUnidadMedida()).getElementoValor());
			}

			if (!vecindari.getElementoValor().equals("")) {
				newCantidadDistrito = Double.parseDouble(vecindari.getElementoValor());
			}

			listaDistritosM.get(vecindario.getDistritoId() + vecindario.getElementoUnidadMedida())
					.setElementoValor((lastCantidadDistrito + newCantidadDistrito) + "");

			if (!listaDistritosM.get(vecindario.getDistritoId() + vecindario.getElementoUnidadMedida())
					.getVecindarioHectareas().equals("")) {
				lastHectareasDistrito = Double.parseDouble(
						listaDistritosM.get(vecindario.getDistritoId() + vecindario.getElementoUnidadMedida())
								.getVecindarioHectareas());
			}

			if (!vecindari.getVecindarioHectareas().equals("")) {
				newHectareasDistrito = Double.parseDouble(vecindari.getVecindarioHectareas());
			}

			listaDistritosM.get(vecindario.getDistritoId() + vecindario.getElementoUnidadMedida())
					.setVecindarioHectareas((lastHectareasDistrito + newHectareasDistrito) + "");

			if (!listaDistritosM.get(vecindario.getDistritoId() + vecindario.getElementoUnidadMedida())
					.getVecindarioKilometros().equals("")) {
				lastKilometrosDistrito = Double.parseDouble(
						listaDistritosM.get(vecindario.getDistritoId() + vecindario.getElementoUnidadMedida())
								.getVecindarioKilometros());
			}

			if (!vecindari.getVecindarioKilometros().equals("")) {
				newKilometrosDistrito = Double.parseDouble(vecindari.getVecindarioKilometros());
			}

			listaDistritosM.get(vecindario.getDistritoId() + vecindario.getElementoUnidadMedida())
					.setVecindarioKilometros((lastKilometrosDistrito + newKilometrosDistrito) + "");
		}

		for (Entry<String, DatosReporteTree> zona : listaVecindarios.entrySet()) {
			DatosReporteTree value = zona.getValue();
			listaZonas.put(value.getParentId() + value.getElementoUnidadMedida(), new DefaultTreeNode(value, root));
		}

		for (Entry<String, DatosReporteTree> zona : listaDistritosM.entrySet()) {
			DatosReporteTree value = zona.getValue();
			listaZonas.put(value.getDistritoId() + value.getElementoUnidadMedida(),
					new DefaultTreeNode(value, listaZonas.get(value.getParentId() + value.getElementoUnidadMedida())));
		}

		for (DatosReporteTree vecindario : vecindarios) {
			vecindario.setNombre(vecindario.getVecindarioNombre());
			listaZonas.put(vecindario.getVecindarioId() + vecindario.getElementoUnidadMedida(), new DefaultTreeNode(
					vecindario, listaZonas.get(vecindario.getDistritoId() + vecindario.getElementoUnidadMedida())));
		}

	}

	public ManagerReporteZonas getManager() {
		return manager;
	}

	public void setManager(ManagerReporteZonas manager) {
		this.manager = manager;
	}

	public SesionBean getSession() {
		return session;
	}

	public void setSession(SesionBean session) {
		this.session = session;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public DatosReporteTree getSelectedDocument() {
		return selectedDocument;
	}

	public void setSelectedDocument(DatosReporteTree selectedDocument) {
		this.selectedDocument = selectedDocument;
	}

	public List<DatosReporteTree> getZonas() {
		return zonas;
	}

	public void setZonas(List<DatosReporteTree> zonas) {
		this.zonas = zonas;
	}

	public ManagerCarga getManagerC() {
		return managerC;
	}

	public String getUrl_doc() {
		return url_doc;
	}

	public void generateExcel() {
		crearExcel(this.getZonas(), listaVecindarios, listaDistritosM, vecindarios);
	}

	public void crearExcel(List<DatosReporteTree> datosReporteTree, HashMap<String, DatosReporteTree> zonas,
			HashMap<String, DatosReporteTree> distritosM, List<DatosReporteTree> vecindarios) {
		String url = url_doc + "/descarga/";
		try {

			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("Datos");
			HSSFCellStyle styleZona = libro.createCellStyle();

			
			int i = 0;
			for (; i <= datosReporteTree.size() - 1; i++) {
				HSSFRow row = hoja.createRow(i);
				styleZona.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
				styleZona.setFillPattern(CellStyle.SOLID_FOREGROUND);
				
				llenarFila(datosReporteTree.get(i), row, styleZona);
			}

			for (Entry<String, DatosReporteTree> zona : zonas.entrySet()) {
				HSSFRow row = hoja.createRow(i++);
				DatosReporteTree value = zona.getValue();
				HSSFCellStyle styleDisitrito = libro.createCellStyle();
				styleDisitrito.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
				styleDisitrito.setFillPattern(CellStyle.SOLID_FOREGROUND);
				
				llenarFila(zona.getValue(), row, styleDisitrito);
				
				String zonaId = value.getParentId() + value.getElementoUnidadMedida();
				for (Entry<String, DatosReporteTree> distrito : listaDistritosM.entrySet()) {
					DatosReporteTree valueDistrito = distrito.getValue();

					String distritoParentId = valueDistrito.getParentId() + valueDistrito.getElementoUnidadMedida();

					if (distritoParentId.equals(zonaId)) {
						HSSFRow rowDistrito = hoja.createRow(i++);
						HSSFCellStyle style = libro.createCellStyle();
						style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
					    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
					    
						llenarFila(distrito.getValue(), rowDistrito, style);
					}
					String distritoId = zonaId + valueDistrito.getDistritoId()
							+ valueDistrito.getElementoUnidadMedida();
					for (DatosReporteTree vecindario : vecindarios) {

						String vecindarioParentId = vecindario.getParentId() + vecindario.getElementoUnidadMedida()
								+ vecindario.getDistritoId() + vecindario.getElementoUnidadMedida();
	
						if (vecindarioParentId.equals(distritoId)) {
							HSSFCellStyle styleVecindario = libro.createCellStyle();
							styleVecindario.setFillForegroundColor(IndexedColors.WHITE.getIndex());
							styleVecindario.setFillPattern(CellStyle.SOLID_FOREGROUND);

							HSSFRow rowVecindario = hoja.createRow(i++);
							llenarFila(vecindario, rowVecindario, styleVecindario);
						}

					}

				}

			}

			OutputStream out = new FileOutputStream(url + "DatosExcel_Territorios.xls");
			libro.write(out);
			libro.close();
			Funciones.descargarExcel(url+"DatosExcel_Territorios.xls");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void llenarFila(DatosReporteTree datosReporteTree, HSSFRow row, HSSFCellStyle style) {
		if (row.getRowNum() == 0) {
			HSSFCell celda0 = row.createCell(0);
			celda0.setCellValue("NOMBRE");
			HSSFCell celda1 = row.createCell(1);
			celda1.setCellValue("KILÓMETROS");
			HSSFCell celda2 = row.createCell(2);
			celda2.setCellValue("HECTÁREAS");
			HSSFCell celda3 = row.createCell(3);
			celda3.setCellValue("ELEMENTO");
			HSSFCell celda4 = row.createCell(4);
			celda4.setCellValue("TIPO ELEMENTO");
			HSSFCell celda5 = row.createCell(5);
			celda5.setCellValue("VALOR");
		} else {
			HSSFCell celda0 = row.createCell(0);
			celda0.setCellValue(datosReporteTree.getNombre());
			celda0.setCellStyle(style);
			HSSFCell celda1 = row.createCell(1);
			celda1.setCellValue(datosReporteTree.getVecindarioKilometros());
			celda1.setCellStyle(style);
			HSSFCell celda2 = row.createCell(2);
			celda2.setCellValue(datosReporteTree.getVecindarioHectareas());
			celda2.setCellStyle(style);
			HSSFCell celda3 = row.createCell(3);
			celda3.setCellValue(datosReporteTree.getElementoNombre());
			celda3.setCellStyle(style);
			HSSFCell celda4 = row.createCell(4);
			celda4.setCellValue(datosReporteTree.getElementoTipo());
			celda4.setCellStyle(style);
			HSSFCell celda5 = row.createCell(5);
			celda5.setCellValue(datosReporteTree.getElementoValor());
			celda5.setCellStyle(style);
		}
	}

}
