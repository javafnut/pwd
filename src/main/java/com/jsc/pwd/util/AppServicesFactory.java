package com.jsc.pwd.util;

import java.io.FileInputStream;
import java.util.Properties;

import com.jsc.pwd.dao.ModelDaoIbatis;
import com.jsc.pwd.model.PasswordFileHandler;
import com.jsc.pwd.services.PasswordEncryptionService;
import com.jsc.pwd.services.PasswordGeneratorService;

public class AppServicesFactory {
	
	private static AppServicesFactory instance = null;
	private Properties props = null;

	private PasswordEncryptionService pwdEncryptionSvc = null;
	private PasswordGeneratorService pwdGeneratorSvc = null;
	private ModelDaoIbatis modelDaoSvc = null;
	private PasswordFileHandler pwdFileHandler = null;

	
	
static {
	instance = new AppServicesFactory();
}

private AppServicesFactory(){
	
	props = new Properties();
	
	try{
		props.load(new FileInputStream("pwd.properties"));
		
		String pwdEncryptionSvcClass = props.getProperty("pwdEncryptionSvc.class");
		String pwdGeneratorSvcClass = props.getProperty("pwdGeneratorSvc.class");
		String modelDaoSvcClass = props.getProperty("modelDaoSvc.class");
		String pwdFileHandlerClass  = props.getProperty("pwdFileHandler.class");
		
		pwdEncryptionSvc = (PasswordEncryptionService) Class.forName(pwdEncryptionSvcClass).newInstance();
		pwdGeneratorSvc = (PasswordGeneratorService) Class.forName(pwdGeneratorSvcClass).newInstance();
		modelDaoSvc = (ModelDaoIbatis) Class.forName(modelDaoSvcClass).newInstance();
		pwdFileHandler = (PasswordFileHandler) Class.forName(pwdFileHandlerClass).newInstance();
		
	}
	
	catch(Exception ex){
		ex.printStackTrace();
	}
	
}

/**
 * @return the instance
 */
public static AppServicesFactory getInstance() {
	return instance;
}

/**
 * @return the props
 */
public Properties getProps() {
	return props;
}

/**
 * @return the pwdEncryptionSvc
 */
public PasswordEncryptionService getPwdEncryptionSvc() {
	return pwdEncryptionSvc;
}


/**
 * @return the pwdGeneratorSvc
 */
public PasswordGeneratorService getPwdGeneratorSvc() {
	return pwdGeneratorSvc;
}


/**
 * @return the pwdFileHandler
 */
public PasswordFileHandler getPwdFileHandler() {
	return pwdFileHandler;
}

/**
 * @return the modelDaoSvc
 */
public ModelDaoIbatis getModelDaoSvc() {
	return modelDaoSvc;
}


}
