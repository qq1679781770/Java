package com.lxj.kbms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {

	private Connection con=null;
	public Connection getconnect(){
		String Driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/kbms?useSSL=true";
		String user="root";
		String password="919434593";
		try {
			Class.forName(Driver);
			con=DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}
	public void closeCon(){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
