package edu.yangtzeu.lmis.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.yangtzeu.lmis.model.AbstractModel;
import edu.yangtzeu.lmis.model.Book;
import edu.yangtzeu.lmis.model.Reader;


public class BookDAL extends AbstractDAL{
	private String[] dispColNames=new String [] {
			"图书ID","索书号","书名","作者","出版社","出版日期","ISBN","分类号","语种","页数","价格","入馆时间","状态"};
	private String[] methodNames=new String [] {"getBkId",
			"getBkCode","getBkName","getBkAuthor",
			"getBkPress","getBkDatePress","getBkISBN",
			"getBkCatalog","getBkLanguage","getBkPages","getBkPrice","getBkDateIn","getBkStatus"};
	

	@Override
	public AbstractModel[] getAllObjects() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(AbstractModel object) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	public void add(AbstractModel object,int num) throws Exception {
		if(object instanceof Book==false) {
			throw new Exception("Can only handle ReaderType");
		}
		int nowmax=checkBkID();
		Book rt=(Book) object;
		for(int i=0;i<num;i++) {
		String sql1="set identity_insert TB_Book ON";
		String sql2=" insert into TB_Book(bkID,bkCode,"
				+"bkName,bkAuthor,bkPress,bkDatePress,"
				+"bkISBN,bkCatalog,bkLanguage,bkPrice,bkBrief,bkCover)"
				+"Values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params=new Object[12];
		params[0]=nowmax+i+1;
		params[1]=String.valueOf(Integer.valueOf(rt.getBkCode())+i);
		params[2]=rt.getBkName();
		params[3]=rt.getBkAuthor();
		params[4]=rt.getBkPress();
		params[5]=rt.getBkDatePress();
		params[6]=rt.getBkISBN();
		params[7]=rt.getBkCatalog();
		params[8]=rt.getBkLanguage();
		params[9]=rt.getBkPrice();
		params[10]=rt.getBkBrief();
		params[11]=rt.getBkCover();
		String sql=sql1+sql2;
		SQLHelper.ExecSql(sql,params);
		}
		
	}
	@Override
	public int delete(AbstractModel object) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AbstractModel object) throws Exception {
		// TODO Auto-generated method stub
		Book rt=(Book) object;
		String sql="update TB_Book set bkCode=?,"+ 
				"bkName=?,bkAuthor=?,bkPress=?,bkDatePress=?," 
			+"bkISBN=?,bkCatalog=?,bkLanguage=?,bkPrice=?,bkBrief=?,bkPages=?,bkCover=? where "
			+ "bkID=? ";
		Object[] params=new Object[13];
		
		params[0]=String.valueOf(Integer.valueOf(rt.getBkCode()));
		params[1]=rt.getBkName();
		params[2]=rt.getBkAuthor();
		params[3]=rt.getBkPress();
		params[4]=rt.getBkDatePress();
		params[5]=rt.getBkISBN();
		params[6]=rt.getBkCatalog();
		params[7]=rt.getBkLanguage();
		params[8]=rt.getBkPrice();
		params[9]=rt.getBkBrief();
		params[10]=rt.getBkPages();
		params[11]=rt.getBkCover();
		params[12]=rt.getBkId();
		SQLHelper.ExecSql(sql,params);
		return 0;
	}

	@Override
	public AbstractModel getObjectByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		Book rt=null;
		ResultSet rs=SQLHelper.getResultSet("select * from TB_Book where bkID="+id);
		if(rs!=null) {
			if(rs.next()) {
				rt=initBook(rs);
			}
			rs.close();
		}
		return rt;
		
	}
	public int getBkNum(String bookName) throws SQLException {
		ResultSet rs=SQLHelper.getResultSet("select MAX(bkID),MIN(bkID) from TB_Book where bkName= "+"'"+bookName+"'");
		if(rs!=null) {
			if(rs.next()) {
				int max=rs.getInt(1);
				int min=rs.getInt(2);
				return (max-min+1);
			}
		}
		return -1;
	}

	@Override
	public String[] getPrettyColumNames() {
		// TODO Auto-generated method stub
		return dispColNames;
	}

	@Override
	public String[] getMethodNames() {
		// TODO Auto-generated method stub
		return methodNames;
	}
	public int checkBkID() throws SQLException {
		ResultSet rs=SQLHelper.getResultSet("select MAX(bkID) from TB_Book");
		if(rs!=null) {
				if(rs.next()) {
					return rs.getInt(1);
				}
				rs.close();
			}
		return -1;	
	}
	private Book initBook(ResultSet rs)throws SQLException{
		Book rt=new Book();
		rt.setBkID(rs.getInt("bkID"));
		rt.setBkCode(rs.getString("bkCode"));
		rt.setBkName(rs.getString("bkName"));
		rt.setBkAuthor(rs.getString("bkAuthor"));
		rt.setBkPress(rs.getString("bkPress"));
		rt.setBkDatePress(rs.getDate("bkDatePress"));
		rt.setBkISBN(rs.getString("bkISBN"));
		rt.setBkCatalog(rs.getString("bkCatalog"));
		rt.setBkLanguage(rs.getInt("bkLanguage"));
		rt.setBkPages(rs.getInt("bkPages"));
		rt.setBkPrice(rs.getFloat("bkPrice"));
		rt.setBkDateIn(rs.getDate("bkDateIn"));
		rt.setBkStatus(rs.getString("bkStatus"));
		rt.setBkBrief(rs.getString("bkBrief"));
		rt.setBkCover(rs.getBytes("bkCover"));
		return rt;
	}
	public Book[] getBookContent(String SearchClass,String SearchItem) throws SQLException {
		ArrayList<Book> objects=new ArrayList<Book>();
		String searchID=searchClassToID(SearchClass);
		System.out.println(searchID);
		System.out.println(SearchItem);
		String sql="select * from TB_Book where "+searchID+" like ?";
		Object[] params=new Object[] {"%"+SearchItem+"%"};
		ResultSet rs=SQLHelper.getResultSet(sql,params);
		if(rs!=null) {
			while(rs.next()) {
				Book book=initBook(rs);
				objects.add(book);
			}
			rs.close();
		}
		if(objects.size()>0) {
			Book[] array=new Book[objects.size()];
			objects.toArray(array);
			return array;
		}
		return null;
		
	}
	public String searchClassToID(String SearchClass) {
		String SearchID[]= {"bkCode","bkName","bkAuthor","bkPress","bkCatalog","bkLanguage"};
		String Searchclass[]= {"索书号","书名","作者","出版社","分类","语种"};
		for(int i=0;i<SearchID.length;i++) {
			if(SearchClass.equals(Searchclass[i]))
				return SearchID[i];
		}
		return null;
	}
	public Book[] getHighLevelSearch(String bookName, String bookPress, String bookAuthor, String bookCatalog,
			String bookBrief, String bookDatePress) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Book> objects=new ArrayList<Book>();
		
		String Bookcontact="bkName like";
		String Presscontact="bkPress like";
		String Authorcontact="bkAuthor like";
		String Catalogcontact="bkCatalog like";
		String Briefcontact="bkBrief like";
		String DatePresscontact="bkDatePress like";
		if(bookName.equals(""))
			{
			bookName="";
			Bookcontact="1=1";
			}
		else 
			bookName="'%"+bookName+"%'";
			
		
		
		if(bookPress.equals(""))
			{
				bookPress="";
				Presscontact="1=1";
			}
			else 
				bookPress="'%"+bookPress+"%'";
				
		if(bookAuthor.equals(""))
			{
				bookAuthor="";
				Authorcontact="1=1";
			}
			else 
				bookAuthor="'%"+bookAuthor+"%'";
				
			
		if(bookCatalog.equals(""))
			{
				bookCatalog="";
				Catalogcontact="1=1";
			}
			else 
				bookCatalog="'%"+bookCatalog+"%'";
		
		if(bookBrief.equals(""))
			{
				bookBrief="";
				Briefcontact="1=1";
			}
		
			else 
				bookBrief="'%"+bookBrief+"%'";
			
		if(bookDatePress.equals(""))
			{
				bookDatePress="";
				DatePresscontact="1=1";
			}
			else 
				bookDatePress="'%"+bookDatePress+"%'";
				
		System.out.println(Presscontact);
		System.out.println(bookPress);
		String sql="select bkID,bkCode,bkName,bkAuthor,"
				+"bkPress,bkDatePress,bkISBN,bkCatalog,bkLanguage,bkPages,bkPrice,"
				+ "bkDateIn,bkBrief,bkCover,bkStatus from TB_Book where "+Bookcontact+" "+bookName+" "+" and "+Presscontact+
						 " "+bookPress+" and  "+Authorcontact+" "+bookAuthor+" and  "+Catalogcontact+" "+bookCatalog
								+ " and  "+Briefcontact+" "+bookBrief+" and  "+DatePresscontact+" "+bookDatePress;
System.out.println(sql);

		ResultSet rs=SQLHelper.getResultSet(sql);
		if(rs!=null) {
			while(rs.next()) {
				Book book=initBook(rs);
				objects.add(book);
			}
			rs.close();
		}
		if(objects.size()>0) {
			Book[] array=new Book[objects.size()];
			objects.toArray(array);
			return array;
		}
		return null;
	}


	

	
}
