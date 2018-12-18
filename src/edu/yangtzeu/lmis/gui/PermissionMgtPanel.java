package edu.yangtzeu.lmis.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.yangtzeu.lmis.bll.ReaderAdmin;
import edu.yangtzeu.lmis.gui.commons.CustomizedTableModel;
import edu.yangtzeu.lmis.model.Reader;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Image;

import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;

public class PermissionMgtPanel extends JPanel {
	private JTextField tfRdID;
	private JTextField tfRdName;
	private JPanel ReaderSearchPanel;
	private JButton btnIDSearch;
	private JButton btnNameSearch;
	private JPanel ReaderInfoPanel;
	private ReaderAdmin readerBll=new ReaderAdmin();
	private JLabel lblId;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfSex;
	private JTextField tfType;
	private JTextField tfDept;
	private JTextField tfStatus;
	private JTextField tfDateReg;
	private JLabel label_9;
	private JTextField tfPermission;
	private JComboBox PermitLevelcomboBox;
	private JLabel lblPhoto;
	private JButton btnChangePermission;
	private JButton btnReturn;
	
	public PermissionMgtPanel() {
		setLayout(null);
		
		
		
		
		initReaderInfoPanel();
		
		initReaderSearchPanel();
		
		
		
	}
	
	
	private void initReaderInfoPanel() {
		ReaderInfoPanel = new JPanel();
		ReaderInfoPanel.setBounds(10, 179, 557, 331);
		add(ReaderInfoPanel);
		ReaderInfoPanel.setLayout(null);
		
		lblId = new JLabel("ID");
		lblId.setBounds(10, 42, 54, 15);
		ReaderInfoPanel.add(lblId);
		
		label_2 = new JLabel("\u59D3\u540D");
		label_2.setBounds(10, 79, 54, 15);
		ReaderInfoPanel.add(label_2);
		
		label_3 = new JLabel("\u6027\u522B");
		label_3.setBounds(175, 42, 54, 15);
		ReaderInfoPanel.add(label_3);
		
		label_4 = new JLabel("\u7C7B\u578B");
		label_4.setBounds(175, 80, 54, 15);
		ReaderInfoPanel.add(label_4);
		
		label_5 = new JLabel("\u5355\u4F4D");
		label_5.setBounds(10, 122, 54, 15);
		ReaderInfoPanel.add(label_5);
		
		label_6 = new JLabel("\u6743\u9650\u7B49\u7EA7");
		label_6.setBounds(175, 125, 74, 15);
		ReaderInfoPanel.add(label_6);
		
		label_7 = new JLabel("\u72B6\u6001");
		label_7.setBounds(10, 208, 54, 15);
		ReaderInfoPanel.add(label_7);
		
		label_8 = new JLabel("\u6CE8\u518C\u65E5\u671F");
		label_8.setBounds(175, 208, 74, 15);
		ReaderInfoPanel.add(label_8);
		
		tfID = new JTextField();
		tfID.setBounds(72, 42, 95, 21);
		ReaderInfoPanel.add(tfID);
		tfID.setColumns(10);
		
		tfName = new JTextField();
		tfName.setBounds(72, 79, 95, 21);
		ReaderInfoPanel.add(tfName);
		tfName.setColumns(10);
		
		tfSex = new JTextField();
		tfSex.setBounds(237, 42, 95, 21);
		ReaderInfoPanel.add(tfSex);
		tfSex.setColumns(10);
		
		tfType = new JTextField();
		tfType.setBounds(237, 80, 95, 21);
		ReaderInfoPanel.add(tfType);
		tfType.setColumns(10);
		
		tfDept = new JTextField();
		tfDept.setBounds(72, 122, 95, 21);
		ReaderInfoPanel.add(tfDept);
		tfDept.setColumns(10);
		
		tfStatus = new JTextField();
		tfStatus.setBounds(72, 208, 95, 21);
		ReaderInfoPanel.add(tfStatus);
		tfStatus.setColumns(10);
		
		tfDateReg = new JTextField();
		tfDateReg.setBounds(237, 208, 95, 21);
		ReaderInfoPanel.add(tfDateReg);
		tfDateReg.setColumns(10);
		
		String a[]= {"0","1","2","4","8","3","5","6","9","10","12","7","11","13","14","15"};
		PermitLevelcomboBox = new JComboBox(a);
		PermitLevelcomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String b[]= {"0","1","2","4","8",
						"3","5","6","9","10","12",
						"7","11","13","14",
						"15"};
				String a[]= {"非管理员","借书证管理员","图书管理员","借阅管理员","系统管理员",
						"借书证、图书管理员","借书证、借阅管理员","借书证、系统管理员","图书、借阅管理员","图书、系统管理员","借阅、系统管理员",
							"借书证、借阅、系统管理员","借书证、图书、系统管理员","借书证、借阅、系统管理员","图书、借阅、系统管理员"
							,"全能管理员"};
				int c=Integer.valueOf((String)PermitLevelcomboBox.getSelectedItem());
				System.out.println(c+"输出成功");
				System.out.println(a.length+"and"+b.length);
				for(int i=0;i<a.length;i++) {
					if(c==Integer.valueOf(b[i])) {
						tfPermission.setText(a[i]);
						
					}
				}
			}
		});
		PermitLevelcomboBox.setBounds(239, 122, 93, 21);
		ReaderInfoPanel.add(PermitLevelcomboBox);
		
		lblPhoto = new JLabel("");
		lblPhoto.setBorder(new LineBorder(Color.GRAY));
		lblPhoto.setBackground(Color.GRAY);
		lblPhoto.setBounds(357, 49, 162, 239);
		ReaderInfoPanel.add(lblPhoto);
		
		JLabel label_1 = new JLabel("\u56FE\u7247");
		label_1.setBounds(399, 24, 54, 15);
		ReaderInfoPanel.add(label_1);
		
		btnChangePermission = new JButton("\u66F4\u6539\u6743\u9650");
		btnChangePermission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rdAdminRoles=Integer.valueOf((String)PermitLevelcomboBox.getSelectedItem());
				int rdID=Integer.valueOf(tfID.getText());
				readerBll.UpdateReaderAdminRoles(rdAdminRoles,rdID);
				JOptionPane.showMessageDialog(null, "修改成功");
			}
		});
		btnChangePermission.setBounds(10, 265, 131, 23);
		ReaderInfoPanel.add(btnChangePermission);
		
		btnReturn = new JButton("\u8FD4\u56DE");
		btnReturn.setBounds(226, 265, 93, 23);
		ReaderInfoPanel.add(btnReturn);
		
		label_9 = new JLabel("\u6743\u9650");
		label_9.setBounds(10, 164, 54, 15);
		ReaderInfoPanel.add(label_9);
		
		tfPermission = new JTextField();
	
		tfPermission.setBounds(72, 161, 260, 21);
		ReaderInfoPanel.add(tfPermission);
		tfPermission.setColumns(10);
	}
	
	private void initReaderSearchPanel() {
		ReaderSearchPanel = new JPanel();
		ReaderSearchPanel.setBounds(10, 52, 451, 88);
		add(ReaderSearchPanel);
		ReaderSearchPanel.setLayout(null);
		
		JLabel label = new JLabel("\u5B66\u53F7\uFF1A");
		label.setBounds(10, 14, 54, 15);
		ReaderSearchPanel.add(label);
		
		JLabel label_1 = new JLabel("\u59D3\u540D\uFF1A");
		label_1.setBounds(10, 59, 54, 15);
		ReaderSearchPanel.add(label_1);
		
		tfRdID = new JTextField();
		tfRdID.setBounds(63, 11, 66, 21);
		ReaderSearchPanel.add(tfRdID);
		tfRdID.setColumns(10);
		
		tfRdName = new JTextField();
		tfRdName.setBounds(63, 56, 66, 21);
		ReaderSearchPanel.add(tfRdName);
		tfRdName.setColumns(10);
		
		btnIDSearch = new JButton("");
		btnIDSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfRdID.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null,"请输入查询学号先");
					return;
				}
				int a=Integer.valueOf(tfRdID.getText().trim());
				
				Reader reader=readerBll.getReader(a);
				
				tfID.setText(String.valueOf(reader.getRdID()));
				tfName.setText(reader.getRdName());
				tfSex.setText(reader.getRdSex());
				tfType.setText(readerBll.getRdTyName(reader.getRdType()));
				tfDept.setText(reader.getRdDept());
				tfStatus.setText(reader.getRdStatus());
				tfDateReg.setText(String.valueOf(reader.getRdDateReg()));
				String b[]= {"0","1","2","4","8","3","5","6","9","10","12","7","11","13","14","15"};
				for(int i=0;i<b.length;i++) {
					if(reader.getRdAdminRoles()==Integer.valueOf(b[i])) {
						PermitLevelcomboBox.setSelectedItem(b[i]);
					}
				}
				if(reader.getRdPhoto()!=null) {
					Image image=null;
					try {
						image=ImageIO.read(new ByteArrayInputStream(reader.getRdPhoto()));
						System.out.println(reader.getRdPhoto());
					}catch(IOException e) {
						e.printStackTrace();
					}
					lblPhoto.setIcon(new ImageIcon(image));
				}
				else lblPhoto.setText("该用户暂无照片保存");
				setEnabled();
			}
		});
		btnIDSearch.setBounds(149, 10, 33, 23);
		ReaderSearchPanel.add(btnIDSearch);
		
		btnNameSearch = new JButton("");
		btnNameSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String Name=tfRdName.getText().trim();
				Reader reader=readerBll.getReaderFromrdName(Name);
				
				tfID.setText(String.valueOf(reader.getRdID()));
				tfName.setText(reader.getRdName());
				tfSex.setText(reader.getRdSex());
				tfType.setText(readerBll.getRdTyName(reader.getRdType()));
				tfDept.setText(reader.getRdDept());
				tfStatus.setText(reader.getRdStatus());
				tfDateReg.setText(String.valueOf(reader.getRdDateReg()));
				String b[]= {"0","1","2","4","8","3","5","6","9","10","12","7","11","13","14","15"};
				for(int i=0;i<b.length;i++) {
					if(reader.getRdAdminRoles()==Integer.valueOf(b[i])) {
						PermitLevelcomboBox.setSelectedItem(b[i]);
					}
				}
				if(reader.getRdPhoto()!=null) {
					Image image=null;
					try {
						image=ImageIO.read(getFileFromBytes(reader.getRdPhoto(),"D:\\1.txt"));
						System.out.println(reader.getRdPhoto());
					}catch(IOException e) {
						e.printStackTrace();
					}
					lblPhoto.setIcon(new ImageIcon(image));
				}
				else {
					lblPhoto.setIcon(null);
					lblPhoto.setText("该用户暂无照片保存");
				}
				setEnabled();
			}
			
		});
		btnNameSearch.setBounds(149, 55, 33, 23);
		ReaderSearchPanel.add(btnNameSearch);
	}


	protected void setEnabled() {
		// TODO Auto-generated method stub
		tfID.setEnabled(false);
		tfName.setEnabled(false);
		tfSex.setEnabled(false);
		tfType.setEnabled(false);
		tfDept.setEnabled(false);
		tfStatus.setEnabled(false);
		tfDateReg.setEnabled(false);
		tfPermission.setEnabled(false);
	}

	  public static File getFileFromBytes(byte[] b, String outputFile){
		        BufferedOutputStream stream = null;
		          File file = null;
		 try {
		              file = new File(outputFile);
		            FileOutputStream fstream = new FileOutputStream(file);
		            stream = new BufferedOutputStream(fstream);
		            stream.write(b);
		      } catch (Exception e) {
		      e.printStackTrace();
		         } finally {
		           if (stream != null) {
		              try {
		                 stream.close();
		               } catch (IOException e1) {
		                 e1.printStackTrace();
		                 }
		            }
		        }
		      return file;
		    }
	  public JButton getbtnReturn() {
		  return this.btnReturn;
	  }
}
