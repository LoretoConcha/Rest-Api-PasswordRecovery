
package recoverpass.apis.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;

import recoverpass.apis.helper.Constantes;

/**
 * DTO de entrada para la autenticacion de clientes
 * 
 *
 */

public class DtoRequestLogin {

	@JsonProperty(Constantes.DTO_REQUEST_SET_PARAMETROS)
	private DtoRequestSetParametrosLogin dtoRequestSetParametros;

	public DtoRequestLogin() {
		this.dtoRequestSetParametros = new DtoRequestSetParametrosLogin();
	}

	/**
	 * @return the dtoRequestSetParametros
	 */
	public DtoRequestSetParametrosLogin getDtoRequestSetParametros() {
		return this.dtoRequestSetParametros;
	}

	/**
	 * @param dtoRequestSetParametros the dtoRequestSetParametros to set
	 */
	public void setDtoRequestSetParametros(DtoRequestSetParametrosLogin dtoRequestSetParametros) {
		this.dtoRequestSetParametros = dtoRequestSetParametros;
	}

}
