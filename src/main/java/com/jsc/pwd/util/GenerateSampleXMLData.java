/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsc.pwd.util;

import com.jsc.pwd.model.Category;
import com.jsc.pwd.model.PasswordCriteria;
import com.jsc.pwd.model.PasswordFileHandler;
import com.jsc.pwd.model.Site;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.jsc.pwd.services.PasswordGeneratorService;
import com.jsc.pwd.util.AppServicesFactory;


/**
 *
 * @author jsc
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class GenerateSampleXMLData {

	/**
	 * @param args the command line arguments
	 */

	private static final String PASSWORD_FILE_XML = "./pwdFile-jaxb.xml";


	public static void main(String[] args) {
        SpringApplication.run(GenerateSampleXMLData.class, args);
        AppServicesFactory appServicesFactory = AppServicesFactory.getInstance();

		ArrayList<Site> siteList = new ArrayList<Site>();

		PasswordCriteria criteria = new PasswordCriteria();

		
		PasswordGeneratorService pwdGen = appServicesFactory.getPwdGeneratorSvc();
		
	    Category category = new Category();
       
	    category.setCatId(1000);
	    category.setChildId(100);
	    category.setParentId(100);
	    category.setDescription("Description");
	    category.setUserId(1000);
	    category.setName("root");


		for (int i =0; i < 10 ;i++){
			Site site = new Site();
			site.setSiteId(100 + i);
			site.setSiteName("Test Site - "  + i);
			site.setSiteURL("FooURL - "  + i);
			site.setSiteLogin("SiteLogin - "  + i);
			site.setNotes("Foo Bar Comment - "  + i);
            site.setPassword(pwdGen.generatePassword(criteria).getBytes(Charset.defaultCharset()));
			site.setCatId(100);
			siteList.add(site);
		}

		PasswordFileHandler pwdFile = new PasswordFileHandler();
		pwdFile.setPwdFileName(PASSWORD_FILE_XML);
		pwdFile.setSiteList(siteList);

		

		try{
			// create JAXB context and instantiate marshaller
			JAXBContext context = JAXBContext.newInstance(PasswordFileHandler.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Write to System.out
			m.marshal(pwdFile, System.out);

			// Write to File
			m.marshal(pwdFile, new File(PASSWORD_FILE_XML));


			DisplaySchemas.displaySchemas(context);

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

	}
}
