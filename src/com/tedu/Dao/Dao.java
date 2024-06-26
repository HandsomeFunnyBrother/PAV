package com.tedu.Dao;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Dao extends BaseDao {
	private static Dao dao;

	static {
		dao = new Dao();
	}

	public static Dao getInstance() {
		return dao;
	}
	
	//driver 
	public Vector sScoreList() {   //查询所有用户信息  不包含密码
		return selectSomeNote(" select * from player order by score desc");
	}
	public Vector sPlyerName(){
		return selectSomeValue("select name from player ");
	}
	public boolean uScore(String name, int score){
		
		String sql = "update player set score = '"+score+"' where name = '"+name+"'";
		System.out.println(sql);
		return longHaul(sql);
	}
	public Object sScore(String name){
		return selectOnlyValue("select score from player where name = '"+name+"'");
	}
	public Object sPlayerPassword(String name) {    //查询用户密码
		return selectOnlyValue("select password from player where name = '"+name+"' ");
	}
	
	public boolean iScoreList(String name,int score) {   //增加排行榜信息
		String sql = "insert into player (name,score) values('"+name+"','"+score+"')";
		System.out.println(sql);
		return longHaul(sql);
	}
	public boolean iPlayer(String name,String password) {   //增加排行榜信息
		String sql = "insert into player (name,password) values('"+name+"','"+password+"')";
		System.out.println(sql);
		return longHaul(sql);
	}
	
}
