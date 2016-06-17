package ecom.common;

public interface FrequentUse {
	
	/*********************** Constants *****************************/
	public static final Integer MAX = 50;
	
	
	/*********************** JavaScript ****************************/

	public static final String jQuery   = "js/jquery-1.11.3.js";
	public static final String style    = "css/style.css";
	public static final String angular  = "js/angular.js";	
	
	
	/********************** PayUMoney ******************************/
	
	//PayUMoney Credentials
	public static final String payUMoneyKey = "50Z4cW";  public static final String payUMoneySalt = "bpJeeV02";  public static final String payUMoneyServiceProvider = "payu_paisa";
		
		
	/************************** Fedex ******************************/	
		
	
	public static final String TestAccountNumber       = "604501202";
	public static final String ProductionAccountNumber = "729620904";
	
	//FedEx Credentials - Test	
	public static final String fedExAccountNumber = TestAccountNumber;  public static final String fedExMeterNumber = "100281592";  public static final String fedExKey = "QCDvipuDZR1Knknb";  public static final String fedExPassword = "W9jhqLpT5wrCV9IuCgSqBlkZj";
	//FedEx Credentials - Production	
	//public static final String fedExAccountNumber = ProductionAccountNumber;  public static final String fedExMeterNumber = "108962648";  public static final String fedExKey = "UqHSsWY9jrGlrk41";  public static final String fedExPassword = "cLjz4E6Sgm4X8IH5dGUnWFLPw";
	
	
			//Amar Ranjan Das  011-47700209   8588864543
	
	
	//FedEx Credentials - Partha
	/*
	public static final String fedExAccountNumber = "510087402";
	public static final String fedExMeterNumber   = "100276864";
	public static final String fedExKey           = "EazJZ9F2y1kw5ncS";
	public static final String fedExPassword      = "pZtFhjWrKwVd1SLTK7qXGmsUo";
	*/
	
	
	
	
	
	
	//Standart_Overnight  - Air - >5000
	//Priority_Overnight  - Air - <5000
	//FEDEX_EXPRESS_SAVER - Road
	
}
