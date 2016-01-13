package bbjvfj;

public class Balance {

	double amount;
	double negativeAmount;
	long lastWorked;
	public Balance(){
		amount = 0;
		negativeAmount = 0;
		lastWorked = 0;
	}
	public double getAmount() {
		
		return Math.round((amount+negativeAmount) * 100.0) / 100.0; 
	}
	public double getNegativeAmount(){
		return negativeAmount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public long getLastWorked() {
		return lastWorked;
	}
	public void setLastWorked(long lastWorked) {
		this.lastWorked = lastWorked;
	}
	public void subtractLateFees(long timeOverdue){
		if (timeOverdue<0) negativeAmount+= Math.round(timeOverdue/200);
	}
	public boolean canWork(long time){
		if((time-lastWorked)/1000>10){
			return true;
		}
		return false;
	}
	public String earnMoney(long time){
		if(canWork(time)){
			amount+= 5.0;
			lastWorked = time;
			return "worked and earned 5 currency.";
		}
		return "can not do a double shifft! Wait until the first job is done!";
	}

}