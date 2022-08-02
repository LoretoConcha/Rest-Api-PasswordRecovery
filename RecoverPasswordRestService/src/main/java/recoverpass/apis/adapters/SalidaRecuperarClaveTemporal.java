/**
 * 
 */
package recoverpass.apis.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Salida para la consulta de clave temporal
 * 
 * @author lconcha
 *
 */
public class SalidaRecuperarClaveTemporal {

	@JsonProperty("dtoResponseCodigosEstadoHttp")
	private DtoResponseCodigosEstadoHttp dtoResponseCodigosEstado;

	@JsonProperty("dtoResponseSetResultados")
	private DtoResponseSetResultados dtoResponseSetResultados;

	public SalidaRecuperarClaveTemporal() {
		this.dtoResponseCodigosEstado = new DtoResponseCodigosEstadoHttp();
		this.dtoResponseSetResultados = new DtoResponseSetResultados();
	}

	public class DtoResponseSetResultados {

		@JsonProperty("dtoResponseCodigosOperacion")
		private DtoResponseCodigosOperacion dtoResponseCodigosOperacion;

//		@JsonProperty("dtoOutRecuperarClaveTemporal")
//		private List<DtoOutRecuperarClaveTemporal> dtoOutRecuperarClaveTemporal;

		public DtoResponseSetResultados() {
//			this.dtoOutRecuperarClaveTemporal = new ArrayList<>();
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
//		/**
//		 * 
//		 * @return
//		 */
//		public List<DtoOutRecuperarClaveTemporal> getDtoOutRecuperarClaveTemporal() {
//			return dtoOutRecuperarClaveTemporal;
//		}
//		/**
//		 * 
//		 * @param dtoOutRecuperarClaveTemporal
//		 */
//		public void setDtoOutRecuperarClaveTemporal(List<DtoOutRecuperarClaveTemporal> dtoOutRecuperarClaveTemporal) {
//			this.dtoOutRecuperarClaveTemporal = dtoOutRecuperarClaveTemporal;
//		}
		
		
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
