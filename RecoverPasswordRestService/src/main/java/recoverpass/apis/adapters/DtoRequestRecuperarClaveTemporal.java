/**
 * 
 */
package recoverpass.apis.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;

import recoverpass.apis.bean.DtoRecuperarClaveTemporal;

/**
 * Dto de entrada
 * 
 * @author lconcha
 *
 */
public class DtoRequestRecuperarClaveTemporal {

	@JsonProperty("dtoRequestSetParametros")
	private DtoRequestSetParametros dtoRequestSetParametros;

	/**
	 * @return the dtoRequestSetParametros
	 */
	public DtoRequestSetParametros getDtoRequestSetParametros() {
		return this.dtoRequestSetParametros;
	}

	/**
	 * @param dtoRequestSetParametros the dtoRequestSetParametros to set
	 */
	public void setDtoRequestSetParametros(DtoRequestSetParametros dtoRequestSetParametros) {
		this.dtoRequestSetParametros = dtoRequestSetParametros;
	}

	public class DtoRequestSetParametros {

		@JsonProperty("dtoRecuperarClaveTemporal")
		private DtoRecuperarClaveTemporal dtoRecuperarClaveTemporal;

		/**
		 * 
		 */
		public DtoRequestSetParametros() {
			this.dtoRecuperarClaveTemporal = new DtoRecuperarClaveTemporal();
		}
		/**
		 * 
		 * @return
		 */
		public DtoRecuperarClaveTemporal getDtoRecuperarClaveTemporal() {
			return this.dtoRecuperarClaveTemporal;
		}
		/**
		 * 
		 * @param dtoRecuperarClaveTemporal
		 */
		public void setDtoRecuperarClaveTemporal(DtoRecuperarClaveTemporal dtoRecuperarClaveTemporal) {
			this.dtoRecuperarClaveTemporal = dtoRecuperarClaveTemporal;
		}
		

	}

}
