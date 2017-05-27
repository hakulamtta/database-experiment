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
				res = search("shop",1);
				break;
			case 2:
				str = "insert into worker values ('" + insertInfo.get(0) +
						"','" + insertInfo.get(1) + "','" + insertInfo.get(2) + "','" + insertInfo.get(3) + "'," + insertInfo.get(4) + ");";
				res = search("worker",2);
				break;
			case 3:
				str = "insert into guest values ('" + insertInfo.get(0) +
						"','" + insertInfo.get(1) + "','" + insertInfo.get(2) + "');";
				res = search("guest",3);
				break;
			case 4:
				str = "insert into good values ('" + insertInfo.get(0) +
						"','" + insertInfo.get(1) + "'," + insertInfo.get(2) + ",'" + insertInfo.get(3) + "'," + insertInfo.get(4) + ");";
				res = search("good",4);
				break;
			case 5:
				str = "insert into worker values ('" + insertInfo.get(0) +
						"','" + insertInfo.get(1) + "','" + insertInfo.get(2) + "','" + insertInfo.get(3) + "','" + insertInfo.get(4) + "',"+ insertInfo.get(5) + "');";
				res = search("worker",5);
			case 6:
				str = "insert into good values ('" + insertInfo.get(0) +
						"','" + insertInfo.get(1) + "','" + insertInfo.get(2) + "," + insertInfo.get(3) + ");";
				res = search("good",6);
				break;
		}
		System.out.println(str);
		stmt.executeUpdate(str);
		return res;
	}
	
	public static String search(String str,int comboValue) throws SQLException
	{
		String res = "";
		ResultSet rs = null ;
		connectSql();//�������ݿ�
		Statement stmt = connect.createStatement();
		rs = (ResultSet) stmt.executeQuery("select * from " + str + ";");
		while (rs.next())
		{
			switch(comboValue)
			{
				case 1:
//					res = "shopID\tshopAddr\t\tShopTel\n";
					res = res + rs.getString("shopID") + "\t"+ rs.getString("shopAddr") + "\t" + rs.getString("shopTel") + "\n";
					break;
				case 2:
//					res = "workID\tworkName\tposition\tworkTel\tsalary\n";
					res = res + rs.getString("workID") + "\t"+ rs.getString("workName") + "\t" + rs.getString("position") + "\t" + rs.getString("workTel") + "\t" + rs.getString("salary")+ "\n";
					break;
				case 3:
//					res = "guestID\tguestTel\tguestAddr\n";
					res = res + rs.getString("guestID") + "\t"+ rs.getString("guestTel") + "\t" + rs.getString("guestAddr")+ "\n";
					break;
				case 4:
//					res = "goodID\tgoodName\tprice\tpdDate\tamount\n";
					res = res + rs.getString("goodID") + "\t"+ rs.getString("goodName") + "\t"+ rs.getString("price") + "\t" + rs.getString("pdDate") + rs.getString("amount")+ "\n";
					break;
				case 5:
//					res = "orderID\tdetailID\tguestID\tworkID\tshopID\ttotalPrice\n";
					res = res + rs.getString("orderID") + "\t"+ rs.getString("detailID") + "\t" + rs.getString("guestID")  + "\t" + rs.getString("workID") + "\t" + rs.getString("shopID") + "\t" + rs.getString("totalPrice") + "\n";
					break;
				case 6:
//					res = "detailID\tgoodID\tquantity\tprice\n";
					res = res + rs.getString("detailID") + "\t"+ rs.getString("goodID") + "\t" + rs.getString("quantity") + "\t" + rs.getString("price")  + "\n";
					break;
			}
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