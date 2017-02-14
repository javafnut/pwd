package com.jsc.pwd.util.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jsc.pwd.dao.ModelDaoIbatis;
import com.jsc.pwd.model.PasswordFileHandler;
import com.jsc.pwd.services.PasswordEncryptionService;
import com.jsc.pwd.services.PasswordGeneratorService;
import com.jsc.pwd.util.AppServicesFactory;

public class testAppServicesFactory {
	
	private AppServicesFactory appServicesFactory = null;

	private PasswordEncryptionService pwdEncryptionSvc = null;
	private PasswordGeneratorService pwdGeneratorSvc = null;
	private ModelDaoIbatis modelDaoSvc = null;
	private PasswordFileHandler pwdFileHandler = null;
	
	@Before
	public void setUp() throws Exception {
		appServicesFactory = AppServicesFactory.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInstance() {
		
		assertFalse(appServicesFactory == null);
	}


	@Test
	public void testGetPwdEncryptionSvc() {

		pwdEncryptionSvc = appServicesFactory.getPwdEncryptionSvc();
		
		assertFalse(pwdEncryptionSvc == null);
		
		assertTrue(pwdEncryptionSvc.getClass() == PasswordEncryptionService.class);
	
	}

	@Test
	public void testGetPwdGeneratorSvc() {
	
		pwdGeneratorSvc = appServicesFactory.getPwdGeneratorSvc();
	
		assertFalse(pwdGeneratorSvc == null);
		
		assertTrue(pwdGeneratorSvc.getClass() == PasswordGeneratorService.class);
	}
	

	@Test
	public void testGetPwdFileHandler() {
		
		pwdFileHandler = appServicesFactory.getPwdFileHandler();
		
		assertFalse(pwdFileHandler == null);
		
		assertTrue(pwdFileHandler.getClass() == PasswordFileHandler.class);
	}

	@Test
	public void testGetModelDaoSvc() {
	
		modelDaoSvc = appServicesFactory.getModelDaoSvc();
		
		assertFalse(modelDaoSvc == null);
		
		assertTrue(modelDaoSvc.getClass() == ModelDaoIbatis.class);
	}

}
