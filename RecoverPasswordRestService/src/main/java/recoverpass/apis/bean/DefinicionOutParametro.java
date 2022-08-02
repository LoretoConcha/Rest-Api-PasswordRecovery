package recoverpass.apis.bean;

import java.io.Serializable;

/**
 * Permite definir los parametros de salida para un procedimiento. llamar a los
 * procedimientos
 * 
 *
 */
public class DefinicionOutParametro implements Serializable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -6930573944517207652L;

	private int tipoCampoJavaSql;

	private Integer indiceCampo;

	/**
	 * Constructor
	 * 
	 * @param tipoCampo
	 * @param valorCampo
	 */
	public DefinicionOutParametro(int tipoCampoJavaSql, Integer indiceCampo) {
		super();
		this.tipoCampoJavaSql = tipoCampoJavaSql;
		this.indiceCampo = indiceCampo;
	}

	/** GET Y SET **/

	/**
	 * @return the indiceCampo
	 */
	public Integer getIndiceCampo() {
		return this.indiceCampo;
	}

	/**
	 * @param indiceCampo
	 *            the indiceCampo to set
	 */
	public void setIndiceCampo(Integer indiceCampo) {
		this.indiceCampo = indiceCampo;
	}

	/**
	 * @return the tipoCampoJavaSql
	 */
	public int getTipoCampoJavaSql() {
		return this.tipoCampoJavaSql;
	}

	/**
	 * @param tipoCampoJavaSql the tipoCampoJavaSql to set
	 */
	public void setTipoCampoJavaSql(int tipoCampoJavaSql) {
		this.tipoCampoJavaSql = tipoCampoJavaSql;
	}
	
	

}
