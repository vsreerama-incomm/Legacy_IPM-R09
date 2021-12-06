package com.incomm.base24eps.ipm;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.log4j.Logger;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;


public final class  EncryptDecryptUtil {
	
	private final static Logger logger = Logger.getLogger(EncryptDecryptUtil.class);
	
	// 8-byte Salt
    private static byte[] salt = { 
    	(byte)0xA2, (byte)0x11, (byte)0xA5, (byte)0xB2, 
    	(byte)0x44, (byte)0x21, (byte)0xC3, (byte)0xD3
    };
    
	
	//private Cipher ecipher;
	private static final int iterationCount = 10;
	
	public String encrypt(String str, String passPhrase) {
		  
		  try {		 
	            
		    // encode the string into a sequence of bytes using the named charset		 
		    // storing the result into a new byte array.
			  Cipher ecipher =  getECipher(passPhrase);
		 
		    byte[] utf8 = str.getBytes("UTF8");		 
		    byte[] enc = ecipher.doFinal(utf8);
		 
		    // 	encode to base64		 
		    enc = BASE64EncoderStream.encode(enc);		 
		    return new String(enc);		 
		  }catch (Exception e) {		 
		    logger.info("Exception while encrypting String ", e);		 
		  }		 
		  return null;		 
	  }
	  
	  
	  private static SecretKey getSecretKey(String passPhrase){
		  try {
			// create a user-chosen password that can be used with password-based encryption (PBE)
	          // provide password, salt, iteration count for generating PBEKey of fixed-key-size PBE ciphers
	          KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);

	          // create a secret (symmetric) key using PBE with MD5 and DES
	          SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
	          return key;
		} catch (InvalidKeySpecException e) {
            logger.error("Invalid Key Spec:" + e.getMessage());
            return null;
        } catch (NoSuchAlgorithmException e) {
	           logger.error("No Such Algorithm:" + e.getMessage());
	            return null;
	        }
		  
	  }
	  
	  private Cipher getECipher(String passPhrase) {
		  
		  Cipher ecipher = null;
		  try {
	            
	            SecretKey key = getSecretKey(passPhrase);
	 
	            ecipher = Cipher.getInstance(key.getAlgorithm());
	 
	         // construct a parameter set for password-based encryption as defined in the PKCS #5 standard
	            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
	            
	            // initialize the ciphers with the given key	 
	            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);         
	 
	        }
	        catch (NoSuchAlgorithmException e) {
	            logger.error("No Such Algorithm:" + e.getMessage());
	            return null;
	        }catch (NoSuchPaddingException e) {
	            logger.error("No Such Padding:" + e.getMessage());
	            return null;
	        }catch (InvalidAlgorithmParameterException e) {
	            logger.error("No Such Padding:" + e.getMessage());
	            return null;
	        }catch (InvalidKeyException e) {
	            logger.error("No Such Padding:" + e.getMessage());
	            return null;
	        }
		  return ecipher;
		  
	  }

	public String decrypt(String str, String passPhrase) {

		try {
			Cipher dcipher = getDCipher(passPhrase);

			// decode with base64 to get bytes

			byte[] dec = BASE64DecoderStream.decode(str.getBytes());

			byte[] utf8 = dcipher.doFinal(dec);

			// create new string based on the specified charset

			return new String(utf8, "UTF8");

		}catch (Exception e) {
			logger.info("Exception while decrypting String ", e);
		}
		return null;

	}
	  private Cipher getDCipher(String passPhrase) {
		  
		  Cipher dcipher = null;
		  try {
	            
	            SecretKey key = getSecretKey(passPhrase);
	 
	            dcipher = Cipher.getInstance(key.getAlgorithm());
	 
	         // construct a parameter set for password-based encryption as defined in the PKCS #5 standard
	            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
	            
	            // initialize the ciphers with the given key	 
	            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);         
	 
	        }
	        catch (NoSuchAlgorithmException e) {
	           logger.error("No Such Algorithm:" + e.getMessage());
	            return null;
	        }catch (NoSuchPaddingException e) {
	        	logger.error("No Such Padding:" + e.getMessage());
	            return null;
	        }catch (InvalidAlgorithmParameterException e) {
	        	logger.error("No Such Padding:" + e.getMessage());
	            return null;
	        }catch (InvalidKeyException e) {
	        	logger.error("No Such Padding:" + e.getMessage());
	            return null;
	        }
		  return dcipher;
		  
	  }
	  
	  public static void main(String[] args) {
		try {
			
			String passPhrase = "Srinivas!Yer456a7!!";
			String encrypted = new EncryptDecryptUtil().encrypt("11007E36A541A8E4F0081660114065395030890000000000000001500000000002000000000002000820145902032177130820105902200713082008218405003010640011005999040000040000326011406539503089=200752110000155201059001823000000000000000 00000000000000043ABCD1234 A StreetAtlanta0000000000GA USA03400840D0000005000000000C00000000840840124124D4CBCDC5FC3D3E89121001008014 0 02003040PRO1      DSCV      DS        DS        005004PULS018006L30RET0190011020005PULSE022003PUL023006INTL11", passPhrase);
			System.out.println("encrypted: " + encrypted);
			String decrypted = new EncryptDecryptUtil().decrypt(encrypted, passPhrase);
			 
			System.out.println("Decrypted: " + decrypted);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	  

}
