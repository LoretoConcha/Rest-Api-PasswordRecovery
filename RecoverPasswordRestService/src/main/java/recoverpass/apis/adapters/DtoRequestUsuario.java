/**
 * 
 */
package recoverpass.apis.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;

import recoverpass.apis.bean.DtoUsuario;

/**
 * DTO de entrada para las funcionalidades de usuario
 * 
 *
 */

public class DtoRequestUsuario {

	@JsonProperty("dtoRequestSetParametros")
	private DtoRequestSetParametros dtoRequestSetParametros;

	public class DtoRequestSetParametros {
		
		@JsonProperty("dtoUsuario")
		private DtoUsuario dtoUsuario;

		public DtoRequestSetParametros() {
			this.dtoUsuario = new DtoUsuario();
		}

		/**
		 * @return the dtoUsuario
		 */
		public DtoUsuario getDtoUsuario() {
			return this.dtoUsuario;
		}

		/**
		 * @param dtoUsuario
		 *            the dtoUsuario to set
		 */
		public void setDtoUsuario(DtoUsuario dtoUsuario) {
			this.dtoUsuario = dtoUsuario;
		}
	}

	/**
	 * @return the dtoRequestSetParametros
	 */
	public DtoRequestSetParametros getDtoRequestSetParametros() {
		return this.dtoRequestSetParametros;
	}

	/**
	 * @param dtoRequestSetParametros
	 *            the dtoRequestSetParametros to set
	 */
	public void setDtoRequestSetParametros(DtoRequestSetParametros dtoRequestSetParametros) {
		this.dtoRequestSetParametros = dtoRequestSetParametros;
	}

}
