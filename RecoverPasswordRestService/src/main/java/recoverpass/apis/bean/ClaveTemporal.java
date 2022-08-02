package recoverpass.apis.bean;


import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO para envío de clave temporal para recuperar contraseña
 * @author lconcha
 *
 */
public class ClaveTemporal {

	@JsonProperty("nombreCliente")
	private String nombreCliente;
	
	@JsonProperty("enviarEmail")
	private String enviarEmail;
	
	@JsonProperty("nombreArchivo")
	private String nombreArchivo;

	/**
	 * Reflection
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	/** GET Y SET **/
	public String getNombreArchivo() {
		return this.nombreArchivo;
	}


	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getEnviarEmail() {
		return this.enviarEmail;
	}

	public void setEnviarEmail(String enviarEmail) {
		this.enviarEmail = enviarEmail;
	}

	public String getNombreCliente() {
		return this.nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

}
