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

public class DtoOutTienePreguntasSecretas {
	
	@JsonProperty("tienePreguntas")
	private String tienePreguntas;

	public String getTienePreguntas() {
		return this.tienePreguntas;
	}

	public void setTienePreguntas(String tienePreguntas) {
		this.tienePreguntas = tienePreguntas;
	}
	
	
}
