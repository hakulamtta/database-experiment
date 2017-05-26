package dynamicOP;
import java.sql.*;
import java.util.ArrayList;

public class ConnectSQL {
	public static ArrayList <Student> search(String searchStr) {
		String res = "";
		ResultSet rs = null ;
		ArrayList <Student> student = new ArrayList <Student> ();
	    try {
	      Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
	      System.out.println("Success loading Mysql Driver!");
	    }
	    catch (Exception e) {
	      System.out.print("Error loading Mysql Driver!");
	      e.printStackTrace();
	    }
	    try {
	      Connection connect = DriverManager.getConnection(
	          "jdbc:mysql://localhost:3306/student","root","fanfun");
	           //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码

	      System.out.println("查询成功!");
	      Statement stmt = connect.createStatement();
	      //ResultSet rs = stmt.executeQuery("select * from stu");//stu 为表的名称
	      rs = (ResultSet) stmt.executeQuery(searchStr);//stu 为表的名称
	      
	      while (rs.next()) {//对检索到的每一条记录，加入结果集
	    	  res = res + rs.getString("num") + rs.getString("class")
	    	  + rs.getString("name") +rs.getString("f") + rs.getString("age")
	    	  + rs.getString("addr")+ rs.getString("sex");
	    	  
		      System.out.println("查询结果："+res);
		      
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
	      System.out.print("查询失败!");
	      e.printStackTrace();
	    }
		return student;
	}
}
