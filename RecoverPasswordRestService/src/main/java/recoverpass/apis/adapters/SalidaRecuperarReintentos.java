/**
 * 
 */
package recoverpass.apis.adapters;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import recoverpass.apis.bean.DtoOutRecuperarReintentos;

/**
 * Salida para la consulta de reintentos de contraseña asociado a un cliente
 * 
 * @author lconcha
 *
 */
public class SalidaRecuperarReintentos {

	@JsonProperty("dtoResponseCodigosEstadoHttp")
	private DtoResponseCodigosEstadoHttp dtoResponseCodigosEstado;

	@JsonProperty("dtoResponseSetResultados")
	private DtoResponseSetResultados dtoResponseSetResultados;

	public SalidaRecuperarReintentos() {
		this.dtoResponseCodigosEstado = new DtoResponseCodigosEstadoHttp();
		this.dtoResponseSetResultados = new DtoResponseSetResultados();
	}

	public class DtoResponseSetResultados {

		@JsonProperty("dtoResponseCodigosOperacion")
		private DtoResponseCodigosOperacion dtoResponseCodigosOperacion;

		@JsonProperty("dtoOutRecuperarReintentos")
		private List<DtoOutRecuperarReintentos> dtoOutRecuperarReintentos;

		public DtoResponseSetResultados() {
			this.dtoOutRecuperarReintentos = new ArrayList<>();
			this.dtoResponseCodigosOperacion = new DtoResponseCodigosOperacion();

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
		/**
		 * 
		 * @return
		 */
		public List<DtoOutRecuperarReintentos> getDtoOutRecuperarReintentos() {
			return this.dtoOutRecuperarReintentos;
		}
		/**
		 * 
		 * @param dtoOutRecuperarReintentos
		 */
		public void setDtoOutRecuperarReintentos(List<DtoOutRecuperarReintentos> dtoOutRecuperarReintentos) {
			this.dtoOutRecuperarReintentos = dtoOutRecuperarReintentos;
		}


		
	}

	/**
	 * @return the dtoResponseCodigosEstado
	 */
	public DtoResponseCodigosEstadoHttp getDtoResponseCodigosEstado() {
		return this.dtoResponseCodigosEstado;
	}

	/**
	 * @param dtoResponseCodigosEstado the dtoResponseCodigosEstado to set
	 */
	public void setDtoResponseCodigosEstado(DtoResponseCodigosEstadoHttp dtoResponseCodigosEstado) {
		this.dtoResponseCodigosEstado = dtoResponseCodigosEstado;
	}

	/**
	 * 
	 * @return
	 */
	public DtoResponseSetResultados getDtoResponseSetResultados() {
		return this.dtoResponseSetResultados;
	}

	/**
	 * 
	 * @param dtoResponseSetResultados
	 */
	public void setDtoResponseSetResultados(DtoResponseSetResultados dtoResponseSetResultados) {
		this.dtoResponseSetResultados = dtoResponseSetResultados;
	}

}
