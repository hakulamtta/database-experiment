package tbshop;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SearchUI {

	private static String comboStr = "";
	private static int comboValue = 0;
	private static ArrayList<String> editInfo = new ArrayList<String>();
	
	private static JFrame frame;
	private JTextField tfDelete;
	private JTextField tfEdit;
	private JTextField tfEditInfo;
		
	/**
	 * Launch the application.
	 */
	public static void runSearch() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchUI window = new SearchUI();
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
	public SearchUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("查找编辑页面");
		frame.setBounds(100, 100, 550, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("选择待查找的表：");
		label1.setBounds(20, 10, 104, 15);
		frame.getContentPane().add(label1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(129, 7, 67, 21);
		comboBox.addItem("shop");  
        comboBox.addItem("worker");  
        comboBox.addItem("guest"); 
        comboBox.addItem("good");  
        comboBox.addItem("orderList");  
        comboBox.addItem("detail"); 
        comboBox.setSelectedItem(null);
        frame.getContentPane().add(comboBox);
        
        JButton bSearch = new JButton("查找");
        bSearch.setBounds(420, 6, 93, 23);
        frame.getContentPane().add(bSearch);
        
        JTextArea ja = new JTextArea();
        ja.setBounds(20, 47, 493, 210);
        frame.getContentPane().add(ja);
        
        JLabel lb2 = new JLabel("输入待删除记录的ID：");
        lb2.setBounds(20, 274, 200, 15);
        frame.getContentPane().add(lb2);
        
        tfDelete = new JTextField();
        tfDelete.setBounds(150, 271, 80, 21);
        frame.getContentPane().add(tfDelete);
        tfDelete.setColumns(10);
        
        JButton bDelete = new JButton("删除 ");
        bDelete.setBounds(420, 274, 93, 23);
        frame.getContentPane().add(bDelete);
        
        JLabel lblid = new JLabel("输入待编辑记录的ID：");
        lblid.setBounds(20, 304, 150, 15);
        frame.getContentPane().add(lblid);
        
        tfEdit = new JTextField();
        tfEdit.setBounds(150, 300, 80, 21);
        frame.getContentPane().add(tfEdit);
        tfEdit.setColumns(10);
        
        JButton bEdit = new JButton("编辑");
        bEdit.setBounds(420, 300, 93, 23);
        frame.getContentPane().add(bEdit);
        
        JLabel lbl = new JLabel("输入待编辑记录的内容：");
        lbl.setBounds(20, 329, 395, 15);
        frame.getContentPane().add(lbl);
        
        tfEditInfo = new JTextField();
        tfEditInfo.setBounds(20, 354, 493, 21);
        frame.getContentPane().add(tfEditInfo);
        tfEditInfo.setColumns(10);
        
        JButton bReturn = new JButton("返回");
        bReturn.setBounds(420, 325, 93, 23);
        frame.getContentPane().add(bReturn);
        
        JLabel lblfanfanwang = new JLabel("@FanfanWang");
        lblfanfanwang.setBounds(431, 385, 93, 15);
        frame.getContentPane().add(lblfanfanwang);
	
	//下拉菜单监听事件
		comboBox.addActionListener(new ActionListener() {	//监听事件
			public void actionPerformed(ActionEvent arg0) {
				comboStr = comboBox.getSelectedItem().toString();
				comboValue = InsertUI.deal(comboStr);
				System.out.println(comboValue);
			}
		});
		
		//删除按钮监听事件
		bDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String id = tfDelete.getText();
        		try {
					ConnectSQL.delete(id,comboValue);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
		
		//编辑按钮监听事件
		 bEdit.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		String info = tfEditInfo.getText();
	        		editInfo = InsertUI.getInfo(info);
	        		String id = tfEdit.getText();//待编辑的ID
	        		try {
						ConnectSQL.edit(editInfo, comboValue,id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        	}
	        });
		
		 //返回按钮监听事件
		 bReturn.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		tfEdit.setText("");
	        		tfEditInfo.setText("");
	        		tfDelete.setText("");
	        		editInfo.clear();
	        	}
	        });
		 
		//查找按钮监听事件
		bSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String res = "";
				switch(comboValue)
				{
					case 1:
						try {
							res = ConnectSQL.search("shop",1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ja.setText(res);
						break;
					case 2:
						try {
							res = ConnectSQL.search("worker",2);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ja.setText(res);
						break;
					case 3:
						try {
							res = ConnectSQL.search("guest",3);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ja.setText(res);
						break;
					case 4:
						try {
							res = ConnectSQL.search("good",4);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ja.setText(res);
						break;
					case 5:
						try {
							res = ConnectSQL.search("orderList",5);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ja.setText(res);
						break;
					case 6:
						try {
							res = ConnectSQL.search("detail",6);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ja.setText(res);
						break;	
				}
        	}
        });
		
	}
}
