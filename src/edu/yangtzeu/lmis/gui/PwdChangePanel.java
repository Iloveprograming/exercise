package edu.yangtzeu.lmis.gui;

import javax.swing.JPanel;

import java.awt.image.BufferedImage;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.yangtzeu.lmis.bll.ReaderAdmin;
import edu.yangtzeu.lmis.gui.commons.VerifyCodeUtils;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;


public class PwdChangePanel extends JPanel {
	private JTextField tfName;
	private JPasswordField tfPwd;
	private JPasswordField tfPwdrepeat;
	private JTextField tfYzm;
	private String code;
	private JButton btnCommit;
	private JButton btnReturn;
	private BufferedImage img;
	private ReaderAdmin readerBll=new ReaderAdmin();
	private JTextField tfoldPwd;
	public PwdChangePanel() {
		setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D");
		label.setBounds(161, 93, 54, 15);
		add(label);
		
		JLabel label_1 = new JLabel("\u65B0\u5BC6\u7801");
		label_1.setBounds(161, 182, 54, 15);
		add(label_1);
		
		JLabel label_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_2.setBounds(161, 233, 81, 15);
		add(label_2);
		
		JLabel label_3 = new JLabel("\u9A8C\u8BC1\u7801");
		label_3.setBounds(161, 275, 54, 15);
		add(label_3);
		
		tfName = new JTextField();
		tfName.setBounds(252, 90, 117, 21);
		add(tfName);
		tfName.setColumns(10);
		
		tfPwd = new JPasswordField();
		tfPwd.setBounds(252, 179, 117, 21);
		add(tfPwd);
		
		tfPwdrepeat = new JPasswordField();
		tfPwdrepeat.setBounds(252, 230, 117, 21);
		add(tfPwdrepeat);
		
		btnCommit = new JButton("\u63D0\u4EA4");
		btnCommit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=0;
				String oldpwd=tfoldPwd.getText().trim();
				char[] pwd=tfPwd.getPassword();
				char[] pwdre=tfPwdrepeat.getPassword();
				String opwd=Login.reader.getRdPwd();
				if(!opwd.equals(oldpwd)) {
					JOptionPane.showMessageDialog(null, "原密码输入错误");
					tfoldPwd.setText("");
					tfPwd.setText("");
					tfPwdrepeat.setText("");
					tfYzm.setText("");
				}
				if(pwd.length!=pwdre.length)
					{
					JOptionPane.showMessageDialog(null, "两次密码长度不一致");
					tfPwd.setText("");
					tfPwdrepeat.setText("");
					tfYzm.setText("");
					}
				else {
				for(;i<pwd.length;i++) {
					if(pwd[i]!=(pwdre[i]))
						{
							JOptionPane.showMessageDialog(null, "两次密码输入不一样，请重新输入");
							tfPwd.setText("");
							tfPwdrepeat.setText("");
							tfYzm.setText("");
							return;
						}
				}
				if(i>=pwd.length)
				{
					String YZM=tfYzm.getText();
					String code=getYZM();
					if(YZM.equals(code))
						{
						readerBll.UpdateReaderPwd(Login.reader.getRdID(),pwd);
						JOptionPane.showMessageDialog(null, "密码修改成功");
						}
					else {
						
						JOptionPane.showMessageDialog(null, "验证码错误");
						tfPwd.setText("");
						tfPwdrepeat.setText("");
						tfYzm.setText("");
					}
				}
			}
			}
		});
		btnCommit.setBounds(179, 344, 93, 23);
		add(btnCommit);
		
		btnReturn = new JButton("\u8FD4\u56DE");
		btnReturn.setBounds(356, 344, 93, 23);
		add(btnReturn);
		
		JLabel lblYzm = new JLabel("");
		lblYzm.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				// 处理鼠标点击
				img = VerifyCodeUtils.paintImage(109, 26);
		        code = VerifyCodeUtils.getCode();
		        System.out.println(code);
				 lblYzm.setIcon(new ImageIcon(img));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		lblYzm.setBounds(356, 272, 109, 26);
		add(lblYzm);
		
		
		tfName.setText(Login.reader.getRdName());
		
		
		img = VerifyCodeUtils.paintImage(109, 26);
        code = VerifyCodeUtils.getCode();
        System.out.println(code);

        //生成验证码
        
        lblYzm.setIcon(new ImageIcon(img));
        
        tfYzm = new JTextField();
        tfYzm.setBounds(252, 272, 72, 21);
        add(tfYzm);
        tfYzm.setColumns(10);
        
        JLabel label_4 = new JLabel("\u539F\u5BC6\u7801");
        label_4.setBounds(161, 142, 54, 15);
        add(label_4);
        
        tfoldPwd = new JTextField();
        tfoldPwd.setBounds(252, 139, 117, 21);
        add(tfoldPwd);
        tfoldPwd.setColumns(10);
	}
	protected String getYZM() {
		// TODO Auto-generated method stub
		return this.code;
	}
	public JButton getReturnBtn() {
		return this.btnReturn;
	}
	public BufferedImage getImg() {
		return this.img;
	}
}
