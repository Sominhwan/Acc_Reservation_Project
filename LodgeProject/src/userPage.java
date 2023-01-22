import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Canvas;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JRadioButton;

public class userPage extends JFrame {
	Color fontColor = Color.DARK_GRAY;
	Color labelColor = Color.GRAY;
	Font textFont = new Font("���� ���", Font.PLAIN, 12);
	
	//---------------------------------------------------------
	ImageIcon Pic1x1 = new ImageIcon(".\\images\\neulpoom.png");
	ImageIcon Pic1x2 = new ImageIcon(".\\images\\doublehome.png");
	ImageIcon Pic1x3 = new ImageIcon(".\\images\\cityhill.png");
	ImageIcon Pic2x1 = new ImageIcon(".\\images\\beach.png");
	ImageIcon SelectCompanyPic = Pic1x1; // �⺻��
	String s_company;
	String b_num;
	//---------------------------------------------------------
	String Name1x1 = "��ǰȣ��";
	String Name1x2 = "����ȫõ��";
	String Name1x3 = "�ѹݵ��������";
	String Name2x1 = "����������볪����";
	String SelectCompanyName = "��ǰȣ��"; // �⺻��
	//--------------------------------------------------------
	String gradeAverage = "4.5";
	String standardPrice = "65000";
	String deluxePrice = "85000";
	String sweetPrice = "100000";
	
	//--------------------------------------------------------
	JRadioButton radioBtn_Standard;
	JRadioButton radioBtn_Deluxe;
	JRadioButton radioBtn_Sweet;
	ButtonGroup roomGrade = new ButtonGroup();
	
	private JPanel mainPanel;
	private JPanel contentPanel;
	private JPanel reservationPanel;
	private JPanel selectPanel;
	private JPanel reviewPanel;
	private JPanel reservationPanel2;
	private String code;
	private String nickname;
	private String room_type;

	public userPage(String code,String company,String c) {
		db_connect db=new db_connect();
		try {
			nickname=db.getnickname(code,company);
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		this.code=code;
		setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 608);
		setLocationRelativeTo(null);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);

		reviewPanel = new JPanel();
		reviewPanel.setVisible(true);
		reviewPanel.setBackground(Color.WHITE);
		
		reviewPanel.setBounds(0, 0, 770, 415);
		reviewPanel.setLayout(null);
		
		JLabel reviewInfoLabel = new JLabel("��  ���亸��");
		reviewInfoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		reviewInfoLabel.setForeground(Color.DARK_GRAY);
		reviewInfoLabel.setFont(new Font("���� ���", Font.BOLD, 14));
		reviewInfoLabel.setBounds(180, 10, 272, 25);
		reviewPanel.add(reviewInfoLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(180, 45, 426, 350);
		reviewPanel.add(scrollPane);
		
		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(Color.WHITE);
		footerPanel.setBounds(20, 499, 770, 70);
		mainPanel.add(footerPanel);
		footerPanel.setLayout(null);
		JSeparator footSeparator = new JSeparator();
		footSeparator.setForeground(Color.LIGHT_GRAY);
		footSeparator.setBounds(0, 0, 770, 2);
		footerPanel.add(footSeparator);
		
		JLabel footerLabel = new JLabel("���� ���Ҹ� �����Ͻ� �� ������ �������ּ���.");
		footerLabel.setForeground(Color.GRAY);
		footerLabel.setFont(new Font("���� ���", Font.PLAIN, 12));
		footerLabel.setBounds(496, 12, 262, 15);
		footerPanel.add(footerLabel);

		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(20, 83, 770, 415);
		mainPanel.add(contentPanel);
		contentPanel.setLayout(null);

		JLabel userpageLabel = new JLabel("|  ����������");
		userpageLabel.setBounds(69, 13, 80, 15);
		userpageLabel.setFont(textFont);
		userpageLabel.setForeground(fontColor);
		mainPanel.add(userpageLabel);
		
		JLabel goback = new JLabel("<  ����");
		goback.setFont(textFont);
		goback.setForeground(fontColor);
		goback.setBounds(12, 10, 45, 20);
		mainPanel.add(goback);
		
		JPanel reservationPanel = new JPanel();
		reservationPanel.setVisible(true);
		reservationPanel.setBackground(Color.WHITE);
		reservationPanel.setBounds(0, 0, 770, 415);
		reservationPanel.setLayout(null);
		
		JLabel selectInfoLabel_1 = new JLabel("��  �����ϱ�");
		selectInfoLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		selectInfoLabel_1.setForeground(Color.DARK_GRAY);
		selectInfoLabel_1.setFont(new Font("���� ���", Font.BOLD, 14));
		selectInfoLabel_1.setBounds(45, 0, 272, 25);
		reservationPanel.add(selectInfoLabel_1);
		
		JLabel reservSeparatorY = new JLabel("");
		reservSeparatorY.setOpaque(true);
		reservSeparatorY.setBackground(UIManager.getColor("Button.light"));
		reservSeparatorY.setBounds(320, 0, 1, 360);
		reservationPanel.add(reservSeparatorY);
		
		JPanel companyPanel = new JPanel();
		companyPanel.setBackground(Color.WHITE);
		companyPanel.setBounds(65, 45, 230, 315);
		reservationPanel.add(companyPanel);
		companyPanel.setLayout(null);
		
		JLabel grade = new JLabel("|  ����");
		grade.setFont(new Font("���� ���", Font.BOLD, 12));
		grade.setForeground(fontColor);
		grade.setBounds(0, 191, 45, 15);
		companyPanel.add(grade);
		
		JLabel address = new JLabel("|  ������ּ�");
		address.setBounds(0, 216, 210, 15);
		address.setFont(new Font("���� ���", Font.BOLD, 12));
		address.setForeground(fontColor);
		companyPanel.add(address);
		
	
		JLabel gradeStar = new JLabel("��");
		gradeStar.setFont(new Font("���� ���", Font.BOLD, 12));
		gradeStar.setForeground(new Color(255,90,0));
		gradeStar.setBounds(63, 190, 15, 15);
	//	companyPanel.add(gradeStar);
		
		JLabel gradeN5 = new JLabel("/ 5");
		gradeN5.setFont(textFont);
		gradeN5.setForeground(fontColor);
		gradeN5.setBounds(105, 191, 25, 15);
		companyPanel.add(gradeN5);
		
		// DB���� ������� �޾ƿͼ� ����ϴ� �κ�
		
		JLabel gradeNumberDB = new JLabel(gradeAverage);
		gradeNumberDB.setFont(new Font("���� ���", Font.BOLD, 12));
		gradeNumberDB.setForeground(new Color(255,90,0));
		gradeNumberDB.setBounds(55, 191, 50, 15);
		gradeNumberDB.setHorizontalAlignment(SwingConstants.RIGHT);
		companyPanel.add(gradeNumberDB);
			
		JTextArea addressDB = new JTextArea();
		addressDB.setText("���ֽ� �ϻ걸 �⸰���90");
		addressDB.setFont(textFont);
		addressDB.setForeground(fontColor);
		addressDB.setEditable(false);
		addressDB.setBounds(10, 236, 210, 34);
		companyPanel.add(addressDB);
	
		JLabel companyBannerDB = new JLabel("");
		companyBannerDB.setBounds(0, 40, 170, 125);
		companyBannerDB.setIcon(SelectCompanyPic);
		companyPanel.add(companyBannerDB);
		companyBannerDB.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		JLabel companyNameDB = new JLabel(SelectCompanyName);
		companyNameDB.setBounds(0, 0, 170, 30);
		companyPanel.add(companyNameDB);
		companyNameDB.setFont(new Font("���� ���", Font.BOLD, 16));
		companyNameDB.setForeground(Color.DARK_GRAY);
		companyNameDB.setHorizontalAlignment(SwingConstants.CENTER);
		
		RoundedButton ToReviewBtn = new RoundedButton("���亸��");
		ToReviewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.removeAll();
				contentPanel.add(reviewPanel);
				revalidate();
				repaint();
			}
		});
		ToReviewBtn.setForeground(Color.DARK_GRAY);
		ToReviewBtn.setFont(new Font("���� ���", Font.PLAIN, 12));
		ToReviewBtn.setBounds(140, 191, 80, 20);
		companyPanel.add(ToReviewBtn);
		
		JPanel roomPanel = new JPanel();
		roomPanel.setBackground(Color.WHITE);
		roomPanel.setBounds(349, 0, 409, 415);
		reservationPanel.add(roomPanel);
		roomPanel.setLayout(null);
		
		JLabel standardPic = new JLabel("");
		standardPic.setIcon(new ImageIcon(".\\images\\standard.png"));
		standardPic.setBorder(new LineBorder(Color.LIGHT_GRAY));
		standardPic.setBounds(10, 0, 150, 100);
		roomPanel.add(standardPic);
		
		JLabel deluxePic = new JLabel("");
		deluxePic.setIcon(new ImageIcon(".\\images\\deluxe.png"));
		deluxePic.setBorder(new LineBorder(Color.LIGHT_GRAY));
		deluxePic.setBounds(10, 130, 150, 100);
		roomPanel.add(deluxePic);
		
		JLabel sweetPic = new JLabel("");
		sweetPic.setIcon(new ImageIcon(".\\images\\sweet.png"));
		sweetPic.setBorder(new LineBorder(Color.LIGHT_GRAY));
		sweetPic.setBounds(10, 260, 150, 100);
		roomPanel.add(sweetPic);
		
		JLabel standardPriceLabel = new JLabel("-  �ݾ�");
		standardPriceLabel.setForeground(Color.DARK_GRAY);
		standardPriceLabel.setFont(textFont);
		standardPriceLabel.setBounds(182, 30, 90, 15);
		roomPanel.add(standardPriceLabel);
		
		JLabel deluxePriceLabel = new JLabel("-  �ݾ�");
		deluxePriceLabel.setForeground(Color.DARK_GRAY);
		deluxePriceLabel.setFont(new Font("���� ���", Font.PLAIN, 12));
		deluxePriceLabel.setBounds(182, 160, 90, 15);
		roomPanel.add(deluxePriceLabel);
		
		JLabel sweetPriceLabel = new JLabel("-  �ݾ�");
		sweetPriceLabel.setForeground(Color.DARK_GRAY);
		sweetPriceLabel.setFont(new Font("���� ���", Font.PLAIN, 12));
		sweetPriceLabel.setBounds(182, 290, 90, 15);
		roomPanel.add(sweetPriceLabel);
		
		JSeparator roomSeparator2 = new JSeparator();
		roomSeparator2.setForeground(SystemColor.control);
		roomSeparator2.setBounds(5, 245, 350, 2);
		roomPanel.add(roomSeparator2);
		
		JSeparator roomSeparator1 = new JSeparator();
		roomSeparator1.setForeground(SystemColor.control);
		roomSeparator1.setBounds(5, 115, 350, 2);
		roomPanel.add(roomSeparator1);
		
		RoundedButton reservationButton = new RoundedButton("���亸��");
		reservationButton.setText("�����ϱ�");
		reservationButton.setForeground(Color.DARK_GRAY);
		reservationButton.setFont(new Font("���� ���", Font.PLAIN, 12));
		reservationButton.setBounds(282, 379, 80, 20);
		roomPanel.add(reservationButton);
		
	
		JTable reservation = new JTable();
		reservationPanel2=new JPanel();
		DefaultTableModel m2 =(DefaultTableModel)reservation.getModel();
		m2.addColumn("������ȣ");
		m2.addColumn("ȣ��");
		m2.addColumn("����");
		m2.addColumn("��Ÿ��");
		
		JScrollPane reservationPane = new JScrollPane(reservation);
		reservationPane.setForeground(Color.DARK_GRAY);
		reservationPane.setFont(new Font("���� ���", Font.PLAIN, 12));
		reservationPane.setBounds(0, 32, 695, 267);
		reservationPanel2.setBackground(Color.white);
		reservationPanel2.setLayout(null);
        reservationPanel2.setBounds(50,50,900,900);
		reservationPanel2.add(reservationPane);		
		
		JComboBox month = new JComboBox();
		month.setBackground(Color.WHITE);
		month.setFont(textFont);
		month.setForeground(new Color(255,90,0));
		month.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05","06","07","08","09","10","11","12"}));
		month.setBounds(0, 305, 89, 23);
		reservationPanel2.add(month);
		
		JLabel s_month=new JLabel("month");
		s_month.setBounds(16,330,89,23);
		reservationPanel2.add(s_month);
	
		JComboBox day = new JComboBox();
		day.setBackground(Color.WHITE);
		day.setFont(textFont);
		day.setForeground(new Color(255,90,0));
	    String d[]=new String[31];
        d=new String[]{"01","02","03","04","05","06","07","08","09","10","11","12","13","14"};

		day.setModel(new DefaultComboBoxModel(d));
		day.setBounds(100, 305, 89, 23);
		reservationPanel2.add(day);
		
		JLabel s_day=new JLabel("day");
		s_day.setBounds(125,330,89,23);
		reservationPanel2.add(s_day);
	
		JComboBox stay = new JComboBox();
		stay.setBackground(Color.WHITE);
		stay.setFont(textFont);
		stay.setForeground(new Color(255,90,0));
		stay.setBounds(203, 305, 89, 23);
        d=new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
    
		stay.setModel(new DefaultComboBoxModel(d));

        reservationPanel2.add(stay);
		
		JLabel s_stay=new JLabel("stay");
		s_stay.setBounds(230,330,89,23);
		reservationPanel2.add(s_stay);
	
		JComboBox b_number = new JComboBox();
		b_number.setBackground(Color.WHITE);
		b_number.setFont(textFont);
		b_number.setForeground(new Color(255,90,0));
		b_number.setBounds(303, 305, 115, 23);
	
		JLabel s_b_number=new JLabel("b_number");
		s_b_number.setBounds(323,330,89,23);
		reservationPanel2.add(s_b_number);
		
		JComboBox room = new JComboBox();
		room.setBackground(Color.WHITE);
		room.setFont(textFont);
		room.setForeground(new Color(255,90,0));
		room.setBounds(430, 305, 89, 23);
	
		JLabel r_number=new JLabel("r_number");
		r_number.setBounds(445,330,89,23);
		reservationPanel2.add(r_number);

		JTextField use_point=new JTextField();
		use_point.setBounds(530,305,89,23);
		reservationPanel2.add(use_point);

		JLabel u_point=new JLabel("u_point");
		u_point.setBounds(550,330,89,23);
		reservationPanel2.add(u_point);
						
	    JButton payment=new RoundedButton("�����ϱ�");
	    payment.setFont(textFont);
	    payment.setForeground(fontColor);
	    payment.setBounds(619, 340, 97, 23);
        reservationPanel2.add(payment);

        payment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				try {
					db.r_procedure((String)b_number.getSelectedItem(), 
							(String)room.getSelectedItem(),(String)month.getSelectedItem() ,(String)day.getSelectedItem() ,nickname, (String)stay.getSelectedItem(), Integer.valueOf(use_point.getText()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}			
			}
		});
        	
		reservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				String query;
				
				if(radioBtn_Standard.isSelected())
				{
					query="select * from �� where ��Ÿ��='���Ĵٵ�'and ������ȣ='"+b_num+"'";
					room_type="���Ĵٵ�";
					try {
						m2.getDataVector().removeAllElements();
						db.getRoom(query,m2);
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else if(radioBtn_Deluxe.isSelected())
				{
					query="select * from �� where ��Ÿ��='�𷰽�' and ������ȣ='"+b_num+"'";
					room_type="�𷰽�";
					try {
						m2.getDataVector().removeAllElements();
						db.getRoom(query,m2);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else if(radioBtn_Sweet.isSelected())
				{
					m2.getDataVector().removeAllElements();

					query="select * from �� where ��Ÿ��='����Ʈ'and ������ȣ='"+b_num+"'";
					room_type="����Ʈ";
					
					try {
						db.getRoom(query,m2);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else
					
				{
		    		 JOptionPane.showMessageDialog(null,"������ ���� �������ּ���");

				}
				if(radioBtn_Standard.isSelected()||radioBtn_Deluxe.isSelected()||radioBtn_Sweet.isSelected())
				{
		
					String q="select distinct(ȣ��) from ��,����� where ��.������ȣ=�����.������ȣ and ������̸�='"+s_company+"' and ȸ���̸�='"+c+"' and ��Ÿ��='"+room_type+"'";
					System.out.println(s_company);
					System.out.println(c);
					System.out.println(room_type);
					try {
						db.getList(q,room);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
	
					q="select ������ȣ from ����� where ������̸�='"+s_company+"'";
					try {
						db.getList(q,b_number);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				contentPanel.removeAll();
				contentPanel.setBackground(Color.WHITE);
				reservationPanel2.add(room);
				reservationPanel2.add(b_number);
				contentPanel.add(reservationPanel2);
				revalidate();
				repaint();
				}
			}
		});
			
		radioBtn_Standard = new JRadioButton(" | ���Ĵٵ�");
		radioBtn_Standard.setBackground(Color.WHITE);
		radioBtn_Standard.setForeground(Color.DARK_GRAY);
		radioBtn_Standard.setFont(new Font("���� ���", Font.BOLD, 13));
		radioBtn_Standard.setBounds(170, 0, 100, 23);
		roomPanel.add(radioBtn_Standard);
		
		radioBtn_Deluxe = new JRadioButton(" | �𷰽�");
		radioBtn_Deluxe.setFont(new Font("���� ���", Font.BOLD, 13));
		radioBtn_Deluxe.setForeground(Color.DARK_GRAY);
		radioBtn_Deluxe.setBackground(Color.WHITE);
		radioBtn_Deluxe.setBounds(170, 130, 100, 23);
		roomPanel.add(radioBtn_Deluxe);
		
		radioBtn_Sweet = new JRadioButton(" | ����Ʈ");
		radioBtn_Sweet.setFont(new Font("���� ���", Font.BOLD, 13));
		radioBtn_Sweet.setForeground(Color.DARK_GRAY);
		radioBtn_Sweet.setBackground(Color.WHITE);
		radioBtn_Sweet.setBounds(170, 260, 100, 23);
		roomPanel.add(radioBtn_Sweet);
		
		// ���� ��ư �׷�ȭ
		roomGrade.add(radioBtn_Standard);
		roomGrade.add(radioBtn_Deluxe);
		roomGrade.add(radioBtn_Sweet);
		
		JLabel standardPriceDB = new JLabel(standardPrice);
		standardPriceDB.setFont(new Font("���� ���", Font.BOLD, 13));
		standardPriceDB.setForeground(fontColor);
		standardPriceDB.setBounds(195, 45, 50, 20);
		standardPriceDB.setHorizontalAlignment(SwingConstants.RIGHT);
		roomPanel.add(standardPriceDB);
		
		JLabel deluxePriceDB = new JLabel(deluxePrice);
		deluxePriceDB.setHorizontalAlignment(SwingConstants.RIGHT);
		deluxePriceDB.setFont(new Font("���� ���", Font.BOLD, 13));
		deluxePriceDB.setForeground(fontColor);
		deluxePriceDB.setBounds(195, 175, 50, 20);
		roomPanel.add(deluxePriceDB);
		
		JLabel sweetPriceDB = new JLabel(sweetPrice);
		sweetPriceDB.setHorizontalAlignment(SwingConstants.RIGHT);
		sweetPriceDB.setFont(new Font("���� ���", Font.BOLD, 13));
		sweetPriceDB.setForeground(fontColor);
		sweetPriceDB.setBounds(195, 305, 50, 20);
		roomPanel.add(sweetPriceDB);
		
		JLabel wonLabel1 = new JLabel("��");
		wonLabel1.setFont(new Font("���� ���", Font.BOLD, 13));
		wonLabel1.setForeground(fontColor);
		wonLabel1.setBounds(250, 45, 25, 20);
		roomPanel.add(wonLabel1);
		
		JLabel wonLabel2 = new JLabel("��");
		wonLabel2.setForeground(Color.DARK_GRAY);
		wonLabel2.setFont(new Font("���� ���", Font.BOLD, 13));
		wonLabel2.setBounds(250, 175, 25, 20);
		roomPanel.add(wonLabel2);
		
		JLabel wonLabel3 = new JLabel("��");
		wonLabel3.setForeground(Color.DARK_GRAY);
		wonLabel3.setFont(new Font("���� ���", Font.BOLD, 13));
		wonLabel3.setBounds(250, 305, 25, 20);
		roomPanel.add(wonLabel3);

		JButton MypageBtn = new RoundedButton("����������");
		MypageBtn.setFont(textFont);
		MypageBtn.setForeground(fontColor);
		MypageBtn.setBounds(546, 20, 110, 30);
		mainPanel.add(MypageBtn);
			
		JButton LogoutBtn = new RoundedButton("��������");
		LogoutBtn.setText("�α׾ƿ�");
		LogoutBtn.setFont(textFont);
		LogoutBtn.setForeground(fontColor);
		LogoutBtn.setBounds(668, 20, 110, 30);
		mainPanel.add(LogoutBtn);
		LogoutBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new startPage();		
			}	
		});
		
		JPanel selectPanel = new JPanel();
		selectPanel.setBackground(Color.WHITE);
		selectPanel.setBounds(0, 0, 770, 415);
		selectPanel.setLayout(null);
		contentPanel.add(selectPanel);
		
		// ���м�
		JSeparator separatorX = new JSeparator();
		separatorX.setForeground(Color.LIGHT_GRAY);
		separatorX.setBounds(20, 60, 770, 2);
		mainPanel.add(separatorX);
		
		JLabel selectInfoLabel = new JLabel("��  ���� �����ϱ�");
		selectInfoLabel.setBounds(45, 0, 345, 35);
		selectInfoLabel.setFont(new Font("���� ���", Font.BOLD, 14));
		selectInfoLabel.setForeground(fontColor);
		selectInfoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		selectPanel.add(selectInfoLabel);
		
		JLabel banner1x1 = new JLabel("");
		banner1x1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		banner1x1.setIcon(Pic1x1);
		banner1x1.setBounds(45, 45, 170, 125);
		selectPanel.add(banner1x1);
		
		JLabel banner1x2 = new JLabel("");
		banner1x2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		banner1x2.setIcon(Pic1x2);
		banner1x2.setBounds(300, 45, 170, 125);
		selectPanel.add(banner1x2);
		
		JLabel banner1x3 = new JLabel("");
		banner1x3.setBorder(new LineBorder(Color.LIGHT_GRAY));
		banner1x3.setIcon(Pic1x3);
		banner1x3.setBounds(555, 45, 170, 125);
		selectPanel.add(banner1x3);
		
		JLabel banner2x1 = new JLabel("");
		banner2x1.setIcon(Pic2x1);
		banner2x1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		banner2x1.setBounds(45, 215, 170, 125);
		selectPanel.add(banner2x1);
		
		JLabel companyName1x1 = new JLabel(Name1x1);
		companyName1x1.setBounds(45, 174, 170, 15);
		companyName1x1.setFont(textFont);
		companyName1x1.setForeground(fontColor);
		companyName1x1.setHorizontalAlignment(SwingConstants.CENTER);
		selectPanel.add(companyName1x1);
		
		JLabel companyName1x2 = new JLabel(Name1x2);
		companyName1x2.setHorizontalAlignment(SwingConstants.CENTER);
		companyName1x2.setForeground(Color.DARK_GRAY);
		companyName1x2.setFont(new Font("���� ���", Font.PLAIN, 12));
		companyName1x2.setBounds(300, 174, 170, 15);
		selectPanel.add(companyName1x2);
		
		JLabel companyName1x3 = new JLabel(Name1x3);
		companyName1x3.setHorizontalAlignment(SwingConstants.CENTER);
		companyName1x3.setForeground(Color.DARK_GRAY);
		companyName1x3.setFont(new Font("���� ���", Font.PLAIN, 12));
		companyName1x3.setBounds(555, 174, 170, 15);
		selectPanel.add(companyName1x3);
		
		JLabel companyName2x1 = new JLabel(Name2x1);
		companyName2x1.setHorizontalAlignment(SwingConstants.CENTER);
		companyName2x1.setForeground(Color.DARK_GRAY);
		companyName2x1.setFont(new Font("���� ���", Font.PLAIN, 12));
		companyName2x1.setBounds(45, 344, 170, 15);
		selectPanel.add(companyName2x1);
		
		setVisible(true);

		
		// �̺�Ʈ ������
		banner1x1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				banner1x1.setBorder(new LineBorder(new Color(255, 90, 0)));
				companyName1x1.setFont(new Font("���� ���", Font.BOLD, 12));
				companyName1x1.setForeground(new Color(255,90,0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				banner1x1.setBorder(new LineBorder(Color.LIGHT_GRAY));
				companyName1x1.setFont(textFont);
				companyName1x1.setForeground(fontColor);
			}
			@Override
			public void mousePressed(MouseEvent e) {
			//	companyBannerDBcompanyNameDB
				s_company="��ǰȣ��";
                b_num="11315363512";
				SelectCompanyPic = Pic1x1; 
				SelectCompanyName = Name1x1;
				companyBannerDB.setIcon(SelectCompanyPic);
				companyNameDB.setText(SelectCompanyName);				
				contentPanel.removeAll();
				contentPanel.add(reservationPanel);
				try {
					db.average_review(gradeNumberDB,"11315363512");
				} catch (SQLException e2) {
					e2.printStackTrace();
				}

				revalidate();
				repaint();
			}
		});
		banner1x2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				banner1x2.setBorder(new LineBorder(new Color(255, 90, 0)));
				companyName1x2.setFont(new Font("���� ���", Font.BOLD, 12));
				companyName1x2.setForeground(new Color(255,90,0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				banner1x2.setBorder(new LineBorder(Color.LIGHT_GRAY));
				companyName1x2.setFont(textFont);
				companyName1x2.setForeground(fontColor);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				s_company="����ȫõ��";
				b_num="31545535352";
				SelectCompanyPic = Pic1x2; 
				SelectCompanyName = Name1x2;
				companyNameDB.setText(SelectCompanyName);
				companyBannerDB.setIcon(SelectCompanyPic);
				addressDB.setText("������ ȫõ�� �Ϲ�� �����66");
				//gradeStar.setText("�ڡڡ�");
				contentPanel.removeAll();
				contentPanel.add(reservationPanel);
				try {
					db.average_review(gradeNumberDB,"31545535352");
				} catch (SQLException e2) {
					e2.printStackTrace();
				}

				revalidate();
				repaint();
			}
		});
		banner1x3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				banner1x3.setBorder(new LineBorder(new Color(255, 90, 0)));
				companyName1x3.setFont(new Font("���� ���", Font.BOLD, 12));
				companyName1x3.setForeground(new Color(255,90,0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				banner1x3.setBorder(new LineBorder(Color.LIGHT_GRAY));
				companyName1x3.setFont(textFont);
				companyName1x3.setForeground(fontColor);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				b_num="89428414122";
				s_company="�ѹݵ��������";

				SelectCompanyPic = Pic1x3; 
				SelectCompanyName = Name1x3;
				companyNameDB.setText(SelectCompanyName);
				companyBannerDB.setIcon(SelectCompanyPic);
				contentPanel.removeAll();
				contentPanel.add(reservationPanel);
				try {
					db.average_review(gradeNumberDB,"89428414122");
				} catch (SQLException e2) {
					e2.printStackTrace();
				}

				revalidate();
				repaint();
			}
		});
		banner2x1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				banner2x1.setBorder(new LineBorder(new Color(255, 90, 0)));
				companyName2x1.setFont(new Font("���� ���", Font.BOLD, 12));
				companyName2x1.setForeground(new Color(255,90,0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				banner2x1.setBorder(new LineBorder(Color.LIGHT_GRAY));
				companyName2x1.setFont(textFont);
				companyName2x1.setForeground(fontColor);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				b_num="31545535352";
				s_company="��������볪����";

				SelectCompanyPic = Pic2x1; 
				SelectCompanyName = Name2x1;
				companyNameDB.setText(SelectCompanyName);
				companyBannerDB.setIcon(SelectCompanyPic);
				contentPanel.removeAll();
				contentPanel.add(reservationPanel);
				try {
					db.average_review(gradeNumberDB,"31545535352");
				} catch (SQLException e2) {
					e2.printStackTrace();
				}

				revalidate();
				repaint();
			}
		});		

		MypageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   new myPage(nickname);
			}
		});
		LogoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		goback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				goback.setForeground(new Color(255, 90, 0));
			}
			public void mouseExited(MouseEvent e) {
				goback.setForeground(fontColor);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				contentPanel.removeAll();
				contentPanel.add(selectPanel);
				revalidate();
				repaint();
			}
		});
	}
}
