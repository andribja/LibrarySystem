package ProgAssignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Library {
	private ArrayList<Book> books;
	private ArrayList<User> users;
	
	public Library() {
		books = new ArrayList<Book>();
		users = new ArrayList<User>();
	}
	
	public Book getBook(String magic) {
		ArrayList<Book> matches = new ArrayList<Book>();
		Book book = null;
		for(Iterator<Book> i = books.iterator(); i.hasNext(); ) {
			book = (Book) i.next();
			if(book.getMagic().toLowerCase().equals(magic.toLowerCase())){
				matches.add(book);
			}
		}
		if(!matches.isEmpty()) return matches.get(0);
		return null;
 	}
	
	public ArrayList<ArrayList<Book>> search(String s) {
		ArrayList<Book> matches = new ArrayList<Book>();
		
		Book book = null;
		for(Iterator<Book> i = books.iterator(); i.hasNext(); ) {
			book = (Book) i.next();
			if(book.getMagic().toLowerCase().equals(s.toLowerCase())){
				matches.add(book);
				continue;
			}
			if(book.getName().toLowerCase().contains(s.toLowerCase())){
				matches.add(book);
				continue;
			}
			if(book.getAuthor().toLowerCase().contains(s.toLowerCase())){
				matches.add(book);
				continue;
			}
			if(book.getPublisher().toLowerCase().contains(s.toLowerCase())){
				matches.add(book);
				continue;
			}
			if(book.getName().toLowerCase().contains(s.toLowerCase())){
				matches.add(book);
				continue;
			}
			if(book.getSubject().toLowerCase().contains(s.toLowerCase())){
				matches.add(book);
				continue;
			}
			try{
				if(book.getYear() == Integer.parseInt(s)) {
					matches.add(book);
					continue;
				}
			} catch(NumberFormatException e) {}
		}
		ArrayList<Book> checkedOutMatches = new ArrayList<Book>();
		for(Book b: books) {
			if(b.isLent()) {
				checkedOutMatches.add(book);
				matches.remove(book);
			}
		}
		ArrayList<ArrayList<Book>> totalMatches = new ArrayList<ArrayList<Book>>();
		totalMatches.add(matches);
		
		// REMOVE
		totalMatches.add(checkedOutMatches);
		for(Book b: checkedOutMatches) {
			b.printBookInfo();
		}
		return totalMatches;
	}
	
	public void searchAndPrint(String search) {
		ArrayList<ArrayList<Book>> matches = this.search(search);
		System.out.println("The following books match your search and are not checked out: \n");
		for(Book b: matches.get(0)) {
			b.printBookInfo();
		}
		
		System.out.println("\nThe following books match your search but are checked out: \n");
		for(Book b: matches.get(1)) {
			b.printBookInfo();
		}
	}
	
	public void initializeLibrary() {
		File file = new File("Library.data");
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.useDelimiter("\\s|\\DBlocked\\D");
		int scanType = 0; // 0 nothing 1 books 2 students 3 profs 4 librarians
		while(sc.hasNext()) {
			String line = sc.nextLine();
			if(line.equals("[Blocked]"))
				continue;
			if(line.equals("::::::::::")) {
				continue;
			}
			if(line.equals("Books")) {
				scanType = 1;
				continue;
			}
			else if(line.equals("Students")) {
				scanType = 2;
				continue;
			}
			else if(line.equals("Professors")) {
				scanType = 3;
				continue;
			}
			else if(line.equals("Librarians")) {
				scanType = 4;
				continue;
			}
			switch(scanType) {
				case 1:
					this.addBook(new Book(line, sc.nextLine(), sc.nextLine(), sc.nextLine(), 
							Integer.parseInt(sc.nextLine()), sc.nextLine()));
					break;
				case 2:
					Student student = new Student(Integer.parseInt(line), Integer.parseInt(sc.nextLine()), 
							sc.nextLine(), sc.nextLine(), Long.parseLong(sc.nextLine()));
					String loans = sc.nextLine();
					DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
					ArrayList<String> dates = new ArrayList<String>();
					ArrayList<String> magic = new ArrayList<String>();
					int cnt = 0;
					int pointer = 0;
					boolean date = true;
					for(int i = 0; i < loans.length(); i++) {
						if(date && loans.charAt(i) == ':') {
							dates.add(loans.substring(pointer, i));
							cnt++;
							pointer = i+1;
							date = false;
						}
						if(!date && loans.charAt(i) == ',') {
							magic.add(loans.substring(pointer,i));
							pointer = i+1;
							date = true;
						}
						if(i == loans.length()-1){
							magic.add(loans.substring(pointer, loans.length()));
						}
					}
					magic.add(loans.substring(pointer,loans.length()-1));
					for(int i = 0; i < cnt; i++) {
						try {
							student.checkOut(this.getBook(magic.get(i)), formatter.parse(dates.get(i)));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					this.addUser(student);
					break;
				case 3: 
					Professor prof = new Professor(Integer.parseInt(line), Integer.parseInt(sc.nextLine()), 
							sc.nextLine(), sc.nextLine(), Long.parseLong(sc.nextLine()));
					String loans1 = sc.nextLine();
					DateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
					ArrayList<String> dates1 = new ArrayList<String>();
					ArrayList<String> magic1 = new ArrayList<String>();
					int cnt1 = 0;
					int pointer1 = 0;
					boolean date1 = true;
					for(int i = 0; i < loans1.length(); i++) {
						if(date1 && loans1.charAt(i) == ':') {
							dates1.add(loans1.substring(pointer1, i));
							cnt1++;
							pointer1 = i+1;
							date1 = false;
						}
						if(!date1 && loans1.charAt(i) == ',') {
							magic1.add(loans1.substring(pointer1,i));
							pointer1 = i+1;
							date1 = true;
						}
						if(i == loans1.length()-1){
							magic1.add(loans1.substring(pointer1, loans1.length()));
						}
					}
					magic1.add(loans1.substring(pointer1,loans1.length()-1));
					for(int i = 0; i < cnt1; i++) {
						try {
							prof.checkOut(this.getBook(magic1.get(i)), formatter1.parse(dates1.get(i)));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					this.addUser(prof);
					break;
				case 4:
					Librarian librarian = new Librarian(Integer.parseInt(line), Integer.parseInt(sc.nextLine()), 
						sc.nextLine(), sc.nextLine(), Long.parseLong(sc.nextLine()));
					this.addUser(librarian);
				default: break;
			}
		}	
	}
	
	public User getUser(String id) {
		int intID;
		try{ intID = Integer.parseInt(id); }
		catch(NumberFormatException e){ intID = 0; }
		if(intID != 0) {
			User user;
			ArrayList<User> match = new ArrayList<User>();
			for(Iterator<User> i = users.iterator(); i.hasNext(); ) {
				user = (User) i.next();
				if(user.getId() == intID){
					match.add(user);
				}
			}
			if(!match.isEmpty()) return match.get(0);
		}
		return null;
	}
	
	public void addBook(Book book) {
		books.add(book);
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public void printAllUsers() {
		for(User user: users) {
			user.printUserInfo();
		}
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public void printAllBooks() {
		for(Book book: books) {
			book.printBookInfo();
			System.out.println("\n");
		}
	}
}
