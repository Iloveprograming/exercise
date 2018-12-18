package edu.yangtzeu.lmis.bll;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.yangtzeu.lmis.dal.AbstractDAL;
import edu.yangtzeu.lmis.dal.ReaderDAL;
import edu.yangtzeu.lmis.dal.SQLHelper;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;

public class ReaderAdmin extends LibraryBLL{
	public ReaderAdmin() {
		dal=new ReaderDAL();
	}
		public Reader getReader(int rdID) {
		try {
			return (Reader)dal.getObjectByID(rdID);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Reader[] retrieveReaders(String rdTypeName,String deptTypeName,String userName)
	{
		int rdType = 0;
		try {
			rdType = ((ReaderDAL) dal).getRdType(rdTypeName);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			return ((ReaderDAL) dal).getReadersBy(rdType,deptTypeName,userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String getRdTyName(int rdType) {
		try {
			return ((ReaderDAL)dal).getRdTypeName(rdType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public int getRdType(String rdTypeName) {
		try {
			return ((ReaderDAL)dal).getRdType(rdTypeName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public void updateReader(Reader reader) {
		// TODO Auto-generated method stub
		try {
			dal.update(reader);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insertReader(Reader reader) {
		// TODO Auto-generated method stub
		try {
			dal.add(reader);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public int getMaxRdID(){
		  String sql="select MAx(rdID) from TB_Reader";
		  ResultSet rs=SQLHelper.getResultSet(sql);
		  if(rs!=null) {
			  try {
				while(rs.next()) {
					  return rs.getInt(1);
				  }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		return -1;
	  }
	 public void reportLoss(int rdID,String a) {
		 String sql="update TB_Reader set rdStatus="+"'"+a+"'"+" where rdID="+rdID;
		 SQLHelper.ExecSql(sql);
	 }
	 public void delete(int rdID) {
		 ((ReaderDAL) dal).deleteRdID(rdID);
	 }
	public void LendBook(int rdID) {
		// TODO Auto-generated method stub
		String sql="update TB_Reader set rdBorrowQty=rdBorrowQty+1 where rdID="+rdID;
		 SQLHelper.ExecSql(sql);
	}
	public void updateReaderStatus(int rdID) {
		// TODO Auto-generated method stub
		String sql="update TB_Reader set rdStatus='·£¿î'  where rdID="+rdID;
		SQLHelper.ExecSql(sql);
	}
	
	public void UpdateReaderPwd(int rdID, char[] pwd) {
		// TODO Auto-generated method stub
		String sql="update TB_Reader set rdPwd="+"'"+String.valueOf(pwd)+"'"+" where rdID="+rdID;
		SQLHelper.ExecSql(sql);
	}
	public Reader getReaderFromrdName(String Name) {
		return ((ReaderDAL)dal).getReaderFromrdName(Name);
	}
	public void UpdateReaderAdminRoles(int rdAdminRoles, int rdID) {
		// TODO Auto-generated method stub
		((ReaderDAL)dal).UpdateReaderAdminRoles(rdAdminRoles,rdID);
		
	}
}
