package com.models;

public class PaymentModel {

	private int UsageID;
	private String RefNo;
	private int units;
	private String month;
	private int amount;
	
	public PaymentModel() {
		super();
	}
	
	public PaymentModel(int UsageID ,String RefNo, int units, String month , int amount) {
		
		super();
		this.UsageID = UsageID;
		this.RefNo = RefNo;
		this.month = month;
		this.units = units;
		this.amount=amount;
		
	}
	
	
	
	public String getRefNo() {
		return RefNo;
	}
	public void setRefNo(String refno) {
		RefNo = refno;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	
	public int getUsageID() {
		return UsageID;		
	}

	public  void setUsageID(int usageid) {
		this.UsageID = usageid;		
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	//...
	
}
