/**
 * 
 */
package recoverpass.apis.helper;

import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import recoverpass.apis.bean.ListadoPalabrasProhibidas;
import recoverpass.apis.res.ResourcesConfig;

/**
 *
 */
public class Utiles {
	/**
	 * Diccionario de palabras
	 */
	private static List<String> LISTA_PALABRAS_PROHIBIDAS = new ArrayList<>();
	private static List<String> LISTA_CARACTERES_PROHIBIDOS = new ArrayList<>();
	private static ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * Mensajes
	 */
	private static final String EVALUAR_POST = "Procediendo a validar integridad de request [POST]";
	private static final String EVALUAR_GET = "Procediendo a validar integridad de request [GET]";
	private static final String ALERTA_PALABRA_ENCONTRADA = "[ALERTA] Palabra prohibida encontrada : ";
	private static final String ALERTA_BYPASS_DICCIONARIO = "[ALERTA] BYPASS VALIDACION CONTRA DICCIONARIO PALABRAS RESERVADAS - NO SE PUDO CARGAR";
	private static final String ALERTA_BYPASS_CARACTERES = "[ALERTA] BYPASS CARACTERES PROHIBIDOS - NO SE PUDO CARGAR";
	private static final String ALERTA_CARACTER_NO_PERMITIDO_ENCONTRADO = "[ALERTA] Caracter no permitido encontrado: ";

	/**
	 * LOG
	 */
	private static final Logger log = Logger.getLogger(Utiles.class);

	/**
	 * Cargar lista
	 */
	static {
		try {
			URL rutaDiccionario = ResourcesConfig.getUrlfile(Constantes.ARCHIVO_PALABRAS_RESERVADAS);
			ListadoPalabrasProhibidas palabrasProhibidas = MAPPER.readValue(rutaDiccionario,
					ListadoPalabrasProhibidas.class);

			LISTA_PALABRAS_PROHIBIDAS.addAll(palabrasProhibidas.getListadoPalabrasProhibidas());
			log.info("Diccionario Palabras Prohibidas Cargado Correctamente");

			LISTA_CARACTERES_PROHIBIDOS = palabrasProhibidas.getListadoCaracteresProhibidos();
			log.info("Diccionario Caracteres Prohibidos Cargado Correctamente");

		} catch (Exception e) {
			log.error("ERROR AL CARGAR VALIDACION METADATA, ERROR > " + Utiles.obtenerExcepcionRaiz(e));
		}
	}

	/**
	 * Permite validar si la cadena de entrada posee algun caracter no reconocido,
	 * esto se realiza para filtar por ethical hacking e inyecciones, por ende se
	 * toma todo el request y se validan unicamente elementos de json tales como
	 * {},:" , etc ademas de letras y numeros
	 * 
	 * 
	 * @param input
	 * @return
	 */
	public static boolean validarEntradaRequestPost(String input) {

		log.info(EVALUAR_POST);

		// Limpiar request de espacios, tabuladores y saltos de linea
		input = input.replaceAll("[\\n\\t ]", "");
		input = input.toLowerCase();

		log.info("Request a validar: " + input);

		boolean contieneCaracteresProhibidos = false;
		boolean contienePalabrasProhibidas = false;

		contieneCaracteresProhibidos = validarCaracteresProhibidos(input, contieneCaracteresProhibidos);
		contienePalabrasProhibidas = validarPalabrasProhibidas(input, contienePalabrasProhibidas);

		log.info("Contiene Caracteres Prohibidos : " + contieneCaracteresProhibidos);
		log.info("Contiene Palabras Prohibidas : " + contienePalabrasProhibidas);

		// Salida
		if (!contieneCaracteresProhibidos & !contienePalabrasProhibidas)
			return true;
		else
			return false;

	}

	/**
	 * Buscar palabras prohibidas
	 * 
	 * @param input
	 * @param contienePalabrasProhibidas
	 * @return
	 */
	private static boolean validarPalabrasProhibidas(String input, boolean contienePalabrasProhibidas) {

		if (LISTA_PALABRAS_PROHIBIDAS.isEmpty()) {
			log.error(ALERTA_BYPASS_DICCIONARIO);
		} else {

			// Validar si el request contiene palabras reservadas
			for (String word : LISTA_PALABRAS_PROHIBIDAS) {

				word = word.toLowerCase();

				if (contieneStringExacta(input, word)) {
					contienePalabrasProhibidas = true;
					log.info(ALERTA_PALABRA_ENCONTRADA + word);
					break;
				}
			}

		}

		return contienePalabrasProhibidas;
	}

	/**
	 * Buscar caracteres prohibidos
	 * 
	 * @param input
	 * @param contieneCaracteresProhibidos
	 * @return
	 */
	private static boolean validarCaracteresProhibidos(String input, boolean contieneCaracteresProhibidos) {

		if (LISTA_CARACTERES_PROHIBIDOS.isEmpty()) {
			log.error(ALERTA_BYPASS_CARACTERES);
		} else {

			// Validar si el request contiene palabras reservadas
			for (String word : LISTA_CARACTERES_PROHIBIDOS) {

				word = word.toLowerCase();

				if (input.contains(word)) {
					contieneCaracteresProhibidos = true;
					log.info(ALERTA_CARACTER_NO_PERMITIDO_ENCONTRADO + word);
					break;
				}
			}

		}

		return contieneCaracteresProhibidos;
	}

	/**
	 * Permite evaluar los parametros por get y tomar acciones en el caso de que
	 * estos no fueran permitidos
	 * 
	 * @param pathparam
	 * @param queryParam
	 * @return
	 */
	public static boolean validarEntradaRequestGet(MultivaluedMap<String, String> pathparam,
			MultivaluedMap<String, String> queryParam) {

		log.info(EVALUAR_GET);
		boolean requestValido = true;

		log.info("Evaluando PathParams...");
		for (Map.Entry<String, List<String>> entry : pathparam.entrySet()) {

			String key = entry.getKey();
			log.info("Key: " + key);

			List<String> valores = entry.getValue();
			String rut = valores.get(0).toLowerCase();

			log.info("Value : " + rut);

			requestValido = validarElementosGET(requestValido, rut);

		}

		log.info("Evaluando QueryParams...");
		for (Map.Entry<String, List<String>> entry : queryParam.entrySet()) {

			String key = entry.getKey();
			log.info("Key: " + key);

			log.info("> Evaluando valores del keyset");
			List<String> valores = entry.getValue();

			for (String valor : valores) {

				valor = valor.toLowerCase();
				log.info("Value : " + valor);

				requestValido = validarElementosGET(requestValido, valor);

			}

		}

		return requestValido;

	}

	/**
	 * @param requestValido
	 * @param valor
	 * @return
	 */
	private static boolean validarElementosGET(boolean requestValido, String valor) {

		/*
		 * Buscar simbolos
		 */
		if (LISTA_CARACTERES_PROHIBIDOS.isEmpty()) {
			log.error(ALERTA_BYPASS_CARACTERES);
		} else {

			// Validar si el request contiene palabras reservadas
			for (String word : LISTA_CARACTERES_PROHIBIDOS) {

				word = word.toLowerCase();

				if (valor.contains(word)) {
					requestValido = false;
					log.info(ALERTA_CARACTER_NO_PERMITIDO_ENCONTRADO + word);
					break;
				}
			}

		}

		/*
		 * Buscar palabras
		 */
		if (LISTA_PALABRAS_PROHIBIDAS.isEmpty()) {
			log.error(ALERTA_BYPASS_DICCIONARIO);
		} else {

			for (String word : LISTA_PALABRAS_PROHIBIDAS) {
				word = word.toLowerCase();

				if (contieneStringExacta(valor, word)) {
					requestValido = false;
					log.info(ALERTA_PALABRA_ENCONTRADA + word);
					break;
				}

			}
		}

		return requestValido;
	}

	/**
	 * ContieneStringExacta
	 * 
	 * @param source
	 * @param subItem
	 * @return
	 */
	private static boolean contieneStringExacta(String text, String word) {

		String REGEX_FIND_WORD = "(?i).*?\\b%s\\b.*?";
		String regex = String.format(REGEX_FIND_WORD, Pattern.quote(word));
		return text.matches(regex);

	}

	/**
	 * Obtener la excepcion raiz, con esto no es necesario imprimir todo el objeto y
	 * solo se obtendra la causa especifica
	 * 
	 * Se llama asi: System.out.println(getRootException(e).getLocalizedMessage());
	 * 
	 * @param exception
	 * @return
	 */
	public static Throwable obtenerExcepcionRaiz(Throwable exception) {

		Throwable rootException = exception;
		while (rootException.getCause() != null) {
			rootException = rootException.getCause();
		}

		return rootException;

	}

	/**
	 * Permite limpiarle el / al final a las url
	 * 
	 * @param input
	 * @return
	 */
	public static String obtenerUrlLimpia(String input) {

		String res = input;
		log.info("URL analizada: " + input);

		try {
			String ultimoCaracter = input.substring(res.length() - 1, res.length());

			if (ultimoCaracter.equalsIgnoreCase("/")) {
				res = res.substring(0, res.length() - 1);
				log.info("La url ha sido limpiada, resultado: " + res);
			} else {
				log.info("Url OK");
			}

		} catch (Exception e) {

			log.error("ERROR > " + obtenerExcepcionRaiz(e));
		}

		return res;

	}

	/**
	 * 
	 * Metodo encargado de formatear los decimales a dos numeros despues de la coma
	 * 
	 * @param numeroFormatea
	 * 
	 * @return
	 * 
	 */
	public static double formateaDecimal(double numeroFormatea) {
		return (double) Math.round(numeroFormatea * 100d) / 100d;
	}

	/**
	 * Limpia el rut de caracteres extras
	 * 
	 * @param rutLimpia
	 * @return
	 */
	public static String limpiaRut(String rutLimpia) {

		String sinPunto = rutLimpia.replace(".", "");
		return sinPunto.replace("-", "");

	}

	/**
	 * Convierte una fecha de tipo java.sql.Date en java.time.LocalDate
	 * 
	 * @param dateToConvert
	 * @return LocalDate
	 */
	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toLocalDate();
	}

	/**
	 * Convierte una fecha en formato string con un patron compatible con
	 * <strong><a href=
	 * "https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html">DateTimeFormatter</a></strong>
	 * en una fecha de tipo LocalDate <br>
	 * 
	 * @param fecha
	 * @return LocalDate
	 */
	public static LocalDate convertStringToLocalDate(String fecha, String patron) throws DateTimeParseException {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patron);
			return LocalDate.parse(fecha, formatter);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Convierte una fecha en formato String en una fecha de tipo java.sql.Date con
	 * un patron compatible con <strong><a href=
	 * "https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html">SimpleDateFormat</a></strong>
	 * 
	 * @param fecha
	 * @param patron
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Date convertStringToDateSql(String fecha, String patron) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(patron);
		java.util.Date parsed = format.parse(fecha);
		return new java.sql.Date(parsed.getTime());
	}

	public static String convertDateToString(Date fecha, String patron) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		// to convert Date to String, use format method of SimpleDateFormat class.
		String strDate = dateFormat.format(fecha);

		return strDate;
	}

	/**
	 * Obtiene el rut del cliente desde el header del request.
	 * 
	 * @param httpServletRequest
	 * @return
	 */
	public static String obtenerRutDeRequest(HttpServletRequest httpServletRequest) {
		return httpServletRequest.getHeader("clienteid");
	}

	/**
	 * Convierte un fecha de tipo LocalDateTime a una cadena string <a href=
	 * "http://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html">Documentacion
	 * </a>
	 * 
	 * @param fecha
	 * @return
	 */
	public static String convertirLocalDateTimeAString(LocalDateTime fecha) throws DateTimeException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy HH:mm");
		String formattedString = fecha.format(formatter);
		return formattedString;

	}

	/**
	 * Convierte un fecha de tipo LocalDate a una cadena string <a href=
	 * "http://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html">Documentacion
	 * </a>
	 * 
	 * @param fecha
	 * @return
	 */
	public static String convertirLocalDateAString(LocalDate fecha) throws DateTimeException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
		String formattedString = fecha.format(formatter);
		return formattedString;

	}

	/**
	 * 
	 * @param fechaFomateada
	 * @param fechaDb
	 * @return
	 * @throws ParseException
	 */
	public static String formatoFecha(String fechaFomateada, String fechaDb) throws ParseException {
		if (!fechaDb.isEmpty()) {
			SimpleDateFormat format = null;
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			java.util.Date fecha = format.parse(fechaDb);

			format = new SimpleDateFormat("dd/MM/yyyy");
			fechaFomateada = format.format(fecha);

		}
		return fechaFomateada;
	}

	/**
	 * 
	 * @param fechaFomateada
	 * @param fechaDb
	 * @return
	 * @throws ParseException
	 */
	public static String formatoFechaString(String fechaFomateada, String fechaDb) throws ParseException {
		if (!fechaDb.isEmpty()) {
			SimpleDateFormat format = null;
			format = new SimpleDateFormat("yyyymmdd");
			java.util.Date fecha = format.parse(fechaDb);

			format = new SimpleDateFormat("dd/MM/yyyy");
			fechaFomateada = format.format(fecha);

		}
		return fechaFomateada;
	}

	/**
	 * 
	 * @param fechaFomateada
	 * @param fechaDb
	 * @return
	 * @throws ParseException
	 */
	public static String formatoFechaGuion(String fechaFomateada, String fechaDb) throws ParseException {
		if (!fechaDb.isEmpty()) {
			SimpleDateFormat format = null;
			format = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date fecha = format.parse(fechaDb);

			format = new SimpleDateFormat("dd/MM/yyyy");
			fechaFomateada = format.format(fecha);

		}
		return fechaFomateada;
	}

	/**
	 * Genera clave aleatoria para contraseña temporal
	 * 
	 * @return
	 */
	public static String calveAleatoria() {
		int n = 99999999;
		int numero;
		numero = (int) (Math.random() * n) + 1;
		String claveFinal = String.valueOf(numero);

		return claveFinal;
	}

	/**
	 * Genera nombre aleatorio para PDF con contraseña encriptada
	 * @return
	 */
	public static String nombrePDF() {
		String nombrePDF = java.util.UUID.randomUUID().toString();
		log.info("Utiles - nombrePDF(): Nombre aleatorio de archivo PDF: " + nombrePDF);
		return nombrePDF;
	}

}
