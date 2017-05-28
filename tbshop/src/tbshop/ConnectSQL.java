package tbshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import tbshop.ConnectSQL;

public class ConnectSQL {
	private static Connection connect;
	//连接数据库
	public static void connectSql()
	{
		try {
		      Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
		      System.out.println("Success loading Mysql Driver!");
		    }
		    catch (Exception e) {
		      System.out.print("Error loading Mysql Driver!");
		      e.printStackTrace();
		    }
		    try {
		      connect = DriverManager.getConnection(
		          "jdbc:mysql://localhost:3306/tbshop?characterEncoding=UTF-8&useSSL=false","root","fanfun");
		           //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		    }catch (Exception e) {
			      System.out.print("连接数据库失败!");
			      e.printStackTrace();
			    }

	}
	
	//插入函数
	public static String insert(ArrayList<String> insertInfo,int comboValue) throws SQLException
	{
		String str = "";
		String res = "";
		connectSql();//连接数据库
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
		connectSql();//连接数据库
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
	
	//删除函数
	public static void delete(String id,int comboValue) throws SQLException
	{
		connectSql();//连接数据库
		Statement stmt = connect.createStatement();
		switch (comboValue)
		{
			case 1:
				 stmt.executeUpdate("delete from shop where shopID='" + id + "';");
				 stmt.executeUpdate("delete from orderList where shopID='" + id + "';");
				 break;
			case 2:
				 stmt.executeUpdate("delete from worker where workID='" + id + "';");
				 stmt.executeUpdate("delete from orderList where workID='" + id + "';");
				 break;
			case 3:
				 stmt.executeUpdate("delete from guest where guestID='" + id + "';");
				 stmt.executeUpdate("delete from orderList where guestID='" + id + "';");
				 break;
			case 4:
				 stmt.executeUpdate("delete from good where goodID='" + id + "';");
				 stmt.executeUpdate("delete from orderList where goodID='" + id + "';");
				 break;
			case 5:
				 stmt.executeUpdate("delete from orderList where orderID='" + id + "';");
				 stmt.executeUpdate("delete from detail where orderID='" + id + "';");
				 break;
			case 6:
				 stmt.executeUpdate("delete from detail where detailID='" + id + "';");
				 stmt.executeUpdate("delete from orderList where detailID='" + id + "';");
				 break;
		}
	}
	
	//查询函数
	public static String search() throws SQLException {
		String res = "";
		ResultSet rs = null ;
		connectSql();//连接数据库
		Statement stmt = connect.createStatement();
		//ResultSet rs = stmt.executeQuery("select * from stu");//stu 为表的名称
		rs = (ResultSet) stmt.executeQuery("select * from good;");//stu 为表的名称
		System.out.println("查询结果：");
		// System.out.println(rs.next());
		while (rs.next()) {//对检索到的每一条记录，加入结果集
		//res += ConnectSQL.makeResult(rs, searchNum,keyword);
		}
		System.out.println(res);
		return res;
	}
	
	//编辑函数
	public static void edit(ArrayList<String> editInfo,int comboValue,String id) throws SQLException
	{		
		connectSql();//连接数据库
		Statement stmt = connect.createStatement();		
		
		String str = "";
		str = dealEditCommand(editInfo,comboValue,id);
		stmt.executeUpdate(str);
	}
	
	private static String dealEditCommand(ArrayList<String> editInfo,int comboValue,String id)
	{
		String str = "update";
		switch (comboValue)
		{
		case 1:
			str += " shop set";
			if (!editInfo.get(0).equals(" "))
				str = str + " shopAddr='" + editInfo.get(0) + "'";
			if (!editInfo.get(1).equals(" "))
				str = str + ",shopTel='" + editInfo.get(1) + "'";
			str = str + " where shopID = '" + id + "';";
			break;
		case 2:
			str += " worker set ";
			if (!editInfo.get(0).equals(" "))
				str = str + "workName='" + editInfo.get(0) + "'";
			if (!editInfo.get(1).equals(" "))
				str = str + ",position='" + editInfo.get(1) + "'";
			if (!editInfo.get(2).equals(" "))
				str = str + ",workTel='" + editInfo.get(2) + "'";
			if (!editInfo.get(3).equals(" "))
				str = str + ",salary=" + editInfo.get(3) ;
			str += " where workID='" + id + "';";
			break;
		case 3:
			str += " guest set ";
			if (!editInfo.get(0).equals(" "))
				str = str + " guestTel='" + editInfo.get(0) + "'";
			if (!editInfo.get(1).equals(" "))
				str = str + ",guestAddr='" + editInfo.get(1) + "'";
			str = str + " where guestID = '" + id + "';";
			break;
		case 4:
			str += " good set ";
			if (!editInfo.get(0).equals(" "))
				str = str + "goodName='" + editInfo.get(0) + "'";
			if (!editInfo.get(1).equals(" "))
				str = str + ",price=" + editInfo.get(1);
			if (!editInfo.get(2).equals(" "))
				str = str + ",pdDate='" + editInfo.get(2) + "'";
			if (!editInfo.get(3).equals(" "))
				str = str + ",amount=" + editInfo.get(3) ;
			str += " where goodID='" + id + "';";
			break;
		case 5:
			str += " orderList set ";
			if (!editInfo.get(0).equals(" "))
				str = str + "detailID='" + editInfo.get(0) + "'";
			if (!editInfo.get(1).equals(" "))
				str = str + ",guestID='" + editInfo.get(1) + "'";
			if (!editInfo.get(2).equals(" "))
				str = str + ",workID='" + editInfo.get(2) + "'";
			if (!editInfo.get(3).equals(" "))
				str = str + ",shopID='" + editInfo.get(3) + "'" ;
			if (!editInfo.get(4).equals(" "))
				str = str + ",totalPrice=" + editInfo.get(4) ;
			str += " where orderID='" + id + "';";
			break;
		case 6:
			str += " detail set ";
			if (!editInfo.get(0).equals(" "))
				str = str + " goodID='" + editInfo.get(0) + "'";
			if (!editInfo.get(1).equals(" "))
				str = str + ",quantity='" + editInfo.get(1) + "'";
			str = str + " where detailID = '" + id + "';";
			break;
		}
		System.out.println(str);
		return str;
	}
}