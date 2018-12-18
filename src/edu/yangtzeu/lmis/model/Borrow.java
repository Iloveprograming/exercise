package edu.yangtzeu.lmis.model;

import java.sql.Date;

public class Borrow extends AbstractModel{
	private long BorrowID;
	private int rdID;
	private int bkID;
	private int IdContinueTimes;
	private Date IdDateOut;
	private Date IdDateRetPlan;
	private Date IdDateRetAct;
	private int IdOverDay;
	private float IdOverMoney;
	private float IdPunishMoney;
	private boolean IsHasReturn;
	private String OperatorLend;
	private String OperatorRet;
	private String bkName;
	
	public Borrow() {
		
	}
	public long getBorrowId() {
		return BorrowID;
	}
	public void setBorrowId(long BorrowID) {
		this.BorrowID=BorrowID;
	}
	public int getRdID() {
		return rdID;
	}
	public void setRdID(int rdID) {
		this.rdID=rdID;
	}
	public int getBkID() {
		return bkID;
	}
	public void setBkID(int bkID) {
		this.bkID=bkID;
	}
	public int getIdContinueTimes(){
		return IdContinueTimes;
	}
	public void setIdContinueTimes(int IdContinueTimes) {
		this.IdContinueTimes=IdContinueTimes;
	}
	public Date getIdDateOut() {
		return IdDateOut;
	}
	public void setIdDateOut(Date IdDateOut) {
		this.IdDateOut=IdDateOut;
	}
	public Date getIdDateRetPlan() {
		return IdDateRetPlan;
	}
	public void setIdDateRetPlan(Date IdDateRetPlan) {
		this.IdDateRetPlan=IdDateRetPlan;
	}
	public Date getIdDateRetAct() {
		return IdDateRetAct;
	}
	public void setIdDateRetAct(Date IdDateRetAct) {
		this.IdDateRetAct=IdDateRetAct;
	}
	public int getIdOverDay() {
		return IdOverDay;
	}
	public void setIdOverDay(int IdOverDay) {
		this.IdOverDay=IdOverDay;
	}
	public float getIdOverMoney() {
		return IdOverMoney;
	}
	public void setIdOverMoney(float IdOverMoney) {
		this.IdOverMoney=IdOverMoney;
	}
	public float getIdPunishMoney() {
		return IdPunishMoney;
	}
	public void setIdPunishMoney(float IdPunishMoney) {
		this.IdPunishMoney=IdPunishMoney;
	}
	public boolean getIsHasReturn() {
		return IsHasReturn;
	}
	public void setIsHasReturn(boolean IsHasReturn) {
		this.IsHasReturn=IsHasReturn;
	}
	public String getOperatorLend() {
		return OperatorLend;
	}
	public void setOperatorLend(String OperatorLend) {
		this.OperatorLend=OperatorLend;
	}
	public String getOperatorRet() {
		return OperatorRet;
	}
	public void setOperatorRet(String OperatorRet) {
		this.OperatorRet=OperatorRet;
	}
	public void setBkName(String bkName) {
		this.bkName=bkName;
	}
	public String getBkName() {
		return bkName;
	}
}
