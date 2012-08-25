/**
 * pug
 */
package com.iacrqq.pug.domain;

/**
 * 
 * @author sihai
 *
 */
public class PointDO extends BaseDO {

	private String name;			// 
	private String description;	//
	private Double longitude;		//
	private Double latitude;		//
	private PugDO 	pug;			//
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public PugDO getPug() {
		return pug;
	}
	public void setPug(PugDO pug) {
		this.pug = pug;
	}
}
