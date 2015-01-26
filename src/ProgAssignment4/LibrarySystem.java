package ProgAssignment4;


// Name: Andri Bjarnason
// Perm: N/A
// Email: andribja@engineering.ucsb.edu

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class LibrarySystem {
	public static void main(String[] args) {
		Library library = new Library();
		library.initializeLibrary();
		Scanner sc = new Scanner(System.in);
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		User user;
		Date date;
		int pin;
		LoginSession login;
		String input = null;
		System.out.println("Commands:\nexit - Exit program\nsearch - Search for a book\n" +
				"checkout - Check out a book\nreturn - Return a book\ninfo - Show user information\n" + 
				"list - List all users' information\nsetblock - Set block status of user\n" +
				"overdue - List all users with overdue items\nlogin - Login as a customer");
		System.out.println("Enter user ID: ");
		while(sc.hasNext()){
			input = sc.nextLine();
			user = library.getUser(input);
			System.out.println("Enter date: [yyyy/mm/dd] if date doesn't match format today will be used: ");
			try {
				date = formatter.parse(sc.nextLine());
			} catch (ParseException e1) {
				date = new Date();
			}
			while(user != null) {
				System.out.println("Enter PIN: ");
				input = sc.nextLine();
				try{ pin = Integer.parseInt(input); }
				catch(NumberFormatException e) {
					pin = 0;
					System.out.println("Invalid PIN");
					continue;
				}
				while(pin == user.getPin() && input != "exit") {
					login = new LoginSession(user,date);
					System.out.println("Enter command or 'exit' to logout: ");
					input = sc.nextLine();
					if(input.equals("search")) {
						System.out.print("Enter search string: ");
						library.searchAndPrint(sc.nextLine());
					}
					if(input.equals("checkout")) {
						System.out.println("Enter book code: ");
						Book b = library.getBook(sc.nextLine());
						if(b != null)
							login.checkOut(b);
						else System.out.println("Invalid Code");
					}
					if(input.equals("return")) {
						System.out.print("Enter book code: ");
						Book b = library.getBook(sc.nextLine());
						if(b != null)
							login.returnBook(b);
						else System.out.println("Invalid code.");
					}
					if(input.equals("info")) {
						user.printUserInfo();
					}
					if(input.equals("list")) {
						if(user instanceof Librarian) 
							library.printAllUsers();
					}
					if(input.equals("setblock")) {
						if(user instanceof Librarian) {
							System.out.println("Enter user ID: ");
							String userid = sc.nextLine();
							if(userid != null) {
								User customer = library.getUser(userid);
								System.out.println("Enter block status [y/n]:");
								String blocked = sc.nextLine();
								if(customer instanceof Customer) {
									if(blocked.equals("y"))
										((Customer) customer).setBlocked(true);
									if(blocked.equals("n"))
										((Customer) customer).setBlocked(false);
								}
							}
							else System.out.println("Invalid ID");
						}
					}
					if(input.equals("overdue")) {
						if(user instanceof Librarian) {
							for(User c: library.getUsers()) {
								if(c instanceof Customer) {
									if(((Customer) c).hasOverdue())
										c.printUserInfo();
								}
							}
						}
					}
				}
			}
			System.out.println("User not found.\nEnter user ID: ");
		}
	}
}
