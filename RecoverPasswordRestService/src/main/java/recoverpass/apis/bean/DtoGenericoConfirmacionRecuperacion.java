package recoverpass.apis.bean;

/**
 * DTO encargado de rellanar el template para recuperaciones standard,
 * confirmaciones que solo necesiten un campo que se envia hacia el email
 * 
 *
 */
public class DtoGenericoConfirmacionRecuperacion {

	private String codigo;
	private String email;

	/** GET Y SET **/
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
