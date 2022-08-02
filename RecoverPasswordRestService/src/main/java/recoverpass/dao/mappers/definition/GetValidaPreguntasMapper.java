package recoverpass.dao.mappers.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import recoverpass.apis.bean.DtoOutValidaPreguntas;
import recoverpass.apis.dao.mappers.schema.RowMapper;

public class GetValidaPreguntasMapper implements RowMapper<DtoOutValidaPreguntas> {

	@Override
	public DtoOutValidaPreguntas mapper(ResultSet paramResultSet) throws SQLException {
		DtoOutValidaPreguntas dtoOutValidaPreguntas = new DtoOutValidaPreguntas();

		dtoOutValidaPreguntas.setEstadoRespuestaUno(String.valueOf(paramResultSet.getInt(1)));
		dtoOutValidaPreguntas.setEstadoRespuestaDos(String.valueOf(paramResultSet.getInt(2)));

		return dtoOutValidaPreguntas;
			
	}

}
