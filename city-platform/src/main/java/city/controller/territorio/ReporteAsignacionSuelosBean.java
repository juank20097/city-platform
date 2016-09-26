package city.controller.territorio;

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
import city.model.dao.entidades.extras.DatosAsignacionSuelo;
import city.model.dao.entidades.extras.DatosFuncionario;
import city.model.dao.entidades.extras.Funcionario;
import city.model.manager.ManagerReporteAsignacionSuelos;
import city.model.manager.ManagerReporteFuncionarios;

@SessionScoped
@ManagedBean
@ViewScoped
public class ReporteAsignacionSuelosBean implements Serializable {

	@EJB
	private ManagerReporteAsignacionSuelos manager;
	@Inject
	private SesionBean session;

	private List<DatosAsignacionSuelo> datosAsignacionSuelo;
	private List<DatosAsignacionSuelo> filteredDatosAsignacionSuelo;


	@PostConstruct
	public void ini() {
		this.datosAsignacionSuelo = new ArrayList<DatosAsignacionSuelo>();
		cargarFuncionarios();

	}
	


	public void cargarFuncionarios() {
		this.setDatosAsignacionSuelo(manager.getAllDatosAsignacionSuelo());
	}



	public ManagerReporteAsignacionSuelos getManager() {
		return manager;
	}



	public void setManager(ManagerReporteAsignacionSuelos manager) {
		this.manager = manager;
	}



	public SesionBean getSession() {
		return session;
	}



	public void setSession(SesionBean session) {
		this.session = session;
	}



	public List<DatosAsignacionSuelo> getDatosAsignacionSuelo() {
		return datosAsignacionSuelo;
	}



	public void setDatosAsignacionSuelo(List<DatosAsignacionSuelo> datosAsignacionSuelo) {
		this.datosAsignacionSuelo = datosAsignacionSuelo;
	}



	public List<DatosAsignacionSuelo> getFilteredDatosAsignacionSuelo() {
		return filteredDatosAsignacionSuelo;
	}



	public void setFilteredDatosAsignacionSuelo(List<DatosAsignacionSuelo> filteredDatosAsignacionSuelo) {
		this.filteredDatosAsignacionSuelo = filteredDatosAsignacionSuelo;
	}


}
