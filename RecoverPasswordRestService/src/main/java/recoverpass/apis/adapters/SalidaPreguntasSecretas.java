/**
 * 
 */
package recoverpass.apis.adapters;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import recoverpass.apis.bean.DtoOutPreguntasSecretas;

/**
 * Salida para la consulta de preguntas secretas asociadas a un usuario
 * 
 * @author lconcha
 *
 */
public class SalidaPreguntasSecretas {

	@JsonProperty("dtoResponseCodigosEstadoHttp")
	private DtoResponseCodigosEstadoHttp dtoResponseCodigosEstado;

	@JsonProperty("dtoResponseSetResultados")
	private DtoResponseSetResultados dtoResponseSetResultados;

	public SalidaPreguntasSecretas() {
		this.dtoResponseCodigosEstado = new DtoResponseCodigosEstadoHttp();
		this.dtoResponseSetResultados = new DtoResponseSetResultados();
	}

	public class DtoResponseSetResultados {

		@JsonProperty("dtoResponseCodigosOperacion")
		private DtoResponseCodigosOperacion dtoResponseCodigosOperacion;

		@JsonProperty("dtoOutPreguntasSecretas")
		private List<DtoOutPreguntasSecretas> dtoOutPreguntasSecretas;

		public DtoResponseSetResultados() {
			this.dtoOutPreguntasSecretas = new ArrayList<>();
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
		public List<DtoOutPreguntasSecretas> getDtoOutPreguntasSecretas() {
			return this.dtoOutPreguntasSecretas;
		}
		/**
		 * 
		 * @param dtoOutPreguntasSecretas
		 */
		public void setDtoOutPreguntasSecretas(List<DtoOutPreguntasSecretas> dtoOutPreguntasSecretas) {
			this.dtoOutPreguntasSecretas = dtoOutPreguntasSecretas;
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
