// signUp.java
// author : 신동호
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class signUpPage extends JFrame {
	// 이미지 URL
	String imageurl = "C:\\images\\";
	// DB 객체
	Connection conn = null;
	Statement stmt = null;
	// 자바 GUI 폰트, 색상
	Color fontColor = Color.DARK_GRAY;
	Color labelColor = Color.GRAY;
	Font textFont = new Font("맑은 고딕", Font.PLAIN, 12);
	//db
	private String driver="oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@localhost:1521:XE";
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private PreparedStatement pstmt2=null;
	private ResultSet rs=null;
	// 입력 필드
	private JTextField code;
	private JTextField nickname;
	private JTextField juminField1;
	private JTextField juminField2;
	private JTextField addressField;
	private JTextField emailField;
	private JTextField phoneField;
	private JTextField nameField;
	private JTextField companyField;
	public signUpPage() {
		db_connect db=new db_connect();
		// 전체 패널
		JPanel signUpPanel = new JPanel();
		signUpPanel.setBackground(Color.WHITE);
		signUpPanel.setLayout(null);
		signUpPanel.setLocation(0, 0);
		signUpPanel.setSize(344, 450);
		
		// 최상단 텍스트 라벨
		JLabel info = new JLabel("|　가입 정보를 입력해주세요.");
		info.setForeground(labelColor);
		info.setFont(textFont);
		info.setHorizontalAlignment(SwingConstants.LEFT);
		info.setBounds(15, 20, 310, 27);
		signUpPanel.add(info);
		// 구분선
		JSeparator separatorLong = new JSeparator();
		separatorLong.setForeground(Color.LIGHT_GRAY);
		separatorLong.setBounds(15, 55, 310, 1);
		signUpPanel.add(separatorLong);
	
		// 텍스트 입력 필드
		code = new JTextField(); // 회원코드
		code.setMargin(new Insets(2, 5, 2, 2));
		code.setForeground(fontColor);
		code.setFont(textFont);
		code.setColumns(10);
	    code.setBounds(110, 75, 186, 27);
		signUpPanel.add(code);
		
		nameField = new JTextField(); // 이름
		nameField.setMargin(new Insets(2, 5, 2, 2));
		nameField.setForeground(fontColor);
		nameField.setFont(textFont);
		nameField.setColumns(10);
		nameField.setBounds(110, 115, 186, 27);
		signUpPanel.add(nameField);
		
		juminField1 = new JTextField(12); // 주민번호 앞자리
		juminField1.setMargin(new Insets(2, 5, 2, 2));
		juminField1.setForeground(fontColor);
		juminField1.setFont(textFont);
		juminField1.setColumns(10);
		juminField1.setBounds(110, 155, 75, 27);
		signUpPanel.add(juminField1);
		
		juminField2 = new JPasswordField(12); // 주민번호 뒷자리
		juminField2.setMargin(new Insets(2,5,2,2));
		juminField2.setForeground(fontColor);
		juminField2.setFont(textFont);
		juminField2.setColumns(10);
		juminField2.setBounds(201,155,95,27);
		signUpPanel.add(juminField2);
		
		phoneField = new JTextField(16); // 전화번호
		phoneField.setMargin(new Insets(2, 5, 2, 2));
		phoneField.setForeground(fontColor);
		phoneField.setBounds(110, 195, 186, 27);
		phoneField.setColumns(10);
		phoneField.setFont(textFont);
		signUpPanel.add(phoneField);
		
		addressField = new JTextField(16); // 주소
		addressField.setMargin(new Insets(2, 5, 2, 2));
		addressField.setForeground(fontColor);
		addressField.setBounds(110, 235, 186, 27);
		addressField.setColumns(10);
		addressField.setFont(textFont);
		signUpPanel.add(addressField);
		
		emailField = new JTextField(16); // 이메일
		emailField.setMargin(new Insets(2, 5, 2, 2));
		emailField.setForeground(fontColor);
		emailField.setFont(textFont);
		emailField.setColumns(10);
		emailField.setBounds(110, 275, 186, 27);
		signUpPanel.add(emailField);
							// 회사 선택 콤보박스
		companyField=new JTextField(16);
		companyField.setMargin(new Insets(2, 5, 2, 2));
		companyField.setForeground(fontColor);
		companyField.setFont(textFont);
		companyField.setColumns(10);
		companyField.setBounds(110, 315, 186, 27);
		signUpPanel.add(companyField);
			
		nickname = new JTextField(); // 닉네임
		nickname.setMargin(new Insets(2, 5, 2, 2));
		nickname.setForeground(fontColor);
		nickname.setFont(textFont);
		nickname.setColumns(10);
		nickname.setBounds(110, 355, 186, 27);
		signUpPanel.add(nickname);
	
		JLabel  nickname= new JLabel("닉네임");
		nickname.setHorizontalAlignment(SwingConstants.LEFT);
		nickname.setForeground(labelColor);
		nickname.setFont(textFont);
		nickname.setBounds(25, 355, 75, 27);
		signUpPanel.add(nickname);
		
		JLabel code = new JLabel("회원코드");
		code.setHorizontalAlignment(SwingConstants.LEFT);
		code.setForeground(labelColor);
		code.setFont(textFont);
		code.setBounds(25, 75, 75, 27);
		signUpPanel.add(code);
		
		// 필드 이름 라벨
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameLabel.setForeground(labelColor);
		nameLabel.setFont(textFont);
		nameLabel.setBounds(25, 115, 75, 27);
		signUpPanel.add(nameLabel);
		
		JLabel juminLabel = new JLabel("주민등록번호");
		juminLabel.setHorizontalAlignment(SwingConstants.LEFT);
		juminLabel.setForeground(labelColor);
		juminLabel.setFont(textFont);
		juminLabel.setBounds(25, 155, 75, 27);
		signUpPanel.add(juminLabel);
		
		JLabel betweenJumin = new JLabel("-");
		betweenJumin.setBounds(189, 155, 10, 27);
		betweenJumin.setForeground(labelColor);
		betweenJumin.setFont(textFont);
		signUpPanel.add(betweenJumin);
		
		JLabel phoneLabel = new JLabel("휴대폰번호");
		phoneLabel.setBounds(new Rectangle(288, 170, 0, 0));
		phoneLabel.setFont(textFont);
		phoneLabel.setForeground(labelColor);
		phoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		phoneLabel.setBounds(25, 195, 75, 27);
		signUpPanel.add(phoneLabel);
		
		JLabel addressLabel = new JLabel("주소");
		addressLabel.setHorizontalAlignment(SwingConstants.LEFT);
		addressLabel.setForeground(labelColor);
		addressLabel.setFont(textFont);
		addressLabel.setBounds(25, 235, 75, 27);
		signUpPanel.add(addressLabel);
		
		JLabel emailLabel = new JLabel("이메일");
		emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailLabel.setBounds(25, 275, 75, 27);
		emailLabel.setForeground(labelColor);
		emailLabel.setFont(textFont);
		signUpPanel.add(emailLabel);

		JLabel companyLabel = new JLabel("등록 회사");
		companyLabel.setHorizontalAlignment(SwingConstants.LEFT);
		companyLabel.setForeground(labelColor);
		companyLabel.setFont(textFont);
		companyLabel.setBounds(25, 315, 75, 27);
		signUpPanel.add(companyLabel);

		// 회원가입 버튼
		JButton signUpBtn = new RoundedButton("SIGN UP");
		signUpBtn.setFont(textFont);
		signUpBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		signUpBtn.setBounds(110, 400, 186, 30);
		signUpBtn.setBorderPainted(false);
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist(); //회원가입 메소드
			}
		});
		signUpPanel.add(signUpBtn);
		
		// 자바 스윙 세팅
		setBackground(Color.WHITE);
		setSize(350,485);
		setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Container c = getContentPane();
		c.add(signUpPanel);
	}
	
	// 회원가입 메소드
	public void regist() {
		int point=0;
		String credit="일반";
		String query="insert into 고객 (고객이름, 닉네임, 주민등록번호, 휴대폰번호, 주소, 이메일, 포인트, 등급)"
				+"values(?,?,?,?,?,?,?,?)";
		
		String query2="insert into 가입 (회원코드, 회사이름, 닉네임)"
				+"values(?,?,?)";
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,"lodge","1234");
			
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, nameField.getText());//아아디
			pstmt.setString(2, nickname.getText());//닉네임
			pstmt.setString(3, juminField1.getText()+"-"+juminField2.getText());//주민등록번호
			pstmt.setString(4, phoneField.getText());//휴대폰번호
			pstmt.setString(5, addressField.getText());//주소
			pstmt.setString(6, emailField.getText());//이메일
			pstmt.setInt(7, point);//포인트
			pstmt.setString(8, credit);//등급
			pstmt.executeUpdate();
				
			pstmt2=con.prepareStatement(query2);
			pstmt2.setString(1, code.getText());//회원코드
			pstmt2.setString(2, companyField.getText());//회사
			pstmt2.setString(3, nickname.getText());//닉네임
			pstmt2.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "회원가입 성공","Message",JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
		}catch(Exception eeee){
			System.out.println(eeee.getMessage());
			JOptionPane.showMessageDialog(null, "회원가입 실패","Message",JOptionPane.ERROR_MESSAGE);
		}finally{
			try{
				pstmt.close();
				con.close();
			}catch(Exception e2){}
		}	
	}
}