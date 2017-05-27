package tbshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import tbshop.ConnectSQL;

public class ConnectSQL {
	private static Connection connect;
	//�������ݿ�
	public static void connectSql()
	{
		try {
		      Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
		      System.out.println("Success loading Mysql Driver!");
		    }
		    catch (Exception e) {
		      System.out.print("Error loading Mysql Driver!");
		      e.printStackTrace();
		    }
		    try {
		      connect = DriverManager.getConnection(
		          "jdbc:mysql://localhost:3306/tbshop?characterEncoding=UTF-8","root","fanfun");
		           //����URLΪ   jdbc:mysql//��������ַ/���ݿ���  �������2�������ֱ��ǵ�½�û���������
		    }catch (Exception e) {
			      System.out.print("�������ݿ�ʧ��!");
			      e.printStackTrace();
			    }

	}
	
	//���뺯��
	public static String insert(ArrayList<String> insertInfo,int comboValue) throws SQLException
	{
		String str = "";
		String res = "";
		connectSql();//�������ݿ�
		Statement stmt = connect.createStatement();
		switch (comboValue)
		{
			case 1:
				str = "insert into shop values ('" + insertInfo.get(0) +
						"','" + insertInfo.get(1) + "','" + insertInfo.get(2) + "');";
				res = search("shop");
				break;
			case 2:
				str = "insert into worker values ('" + insertInfo.get(0) +
						"','" + insertInfo.get(1) + "','" + insertInfo.get(2) + "','" + insertInfo.get(3) + "'," + insertInfo.get(4) + ");";
				res = search("worker");
				break;
			case 3:
				str = "insert into guest values ('" + insertInfo.get(0) +
						"','" + insertInfo.get(1) + "','" + insertInfo.get(2) + "');";
				res = search("guest");
				break;
			case 4:
				str = "insert into good values ('" + insertInfo.get(0) +
						"','" + insertInfo.get(1) + "'," + insertInfo.get(2) + ",'" + insertInfo.get(3) + "'," + insertInfo.get(4) + ");";
				res = search("good");
				break;
			case 5:
				str = "insert into worker values ('" + insertInfo.get(0) +
						"','" + insertInfo.get(1) + "','" + insertInfo.get(2) + "','" + insertInfo.get(3) + "','" + insertInfo.get(4) + "',"+ insertInfo.get(5) + "');";
				res = search("worker");
			case 6:
				str = "insert into good values ('" + insertInfo.get(0) +
						"','" + insertInfo.get(1) + "','" + insertInfo.get(2) + "," + insertInfo.get(3) + ");";
				res = search("good");
				break;
		}
		System.out.println(str);
		stmt.executeUpdate(str);
		return res;
	}
	
	public static String search(String str) throws SQLException
	{
		String res = "";
		ResultSet rs = null ;
		Statement stmt = connect.createStatement();
		rs = (ResultSet) stmt.executeQuery("select * from " + str + ";");
		while (rs.next())
		{
			res = res + rs.getString("shopID") + " "+ rs.getString("shopAddr") + " " + rs.getString("shopTel") + "\n";
		}
		return res;
	}
	
	//��ѯ����
	public static String search() throws SQLException {
		String res = "";
		ResultSet rs = null ;
		connectSql();//�������ݿ�
		Statement stmt = connect.createStatement();
		//ResultSet rs = stmt.executeQuery("select * from stu");//stu Ϊ�������
		rs = (ResultSet) stmt.executeQuery("select * from good;");//stu Ϊ�������
		System.out.println("��ѯ�����");
		// System.out.println(rs.next());
		while (rs.next()) {//�Լ�������ÿһ����¼����������
		//res += ConnectSQL.makeResult(rs, searchNum,keyword);
		}
System.out.println(res);
return res;
	}
}