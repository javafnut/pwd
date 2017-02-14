package com.jsc.pwd.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import java.util.ArrayList;
import com.jsc.pwd.model.Site;

public interface SiteDao {
	public Site addSite(Site site, SqlMapClient sqlmapClient);
	public Site getSiteById(Long id, SqlMapClient sqlmapClient);
	public ArrayList<Site> getSitesByCatId(Long id, SqlMapClient sqlmapClient);
	public void deleteSiteById(Long id, SqlMapClient sqlmapClient);
	public void deleteSitesByCatId(long id, SqlMapClient sqlmapClient);
	public long getMaxSiteId(SqlMapClient sqlMapClient);
	public long getCountAllSites(SqlMapClient sqlMapClient);
};