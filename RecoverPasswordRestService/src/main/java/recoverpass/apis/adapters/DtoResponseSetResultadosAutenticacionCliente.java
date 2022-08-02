
package recoverpass.apis.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;

import recoverpass.apis.bean.DtoCliente;
import recoverpass.apis.helper.Constantes;

/**
 * Esta clase representa todos los datos de salida para la autenticacion de un
 * cliente.
 * 
 *
 */
public class DtoResponseSetResultadosAutenticacionCliente {

	@JsonProperty(Constantes.DTO_RESPONSE_CODIGOS_OPERACION)
	private DtoResponseCodigosOperacion dtoResponseCodigosOperacion;

	private DtoCliente dtoCliente;

	public DtoResponseSetResultadosAutenticacionCliente() {
		this.dtoCliente = new DtoCliente();
		this.dtoResponseCodigosOperacion = new DtoResponseCodigosOperacion();

	}

	/**
	 * @return the dtoCliente
	 */
	public DtoCliente getDtoCliente() {
		return this.dtoCliente;
	}

	/**
	 * @param dtoCliente the dtoCliente to set
	 */
	public void setDtoCliente(DtoCliente dtoCliente) {
		this.dtoCliente = dtoCliente;
	}

	/**
	 * @return the dtoResponseCodigosOperacion
	 */
	public DtoResponseCodigosOperacion getDtoResponseCodigosOperacion() {
		return this.dtoResponseCodigosOperacion;
	}

	/**
	 * @param dtoResponseCodigosOperacion the dtoResponseCodigosOperacion to set
	 */
	public void setDtoResponseCodigosOperacion(DtoResponseCodigosOperacion dtoResponseCodigosOperacion) {
		this.dtoResponseCodigosOperacion = dtoResponseCodigosOperacion;
	}

}
