package console.application.v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ApplicationDatabase {
	private Map<String, Courses> courses = new LinkedHashMap<>();
	private Map<String, Students> students = new HashMap<>();
	private Map<String, ArrayList<Enrollment>> enrollmentDetails = new HashMap<>();
	
	private static final int SUCCESSFULLY_UPDATED = 1;
	private static final int INVALID_TIME = 2;
	
	public ApplicationDatabase() {
		
		courses.put("C1", new Courses ("Java Basics", "Low", 3));
		courses.put("C2", new Courses ("Advanced Java Tutorial", "Medium", 4));
		courses.put("C3", new Courses ("SQL developers course", "Medium", 4));
		courses.put("C4", new Courses ("DevOps Tutorial", "High", 5));
		
		students.put("S101", new Students("John", "abcd"));
		students.put("S102", new Students("Daniel", "mnop"));
		
	}
	
	public Courses getCourseDetails(String courseId) {
		return courses.get(courseId);
	}
	
	public boolean checkCourseId(String courseId) {
		return courses.containsKey(courseId);
	}
	
	public Students getStudentDetails(String studentId) {
		return students.get(studentId);
	}
	
	public boolean authenticateUser(String studentId, String loginPwd) {
		Students student = getStudentDetails(studentId);
		if(student != null) {
			if(student.getLoginPwd().equals(loginPwd)) {
				return true;
			}
		} return false;
	}
	
	public void displayAllCourses() {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.printf("%s%-18s%-30s%-20s%-2s%s","", "Course Id", "Name", "Difficulty Level", "Duration","");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------");
		for(Entry<String, Courses> course : courses.entrySet()) {
			System.out.printf("%3s%-15s%-30s%-20s%-2s%s", "   ", course.getKey(), course.getValue().getCourseName(), 
					course.getValue().getDifficultyLevel(),	course.getValue().getDuration(), "hrs");
			System.out.println();
			
		}
		
	}
	
	public boolean getEnrolledStudentId(String studentId) {
		return enrollmentDetails.containsKey(studentId);
	}
	
	public void setEnrollmentDetails(String studentId, Enrollment newEnrollment) {
		Courses course = getCourseDetails(newEnrollment.getCourseId());
		boolean courseRecordExists = false;
			if(getEnrolledStudentId(studentId)) {
				for(Enrollment courseId : enrollmentDetails.get(studentId)) {
					if(newEnrollment.getCourseId().equals(courseId.getCourseId())) {
						System.out.println("Course already exists");
						courseRecordExists = true;
					}
				} if(! courseRecordExists) {
					enrollmentDetails.get(studentId).add(newEnrollment);
					System.out.println("You are successfully enrolled in " + course.getCourseName() +"!");
					System.out.println();
				}
				
			}
			else {
				ArrayList<Enrollment> enrollment = new ArrayList<>();
				enrollment.add(newEnrollment);
				enrollmentDetails.put(studentId, enrollment);
			System.out.println("You are successfully enrolled in " + course.getCourseName() +"!");
				System.out.println();
				
			}
	}
	
	public ArrayList<Enrollment> getEnrollmentDetails(String studentId) {
		return enrollmentDetails.get(studentId);
	}
	
	public void setClockInTime(String studentId, String courseId, float timeSpent) {
		int recordUpdationStatus = 0;
		Courses courseDetail = null;
		try {
			for(Enrollment course : enrollmentDetails.get(studentId)) {
				courseDetail = getCourseDetails(course.getCourseId());
				if(courseId.equals(course.getCourseId())) {
					float newTimeSpent = course.getTimeSpent() + timeSpent;
					if(newTimeSpent <= courseDetail.getDuration()) {
						double newCompletion = ((newTimeSpent/courseDetail.getDuration())*100);
						course.setTimeSpent(newTimeSpent);
						course.setCompletion(newCompletion);
						recordUpdationStatus = SUCCESSFULLY_UPDATED;
					}
					else {
						recordUpdationStatus = INVALID_TIME;
					}
					
					
				}
				if(recordUpdationStatus != 0) {
					break;
				}
					
				}
			if(recordUpdationStatus == 1) {
				System.out.println("You have successfully clocked-in " + timeSpent + " hrs to " + courseDetail.getCourseName());
			}
			else if(recordUpdationStatus == 2) {
				System.out.println("Enter valid time spent");
			}
			else {
				System.out.println("Enter valid Course Id\n  (Note: Course Id is Case Sensitive & Ensure enrolling in Course first before Clock-in)");
			}
		}
		catch(NullPointerException e) {
			System.out.println("Enter valid Course Id\n  (Note: Course Id is Case Sensitive & Ensure enrolling in Course first before Clock-in)");
		}

	}

}
