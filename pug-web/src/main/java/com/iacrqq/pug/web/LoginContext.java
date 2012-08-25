/**
 * pug
 */
package com.iacrqq.pug.web;

import com.iacrqq.pug.domain.UserDO;

/**
 * 用戶上下文
 * @author sihai
 *
 */
public class LoginContext {
	
	private Boolean 	isLogin;
	private Long 		vistorId;
	private String 	vistorNick;
	private String  	logo;
	
	public LoginContext(Boolean isLogin, Long vistorId, String vistorNick, String logo) {
		this.isLogin = isLogin; 
		this.vistorId = vistorId;
		this.vistorNick = vistorNick;
		this.logo = logo;
	}
	
	public Boolean getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}
	
	public Long getVistorId() {
		return vistorId;
	}
	public void setVistorId(Long vistorId) {
		this.vistorId = vistorId;
	}
	public String getVistorNick() {
		return vistorNick;
	}
	public void setVistorNick(String vistorNick) {
		this.vistorNick = vistorNick;
	}
	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public static LoginContext fromUser(UserDO user) {
		LoginContext context = new LoginContext(true, user.getId(), user.getName(), user.getLogo());
		return context;
	}
}
