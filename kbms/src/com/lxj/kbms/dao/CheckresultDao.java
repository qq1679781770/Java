package com.lxj.kbms.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.lxj.kbms.entities.Checkresult;

public class CheckresultDao extends BaseDao{

	public void addCheckresult(Checkresult cr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql="insert into checkresult values("+cr.getIncidentid()+",'"+
	                cr.getResult()+"','"+sdf.format(cr.getTime())+"',"+cr.getOperator()+
	                ")";
		try {
			PreparedStatement ps=getconnect().prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
