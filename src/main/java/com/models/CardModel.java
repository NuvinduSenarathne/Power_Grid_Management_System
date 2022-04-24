package com.models;


public class CardModel {
	
	
		private int cardID;
		private int cardNo;
		private String cardType;
		private int cvv;
		private String expDate;
		
		
		public CardModel() {
			super();
		}
		
		public CardModel(int cardID,int cardNo, String cardType, int cvv, String expDate) {
			super( );
			
			this.cardID=cardID;
			this.cardNo=cardNo;
			this.cardType=cardType;
			this.cvv=cvv;
			this.expDate=expDate;
			
		}
		
		public int getCardID() {
			return cardID;
		}
		public void setCardID(int cardID) {
			this.cardID = cardID;
		}

		public int getCardNo() {
			return cardNo;
		}

		public void setCardNo(int cardNo) {
			this.cardNo = cardNo;
		}

		public String getCardType() {
			return cardType;
		}

		public void setCardType(String cardType) {
			this.cardType = cardType;
		}

		public int getCvv() {
			return cvv;
		}

		public void setCvv(int cvv) {
			this.cvv = cvv;
		}

		public String getExpDate() {
			return expDate;
		}

		public void setExpDate(String expDate) {
			this.expDate = expDate;
		}

		
		
		
	}



