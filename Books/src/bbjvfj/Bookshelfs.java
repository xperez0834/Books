package bbjvfj;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
public class Bookshelfs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person author1 = new Person();
		author1.setName("Xavier","Perez");
		
		Book book1 = new Book("The Mixtape Chronicles",1000,author1);
		book1.setColor(Color.ORANGE);
		book1.setCheckedOut(false);
		book1.setCheckOutDate(0);
		book1.setDueDate(0);
		Book book2 = new Book("Fire",50,author1);
		book2.setColor(new Color(10,0,230));
		Person author2 = new Person();
		author2.setName("Chief","Sakif","Keef");
		
		Book book3 = new Book("Bang Bang",156,author2);
		book3.setColor(Color.RED);
		Book book4 = new Book(".....",152,author2);
		book4.setColor(Color.CYAN);
		
		Book[] bookArray = new Book[4];
		
		Person customer1 = new Person();
		customer1.setName("Guy", "1");
		customer1.setMale(true);
		customer1.setBalance(new Balance());
		Person customer2 = new Person();
		customer2.setName("Guy", "2");
		customer2.setMale(true);
		customer2.setBalance(new Balance());
		Person customer3 = new Person();
		customer3.setName("Guy", "3");
		customer3.setMale(true);
		customer3.setBalance(new Balance());
		customer3.checkedOutBooks = new ArrayList<Book>();
		customer2.checkedOutBooks = new ArrayList<Book>();
		customer1.checkedOutBooks = new ArrayList<Book>();
		
		
		ArrayList<Person> people = new ArrayList<Person>();
		people.add(customer1);
		people.add(customer2);
		people.add(customer3);
		
		
		ArrayList<Book> books = new ArrayList<Book>();
		//<?> means "generic type" tells Java that the objects in this 
		// ArrayList can always be treated as "Books" without "casting"
		/**
		 * Adding objects to ArrayList
		 * for arrays
		 * bookArray[0] = book1
		 * 
		 */
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);
		sortByPageNumber(false, books);
		sortByAuthor(true,books);
		sortByTitle(true,books);
		Library lib = new Library(books, people);//use "books" or "shelf" whatever your ArrayList is 

		lib.setSize(new Dimension(500,500));

		lib.setVisible(true);

		lib.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void sortByPageNumber(final boolean ascending, ArrayList<Book> list){
		Collections.sort(list, new Comparator<Book>(){
			public int compare(Book a, Book b){
				if(ascending)return a.getNumberOfPages() - b.getNumberOfPages();
				return b.getNumberOfPages() - a.getNumberOfPages();
			}
		});
	}
	public static void sortByAuthor(final boolean ascending, ArrayList<Book> list){
		Collections.sort(list, new Comparator<Book>(){
			public int compare(Book a, Book b){
				if(ascending)return a.getAuthor().getLastName().compareTo(b.getAuthor().getLastName());
				return b.getAuthor().getLastName().compareTo(a.getAuthor().getLastName());
			}
		});
	}
	public static void sortByTitle(final boolean ascending, ArrayList<Book> list){
		Collections.sort(list, new Comparator<Book>(){
			public int compare(Book a, Book b){
				if(ascending)return a.getTitleIgnoreThe().compareTo(b.getTitleIgnoreThe());
				return b.getTitleIgnoreThe().compareTo(a.getTitleIgnoreThe());
			}
		});
	}
	public static void sortByHeight(final boolean ascending, ArrayList<Book> list){
		Collections.sort(list, new Comparator<Book>(){
			public int compare(Book a, Book b){
				if(ascending)return a.getHeight() - b.getHeight();
				return b.getHeight() - a.getHeight();
			}
		});
	}

}