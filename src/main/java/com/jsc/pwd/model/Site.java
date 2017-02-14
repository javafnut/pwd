/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsc.pwd.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author jsc
 */


@XmlRootElement(name = "site")
// If you want you can define the order in which the fields are written
// Optional
@XmlType(propOrder = { "siteName", "catId","appUserId","siteURL", "siteLogin",
                       "password", "notes", "createDate", "modDate"})
public class Site { 
    
    private long siteId;
    private String siteName;
    private long catId;
    private long appUserId;

    private String siteURL;
    private String siteLogin;
    private byte[] password;
    private String notes;
    //private String pwdText;


    
    // Change To Date
    private Date createDate;
    private Date modDate;
    

    @XmlAttribute
    public long getSiteId() {
        return siteId;
    }

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}

	/**
	 * @return the siteName
	 */
	public String getSiteName() {
		return siteName;
	}


	/**
	 * @param siteName the siteName to set
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}


	/**
	 * @return the catId
	 */
	public long getCatId() {
		return catId;
	}


	/**
	 * @param catId the catId to set
	 */
	public void setCatId(long catId) {
		this.catId = catId;
	}


	/**
	 * @return the appUserId
	 */
	public long getAppUserId() {
		return appUserId;
	}


	/**
	 * @param appUserId the appUserId to set
	 */
	public void setAppUserId(long appUserId) {
		this.appUserId = appUserId;
	}


	/**
	 * @return the siteURL
	 */
	public String getSiteURL() {
		return siteURL;
	}


	/**
	 * @param siteURL the siteURL to set
	 */
	public void setSiteURL(String siteURL) {
		this.siteURL = siteURL;
	}


	/**
	 * @return the siteLogin
	 */
	public String getSiteLogin() {
		return siteLogin;
	}


	/**
	 * @param siteLogin the siteLogin to set
	 */
	public void setSiteLogin(String siteLogin) {
		this.siteLogin = siteLogin;
	}


	/**
	 * @return the password
	 */
	public byte[] getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(byte[] password) {
		this.password = password;
	}


	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}


	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}


	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}


	/**
	 * @param date the createDate to set
	 */
	public void setCreateDate(Date date) {
		this.createDate = date;
	}


	/**
	 * @return the modDate
	 */
	public Date getModDate() {
		return modDate;
	}


	/**
	 * @param modDate the modDate to set
	 */
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}






}
