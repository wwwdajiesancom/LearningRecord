package com.loujie.util.page;

import java.io.Serializable;
import java.util.ArrayList;

import com.github.pagehelper.Page;

/**
 * 
 * @author loujie
 *
 */
public class PageResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4712940369783818982L;

	public PageResult() {

	}

	@SuppressWarnings({ "rawtypes" })
	public PageResult(Page page) {
		this.init(page);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void init(Page page) {
		if (page == null) {
			return;
		}
		this.getRows().addAll(page.getResult());
		this.setPageNum(page.getPageNum());
		this.setPageSize(page.getPageSize());
		this.setPages(page.getPages());
		this.setTotal(page.getTotal());
	}

	protected void init(PageResult pageResult) {
		if (pageResult == null) {
			return;
		}
		this.setRows(pageResult.getRows());
		this.setPageNum(pageResult.getPageNum());
		this.setPageSize(pageResult.getPageSize());
		this.setPages(pageResult.getPages());
		this.setTotal(pageResult.getTotal());
	}

	/**
	 * 当前页码
	 */
	public int pageNum = 0;

	/**
	 * 总页数
	 */
	public int pages = 0;

	/**
	 * 每页条数
	 */
	public int pageSize = 0;

	/**
	 * 总条数
	 */
	public long total = 0;

	/**
	 * 数据
	 */
	public ArrayList<?> rows = new ArrayList<Object>();

	public Object params = null;// 传递条件参数

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public ArrayList<?> getRows() {
		return rows;
	}

	public void setRows(ArrayList<?> rows) {
		this.rows = rows;
	}

	public void setRows2(ArrayList<Object> rows) {
		this.rows = rows;
	}

	public void setRows3(ArrayList<?> rows) {
		this.rows = rows;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

}