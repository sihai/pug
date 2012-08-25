/**
 * pug
 */
package com.iacrqq.pug.model;

import java.util.Date;

/**
 * 
 * @author sihai
 *
 */
public class PugQueryModel extends BaseQueryModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5201645907886446360L;
	
	private Long owner;
	private Long type;
	private Date startTime;
	private Date endTime;
	
	public Long getOwner() {
		return owner;
	}
	public void setOwner(Long owner) {
		this.owner = owner;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * 
	 * @param owner
	 * @param startTime
	 * @param endTime
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public static final PugQueryModel buildQueryModel(Long owner, Long type, Date startTime, Date endTime, Long currentPage, Long pageSize) {
		PugQueryModel queryModel = new PugQueryModel();
		queryModel.owner = owner;
		queryModel.type = type;
		queryModel.startTime = startTime;
		queryModel.endTime = endTime;
		queryModel.setCurrentPage(currentPage);
		queryModel.setPageSize(pageSize);
		return queryModel;
	}
}
