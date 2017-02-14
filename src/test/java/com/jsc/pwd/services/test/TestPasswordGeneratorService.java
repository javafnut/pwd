package com.jsc.pwd.services.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jsc.pwd.services.PasswordGeneratorService;
import com.jsc.pwd.model.PasswordCriteria;

public class TestPasswordGeneratorService {
	
	PasswordGeneratorService  passwordGenerator = null;
	PasswordCriteria criteria = null;

	@Before
	public void setUp() throws Exception {

		passwordGenerator = new PasswordGeneratorService();
		criteria = new PasswordCriteria(); 
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testGeneratePassword() {

	    // Generate Using Default Criteria
	    String password = passwordGenerator.generatePassword();
		    
	    assertTrue(password.length() == PasswordCriteria.DEFAULT_LEN);
	    
	    
	    // Make sure the pwd is valid for default charset
	    String charSet = passwordGenerator.getCharSet(criteria).toString();
	    for (int i = 0; i < password.length(); i++){
		  	assertFalse(charSet.indexOf(password.charAt(i)) == -1);
	    
		}
	    
	    // now test using specific criteria 
	    criteria.setPwdLen(20);
	    criteria.setAlphaLowerChars(false);
	    criteria.setNumericChars(true);
	    criteria.setOtherChars(false);
	    
	    password = passwordGenerator.generatePassword(criteria);
	    assertTrue(password.length() == criteria.getPwdLen());
		
	//	fail("Not yet implemented"); // TODO
	    
	    // Make sure the pwd is valid for specified charset
	    charSet = passwordGenerator.getCharSet(criteria).toString();
	    
	    // Test Agains Specified Char Set
	    for (int i = 0; i < password.length(); i++){
		  	assertFalse(charSet.indexOf(password.charAt(i)) == -1);
		}
	    
	    String lowerCharTest = "abcd";
	    String specCharTest = "%$^";
	    
	    // Test Agains Specified Char Set
	    for (int i = 0; i < password.length(); i++){
		  	assertTrue(lowerCharTest.indexOf(password.charAt(i)) == -1);
		  	assertTrue(specCharTest.indexOf(password.charAt(i)) == -1);
		}
	}

}
