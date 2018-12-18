package edu.yangtzeu.lmis.bll;

import java.sql.SQLException;

import edu.yangtzeu.lmis.dal.BookDAL;
import edu.yangtzeu.lmis.dal.SQLHelper;
import edu.yangtzeu.lmis.model.Book;

public class BookAdmin extends LibraryBLL{
	public BookAdmin() {
		dal=new BookDAL();
	}

	public void bookInsert(Book book, int bookNum) {
		// TODO Auto-generated method stub
		try {
			((BookDAL)dal).add(book,bookNum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Book[] getBook(String SearchClass,String SearchItem) {
		try {
			return ((BookDAL)dal).getBookContent(SearchClass,SearchItem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Book[] highLevelSearch(String bookName, String bookPress, String bookAuthor, String bookCatalog,
			String bookBrief, String bookDatePress) {
				try {
					return ((BookDAL)dal).getHighLevelSearch(bookName,bookPress,bookAuthor,bookCatalog,bookBrief,bookDatePress);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
		// TODO Auto-generated method stub
		
		
	}
	public int languageToNum(String language) {
		String []language1= {"0-中文","1-英语","2-日语","3-法语","4-俄语","5-西班牙语"};
		if(language ==null)return -1;
		for(int i=0;i<language1.length;i++) {
			if(language.equals(language1[i]))
					return i;
		}
		return -1;
	}
	public String numToLanguage(int num) {
		String []language1= {"0-中文","1-英语","2-日语","3-法语","4-俄语","5-西班牙语"};
		
		for(int i=0;i<language1.length;i++) {
			if(num==i)
					return language1[i];
		}
		return null;
	}
	public String classToNum(String classNam) {
		if(classNam==null) {
			return null;
		}
		String []className={"自动化技术、计算机技术","计算技术、计算机技术","计算机软件","程序设计、软件工程","程序语言、算法语言","数据库理论与系统","数据库理论"};
		String []classNum= {"TP","TP3","TP31","TP311","TP312","TP311.13","TP311.131"};
		for(int i=0;i<className.length;i++) {
			if(classNam.equals(className[i]))
				return classNum[i];
		}
		return null;

	}
	public String numToClass(String classNumber) {
		if(classNumber==null) {
			return null;
		}
		String []className={"自动化技术、计算机技术","计算技术、计算机技术","计算机软件","程序设计、软件工程","程序语言、算法语言","数据库理论与系统","数据库理论"};
		String []classNum= {"TP","TP3","TP31","TP311","TP312","TP311.13","TP311.131"};
		for(int i=0;i<classNum.length;i++) {
			if(classNumber.equals(classNum[i]))
				return className[i];
		}
		return null;

	}
	public Book getIDtoBook(int c) {
		// TODO Auto-generated method stub
		try {
			return (Book)dal.getObjectByID(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public int getBkNum(String bookName) {
		try {
			return ((BookDAL)dal).getBkNum(bookName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public int getBkStart() {
		try {
			return ((BookDAL)dal).checkBkID();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}



	public void delete(int c) {
		// TODO Auto-generated method stub
		String sql="delete from TB_Book where bkID="+c;
		SQLHelper.ExecSql(sql);
		
	}
	public int Update(Book book) {
		try {
			return dal.update(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
