package com.tedu.Dao;

import java.sql.*;

public class JDBC {
	private static final String DRIVERCLASS = "com.mysql.cj.jdbc.Driver";

	private static final String URL = "jdbc:mysql://localhost:3306/playgame1?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";

	private static final String USERNAME = "root";

	private static final String PASSWORD = "zxk545454";

	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	static {// ͨ����̬�����������ݿ�����
		try {
			Class.forName(DRIVERCLASS);// �������ݿ�����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {// �������ݿ����ӵķ���
		Connection conn = threadLocal.get();// ���߳��л�����ݿ�����
		if (conn == null) {// û�п��õ����ݿ�����
			try {
				Class.forName(DRIVERCLASS);// �������ݿ�����
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

	public static boolean closeConnection() {// �ر����ݿ����ӵķ���
		boolean isClosed = true;
		Connection conn = threadLocal.get();// ���߳��л�����ݿ�����
		threadLocal.set(null);// ����߳��е����ݿ�����
		if (conn != null) {// ���ݿ����ӿ���
			try {
				conn.close();// �ر����ݿ�����
			} catch (SQLException e) {
				isClosed = false;
				e.printStackTrace();
			}
		}
		return isClosed;
	}

}
