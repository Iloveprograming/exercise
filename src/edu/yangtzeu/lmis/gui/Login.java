package edu.yangtzeu.lmis.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.DropMode;
import java.awt.Dimension;
import javax.swing.JButton;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.bll.ReaderAdmin;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Login extends JFrame {
	private JTextField tfUserName;
	private JPasswordField pwdField;
	private JButton btnLogin;
	private JButton btnClose;
	private int loginTimes=0;//登陆次数
	private ReaderAdmin readerBLL=new ReaderAdmin();
	public static Reader reader=null;//登陆用户信息 static防止读者对象丢失
	private JLabel labelLoginInfo;
	public Login() {
		setSize(new Dimension(350, 250));
		setResizable(false);
		setTitle("\u957F\u6C5F\u5927\u5B66\u56FE\u4E66\u9986\u7BA1\u7406\u4FE1\u606F\u7CFB\u7EDF");
		getContentPane().setLayout(null);
		
		JLabel labelUserName = new JLabel("\u7528\u6237\u7F16\u53F7\uFF1A");
		labelUserName.setBounds(56, 54, 65, 15);
		getContentPane().add(labelUserName);
		
		JLabel labelPassword = new JLabel("\u7528\u6237\u5BC6\u7801\uFF1A");
		labelPassword.setBounds(56, 99, 65, 15);
		getContentPane().add(labelPassword);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(131, 51, 126, 21);
		getContentPane().add(tfUserName);
		tfUserName.setColumns(10);
		
		pwdField = new JPasswordField();
		pwdField.setBounds(131, 96, 126, 21);
		getContentPane().add(pwdField);
		

		btnLogin = new JButton("\u767B\u5F55");	
		
		btnLogin.setBounds(42, 161, 93, 23);
		getContentPane().add(btnLogin);
		
		btnClose = new JButton("\u9000\u51FA");
		btnClose.setBounds(164, 161, 93, 23);
		getContentPane().add(btnClose);
		
		labelLoginInfo = new JLabel("");
		labelLoginInfo.setForeground(Color.RED);
		labelLoginInfo.setBounds(131, 127, 126, 15);
		getContentPane().add(labelLoginInfo);
		addButtonClickEventHandlers();
	}
	private void loadMainGUI() {
		this.dispose();
		Main mainGUI=new Main();
		mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGUI.setLocationRelativeTo(null);
		mainGUI.setVisible(true);
	}
	
	private void addButtonClickEventHandlers() {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			++loginTimes;
			String errorMsg="";
			int rdId=-1;
			try {
				rdId=Integer.valueOf(tfUserName.getText().trim());	 //获得文本框中的字符串并去掉空格			
			}
			catch (NumberFormatException e) {
				errorMsg="用户名无效";
				tfUserName.requestFocus();
			}
			if(rdId!=-1) {
				reader=readerBLL.getReader(rdId);
				if(reader==null) {
					errorMsg="用户名无效";
					tfUserName.requestFocus();
				}else if(reader.getRdPwd().equals(
						new String(pwdField.getPassword()).trim())) {
					loadMainGUI();
				}else {
					errorMsg="密码有误";
					pwdField.requestFocus();
				}
			}
			if(errorMsg.length()>0)
				labelLoginInfo.setText(errorMsg);;			
		}
	});
	btnClose.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			dispose();//关闭当前窗体
		}
	});
	}
	public void start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		Login login=new Login();
		login.start();
	}
}
