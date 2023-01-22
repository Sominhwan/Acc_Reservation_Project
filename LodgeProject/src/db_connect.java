import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class db_connect {
	
	Connection con = null;
	Statement stmt=null;
	ResultSet rs=null;
	int num;
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String id = "lodge"; String password = "1234";
	public db_connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    System.out.println("����̹� ���� ����");
		    } catch (ClassNotFoundException e) { System.out.println("No Driver."); }  
	}
	
	private void Connect() {
		try {
			con = DriverManager.getConnection(url, id, password);
		    System.out.println("DB ���� ����");
		    } catch (SQLException e) {         
		    	System.out.println("Connection Fail");      
		    	}
	}
	   
	public void getCompayList(JComboBox companyList) throws SQLException{
		Connect();
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		ResultSet.CONCUR_UPDATABLE);
		rs = stmt.executeQuery("select distinct(ȸ���̸�) from �����");	
		rs.last();
		int size = rs.getRow();
	    rs.beforeFirst();
	    String list[]=new String[size];
	    int i=0;
	    
	    while(rs.next()){
	    	list[i]=rs.getString("ȸ���̸�");
	    	i++;
	    }			
	    companyList.setModel(new DefaultComboBoxModel(list));
	}
	   
	public boolean check_code(String code,String company) throws SQLException{
		Connect();
		boolean flag;
		stmt = con.createStatement();
		rs = stmt.executeQuery("select ȸ���ڵ� from ���� where ȸ���ڵ�='"+code+"' and ȸ���̸�='"+company+"'");
		while(rs.next()){ 
			return true;
			}
					   
		return false;   
	}
	   
	public String getnickname(String code,String company) throws SQLException{
		Connect();
		stmt = con.createStatement();
		rs = stmt.executeQuery("select �г��� from ���� where ȸ���ڵ�='"+code+"' and ȸ���̸�='"+company+"'");
		rs.next();
		if(rs==null){
			JOptionPane.showMessageDialog(null,"ȸ�翡 ��ϵ������� ���̵��Դϴ�");
			return null;
		}
		else
			return rs.getString("�г���");		 
	}
	   
	public void r_procedure(String b_number,String r_number,String month,String day,String nickname,String stay,int u_point) throws SQLException {   
		Connect();
		CallableStatement cstmt = con.prepareCall("{call reservation(?,?,?,?,?,?)}");
	    cstmt.setString(1,b_number);
	    cstmt.setString(2,r_number);
        Date d=null;
        DateFormat formatter ; 
	    String date=Calendar.getInstance().get(Calendar.YEAR)+month+day;
	    formatter = new SimpleDateFormat("yyyyMMdd"); 
	    try {
	    	d = (Date)formatter.parse(date);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    
	    java.sql.Date sqlDate2 = new java.sql.Date(d.getTime());
	    cstmt.setDate(3,sqlDate2);
	    cstmt.setString(4,nickname);
	    cstmt.setInt(5,Integer.valueOf(stay));
	    cstmt.setInt(6,u_point);
	    int flag=cstmt.executeUpdate();
	    if(flag==0){
	    	JOptionPane.showMessageDialog(null,"���೯¥�� ��Ĩ�ϴ�");
	    }
	    else{
	    	JOptionPane.showMessageDialog(null,"����Ǿ����ϴ�");
	    }
	    con.close();	
	    cstmt.close();   
	}
	
	public void average_review(JLabel a_review,String b_number) throws SQLException{
		double d;
		DecimalFormat form=new DecimalFormat("#.#");
		Connect();
		stmt = con.createStatement();
		rs = stmt.executeQuery("select avg(����) from �ۼ� where ������ȣ='"+b_number+"'");
		rs.next();
		d=rs.getDouble(1);
			 
		a_review.setText(form.format(d));		 
	}
	
	public void r_history(String query, DefaultTableModel table) throws SQLException{
		Connect();
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			table.addRow(new Object[]{rs.getString("������ȣ"),rs.getString("ȣ��"),rs.getDate("������"),rs.getString("�г���"),rs.getInt("���ڱⰣ"),rs.getInt("�������Ʈ")});
		}
			con.close();
			rs.close();
			stmt.close();
	}
	
	public void getList(String query,JComboBox c) throws SQLException{
		Connect();
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		ResultSet.CONCUR_UPDATABLE);
		rs = stmt.executeQuery(query);  
		rs.last();
		int size = rs.getRow();
		String s[]=new String[size];
	    rs.beforeFirst();
	    int i=0;
			
	    while(rs.next()) {
	    	s[i]=rs.getString(1);
	    	i++;
	    	}

	    c.setModel(new DefaultComboBoxModel(s));
	    con.close();
	    rs.close();
		stmt.close();
	}
	
	public void r_cancel(String query,int use_point,String nickname) throws SQLException{
		Connect();
		stmt = con.createStatement();
		con.setAutoCommit(false);
		stmt.addBatch(query);		 
		String q="update �� set ����Ʈ=����Ʈ+"+use_point+" where �г��� ='"+nickname+"'";
		stmt.addBatch(q);
		stmt.executeBatch();
		con.commit();						
   	    con.setAutoCommit(true);		
 
		con.close();
		stmt.close();
		rs.close();  
	}
	
	public void cancelList(String query,DefaultTableModel d) throws SQLException{
		Connect();    
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);

		while(rs.next()) { 
			d.addRow(new Object[]{rs.getString("������ȣ"),rs.getString("ȣ��"),rs.getDate("������"),rs.getInt("���ڱⰣ"),rs.getInt("�������Ʈ")});
			}

		con.close();
		stmt.close();
		rs.close();		
	}
	
    public void getRoom(String query,DefaultTableModel table) throws SQLException{
		Connect();
		stmt = con.createStatement();
		rs = stmt.executeQuery(query); 
		
		while(rs.next()) {
			table.addRow(new Object[] {rs.getString("������ȣ"),rs.getString("ȣ��"),rs.getInt("����"),rs.getString("��Ÿ��")});
			}
		con.close();
		stmt.close();
		rs.close();
    }
	   
	public void w_review(JLabel b_number,JLabel room_number,String nickname,String content,int review_grade) throws SQLException{
		Connect();
		     
		num=num+1;
	//  String query="insert into �ۼ� values("+num+","+b_number.getText()+",'"+room_number.getText()+"','"+nickname+"','"+content+"',"+review_grade+")";
		stmt = con.createStatement();	
		System.out.println(b_number.getText());
		System.out.println(room_number.getText());
		System.out.println(nickname);
		System.out.println(content);
		System.out.println(review_grade+1);
		  	     
		stmt.executeUpdate("insert into �ۼ� values("+num+",'"+b_number.getText()+"','"+room_number.getText()+"','"+nickname+"','"+content+"',"+review_grade+1+")");
		 //    System.out.println(query);
		con.close();
		stmt.close();
	}
}
