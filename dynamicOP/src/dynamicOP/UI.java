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
	
	private String searchStr = "select * From stu where";//�������
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
	
	//ѡ�������������
	JCheckBox cbNum = new JCheckBox("ѧ��");
	JTextPane tNum = new JTextPane();
	JCheckBox cbClass = new JCheckBox("�༶");
	JTextPane tClass = new JTextPane();
	JCheckBox cbName = new JCheckBox("����");
	JTextPane tName = new JTextPane();
	JCheckBox cbF = new JCheckBox("ϵ��");
	JTextPane tF = new JTextPane();
	JCheckBox cbAge = new JCheckBox("����");
	JTextPane tAgeL = new JTextPane();
	JTextPane tAgeH = new JTextPane();
	JCheckBox cbAddr = new JCheckBox("��ַ");
	JTextPane tAddr = new JTextPane();
	JCheckBox cbSex = new JCheckBox("�Ա�");
	JTextPane tSex = new JTextPane();
	JButton bSearch = new JButton("��ѯ");
	JButton bReturn = new JButton("����");
	//������ֵ  
	String[][] data = {{" "," "," "," "," "," "," "}};
	String[] name = { "ѧ��","�༶","����","ϵ��","����","��ַ","�Ա�" };
	DefaultTableModel model = new DefaultTableModel(data,name);
	
	//����ͼ���û�����
	private void initialize() {
		//������
		frame = new JFrame("ѧ����Ϣ��ѯϵͳ @FanfanWang");
		frame.setBounds(100, 100, 730, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//��ѡ����ı���
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
		textField.setText("��");
		textField.setBounds(101, 63, 20, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		tAgeL.setBounds(143, 62, 29, 21);
		frame.getContentPane().add(tAgeL);
		
		textField_1 = new JTextField();
		textField_1.setText("��");
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
		
		//������ť
		bSearch.setBounds(621, 8, 72, 110);
		frame.getContentPane().add(bSearch);
		bSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                methodBSearch();
            }
            //������ť�¼�
            private void methodBSearch() {
				// TODO Auto-generated method stub
            	if (searchStr.equals("select * From stu where"))//���û�и�ѡ��ѡ��
            		searchStr = "select * From stu";
            	tResult.setText(searchStr);
				System.out.println("UI:"+searchStr+"\n");
				ArrayList <Student> student = new ArrayList <Student> ();
				student = ConnectSQL.search(searchStr);	//�������ݿ����			
				Student st = new Student();
				data = st.getData(student);//��ȡ�����������
				System.out.println("UI:"+data+"\n");
				model.setDataVector(data, name);//ˢ�±������
			}
		});

        JScrollPane jsp = new JScrollPane();
        
        //��������
        table = new JTable(model);
        //�������ݾ���
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, tcr);
        
		jsp.setBounds(10, 210, 683, 208);
		jsp.setViewportView(table);
		frame.getContentPane().add(jsp);
		
		//���ɵļ������
		tResult = new JTextField();
		tResult.setBounds(10, 131, 683, 69);
		frame.getContentPane().add(tResult);
		tResult.setColumns(10);
		
		//���ذ�ť
		bReturn.setBounds(600, 428, 93, 23);
		frame.getContentPane().add(bReturn);
		bReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                methodBReturn();
            }
            //���ذ�ť�¼�
            private void methodBReturn() {
				System.out.println("����Ҫ�������״̬��");
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
	
	//��ѡ������¼������ɼ������
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
