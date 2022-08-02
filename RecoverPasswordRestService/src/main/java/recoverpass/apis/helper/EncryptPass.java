package recoverpass.apis.helper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import com.itextpdf.text.pdf.codec.Base64;

public class EncryptPass {

	// llave de 64 bits para encriptar datos
	private static final String salt = Constantes.SALTRECUPERAR;
	private static final String formato = Constantes.MODO_UTF8;

	/**
	 * LOGGER
	 */
	private static final Logger log = Logger.getLogger(EncryptPass.class);
			
	/**
	 * 
	 * @param password
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public static String computeHash(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, NullPointerException
    {
       
       String hashValue=null;
       byte[] saltBytes =null;
       byte[] plainTextBytes =null;
       byte[] hashBytes =null;
       byte[] hashWithSaltBytes = null;
        
   	log.info("EncryptPass- computeHash(): salt: "+salt);

        try {
			if (password == null || salt == null) {
				log.info("EncryptPass- computeHash(): Tanto password como salt no deben ser null");
			}
			
			else {

			//Convertimos ambas cadenas en arreglos de bytes
			
			saltBytes = salt.getBytes(formato);
			plainTextBytes =  password.getBytes(formato);

			// Allocate array, which will hold plain text and salt.
			byte[] plainTextWithSaltBytes = new byte[plainTextBytes.length + saltBytes.length];

			// Copy plain text bytes into resulting array.
			for (int i = 0; i < plainTextBytes.length; i++)
			    plainTextWithSaltBytes[i] = plainTextBytes[i];

			// Append salt bytes to the resulting array.
			for (int i = 0; i < saltBytes.length; i++)
			    plainTextWithSaltBytes[plainTextBytes.length + i] = saltBytes[i];

			MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
			
			// Compute hash value of our plain text with appended salt.
			hashBytes = sha512.digest(plainTextWithSaltBytes);

			// Create array which will hold hash and original salt bytes.
			hashWithSaltBytes = new byte[hashBytes.length + saltBytes.length];

			// Copy hash bytes into resulting array.
			for (int i = 0; i < hashBytes.length; i++)
			    hashWithSaltBytes[i] = hashBytes[i];

			// Append salt bytes to the result.
			for (int i = 0; i < saltBytes.length; i++)
			    hashWithSaltBytes[hashBytes.length + i] = saltBytes[i];

			// Convert result into a base64-encoded string.
			hashValue = Base64.encodeBytes(hashWithSaltBytes);
			log.info("EncryptPass- hashValue(): "+hashValue);
			}
		} catch (Exception e) {
			log.error("EncryptPass()-computeHash(): Ha ocurrido un error al encriptar clave: "+e);
		}
       
        return hashValue;
    }
	
	
	
	
}
	