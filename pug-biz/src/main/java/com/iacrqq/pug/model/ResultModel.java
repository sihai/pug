/**
 * pug
 */
package com.iacrqq.pug.model;

import java.util.List;

/**
 * 
 * @author sihai
 * 
 */
public class ResultModel<T> extends BaseModel {
	private BaseQueryModel queryModel;

	private Long totalItem;	// 	
	private Long totalPage;	//
	private Double time; 		// 耗时, 单位秒

	private List<T> itemList;

	public static <T> ResultModel buildResultModel(BaseQueryModel queryModel,
			List<T> itemList, Long totalItem, Double time) {
		ResultModel<T> resultModel = new ResultModel<T>();
		resultModel.setQueryModel(queryModel);
		resultModel.setItemList(itemList);
		resultModel.setTotalItem(totalItem);
		resultModel.time = time;
		resultModel
				.setTotalPage((totalItem - 1) / queryModel.getPageSize() + 1);

		return resultModel;
	}

	public static <T> ResultModel buildResultModel(BaseQueryModel queryModel,
			List<T> itemList, Long totalItem) {
		return buildResultModel(queryModel, itemList, totalItem, null);
	}

	public BaseQueryModel getQueryModel() {
		return queryModel;
	}

	public Long getTotalItem() {
		return totalItem;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public List<T> getItemList() {
		return itemList;
	}

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public void setQueryModel(BaseQueryModel queryModel) {
		this.queryModel = queryModel;
	}

	public void setTotalItem(Long totalItem) {
		this.totalItem = totalItem;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}
}
