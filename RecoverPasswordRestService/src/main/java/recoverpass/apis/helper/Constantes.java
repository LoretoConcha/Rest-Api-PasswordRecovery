package recoverpass.apis.helper;

import recoverpass.apis.res.Resources;

/**
 * Constantes para este servicio
 * 
 *
 */
public class Constantes {

	/**
	 * Archivo de parametros
	 */
	public static final String ARCHIVO_PALABRAS_RESERVADAS = "EH_PALABRAS_NOAUTORIZADAS.json";

	/**
	 * Variables desde las properties
	 */
	public static final String DATASOURCE_SQLSERVER;
	public static final String DATASOURCE_SQLSERVER2;
	public static final String DATASOURCE_ORACLE_METADATA;

	public static final String VERSION_SERVICIO;
	public static final String CHARSET_UTF8 = ";charset=utf-8";
	public static final String PATRON_FECHA_DEFAULT = "dd/MM/yyyy";
	public static final String PATRON_FECHA_STRING = "yyyyMMdd";
	public static final int codigoMetadataFiltroCUENTA = 1;
	public static final int NUMEROPROCESO = 1;
	public static final String NOMBRE_PLANTILLA = "template_recoverpass.html";
	public static final String EXTENSION_PDF = ".pdf";
	public static final String EXTENSION_PDF_PASS = "_pass.pdf";
	public static final String TIPO_ENVIO="17";
	public static final String MODO_UTF8="UTF-8";
	public static final String DTO_RESPONSE_CODIGOS_ESTADO_HTTP = "dtoResponseCodigosEstadoHttp";
	public static final String DTO_REQUEST_SET_PARAMETROS = "dtoRequestSetParametros";
	public static final String DTO_RESPONSE_CODIGOS_OPERACION = "dtoResponseCodigosOperacion";
	public static final String DTO_RESPONSE_SET_RESULTADOS = "dtoResponseSetResultados";
	public static final String UTF_8 = "UTF-8";
	public static final String CIPHER_AES = "AES/CBC/PKCS5PADDING";
	public static final String AES = "AES";
	public static final String SHA_512 = "SHA-512";
	public static final String USUARIO = "USUARIO ";

	public static final String MODALIDAD = "modalidad";
	public static final String CODIGO_CANAL = "codigoCanal";
	public static final String EMPRESA_APLICACION = "empresaAplicacion";
	public static final String CODIGO_APLICACION = "codigoAplicacion";
	public static final String IP_CLIENTE = "ipCliente";
	public static final String USER_ID = "userID";
	public static final String CLIENT_ID = "clientid";
	public static final String REQUEST_ID = "requestid";
	public static final String ERROR = "Error ";
	public static final String ERROR_EN_LA_EJECUCION = "Ha ocurrido un error en la ejecucion ";

	
	/**
	 * Endpoint para el servicio de Mensajeria
	 */
	public static final String ENDPOINT_SERVICIO_MENSAJERIA;
	/**
	 * Repuestas
	 */
	public static final String RES_ERROR_TOKEN = "TOKEN INVALIDO, SIN AUTORIZACION";

	/**
	 * Procedure que obtiene el estado de la contraseña y si existe el usuario
	 */
	public static final String POCEDURE_DATOSUSUARIO;
	/**
	 * Procedure recuperar la cantidad de reintentos para responder preguntas
	 * secretas
	 */
	public static final String POCEDURE_RECUPERAREINTENTOS;
	/**
	 * Procedure para verificar si preguntas secretas
	 */
	public static final String POCEDURE_TIENEPREGUNTAS;
	/**
	 * Procedure para obtener preguntas secretas
	 */
	public static final String POCEDURE_OBTENERPREGUNTAS;
	/**
	 * Procedure valida las preguntas enviadas por usuario
	 */
	public static final String POCEDURE_VALIDARPREGUNTAS;
	/**
	 * Procedure lista claves temporales asociadas a un rut
	 */
	public static final String POCEDURE_LISTARCLAVESTEMP;
	/**
	 * Procedure para setear clave temporal en el caso que haya respondido de manera
	 * correcta las preguntas
	 */
	public static final String POCEDURE_INSERTACLAVETEMPORAL;
	/**
	 * Procedure para obtener clave temporal
	 */
	public static final String POCEDURE_RECUPERARCLAVETEMPORAL;
	/**
	 * Procedure para ANULAR clave temporal
	 */
	public static final String POCEDURE_ADDINTENTOSCLAVETEMPORAL;
	/**
	 * Procedure para actualizar clave temporal a final
	 */
	public static final String POCEDURE_ACTUALIZARCLAVE;
	/**
	 * Procedure para ANULAR clave temporal
	 */
	public static final String POCEDURE_ANULARCLAVETEMPORAL;
	/**
	 * Valida definicion
	 */
	public static final String PROCEDURE_DEFINICION_VALIDA_METADATA;

	public static final String PATH_PDF_CLAVE;

	public static final String DIRECTORIO_PLANTILLA;
	/**
	 * KEY RECUPERAR CONTRASEÑA
	 */
	public static final String SALTRECUPERAR;
	


	/**
	 * Endpoint para el servicio de token
	 */
	public static final String ENDPOINT_SERVICIO_TOKEN;

	/**
	 * Procedure para autenticación de un cliente.
	 */
	public static final String PROCEDURE_AUTENTICACION_CLIENTE;

	/**
	 * Endpoint para el servicio que genera un nuevo token
	 */
	public static final String ENDPOINT_SERVICIO_GENERA_TOKEN;

	/**
	 * Obtiene la llave para encriptar/desencriptar con algoritmo AES
	 */
	public static final String AES_KEY;

	/**
	 * Obtiene el vector de inicializacion para encriptar/desencriptar con algoritmo
	 * AES
	 */
	public static final String INITIALIZATION_VECTOR;

	/**
	 * Obtiene el valor salt para la encriptacion SHA-512
	 */
	public static final String SALT;

	/**
	 * Especifica el directorio donde se ubica la llave privada RSA
	 */
	public static final String RUTA_LLAVE_PRIVADA;
	
	
	
	/**
	 * Constructor
	 */
	static {

		// Setear los datos desde las properties hacia las constantes
		DATASOURCE_SQLSERVER = Resources.getResource("DATASOURCE_SQLSERVER");
		DATASOURCE_SQLSERVER2= Resources.getResource("DATASOURCE_SQLSERVER2");
		VERSION_SERVICIO = Resources.getResource("VERSION_SERVICIO");
		DATASOURCE_ORACLE_METADATA = Resources.getResource("DATASOURCE_ORACLE_METADATA");

		// Endpoint para el servicio de token
		ENDPOINT_SERVICIO_TOKEN = Resources.getResource("ENDPOINT_SERVICIO_TOKEN");
		
		// Endpoint para el servicio que genera un nuevo token
		ENDPOINT_SERVICIO_GENERA_TOKEN = Resources.getResource("ENDPOINT_SERVICIO_GENERA_TOKEN");

		PROCEDURE_AUTENTICACION_CLIENTE = Resources.getResource("PROCEDURE_LOGIN");

		AES_KEY = Resources.getResource("AES_KEY");

		INITIALIZATION_VECTOR = Resources.getResource("INITIALIZATION_VECTOR");

		SALT = Resources.getResource("SALT");

		RUTA_LLAVE_PRIVADA = Resources.getResource("RUTA_LLAVE_PRIVADA");


		// Procedure correspondiente a recuperar contraseña
		POCEDURE_DATOSUSUARIO = Resources.getResource("POCEDURE_DATOSUSUARIO");
		POCEDURE_RECUPERAREINTENTOS = Resources.getResource("POCEDURE_RECUPERAREINTENTOS");
		POCEDURE_TIENEPREGUNTAS = Resources.getResource("POCEDURE_TIENEPREGUNTAS");
		POCEDURE_OBTENERPREGUNTAS = Resources.getResource("POCEDURE_OBTENERPREGUNTAS");
		POCEDURE_VALIDARPREGUNTAS = Resources.getResource("POCEDURE_VALIDARPREGUNTAS");
		POCEDURE_LISTARCLAVESTEMP = Resources.getResource("POCEDURE_LISTARCLAVESTEMP");
		POCEDURE_INSERTACLAVETEMPORAL = Resources.getResource("POCEDURE_INSERTACLAVETEMPORAL");
		POCEDURE_RECUPERARCLAVETEMPORAL = Resources.getResource("POCEDURE_RECUPERARCLAVETEMPORAL");
		POCEDURE_ACTUALIZARCLAVE = Resources.getResource("POCEDURE_ACTUALIZARCLAVE");
		POCEDURE_ANULARCLAVETEMPORAL = Resources.getResource("POCEDURE_ANULARCLAVETEMPORAL");
		PROCEDURE_DEFINICION_VALIDA_METADATA = Resources.getResource("PROCEDURE_SP_VALIDA_METADATA");
		POCEDURE_ADDINTENTOSCLAVETEMPORAL = Resources.getResource("POCEDURE_ADDINTENTOSCLAVETEMPORAL"); 
		PATH_PDF_CLAVE =Resources.getResource("PATH_PDF_CLAVE"); 
		DIRECTORIO_PLANTILLA=Resources.getResource("DIRECTORIO_PLANTILLA"); 
		ENDPOINT_SERVICIO_MENSAJERIA=Resources.getResource("ENDPOINT_SERVICIO"); 
		SALTRECUPERAR=Resources.getResource("SALTRECUPERAR"); 
	}

}
