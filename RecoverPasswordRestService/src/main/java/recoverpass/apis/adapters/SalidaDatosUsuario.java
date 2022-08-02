/**
 * 
 */
package recoverpass.apis.adapters;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import recoverpass.apis.bean.DtoOutUserData;

/**
 * Salida para la consulta resumen de datos de usuario
 * 
 * @author lconcha
 *
 */
public class SalidaDatosUsuario {

	@JsonProperty("dtoResponseCodigosEstadoHttp")
	private DtoResponseCodigosEstadoHttp dtoResponseCodigosEstado;

	@JsonProperty("dtoResponseSetResultados")
	private DtoResponseSetResultados dtoResponseSetResultados;

	public SalidaDatosUsuario() {
		this.dtoResponseCodigosEstado = new DtoResponseCodigosEstadoHttp();
		this.dtoResponseSetResultados = new DtoResponseSetResultados();
	}

	public class DtoResponseSetResultados {

		@JsonProperty("dtoResponseCodigosOperacion")
		private DtoResponseCodigosOperacion dtoResponseCodigosOperacion;

		@JsonProperty("dtoOutUserData")
		private List<DtoOutUserData> dtoOutUserData;

		public DtoResponseSetResultados() {
			this.dtoOutUserData = new ArrayList<>();
			this.dtoResponseCodigosOperacion = new DtoResponseCodigosOperacion();

		}

		/**
		 * @return the dtoResponseCodigosOperacion
		 */
		public DtoResponseCodigosOperacion getDtoResponseCodigosOperacion() {
			return dtoResponseCodigosOperacion;
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
		public List<DtoOutUserData> getDtoOutDatosUsuario() {
			return dtoOutUserData;
		}
		/**
		 * 
		 * @param dtoOutUserData
		 */
		public void setDtoOutDatosUsuario(List<DtoOutUserData> dtoOutUserData) {
			this.dtoOutUserData = dtoOutUserData;
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
