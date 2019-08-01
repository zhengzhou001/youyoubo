package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

public class DbUtil {
	private Connection conn;
	Statement stmt;
	private Map mapProperties = new HashMap();

	DbUtil(Map map){
		this.mapProperties =map;
		this.ConnByJdbc();
	}
       
	/**
	 * 获取表数据库相关信息
	 *
	 * @param tableName 表名
	 */
	public  void getTableAttr(String tableName) throws Exception {
		System.out.println("开始生成"+tableName+"相关代码、配置...");
 		//表注释
		String sql = "SHOW TABLE STATUS FROM `"+MapUtils.getString(mapProperties, "dbms_db")+"`";
				
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		
		
		String comm = "";
		try {
            while (rs.next()) {
                if (rs.getString(1).toUpperCase().equals(tableName)) {
                	comm = rs.getString(18) != null ? rs.getString(18) : "";
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
		
		
		
		mapProperties.put("date", getCurDate());
		mapProperties.put("tableName", tableName); 
		mapProperties.put("tabelComments", comm != null ? comm : "");
		rs.close();
		
		sql = " SHOW FULL COLUMNS FROM  " +tableName;
		rs = stmt.executeQuery(sql);
 		List colList = new ArrayList<>();
		while (rs.next()){
			Map tMap = new HashMap();
			tMap.put("COLUMN_NAME", rs.getString("Field").toUpperCase());
			tMap.put("COMMENTS", rs.getString("Comment")==null?"":rs.getString("Comment"));
			
			
			String type = rs.getString("Type");
			String Atype ="";
			if (StringUtils.containsIgnoreCase(type, "decimal")) {
				Atype="NUMBER";
			}else if(StringUtils.containsIgnoreCase(type, "varchar")){
				Atype="VARCHAR";
			}else if(StringUtils.containsIgnoreCase(type, "DATE")){
				Atype="DATE";
			}
			
			tMap.put("DATA_TYPE", Atype);
			
			colList.add(tMap);
		}
		
		rs.close();
		mapProperties.put("colList", colList);

	}


	//获取当前时间
	public static String getCurDate() {
		java.util.Calendar c = java.util.Calendar.getInstance();
		return (new java.text.SimpleDateFormat("yyyy-MM-dd")).format((java.util.Calendar.getInstance()).getTime());
	}
	/**
	 * 使用JDBC的URL方式连接数据库
	 */
	private void ConnByJdbc() {
		try {
			Class.forName(MapUtils.getString(mapProperties, "driver", ""));
			conn = DriverManager.getConnection(MapUtils.getString(mapProperties, "url", ""), MapUtils.getString(mapProperties, "user", ""), MapUtils.getString(mapProperties, "pass", ""));
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			closeDb();
		}
	}
	/**
	 * 关闭数据库连接
	 */
	public void closeDb() {

		try {
			if (!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}
}
