package recoverpass.dao.mappers.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import recoverpass.apis.bean.DtoOutUserData;
import recoverpass.apis.dao.mappers.schema.RowMapper;

public class GetDatosUsuarioMapper implements RowMapper<DtoOutUserData> {

	@Override
	public DtoOutUserData mapper(ResultSet paramResultSet) throws SQLException {
		DtoOutUserData dtoOutUserData = new DtoOutUserData();
		dtoOutUserData.setNameUser(paramResultSet.getString(2).trim());
		dtoOutUserData.setLastNameUser(paramResultSet.getString(3).trim());
		dtoOutUserData.setEmail(paramResultSet.getString(8).trim());
		dtoOutUserData.setPassEstado(String.valueOf(paramResultSet.getInt(16)));

		return dtoOutUserData;
			
	}

}
