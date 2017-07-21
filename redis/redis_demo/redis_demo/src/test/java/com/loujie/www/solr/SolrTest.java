package com.loujie.www.solr;

import java.io.IOException;
import java.util.Arrays;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;

public class SolrTest {

	private String path = "http://lm.loujie.com/solr";
	private SolrClient solrClient;

	@Test
	public void li() {
		solrClient = new HttpSolrClient(path);
		SolrParams params = new SolrQuery("id:1");
		try {
			QueryResponse queryResponse = solrClient.query("solr_core", params);
			SolrDocumentList solrDoc = queryResponse.getResults();
			System.err.println(Arrays.toString(solrDoc.toArray()));
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void lll() {
		String path = "/abcb/cdlil";
		String[] paths = path.split("/");
		for (String ll : paths) {
			System.err.println(ll);
		}
	}

}
