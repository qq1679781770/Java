package com.lxj.kbms.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.lxj.kbms.dao.CheckresultDao;
import com.lxj.kbms.dao.checkincidentDao;
import com.lxj.kbms.dao.incidentDao;
import com.lxj.kbms.entities.Checkincident;
import com.lxj.kbms.entities.Checkresult;
import com.lxj.kbms.entities.Customer;
import com.lxj.kbms.entities.Incident;

@SuppressWarnings("serial")
public class checkPanel extends JPanel{

	private JScrollPane js;
	private JTable table;
	private String[] tablerow=new String[]{"事件名称","省份","城市","开始时间","结束时间","影响","提交时间"
			                              ,"提交账号"};
	private JButton[] showbt,agreebt,disagreebt;
	private JLabel tip;
	private JPanel operatorpanel;
	private Customer cus;
	private List<Checkincident> cincs;
	private JButton refresh;
	
	public checkPanel(Customer cus){
		setOpaque(false);
		this.setLayout(null);
		this.cus=cus;
		this.setBounds(0, 0, 1800, 1000);
		tip=new JLabel();
		tip.setBounds(0, 0, 150, 20);
		tip.setText("                 操作");	
		refresh=new JButton("刷新");
		refresh.setFont(new Font("宋体",Font.BOLD,14));
		refresh.setFocusPainted(false);
		refresh.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		refresh.setBackground(new Color(255,165,0));
		refresh.setBounds(150,0, 100, 20);
		refresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				init();
			}
		});
		init();
		this.setVisible(true);
	}
	
	public void init(){
		cincs=new checkincidentDao().findAllcheckincident();
		this.removeAll();
		this.repaint();
		this.validate();
		if(cincs.size()==0){
			JOptionPane.showMessageDialog(checkPanel.this, "无审核事件");
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		int num=cincs.size();
		operatorpanel=new JPanel();
		operatorpanel.setLayout(null);
		operatorpanel.setOpaque(false);
		operatorpanel.setBounds(1400, 0, 300, 20+28*num);
		operatorpanel.add(tip);
		String[][] tablestr=new String[num][8];
		showbt=new JButton[num];
		agreebt=new JButton[num];
		disagreebt=new JButton[num];
		for(int i=0;i<num;i++){
			tablestr[i][0]=cincs.get(i).getIncidentname();
			tablestr[i][1]=cincs.get(i).getProvince();
			tablestr[i][2]=cincs.get(i).getCity();
			tablestr[i][3]=sd.format(cincs.get(i).getBegin_time());
			tablestr[i][4]=sd.format(cincs.get(i).getEnd_time());
			tablestr[i][5]=cincs.get(i).getInfluence();
			tablestr[i][6]=sdf.format(cincs.get(i).getCommittime());
			tablestr[i][7]=String.valueOf(cincs.get(i).getCommituser());
			showbt[i]=new JButton("查看");
			showbt[i].setFont(new Font("宋体",Font.BOLD,19));
	    	showbt[i].setFocusPainted(false);
	    	showbt[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    	showbt[i].setBackground(new Color(255,215,0));
	    	showbt[i].setBounds(0, 20+28*i, 100, 28);
	    	showbt[i].addActionListener(new showactionListener());
	    	agreebt[i]=new JButton("通过");
	    	agreebt[i].setFont(new Font("宋体",Font.BOLD,19));
	    	agreebt[i].setFocusPainted(false);
	    	agreebt[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    	agreebt[i].setBackground(new Color(0,191,255));
	    	agreebt[i].setBounds(100, 20+28*i, 100, 28);
	    	agreebt[i].addActionListener(new agreeactionListener());
	    	disagreebt[i]=new JButton("驳回");
	    	disagreebt[i].setFont(new Font("宋体",Font.BOLD,19));
	    	disagreebt[i].setFocusPainted(false);
	    	disagreebt[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    	disagreebt[i].setBackground(new Color(50,205,50));
	    	disagreebt[i].setBounds(200, 20+28*i, 100, 28);
	    	disagreebt[i].addActionListener(new disagreeactionListener()); 
	    	operatorpanel.add(showbt[i]);
	    	operatorpanel.add(agreebt[i]);
	    	operatorpanel.add(disagreebt[i]);
		}
		operatorpanel.add(refresh);
		table=new JTable(tablestr, tablerow);
		table.setRowHeight(28);
		js=new JScrollPane(table);
		js.setBounds(0, 0, 1400,20+28*num);
		add(js);
		add(operatorpanel);

	}
	
	
	class showactionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			for(int i=0;i<showbt.length;i++){
				if(arg0.getSource()==showbt[i]){
					new showMessage(cincs.get(i).getContent());
					break; 
				}
			}
		}
		
		public class showMessage extends JFrame{
			private JTextArea showlb;
			private JScrollPane js;
			
			public showMessage(String content){
				setLayout(null);				
				showlb=new JTextArea(content);
				showlb.setEditable(false);
				showlb.setLineWrap(true);
				showlb.setFont(new Font("宋体",Font.PLAIN,19));
				setSize(500, 400);
				js=new JScrollPane(showlb);
				js.setBounds(0, 0, 500, 400);
				add(js);
				setVisible(true);
			}
		}
	}
	
	
	class agreeactionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			for(int i=0;i<agreebt.length;i++){
				if(arg0.getSource()==agreebt[i]){
					new agreepanel(i);					
					break;
				}
			}
		}
		
	}
	
	class disagreeactionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			for(int i=0;i<disagreebt.length;i++){
				if(arg0.getSource()==disagreebt[i]){
					new checkincidentDao().updateCheck(cincs.get(i).getId());
					Checkresult cr=new Checkresult();
					cr.setIncidentid(cincs.get(i).getId());
					cr.setOperator(cus.getUser_id());
					cr.setResult("不通过");
					cr.setTime(new Date());
					new CheckresultDao().addCheckresult(cr);
					JOptionPane.showMessageDialog(checkPanel.this, "拒绝成功");
					cincs.remove(i);
					init();
					break;
				}
			}
		}
		
	}
	
	class agreepanel extends JFrame{
		private JLabel keywordlb,gradelb;
		private JComboBox<String> grade;
		private JTextField keyword;
		private JButton add;
		private Integer i;
		public agreepanel(Integer i){
			this.i=i;
			setBounds(600, 600, 300, 200);
			setLayout(null);
			Font font=new Font("楷体",Font.BOLD,19);
			keywordlb=new JLabel("输入关键词");
			keywordlb.setFont(font);
			keywordlb.setBounds(0,10, 100, 28);
			keyword=new JTextField();
			keyword.setFont(font);
			keyword.setBounds(100, 10, 174, 28);
			gradelb=new JLabel("确认等级");
			gradelb.setFont(font);
			gradelb.setBounds(0, 63, 100, 28);
			grade=new JComboBox<>(new String[]{"A","B","C","D"});
			grade.setBounds(100, 63, 100, 28);
			add(keywordlb);
			add(keyword);
			add(gradelb);
			add(grade);
			add=new JButton("确认");
			add.setBorder(null);
			add.setFocusPainted(false);
			add.setBackground(new Color(68,137,236));
			add.addActionListener(new confirmadd());
			add.setBounds(0, 110, 300, 30);
			add(add);
			setVisible(true);
		}
		class confirmadd implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(keyword.getText().equals("")){
					JOptionPane.showMessageDialog(agreepanel.this, "填写完整");
					return;
				}
				Incident inc=new Incident();
				Checkresult cr=new Checkresult();
				Checkincident cinc=cincs.get(i);
				new checkincidentDao().updateCheck(cinc.getId());
				inc.setIncidentname(cinc.getIncidentname());
				inc.setProvince(cinc.getProvince());
				inc.setCity(cinc.getCity());
				inc.setBegin_time(cinc.getBegin_time());
				inc.setEnd_time(cinc.getEnd_time());
				inc.setInfluence(cinc.getInfluence());
				inc.setContent(cinc.getContent());
				inc.setKetword(keyword.getText());
				inc.setGrade((String)grade.getSelectedItem());
				new incidentDao().addIncident(inc);
				cr.setIncidentid(cinc.getId());
				cr.setOperator(cus.getUser_id());
				cr.setTime(new Date());
				cr.setResult("通过");
				new CheckresultDao().addCheckresult(cr);
				JOptionPane.showMessageDialog(agreepanel.this, "操作成功");
                agreepanel.this.setVisible(false);
                init();
			}
			
		}
	}	
}
