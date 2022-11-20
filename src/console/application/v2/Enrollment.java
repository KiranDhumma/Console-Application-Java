package console.application.v2;

import java.time.LocalDate;

public class Enrollment {
	private String studentId;
	private String courseId;
	private float timeSpent;
	private double completion;
	private LocalDate date;
	
	
	public Enrollment(String courseId, float timeSpent, double completion, LocalDate date) {
		this.courseId = courseId;
		this.timeSpent = timeSpent;
		this.completion = completion;
		this.date = date;
	}


	public float getTimeSpent() {
		return timeSpent;
	}


	public void setTimeSpent(float timeSpent) {
		this.timeSpent = timeSpent;
	}


	public double getCompletion() {
		return completion;
	}


	public void setCompletion(double completion) {
		this.completion = completion;
	}


	public String getStudentId() {
		return studentId;
	}


	public String getCourseId() {
		return courseId;
	}


	public LocalDate getDate() {
		return date;
	}
	
	
	
}
