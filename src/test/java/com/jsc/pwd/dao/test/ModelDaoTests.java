/**
 * 
 */
package com.jsc.pwd.dao.test;

import static org.junit.Assert.*;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

// DAO/POJO to Test
import com.jsc.pwd.dao.ModelDaoIbatis;
import com.jsc.pwd.model.AppUser;
import com.jsc.pwd.model.Category;
import com.jsc.pwd.model.Site;

/**
 * @author jsc
 *
 */

public class ModelDaoTests {
	
	private static final long TEST_USER_ID = 999999;
	private static final long TEST_CATEGORY_ID = 888888;
	private static final long TEST_SITE_ID = 7777777;
	private static final long ROOT_CAT_ID = 0;
	
	private static final byte[] TEST_BYTE_ARRAY =  new byte[] { (byte)0xba, (byte)0x8a, 0x0d,
	    0x45, 0x25, (byte)0xad, (byte)0xd0, 0x11, (byte)0x98, (byte)0xa8, 0x08, 0x00,
	    0x36, 0x1b, 0x11, 0x03 };
	

	Reader reader = null;
	SqlMapClient sqlMapClient= null;
	ModelDaoIbatis manager = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		
		reader = Resources.getResourceAsReader("sql-maps-config.xml");
		
		//reader = Resources.getResourceAsReader("ModelDAO.xml");
		sqlMapClient = SqlMapClientBuilder.buildSqlMapClient (reader);
		manager = new ModelDaoIbatis();
	}
	
	@Test
	public void testSetup(){
	    assertTrue(reader != null);
	}
	

	// SIMPLE CRUD TEST
	@Test
	public void testAppUserDao() {
		
		    AppUser user = new AppUser();
		    AppUser createdUser = null;
		    Long createdID;
		    
		    user.setFirstName("Foo");
		    user.setLastName("Bar");
		    user.setPassword(TEST_BYTE_ARRAY);
		    user.setSalt(TEST_BYTE_ARRAY);
			user.setEmail("demo-user@gmail.com");
		

			user = manager.addUser(user,sqlMapClient);
			
			createdID = user.getUserId();
		    
 			createdUser = manager.getUserById(createdID, sqlMapClient);
	 		
	 		manager.deleteUserById(createdUser.getUserId(), sqlMapClient);
	 		
	 		try{
	 			
	 			user = manager.getUserById(createdID, sqlMapClient);	 			
	 		
	 		}
	 		catch (Exception e){
	 			assertFalse(user != null);
	 		}
	
	}
	
	// SIMPLE CRUD TEST
	@Test
	public void testUdateAppUserDao() {
		
		    AppUser user = new AppUser();
		    AppUser createdUser = null;
		    Long createdID;
		    
		    user.setFirstName("Foo");
		    user.setLastName("Bar");
		    user.setPassword(TEST_BYTE_ARRAY);
		    user.setSalt(TEST_BYTE_ARRAY);
			user.setEmail("demo-user@gmail.com");
		

			user = manager.addUser(user,sqlMapClient);
			
			createdID = user.getUserId();
		    
 			createdUser = manager.getUserById(createdID, sqlMapClient);
 			
 		    // Change Name to test
 			String oldName = createdUser.getFirstName();
 			String newName = "TestNewName";
 			
 			assertFalse(oldName.equalsIgnoreCase(newName));
 			
 			// change it
 			createdUser.setFirstName(newName);
 			manager.updateUser(createdUser, sqlMapClient);
 			
 			// refresh it
 			createdUser = manager.getUserById(createdID, sqlMapClient);
 			assertTrue(createdUser.getFirstName().equalsIgnoreCase(newName));
 			
	 		
	 		manager.deleteUserById(createdUser.getUserId(), sqlMapClient);
	 		
	 		try{
	 			
	 			user = manager.getUserById(createdID, sqlMapClient);	 			
	 		
	 		}
	 		catch (Exception e){
	 			assertFalse(user != null);
	 		}
	
	}	
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		reader = null;
		sqlMapClient= null;
	}
	
	
	@Test
	public void testCategoryDao() {
		
		    Category category = new Category();
    	    Category createdCategory = null;
   		    long createdId;
   		    
		    category.setName("FOO CAT");
		    category.setDescription("CAT DESC");
		    category.setCatId(TEST_CATEGORY_ID);
		    category.setUserId(TEST_USER_ID);
		    category.setParentId(TEST_USER_ID);

            category =  manager.addCategory(category, sqlMapClient);
	        createdId = category.getCatId();
            
		    createdCategory = manager.getCategoryById(createdId, sqlMapClient);
		    
	 		manager.deleteCategoryById(createdCategory.getCatId(), sqlMapClient);
	 		
	 		try{
	 			
	 			category = manager.getCategoryById(createdId, sqlMapClient);
	 		}
	 		catch (Exception e){
	 			assertFalse(category != null);
	 		}

	
	}
	
	@Test
	public void testUpdateCategoryDao() {
		
		    Category category = new Category();
    	    Category createdCategory = null;
   		    long createdId;
   		    
		    category.setName("FOO CAT");
		    category.setDescription("CAT DESC");
		    category.setCatId(TEST_CATEGORY_ID);
		    category.setUserId(TEST_USER_ID);
		    category.setParentId(TEST_USER_ID);

            category =  manager.addCategory(category, sqlMapClient);
	        createdId = category.getCatId();
            
		    createdCategory = manager.getCategoryById(createdId, sqlMapClient);
		    
		    String oldName = createdCategory.getName();
		    String newName = "NEW_CATEGORY_NAME";
		    
		    assertFalse(oldName.equalsIgnoreCase(newName));
		    
//		    // Change it
		    createdCategory.setName(newName);
 		    manager.updateCategory(createdCategory, sqlMapClient);
//		    
//		    // Retrieve it
		    createdCategory = manager.getCategoryById(createdId, sqlMapClient);
    	    assertTrue(createdCategory.getName().equalsIgnoreCase(newName));
		    
		    
     		manager.deleteCategoryById(createdCategory.getCatId(), sqlMapClient);
	 		
	 		try{
	 			
	 			category = manager.getCategoryById(createdId, sqlMapClient);
	 		}
	 		catch (Exception e){
	 			assertFalse(category != null);
	 		}

	
	}
	
	@Test
	public void testSiteDao(){
        Site site = new Site();
        Site createdSite = null;

        
        site.setSiteName("Foo Bar Site");
        site.setSiteLogin("FooLogin");
        site.setPassword(TEST_BYTE_ARRAY);
        site.setSiteURL("www.FOO.COM");
        site.setNotes("FOO TEST NOTES");
        
        createdSite = manager.addSite(site, sqlMapClient);
        
        site = manager.getSiteById(createdSite.getSiteId(), sqlMapClient); 

		assertTrue(site.getSiteId() == createdSite.getSiteId());
		
		manager.deleteSiteById(createdSite.getSiteId(), sqlMapClient);
		 		
 		try{
 			site = manager.getSiteById(createdSite.getSiteId(), sqlMapClient);  			
 		
 		}
 		catch (Exception e){
 			assertFalse(site != null);
 		}
	}
	
	
	@Test
	public void testUpdateSiteDao(){
        Site site = new Site();
        Site createdSite = null;

        
        site.setSiteName("Foo Bar Site");
        site.setSiteLogin("FooLogin");
        site.setPassword(TEST_BYTE_ARRAY);
        site.setSiteURL("www.FOO.COM");
        site.setNotes("FOO TEST NOTES");
        
        createdSite = manager.addSite(site, sqlMapClient);
        
        site = manager.getSiteById(createdSite.getSiteId(), sqlMapClient); 

		assertTrue(site.getSiteId() == createdSite.getSiteId());
		
		String newSiteName = "NEW_SIIE_NAME";
		String oldSiteName = createdSite.getSiteName();
		
		assertFalse(newSiteName.equalsIgnoreCase(oldSiteName));
		
		createdSite.setSiteName(newSiteName);
		manager.updateSite(createdSite, sqlMapClient);
		
        site = manager.getSiteById(createdSite.getSiteId(), sqlMapClient); 
        assertTrue(site.getSiteName().equalsIgnoreCase(newSiteName));
        
       
        
		manager.deleteSiteById(createdSite.getSiteId(), sqlMapClient);
		 		
 		try{
 			site = manager.getSiteById(createdSite.getSiteId(), sqlMapClient);  			
 		
 		}
 		catch (Exception e){
 			assertFalse(site != null);
 		}
	}
	
	
	@Test
	public void testGetAllCategories() {
				   
		// Clean Up Before
		manager.deleteAllCategoriesByUserId(TEST_USER_ID, sqlMapClient);
		
		
		int testSize = 12;
        
        for (int i = 0; i < testSize; i++){		
	    	Category category = new Category();
    	    category.setName("FOO CAT");
		    category.setDescription("CAT DESC");
		    category.setCatId(TEST_CATEGORY_ID);
		    category.setUserId(TEST_USER_ID);
		    category.setParentId(TEST_USER_ID);

            manager.addCategory(category, sqlMapClient);
        }
	
      
        List<Category> categoryList = manager.getAllCategoriesByUserId(TEST_USER_ID, sqlMapClient);
        
         assertTrue(categoryList.size() == testSize);
        
        
		// Clean Up After
 		manager.deleteAllCategoriesByUserId(TEST_USER_ID, sqlMapClient);
	
	}
	
	
	@Test
	public void testRetrieveSites(){
		
	      // Clean Up Before
        manager.deleteSitesByCatId(TEST_CATEGORY_ID, sqlMapClient);
 
        
        int testSize = 12;
		// setup 10 sites
        for (int i = 0; i < testSize; i++){		
           	Site site = new Site();
          	site.setCatId(TEST_CATEGORY_ID);
            site.setSiteName("Foo Bar Site - " + i);
            site.setSiteLogin("FooLogin");
            site.setPassword(TEST_BYTE_ARRAY);
            site.setSiteURL("www.FOO.COM");
            site.setNotes("FOO TEST NOTES");
            
           manager.addSite(site, sqlMapClient);
        
        }
        
        List<Site> siteList = manager.getSitesByCatId(TEST_CATEGORY_ID, sqlMapClient);
        
            
        assertTrue(siteList.size() == testSize);
        
       
        // Clean Up After
        manager.deleteSitesByCatId(TEST_CATEGORY_ID, sqlMapClient);
        
		
	}	
		
	
}

