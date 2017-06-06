package com.loujie.www.lucene3_5_0;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.FlagsAttribute;
import org.apache.lucene.analysis.tokenattributes.KeywordAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PayloadAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import com.loujie.www.entity.User;

public class LuceneUtils2 {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static Directory directory = null;// 索引位置
	private static IndexWriter indexWriter = null;// 写数据到索引的对象
	private static IndexReader indexReader = null;// 读取索引信息

	/**
	 * 1.创建索引
	 * 
	 * @param indexPath
	 * @throws IOException
	 */
	public static void createIndex(String indexPath) throws IOException {
		// 1.创建Directory,索引所在的位置
		directory = getDirectory(indexPath);
		// 2.声明IndexWriter
		indexWriter = getIndexWriter(directory);
		// 3.创建文档
		List<Document> lists = createDocuments();
		// 4.将文档添加到indexWriter中
		indexWriter.addDocuments(lists);
		// 5.提交
		indexWriter.commit();
		// 6.关闭,如果不提交,直接关闭,也可以保存上述的记录文档
		// indexWriter.close();
	}

	/**
	 * 2.索引信息
	 * 
	 * @param indexPath
	 * @throws IOException
	 */
	public static void indexInfo(String indexPath) throws IOException {
		// 1.创建Directory,索引所在的位置
		directory = getDirectory(indexPath);
		// 2.查询相关配置数量信息
		indexReader = getIndexReader(directory);
		System.err.println("maxDoc:" + indexReader.maxDoc());
		System.err.println("numDocs:" + indexReader.numDocs());
		System.err.println("numDeletedDocs:" + indexReader.numDeletedDocs());
	}

	/**
	 * 查询分页
	 * 
	 * @param indexPath
	 *            索引位置
	 * @param pageNum
	 *            从1开始,第几页
	 * @param pageSize
	 *            每页多少条
	 * @throws IOException
	 * @throws org.apache.lucene.queryParser.ParseException
	 */
	public static void indexPage(String indexPath, Integer pageNum, Integer pageSize, String queryStr) throws IOException, org.apache.lucene.queryParser.ParseException {
		// 1.创建Directory,索引所在的位置
		directory = getDirectory(indexPath);
		// 2.IndexReader
		indexReader = getIndexReader(directory);
		// 3.查询对象创建
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		// 3.1.Query条件
		QueryParser queryParser = new QueryParser(Version.LUCENE_35, "desc", new StandardAnalyzer(Version.LUCENE_35));
		Query query = queryParser.parse(queryStr);
		// 4.查询
		int n = pageNum * pageSize;// 最起码的数量
		TopDocs topDocs = indexSearcher.search(query, n);
		// 5.手动的分页
		System.err.println("总共有:" + topDocs.totalHits);
		for (int start = (pageNum - 1) * pageSize; ((start < topDocs.totalHits) && (start < pageNum * pageSize)); start++) {
			Document tempDoc = indexSearcher.doc(topDocs.scoreDocs[start].doc);
			System.err.println(topDocs.scoreDocs[start] + "," + tempDoc.get("toString"));
		}
		indexSearcher.close();
	}

	/**
	 * 查询分页
	 * 
	 * @param indexPath
	 *            索引位置
	 * @param pageNum
	 *            从1开始,第几页
	 * @param pageSize
	 *            每页多少条
	 * @param queryStr
	 *            status:1 AND desc:i
	 * @param filter 
	 * @throws IOException
	 * @throws org.apache.lucene.queryParser.ParseException
	 */
	public static void indexPage(String indexPath, Integer pageNum, Integer pageSize, String queryStr, Filter filter) throws IOException, org.apache.lucene.queryParser.ParseException {
		// 1.创建Directory,索引所在的位置
		directory = getDirectory(indexPath);
		// 2.IndexReader
		indexReader = getIndexReader(directory);
		// 3.查询对象创建
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		// 3.1.Query条件
		QueryParser queryParser = new QueryParser(Version.LUCENE_35, "desc", new StandardAnalyzer(Version.LUCENE_35));
		Query query = queryParser.parse(queryStr);
		// 4.查询
		int n = pageNum * pageSize;// 最起码的数量
		TopDocs topDocs = null;
		if (filter == null) {
			topDocs = indexSearcher.search(query, n);
		} else {
			topDocs = indexSearcher.search(query, filter, n);
		}
		// 5.手动的分页
		System.err.println("总共有:" + topDocs.totalHits);
		for (int start = (pageNum - 1) * pageSize; ((start < topDocs.totalHits) && (start < pageNum * pageSize)); start++) {
			Document tempDoc = indexSearcher.doc(topDocs.scoreDocs[start].doc);
			System.err.println(topDocs.scoreDocs[start] + "," + tempDoc.get("toString"));
		}
		indexSearcher.close();
	}

	/**
	 * 查询分页,携带排序
	 * 
	 * @param indexPath
	 *            索引位置
	 * @param pageNum
	 *            从1开始,第几页
	 * @param pageSize
	 *            每页多少条
	 * @param queryStr
	 *            status:1 AND desc:i
	 * @param sort
	 *            排序对象
	 * @throws IOException
	 * @throws org.apache.lucene.queryParser.ParseException
	 */
	public static void indexPage(String indexPath, Integer pageNum, Integer pageSize, String queryStr, Sort sort) throws IOException, org.apache.lucene.queryParser.ParseException {
		// 1.创建Directory,索引所在的位置
		directory = getDirectory(indexPath);
		// 2.IndexReader
		indexReader = getIndexReader(directory);
		// 3.查询对象创建
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		// 3.1.Query条件
		QueryParser queryParser = new QueryParser(Version.LUCENE_35, "desc", new StandardAnalyzer(Version.LUCENE_35));
		Query query = queryParser.parse(queryStr);
		// 4.查询
		int n = pageNum * pageSize;// 最起码的数量
		TopDocs topDocs = null;
		if (sort == null) {
			topDocs = indexSearcher.search(query, n);
		} else {
			// sort不可为空
			topDocs = indexSearcher.search(query, n, sort);
		}
		// 5.手动的分页
		System.err.println("总共有:" + topDocs.totalHits);
		for (int start = (pageNum - 1) * pageSize; ((start < topDocs.totalHits) && (start < pageNum * pageSize)); start++) {
			Document tempDoc = indexSearcher.doc(topDocs.scoreDocs[start].doc);
			System.err.println(topDocs.scoreDocs[start] + "," + tempDoc.get("toString"));
		}
		indexSearcher.close();
	}

	/**
	 * 分词器
	 * 
	 * @param worldStr
	 * @param analyzer
	 * @throws IOException
	 */
	public static void partcipleAnalyzer(String worldStr, Analyzer analyzer) throws IOException {
		TokenStream ts = analyzer.tokenStream("name", new StringReader(worldStr));
		// 1.单词
		CharTermAttribute cta = ts.addAttribute(CharTermAttribute.class);
		// 2.位置偏移量
		OffsetAttribute oa = ts.addAttribute(OffsetAttribute.class);
		// 3.单词与单词之间的增量
		PositionIncrementAttribute pia = ts.addAttribute(PositionIncrementAttribute.class);
		//
		FlagsAttribute fa = ts.addAttribute(FlagsAttribute.class);
		KeywordAttribute ka = ts.addAttribute(KeywordAttribute.class);
		PayloadAttribute pa = ts.addAttribute(PayloadAttribute.class);
		while (ts.incrementToken()) {
			System.err.print("[" + cta.toString() + "]");
			System.err.println("[" + oa + "]");
			System.err.println("[" + pia + "]");
			System.err.println("[" + fa + "]");
			System.err.println("[" + ka + "]");
			System.err.println("[" + pa + "]");
		}
		ts.close();
		System.err.println("");
		System.err.println("----------------------------------");
	}

	/**
	 * 创建索引存储位置
	 * 
	 * @param indexPath
	 *            可填;索引存放位置,如果不填默认为内存索引
	 * @return
	 */
	private static Directory getDirectory(String indexPath) {
		if (directory == null) {
			if (indexPath == null || indexPath.isEmpty()) {
				// 声明索引位置在内存中,有点是速度快,但不可以持久
				directory = new RAMDirectory();
			} else {
				try {
					// 采用存储在文件系统中,它会根据实际情况,选择一种最优的方式创建
					directory = FSDirectory.open(new File(indexPath));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return directory;
	}

	/**
	 * 初始化IndexWriter
	 * 
	 * @param directory
	 *            索引位置
	 * @return
	 * @throws CorruptIndexException
	 * @throws LockObtainFailedException
	 * @throws IOException
	 */
	private static IndexWriter getIndexWriter(Directory directory) throws CorruptIndexException, LockObtainFailedException, IOException {
		if (indexWriter == null) {
			// 2.声明IndexWriter
			// 2.1创建IndexWriter需要一些配置及解析器
			IndexWriterConfig conf = null;
			// 2.2解析器,它主要是为了写入事,对一些文档、描述等进行分词解析
			// 我们这里声明的是标准解析器,(汉字:解析成一个一个的;单词:根据空格分开)
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
			// 2.3IndexWriterConfig的初始化
			conf = new IndexWriterConfig(Version.LUCENE_35, analyzer);
			// 2.3.1控制以后每次都是创建,也可是追加,更新等
			conf.setOpenMode(OpenMode.CREATE);
			// 2.4初始化IndexWriter
			indexWriter = new IndexWriter(directory, conf);
		}
		return indexWriter;
	}

	/**
	 * 获取IndexReder
	 * 
	 * @param directory
	 *            索引
	 * @return
	 * @throws IOException
	 */
	private static IndexReader getIndexReader(Directory directory) throws IOException {
		if (indexReader != null) {
			// 如果有改变,就变成ir；如果没有变更ir==null;
			IndexReader ir = IndexReader.openIfChanged(indexReader);
			if (ir != null) {
				// 需先关闭
				indexReader.close();
				indexReader = ir;
			}
		} else {
			indexReader = IndexReader.open(directory);
		}
		return indexReader;
	}

	/**
	 * 生成文档
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	private static List<Document> createDocuments() throws FileNotFoundException {
		List<Document> listDocument = new ArrayList<>();
		for (User user : getUsers()) {
			Document tempDoc = new Document();
			// 1.添加一个id（域）,存储并索引,但不分词,不记录所以的norms
			tempDoc.add(new NumericField("id", Store.YES, false).setIntValue(user.getId()));
			// 2.再添加一个域phone,存储并索引，同时记录索引的norms,但不分词
			tempDoc.add(new Field("phone", user.getPhone(), Store.YES, Index.NOT_ANALYZED));
			// 3.名称,进行存储，分词，索引，但不记录索引的norms
			tempDoc.add(new Field("name", user.getName(), Store.YES, Index.ANALYZED_NO_NORMS));
			// 4.描述,不存储,进行分词，索引
			tempDoc.add(new Field("desc", user.getPassword(), Store.NO, Index.ANALYZED));
			// 5.身份证号,存储,进行索引，但不分词及记录索引的norms
			tempDoc.add(new Field("idno", user.getIdno(), Store.YES, Index.NOT_ANALYZED_NO_NORMS));
			tempDoc.add(new Field("status", user.getStatus().toString(), Store.YES, Index.NOT_ANALYZED_NO_NORMS));
			try {
				// 6.记录创建时间
				tempDoc.add(new NumericField("createTime", Store.YES, true).setLongValue(sdf.parse(user.getCreateTime()).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// 7.记录整体信息;存储,但不分词，不索引
			tempDoc.add(new Field("toString", user.toString(), Store.YES, Index.NO));
			listDocument.add(tempDoc);
		}
		return listDocument;
	}

	/**
	 * 获取一个用户列表
	 * 
	 * @return
	 */
	private static List<User> getUsers() {
		List<User> listUsers = new ArrayList<>();
		listUsers.add(new User(2, "15801852667", "丛祺", "i am jiege", "435432199010208765", 1, "2016-08-19 13:52:29"));
		listUsers.add(new User(39, "13671588178", "李华", "so easy", "310115198806120925", 1, "2015-08-19 13:52:29"));
		listUsers.add(new User(56, "13761168844", "张敏", "da jia hao", "310115198703305214", 1, "2014-08-19 14:04:04"));
		listUsers.add(new User(67, "13671812824", "徐晓琼", "tong xue men xin ku le!", "310115198801282220", 0, "2013-08-19 14:04:04"));
		listUsers.add(new User(47, "13917041493", "王寒", "da he xiang dong liu", "31011519891210323x", 1, "2012-08-19 14:04:04"));
		listUsers.add(new User(8, "13817770047", "罗楠", "i am jiege ma", "430781199201215014", 1, "2011-08-19 14:04:04"));
		listUsers.add(new User(95, "18016306785", "厉论", "da da da", "430111198810310352", 1, "2011-08-19 14:04:04"));
		listUsers.add(new User(140, "18017005450", "顾福祺", "ha ha", "310110198811041517", 1, "2012-08-19 14:04:04"));
		listUsers.add(new User(161, "15800769360", "张荣", "ss", "310230198902044976", 1, "2013-08-19 14:04:04"));
		return listUsers;
	}

	/**
	 * 关闭
	 * 
	 * @param indexObj
	 */
	public static void close() {
		try {
			if (indexReader != null) {
				indexReader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (indexWriter != null) {
				indexWriter.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提交
	 */
	public static void commit() {
		try {
			if (indexWriter != null) {
				indexWriter.commit();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
