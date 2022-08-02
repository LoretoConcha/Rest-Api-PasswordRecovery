
package recoverpass.apis.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;

import recoverpass.apis.bean.DtoCredenciales;

/**
 *
 */
public class DtoRequestSetParametrosLogin {

	@JsonProperty("dtoCredenciales")
	private DtoCredenciales dtoCredenciales;

	/**
	 * @return the dtoCredenciales
	 */
	public DtoCredenciales getDtoCredenciales() {
		return this.dtoCredenciales;
	}

	/**
	 * @param dtoCredenciales the dtoCredenciales to set
	 */
	public void setDtoCredenciales(DtoCredenciales dtoCredenciales) {
		this.dtoCredenciales = dtoCredenciales;
	}

}
