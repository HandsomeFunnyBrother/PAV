package com.tedu.Dao;

import java.sql.*;

public class JDBC {
	private static final String DRIVERCLASS = "com.mysql.cj.jdbc.Driver";

	private static final String URL = "jdbc:mysql://localhost:3306/playgame1?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";

	private static final String USERNAME = "root";

	private static final String PASSWORD = "zxk545454";

	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	static {// 通过静态方法加载数据库驱动
		try {
			Class.forName(DRIVERCLASS);// 加载数据库驱动
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {// 创建数据库连接的方法
		Connection conn = threadLocal.get();// 从线程中获得数据库连接
		if (conn == null) {// 没有可用的数据库连接
			try {
				Class.forName(DRIVERCLASS);// 加载数据库驱动
				conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static boolean closeConnection() {// 关闭数据库连接的方法
		boolean isClosed = true;
		Connection conn = threadLocal.get();// 从线程中获得数据库连接
		threadLocal.set(null);// 清空线程中的数据库连接
		if (conn != null) {// 数据库连接可用
			try {
				conn.close();// 关闭数据库连接
			} catch (SQLException e) {
				isClosed = false;
				e.printStackTrace();
			}
		}
		return isClosed;
	}

}
