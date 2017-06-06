package com.loujie.www.lucene3_5_0;

import java.io.IOException;
import java.util.UUID;

import org.junit.Test;

public class LuceneHelloWord {

	private String indexPath = "D:\\lucene-3.5.0\\datasource\\index";
	/**
	 * 1.测试创建索引
	 */
	@Test
	public void createIndex1() {
		try {
			LuceneUtils.createIndex(indexPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.err.println("ok");
	}

	/**
	 * 2.输出索引数量
	 */
	@Test
	public void printlnIndex2() {
		try {
			LuceneUtils.printIndex(indexPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.err.println("ok");
	}

	/**
	 * 3.查询索引
	 */
	@Test
	public void searchIndex3() {
		try {
			LuceneUtils.searchIndex(indexPath, "id", "2", 10);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void searchByRange() {
		LuceneUtils.searchByRange(indexPath, "phone", "1", "4", 10);
	}

	@Test
	public void searchByPrefix() {
		LuceneUtils.searchByPrefix(indexPath, "name", "寒", 3);
	}

	@Test
	public void searchByBoolean() {
		LuceneUtils.searchByBoolean(indexPath, 3);
	}

	@Test
	public void searchByPhrase() {
		LuceneUtils.searchByPhraseQuery(indexPath, 3);
	}

	@Test
	public void serachByFuzzy() {
		LuceneUtils.searchFuzzy(indexPath, 4);
	}

}
