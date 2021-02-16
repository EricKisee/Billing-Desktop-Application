package com.erickisee.app;

public class Methods {
	


	
	public String dateTime() {
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);
		
	}
	
	public String trustSaleStatus (int status) {
		if(status == -1)
			return "returned";
		else if (status == 1)
			return "paid";
		else
			return "pending";
	}

}
