/**
 * 
 */
package recoverpass.apis.helper.json;

import recoverpass.apis.adapters.DtoResponseCodigosEstadoHttp;
import recoverpass.apis.helper.Constantes;

/**
 * Clase con mensajes de respuesta HTTP para informar a los clientes sobre el
 * resultado general de su solicitud.
 * 
 * 
 *
 */
public class JsonHeaderResponse {


	/**
	 * Retorna error cuando la lista de respuesta no contiene datos.
	 * 
	 * @return
	 */
	public static DtoResponseCodigosEstadoHttp sinCoincidencias() {

		DtoResponseCodigosEstadoHttp res = new DtoResponseCodigosEstadoHttp();

		res.setCodigo("204");
		res.setMensaje("NO CONTENT");
		res.setDescripcion("No se han encontrado coincidencias para los datos ingresados");

		return res;

	}

	/**
	 * Todo OK
	 * 
	 * @return
	 */
	public static DtoResponseCodigosEstadoHttp requestOK() {

		DtoResponseCodigosEstadoHttp res = new DtoResponseCodigosEstadoHttp();

		res.setCodigo("200");
		res.setMensaje("OK");
		res.setDescripcion("OK");

		return res;

	}

	/**
	 * Bad request
	 * 
	 * @return
	 */
	public static DtoResponseCodigosEstadoHttp dataIncorrecta() {

		DtoResponseCodigosEstadoHttp res = new DtoResponseCodigosEstadoHttp();

		res.setCodigo("400");
		res.setMensaje("BAD REQUEST");
		res.setDescripcion("ERROR");

		return res;

	}

	/**
	 * Error ejecucion
	 * 
	 * @return
	 */
	public static DtoResponseCodigosEstadoHttp errorInternoDelServidor() {

		DtoResponseCodigosEstadoHttp res = new DtoResponseCodigosEstadoHttp();

		res.setCodigo("500");
		res.setMensaje("INTERNAL SERVER ERROR");
		res.setDescripcion("ERROR");

		return res;

	}
	/**
	 * Unauthorized
	 * 
	 * @return
	 */
	public static DtoResponseCodigosEstadoHttp noAutorizada() {

		DtoResponseCodigosEstadoHttp res = new DtoResponseCodigosEstadoHttp();

		res.setCodigo("401");
		res.setMensaje("Unauthorized");
		res.setDescripcion("Solicitud no autorizada");

		return res;

	}

}
