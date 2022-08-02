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

public class DtoOutValidaPreguntas {
	
	@JsonProperty("estadoRespuestaUno")
	private String estadoRespuestaUno;
	private String estadoRespuestaDos;

	public String getEstadoRespuestaUno() {
		return estadoRespuestaUno;
	}

	public void setEstadoRespuestaUno(String estadoRespuestaUno) {
		this.estadoRespuestaUno = estadoRespuestaUno;
	}

	public String getEstadoRespuestaDos() {
		return estadoRespuestaDos;
	}

	public void setEstadoRespuestaDos(String estadoRespuestaDos) {
		this.estadoRespuestaDos = estadoRespuestaDos;
	}

}
