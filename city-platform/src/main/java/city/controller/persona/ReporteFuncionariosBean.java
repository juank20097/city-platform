package city.controller.persona;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GenPersona;
import city.model.dao.entidades.extras.DatosFuncionario;
import city.model.dao.entidades.extras.Funcionario;
import city.model.manager.ManagerReporteFuncionarios;

@SessionScoped
@ManagedBean
@ViewScoped
public class ReporteFuncionariosBean implements Serializable {

	@EJB
	private ManagerReporteFuncionarios manager;
	@Inject
	private SesionBean session;

	private List<DatosFuncionario> funcionarios;
	private List<DatosFuncionario> filteredFuncionarios;


	@PostConstruct
	public void ini() {
		this.funcionarios = new ArrayList<DatosFuncionario>();
		cargarFuncionarios();

	}
	


	public void cargarFuncionarios() {
		this.setFuncionarios(manager.getAllDatosFuncionarios());
	}

	private ManagerReporteFuncionarios getManager() {
		return manager;
	}

	private void setManager(ManagerReporteFuncionarios manager) {
		this.manager = manager;
	}

	private SesionBean getSession() {
		return session;
	}

	private void setSession(SesionBean session) {
		this.session = session;
	}

	public List<DatosFuncionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(ArrayList<DatosFuncionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	
	public List<DatosFuncionario> getFilteredFuncionarios() {
        return filteredFuncionarios;
    }
 
    public void setFilteredFuncionarios(List<DatosFuncionario> filteredFuncionarios) {
        this.filteredFuncionarios = filteredFuncionarios;
    }
 

}
