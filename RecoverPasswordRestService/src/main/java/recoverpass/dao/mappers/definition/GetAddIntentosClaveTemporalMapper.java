package recoverpass.dao.mappers.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import recoverpass.apis.bean.DtoOutAddIntentosClaveTemporal;
import recoverpass.apis.dao.mappers.schema.RowMapper;

public class GetAddIntentosClaveTemporalMapper implements RowMapper<DtoOutAddIntentosClaveTemporal> {

	@Override
	public DtoOutAddIntentosClaveTemporal mapper(ResultSet paramResultSet) throws SQLException {
		DtoOutAddIntentosClaveTemporal DtoOutAddIntentosCT = new DtoOutAddIntentosClaveTemporal();
		DtoOutAddIntentosCT.setCodigo(paramResultSet.getInt(1));
		DtoOutAddIntentosCT.setMensaje(paramResultSet.getString(2));

		return DtoOutAddIntentosCT;
			
	}

}
