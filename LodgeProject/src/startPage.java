// �����ͺ��̽����� ������ ��������Ʈ
// ���� ���� �����ͺ��̽� JDBC GUI
// ���� ȭ�� (�α���ȭ��)
// Author : �ŵ�ȣ(20153150)

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class startPage extends JFrame {
	private JTextField codeField;
	private JTextField pwField;
	private JComboBox companyList;
	// Settings
	Color fontColor = Color.DARK_GRAY;
	Font textFont = new Font("���� ���", Font.PLAIN, 12);
	
	public startPage() {
		db_connect db=new db_connect();
		// ��ü ���� �г� 'loginPanel'
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(Color.WHITE);
		loginPanel.setBounds(0, 0, 594, 421);
		loginPanel.setLayout(null);
		loginPanel.setBorder(null);
		loginPanel.setOpaque(true);
		
		// ���� ���̵�(ȸ���ڵ�) �Է� �ʵ�
		codeField = new JTextField();
		codeField.setForeground(new Color(255, 90, 0));
		codeField.setBounds(326, 166, 186, 37);
		codeField.setColumns(10);
		codeField.setFont(textFont);
		loginPanel.add(codeField);
		
		// ��� ȸ�� ����Ʈ
		companyList = new JComboBox();
		companyList.setVisible(true);
		companyList.setBounds(326, 213, 186, 37);
		loginPanel.add(companyList);
		try {
			db.getCompayList(companyList);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
				
		// ��й�ȣ �Է� �ʵ�
		pwField = new JPasswordField();
		pwField.setVisible(false);
		pwField.setForeground(new Color(255, 90, 0));
		pwField.setColumns(10);
		pwField.setBounds(326, 213, 186, 37);
		loginPanel.add(pwField);
		
		// ���α׷� ���� ���
		JLabel banner = new JLabel("");
		banner.setBounds(new Rectangle(0, 0, 192, 150));
		banner.setSize(new Dimension(192, 150));

		banner.setIcon(new ImageIcon(".\\images\\banner.png"));
		banner.setBounds(67, 134, 186, 150);
		loginPanel.add(banner);

		// "CODE", "PW" �ؽ�Ʈ ��
		JLabel codeLabel = new JLabel("�ڵ�");
		codeLabel.setVisible(true);
		codeLabel.setBounds(new Rectangle(288, 170, 0, 0));
		codeLabel.setFont(textFont);
		codeLabel.setForeground(Color.GRAY);
		codeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		codeLabel.setBounds(265, 166, 49, 37);
		loginPanel.add(codeLabel);
		
		JLabel clLabel = new JLabel("���ȸ��");
		clLabel.setVisible(true);
		clLabel.setFont(textFont);
		clLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		clLabel.setForeground(Color.GRAY);
		clLabel.setBounds(256, 213, 58, 37);
		loginPanel.add(clLabel);
		
		JLabel pwLabel = new JLabel("�н�����");
		pwLabel.setVisible(false);
		pwLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		pwLabel.setForeground(Color.GRAY);
		pwLabel.setFont(textFont);
		pwLabel.setBounds(256, 213, 58, 37);
		loginPanel.add(pwLabel);
		
		// ȸ������("SIGN UP") ��ư
		JButton signUpBtn = new JButton("SIGN UP");
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new signUpPage();
			}
		});
		signUpBtn.setMargin(new Insets(0, 0, 0, 0));
		signUpBtn.setFont(textFont);
		signUpBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		signUpBtn.setForeground(Color.GRAY);
		signUpBtn.setBackground(Color.WHITE);
		signUpBtn.setBorderPainted(false);
		signUpBtn.setBounds(460, 133, 57, 23);
		loginPanel.add(signUpBtn);
		
		// �α��� ��ư
		JButton loginBtn = new JButton();
		
		loginBtn.setIcon(new ImageIcon(".\\images\\login_btn.png"));
		loginBtn.setRolloverIcon(new ImageIcon(".\\images\\login_btn_rollover.png"));
		loginBtn.setBorderPainted(false);
		loginBtn.setContentAreaFilled(false);
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					login(codeField.getText(),companyList.getSelectedItem().toString());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		loginBtn.setFont(textFont);
		loginBtn.setBounds(326, 284, 186, 25);
		loginPanel.add(loginBtn);
		
		JButton adminLoginBtn = new RoundedButton("������ �α���");
		
		adminLoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code="blank";
				if(code.equals(codeField.getText()) && Arrays.equals(((JPasswordField) pwField).getPassword(), "1234".toCharArray()) )
				{
					 JOptionPane.showMessageDialog(null,"�α��� ����");
					 setVisible(false);	
					 new managerPage();
					 
				}
				else{
					 JOptionPane.showMessageDialog(null, "�α��� ����","Message",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		adminLoginBtn.setFont(textFont);
		adminLoginBtn.setBounds(326, 284, 186, 25);
		loginPanel.add(adminLoginBtn);
		
		// ������ �α��� üũ�ڽ�
		JCheckBox check = new JCheckBox("�����ڷ� �α���");
		check.setBounds(399, 256, 113, 23);
		loginPanel.add(check);
		check.addActionListener(new ActionListener() {
			// ������ �α��� üũ�Ǹ� �н����� �ʵ尡 ��Ÿ���� ������ ��ġ����
			public void actionPerformed(ActionEvent e) {
				if(check.isSelected()) {
					pwLabel.setVisible(true);
					pwField.setVisible(true);
					clLabel.setVisible(false);
					companyList.setVisible(false);
					loginBtn.setVisible(false);
					adminLoginBtn.setVisible(true);
				}
				else {
					pwLabel.setVisible(false);
					pwField.setVisible(false);
					clLabel.setVisible(true);
					companyList.setVisible(true);
					adminLoginBtn.setVisible(false);
					loginBtn.setVisible(true);
				}
			}
		});
		check.setFont(textFont);
		check.setForeground(fontColor);
		check.setBackground(Color.WHITE);
		check.setBorderPainted(false);
		
		// �����̳�
		Container c = getContentPane();
		c.add(loginPanel);
		
		getContentPane().setBackground(Color.WHITE);
		this.setLocationRelativeTo(null);
		this.setBounds(new Rectangle(0, 0, 630, 480));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// �α��� ��ư �޼ҵ� 
	public void login(String codeLabel,String company) throws SQLException {
		db_connect db=new db_connect();
		if(db.check_code(codeLabel,company))
		{
			System.out.println(db.check_code(codeLabel, company));
			setVisible(false);
			new userPage(codeLabel,company,companyList.getSelectedItem().toString());
		}
		else
		{
   		 JOptionPane.showMessageDialog(null,"��ϵ� ������ �����ϴ�.");

		}
	}
}