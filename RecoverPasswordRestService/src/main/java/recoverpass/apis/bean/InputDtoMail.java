package recoverpass.apis.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Input principal cuando se envia EMAIL
 * 
 *
 */
public class InputDtoMail {

	@JsonProperty("informacionEmail")
	private Informacionemail informacionemail;
	
	@JsonProperty("claveTemporal")
	private ClaveTemporal claveTemporal;


	/**
	 * Reflection
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	public Informacionemail getInformacionemail() {
		return informacionemail;
	}


	public void setInformacionemail(Informacionemail informacionemail) {
		this.informacionemail = informacionemail;
	}


	public ClaveTemporal getClaveTemporal() {
		return claveTemporal;
	}


	public void setClaveTemporal(ClaveTemporal claveTemporal) {
		this.claveTemporal = claveTemporal;
	}

	/** GET Y SET **/
	
	
	
}