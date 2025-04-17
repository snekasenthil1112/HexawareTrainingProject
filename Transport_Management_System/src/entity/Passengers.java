package entity;

public class Passengers {
	private int passengerID;
	private String firstName;
	private String gender;
	private int age;
	private String email;
	private int phoneNumber;
	
	public Passengers () {}
	public Passengers(int passengerID, String firstName, String gender, int age, String email, int phoneNumber) {
		this.setPassengerID(passengerID);
		this.setFirstName(firstName);
		this.setGender(gender);
		this.setAge(age);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
	}
	public int getPassengerID() {
		return passengerID;
	}
	public void setPassengerID(int passengerID) {
		this.passengerID = passengerID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
