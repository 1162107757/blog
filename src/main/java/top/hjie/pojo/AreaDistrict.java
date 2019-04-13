package top.hjie.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;



@Table(name="areaDistrict")
public class AreaDistrict implements Serializable{

	private static final long serialVersionUID = -3186536046482485034L;
	
	@Id
	@Column(name = "districtId")
	private Integer districtId;   
	@Column(name = "countryId")
	private Integer countryId;
	@Column(name = "sequence")
	private Integer sequence;
	@Column(name="districtCode")
	private String districtCode;
	@Column(name="districtName")
	private String districtName;
	@Column(name="level")
	private Integer level;
	@Column(name = "parentId") 
	private Integer parentId;
	
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
}
