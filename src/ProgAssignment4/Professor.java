package ProgAssignment4;

public class Professor extends Customer {
	
	public Professor(int id, int pin, String name, String address, long phone) {
		super(id, pin, name, address, phone);
		this.maxDays = 20;
	}
	
}