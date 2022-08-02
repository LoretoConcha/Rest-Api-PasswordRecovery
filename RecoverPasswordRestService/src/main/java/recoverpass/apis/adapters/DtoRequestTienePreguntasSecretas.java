/**
 * 
 */
package recoverpass.apis.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;

import recoverpass.apis.bean.DtoRutGenerico;

/**
 * Dto de entrada
 * 
 * @author lconcha
 *
 */
public class DtoRequestTienePreguntasSecretas {

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

		@JsonProperty("dtoRutGenerico")
		private DtoRutGenerico dtoRutGenerico;

		/**
		 * 
		 */
		public DtoRequestSetParametros() {
			this.dtoRutGenerico = new DtoRutGenerico();
		}

		public DtoRutGenerico getDtoRutGenerico() {
			return dtoRutGenerico;
		}

		public void setDtoRutGenerico(DtoRutGenerico dtoRutGenerico) {
			this.dtoRutGenerico = dtoRutGenerico;
		}


	}

}
