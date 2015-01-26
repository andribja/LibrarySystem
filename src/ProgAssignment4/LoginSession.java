package ProgAssignment4;

import java.util.Date;

public class LoginSession {
	private User user;
	private Date date;
	
	public LoginSession(User user) {
		this.user = user;
		this.date = new Date();
	}
	
	public LoginSession(User user, Date date) {
		this.user = user;
		this.date = date;
	}
	
	public void checkOut(Book book) {
		if(user instanceof Customer) {
			((Customer) user).checkOut(book,date);
		}
		else System.out.println("Checking out is for customers only.");
	}
	
	public void returnBook(Book book) {
		if(user instanceof Customer) {
			((Customer) user).returnBook(book);
		}
		else System.out.println("Returning books is for customers only.");
	}
	
	public void showUserInformation(){
		user.printUserInfo();
	}
	
	public Date getDate() {
		return date;
	}
}
