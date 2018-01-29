package com.lxj.kbms.view;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.lxj.kbms.dao.incidentDao;
import com.lxj.kbms.entities.Incident;


@SuppressWarnings("serial")
public class addPanel extends JPanel{

	private JLabel provincelb,citylb,begin_timelb,end_timelb,influencelb,
	               gradelb,keywordlb,incidentnamelb,contentlb;
	private JTextField province,city,begin_time,end_time,influence,
	                   keyword,incidentname;
	private JTextArea content;
	private JComboBox<String> grade;
	private JButton addbt,uploadbt;
	
	public addPanel(){
		setOpaque(false);
		setLayout(null);
		setBounds(600, 0, 580,800);
		Font font=new Font("楷体",Font.BOLD,19);
		incidentnamelb=new JLabel("事件名称");
		incidentnamelb.setFont(font);
		incidentnamelb.setBounds(20, 20, 86, 28);
		provincelb=new JLabel("省份");
		provincelb.setFont(font);
		provincelb.setBounds(295, 20, 86, 28);
		citylb=new JLabel("城市");
		citylb.setFont(font);
		citylb.setBounds(20, 53, 86, 28);
		begin_timelb=new JLabel("开始时间");
		begin_timelb.setFont(font);
		begin_timelb.setBounds(295, 53, 86, 28);
		end_timelb=new JLabel("结束时间");
		end_timelb.setFont(font);
		end_timelb.setBounds(20, 86, 86, 28);
		influencelb=new JLabel("影响");
		influencelb.setFont(font);
		influencelb.setBounds(295, 86, 86, 28);
		gradelb=new JLabel("等级");
		gradelb.setFont(font);
		gradelb.setBounds(20, 119, 86,28);
		keywordlb=new JLabel("关键词");
		keywordlb.setFont(font);
		keywordlb.setBounds(295, 119, 86, 28);
		contentlb=new JLabel("内容");
		contentlb.setFont(font);
		contentlb.setBounds(20, 152, 86, 28);
		incidentname=new JTextField();
		incidentname.setFont(font);
		incidentname.setBounds(111, 20, 174, 28);
		province=new JTextField();
		province.setFont(font);
		province.setBounds(386, 20, 174, 28);
		city=new JTextField();
		city.setFont(font);
		city.setBounds(111, 53, 174, 28);
		begin_time=new JTextField();
		begin_time.setFont(font);
		begin_time.setBounds(386, 53, 174, 28);
		end_time=new JTextField();
		end_time.setFont(font);
		end_time.setBounds(111, 86, 174, 28);
		influence=new JTextField();
		influence.setFont(font);
		influence.setBounds(386, 86, 174, 28);
		String arg0[]={"A","B","C","D"};
		grade=new JComboBox<>(arg0);
		grade.setBounds(111, 119, 174, 28);
		keyword=new JTextField();
		keyword.setFont(font);
		keyword.setBounds(386, 119, 174, 28);
		content=new JTextArea();
		content.setLineWrap(true);
		content.setFont(font);
		JScrollPane js=new JScrollPane(content);
		js.setBounds(20, 185, 550, 500);
		add(incidentnamelb);
		add(incidentname);
		add(provincelb);
		add(province);
		add(citylb);
		add(city);
		add(begin_timelb);
		add(begin_time);
		add(end_timelb);
		add(end_time);
		add(influencelb);
		add(influence);
		add(gradelb);
		add(grade);
		add(keywordlb);
		add(keyword);
		add(contentlb);
		add(js);
		
		uploadbt=new JButton("上传文件");
		uploadbt.setFont(new Font("宋体",Font.PLAIN,20));
		uploadbt.setBorder(null);
		uploadbt.setFocusPainted(false);
		uploadbt.setBackground(new Color(68,137,236));
		uploadbt.setBounds(20, 700, 270, 30);
		uploadbt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc=new JFileChooser();
				jfc.showOpenDialog(addPanel.this);
				File file=jfc.getSelectedFile();
				if(!file.getName().endsWith(".txt")){
					JOptionPane.showMessageDialog(addPanel.this,"请选择txt文件");
					return;
				}
				try {
					BufferedReader reader=new BufferedReader(new FileReader(file));
					String s;
					while((s=reader.readLine())!=null){
						content.append(s);
					}
					reader.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add(uploadbt);
		addbt=new JButton("确认添加");
		addbt.setFont(new Font("宋体",Font.PLAIN,20));
		addbt.setBorder(null);
		addbt.setFocusPainted(false);
		addbt.setBackground(new Color(68,137,236));
		addbt.setBounds(300, 700, 270, 30);
		addbt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
				Incident inc=new Incident();
				if(province.getText().equals("")||incidentname.getText().equals("")||
				   city.getText().equals("")||begin_time.getText().equals("")||end_time.getText()
				   .equals("")||influence.getText().equals("")||keyword.getText().equals("")||
				   content.getText().equals("")){
					JOptionPane.showMessageDialog(addPanel.this, "请填写完整");
					return;
				}
				if(!begin_time.getText().matches("\\d{4}-((0[1-9])|(1[0-2]))-((0[1-9])|([1-2][0-9])|(3[0-1]))")||!
						end_time.getText().matches("\\d{4}-((0[1-9])|(1[0-2]))-((0[1-9])|([1-2][0-9])|(3[0-1]))")){
					JOptionPane.showMessageDialog(addPanel.this, "输入正确时间格式(yyyy-MM-dd)");
					return;
				}
				inc.setProvince(province.getText());
				inc.setIncidentname(incidentname.getText());
				inc.setCity(city.getText());
				try {
					inc.setBegin_time(new java.sql.Date(sd.parse(begin_time.getText()).getTime()));
					inc.setEnd_time(new java.sql.Date(sd.parse(end_time.getText()).getTime()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				inc.setInfluence(influence.getText());
				inc.setGrade((String)grade.getSelectedItem());
				inc.setKetword(keyword.getText());
				inc.setContent(content.getText());
				new incidentDao().addIncident(inc);
				JOptionPane.showMessageDialog(addPanel.this, "添加成功");
			}
		});
		add(addbt);
		setVisible(true);
	}
}
