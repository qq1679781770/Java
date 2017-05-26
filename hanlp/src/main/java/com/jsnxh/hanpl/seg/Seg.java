package com.jsnxh.hanpl.seg;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

public class Seg {

	public String wordMarkdoc(String content){
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
	
	public String wordMarkpic(String content){
		String[] categoriesstr=new String[]{"Ãû´Ê","ÐÎÈÝ´Ê","¶¯´Ê","¸±´Ê","Á¬´Ê","½é´Ê","Öú´Ê","´ú´Ê"};
		List<Term> termlist=NLPTokenizer.segment(content);
		JSONObject result=new JSONObject();
		JSONArray datas=new JSONArray();
		JSONArray links=new JSONArray();
		JSONArray legend=new JSONArray();
		JSONArray categories=new JSONArray();
		for(String str:categoriesstr){
			legend.put(str);
			JSONObject category=new JSONObject();
			category.put("name", str);
			categories.put(category);
		}
		JSONObject data;
		JSONObject link;
		for(Term term:termlist){
			data=new JSONObject();
			link=new JSONObject();
			char start=term.nature.toString().charAt(0);
			switch (start){
			case 'n':data.put("name", term.word);data.put("value", 1);data.put("category", 0);
			         link.put("source",term.word);link.put("target", "Ãû´Ê") ;datas.put(data);break;
			case 'a':data.put("name", term.word);data.put("value", 1);data.put("category", 1);
			         link.put("source",term.word);link.put("target", "ÐÎÈÝ´Ê") ;datas.put(data);break;
			case 'v':data.put("name", term.word);data.put("value", 1);data.put("category", 2);
	                 link.put("source",term.word);link.put("target", "¶¯´Ê") ;datas.put(data);break;
			case 'd':data.put("name", term.word);data.put("value", 1);data.put("category", 3);
	                 link.put("source",term.word);link.put("target", "¸±´Ê") ;datas.put(data);break;
			case 'c':data.put("name", term.word);data.put("value", 1);data.put("category", 4);
			         link.put("source",term.word);link.put("target", "Á¬´Ê") ;datas.put(data);break;
			case 'p':data.put("name", term.word);data.put("value", 1);data.put("category", 5);
	                 link.put("source",term.word);link.put("target", "½é´Ê") ;datas.put(data);break;
			case 'u':data.put("name", term.word);data.put("value", 1);data.put("category", 6);
	                 link.put("source",term.word);link.put("target", "Öú´Ê") ;datas.put(data);break;
			case 'r':data.put("name", term.word);data.put("value", 1);data.put("category", 7);
	                 link.put("source",term.word);link.put("target", "´ú´Ê") ;datas.put(data);break;
	        default:break;
			}
	//		datas.put(data);
	//		links.put(links);
		}
		result.put("legend", legend);
		result.put("datas", datas);
//		result.put("links", links);
		result.put("categories", categories);
		return result.toString();
	}
}
