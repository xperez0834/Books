package bbjvfj;

import java.util.ArrayList;

public class Person {
	private String firstName;
	private String middleName;
	private String lastName;
	public static int MAX_ALLOWED_BOOKS = 3;
	ArrayList<Book> checkedOutBooks;
	boolean male;
	Balance balance; 
	
	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public void setName(String fN, String lN ){
		this.firstName = fN;
		this.middleName ="";
		this.lastName = lN;
	}
	
	public void setName(String fN,String Mn,String lN ){
		this.firstName = fN;
		this.middleName = Mn;
		this.lastName = lN;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String toString(){
		return firstName +" "+middleName+" "+ lastName;
		
	}
	
	public ArrayList<Book> getCheckedOutBooks() {
		return checkedOutBooks;
	}
	
	public void checkOutBook(Book book){
		book.setCheckedOut(true);
		
		book.setCheckOutDate(System.currentTimeMillis());
		book.setDueDate(System.currentTimeMillis()+30000);
		checkedOutBooks.add(book);
	}
	
	public void returnBook(Book book){
		book.setCheckedOut(false);
		book.updateCondition(System.currentTimeMillis());
		balance.subtractLateFees(book.getDueDate()-System.currentTimeMillis());
		book.setCheckOutDate(0);
		book.setDueDate(0);
		checkedOutBooks.remove(book);
	}
	
	public void renewBook(Book book){
		book.setDueDate(System.currentTimeMillis()+30000);
	}
	
	public String getGenderPosessivePronoun(){
		if(male) return "his";
		return "her";
	}
	public String getLibraryDescription(){
		return firstName +" going to borrow a book. " + firstName + "'s balance is " + balance.getAmount() ; 
	}
}