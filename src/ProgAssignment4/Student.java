package ProgAssignment4;

public class Student extends Customer {
	
	public Student(int id, int pin, String name, String address, long phone) {
		super(id, pin, name, address, phone);
		this.maxDays = 10;
	}
	
}