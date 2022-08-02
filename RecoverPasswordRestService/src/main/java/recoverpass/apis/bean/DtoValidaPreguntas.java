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
public class DtoValidaPreguntas {
	
	@JsonProperty("rutCliente")
	private String rutCliente;
	private Integer idPreguntaUno;
	private Integer idPreguntaDos;
	private String respuestaUno;
	private String respuestaDos;

	public String getRutCliente() {
		return rutCliente;
	}

	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}

	public Integer getIdPreguntaUno() {
		return idPreguntaUno;
	}

	public void setIdPreguntaUno(Integer idPreguntaUno) {
		this.idPreguntaUno = idPreguntaUno;
	}

	public Integer getIdPreguntaDos() {
		return idPreguntaDos;
	}

	public void setIdPreguntaDos(Integer idPreguntaDos) {
		this.idPreguntaDos = idPreguntaDos;
	}

	public String getRespuestaUno() {
		return respuestaUno;
	}

	public void setRespuestaUno(String respuestaUno) {
		this.respuestaUno = respuestaUno;
	}

	public String getRespuestaDos() {
		return respuestaDos;
	}

	public void setRespuestaDos(String respuestaDos) {
		this.respuestaDos = respuestaDos;
	}

}
