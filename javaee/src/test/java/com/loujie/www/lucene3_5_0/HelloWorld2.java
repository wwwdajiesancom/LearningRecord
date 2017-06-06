package com.loujie.www.lucene3_5_0;

import java.io.IOException;

import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.util.Version;
import org.junit.After;
import org.junit.Test;

public class HelloWorld2 {

	private String indexPath = "D:\\lucene-3.5.0\\datasource\\index2";

	@Test
	public void index1// 创建索引
	() {
		try {
			// 1.创建索引
			LuceneUtils2.createIndex(indexPath);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

	@Test
	public void indexInfo2// 输出索引信息
	() {
		try {
			// 输出索引信息
			LuceneUtils2.indexInfo(indexPath);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

	@Test
	public void indexPage3() throws ParseException {
		try {
			LuceneUtils2.indexPage(indexPath, 1, 10, "status:1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void indexPage4() throws IOException, ParseException {
		// Sort.INDEXORDER=通过doc来排序
		// Sort.RELEVANCE=通过评分score来排序
		Sort sort = new Sort(new SortField("createTime", SortField.LONG, true));
		LuceneUtils2.indexPage(indexPath, 1, 10, "status:1 OR desc:ma", sort);
	}

	@Test
	public void indexPage5() {
		// SearcherManager searcherManager = new SearcherManager(dir, warmer, es)
	}

	@Test
	public void analyzer() {
		try {
			String string = "my name is loujie , thank you!My email loujie@btte.net,My QQ is 805240128";
			string = "我来自中国，我的名字叫做娄杰，QQ帐号是805240128.";
			LuceneUtils2.partcipleAnalyzer(string, new StandardAnalyzer(Version.LUCENE_35));
			LuceneUtils2.partcipleAnalyzer(string, new StopAnalyzer(Version.LUCENE_35));
			LuceneUtils2.partcipleAnalyzer(string, new SimpleAnalyzer(Version.LUCENE_35));
			LuceneUtils2.partcipleAnalyzer(string, new WhitespaceAnalyzer(Version.LUCENE_35));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@After
	public void after() {
		LuceneUtils2.close();
		System.err.println("------------ok------------");
	}

}
