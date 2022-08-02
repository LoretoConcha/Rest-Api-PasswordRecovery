/**
 * 
 */
package recoverpass.apis.adapters;

/**
 * DTO para los servicios que ejecutan procedures con codigos de respuesta
 * 
 *
 */
public class DtoResponseCodigosOperacion {

	private String codigoRespuesta;
	private String mensaje;

	public DtoResponseCodigosOperacion() {

	}

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	


}
