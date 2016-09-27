package city.controller.territorio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import city.controller.access.SesionBean;
import city.model.dao.entidades.extras.DatosBarrios;
import city.model.dao.entidades.extras.DatosDistrito;
import city.model.dao.entidades.extras.DatosReporteTree;
import city.model.dao.entidades.extras.DatosZona;
import city.model.manager.ManagerReporteZonas;

@SessionScoped
@ManagedBean
@ViewScoped
public class ReporteZonasBean implements Serializable {

	@EJB
	private ManagerReporteZonas manager;
	@Inject
	private SesionBean session;

	private TreeNode root;

	// private List<DatosZona> filteredZonas;

	@PostConstruct
	public void ini() throws CloneNotSupportedException {
		root = new DefaultTreeNode();
		cargarDatos();

	}

	public ReporteZonasBean() {

	}

	public List<DatosZona> cargarDatos() throws CloneNotSupportedException {

		List<DatosReporteTree> zonas;
		List<DatosReporteTree> distritos;
		List<DatosReporteTree> vecindarios;

		zonas = manager.getAllElementosZonas();
		// distritos = manager.getAllDatosDistritos();
		vecindarios = manager.getAllDatosVecindarios();

		HashMap<String, DefaultTreeNode> listaZonas = new HashMap<>();
		int idZona = 0;
		for (DatosReporteTree datosZonas : zonas) {
			listaZonas.put(idZona++ + "", new DefaultTreeNode(datosZonas, root));

		}

		HashMap<String, DefaultTreeNode> listaDistritos = new HashMap<>();
		// for (DatosReporteTree datosDistritos : distritos) {
		// listaDistritos.put(datosDistritos.getId(),
		// new DefaultTreeNode(datosDistritos,
		// listaZonas.get(datosDistritos.getParentId())));
		// }

		HashMap<String, DatosReporteTree> listaVecindarios = new HashMap<>();

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

				}
			}

			double lastCantidad = 0;
			double newCantidad = 0;

			if (!listaVecindarios.get(vecindario.getParentId() + vecindario.getElementoUnidadMedida())
					.getElementoValor().equals("")) {
				lastCantidad = Double.parseDouble(listaVecindarios
						.get(vecindario.getParentId() + vecindario.getElementoUnidadMedida()).getElementoValor());

			}

			if (!vecindari.getElementoValor().equals("")) {
				newCantidad = Double.parseDouble(vecindari.getElementoValor());
			}

			listaVecindarios.get(vecindario.getParentId() + vecindario.getElementoUnidadMedida())
					.setElementoValor((lastCantidad + newCantidad) + "");

		}

		HashMap<String, DatosReporteTree> listaDistritosM = new HashMap<>();

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

				}
			}

			double lastCantidad = 0;
			double newCantidad = 0;

			if (!listaDistritosM.get(vecindario.getDistritoId() + vecindario.getElementoUnidadMedida())
					.getElementoValor().equals("")) {
				lastCantidad = Double.parseDouble(listaDistritosM
						.get(vecindario.getDistritoId() + vecindario.getElementoUnidadMedida()).getElementoValor());

			}

			if (!vecindari.getElementoValor().equals("")) {
				newCantidad = Double.parseDouble(vecindari.getElementoValor());
			}

			listaDistritosM.get(vecindario.getDistritoId() + vecindario.getElementoUnidadMedida())
					.setElementoValor((lastCantidad + newCantidad) + "");

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

		// for (Entry<String, DatosReporteTree> distrito :
		// listaDistritoTemporal.entrySet()) {
		// DatosReporteTree value = distrito.getValue();
		// System.out.println("Distrito " + value.toString());
		// listaDistritos.put(value.getDistritoId() +
		// value.getElementoUnidadMedida(),
		// new DefaultTreeNode(value, listaZonas.get(value.getParentId() +
		// value.getElementoUnidadMedida())));
		// }
		//
		// for (Entry<String, DatosReporteTree> vecindario :
		// listaVecindarioTemporal.entrySet()) {
		// DatosReporteTree value = vecindario.getValue();
		// System.out.println("Vecindario " + value.toString());
		// listaVecindarios.put(value.getVecindarioId(), new
		// DefaultTreeNode(value,
		// listaDistritos.get(value.getDistritoId() +
		// value.getElementoUnidadMedida())));
		// }
		// listaZonas.get("ZONA1").ge;

		// TreeNode zonas = new DefaultTreeNode(new D(), root);

		return null;
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

	// public List<DatosZona> getFilteredZonas() {
	// return filteredZonas;
	// }
	//
	// public void setFilteredZonas(List<DatosZona> filteredZonas) {
	// this.filteredZonas = filteredZonas;
	// }

}
