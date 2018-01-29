package com.lxj.kbms.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.lxj.kbms.dao.incidentDao;
import com.lxj.kbms.entities.Incident;

@SuppressWarnings("serial")
public class deletePanel extends JPanel{
    private JScrollPane js;
    private JTable table;
    private String[] tablerow=new String[]{"事件名称","省份","城市","开始时间","结束时间",
                                            "影响","等级","关键词"};
    private JPanel btpanel;
    private JButton[] deleterbuttons;
    private List<Incident> incs=new LinkedList<>();
	
	
	public deletePanel(){
		setOpaque(false);
		setLayout(null);
		setBounds(150, 0, 1500, 1000);
		incs=new incidentDao().findbyAllIncidents();
		
		init(incs);
		
		setVisible(true);
	}
	
	public void init(List<Incident> incs){
		this.removeAll();
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		int num=incs.size();
		deleterbuttons=new JButton[num];
		btpanel=new JPanel();
		btpanel.setLayout(null);
		btpanel.setBounds(1400, 0, 100, 28*num+20);
		String[][] tablestr=new String[num][8];
		for(int i=0;i<num;i++){
			tablestr[i][0]=incs.get(i).getIncidentname();
	    	tablestr[i][1]=incs.get(i).getProvince();
	    	tablestr[i][2]=incs.get(i).getCity();
	    	tablestr[i][3]=sd.format(incs.get(i).getBegin_time());
	    	tablestr[i][4]=sd.format(incs.get(i).getEnd_time());
	    	tablestr[i][5]=incs.get(i).getInfluence();
	    	tablestr[i][6]=incs.get(i).getGrade();
	    	tablestr[i][7]=incs.get(i).getKetword();
	    	deleterbuttons[i]=new JButton("删除");
	    	deleterbuttons[i].setFont(new Font("宋体",Font.BOLD,19));
	    	deleterbuttons[i].setFocusPainted(false);
	    	deleterbuttons[i].setBorder(BorderFactory.createLineBorder(Color.black));
	    	deleterbuttons[i].setBackground(new Color(68,137,236));
	    	deleterbuttons[i].setBounds(0, 20+28*i, 100, 28);
	    	deleterbuttons[i].addActionListener(new mybtactionListener());
	    	btpanel.add(deleterbuttons[i]);
		}
		table=new JTable(tablestr, tablerow);
	    table.setRowHeight(28);
	    js=new JScrollPane(table);
	    js.repaint();
	    js.validate();
	    add(btpanel);
	    js.setBounds(0, 0, 1400,20+28*num);
		add(js);
	    this.repaint();
	    this.validate();
	}
	
	class mybtactionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			for(int i=0;i<deleterbuttons.length;i++){
				if(e.getSource()==deleterbuttons[i]){
					new incidentDao().deleteIncident(incs.get(i).getId());
					JOptionPane.showMessageDialog(deletePanel.this, "删除成功");
					incs.remove(i);
					init(incs);
					break;
				}
			}
		}
		
	}
}
