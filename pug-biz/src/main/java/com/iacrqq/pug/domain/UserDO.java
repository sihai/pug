/**
 * pug
 */
package com.iacrqq.pug.domain;

import java.util.Date;

/**
 * 
 * @author sihai
 *
 */
public class UserDO extends BaseDO {
	
	public static final String QQ_SEX_MALE = "男";
	public static final String QQ_SEX_FEMALE = "女";
	
	public static final Long USER_FROM_PUG = 0L;
	public static final Long USER_FROM_QQ = 1L;
	
	private String name;		// 
	private Long   sex;		//
	private String password;	// 
	private String email;		//
	private String mobile;		//
	private Date   birthday;	//
	private String logo;		//
	private Long   grade;		//
	private Long   type;		//
	private Long   status;		//
	private Long   fromWhere;	//
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getSex() {
		return sex;
	}
	public void setSex(Long sex) {
		this.sex = sex;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Long getGrade() {
		return grade;
	}
	public void setGrade(Long grade) {
		this.grade = grade;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public Long getFromWhere() {
		return fromWhere;
	}
	public void setFromWhere(Long fromWhere) {
		this.fromWhere = fromWhere;
	}
}
