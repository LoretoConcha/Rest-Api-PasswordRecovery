package recoverpass.dao.mappers.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import recoverpass.apis.bean.DtoOutRecuperarReintentos;
import recoverpass.apis.dao.mappers.schema.RowMapper;

public class GetRecuperarReintentosMapper implements RowMapper<DtoOutRecuperarReintentos> {

	@Override
	public DtoOutRecuperarReintentos mapper(ResultSet paramResultSet) throws SQLException {
			DtoOutRecuperarReintentos dtoOutRecuperarReintentos = new DtoOutRecuperarReintentos();
					
			dtoOutRecuperarReintentos.setReintentos(String.valueOf(paramResultSet.getInt(1)));

			return dtoOutRecuperarReintentos;
	}

}
