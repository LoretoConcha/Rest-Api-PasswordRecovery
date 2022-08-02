package recoverpass.apis.mgr;

import recoverpass.apis.adapters.DtoRequestDatosUsuario;
import recoverpass.apis.adapters.DtoRequestPreguntasSecretas;
import recoverpass.apis.adapters.DtoRequestRecuperarClaveTemporal;
import recoverpass.apis.adapters.DtoRequestRecuperarReintentos;
import recoverpass.apis.adapters.DtoRequestTienePreguntasSecretas;
import recoverpass.apis.adapters.DtoRequestValidaPreguntas;
import recoverpass.apis.adapters.SalidaDatosUsuario;
import recoverpass.apis.adapters.SalidaPreguntasSecretas;
import recoverpass.apis.adapters.SalidaRecuperarClaveTemporal;
import recoverpass.apis.adapters.SalidaRecuperarReintentos;
import recoverpass.apis.adapters.SalidaTienePreguntasSecretas;
import recoverpass.apis.adapters.SalidaValidaPreguntas;

/**
 * Interface para servicio de obtener recuros de Recuperar Contraseña
 * 
 * @author lconcha
 *
 */
public interface RecoverPasswordMgr {

	/**
	 * Valida datos de usuario in rutCliente out NombreUsuario, ApellidoUsuario,
	 * @param dtoRequestDatosUsuario
	 * @return
	 */
	SalidaDatosUsuario datosUsuario(DtoRequestDatosUsuario dtoRequestDatosUsuario);
	/**
	 * Obtiene y setea cantidad de reintentos para responder preguntas secretas
	 * proceso 1= verificacion, 2=respuestas equivocadas (se incrementa en uno el
	 * reintento)
	 * @param dtoRequestRecuperarReintentos
	 * @return
	 */
	SalidaRecuperarReintentos recuperarReintentos(DtoRequestRecuperarReintentos dtoRequestRecuperarReintentos);

	/**
	 * Obtiene tienePreguntas=1 si usuario tiene asociadas preguntas secretas, de lo
	 * contrario, no tiene.
	 * @param dtoRequestTienePreguntasSecretas
	 * @return
	 */
	SalidaTienePreguntasSecretas tienePreguntasSecretas(
			DtoRequestTienePreguntasSecretas dtoRequestTienePreguntasSecretas);

	/**
	 * En caso de que usuario tenga asociadas preguntas secretas se obtiene:
	 * pregunta, id, respuesta, idUsuario
	 * @param dtoRequestPreguntasSecretas
	 * @return
	 */
	SalidaPreguntasSecretas obtenerPreguntasSecretas(DtoRequestPreguntasSecretas dtoRequestPreguntasSecretas);
	/**
	 *  Valida la respuesta a las preguntas secretas
	 * @param dtoRequestValidaPreguntas
	 * @return
	 */
	SalidaValidaPreguntas validaPreguntas(DtoRequestValidaPreguntas dtoRequestValidaPreguntas);

	/**
	 * Recurso encargado de obtener clave temporal, validarla con la ingresada por el usuario e insertar nueva clave ingresada por usuario luego de haber sido encriptada
	 * @param dtoRequestRecuperarClaveTemporal
	 * @return
	 */
	SalidaRecuperarClaveTemporal recoverPassTemp(
			DtoRequestRecuperarClaveTemporal dtoRequestRecuperarClaveTemporal);

}