/**
 * 
 */
package recoverpass.apis.helper;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase utilitaria para el manejo de texto en formato JSON.
 * 
 *
 */
public class JsonUtilities {

	/**
	 * Método estatico que transforma un texto con fomarto JSON a un objeto de tipo DTO. <br>
	 * Ej: DtoNew dtoNew = (DtoNew) JsonUtilities.convertJSONToDto(json,
	 * DtoNew.class);
	 * 
	 * @param textoJSON
	 * @param dto
	 * 
	 * @return Un objeto que debe ser casteado a la clase que se requiera.
	 */
	public static Object convertJSONToDto(String textoJSON, Object dto) {
		try {
			Object o = null;
			Class<?> newClass = (Class<?>) dto;
			byte[] b = textoJSON.getBytes();
			ObjectMapper objectMapper = new ObjectMapper();
			o = objectMapper.readValue(b, newClass);
			return o;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
