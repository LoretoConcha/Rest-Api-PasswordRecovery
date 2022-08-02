package recoverpass.apis.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO de entrada con datos de contexto
 * 
 *
 */
public class DtoRequestDatosContextoHttp {

	@JsonProperty("ipCliente")
	private String ipcliente;

	@JsonProperty("codigoCanal")
	private String codigocanal;

	@JsonProperty("nombreCanal")
	private String nombrecanal;

	@JsonProperty("codigoAplicacion")
	private String codigoaplicacion;

	@JsonProperty("nombreAplicacion")
	private String nombreaplicacion;

	@JsonProperty("empresaAplicacion")
	private String empresaaplicacion;
	
	/**
	 * Reflection
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/** GET Y SET **/
	public void setIpcliente(String ipcliente) {
		this.ipcliente = ipcliente;
	}

	public String getIpcliente() {
		return ipcliente;
	}

	public void setCodigocanal(String codigocanal) {
		this.codigocanal = codigocanal;
	}

	public String getCodigocanal() {
		return codigocanal;
	}

	public void setNombrecanal(String nombrecanal) {
		this.nombrecanal = nombrecanal;
	}

	public String getNombrecanal() {
		return nombrecanal;
	}

	public void setCodigoaplicacion(String codigoaplicacion) {
		this.codigoaplicacion = codigoaplicacion;
	}

	public String getCodigoaplicacion() {
		return codigoaplicacion;
	}

	public void setNombreaplicacion(String nombreaplicacion) {
		this.nombreaplicacion = nombreaplicacion;
	}

	public String getNombreaplicacion() {
		return nombreaplicacion;
	}

	public void setEmpresaaplicacion(String empresaaplicacion) {
		this.empresaaplicacion = empresaaplicacion;
	}

	public String getEmpresaaplicacion() {
		return empresaaplicacion;
	}

}
