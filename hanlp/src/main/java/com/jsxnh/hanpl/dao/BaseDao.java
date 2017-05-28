package com.jsxnh.hanpl.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.hanpl.entities.Document;
import com.jsxnh.hanpl.entities.Word;
import com.jsxnh.hanpl.mapper.DocumentMapper;
import com.jsxnh.hanpl.mapper.WordMapper;

public class BaseDao {

	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public Integer getTotaldoc(){
		RowMapper<Document> docmapper=new DocumentMapper();
		String sql="select * from document";
		@SuppressWarnings("unchecked")
		List<Document> docs=(List<Document>) jdbctemplate.queryForObject(sql, docmapper);
		return docs.size();
	}
	
	public Integer docsincludeofWord(String word){
		RowMapper<Word> wordmapper=new WordMapper();
		String sql="select * from word where word=?";
		@SuppressWarnings("unchecked")
		List<Word> words=(List<Word>) jdbctemplate.queryForObject(sql,new Object[]{word} ,wordmapper);
		return words.size();
	}
	
	public void saveDoc(Document doc){
		String sql="insert into document(id,context) values(?,?)";
		jdbctemplate.update(sql, doc.getId(),doc.getContext());
	}
	
	public void saveWord(Word word){
		String sql="insert into Word(word,nature,doc_id) values(?,?,?)";
		jdbctemplate.update(sql,word.getWord(),word.getNature(),word.getDoc_id());
	}
	
}
