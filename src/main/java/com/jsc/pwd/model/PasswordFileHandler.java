/*
 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsc.pwd.model;


import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 *
 * @author jsc
 */

//This statement means that class "Bookstore.java" is the root-element of our example
@XmlRootElement(name = "passwordFile")
public class PasswordFileHandler  {
    
      // XmLElementWrapper generates a wrapper element around XML representation
  @XmlElementWrapper(name = "siteList")
  @XmlElement(name = "site")

  //private ArrayList<Site> siteList;


  private ArrayList<Site> siteList;
  private String pwdFileName;
  private AppUser appUser;
  
 
  //  @Override
  public ArrayList<Site> getSitesList() {
        return siteList;
   }

   // @Override
  public void setSiteList(ArrayList<Site> pSiteList) {
        siteList = pSiteList;
    }

   // @Override
    public String getPwdFileName() {
        return pwdFileName;
    }

    //@Override
    public void setPwdFileName(String pPwdFileName) {
        pwdFileName = pPwdFileName;
    }


	/**
	 * @return the userInfo
	 */
	public AppUser getAppUser() {
		return appUser;
	}

	/**
	 * @param userInfo the userInfo to set
	 */
	public void setAppUser(AppUser pAppUser) {
		appUser = pAppUser;
	}



}
