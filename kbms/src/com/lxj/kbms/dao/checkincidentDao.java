package com.lxj.kbms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.lxj.kbms.entities.Checkincident;

public class checkincidentDao extends BaseDao{

	public void addcheckincidentDao(Checkincident ci){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		String sql="insert into checkincident(incidentname,province,city,begin_time,"
				+ "end_time,influence,content,commituser,committime,ischeck) values('"+
				ci.getIncidentname()+"','"+ci.getProvince()+"','"+ci.getCity()+"','"+sd.format(ci.getBegin_time())+
				"','"+sd.format(ci.getEnd_time())+"','"+ci.getInfluence()+"','"+
				ci.getContent()+"',"+ci.getCommituser()+",'"+sdf.format(ci.getCommittime())+
				"',"+ci.getIschecked()+")";
		//System.out.println(sql);
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
	
	public void updateCheck(Integer id){
		String sql="update checkincident set ischeck=1 where id=?";
		try {
			PreparedStatement ps=getconnect().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Checkincident> finduncheckedByuserid(Integer user_id){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql="select * from checkincident where commituser=? and ischecke=0";
		List<Checkincident> cis=new LinkedList<Checkincident>();
		try {
			PreparedStatement ps=getconnect().prepareStatement(sql);
			ps.setInt(1, user_id);
			ResultSet res=ps.executeQuery();
			while(res.next()){
				Checkincident ci=new Checkincident();
				ci.setId(res.getInt(1));
				ci.setIncidentname(res.getString(2));
				ci.setProvince(res.getString(3));
				ci.setCity(res.getString(4));
				ci.setBegin_time(res.getDate(5));
				ci.setEnd_time(res.getDate(6));
				ci.setInfluence(res.getString(7));
				ci.setContent(res.getString(8));
				ci.setCommituser(res.getInt(9));
				try {
					ci.setCommittime(sdf.parse(res.getString(10)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ci.setIschecked(0);
				cis.add(ci);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cis;
	}
	
	public List<Checkincident> findAllcheckincident(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql="select * from checkincident where ischeck=0";
		List<Checkincident> cincs=new LinkedList<>();
		try {
			PreparedStatement ps=getconnect().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Checkincident cic=new Checkincident();
				cic.setId(rs.getInt(1));
				cic.setIncidentname(rs.getString(2));
				cic.setProvince(rs.getString(3));
				cic.setCity(rs.getString(4));
				cic.setBegin_time(rs.getDate(5));
				cic.setEnd_time(rs.getDate(6));
				cic.setInfluence(rs.getString(7));
				cic.setContent(rs.getString(8));
				cic.setCommituser(rs.getInt(9));
				try {
					cic.setCommittime(sdf.parse(rs.getString(10)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cic.setIschecked(rs.getInt(11));
				cincs.add(cic);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cincs;
	}
}
