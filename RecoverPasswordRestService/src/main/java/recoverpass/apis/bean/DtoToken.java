/**
 * 
 */
package recoverpass.apis.bean;

/**
 *
 */
public class DtoToken {

	private String requestId;
	private String token;
	private Integer expiracion;

	/**
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the expiracion
	 */
	public Integer getExpiracion() {
		return expiracion;
	}

	/**
	 * @param expiracion the expiracion to set
	 */
	public void setExpiracion(Integer expiracion) {
		this.expiracion = expiracion;
	}

}
