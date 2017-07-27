package com.loujie.www.solr;

import java.io.IOException;
import java.util.Arrays;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.SolrParams;

public class SolrJUtils {

	/**
	 * 例子
	 */
	public static void example() {
		new SolrJCallback() {
			@Override
			public <T> T callback(SolrClient solrClient, Class<T> cla) throws SolrServerException, IOException {
				SolrParams params = new SolrQuery("id:1");
				QueryResponse queryResponse = null;
				queryResponse = SolrJResource.getSolrClient().query("solr_core", params);
				SolrDocumentList solrDoc = queryResponse.getResults();
				System.err.println(Arrays.toString(solrDoc.toArray()));
				return null;
			}
		}.run(void.class);
	}

}
