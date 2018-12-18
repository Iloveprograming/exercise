package edu.yangtzeu.lmis.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.border.LineBorder;

import edu.yangtzeu.lmis.bll.ReaderAdmin;
import edu.yangtzeu.lmis.bll.ReaderTypeAdmin;
import edu.yangtzeu.lmis.dal.SQLHelper;
import edu.yangtzeu.lmis.gui.commons.CustomizedTableModel;
import edu.yangtzeu.lmis.gui.commons.ImageFilter;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Rectangle;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class ReaderPanel extends JPanel {
	private enum OpStatus{
		//FIXME
		inSelect,inNew,inChange             //˼��ȷ�ϰ�֤��ȷ�ϱ����Click�¼�������л�״̬��
	}
	
	private ReaderTypeAdmin readerTypeBll=new ReaderTypeAdmin();

	private ReaderAdmin readerBll=new ReaderAdmin();
	
	private OpStatus ops;
	
	private JTextField tfUserName;
	private JTextField tfReaderID;
	private JTextField tfReaderName;
	private JPasswordField passwordField;
	private JTextField tfNumBorrowed;
	private JTextField tfStatus;
	private JTextField tfReaderRole;
	private JTextField tfReaderPhone;
	private JTextField tfEmail;
	private JTextField tfDate;
	
	private JLabel lblPhoto;
	
	private JComboBox rdTypeComboBox;		//ѡ���
	private JComboBox deptTypeComboBox;
	private JComboBox cbGender;
	private JComboBox cbReaderType;
	private JComboBox cbDeptType;
	
	private JPanel searchPanel;
	private JPanel readerInfoPanel;
	private JPanel functionCtrlPanel;
	private JPanel editCtrlPanel;
	
	private JButton lblBorrowInfo;
	private JButton btnCancelEdit;
	private JButton btnSubmitUpdate;
	private JButton btnAddReader;          //�༭���ܰ�ť
	
	private JButton btnClose;				
	private JButton btnCancelReader;
	private JButton btnFound;
	private JButton btnLost;
	private JButton btnUpdateReader;
	private JButton btnNewReader;
	private JButton btnQuery;			//���ܰ�ť
	private JTable searchResultTable;
	private JPanel searchResultPanel;
	private JScrollPane searchResultScrollPane;

	
	public ReaderPanel() {
		setLayout(null);
		//��ѯ����ģ���initSearchPanel()
		
		//��ѯ���ģ���initSearchResultPanel()
		
		//������Ϣģ���initReaderInfoPanel()
		
		//���ܰ�ťģ���initFunctionControlsPanel()
		
		//�༭����ģ���initEditControlsPanel()
		
		//��ʼ������Panel
		initSearchPanel();
		initSearchResultPanel();
		initReaderInfoPanel();
		initFunctionControlsPanel();
		initEditControlsPanel();
		//���ó�ʼ����״̬
		setStatus(OpStatus.inSelect);
		//��Ӷ���������
		getMouseMotionListeners();    //addActionListeners();
	}
	private void initSearchPanel() {
		searchPanel = new JPanel();	//��ѯ����ģ���
		searchPanel.setBounds(14, 19, 774, 32);
		add(searchPanel);
		searchPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BFB\u8005\u7C7B\u522B");
		lblNewLabel.setBounds(10, 10, 54, 15);
		searchPanel.add(lblNewLabel);
		
		String rdTyCom[]= {"��ʦ","������","˶ʿ�о���","��ʿ�о���"};
		rdTypeComboBox = new JComboBox(rdTyCom);
		//rdTypeComboBox = new JComboBox<String>(readerTypeBll.getReaderTypes());
		//rdTypeComboBox=new JComboBox<ReaderType>(readerTypeBll.getReaderTypes());
		//�Ȳ��÷���
		rdTypeComboBox.setBounds(74, 7, 104, 21);
		searchPanel.add(rdTypeComboBox);
		
		JLabel lblNewLabel_1 = new JLabel("\u5355\u4F4D");
		lblNewLabel_1.setBounds(188, 10, 31, 15);
		searchPanel.add(lblNewLabel_1);
		
		String deTyCom[]= {"ͼ���","�ƿ�Ժ","�ƿ�11001��","˶��11201��"};
		deptTypeComboBox=new JComboBox(deTyCom);
		//deptTypeComboBox = new JComboBox(deptTypeBll.getDepartmentTypes());
		
		//deptTypeComboBox=new JComboBox<DepartmentType>(deptTypeBll.getDepartmentTypes());
		//�Ȳ��÷���
		deptTypeComboBox.setBounds(229, 7, 96, 21);
		searchPanel.add(deptTypeComboBox);
		
		JLabel label = new JLabel("\u59D3\u540D");
		label.setBounds(337, 10, 31, 15);
		searchPanel.add(label);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(375, 7, 104, 21);
		searchPanel.add(tfUserName);
		tfUserName.setColumns(10);
		
		btnQuery = new JButton("\u67E5\u627E");
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rdTypeName=(String) rdTypeComboBox.getSelectedItem();
				System.out.println("rdTypeNameΪ"+rdTypeName);
				//ReaderType rdType=(ReaderType)rdTypeComboBox.getSelectedItem();
				//DepartmentType deptType=(DepartmentType)deptTypeComboBox.getSelectedItem();
				String deptTypeName=(String)deptTypeComboBox.getSelectedItem();
				System.out.println("deptTypeNameΪ"+deptTypeName);
				String userName=tfUserName.getText().trim();
				System.out.println("userNameΪ"+userName);
				Reader[] hits=readerBll.retrieveReaders(rdTypeName, deptTypeName,userName);
				
				//���²�ѯ����б�
				updateResultTable(hits);
			}
		});
		btnQuery.setBounds(511, 6, 93, 23);
		searchPanel.add(btnQuery);
	}
	private void initReaderInfoPanel(){
		readerInfoPanel = new JPanel();      //������Ϣģ���
		readerInfoPanel.setBorder(new TitledBorder(null, "\u8BFB\u8005\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		readerInfoPanel.setBounds(505, 59, 283, 438);
		add(readerInfoPanel);
		readerInfoPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("\u501F\u4E66\u8BC1\u53F7");
		lblNewLabel_2.setBounds(9, 24, 54, 15);
		readerInfoPanel.add(lblNewLabel_2);
		
		tfReaderID = new JTextField();
		tfReaderID.setBounds(66, 21, 89, 21);
		readerInfoPanel.add(tfReaderID);
		tfReaderID.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u59D3\u540D");
		lblNewLabel_3.setBounds(9, 52, 37, 15);
		readerInfoPanel.add(lblNewLabel_3);
		
		tfReaderName = new JTextField();
		tfReaderName.setColumns(10);
		tfReaderName.setBounds(66, 49, 89, 21);
		readerInfoPanel.add(tfReaderName);
		
		JLabel lblNewLabel_4 = new JLabel("\u5BC6\u7801");
		lblNewLabel_4.setBounds(9, 84, 32, 15);
		readerInfoPanel.add(lblNewLabel_4);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(66, 80, 89, 21);
		readerInfoPanel.add(passwordField);
		
		JLabel label_1 = new JLabel("\u6027\u522B");
		label_1.setBounds(9, 113, 37, 15);
		readerInfoPanel.add(label_1);
		
		String gender[]= {"��","Ů"};
		cbGender = new JComboBox(gender);
		cbGender.setBounds(66, 110, 88, 21);
		readerInfoPanel.add(cbGender);
		
		JLabel label_2 = new JLabel("\u5DF2\u501F\u4E66");
		label_2.setBounds(10, 143, 42, 15);
		readerInfoPanel.add(label_2);
		
		tfNumBorrowed = new JTextField();
		tfNumBorrowed.setBounds(67, 139, 88, 21);
		readerInfoPanel.add(tfNumBorrowed);
		tfNumBorrowed.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("\u8BC1\u4EF6\u72B6\u6001");
		lblNewLabel_5.setBounds(11, 169, 54, 15);
		readerInfoPanel.add(lblNewLabel_5);
		
		tfStatus = new JTextField();
		tfStatus.setColumns(10);
		tfStatus.setBounds(68, 167, 88, 21);
		readerInfoPanel.add(tfStatus);
		
		JLabel label_3 = new JLabel("\u8BFB\u8005\u89D2\u8272");
		label_3.setBounds(9, 201, 66, 15);
		readerInfoPanel.add(label_3);
		
		tfReaderRole = new JTextField();
		tfReaderRole.setColumns(10);
		tfReaderRole.setBounds(66, 199, 88, 21);
		readerInfoPanel.add(tfReaderRole);
		
		JLabel label_4 = new JLabel("\u8BFB\u8005\u7C7B\u522B");
		label_4.setBounds(9, 230, 54, 15);
		readerInfoPanel.add(label_4);
		
		String rdTyCom[]= {"��ʦ","������","˶ʿ�о���","��ʿ�о���"};
		cbReaderType = new JComboBox(rdTyCom);
		cbReaderType.setBounds(66, 228, 88, 21);
		readerInfoPanel.add(cbReaderType);
		
		lblPhoto = new JLabel("");
		lblPhoto.setBorder(new LineBorder(Color.GRAY));
		lblPhoto.setBackground(new Color(255, 255, 255));
		lblPhoto.setBounds(159, 23, 117, 193);
		readerInfoPanel.add(lblPhoto);
		
		JButton btnLoadPictureFile = new JButton("\u56FE\u7247\u6587\u4EF6");
		btnLoadPictureFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//ͼ��Ķ�ȡ
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new ImageFilter());
				int returnVal=fc.showOpenDialog(ReaderPanel.this);
				if(returnVal==JFileChooser.APPROVE_OPTION) {
					try {
					File file=fc.getSelectedFile();
						BufferedImage img=ImageIO.read(file);
						//scale image to fit the label
						//FIXME
						Image dimg=img.getScaledInstance(lblPhoto.getWidth(),
								lblPhoto.getHeight(), Image.SCALE_SMOOTH);
						ImageIcon icon=new ImageIcon(dimg);
						lblPhoto.setIcon(icon);
					}catch(IOException e) {
						e.printStackTrace();
					}
					}
			}
		});
		btnLoadPictureFile.setBounds(170, 226, 93, 23);
		readerInfoPanel.add(btnLoadPictureFile);
		
		JLabel lblNewLabel_6 = new JLabel("\u5355\u4F4D");
		lblNewLabel_6.setBounds(9, 269, 40, 15);
		readerInfoPanel.add(lblNewLabel_6);
		
		String deTy[]= {"ͼ���","�ƿ�Ժ","�ƿ�11001��","˶��11201��"};
		cbDeptType = new JComboBox(deTy);
		cbDeptType.setBounds(66, 266, 162, 21);
		readerInfoPanel.add(cbDeptType);
		
		JLabel label_6 = new JLabel("\u7535\u8BDD\u53F7\u7801");
		label_6.setBounds(9, 302, 54, 15);
		readerInfoPanel.add(label_6);
		
		tfReaderPhone = new JTextField();
		tfReaderPhone.setBounds(66, 298, 163, 21);
		readerInfoPanel.add(tfReaderPhone);
		tfReaderPhone.setColumns(10);
		
		JLabel label_7 = new JLabel("\u7535\u5B50\u90AE\u4EF6");
		label_7.setBounds(9, 334, 54, 15);
		readerInfoPanel.add(label_7);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(66, 332, 164, 21);
		readerInfoPanel.add(tfEmail);
		tfEmail.setColumns(10);
		
		JLabel label_8 = new JLabel("\u529E\u8BC1\u65E5\u671F");
		label_8.setBounds(9, 366, 54, 15);
		readerInfoPanel.add(label_8);
		
		tfDate = new JTextField();
		tfDate.setBounds(66, 364, 164, 21);
		readerInfoPanel.add(tfDate);
		tfDate.setColumns(10);
	}
	private void initFunctionControlsPanel() {
		functionCtrlPanel = new JPanel();       //���ܰ�ťģ���
		functionCtrlPanel.setBounds(10, 507, 480, 26);
		add(functionCtrlPanel);
		functionCtrlPanel.setLayout(null);
		
		btnNewReader = new JButton("\u529E\u7406\u501F\u4E66\u8BC1");
		btnNewReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStatus(OpStatus.inNew);
				int rdID=readerBll.getMaxRdID()+1;
				tfReaderID.setText(String.valueOf(rdID));
				tfNumBorrowed.setText(String.valueOf(0));
				tfStatus.setText("��Ч");
				tfReaderRole.setText("0");
				Calendar date=Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date DateReg = date.getTime();
				tfDate.setText(sdf.format(DateReg));
				tfReaderName.setText("");
				passwordField.setText("");
				tfReaderPhone.setText("");
				tfEmail.setText("");
			}
		});
		btnNewReader.setBounds(0, 0, 102, 23);
		functionCtrlPanel.add(btnNewReader);
		
		btnUpdateReader = new JButton("\u53D8\u66F4\u4FE1\u606F");
		btnUpdateReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStatus(OpStatus.inChange);
				int selectedRow=searchResultTable.getSelectedRow();
				if(selectedRow<0) {
					JOptionPane.showMessageDialog(null, "����ѡ��һ����¼��");
					return;
				}
				//setReaderToText(((ReaderTableModel) searchResultTable.getModel()).getReaderAt(selectedRow));
				/*String a=(String) ((CustomizedTableModel) searchResultTable.getModel()).getValueAt(selectedRow,1);
				System.out.println(a);
				String b=(String) ((CustomizedTableModel) searchResultTable.getModel()).getValueAt(selectedRow,2);
				System.out.println(b);*/
				int c=(int) ((CustomizedTableModel) searchResultTable.getModel()).getValueAt(selectedRow,0);
				System.out.println(c);
				setReaderToText(readerBll.getReader(c));
			}
		});
		btnUpdateReader.setBounds(98, 0, 86, 23);
		functionCtrlPanel.add(btnUpdateReader);
		
		btnLost = new JButton("\u6302\u5931");
		btnLost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=searchResultTable.getSelectedRow();
				if(selectedRow<0) {
					JOptionPane.showMessageDialog(null, "����ѡ��һ����¼��");
					return;
				}
				int c=(int) ((CustomizedTableModel) searchResultTable.getModel()).getValueAt(selectedRow,0);
				readerBll.reportLoss(c,"��ʧ");
				
				String rdTypeName=(String) rdTypeComboBox.getSelectedItem();
				
				String deptTypeName=(String)deptTypeComboBox.getSelectedItem();
				
				String userName=tfUserName.getText().trim();
				
				Reader[] hits=readerBll.retrieveReaders(rdTypeName, deptTypeName,userName);
				
				//���²�ѯ����б�
				updateResultTable(hits);
				
				JOptionPane.showMessageDialog(null, "����ɹ�ʧ");
			}
		});
		btnLost.setBounds(184, 0, 67, 23);
		functionCtrlPanel.add(btnLost);
		
		btnFound = new JButton("\u89E3\u9664\u6302\u5931");
		btnFound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=searchResultTable.getSelectedRow();
				if(selectedRow<0) {
					JOptionPane.showMessageDialog(null, "����ѡ��һ����¼��");
					return;
				}
				int c=(int) ((CustomizedTableModel) searchResultTable.getModel()).getValueAt(selectedRow,0);
				Reader reader=readerBll.getReader(c);
				if(reader.getRdStatus().equals("��ʧ"))
				{
					readerBll.reportLoss(c,"��Ч");
					String rdTypeName=(String) rdTypeComboBox.getSelectedItem();
					
					String deptTypeName=(String)deptTypeComboBox.getSelectedItem();
					
					String userName=tfUserName.getText().trim();
					
					Reader[] hits=readerBll.retrieveReaders(rdTypeName, deptTypeName,userName);
					
					//���²�ѯ����б�
					updateResultTable(hits);
					JOptionPane.showMessageDialog(null, "�ѽ����ʧ");
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "��ѡ�û�ͼ��֤��δ��ʧ");
					return;
				}
			}
		});
		btnFound.setBounds(250, 0, 96, 23);
		functionCtrlPanel.add(btnFound);
		
		btnCancelReader = new JButton("\u6CE8\u9500");
		btnCancelReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=searchResultTable.getSelectedRow();
				if(selectedRow<0) {
					JOptionPane.showMessageDialog(null, "����ѡ��һ����¼��");
					return;
				}
				int c=(int) ((CustomizedTableModel) searchResultTable.getModel()).getValueAt(selectedRow,0);
				Reader reader=readerBll.getReader(c);
				if(reader.getRdBorrowQty()>0)
				{
					JOptionPane.showMessageDialog(null, "���û�������δ�黹���޷�ע��");
					return;
				}
				readerBll.delete(c);
				String rdTypeName=(String) rdTypeComboBox.getSelectedItem();
				
				String deptTypeName=(String)deptTypeComboBox.getSelectedItem();
				
				String userName=tfUserName.getText().trim();
				
				Reader[] hits=readerBll.retrieveReaders(rdTypeName, deptTypeName,userName);
				
				//���²�ѯ����б�
				CustomizedTableModel<Reader> tableModel=(CustomizedTableModel<Reader>)searchResultTable.getModel();
				tableModel.setRecords(hits);
				//���±��
				tableModel.fireTableDataChanged();
			
				
				JOptionPane.showMessageDialog(null, "ע���ɹ�");
			}
		});
		btnCancelReader.setBounds(346, 0, 67, 23);
		functionCtrlPanel.add(btnCancelReader);
		
		btnClose = new JButton("\u9000\u51FA");
		btnClose.setBounds(413, 0, 67, 23);
		functionCtrlPanel.add(btnClose);
	}
	private void initEditControlsPanel() {
		editCtrlPanel = new JPanel();     //�༭����ģ���
		editCtrlPanel.setBounds(505, 507, 283, 26);
		add(editCtrlPanel);
		editCtrlPanel.setLayout(null);
		
		btnAddReader = new JButton("\u786E\u8BA4\u529E\u8BC1");
		btnAddReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reader reader=getReaderFromText();
				readerBll.insertReader(reader);
				JOptionPane.showMessageDialog(null, "�ɹ���֤��");
			}
		});
		btnAddReader.setBounds(0, 0, 99, 23);
		editCtrlPanel.add(btnAddReader);
		
		btnSubmitUpdate = new JButton("\u786E\u8BA4\u53D8\u66F4");
		btnSubmitUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reader reader=getReaderFromText();
				readerBll.updateReader(reader);
				String rdTypeName=(String) rdTypeComboBox.getSelectedItem();
				
				String deptTypeName=(String)deptTypeComboBox.getSelectedItem();
				
				String userName=tfUserName.getText().trim();
				
				Reader[] hits=readerBll.retrieveReaders(rdTypeName, deptTypeName,userName);
				
				//���²�ѯ����б�
				updateResultTable(hits);
			}
		});
		btnSubmitUpdate.setBounds(97, 0, 93, 23);
		editCtrlPanel.add(btnSubmitUpdate);
		
		btnCancelEdit = new JButton("\u53D6\u6D88");
		btnCancelEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 setStatus(OpStatus.inSelect);
			}
		});
		btnCancelEdit.setBounds(188, 0, 73, 23);
		editCtrlPanel.add(btnCancelEdit);
		
	}
	private void initSearchResultPanel() {
		CustomizedTableModel<Reader> tableModel=new CustomizedTableModel<Reader>(
				readerBll.getDisplayColumnNames(),readerBll.getMethodNames());
	
		searchResultPanel = new JPanel();
		searchResultPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u67E5\u8BE2\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		searchResultPanel.setBounds(24, 61, 471, 436);
		add(searchResultPanel);
		searchResultPanel.setLayout(null);
		searchResultTable = new JTable(tableModel);
		searchResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		searchResultScrollPane=new JScrollPane(searchResultTable);
		searchResultScrollPane.setBounds(0,20,471,416);
		searchResultPanel.add(searchResultScrollPane);
		
		String a[]=readerBll.getDisplayColumnNames();
		for(int i=0;i<a.length;i++) {
			System.out.println(a[i]);
		}
		String b[]=readerBll.getMethodNames();
		for(int i=0;i<b.length;i++) {
			System.out.println(b[i]);
		}
	}
	private void setStatus(OpStatus opst) {
		ops=opst;
		switch(ops) {
		case inSelect:
			searchPanel.setEnabled(true);
			searchResultPanel.setEnabled(true);
			functionCtrlPanel.setEnabled(true);
			//����Panel�������״̬
			setComponentStatusInPanel(functionCtrlPanel,true);
			readerInfoPanel.setEnabled(false);
			readerInfoPanel.setVisible(false);
			editCtrlPanel.setEnabled(false);
			editCtrlPanel.setVisible(false);
			setComponentStatusInPanel(editCtrlPanel,false);
			break;
		case inNew:
			tfNumBorrowed.setEnabled(false);
			tfStatus.setEnabled(false);
			tfReaderRole.setEnabled(false);
			tfReaderID.setEnabled(false);
			tfDate.setEnabled(false);
			searchPanel.setEnabled(false);
			searchResultPanel.setEnabled(false);
			functionCtrlPanel.setEnabled(false);
			setComponentStatusInPanel(functionCtrlPanel,false);
			readerInfoPanel.setEnabled(true);
			readerInfoPanel.setVisible(true);
			editCtrlPanel.setVisible(true);
			editCtrlPanel.setEnabled(true);
			setComponentStatusInPanel(editCtrlPanel,true);
			btnSubmitUpdate.setEnabled(false);
			break;
		case inChange:
			searchPanel.setEnabled(false);
			searchResultPanel.setEnabled(false);
			functionCtrlPanel.setEnabled(false);
			setComponentStatusInPanel(functionCtrlPanel,false);
			readerInfoPanel.setEnabled(true);
			readerInfoPanel.setVisible(true);
			tfReaderID.setEnabled(false);
			tfNumBorrowed.setEnabled(false);
			tfStatus.setEnabled(false);
			tfReaderRole.setEnabled(false);
			tfDate.setEnabled(false);
			editCtrlPanel.setEnabled(true);
			editCtrlPanel.setVisible(true);
			setComponentStatusInPanel(editCtrlPanel,true);
			btnAddReader.setEnabled(false);
			break;
		}
	}
	private void setComponentStatusInPanel(JPanel panel,boolean status) {
		for(Component comp:panel.getComponents()) {
			comp.setEnabled(status);
		}
	}
	private void updateResultTable(Reader[] readers) {
		if(readers==null) {
			JOptionPane.showMessageDialog(null, "û���ҵ�����Ҫ��ļ�¼:");
			return;
		}
		CustomizedTableModel<Reader> tableModel=(CustomizedTableModel<Reader>)searchResultTable.getModel();
		tableModel.setRecords(readers);
		//���±��
		tableModel.fireTableDataChanged();
	}
	private void setReaderToText(Reader reader) {
		tfReaderID.setText(String.valueOf(reader.getRdID()));
		tfReaderName.setText(reader.getRdName());
		passwordField.setText(reader.getRdPwd());
		tfNumBorrowed.setText(String.valueOf(reader.getRdBorrowQty()));
		tfStatus.setText(reader.getRdStatus());
		tfReaderRole.setText(String.valueOf(reader.getRdAdminRoles()));
		tfReaderPhone.setText(reader.getRdPhone());
		tfEmail.setText(reader.getRdEmail());
		tfDate.setText(String.valueOf(reader.getRdDateReg()));
		cbGender.setSelectedItem(reader.getRdSex());
		cbReaderType.setSelectedItem(readerBll.getRdTyName(reader.getRdType()));
		cbDeptType.setSelectedItem(reader.getRdDept());
		System.out.println(reader.getRdPhoto());
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
		else lblPhoto.setIcon(null);
	}
	private Reader getReaderFromText() {
		Reader reader=new Reader(Integer.valueOf(tfReaderID.getText()));
		reader.setRdName(tfReaderName.getText().trim());
		reader.setRdPwd(String.valueOf(passwordField.getPassword()));
		reader.setRdSex(cbGender.getSelectedItem().toString());
		reader.setRdType(readerBll.getRdType((String) cbReaderType.getSelectedItem()));
		reader.setRdDept((String)cbDeptType.getSelectedItem());
		reader.setRdPhone(tfReaderPhone.getText().trim());
		reader.setRdEmail(tfEmail.getText().trim());
		reader.setRdStatus(tfStatus.getText().trim());
		reader.setRdAdminRoles(Integer.valueOf(tfReaderRole.getText().trim()));
		reader.setRdDateReg(Date.valueOf(tfDate.getText().trim()));
		reader.setRdBorrowQty(Integer.valueOf(tfNumBorrowed.getText().trim()));
		if(lblPhoto.getIcon()!=null) {
			Image image=((ImageIcon)lblPhoto.getIcon()).getImage();
			BufferedImage bi=new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D g2=bi.createGraphics();
			g2.drawImage(image,0,0,null);
			g2.dispose();
			ByteArrayOutputStream os=new ByteArrayOutputStream();
			try {
				ImageIO.write(bi, "png", os);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			reader.setRdPhoto(os.toByteArray());
		}
		return reader;
	}
	
	  public JButton getReturnBtn() {
		  return this.btnClose;
	  }

	  
}

