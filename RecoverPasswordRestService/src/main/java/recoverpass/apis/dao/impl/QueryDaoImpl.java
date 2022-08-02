package recoverpass.apis.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;

import recoverpass.apis.bean.DefinicionOutParametro;
import recoverpass.apis.bean.DefinicionParametro;
import recoverpass.apis.dao.QueryDao;
import recoverpass.apis.dao.config.ConnectionFactory;
import recoverpass.apis.dao.mappers.schema.RowMapper;
import recoverpass.apis.helper.ConstantesBD;
import recoverpass.apis.helper.DiccionarioProcedimientos;

/**
 * Permite obtener la data desde el Procedure
 * 
 *
 */
public class QueryDaoImpl implements QueryDao {

	/**
	 * LOGGER
	 */
	private static final Logger log = Logger.getLogger(QueryDaoImpl.class);

	/**
	 * Codigo de retorno para la consulta
	 */
	private int codRetorno;

	/**
	 * Esta salida permite obtener parametros que NO sean de un ResultSet
	 */
	private List<String> salidaCompuesta;

	/**
	 * SQL Server. Se setean los parametros de entrada y luego se ejecuta SP sin enviar datos de salida
	 */
	@Override
	public boolean queryForProcedureSqlServerUpdate(String dataSource, Map<Integer, DefinicionParametro> params, String dbProcedimiento) {

		// Generacion de Objetos
		Connection dbConexion = null;
		CallableStatement dbComando = null;
		ResultSet dbResultados = null;
		boolean salida=true;

		try {

			log.info("Conectando al recurso DataSource SQLServer...");
			dbConexion = ConnectionFactory.obtenerConexion(dataSource);
			log.info("Conexion OK");

			log.info("Conectando al Procedure");
			String procedure = DiccionarioProcedimientos.armarProcedure(dbProcedimiento, params.size());
			dbComando = dbConexion.prepareCall(procedure);
			log.info("Procedure: " + procedure);

			log.info("Seteando Parametros...");
			SQLDaoMapper.setParamsSqlServer(dbComando, params);

			log.info("Ejecutando Procedure...");
//			dbComando.executeQuery();
			dbComando.executeUpdate();
			log.info("Procedure ejecutado OK");
			log.info("Procedimiento consultado exitosamente");
			codRetorno = 1;

		} catch (Exception e) {
			log.error("Ha ocurrido un problema al consultar, Detalle > " + e.getMessage());
			codRetorno = -1;
			salida=false;
		} finally {
			try {
				cerrarConexion(dbConexion, dbComando, dbResultados);
				log.info("Conexion Cerrada Exitosamente");
			} catch (SQLException e) {
				log.error("Ha ocurrido un problema al cerrar la conexion, Detalle > " + e.getMessage());
				codRetorno = -1;
				salida=false;
			}
		}

		return salida;

	}
	/**
	 * Obtener los datos del cliente en la busqueda, SQL Server, Se setean los
	 * parametros de entrada y se obtiene simplemente el resultado asumiendo que
	 * este es una lista de respuesta
	 */
	@Override
	public <T> List<T> queryForProcedureSqlServer(String dataSource, Map<Integer, DefinicionParametro> params,
			RowMapper<T> rowMapper, String dbProcedimiento) {

		// Generacion de Objetos
		List<T> models = new ArrayList<>();
		Connection dbConexion = null;
		CallableStatement dbComando = null;
		ResultSet dbResultados = null;

		try {

			log.info("Conectando al recurso DataSource SQLServer...");
			dbConexion = ConnectionFactory.obtenerConexion(dataSource);
			log.info("Conexion OK");

			log.info("Conectando al Procedure");
			String procedure = DiccionarioProcedimientos.armarProcedure(dbProcedimiento, params.size());
			dbComando = dbConexion.prepareCall(procedure);
			log.info("Procedure: " + procedure);

			log.info("Seteando Parametros...");
			SQLDaoMapper.setParamsSqlServer(dbComando, params);

			log.info("Ejecutando Procedure...");
			dbResultados = dbComando.executeQuery();
			log.info("Procedure ejecutado OK");

			while (dbResultados.next()) {
				T model = (T) rowMapper.mapper(dbResultados);
				models.add(model);
			}

			log.info("Procedimiento consultado exitosamente");
			codRetorno = 1;

		} catch (Exception e) {
			log.error("Ha ocurrido un problema al consultar, Detalle > " + e.getMessage());
			codRetorno = -1;
		} finally {
			try {
				cerrarConexion(dbConexion, dbComando, dbResultados);
				log.info("Conexion Cerrada Exitosamente");
			} catch (SQLException e) {
				log.error("Ha ocurrido un problema al cerrar la conexion, Detalle > " + e.getMessage());
				codRetorno = -1;
			}
		}

		return models;

	}
	/**
	 * Metodo que permite ejecutar un procedimiento, definiendo una lista de
	 * parametros de salida. <br>
	 * Para usar este metodo se deben tener claros los parametros de entradas y
	 * salida del procedimiento que se esta ejecutando
	 * 
	 * @param dataSource
	 * @param params
	 * @param rowMapper
	 * @param dbProcedimiento
	 * @return
	 */
	@Override
	public Map<Integer, Object> queryForProcedureSqlServerOutParameters(String dataSource,
			Map<Integer, DefinicionParametro> paramsIn, List<DefinicionOutParametro> paramsOut,
			String dbProcedimiento) {

		// Generacion de Objetos

		Connection dbConexion = null;
		CallableStatement dbComando = null;
		ResultSet dbResultados = null;
		Map<Integer, Object> results = new HashMap<>();
		try {

			log.info(ConstantesBD.CONECTANTO_AL_RECURSO);
			dbConexion = ConnectionFactory.obtenerConexion(dataSource);
			log.info(ConstantesBD.CONEXION_OK);

			log.info(ConstantesBD.CONECTANTO_AL_PROCEDURE);
			String procedure = DiccionarioProcedimientos.armarProcedure(dbProcedimiento,
					paramsIn.size() + paramsOut.size());
			dbComando = dbConexion.prepareCall(procedure);
			log.info(ConstantesBD.PROCEDURE);

			log.info(ConstantesBD.SETEANDO_PARAMETROS);
			SQLDaoMapper.setParamsSqlServer(dbComando, paramsIn);

			for (DefinicionOutParametro d : paramsOut) {
				dbComando.registerOutParameter(d.getIndiceCampo().intValue(), d.getTipoCampoJavaSql());
			}

			dbComando.setQueryTimeout(10);

			log.info(ConstantesBD.EJECUTANDO_PROCEDURE);
			dbComando.execute();

			for (DefinicionOutParametro d : paramsOut) {
				results.put(d.getIndiceCampo(), dbComando.getObject(d.getIndiceCampo()));
			}

		} catch (Exception e) {
			log.error(ConstantesBD.ERROR_AL_CONSULTAR + e.getMessage());
			codRetorno = -1;
		} finally {
			try {
				cerrarConexion(dbConexion, dbComando, dbResultados);
				log.info(ConstantesBD.CONEXION_CERRADA_OK);
			} catch (SQLException e) {
				log.error(ConstantesBD.ERROR_AL_CONSULTAR + e.getMessage());
				codRetorno = -1;
			}
		}

		return results;

	}
	
	/**
	 * Permite cerrar las conexiones
	 * 
	 * @param dbConexion
	 * @param dbComando
	 * @param dbResultados
	 * @throws SQLException
	 */
	private void cerrarConexion(Connection dbConexion, CallableStatement dbComando, ResultSet dbResultados)
			throws SQLException {
		if (dbResultados != null) {
			dbResultados.close();
		}

		if (dbComando != null) {
			dbComando.close();
		}

		if (dbConexion != null) {
			dbConexion.close();
		}
	}

	/**
	 * Permite cerrar las conexiones
	 * 
	 * @param dbConexion
	 * @param dbComando
	 * @param dbResultados
	 * @throws SQLException
	 */
	private void cerrarConexion(Connection dbConexion, CallableStatement dbComando) throws SQLException {

		if (dbComando != null) {
			dbComando.close();
		}

		if (dbConexion != null) {
			dbConexion.close();
		}
	}

	/**
	 * Permite cerrar las conexiones
	 * 
	 * @param dbConexion
	 * @param dbComando
	 * @param dbResultados
	 * @throws SQLException
	 */
	private void cerrarConexion(Connection dbConexion, SQLServerPreparedStatement dbComando) throws SQLException {

		if (dbComando != null) {
			dbComando.close();
		}

		if (dbConexion != null) {
			dbConexion.close();
		}
	}

	/**
	 * Obtener datos adicionales
	 */
	@Override
	public List<String> getSalidaCompuesta() {
		return salidaCompuesta;
	}

	/*
	 * Codigo de retorno de la llamada al servicio
	 */
	@Override
	public int codRetorno() {
		return codRetorno;
	}

}
