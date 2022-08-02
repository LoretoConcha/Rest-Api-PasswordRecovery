package recoverpass.apis.rest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
import recoverpass.apis.delegate.Delegate;
import recoverpass.apis.filters.ValidaIntegridadRequest;
import recoverpass.apis.helper.Constantes;

/**
 * Punto de entrada para servicios correspondientes a recuperar contraseña
 * 
 * @author lconcha
 *
 */
@Path("RecuperarContrasena")
@ValidaIntegridadRequest
public class RecoverPassword {


	/**
	 * 
	 * @param dtoRequestDatosUsuario
	 * @return
	 */
	@POST
	@Path("DatosUsuario")
	@Produces(MediaType.APPLICATION_JSON + Constantes.CHARSET_UTF8)
	@Consumes(MediaType.APPLICATION_JSON)
	public SalidaDatosUsuario datosUsuario(DtoRequestDatosUsuario dtoRequestDatosUsuario) {
		Delegate delegate = new Delegate();
		return delegate.datosUsuario(dtoRequestDatosUsuario);
	}

	/**
	 * 
	 * @param dtoRequestRecuperarReintentos
	 * @return
	 */
	@POST
	@Path("RecuperarReintentos")
	@Produces(MediaType.APPLICATION_JSON + Constantes.CHARSET_UTF8)
	@Consumes(MediaType.APPLICATION_JSON)
	public SalidaRecuperarReintentos recuperarReintentos(DtoRequestRecuperarReintentos dtoRequestRecuperarReintentos) {
		Delegate delegate = new Delegate();
		return delegate.recuperarReintentos(dtoRequestRecuperarReintentos);
	}

	/**
	 * 
	 * @param dtoRequestTienePreguntasSecretas
	 * @return
	 */
	@POST
	@Path("TienePreguntasSecretas")
	@Produces(MediaType.APPLICATION_JSON + Constantes.CHARSET_UTF8)
	@Consumes(MediaType.APPLICATION_JSON)
	public SalidaTienePreguntasSecretas tienePreguntasSecretas(
			DtoRequestTienePreguntasSecretas dtoRequestTienePreguntasSecretas) {
		Delegate delegate = new Delegate();
		return delegate.tienePreguntasSecretas(dtoRequestTienePreguntasSecretas);
	}

	/**
	 * 
	 * @param dtoRequestPreguntasSecretas
	 * @return
	 */
	@POST
	@Path("PreguntasSecretas")
	@Produces(MediaType.APPLICATION_JSON + Constantes.CHARSET_UTF8)
	@Consumes(MediaType.APPLICATION_JSON)
	public SalidaPreguntasSecretas obtenerPreguntasSecretas(DtoRequestPreguntasSecretas dtoRequestPreguntasSecretas) {
		Delegate delegate = new Delegate();
		return delegate.obtenerPreguntasSecretas(dtoRequestPreguntasSecretas);
	}

	/**
	 * 
	 * @param dtoRequestValidaPreguntas
	 * @return
	 */
	@POST
	@Path("ValidaPreguntas")
	@Produces(MediaType.APPLICATION_JSON + Constantes.CHARSET_UTF8)
	@Consumes(MediaType.APPLICATION_JSON)
	public SalidaValidaPreguntas validaPreguntas(DtoRequestValidaPreguntas dtoRequestValidaPreguntas) {
		Delegate delegate = new Delegate();
		return delegate.validaPreguntas(dtoRequestValidaPreguntas);
	}

	/**
	 * 
	 * @param dtoRequestRecuperarClaveTemporal
	 * @return
	 */
	@POST
	@Path("RecuperarClaveTemporal")
	@Produces(MediaType.APPLICATION_JSON + Constantes.CHARSET_UTF8)
	@Consumes(MediaType.APPLICATION_JSON)
	public SalidaRecuperarClaveTemporal recoverPassTemp(
			DtoRequestRecuperarClaveTemporal dtoRequestRecuperarClaveTemporal) {
		Delegate delegate = new Delegate();
		return delegate.recoverPassTemp(dtoRequestRecuperarClaveTemporal);
	}

}
