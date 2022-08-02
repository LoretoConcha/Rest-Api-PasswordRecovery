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

public class DtoOutAddIntentosClaveTemporal {
	
	@JsonProperty("codigo")
	private int codigo;
	private String mensaje;
	
	public int getCodigo() {
		return this.codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return this.mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
