/**
 * 
 */
package recoverpass.apis.bean;

/**
 *
 */
public class DtoAutorizacion {
	
	private String rol;
	private String perfil;
	private DtoToken dtoToken;
	
	public DtoAutorizacion(){
		this.dtoToken = new DtoToken();
	}

	/**
	 * @return the rol
	 */
	public String getRol() {
		return this.rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * @return the perfil
	 */
	public String getPerfil() {
		return this.perfil;
	}

	/**
	 * @param perfil the perfil to set
	 */
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	/**
	 * @return the dtoToken
	 */
	public DtoToken getDtoToken() {
		return this.dtoToken;
	}

	/**
	 * @param dtoToken the dtoToken to set
	 */
	public void setDtoToken(DtoToken dtoToken) {
		this.dtoToken = dtoToken;
	}
	
	
	

}
