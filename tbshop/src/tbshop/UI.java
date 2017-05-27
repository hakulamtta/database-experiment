package tbshop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class UI {
	private static String comboStr = "";
	private static int comboValue = 0;
	ArrayList<String> insertInfo = new ArrayList<String>();
	private String  info = "";
	private static JFrame frame;
	private static JLabel jl1 = new JLabel("按序输入shopID,shopAddr,shopTel");
	private static JLabel jl2 = new JLabel("按序输入workID,workName,position,workTel,salary");
	private static JLabel jl3 = new JLabel("按序输入guestID,guestTel,guestAddr");
	private static JLabel jl4 = new JLabel("按序输入goodID,goodName,price,pdDate,amount");
	private static JLabel jl5 = new JLabel("按序输入orderID,detailID,guestID,workID,shopID,totalPrice");
	private static JLabel jl6 = new JLabel("按序输入detailID,goodID,quantity,price");

	private JTextField jf = new JTextField();//插入数据输入区域
	private JTextArea ja = new JTextArea();//插入结果显示区域
	
	//获取要插入的信息，结果按序存在ArrayList
	public ArrayList<String> getInfo(String info)
	{
		
		String tmp = "";
		info += "#";//方便处理最后一组赋值问题
		System.out.println("在函数内部："+info);
		int i = 0;
		while(i < info.length())
		{
			if (info.charAt(i) != ' ' && info.charAt(i) != '#')
			{
				tmp = tmp + info.charAt(i);
				i++;
			}
			else
			{
				insertInfo.add(tmp);
				tmp = "";
				i++;
			}
		}
		insertInfo.add(tmp);
		System.out.println("分割语句测试：");
		for (i=0;i<3;i++)
			System.out.println(insertInfo.get(i));
		return insertInfo;
	}
	
	//将下拉菜单选中值等价于整数
	private static int deal(String comboStr)
	{
		if (comboStr.equals("shop"))
			comboValue = 1;
		else if(comboStr.equals("worker"))
			comboValue = 2;
		else if(comboStr.equals("guest"))
			comboValue = 3;
		else if(comboStr.equals("good"))
			comboValue = 4;
		else if(comboStr.equals("order"))
			comboValue = 5;
		else if(comboStr.equals("detial"))
			comboValue = 6;
		else
			comboValue = 0;
		return comboValue;
	}
	
	//清除其他无关的提示文字
	private static void clear()
	{
		frame.getContentPane().remove(jl1);
		frame.getContentPane().remove(jl2);
		frame.getContentPane().remove(jl3);
		frame.getContentPane().remove(jl4);
		frame.getContentPane().remove(jl5);
		frame.getContentPane().remove(jl6);
	}
	/**
	 * Launch the application.
	 */
	public static void run() {
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
	private void initialize() {
		frame = new JFrame("网店管理系统");
		frame.setBounds(100, 100, 450, 503);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("选择待操作的表：");
		label1.setBounds(20, 10, 104, 15);
		frame.getContentPane().add(label1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(129, 7, 67, 21);
		comboBox.addItem("shop");  
        comboBox.addItem("worker");  
        comboBox.addItem("guest"); 
        comboBox.addItem("good");  
        comboBox.addItem("order");  
        comboBox.addItem("detail"); 
        comboBox.setSelectedItem(null);
        frame.getContentPane().add(comboBox);
        
        JButton bInsert = new JButton("插入");
        bInsert.setBounds(318, 6, 93, 23);
        frame.getContentPane().add(bInsert);
        
        JButton bRefresh = new JButton("刷新");
        bRefresh.setBounds(318, 34, 93, 23);
        frame.getContentPane().add(bRefresh);
		
		ja.setBounds(20, 100, 390, 332);
        frame.getContentPane().add(ja);
        
        JLabel jl = new JLabel();
        jl.setText("@FanfanWang");
        jl.setBounds(320, 435, 93, 21);
        frame.getContentPane().add(jl);
        
        JButton bReturn = new JButton("返回");
        bReturn.setBounds(318, 62, 93, 23);
        frame.getContentPane().add(bReturn);
        
        //插入按钮监听事件
        bInsert.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		info = jf.getText();
        		String res = "";
				System.out.println("在插入按钮监听事件：" + info);
				getInfo(info);
				try {
					res = ConnectSQL.insert(insertInfo, comboValue);
					ja.setText(res);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        //刷新按钮监听事件
        bRefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String res = "";
        		try {
					res = ConnectSQL.search(comboStr);
					ja.setText(res);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        
        //返回按钮监听事件
        bReturn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		ja.setText(null);
        		jf.setText(null);
        		info = "";
//        		comboValue = 0;
//        		comboStr = "";
        		insertInfo.clear();
        	}
        });
        
        //下拉菜单监听事件
		comboBox.addActionListener(new ActionListener() {	//监听事件
			public void actionPerformed(ActionEvent arg0) {
				comboStr = comboBox.getSelectedItem().toString();
				comboValue = deal(comboStr);
				System.out.println(comboValue);
				switch(comboValue)
				{
					case 1:
						clear();
						frame.getContentPane().add(jl1);
						jl1.setBounds(15, 45, 390, 15);
						break;
					case 2:
						clear();
						frame.getContentPane().add(jl2);
						jl2.setBounds(15, 45, 390, 15);
						break;
					case 3:
						clear();
						frame.getContentPane().add(jl3);
						jl3.setBounds(15, 45, 390, 15);
						break;
					case 4:
						clear();
						frame.getContentPane().add(jl4);
						jl4.setBounds(15, 45, 390, 15);
						break;
					case 5:
						clear();
						frame.getContentPane().add(jl5);
						jl5.setBounds(15, 45, 390, 15);
						break;
					case 6:
						clear();
						frame.getContentPane().add(jl6);
						jl6.setBounds(15, 45, 390, 15);
						break;	
				}
				frame.getContentPane().add(jf);
				jf.setBounds(15, 60, 300, 20);
			}
		});
	}
}
