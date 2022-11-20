package console.application.v2;

public class Courses {
	private String courseId;
	private String courseName;
	private String difficultyLevel;
	private int duration;
	
	public Courses(String courseName, String difficultyLevel, int duration) {
		this.courseName = courseName;
		this.difficultyLevel = difficultyLevel;
		this.duration = duration;
	}

	public String getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public int getDuration() {
		return duration;
	}

}
