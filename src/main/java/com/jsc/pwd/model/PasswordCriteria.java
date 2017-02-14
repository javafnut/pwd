/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsc.pwd.model;
import javax.xml.bind.annotation.*;

/*
 *
 * @author jsc
 */

@XmlRootElement(name = "password")
@XmlAccessorType(XmlAccessType.FIELD)
public class PasswordCriteria{

    public static final String ALPHA_CHARS_UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHA_CHARS_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMERIC_CHARS = "0123456789";
    public static final String OTHER_CHARS = "!@#$%^&*()_+<>?{}[]|*-";
    public static final int DEFAULT_LEN = 8;
	
		
	private int pwdLen = DEFAULT_LEN;

	private boolean alphaUpperChars = true;
	private boolean alphaLowerChars = true;
	private boolean numericChars = true;
	private boolean isOtherChars = true;
	/**
	 * @return the pwdLen
	 */
	public int getPwdLen() {
		return pwdLen;
	}
	/**
	 * @param pwdLen the pwdLen to set
	 */
	public void setPwdLen(int pwdLen) {
		this.pwdLen = pwdLen;
	}
	/**
	 * @return the alphaUpperChars
	 */
	public boolean isAlphaUpperChars() {
		return alphaUpperChars;
	}
	/**
	 * @param alphaUpperChars the alphaUpperChars to set
	 */
	public void setAlphaUpperChars(boolean alphaUpperChars) {
		this.alphaUpperChars = alphaUpperChars;
	}
	/**
	 * @return the alphaLowerChars
	 */
	public boolean isAlphaLowerChars() {
		return alphaLowerChars;
	}
	/**
	 * @param alphaLowerChars the alphaLowerChars to set
	 */
	public void setAlphaLowerChars(boolean alphaLowerChars) {
		this.alphaLowerChars = alphaLowerChars;
	}
	/**
	 * @return the numericChars
	 */
	public boolean isNumericChars() {
		return numericChars;
	}
	/**
	 * @param numericChars the numericChars to set
	 */
	public void setNumericChars(boolean numericChars) {
		this.numericChars = numericChars;
	}
	/**
	 * @return the isOtherChars
	 */
	public boolean isOtherChars() {
		return isOtherChars;
	}
	/**
	 * @param isOtherChars the isOtherChars to set
	 */
	public void setOtherChars(boolean isOtherChars) {
		this.isOtherChars = isOtherChars;
	}
	/**
	 * @return the alphaCharsUpperCase
	 */
	public static String getAlphaCharsUpperCase() {
		return ALPHA_CHARS_UPPER_CASE;
	}
	/**
	 * @return the alphaCharsLowerCase
	 */
	public static String getAlphaCharsLowerCase() {
		return ALPHA_CHARS_LOWER_CASE;
	}
	/**
	 * @return the numericChars
	 */
	public static String getNumericChars() {
		return NUMERIC_CHARS;
	}
	/**
	 * @return the otherChars
	 */
	public static String getOtherChars() {
		return OTHER_CHARS;
	}
	/**
	 * @return the defaultLen
	 */
	public static int getDefaultLen() {
		return DEFAULT_LEN;
	}



}
