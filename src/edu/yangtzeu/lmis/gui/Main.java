package edu.yangtzeu.lmis.gui;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import edu.yangtzeu.lmis.bll.BookAdmin;
import edu.yangtzeu.lmis.gui.commons.CustomizedTableModel;

import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

public class Main extends JFrame {
	private JMenu MN_ReaderMgt;
	private JMenu MN_BookMgt;
	private JMenu MN_BorrowMgt;
	private JMenu MN_UserPerMgt;
	
	private final String blankPanelName="Blank";
	private final String readerPanelName="Reader";
	private final String readerTypePanelName="ReaderType";
	private final String bookPanelName="Book";

	private final String SearchBookName="SearchBook";
	private final String pwdChangePanelName="PwdChange";
	private final String permissionMgtPanelName="Permission";
	
	private JPanel cards;
	private ReaderPanel readerPanel;
	private ReaderTypePanel readerTypePanel;
	private BookPanel bookPanel;
	private SearchBookPanel searchbookPanel;
	private PwdChangePanel pwdchangePanel;
	private PermissionMgtPanel permissionMgtPanel;
	private BlankPanel blankPanel;
	private JMenuItem MI_SwitchUser;
	private JMenu MN_UserMgt;
	
	
	public Main() {
		    
		    JMenuBar menuBar = new JMenuBar();
		    menuBar.setBounds(0, 0, 784, 21);
		    getContentPane().add(menuBar);
		    
		     MN_BookMgt = new JMenu("\u56FE\u4E66\u7BA1\u7406");
		     menuBar.add(MN_BookMgt);
		     
		     JMenuItem MI_NewBook = new JMenuItem("\u65B0\u4E66\u5165\u5E93");
		     MI_NewBook.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent e) {
		     		CardLayout cl=(CardLayout)(cards.getLayout());
		      		cl.show(cards, bookPanelName);
		      		bookPanel.SetText();
		      		bookPanel.setStatus(2);
		     	}
		     });
		     MN_BookMgt.add(MI_NewBook);
		     
		     
		     JMenuItem MI_SearchBook = new JMenuItem("\u56FE\u4E66\u67E5\u8BE2");
		     MI_SearchBook.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent e) {
		     	CardLayout cl=(CardLayout)(cards.getLayout());
	      		cl.show(cards, SearchBookName);
		     	}
		     });
		     MN_BookMgt.add(MI_SearchBook);
		     
		      MN_ReaderMgt = new JMenu("\u8BFB\u8005\u7BA1\u7406");
		      menuBar.add(MN_ReaderMgt);
		      
		      JMenuItem MI_NewReader = new JMenuItem("\u56FE\u4E66\u8BC1\u7BA1\u7406");
		      MI_NewReader.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		CardLayout cl=(CardLayout)(cards.getLayout());
		      		cl.show(cards, readerPanelName);
		      	}
		      });
		      MN_ReaderMgt.add(MI_NewReader);
		      
		      JMenuItem MI_ReaderTypeMgt = new JMenuItem("\u8BFB\u8005\u7C7B\u578B\u7BA1\u7406");
		      MI_ReaderTypeMgt.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		CardLayout cl=(CardLayout)(cards.getLayout());
		      		cl.show(cards, readerTypePanelName);
		      	}
		      });
		      MN_ReaderMgt.add(MI_ReaderTypeMgt);
		      
		       MN_BorrowMgt = new JMenu("\u501F\u9605\u7BA1\u7406");
		       menuBar.add(MN_BorrowMgt);
		       
		       JMenuItem MI_Borror = new JMenuItem("\u501F\u4E66");
		       MI_Borror.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		       		loadBorrowGUI();
		       	}
		       });
		       MN_BorrowMgt.add(MI_Borror);
		       
		       
		        MN_UserPerMgt = new JMenu("\u7528\u6237\u6743\u9650\u7BA1\u7406");
		        menuBar.add(MN_UserPerMgt);
		        
		        JMenuItem MI_PermissionMgt = new JMenuItem("\u6743\u9650\u7BA1\u7406");
		        MI_PermissionMgt.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		CardLayout cl=(CardLayout)(cards.getLayout());
			      		cl.show(cards, permissionMgtPanelName);

		        	}
		        });
		        MN_UserPerMgt.add(MI_PermissionMgt);
		        
		        MN_UserMgt = new JMenu("\u7528\u6237\u7BA1\u7406");
		        menuBar.add(MN_UserMgt);
		        
		        JMenuItem MI_UpdatePassword = new JMenuItem("\u5BC6\u7801\u4FEE\u6539");
		        MN_UserMgt.add(MI_UpdatePassword);
		        
		        MI_SwitchUser = new JMenuItem("\u5207\u6362\u7528\u6237");
		        MN_UserMgt.add(MI_SwitchUser);
		        MI_SwitchUser.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		dispose();
		        		loadLoginGUI();
		        		
		        	}
		        });
		        MI_UpdatePassword.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		CardLayout cl=(CardLayout)(cards.getLayout());
		        		cl.show(cards, pwdChangePanelName);
		        	}
		        });
		        
		setSize(new Dimension(800, 600));
		setTitle("\u957F\u6C5F\u5927\u5B66\u56FE\u4E66\u9986\u7BA1\u7406\u4FE1\u606F\u7CFB\u7EDF");
		/*initComponents();*/
		initMenu();
		initCardPanels();
	}
	private void initMenu() {
		MN_ReaderMgt.setEnabled(Login.reader.isReaderAdmin());
		MN_BookMgt.setEnabled(Login.reader.isBookAdmin());
		MN_BorrowMgt.setEnabled(Login.reader.isBorrowAdmin());
		MN_UserPerMgt.setEnabled(Login.reader.isSysAdmin());
	}
	private void initCardPanels() {
		 blankPanel=new BlankPanel();
	        
	        readerPanel=new ReaderPanel();
	        readerPanel.setVisible(false);
	        
	        readerTypePanel=new ReaderTypePanel();
	        readerTypePanel.setVisible(false);
	        
	        
	        cards=new JPanel(new CardLayout());
	        getContentPane().add(cards);
	        cards.add(blankPanel,blankPanelName);
	        cards.add(readerPanel,readerPanelName);
	        cards.add(readerTypePanel,readerTypePanelName);
	        bookPanel=new BookPanel();
	        bookPanel.setVisible(false);
	        pwdchangePanel=new PwdChangePanel();
	        pwdchangePanel.setVisible(false);
	        cards.add(pwdchangePanel,pwdChangePanelName);
	        
	        permissionMgtPanel=new PermissionMgtPanel();
	        permissionMgtPanel.setVisible(false);
	        cards.add(permissionMgtPanel,permissionMgtPanelName);
	        
	        searchbookPanel=new SearchBookPanel();
	        searchbookPanel.setVisible(false);
	        cards.add(searchbookPanel,SearchBookName);
	      
	        cards.add(bookPanel,bookPanelName);
	        JButton close=readerPanel.getReturnBtn();
	        close.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	CardLayout cl=(CardLayout)(cards.getLayout());
		      		cl.show(cards, blankPanelName);
	            }
	        });
	        JButton btnBookCancel = bookPanel.getbtnCancel();
	        btnBookCancel.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	CardLayout cl=(CardLayout)(cards.getLayout());
		      		cl.show(cards, SearchBookName);
	            }
	        });
	        JButton btnBookReturn = bookPanel.getbtnReturn();
	        btnBookReturn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	CardLayout cl=(CardLayout)(cards.getLayout());
		      		cl.show(cards, SearchBookName);
	            }
	        });
	        
	        JButton btn = searchbookPanel.getChangeButton();
	        btn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	JTable searchbookResultTable=searchbookPanel.getsearchResultTable();
	            	int selectedRow=searchbookResultTable.getSelectedRow();
					if(selectedRow<0) {
						JOptionPane.showMessageDialog(null, "请先选中一条记录！");
						return;
					}
					int c=(int) ((CustomizedTableModel) searchbookResultTable.getModel()).getValueAt(selectedRow,0);
					
					BookAdmin bookBll=searchbookPanel.getBookBll();
					bookPanel.setBookToText(bookBll.getIDtoBook(c));
	            	
					CardLayout cl=(CardLayout)(cards.getLayout());
		      		cl.show(cards, bookPanelName);
		      		bookPanel.setStatus(1);
	            }
	        });
	        
	        JButton btnreturn1 = pwdchangePanel.getReturnBtn();
	        btnreturn1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	CardLayout cl=(CardLayout)(cards.getLayout());
		      		cl.show(cards, blankPanelName);
	            }
	        });
	        JButton btnreturn2 = permissionMgtPanel.getbtnReturn();
	        btnreturn2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	CardLayout cl=(CardLayout)(cards.getLayout());
		      		cl.show(cards, blankPanelName);
	            }
	        });
	        
	        JButton btnreturn3 = readerTypePanel.getbtnreturn();
	        btnreturn3.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	CardLayout cl=(CardLayout)(cards.getLayout());
		      		cl.show(cards, blankPanelName);
	            }
	        });
	        JButton btnreturn4 = searchbookPanel.getbtnReturn();
	        btnreturn4.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	CardLayout cl=(CardLayout)(cards.getLayout());
		      		cl.show(cards, blankPanelName);
	            }
	        });
	        
	}
	private void loadBorrowGUI() {
		
		BorrowWindows borrowGUI=new BorrowWindows();
		borrowGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borrowGUI.setLocationRelativeTo(null);
		borrowGUI.setVisible(true);
	}
	private void loadLoginGUI() {
		Login loginGUI=new Login();
		loginGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginGUI.setLocationRelativeTo(null);
		loginGUI.setVisible(true);
	}
}
