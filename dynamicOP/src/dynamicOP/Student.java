package dynamicOP;

import java.util.ArrayList;

public class Student {
	public String Num;
	public String Class;
	public String Name;
	public String F;
	public String Age;
	public String Addr;
	public String Sex;
	
	//���������ת��Ϊ�������ֵ
	public String[][] getData(ArrayList<Student> student) {
		String [][] data = new String [100][];// ����
		for (int i=0;i<student.size();i++)
		{
			String[] st = {student.get(i).Num,student.get(i).Class,student.get(i).Name,
					student.get(i).F,student.get(i).Age,student.get(i).Addr,student.get(i).Sex};
			data[i] = st;
		}
		return data;
	}
}
