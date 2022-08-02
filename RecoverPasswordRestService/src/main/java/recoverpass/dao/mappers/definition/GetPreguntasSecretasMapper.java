package recoverpass.dao.mappers.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import recoverpass.apis.bean.DtoOutPreguntasSecretas;
import recoverpass.apis.dao.mappers.schema.RowMapper;

public class GetPreguntasSecretasMapper implements RowMapper<DtoOutPreguntasSecretas> {

	@Override
	public DtoOutPreguntasSecretas mapper(ResultSet paramResultSet) throws SQLException {

		DtoOutPreguntasSecretas dtoOutPreguntasSecretas = new DtoOutPreguntasSecretas();
		
		dtoOutPreguntasSecretas.setIdUsuario(String.valueOf(paramResultSet.getLong(1)));
		dtoOutPreguntasSecretas.setPregunta(paramResultSet.getString(2));
		dtoOutPreguntasSecretas.setId(String.valueOf(paramResultSet.getInt(3)));
		dtoOutPreguntasSecretas.setRespuesta(paramResultSet.getString(4));
		
		return dtoOutPreguntasSecretas;
			
	}

}
