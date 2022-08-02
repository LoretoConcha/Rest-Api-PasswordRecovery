
package recoverpass.apis.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;

import recoverpass.apis.helper.Constantes;

/**
 *
 */
public class SalidaAutenticacionCliente {

	@JsonProperty(Constantes.DTO_RESPONSE_CODIGOS_ESTADO_HTTP)
	private DtoResponseCodigosEstadoHttp dtoResponseCodigosEstadoHttp;
	
	@JsonProperty(Constantes.DTO_RESPONSE_SET_RESULTADOS)
	private DtoResponseSetResultadosAutenticacionCliente dtoResponseSetResultados;

	public SalidaAutenticacionCliente() {
		this.dtoResponseCodigosEstadoHttp = new DtoResponseCodigosEstadoHttp();
		this.dtoResponseSetResultados = new DtoResponseSetResultadosAutenticacionCliente();
	}

	/**
	 * @return the dtoResponseSetResultados
	 */
	public DtoResponseSetResultadosAutenticacionCliente getDtoResponseSetResultados() {
		return this.dtoResponseSetResultados;
	}

	/**
	 * @param dtoResponseSetResultados
	 *            the dtoResponseSetResultados to set
	 */
	public void setDtoResponseSetResultados(DtoResponseSetResultadosAutenticacionCliente dtoResponseSetResultados) {
		this.dtoResponseSetResultados = dtoResponseSetResultados;
	}
	/**
	 * 
	 * @return
	 */
	public DtoResponseCodigosEstadoHttp getDtoResponseCodigosEstadoHttp() {
		return this.dtoResponseCodigosEstadoHttp;
	}
	/**
	 * 
	 * @param dtoResponseCodigosEstadoHttp
	 */
	public void setDtoResponseCodigosEstadoHttp(DtoResponseCodigosEstadoHttp dtoResponseCodigosEstadoHttp) {
		this.dtoResponseCodigosEstadoHttp = dtoResponseCodigosEstadoHttp;
	}



}
