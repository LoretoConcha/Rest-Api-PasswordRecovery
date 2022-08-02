package recoverpass.apis.bean;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON input para email
 * 
 *
 */
public class Informacionemail {

	@JsonProperty("nombreCliente")
	private String nombrecliente;

	@NotNull
	@JsonProperty("tipoEnvio")
	private String tipoenvio;

	private String fecha;
	private String hora;

	/**
	 * Reflection
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/** GET Y SET **/

	public String getNombrecliente() {
		return nombrecliente;
	}

	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}

	public String getTipoenvio() {
		return tipoenvio;
	}

	public void setTipoenvio(String tipoenvio) {
		this.tipoenvio = tipoenvio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	

}