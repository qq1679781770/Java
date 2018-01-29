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
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.lxj.kbms.dao.checkincidentDao;
import com.lxj.kbms.entities.Checkincident;
import com.lxj.kbms.entities.Customer;


@SuppressWarnings("serial")
public class uploadPanel extends JPanel {
	private JLabel provincelb, citylb, begin_timelb, end_timelb, influencelb, incidentnamelb,
			contentlb;
	private JTextField province, city, begin_time, end_time, influence, incidentname;
	private JTextArea content;
	private JButton addbt, uploadbt;

	public uploadPanel(Customer cus) {
		setOpaque(false);
		setLayout(null);
		setBounds(600, 0, 580, 800);
		Font font = new Font("楷体", Font.BOLD, 19);
		incidentnamelb = new JLabel("事件名称");
		incidentnamelb.setFont(font);
		incidentnamelb.setBounds(20, 20, 86, 28);
		provincelb = new JLabel("省份");
		provincelb.setFont(font);
		provincelb.setBounds(295, 20, 86, 28);
		citylb = new JLabel("城市");
		citylb.setFont(font);
		citylb.setBounds(20, 53, 86, 28);
		begin_timelb = new JLabel("开始时间");
		begin_timelb.setFont(font);
		begin_timelb.setBounds(295, 53, 86, 28);
		end_timelb = new JLabel("结束时间");
		end_timelb.setFont(font);
		end_timelb.setBounds(20, 86, 86, 28);
		influencelb = new JLabel("影响");
		influencelb.setFont(font);
		influencelb.setBounds(295, 86, 86, 28);		
		contentlb = new JLabel("内容");
		contentlb.setFont(font);
		contentlb.setBounds(20, 152, 86, 28);
		incidentname = new JTextField();
		incidentname.setFont(font);
		incidentname.setBounds(111, 20, 174, 28);
		province = new JTextField();
		province.setFont(font);
		province.setBounds(386, 20, 174, 28);
		city = new JTextField();
		city.setFont(font);
		city.setBounds(111, 53, 174, 28);
		begin_time = new JTextField();
		begin_time.setFont(font);
		begin_time.setBounds(386, 53, 174, 28);
		end_time = new JTextField();
		end_time.setFont(font);
		end_time.setBounds(111, 86, 174, 28);
		influence = new JTextField();
		influence.setFont(font);
		influence.setBounds(386, 86, 174, 28);
		content = new JTextArea();
		content.setLineWrap(true);
		content.setFont(font);
		JScrollPane js = new JScrollPane(content);
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
		add(contentlb);
		add(js);

		uploadbt = new JButton("上传文件");
		uploadbt.setFont(new Font("宋体", Font.PLAIN, 20));
		uploadbt.setBorder(null);
		uploadbt.setFocusPainted(false);
		uploadbt.setBackground(new Color(68, 137, 236));
		uploadbt.setBounds(20, 700, 270, 30);
		uploadbt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				jfc.showOpenDialog(uploadPanel.this);
				File file = jfc.getSelectedFile();
				if (!file.getName().endsWith(".txt")) {
					JOptionPane.showMessageDialog(uploadPanel.this, "请选择txt文件");
					return;
				}
				try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String s;
					while ((s = reader.readLine()) != null) {
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
		addbt = new JButton("确认添加");
		addbt.setFont(new Font("宋体", Font.PLAIN, 20));
		addbt.setBorder(null);
		addbt.setFocusPainted(false);
		addbt.setBackground(new Color(68, 137, 236));
		addbt.setBounds(300, 700, 270, 30);
		addbt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				Checkincident chinc=new Checkincident();
				if (province.getText().equals("") || incidentname.getText().equals("") || city.getText().equals("")
						|| begin_time.getText().equals("") || end_time.getText().equals("")
						|| influence.getText().equals("")
						|| content.getText().equals("")) {
					JOptionPane.showMessageDialog(uploadPanel.this, "请填写完整");
					return;
				}
				if (!begin_time.getText().matches("\\d{4}-((0[1-9])|(1[0-2]))-((0[1-9])|([1-2][0-9])|(3[0-1]))")
						|| !end_time.getText().matches("\\d{4}-((0[1-9])|(1[0-2]))-((0[1-9])|([1-2][0-9])|(3[0-1]))")) {
					JOptionPane.showMessageDialog(uploadPanel.this, "输入正确时间格式(yyyy-MM-dd)");
					return;
				}
				chinc.setProvince(province.getText());
				chinc.setIncidentname(incidentname.getText());
				chinc.setCity(city.getText());
				try {
					chinc.setBegin_time(new java.sql.Date(sd.parse(begin_time.getText()).getTime()));
					chinc.setEnd_time(new java.sql.Date(sd.parse(end_time.getText()).getTime()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				chinc.setInfluence(influence.getText());				
				chinc.setContent(content.getText());
				chinc.setCommituser(cus.getUser_id());
				chinc.setCommittime(new Date());
				chinc.setIschecked(0);
                new checkincidentDao().addcheckincidentDao(chinc);
				JOptionPane.showMessageDialog(uploadPanel.this, "等待审核");
			}
		});
		add(addbt);
		setVisible(true);
	}

}
