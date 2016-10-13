package city.model.manager;

import city.model.generic.Funciones;

public class Test {
	
	

	public static void main(String[] args) {
		
		
		Funciones f = new Funciones();
		
        String datosCifrados = f.encriptarAES256("aspirina",f.addKey("YachayEP2016-Salud!/"));
    System.out.println("Datos Cifrados -> " + datosCifrados);
            System.out.println("Datos Descifrados -> " + f.desencriptarAES256("KSatKgDJZsgJOlvt3tCLKFQRLrsp7KO6Xdzb1zqKDRJY7Szav7ziI3h8OM3t8KBc",f.addKey("YachayEP2016-Salud!/")));
            //System.out.println("Datos Descifrados -> " + f.desencriptarAES256("6uggZCXgSOHLWKzqkeFUBg==",f.addKey("YachayEP2016-Salud!/")));

           
	//		
//		System.out.println();
//		f.UTM2Deg("17 N 813628.4500000001 45096.81");''
		
//		System.out.println("valor: " + Funciones.cifradoPropio("AZC1003422365", 3));
//		System.out.println("valor decifrado:" + Funciones.descifradoPropio("D?F4336755698", 3));

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
		
//			String nombreArchivo ="Maria.sql.txt";
//			String [] extension = nombreArchivo.split(".");
//			
//			Integer j=nombreArchivo.lastIndexOf('.');
//			String a = nombreArchivo.substring(j);
//			System.out.println(a);
//			System.out.println(extension[0]);
//			StringBuilder s = new StringBuilder();
//			for (int i = extension.length; i < 0; i--) {
//				if(!extension[i].equals(".")){
//					s.append(extension[i]);
//				}else {
//					break;
//				}
//			}
//		
	}

}
