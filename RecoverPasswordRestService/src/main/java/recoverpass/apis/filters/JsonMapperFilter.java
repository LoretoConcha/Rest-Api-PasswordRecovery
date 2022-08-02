/**
 * 
 */
package recoverpass.apis.filters;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import recoverpass.apis.adapters.SalidaResponseCodigosEstado;
import recoverpass.apis.helper.json.JsonHeaderResponse;

/**
 * Filtra todos los request que se emiten a los servicios.
 * 
 *
 */
@Provider
public class JsonMapperFilter implements ExceptionMapper<Throwable> {

	/**
	 * LOGGER
	 */
	private static final Logger log = Logger.getLogger(JsonMapperFilter.class);

	/**
	 * Verifica que el cuerpo de la solictud sea consistente con el objeto DTO
	 * que se esta mapeando
	 */
	@Override
	public Response toResponse(Throwable exception) {
		log.error("El cuerpo de la solicitud es incompatible con el objeto DTO de destino, ERROR = "
				+ exception.getMessage());
		SalidaResponseCodigosEstado salida = new SalidaResponseCodigosEstado();
		salida.setDtoResponseCodigosEstado(JsonHeaderResponse.dataIncorrecta());
		Response response = Response.ok().entity(salida).type(MediaType.APPLICATION_JSON).build();
		return response;
	}

}
