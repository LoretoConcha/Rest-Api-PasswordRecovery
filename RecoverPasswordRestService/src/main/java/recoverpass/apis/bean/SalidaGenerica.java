package recoverpass.apis.bean;

import recoverpass.apis.adapters.DtoResponseCodigosEstadoHttp;

/**
 * Permite obtener la salida generica para los servicios
 * 
 *
 */
public class SalidaGenerica {

	/**
	 * Fields
	 */
	private DtoResponseCodigosEstadoHttp dtoResponseCodigosEstadoHttp;
	private DtoResponseSetParametros dtoResponseSetParametros;

	/** GET Y SET **/
	public DtoResponseCodigosEstadoHttp getDtoResponseCodigosEstadoHttp() {
		return dtoResponseCodigosEstadoHttp;
	}

	public void setDtoResponseCodigosEstadoHttp(DtoResponseCodigosEstadoHttp dtoResponseCodigosEstadoHttp) {
		this.dtoResponseCodigosEstadoHttp = dtoResponseCodigosEstadoHttp;
	}

	public DtoResponseSetParametros getDtoResponseSetParametros() {
		return dtoResponseSetParametros;
	}

	public void setDtoResponseSetParametros(DtoResponseSetParametros dtoResponseSetParametros) {
		this.dtoResponseSetParametros = dtoResponseSetParametros;
	}
}
