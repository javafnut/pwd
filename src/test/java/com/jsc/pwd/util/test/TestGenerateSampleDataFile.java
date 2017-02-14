/**
 * 
 */
package com.jsc.pwd.util.test;

import static org.junit.Assert.*;

import com.jsc.pwd.model.Category;
import com.jsc.pwd.model.PasswordFileHandler;
import com.jsc.pwd.model.Site;
import com.jsc.pwd.model.AppUser;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.jsc.pwd.services.PasswordEncryptionService;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jsc
 *
 */
public class TestGenerateSampleDataFile {
	

	private static final String PASSWORD_FILE_XML = "./pwdFile-jaxb.xml";
    private static final String TEST_PASSWORD = "fooBarPassword";
   
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.jsc.pwd.util.GenerateSampleData#main(java.lang.String[])}.
	 */
	@Test
	public void testMain() {
   
		ArrayList<Site> siteList = new ArrayList<Site>();
        AppUser user = new AppUser();
		//PasswordCriteria criteria = new PasswordCriteria();
	    Category category = new Category();


	    category.setCatId(1000);
	    category.setChildId(100);
	    category.setParentId(100);
	    category.setDescription("Description");
	    category.setUserId(1000);
	    category.setName("root");
		
        
		
		// Setup user information, create hash and key (tested in testPasswordEncryptionService
		try {
			user.setUserId(10000);
			user.setLastName("Last");
			user.setFirstName("First");
			user.setSalt(PasswordEncryptionService.generateSalt());
			user.setPassword(PasswordEncryptionService.getEncryptedPassword(TEST_PASSWORD,user.getSalt()));

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		for (int i =0; i < 10 ;i++){
			Site site = new Site();
			site.setSiteId(100 + i );
			site.setSiteName("Test Site - "  + i);
			site.setSiteURL("FooURL - "  + i);
			site.setSiteLogin("SiteLogin - "  + i);
			site.setNotes("Foo Bar Comment - "  + i);
			
			try{
     		   site.setPassword(PasswordEncryptionService.getEncryptedPassword(TEST_PASSWORD, user.getSalt()));
			}
			catch(Exception e){
				e.printStackTrace();
			}
			site.setCatId(100);

			siteList.add(site);
		}

		PasswordFileHandler pwdFile = new PasswordFileHandler();
		pwdFile.setPwdFileName(PASSWORD_FILE_XML);
		pwdFile.setSiteList(siteList);

		pwdFile.setAppUser(user);

		try{
			// create JAXB context and instantiate marshaller
			JAXBContext context = JAXBContext.newInstance(PasswordFileHandler.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Write to System.out
			m.marshal(pwdFile, System.out);

			// Write to File
			m.marshal(pwdFile, new File(PASSWORD_FILE_XML));


			//DisplaySchemas.displaySchemas(context);

		} catch (JAXBException je) {
			je.printStackTrace();
		} 
		
		PasswordFileHandler pwdFileXml = new PasswordFileHandler();
		
		try{
			
			JAXBContext context = JAXBContext.newInstance(PasswordFileHandler.class);
			Unmarshaller um = context.createUnmarshaller();
			File inXML = new File(PASSWORD_FILE_XML);
			pwdFileXml = (PasswordFileHandler) um.unmarshal(inXML);
			
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			// Write to System.out
			System.out.println("/n OUTPUT FROM READ XML ============================ \n\n");
			m.marshal(pwdFileXml, System.out);
			
			
		} catch (JAXBException je) {
			je.printStackTrace();
		} 
		
		int gotHere = 0;
		assertTrue(gotHere == 0);
		
	
		
	}

}
