package com.loujie.www.elastic;

import org.elasticsearch.client.Client;

/**
 * 定义钩子函数,可以更好的处理异常
 * 
 * @author loujie
 *
 */
public abstract class ElasticSearchCallback {

	private Client client = null;

	/**
	 * 初始化
	 */
	private void init() {
		this.client = ElasticSearchResource.getClient();
	}

	/**
	 * 实现体,完成主要的功能
	 * 
	 * @param solrClient
	 * @param cla
	 * @return
	 */
	public abstract <T> T callback(Client client, Class<T> cla);

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
			return this.callback(client, cla);
		} catch (Exception e) {
			try {
				return cla.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				return null;
			}
		}
	}

}
