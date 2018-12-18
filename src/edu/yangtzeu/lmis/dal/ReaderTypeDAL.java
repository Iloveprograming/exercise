package edu.yangtzeu.lmis.dal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import edu.yangtzeu.lmis.model.AbstractModel;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;
public class ReaderTypeDAL extends AbstractDAL{
	//临时
	private String[] dispColNames=new String [] {
			"ID","读者类型","可借数量","可借天数","可续借次数","罚金率","证件有效期"};
	private String[] methodNames=new String [] {"getRdType",
			"getRdTypeName","getCanLendQty","getCanLendDay",
			"getCanContinueTimes","getPunishRate","getDateValid"};
	
	public int add(AbstractModel object)throws Exception{
		if(object instanceof ReaderType==false) {
			throw new Exception("Can only handle ReaderType");
		}
		int existID[]=idCheck();
		ReaderType rt=(ReaderType) object;
		String sql="insert into TB_ReaderType(rdType,"
				+"rdTypeName,CanLendQty,CanLendDay,"
				+"CanContinueTimes,PunishRate,DateValid)"
				+"Values(?,?,?,?,?,?,?)";
		Object[] params=new Object[7];
		int ID=1;
		for(int i=0;i<existID.length;i++)
		{
			if(ID==existID[i])
				ID++;
		}
		
		params[0]=ID;
		params[1]=rt.getRdTypeName();
		params[2]=rt.getCanLendQty();
		params[3]=rt.getCanLendDay();
		params[4]=rt.getCanContinueTimes();
		params[5]=rt.getPunishRate();
		params[6]=rt.getDateValid();
		return SQLHelper.ExecSql(sql,params);
	}
		public int delete(AbstractModel object)throws Exception{
			if(object instanceof ReaderType==false) {
				throw new Exception("Can only handle ReaderType");
			}
			ReaderType rt=(ReaderType)object;
			String sql="delete from TB_ReaderType where rdType=?";
			Object[] params=new Object[] {rt.getRdType()};
			int rows=SQLHelper.ExecSql(sql,params);
			return rows;
		}
		public int update(AbstractModel object)throws Exception{
			if(object instanceof ReaderType==false) {
				throw new Exception("Can only handle ReaderType");
			}
			ReaderType rt=(ReaderType)object;
			String sql="update TB_ReaderType set rdTypeName=?,"
					+"CanLendQty=?,CanLendDay=?,"
					+"CanContinueTimes=?,PunishRate=?,"
					+"DateValid=? where rdType=?";
			Object[] params=new Object[] {
					rt.getRdTypeName(),
					rt.getCanLendQty(),
					rt.getCanLendDay(),
					rt.getCanContinueTimes(),
					rt.getPunishRate(),
					rt.getDateValid(),
					rt.getRdType()};
			return SQLHelper.ExecSql(sql,params);
			}
		public ReaderType getObjectByID(int rdType)throws SQLException{
			ReaderType rt=null;
			ResultSet rs=SQLHelper.getResultSet("select * from TB_ReaderType where rdType="
					+rdType);
			if(rs!=null) {
				if(rs.next()) {
					rt=initReaderType(rs);
				}
				rs.close();
			}
			return rt;
		}
		private ReaderType initReaderType(ResultSet rs)throws SQLException{
			ReaderType rt=new ReaderType();
			rt.setRdType(rs.getInt("rdType"));
			rt.setRdTypeName(rs.getString("rdTypeName"));
			rt.setCanLendQty(rs.getInt("CanLendQty"));
			rt.setCanLendDay(rs.getInt("CanLendDay"));
			rt.setCanContinueTimes(rs.getInt("CanContinueTimes"));
			rt.setPunishRate(rs.getFloat("PunishRate"));
			rt.setDateValid(rs.getInt("DateValid"));
			return rt;
		}
		public AbstractModel[] getAllObjects() throws Exception{
			ArrayList<ReaderType> objects=new ArrayList<ReaderType>();
			ResultSet rs=SQLHelper.getResultSet("select * from TB_ReaderType");
			if(rs!=null) {
				while(rs.next()) {
					ReaderType rt=initReaderType(rs);
					objects.add(rt);
				}
				rs.close();
			}
			ReaderType[] types=new ReaderType[objects.size()];
			objects.toArray(types);
			return types;
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
		public ReaderType[] getReadersBy() throws SQLException {
			// TODO Auto-generated method stub
			ArrayList<ReaderType> readerTypes=new ArrayList<ReaderType>();
			String sql="select * from TB_ReaderType";
			ResultSet rs=SQLHelper.getResultSet(sql);
			if(rs!=null) {
				while(rs.next()) {
					ReaderType readerType=initReaderType(rs);
					readerTypes.add(readerType);
				}
				rs.close();
			}
			if(readerTypes.size()>0) {
				ReaderType[] array=new ReaderType[readerTypes.size()];
				readerTypes.toArray(array);
				return array;
			}
			return null;
		}
		public int[] idCheck() throws SQLException {
			ArrayList<Integer> ID=new ArrayList<Integer>();
			ResultSet rs=SQLHelper.getResultSet("select rdType from TB_ReaderType");
			if(rs!=null) {
				while(rs.next()) {
					ID.add(rs.getInt("rdType"));
				}
				rs.close();
			}
			if(ID.size()>0) {
				int[] array=new int[ID.size()];
				for(int i=0;i<ID.size();i++)
				{
					array[i]=ID.get(i);
				}
				return array;
			}
			return null;
		}
}

