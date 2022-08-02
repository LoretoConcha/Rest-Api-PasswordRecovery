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

public class DtoOutUserData {
	
	@JsonProperty("passEstado")
	private String passEstado;
	private String email;
	private String nameUser;
	private String lastNameUser;
	
	
	public String getPassEstado() {
		return this.passEstado;
	}
	public void setPassEstado(String passEstado) {
		this.passEstado = passEstado;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNameUser() {
		return this.nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public String getLastNameUser() {
		return this.lastNameUser;
	}
	public void setLastNameUser(String lastNameUser) {
		this.lastNameUser = lastNameUser;
	}



	
}
