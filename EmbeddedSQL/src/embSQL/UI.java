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
		frame = new JFrame("ѧ����Ϣ��ѯϵͳ");
		frame.setBounds(100, 100, 625, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("��ѯ��ţ�");
		label1.setBounds(28, 25, 71, 26);
		frame.getContentPane().add(label1);
		
		searchNum = new JTextArea();
		searchNum.setBounds(92, 29, 66, 21);
		frame.getContentPane().add(searchNum);
		searchNum.setColumns(10);
		
		JLabel label2 = new JLabel("�ؼ��֣�");
		label2.setBounds(256, 31, 54, 15);
		frame.getContentPane().add(label2);
		
		keyword = new JTextArea();
		keyword.setBounds(309, 30, 111, 21);
		frame.getContentPane().add(keyword);
		keyword.setColumns(10);
		
		JButton bSearch = new JButton("��ѯ");
		bSearch.setBounds(474, 28, 93, 23);
		frame.getContentPane().add(bSearch);
		bSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                methodBSearch();
            }
            //������ť�¼�
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
		
		JLabel label3 = new JLabel("��ѯ������£�");
		label3.setBounds(28, 104, 109, 15);
		frame.getContentPane().add(label3);
		
		JButton bReturn = new JButton("����");
		bReturn.setBounds(474, 310, 93, 23);
		frame.getContentPane().add(bReturn);
		bReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                methodBSearch();
            }
            //������ť�¼�
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
		
		JButton bTip = new JButton("��ʾ");
		bTip.setBounds(168, 27, 71, 23);
		frame.getContentPane().add(bTip);
		
		JLabel lblfanfanwang = new JLabel("@FanfanWang");
		lblfanfanwang.setBounds(475, 338, 108, 15);
		frame.getContentPane().add(lblfanfanwang);
		bTip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                methodBSearch();
            }
            //������ť�¼�
            private void methodBSearch() {
				// TODO Auto-generated method stub
            	switch (searchNum.getText()){
    			case "1"://[1]ѡ���˿γ̺�ΪCNO��ѧ��ѧ��
    				strTip = "ѡ���˿γ̺�ΪCNO��ѧ��ѧ��";
    				break;
    			case "2"://[2]ѧ��ΪSNO��ѧ��ѡ������пγ̵Ŀγ̺źͳɼ�
    				strTip = "ѧ��ΪSNO��ѧ��ѡ������пγ̵Ŀγ̺źͳɼ�";
    				break;
    			case "3"://[3]ѡ���˿γ���ΪCNAME��ѧ��������
    				strTip = "ѡ���˿γ���ΪCNAME��ѧ��������";
    				break;
    			case "4"://[4]����ΪSNAME��ѧ����ѡ���пγ̵Ŀγ�����ѧʱ��ѧ�ֺͿ���ѧ�ں�
    				strTip = "����ΪSNAME��ѧ����ѡ���пγ̵Ŀγ�����ѧʱ��ѧ�ֺͿ���ѧ�ں�";
    				break;
    			case "5"://[5]��ѯ�ɼ���SCORE�����ϵ�ѧ���������γ̺źͳɼ�
    				strTip = "��ѯ�ɼ���SCORE�����ϵ�ѧ���������γ̺źͳɼ�";
    				break;
    			case "6"://[6]ͳ��ѡ��ƽ���ֵ���SCORE��ѧ��ѧ�źͳɼ�
    				strTip = "ͳ��ѡ��ƽ���ֵ���SCORE��ѧ��ѧ�źͳɼ�";
    				break;
    			case "7"://[7]ͳ������ΪSNAME��ѧ��ѡ�޵Ŀγ���
    				strTip = "ͳ������ΪSNAME��ѧ��ѡ�޵Ŀγ���";
    				break;
    			case "8"://[8]��ѯ�γ���ΪCNAME�Ŀγ̵���߷֡���ͷֺ�ƽ����
    				strTip = "��ѯ�γ���ΪCNAME�Ŀγ̵���߷֡���ͷֺ�ƽ����";
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
