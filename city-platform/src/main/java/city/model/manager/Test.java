package city.model.manager;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class Test {

	public static void main(String[] args) {
//		ManagerInfo i = new ManagerInfo();
//		
//		System.out.println(i.datoEliminar("c001"));

//		InformacionBean i = new InformacionBean();
		
//		i.nombreImg("http://localhost:8080/y-mobile/resources/img/asda_Yachay Tech.jpg");
		
	
		
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		
		try {
			Date f1 = format.parse("20-04-2015 20:00:00");
			Date f2 = format.parse("20-04-2015 20:10:00");
			
			Timestamp t1 = new Timestamp(f1.getTime());
			Timestamp t2 = new Timestamp(f2.getTime());
			
			if (t1.after(t2)){
				System.out.println(false);
			}else{
				System.out.println(true);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
