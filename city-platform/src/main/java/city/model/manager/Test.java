package city.model.manager;

import city.model.generic.Funciones;

public class Test {

	public static void main(String[] args) {
		System.out.println("valor: "+Funciones.cifradoCesarInteger("10003422365", 3));
		System.out.println("valor decifrado:"+ Funciones.descifradoCesarInteger("43336755698", 3) );

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
