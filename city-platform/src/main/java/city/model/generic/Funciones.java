package city.model.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

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
		// verifica que los dos primeros d�gitos correspondan a un valor entre
		// 1
		// y NUMERO_DE_PROVINCIAS
		int prov = Integer.parseInt(cedula.substring(0, 2));
		if (!((prov > 0) && (prov <= num_provincias) || prov == 30)) {
			// addError("La c�dula ingresada no es v�lida");
			System.out.println("Error: cedula ingresada mal");
			return false;
		}
		// verifica que el �ltimo d�gito de la c�dula sea v�lido
		int[] d = new int[10];
		// Asignamos el string a un array
		for (int i = 0; i < d.length; i++) {
			d[i] = Integer.parseInt(cedula.charAt(i) + "");
		}
		int imp = 0;
		int par = 0;
		// sumamos los duplos de posici�n impar
		for (int i = 0; i < d.length; i += 2) {
			d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
			imp += d[i];
		}
		// sumamos los digitos de posici�n par
		for (int i = 1; i < (d.length - 1); i += 2) {
			par += d[i];
		}
		// Sumamos los dos resultados
		int suma = imp + par;
		// Restamos de la decena superior
		int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1) + "0") - suma;
		// Si es diez el d�cimo d�gito es cero
		d10 = (d10 == 10) ? 0 : d10;
		// si el d�cimo d�gito calculado es igual al digitado la c�dula es
		// correcta
		if (d10 == d[9]) {
			return true;
		} else {
			// addError("La c�dula ingresada no es v�lida");
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
		if (fecha.isEmpty() || fecha.equals("null"))
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
	 * Convierte una cadena en otra con codificaci�n utf-8
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
		// Se ajusta el a�o dependiendo el mes y el d�a
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
	 * M�todo para descargar archivos excel
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
	 * M�todo para descargar archivos excel
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

	// m�todo de cifrado AES

	public static String encriptarAES256(String cadena, SecretKey key) {
		Cipher cipher;
		String value = "";
		if (cadena != null){
		try {
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] textobytes = cadena.getBytes();
			byte[] cipherbytes = cipher.doFinal(textobytes);
			value = new BASE64Encoder().encode(cipherbytes);
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex.getMessage());
			value="error";
		} catch (NoSuchPaddingException ex) {
			System.err.println(ex.getMessage());
			value="error";
		} catch (InvalidKeyException ex) {
			System.err.println(ex.getMessage());
			value="error";
		} catch (IllegalBlockSizeException ex) {
			System.err.println(ex.getMessage());
			value="error";
		} catch (BadPaddingException ex) {
			System.err.println(ex.getMessage());
			value="error";
		}
		}
		return value;
	}

	public static String desencriptarAES256(String datosCifrados, SecretKey key) {
		Cipher cipher;
		String str = "error";
		if (datosCifrados != null){
		try {
			byte[] value = new BASE64Decoder().decodeBuffer(datosCifrados);
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] cipherbytes = cipher.doFinal(value);
			str = new String(cipherbytes);
		} catch (InvalidKeyException ex) {
			System.err.println(ex.getMessage());
			str = "error";
		} catch (IllegalBlockSizeException ex) {
			System.err.println(ex.getMessage());
			str = "error";
		} catch (BadPaddingException ex) {
			System.err.println(ex.getMessage());
			str = "error";
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			str = "error";
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex.getMessage());
			str = "error";
		} catch (NoSuchPaddingException ex) {
			System.err.println(ex.getMessage());
			str = "error";
		}
		}
		return str;
	}
	
	 public static SecretKey addKey( String value ){
		 SecretKey key;
	        byte[] valuebytes = value.getBytes();            
	        key = new SecretKeySpec( Arrays.copyOf( valuebytes, 16 ) , "AES" );   
	        return key;
	    }

	public static String quitarEspacios(String parametro) {
		if (parametro == null)
			parametro = "";
		return parametro.trim();
	}

	// coordenadas UTM

	public static String UTM2Deg(String UTM) {
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

		return latitude+" "+longitude;
	}
	
	public static String Deg2UTM(double Lat, double Lon) {
		double Easting;
		double Northing;
		int Zone;
		char Letter;
		Zone = (int) Math.floor(Lon / 6 + 31);
		if (Lat < -72)
			Letter = 'C';
		else if (Lat < -64)
			Letter = 'D';
		else if (Lat < -56)
			Letter = 'E';
		else if (Lat < -48)
			Letter = 'F';
		else if (Lat < -40)
			Letter = 'G';
		else if (Lat < -32)
			Letter = 'H';
		else if (Lat < -24)
			Letter = 'J';
		else if (Lat < -16)
			Letter = 'K';
		else if (Lat < -8)
			Letter = 'L';
		else if (Lat < 0)
			Letter = 'M';
		else if (Lat < 8)
			Letter = 'N';
		else if (Lat < 16)
			Letter = 'P';
		else if (Lat < 24)
			Letter = 'Q';
		else if (Lat < 32)
			Letter = 'R';
		else if (Lat < 40)
			Letter = 'S';
		else if (Lat < 48)
			Letter = 'T';
		else if (Lat < 56)
			Letter = 'U';
		else if (Lat < 64)
			Letter = 'V';
		else if (Lat < 72)
			Letter = 'W';
		else
			Letter = 'X';
		Easting = 0.5
				* Math.log((1 + Math.cos(Lat * Math.PI / 180)
						* Math.sin(Lon * Math.PI / 180 - (6 * Zone - 183) * Math.PI / 180))
						/ (1 - Math.cos(Lat * Math.PI / 180) * Math.sin(
								Lon * Math.PI / 180 - (6 * Zone - 183) * Math.PI / 180)))
				* 0.9996 * 6399593.62
				/ Math.pow(
						(1 + Math
								.pow(0.0820944379,
										2)
								* Math.pow(Math.cos(Lat * Math.PI / 180), 2)),
						0.5)
				* (1 + Math.pow(0.0820944379, 2) / 2
						* Math.pow(
								(0.5 * Math.log((1
										+ Math.cos(Lat * Math.PI / 180)
												* Math.sin(Lon * Math.PI / 180 - (6 * Zone - 183) * Math.PI / 180))
										/ (1 - Math.cos(Lat * Math.PI / 180)
												* Math.sin(Lon * Math.PI / 180 - (6 * Zone - 183) * Math.PI / 180)))),
								2)
						* Math.pow(Math.cos(Lat * Math.PI / 180), 2) / 3)
				+ 500000;
		Easting = Math.round(Easting * 100) * 0.01;
		Northing = (Math
				.atan(Math.tan(Lat * Math.PI / 180)
						/ Math.cos((Lon * Math.PI / 180 - (6 * Zone - 183) * Math.PI / 180)))
				- Lat * Math.PI
						/ 180)
				* 0.9996 * 6399593.625
				/ Math.sqrt(
						1 + 0.006739496742
								* Math.pow(
										Math.cos(
												Lat * Math.PI
														/ 180),
										2))
				* (1 + 0.006739496742 / 2
						* Math.pow(
								0.5 * Math.log((1 + Math.cos(Lat * Math.PI / 180)
										* Math.sin((Lon * Math.PI / 180 - (6 * Zone - 183) * Math.PI / 180)))
										/ (1 - Math.cos(Lat * Math.PI / 180) * Math.sin(
												(Lon * Math.PI / 180 - (6 * Zone - 183) * Math.PI / 180)))),
								2)
						* Math.pow(Math.cos(Lat * Math.PI / 180), 2))
				+ 0.9996 * 6399593.625 * (Lat * Math.PI / 180
						- 0.005054622556 * (Lat * Math.PI / 180 + Math.sin(2 * Lat * Math.PI / 180) / 2)
						+ 4.258201531e-05 * (3 * (Lat * Math.PI / 180 + Math.sin(2 * Lat * Math.PI / 180) / 2)
								+ Math.sin(2 * Lat * Math.PI / 180) * Math.pow(Math.cos(Lat * Math.PI / 180), 2)) / 4
						- 1.674057895e-07 * (5
								* (3 * (Lat * Math.PI / 180 + Math.sin(2 * Lat * Math.PI / 180) / 2)
										+ Math.sin(2 * Lat * Math.PI / 180)
												* Math.pow(Math.cos(Lat * Math.PI / 180), 2))
								/ 4
								+ Math.sin(2 * Lat * Math.PI / 180) * Math.pow(Math.cos(Lat * Math.PI / 180), 2)
										* Math.pow(Math.cos(Lat * Math.PI / 180), 2))
								/ 3);
		if (Letter < 'M')
			Northing = Northing + 10000000;
		Northing = Math.round(Northing * 100) * 0.01;
		return Easting + " " + Northing;
	}
}
