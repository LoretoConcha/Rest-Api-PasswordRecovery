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
public class DtoRecuperarReintentos {
	
	@JsonProperty("rutCliente")
	private String rutCliente;
	private int procesoReintento;

	public String getRutCliente() {
		return rutCliente;
	}

	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}

	public int getProcesoReintento() {
		return procesoReintento;
	}

	public void setProcesoReintento(int procesoReintento) {
		this.procesoReintento = procesoReintento;
	}


}
