package city.model.manager;

import city.controller.persona.SeguridadBean;


public class Test {

	public static void main(String[] args) {
		
		SeguridadBean s = new SeguridadBean();
		String a="1,2314245234;2,354556345";
		
		System.out.println(s.posicion(a));
		System.out.println(a.substring(s.posicion(a)+1));
		System.out.println(a.substring(0, s.posicion(a)));
		
//		SingletonJDBC s = SingletonJDBC.getInstance();
//		
//		try {
//			ResultSet r=s.consultaSQL("select * from EstadisticasSeguridad");
//			if (r==null){
//				System.out.println("datos vacio");
//			}else{
//				System.out.println("si hay datos");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ManagerInfo i = new ManagerInfo();
//		
//		System.out.println(i.datoEliminar("c001"));

//		InformacionBean i = new InformacionBean();
		
//		i.nombreImg("http://localhost:8080/y-mobile/resources/img/asda_Yachay Tech.jpg");
		
//	System.out.println(Funciones.validarEmail("juank20097@y.com"));
		
//		DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//		
//		
//		try {
//			Date f1 = format.parse("20-04-2015 20:00:00");
//			Date f2 = format.parse("20-04-2015 20:10:00");
//			
//			Timestamp t1 = new Timestamp(f1.getTime());
//			Timestamp t2 = new Timestamp(f2.getTime());
//			
//			if (t1.after(t2)){
//				System.out.println(false);
//			}else{
//				System.out.println(true);
//			}
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		ManagerPersona mp = new ManagerPersona();
//		
//		List<GenItem> l = mp.AllofItems("cat_paises");
//		
//		System.out.println(l.size());
		
		
		
	}

}
