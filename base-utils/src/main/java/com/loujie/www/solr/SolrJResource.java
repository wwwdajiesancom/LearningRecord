package com.loujie.www.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 * 实例化
 * 
 * @author loujie
 *
 */
public class SolrJResource {

	private static final String solr_path = "http://lm.loujie.com/solr";
	private static SolrClient solrClient;

	static {
		solrClient = new HttpSolrClient(solr_path);
	}

	/**
	 * 对外提供的
	 * 
	 * @return
	 */
	public static SolrClient getSolrClient() {
		return solrClient;
	}

}
