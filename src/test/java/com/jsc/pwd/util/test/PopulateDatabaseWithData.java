package com.jsc.pwd.util.test;

import static org.junit.Assert.*;

import java.io.Reader;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.List;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.jsc.pwd.dao.ModelDaoIbatis;
import com.jsc.pwd.model.AppUser;
import com.jsc.pwd.model.Category;
import com.jsc.pwd.model.Site;
import com.jsc.pwd.services.PasswordEncryptionService;
import com.jsc.pwd.services.PasswordGeneratorService;

public class PopulateDatabaseWithData {

	// Setup user values, point is to keep generated data above other test data
	// easier to clean-up and avoids clobbering other test data
	private static final int NUM_USERS = 5000;
	private static final int NUM_CATEGORIES_PER_USER = 20;
	private static final int NUM_SITES_PER_CATEGORY = 15;

	Reader reader = null;
	SqlMapClient sqlMapClient = null;
	ModelDaoIbatis manager = null;
	PasswordGeneratorService passwordGenerator = null;
	PasswordEncryptionService passwordEncryptionService = null;
    
	Date startTime;

	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		passwordGenerator = new PasswordGeneratorService();
		passwordEncryptionService = new PasswordEncryptionService();
		reader = Resources.getResourceAsReader("sql-maps-config.xml");
		// reader = Resources.getResourceAsReader("ModelDAO.xml");
		sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
		manager = new ModelDaoIbatis();
        
	
	}
	
	public void ClearCurrentData()
	{
		
		// For every user add random number of categories
	    ArrayList<AppUser> allUsersList = manager.getAllUsers(sqlMapClient);
	    
	    if (allUsersList.isEmpty())
	           return;
	    
	    for (AppUser user : allUsersList){

	    	ArrayList<Category> categoriesList = manager.getAllCategoriesByUserId(user.getUserId(),sqlMapClient);

            for (Category category : categoriesList)
            {
                manager.deleteSitesByCatId(category.getCatId(), sqlMapClient);
			}
            
            manager.deleteAllCategoriesByUserId(user.getUserId(), sqlMapClient);
            manager.deleteUserById(user.getUserId(), sqlMapClient);
		}

	}

	
	/**
	 * Test method for
	 * {@link com.jsc.pwd.util.GenerateSampleData#main(java.lang.String[])}.
	 */
	@Test
	public void populateDB() {

	   ClearCurrentData();
       CreateUsers();
       AddCategoriesToUsers();
       AddSitesToCategoriesByUsers();

	}

	public void CreateUsers() {

		for (long i = 0; i < NUM_USERS; i++) {
			AppUser user = new AppUser();
			user.setUserId(i);

			user.setFirstName("FOO_" + i);
			user.setLastName("BAR_" + i);
			user.setEmail("MyFooEmail_" + i + "@email.com");

			user.setCreateDate(new Date());
			user.setModDate(new Date());

			try {
				byte[] salt = PasswordEncryptionService.generateSalt();
				user.setSalt(salt);
				user.setPassword(PasswordEncryptionService
						.getEncryptedPassword(user.getFirstName(), salt));
			} catch (NoSuchAlgorithmException nsa) {
				nsa.printStackTrace();
			} catch (InvalidKeySpecException ikse) {
				ikse.printStackTrace();
			}

			user = manager.addUser(user, sqlMapClient);

		}
	}

	public void AddCategoriesToUsers() {

		Random random = new Random();
	
		long categoryCnt =  manager.getMaxCategoryId(sqlMapClient);
	    ArrayList<AppUser> allUsersList = manager.getAllUsers(sqlMapClient);
	    
	    for (AppUser user : allUsersList){
	    	

			// create ROOT_CATEGORY

			for (int i = 0; i < NUM_CATEGORIES_PER_USER; i++) {
				Category category = new Category();
				category.setCatId(categoryCnt++);
				category.setUserId(user.getUserId());
				category.setName("FOO_CAT_NAME_" + i);
				category.setDescription("FOO_CAT_DESC_" + i);
				category.setCreateDate(new Date());
				category.setModDate(new Date());
				category = manager.addCategory(category, sqlMapClient);
			}

		}

	}

	public void AddSitesToCategoriesByUsers() {

		Random random = new Random();
		long siteIdCnt = manager.getMaxSiteId(sqlMapClient);

		
		Date now = new Date();
		// For every user add random number of categories
		
	    ArrayList<AppUser> allUsersList = manager.getAllUsers(sqlMapClient);
	    
	    for (AppUser user : allUsersList){

	    	ArrayList<Category> categoriesList = manager.getAllCategoriesByUserId(user.getUserId(),sqlMapClient);

            for (Category category : categoriesList)
            {


				for (int i = 0; i < NUM_SITES_PER_CATEGORY; i++) {
					Site site = new Site();
					site.setSiteId(siteIdCnt++);
					site.setAppUserId(user.getUserId());
					site.setCatId(category.getCatId());
					site.setSiteName("My_Site_Name_" + siteIdCnt);
					site.setNotes("My_Site_Notes_" + siteIdCnt);
					site.setSiteLogin("Login_Name_" + siteIdCnt);
					site.setCreateDate(new Date());
					site.setModDate(new Date());

					try {
						byte[] salt = user.getSalt();
						String str = "Password_" + siteIdCnt;
						site.setPassword(PasswordEncryptionService
								.getEncryptedPassword(str, salt));
					} catch (NoSuchAlgorithmException nsa) {
						nsa.printStackTrace();
					} catch (InvalidKeySpecException ikse) {
						ikse.printStackTrace();
					}
					
					site = manager.addSite(site, sqlMapClient);

				}

			}

		}

	}
	
	@After
	public void DumpStatisticsToConsole()
	{
	   Date endTime = new Date();
	   long numUsers =  manager.getCountAllAppUsers(sqlMapClient);
	   long numCategories = manager.getCountAllCategories(sqlMapClient);
	   long numSites = manager.getCountAllSites(sqlMapClient);
			  
	   
	   long l1 = startTime.getTime();
	   long l2 = endTime.getTime();
	   long diff = l2 - l1;
	  
	   long secondInMillis =  1000;
	   long minuteInMillis = (secondInMillis * 60) / diff;
	   long hourInMillis = (minuteInMillis * 60) / diff;
	   

	   
	   String bar = "|===========================================================|";
	   String blankLine = "\n";
	   
	   System.out.println(bar + blankLine);
	   System.out.println("Number of Users - " + numUsers);
	   System.out.println("Number of Categories - " + numCategories);
	   System.out.println("Number of Sites - " + numSites);
	   System.out.println(blankLine);
	   System.out.println("Elapsed Time Stats:" + hourInMillis + ":" + minuteInMillis + ":" + secondInMillis);
	   
	   System.out.println(bar);
			
	}
	
	
}
