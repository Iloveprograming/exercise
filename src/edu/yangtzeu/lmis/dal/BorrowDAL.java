package edu.yangtzeu.lmis.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.yangtzeu.lmis.model.AbstractModel;
import edu.yangtzeu.lmis.model.Book;
import edu.yangtzeu.lmis.model.Borrow;
import edu.yangtzeu.lmis.model.Reader;

public class BorrowDAL extends AbstractDAL{
	private String[] dispColNames=new String [] {
			"图书序号","图书名称","续借次数","借阅日期","应还日期","还书日期","超期天数","超期金额（元）"};
	private String[] methodNames=new String [] {"getBkID",
			"getBkName","getIdContinueTimes","getIdDateOut",
			"getIdDateRetPlan","getIdDateRetAct","getIdOverDay","getIdOverMoney"};
	@Override
	public AbstractModel[] getAllObjects() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(AbstractModel object) throws Exception {
		// TODO Auto-generated method stub
		if(object instanceof Reader==false) {
			throw new Exception("Can only handle Reader");
		}
		Borrow rt=(Borrow) object;
		String sql="insert into TB_Borrow values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params=new Object[14];
		params[0]=rt.getBorrowId();
		params[1]=rt.getRdID();
		params[2]=rt.getBkID();
		params[3]=rt.getIdContinueTimes();
		params[4]=rt.getIdDateOut();
		params[5]=rt.getIdDateRetPlan();
		params[6]=rt.getIdDateRetAct();
		params[7]=rt.getIdOverDay();
		params[8]=rt.getIdOverMoney();
		params[9]=rt.getIdPunishMoney();
		params[10]=rt.getIsHasReturn();
		params[11]=rt.getOperatorLend();
		params[12]=rt.getOperatorRet();
		params[13]=rt.getBkName();
		return SQLHelper.ExecSql(sql,params);
	}

	@Override
	public int delete(AbstractModel object) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AbstractModel object) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AbstractModel getObjectByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
	public Borrow[] getObjectrdID(int id) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Borrow> objects=new ArrayList<Borrow>();
		ResultSet rs=SQLHelper.getResultSet("select * from TB_Borrow where rdID="+id);
		if(rs!=null) {
			while(rs.next()) {
				Borrow borrow=initBorrow(rs);
				objects.add(borrow);
			}
			rs.close();
		}
		if(objects.size()>0) {
			Borrow[] array=new Borrow[objects.size()];
			objects.toArray(array);
			return array;
		}
		return null;
	}

	private Borrow initBorrow(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Borrow rt=new Borrow();
		rt.setBorrowId(rs.getLong("BorrowID"));
		rt.setRdID(rs.getInt("rdID"));
		rt.setBkID(rs.getInt("bkID"));
		rt.setIdContinueTimes(rs.getInt("IdContinueTimes"));
		rt.setIdDateOut(rs.getDate("IdDateOut"));
		rt.setIdDateRetAct(rs.getDate("IdDateRetAct"));
		rt.setIdOverDay(rs.getInt("IdOverDay"));
		rt.setIdOverMoney(rs.getFloat("IdOverMoney"));
		rt.setIdPunishMoney(rs.getFloat("IdPunishMoney"));
		rt.setIsHasReturn(rs.getBoolean("IsHasReturn"));
		rt.setOperatorLend(rs.getString("OperatorLend"));
		rt.setOperatorRet(rs.getString("OperatorRet"));
		rt.setBkName(rs.getString("bkName"));
		rt.setIdDateRetPlan(rs.getDate("IdDateRetPlan"));
		return rt;
	}

	public Book[] getObjectbkID(int bkID) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Book> objects=new ArrayList<Book>();
		ResultSet rs=SQLHelper.getResultSet("select * from TB_Book where bkID="+bkID);
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

	private Book initBook(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
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

	public Book[] getObjectbkName(String bkName) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Book> objects=new ArrayList<Book>();
		ResultSet rs=SQLHelper.getResultSet("select * from TB_Book where bkName like "+"'%"+bkName+"%'");
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

	public Borrow getBorrow(int rdID, int c) {
		// TODO Auto-generated method stub
		String sql="select * from TB_Borrow where rdID="+rdID+" and bkID="+c;
		ResultSet rs=SQLHelper.getResultSet(sql);
		if(rs!=null) {
			try {
				while(rs.next()) {
					Borrow borrow=initBorrow(rs);
					return borrow;
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public void submitMoney(int rdID) {
		// TODO Auto-generated method stub
		String sql="update TB_Reader set rdStatus='有效' where rdID="+rdID;
		SQLHelper.ExecSql(sql);
	}

	public int getLendBookNum(int rdID) {
		// TODO Auto-generated method stub
		String sql="select count(bkID) from TB_Borrow where rdID="+rdID+" and IdOverDay>0";
		ResultSet rs=SQLHelper.getResultSet(sql);
		if(rs!=null) {
			try {
				while(rs.next()) {
					try {
						return rs.getInt(1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}

	public void setIdOverDay(int rdID, int c) {
		// TODO Auto-generated method stub
		String sql="Update TB_Borrow set IdOverDay=0 where rdID="+rdID+" and bkID="+c;
		SQLHelper.ExecSql(sql);
	}

	
}
