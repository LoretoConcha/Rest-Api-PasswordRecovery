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

public class DtoOutRecuperarClaveTemporal {
	
	@JsonProperty("idUsuario")
	private String idUsuario;
	private String claveTemporal;
	private int intentos;
	private long idClaveTemporal;
	
	public String getIdUsuario() {
		return this.idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getClaveTemporal() {
		return this.claveTemporal;
	}
	public void setClaveTemporal(String claveTemporal) {
		this.claveTemporal = claveTemporal;
	}
	public int getIntentos() {
		return this.intentos;
	}
	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}
	public long getIdClaveTemporal() {
		return this.idClaveTemporal;
	}
	public void setIdClaveTemporal(long idClaveTemporal) {
		this.idClaveTemporal = idClaveTemporal;
	}
//	public int getIdClaveTemporal() {
//		return idClaveTemporal;
//	}
//	public void setIdClaveTemporal(int idClaveTemporal) {
//		this.idClaveTemporal = idClaveTemporal;
//	}



}
