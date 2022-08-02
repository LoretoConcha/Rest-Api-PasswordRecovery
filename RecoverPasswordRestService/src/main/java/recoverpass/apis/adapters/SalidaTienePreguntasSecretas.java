/**
 * 
 */
package recoverpass.apis.adapters;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import recoverpass.apis.bean.DtoOutTienePreguntasSecretas;

/**
 * Salida para la consulta si un cliente tiene asociadas preguntas secretas
 * 
 * @author lconcha
 *
 */
public class SalidaTienePreguntasSecretas {

	@JsonProperty("dtoResponseCodigosEstadoHttp")
	private DtoResponseCodigosEstadoHttp dtoResponseCodigosEstado;

	@JsonProperty("dtoResponseSetResultados")
	private DtoResponseSetResultados dtoResponseSetResultados;

	public SalidaTienePreguntasSecretas() {
		this.dtoResponseCodigosEstado = new DtoResponseCodigosEstadoHttp();
		this.dtoResponseSetResultados = new DtoResponseSetResultados();
	}

	public class DtoResponseSetResultados {

		@JsonProperty("dtoResponseCodigosOperacion")
		private DtoResponseCodigosOperacion dtoResponseCodigosOperacion;

		@JsonProperty("dtoOutTienePreguntasSecretas")
		private List<DtoOutTienePreguntasSecretas> dtoOutTienePreguntasSecretas;

		public DtoResponseSetResultados() {
			this.dtoOutTienePreguntasSecretas = new ArrayList<>();
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
		public List<DtoOutTienePreguntasSecretas> getDtoOutTienePreguntasSecretas() {
			return this.dtoOutTienePreguntasSecretas;
		}
		/**
		 * 
		 * @param dtoOutTienePreguntasSecretas
		 */
		public void setDtoOutTienePreguntasSecretas(List<DtoOutTienePreguntasSecretas> dtoOutTienePreguntasSecretas) {
			this.dtoOutTienePreguntasSecretas = dtoOutTienePreguntasSecretas;
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
