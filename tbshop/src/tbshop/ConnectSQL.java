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
		          "jdbc:mysql://localhost:3306/tbshop?characterEncoding=UTF-8&useSSL=false","root","fanfun");
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
		ResultSet rs = null ;
		Statement stmt2 = connect.createStatement();
		Boolean flag = false;
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
				str = "insert into orderList values ('" + insertInfo.get(0) +
						"','" + insertInfo.get(1) + "','" + insertInfo.get(2) + "','" + insertInfo.get(3) + "','" + insertInfo.get(4) + "',"+ insertInfo.get(5) + ");";
				res = search("orderList",5);
			case 6:
				rs = (ResultSet) stmt2.executeQuery("select goodID from good;");
				
				while(rs.next())
				{
					if ( insertInfo.get(1).equals(rs.getString("goodID")))
					{
						flag = true;
					}
				}
				if (flag == true)
				{
					str = "insert into detail values ('" + insertInfo.get(0) +
							"','" + insertInfo.get(1) + "','" + insertInfo.get(2) + "," + insertInfo.get(3) + ");";
					res = search("good",6);
				}
				else
					System.out.println("��Ʒ�����ڣ�");
				break;
		}
		if (flag == true){
			System.out.println(str);
			stmt.executeUpdate(str);
		}
		flag = false;
		update();
		return res;
	}
	
	//���Һ���
	public static String search(String str,int comboValue) throws SQLException
	{
		String res = "";
		ResultSet rs = null ;
		connectSql();//�������ݿ�
		Statement stmt = connect.createStatement();
		switch(comboValue)
		{
			case 1:
				res = "shopID\tshopAddr\t\tShopTel\n";
				break;
			case 2:
				res = "workID\tworkName\tposition\tworkTel\tsalary\n";
				break;
			case 3:
				res = "guestID\tguestTel\tguestAddr\n";
				break;
			case 4:
				res = "goodID\tgoodName\tprice\tpdDate\tamount\n";
				break;
			case 5:
				res = "orderID\tdetailID\tguestID\tworkID\tshopID\ttotalPrice\n";
				break;
			case 6:
				res = "detailID\tgoodID\tquantity\tprice\n";
				break;
		}
		rs = (ResultSet) stmt.executeQuery("select * from " + str + ";");
		while (rs.next())//��֯������
		{
			switch(comboValue)
			{
				case 1:
					res = res + rs.getString("shopID") + "\t"+ rs.getString("shopAddr") + "\t" + rs.getString("shopTel") + "\n";
					break;
				case 2:
					res = res + rs.getString("workID") + "\t"+ rs.getString("workName") + "\t" + rs.getString("position") + "\t" + rs.getString("workTel") + "\t" + rs.getString("salary")+ "\n";
					break;
				case 3:
					res = res + rs.getString("guestID") + "\t"+ rs.getString("guestTel") + "\t" + rs.getString("guestAddr")+ "\n";
					break;
				case 4:
					res = res + rs.getString("goodID") + "\t"+ rs.getString("goodName") + "\t"+ rs.getString("price") + "\t" + rs.getString("pdDate") + "\t" + rs.getString("amount")+ "\n";
					break;
				case 5:
					res = res + rs.getString("orderID") + "\t"+ rs.getString("detailID") + "\t" + rs.getString("guestID")  + "\t" + rs.getString("workID") + "\t" + rs.getString("shopID") + "\t" + rs.getString("totalPrice") + "\n";
					break;
				case 6:
					res = res + rs.getString("detailID") + "\t"+ rs.getString("goodID") + "\t" + rs.getString("quantity") + "\t" + rs.getString("price")  + "\n";
					break;
			}
		}
		return res;
	}
	
	//ɾ������
	public static void delete(String id,int comboValue) throws SQLException
	{
		connectSql();//�������ݿ�
		Statement stmt1 = connect.createStatement();
		Statement stmt2 = connect.createStatement();
		ResultSet rs = null ;
		switch (comboValue)
		{
			case 1:
				//ɾ���̵�
				 stmt1.executeUpdate("delete from shop where shopID='" + id + "';");
				 //ɾ���̵��Ӧ�Ķ�����ϸ
				 rs = (ResultSet) stmt2.executeQuery("select detailID from orderList where shopID='" + id + "';");
				 while(rs.next())
					 stmt1.executeUpdate("delete from detail where detailID='" + rs.getString("detailID") + "';");
				 //ɾ���̵��Ӧ�Ķ���
				 stmt1.executeUpdate("delete from orderList where shopID='" + id + "';");
				 break;
			case 2:
				//ɾ��Ա��
				 stmt1.executeUpdate("delete from worker where workID='" + id + "';");
				 //Ա����Ӧ�Ķ����б���ɾ��
//				 stmt1.executeUpdate("delete from orderList where workID='" + id + "';");
				 break;
			case 3:
				//ɾ���˿�
				 stmt1.executeUpdate("delete from guest where guestID='" + id + "';");
				 //ɾ���˿Ͷ�Ӧ�Ķ�����ϸ
				 rs = (ResultSet) stmt2.executeQuery("select detailID from orderList where guestID='" + id + "';");
				 while(rs.next())
					 stmt1.executeUpdate("delete from detail where detailID='" + rs.getString("detailID") + "';");
				 //ɾ���˿Ͷ�Ӧ�Ķ���
				 stmt1.executeUpdate("delete from orderList where guestID='" + id + "';");
				 break;
			case 4:
				//ɾ����Ʒ
				 stmt1.executeUpdate("delete from good where goodID='" + id + "';");
				 //ɾ����Ʒ��Ӧ�Ķ�����ϸ
				 rs = (ResultSet) stmt2.executeQuery("select detailID from orderList where goodID='" + id + "';");
				 while(rs.next())
					 stmt1.executeUpdate("delete from detail where detailID='" + rs.getString("detailID") + "';");
				 //��Ʒ��Ӧ�Ķ�������ɾ��
//				 stmt1.executeUpdate("delete from orderList where goodID='" + id + "';");
				 break;
			case 5:
				 //ɾ��������Ӧ����ϸ
				 rs = (ResultSet) stmt2.executeQuery("select detailID from orderList where orderID='" + id + "';");
				 while(rs.next())
					 stmt1.executeUpdate("delete from detail where detailID='" + rs.getString("detailID") + "';");
				//ɾ������
				 stmt1.executeUpdate("delete from orderList where orderID='" + id + "';");
				 break;
			case 6:
				//ɾ��������ϸ
				 stmt1.executeUpdate("delete from detail where detailID='" + id + "';");
				 //�Ӷ�����ɾ����ϸ��
				 stmt1.executeUpdate("delete from orderList where detailID='" + id + "';");
				 break;
		}
		update();
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
	
	//�༭����
	public static void edit(ArrayList<String> editInfo,int comboValue,String id) throws SQLException
	{		
		connectSql();//�������ݿ�
		Statement stmt = connect.createStatement();		
		String str = "";
		str = dealEditCommand(editInfo,comboValue,id);
		stmt.executeUpdate(str);
		update();
	}
	
	//��������༭������ָ��
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
			String id1,id2;
			System.out.println(id);
			id1  = id.substring(0, 9);//detailID
			id2 = id.substring(10);//goodID
			str += " detail set quantity='" + editInfo.get(0) + "'";
			str = str + " where detailID = '" + id1 + "' and goodID='" + id2 + "';";
			break;
		}
		System.out.println(str);
		return str;
	}
	
	//�������ݿ����ݣ����¼��㶩���ܼ�,������Ʒ����
	private static void update() throws SQLException
	{
		connectSql();//�������ݿ�
		ResultSet rs0 = null ;
		ResultSet rs1 = null ;
		Statement stmt0 = connect.createStatement();		//���ڲ�ѯ
		Statement stmt1 = connect.createStatement();		//���ڲ�ѯ
		Statement stmt2 = connect.createStatement();		//���ڸ���
		rs1 = (ResultSet) stmt1.executeQuery("select goodID,price from good;");
		while(rs1.next())//������Ʒ����
			stmt2.executeUpdate("update detail set price=" + rs1.getString("price") + "where goodID='" + rs1.getString("goodID") + "';");
		
		//���㶩���ܼ�-------------------------------------------------------------------------------------------ÿ�������ܼ�һ��
		float sum  = 0;
		rs0 = (ResultSet) stmt0.executeQuery("select detailID,orderID from orderList;");
		while(rs0.next())//��ÿһ������
		{
			sum = 0;
			rs1 = (ResultSet) stmt1.executeQuery("select quantity,price from detail where detailID='" + rs0.getString("detailID") + "';");
			while (rs1.next()){//��ÿ����������ϸ
				int quantity = rs1.getInt("quantity");
				float price = rs1.getFloat("price");
				sum += quantity * price;
			}
			stmt2.executeUpdate("update orderList set totalPrice=" + sum + "where orderID='" + rs0.getString("orderID") + "';");
		}
	}
}