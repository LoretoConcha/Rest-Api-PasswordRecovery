/**
 * 
 */
package recoverpass.apis.bean;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class DtoCliente {

	private String rut;
	@JsonIgnore
	private Long idUsuario;
	@JsonIgnore
	private String nombres;
	@JsonIgnore
	private String apellidoPaterno;
	@JsonIgnore
	private String apellidoMaterno;
	@JsonIgnore
	private String direccion;
	@JsonIgnore
	private String comuna;
	@JsonIgnore
	private String telefono;
	@JsonIgnore
	private Integer estadoClave;
	
	@JsonProperty("dtoAutenticacion")
	private DtoResponseAutenticacion dtoAutenticacion;

	@JsonIgnore
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDate ultimoAcceso;

	public DtoCliente() {
		this.dtoAutenticacion = new DtoResponseAutenticacion();
	}

	/**
	 * @return the rut
	 */
	public String getRut() {
		return this.rut;
	}

	/**
	 * @param rut
	 *            the rut to set
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}

	/**
	 * @return the idUsuario
	 */
	public Long getIdUsuario() {
		return this.idUsuario;
	}

	/**
	 * @param idUsuario
	 *            the idUsuario to set
	 */
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return this.nombres;
	}

	/**
	 * @param nombres
	 *            the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	/**
	 * @param apellidoPaterno
	 *            the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * @return the apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno
	 *            the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return this.direccion;
	}

	/**
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the comuna
	 */
	public String getComuna() {
		return this.comuna;
	}

	/**
	 * @param comuna
	 *            the comuna to set
	 */
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return this.telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the estadoClave
	 */
	public Integer getEstadoClave() {
		return this.estadoClave;
	}

	/**
	 * @param estadoClave
	 *            the estadoClave to set
	 */
	public void setEstadoClave(Integer estadoClave) {
		this.estadoClave = estadoClave;
	}

	/**
	 * @return the ultimoAcceso
	 */
	public LocalDate getUltimoAcceso() {
		return this.ultimoAcceso;
	}

	/**
	 * @param ultimoAcceso
	 *            the ultimoAcceso to set
	 */
	public void setUltimoAcceso(LocalDate ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}

	/**
	 * @return the dtoAutenticacion
	 */
	public DtoResponseAutenticacion getDtoAutenticacion() {
		return this.dtoAutenticacion;
	}

	/**
	 * @param dtoAutenticacion
	 *            the dtoAutenticacion to set
	 */
	public void setDtoAutenticacion(DtoResponseAutenticacion dtoAutenticacion) {
		this.dtoAutenticacion = dtoAutenticacion;
	}

}
