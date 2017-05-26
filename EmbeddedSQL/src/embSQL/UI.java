package embSQL;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class UI implements ItemListener{

	private JFrame frame;
	private JTextArea searchNum;
	private JTextArea keyword;
	private JTextArea result;
	private String searchRes;
	private String strTip;

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
		frame = new JFrame("学生信息查询系统");
		frame.setBounds(100, 100, 625, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("查询序号：");
		label1.setBounds(28, 25, 71, 26);
		frame.getContentPane().add(label1);
		
		searchNum = new JTextArea();
		searchNum.setBounds(92, 29, 66, 21);
		frame.getContentPane().add(searchNum);
		searchNum.setColumns(10);
		
		JLabel label2 = new JLabel("关键字：");
		label2.setBounds(256, 31, 54, 15);
		frame.getContentPane().add(label2);
		
		keyword = new JTextArea();
		keyword.setBounds(309, 30, 111, 21);
		frame.getContentPane().add(keyword);
		keyword.setColumns(10);
		
		JButton bSearch = new JButton("查询");
		bSearch.setBounds(474, 28, 93, 23);
		frame.getContentPane().add(bSearch);
		bSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                methodBSearch();
            }
            //搜索按钮事件
            private void methodBSearch() {
				// TODO Auto-generated method stub
            	String strNum = searchNum.getText();
            	String strKey = keyword.getText();
            	searchRes = ConnectSQL.search(strNum, strKey);
            	result.setText(searchRes);
			}
		});
		
		result = new JTextArea();
		result.setBounds(28, 129, 539, 171);
		frame.getContentPane().add(result);
		result.setColumns(10);
		result.setLineWrap(true);
		
		JLabel label3 = new JLabel("查询结果如下：");
		label3.setBounds(28, 104, 109, 15);
		frame.getContentPane().add(label3);
		
		JButton bReturn = new JButton("返回");
		bReturn.setBounds(474, 310, 93, 23);
		frame.getContentPane().add(bReturn);
		bReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                methodBSearch();
            }
            //搜索按钮事件
            private void methodBSearch() {
				// TODO Auto-generated method stub
            	result.setText("");
            	searchNum.setText("");
            	keyword.setText("");
			}
		});
		
		JLabel tip = new JLabel("");
		tip.setBounds(28, 72, 538, 15);
		frame.getContentPane().add(tip);
		
		JButton bTip = new JButton("提示");
		bTip.setBounds(168, 27, 71, 23);
		frame.getContentPane().add(bTip);
		
		JLabel lblfanfanwang = new JLabel("@FanfanWang");
		lblfanfanwang.setBounds(475, 338, 108, 15);
		frame.getContentPane().add(lblfanfanwang);
		bTip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                methodBSearch();
            }
            //搜索按钮事件
            private void methodBSearch() {
				// TODO Auto-generated method stub
            	switch (searchNum.getText()){
    			case "1"://[1]选择了课程号为CNO的学生学号
    				strTip = "选择了课程号为CNO的学生学号";
    				break;
    			case "2"://[2]学号为SNO的学生选择的所有课程的课程号和成绩
    				strTip = "学号为SNO的学生选择的所有课程的课程号和成绩";
    				break;
    			case "3"://[3]选择了课程名为CNAME的学生的姓名
    				strTip = "选择了课程名为CNAME的学生的姓名";
    				break;
    			case "4"://[4]姓名为SNAME的学生所选所有课程的课程名，学时，学分和开课学期号
    				strTip = "姓名为SNAME的学生所选所有课程的课程名，学时，学分和开课学期号";
    				break;
    			case "5"://[5]查询成绩在SCORE分以上的学生姓名、课程号和成绩
    				strTip = "查询成绩在SCORE分以上的学生姓名、课程号和成绩";
    				break;
    			case "6"://[6]统计选课平均分低于SCORE的学生学号和成绩
    				strTip = "统计选课平均分低于SCORE的学生学号和成绩";
    				break;
    			case "7"://[7]统计姓名为SNAME的学生选修的课程数
    				strTip = "统计姓名为SNAME的学生选修的课程数";
    				break;
    			case "8"://[8]查询课程名为CNAME的课程的最高分、最低分和平均分
    				strTip = "查询课程名为CNAME的课程的最高分、最低分和平均分";
    				break;
    			}
            	tip.setText(strTip);
			}
		});
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
