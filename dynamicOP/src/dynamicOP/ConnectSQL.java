package dynamicOP;
import java.sql.*;
import java.util.ArrayList;

public class ConnectSQL {
	public static ArrayList <Student> search(String searchStr) {
		String res = "";
		ResultSet rs = null ;
		ArrayList <Student> student = new ArrayList <Student> ();
	    try {
	      Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
	      System.out.println("Success loading Mysql Driver!");
	    }
	    catch (Exception e) {
	      System.out.print("Error loading Mysql Driver!");
	      e.printStackTrace();
	    }
	    try {
	      Connection connect = DriverManager.getConnection(
	          "jdbc:mysql://localhost:3306/student","root","fanfun");
	           //����URLΪ   jdbc:mysql//��������ַ/���ݿ���  �������2�������ֱ��ǵ�½�û���������

	      System.out.println("��ѯ�ɹ�!");
	      Statement stmt = connect.createStatement();
	      //ResultSet rs = stmt.executeQuery("select * from stu");//stu Ϊ�������
	      rs = (ResultSet) stmt.executeQuery(searchStr);//stu Ϊ�������
	      
	      while (rs.next()) {//�Լ�������ÿһ����¼����������
	    	  res = res + rs.getString("num") + rs.getString("class")
	    	  + rs.getString("name") +rs.getString("f") + rs.getString("age")
	    	  + rs.getString("addr")+ rs.getString("sex");
	    	  
		      System.out.println("��ѯ�����"+res);
		      
	    	  Student st=new Student(); 
	    	  st.Num = rs.getString("num");
	    	  st.Class= rs.getString("class");
	    	  st.Name= rs.getString("name");
	    	  st.F= rs.getString("f");
	    	  st.Age = rs.getString("age");
	    	  st.Addr = rs.getString("addr");
	    	  st.Sex = rs.getString("sex");
	    	  student.add(st);
		  }
	      for (int i=0;i<student.size();i++)
			  System.out.println(student.get(i));
	    }
	    catch (Exception e) {
	      System.out.print("��ѯʧ��!");
	      e.printStackTrace();
	    }
		return student;
	}
}
