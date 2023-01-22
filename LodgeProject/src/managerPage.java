import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class managerPage extends JFrame {
	String imageurl = ".\\image\\";
	Color fontColor = Color.DARK_GRAY;
	Font textFont = new Font("맑은 고딕", Font.PLAIN, 12);
	private JPanel contentPane;
	private JTable userInfoTable;
	private JTable reservationTable;
	private JTable companyTable;
	private JScrollPane scrollPane;
	
	private String driver="oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@localhost:1521:XE";
	
	private String userColNames[]={"고객이름","닉네임","주민등록번호","휴대폰번호","주소","이메일","포인트","등급"};
	private DefaultTableModel model0=new DefaultTableModel(userColNames,0);
	
	private String reservationColNames[]={"사업장번호","호실","예약일","닉네임","숙박기간","사용포인드"};
	private DefaultTableModel model3=new DefaultTableModel(reservationColNames,0);
	
	private String companyColNames[]={"사업장번호","회사이름","사업장이름","사업장주소"};
	private DefaultTableModel model=new DefaultTableModel(companyColNames,0);
	
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	
	public void selectUserInfo(){//고객 테이블
		String query = "select 고객이름, 닉네임, 주민등록번호, 휴대폰번호, 주소, 이메일, 포인트, 등급 from 고객";
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,"lodge","1234");
			pstmt=con.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()){
				model0.addRow(new Object[]{rs.getString("고객이름"),rs.getString("닉네임"),rs.getString("주민등록번호"),
						rs.getString("휴대폰번호"),rs.getString("주소"),rs.getString("이메일"),rs.getString("포인트"),rs.getString("등급")});
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			try{
				rs.close();
				pstmt.close();
				con.close();
			}catch(Exception e2){}
		}
	}
	
	public void selectReservation(){//예약 테이블 
		String query = "select 사업장번호, 호실, 예약일, 닉네임, 숙박기간, 사용포인트 from 예약";
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,"lodge","1234");
			pstmt=con.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()){
				model3.addRow(new Object[]{rs.getString("사업장번호"),rs.getString("호실"),rs.getString("예약일"),
						rs.getString("닉네임"),rs.getString("숙박기간"),rs.getString("사용포인트")});
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			try{
				rs.close();
				pstmt.close();
				con.close();
			}catch(Exception e2){}
		}
	}
	
	public void selectCompany(){//사업장 테이블
		String query = "select 사업장번호, 회사이름, 사업장이름, 사업장주소 from 사업장";
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,"lodge","1234");
			pstmt=con.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("사업장번호"),rs.getString("회사이름"),
						rs.getString("사업장이름"),rs.getString("사업장주소")});
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			try{
				rs.close();
				pstmt.close();
				con.close();
			}catch(Exception e2){}
		}
	}
			
	public managerPage() {
		setVisible(true);
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		setBounds(500,200,823, 608);	
		selectCompany();
		selectReservation();
		selectUserInfo();
	
		// 하단부 패널
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.ORANGE);
		bottomPanel.setBounds(20, 83, 770, 476);
		contentPane.add(bottomPanel);
		bottomPanel.setLayout(null);
		
		// '이전' 패널
		JLabel goback = new JLabel("<  이전");
		goback.setFont(textFont);
		goback.setForeground(fontColor);
		goback.setBounds(12, 10, 45, 20);
		contentPane.add(goback);
		
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
				setVisible(false);
				new startPage();
			}
		});
		
		// 구분선
		JSeparator separatorX = new JSeparator();
		separatorX.setForeground(Color.LIGHT_GRAY);
		separatorX.setBounds(20, 60, 770, 2);
		contentPane.add(separatorX);
		
		JLabel managerLabel = new JLabel("|  관리자 페이지");
		managerLabel.setBounds(75, 13, 95, 15);
		managerLabel.setFont(textFont);
		managerLabel.setForeground(fontColor);
		contentPane.add(managerLabel);
		
		// 회원정보 패널--------------------------------------------------------------------
		JPanel userInfoPanel = new JPanel();
		userInfoPanel.setBackground(Color.WHITE);
		userInfoPanel.setBounds(0, 0, 770, 476);
		bottomPanel.add(userInfoPanel);
		userInfoPanel.setLayout(null);
		
		JLabel userInfoLabel = new JLabel("<회원정보>");
		userInfoLabel.setFont(textFont);
		userInfoLabel.setForeground(fontColor);
		userInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userInfoLabel.setBounds(47, 0, 676, 35);
		userInfoPanel.add(userInfoLabel);

		userInfoTable = new JTable(model0);
		userInfoTable.setGridColor(SystemColor.activeCaption);
		userInfoTable.setFont(textFont);
		userInfoTable.setForeground(fontColor);
		userInfoTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane userInfoSPane = new JScrollPane(userInfoTable);
		userInfoSPane.setBounds(12, 35, 746, 397);
		userInfoSPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		userInfoTable.setBackground(Color.WHITE);
		userInfoTable.setBounds(50, 57, 600, 400);
		userInfoPanel.add(userInfoSPane);
			
		// 예약정보 패널------------------------------------------------------------------	
		JPanel reservationPanel = new JPanel();
		reservationPanel.setBackground(Color.WHITE);
		reservationPanel.setBounds(0, 0, 770, 476);
		bottomPanel.add(reservationPanel);
		reservationPanel.setLayout(null);
		
		JLabel reservationInfoLabel = new JLabel("<예약내역>");
		reservationInfoLabel.setFont(textFont);
		reservationInfoLabel.setForeground(fontColor);
		reservationInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reservationInfoLabel.setBounds(47, 0, 676, 35);
		reservationPanel.add(reservationInfoLabel);
		
		reservationTable = new JTable(model3);
		reservationTable.setGridColor(SystemColor.activeCaption);
		reservationTable.setFont(textFont);
		reservationTable.setForeground(fontColor);
		reservationTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane reservationSPane = new JScrollPane(reservationTable);
		reservationSPane.setBounds(12, 35, 746, 397);
		reservationSPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		reservationTable.setBackground(Color.WHITE);
		reservationTable.setBounds(50, 57, 600, 400);
		reservationPanel.add(reservationSPane);
			
		// 사업자등록 패널-----------------------------------------------------------------		
		JPanel companyPanel = new JPanel();
		companyPanel.setBackground(Color.WHITE);
		companyPanel.setBounds(0, 0, 770, 476);
		bottomPanel.add(companyPanel);
		companyPanel.setLayout(null);
		
		JLabel companyInfoLabel = new JLabel("<사업장등록>");
		companyInfoLabel.setFont(textFont);
		companyInfoLabel.setForeground(fontColor);
		companyInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		companyInfoLabel.setBounds(47, 0, 676, 35);
		companyPanel.add(companyInfoLabel);
		
		companyTable = new JTable(model);
		companyTable.setGridColor(SystemColor.activeCaption);
		companyTable.setFont(textFont);
		companyTable.setForeground(fontColor);
		companyTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane companySPane = new JScrollPane(companyTable);
		companySPane.setBounds(12, 35, 746, 357);
		companySPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		companyTable.setBackground(Color.WHITE);
		companyTable.setBounds(50, 57, 600, 400);
		companyPanel.add(companySPane);
	
		JButton addRowButton = new RoundedButton("추가");
		addRowButton.setFont(textFont);
		addRowButton.setBackground(Color.WHITE);
		addRowButton.setBounds(526, 416, 110, 30);
		companyPanel.add(addRowButton);
		
		addRowButton.addActionListener(new ActionListener(){//테이블 행 추가
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
				DefaultTableModel model2=(DefaultTableModel)companyTable.getModel();
				model2.addRow(new String[]{"","","",""});
			}
		});
		
		JButton registButton = new RoundedButton("등록");
		registButton.setFont(textFont);
		registButton.setBackground(Color.WHITE);
		registButton.setBounds(648, 416, 110, 30);
		companyPanel.add(registButton);
		
		registButton.addActionListener(new ActionListener(){//테이블 저장
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
				DefaultTableModel model2=(DefaultTableModel)companyTable.getModel();
				int row=companyTable.getSelectedRow();
				if(row<0) return;
				
				String query="insert into 사업장 (사업장번호,회사이름,사업장이름,사업장주소)"
						+ "values(?,?,?,?)";
				
				try{
					Class.forName(driver);
					con=DriverManager.getConnection(url,"lodge","1234");
					pstmt=con.prepareStatement(query);
					
					pstmt.setString(1, (String) model2.getValueAt(row, 0));
					pstmt.setString(2, (String) model2.getValueAt(row, 1));
					pstmt.setString(3, (String) model2.getValueAt(row, 2));
					pstmt.setString(4, (String) model2.getValueAt(row, 3));
					pstmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "등록성공","Message",JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception eeee){
					System.out.println(eeee.getMessage());
					JOptionPane.showMessageDialog(null, "등록실패","Message",JOptionPane.ERROR_MESSAGE);
				}finally{
					try{
						pstmt.close();
						con.close();
					}catch(Exception e2){}
				}
				model2.setRowCount(0);
				selectCompany();			
			}		
		});

		// 버튼 ------------------------------------------------------------------------	
		JButton ToUserInfoBtn = new RoundedButton();
		ToUserInfoBtn.setText("회원정보");
		ToUserInfoBtn.setFont(textFont);
		ToUserInfoBtn.setForeground(fontColor);
		ToUserInfoBtn.setBounds(424, 20, 110, 30);
		contentPane.add(ToUserInfoBtn);
		
		ToUserInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bottomPanel.removeAll();
				bottomPanel.add(userInfoPanel);
				revalidate();
				repaint();
			}
		});
			
		JButton ToReservationBtn = new RoundedButton();
		ToReservationBtn.setText("예약정보");
		ToReservationBtn.setFont(textFont);
		ToReservationBtn.setForeground(fontColor);
		ToReservationBtn.setBounds(546, 20, 110, 30);
		contentPane.add(ToReservationBtn);
		
		ToReservationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bottomPanel.removeAll();
				bottomPanel.add(reservationPanel);
				revalidate();
				repaint();
			}
		});
			
		JButton ToCompanyBtn = new RoundedButton();
		ToCompanyBtn.setText("사업장등록");
		ToCompanyBtn.setFont(textFont);
		ToCompanyBtn.setForeground(fontColor);
		ToCompanyBtn.setBounds(668, 20, 110, 30);
		contentPane.add(ToCompanyBtn);
		
		ToCompanyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bottomPanel.removeAll();
				bottomPanel.add(companyPanel);
				revalidate();
				repaint();
			}
		});
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					managerPage frame = new managerPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}