/**
 * 
 */
package recoverpass.apis.bean;

/**
 *
 */
public class DtoResponseAutenticacion {

	private Integer codigo;
	private String estado;
	private String descripcion;
	private DtoAutorizacion dtoAutorizacion;

	public DtoResponseAutenticacion() {
		this.dtoAutorizacion = new DtoAutorizacion();
	}

	

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}



	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}



	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the dtoAutorizacion
	 */
	public DtoAutorizacion getDtoAutorizacion() {
		return dtoAutorizacion;
	}

	/**
	 * @param dtoAutorizacion the dtoAutorizacion to set
	 */
	public void setDtoAutorizacion(DtoAutorizacion dtoAutorizacion) {
		this.dtoAutorizacion = dtoAutorizacion;
	}
	
	

}
