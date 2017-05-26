package embSQL;

import java.sql.*;

public class ConnectSQL {
	public static String makeString(String searchNum,String keyword){
		String searchStr = null;
		System.out.println(searchNum);
		System.out.println(keyword);
		switch (searchNum){
		case "1"://[1]ѡ���˿γ̺�ΪCNO��ѧ��ѧ��
			searchStr = "select * from sc where Cid=" + keyword + ";";
//			searchStr = "select Sid from sc where Cid=" + keyword + ";";
			break;
		case "2"://[2]ѧ��ΪSNO��ѧ��ѡ������пγ̵Ŀγ̺źͳɼ�
			searchStr = "select * from sc where Sid=" + keyword + ";";
//			searchStr = "select Cid,Score from sc where Sid=" + keyword + ";";
			break;
		case "3"://[3]ѡ���˿γ���ΪCNAME��ѧ��������
			searchStr = "select * from student,course,sc where student.Sid=sc.Sid and sc.Cid=course.Cid and course.Cname=\"" + keyword + "\";";
//			searchStr = "select Sname from student,course,sc where student.Sid=sc.Sid and sc.Cid=course.Cid and course.Cname='" + keyword + "';";
			break;
		case "4"://[4]����ΪSNAME��ѧ����ѡ���пγ̵Ŀγ�����ѧʱ��ѧ�ֺͿ�ѧ�ں�
			searchStr = "select Cname,Chours,Credit,Csemster from student,sc,course where student.Sid=sc.Sid and sc.Cid=course.Cid and student.Sname='" + keyword + "';";
			break;
		case "5"://[5]��ѯ�ɼ���SCORE�����ϵ�ѧ���������γ̺źͳɼ�
			searchStr = "select Sname,Cid,Score from student,sc where student.Sid=sc.Sid and Score>" + keyword + ";";
			break;
		case "6"://[6]ͳ��ѡ��ƽ���ֵ���SCORE��ѧ��ѧ�źͳɼ�
			searchStr = "select Sid, AVG(Score) from sc group by Sid Having AVG(Score)<" + keyword + ";";
			break;
		case "7"://[7]ͳ������ΪSNAME��ѧ��ѡ�޵Ŀγ���
			searchStr = "select COUNT(sc.Cid) from sc,student,course where student.Sid=sc.Sid and sc.Cid=course.Cid and Sname='" + keyword + "';";
			break;
		case "8"://[8]��ѯ�γ���ΪCNAME�Ŀγ̵���߷֡���ͷֺ�ƽ����
			searchStr = "select sc.Cid, course.Cname, AVG(Score), MAX(Score), MIN(Score) from sc, course where sc.Cid=course.Cid and Cname='" + keyword + "' group by Cid;";
			break;
		}
		System.out.println(searchStr);
		return searchStr;
		
	}
	
	public static String makeResult(ResultSet rs,String searchNum,String keyword) throws SQLException{
		String res = null;
		switch (searchNum){
		case "1"://[1]ѡ���˿γ̺�ΪCNO��ѧ��ѧ��
			res = rs.getString("Sid") + "\n";
			break;
		case "2"://[2]ѧ��ΪSNO��ѧ��ѡ������пγ̵Ŀγ̺źͳɼ�
			res = rs.getString("Cid") + "\t" + rs.getFloat("Score") + "\n";
			break;
		case "3"://[3]ѡ���˿γ���ΪCNAME��ѧ��������
			res = rs.getString("Sname") + "\n";
			break;
		case "4"://[4]����ΪSNAME��ѧ����ѡ���пγ̵Ŀγ�����ѧʱ��ѧ�ֺͿ���ѧ�ں�
			res = rs.getString("Cname") + "\t" + rs.getInt("Chours") + "\t" + rs.getFloat("Credit") + "\t" + rs.getInt("Csemster") + "\n";
			break;
		case "5"://[5]��ѯ�ɼ���SCORE�����ϵ�ѧ���������γ̺źͳɼ�
			res = rs.getString("Sname") + "\t" + rs.getString("Cid") + "\t" + rs.getFloat("Score") + "\n";
			break;
		case "6"://[6]ͳ��ѡ��ƽ���ֵ���SCORE��ѧ��ѧ�źͳɼ�
			res = rs.getInt(1) + "\t" + rs.getInt(2) + "\n";
			break;
		case "7"://[7]ͳ������ΪSNAME��ѧ��ѡ�޵Ŀγ���
			res = rs.getString("COUNT(sc.Cid)") + "\n";
			break;
		case "8"://[8]��ѯ�γ���ΪCNAME�Ŀγ̵���߷֡���ͷֺ�ƽ����
			res ="ƽ����Ϊ" + rs.getString("AVG(Score)") + "����߷�Ϊ" + rs.getString("MAX(Score)")
				+ "����ͷ�Ϊ" + rs.getString("MIN(Score)") + "\n";
			break;
		}
		return res;	
	}
	public static String search(String searchNum,String keyword) {
		String searchStr = ConnectSQL.makeString(searchNum, keyword);
		String res = "";
		ResultSet rs = null ;
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
	          "jdbc:mysql://localhost:3306/sct?characterEncoding=UTF-8","root","fanfun");
	           //����URLΪ   jdbc:mysql//��������ַ/���ݿ���  �������2�������ֱ��ǵ�½�û���������

	      System.out.println("��ѯ�ɹ�!");
	      Statement stmt = connect.createStatement();
	      //ResultSet rs = stmt.executeQuery("select * from stu");//stu Ϊ�������
	      rs = (ResultSet) stmt.executeQuery(searchStr);//stu Ϊ�������
	      System.out.println("��ѯ�����");
	      switch (searchNum){
			case "1"://[1]ѡ���˿γ̺�ΪCNO��ѧ��ѧ��
				res = "ѡ���˿γ̺�Ϊ" + keyword + "��ѧ��ѧ��Ϊ��\n";
				break;
			case "2"://[2]ѧ��ΪSNO��ѧ��ѡ������пγ̵Ŀγ̺źͳɼ�
				res = "ѧ��Ϊ" + keyword + "��ѧ��ѡ������пγ̵Ŀγ̺źͳɼ�Ϊ��\n�γ̺�\t�ɼ�\n";
				break;
			case "3"://[3]ѡ���˿γ���ΪCNAME��ѧ��������
				res = "ѡ���˿γ���Ϊ" + keyword + "��ѧ��������Ϊ��\n";
				break;
			case "4"://[4]����ΪSNAME��ѧ����ѡ���пγ̵Ŀγ�����ѧʱ��ѧ�ֺͿ���ѧ�ں�
				res = "����Ϊ" + keyword + "��ѧ����ѡ���пγ̵Ŀγ�����ѧʱ��ѧ�ֺͿ���ѧ�ں�Ϊ��\n�γ���\tѧʱ\tѧ��\t����ѧ��\n";
				break;
			case "5"://[5]��ѯ�ɼ���SCORE�����ϵ�ѧ���������γ̺źͳɼ�
				res = "�ɼ���" + keyword + "�����ϵ�ѧ���������γ̺źͳɼ�Ϊ��\n����\t�γ̺�\t�ɼ�\n";
				break;
			case "6"://[6]ͳ��ѡ��ƽ���ֵ���SCORE��ѧ��ѧ�źͳɼ�
				res = "ѡ��ƽ���ֵ���" + keyword + "��ѧ��ѧ�ź�ƽ���ɼ�Ϊ��\nѧ��\tƽ���ɼ�\n";
				break;
			case "7"://[7]ͳ������ΪSNAME��ѧ��ѡ�޵Ŀγ���
				res = "����Ϊ" + keyword + "��ѧ��ѡ�޵Ŀγ���Ϊ��";
				break;
			case "8"://[8]��ѯ�γ���ΪCNAME�Ŀγ̵���߷֡���ͷֺ�ƽ����
				res = "�γ���Ϊ" + keyword + "�Ŀγ̵�";
				break;
			}
	     // System.out.println(rs.next());
	      while (rs.next()) {//�Լ�������ÿһ����¼����������
	    	  res += ConnectSQL.makeResult(rs, searchNum,keyword);
		  }
//	    if (rs.next())
//	    	res = "��ѯ���Ϊ��";
	    System.out.println(res);
	    }
	    catch (Exception e) {
	      System.out.print("��ѯʧ��!");
	      e.printStackTrace();
	    }
		return res;
	}
}