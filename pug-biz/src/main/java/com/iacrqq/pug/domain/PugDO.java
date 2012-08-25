/**
 * pug
 */
package com.iacrqq.pug.domain;

import java.util.List;

/**
 * 
 * @author sihai
 *
 */
public class PugDO extends BaseDO {

	private String name;			// 
	private String description;	//
	private UserDO owner;			//
	private Long   status;			//
	private Long   type;			//
	
	private List<PointDO> pointList;	//
	
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
	public UserDO getOwner() {
		return owner;
	}
	public void setOwner(UserDO owner) {
		this.owner = owner;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public List<PointDO> getPointList() {
		return pointList;
	}
	public void setPointList(List<PointDO> pointList) {
		this.pointList = pointList;
	}
}
