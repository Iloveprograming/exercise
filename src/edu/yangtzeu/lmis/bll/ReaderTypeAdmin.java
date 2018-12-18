package edu.yangtzeu.lmis.bll;



import java.sql.SQLException;

import edu.yangtzeu.lmis.dal.ReaderDAL;
import edu.yangtzeu.lmis.dal.ReaderTypeDAL;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;

public class ReaderTypeAdmin extends LibraryBLL{

	public ReaderTypeAdmin() {
		dal=new ReaderTypeDAL();
	}
	public ReaderType[] getReaderTypes() {
		// TODO Auto-generated method stub
		try {
			/*return (ReaderType[])dal.getAllObjects();*/
			return (ReaderType[])dal.getAllObjects();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public ReaderType getObjectById(int rdType) {
		// TODO Auto-generated method stub
		try {
			return ((ReaderTypeDAL) dal).getObjectByID(rdType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public ReaderType[] retrieveReaders() {
		// TODO Auto-generated method stub
		try {
			return ((ReaderTypeDAL) dal).getReadersBy();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public ReaderType getReaderType(int rdTypeID) {
		// TODO Auto-generated method stub
		try {
			return (ReaderType)dal.getObjectByID(rdTypeID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void deleteReaderType(ReaderType readerType) {
		// TODO Auto-generated method stub
			try {
				dal.delete(readerType);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void upDateType(ReaderType readerType) {
		// TODO Auto-generated method stub
			try {
				dal.update(readerType);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void typeInsert(ReaderType readerType) {
		// TODO Auto-generated method stub
		try {
			dal.add(readerType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
