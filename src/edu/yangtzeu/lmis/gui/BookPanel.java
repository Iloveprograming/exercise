package edu.yangtzeu.lmis.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import edu.yangtzeu.lmis.bll.BookAdmin;
import edu.yangtzeu.lmis.gui.commons.ImageFilter;
import edu.yangtzeu.lmis.model.Book;
import edu.yangtzeu.lmis.model.Reader;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class BookPanel extends JPanel{
	private JTextField tfBkCode;
	private JTextField tfBkID;
	private JTextField tfBkNum;
	private JTextField tfBkAuthor;
	private JTextField tfBkName;
	private JTextField tfBkPress;
	private JTextField tfBkDatePress;
	private JTextField tfBkISBN;
	private JTextField tfClassNum;
	private JTextField tfBkPrice;
	private JTextField tfBkPages;
	private JTextField tfBkDateIn;
	
	private JTextArea taBkBrief;
	
	private JPanel editCtrlPanel;
	private JPanel contentValidityPanel;
	private JPanel coverPanel;
	private JPanel functionCtrlPanel;
	
	private JLabel lblBkCover;
	
	private JComboBox classNamecomboBox;
	private JComboBox bkLanguageCombox;
	
	private JButton btnSave;
	
	private JButton btnReturn;
	private JButton btnNewBook;
	private JButton btnCancel;
	
	private BookAdmin bookBll=new BookAdmin();
	
	public BookPanel() {
		setLayout(null);
		
		
		
		
		initeditCtrlPanel();
		initcontentValidityPanel();
		initcoverPanel();
		
		initfunctionCtrlPanel();
		
	}
	private void initeditCtrlPanel() {
		editCtrlPanel = new JPanel();
		editCtrlPanel.setBounds(10, 47, 236, 383);
		add(editCtrlPanel);
		editCtrlPanel.setLayout(null);
		
		JLabel label = new JLabel("\u56FE\u4E66\u8D77\u59CB\u5E8F\u53F7\uFF1A");
		label.setBounds(10, 13, 113, 15);
		editCtrlPanel.add(label);
		
		JLabel label_1 = new JLabel("\u7D22\u4E66\u53F7\uFF1A");
		label_1.setBounds(10, 37, 60, 15);
		editCtrlPanel.add(label_1);
		
		JLabel label_2 = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
		label_2.setBounds(10, 62, 73, 15);
		editCtrlPanel.add(label_2);
		
		JLabel label_3 = new JLabel("\u56FE\u4E66\u4F5C\u8005\uFF1A");
		label_3.setBounds(10, 83, 73, 15);
		editCtrlPanel.add(label_3);
		
		tfBkCode = new JTextField();
		tfBkCode.setBounds(75, 38, 147, 21);
		editCtrlPanel.add(tfBkCode);
		tfBkCode.setColumns(10);
		
		tfBkID = new JTextField();
		tfBkID.setBounds(105, 10, 94, 21);
		editCtrlPanel.add(tfBkID);
		tfBkID.setColumns(10);
		
		tfBkAuthor = new JTextField();
		tfBkAuthor.setBounds(75, 80, 147, 21);
		editCtrlPanel.add(tfBkAuthor);
		tfBkAuthor.setColumns(10);
		
		JLabel label_4 = new JLabel("\u51FA\u7248\u793E\uFF1A");
		label_4.setBounds(10, 108, 60, 15);
		editCtrlPanel.add(label_4);
		
		tfBkName = new JTextField();
		tfBkName.setBounds(75, 59, 147, 21);
		editCtrlPanel.add(tfBkName);
		tfBkName.setColumns(10);
		
		JLabel label_5 = new JLabel("\u51FA\u7248\u65E5\u671F\uFF1A");
		label_5.setBounds(10, 133, 73, 15);
		editCtrlPanel.add(label_5);
		
		tfBkPress = new JTextField();
		tfBkPress.setBounds(75, 105, 147, 21);
		editCtrlPanel.add(tfBkPress);
		tfBkPress.setColumns(10);
		
		JLabel lblIsbn = new JLabel("ISBN\uFF1A");
		lblIsbn.setBounds(10, 158, 60, 15);
		editCtrlPanel.add(lblIsbn);
		
		tfBkDatePress = new JTextField();
		tfBkDatePress.setBounds(75, 130, 147, 21);
		editCtrlPanel.add(tfBkDatePress);
		tfBkDatePress.setColumns(10);
		
		JLabel label_6 = new JLabel("\u5206\u7C7B\u540D\uFF1A");
		label_6.setBounds(10, 183, 60, 15);
		editCtrlPanel.add(label_6);
		
		String []className= {"自动化技术、计算机技术","计算技术、计算机技术","计算机软件","程序设计、软件工程","程序语言、算法语言","数据库理论与系统","数据库理论"};
		classNamecomboBox = new JComboBox(className);
		classNamecomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfClassNum.setText(bookBll.classToNum((String) classNamecomboBox.getSelectedItem()));
			}
		});
		classNamecomboBox.setBounds(75, 180, 147, 21);
		editCtrlPanel.add(classNamecomboBox);
		
		JLabel label_7 = new JLabel("\u5206\u7C7B\u53F7\uFF1A");
		label_7.setBounds(10, 208, 60, 15);
		editCtrlPanel.add(label_7);
		
		tfBkISBN = new JTextField();
		tfBkISBN.setBounds(75, 155, 147, 21);
		editCtrlPanel.add(tfBkISBN);
		tfBkISBN.setColumns(10);
		
		JLabel label_8 = new JLabel("\u8BED\u79CD\uFF1A");
		label_8.setBounds(10, 233, 60, 15);
		editCtrlPanel.add(label_8);
		
		String []language= {"0-中文","1-英语","2-日语","3-法语","4-俄语","5-西班牙语"};
		bkLanguageCombox = new JComboBox(language);
	
		bkLanguageCombox.setBounds(75, 230, 147, 21);
		editCtrlPanel.add(bkLanguageCombox);
		
		JLabel label_9 = new JLabel("\u56FE\u4E66\u9875\u6570\uFF1A");
		label_9.setBounds(10, 258, 73, 15);
		editCtrlPanel.add(label_9);
	
		tfClassNum = new JTextField();
		tfClassNum.setBounds(75, 205, 147, 21);
		editCtrlPanel.add(tfClassNum);
		tfClassNum.setColumns(10);
		
		JLabel label_10 = new JLabel("\u56FE\u4E66\u4EF7\u683C\uFF1A");
		label_10.setBounds(10, 283, 73, 15);
		editCtrlPanel.add(label_10);
	
		tfBkPrice = new JTextField();
		tfBkPrice.setBounds(75, 280, 147, 21);
		editCtrlPanel.add(tfBkPrice);
		tfBkPrice.setColumns(10);
		
		JLabel label_11 = new JLabel("\u5165\u9986\u65E5\u671F\uFF1A");
		label_11.setBounds(10, 308, 73, 15);
		editCtrlPanel.add(label_11);

		tfBkPages = new JTextField();
		tfBkPages.setBounds(75, 255, 147, 21);
		editCtrlPanel.add(tfBkPages);
		tfBkPages.setColumns(10);
		
		JLabel label_12 = new JLabel("\u56FE\u4E66\u672C\u6570\uFF1A");
		label_12.setBounds(10, 333, 73, 15);
		editCtrlPanel.add(label_12);

		tfBkDateIn = new JTextField();
		tfBkDateIn.setBounds(75, 305, 147, 21);
		editCtrlPanel.add(tfBkDateIn);
		tfBkDateIn.setColumns(10);

		tfBkNum = new JTextField();
		tfBkNum.setBounds(75, 330, 147, 21);
		editCtrlPanel.add(tfBkNum);
		tfBkNum.setColumns(10);
	}
	private void initcontentValidityPanel() {
		contentValidityPanel = new JPanel();
		contentValidityPanel.setBounds(244, 47, 315, 384);
		add(contentValidityPanel);
		contentValidityPanel.setLayout(null);
		
		JLabel label_13 = new JLabel("\u5185\u5BB9\u7B80\u4ECB\uFF1A");
		label_13.setBounds(10, 10, 98, 15);
		contentValidityPanel.add(label_13);
		
		taBkBrief = new JTextArea();
		taBkBrief.setBounds(20, 27, 278, 347);
		contentValidityPanel.add(taBkBrief);
	}
	private void initcoverPanel() {
		coverPanel = new JPanel();
		coverPanel.setBounds(563, 47, 227, 383);
		add(coverPanel);
		coverPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5C01\u9762\uFF1A");
		lblNewLabel.setBounds(10, 10, 58, 15);
		coverPanel.add(lblNewLabel);
		
		lblBkCover = new JLabel("");
		lblBkCover.setBorder(new LineBorder(Color.GRAY));
		lblBkCover.setBounds(10, 35, 208, 303);
		coverPanel.add(lblBkCover);
		
		JButton btnNewPic = new JButton("\u6DFB\u52A0\u56FE\u7247");
		btnNewPic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new ImageFilter());
				int returnVal=fc.showOpenDialog(BookPanel.this);
				if(returnVal==JFileChooser.APPROVE_OPTION) {
					try {
					File file=fc.getSelectedFile();
						BufferedImage img=ImageIO.read(file);
						//scale image to fit the label
						//FIXME
						Image dimg=img.getScaledInstance(lblBkCover.getWidth(),
								lblBkCover.getHeight(), Image.SCALE_SMOOTH);
						ImageIcon icon=new ImageIcon(dimg);
						lblBkCover.setIcon(icon);
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnNewPic.setBounds(59, 350, 93, 23);
		coverPanel.add(btnNewPic);
	}
	private void initfunctionCtrlPanel() {
		functionCtrlPanel = new JPanel();
		functionCtrlPanel.setBounds(244, 485, 357, 23);
		add(functionCtrlPanel);
		functionCtrlPanel.setLayout(null);
		
		btnNewBook = new JButton("\u6DFB\u52A0");
		btnNewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book book=getReaderFromText();
				int BookNum=Integer.valueOf((tfBkNum.getText().trim()));
				bookBll.bookInsert(book,BookNum);
				JOptionPane.showMessageDialog(null, "添加成功");
			}
		});
		btnNewBook.setBounds(0, 0, 93, 23);
		functionCtrlPanel.add(btnNewBook);
		
		btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.setBounds(92, 0, 93, 23);
		functionCtrlPanel.add(btnCancel);
		
		btnReturn = new JButton("\u8FD4\u56DE");
		btnReturn.setBounds(263, 0, 93, 23);
		functionCtrlPanel.add(btnReturn);
		
		btnSave = new JButton("\u4FDD\u5B58\u4FEE\u6539");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book book=getReaderFromText();
				bookBll.Update(book);
				JOptionPane.showMessageDialog(null,"修改成功");
			}
		});
		btnSave.setBounds(181, 0, 93, 23);
		functionCtrlPanel.add(btnSave);
		
	}
	private Book getReaderFromText() {
		// TODO Auto-generated method stub
		Book book=new Book();
		//(Integer.valueOf(tfBkID.getText()));
		book.setBkID(Integer.valueOf(tfBkID.getText()));
		book.setBkCode(tfBkCode.getText().trim());
		book.setBkName(tfBkName.getText().trim());
		book.setBkAuthor(tfBkAuthor.getText().trim());
		book.setBkPress(tfBkPress.getText().trim());
		if(tfBkDatePress.getText().trim().equals("")) {
			book.setBkDatePress(null);
		}else {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date str = null;
		try {
			str = sdf.parse(tfBkDatePress.getText().trim());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		book.setBkDatePress(java.sql.Date.valueOf(sdf.format(str)));
		}
		book.setBkISBN(tfBkISBN.getText().trim());
		book.setBkCatalog(tfClassNum.getText().trim());
		int languageNum=bookBll.languageToNum((String) bkLanguageCombox.getSelectedItem());
		book.setBkLanguage(languageNum);
		book.setBkPages(Integer.valueOf(tfBkPages.getText().trim()));
		book.setBkPrice(Float.valueOf(tfBkPrice.getText().trim()));
		book.setBkDateIn(Date.valueOf(tfBkDateIn.getText().trim()));
		book.setBkBrief(taBkBrief.getText().trim());
		
		if(lblBkCover.getIcon()!=null) {
			Image image=((ImageIcon)lblBkCover.getIcon()).getImage();
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
			
			book.setBkCover(os.toByteArray());
		}
		return book;
	}

	public void setBookToText(Book book) {
		tfBkID.setText(String.valueOf(book.getBkId()));
		tfBkCode.setText(book.getBkCode());
		tfBkAuthor.setText(book.getBkAuthor());
		tfBkName.setText(book.getBkName());
		tfBkPress.setText(book.getBkPress());
		tfBkDatePress.setText(String.valueOf(book.getBkDatePress()));
		tfBkISBN.setText(book.getBkISBN());
		tfBkPrice.setText(String.valueOf(book.getBkPrice()));
		tfBkPages.setText(String.valueOf(book.getBkPages()));
		tfBkDateIn.setText(String.valueOf(book.getBkDateIn()));
		taBkBrief.setText(book.getBkBrief());
		tfBkNum.setText(String.valueOf(bookBll.getBkNum(book.getBkName())));
		classNamecomboBox.setSelectedItem(bookBll.numToClass(book.getBkCatalog()));
		bkLanguageCombox.setSelectedItem(bookBll.numToLanguage(book.getBkLanguage()));
		if(book.getBkCover()!=null) {
			Image image=null;
			try {
				image=ImageIO.read(new ByteArrayInputStream(book.getBkCover()));
				System.out.println(book.getBkCover());
			}catch(IOException e) {
				e.printStackTrace();
			}
			lblBkCover.setIcon(new ImageIcon(image));
		}
		else lblBkCover.setIcon(null);
	}
	public void setStatus(int opst) {
		int ops=opst;
		switch(ops) {
		case 1:
			btnNewBook.setEnabled(false);
			btnCancel.setEnabled(false);
			tfBkNum.setEnabled(false);
			btnSave.setEnabled(true);
			btnReturn.setEnabled(true);
			tfClassNum.setEnabled(false);
			break;
		case 2:
			btnSave.setEnabled(false);
			btnReturn.setEnabled(false);
			btnNewBook.setEnabled(true);
			btnCancel.setEnabled(true);
			tfBkID.setText(String.valueOf(bookBll.getBkStart()));
			tfBkID.setEnabled(false);
			tfBkNum.setEnabled(true);
			tfClassNum.setEnabled(false);
			break;
	
		}
	}

	
	  public JButton getbtnCancel() {
		  return this.btnCancel;
	  }
	  public JButton getbtnReturn() {
		  return this.btnReturn;
	  }
	  public void SetText() {
		  int i=bookBll.getBkStart()+1;
		  tfBkID.setText(String.valueOf(i));
		  tfBkID.setEnabled(false);
			tfBkCode.setText("10001");
			tfBkAuthor.setText("王二狗");
			tfBkName.setText("见风使舵老规矩");
			tfBkPress.setText("飞机上的考虑");
			tfBkDatePress.setText("2016-3-3");
			tfBkISBN.setText("15456165");
			tfBkPrice.setText("60");
			tfBkPages.setText("60");
			tfBkDateIn.setText("2016-3-6");
			taBkBrief.setText("发生");
			tfBkNum.setText("2");
			lblBkCover.setIcon(null);
	  }
}
