package com.lxj.kbms.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.lxj.kbms.entities.Incident;

public class incidentDao extends BaseDao{

	public void addIncident(Incident incident){
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		String sql="insert into incident(province,city,begin_time,end_time,influence,grade,"
				+ "keyword,content,incidentname) values('"+incident.getProvince()+"','"+
				incident.getCity()+"','"+sd.format(incident.getBegin_time())+"','"+
				sd.format(incident.getEnd_time())+"','"+incident.getInfluence()+"','"+
				incident.getGrade()+"','"+incident.getKetword()+"','"+incident.getContent()+
				"','"+incident.getIncidentname()+"')";
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
	
	public void deleteIncident(Integer id){
		String sql="delete from incident where id=?";
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
	public List<Incident> findbyProvince(String province) {
		String sql="select * from incident where province=?";
		List<Incident> incs=new LinkedList<>();
		try {
			PreparedStatement ps=getconnect().prepareStatement(sql);
			ps.setString(1, province);
			ResultSet res=ps.executeQuery();
			while(res.next()){
				Incident inc=new Incident();
				inc.setId(res.getInt(1));
				inc.setProvince(res.getString(2));
				inc.setCity(res.getString(3));
			    inc.setBegin_time(res.getDate(4));
				inc.setEnd_time(res.getDate(5));
				inc.setInfluence(res.getString(6));
				inc.setGrade(res.getString(7));
				inc.setKetword(res.getString(8));
				inc.setContent(res.getString(9));
				inc.setIncidentname(res.getString(10));
				incs.add(inc);
			}
			res.close();
			ps.close();
			closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return incs;
	}
	
	public List<Incident> findbyCity(String city) {		
		String sql="select * from incident where city=?";
		List<Incident> incs=new LinkedList<>();
		try {
			PreparedStatement ps=getconnect().prepareStatement(sql);
			ps.setString(1, city);
			ResultSet res=ps.executeQuery();
			while(res.next()){
				
				Incident inc=new Incident();
				inc.setId(res.getInt(1));
				inc.setProvince(res.getString(2));
				inc.setCity(res.getString(3));
				inc.setBegin_time(res.getDate(4));
				inc.setEnd_time(res.getDate(5));
				inc.setInfluence(res.getString(6));
				inc.setGrade(res.getString(7));
				inc.setKetword(res.getString(8));
				inc.setContent(res.getString(9));
				inc.setIncidentname(res.getString(10));
				incs.add(inc);
				
			}
			res.close();
			ps.close();
			closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return incs;
	}
	
	public List<Incident> findbyKeyword(String keyword) {
		String sql="select * from incident where keyword=?";
		List<Incident> incs=new LinkedList<>();
		try {
			PreparedStatement ps=getconnect().prepareStatement(sql);
			ps.setString(1,keyword);
			ResultSet res=ps.executeQuery();
			while(res.next()){
				Incident inc=new Incident();
				inc.setId(res.getInt(1));
				inc.setProvince(res.getString(2));
				inc.setCity(res.getString(3));
				inc.setBegin_time(res.getDate(4));
				inc.setEnd_time(res.getDate(5));
				inc.setInfluence(res.getString(6));
				inc.setGrade(res.getString(7));
				inc.setKetword(res.getString(8));
				inc.setContent(res.getString(9));
				inc.setIncidentname(res.getString(10));
				incs.add(inc);
				
			}
			res.close();
			ps.close();
			closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return incs;
	}
	
	public List<Incident> findbyGrade(String grade) {
		String sql="select * from incident where grade=?";
		List<Incident> incs=new LinkedList<>();
		try {
			PreparedStatement ps=getconnect().prepareStatement(sql);
			ps.setString(1, grade);
			ResultSet res=ps.executeQuery();
			while(res.next()){
				Incident inc=new Incident();
				inc.setId(res.getInt(1));
				inc.setProvince(res.getString(2));
				inc.setCity(res.getString(3));
				inc.setBegin_time(res.getDate(4));
				inc.setEnd_time(res.getDate(5));
				inc.setInfluence(res.getString(6));
				inc.setGrade(res.getString(7));
				inc.setKetword(res.getString(8));
				inc.setContent(res.getString(9));
				inc.setIncidentname(res.getString(10));
				incs.add(inc);
				
			}
			res.close();
			ps.close();
			closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return incs;
	}
	public List<Incident> findbyIncidentname(String incidentname) {
		String sql="select * from incident where incidentname=?";
		List<Incident> incs=new LinkedList<>();
		try {
			PreparedStatement ps=getconnect().prepareStatement(sql);
			ps.setString(1, incidentname);
			ResultSet res=ps.executeQuery();
			while(res.next()){
				Incident inc=new Incident();
				inc.setId(res.getInt(1));
				inc.setProvince(res.getString(2));
				inc.setCity(res.getString(3));
				inc.setBegin_time(res.getDate(4));
				inc.setEnd_time(res.getDate(5));
				inc.setInfluence(res.getString(6));
				inc.setGrade(res.getString(7));
				inc.setKetword(res.getString(8));
				inc.setContent(res.getString(9));
				inc.setIncidentname(res.getString(10));
				incs.add(inc);
				
			}
			res.close();
			ps.close();
			closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return incs;
	}
	
	public List<Incident> findbyAllIncidents() {
		String sql="select * from incident";
		List<Incident> incs=new LinkedList<>();
		try {
			PreparedStatement ps=getconnect().prepareStatement(sql);
			ResultSet res=ps.executeQuery();
			while(res.next()){
				Incident inc=new Incident();
				inc.setId(res.getInt(1));
				inc.setProvince(res.getString(2));
				inc.setCity(res.getString(3));
				inc.setBegin_time(res.getDate(4));
				inc.setEnd_time(res.getDate(5));
				inc.setInfluence(res.getString(6));
				inc.setGrade(res.getString(7));
				inc.setKetword(res.getString(8));
				inc.setContent(res.getString(9));
				inc.setIncidentname(res.getString(10));
				incs.add(inc);
				
			}
			res.close();
			ps.close();
			closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return incs;
	}
}
