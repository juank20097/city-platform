package city.controller.persona;

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
import city.model.dao.entidades.extras.DatosMedicosFuncionario;
import city.model.manager.ManagerReporteMedico;

@SessionScoped
@ManagedBean
@ViewScoped
public class ReporteMedicoBean implements Serializable {

	@EJB
	private ManagerReporteMedico manager;
	@Inject
	private SesionBean session;

	private List<DatosMedicosFuncionario> funcionarios;
	private List<DatosMedicosFuncionario> filteredFuncionarios;


	@PostConstruct
	public void ini() {
		this.funcionarios = new ArrayList<DatosMedicosFuncionario>();
		cargarFuncionarios();

	}
	


	public void cargarFuncionarios() {
		this.setFuncionarios(manager.getAllDatosFuncionarios());
	}

	private ManagerReporteMedico getManager() {
		return manager;
	}

	private void setManager(ManagerReporteMedico manager) {
		this.manager = manager;
	}

	private SesionBean getSession() {
		return session;
	}

	private void setSession(SesionBean session) {
		this.session = session;
	}

	public List<DatosMedicosFuncionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(ArrayList<DatosMedicosFuncionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	
	public List<DatosMedicosFuncionario> getFilteredFuncionarios() {
        return filteredFuncionarios;
    }
 
    public void setFilteredFuncionarios(List<DatosMedicosFuncionario> filteredFuncionarios) {
        this.filteredFuncionarios = filteredFuncionarios;
    }
 

}
