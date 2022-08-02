package recoverpass.dao.mappers.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import recoverpass.apis.bean.DtoOutClavesTemporales;
import recoverpass.apis.dao.mappers.schema.RowMapper;

public class GetClavesTemporalesMapper implements RowMapper<DtoOutClavesTemporales> {

	@Override
	public DtoOutClavesTemporales mapper(ResultSet paramResultSet) throws SQLException {
		DtoOutClavesTemporales dtoOutClavesTemporales = new DtoOutClavesTemporales();

		dtoOutClavesTemporales.setRutUsuario(paramResultSet.getString(1));
		dtoOutClavesTemporales.setClaveTemporal(paramResultSet.getString(2));

		return dtoOutClavesTemporales;
			
	}

}
