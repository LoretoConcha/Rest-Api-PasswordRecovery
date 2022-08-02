package recoverpass.apis.bean;

/**
 * Permite definir que contendra y que tipo de dato contiene cada parametro para
 * llamar a los procedimientos
 * 
 *
 */
public class DefinicionParametro  {

	

	private String tipoCampo;

	private Object valorCampo;

	/**
	 * Constructor
	 * 
	 * @param tipoCampo
	 * @param valorCampo
	 */
	public DefinicionParametro(String tipoCampo, Object valorCampo) {
		super();
		this.tipoCampo = tipoCampo;
		this.valorCampo = valorCampo;
	}

	/** GET Y SET **/
	public String getTipoCampo() {
		return this.tipoCampo;
	}

	public void setTipoCampo(String tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

	public Object getValorCampo() {
		return this.valorCampo;
	}

	public void setValorCampo(Object valorCampo) {
		this.valorCampo = valorCampo;
	}

}
