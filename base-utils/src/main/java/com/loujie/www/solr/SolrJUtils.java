package com.loujie.www.solr;

import java.io.IOException;
import java.util.Arrays;

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
		SolrParams params = new SolrQuery("id:1");
		try {
			QueryResponse queryResponse = SolrJResource.getSolrClient().query("core1", params);
			SolrDocumentList solrDoc = queryResponse.getResults();
			System.err.println(Arrays.toString(solrDoc.toArray()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
