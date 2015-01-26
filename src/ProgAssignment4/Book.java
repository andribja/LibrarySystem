package ProgAssignment4;

import java.util.Date;

public class Book {
	private String magic;
	private int year;
	private String name;
	private String author;
	private String publisher;
	private String subject;
	private boolean lent;
	private Date dateLent;
	private Date returnDate;
	private Customer customer;
	private long DAY_IN_MS;

	public Book(String magic, String name, String author, String publisher, int year, String subject) {
		this.magic = magic;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.year = year;
		this.lent = false;
		this.subject = subject;
		DAY_IN_MS = 1000 * 60 * 60 * 24;
		dateLent = null;
		returnDate = null;
		customer = null;
	}
	
	public void printBookInfo() {
		System.out.println("Magic number: " + magic);
		System.out.println("Book title: " + name);
		System.out.println("Author(s): " + author);
		System.out.println("Publisher: " + publisher);
		System.out.println("Subject: " + subject);
		if(lent = true && dateLent != null && returnDate != null && customer != null) {
			System.out.println("Checked out by: " + customer.getName() + " ID: " + customer.getId());
			System.out.println("Checkout date: " + dateLent);
			System.out.println("To be returned by: " + returnDate);
		}
	}
	
	public String getMagic() {
		return magic;
	}
	
	public void setMagic(String magic) {
		this.magic = magic;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isLent() {
		return lent;
	}

	public void setLent(boolean lent) {
		this.lent = lent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void checkOut(Customer customer) {
		this.customer = customer;
		this.dateLent = new Date();
		this.lent = true;
	}
	
	public void checkOut(Customer customer, Date date) {
		this.customer = customer;
		this.dateLent = date;
		this.returnDate = new Date(date.getTime() + customer.getMaxDays()*DAY_IN_MS);
		this.lent = true;
	}
	
	public void returnBook() {
		this.customer = null;
		this.lent = false;
		this.dateLent = null;
		this.returnDate = null;
	}
	
	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		if(this.dateLent != null && this.customer != null) {
			returnDate = new Date(dateLent.getTime() + customer.getMaxDays()*DAY_IN_MS);
		}
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDateLent() {
		return dateLent;
	}
	
	public void setDateLent(Date date) {
		this.dateLent = date;
	}

}
