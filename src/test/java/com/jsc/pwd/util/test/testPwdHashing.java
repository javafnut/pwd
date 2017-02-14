package com.jsc.pwd.util.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jsc.pwd.services.PasswordEncryptionService;

public class testPwdHashing {
	
	 private String pwd = "()ThisIsATest!@44";
	 private byte[] lSalt = null;
     private byte[] lEncPwd = null;
	 private boolean isValid = false;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPasswordHashing() {
		
		try{
			lSalt = PasswordEncryptionService.generateSalt();
			lEncPwd = PasswordEncryptionService.getEncryptedPassword(pwd, lSalt);
		} catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		
		assertFalse(lSalt == null & lEncPwd == null);

		try{
			isValid = PasswordEncryptionService.authenticate(pwd,lEncPwd,lSalt);
		} 
		catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		
		assertTrue(isValid);

	}

}
