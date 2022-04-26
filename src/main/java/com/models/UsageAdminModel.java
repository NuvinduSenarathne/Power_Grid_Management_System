package com.models;
import java.lang.Integer;

public class UsageAdminModel {
	
	private int UsageID;
	private String RefNo;
	private int Units;
	private String Month;
	private String Amount;
	
	public UsageAdminModel() {
		super();
	}
	
	public UsageAdminModel(int UsageID,String RefNo, int units, String month, String Amount) {
		super();
		this.UsageID = UsageID;
		this.RefNo = RefNo;
		this.Month = month;
		this.Units = units;
		this.Amount = Amount;
		
	}

	public int getUsageID() {
		return UsageID;		
	}

	public void setUsageID(int usageid) {
		this.UsageID = usageid;		
	}
	
	public String getRefNo() {
		return RefNo;
	}
	public void setRefNo(String refno) {
		RefNo = refno;
	}
	public int getUnits() {
		return Units;
	}
	public void setUnits(int units) {
		this.Units = units;
	}
	public String getMonth() {
		return Month;
	}
	public void setMonth(String month) {
		this.Month = month;
	}
	
	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}
	
//	public float calculateAmount(int units) {
//
//		float amount = 0;
//		if (30<=units && units<=60) {
//			amount = (float) (units*2.50);
//		}
//		else if (units>60) {
//			amount = (float) ((30*2.50)+(units-30)*4.85);
//		}
//		
//		return amount;
//	}

}
