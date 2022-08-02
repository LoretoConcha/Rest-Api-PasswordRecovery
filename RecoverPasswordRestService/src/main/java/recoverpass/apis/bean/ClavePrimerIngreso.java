package recoverpass.apis.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ClavePrimerIngreso {
	/**
	 * Campos
	 */
	private String enviarEmail;
	private String codigoVerificacion;
	
	/**
	 * Reflection
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/** GET y SET **/
	public String getEnviarEmail() {
		return this.enviarEmail;
	}
	public void setEnviarEmail(String enviarEmail) {
		this.enviarEmail = enviarEmail;
	}
	public String getCodigoVerificacion() {
		return this.codigoVerificacion;
	}
	public void setCodigoVerificacion(String codigoVerificacion) {
		this.codigoVerificacion = codigoVerificacion;
	}
}
