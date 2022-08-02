package recoverpass.apis.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO para el cambio de clave
 * 
 *
 */
public class CambioClave {

	@JsonProperty("claveGenerada")
	private String claveGenerada;

	@JsonProperty("mailCliente")
	private String mailCliente;
	
	/**
	 * Reflection
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/** GET Y SET **/
	public void setClaveGenerada(String claveGenerada) {
		this.claveGenerada = claveGenerada;
	}

	public String getClaveGenerada() {
		return this.claveGenerada;
	}

	public String getMailCliente() {
		return this.mailCliente;
	}

	public void setMailCliente(String mailCliente) {
		this.mailCliente = mailCliente;
	}

}
