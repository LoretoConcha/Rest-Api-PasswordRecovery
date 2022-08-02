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

public class DtoOutClavesTemporales {
	
	@JsonProperty("rutUsuario")
	private String rutUsuario;
	private String claveTemporal;

	
	public String getRutUsuario() {
		return this.rutUsuario;
	}

	public void setRutUsuario(String rutUsuario) {
		this.rutUsuario = rutUsuario;
	}

	public String getClaveTemporal() {
		return this.claveTemporal;
	}

	public void setClaveTemporal(String claveTemporal) {
		this.claveTemporal = claveTemporal;
	}

}
