package recoverpass.dao.mappers.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import recoverpass.apis.bean.DtoOutTienePreguntasSecretas;
import recoverpass.apis.dao.mappers.schema.RowMapper;

public class GetTienePreguntasSecretasMapper implements RowMapper<DtoOutTienePreguntasSecretas> {

	@Override
	public DtoOutTienePreguntasSecretas mapper(ResultSet paramResultSet) throws SQLException {
		DtoOutTienePreguntasSecretas dtoOutTienePreguntasSecretas = new DtoOutTienePreguntasSecretas();
		
		dtoOutTienePreguntasSecretas.setTienePreguntas(String.valueOf(paramResultSet.getInt(1)));

		return dtoOutTienePreguntasSecretas;
			
	}

}
