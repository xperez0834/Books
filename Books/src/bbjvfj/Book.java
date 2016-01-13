package bbjvfj;

import java.awt.Color;

public class Book{
	private String title;
	private int numberOfPages;
	private Person author;
	private int thickness;
	private int height;
	private Color color;
	private boolean checkedOut;
	private long checkOutDate;
	private long dueDate;
	String description;
	int accumulatedUse;
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getThickness() {
		return thickness;
	}
	public int getHeight() {
		return height;
	}
	public boolean isCheckedOut() {
		return checkedOut;
	}
	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}
	public long getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(long checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public long getDueDate() {
		return dueDate;
	}
	public void setDueDate(long dueDate) {
		this.dueDate = dueDate;
	}
	public Book(String title, int numberOfPages, Person author){
		this.title = title;
		this.numberOfPages = numberOfPages;
		this.author = author;
		height = (int)(Math.random()*100)+250;
		thickness = numberOfPages/5;
		color = Color.GRAY;
		this.description = conditions[0];
		this.accumulatedUse = 0;
	}
	public String getBookInfo(){
		return description;
	}
	public String toString(){
		return title +" by " + author + " Pages:" + numberOfPages; 
	}
	public String getTitle(){
		return title;
	}
	public int getNumberOfPages(){
		return numberOfPages;
	}
	public Person getAuthor(){
		return author;
	}
	public String getTitleIgnoreThe(){
		if(title.toLowerCase().indexOf("the")>-1) return title.substring(3, title.length()-1);
		return title;
	}
	public long getSecondsRemaining(){
		return (dueDate - System.currentTimeMillis() )/1000;
	}
	public void updateCondition(long timeOfReturn){
		accumulatedUse += (timeOfReturn-checkOutDate);
		if (accumulatedUse>30) description = conditions[1];
		if (accumulatedUse>1000) description = conditions[2];
		if (accumulatedUse>2000) description = conditions[3];
	}
	static public String[] conditions = {"This is a brand new book.",
		"This book has some cosmetic wear.",
		"What's this ticfky stuff inside the book",
		"This is disgusting, almost as bad as tech textbooks"};

}