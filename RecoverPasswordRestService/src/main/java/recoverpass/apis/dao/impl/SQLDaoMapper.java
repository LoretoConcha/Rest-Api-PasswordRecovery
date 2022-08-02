/**
 * 
 */
package recoverpass.apis.dao.impl;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;

import recoverpass.apis.bean.DefinicionParametro;
import recoverpass.apis.helper.ConstantesBD;

/**
 * Funciones y mapeos para los DAOS
 *
 */
public class SQLDaoMapper {

	/**
	 * LOGGER
	 */
	private static final Logger log = Logger.getLogger(SQLDaoMapper.class);

	/**
	 * Permite setear los parametros de manera dinamica por cada uno de los
	 * procedimientos
	 * 
	 * @param dbComando
	 * @throws SQLException
	 */
	public static void setParamsSqlServer(SQLServerPreparedStatement dbComando,
			Map<Integer, DefinicionParametro> inputParams) throws SQLException {

		if (dbComando != null && inputParams.size() > 0) {

			int cont = 1;
			for (Entry<Integer, DefinicionParametro> entry : inputParams.entrySet()) {
				log.info("Asignare en la posicion " + cont + " del SP el valor: " + entry.getValue().getValorCampo());

				SQLDaoMapper.mappingDataToType(dbComando, cont, entry);

				cont++;
			}

		}

	}
	/**
	 * Permite setear los parametros de manera dinamica por cada uno de los
	 * parametros del procedimiento, ademas de esto no importa en donde este la
	 * posicion del cursor (ya que usualmente este submetodo se utiliza en
	 * procedimientos que no entregan cursores de salida)
	 * 
	 * @param dbComando
	 * @param inputParams
	 *            Parametros de entrada
	 * @param outputParams
	 *            Definicion con los parametros de salida
	 * @throws SQLException
	 */
	public static int setParamsOracleInsertUpdate(CallableStatement dbComando, Map<Integer, DefinicionParametro> inputParams, Map<Integer, Integer> outputParams)
			throws SQLException {

		if (dbComando != null && inputParams.size() > 0 && outputParams.size() > 0) {

			int posicionCursor = 0;

			// Agregar los parametros de entrada
			log.info("ASIGNAR ENTRADAS DEL SP ORACLE");

			int cont = 1;
			for (Map.Entry<Integer, DefinicionParametro> entry : inputParams.entrySet()) {
				log.info("Asignare en la posicion " + cont + " del SP el valor: " + entry.getValue().getValorCampo());

				SQLDaoMapper.mappingDataToType(dbComando, cont, entry);
				cont++;
			}

			/*
			 * Agregar los parametros de salida, se deben de comenzar a agregar los
			 * parametros desde el ultimo que se dejo en los parametros de entrada
			 */
			log.info("ASIGNAR PARAMETROS DE SALIDA DEL SP ORACLE");
			for (Map.Entry<Integer, Integer> entry : outputParams.entrySet()) {

				int fixedLenght = cont;
				log.info("Asignare en la posicion " + fixedLenght + " del SP el valor: " + entry.getValue());
				dbComando.registerOutParameter(fixedLenght, entry.getValue());

				cont++;

			}

			return posicionCursor;

		}

		return 0;

	}
	/**
	 * Permite mapear los parametros de salida para el procedure deseado
	 * 
	 * @param dbComando
	 * 
	 * @param outParams
	 * @return
	 */
	public static LinkedHashMap<Integer, Object> getReturnParamsMapping(CallableStatement dbComando, Map<Integer, Integer> outParams) {

		LinkedHashMap<Integer, Object> res = new LinkedHashMap<>();

		for (Entry<Integer, Integer> entry : outParams.entrySet()) {
			int pos = entry.getKey();
			int tipoData = entry.getValue();

			Object campo = null;

			try {
				campo = obtenerTipoDatoMapping(dbComando, pos, tipoData);
			} catch (SQLException e) {
				log.error("Ha ocurrido un error al obtener el mapeo del campo");
				log.error("Posicion: " + pos + " , TipoData: " + tipoData);
			}

			log.info("Campo Salida Numero: " + pos + " , Contenido: " + campo);
			res.put(pos, campo);
		}

		return res;

	}
	/**
	 * Obtener el dato segun sea el tipo de mapeo
	 * 
	 * @param dbComando
	 * @param pos
	 * @param tipoData
	 * @return
	 * @throws SQLException
	 */
	public static Object obtenerTipoDatoMapping(CallableStatement dbComando, int pos, int tipoData) throws SQLException {

		Object res = new Object();

		switch (tipoData) {
		case java.sql.Types.NUMERIC:
			res = (Integer) dbComando.getInt(pos);
			break;

		case java.sql.Types.VARCHAR:
			res = (String) dbComando.getString(pos);
			break;

		case java.sql.Types.DATE:
			res = (java.util.Date) dbComando.getDate(pos);
			break;
		}

		return res;

	}
	/**
	 * Permite obtener datos que sean distintos de los del resultset
	 * 
	 * @param metadataParametros
	 * 
	 * @param dbComando
	 * @throws SQLException
	 */
	public static List<String> extraerParametrosAdicionales(Map<Integer, String> metadataParametros,
			CallableStatement dbComando) throws SQLException {

		List<String> res = new ArrayList<>();
		if (metadataParametros.size() > 0) {

			for (Map.Entry<Integer, String> entry : metadataParametros.entrySet()) {

				switch (entry.getValue()) {

				case ConstantesBD.TIPO_VARCHAR:
					res.add(dbComando.getString(entry.getKey()));
					break;

				case ConstantesBD.TIPO_INTEGER:
					res.add(String.valueOf(dbComando.getInt(entry.getKey())));
					break;

				}

			}

		}

		return res;

	}

	/**
	 * Permite setear los parametros de manera dinamica por cada uno de los
	 * procedimientos
	 * 
	 * @param dbComando
	 * @param inputParams
	 *            Parametros de entrada
	 * @param outputParams
	 *            Definicion con los parametros de salida
	 * @throws SQLException
	 */
	public static HashMap<Integer, Map<Integer, String>> setParamsOracle(CallableStatement dbComando,
			Map<Integer, DefinicionParametro> inputParams, Map<String, Integer> outputParams) throws SQLException {

		HashMap<Integer, Map<Integer, String>> res = new HashMap<>();

		if (dbComando != null && inputParams.size() > 0 && outputParams.size() > 0) {

			int posicionCursor = 0;

			// Agregar los parametros de entrada
			log.info("ASIGNAR ENTRADAS DEL SP ORACLE");

			int cont = 1;
			for (Map.Entry<Integer, DefinicionParametro> entry : inputParams.entrySet()) {
				log.info("Asignare en la posicion " + cont + " del SP el valor: " + entry.getValue().getValorCampo());

				SQLDaoMapper.mappingDataToType(dbComando, cont, entry);
				cont++;
			}

			/*
			 * Agregar los parametros de salida, se deben de comenzar a agregar
			 * los parametros desde el ultimo que se dejo en los parametros de
			 * entrada
			 */
			log.info("ASIGNAR PARAMETROS DE SALIDA DEL SP ORACLE");
			Map<Integer, String> metadataParametros = new HashMap<>();
			for (Map.Entry<String, Integer> entry : outputParams.entrySet()) {

				int fixedLenght = cont;
				log.info("Asignare en la posicion " + fixedLenght + " del SP el valor: " + entry.getValue());

				metadataParametros.put(fixedLenght, entry.getKey());

				dbComando.registerOutParameter(fixedLenght, entry.getValue());

				// Comprobar si este es el cursor de la lista
				if (entry.getKey().equals("CURSOR")) {
					posicionCursor = fixedLenght;
				}

				cont++;

			}

			res.put(posicionCursor, metadataParametros);

		}

		return res;

	}

	/**
	 * Permite Contrastar los datos desde los procedures hacia los que iran a la
	 * base de datos
	 * 
	 * @param dbComando
	 * @param cont
	 * @param entry
	 * @throws SQLException
	 */
	public static void mappingDataToType(CallableStatement dbComando, int cont,
			Map.Entry<Integer, DefinicionParametro> entry) throws SQLException {

		switch (entry.getValue().getTipoCampo()) {
		case ConstantesBD.TIPO_VARCHAR:
			dbComando.setString(cont, String.valueOf(entry.getValue().getValorCampo()));
			break;

		case ConstantesBD.TIPO_NUMERIC:
			dbComando.setInt(cont, Integer.parseInt(entry.getValue().getValorCampo().toString()));
			break;

		case ConstantesBD.TIPO_INTEGER:
			dbComando.setInt(cont, Integer.parseInt(entry.getValue().getValorCampo().toString()));
			break;

		case ConstantesBD.TIPO_FLOAT:
			dbComando.setFloat(cont, Float.parseFloat(entry.getValue().getValorCampo().toString()));
			break;

		case ConstantesBD.TIPO_SHORT:
			dbComando.setShort(cont, Short.parseShort(entry.getValue().getValorCampo().toString()));
			break;

		case ConstantesBD.TIPO_DATE:
			java.sql.Date tipoDataDate = (java.sql.Date) entry.getValue().getValorCampo();
			dbComando.setDate(cont, tipoDataDate);
			break;

		case ConstantesBD.TIPO_DATETIME:
			java.sql.Date tipoData = (java.sql.Date) entry.getValue().getValorCampo();
			dbComando.setDate(cont, tipoData);
			break;

		case ConstantesBD.TIPO_LONG:
			dbComando.setLong(cont, Long.parseLong(entry.getValue().getValorCampo().toString()));
			break;

		case ConstantesBD.TIPO_NULL:
			dbComando.setNull(cont, java.sql.Types.NULL);
			break;

		}

	}

	/**
	 * Permite Contrastar los datos desde los procedures hacia los que iran a la
	 * base de datos
	 * 
	 * @param dbComando
	 * @param cont
	 * @param entry
	 * @throws SQLException
	 */
	public static void mappingDataToType(SQLServerPreparedStatement dbComando, int cont,
			Map.Entry<Integer, DefinicionParametro> entry) throws SQLException {

		switch (entry.getValue().getTipoCampo()) {
		case ConstantesBD.TIPO_VARCHAR:
			dbComando.setString(cont, String.valueOf(entry.getValue().getValorCampo()));
			break;

		case ConstantesBD.TIPO_NUMERIC:
			dbComando.setInt(cont, Integer.parseInt(entry.getValue().getValorCampo().toString()));
			break;

		case ConstantesBD.TIPO_INTEGER:
			dbComando.setInt(cont, Integer.parseInt(entry.getValue().getValorCampo().toString()));
			break;

		case ConstantesBD.TIPO_FLOAT:
			dbComando.setFloat(cont, Float.parseFloat(entry.getValue().getValorCampo().toString()));
			break;

		case ConstantesBD.TIPO_LONG:
			dbComando.setLong(cont, Long.parseLong(entry.getValue().getValorCampo().toString()));
			break;

		case ConstantesBD.TIPO_DATETIME:
			java.sql.Date tipoDataDateTime = (java.sql.Date) entry.getValue().getValorCampo();
			dbComando.setDate(cont, tipoDataDateTime);
			break;

		case ConstantesBD.TIPO_DATE:
			java.sql.Date tipoDataDate = (java.sql.Date) entry.getValue().getValorCampo();
			dbComando.setDate(cont, tipoDataDate);
			break;

		case ConstantesBD.TIPO_STRUCTURE:
			@SuppressWarnings("unchecked")
			Map<String, SQLServerDataTable> definicionData = (Map<String, SQLServerDataTable>) entry.getValue()
					.getValorCampo();

			for (Map.Entry<String, SQLServerDataTable> tipoTabla : definicionData.entrySet()) {
				dbComando.setStructured(cont, tipoTabla.getKey(), tipoTabla.getValue());
			}

			break;

		case ConstantesBD.TIPO_SHORT:
			dbComando.setShort(cont, Short.parseShort(entry.getValue().getValorCampo().toString()));
			break;

		}

	}

	/**
	 * Permite setear los parametros de manera dinamica por cada uno de los
	 * procedimientos
	 * 
	 * @param dbComando
	 * @throws SQLException
	 */
	public static void setParamsSqlServer(CallableStatement dbComando, Map<Integer, DefinicionParametro> inputParams)
			throws SQLException {

		if (dbComando != null && inputParams.size() > 0) {

			int cont = 1;
			for (Entry<Integer, DefinicionParametro> entry : inputParams.entrySet()) {
				log.info("Asignare en la posicion " + cont + " del SP el valor: " + entry.getValue().getValorCampo());

				SQLDaoMapper.mappingDataToType(dbComando, cont, entry);

				cont++;
			}

		}

	}

}
