/**
 * 
 */
package com.jsc.pwd.services.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.jsc.pwd.services.PasswordEncryptionService;

/**
 * @author jsc
 *
 */
public class TestPasswordEncryptionService {
	
	
	
	private static final String hashAlgorithm = "PBKDF2WithHmacSHA1";
	private static final String saltAlgorithm = "SHA1PRNG";
	
	String testPassword = "()ThisIsATest!@44";

	/**
	 * Test method for {@link com.jsc.pwd.util.PasswordEncryptionService#authenticate(java.lang.String, byte[], byte[])}.
	 */
	@Test
	public void testAuthenticate() {
		byte[] lSalt = null;
	    byte[] lEncPwd = null;
	    boolean isValidPwd = false;
	    boolean isNotValidPwd = false;
	    String lFooBarPwd = "FooBarPassword";

		
		   try{
			      lSalt = PasswordEncryptionService.generateSalt();
			      lEncPwd = PasswordEncryptionService.getEncryptedPassword(testPassword, lSalt);
			   } catch(NoSuchAlgorithmException nsa){
					nsa.printStackTrace();
					fail("Failed - NoSuchAlgorithmException");
	    	   } catch(InvalidKeySpecException ikse){
	    		   ikse.printStackTrace();
	    		   fail("Failed - InvalideKeySpecException");
	    	   }
			
               // test if we can authenticate,check for valid and not valid
		   
		    try{
		    	isValidPwd = PasswordEncryptionService.authenticate(testPassword, lEncPwd, lSalt);
		    	isNotValidPwd = PasswordEncryptionService.authenticate(lFooBarPwd, lEncPwd, lSalt);
			   } catch(NoSuchAlgorithmException nsa){
					nsa.printStackTrace();
					fail("Failed - NoSuchAlgorithmException");
	    	   } catch(InvalidKeySpecException ikse){
	    		   ikse.printStackTrace();
	    		   fail("Failed - InvalideKeySpecException");
	    	   }
	
		      assertTrue(isValidPwd);
		      assertFalse(isNotValidPwd);
	
	
	}

	/**
	 * Test method for {@link com.jsc.pwd.util.PasswordEncryptionService#getEncryptedPassword(java.lang.String, byte[])}.
	 */
	@Test
	public void testGetEncryptedPassword() {
			
		byte[] lSalt1 = null;
		byte[] lSalt2 = null;
		byte[] lEncPwd2 = null;
		byte[] lEncPwd1 = null;


		   
		   try{
		      lSalt1 = PasswordEncryptionService.generateSalt();
		      lEncPwd1 = PasswordEncryptionService.getEncryptedPassword(testPassword, lSalt1);
		      lEncPwd2 = PasswordEncryptionService.getEncryptedPassword(testPassword, lSalt1);
		   } catch(NoSuchAlgorithmException nsa){
				nsa.printStackTrace();
				fail("Failed - NoSuchAlgorithmException");
    	   } catch(InvalidKeySpecException ikse){
    		   ikse.printStackTrace();
    		   fail("Failed - InvalideKeySpecException");
    	   }
		
		   // Actual test is that we did not throw exception
		   
		   // Double check they are equal using same Salt
	       assertTrue(Arrays.equals(lEncPwd1, lEncPwd2));
	       
		   try{
			      lSalt1 = PasswordEncryptionService.generateSalt();
			      lSalt2 = PasswordEncryptionService.generateSalt();
			      lEncPwd1 = PasswordEncryptionService.getEncryptedPassword(testPassword, lSalt1);
			      lEncPwd2 = PasswordEncryptionService.getEncryptedPassword(testPassword, lSalt2);
			   } catch(NoSuchAlgorithmException nsa){
					nsa.printStackTrace();
					fail("Failed - NoSuchAlgorithmException");
	    	   } catch(InvalidKeySpecException ikse){
	    		   ikse.printStackTrace();
	    		   fail("Failed - InvalideKeySpecException");
	    	   }
		   
		   // Check to make sure pwds are unique with different salts
		   assertFalse(Arrays.equals(lEncPwd1, lEncPwd2));
	       
	       
	
	}

	/**
	 * Test method for {@link com.jsc.pwd.util.PasswordEncryptionService#generateSalt()}.
	 * Check to make sure generated salt values are unique
	 */
	@Test
	public void testGenerateSalt() {
		
		byte[] lSalt1 = null;
		byte[] lSalt2 = null;
		
		try{
		  lSalt1 = PasswordEncryptionService.generateSalt();
		  lSalt2 = PasswordEncryptionService.generateSalt();
		}
		catch (NoSuchAlgorithmException nsa){
			nsa.printStackTrace();
			fail("NoSuchAlgorithmException - ");
		}
 

		assertFalse(Arrays.equals(lSalt1, lSalt2));
	}

}
