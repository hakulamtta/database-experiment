package tbshop;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class SearchUI {

	private static String comboStr = "";
	private static int comboValue = 0;
	
	private static JFrame frame;
		
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
		frame.setBounds(100, 100, 550, 480);
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
	
	//下拉菜单监听事件
		comboBox.addActionListener(new ActionListener() {	//监听事件
			public void actionPerformed(ActionEvent arg0) {
				comboStr = comboBox.getSelectedItem().toString();
				comboValue = InsertUI.deal(comboStr);
				System.out.println(comboValue);
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
