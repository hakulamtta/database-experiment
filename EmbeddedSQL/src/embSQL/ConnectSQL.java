package embSQL;

import java.sql.*;

public class ConnectSQL {
	public static String makeString(String searchNum,String keyword){
		String searchStr = null;
		System.out.println(searchNum);
		System.out.println(keyword);
		switch (searchNum){
		case "1"://[1]选择了课程号为CNO的学生学号
			searchStr = "select * from sc where Cid=" + keyword + ";";
//			searchStr = "select Sid from sc where Cid=" + keyword + ";";
			break;
		case "2"://[2]学号为SNO的学生选择的所有课程的课程号和成绩
			searchStr = "select * from sc where Sid=" + keyword + ";";
//			searchStr = "select Cid,Score from sc where Sid=" + keyword + ";";
			break;
		case "3"://[3]选择了课程名为CNAME的学生的姓名
			searchStr = "select * from student,course,sc where student.Sid=sc.Sid and sc.Cid=course.Cid and course.Cname=\"" + keyword + "\";";
//			searchStr = "select Sname from student,course,sc where student.Sid=sc.Sid and sc.Cid=course.Cid and course.Cname='" + keyword + "';";
			break;
		case "4"://[4]姓名为SNAME的学生所选所有课程的课程名，学时，学分和课学期号
			searchStr = "select Cname,Chours,Credit,Csemster from student,sc,course where student.Sid=sc.Sid and sc.Cid=course.Cid and student.Sname='" + keyword + "';";
			break;
		case "5"://[5]查询成绩在SCORE分以上的学生姓名、课程号和成绩
			searchStr = "select Sname,Cid,Score from student,sc where student.Sid=sc.Sid and Score>" + keyword + ";";
			break;
		case "6"://[6]统计选课平均分低于SCORE的学生学号和成绩
			searchStr = "select Sid, AVG(Score) from sc group by Sid Having AVG(Score)<" + keyword + ";";
			break;
		case "7"://[7]统计姓名为SNAME的学生选修的课程数
			searchStr = "select COUNT(sc.Cid) from sc,student,course where student.Sid=sc.Sid and sc.Cid=course.Cid and Sname='" + keyword + "';";
			break;
		case "8"://[8]查询课程名为CNAME的课程的最高分、最低分和平均分
			searchStr = "select sc.Cid, course.Cname, AVG(Score), MAX(Score), MIN(Score) from sc, course where sc.Cid=course.Cid and Cname='" + keyword + "' group by Cid;";
			break;
		}
		System.out.println(searchStr);
		return searchStr;
		
	}
	
	public static String makeResult(ResultSet rs,String searchNum,String keyword) throws SQLException{
		String res = null;
		switch (searchNum){
		case "1"://[1]选择了课程号为CNO的学生学号
			res = rs.getString("Sid") + "\n";
			break;
		case "2"://[2]学号为SNO的学生选择的所有课程的课程号和成绩
			res = rs.getString("Cid") + "\t" + rs.getFloat("Score") + "\n";
			break;
		case "3"://[3]选择了课程名为CNAME的学生的姓名
			res = rs.getString("Sname") + "\n";
			break;
		case "4"://[4]姓名为SNAME的学生所选所有课程的课程名，学时，学分和开课学期号
			res = rs.getString("Cname") + "\t" + rs.getInt("Chours") + "\t" + rs.getFloat("Credit") + "\t" + rs.getInt("Csemster") + "\n";
			break;
		case "5"://[5]查询成绩在SCORE分以上的学生姓名、课程号和成绩
			res = rs.getString("Sname") + "\t" + rs.getString("Cid") + "\t" + rs.getFloat("Score") + "\n";
			break;
		case "6"://[6]统计选课平均分低于SCORE的学生学号和成绩
			res = rs.getInt(1) + "\t" + rs.getInt(2) + "\n";
			break;
		case "7"://[7]统计姓名为SNAME的学生选修的课程数
			res = rs.getString("COUNT(sc.Cid)") + "\n";
			break;
		case "8"://[8]查询课程名为CNAME的课程的最高分、最低分和平均分
			res ="平均分为" + rs.getString("AVG(Score)") + "，最高分为" + rs.getString("MAX(Score)")
				+ "，最低分为" + rs.getString("MIN(Score)") + "\n";
			break;
		}
		return res;	
	}
	public static String search(String searchNum,String keyword) {
		String searchStr = ConnectSQL.makeString(searchNum, keyword);
		String res = "";
		ResultSet rs = null ;
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
	          "jdbc:mysql://localhost:3306/sct?characterEncoding=UTF-8","root","fanfun");
	           //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码

	      System.out.println("查询成功!");
	      Statement stmt = connect.createStatement();
	      //ResultSet rs = stmt.executeQuery("select * from stu");//stu 为表的名称
	      rs = (ResultSet) stmt.executeQuery(searchStr);//stu 为表的名称
	      System.out.println("查询结果：");
	      switch (searchNum){
			case "1"://[1]选择了课程号为CNO的学生学号
				res = "选择了课程号为" + keyword + "的学生学号为：\n";
				break;
			case "2"://[2]学号为SNO的学生选择的所有课程的课程号和成绩
				res = "学号为" + keyword + "的学生选择的所有课程的课程号和成绩为：\n课程号\t成绩\n";
				break;
			case "3"://[3]选择了课程名为CNAME的学生的姓名
				res = "选择了课程名为" + keyword + "的学生的姓名为：\n";
				break;
			case "4"://[4]姓名为SNAME的学生所选所有课程的课程名，学时，学分和开课学期号
				res = "姓名为" + keyword + "的学生所选所有课程的课程名，学时，学分和开课学期号为：\n课程名\t学时\t学分\t开课学期\n";
				break;
			case "5"://[5]查询成绩在SCORE分以上的学生姓名、课程号和成绩
				res = "成绩在" + keyword + "分以上的学生姓名、课程号和成绩为：\n姓名\t课程号\t成绩\n";
				break;
			case "6"://[6]统计选课平均分低于SCORE的学生学号和成绩
				res = "选课平均分低于" + keyword + "的学生学号和平均成绩为：\n学号\t平均成绩\n";
				break;
			case "7"://[7]统计姓名为SNAME的学生选修的课程数
				res = "姓名为" + keyword + "的学生选修的课程数为：";
				break;
			case "8"://[8]查询课程名为CNAME的课程的最高分、最低分和平均分
				res = "课程名为" + keyword + "的课程的";
				break;
			}
	     // System.out.println(rs.next());
	      while (rs.next()) {//对检索到的每一条记录，加入结果集
	    	  res += ConnectSQL.makeResult(rs, searchNum,keyword);
		  }
//	    if (rs.next())
//	    	res = "查询结果为空";
	    System.out.println(res);
	    }
	    catch (Exception e) {
	      System.out.print("查询失败!");
	      e.printStackTrace();
	    }
		return res;
	}
}