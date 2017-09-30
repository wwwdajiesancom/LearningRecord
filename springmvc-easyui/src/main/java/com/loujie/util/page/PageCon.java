package com.loujie.util.page;

/**
 * 分页基本参数,分页是从0开始的
 * 
 * @author loujie
 *
 */
public class PageCon extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3062178248154198511L;

	protected Integer page_number;// 第几页,默认0

	protected Integer page_size;// 每页多少条,默认36

	protected String sort;// 那个字段排序

	protected String order;// 如何排序

	public Integer getPageNum() {
		if (this.page_number == null || this.page_number < 0) {
			return 1;
		}
		return (page_number + 1);
	}

	public void setPageNum(Integer page_number) {
		this.page_number = page_number;
	}

	public Integer getPageSize() {
		if (this.page_size == null || this.page_size <= 0) {
			return 36;
		}
		return page_size;
	}

	public void setPageSize(Integer page_size) {
		this.page_size = page_size;
	}

	public Integer getPage_number() {
		return this.getPageNum();
	}

	public void setPage_number(Integer page_number) {
		this.page_number = page_number;
	}

	public Integer getPage_size() {
		return this.getPageSize();
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		if (this.order == null || this.order.isEmpty()) {
			if (!order.toLowerCase().equals("asc") && !order.toLowerCase().equals("desc")) {
				order = "asc";
			}
		}
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
