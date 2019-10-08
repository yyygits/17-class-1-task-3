package shiyan4; 
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class mainview extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainview frame = new mainview();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainview() {
		setTitle("\u65B9\u6848\u9009\u62E9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\u7B97\u6CD5a");
		btnNewButton.setFont(new Font("×Ö»ê49ºÅ-åÐÒ£ÐÐÊé", Font.PLAIN, 27));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gongzidanganA GZ=new gongzidanganA();
				GZ.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(128, 69, 148, 45);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u7B97\u6CD5b");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gongzidanganB GZB=new gongzidanganB();
				GZB.setVisible(true);
				
			}
		});
		btnNewButton_1.setFont(new Font("×Ö»ê49ºÅ-åÐÒ£ÐÐÊé", Font.PLAIN, 27));
		btnNewButton_1.setBounds(128, 135, 148, 45);
		contentPane.add(btnNewButton_1);
	}

}
