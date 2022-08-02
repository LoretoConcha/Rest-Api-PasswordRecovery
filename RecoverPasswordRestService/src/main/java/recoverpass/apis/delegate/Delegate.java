/**
 * 
 */
package recoverpass.apis.delegate;

import javax.servlet.http.HttpServletRequest;

import recoverpass.apis.adapters.DtoRequestDatosUsuario;
import recoverpass.apis.adapters.DtoRequestLogin;
import recoverpass.apis.adapters.DtoRequestPreguntasSecretas;
import recoverpass.apis.adapters.DtoRequestRecuperarClaveTemporal;
import recoverpass.apis.adapters.DtoRequestRecuperarReintentos;
import recoverpass.apis.adapters.DtoRequestTienePreguntasSecretas;
import recoverpass.apis.adapters.DtoRequestValidaPreguntas;
import recoverpass.apis.adapters.SalidaAutenticacionCliente;
import recoverpass.apis.adapters.SalidaDatosUsuario;
import recoverpass.apis.adapters.SalidaPreguntasSecretas;
import recoverpass.apis.adapters.SalidaRecuperarClaveTemporal;
import recoverpass.apis.adapters.SalidaRecuperarReintentos;
import recoverpass.apis.adapters.SalidaTienePreguntasSecretas;
import recoverpass.apis.adapters.SalidaValidaPreguntas;
import recoverpass.apis.mgr.ILogin;
import recoverpass.apis.mgr.RecoverPasswordMgr;
import recoverpass.apis.mgr.impl.LoginMgrImpl;
import recoverpass.apis.mgr.impl.RecoverPasswordMgrImpl;

/**
 * Permite hacer contacto entre la capa de presentación y las capas inferiores
 * para el acceso a informacion de recuperar contraseña
 * 
 * @author lconcha
 *
 */

public class Delegate {

	private RecoverPasswordMgr negocio;
	
	public SalidaDatosUsuario datosUsuario(DtoRequestDatosUsuario dtoRequestDatosUsuario) {
		this.negocio = new RecoverPasswordMgrImpl();
		return this.negocio.datosUsuario(dtoRequestDatosUsuario);

	}
	/**
	 * 
	 * @param dtoRequestRecuperarReintentos
	 * @return
	 */
	public SalidaRecuperarReintentos recuperarReintentos(DtoRequestRecuperarReintentos dtoRequestRecuperarReintentos) {
		this.negocio = new RecoverPasswordMgrImpl();
		return this.negocio.recuperarReintentos(dtoRequestRecuperarReintentos);
	}

	/**
	 * 
	 * @param dtoRequestTienePreguntasSecretas
	 * @return
	 */
	public SalidaTienePreguntasSecretas tienePreguntasSecretas(
			DtoRequestTienePreguntasSecretas dtoRequestTienePreguntasSecretas) {
		this.negocio = new RecoverPasswordMgrImpl();
		return this.negocio.tienePreguntasSecretas(dtoRequestTienePreguntasSecretas);
	}

	/**
	 * 
	 * @param dtoRequestCliente
	 * @return
	 */
	public SalidaPreguntasSecretas obtenerPreguntasSecretas(DtoRequestPreguntasSecretas dtoRequestPreguntasSecretas) {
		this.negocio = new RecoverPasswordMgrImpl();
		return this.negocio.obtenerPreguntasSecretas(dtoRequestPreguntasSecretas);
	}
	/**
	 * 
	 * @param dtoRequestValidaPreguntas
	 * @return
	 */
	public SalidaValidaPreguntas validaPreguntas(DtoRequestValidaPreguntas dtoRequestValidaPreguntas) {
		this.negocio = new RecoverPasswordMgrImpl();
		return this.negocio.validaPreguntas(dtoRequestValidaPreguntas);
	}

	/**
	 * 
	 * @param dtoRequestRecuperarClaveTemporal
	 * @return
	 */
	public SalidaRecuperarClaveTemporal recoverPassTemp(
			DtoRequestRecuperarClaveTemporal dtoRequestRecuperarClaveTemporal) {
		this.negocio = new RecoverPasswordMgrImpl();
		return this.negocio.recoverPassTemp(dtoRequestRecuperarClaveTemporal);
	}
	
	
	/**
	 * Permite autenticar a un cliente
	 * 
	 * @param httpServletRequest
	 * @param dtoCredenciales
	 * @return SalidaAutenticacionCliente
	 */
	public SalidaAutenticacionCliente autenticarCliente(HttpServletRequest httpServletRequest,
			DtoRequestLogin dtoCredenciales) {
		ILogin autenticar = new LoginMgrImpl();
		return autenticar.autenticarCliente(httpServletRequest, dtoCredenciales);
	}
	
	
}
