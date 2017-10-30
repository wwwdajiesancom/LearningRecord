package com.loujie.util.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.DatabaseMetaData;

public class DBHelper {

	public static final String url = "jdbc:mysql://localhost:3306/local_study";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "admin";

	public static Connection conn = null;

	public static Object[] getColumns(String tableName) {
		List<String> columns = new ArrayList<>();
		try {
			Class.forName(name);// 指定连接类型
			conn = DriverManager.getConnection(url, user, password);// 获取连接
			DatabaseMetaData dmd = (DatabaseMetaData) conn.getMetaData();
			ResultSet resultSet = dmd.getColumns(conn.getCatalog(), "", tableName, null);
			while (resultSet.next()) {
				columns.add(resultSet.getString("COLUMN_NAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return columns.toArray();
	}

	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
