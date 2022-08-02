package recoverpass.apis.dao;

import java.util.List;
import java.util.Map;

import recoverpass.apis.bean.DefinicionOutParametro;
import recoverpass.apis.bean.DefinicionParametro;
import recoverpass.apis.dao.mappers.schema.RowMapper;

/**
 * Interface para el dao de obtener la data desde las bases de datos
 * 
 *
 */
public interface QueryDao {
	
	public boolean queryForProcedureSqlServerUpdate(String dataSource, Map<Integer, DefinicionParametro> params,
			String dbProcedimiento);
	/**
	 * Permite consultar un procedure ubicado en una base de datos Microsoft SQL
	 * Server
	 * 
	 * @param dataSource
	 *            Datasource hacia el cual se conectara el metodo, debe estar en
	 *            la properties
	 * @param params
	 *            Parametros de entrada para el procedimiento
	 * @param rowMapper
	 *            Mapeo de salida que retornara este metodo
	 * @param dbProcedimiento
	 *            Nombre del procedure a llamar
	 * @return Lista de respuesta generica
	 */
	public <T> List<T> queryForProcedureSqlServer(String dataSource, Map<Integer, DefinicionParametro> params,
			RowMapper<T> rowMapper, String dbProcedimiento);

	/**
	 * Método que permite ejecutar un procedimiento, definiendo una lista de
	 * parametros de salida. <br>
	 * Para usar este metodo se deben tener claros los parametros de entradas y
	 * salida del procedimiento que se esta ejecutando.
	 * 
	 * @param dataSource
	 * @param paramsIn
	 * @param paramsOut
	 * @param dbProcedimiento
	 * @return
	 */
	public Map<Integer, Object> queryForProcedureSqlServerOutParameters(String dataSource,
			Map<Integer, DefinicionParametro> paramsIn, List<DefinicionOutParametro> paramsOut, String dbProcedimiento);

		/**
	 * Permite obtener parametros adicionales
	 * 
	 * @return
	 */
	public List<String> getSalidaCompuesta();

	/**
	 * Codigo de retorno de la llamada al servicio
	 * 
	 * @return
	 */
	public int codRetorno();

}
