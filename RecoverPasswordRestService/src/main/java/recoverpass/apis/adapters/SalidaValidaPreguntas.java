/**
 * 
 */
package recoverpass.apis.adapters;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import recoverpass.apis.bean.DtoOutValidaPreguntas;

/**
 * Salida para la validación de respuesta a preguntas secretas
 * 
 * @author lconcha
 *
 */
public class SalidaValidaPreguntas {

	@JsonProperty("dtoResponseCodigosEstadoHttp")
	private DtoResponseCodigosEstadoHttp dtoResponseCodigosEstado;

	@JsonProperty("dtoResponseSetResultados")
	private DtoResponseSetResultados dtoResponseSetResultados;

	public SalidaValidaPreguntas() {
		this.dtoResponseCodigosEstado = new DtoResponseCodigosEstadoHttp();
		this.dtoResponseSetResultados = new DtoResponseSetResultados();
	}

	public class DtoResponseSetResultados {

		@JsonProperty("dtoResponseCodigosOperacion")
		private DtoResponseCodigosOperacion dtoResponseCodigosOperacion;

		@JsonProperty("dtoOutValidaPreguntas")
		private List<DtoOutValidaPreguntas> dtoOutValidaPreguntas;

		public DtoResponseSetResultados() {
			this.dtoOutValidaPreguntas = new ArrayList<>();
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
		public List<DtoOutValidaPreguntas> getDtoOutValidaPreguntas() {
			return this.dtoOutValidaPreguntas;
		}
		/**
		 * 
		 * @param dtoOutValidaPreguntas
		 */
		public void setDtoOutValidaPreguntas(List<DtoOutValidaPreguntas> dtoOutValidaPreguntas) {
			this.dtoOutValidaPreguntas = dtoOutValidaPreguntas;
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
