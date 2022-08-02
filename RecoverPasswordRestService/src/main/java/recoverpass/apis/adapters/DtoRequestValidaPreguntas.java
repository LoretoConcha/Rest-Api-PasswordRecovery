/**
 * 
 */
package recoverpass.apis.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;

import recoverpass.apis.bean.DtoValidaPreguntas;

/**
 * Dto de entrada
 * 
 * @author lconcha
 *
 */
public class DtoRequestValidaPreguntas {

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

		@JsonProperty("dtoValidaPreguntas")
		private DtoValidaPreguntas dtoValidaPreguntas;

		/**
		 * 
		 */
		public DtoRequestSetParametros() {
			this.dtoValidaPreguntas = new DtoValidaPreguntas();
		}
		/**
		 * 
		 * @return
		 */
		public DtoValidaPreguntas getDtoValidaPreguntas() {
			return this.dtoValidaPreguntas;
		}
		/**
		 * 
		 * @param dtoValidaPreguntas
		 */
		public void setDtoValidaPreguntas(DtoValidaPreguntas dtoValidaPreguntas) {
			this.dtoValidaPreguntas = dtoValidaPreguntas;
		}


	}

}
