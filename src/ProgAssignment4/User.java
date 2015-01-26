package ProgAssignment4;

abstract class User{
	protected int id;
	protected int pin;
	protected String name;
	protected String address;
	protected long phone;
	protected LoginSession login;
	
	public User(int id, int pin, String name, String address, long phone) {
		this.id = id;
		this.pin = pin;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.login = new LoginSession(this);
	}
	
	public void printUserInfo() {
		System.out.println("ID: " + id);
		System.out.println("PIN: " + pin);
		System.out.println("Name: " + name);
		System.out.println("Address: " + address);
		System.out.println("Phone: " + phone);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	public void setLoginSession(LoginSession login){
		this.login = login;
	}
	
}
