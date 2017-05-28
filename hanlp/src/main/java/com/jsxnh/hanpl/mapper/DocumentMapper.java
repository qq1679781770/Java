package com.jsxnh.hanpl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.hanpl.entities.Document;


public class DocumentMapper implements RowMapper<Document>{

	public Document mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Document doc=new Document();
		doc.setId(arg0.getInt("id"));
		doc.setContext(arg0.getString("context"));
		return doc;
	}
}
