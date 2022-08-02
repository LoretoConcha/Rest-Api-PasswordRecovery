/**
 * 
 */
package recoverpass.apis.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Salida para los codigos de estado de solicitudes http
 * 
 *
 */
public class SalidaResponseCodigosEstado {

	@JsonProperty("dtoResponseCodigosEstadoHttp")
	private DtoResponseCodigosEstadoHttp dtoResponseCodigosEstado;

	public SalidaResponseCodigosEstado() {
		this.dtoResponseCodigosEstado = new DtoResponseCodigosEstadoHttp();
	}

	/**
	 * @return the dtoResponseCodigosEstado
	 */
	public DtoResponseCodigosEstadoHttp getDtoResponseCodigosEstado() {
		return this.dtoResponseCodigosEstado;
	}

	/**
	 * @param dtoResponseCodigosEstado
	 *            the dtoResponseCodigosEstado to set
	 */
	public void setDtoResponseCodigosEstado(DtoResponseCodigosEstadoHttp dtoResponseCodigosEstado) {
		this.dtoResponseCodigosEstado = dtoResponseCodigosEstado;
	}

}
