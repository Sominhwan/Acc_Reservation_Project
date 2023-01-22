// signUp.java
// author : �ŵ�ȣ
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
	// �̹��� URL
	String imageurl = "C:\\images\\";
	// DB ��ü
	Connection conn = null;
	Statement stmt = null;
	// �ڹ� GUI ��Ʈ, ����
	Color fontColor = Color.DARK_GRAY;
	Color labelColor = Color.GRAY;
	Font textFont = new Font("���� ���", Font.PLAIN, 12);
	//db
	private String driver="oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@localhost:1521:XE";
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private PreparedStatement pstmt2=null;
	private ResultSet rs=null;
	// �Է� �ʵ�
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
		// ��ü �г�
		JPanel signUpPanel = new JPanel();
		signUpPanel.setBackground(Color.WHITE);
		signUpPanel.setLayout(null);
		signUpPanel.setLocation(0, 0);
		signUpPanel.setSize(344, 450);
		
		// �ֻ�� �ؽ�Ʈ ��
		JLabel info = new JLabel("|������ ������ �Է����ּ���.");
		info.setForeground(labelColor);
		info.setFont(textFont);
		info.setHorizontalAlignment(SwingConstants.LEFT);
		info.setBounds(15, 20, 310, 27);
		signUpPanel.add(info);
		// ���м�
		JSeparator separatorLong = new JSeparator();
		separatorLong.setForeground(Color.LIGHT_GRAY);
		separatorLong.setBounds(15, 55, 310, 1);
		signUpPanel.add(separatorLong);
	
		// �ؽ�Ʈ �Է� �ʵ�
		code = new JTextField(); // ȸ���ڵ�
		code.setMargin(new Insets(2, 5, 2, 2));
		code.setForeground(fontColor);
		code.setFont(textFont);
		code.setColumns(10);
	    code.setBounds(110, 75, 186, 27);
		signUpPanel.add(code);
		
		nameField = new JTextField(); // �̸�
		nameField.setMargin(new Insets(2, 5, 2, 2));
		nameField.setForeground(fontColor);
		nameField.setFont(textFont);
		nameField.setColumns(10);
		nameField.setBounds(110, 115, 186, 27);
		signUpPanel.add(nameField);
		
		juminField1 = new JTextField(12); // �ֹι�ȣ ���ڸ�
		juminField1.setMargin(new Insets(2, 5, 2, 2));
		juminField1.setForeground(fontColor);
		juminField1.setFont(textFont);
		juminField1.setColumns(10);
		juminField1.setBounds(110, 155, 75, 27);
		signUpPanel.add(juminField1);
		
		juminField2 = new JPasswordField(12); // �ֹι�ȣ ���ڸ�
		juminField2.setMargin(new Insets(2,5,2,2));
		juminField2.setForeground(fontColor);
		juminField2.setFont(textFont);
		juminField2.setColumns(10);
		juminField2.setBounds(201,155,95,27);
		signUpPanel.add(juminField2);
		
		phoneField = new JTextField(16); // ��ȭ��ȣ
		phoneField.setMargin(new Insets(2, 5, 2, 2));
		phoneField.setForeground(fontColor);
		phoneField.setBounds(110, 195, 186, 27);
		phoneField.setColumns(10);
		phoneField.setFont(textFont);
		signUpPanel.add(phoneField);
		
		addressField = new JTextField(16); // �ּ�
		addressField.setMargin(new Insets(2, 5, 2, 2));
		addressField.setForeground(fontColor);
		addressField.setBounds(110, 235, 186, 27);
		addressField.setColumns(10);
		addressField.setFont(textFont);
		signUpPanel.add(addressField);
		
		emailField = new JTextField(16); // �̸���
		emailField.setMargin(new Insets(2, 5, 2, 2));
		emailField.setForeground(fontColor);
		emailField.setFont(textFont);
		emailField.setColumns(10);
		emailField.setBounds(110, 275, 186, 27);
		signUpPanel.add(emailField);
							// ȸ�� ���� �޺��ڽ�
		companyField=new JTextField(16);
		companyField.setMargin(new Insets(2, 5, 2, 2));
		companyField.setForeground(fontColor);
		companyField.setFont(textFont);
		companyField.setColumns(10);
		companyField.setBounds(110, 315, 186, 27);
		signUpPanel.add(companyField);
			
		nickname = new JTextField(); // �г���
		nickname.setMargin(new Insets(2, 5, 2, 2));
		nickname.setForeground(fontColor);
		nickname.setFont(textFont);
		nickname.setColumns(10);
		nickname.setBounds(110, 355, 186, 27);
		signUpPanel.add(nickname);
	
		JLabel  nickname= new JLabel("�г���");
		nickname.setHorizontalAlignment(SwingConstants.LEFT);
		nickname.setForeground(labelColor);
		nickname.setFont(textFont);
		nickname.setBounds(25, 355, 75, 27);
		signUpPanel.add(nickname);
		
		JLabel code = new JLabel("ȸ���ڵ�");
		code.setHorizontalAlignment(SwingConstants.LEFT);
		code.setForeground(labelColor);
		code.setFont(textFont);
		code.setBounds(25, 75, 75, 27);
		signUpPanel.add(code);
		
		// �ʵ� �̸� ��
		JLabel nameLabel = new JLabel("�̸�");
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameLabel.setForeground(labelColor);
		nameLabel.setFont(textFont);
		nameLabel.setBounds(25, 115, 75, 27);
		signUpPanel.add(nameLabel);
		
		JLabel juminLabel = new JLabel("�ֹε�Ϲ�ȣ");
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
		
		JLabel phoneLabel = new JLabel("�޴�����ȣ");
		phoneLabel.setBounds(new Rectangle(288, 170, 0, 0));
		phoneLabel.setFont(textFont);
		phoneLabel.setForeground(labelColor);
		phoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		phoneLabel.setBounds(25, 195, 75, 27);
		signUpPanel.add(phoneLabel);
		
		JLabel addressLabel = new JLabel("�ּ�");
		addressLabel.setHorizontalAlignment(SwingConstants.LEFT);
		addressLabel.setForeground(labelColor);
		addressLabel.setFont(textFont);
		addressLabel.setBounds(25, 235, 75, 27);
		signUpPanel.add(addressLabel);
		
		JLabel emailLabel = new JLabel("�̸���");
		emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailLabel.setBounds(25, 275, 75, 27);
		emailLabel.setForeground(labelColor);
		emailLabel.setFont(textFont);
		signUpPanel.add(emailLabel);

		JLabel companyLabel = new JLabel("��� ȸ��");
		companyLabel.setHorizontalAlignment(SwingConstants.LEFT);
		companyLabel.setForeground(labelColor);
		companyLabel.setFont(textFont);
		companyLabel.setBounds(25, 315, 75, 27);
		signUpPanel.add(companyLabel);

		// ȸ������ ��ư
		JButton signUpBtn = new RoundedButton("SIGN UP");
		signUpBtn.setFont(textFont);
		signUpBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		signUpBtn.setBounds(110, 400, 186, 30);
		signUpBtn.setBorderPainted(false);
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist(); //ȸ������ �޼ҵ�
			}
		});
		signUpPanel.add(signUpBtn);
		
		// �ڹ� ���� ����
		setBackground(Color.WHITE);
		setSize(350,485);
		setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Container c = getContentPane();
		c.add(signUpPanel);
	}
	
	// ȸ������ �޼ҵ�
	public void regist() {
		int point=0;
		String credit="�Ϲ�";
		String query="insert into �� (���̸�, �г���, �ֹε�Ϲ�ȣ, �޴�����ȣ, �ּ�, �̸���, ����Ʈ, ���)"
				+"values(?,?,?,?,?,?,?,?)";
		
		String query2="insert into ���� (ȸ���ڵ�, ȸ���̸�, �г���)"
				+"values(?,?,?)";
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,"lodge","1234");
			
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, nameField.getText());//�ƾƵ�
			pstmt.setString(2, nickname.getText());//�г���
			pstmt.setString(3, juminField1.getText()+"-"+juminField2.getText());//�ֹε�Ϲ�ȣ
			pstmt.setString(4, phoneField.getText());//�޴�����ȣ
			pstmt.setString(5, addressField.getText());//�ּ�
			pstmt.setString(6, emailField.getText());//�̸���
			pstmt.setInt(7, point);//����Ʈ
			pstmt.setString(8, credit);//���
			pstmt.executeUpdate();
				
			pstmt2=con.prepareStatement(query2);
			pstmt2.setString(1, code.getText());//ȸ���ڵ�
			pstmt2.setString(2, companyField.getText());//ȸ��
			pstmt2.setString(3, nickname.getText());//�г���
			pstmt2.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "ȸ������ ����","Message",JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
		}catch(Exception eeee){
			System.out.println(eeee.getMessage());
			JOptionPane.showMessageDialog(null, "ȸ������ ����","Message",JOptionPane.ERROR_MESSAGE);
		}finally{
			try{
				pstmt.close();
				con.close();
			}catch(Exception e2){}
		}	
	}
}