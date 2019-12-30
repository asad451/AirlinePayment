package com.asad.beans;

/*
 * User Model class that stores user's card information
 * private variables has been defined along with getters and setters
 */

public class User {
	    
	private String name;    
	private String number;    
	private String cvv;
	private String exp_date;
	    
	public String getName() {    
	    return name;    
	}    

	public void setName(String name) {    
	    this.name = name;    
	}    
	
	public String getCardNumber() {    
	    return number;    
	}    
	
	public void setCardNumber(String number) {    
	    this.number = number;    
	}    
	
	public String getCVV() {    
	    return cvv;    
	}    
	
	public void setCVV(String cvv) {    
	    this.cvv = cvv;    
	}    

	public String getExpDate() {    
	    return exp_date;    
	}    
	
	public void setExpDate(String exp_date) {    
	    this.exp_date = exp_date;    
	}
} 
