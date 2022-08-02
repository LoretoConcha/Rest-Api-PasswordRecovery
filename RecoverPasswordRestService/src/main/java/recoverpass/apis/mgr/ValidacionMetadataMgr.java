package recoverpass.apis.mgr;

/**
 * Permite validar la metadata enviada en contraste al SP
 *
 */
public interface ValidacionMetadataMgr {

	/**
	 * Valida dependiendo del producto
	 * 
	 * @return
	 */
	public boolean validacionMetadataFiltro(String rut, String nroProducto, int tipoProducto);

}
