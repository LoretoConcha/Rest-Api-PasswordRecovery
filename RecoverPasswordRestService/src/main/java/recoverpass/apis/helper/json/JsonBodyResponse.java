package recoverpass.apis.helper.json;

import recoverpass.apis.adapters.DtoResponseCodigosOperacion;
import recoverpass.apis.bean.DtoResponseAutenticacion;
import recoverpass.apis.bean.DtoResponseSetParametros;
import recoverpass.apis.helper.Constantes;

/**
 * Resultados para el cuerpo del response de los servicios
 * 
 *
 */
public class JsonBodyResponse {

	/**
	 * Construye un objeto DTO para la respues de ejecucion de eperacion exitosa
	 * 
	 * @return
	 */
	public static DtoResponseCodigosOperacion operacionOk() {
		DtoResponseCodigosOperacion dto = new DtoResponseCodigosOperacion();
		dto.setCodigoRespuesta("0");
		dto.setMensaje("Operación ejecutada exitosamente");
		return dto;
	}
	/**
	 * Construye un objeto DTO para la respues de ejecucion de eperacion exitosa
	 * 
	 * @return
	 */
	public static DtoResponseCodigosOperacion cambioClaveOk() {
		DtoResponseCodigosOperacion dto = new DtoResponseCodigosOperacion();
		dto.setCodigoRespuesta("0");
		dto.setMensaje("Cambio de clave exitoso");
		return dto;
	}

	/**
	 * Construye un objeto DTO para la respuesta de una ejecucion de eperacion
	 * fallida
	 * 
	 * @return
	 */
	public static DtoResponseCodigosOperacion errorOperacion() {
		DtoResponseCodigosOperacion dto = new DtoResponseCodigosOperacion();
		dto.setCodigoRespuesta("1");
		dto.setMensaje("Servicio no disponible");
		return dto;
	}
	
	/**
	 * Sin data salida
	 * 
	 * @return
	 */
	public static DtoResponseSetParametros sinAutorizacionMetadata() {

		DtoResponseSetParametros res = new DtoResponseSetParametros();

		res.setCodigoError("104");
		res.setMsjError("METADATA NO AUTORIZADA");

		return res;

	}
	/**
	 * Construye un objeto DTO para la respues de ejecucion de eperacion exitosa
	 * 
	 * @return
	 */
	public static DtoResponseCodigosOperacion errorRegistro() {
		DtoResponseCodigosOperacion dto = new DtoResponseCodigosOperacion();
		dto.setCodigoRespuesta("0");
		dto.setMensaje("Rut inexistente");
		return dto;
	}
	public static DtoResponseCodigosOperacion errorPreguntas() {
		DtoResponseCodigosOperacion dto = new DtoResponseCodigosOperacion();
		dto.setCodigoRespuesta("0");
		dto.setMensaje("Respuestas no coinciden");
		return dto;
	}
	
	public static DtoResponseCodigosOperacion intentosExcedido() {
		DtoResponseCodigosOperacion dto = new DtoResponseCodigosOperacion();
		dto.setCodigoRespuesta("0");
		dto.setMensaje("Maximo de intentos excedido");
		return dto;
	}
	
	public static DtoResponseCodigosOperacion nuevaPassError() {
		DtoResponseCodigosOperacion dto = new DtoResponseCodigosOperacion();
		dto.setCodigoRespuesta("2");
		dto.setMensaje("Las contraseñas no coinciden");
		return dto;
	}
	public static DtoResponseCodigosOperacion claveTemporalIncorrecta() {
		DtoResponseCodigosOperacion dto = new DtoResponseCodigosOperacion();
		dto.setCodigoRespuesta("1");
		dto.setMensaje("Clave temporal incorrecta");
		return dto;
	}
	
	//Login
	
	/**
	 * Mensaje para un login incorrecto
	 * 
	 * @return
	 */
	public static DtoResponseAutenticacion requestLoginInvalido() {

		DtoResponseAutenticacion res = new DtoResponseAutenticacion();

		res.setCodigo(0);
		res.setEstado(Constantes.ERROR);
		res.setDescripcion("CREDENCIALES INVALIDAS");
		res.setDtoAutorizacion(null);
		return res;

	}
	
	/**
	 * Usuario que se esta logeando esta bloqueado
	 * 
	 * @return
	 */
	public static DtoResponseAutenticacion requestLoginUsuarioBloqueado() {

		DtoResponseAutenticacion res = new DtoResponseAutenticacion();

		res.setCodigo(2);
		res.setEstado(Constantes.ERROR);
		res.setDescripcion("USUARIO BLOQUEADO");
		res.setDtoAutorizacion(null);
		return res;

	}
	
	/**
	 * Usuario que se esta logeando no posee empresa.
	 * 
	 * @return
	 */
	public static DtoResponseAutenticacion requestLoginUsuarioNoPoseeEmpresa() {

		DtoResponseAutenticacion res = new DtoResponseAutenticacion();

		res.setCodigo(3);
		res.setEstado(Constantes.ERROR);
		res.setDescripcion("USUARIO NO POSEE EMPRESA");
		res.setDtoAutorizacion(null);
		return res;

	}
	
	/**
	 * Usuario que esta logeando accede por primera ves al sistema.
	 * 
	 * @return
	 */
	public static DtoResponseAutenticacion requestLoginPrimerAccesoUsuario() {

		DtoResponseAutenticacion res = new DtoResponseAutenticacion();

		res.setCodigo(4);
		res.setEstado("OK");
		res.setDescripcion("PRIMER ACCESO DEL USUARIO");
		res.setDtoAutorizacion(null);
		return res;

	}
}
