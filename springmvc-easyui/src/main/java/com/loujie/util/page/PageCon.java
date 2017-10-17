package com.loujie.util.page;

/**
 * 分页基本参数,分页是从0开始的
 * 
 * @author loujie
 *
 */
public class PageCon extends BaseDto {

	private static final long serialVersionUID = -3062178248154198511L;

	private static final int default_page_size = 20;// 默认一页显示条数

	private static final int default_page_number_diff = 0;// 如果默认页数从1开始就为0;如果从0开始就为1

	protected Integer page_number;// 第几页,默认1

	protected Integer page_size;// 每页多少条,默认default_page_size

	protected String sort;// 那个字段排序

	protected String order;// 如何排序

	public void setPage(Integer page) {
		this.setPageNum(page);
	}

	public Integer getPage() {
		return this.getPageNum();
	}

	public void setRows(Integer rows) {
		this.setPageSize(rows);
	}

	public Integer getRows() {
		return this.getPageSize();
	}

	public Integer getPageNum() {
		if (this.page_number == null || this.page_number < 0) {
			return 1;
		}
		return (page_number + default_page_number_diff);
	}

	public void setPageNum(Integer page_number) {
		this.page_number = page_number;
	}

	public Integer getPageSize() {
		if (this.page_size == null || this.page_size <= 0) {
			return default_page_size;
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
