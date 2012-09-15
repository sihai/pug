/**
 * pug
 */
package com.iacrqq.pug.domain;

import java.io.Serializable;
import java.util.Date;

import com.iacrqq.util.PasswordUtil;

/**
 * 
 * @author sihai
 * 
 */
public class BaseDO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4166624369030106631L;
	
	protected Long id;
	protected Boolean isDeleted = false;
	protected Date gmtCreate;
	protected Date gmtModified;

	public Long getId() {
		return id;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
	public static void main(String[] args) {
		String passowrd = PasswordUtil.generatePassowrd(128);
		System.out.println(passowrd);
		System.out.println(PasswordUtil.encrypt(passowrd));
	}
}
