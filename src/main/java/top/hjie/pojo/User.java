package top.hjie.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = -4292977806446399937L;
	
	@Id
	@Column(name="userId")
	private String userId;
	@Column(name="userCode")
	private String userCode;
	@Column(name="userName")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="sex")
	private Boolean sex; 
	@Column(name="birthday")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date birthday;
	@Column(name="country")
	private Integer country;
	@Column(name="province")
	private Integer province;
	@Column(name="city")
	private Integer city;
	@Column(name="area")
	private Integer area;
	@Column(name="status")
	private Boolean status;
	@Column(name="account")
	private String account;
	@Column(name="registerDate")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date registerDate;
	@Column(name="endLoginDate")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date endLoginDate;
	@Column(name="email")
	private String email;
	@Column(name="phone")
	private String phone;
	@Column(name="headUrl")
	private String headUrl;
	
	@Transient
	private String countryName;
	@Transient
	private String provinceName;
	@Transient
	private String cityName;
	@Transient
	private String areaName;
	
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getCountry() {
		return country;
	}
	public void setCountry(Integer country) {
		this.country = country;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Date getEndLoginDate() {
		return endLoginDate;
	}
	public void setEndLoginDate(Date endLoginDate) {
		this.endLoginDate = endLoginDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	
}
