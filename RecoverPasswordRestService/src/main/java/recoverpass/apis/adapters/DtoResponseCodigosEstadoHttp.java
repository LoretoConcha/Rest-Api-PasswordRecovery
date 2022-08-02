package recoverpass.apis.adapters;

/**
 * Salidas para los servicios standard
 * 
 *
 */
public class DtoResponseCodigosEstadoHttp {

	private String codigo;
	private String mensaje;
	private String descripcion;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



}