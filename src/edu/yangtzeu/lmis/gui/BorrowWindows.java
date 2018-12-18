package edu.yangtzeu.lmis.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import edu.yangtzeu.lmis.bll.BookAdmin;
import edu.yangtzeu.lmis.bll.BorrowAdmin;
import edu.yangtzeu.lmis.bll.ReaderAdmin;
import edu.yangtzeu.lmis.bll.ReaderTypeAdmin;
import edu.yangtzeu.lmis.dal.SQLHelper;
import edu.yangtzeu.lmis.gui.commons.CustomizedTableModel;
import edu.yangtzeu.lmis.gui.commons.SystTimeUpdateTimer;
import edu.yangtzeu.lmis.model.Book;
import edu.yangtzeu.lmis.model.Borrow;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class BorrowWindows extends JFrame {
	private JTextField tfRdID;
	private JTextField tfRdName;
	private JTextField RdCanLendNum;
	private JTextField tfRdDept;
	private JTextField tfRdCanLendDay;
	private JTextField tfRdType;
	private JTextField tfBorrowQty;
	private JTextField tfbkID;
	private JTextField tfbkName;
	
	private JPanel readerInfoPanel;
	private JPanel lendBookPanel;
	private JPanel bookInfoPanel;
	private JPanel searchResultPanel;
	private JPanel showItemPanel;
	private JPanel functionCtrlPanel;
	
	private JButton btnSearchLendBook;
	private JButton btnIDSearch;
	private JButton btnBkNameSearch;
	private JButton btnLend;
	private JButton btnReturn;
	
	private JLabel lblTime;
	private JTable searchResultTable;
	private JTable lendBookTable;
	private JScrollPane lendBookScrollPane;
	private JScrollPane searchResultScrollPane;
	private JButton btnReturnBook;
	private JButton btnConLend;
	
	private BorrowAdmin borrowBll=new BorrowAdmin();
	private BookAdmin bookBll=new BookAdmin();
	private ReaderAdmin readerBll=new ReaderAdmin();
	private ReaderTypeAdmin readerTypeBll=new ReaderTypeAdmin();
	
	private JLabel lblOperator;
	
	public BorrowWindows() {
		setSize(new Dimension(885, 739));
		

		setTitle("\u501F\u4E66");
		getContentPane().setLayout(null);
		
		
		initreaderInfoPanel();
		
		initlendBookPanel();
		
		initbookInfoPanel();
		
		initsearchResultPanel();
		
		initshowItemPanel();
		
		initfunctionCtrlPanel();

	}
	private void initreaderInfoPanel() {
		readerInfoPanel = new JPanel();
		readerInfoPanel.setBorder(new TitledBorder(null, "\u8BFB\u8005\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		readerInfoPanel.setBounds(10, 10, 857, 105);
		getContentPane().add(readerInfoPanel);
		readerInfoPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BFB\u8005\u7F16\u53F7");
		lblNewLabel.setBounds(10, 37, 54, 25);
		readerInfoPanel.add(lblNewLabel);
		
		tfRdID = new JTextField();
		tfRdID.setBounds(74, 34, 82, 28);
		readerInfoPanel.add(tfRdID);
		tfRdID.setColumns(10);
		
		btnSearchLendBook = new JButton("");
		btnSearchLendBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfRdID.getText().trim().equals(""))
					JOptionPane.showMessageDialog(null, "请输入学号先，亲！");
				else {
				int rdID=Integer.valueOf(tfRdID.getText().trim());
				Reader reader=readerBll.getReader(rdID);
				ReaderType readerType=readerTypeBll.getObjectById(reader.getRdType());
				tfRdName.setText(reader.getRdName());
				int RdCanLenNum=readerType.getCanLendQty()-reader.getRdBorrowQty();
				RdCanLendNum.setText(String.valueOf(RdCanLenNum));
				tfRdDept.setText(reader.getRdDept());
				tfRdCanLendDay.setText(String.valueOf(readerType.getCanLendDay()));
				tfRdType.setText(readerBll.getRdTyName(reader.getRdType()));
				tfBorrowQty.setText(String.valueOf(reader.getRdBorrowQty()));
				
				Borrow[] hits=borrowBll.retrieveBorrows(rdID);
				
				//更新查询结果列表
				updateResultTable(hits);
				}
			}
		});
		btnSearchLendBook.setIcon(new ImageIcon("F:\\Picture\\code_pic\\dcfb257fd780a3b05943c907e54c9c77e54e625c2a844-DIRrRz_fw658.png"));
		btnSearchLendBook.setBounds(157, 34, 32, 28);
		readerInfoPanel.add(btnSearchLendBook);
		
		JLabel label = new JLabel("\u8BFB\u8005\u59D3\u540D");
		label.setBounds(233, 20, 54, 15);
		readerInfoPanel.add(label);
		
		tfRdName = new JTextField();
		tfRdName.setBounds(311, 17, 66, 21);
		readerInfoPanel.add(tfRdName);
		tfRdName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u53EF\u501F\u4E66\u6570\u91CF");
		lblNewLabel_1.setBounds(221, 57, 82, 15);
		readerInfoPanel.add(lblNewLabel_1);
		
		RdCanLendNum = new JTextField();
		RdCanLendNum.setBounds(311, 54, 66, 21);
		readerInfoPanel.add(RdCanLendNum);
		RdCanLendNum.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8BFB\u8005\u5355\u4F4D");
		label_1.setBounds(406, 20, 54, 15);
		readerInfoPanel.add(label_1);
		
		tfRdDept = new JTextField();
		tfRdDept.setBounds(470, 18, 66, 21);
		readerInfoPanel.add(tfRdDept);
		tfRdDept.setColumns(10);
		
		JLabel label_2 = new JLabel("\u53EF\u501F\u4E66\u5929\u6570");
		label_2.setBounds(387, 57, 73, 15);
		readerInfoPanel.add(label_2);
		
		tfRdCanLendDay = new JTextField();
		tfRdCanLendDay.setBounds(470, 54, 66, 21);
		readerInfoPanel.add(tfRdCanLendDay);
		tfRdCanLendDay.setColumns(10);
		
		JLabel label_3 = new JLabel("\u8BFB\u8005\u7C7B\u578B");
		label_3.setBounds(563, 20, 54, 15);
		readerInfoPanel.add(label_3);
		
		tfRdType = new JTextField();
		tfRdType.setBounds(627, 17, 66, 21);
		readerInfoPanel.add(tfRdType);
		tfRdType.setColumns(10);
		
		JLabel label_4 = new JLabel("\u5DF2\u501F\u6570\u91CF");
		label_4.setBounds(563, 57, 66, 15);
		readerInfoPanel.add(label_4);
		
		tfBorrowQty = new JTextField();
		tfBorrowQty.setBounds(627, 54, 66, 21);
		readerInfoPanel.add(tfBorrowQty);
		tfBorrowQty.setColumns(10);
		
		JLabel label_5 = new JLabel("\u5DF2\u501F\u56FE\u4E66");
		label_5.setFont(new Font("宋体", Font.BOLD, 14));
		label_5.setBounds(20, 125, 80, 15);
		getContentPane().add(label_5);
	}
	
	private void initlendBookPanel() {
		CustomizedTableModel<Borrow> tableModel=new CustomizedTableModel<Borrow>(
				borrowBll.getDisplayColumnNames(),borrowBll.getMethodNames());
		lendBookPanel = new JPanel();
		lendBookPanel.setBackground(Color.WHITE);
		lendBookPanel.setBounds(10, 150, 857, 150);
		getContentPane().add(lendBookPanel);
		lendBookPanel.setLayout(null);
		lendBookTable = new JTable(tableModel);
		lendBookScrollPane=new JScrollPane(lendBookTable);
		lendBookScrollPane.setBounds(0, 0, 857, 151);
		lendBookPanel.add(lendBookScrollPane);
	}
	
	private void initbookInfoPanel() {
		bookInfoPanel = new JPanel();
		bookInfoPanel.setBorder(new TitledBorder(null, "\u56FE\u4E66\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		bookInfoPanel.setBounds(10, 310, 857, 83);
		getContentPane().add(bookInfoPanel);
		bookInfoPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("\u56FE\u4E66\u5E8F\u53F7");
		lblNewLabel_2.setBounds(10, 30, 62, 15);
		bookInfoPanel.add(lblNewLabel_2);
		
		tfbkID = new JTextField();
		tfbkID.setBounds(71, 27, 91, 21);
		bookInfoPanel.add(tfbkID);
		tfbkID.setColumns(10);
		
		btnIDSearch = new JButton("");
		btnIDSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfbkID.getText().trim().equals(""))
					JOptionPane.showMessageDialog(null, "请输入图书编号先，亲！");
				else {
				int bkID=Integer.valueOf(tfbkID.getText().trim());
				Book[] hits=borrowBll.retrieveBooks(bkID);
				
				//更新查询结果列表
				updateResultTable(hits);
				}
			}
		});
		btnIDSearch.setIcon(new ImageIcon("F:\\Picture\\code_pic\\timg.jpg"));
		btnIDSearch.setBounds(162, 27, 27, 22);
		bookInfoPanel.add(btnIDSearch);
		
		JLabel label_6 = new JLabel("\u56FE\u4E66\u540D\u79F0");
		label_6.setBounds(199, 30, 76, 15);
		bookInfoPanel.add(label_6);
		
		tfbkName = new JTextField();
		tfbkName.setBounds(287, 27, 91, 21);
		bookInfoPanel.add(tfbkName);
		tfbkName.setColumns(10);
		
		btnBkNameSearch = new JButton("");
		btnBkNameSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bkName=tfbkName.getText().trim();
				Book[] hits=borrowBll.retrieveBookName(bkName);
				
				//更新查询结果列表
				updateResultTable(hits);
			}
		});
		btnBkNameSearch.setIcon(new ImageIcon("F:\\Picture\\code_pic\\u=2067561964,964120144&fm=27&gp=0.jpg"));
		btnBkNameSearch.setBounds(377, 26, 27, 22);
		bookInfoPanel.add(btnBkNameSearch);
	}
	private void initsearchResultPanel() {
		CustomizedTableModel<Book> tableModel=new CustomizedTableModel<Book>(
				bookBll.getDisplayColumnNames(),bookBll.getMethodNames());
		
		searchResultPanel = new JPanel();
		searchResultPanel.setBounds(10, 390, 857, 173);
		getContentPane().add(searchResultPanel);
		searchResultPanel.setLayout(null);
		searchResultTable = new JTable(tableModel);
		searchResultScrollPane=new JScrollPane(searchResultTable);
		searchResultScrollPane.setBounds(0, 0, 857, 173);
		searchResultPanel.add(searchResultScrollPane);
		
	}
	private void initshowItemPanel() {
		showItemPanel = new JPanel();
		showItemPanel.setBounds(10, 564, 857, 57);
		getContentPane().add(showItemPanel);
		showItemPanel.setLayout(null);
		
		JLabel label_7 = new JLabel("\u64CD\u4F5C\u5458");
		label_7.setBounds(10, 20, 54, 15);
		showItemPanel.add(label_7);
		
		lblOperator = new JLabel("");
		lblOperator.setBounds(78, 20, 54, 15);
		lblOperator.setText(Login.reader.getRdName());
		showItemPanel.add(lblOperator);
		
		lblTime = new JLabel("");
		 new SystTimeUpdateTimer(lblTime);
		lblTime.setForeground(Color.RED);
		lblTime.setBounds(525, 20, 296, 15);
		showItemPanel.add(lblTime);
	}
	
	private void initfunctionCtrlPanel() {
		functionCtrlPanel = new JPanel();
		functionCtrlPanel.setBounds(326, 643, 518, 38);
		getContentPane().add(functionCtrlPanel);
		functionCtrlPanel.setLayout(null);
		
		btnLend = new JButton("\u501F\u9605");
		btnLend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=searchResultTable.getSelectedRow();
				if(selectedRow<0) {
					JOptionPane.showMessageDialog(null, "请先选中一条记录！");
					return;
				}
				int rdID=Integer.valueOf(tfRdID.getText());
				int c=(int) ((CustomizedTableModel) searchResultTable.getModel()).getValueAt(selectedRow,0);
				Borrow borrow=new Borrow();
				Reader reader=readerBll.getReader(rdID);
				Book book=bookBll.getIDtoBook(c);
				ReaderType readerType=readerTypeBll.getObjectById(reader.getRdType());
				int canLendQty=readerType.getCanLendQty();
				if(reader.getRdStatus().equals("罚款")) {
					JOptionPane.showMessageDialog(null,"请先提交罚款");
					return;
				}
				if(reader.getRdStatus().equals("挂失")) {
					JOptionPane.showMessageDialog(null,"挂失状态不能借书");
					return;
				}
				if(reader.getRdBorrowQty()>=canLendQty) {
					JOptionPane.showMessageDialog(null,"借书数量达到上线，无法借书");
					return;
				}
				
				if(book.getBkStatus().equals("借出")) {
					JOptionPane.showMessageDialog(null,"所选图书已被借出");
					return;
				}
				
				borrow.setBkID(c);
				borrow.setBkName(book.getBkName());
				borrow.setBorrowId(getBorrowId());
				System.out.println(getBorrowId());
				borrow.setIdContinueTimes(0);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar thisTime = Calendar.getInstance();
				/*int day=thisTime.get(Calendar.DATE);
				int month=thisTime.get(Calendar.MONTH);
				int year=thisTime.get(Calendar.YEAR);*/
				int addDay=readerType.getCanLendDay();
				java.util.Date Dateout = (java.util.Date) thisTime.getTime();
				thisTime.add(Calendar.DATE,addDay);
				java.util.Date DateRetAct=(java.util.Date)thisTime.getTime();
				borrow.setIdDateOut(Date.valueOf(sdf.format(Dateout)));
				
				
//				borrow.setIdDateRetAct(Date.valueOf(sdf.format(DateRetAct)));
				borrow.setIdDateRetPlan(Date.valueOf(sdf.format(DateRetAct)));
				borrow.setIsHasReturn(false);
				borrow.setOperatorLend(Login.reader.getRdName());
				borrow.setRdID(rdID);
				
				
				LendBook(c,rdID,borrow);
				
				String bkName=tfbkName.getText().trim();
				Book[] books=borrowBll.retrieveBookName(bkName);
				Borrow[] borrows=borrowBll.retrieveBorrows(rdID);
				updateDouTable(books,borrows);
				
				
				
				Reader reader1=readerBll.getReader(rdID);
				tfRdName.setText(reader1.getRdName());
				int RdCanLenNum=readerType.getCanLendQty()-reader1.getRdBorrowQty();
				RdCanLendNum.setText(String.valueOf(RdCanLenNum));
				tfRdDept.setText(reader1.getRdDept());
				tfRdCanLendDay.setText(String.valueOf(readerType.getCanLendDay()));
				tfRdType.setText(readerBll.getRdTyName(reader1.getRdType()));
				tfBorrowQty.setText(String.valueOf(reader1.getRdBorrowQty()));
				
				JOptionPane.showMessageDialog(null, "借书操作成功");
				
				
			}
		});
		btnLend.setBounds(0, 10, 93, 23);
		functionCtrlPanel.add(btnLend);
		
		btnReturn = new JButton("\u8FD4\u56DE");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dispose();
				
			}
		});
		btnReturn.setBounds(364, 10, 93, 23);
		functionCtrlPanel.add(btnReturn);
		
		btnConLend = new JButton("\u7EED\u501F");
		btnConLend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=lendBookTable.getSelectedRow();
				if(selectedRow<0) {
					JOptionPane.showMessageDialog(null, "请先选中一条记录！");
					return;
				}
				int rdID=Integer.valueOf(tfRdID.getText());
				int c=(int) ((CustomizedTableModel) lendBookTable.getModel()).getValueAt(selectedRow,0);
				Borrow borrow2=borrowBll.getBorrow(rdID, c);
				Borrow borrow=new Borrow();
				Reader reader=readerBll.getReader(rdID);
				Book book=bookBll.getIDtoBook(c);
				ReaderType readerType=readerTypeBll.getObjectById(reader.getRdType());
				int canLendQty=readerType.getCanLendQty();
				
				if(reader.getRdBorrowQty()>=canLendQty) {
					JOptionPane.showMessageDialog(null,"借书数量达到上线，无法借书");
					return;
				}
			
				borrow.setRdID(rdID);
				borrow.setBkID(c);
				borrow.setBkName(book.getBkName());
				borrow.setBorrowId(getBorrowId());
				System.out.println(getBorrowId());
				borrow.setIdContinueTimes(reader.getRdBorrowQty());
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar thisTime = Calendar.getInstance();
				/*int day=thisTime.get(Calendar.DATE);
				int month=thisTime.get(Calendar.MONTH);
				int year=thisTime.get(Calendar.YEAR);*/
				int addDay=readerType.getCanLendDay();
				thisTime.setTime(borrow2.getIdDateOut());
				java.util.Date Dateout = (java.util.Date) thisTime.getTime();
				int n=borrow2.getIdContinueTimes()+2;
				thisTime.add(Calendar.DATE,addDay*n);
				
				java.util.Date DateRetAct=(java.util.Date)thisTime.getTime();
				System.out.println(sdf.format(DateRetAct));
//				borrow.setIdDateOut(Date.valueOf(sdf.format(Dateout)));
				
				if(borrow2.getIdDateRetPlan().getTime()<Dateout.getTime()) {
					JOptionPane.showMessageDialog(null, "超期无法续借，请先缴纳罚金");
					readerBll.updateReaderStatus(rdID);
					return;
				}
//				borrow.setIdDateRetAct(Date.valueOf(sdf.format(DateRetAct)));
				borrow.setIdDateRetPlan(Date.valueOf(sdf.format(DateRetAct)));
				borrow.setIsHasReturn(false);
				borrow.setOperatorLend(Login.reader.getRdName());
				
				
				ReLend(borrow);
				
				
				
				String bkName=tfbkName.getText().trim();
				Book[] books=borrowBll.retrieveBookName(bkName);
				Borrow[] borrows=borrowBll.retrieveBorrows(rdID);
				updateDouTable(books,borrows);
				Reader reader1=readerBll.getReader(rdID);
				tfRdName.setText(reader1.getRdName());
				int RdCanLenNum=readerType.getCanLendQty()-reader1.getRdBorrowQty();
				RdCanLendNum.setText(String.valueOf(RdCanLenNum));
				tfRdDept.setText(reader1.getRdDept());
				tfRdCanLendDay.setText(String.valueOf(readerType.getCanLendDay()));
				tfRdType.setText(readerBll.getRdTyName(reader1.getRdType()));
				tfBorrowQty.setText(String.valueOf(reader1.getRdBorrowQty()));
				
				JOptionPane.showMessageDialog(null, "续借操作成功");																													
			}
		});
		btnConLend.setBounds(90, 10, 93, 23);
		functionCtrlPanel.add(btnConLend);
		
		btnReturnBook = new JButton("\u8FD8\u4E66");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=lendBookTable.getSelectedRow();
				if(selectedRow<0) {
					JOptionPane.showMessageDialog(null, "请先选中一条记录！");
					return;
				}
				int c=(int) ((CustomizedTableModel) lendBookTable.getModel()).getValueAt(selectedRow,0);
				int rdID=Integer.valueOf(tfRdID.getText());
				Borrow borrow=new Borrow();
				Reader reader=readerBll.getReader(rdID);
				Book book=bookBll.getIDtoBook(c);
				ReaderType readerType=readerTypeBll.getReaderType(reader.getRdType());
				
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar thisTime = Calendar.getInstance();

				borrow.setIdDateRetAct(Date.valueOf(sdf.format(thisTime.getTime())));
			
				borrow.setOperatorRet(Login.reader.getRdName());
				borrow.setIsHasReturn(true);
				
				
				Borrow borrow1=borrowBll.getBorrow(rdID,c);
				java.util.Date IdDateRetAct=thisTime.getTime();
				long day=(IdDateRetAct.getTime()-borrow1.getIdDateRetPlan().getTime())/(24*60*60*1000);
				
				if(day>0) {
					readerBll.updateReaderStatus(rdID);
					borrow.setIdOverDay((int) day);
					float OverMoney=day*readerType.getPunishRate();
					borrow.setIdOverMoney(OverMoney);
					JOptionPane.showMessageDialog(null, "还书时间已超过，请提交罚金"+OverMoney+"元");
				}
				String bkName=tfbkName.getText().trim();
				returnBook(rdID,c,borrow);
				if(day>0) {
					JOptionPane.showMessageDialog(null, "成功还书,但需缴纳罚金，否则无法借书");
				}
				else
					JOptionPane.showMessageDialog(null, "成功还书");
				Book[] books=borrowBll.retrieveBookName(bkName);
				Borrow[] borrows=borrowBll.retrieveBorrows(rdID);
				updateDouTable(books,borrows);
				
				
				Reader reader1=readerBll.getReader(rdID);
				tfRdName.setText(reader1.getRdName());
				int RdCanLenNum=readerType.getCanLendQty()-reader1.getRdBorrowQty();
				RdCanLendNum.setText(String.valueOf(RdCanLenNum));
				tfRdDept.setText(reader1.getRdDept());
				tfRdCanLendDay.setText(String.valueOf(readerType.getCanLendDay()));
				tfRdType.setText(readerBll.getRdTyName(reader1.getRdType()));
				tfBorrowQty.setText(String.valueOf(reader1.getRdBorrowQty()));
				/*Borrow[] borrow1=borrowBll.retrieveBorrows(rdID);
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				System.out.print(sdf.format(borrow1[0].getIdDateOut()));
				
				long day=(borrow1[0].getIdDateRetPlan().getTime()-borrow1[0].getIdDateOut().getTime())/(24*60*60*1000);
				System.out.println("两者相差的日期：" + day);*/
				
			
				
			}
		});
		btnReturnBook.setBounds(182, 10, 93, 23);
		functionCtrlPanel.add(btnReturnBook);
		
		JButton button = new JButton("\u7F5A\u91D1\u7F34\u7EB3");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=lendBookTable.getSelectedRow();
				if(selectedRow<0) {
					JOptionPane.showMessageDialog(null, "请先选中一条记录！");
					return;
				}
				int rdID=Integer.valueOf(tfRdID.getText());
				int c=(int) ((CustomizedTableModel) lendBookTable.getModel()).getValueAt(selectedRow,0);
				Reader reader=readerBll.getReader(rdID);
				int num=borrowBll.getLendBookNum(rdID);
				if(num==0) {
					JOptionPane.showMessageDialog(null, "并未欠款，无需交罚款");
					return;
				}
				
				if(num==1) {
					borrowBll.submitMoney(rdID);
					borrowBll.setIdOverDay(rdID,c);
					String bkName=tfbkName.getText().trim();
					Book[] books=borrowBll.retrieveBookName(bkName);
					Borrow[] borrows=borrowBll.retrieveBorrows(rdID);
					updateDouTable(books,borrows);
				}
				else {
					borrowBll.setIdOverDay(rdID,c);
					JOptionPane.showMessageDialog(null, "该用户还有其他欠款记录");
					String bkName=tfbkName.getText().trim();
					Book[] books=borrowBll.retrieveBookName(bkName);
					Borrow[] borrows=borrowBll.retrieveBorrows(rdID);
					updateDouTable(books,borrows);
				}
			}
		});
		button.setBounds(272, 10, 93, 23);
		functionCtrlPanel.add(button);
	}

	protected void ReLend(Borrow borrow) {
		// TODO Auto-generated method stub
		String sql="update TB_Borrow set IdContinueTimes=IdConTinueTimes+1,IdDateRetPlan=?,OperatorLend=? where rdID=?"
				+ " and bkID=?";
		Object[] params=new Object[4];
		
	
		params[0]=borrow.getIdDateRetPlan();
		params[1]=borrow.getOperatorLend();
		params[2]=borrow.getRdID();
		params[3]=borrow.getBkID();
		SQLHelper.ExecSql(sql,params);
		
	}
	protected void returnBook(int rdID, int c, Borrow borrow) {
		// TODO Auto-generated method stub
		String sql0="begin tran begin try ";
		String sql1=" update TB_Book set bkStatus="+"'"+"在馆"+"'"+"where bkID="+c;
		String sql2=" update TB_Reader set rdBorrowQty=rdBorrowQty-1 where rdID="+rdID;
		Borrow rt=(Borrow)borrow;
		String sql3=" update TB_Borrow set IdDateRetAct=?, IdOverDay=?,IdOverMoney=?, IsHasReturn=?,"
				+ " OperatorRet=?,IdContinueTimes=0 where rdID=? and bkID=?";
		String sql4=" print'操作成功!'  commit end try begin catch print'操作失败!'  rollback end catch";
		String sql=sql0+sql1+sql2+sql3+sql4;
		Object[] params=new Object[7];
		params[0]=rt.getIdDateRetAct();
		params[1]=rt.getIdOverDay();
		params[2]=rt.getIdOverMoney();
		params[3]=rt.getIsHasReturn();
		params[4]=rt.getOperatorRet();
		params[5]=rdID;
		params[6]=c;
		SQLHelper.ExecSql(sql,params);
		
	}
	protected void LendBook(int c, int rdID, Borrow borrow) {
		// TODO Auto-generated method stub
		String sql0="begin tran begin try ";
				
		String sql1="update TB_Book set bkStatus="+"'"+"借出"+"'"+"where bkID="+c;
		String sql2=" update TB_Reader set rdBorrowQty=rdBorrowQty+1 where rdID="+rdID;
		Borrow rt=(Borrow) borrow;
		String sql3=" insert into TB_Borrow(rdID,bkID,IdContinueTimes,IdDateOut,"
				+ "IdDateRetPlan,IdDateRetAct,IdOverDay,IdOverMoney,IdPunishMoney,IsHasReturn,"
				+ "OperatorLend,OperatorRet,bkName) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String sql4=" print'操作成功!'  commit end try begin catch print'操作失败!'  rollback end catch";
		Object[] params=new Object[13];
		String sql=sql0+sql1+sql2+sql3+sql4;
		params[0]=rt.getRdID();
		params[1]=rt.getBkID();
		params[2]=rt.getIdContinueTimes();
		params[3]=rt.getIdDateOut();
		params[4]=rt.getIdDateRetPlan();
		params[5]=rt.getIdDateRetAct();
		params[6]=rt.getIdOverDay();
		params[7]=rt.getIdOverMoney();
		params[8]=rt.getIdPunishMoney();
		params[9]=rt.getIsHasReturn();
		params[10]=rt.getOperatorLend();
		params[11]=rt.getOperatorRet();
		params[12]=rt.getBkName();
		
		SQLHelper.ExecSql(sql,params);
		
	}
	private void updateResultTable(Borrow[] borrows) {
		if(borrows==null) {
			CustomizedTableModel<Borrow> tableModel=(CustomizedTableModel<Borrow>)lendBookTable.getModel();
			tableModel.setRecords(borrows);
			tableModel.fireTableDataChanged();
			JOptionPane.showMessageDialog(null, "该读者还未借阅过任何书");
			return;
		}
		CustomizedTableModel<Borrow> tableModel=(CustomizedTableModel<Borrow>)lendBookTable.getModel();
		tableModel.setRecords(borrows);
		//更新表格
		tableModel.fireTableDataChanged();
	}
	private void updateResultTable(Book[] books) {
		if(books==null) {
			CustomizedTableModel<Book> tableModel=(CustomizedTableModel<Book>)searchResultTable.getModel();
			tableModel.setRecords(books);
			tableModel.fireTableDataChanged();
			JOptionPane.showMessageDialog(null, "没有找到符合要求的记录:");
			return;
		}
		CustomizedTableModel<Book> tableModel=(CustomizedTableModel<Book>)searchResultTable.getModel();
		tableModel.setRecords(books);
		//更新表格
		tableModel.fireTableDataChanged();
	}
	public Long getBorrowId() {
		String sql="select BorrowID from TB_Borrow order by BorrowID desc";
		ResultSet rs=SQLHelper.getResultSet(sql);
		
		try {
			if(rs.next()) {
				try {
					 return rs.getLong(1)+1;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else return (long) 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (long) -1;
	}
	public void updateDouTable(Book[] books,Borrow[] borrows) {
		CustomizedTableModel<Book> tableModel=(CustomizedTableModel<Book>)searchResultTable.getModel();
		tableModel.setRecords(books);
		//更新表格
		tableModel.fireTableDataChanged();
		CustomizedTableModel<Borrow> tableModel1=(CustomizedTableModel<Borrow>)lendBookTable.getModel();
		tableModel1.setRecords(borrows);
		//更新表格
		tableModel1.fireTableDataChanged();
	}
}
