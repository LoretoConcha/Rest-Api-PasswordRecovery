package recoverpass.apis.helper;

import javax.ws.rs.container.ContainerRequestContext;

/**
 * Valida parametros de entrada generales
 * 
 *
 */
public class Validadores {

	/**
	 * Validar los parametros de entrada en el caso de que sea el rut
	 * 
	 * @param campo1
	 * @return
	 */
	public static boolean parametrosEntradaIncorrectosUnCampo(String campo1) {
		if (campo1 == null || "".equals(campo1)) {
			return true;
		}

		return false;
	}

	/**
	 * Validar entrada del servicio
	 * 
	 * @param campo1
	 * @param campo2
	 * @param res
	 * @return
	 */
	public static boolean parametrosEntradaIncorrectosDobleCampo(String campo1, String campo2) {
		if (campo1 == null || "".equals(campo1)) {
			return true;
		}

		if (campo2 == null || "".equals(campo2)) {
			return true;
		}

		return false;
	}

	/**
	 * Valida que los parametros del header del request actual sean validos.
	 * Nombres de los parametros del header se ausmieron del documento
	 * BCNSWSR_Login - v1.1
	 * 
	 * @param requestContext
	 * @return boolean
	 */
	public static boolean headersModalidadConsultarValidos(ContainerRequestContext requestContext) {

		String token = requestContext.getHeaderString("Token-Authorization");
		String modalidad = requestContext.getHeaderString("modalidad");
		String codigoCanal = requestContext.getHeaderString("codigoCanal");
		String empresaAplicacion = requestContext.getHeaderString("empresaAplicacion");
		String requestid = requestContext.getHeaderString("requestid");
		String clientid = requestContext.getHeaderString("clientid");
		String codigoAplicacion = requestContext.getHeaderString("codigoAplicacion");
		String ipCliente = requestContext.getHeaderString("ipCliente");

		return (token != null && !token.isEmpty()) && (modalidad != null && !modalidad.isEmpty())//token != null
				&& (codigoCanal != null && !codigoCanal.isEmpty())
				&& (empresaAplicacion != null && !empresaAplicacion.isEmpty())
				&& (requestid != null && !requestid.isEmpty()) && (clientid != null && !clientid.isEmpty())
				&& (codigoAplicacion != null && !codigoAplicacion.isEmpty())
				&& (ipCliente != null && !ipCliente.isEmpty());
	}

	/**
	 * Valida que los parametros del header del request actual sean validos.
	 * Nombres de los parametros del header se ausmieron del documento
	 * BCNSWSR_Login - v1.1
	 * 
	 * @param requestContext
	 * @return boolean
	 */
	public static boolean headersModalidadGenerarValidos(ContainerRequestContext requestContext) {

		String modalidad = requestContext.getHeaderString("modalidad");
		String codigoCanal = requestContext.getHeaderString("codigoCanal");
		String empresaAplicacion = requestContext.getHeaderString("empresaAplicacion");
		String codigoAplicacion = requestContext.getHeaderString("codigoAplicacion");
		String ipCliente = requestContext.getHeaderString("ipCliente");

		return (modalidad != null && !modalidad.isEmpty()) && (codigoCanal != null && !codigoCanal.isEmpty())
				&& (empresaAplicacion != null && !empresaAplicacion.isEmpty())
				&& (codigoAplicacion != null && !codigoAplicacion.isEmpty())
				&& (ipCliente != null && !ipCliente.isEmpty());
	}

}
