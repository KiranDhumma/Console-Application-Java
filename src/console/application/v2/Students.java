package console.application.v2;

public class Students {
	private String studentId;
	private String studentName;
	private String loginPwd;
	
	public Students(String studentName, String loginPwd) {
		this.studentName = studentName;
		this.loginPwd = loginPwd;
	}

	public String getStudentId() {
		return studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}
	
}
