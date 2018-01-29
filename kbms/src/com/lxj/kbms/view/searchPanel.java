package com.lxj.kbms.view;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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

import com.lxj.kbms.dao.incidentDao;
import com.lxj.kbms.entities.Incident;

@SuppressWarnings("serial")
public class searchPanel extends JPanel{

	private JPanel north;
	private JTextField searchtf;
	private JButton confirmbt;
	private JComboBox<String> types;
	private JLabel searchlb;
	private JTable table;
	private List<Incident> incs; 
	private JScrollPane js;
	private JPanel btpanel;
	private JButton[] look;
	private String[] tablerow=new String[]{"事件名称","省份","城市","开始时间","结束时间",
			                   "影响","等级","关键词"};
	
	public searchPanel(){
		setBounds(200, 0,1500, 1000);
		setLayout(null);
		setOpaque(false);
		north=new JPanel();
		north.setLayout(null);
		north.setOpaque(false);
		north.setBounds(425, 0, 550, 100);
		searchlb=new JLabel("选择搜索条件");
		searchlb.setFont(new Font("宋体",Font.BOLD,19));
		searchlb.setBackground(new Color(68,137,236));
		searchlb.setBounds(50, 20, 136, 28);
		String[] typesstr=new String[]{"省份","城市","等级","关键词","事件名称"};
		types=new JComboBox<>(typesstr);
		types.setBounds(191, 20, 86, 28);
		searchtf=new JTextField();
		searchtf.setFont(new Font("宋体",Font.BOLD,19));
		searchtf.setBounds(281, 20, 174, 28);
		confirmbt=new JButton("搜索");
		confirmbt.setBackground(new Color(68,137,236));
		confirmbt.setFont(new Font("宋体",Font.PLAIN,20));
		confirmbt.setBorder(null);
		confirmbt.setFocusPainted(false);
		confirmbt.setBounds(455, 20, 86, 28);
		confirmbt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String searchtype=(String) types.getSelectedItem();
				String searchcontent=searchtf.getText();
				if(searchtype.equals("省份")){
					incs=new incidentDao().findbyProvince(searchcontent);
				}else if(searchtype.equals("城市")){
					incs=new incidentDao().findbyCity(searchcontent);
				}else if(searchtype.equals("等级")){
					incs=new incidentDao().findbyGrade(searchcontent);
				}else if(searchtype.equals("关键词")){
					incs=new incidentDao().findbyKeyword(searchcontent);
				}else{
					incs=new incidentDao().findbyIncidentname(searchcontent);
				}
				
				if(incs.size()==0){
					JOptionPane.showMessageDialog(searchPanel.this, "没有相关事件");
					searchPanel.this.removeAll();
					add(north);
				    searchPanel.this.repaint();
				    searchPanel.this.validate();
				}else{					
					searchPanel.this.removeAll();					
					btpanel=new JPanel();
					btpanel.setLayout(null);
					SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
					int num=incs.size();
					btpanel.setBounds(1400, 100, 100, 28*num+20);
					look=new JButton[num];
				    String[][] inctable=new String[num][8];
				    for(int i=0;i<num;i++){
				    	inctable[i][0]=incs.get(i).getIncidentname();
				    	inctable[i][1]=incs.get(i).getProvince();
				    	inctable[i][2]=incs.get(i).getCity();
				    	inctable[i][3]=sd.format(incs.get(i).getBegin_time());
				    	inctable[i][4]=sd.format(incs.get(i).getEnd_time());
				    	inctable[i][5]=incs.get(i).getInfluence();
				    	inctable[i][6]=incs.get(i).getGrade();
				    	inctable[i][7]=incs.get(i).getKetword();
				    	look[i]=new JButton("查看");
				    	look[i].setFont(new Font("宋体",Font.BOLD,19));
				    	look[i].setFocusPainted(false);
				    	look[i].setBorder(BorderFactory.createLineBorder(Color.black));
				    	look[i].setBackground(new Color(68,137,236));
				    	look[i].setBounds(0, 20+28*i, 100, 28);
				    	look[i].addActionListener(new mybtactionListener());
				    	btpanel.add(look[i]);
				    }
				    table=new JTable(inctable, tablerow);
				    table.setRowHeight(28);
				    js=new JScrollPane();
					js.setBounds(0, 100, 1400,20+28*num);
				    js.setViewportView(table);
				    js.repaint();
				    js.validate();
				    add(js);
				    add(north);
				    searchPanel.this.add(btpanel);
				    searchPanel.this.repaint();
				    searchPanel.this.validate();
				}
			}
		});
		north.add(searchlb);
		north.add(types);
		north.add(searchtf);
		north.add(confirmbt);
		add(north);		
		setVisible(true);
	}
	
	class mybtactionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			for(int i=0;i<look.length;i++){
				if(e.getSource()==look[i]){
					new showMessage(incs.get(i).getContent());
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
	
}
