package city.controller.territorio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import city.controller.access.SesionBean;

import city.model.dao.entidades.extras.DatosTerritorio;
import city.model.manager.ManagerReporteTerritorio;


@SessionScoped
@ManagedBean
@ViewScoped
public class ReporteTerritorioBean implements Serializable {

	@EJB
	private ManagerReporteTerritorio manager;
	@Inject
	private SesionBean session;

	private List<DatosTerritorio> territorios;
	private List<DatosTerritorio> filteredTerritorios;


	@PostConstruct
	public void ini() {
		this.territorios = new ArrayList<DatosTerritorio>();
		cargarTerritorios();

	}
	


	public void cargarTerritorios() {
		this.setTerritorios(manager.getAllDatosTerritorios());
	}

	private ManagerReporteTerritorio getManager() {
		return manager;
	}

	private void setManager(ManagerReporteTerritorio manager) {
		this.manager = manager;
	}

	private SesionBean getSession() {
		return session;
	}

	private void setSession(SesionBean session) {
		this.session = session;
	}

	public List<DatosTerritorio> getTerritorios() {
		return territorios;
	}

	public void setTerritorios(ArrayList<DatosTerritorio> territorios) {
		this.territorios = territorios;
	}
	
	
	public List<DatosTerritorio> getFilteredTerritorios() {
        return filteredTerritorios;
    }
 
    public void setFilteredTerritorios(List<DatosTerritorio> filteredTerritorios) {
        this.filteredTerritorios = filteredTerritorios;
    }
 

}
