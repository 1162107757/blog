package top.hjie.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="admin")
public class Admin implements Serializable {

	private static final long serialVersionUID = -3415946981802507034L;
	
	@Id
	@Column(name="adminId")
	private String adminId;
	@Column(name="adminCode")
	private String adminCode;
	@Column(name="password")
	private String password;
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminCode() {
		return adminCode;
	}
	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
