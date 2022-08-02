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
public class DtoRecuperarClaveTemporal {
	@JsonProperty("rutCliente")
	private String rutCliente;
	private String claveTemporal;
	private String claveNueva1;
	private String claveNueva2;
	
	public String getRutCliente() {
		return rutCliente;
	}
	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}
	public String getClaveTemporal() {
		return claveTemporal;
	}
	public void setClaveTemporal(String claveTemporal) {
		this.claveTemporal = claveTemporal;
	}
	public String getClaveNueva1() {
		return claveNueva1;
	}
	public void setClaveNueva1(String claveNueva1) {
		this.claveNueva1 = claveNueva1;
	}
	public String getClaveNueva2() {
		return claveNueva2;
	}
	public void setClaveNueva2(String claveNueva2) {
		this.claveNueva2 = claveNueva2;
	}
}
