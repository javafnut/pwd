package com.jsc.pwd.dao;

import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jsc.pwd.model.AppUser;



//
public interface AppUserDao {
	
	public AppUser addUser(AppUser user, SqlMapClient sqlmapClient);
	public AppUser getUserById(Long id, SqlMapClient sqlmapClient);
	public ArrayList <AppUser> getAllUsers(SqlMapClient sqlmapClient);
	public void deleteUserById(Long id, SqlMapClient sqlmapClient);
	public long getMaxUserId(SqlMapClient sqlMapClient);
	public long getCountAllAppUsers(SqlMapClient sqlMapClient);

}
