package recoverpass.apis.mgr;

import javax.servlet.http.HttpServletRequest;

import recoverpass.apis.adapters.DtoRequestLogin;
import recoverpass.apis.adapters.SalidaAutenticacionCliente;

/**
 * Clase que permite servir como capa intermedia
 * 
 *
 */
public interface ILogin {

	/**
	 * Permite autenticar a un cliente. En caso que el cliente sea válido, se
	 * genera un nuevo token para las posteriores llamadas a los servicios REST.
	 * 
	 * @param httpServletRequest
	 * @param dtoCredenciales
	 * @return SalidaAutenticacionCliente
	 */
	SalidaAutenticacionCliente autenticarCliente(HttpServletRequest httpServletRequest,
			DtoRequestLogin dtoCredenciales);

	

}