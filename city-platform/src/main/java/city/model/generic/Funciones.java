package city.model.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jestevez
 *
 */
/**
 * @author jestevez
 *
 */
/**
 * @author jestevez
 * 
 */
public class Funciones {

	private static final int num_provincias = 24;

	public static Integer mayoriaDeEdad = 18;

	public static String hostWS = "http://app-permisos.yachay.gob.ec/";

	public static Boolean validacionCedula(String cedula) {
		// verifica que los dos primeros dï¿½gitos correspondan a un valor entre
		// 1
		// y NUMERO_DE_PROVINCIAS
		int prov = Integer.parseInt(cedula.substring(0, 2));
		if (!((prov > 0) && (prov <= num_provincias) || prov == 30)) {
			// addError("La cï¿½dula ingresada no es vï¿½lida");
			System.out.println("Error: cedula ingresada mal");
			return false;
		}
		// verifica que el ï¿½ltimo dï¿½gito de la cï¿½dula sea vï¿½lido
		int[] d = new int[10];
		// Asignamos el string a un array
		for (int i = 0; i < d.length; i++) {
			d[i] = Integer.parseInt(cedula.charAt(i) + "");
		}
		int imp = 0;
		int par = 0;
		// sumamos los duplos de posiciï¿½n impar
		for (int i = 0; i < d.length; i += 2) {
			d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
			imp += d[i];
		}
		// sumamos los digitos de posiciï¿½n par
		for (int i = 1; i < (d.length - 1); i += 2) {
			par += d[i];
		}
		// Sumamos los dos resultados
		int suma = imp + par;
		// Restamos de la decena superior
		int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1) + "0") - suma;
		// Si es diez el dï¿½cimo dï¿½gito es cero
		d10 = (d10 == 10) ? 0 : d10;
		// si el dï¿½cimo dï¿½gito calculado es igual al digitado la cï¿½dula es
		// correcta
		if (d10 == d[9]) {
			return true;
		} else {
			// addError("La cï¿½dula ingresada no es vï¿½lida");
			return false;
		}
	}

	/**
	 * Convierte un cadena en codigo MD5
	 * 
	 * @param input
	 *            entrada de cadena para convertirla en MD5
	 * @return String MD5
	 */
	public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean validarEmail(String email) {

		// Definicion de pattern con la expresion regular
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		// COmprobacion del correo ingresado
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();

	}

	/**
	 * Convierte un cadena en codigo SHA2
	 * 
	 * @param input
	 *            entrada de cadena para convertirla en SHA2
	 * @return String MD5
	 */
	public String getSHA2(String input) {
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			String salt = "some_random_salt";
			String passWithSalt = input + salt;
			byte[] passBytes = passWithSalt.getBytes();
			byte[] passHash = sha256.digest(passBytes);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < passHash.length; i++) {
				sb.append(Integer.toString((passHash[i] & 0xff) + 0x100, 16).substring(1));
			}
			String generatedPassword = sb.toString();
			return generatedPassword;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Transforma una fecha a String
	 * 
	 * @param fecha
	 * @return String
	 */
	public static String dateToString(Date fecha) {
		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		if (fecha == null)
			return "";
		else
			return formato.format(fecha).toString();
	}

	/**
	 * Transforma un string de fecha en Date
	 * 
	 * @param fecha
	 * @return Date
	 * @throws ParseException
	 */
	public static Date stringToDate(String fecha) throws ParseException {
		DateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		if (fecha.isEmpty())
			return null;
		else
			return formato.parse(fecha);
	}

	/**
	 * Transforma una fecha a letters
	 * 
	 * @param fecha
	 * @return String
	 */
	public static String dateToLetters(Date fecha) {
		DateFormat formato = new SimpleDateFormat("dd-MMMM-yyyy");
		if (fecha == null)
			return "";
		else
			return formato.format(fecha).toString().replace("-", " de ");
	}

	/**
	 * Transforma un string de fecha en Date
	 * 
	 * @param fecha
	 * @return Date
	 * @throws ParseException
	 */
	public static Date stringToDateF(String fecha) throws ParseException {
		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		if (fecha.isEmpty())
			return null;
		else
			return formato.parse(fecha);
	}

	/**
	 * Evalua si una cadena es numerica
	 * 
	 * @param cadena
	 * @return
	 */
	public static boolean isNumeric(String cadena) {
		try {
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/**
	 * Evalua un dato que viene desde un JSON
	 * 
	 * @param dato
	 * @return String
	 */
	public static String evaluarDatoWS(Object dato) {
		if (dato != null)
			return dato.toString();
		else
			return "";
	}

	/**
	 * Evalua un String para convertirlo a Entero
	 * 
	 * @param dato
	 * @return Integer
	 */
	public static Integer evaluarString(String dato) {
		if (dato.isEmpty())
			return 0;
		else
			return Integer.parseInt(dato);
	}

	/**
	 * Genera un pass unico
	 * 
	 * @return pass
	 */
	public static String genPass() {
		String pass = "";
		for (int i = 0; i < 4; i++) {
			pass += (char) (Math.random() * 25 + 97) + "" + (int) (Math.random() * 9 + 1);
		}
		return pass;
	}

	/**
	 * Convierte una cadena en otra con codificaciï¿½n utf-8
	 * 
	 * @param cadena
	 * @return String
	 */
	public static String utf8Sting(String cadena) {
		try {
			return new String(cadena.getBytes("UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "Error utf8Sting";
		}
	}

	/**
	 * Transforma un timestamp a String
	 * 
	 * @param estFechaIni
	 * @return String
	 */
	public static String timestampToString(Timestamp estFechaIni) {
		return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(estFechaIni);
	}

	/**
	 * Calcula la edad de una persona (Date fechaNac = new
	 * SimpleDateFormat("dd-MM-yyyy").parse("10-12-1980");)
	 * 
	 * @param fecha
	 * @return Integer
	 */
	public static Integer calcularEdad(Date fecha) {
		Calendar fechaNacimiento = Calendar.getInstance();
		// Se crea un objeto con la fecha actual
		Calendar fechaActual = Calendar.getInstance();
		// Se asigna la fecha recibida a la fecha de nacimiento.
		fechaNacimiento.setTime(fecha);
		// Se restan la fecha actual y la fecha de nacimiento
		int anio = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
		int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
		int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
		// Se ajusta el aï¿½o dependiendo el mes y el dï¿½a
		if (mes < 0 || (mes == 0 && dia < 0)) {
			anio--;
		}
		// Regresa la edad en base a la fecha de nacimiento
		return anio;
	}

	/**
	 * Determina si es mayor de edad
	 * 
	 * @param fecha
	 * @return boolean
	 */
	public static boolean mayorDeEdad(Date fecha) {
		if (calcularEdad(fecha) >= mayoriaDeEdad)
			return true;
		else
			return false;
	}

	/**
	 * Metodo para obtener la Ip del equipo
	 * 
	 * @return
	 */
	public static String getIp() {
		// IP en formato String
		String sIPAddress = "";
		try {
			InetAddress address = InetAddress.getLocalHost();
			byte[] bIPAddress = address.getAddress();

			for (int x = 0; x < bIPAddress.length; x++) {
				if (x > 0) {
					// A todos los numeros les anteponemos
					// un punto menos al primero
					sIPAddress += ".";
				}
				// Jugamos con los bytes y cambiamos el bit del signo
				sIPAddress += bIPAddress[x] & 255;
			}
			// sIPAddress=sIPAddress+ " "+ address.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sIPAddress;
	}

	/**
	 * Mï¿½todo para descargar archivos excel
	 * 
	 * @param url
	 */
	public static void descargarExcel(String url) {
		System.out.println(url);
		File ficheroXLS = new File(url);
		FacesContext ctx = FacesContext.getCurrentInstance();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(ficheroXLS);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] bytes = new byte[1000];
		int read = 0;

		if (!ctx.getResponseComplete()) {
			String fileName = ficheroXLS.getName();
			String contentType = "application/vnd.ms-excel";
			// String contentType = "application/pdf";
			HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
			response.setContentType(contentType);
			response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
			ServletOutputStream out = null;
			try {
				out = response.getOutputStream();
				while ((read = fis.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Descargado....!!!");
			ctx.responseComplete();
		}
	}

	/**
	 * Mï¿½todo para descargar archivos excel
	 * 
	 * @param url
	 */
	public static void descargarPDF(String url) {
		System.out.println(url);
		File ficheroXLS = new File(url);
		FacesContext ctx = FacesContext.getCurrentInstance();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(ficheroXLS);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] bytes = new byte[1000];
		int read = 0;

		if (!ctx.getResponseComplete()) {
			String fileName = ficheroXLS.getName();
			// String contentType = "application/vnd.ms-excel";
			String contentType = "application/pdf";
			HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
			response.setContentType(contentType);
			response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
			ServletOutputStream out = null;
			try {
				out = response.getOutputStream();
				while ((read = fis.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Descargado PDF....!!!");
			ctx.responseComplete();
		}
	}

	// Mï¿½todo Cifrado

	// mï¿½todo para cifrar el texto
	public static String cifradoCesar(String texto, int codigo) {
		StringBuilder cifrado = new StringBuilder();
		codigo = codigo % 26;
		for (int i = 0; i < texto.length(); i++) {
			if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
				if ((texto.charAt(i) + codigo) > 'z') {
					cifrado.append((char) (texto.charAt(i) + codigo - 26));
				} else {
					cifrado.append((char) (texto.charAt(i) + codigo));
				}
			} else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
				if ((texto.charAt(i) + codigo) > 'Z') {
					cifrado.append((char) (texto.charAt(i) + codigo - 26));
				} else {
					cifrado.append((char) (texto.charAt(i) + codigo));
				}
			}
		}
		return cifrado.toString();
	}

	// mï¿½todo para cifrar el texto
	public static String cifradoCesarInteger(String texto, int codigo) {
		String valor = "0123456789";
		StringBuilder cifrado = new StringBuilder();
		codigo = codigo % 9;
		for (int i = 0; i < texto.length(); i++) {
			if ((texto.charAt(i) + codigo) > valor.charAt(9)) {
				cifrado.append((char) (texto.charAt(i) + codigo - 9));
			} else {
				cifrado.append((char) (texto.charAt(i) + codigo));
			}
		}
		return cifrado.toString();
	}

	// mï¿½todo para descifrar el texto
	public static String descifradoCesar(String texto, int codigo) {
		StringBuilder cifrado = new StringBuilder();
		codigo = codigo % 26;
		for (int i = 0; i < texto.length(); i++) {
			if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
				if ((texto.charAt(i) - codigo) < 'a') {
					cifrado.append((char) (texto.charAt(i) - codigo + 26));
				} else {
					cifrado.append((char) (texto.charAt(i) - codigo));
				}
			} else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
				if ((texto.charAt(i) - codigo) < 'A') {
					cifrado.append((char) (texto.charAt(i) - codigo + 26));
				} else {
					cifrado.append((char) (texto.charAt(i) - codigo));
				}
			}
		}
		return cifrado.toString();
	}

	// mï¿½todo para descifrar el texto
	public static String descifradoCesarInteger(String texto, int codigo) {
		String valor = "0123456789";
		StringBuilder cifrado = new StringBuilder();
		codigo = codigo % 9;
		for (int i = 0; i < texto.length(); i++) {
			if ((texto.charAt(i) - codigo) < valor.charAt(0)) {
				cifrado.append((char) (texto.charAt(i) - codigo + 9));
			} else {
				cifrado.append((char) (texto.charAt(i) - codigo));
			}
		}
		return cifrado.toString();
	}

	// mï¿½todo para cifrar el texto
	public static String cifradoPropio(String texto, int codigo) {
		String valor = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder cifrado = new StringBuilder();
		codigo = codigo % 26;
		for (int i = 0; i < texto.length(); i++) {
			if ((texto.charAt(i) + codigo) > valor.charAt(61)) {
				cifrado.append((char) (texto.charAt(i) + codigo - 30));
			} else {
				cifrado.append((char) (texto.charAt(i) + codigo));
			}
		}
		return cifrado.toString();
	}

	// mï¿½todo para descifrar el texto
	public static String descifradoPropio(String texto, int codigo) {
		String valor = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder cifrado = new StringBuilder();
		codigo = codigo % 26;
		for (int i = 0; i < texto.length(); i++) {
			if ((texto.charAt(i) - codigo) < valor.charAt(0)) {
				cifrado.append((char) (texto.charAt(i) - codigo + 26));
			} else {
				cifrado.append((char) (texto.charAt(i) - codigo));
			}
		}
		return cifrado.toString();
	}
	
	//método de cifrado AES 
	
	private static String llaveSimetrica = "Yachay";
	
	 public static byte[] encDatos(String cadena){
	        byte [] aError = null;
	        SecretKeySpec key = new SecretKeySpec(llaveSimetrica.getBytes(), "AES");
	        Cipher cipher;
	        try {
	                cipher = Cipher.getInstance("AES");
	                //Comienzo a encriptar
	                cipher.init(Cipher.ENCRYPT_MODE, key);
	                byte[] datosCifrados = cipher.doFinal(cadena.getBytes()); //cadena = a texto a cifrar
	                /*
	                 * TODO: Representar los bytes como string vía base64, así será
	                 * humanamente leíble. La otra opción es expresar como hexadecimal
	                 * 
	                 * En este caso lo imprimo en pantalla como bytes.
	                 */
	                return datosCifrados;
	        } catch (Exception e) {
	                return null;
	        }
	    
	    }
	 
	 public static String dencDatos(byte [] datosCifrados){
	        SecretKeySpec key = new SecretKeySpec(llaveSimetrica.getBytes(), "AES");
	        Cipher cipher;
	        try {
	                cipher = Cipher.getInstance("AES");
	               
	                //Comienzo a desencriptar
	                cipher.init(Cipher.DECRYPT_MODE, key);
	                byte[] datosDecifrados = cipher.doFinal(datosCifrados);
	                return new String(datosDecifrados); 
	                /*
	                 * TODO: Representar los bytes como string vía base64, así será
	                 * humanamente leíble. La otra opción es expresar como hexadecimal
	                 * 
	                 * En este caso lo imprimo en pantalla como bytes.
	                 */
	        } catch (Exception e) {
	                return null;
	        }  
	    }
	    
	    public static String asHex (byte buf[]) {
	         StringBuffer strbuf = new StringBuffer(buf.length * 2);
	         int i;

	         for (i = 0; i < buf.length; i++) {
	          if (((int) buf[i] & 0xff) < 0x10)
	               strbuf.append("0");

	          strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
	         }

	         return strbuf.toString();
	    }

	public static String quitarEspacios(String parametro) {
		if (parametro == null)
			parametro = "";
		return parametro.trim();
	}

	

	public static void UTM2Deg(String UTM) {
		double latitude;
	    double longitude;
		String[] parts = UTM.split(" ");
		int Zone = Integer.parseInt(parts[0]);
		char Letter = parts[1].toUpperCase(Locale.ENGLISH).charAt(0);
		double Easting = Double.parseDouble(parts[2]);
		double Northing = Double.parseDouble(parts[3]);
		double Hem;
		if (Letter > 'M')
			Hem = 'N';
		else
			Hem = 'S';
		double north;
		if (Hem == 'S')
			north = Northing - 10000000;
		else
			north = Northing;
		latitude = (north / 6366197.724 / 0.9996
				+ (1 + 0.006739496742 * Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2)
						- 0.006739496742 * Math.sin(north / 6366197.724 / 0.9996)
								* Math.cos(north / 6366197.724
										/ 0.9996)
								* (Math.atan(Math
										.cos(Math
												.atan((Math
														.exp((Easting - 500000)
																/ (0.9996 * 6399593.625
																		/ Math.sqrt((1
																				+ 0.006739496742
																						* Math.pow(
																								Math.cos(north
																										/ 6366197.724
																										/ 0.9996),
																								2))))
																* (1 - 0.006739496742
																		* Math.pow(
																				(Easting - 500000) / (0.9996
																						* 6399593.625
																						/ Math.sqrt((1 + 0.006739496742
																								* Math.pow(
																										Math.cos(
																												north / 6366197.724
																														/ 0.9996),
																										2)))),
																				2)
																		/ 2
																		* Math.pow(
																				Math.cos(north / 6366197.724 / 0.9996),
																				2)
																		/ 3))
														- Math.exp(-(Easting - 500000)
																/ (0.9996 * 6399593.625
																		/ Math.sqrt((1 + 0.006739496742 * Math.pow(
																				Math.cos(north / 6366197.724 / 0.9996),
																				2))))
																* (1 - 0.006739496742
																		* Math.pow(
																				(Easting - 500000) / (0.9996
																						* 6399593.625
																						/ Math.sqrt((1 + 0.006739496742
																								* Math.pow(
																										Math.cos(
																												north / 6366197.724
																														/ 0.9996),
																										2)))),
																				2)
																		/ 2
																		* Math.pow(
																				Math.cos(north / 6366197.724 / 0.9996),
																				2)
																		/ 3)))
														/ 2
														/ Math.cos((north - 0.9996 * 6399593.625
																* (north / 6366197.724 / 0.9996
																		- 0.006739496742 * 3 / 4
																				* (north / 6366197.724 / 0.9996 + Math
																						.sin(2 * north
																								/ 6366197.724 / 0.9996)
																						/ 2)
																		+ Math.pow(0.006739496742 * 3 / 4, 2) * 5
																				/ 3 * (3
																						* (north / 6366197.724
																								/ 0.9996
																								+ Math
																										.sin(2 * north
																												/ 6366197.724
																												/ 0.9996)
																										/ 2)
																						+ Math.sin(2 * north
																								/ 6366197.724 / 0.9996)
																								* Math.pow(
																										Math.cos(
																												north / 6366197.724
																														/ 0.9996),
																										2))
																				/ 4
																		- Math.pow(0.006739496742 * 3 / 4, 3) * 35 / 27
																				* (5 * (3
																						* (north / 6366197.724 / 0.9996
																								+ Math.sin(2 * north
																										/ 6366197.724
																										/ 0.9996) / 2)
																						+ Math.sin(2 * north
																								/ 6366197.724 / 0.9996)
																								* Math.pow(
																										Math.cos(
																												north / 6366197.724
																														/ 0.9996),
																										2))
																						/ 4
																						+ Math.sin(2 * north
																								/ 6366197.724 / 0.9996)
																								* Math.pow(
																										Math.cos(
																												north / 6366197.724
																														/ 0.9996),
																										2)
																								* Math.pow(
																										Math.cos(
																												north / 6366197.724
																														/ 0.9996),
																										2))
																				/ 3))
																/ (0.9996 * 6399593.625
																		/ Math.sqrt((1 + 0.006739496742 * Math.pow(
																				Math.cos(north / 6366197.724 / 0.9996),
																				2))))
																* (1 - 0.006739496742
																		* Math.pow(
																				(Easting - 500000) / (0.9996
																						* 6399593.625
																						/ Math.sqrt((1 + 0.006739496742
																								* Math.pow(
																										Math.cos(
																												north / 6366197.724
																														/ 0.9996),
																										2)))),
																				2)
																		/ 2
																		* Math.pow(
																				Math.cos(north / 6366197.724 / 0.9996),
																				2))
																+ north / 6366197.724 / 0.9996)))
										* Math.tan((north - 0.9996 * 6399593.625 * (north / 6366197.724 / 0.9996
												- 0.006739496742 * 3 / 4
														* (north / 6366197.724 / 0.9996
																+ Math.sin(2 * north / 6366197.724 / 0.9996) / 2)
												+ Math.pow(0.006739496742 * 3 / 4, 2) * 5 / 3 * (3
														* (north / 6366197.724 / 0.9996
																+ Math.sin(2 * north / 6366197.724 / 0.9996) / 2)
														+ Math.sin(2 * north / 6366197.724 / 0.9996)
																* Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2))
														/ 4
												- Math.pow(0.006739496742 * 3 / 4, 3) * 35 / 27 * (5 * (3
														* (north / 6366197.724 / 0.9996
																+ Math.sin(2 * north / 6366197.724 / 0.9996) / 2)
														+ Math.sin(2 * north / 6366197.724 / 0.9996)
																* Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2))
														/ 4
														+ Math.sin(2 * north / 6366197.724 / 0.9996)
																* Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2)
																* Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2))
														/ 3))
												/ (0.9996 * 6399593.625 / Math.sqrt((1 + 0.006739496742
														* Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2))))
												* (1 - 0.006739496742
														* Math.pow((Easting - 500000) / (0.9996 * 6399593.625
																/ Math.sqrt((1 + 0.006739496742 * Math.pow(
																		Math.cos(north / 6366197.724 / 0.9996), 2)))),
																2)
														/ 2 * Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2))
												+ north / 6366197.724 / 0.9996))
										- north / 6366197.724
												/ 0.9996)
								* 3 / 2)
						* (Math.atan(Math
								.cos(Math
										.atan((Math
												.exp((Easting - 500000) / (0.9996 * 6399593.625
														/ Math.sqrt((1 + 0.006739496742 * Math
																.pow(Math.cos(north / 6366197.724 / 0.9996), 2))))
														* (1 - 0.006739496742
																* Math.pow(
																		(Easting - 500000)
																				/ (0.9996 * 6399593.625
																						/ Math.sqrt((1 + 0.006739496742
																								* Math.pow(
																										Math.cos(
																												north / 6366197.724
																														/ 0.9996),
																										2)))),
																		2)
																/ 2
																* Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2)
																/ 3))
												- Math.exp(
														-(Easting - 500000)
																/ (0.9996 * 6399593.625
																		/ Math.sqrt(
																				(1 + 0.006739496742
																						* Math.pow(
																								Math.cos(north
																										/ 6366197.724
																										/ 0.9996),
																								2))))
																* (1 - 0.006739496742
																		* Math.pow(
																				(Easting - 500000) / (0.9996
																						* 6399593.625
																						/ Math.sqrt((1 + 0.006739496742
																								* Math.pow(
																										Math.cos(
																												north / 6366197.724
																														/ 0.9996),
																										2)))),
																				2)
																		/ 2
																		* Math.pow(
																				Math.cos(north / 6366197.724 / 0.9996),
																				2)
																		/ 3)))
												/ 2
												/ Math.cos(
														(north - 0.9996 * 6399593.625
																* (north / 6366197.724 / 0.9996
																		- 0.006739496742 * 3 / 4 * (north / 6366197.724
																				/ 0.9996
																				+ Math
																						.sin(2 * north / 6366197.724
																								/ 0.9996)
																						/ 2)
																		+ Math.pow(0.006739496742 * 3 / 4, 2) * 5
																				/ 3 * (3
																						* (north / 6366197.724
																								/ 0.9996
																								+ Math
																										.sin(2 * north
																												/ 6366197.724
																												/ 0.9996)
																										/ 2)
																						+ Math.sin(2 * north
																								/ 6366197.724 / 0.9996)
																								* Math.pow(
																										Math.cos(
																												north / 6366197.724
																														/ 0.9996),
																										2))
																				/ 4
																		- Math.pow(0.006739496742 * 3 / 4, 3) * 35 / 27
																				* (5 * (3
																						* (north / 6366197.724 / 0.9996
																								+ Math.sin(2 * north
																										/ 6366197.724
																										/ 0.9996) / 2)
																						+ Math.sin(2 * north
																								/ 6366197.724 / 0.9996)
																								* Math.pow(
																										Math.cos(
																												north / 6366197.724
																														/ 0.9996),
																										2))
																						/ 4
																						+ Math.sin(2 * north
																								/ 6366197.724 / 0.9996)
																								* Math.pow(
																										Math.cos(
																												north / 6366197.724
																														/ 0.9996),
																										2)
																								* Math.pow(
																										Math.cos(
																												north / 6366197.724
																														/ 0.9996),
																										2))
																				/ 3))
																/ (0.9996 * 6399593.625
																		/ Math.sqrt((1 + 0.006739496742 * Math.pow(
																				Math.cos(north / 6366197.724 / 0.9996),
																				2))))
																* (1 - 0.006739496742
																		* Math.pow(
																				(Easting - 500000) / (0.9996
																						* 6399593.625
																						/ Math.sqrt((1 + 0.006739496742
																								* Math.pow(
																										Math.cos(
																												north / 6366197.724
																														/ 0.9996),
																										2)))),
																				2)
																		/ 2
																		* Math.pow(
																				Math.cos(north / 6366197.724 / 0.9996),
																				2))
																+ north / 6366197.724 / 0.9996)))
								* Math.tan((north - 0.9996 * 6399593.625 * (north / 6366197.724 / 0.9996
										- 0.006739496742 * 3 / 4
												* (north / 6366197.724 / 0.9996
														+ Math.sin(2 * north / 6366197.724 / 0.9996) / 2)
										+ Math.pow(0.006739496742 * 3 / 4, 2) * 5 / 3
												* (3 * (north / 6366197.724 / 0.9996
														+ Math.sin(2 * north / 6366197.724 / 0.9996) / 2)
														+ Math.sin(2 * north / 6366197.724 / 0.9996)
																* Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2))
												/ 4
										- Math.pow(0.006739496742 * 3 / 4, 3) * 35 / 27
												* (5 * (3
														* (north / 6366197.724 / 0.9996
																+ Math.sin(2 * north / 6366197.724 / 0.9996) / 2)
														+ Math.sin(2 * north / 6366197.724 / 0.9996)
																* Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2))
														/ 4
														+ Math.sin(2 * north / 6366197.724 / 0.9996)
																* Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2)
																* Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2))
												/ 3))
										/ (0.9996 * 6399593.625
												/ Math.sqrt((1 + 0.006739496742
														* Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2))))
										* (1 - 0.006739496742
												* Math.pow(
														(Easting - 500000) / (0.9996 * 6399593.625
																/ Math.sqrt((1 + 0.006739496742 * Math.pow(
																		Math.cos(north / 6366197.724 / 0.9996), 2)))),
														2)
												/ 2 * Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2))
										+ north / 6366197.724 / 0.9996))
								- north / 6366197.724 / 0.9996))
				* 180 / Math.PI;
		latitude = Math.round(latitude * 10000000);
		latitude = latitude / 10000000;
		longitude = Math
				.atan((Math
						.exp((Easting - 500000)
								/ (0.9996 * 6399593.625
										/ Math.sqrt((1 + 0.006739496742
												* Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2))))
								* (1 - 0.006739496742
										* Math.pow(
												(Easting - 500000)
														/ (0.9996 * 6399593.625
																/ Math.sqrt((1 + 0.006739496742 * Math.pow(
																		Math.cos(north / 6366197.724 / 0.9996), 2)))),
												2)
										/ 2 * Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2) / 3))
						- Math.exp(-(Easting - 500000) / (0.9996 * 6399593.625 / Math
								.sqrt((1 + 0.006739496742 * Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2))))
								* (1
										- 0.006739496742
												* Math.pow((Easting - 500000) / (0.9996 * 6399593.625
														/ Math.sqrt((1 + 0.006739496742 * Math
																.pow(Math.cos(north / 6366197.724 / 0.9996), 2)))),
														2)
												/ 2 * Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2)
												/ 3)))
						/ 2
						/ Math.cos((north - 0.9996 * 6399593.625 * (north / 6366197.724 / 0.9996
								- 0.006739496742 * 3 / 4
										* (north / 6366197.724 / 0.9996
												+ Math.sin(2 * north / 6366197.724 / 0.9996) / 2)
								+ Math.pow(0.006739496742 * 3 / 4, 2) * 5 / 3
										* (3 * (north / 6366197.724 / 0.9996
												+ Math.sin(2 * north / 6366197.724 / 0.9996) / 2)
												+ Math
														.sin(2 * north / 6366197.724 / 0.9996)
														* Math
																.pow(Math.cos(north / 6366197.724 / 0.9996), 2))
										/ 4
								- Math.pow(0.006739496742 * 3 / 4, 3) * 35 / 27
										* (5 * (3
												* (north / 6366197.724 / 0.9996
														+ Math.sin(2 * north / 6366197.724 / 0.9996) / 2)
												+ Math.sin(2 * north / 6366197.724 / 0.9996)
														* Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2))
												/ 4
												+ Math.sin(2 * north / 6366197.724 / 0.9996)
														* Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2)
														* Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2))
										/ 3))
								/ (0.9996 * 6399593.625
										/ Math.sqrt((1 + 0.006739496742
												* Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2))))
								* (1 - 0.006739496742
										* Math.pow(
												(Easting - 500000)
														/ (0.9996 * 6399593.625
																/ Math.sqrt((1 + 0.006739496742 * Math.pow(
																		Math.cos(north / 6366197.724 / 0.9996), 2)))),
												2)
										/ 2 * Math.pow(Math.cos(north / 6366197.724 / 0.9996), 2))
								+ north / 6366197.724 / 0.9996))
				* 180 / Math.PI + Zone * 6 - 183;
		longitude = Math.round(longitude * 10000000);
		longitude = longitude / 10000000;
		
		System.out.println("Latitud: "+latitude);
		System.out.println("Longitud: "+longitude);
		
	}
}
