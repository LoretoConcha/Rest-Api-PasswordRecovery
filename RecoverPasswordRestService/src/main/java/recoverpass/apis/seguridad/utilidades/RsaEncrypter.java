package recoverpass.apis.seguridad.utilidades;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.log4j.Logger;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;

/**
 * Esta clase contiene metodos para encriptar y desencriptar arreglos bytes
 * encriptados con algoritmo RSA
 * 
 * @author
 *
 */
public class RsaEncrypter {

	/**
	 * LOGGER
	 */
	private static final Logger log = Logger.getLogger(RsaEncrypter.class);

	/**
	 * Obtiene el par de llaves RSA, desde un archivo .pem que contiene una llave
	 * privada RSA. Este metodo se usa internamente para encriptar y desencriptar,
	 * por lo que es obligatorio tener acceso a una llave privada RSA.<br>
	 * Para crear el par de llaves se debe ejecutar el programa
	 * <strong>openssl</strong><br>
	 * Un ejemplo del comando para crear el par de llaves es el siguiente: <br>
	 * 
	 * 
	 * 
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	private static KeyPair obtenerKeyPair() {
		KeyPair kp = null;
		PEMParser pemParser = null;
		try {
			File f = new File(ConstantesSeguridad.RUTA_LLAVE_PRIVADA_RSA);
			pemParser = new PEMParser(new FileReader(f));
			Object object = pemParser.readObject();
			JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
			// En caso que la llave publica cuente con contraseña de seguridad se debe
			// establecer aqui
			String password = "";

			if (object instanceof PEMEncryptedKeyPair) {
				PEMEncryptedKeyPair ckp = (PEMEncryptedKeyPair) object;
				PEMDecryptorProvider decProv = new JcePEMDecryptorProviderBuilder().build(password.toCharArray());
				kp = converter.getKeyPair(ckp.decryptKeyPair(decProv));
			} else {
				// No necesita password
				PEMKeyPair ukp = (PEMKeyPair) object;
				kp = converter.getKeyPair(ukp);
			}
		} catch (Exception e) {
			log.error(ConstantesSeguridad.ERROR_EN_EJECUCION + e.getCause());
		} finally {
			try {
				if (pemParser != null)
					pemParser.close();
			} catch (IOException e) {
				log.error(ConstantesSeguridad.ERROR_EN_EJECUCION + e.getCause());
			}
		}
		return kp;
	}

	/**
	 * Desencripta un string encriptado con algoritmo RSA y codificado en Base64
	 * 
	 * @param bytes
	 * @return El texto original desencriptado
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String desencriptartRsa(String textoEncriptado)
			throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeySpecException,
			IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher xCipher = Cipher.getInstance("RSA/NONE/OAEPWithSHA1AndMGF1Padding", "BC");
		KeyPair privateKey = obtenerKeyPair();
		if (privateKey == null)
			return null;
		xCipher.init(Cipher.DECRYPT_MODE, privateKey.getPrivate());
		byte[] original = xCipher.doFinal(textoEncriptado.getBytes("UTF-8"));
		return new String(original);
	}

	/**
	 * Encripta un arreglo de bytes con algoritmo RSA
	 * 
	 * @param textoEncriptar
	 * @param publicKey
	 * @return El texto original encriptado con algoritmo RSA y codificado en
	 *         Base64.
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public static String encriptarRsa(String textoEncriptar, PublicKey publicKey)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException {
		SecureRandom secureRandom = new SecureRandom();
		Cipher xCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
		xCipher.init(Cipher.ENCRYPT_MODE, publicKey, secureRandom);
		byte[] bytesEncrypted = xCipher.doFinal(textoEncriptar.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(bytesEncrypted);

	}

}