package com.loujie.www.elastic;

import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;

/**
 * es查询
 * 
 * @author loujie
 *
 */
public class ElasticSearchUtils {

	/**
	 * 例子
	 */
	public static void example(int pageNum, int pageSize) {
		// 1.定义查询条件
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		// 1.1 type=1,并且不参与计算分数（score）值
		queryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termQuery("type", 1)));
		queryBuilder.must(QueryBuilders.multiMatchQuery("美国", "name"));
		// QueryBuilders.rangeQuery("payment").from(rangeDataDto.getFromValue()).to(rangeDataDto.getToValue())
		// QueryBuilders.matchQuery("name", dto.getName())
		// queryBuilder.filter(QueryBuilders.hasChildQuery("schoolBranch", childQuery));

		// 2.设置一些分页、排序设置、高亮、需要那些字段
		SearchRequestBuilder searchRequestBuilder = ElasticSearchResource.getClient()//
				.prepareSearch("course_collections")// 索引名称
				.setTypes("course")// 索引中的类型
				.setQuery(queryBuilder)// 查询条件
		;
		// 2.1设置分页
		int from = (pageNum - 1) * pageSize;
		int size = pageSize;
		searchRequestBuilder//
				.setFrom(from)// 设置起始值
				.setSize(size)// 设置多少条
		;
		// 2.2排序设置
		searchRequestBuilder//
				.addSort("id", SortOrder.ASC)//
		;
		// 2.3高亮（注意：高亮不能随意的设置，查询的条件中及结果中必须要有这个字段）
		boolean isHighligh = true;
		if (isHighligh) {
			searchRequestBuilder//
					.addHighlightedField("name")// 需要高亮的字段,可以设置多个
					.setHighlighterPreTags("<i>").setHighlighterPostTags("</i>")// 添加相应的样式
			;
		}
		// 2.4要取出的字段
		String[] fields = {"id", "name", "price", "pay_type", "type", "created_at"};
		searchRequestBuilder.addFields(fields);
		// 3.查询,并处理结果
		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
		SearchHits searchHits = searchResponse.getHits();
		System.err.println("符合条件的共有：" + searchHits.getTotalHits());
		System.err.println("查询出的部分内容：");
		int i = 1;
		for (SearchHit hit : searchHits.getHits()) {
			// 当用到了searchRequestBuilder.addFields方法后,hit.getFields()它才生效,而hit.getSourceAsString()失效
			for (Map.Entry<String, SearchHitField> entry : hit.getFields().entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue().getValue());
			}
			if (hit.getHighlightFields() != null && !hit.getHighlightFields().isEmpty()) {
				for (Map.Entry<String, HighlightField> entry : hit.getHighlightFields().entrySet()) {
					System.out.println(entry.getKey() + ":" + entry.getValue().getFragments());
				}
			}
			System.out.println("end############################" + i++);
		}
	}

}
