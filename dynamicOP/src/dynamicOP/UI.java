package dynamicOP;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

public  class UI implements ItemListener{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField tResult;
	private JTable table;
	
	private String searchStr = "select * From stu where";//搜索语句
	public void setSearchStr(String searchStr)
	{
		this.searchStr = searchStr;
	}
	
	public String getSearchStr()
	{
		return this.searchStr;
	}
		
	public static void getUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	//选择条件组件定义
	JCheckBox cbNum = new JCheckBox("学号");
	JTextPane tNum = new JTextPane();
	JCheckBox cbClass = new JCheckBox("班级");
	JTextPane tClass = new JTextPane();
	JCheckBox cbName = new JCheckBox("姓名");
	JTextPane tName = new JTextPane();
	JCheckBox cbF = new JCheckBox("系别");
	JTextPane tF = new JTextPane();
	JCheckBox cbAge = new JCheckBox("年龄");
	JTextPane tAgeL = new JTextPane();
	JTextPane tAgeH = new JTextPane();
	JCheckBox cbAddr = new JCheckBox("地址");
	JTextPane tAddr = new JTextPane();
	JCheckBox cbSex = new JCheckBox("性别");
	JTextPane tSex = new JTextPane();
	JButton bSearch = new JButton("查询");
	JButton bReturn = new JButton("返回");
	//创建表值  
	String[][] data = {{" "," "," "," "," "," "," "}};
	String[] name = { "学号","班级","姓名","系别","年龄","地址","性别" };
	DefaultTableModel model = new DefaultTableModel(data,name);
	
	//生成图形用户界面
	private void initialize() {
		//定义框架
		frame = new JFrame("学生信息查询系统 @FanfanWang");
		frame.setBounds(100, 100, 730, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//复选框和文本框
		cbNum.setBounds(10, 8, 75, 23);
		frame.getContentPane().add(cbNum);
		cbNum.addItemListener(this);
			
		tNum.setBounds(91, 8, 97, 21);
		frame.getContentPane().add(tNum);
		
		cbClass.setBounds(332, 8, 76, 23);
		frame.getContentPane().add(cbClass);
		cbClass.addItemListener(this);
		
		tClass.setBounds(414, 8, 97, 21);
		frame.getContentPane().add(tClass);
		
		cbName.setBounds(10, 33, 75, 23);
		frame.getContentPane().add(cbName);
		cbName.addItemListener(this);
		
		tName.setBounds(91, 35, 97, 21);
		frame.getContentPane().add(tName);
		
		cbF.setBounds(332, 33, 76, 23);
		frame.getContentPane().add(cbF);
		cbF.addItemListener(this);
		
		tF.setBounds(414, 33, 97, 21);
		frame.getContentPane().add(tF);
		
		cbAge.setBounds(10, 62, 75, 23);
		frame.getContentPane().add(cbAge);
		cbAge.addItemListener(this);
		
		textField = new JTextField();
		textField.setText("自");
		textField.setBounds(101, 63, 20, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		tAgeL.setBounds(143, 62, 29, 21);
		frame.getContentPane().add(tAgeL);
		
		textField_1 = new JTextField();
		textField_1.setText("到");
		textField_1.setColumns(10);
		textField_1.setBounds(188, 63, 20, 21);
		frame.getContentPane().add(textField_1);
		
		tAgeH.setBounds(218, 64, 29, 21);
		frame.getContentPane().add(tAgeH);
		
		cbAddr.setBounds(332, 62, 76, 23);
		frame.getContentPane().add(cbAddr);
		cbAddr.addItemListener(this);
		
		tAddr.setBounds(414, 62, 182, 21);
		frame.getContentPane().add(tAddr);
		
		cbSex.setBounds(10, 95, 75, 23);
		frame.getContentPane().add(cbSex);
		cbSex.addItemListener(this);
		
		tSex.setBounds(91, 97, 49, 21);
		frame.getContentPane().add(tSex);
		
		//搜索按钮
		bSearch.setBounds(621, 8, 72, 110);
		frame.getContentPane().add(bSearch);
		bSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                methodBSearch();
            }
            //搜索按钮事件
            private void methodBSearch() {
				// TODO Auto-generated method stub
            	if (searchStr.equals("select * From stu where"))//如果没有复选框被选中
            		searchStr = "select * From stu";
            	tResult.setText(searchStr);
				System.out.println("UI:"+searchStr+"\n");
				ArrayList <Student> student = new ArrayList <Student> ();
				student = ConnectSQL.search(searchStr);	//进行数据库检索			
				Student st = new Student();
				data = st.getData(student);//获取表格内容数据
				System.out.println("UI:"+data+"\n");
				model.setDataVector(data, name);//刷新表格内容
			}
		});

        JScrollPane jsp = new JScrollPane();
        
        //结果表格定义
        table = new JTable(model);
        //文字内容居中
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, tcr);
        
		jsp.setBounds(10, 210, 683, 208);
		jsp.setViewportView(table);
		frame.getContentPane().add(jsp);
		
		//生成的检索语句
		tResult = new JTextField();
		tResult.setBounds(10, 131, 683, 69);
		frame.getContentPane().add(tResult);
		tResult.setColumns(10);
		
		//返回按钮
		bReturn.setBounds(600, 428, 93, 23);
		frame.getContentPane().add(bReturn);
		bReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                methodBReturn();
            }
            //返回按钮事件
            private void methodBReturn() {
				System.out.println("这里要清除所有状态了");
				cbNum.setSelected((false));
				cbClass.setSelected((false));
				cbName.setSelected((false));
				cbF.setSelected((false));
				cbAge.setSelected((false));
				cbAddr.setSelected((false));
				cbSex.setSelected((false));
				tNum.setText("");
				tClass.setText("");
				tName.setText("");
				tF.setText("");
				tAgeH.setText("");
				tAgeL.setText("");
				tAddr.setText("");
				tSex.setText("");
				tResult.setText("");
				data = null;
				model.setDataVector(data, name);
				searchStr = "select * From stu where";
				flag = true;
			}
		});
	}
	
	private boolean flag = true;
	
	//复选框监听事件，生成检索语句
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange()==ItemEvent.SELECTED)
		{
			if (e.getSource() == cbNum)
			{
				if (!flag)
					searchStr = searchStr + " and num = " + tNum.getText();
				else
				{
					searchStr = searchStr + " num = " + tNum.getText();
					flag = false;
				}
			}
			else if(e.getSource() == cbClass)
			{
				if (!flag)
					searchStr = searchStr + " and class = " + tClass.getText();
				else
				{
					searchStr = searchStr + " class = " + tClass.getText();
					flag = false;
				}
			}
			else if(e.getSource() == cbName)
			{
				if (!flag)
					searchStr = searchStr + " and name = " + tName.getText();
				else
				{
					searchStr = searchStr + " name = " + tName.getText();
					flag = false;
				}
			}
			else if(e.getSource() == cbF)
			{
				if (!flag)
					searchStr = searchStr + " and f = " + tF.getText();
				else
				{
					searchStr = searchStr + " f = " + tF.getText();
					flag = false;
				}
			}
			else if(e.getSource() == cbAge )
			{
				if (!flag)
				{
					if (!(null == tAgeL.getText()))
						searchStr = searchStr + " and age >= " + tAgeL.getText();
					if (!(null == tAgeH.getText()))
						searchStr = searchStr + " and age <= " + tAgeH.getText();
				}
				else
				{
					if (!(null == tAgeL.getText()))
						searchStr = searchStr + " and age >= " + tAgeL.getText();
					if (!(null == tAgeH.getText()))
						searchStr = searchStr + " and age <= " + tAgeH.getText();
					flag = false;
				}
			}
			else if(e.getSource() == cbAddr)
			{
				if (!flag)
					searchStr = searchStr + " and addr = " + tAddr.getText();
				else
				{
					searchStr = searchStr + " addr = " + tAddr.getText();
					flag = false;
				}
			}
			else if(e.getSource() == cbSex)
			{
				if (!flag)
					searchStr = searchStr + " and sex = " + tSex.getText();
				else
				{
					searchStr = searchStr + " sex = " + tSex.getText();
					flag = false;
				}
			}				
		}
	}
}
