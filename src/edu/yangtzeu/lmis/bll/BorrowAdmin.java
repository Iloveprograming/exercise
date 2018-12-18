package edu.yangtzeu.lmis.bll;

import java.sql.SQLException;

import edu.yangtzeu.lmis.dal.BorrowDAL;
import edu.yangtzeu.lmis.model.Book;
import edu.yangtzeu.lmis.model.Borrow;

public class BorrowAdmin extends LibraryBLL{
	public BorrowAdmin() {
		dal=new BorrowDAL();
	}

	public Borrow[] retrieveBorrows(int rdID) {
		try {
			return ((BorrowDAL)dal).getObjectrdID(rdID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Book[] retrieveBooks(int bkID) {
		// TODO Auto-generated method stub
		try {
			return ((BorrowDAL)dal).getObjectbkID(bkID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Book[] retrieveBookName(String bkName) {
		// TODO Auto-generated method stub
		try {
			return ((BorrowDAL)dal).getObjectbkName(bkName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void add(Borrow borrow) {
		try {
			dal.add(borrow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Borrow getBorrow(int rdID, int c) {
		// TODO Auto-generated method stub
		return ((BorrowDAL)dal).getBorrow(rdID,c);
	}

	public void submitMoney(int rdID) {
		// TODO Auto-generated method stub
		((BorrowDAL)dal).submitMoney(rdID);
	}

	public int getLendBookNum(int rdID) {
		// TODO Auto-generated method stub
		return ((BorrowDAL)dal).getLendBookNum(rdID);
	}

	public void setIdOverDay(int rdID, int c) {
		// TODO Auto-generated method stub
		((BorrowDAL)dal).setIdOverDay(rdID,c);
	}
	

}
