package shiyan4;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.Choice;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class gongzidanganB extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField name;
	private JTextField money;
	private JTextField people;
	private JTextField year;
	private JTextField newmoney;
	String n;
	double m;
    double p;
    double y;
    double nm;
    private static DbProcesss dbProcesss=new DbProcesss();
    Vector personVector = new Vector();
	Vector titleVector = new Vector();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gongzidanganA frame = new gongzidanganA();
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
	public gongzidanganB() {
		setTitle("工资档案");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 10, 430, 117);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"\u59D3\u540D", "\u8D61\u517B\u4EBA\u6570", "\u96C7\u7528\u65E5\u671F", "\u5E74\u5DE5\u8D44\uFF08\u539F\uFF09", "\u5E74\u5DE5\u8D44\uFF08\u65B0\uFF09"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(95);
		table.getColumnModel().getColumn(4).setPreferredWidth(115);
		scrollPane.setViewportView(table);
		
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setBounds(32, 145, 54, 15);
		contentPane.add(label);
		
		name = new JTextField();
		name.setBounds(82, 142, 66, 21);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5E74\u5DE5\u8D44\uFF08\u65E7\uFF09\uFF1A");
		label_1.setBounds(182, 145, 90, 15);
		contentPane.add(label_1);
		
		money = new JTextField();
		money.setText("25000");
		money.setBounds(268, 142, 66, 21);
		contentPane.add(money);
		money.setColumns(10);
		m=new Double(money.getText()).doubleValue();
		
		JLabel label_2 = new JLabel("\u8D61\u517B\u4EBA\u6570\uFF1A");
		label_2.setBounds(22, 173, 74, 15);
		contentPane.add(label_2);
		
		people = new JTextField();
		people.setText("2");
		people.setBounds(82, 170, 66, 21);
		contentPane.add(people);
		people.setColumns(10);
		p=new Double(people.getText()).doubleValue();
		
		JLabel label_3 = new JLabel("\u96C7\u7528\u65E5\u671F\uFF1A");
		label_3.setBounds(182, 173, 74, 15);
		contentPane.add(label_3);
		
		year = new JTextField();
		year.setText("2018");
		year.setBounds(268, 170, 66, 21);
		contentPane.add(year);
		year.setColumns(10);
		y=new Double(year.getText()).doubleValue();
		//插入
		JButton button_1 = new JButton("\u63D2\u5165");
		button_1.setBounds(32, 215, 93, 23);
		contentPane.add(button_1);
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("actionPerformed(). 插入");
			    dbProcesss.connect();
			    n=name.getText();
			    y=new Double(year.getText()).doubleValue();
				m=new Double(money.getText()).doubleValue();
				p=new Double(people.getText()).doubleValue();
				nm=function.newmoney(p,m,y);
				newmoney.setText(nm+"");
				table.setModel(new DefaultTableModel(
						new Object[][] {
							{"老五", "5", "2017", "25000", "25600"},
							{"老六", "6", "2018", "25000", "25650"},
							{"老四", "4", "2016", "25000", "25550"},
							{n,p,y,m,nm},
						},
						new String[] {
							"姓名", "赡养人数", "雇用日期", "年工资（原）", "年工资（新）"
						}
		));
		}
	});
		//删除
		JButton button_3 = new JButton("\u5220\u9664");
		button_3.setBounds(135, 215, 93, 23);
		contentPane.add(button_3);
		button_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("actionPerformed(). 删除当前记录");
				table.setModel(new DefaultTableModel(
						new Object[][] {
							{"老六", "6", "2018", "25000", "25650"},
							{"老五", "5", "2017", "25000", "25600"},
							{"老四", "4", "2016", "25000", "25550"},
						},
						new String[] {
							"姓名", "赡养人数", "雇用日期", "年工资（原）", "年工资（新）"
			}
	));
		}
	});
		
		//工资计算
		JButton button_4 = new JButton("确定");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				y=new Double(year.getText()).doubleValue();
				m=new Double(money.getText()).doubleValue();
				p=new Double(people.getText()).doubleValue();
				nm=function.newmoney(p,m,y);
				newmoney.setText(nm+"");
			}
		});
		button_4.setBounds(182, 248, 93, 23);
		contentPane.add(button_4);
		
		JLabel label_4 = new JLabel("\u5E74\u5DE5\u8D44\uFF08\u65B0\uFF09\uFF1A");
		label_4.setBounds(32, 290, 93, 15);
		contentPane.add(label_4);
		
		newmoney = new JTextField();
		newmoney.setBounds(115, 287, 113, 21);
		contentPane.add(newmoney);
		newmoney.setColumns(10);
		
		JLabel label_5 = new JLabel("\u5143");
		label_5.setBounds(238, 290, 54, 15);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("\u5143");
		label_6.setBounds(341, 145, 54, 15);
		contentPane.add(label_6);
		//查询并排序
		JButton button = new JButton("\u67E5\u8BE2\u5168\u90E8");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryAllProcess();
			}
		});
		button.setBounds(241, 215, 93, 23);
		contentPane.add(button);
		
		JButton button_5 = new JButton("\u8FD4\u56DE");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainview MV=new mainview();
				MV.setVisible(true);
				setVisible(false);
			}
		});
		button_5.setBounds(375, 307, 93, 23);
		contentPane.add(button_5);
	}
	public void queryAllProcess() {
		String sql = "select * from dangan;";
		System.out.println("queryAllProcess(). sql = " + sql);
		
		dbProcesss.connect();
		ResultSet rs = dbProcesss.executeQuery(sql);
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{"老六", "2", "2018", "25000", "25250"},
					{"老五", "5", "2016", "25000", "25650"},
					{"老四", "4", "2015", "25000", "25600"},
					
				},
				new String[] {
					"姓名", "赡养人数", "雇用日期", "年工资（原）", "年工资（新）"
				}
			));
	}
}
