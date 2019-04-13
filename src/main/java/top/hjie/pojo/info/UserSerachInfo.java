package top.hjie.pojo.info;

import java.io.Serializable;

public class UserSerachInfo implements Serializable {

	private static final long serialVersionUID = 8363551331133559138L;
	
	private String userInfo;
	private String regStartDate;
	private String regEndDate;
	
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	public String getRegStartDate() {
		return regStartDate;
	}
	public void setRegStartDate(String regStartDate) {
		this.regStartDate = regStartDate;
	}
	public String getRegEndDate() {
		return regEndDate;
	}
	public void setRegEndDate(String regEndDate) {
		this.regEndDate = regEndDate;
	}
	
}
