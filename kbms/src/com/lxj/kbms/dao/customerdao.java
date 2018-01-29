package com.lxj.kbms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lxj.kbms.entities.Customer;

public class customerdao extends BaseDao{

	//注册
	public void regsiter(Integer id,String password){
		String sql="insert into customer (user_id,password,identity,score) values(?,?,?,?)";
		try {
			PreparedStatement ps=getconnect().prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, password);
			ps.setString(3, "user");
			ps.setInt(4, 0);
			ps.executeUpdate();
			ps.close();
			closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//是否已有用户账号
	public boolean isRegistered(Integer id){
		String sql="select * from customer where user_id=?";
		try {
			PreparedStatement ps=getconnect().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet res=ps.executeQuery();
			int col=res.getMetaData().getColumnCount();
			res.close();
		    ps.close();
		    closeCon();
			if(col==1){
				
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//更新分数
	public void updateScore(Integer id,Integer score){
		String sql="update customer set score=? where user_id=?";
		try {
			PreparedStatement ps=getconnect().prepareStatement(sql);
			ps.setInt(1, score);
			ps.setInt(2, id);
			ps.executeUpdate();
			ps.close();
			closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//升级为管理员
	public void updateIdentity(Integer id){
		String sql="update customer set identity=? where id=?";
		try {
			PreparedStatement ps=getconnect().prepareStatement(sql);
			ps.setString(1, "admin");
			ps.setInt(2, id);
			ps.executeUpdate();
			ps.close();
			closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Customer findCustomer(Integer id){
		String sql="select * from customer where user_id=?";
		Customer customer=null;
		try {
			PreparedStatement ps=getconnect().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet res=ps.executeQuery();
			while(res.next()){
				customer=new Customer();
				customer.setUser_id(res.getInt(1));
				customer.setPassword(res.getString(2));
				customer.setIdentity(res.getString(3));
				customer.setScore(res.getInt(4));
			}
			res.close();
			ps.close();
			closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}
	
	public Customer login(Integer id,String password){
		String sql="select * from customer where user_id=? and password=?";
		Customer cus=null;
		try {
			PreparedStatement ps=getconnect().prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, password);
			ResultSet res=ps.executeQuery();
			while(res.next()){
				cus=new Customer();
				cus.setUser_id(res.getInt(1));
				cus.setPassword(res.getString(2));
				cus.setIdentity(res.getString(3));
				cus.setScore(res.getInt(4));
			}
			res.close();
			ps.close();
			closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cus;
	}
	
}
