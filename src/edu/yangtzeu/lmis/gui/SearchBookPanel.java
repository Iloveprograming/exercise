package edu.yangtzeu.lmis.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import edu.yangtzeu.lmis.bll.BookAdmin;
import edu.yangtzeu.lmis.gui.commons.CustomizedTableModel;
import edu.yangtzeu.lmis.model.Book;
import edu.yangtzeu.lmis.model.Reader;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class SearchBookPanel extends JPanel{
	private BookAdmin bookBll=new BookAdmin();
	private JTextField tfSearchItem;
	private JToggleButton tglbtnNewToggleButton; 
	private JToggleButton toggleButton; 
	private JScrollPane scrollPane;
	private JTable searchResultTable;
	private JComboBox simpleSearchComboBox;
	private JButton btnHighLevelSearch;
	private JButton btnChange;
	private JButton btnDelete;
	private JButton btnReturn;
	private JButton btnSearch;
	
	
	private JPanel SearchSimplePanel;
	private JPanel highlevelSearchPanel;
	private JPanel SearchResultPanel;
	private JPanel FunctionControlsPanel;
	private JTextField tfBookName;
	private JTextField tfBkPress;
	private JTextField tfBkAuthor;
	private JTextField tfBkCatalog;
	private JTextField tfBkBrief;
	private JTextField tfDatePress;
	
	public SearchBookPanel() {
		setLayout(null);
		toggleButton = new JToggleButton("\u7B80\u5355\u67E5\u8BE2");
		toggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleButton.setSelected(true);
				tglbtnNewToggleButton.setSelected(false);
				highlevelSearchPanel.setVisible(false);
				SearchSimplePanel.setVisible(true);
			}
		});
		toggleButton.setSelected(true);
		toggleButton.setBounds(10, 69, 98, 23);
		add(toggleButton);
		
		tglbtnNewToggleButton = new JToggleButton("\u9AD8\u7EA7\u67E5\u8BE2");
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleButton.setSelected(false);
				tglbtnNewToggleButton.setSelected(true);
				highlevelSearchPanel.setVisible(true);
				SearchSimplePanel.setVisible(false);
			}
		});
		tglbtnNewToggleButton.setBounds(106, 69, 98, 23);
		add(tglbtnNewToggleButton);
		
		initSearchSimplePanel();
		inithighlevelSearchPanel();
		initSearchResultPanel();
		initFunctionControlsPanel();
	}
	private void initSearchSimplePanel() {
		SearchSimplePanel = new JPanel();
		SearchSimplePanel.setBorder(new LineBorder(new Color(100, 149, 237), 2));
		SearchSimplePanel.setBounds(10, 91, 763, 101);
		add(SearchSimplePanel);
		SearchSimplePanel.setLayout(null);
		
		JLabel label = new JLabel("\u68C0\u7D22\u5B57\u6BB5\uFF1A");
		label.setBounds(43, 42, 69, 15);
		SearchSimplePanel.add(label);
		
		String a[]= {"索书号","书名","作者","出版社","分类","语种"};
		simpleSearchComboBox = new JComboBox(a);
		simpleSearchComboBox.setBounds(122, 39, 106, 21);
		SearchSimplePanel.add(simpleSearchComboBox);
		
		tfSearchItem = new JTextField();
		tfSearchItem.setBounds(260, 39, 239, 21);
		SearchSimplePanel.add(tfSearchItem);
		tfSearchItem.setColumns(10);
		
		btnSearch = new JButton("\u67E5\u8BE2");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String SearchClass=(String) simpleSearchComboBox.getSelectedItem();
				String SearchItem=tfSearchItem.getText().trim();
				Book[] hits=bookBll.getBook(SearchClass,SearchItem);
				updateResultTable(hits);
				
			}
		});
		btnSearch.setBounds(553, 38, 93, 23);
		SearchSimplePanel.add(btnSearch);
	}
	private void inithighlevelSearchPanel() {
		highlevelSearchPanel = new JPanel();
		highlevelSearchPanel.setBorder(new LineBorder(new Color(100, 149, 237), 2));
		highlevelSearchPanel.setBounds(10, 91, 763, 101);
		add(highlevelSearchPanel);
		highlevelSearchPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
		lblNewLabel.setBounds(10, 25, 81, 15);
		highlevelSearchPanel.add(lblNewLabel);
		
		tfBookName = new JTextField();
		tfBookName.setBounds(101, 19, 138, 21);
		highlevelSearchPanel.add(tfBookName);
		tfBookName.setColumns(10);
		
		JLabel label = new JLabel("\u51FA\u7248\u793E\u540D\uFF1A");
		label.setBounds(10, 57, 81, 15);
		highlevelSearchPanel.add(label);
		
		tfBkPress = new JTextField();
		tfBkPress.setBounds(101, 51, 138, 21);
		highlevelSearchPanel.add(tfBkPress);
		tfBkPress.setColumns(10);
		
		JLabel label_1 = new JLabel("\u56FE\u4E66\u4F5C\u8005\uFF1A");
		label_1.setBounds(253, 28, 66, 15);
		highlevelSearchPanel.add(label_1);
		
		tfBkAuthor = new JTextField();
		tfBkAuthor.setBounds(313, 25, 123, 21);
		highlevelSearchPanel.add(tfBkAuthor);
		tfBkAuthor.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5206\u7C7B\u53F7\uFF1A");
		label_2.setBounds(249, 60, 54, 15);
		highlevelSearchPanel.add(label_2);
		
		tfBkCatalog = new JTextField();
		tfBkCatalog.setBounds(313, 57, 123, 21);
		highlevelSearchPanel.add(tfBkCatalog);
		tfBkCatalog.setColumns(10);
		
		JLabel label_3 = new JLabel("\u56FE\u4E66\u63CF\u8FF0\uFF1A");
		label_3.setBounds(446, 25, 66, 15);
		highlevelSearchPanel.add(label_3);
		
		tfBkBrief = new JTextField();
		tfBkBrief.setBounds(510, 22, 123, 21);
		highlevelSearchPanel.add(tfBkBrief);
		tfBkBrief.setColumns(10);
		
		JLabel label_4 = new JLabel("\u51FA\u7248\u5E74\uFF1A");
		label_4.setBounds(445, 57, 54, 15);
		highlevelSearchPanel.add(label_4);
		
		tfDatePress = new JTextField();
		tfDatePress.setBounds(510, 54, 123, 21);
		highlevelSearchPanel.add(tfDatePress);
		tfDatePress.setColumns(10);
		
		btnHighLevelSearch = new JButton("\u67E5\u8BE2");
		btnHighLevelSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String BookName=tfBookName.getText().trim();
				String BookPress=tfBkPress.getText().trim();
				String BookAuthor=tfBkAuthor.getText().trim();
				String BookCatalog=tfBkCatalog.getText().trim();
				String BookBrief=tfBkBrief.getText().trim();
				String BookDatePress=tfDatePress.getText().trim();
				Book[] hits=bookBll.highLevelSearch(BookName,BookPress,BookAuthor,BookCatalog,BookBrief,BookDatePress);
				updateResultTable(hits);
			}
		});
		btnHighLevelSearch.setBounds(660, 35, 93, 23);
		highlevelSearchPanel.add(btnHighLevelSearch);
		highlevelSearchPanel.setVisible(false);
	}
	private void initSearchResultPanel() {
		CustomizedTableModel<Reader> tableModel=new CustomizedTableModel<Reader>(
				bookBll.getDisplayColumnNames(),bookBll.getMethodNames());
		SearchResultPanel = new JPanel();
		SearchResultPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u67E5\u8BE2\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		SearchResultPanel.setBounds(10, 223, 780, 220);
		add(SearchResultPanel);
		SearchResultPanel.setLayout(null);
		searchResultTable = new JTable(tableModel);
		
		scrollPane = new JScrollPane(searchResultTable);
		scrollPane.setBounds(10, 23, 760, 187);
		SearchResultPanel.add(scrollPane);
		searchResultTable.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(MouseEvent e) {//仅当鼠标单击时响应
               //得到选中的行列的索引值
              int r= searchResultTable.getSelectedRow();
              int c=(int) ((CustomizedTableModel) searchResultTable.getModel()).getValueAt(r,0);
              System.out.println(c);
				setReaderTypeToText(bookBll.getIDtoBook(c));
            }

        }); //在这个内部类中添加了mouseadaper这个类
	}
	private void initFunctionControlsPanel() {
		FunctionControlsPanel = new JPanel();
		FunctionControlsPanel.setBounds(242, 453, 267, 23);
		add(FunctionControlsPanel);
		FunctionControlsPanel.setLayout(null);
		
		btnChange = new JButton("\u4FEE\u6539");
		btnChange.setBounds(0, 0, 93, 23);
		FunctionControlsPanel.add(btnChange);
		
		btnDelete = new JButton("\u5220\u9664");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=searchResultTable.getSelectedRow();
				if(selectedRow<0) {
					JOptionPane.showMessageDialog(null, "请先选中一条记录！");
					return;
				}
				int c=(int) ((CustomizedTableModel) searchResultTable.getModel()).getValueAt(selectedRow,0);
				Book book=bookBll.getIDtoBook(c);
				if(!book.getBkStatus().equals("在馆"))
				{
					JOptionPane.showMessageDialog(null, "图书为借出状态，无法删除");
					return;
				}
				bookBll.delete(c);
				String SearchClass=(String) simpleSearchComboBox.getSelectedItem();
				String SearchItem=tfSearchItem.getText().trim();
				Book[] hits=bookBll.getBook(SearchClass,SearchItem);
				updateResultTable(hits);
				JOptionPane.showMessageDialog(null, "删除成功");
			}
		});
		btnDelete.setBounds(92, 0, 93, 23);
		FunctionControlsPanel.add(btnDelete);
		
		btnReturn = new JButton("\u8FD4\u56DE");
		btnReturn.setBounds(182, 0, 81, 23);
		FunctionControlsPanel.add(btnReturn);
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
	private void setReaderTypeToText(Book book) {
		tfBookName.setText(book.getBkName());
		tfBkPress.setText(book.getBkPress());
		tfBkAuthor.setText(book.getBkAuthor());
		tfBkCatalog.setText(book.getBkCatalog());
		tfBkBrief.setText(book.getBkBrief());
		
	}
	public JButton getChangeButton() {
		return this.btnChange;
	}
	public JTable getsearchResultTable() {
		return this.searchResultTable;
	}
	public BookAdmin getBookBll() {
		return this.bookBll;
	}
	public JButton getbtnReturn() {
		return this.btnReturn;
	}

}
