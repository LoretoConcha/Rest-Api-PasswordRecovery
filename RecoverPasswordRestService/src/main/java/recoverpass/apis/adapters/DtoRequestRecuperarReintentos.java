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
public class DtoRequestRecuperarReintentos {

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

		@JsonProperty("dtoRecuperarReintentos")
		private DtoRutGenerico dtoRecuperarReintentos;

		/**
		 * 
		 */
		public DtoRequestSetParametros() {
			this.dtoRecuperarReintentos = new DtoRutGenerico();
		}
		/**
		 * 
		 * @return
		 */
		public DtoRutGenerico getDtoRecuperarReintentos() {
			return this.dtoRecuperarReintentos;
		}
		/**
		 * 
		 * @param dtoRecuperarReintentos
		 */
		public void setDtoRecuperarReintentos(DtoRutGenerico dtoRecuperarReintentos) {
			this.dtoRecuperarReintentos = dtoRecuperarReintentos;
		}



	}

}
