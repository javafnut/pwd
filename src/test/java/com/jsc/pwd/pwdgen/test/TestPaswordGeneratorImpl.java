/**
 * 
 */
package com.jsc.pwd.pwdgen.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jsc.pwd.model.*;
import com.jsc.pwd.pwdgen.*;
import com.jsc.pwd.services.PasswordGeneratorService;


/**
 * @author jsc
 *
 */
public class TestPaswordGeneratorImpl {

	PasswordGeneratorService  passwordGenerator = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		passwordGenerator = new PasswordGeneratorService();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.jsc.pwd.pwdgen.PasswordGeneratorImpl#generatePassword()}.
	 */
	@Test
	public final void testGeneratePassword() {
	
	PasswordCriteria criteria = new PasswordCriteria();
		
    // Generate Using Default Criteria
    String password = passwordGenerator.generatePassword();
	    
    assertTrue(password.length() == criteria.DEFAULT_LEN);
    	
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
