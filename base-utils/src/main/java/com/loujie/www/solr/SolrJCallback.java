package com.loujie.www.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;

/**
 * 定义钩子函数,可以更好的处理异常
 * 
 * @author loujie
 *
 */
public abstract class SolrJCallback {

	private SolrClient solrClient;

	/**
	 * 初始化
	 */
	private void init() {
		this.solrClient = SolrJResource.getSolrClient();
	}

	/**
	 * 实现体,完成主要的功能
	 * 
	 * @param solrClient
	 * @param cla
	 * @return
	 */
	public abstract <T> T callback(SolrClient solrClient, Class<T> cla) throws SolrServerException, IOException;

	/**
	 * 对外提供的运行方法
	 * 
	 * @param cla
	 *            返回值类型
	 * @return
	 */
	public <T> T run(Class<T> cla) {
		this.init();
		try {
			return this.callback(solrClient, cla);
		} catch (Exception e) {
			try {
				return cla.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				return null;
			}
		}
	}

}
