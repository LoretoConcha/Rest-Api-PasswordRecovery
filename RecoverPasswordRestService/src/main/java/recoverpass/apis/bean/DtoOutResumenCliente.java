/**
 * 
 */
package recoverpass.apis.bean;

/**
 * 
 * @author lconcha
 *
 */

public class DtoOutResumenCliente {

	private String totalDeuda;
	private String totalDoctos;
	private String saldoVigente;
	private String doctosVigentes;
	private String saldoMorosos;
	private String doctosMorosos;

	public String getTotalDeuda() {
		return this.totalDeuda;
	}

	public void setTotalDeuda(String totalDeuda) {
		this.totalDeuda = totalDeuda;
	}

	public String getTotalDoctos() {
		return this.totalDoctos;
	}

	public void setTotalDoctos(String totalDoctos) {
		this.totalDoctos = totalDoctos;
	}

	public String getSaldoVigente() {
		return this.saldoVigente;
	}

	public void setSaldoVigente(String saldoVigente) {
		this.saldoVigente = saldoVigente;
	}

	public String getDoctosVigentes() {
		return this.doctosVigentes;
	}

	public void setDoctosVigentes(String doctosVigentes) {
		this.doctosVigentes = doctosVigentes;
	}

	public String getSaldoMorosos() {
		return this.saldoMorosos;
	}

	public void setSaldoMorosos(String saldoMorosos) {
		this.saldoMorosos = saldoMorosos;
	}

	public String getDoctosMorosos() {
		return this.doctosMorosos;
	}

	public void setDoctosMorosos(String doctosMorosos) {
		this.doctosMorosos = doctosMorosos;
	}

}
