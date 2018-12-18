package edu.yangtzeu.lmis.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import edu.yangtzeu.lmis.bll.ReaderTypeAdmin;
import edu.yangtzeu.lmis.gui.commons.CustomizedTableModel;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;

import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class ReaderTypePanel extends JPanel {
	private JTextField tfRdTypeName;
	private JTextField tfCanContinueTimes;
	private JTextField tfCanLendQty;
	private JTextField tfPunishRate;
	private JTextField tfCanLendDay;
	private JTextField tfDateValid;
	private JScrollPane searchResultScrollPane; 
	private ReaderTypeAdmin readerTypeBll=new ReaderTypeAdmin();
	private JTable searchResultTable;
	
	private JButton btnreturn;
	private JPanel SearchResultPanel;
	private JPanel initFunctionControlsPanel;
	private JPanel SearchPanel;
	private JButton btnDelete;
	public ReaderTypePanel() {
		setLayout(null);
		//查询条件模块儿initSearchPanel()
		
		//查询结果模块儿initSearchResultPanel()
		
		//功能按钮模块儿initFunctionControlsPanel()
		initSearchPanel();
		initSearchResultPanel();
		initFunctionControlsPanel();
		
	}
	private void initSearchPanel() {

		SearchPanel = new JPanel();
		SearchPanel.setBounds(10, 47, 597, 73);
		add(SearchPanel);
		SearchPanel.setLayout(null);
		
		JLabel tfRd = new JLabel("\u7C7B\u578B\u540D\u79F0\uFF1A");
		tfRd.setBounds(10, 10, 70, 15);
		SearchPanel.add(tfRd);
		
		tfRdTypeName = new JTextField();
		tfRdTypeName.setBounds(90, 10, 66, 21);
		SearchPanel.add(tfRdTypeName);
		tfRdTypeName.setColumns(10);
		
		JLabel label_1 = new JLabel("\u53EF\u7EED\u501F\u6B21\u6570\uFF1A");
		label_1.setBounds(10, 40, 85, 15);
		SearchPanel.add(label_1);
		
		tfCanContinueTimes = new JTextField();
		tfCanContinueTimes.setBounds(90, 37, 66, 21);
		SearchPanel.add(tfCanContinueTimes);
		tfCanContinueTimes.setColumns(10);
		
		JLabel label_2 = new JLabel("\u53EF\u501F\u6570\u91CF\uFF1A");
		label_2.setBounds(166, 10, 85, 15);
		SearchPanel.add(label_2);
		
		tfCanLendQty = new JTextField();
		tfCanLendQty.setBounds(261, 10, 66, 21);
		SearchPanel.add(tfCanLendQty);
		tfCanLendQty.setColumns(10);
		
		JLabel label_3 = new JLabel("\u7F5A\u91D1\u7387\uFF1A");
		label_3.setBounds(164, 40, 72, 15);
		SearchPanel.add(label_3);
		
		tfPunishRate = new JTextField();
		tfPunishRate.setBounds(261, 37, 66, 21);
		SearchPanel.add(tfPunishRate);
		tfPunishRate.setColumns(10);
		
		JLabel label_4 = new JLabel("\u53EF\u501F\u5929\u6570\uFF1A");
		label_4.setBounds(337, 10, 83, 15);
		SearchPanel.add(label_4);
		
		tfCanLendDay = new JTextField();
		tfCanLendDay.setBounds(442, 10, 66, 21);
		SearchPanel.add(tfCanLendDay);
		tfCanLendDay.setColumns(10);
		
		JLabel label_5 = new JLabel("\u8BC1\u4EF6\u6709\u6548\u671F\uFF1A");
		label_5.setBounds(337, 37, 95, 15);
		SearchPanel.add(label_5);
		
		tfDateValid = new JTextField();
		tfDateValid.setBounds(443, 37, 66, 21);
		SearchPanel.add(tfDateValid);
		tfDateValid.setColumns(10);
		
	}
	private void initSearchResultPanel() {
		CustomizedTableModel<ReaderType> tableModel=new CustomizedTableModel<ReaderType>(
				readerTypeBll.getDisplayColumnNames(),readerTypeBll.getMethodNames());
		
		SearchResultPanel = new JPanel();
		SearchResultPanel.setBorder(new TitledBorder(null, "\u67E5\u8BE2\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		SearchResultPanel.setBounds(0, 142, 790, 332);
		add(SearchResultPanel);
		SearchResultPanel.setLayout(null);
		
		searchResultTable = new JTable(tableModel);
		searchResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		searchResultScrollPane = new JScrollPane(searchResultTable);
		searchResultScrollPane.setBounds(10,20,770,302);
		SearchResultPanel.add(searchResultScrollPane);
		ReaderType[] hits=readerTypeBll.retrieveReaders();
		
		updateResultTable(hits);
		searchResultTable.addMouseListener(new java.awt.event.MouseAdapter(){
             public void mouseClicked(MouseEvent e) {//仅当鼠标单击时响应
                //得到选中的行列的索引值
               int r= searchResultTable.getSelectedRow();
               int c=(int) ((CustomizedTableModel) searchResultTable.getModel()).getValueAt(r,0);
				setReaderTypeToText(readerTypeBll.getReaderType(c));
             }

         }); //在这个内部类中添加了mouseadaper这个类，这是没有注意的。

		
	}
	private void initFunctionControlsPanel() {
		initFunctionControlsPanel = new JPanel();
		initFunctionControlsPanel.setBounds(149, 501, 317, 38);
		add(initFunctionControlsPanel);
		initFunctionControlsPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReaderType readerType=getReaderFromText();
				readerTypeBll.typeInsert(readerType);
				ReaderType[] hits=readerTypeBll.retrieveReaders();
				
				updateResultTable(hits);
			}
		});
		btnNewButton.setBounds(0, 10, 72, 23);
		initFunctionControlsPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u4FEE\u6539");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=searchResultTable.getSelectedRow();
				if(selectedRow<0) {
					JOptionPane.showMessageDialog(null, "请先选中一条记录！");
					return;
				}
				int c=(int) ((CustomizedTableModel) searchResultTable.getModel()).getValueAt(selectedRow,0);
				ReaderType readerType=getReaderFromText(readerTypeBll.getReaderType(c));
				readerTypeBll.upDateType(readerType);
				JOptionPane.showMessageDialog(null, "修改成功！");
				
				ReaderType[] hits=readerTypeBll.retrieveReaders();
				
				updateResultTable(hits);
			}
		});
		btnNewButton_1.setBounds(69, 10, 63, 23);
		initFunctionControlsPanel.add(btnNewButton_1);
		
		btnDelete = new JButton("\u5220\u9664");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=searchResultTable.getSelectedRow();
				if(selectedRow<0) {
					JOptionPane.showMessageDialog(null, "请先选中一条记录！");
					return;
				}
				int c=(int) ((CustomizedTableModel) searchResultTable.getModel()).getValueAt(selectedRow,0);
				readerTypeBll.deleteReaderType(readerTypeBll.getReaderType(c));
				ReaderType[] hits=readerTypeBll.retrieveReaders();
				
				updateResultTable(hits);
			}
		});
		btnDelete.setBounds(129, 10, 63, 23);
		initFunctionControlsPanel.add(btnDelete);
		
		btnreturn = new JButton("\u8FD4\u56DE");
	
		btnreturn.setBounds(190, 10, 60, 23);
		initFunctionControlsPanel.add(btnreturn);
		
	}
	private void updateResultTable(ReaderType[] readerTypes) {
		if(readerTypes==null) {
			JOptionPane.showMessageDialog(null, "没有找到符合要求的记录:");
			return;
		}
		CustomizedTableModel<ReaderType> tableModel=(CustomizedTableModel<ReaderType>)searchResultTable.getModel();
		tableModel.setRecords(readerTypes);
		//更新表格
		tableModel.fireTableDataChanged();
}
	private void setReaderTypeToText(ReaderType readerType) {
		tfRdTypeName.setText(readerType.getRdTypeName());
		tfCanContinueTimes.setText(String.valueOf(readerType.getCanContinueTimes()));
		tfCanLendQty.setText(String.valueOf(readerType.getCanLendQty()));
		tfPunishRate.setText(String.valueOf(readerType.getPunishRate()));
		tfCanLendDay.setText(String.valueOf(readerType.getCanLendDay()));
		tfDateValid.setText(String.valueOf(readerType.getDateValid()));
	}
	private ReaderType getReaderFromText(ReaderType readerType) {
		readerType.setRdTypeName(tfRdTypeName.getText().trim());
		readerType.setCanContinueTimes(Integer.valueOf(tfCanContinueTimes.getText().trim()));
		readerType.setCanLendQty(Integer.valueOf(tfCanLendQty.getText().trim()));
		readerType.setPunishRate(Float.valueOf(tfPunishRate.getText().trim()));
		readerType.setCanLendDay(Integer.valueOf(tfCanLendDay.getText().trim()));
		readerType.setDateValid(Integer.valueOf(tfDateValid.getText().trim()));
		return readerType;
}
	private ReaderType getReaderFromText() {
		ReaderType readerType = new ReaderType();
		readerType.setRdTypeName(tfRdTypeName.getText().trim());
		if(tfCanContinueTimes.getText().trim().equals(""))
			readerType.setCanContinueTimes(0);
		else
		readerType.setCanContinueTimes(Integer.valueOf(tfCanContinueTimes.getText().trim()));
		
		if(tfCanLendQty.getText().trim().equals(""))
			readerType.setCanLendQty(0);
		else
		readerType.setCanLendQty(Integer.valueOf(tfCanLendQty.getText().trim()));
		
		if(tfPunishRate.getText().trim().equals(""))
			readerType.setPunishRate(0);
		else
		readerType.setPunishRate(Float.valueOf(tfPunishRate.getText().trim()));
		
		if(tfCanLendDay.getText().trim().equals(""))
			readerType.setCanLendDay(0);
		else
		readerType.setCanLendDay(Integer.valueOf(tfCanLendDay.getText().trim()));
		
		if(tfDateValid.getText().trim().equals(""))
			readerType.setDateValid(0);
		else
		readerType.setDateValid(Integer.valueOf(tfDateValid.getText().trim()));
		return readerType;
	}
	public JButton getbtnreturn() {
		return this.btnreturn;
	}
}
