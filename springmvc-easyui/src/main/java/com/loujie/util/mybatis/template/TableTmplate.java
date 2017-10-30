package com.loujie.util.mybatis.template;

import com.loujie.util.mysql.DBHelper;

public class TableTmplate {

	private final static String wrap = "\r\n";
	private final static String space = "	";

	// 存放最终的mapper文件信息
	private StringBuilder resultBuilder = new StringBuilder();
	// 存放了mapper开头
	private StringBuilder namespaceBuilder = new StringBuilder();

	private String tableName = null;

	public TableTmplate(String tableName) {
		this.tableName = tableName;
		resultBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(wrap);
		resultBuilder.append(
				"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >")
				.append(wrap);
		this.setNamespace(null);
	}

	public TableTmplate(String tableName, String daoClassName) {
		this(tableName);
		this.setNamespace(daoClassName);
	}

	public void mapperBody() {

	}

	public void setNamespace(String daoClassName) {
		namespaceBuilder.delete(0, namespaceBuilder.length());
		namespaceBuilder.append("<mapper namespace=\"" + daoClassName + "\">").append(wrap);
	}

	public String getColumns() {
		StringBuilder columnsBuilder = new StringBuilder();
		columnsBuilder.append(space).append("<sql id=\"column\">").append(wrap);

		// 获取字段列表
		Object[] columns = DBHelper.getColumns(this.tableName);

		for (int i = 0; i < columns.length; i++) {
			if (i == columns.length - 1) {
				columnsBuilder.append(space).append(space).append("a.`" + columns[i] + "`").append(wrap);
			} else {
				columnsBuilder.append(space).append(space).append("a.`" + columns[i] + "`,").append(wrap);
			}
		}
		columnsBuilder.append(space).append("</sql>").append(wrap);
		return columnsBuilder.toString();
	}

	public String getWhere() {
		StringBuilder whereBuilder = new StringBuilder();
		whereBuilder.append(space).append("<sql id=\"where\">").append(wrap);
		whereBuilder.append(space).append(space).append("<if test=\"id != null\">").append(wrap);
		whereBuilder.append(space).append(space).append("and a.`id` = #{id}").append(wrap);
		whereBuilder.append(space).append(space).append("</if>").append(wrap);
		whereBuilder.append(space).append("</sql>").append(wrap);
		return whereBuilder.toString();
	}

	public String getTableEntity() {
		String[] segs = this.tableName.split("_");
		StringBuilder tableBuilder = new StringBuilder();
		for (String seg : segs) {
			tableBuilder.append((seg.charAt(0) + "").toUpperCase()).append(seg.substring(1, seg.length()));
		}
		return tableBuilder.toString();
	}

	public String getQuery(String... methodNames) {
		StringBuilder queryBuilder = new StringBuilder();

		if (methodNames != null && methodNames.length > 0) {
			for (String methodName : methodNames) {
				queryBuilder.append(space).append("<select id=\"" + methodName + "\" resultType=\""
						+ this.getTableEntity() + "\" parameterType=\"java.util.Map\">").append(wrap);
				queryBuilder.append(space).append(space).append("select").append(wrap);
				queryBuilder.append(space).append(space).append("<include refid=\"column\" />").append(wrap);
				queryBuilder.append(space).append(space).append("from").append(wrap);
				queryBuilder.append(space).append(space).append("<include refid=\"tables\" />").append(wrap);
				queryBuilder.append(space).append(space).append("where 1 = 1").append(wrap);
				queryBuilder.append(space).append(space).append("<include refid=\"where\"/>").append(wrap);
				queryBuilder.append(space).append(space).append("<include refid=\"order\"/>").append(wrap);
				queryBuilder.append(space).append("</select>").append(wrap).append(wrap);
			}
		}
		return queryBuilder.toString();
	}

	public String toMapper() {
		resultBuilder.append(namespaceBuilder.toString()).append(wrap).append(wrap);
		resultBuilder.append(space).append("<!-- 1.定义的ResultMap -->").append(wrap).append(wrap);

		resultBuilder.append(space).append("<!-- 2.定义表名称 -->").append(wrap);
		resultBuilder.append(space).append("<sql id=\"table\">" + tableName + "</sql>").append(wrap);
		resultBuilder.append(space).append("<sql id=\"tables\">" + tableName + " a</sql>").append(wrap).append(wrap);

		resultBuilder.append(space).append("<!-- 3.需要查询的字段及添加、排序 -->").append(wrap);
		resultBuilder.append(this.getColumns()).append(wrap);

		resultBuilder.append(space).append("<!-- 3.2查询条件 -->").append(wrap);
		resultBuilder.append(this.getWhere()).append(wrap);

		resultBuilder.append(space).append("<!-- 3.3排序 -->").append(wrap);
		resultBuilder.append(space).append("<sql id=\"order\"></sql>").append(wrap);

		resultBuilder.append(space).append("<!-- 4.查询模块 -->").append(wrap);
		resultBuilder.append(this.getQuery("get", "getList"));

		resultBuilder.append(space).append("<!-- 5.插入模块 -->").append(wrap);
		resultBuilder.append(space).append("<!-- 6.更新模块 -->").append(wrap);
		resultBuilder.append(space).append("<!-- 7.删除模块 -->").append(wrap);

		// 最下面部分
		resultBuilder.append(wrap).append("</mapper>").append(wrap);
		return resultBuilder.toString();
	}

}
