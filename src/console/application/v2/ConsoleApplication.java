package console.application.v2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class ConsoleApplication {
	private boolean userAuthenticated = false;
	private String userId;
	private String courseId;
	private ApplicationDatabase applicationDatabase;
	private int timeSpent;
	private float completion;
	private static LocalDate date;
	
	private static final String DISPLAY_ALL_COURSES = "1";
	private static final String VIEW_ENROLLED_COURSES = "2";
	private static final String ENROLL_IN_NEW_COURSE = "3";
	private static final String CLOCK_IN_TIME = "4";
	private static final String VIEW_SUMMARY = "5";
	private static final String EXIT = "6";
	
	public ConsoleApplication() {
		applicationDatabase = new ApplicationDatabase();
		date = LocalDate.now();
		timeSpent = 0;
		completion = 0;
		
	}
	
	public void run() {
		while(true) {
			while(! userAuthenticated) {
				System.out.println("Wel-Come\n");
				authenticateUser();
			}
			performAction();
			
			userAuthenticated = false;
			userId = null;
			
			}
		}
	
	private boolean authenticateUser() {
		Scanner readId = new Scanner(System.in);
		System.out.println("Enter Student ID: ");
		String studentId = readId.nextLine();
		System.out.println("Enter Password: ");
		String loginPwd = readId.nextLine();
		userAuthenticated = applicationDatabase.authenticateUser(studentId, loginPwd);
		
		if(userAuthenticated) {
			userId = studentId;
			System.out.println("Successfully Logged in");
		}
		else {
			System.out.println("Invalid username or password. Please try again");
			System.out.println();
		} return userAuthenticated;
	}
	
	private String chooseYourOption() {
		System.out.println();
		System.out.printf("%10s", "Main Menu");
		System.out.println();
		System.out.printf("%-3d%-20s", 1, "Display all courses\n");
		System.out.printf("%-3d%-20s", 2, "View enrolled courses\n");
		System.out.printf("%-3d%-20s", 3, "Enroll in a new Course\n");
		System.out.printf("%-3d%-10s", 4, "Clock-in time\n");
		System.out.printf("%-3d%-10s", 5, "View summary\n");
		System.out.printf("%-3d%-20s", 6, "exit\n");
		System.out.println();
		
		System.out.println("Enter your option: ");
		Scanner readOption = new Scanner(System.in);
		return readOption.nextLine();


	}
	
	private void performAction() {
		boolean userExited = false;
		
			while(!userExited) {
				String mainMenuSelection = chooseYourOption();
				System.out.println();
				switch(mainMenuSelection) {
				case DISPLAY_ALL_COURSES:
					applicationDatabase.displayAllCourses();
					break;
					
				case VIEW_ENROLLED_COURSES:
					viewEnrolledCourses();
					break;
					
				case ENROLL_IN_NEW_COURSE:
					courseEnrollment();
					break;
					
				case CLOCK_IN_TIME:
					clockIntime();
					break;
					
				case VIEW_SUMMARY:
					viewSummary();
					break;
					
				case EXIT:
					System.out.println("Exiting the system");
					userExited = true;
					break;
					
				default: 
					System.out.println("You did not enter a valid selection. Try again. ");
					break;
				}
			}

	}
	
	private void courseEnrollment() {
		Scanner readLine = new Scanner(System.in);
		System.out.println("Enter Course Id: ");
		courseId = readLine.nextLine();
		
		if(applicationDatabase.checkCourseId(courseId)) {
			applicationDatabase.setEnrollmentDetails(userId,
					new Enrollment(courseId, timeSpent, completion, date));
		}
		else {
			System.out.println("Enter valid Course Id\n (Note: Course Id is Case Sensitive)");
		}
		
		
	}
	
	private void viewEnrolledCourses() {
		try {
		Students student = applicationDatabase.getStudentDetails(userId);
		
		ArrayList<Enrollment> enrolledCourseDetails = applicationDatabase.getEnrollmentDetails(userId);
		System.out.println("Student Id: " + userId);
		System.out.println("Student Name: "  + student.getStudentName());
		
		System.out.println("-------------------------------------------------------------------------------");
		System.out.printf("%s%-15s%-30s%-15s", "", "Course Id", "Course Name", "Enrollment Date");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------");
		
		for (Enrollment enrolledCourseDetail : enrolledCourseDetails) {
			Courses course = applicationDatabase.getCourseDetails(enrolledCourseDetail.getCourseId());
			System.out.printf("%3s%-12s%-30s%-15s", "   ", enrolledCourseDetail.getCourseId(), 
					course.getCourseName(), date);
			System.out.println();
		}
		}
		catch (NullPointerException e) {
			System.out.println("No courses found");
		}
	}
	
	private void clockIntime() {
		Scanner readDetail = new Scanner(System.in);
		System.out.println("Enter course Id: ");
		String courseId = readDetail.nextLine();
		System.out.println("Enter time spent in hrs: ");
		float timeSpent = readDetail.nextFloat();
		applicationDatabase.setClockInTime(userId, courseId, timeSpent);
	}
	
	private void viewSummary() {
		Students student = applicationDatabase.getStudentDetails(userId);
		
		
		if(applicationDatabase.getEnrolledStudentId(userId)) {
			ArrayList<Enrollment> enrolledCourseDetails = applicationDatabase.getEnrollmentDetails(userId);
			System.out.println("Student Id: " + userId);
			System.out.println("Student Name: "  + student.getStudentName());
			
			System.out.println("----------------------------------------------------------------------------------------------");
			System.out.printf("%s%-13s%-30s%s%-25s%s%-15s%s%-15s", "", "Course Id", "Course Name", "","Course Duration", "","Time spent","",
					"Completion %");
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------------------");
			
			for (Enrollment enrolledCourseDetail : enrolledCourseDetails) {
				Courses course = applicationDatabase.getCourseDetails(enrolledCourseDetail.getCourseId());
				System.out.printf("%3s%-10s%-30s%5s%-21s%3s%-15s%1s%-13s", "   ", enrolledCourseDetail.getCourseId(), course.getCourseName(),"   ",
						course.getDuration(),"", enrolledCourseDetail.getTimeSpent(), "", enrolledCourseDetail.getCompletion());
				System.out.println();
			}
		}
		else {
			System.out.println("You haven't enrolled in any courses");
		}
		
	}

}
