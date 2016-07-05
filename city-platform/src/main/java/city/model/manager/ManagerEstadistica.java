package city.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import city.model.dao.entidades.GenPersona;

@Stateless
public class ManagerEstadistica {
	
	@EJB
	private ManagerDAO mngDao;
	
	public ManagerEstadistica() {}
	
	/**
	 * Busca a las personas por código de institución
	 * @param insCodigo
	 * @return List<GenPersona>
	 */
	@SuppressWarnings("unchecked")
	public List<GenPersona> personasPorInstitucion(String insCodigo){
		return mngDao.findWhere(GenPersona.class, 
				"o.perDni IN (SELECT ei.id.perDni FROM GenEstudianteInstitucion ei WHERE ei.genInstitucione.insCodigo = '"+insCodigo+"') "
				+ "OR o.perDni IN (SELECT fi.id.perDni FROM GenFuncionariosInstitucion fi WHERE fi.genInstitucione.insCodigo = '"+insCodigo+"')", 
				"o.perDni");
	}
	
	/**
	 * Busca a los estudiantes por código de institución
	 * @param insCodigo
	 * @return List<GenPersona>
	 */
	@SuppressWarnings("unchecked")
	public List<GenPersona> estudiantesPorInstitucion(String insCodigo){
		return mngDao.findWhere(GenPersona.class, 
				"o.perDni IN (SELECT ei.id.perDni FROM GenEstudianteInstitucion ei WHERE ei.genInstitucione.insCodigo = '"+insCodigo+"') ",
				"o.perDni");
	}
	
	/**
	 * Busca a los funcionarios por código de institución
	 * @param insCodigo
	 * @return List<GenPersona>
	 */
	@SuppressWarnings("unchecked")
	public List<GenPersona> funcionariosPorInstitucion(String insCodigo){
		return mngDao.findWhere(GenPersona.class, 
				"o.perDni IN (SELECT fi.id.perDni FROM GenFuncionariosInstitucion fi WHERE fi.genInstitucione.insCodigo = '"+insCodigo+"')", 
				"o.perDni");
	}


	/**
	 * Calcula el valor de personal por institucion y devuelve el mayor de ellos sumado 15%
	 * @param valores
	 * @return int
	 */
	public int mayorPersonas(int[] valores) {
		int valorA = valores[0];
		int valorB = valores[1];
		int valorC = valores[2];
		int valorD = valores[3];
		if(valorA > valorB && valorA > valorC){
			return valorA+(valorA*15/100);
		}else{
			if(valorB > valorA && valorB > valorC){
				return mayorEntreDos(valorB, valorD)+(mayorEntreDos(valorB, valorD)*15/100);
			}else{
				return mayorEntreDos(valorC, valorD)+(mayorEntreDos(valorC, valorD)*15/100);
			}
		}
	}
	
	/**
	 * Calcula el valor mayor entre dos enteros
	 * @param valorA
	 * @param valorB
	 * @return int
	 */
	public int mayorEntreDos(int valorA, int valorB){
		if(valorA>valorB)
			return valorA;
		else
			return valorB;
	}
	
	/**
	 * Calcula el valor mayor entre dos enteros y suma un 15% al valor mayor
	 * @param valorA
	 * @param valorB
	 * @return int
	 */
	public int mayorEntreDosPorcentaje(int valorA, int valorB){
		if(valorA>valorB)
			return valorA+(valorA*15/100);
		else
			return valorB+(valorB*15/100);
	}
}
