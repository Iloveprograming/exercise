package edu.yangtzeu.lmis.bll;

import edu.yangtzeu.lmis.dal.AbstractDAL;
import edu.yangtzeu.lmis.dal.ReaderDAL;

public abstract class LibraryBLL {
	protected AbstractDAL dal;
	public String[] getDisplayColumnNames() {
		return dal.getPrettyColumNames();
	}
	public String[] getMethodNames() {
		return dal.getMethodNames();
	}
	/*public String[] getDisplayPermitssionNames() {
		return ((ReaderDAL)dal).getPermissionNames();
	}
	public String[] getPermitssionMethodNames() {
		return ((ReaderDAL)dal).getPerMethodNames();
	}*/
}
