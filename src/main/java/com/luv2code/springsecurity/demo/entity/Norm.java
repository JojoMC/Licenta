package com.luv2code.springsecurity.demo.entity;

public class Norm {

	private int id;
	private int normNumber;
	private int availableHoursCourse;
	private int availableHoursApplication;
	private String positionName;
	private String availability;
	private String subjectName;
	private String faculty;
	private String studiesType;
	
	public Norm() {
		this.availableHoursCourse = 0;
		this.availableHoursApplication = 0;
	}
	
	public int getNormNumber() {
		return normNumber;
	}
	public void setNormNumber(int normNumber) {
		this.normNumber = normNumber;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getStudiesType() {
		return studiesType;
	}
	public void setStudiesType(String studiesType) {
		this.studiesType = studiesType;
	}
	public int getHoursPerWeekCourse1() {
		return hoursPerWeekCourse1;
	}
	public void setHoursPerWeekCourse1(int hoursPerWeekCourse1) {
		this.hoursPerWeekCourse1 = hoursPerWeekCourse1;
	}
	public int getHoursPerWeekApplications1() {
		return hoursPerWeekApplications1;
	}
	public void setHoursPerWeekApplications1(int hoursPerWeekApplications1) {
		this.hoursPerWeekApplications1 = hoursPerWeekApplications1;
	}
	public int getHoursPerWeekCourse2() {
		return hoursPerWeekCourse2;
	}
	public void setHoursPerWeekCourse2(int hoursPerWeekCourse2) {
		this.hoursPerWeekCourse2 = hoursPerWeekCourse2;
	}
	public int getHoursPerWeekApplications2() {
		return hoursPerWeekApplications2;
	}
	public void setHoursPerWeekApplications2(int hoursPerWeekApplications2) {
		this.hoursPerWeekApplications2 = hoursPerWeekApplications2;
	}
	private String language;
	private String year;
	private String series;
	private int hoursPerWeekCourse1;
	private int hoursPerWeekApplications1;
	private int hoursPerWeekCourse2;
	private int hoursPerWeekApplications2;
	
	
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAvailableHoursCourse() {
		return availableHoursCourse;
	}
	public void setAvailableHoursCourse(int availableHours) {
		this.availableHoursCourse = availableHours;
	}
	public int getAvailableHoursApplication() {
		return availableHoursApplication;
	}
	public void setAvailableHoursApplication(int availableHoursApplication) {
		this.availableHoursApplication = availableHoursApplication;
	}
	
	
	
	
	
	
}
