package com.entity;

public class Course {
	private String ID;
	private String Name;
	private String Lecture;
	private int Year;
	private String Note;
	private float grade;

	public Course() {

	}
	
	

	public float getGrade() {
		return grade;
	}



	public void setGrade(float grade) {
		this.grade = grade;
	}



	public Course(String iD, String name, String lecture, int year, String note, float grade) {
		super();
		ID = iD;
		Name = name;
		Lecture = lecture;
		Year = year;
		Note = note;
		this.grade = grade;
	}



	public Course(String name, String lecture, int year, String note) {
		super();
		Name = name;
		Lecture = lecture;
		Year = year;
		Note = note;
	}

	public Course(String iD, String name, String lecture, int year, String note) {
		super();
		ID = iD;
		Name = name;
		Lecture = lecture;
		Year = year;
		Note = note;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getLecture() {
		return Lecture;
	}

	public void setLecture(String lecture) {
		Lecture = lecture;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

}
