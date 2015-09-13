
public class LoanData {
public static final String CAL_TYPE_DENG_E_BEN_JIN = "Jin";
public static final String CAL_TYPE_DENG_E_BEN_XI= "Xi";
	
private int year;
private String rate;
private int amount;
private String calculateType;


public int getYear() {
	return year;
}


public void setYear(int year) {
	this.year = year;
}


public String getRate() {
	return rate;
}


public void setRate(String rate) {
	this.rate = rate;
}


public int getAmount() {
	return amount;
}


public void setAmount(int amount) {
	this.amount = amount;
}


public String getCalculateType() {
	return calculateType;
}


public void setCalculateType(String caculateType) {
	calculateType = caculateType;
}

}
