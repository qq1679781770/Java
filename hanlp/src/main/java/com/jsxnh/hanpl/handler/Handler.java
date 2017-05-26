package com.jsxnh.hanpl.handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jsnxh.hanpl.seg.Seg;

import org.json.JSONArray;

@Controller
public class Handler {

	public static String content="";
	
	@RequestMapping(value="/fileupload",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody String uploadFile(MultipartFile file) throws IllegalStateException, IOException, ServletException{
		String text="";
		String contexttype=file.getContentType();
		String filename=file.getOriginalFilename();
		JSONArray res=new JSONArray();
		if(contexttype.equals("text/plain")){
			BufferedReader reader=new BufferedReader(new InputStreamReader(file.getInputStream()));
			String linetext=null;
			StringBuilder context=new StringBuilder();
			while((linetext=reader.readLine())!=null){
				context.append(linetext);
			}
			text=context.toString();
			reader.close();
		}else if(contexttype.equals("application/pdf")){
			BufferedInputStream reader=new BufferedInputStream(file.getInputStream());
			String temfilename=filename;
			File temfile=new File(temfilename);
			BufferedOutputStream write=new BufferedOutputStream(new FileOutputStream(temfile));
			byte[] bytes=new byte[1024];
			while(reader.read(bytes)!=-1){
				write.write(bytes);
			}
			reader.close();
			write.close();			
			PDDocument document = PDDocument.load(new File(temfilename)) ; 
	        PDFTextStripper stripper = new PDFTextStripper();  
	        text = stripper.getText(document);
	        document.close();
		}		
		System.out.println(text);
		res.put(text);
		return res.toString();
	}
	
	@RequestMapping(value="/analysefile",method=RequestMethod.POST,consumes = "application/json",produces="application/json;charset=UTF-8")
	public @ResponseBody String Analyse(@RequestBody String str){
		content=str;
		System.out.println(content);
		return new Seg().wordMarkdoc(content);	
	}
	
	@RequestMapping(value="/wordmarkdoc",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody String wordMarkdoc(){
		return new Seg().wordMarkdoc(content);	
	}
	
	@RequestMapping(value="/wordmarkpic",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody String wordMarkpic(){
		return new Seg().wordMarkdoc(content);	
	}
}
