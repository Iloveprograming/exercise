package edu.yangtzeu.lmis.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.yangtzeu.lmis.model.AbstractModel;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;

public class ReaderDAL extends AbstractDAL{
	private String[] dispColNames=new String [] {
			"ID","姓名","性别","类型","单位","电话","email","状态","已借书数量","注册日期"};
	private String[] methodNames=new String [] {"getRdID",
			"getRdName","getRdSex","getRdType",
			"getRdDept","getRdPhone","getRdEmail",
			"getRdStatus","getRdBorrowQty","getRdDateReg"};
/*	private String[] dispColPerNames=new String[] {
		"ID","姓名","性别","类型","单位","权限","状态","注册日期"
	};
	private String[] methodPerNames=new String[] {
			"getRdID","getRdName","getRdSex","getRdType","getRdDept","getRdAdminRoles","getRdStatus","getRdDateReg"
	};*/
	
	public int add(AbstractModel object)throws Exception{
		if(object instanceof Reader==false) {
			throw new Exception("Can only handle Reader");
		}
		Reader rt=(Reader) object;
		String sql="insert into TB_Reader Values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params=new Object[13];
		params[0]=rt.getRdID();
		params[1]=rt.getRdName();
		params[2]=rt.getRdSex();
		params[3]=rt.getRdType();
		params[4]=rt.getRdDept();
		params[5]=rt.getRdPhone();
		params[6]=rt.getRdEmail();
		params[7]=rt.getRdDateReg();
		params[8]=rt.getRdPhoto();
		params[9]=rt.getRdStatus();
		params[10]=rt.getRdBorrowQty();
		params[11]=rt.getRdPwd();
		params[12]=rt.getRdAdminRoles();
		
		return SQLHelper.ExecSql(sql,params);
	}
	
		public int delete(AbstractModel object)throws Exception{
			if(object instanceof Reader==false) {
				throw new Exception("Can only handle ReaderType");
			}
			Reader rt=(Reader)object;
			String sql="delete from TB_ReaderType where rdType=?";
			Object[] params=new Object[] {rt.getRdType()};
			int rows=SQLHelper.ExecSql(sql,params);
			return rows;
		}
		public int update(AbstractModel object)throws Exception{
			if(!(object instanceof Reader)) {
				throw new Exception("Can only handle Reader");
			}
			Reader rt=(Reader)object;
			String sql="update TB_Reader set rdType=?,"
					+"rdName=?,rdSex=?,"
					+"rdDept=?,rdPhoto=?,"
					+"rdStatus=?,rdBorrowQty=?,rdPwd=?,rdAdminRoles=?,rdPhone=?,rdEmail=? where rdID=?";
			Object[] params=new Object[] {
					rt.getRdType(),
					rt.getRdName(),
					rt.getRdSex(),
					rt.getRdDept(),
					rt.getRdPhoto(),
					rt.getRdStatus(),
					rt.getRdBorrowQty(),
					rt.getRdPwd(),
					rt.getRdAdminRoles(),
					rt.getRdPhone(),
					rt.getRdEmail(),
					rt.getRdID()};
			return SQLHelper.ExecSql(sql,params);
			}
		
		public Reader getObjectByID(int rdID)throws SQLException{
			Reader rt=null;
			ResultSet rs=SQLHelper.getResultSet("select * from TB_Reader where rdID="
					+rdID);
			if(rs!=null) {
				if(rs.next()) {
					rt=initReader(rs);
				}
				rs.close();
			}
			return rt;
		}
		
		private Reader initReader(ResultSet rs)throws SQLException{
			Reader rt=new Reader();
			rt.setRdType(rs.getInt("rdType"));
			rt.setRdAdminRoles(rs.getInt("rdAdminRoles"));
			rt.setRdBorrowQty(rs.getInt("rdBorrowQty"));
			rt.setRdDateReg(rs.getDate("rdDateReg"));
			rt.setRdDept(rs.getString("rdDept"));
			rt.setRdID(rs.getInt("rdID"));
			rt.setRdName(rs.getString("rdName"));
			rt.setRdPwd(rs.getString("rdPwd"));
			rt.setRdStatus(rs.getString("rdStatus"));
			rt.setRdSex(rs.getString("rdSex"));
			rt.setRdPhoto(rs.getBytes("rdPhoto"));
			rt.setRdPhone(rs.getString("rdPhone"));
			rt.setRdEmail(rs.getString("rdEmail"));
			return rt;
		}
		
		public AbstractModel[] getAllObjects() throws Exception{
			ArrayList<Reader> objects=new ArrayList<Reader>();
			ResultSet rs=SQLHelper.getResultSet("select * from TB_Reader");
			if(rs!=null) {
				while(rs.next()) {
					Reader rt=initReader(rs);
					objects.add(rt);
				}
				rs.close();
			}
			Reader[] types=new Reader[objects.size()];
			objects.toArray(types);
			return types;
		}
		public String[] getPrettyColumNames() {
			return dispColNames;
		}
		public String[] getMethodNames() {
			return methodNames;
		}
		
		/*public String[] getPermissionNames() {
			return dispColPerNames;
		}
		
		public String[] getPerMethodNames() {
			return methodPerNames;
		}*/
		
		public Reader[] getReadersBy(int rdType,String deptTypeName,
				String userName) throws SQLException {
			ArrayList<Reader> readers=new ArrayList<Reader>();
			/*String sql="select * from TB_Reader WHERE rdType="+rdType+" and rdDept='"+deptTypeName+"' and"
					+ " rdName like '%"+userName+"%'";
					
			ResultSet rs=SQLHelper.getResultSet(sql);*/
			String sql="select * from TB_Reader WHERE rdType=? and "
					+"rdDept=? and rdName like ?";
			Object[] params=new Object[] {rdType,deptTypeName,"%"+userName+"%"};
			ResultSet rs=SQLHelper.getResultSet(sql,params);
			if(rs!=null) {
				while(rs.next()) {
					Reader reader=initReader(rs);
					readers.add(reader);
				}
				rs.close();
			}
			if(readers.size()>0) {
				Reader[] array=new Reader[readers.size()];
				readers.toArray(array);
				return array;
			}
			return null;
		}
		public int getRdType(String rdTypeName) throws SQLException {
			 String sql="select rdType from TB_ReaderType where rdTypeName=?";
			 Object[] params=new Object[] {rdTypeName};
			 ResultSet rs=SQLHelper.getResultSet(sql,params);
			 if(rs!=null) {
				 while(rs.next()) {
					 return rs.getInt("rdType");
				 }
			 }
			 return -1;
		}

		public String getRdTypeName(int rdType) throws SQLException {
			// TODO Auto-generated method stub
			String sql="select rdTypeName from TB_ReaderType where rdType=?";
			 Object[] params=new Object[] {rdType};
			 ResultSet rs=SQLHelper.getResultSet(sql,params);
			 if(rs!=null) {
				 while(rs.next()) {
					 return rs.getString("rdTypeName");
				 }
			 }
			return null;
		}
		public void deleteRdID(int rdID) {
			String sql="delete from TB_Reader where rdID="+rdID;
			SQLHelper.ExecSql(sql);
		}
		public Reader getReaderFromrdName(String Name) {
			String sql="select * from TB_Reader where RdName like "+"'%"+Name+"%'";
			ResultSet rs=SQLHelper.getResultSet(sql);
			if(rs!=null) {
				try {
					while(rs.next()) {
						Reader reader=initReader(rs);
						return reader;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}

		public void UpdateReaderAdminRoles(int rdAdminRoles, int rdID) {
			// TODO Auto-generated method stub
			String sql="Update TB_Reader set RdAdminRoles="+rdAdminRoles+" where RdID="+rdID;
			SQLHelper.ExecSql(sql);
		
		}
}
