package recoverpass.dao.mappers.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import recoverpass.apis.bean.DtoOutRecuperarClaveTemporal;
import recoverpass.apis.dao.mappers.schema.RowMapper;

public class GetRecuperarClaveTemporalMapper implements RowMapper<DtoOutRecuperarClaveTemporal> {

	@Override
	public DtoOutRecuperarClaveTemporal mapper(ResultSet paramResultSet) throws SQLException {
		DtoOutRecuperarClaveTemporal dtoOutRecuperarClaveTemporal = new DtoOutRecuperarClaveTemporal();
		
		dtoOutRecuperarClaveTemporal.setIdUsuario(paramResultSet.getString(1));
		dtoOutRecuperarClaveTemporal.setClaveTemporal(paramResultSet.getString(2));
		dtoOutRecuperarClaveTemporal.setIntentos(paramResultSet.getInt(3));
		dtoOutRecuperarClaveTemporal.setIdClaveTemporal(paramResultSet.getLong(4));

		return dtoOutRecuperarClaveTemporal;
			
	}

}
