import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Container;

public class myPage extends JFrame implements MouseListener{
	String imageurl = ".\\images\\";
	Color fontColor = Color.DARK_GRAY;
	Font textFont = new Font("���� ���", Font.PLAIN, 12);
	
	private JPanel contentPane;
	private JPanel reviewPanel;
	private JTable historyTable;
	private JTable cancelTable;
	private JTable reviewTable;
	private String nickname;
	private RoundedButton roundbutton;
	int row;
		 
	public myPage(String nickname) {
		this.nickname=nickname;
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 608);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	

		// ���̺� ���� - test��
		String cancelHeader[] = {"", "����", "�����ȣ", "������̸�", "������", "���ڱⰣ", "��Ÿ��", "ȣ��", "����"};
		String cancelContents[][] = {{"1", "1", "(null)", "��ǰȣ��","21/09/19","1","���Ĵٵ�","A-001","70000" }};
		String historyHeader[] = {"", "�����ȣ", "������̸�", "������", "���ڱⰣ", "��Ÿ��", "ȣ��", "����"};
		String historyContents[][] = {{"1", "(null)", "��ǰȣ��","21/09/19","1","���Ĵٵ�","A-001","70000" }};
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.ORANGE);
		bottomPanel.setBounds(20, 83, 770, 476);
		contentPane.add(bottomPanel);
		bottomPanel.setLayout(null);
		
		// �����ۼ� �г�---------------------------------------------------------------------
		reviewPanel = new JPanel();
		reviewPanel.setBackground(Color.WHITE);
		reviewPanel.setBounds(0, 0, 770, 476);
		bottomPanel.add(reviewPanel);
		reviewPanel.setLayout(null);
		
		JLabel reviewInfoLabel = new JLabel("<�����ۼ�>");
		reviewInfoLabel.setBounds(47, 0, 676, 35);
		reviewInfoLabel.setFont(textFont);
		reviewInfoLabel.setForeground(fontColor);
		reviewInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reviewPanel.add(reviewInfoLabel);
		
		RoundedButton writeBtn = new RoundedButton("����ϱ�");
		writeBtn.setText("����ϱ�");
		writeBtn.setForeground(Color.DARK_GRAY);
		writeBtn.setFont(new Font("���� ���", Font.PLAIN, 12));
		writeBtn.setBounds(626, 431, 97, 23);
		reviewPanel.add(writeBtn);
		
		
		JLabel companyName = new JLabel("���並 �ۼ��� ��ü �̸� :");
		companyName.setFont(textFont);
		companyName.setForeground(fontColor);
		companyName.setBounds(47, 309, 151, 15);
		reviewPanel.add(companyName);
		
		// DB���� �о�;��� �κ� / �ӽ÷� ��ǰȣ�ڷ� �س����
		JLabel readCompanyName = new JLabel("");
		readCompanyName.setBounds(195, 309, 197, 15);
		readCompanyName.setFont(textFont);
		readCompanyName.setForeground(new Color(255, 90, 0));
		reviewPanel.add(readCompanyName);
		
		JLabel starInfoLabel = new JLabel("���� |");
		starInfoLabel.setForeground(Color.DARK_GRAY);
		starInfoLabel.setFont(new Font("���� ���", Font.PLAIN, 12));
		starInfoLabel.setBounds(590, 309, 37, 15);
		reviewPanel.add(starInfoLabel);
		
		JComboBox gradeBox = new JComboBox();
		gradeBox.setBackground(Color.WHITE);
		gradeBox.setFont(textFont);
		gradeBox.setForeground(new Color(255,90,0));
		gradeBox.setModel(new DefaultComboBoxModel(new String[] {"��", "�ڡ�", "�ڡڡ�", "�ڡڡڡ�", "�ڡڡڡڡ�"}));
		gradeBox.setBounds(634, 305, 89, 23);
		reviewPanel.add(gradeBox);
		
		JTextArea reviewContent = new JTextArea();
		reviewContent.setLineWrap(true);
		reviewContent.setBounds(47, 331, 676, 103);
		reviewContent.setBorder(new LineBorder(new Color(255, 90, 0), 1, true));
		reviewContent.setFont(textFont);
		reviewContent.setForeground(fontColor);
		reviewContent.setBounds(47, 333, 676, 90);
		reviewPanel.add(reviewContent);	
		reviewTable = new JTable();
		DefaultTableModel m =(DefaultTableModel)reviewTable.getModel();
		 m.addColumn("������ȣ");
		 m.addColumn("ȣ��");
		 m.addColumn("������");
		 m.addColumn("�г���");
		 m.addColumn("���ڱⰣ");
		 m.addColumn("�������Ʈ");
		reviewTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reviewTable.addMouseListener(this);
		
		JScrollPane reviewSPane = new JScrollPane(reviewTable);
		reviewSPane.setForeground(Color.DARK_GRAY);
		reviewSPane.setFont(new Font("���� ���", Font.PLAIN, 12));
		reviewSPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		reviewSPane.setBackground(Color.WHITE);
		reviewSPane.setBounds(37, 32, 695, 267);
		reviewPanel.add(reviewSPane);
		db_connect db=new db_connect();
		String query="select * from ���� where ������<(select sysdate from dual) and �г���='"+nickname+"'";
		try {
		db.r_history(query,m);
		}
		catch(SQLException e)
		{
			
		}
		JLabel goback = new JLabel("<  ����");
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
			}
		});
		
		// �������� �г�--------------------------------------------------------------------
		JPanel historyPanel = new JPanel();
		historyPanel.setBackground(Color.WHITE);
		historyPanel.setBounds(0, 0, 770, 476);
		bottomPanel.add(historyPanel);
		historyPanel.setLayout(null);
		
		JLabel historyInfoLabel = new JLabel("<��������>");
		historyInfoLabel.setFont(textFont);
		historyInfoLabel.setForeground(fontColor);
		historyInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		historyInfoLabel.setBounds(47, 0, 676, 35);
		historyPanel.add(historyInfoLabel);
		
		historyTable = new JTable(historyContents, historyHeader);
		historyTable.setBackground(Color.GRAY);
		historyTable.setBounds(50, 57, 600, 400);
		historyTable.setGridColor(SystemColor.activeCaption);
		historyTable.setFont(textFont);
		historyTable.setForeground(fontColor);
		historyTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane historySPane = new JScrollPane(historyTable);
		historySPane.setBounds(47, 35, 676, 397);
		historySPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		historyTable.setBackground(Color.WHITE);
		historyTable.setBounds(50, 57, 600, 400);
		historyPanel.add(historySPane);		
		
		// ������� �г�------------------------------------------------------------------
		
		JPanel cancelPanel = new JPanel();
		cancelPanel.setBackground(Color.WHITE);
		cancelPanel.setBounds(0, 0, 770, 476);
		bottomPanel.add(cancelPanel);
		cancelPanel.setLayout(null);
		
		JLabel cancelInfoLabel = new JLabel("<�������>");
		cancelInfoLabel.setFont(textFont);
		cancelInfoLabel.setForeground(fontColor);
		cancelInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cancelInfoLabel.setBounds(47, 0, 676, 35);
		cancelPanel.add(cancelInfoLabel);
		
		JTable cancelTable = new JTable();
		  DefaultTableModel m2 =(DefaultTableModel)cancelTable.getModel();
		  m2.addColumn("������ȣ");
		  m2.addColumn("ȣ��");
		  m2.addColumn("������");
		  m2.addColumn("���ڱⰣ");
		  m2.addColumn("�������Ʈ");
		  String q="select ������ȣ,ȣ��,������,���ڱⰣ,�������Ʈ from ���� where ������>=(select sysdate from dual) and �г���='"+nickname+"'";
		  System.out.println(nickname);
		  try {
			db.cancelList(q,m2);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		  JScrollPane cancelSPane = new JScrollPane(cancelTable);
			cancelSPane.setBounds(47, 35, 676, 347);
			cancelSPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			cancelTable.setBackground(Color.WHITE);
			cancelTable.setBounds(50, 57, 600, 400);
			cancelPanel.add(cancelSPane);
			cancelTable.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					 row= cancelTable.getSelectedRow();
					
					revalidate();
					repaint();
				}
			});
        
		JButton cancelBtn = new RoundedButton("����ϱ�");
		cancelBtn.setForeground(fontColor);
		cancelBtn.setFont(textFont);
		cancelBtn.setBounds(626, 400, 97, 23);
		cancelPanel.add(cancelBtn);
		
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q="delete from ���� where ������ȣ='"+m2.getValueAt(row,0).toString()+"' and ȣ��='"+(String)m2.getValueAt(row,1)+"' and ������='"+(Date)m2.getValueAt(row,2)+"'";
				System.out.println(m2.getValueAt(row,0));
				System.out.println(m2.getValueAt(row,1));
				System.out.println(m2.getValueAt(row,2));
				try {
					db.r_cancel(q,(int)m2.getValueAt(row,4),nickname);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				revalidate();
				repaint();
			}
		});
		
		//ȸ���������� �г�-----------------------------------------------------------------
		
		JPanel editPanel = new JPanel();
		editPanel.setBackground(Color.WHITE);
		editPanel.setBounds(0, 0, 770, 476);
		bottomPanel.add(editPanel);
		editPanel.setLayout(null);
		
		JLabel editInfoLabel = new JLabel("<ȸ����������>");
		editInfoLabel.setFont(textFont);
		editInfoLabel.setForeground(fontColor);
		editInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editInfoLabel.setBounds(47, 0, 676, 35);
		editPanel.add(editInfoLabel);
			
		// ��ư ----------------------------------------------------------------------------
		JButton ToReviewBtn = new RoundedButton("�����ۼ�");
		ToReviewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bottomPanel.removeAll();
				bottomPanel.add(reviewPanel);
				revalidate();
				repaint();
			}
		});
		ToReviewBtn.setFont(textFont);
		ToReviewBtn.setForeground(fontColor);
		ToReviewBtn.setBounds(302, 20, 110, 30);
		contentPane.add(ToReviewBtn);
		
		
		JButton ToEditBtn = new RoundedButton("ȸ����������");
		ToEditBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bottomPanel.removeAll();
				bottomPanel.add(editPanel);
				revalidate();
				repaint();
			}
		});
		ToEditBtn.setFont(textFont);
		ToEditBtn.setForeground(fontColor);
		ToEditBtn.setBounds(424, 20, 110, 30);
		contentPane.add(ToEditBtn);
			
		JButton ToCancelBtn = new RoundedButton("�������");
		ToCancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bottomPanel.removeAll();
				bottomPanel.add(cancelPanel);
				revalidate();
				repaint();
			}
		});
		ToCancelBtn.setFont(textFont);
		ToCancelBtn.setForeground(fontColor);
		ToCancelBtn.setBounds(546, 20, 110, 30);
		contentPane.add(ToCancelBtn);
			
		JButton ToHistoryBtn = new RoundedButton("��������");
		ToHistoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bottomPanel.removeAll();
				bottomPanel.add(historyPanel);
				revalidate();
				repaint();
			}
		});
		ToHistoryBtn.setFont(textFont);
		ToHistoryBtn.setForeground(fontColor);
		ToHistoryBtn.setBounds(668, 20, 110, 30);
		contentPane.add(ToHistoryBtn);
	
		// ���м�
		JSeparator separatorX = new JSeparator();
		separatorX.setForeground(Color.LIGHT_GRAY);
		separatorX.setBounds(20, 60, 770, 2);
		contentPane.add(separatorX);
		JLabel mypageLabel = new JLabel("|  ����������");
		mypageLabel.setBounds(69, 13, 80, 15);
		mypageLabel.setFont(textFont);
		mypageLabel.setForeground(fontColor);
		contentPane.add(mypageLabel);
	
		JLabel b_number = new JLabel("");
		b_number.setFont(textFont);
		b_number.setForeground(new Color(255, 90, 0));
		b_number.setBounds(195, 309, 197, 15);

		JLabel room_number = new JLabel("");
		room_number.setFont(textFont);
		room_number.setBounds(290, 309, 197, 15);
		room_number.setForeground(new Color(255, 90, 0));
		reviewPanel.add(b_number);
		reviewPanel.add(room_number);
					  
		writeBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int review_grade=gradeBox.getSelectedIndex(); 

		    	try {
		    		db.w_review(b_number,room_number,nickname,reviewContent.getText(),review_grade);
		    		JOptionPane.showMessageDialog(null,"��ϵǾ����ϴ�.");
		    		}
		    	catch(SQLException e1) {
		    		System.out.println(e1);  
		    		}
		    	} 
		    });
		setVisible(true);
		}
	  
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("����");
			int row = reviewTable.getSelectedRow();
			TableModel data = reviewTable.getModel();
			String number1 = (String)data.getValueAt(row,0);
			String number2 = (String)data.getValueAt(row,1);
			((JLabel)reviewPanel.getComponent(8)).setText(number1);		
			((JLabel)reviewPanel.getComponent(9)).setText(number2);			
			
			repaint();
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
		
		}

		@Override
		public void mouseExited(MouseEvent e) {
		
		}

		@Override
		public void mousePressed(MouseEvent e) {
		
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		
		}
	}