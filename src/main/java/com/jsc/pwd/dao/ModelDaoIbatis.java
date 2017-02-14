package com.jsc.pwd.dao;


import java.util.ArrayList;
import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jsc.pwd.model.*;

public class ModelDaoIbatis implements AppUserDao, CategoryDao, SiteDao
{

	public  AppUser addUser(AppUser user, SqlMapClient sqlMapClient) {
		try
		{
			Long id = (Long) sqlMapClient.insert("user.addUser", user);
			user = getUserById(id, sqlMapClient);
			return user;
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void updateUser(AppUser user, SqlMapClient sqlMapClient){

		try{
			sqlMapClient.update("user.updateUserById", user);

		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}


	public AppUser getUserById(Long id, SqlMapClient sqlMapClient) {
		try
		{
			AppUser user = (AppUser)sqlMapClient.queryForObject("user.getUserById", id);
			return user;
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void deleteUserById(Long id, SqlMapClient sqlMapClient) {
		try
		{
			sqlMapClient.delete("user.deleteUserById", id);
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public long getMaxUserId(SqlMapClient sqlMapClient) {
		try
		{
			return (long) sqlMapClient.queryForObject("user.getUserMaxId");
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return -1;
	}

	

	public ArrayList <AppUser> getAllUsers(SqlMapClient sqlMapClient)
	{
		try
		{
			ArrayList<AppUser> userList =  (ArrayList<AppUser>) sqlMapClient.queryForList("user.getAllUsers");
			return userList;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}    
	
	public long getCountAllAppUsers(SqlMapClient sqlMapClient) {
		try
		{
          return (long) sqlMapClient.queryForObject("user.countAllUsers");
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return -1;
	}
	
	// START CATEGORY

	public  Category addCategory(Category category, SqlMapClient sqlMapClient) {
		try
		{
			Long id = (Long) sqlMapClient.insert("user.addCategory", category);
			category = getCategoryById(id, sqlMapClient);
			return category;
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public long getMaxCategoryId(SqlMapClient sqlMapClient) {
		try
		{
			return (long) sqlMapClient.queryForObject("user.getMaxCategoryId");
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return -1;
	}

	public void updateCategory(Category category, SqlMapClient sqlMapClient){

		try{
			sqlMapClient.update("user.updateCategoryById", category);

		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		

	}


	public Category getCategoryById(Long id, SqlMapClient sqlMapClient) {
		try
		{
			Category category = (Category )sqlMapClient.queryForObject("user.getCategoryById", id);
			return category;
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void deleteCategoryById(Long id, SqlMapClient sqlMapClient) {
		try
		{
			sqlMapClient.delete("user.deleteCategoryById", id);
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void deleteAllCategoriesByUserId(Long id, SqlMapClient sqlMapClient) {
		try
		{                             
			sqlMapClient.delete("user.deleteCategoriesByAppUserId", id);
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	@Override
	public ArrayList<Category> getAllCategoriesByUserId(Long id, SqlMapClient sqlMapClient) {
		try
		{
			ArrayList<Category> catList = (ArrayList<Category>) sqlMapClient.queryForList("user.getAllCategoriesById", id);
			return catList;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public long getCountAllCategories(SqlMapClient sqlMapClient) {
		try
		{
            return (long) sqlMapClient.queryForObject("user.countAllCategories");
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return -1;
	}
	
	// START SITE
	


	public  Site addSite(Site site, SqlMapClient sqlMapClient) {
		try
		{
			Long id = (Long) sqlMapClient.insert("user.addSite", site);
			site = getSiteById(id, sqlMapClient);
			return site;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void updateSite(Site site, SqlMapClient sqlMapClient){

		try{
			sqlMapClient.update("user.updateSiteById", site);

		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		

	}


	public Site getSiteById(Long id, SqlMapClient sqlMapClient) {
		try
		{
			Site site = (Site )sqlMapClient.queryForObject("user.getSiteById", id);
			return site;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}


	public void deleteSiteById(Long id, SqlMapClient sqlMapClient) {
		try
		{
			sqlMapClient.delete("user.deleteSiteById", id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public ArrayList<Site> getSitesByCatId(Long id, SqlMapClient sqlMapClient){
		try
		{
			ArrayList<Site> catList = (ArrayList<Site>) sqlMapClient.queryForList("user.getAllSitesByCatId", id);
			return catList;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public long getMaxSiteId(SqlMapClient sqlMapClient) {
		try
		{
			return (long) sqlMapClient.queryForObject("user.getMaxSiteId");
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public void deleteSitesByCatId(long id, SqlMapClient sqlMapClient) {
		try
		{
			sqlMapClient.delete("user.deleteSitesByCatId", id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public long getCountAllSites(SqlMapClient sqlMapClient) {
		try
		{
			return (long) sqlMapClient.queryForObject("user.countAllSites");
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return -1;
	}

}