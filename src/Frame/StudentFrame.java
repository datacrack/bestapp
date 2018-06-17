package Frame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Frame.Panel.Dor_P;
import Frame.Panel.chpasswd;
import Frame.Panel.stu_table;
import JDBC.Get;

import java.awt.Font;
import javax.swing.JTable;



public class StudentFrame extends JFrame {

	private JPanel pan;
	public stu_table tab;
	public static Connection con;
	chpasswd jp_passwd=new chpasswd();
	String sno;
	/**
	 * Create the frame.
	 */
	public StudentFrame() {
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 934, 619);
		pan = new JPanel();
		pan.setBounds(0, 1, 912, 562);
		pan.setBackground(new Color(102, 153, 0));
		pan.setBorder(new EmptyBorder(5, 5, 5, 5));
		pan.setLayout(null);
		
		Button button = new Button("\u5BBF\u820D\u60C5\u51B5");
		button.setBounds(10, 42, 122, 54);
		button.setFont(new Font("Dialog", Font.PLAIN, 28));
		pan.add(button);
		
		Button button_1 = new Button("\u8054\u7CFB\u5BBF\u7BA1");
		button_1.setBounds(8, 160, 124, 54);
		button_1.setFont(new Font("Dialog", Font.PLAIN, 28));
		pan.add(button_1);
		
		Button button_2 = new Button("\u67E5\u770B\u8D44\u6599");
		button_2.setBounds(8, 282, 122, 54);
		button_2.setFont(new Font("Dialog", Font.PLAIN, 28));
		pan.add(button_2);
		
		Button button_3 = new Button("\u91CD\u7F6E\u5BC6\u7801");
		button_3.setBounds(11, 405, 111, 54);
		button_3.setFont(new Font("Dialog", Font.PLAIN, 28));
		pan.add(button_3);
		
		JLabel lab1 = new JLabel("");
		lab1.setBounds(138, 0, 820, 607);
		lab1.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/black.png")));
		pan.add(lab1);
		
		JLabel lab2 = new JLabel("");
		lab2.setBounds(0, 0, 136, 587);
		lab2.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/back2.jpg")));
		pan.add(lab2);
		
		tab = new stu_table();
		//tab.remove(tab.btnNewButton);
		//tab.remove(tab.btnNewButton_1);
		
		
		listener1 lis=new listener1();
		lis.frame=this;
		button.addActionListener(lis);
		button_1.addActionListener(lis);
		button_2.addActionListener(lis);
		button_3.addActionListener(lis);
		getContentPane().setLayout(null);
		
		getContentPane().add(tab);
		getContentPane().add(jp_passwd);
		getContentPane().add(pan);
		
		jp_passwd.setVisible(false);
		tab.setVisible(false);
		
	}
}
class listener1 implements ActionListener{
	StudentFrame frame;
	public void actionPerformed(ActionEvent e) {
		String str=e.getActionCommand();
		if (str.equals("宿舍情况")) {
			frame.tab.setVisible(true);
			frame.jp_passwd.setVisible(false);
			//select STUDENT.*,LIVE.* from LIVE,STUDENT WHERE LIVE.BDNO IN (SELECT BDNO FROM LIVE WHERE LIVE.SNO='5120150570') AND LIVE.SNO=STUDENT.SNO AND LIVE.DORNO IN (SELECT DORNO FROM LIVE WHERE LIVE.SNO='5120150570');
			String sql="select STUDENT.*,LIVE.BDNO,LIVE.DORNO,LIVE.BED from LIVE,STUDENT WHERE LIVE.BDNO IN(SELECT BDNO FROM LIVE WHERE LIVE.SNO='"+frame.sno+"')AND LIVE.SNO=STUDENT.SNO AND LIVE.DORNO IN (SELECT DORNO FROM LIVE WHERE LIVE.SNO='"+frame.sno+"');";
			System.out.println(sql);
			try {
				Object[][] data=new Get().Get(frame.con,sql);
				Object name[]= {"学号","姓名","性别","学院","专业","班级","楼号","宿舍号","床号"};
				DefaultTableModel model=new DefaultTableModel(data,name);
				frame.tab.table.setModel(model);
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}else if (str.equals("联系宿管")) {System.out.println("asfasfasfas");
			frame.tab.setVisible(true);
			frame.jp_passwd.setVisible(false);
			String sql="select MANAGER.* from MANAGER,LIVE WHERE MANAGER.BDNO=LIVE.BDNO AND LIVE.SNO ='"+frame.sno+"';";
			
			try {
				Object[][] data=new Get().Get(frame.con,sql);
				Object name[]= {"工号","姓名","性别","联系方式","管理区域"};
				DefaultTableModel model=new DefaultTableModel(data,name);
				frame.tab.table.setModel(model);
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}else if (str.equals("查看资料")) {
			frame.tab.setVisible(true);
			frame.jp_passwd.setVisible(false);
			String sql="select * from STUDENT WHERE SNO ='"+frame.sno+"';";
			
			try {
				Object[][] data=new Get().Get(frame.con,sql);
				Object name[]= {"学号","姓名","性别","学院","专业","班级"};
				DefaultTableModel model=new DefaultTableModel(data,name);
				frame.tab.table.setModel(model);
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}else if(str.equals("重置密码")) {
			frame.tab.setVisible(false);
			frame.jp_passwd.setVisible(true);
			frame.jp_passwd.click(frame.con, frame.sno);
		}
	}
}
