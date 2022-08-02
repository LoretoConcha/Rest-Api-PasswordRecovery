package recoverpass.apis.adapters;
/**
 * 
 */

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Salida estandar para la ejecucion de procedimientos que retornan solo un
 * codigo de operacion
 * 
 *
 */
public class SalidaEstandarEjecutarOperacion {

	@JsonProperty("dtoResponseCodigosEstadoHttp")
	private DtoResponseCodigosEstadoHttp dtoResponseCodigosEstado;

	private DtoResponseCodigosOperacion dtoResponseCodigosOperacion;

	public SalidaEstandarEjecutarOperacion() {
		this.dtoResponseCodigosEstado = new DtoResponseCodigosEstadoHttp();
		this.dtoResponseCodigosOperacion = new DtoResponseCodigosOperacion();
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

	/**
	 * @return the dtoResponseCodigosOperacion
	 */
	public DtoResponseCodigosOperacion getDtoResponseCodigosOperacion() {
		return this.dtoResponseCodigosOperacion;
	}

	/**
	 * @param dtoResponseCodigosOperacion
	 *            the dtoResponseCodigosOperacion to set
	 */
	public void setDtoResponseCodigosOperacion(DtoResponseCodigosOperacion dtoResponseCodigosOperacion) {
		this.dtoResponseCodigosOperacion = dtoResponseCodigosOperacion;
	}

}
