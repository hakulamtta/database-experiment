package tbshop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
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
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 250, 257);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton bSearch = new JButton("查询界面");
		bSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchUI.runSearch();
			}
		});
		bSearch.setBounds(70, 40, 93, 45);
		frame.getContentPane().add(bSearch);
		
		JButton bEdit = new JButton("插入界面");
		bEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertUI.runInsert();
			}
		});
		bEdit.setBounds(70, 110, 93, 45);
		frame.getContentPane().add(bEdit);
		
		JLabel lblfanfanwang = new JLabel("@FanfanWang");
		lblfanfanwang.setBounds(80, 174, 90, 34);
		frame.getContentPane().add(lblfanfanwang);
	}

}
