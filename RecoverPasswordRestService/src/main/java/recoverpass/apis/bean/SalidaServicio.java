package recoverpass.apis.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Salida estandard para la salida de los servicios
 * 
 *
 */
public class SalidaServicio {

	@JsonProperty("codigo")
	private int codigo;

	@JsonProperty("descripcion")
	private String descripcion;
	
	/**
	 * Reflection
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/** GET Y SET **/
	public void setCodigo(int Codigo) {
		this.codigo = Codigo;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setDescripcion(String Descripcion) {
		this.descripcion = Descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

}
