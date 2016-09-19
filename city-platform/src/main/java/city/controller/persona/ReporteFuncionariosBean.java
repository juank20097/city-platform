package city.controller.persona;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import city.controller.access.SesionBean;
import city.model.dao.entidades.GenPersona;
import city.model.dao.entidades.extras.Funcionario;
import city.model.manager.ManagerReporteFuncionarios;

@SessionScoped
@ManagedBean
public class ReporteFuncionariosBean {

	@EJB
	private ManagerReporteFuncionarios manager;
	@Inject
	private SesionBean session;

	private List<Object[]> list;

	private List<Funcionario> funcionarios;

	@PostConstruct
	public void ini() {
		this.funcionarios = new ArrayList<Funcionario>();

	}

	public void cargarFuncionarios() {
		this.setList(manager.test());
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

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	private void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	private List<Object[]> getList() {
		return list;
	}

	private void setList(List<Object[]> list) {
		this.list = list;
	}

}
