package com.jsc.pwd.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jsc.pwd.model.Category;
import java.util.ArrayList;

public interface CategoryDao {
	
	public Category addCategory(Category category, SqlMapClient sqlMapClient);
	public Category getCategoryById(Long id, SqlMapClient sqlMapClient);
	public ArrayList<Category> getAllCategoriesByUserId(Long id, SqlMapClient sqlMapClient);
	public void deleteCategoryById(Long id, SqlMapClient sqlmapClient);
	public void deleteAllCategoriesByUserId(Long id, SqlMapClient sqlMapClient);
	public long getMaxCategoryId(SqlMapClient sqlMapClient);
	public long getCountAllCategories(SqlMapClient sqlMapClient);

}
