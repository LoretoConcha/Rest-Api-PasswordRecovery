/**
 * 
 */
package recoverpass.apis.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DtoUsuario {

	@JsonProperty("rutUsuario")
	private String rutUsurio;

	@JsonProperty("nombre")
	private String nombre;

	@JsonProperty("apellidoPaterno")
	private String apellidoPaterno;

	@JsonProperty("apellidoMaterno")
	private String apellidoMaterno;

	@JsonProperty("estado")
	private Integer estado;

	@JsonProperty("email")
	private String email;

	@JsonProperty("fechaNacimiento")
	private String fechaNacimiento;

	@JsonProperty("direccion")
	private String direccion;

	@JsonProperty("comuna")
	private String comuna;

	@JsonProperty("telefono")
	private String telefono;

	@JsonProperty("celular")
	private String celular;

	@JsonProperty("sexo")
	private String sexo;

	@JsonProperty("password")
	private String password;

	@JsonProperty("estadoClave")
	private Integer estadoClave;

	@JsonProperty("idRol")
	private String idRol;

	@JsonProperty("idUsuario")
	private String idUsuario;

	@JsonProperty("rutEmpresa")
	private String rutEmpresa;

	/**
	 * @return the rutUsurio
	 */
	public String getRutUsurio() {
		return rutUsurio;
	}

	/**
	 * @param rutUsurio
	 *            the rutUsurio to set
	 */
	public void setRutUsurio(String rutUsurio) {
		this.rutUsurio = rutUsurio;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
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
		return apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno
	 *            the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * @return the estado
	 */
	public Integer getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento
	 *            the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
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
		return comuna;
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
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * @param celular
	 *            the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo
	 *            the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the estadoClave
	 */
	public Integer getEstadoClave() {
		return estadoClave;
	}

	/**
	 * @param estadoClave
	 *            the estadoClave to set
	 */
	public void setEstadoClave(Integer estadoClave) {
		this.estadoClave = estadoClave;
	}

	/**
	 * @return the idRol
	 */
	public String getIdRol() {
		return idRol;
	}

	/**
	 * @param idRol
	 *            the idRol to set
	 */
	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario
	 *            the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the rutEmpresa
	 */
	public String getRutEmpresa() {
		return rutEmpresa;
	}

	/**
	 * @param rutEmpresa
	 *            the rutEmpresa to set
	 */
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

}
