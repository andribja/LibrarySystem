package ProgAssignment4;

import java.util.ArrayList;
import java.util.Date;

abstract class Customer extends User {

	protected long maxDays;
	protected boolean blocked;
	private ArrayList<Book> loans;
	private boolean pardoned;
	
	public Customer(int id, int pin, String name, String address, long phone) {
		super(id, pin, name, address, phone);
		loans = new ArrayList<Book>();
		blocked = this.isBlocked();
		login = new LoginSession(this);
		pardoned = false;
	}
	
	public void checkOut(Book book) {
		if(blocked == false && !book.isLent()) {
			loans.add(book);
			book.checkOut(this);
		}
		else if(book.isLent()) {
			System.out.println("Book already checked out!");
		}
		else System.out.println("User blocked; cannot check out books!");
	}
	
	public void checkOut(Book book, Date date) {
		loans.add(book);
		book.checkOut(this, date);
	}
	
	public void returnBook(Book book) {
		boolean b = loans.remove(book);
		if(b == true)
			book.returnBook();
		else
			System.out.println("Book not found in user's books");
	}

	public long getMaxDays() {
		return maxDays;
	}

	public void setMaxDays(long maxDays) {
		this.maxDays = maxDays;
	}
	
	public boolean isBlocked() {
		this.updateBlocked();
		return blocked;
	}
	
	public void updateBlocked() {
		boolean isBlocked = this.hasOverdue();
		/*
		for(Book b: loans) {
			if(login.getDate().getTime() - (b.getReturnDate().getTime()) > 0)
				this.blocked = true;
		}*/
		if(pardoned) isBlocked = false;
		this.blocked = isBlocked;
	}
	
	public boolean hasOverdue() {
		boolean overdue = false;
		for(Book b: loans) {
			if(login.getDate().getTime() - (b.getReturnDate().getTime()) > 0)
				overdue = true;
		}
		return overdue;
	}
	
	public void setLoginSession(LoginSession login) {
		this.login = login;
	}

	public void setBlocked(boolean blocked) {
		if(blocked == false && this.blocked == false) {
			setPardoned(true);
		}
		if(blocked == true) {
			setPardoned(false);
		}
		this.blocked = blocked;
	}
	
	public void setPardoned(boolean b) {
		this.pardoned = b;
	}

	public ArrayList<Book> getLoans() {
		return loans;
	}

	public void setLoans(ArrayList<Book> loans) {
		this.loans = loans;
	}
	
	@Override
	public void printUserInfo() {
		super.printUserInfo();
		String s;
		if(blocked) {
			s = "No";
		}
		else{
			s = "Yes";
		}
		System.out.println("Blocked: " + s);
		System.out.println("Books checked out by this user:");
		for(Book b: loans) {
			b.printBookInfo();
		}
	}
}
