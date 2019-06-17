package test;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.tomcat.dbcp.dbcp.DbcpException;

import bean.Collection;
import bean.GidAndCount;
import bean.Goods;
import bean.TypeBig;
import bean.TypeSmall;
import bean.User;
import dao.DBHelper;

public class Test {
	
	public static void main(String[] args) {
		List<Object>params=new ArrayList<>();
		//  1 2 
		System.out.println(DBHelper.select("select * from user where account like '%' ? '%' ", User.class, params));
	    }

	private static void change(List<Object> params) {
		params.add(6);
	}


}