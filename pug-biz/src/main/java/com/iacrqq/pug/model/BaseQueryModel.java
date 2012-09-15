/**
 * pug
 */
package com.iacrqq.pug.model;

/**
 * 
 * @author sihai
 * 
 */
public class BaseQueryModel extends BaseModel {
	public static final Long DEFAULT_PAGE_SIZE = 20L;
	public static final Long MAX_PAGE_SIZE = 100 * DEFAULT_PAGE_SIZE;

	private Long currentPage = 1L;
	private Long pageSize = DEFAULT_PAGE_SIZE;

	public Long getCurrentPage() {
		return currentPage;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getStart() {
		currentPage = this.currentPage == null || this.currentPage < 1L ? 1L
				: this.currentPage;
		pageSize = this.pageSize == null || this.pageSize > MAX_PAGE_SIZE ? DEFAULT_PAGE_SIZE
				: this.pageSize;

		return (currentPage - 1) * pageSize;
	}

	public Long getEnd() {
		currentPage = this.currentPage == null || this.currentPage < 1L ? 1L
				: this.currentPage;
		pageSize = this.pageSize == null || this.pageSize > MAX_PAGE_SIZE ? DEFAULT_PAGE_SIZE
				: this.pageSize;

		return (currentPage - 1) * pageSize + pageSize;
	}

	// 子类请覆盖
	public static BaseQueryModel build() {
		return null;
	}
}
