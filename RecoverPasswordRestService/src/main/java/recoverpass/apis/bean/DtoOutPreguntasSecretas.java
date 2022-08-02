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

public class DtoOutPreguntasSecretas {
	
	@JsonProperty("idUsuario")
	private String idUsuario;
	private String pregunta;
	private String id;
	private String respuesta;
	
	
	
	public String getIdUsuario() {
		return this.idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getPregunta() {
		return this.pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRespuesta() {
		return this.respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}


	
}
