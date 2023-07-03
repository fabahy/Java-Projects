package com.entity;

public class Student {
	private String ID;
	private String Name;
	private float Grade;
	private String Birthday;
	private String Address;
	private String Note;

	public Student() {

	}

	

	public Student(String name, float grade, String birthday, String address, String note) {
		super();
		Name = name;
		Grade = grade;
		Birthday = birthday;
		Address = address;
		Note = note;
	}


	public Student(String iD, String name, float grade, String birthday, String address, String note) {
		super();
		ID = iD;
		Name = name;
		Grade = grade;
		Birthday = birthday;
		Address = address;
		Note = note;
	}



	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public float getGrade() {
		return Grade;
	}

	public void setGrade(float grade) {
		Grade = grade;
	}

	public String getBirthday() {
		return Birthday;
	}

	public void setBirthday(String birthday) {
		Birthday = birthday;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}
}
