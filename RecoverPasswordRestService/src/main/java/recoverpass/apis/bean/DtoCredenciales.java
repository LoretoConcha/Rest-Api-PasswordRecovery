/**
 * 
 */
package recoverpass.apis.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class DtoCredenciales {
	@JsonProperty("rut")
	private String rut;

	@JsonProperty("contrasena")
	private String contrasena;


	/**
	 * @return the rut
	 */

	public String getRut() {
		return this.rut;
	}

	/**
	 * @param rut
	 *            the rut to set
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}

	/**
	 * @return the contrasena
	 */
	public String getContrasena() {
		return this.contrasena;
	}

	/**
	 * @param contrasena
	 *            the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
