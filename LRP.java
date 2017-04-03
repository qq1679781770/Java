
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

class sqlDB {
	String drivername = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:50680;DatabaseName=library";
	String user = "sa";
	String password = "2019623325518zjh";
	Connection con;
	Statement com;
	ResultSet rs;

	public sqlDB() {
		try {
			Class.forName(drivername);
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("�޷��������ݿ�");
		}
	}

	public ResultSet exceQuery(String stmt) {
		try {
			com = con.createStatement();
			rs = com.executeQuery(stmt);
			return rs;
		} catch (Exception e1) {
			System.out.println("�޷�����statement");
			return null;
		}
	}

	public void exceUpadate(String stmt) {
		try {
			com = con.createStatement();
			com.executeUpdate(stmt);

		} catch (Exception e2) {
			e2.getMessage();
		}
	}

	public void closeDB() {
		try {
			com.close();
			con.close();
		} catch (Exception e3) {
			System.out.println(e3.getMessage());
		}
	}

}

public class LRP extends JFrame implements ActionListener {
	JMenuBar bar;
	JMenuItem borrowmenu, loanmenu,lognin,lognout,enquirymenu,registermenu;
	JMenu usermenu;
	Borrow borrowpane;
	Sendback loanpane;
	public static JLabel jlabel=new JLabel("�˺�δ��¼");
	public static String currentop=null;
	JLabel label;
	LoginPane loginpane;
	
	ImageIcon icon[]=new ImageIcon[10];
	JLabel picture;
	int num=1;
	
	//JPanel jPanel;
	Register registerpane;
	Enquiry enquirypane;
   // public static boolean 
	public void repaint(){
		super.repaint();
		picture.setIcon(icon[num]);
		num++;
		if(num>9) num-=10;
	}
	public void background(){
		this.getLayeredPane().remove(label);
		ImageIcon icon=new ImageIcon(getClass().getResource("x.png"));
		
		label=new JLabel(icon);
		label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		
		this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
		this.setSize(icon.getIconWidth(),icon.getIconHeight());
	}
	
	public void mainbackground(){
		if(label!=null)
		   this.getLayeredPane().remove(label);
		ImageIcon background =new ImageIcon(getClass().getResource("ͼ�����ϵͳ.jpg"));
		 label = new JLabel(background); //backgroundΪImageIcon
		// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ����������� 
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		//����ͼƬ��frame�ĵڶ���(�ѱ���ͼƬ���ӵ��ֲ㴰�����ײ���Ϊ����)
		
		//this.getLayeredPane().setLayout(null);
	    this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
	    this.setSize(background.getIconWidth(),background.getIconHeight()+44);
	      JPanel jPanell=(JPanel)this.getContentPane();
	    jPanell.setOpaque(false);
		//�����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
	}
	public LRP() {
		icon[0]=new ImageIcon(getClass().getResource("1.jpg"));
		icon[1]=new ImageIcon(getClass().getResource("2.jpg"));
		icon[2]=new ImageIcon(getClass().getResource("3.jpg"));
		icon[3]=new ImageIcon(getClass().getResource("4.jpg"));
		icon[4]=new ImageIcon(getClass().getResource("5.jpg"));
		icon[5]=new ImageIcon(getClass().getResource("6.jpg"));
		icon[6]=new ImageIcon(getClass().getResource("7.jpg"));
		icon[7]=new ImageIcon(getClass().getResource("8.jpg"));
		icon[8]=new ImageIcon(getClass().getResource("9.jpg"));
		icon[9]=new ImageIcon(getClass().getResource("10.jpg"));
		picture =new JLabel(icon[0]);
			
		mainbackground();
		//jlabel=new JLabel("��ʾ");
		//jPanel.add(jlabel,BorderLayout.SOUTH);
		
		setTitle("ͼ�����ϵͳ");
		bar = new JMenuBar();
		usermenu=new JMenu("�˺�");
		lognin=new JMenuItem("��¼");
		lognout=new JMenuItem("ע��");
		usermenu.add(lognin);usermenu.add(lognout);
		borrowmenu = new JMenuItem("����Ǽ�");
		loanmenu = new JMenuItem("����Ǽ�");
		enquirymenu=new JMenuItem("ͼ���ѯ");
		registermenu=new JMenuItem("���Ŀ�ע��");
		bar.add(borrowmenu);
		bar.add(loanmenu);
		bar.add(enquirymenu);
		bar.add(registermenu);
		bar.add(usermenu);
		
		registermenu.addActionListener(this);
		borrowmenu.addActionListener(this);
		loanmenu.addActionListener(this);
		enquirymenu.addActionListener(this);
		lognin.addActionListener(this);
		lognout.addActionListener(this);
        setJMenuBar(bar);
		borrowpane = new Borrow();
		loanpane = new Sendback();
		enquirypane=new Enquiry();
		loginpane=new LoginPane();
		registerpane=new Register();
		
		add(loginpane);
		add(jlabel,BorderLayout.SOUTH);
		 //Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("o.png"));
		 //int imWidth = image.getWidth(this);
			//int imHeight = image.getHeight(this); // ����ͼƬ�Ŀ��ȡ��߶�
			//int FWidth = getWidth();
			//int FHeight = getHeight();// ���崰�ڵĿ��ȡ��߶�
			//int x = (FWidth - imWidth) / 2;
			//int y = (FHeight - imHeight)/2;
		//getGraphics().drawImage(image,0,0,null);// ����ͼƬ
	}

	public void run() {
		while (true) {
			this.repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == borrowmenu) {
			//remove(loanpane);
			//remove(enquirypane);
			//setSize(200,200);
			getContentPane().removeAll();
			background();
			getContentPane().add(borrowpane, BorderLayout.NORTH);
			getContentPane().add(picture,BorderLayout.CENTER);
			getContentPane().add(jlabel,BorderLayout.SOUTH);
			this.repaint();
			validate();
		}
		if (e.getSource() == loanmenu) {
			//remove(borrowpane);
			//remove(enquirypane);
			getContentPane().removeAll();
			background();
			getContentPane().add(loanpane, BorderLayout.NORTH);			
			getContentPane().add(picture,BorderLayout.CENTER);
			getContentPane().add(jlabel,BorderLayout.SOUTH);
			this.repaint();
			validate();
		}
		if(e.getSource()==enquirymenu){
			//remove(borrowpane);
			//remove(loanpane);
			getContentPane().removeAll();
			background();
			getContentPane().add(enquirypane,BorderLayout.CENTER);
			getContentPane().add(jlabel,BorderLayout.SOUTH);
			this.repaint();
			validate();
		}
		if(e.getSource()==lognin){
			getContentPane().removeAll();
			mainbackground();
			getContentPane().add(loginpane,BorderLayout.CENTER);
			getContentPane().add(jlabel,BorderLayout.SOUTH);
			this.repaint();
			validate();
		}
		if(e.getSource()==lognout){
			if(jlabel!=null) getContentPane().remove(jlabel);
			currentop=null;
			jlabel=new JLabel("�˺�δ��¼");
			
			getContentPane().add(jlabel,BorderLayout.SOUTH);
			this.repaint();
			validate();
		}
		if(e.getSource()==registermenu){
			getContentPane().removeAll();
			background();
			getContentPane().add(registerpane,BorderLayout.NORTH);
			getContentPane().add(picture,BorderLayout.CENTER);
			getContentPane().add(jlabel,BorderLayout.SOUTH);
			this.repaint();
			validate();
		}
	}

	public static void main(String[] args) {
		LRP lrp = new LRP();
		lrp.setVisible(true);
		lrp.show();
		lrp.run();
		lrp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
// class InsertBook extends JPanel implements ActionListener{

// public void actionPerformed(ActionEvent e){

// }
// }
class Enquiry extends JPanel implements ActionListener{ 
	  JComboBox combobox;
	  JLabel label;
      String requirement[]={"����","����","����"}; 
      String msg[]={"ͼ����","����","����","����","�������","�Ƿ���"}; 
      JTable table; 
      JTextField write;
      JButton ok; 
      JScrollPane scrollpane; 
     public Enquiry(){
    	    setOpaque(false);
    	    Object[][] a=new Object[22][6];
    	    for(int i=0;i<22;i++){
    	    	for(int j=0;j<6;j++)
    	    		a[i][j]='\0';
    	    }
    	    label=new JLabel("ˢѡ����");
        	combobox=new JComboBox(requirement);
        	combobox.setSelectedIndex(0);
        	write=new JTextField(15); 
        	ok=new JButton("ȷ��");
            JPanel northpanel=new JPanel();
            northpanel.setOpaque(false);
            northpanel.add(label);
            northpanel.add(combobox);
            northpanel.add(write);
            northpanel.add(ok);
            add(northpanel,BorderLayout.NORTH); 
            table=new JTable(a,msg); 
           table.setOpaque(false);
            scrollpane=new JScrollPane(table);
            scrollpane.setOpaque(false);
            add(scrollpane,BorderLayout.CENTER); }
     public void actionPerformed(ActionEvent e){ 
    	 if(e.getSource()==ok){
    		 int i=combobox.getSelectedIndex();
    		 ResultSet com = null;
    		 int number = 0;
    		 if(i==0){
    			 try{
    				 sqlDB sql=new sqlDB();
    			     com=sql.exceQuery("select book_ID,book_name,subject,author,bookshelf_name,isloan from dbo.book where subject like '%"+write.getText()+"%'");
    			 }catch(Exception ea){System.out.println(ea.getMessage());}
    			 }
    		 if(i==1){
    			 try{
    				 sqlDB sql=new sqlDB();
    			     com=sql.exceQuery("select book_ID,book_name,subject,author,bookshelf_name,isloan from dbo.book where author like '%"+write.getText()+"%'");
    			 }catch(Exception ea){System.out.println(ea.getMessage());}
    			 }
    		 if(i==2){
    			 try{
    				 sqlDB sql=new sqlDB();
    			     com=sql.exceQuery("select book_ID,book_name,subject,author,bookshelf_name,isloan from dbo.book where book_name like '%"+write.getText()+"%'");
    			 }catch(Exception ea){System.out.println(ea.getMessage());}
    			 }
    		try{ number=com.getRow();}
    		catch(Exception eq){System.out.println(eq.getMessage());}
    	   String  b[][]=new String[number][6];
    		
    		int j=0;
    		try {
				while(com.next()){
					b[j][0]=com.getString(1);
					b[j][1]=com.getString(2);
					b[j][2]=com.getString(3);
					b[j][3]=com.getString(4);
					b[j][4]=com.getString(5);
					b[j][5]=com.getString(6);
					j++;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		table=new JTable(b,msg);
    		table.setRowMargin(20);
    		scrollpane.removeAll();
    		scrollpane.add(table);
    		add(scrollpane,BorderLayout.CENTER);
    		validate();
      } 
   } 
}

class Borrow extends JPanel implements ActionListener {
	JLabel label1, label2, label3, label4;
	JTextField textfield[];
	JButton ok;
	Image image;

	public String getdate() {
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ����д��SimpleDateFormat
																			// df
																			// =
																			// new
																			// SimpleDateFormat("yyyy-MM-dd
																			// HH:mm:ss");
		String dateTime = df.format(d);
		return dateTime;
	}

	public void paintComponent(Graphics g) {
       
		super.paintComponent(g);
		//ImageIcon background =new ImageIcon(getClass().getResource("x.png"));
		//image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("o.png"));
		//int imWidth = image.getWidth(this);
		//int imHeight = image.getHeight(this); // ����ͼƬ�Ŀ��ȡ��߶�
		//int FWidth = getWidth();
		//int FHeight = getHeight();// ���崰�ڵĿ��ȡ��߶�
		//int x = (FWidth - imWidth) / 2;
		//int y = (FHeight - imHeight) / 2;// ����ͼƬ������,ʹͼƬ��ʾ�ڴ������м�
		//g.drawImage(image, 0, 0, 150,150,null);// ����ͼƬ
		textfield[2].setText(getdate());
	}

	public Borrow() {
		setOpaque(false);
		label1 = new JLabel("����֤��");
		label2 = new JLabel("ͼ����");
		label3 = new JLabel("��������");
		label4 = new JLabel("����Ա");
		textfield = new JTextField[4];
		for (int i = 0; i < 4; i++) {
			textfield[i] = new JTextField(15);
		}
		ok = new JButton("ȷ��");
		JPanel eastpanel = new JPanel();
		eastpanel.setOpaque(false);
		eastpanel.setLayout(new GridLayout(4, 1));
		eastpanel.add(textfield[0]);
		eastpanel.add(textfield[1]);
		eastpanel.add(textfield[2]);
		eastpanel.add(textfield[3]);

		JPanel westpanel = new JPanel();
		westpanel.setOpaque(false);
		westpanel.setLayout(new GridLayout(4, 1));
		westpanel.add(label1);
		westpanel.add(label2);
		westpanel.add(label3);
		westpanel.add(label4);
		add(westpanel, BorderLayout.WEST);
		add(eastpanel, BorderLayout.EAST);
		ok.addActionListener(this);
		add(ok, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			if(LRP.jlabel.getText().equals("�˺�δ��¼"))  JOptionPane.showMessageDialog(null, "����δ��¼","����",JOptionPane.OK_OPTION);
			else
				try {
				sqlDB db = new sqlDB();
				db.exceUpadate("insert into dbo.borrow values('" + textfield[0].getText().trim() + "','"
						+ textfield[1].getText().trim() + "','" + this.getdate() + "','" + textfield[3].getText().trim()
						+ "')");
				db.closeDB();
			} catch (Exception ee) {
				System.out.println(ee.getMessage());
			}
		}

	}
}

class Sendback extends JPanel implements ActionListener {
	JLabel label1, label2, label3, label4;
	JTextField textfield[];
	JButton ok;
    
	public String getdate() {
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ����д��SimpleDateFormat
																			// df
																			// =
																			// new
																			// SimpleDateFormat("yyyy-MM-dd
																			// HH:mm:ss");
		String dateTime = df.format(d);
		return dateTime;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		textfield[2].setText(getdate());
	}

	public Sendback() {
		setOpaque(false);
		label1 = new JLabel("����֤��");
		label2 = new JLabel("ͼ����");
		label3 = new JLabel("�黹����");
		label4 = new JLabel("����Ա");
		textfield = new JTextField[4];
		for (int i = 0; i < 4; i++) {
			textfield[i] = new JTextField(15);
		}
		ok = new JButton("ȷ��");
		JPanel eastpanel = new JPanel();
		eastpanel.setLayout(new GridLayout(4, 1));
		eastpanel.add(textfield[0]);
		eastpanel.add(textfield[1]);
		eastpanel.add(textfield[2]);
		eastpanel.add(textfield[3]);
		textfield[2].setText(getdate());
		JPanel westpanel = new JPanel();
		westpanel.setLayout(new GridLayout(4, 1));
		westpanel.setOpaque(false);
		westpanel.add(label1);
		westpanel.add(label2);
		westpanel.add(label3);
		westpanel.add(label4);
		add(westpanel, BorderLayout.WEST);
		add(eastpanel, BorderLayout.EAST);
		ok.addActionListener(this);
		add(ok, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			if(LRP.jlabel.getText().equals("�˺�δ��¼"))  JOptionPane.showMessageDialog(null, "����δ��¼","����",JOptionPane.OK_OPTION);
			else
			   try {
				sqlDB db = new sqlDB();
				db.exceUpadate("insert into dbo.loan  values('" + textfield[0].getText() + "','"
						+ textfield[0].getText() + "','" + this.getdate() + "','" + textfield[0].getText() + "')");
			} catch (Exception ee) {
				System.out.println(ee.getMessage());
			}
		}
	}
}
class LoginPane extends JPanel implements ActionListener{
	JLabel userlabel,passwordlabel;
	JTextArea userfield;
	JPasswordField passwordfield;
	JButton ok;
	
	public LoginPane(){
		Icon button=new ImageIcon(getClass().getResource("ok.jpg"));
		ok=new JButton(button);
		ok.setBorderPainted(false);
		userlabel=new JLabel("�˺�:");
		passwordlabel=new JLabel("����:");
		userfield=new JTextArea(1,15);
		passwordfield=new JPasswordField(15);
		setOpaque(false);
		ok.addActionListener(this);
		JPanel panel=new JPanel();
		panel.setOpaque(false);
		JPanel westpanel=new JPanel();
		westpanel.setOpaque(false);
		westpanel.setLayout(new GridLayout(2,1));
		JPanel eastpanel=new JPanel();
		eastpanel.setOpaque(false);
		eastpanel.setLayout(new GridLayout(3,1));
		JPanel centerpanel=new JPanel();
		centerpanel.setLayout(new GridLayout(1,3));
		centerpanel.setOpaque(false);
		centerpanel.add(new JLabel());
		centerpanel.add(ok);
		centerpanel.add(new JLabel());
		//userlabel.setBounds(0, 0, this.getWidth()/4, this.getHeight()/4);
		userlabel.setFont(new Font("����",Font.BOLD,40));
		passwordlabel.setFont(new Font("����",Font.BOLD,40));
		westpanel.add(userlabel);westpanel.add(passwordlabel);
		eastpanel.add(userfield);
		eastpanel.add(new JLabel());eastpanel.add(passwordfield);
		//passwordfield.setFont(new Font("����",Font.BOLD,50));
	   // userlabel.setLocation(100, 100);
	    panel.add(westpanel,BorderLayout.WEST);
	    panel.add(eastpanel,BorderLayout.EAST);
	    //panel.add(passwordfield);
	   // panel.add(passwordlabel);
	    //panel.add(passwordfield);
		
	    add(panel,BorderLayout.NORTH);
	    add(centerpanel, BorderLayout.CENTER);
	    //add(LRP.jlabel,BorderLayout.SOUTH);
		//jlabel=new JLabel("��ʾ");
		//add(LRP.jlabel,BorderLayout.SOUTH);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==ok){
			boolean islogin = false;
			String use=userfield.getText();
			String psd=passwordfield.getText();
			//passwordfield.setText(psd);
			//userfield.setText(psd+use);
			//passwordfield.setText(use+psd);
			
			if(use.equals("123456")&&psd.equals("123456")) 
			
			{LRP.jlabel=new JLabel("����Ա�ѵ�¼");
			islogin=true;
			LRP.currentop="����Ա";
			JOptionPane.showMessageDialog(null, "��¼�ɹ�","",JOptionPane.OK_OPTION);}
			
			else
				try{
				
					sqlDB sql=new sqlDB();
			        ResultSet rs=sql.exceQuery("select operator_ID,password from dbo.operator ");
			        
			    while(rs.next()){
			    	  // userfield.setText(rs.getString("operator_ID"));
			           //passwordfield.setText(rs.getString("password"));
			    	if(use.equals(rs.getString("operator_ID"))&&psd.equals(rs.getString("password")))
			    	{
			    		islogin=true;
			    		LRP.currentop=rs.getString("operator_ID");
			    		LRP.jlabel=new JLabel("����Ա:"+use);
			    		JOptionPane.showMessageDialog(null, "��¼�ɹ�","",JOptionPane.OK_OPTION);
			    		break;
			    	}
			   
			}
			    if(!islogin){
			    	JOptionPane.showMessageDialog(null, "��¼ʧ��","",JOptionPane.OK_OPTION);
			    }
			}catch(SQLException ew){ew.printStackTrace();}
		}
			
		}
	}
class Register extends JPanel implements ActionListener{
	JLabel name,card,registertime;
	Calendar c;
	int year,mouth,day,honr,minute,second;
	JTextField namefield,cardfield,registertimefield;
	JButton register;
	public String getdata(){
		Date date=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=df.format(date);
		return time;
	}
	public String getdeadline(){
		c=Calendar.getInstance();
		year=c.get(Calendar.YEAR)+4;
		mouth=c.get(Calendar.MONTH);
		day=c.get(Calendar.DAY_OF_MONTH);
		honr=c.get(Calendar.HOUR_OF_DAY);
		minute=c.get(Calendar.MINUTE);
		second=c.get(Calendar.SECOND);
		String time=String.valueOf(year)+"-"+String.valueOf(mouth)+"-"+String.valueOf(day)+" "+String.valueOf(honr)+":"+String.valueOf(minute)+":"+String.valueOf(second);
		return time;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		registertimefield.setText(getdata());
	}
	public Register(){
		setOpaque(false);
		name=new JLabel("����");card=new JLabel("����");registertime=new JLabel("ע��ʱ��");
		namefield=new JTextField(15);cardfield=new JTextField(15);registertimefield=new JTextField(getdata());
		register=new JButton("ע��");
		JPanel panel=new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(3,2));
		panel.add(name);panel.add(namefield);panel.add(card);panel.add(cardfield);panel.add(registertime);panel.add(registertimefield);
		JPanel southpanel=new JPanel();
		southpanel.setOpaque(false);
		southpanel.setLayout(new GridLayout(1,3));
		southpanel.add(new JLabel());southpanel.add(register);southpanel.add(new JLabel());
		add(panel,BorderLayout.NORTH);
		add(southpanel,BorderLayout.CENTER);
		register.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==register){
			if(LRP.jlabel.getText().equals("�˺�δ��¼"))  JOptionPane.showMessageDialog(null, "����δ��¼","����",JOptionPane.OK_OPTION);
			else{
			sqlDB sql=new sqlDB();
			sql.exceUpadate("insert dbo.stuedent values('"+namefield.getText()+"','"+cardfield.getText()+"',10)");
			sql.exceUpadate("insert dbo.librarycard values('"+cardfield.getText()+"','"+getdata()+"','"+getdeadline()+"')");
			}
		}
	}
}