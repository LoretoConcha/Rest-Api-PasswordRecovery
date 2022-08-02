/**
 * 
 */
package recoverpass.apis.mgr.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import recoverpass.apis.adapters.DtoRequestLogin;
import recoverpass.apis.adapters.SalidaAutenticacionCliente;
import recoverpass.apis.bean.DefinicionOutParametro;
import recoverpass.apis.bean.DefinicionParametro;
import recoverpass.apis.dao.QueryDao;
import recoverpass.apis.dao.impl.QueryDaoImpl;
import recoverpass.apis.helper.Constantes;
import recoverpass.apis.helper.ConstantesBD;
import recoverpass.apis.helper.json.JsonBodyResponse;
import recoverpass.apis.helper.json.JsonHeaderResponse;
import recoverpass.apis.mgr.ILogin;

/**
 *
 */
public class LoginMgrImpl implements ILogin {
	/**
	 * LOGGER
	 */
	private static final Logger log = Logger.getLogger(LoginMgrImpl.class);

	/**
	 * Permite autenticar a un cliente. En caso que el cliente sea valido para las posteriores llamadas a los servicios REST.
	 */
	@Override
	public SalidaAutenticacionCliente autenticarCliente(HttpServletRequest httpServletRequest,
			DtoRequestLogin dtoCredenciales) {

		log.info("Comenzando la autenticacion para el usuario = "
				+ dtoCredenciales.getDtoRequestSetParametros().getDtoCredenciales().getRut());
		SalidaAutenticacionCliente salida = new SalidaAutenticacionCliente();

		try {

			QueryDao query = new QueryDaoImpl();

			LinkedHashMap<Integer, DefinicionParametro> params = new LinkedHashMap<>();
			params.put(1, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR,
					dtoCredenciales.getDtoRequestSetParametros().getDtoCredenciales().getRut().trim()));
			params.put(2, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR, null));

			List<DefinicionOutParametro> outParametros = new ArrayList<>();
			outParametros.add(new DefinicionOutParametro(java.sql.Types.INTEGER, 3));
			Map<Integer, Object> queryResult = query.queryForProcedureSqlServerOutParameters(
					Constantes.DATASOURCE_SQLSERVER, params, outParametros, Constantes.PROCEDURE_AUTENTICACION_CLIENTE);
			Integer result = (Integer) queryResult.get(3);

			switch (result) {
			case 0:
				/**
				 * Cuando el procedimiento retorna este codigo como resultado, efectivamente no
				 * existe un usuario registrado con RUT ingresado, sin embargo por acuerdo de
				 * seguridad se retorna al cliente una respuesta de credenciales invalidas
				 */
				log.info(Constantes.USUARIO + dtoCredenciales.getDtoRequestSetParametros().getDtoCredenciales().getRut()
						+ " NO EXISTE");
				salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.operacionOk());

				salida.setDtoResponseCodigosEstadoHttp(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados().getDtoCliente()
						.setDtoAutenticacion(JsonBodyResponse.requestLoginInvalido());
				salida.getDtoResponseSetResultados().getDtoCliente()
						.setRut(dtoCredenciales.getDtoRequestSetParametros().getDtoCredenciales().getRut());
				break;
			case 1:
				log.info(Constantes.USUARIO + dtoCredenciales.getDtoRequestSetParametros().getDtoCredenciales().getRut()
						+ " LOGEADO CORRECTAMENTE");
				salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.operacionOk());
				salida.getDtoResponseSetResultados().getDtoCliente()
						.setRut(dtoCredenciales.getDtoRequestSetParametros().getDtoCredenciales().getRut());
				salida.getDtoResponseSetResultados().getDtoCliente().getDtoAutenticacion().setCodigo(1);
				salida.getDtoResponseSetResultados().getDtoCliente().getDtoAutenticacion()
						.setEstado("CREDENCIALES_VALIDAS");
				salida.getDtoResponseSetResultados().getDtoCliente().getDtoAutenticacion()
						.setDescripcion("LAS CREDENCIALES INGRESADAS SON VALIDAS");
				salida.setDtoResponseCodigosEstadoHttp(JsonHeaderResponse.requestOK());
				break;
			case 2:
				log.info(Constantes.USUARIO + dtoCredenciales.getDtoRequestSetParametros().getDtoCredenciales().getRut()
						+ " BLOQUEADO");
				salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.operacionOk());
				salida.setDtoResponseCodigosEstadoHttp(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados().getDtoCliente()
						.setDtoAutenticacion(JsonBodyResponse.requestLoginUsuarioBloqueado());
				salida.getDtoResponseSetResultados().getDtoCliente()
						.setRut(dtoCredenciales.getDtoRequestSetParametros().getDtoCredenciales().getRut());

				break;
			case 3:
				log.info(Constantes.USUARIO + dtoCredenciales.getDtoRequestSetParametros().getDtoCredenciales().getRut()
						+ " NO POSEE EMPRESA");
				salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.operacionOk());
				salida.setDtoResponseCodigosEstadoHttp(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados().getDtoCliente()
						.setDtoAutenticacion(JsonBodyResponse.requestLoginUsuarioNoPoseeEmpresa());
				salida.getDtoResponseSetResultados().getDtoCliente()
						.setRut(dtoCredenciales.getDtoRequestSetParametros().getDtoCredenciales().getRut());

				break;
			case 4:
				log.info("PRIMER ACCESO DE USUARIO "
						+ dtoCredenciales.getDtoRequestSetParametros().getDtoCredenciales().getRut());
				salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.operacionOk());
				salida.setDtoResponseCodigosEstadoHttp(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados().getDtoCliente()
						.setDtoAutenticacion(JsonBodyResponse.requestLoginPrimerAccesoUsuario());
				salida.getDtoResponseSetResultados().getDtoCliente()
						.setRut(dtoCredenciales.getDtoRequestSetParametros().getDtoCredenciales().getRut());

				break;
			default:
				break;
			}
		} catch (Exception e) {
			log.error("Ha ocurrido un error al ejecutar el servicio, ERROR = " + e.getMessage());
			salida.setDtoResponseCodigosEstadoHttp(JsonHeaderResponse.requestOK());
			salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.errorOperacion());
			salida.getDtoResponseSetResultados().setDtoCliente(null);
		}
		return salida;
	}


}
