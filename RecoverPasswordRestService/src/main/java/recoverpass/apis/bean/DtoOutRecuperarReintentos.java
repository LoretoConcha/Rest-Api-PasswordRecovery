/**
 * 
 */
package recoverpass.apis.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author lconcha
 *
 */

public class DtoOutRecuperarReintentos {
	
	@JsonProperty("reintentos")
	private String reintentos;

	public String getReintentos() {
		return getReintentos();
	}

	public void setReintentos(String reintentos) {
		this.reintentos = reintentos;
	}

	
}
