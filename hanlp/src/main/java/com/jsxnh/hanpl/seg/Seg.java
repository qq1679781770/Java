package com.jsxnh.hanpl.seg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.summary.TextRankKeyword;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.jsxnh.hanpl.dao.BaseDao;
import com.jsxnh.hanpl.util.Myterm;

@Service
public class Seg {

	@Autowired
	private BaseDao basedao;
	
	public Seg(){
		basedao=new BaseDao();
	}
	
	public String wordMark(String content){
		List<Term> termlist=NLPTokenizer.segment(content);
		JSONArray result=new JSONArray();
		for(Term term:termlist){
			JSONArray item=new JSONArray();
			if(term.word.equals("\\")||term.word.equals("n")){
				continue;
			}
			item.put(term.word);
			item.put(term.nature.toString());
			result.put(item);
		}
		return result.toString();
	}
	

	public String wordFrequency(String content){
		JSONArray result=new JSONArray();
		Integer totaldoc=basedao.getTotaldoc();
		List<Term> termlist=NLPTokenizer.segment(content);
		HashMap<Myterm, Integer> termmap=new HashMap<Myterm, Integer>();
		Integer  totalterm=termlist.size();
		for(Term term:termlist){
			Myterm myterm=new Myterm(term.word,term.nature);
			if(termmap.containsKey(myterm)){
				Integer num=termmap.get(myterm);
				termmap.put(myterm, num+1);
			}else{
				termmap.put(myterm, 1);
			}
		}
		
		for(Map.Entry<Myterm, Integer> entry:termmap.entrySet()){
			Myterm term=entry.getKey();
			Integer wordnum=entry.getValue();
			Integer wordincludedoc=basedao.docsincludeofWord(term.getWord());
			double rate=(double)wordnum/totalterm;
			double tf_idf=rate*Math.log10((double)totaldoc/(1+wordincludedoc));
			JSONArray json=new JSONArray();
			json.put(term.getWord());
			json.put(wordnum);
			json.put(rate);
			json.put(tf_idf);
		    result.put(json);
		}		
		return result.toString();
	}
	
	public String wordCloud(String content){
		JSONArray result=new JSONArray();
		Map<String, Float> keys=new TextRankKeyword().getTermAndRank(content);
		if(keys.size()>50){
			keys=new TextRankKeyword().getTermAndRank(content, 50);
		}
		for(Map.Entry<String, Float> entry:keys.entrySet()){
			JSONObject json=new JSONObject();
			json.put("text",entry.getKey());
			json.put("score", entry.getValue());
			result.put(json);
		}
		return result.toString();
	}
}
