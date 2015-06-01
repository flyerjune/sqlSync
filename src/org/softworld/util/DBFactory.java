package org.softworld.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBFactory {
	final static Logger logger = LoggerFactory.getLogger(DBFactory.class);
	private static Connection con = getConnection(true);
	public DBFactory() {
	}

	public int deleteDB(String sql, String id) {
		int result = -1;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.info("SQLException is:"+e);
			e.printStackTrace();
		}
		return result;
	}

	public int operateDB(String sql, Object[] params) {
		int result = -1;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setString(i + 1, params[i].toString());
			}
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.info("SQLException is:"+e);
			e.printStackTrace();
		}
		return result;
	}


	public List<Object[]> queryTab(String sql) {
		List<Object[]> rsList = new ArrayList<Object[]>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				Object[] Obj = new Object[columnCount];
				for (int i = 0; i < columnCount; i++) {
					Obj[i] = rs.getString(i);// 获取字段时下标从1开始，所以偏移1个单位
				}
				rsList.add(Obj);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.info("SQLException is:"+e);
			e.printStackTrace();
		}
		return rsList;
	}





	public static Connection getConnection(boolean isMaster) {
        try{
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            logger.info("成功加载MySQL驱动！");
            System.out.println("成功加载MySQL驱动！");
        }catch(ClassNotFoundException e1){
            System.out.println("找不到MySQL驱动!");
            logger.info("找不到MySQL驱动:"+e1);
            e1.printStackTrace();
        }
        String prefix = isMaster?"MASTER_":"SLAVE_";
        String IP = UtilTool.getProperty(prefix+"IP");
        String dataBase = UtilTool.getProperty("DATEBASE");
        String url="jdbc:mysql://"+UtilTool.getProperty(prefix+"IP")+"/"+dataBase;    //JDBC的URL
        String account = UtilTool.getProperty(prefix+"ACCOUNT");
        String passwd = UtilTool.getProperty(prefix+"PASSWD");
        //调用DriverManager对象的getConnection()方法，获得一个Connection对象
        try {
        	con = DriverManager.getConnection(url,account,passwd);
            //创建一个Statement对象
            System.out.print("成功连接到数据库！");
        	logger.info("成功连接到数据库！");
            con.close();
        } catch (SQLException e){
        	logger.info("SQLException is:"+e);
            e.printStackTrace();
        }
		return null;
	}

	public void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				logger.info("SQLException is:"+e);
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		DBFactory.getConnection(false);
	}

}